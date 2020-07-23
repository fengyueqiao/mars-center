package io.github.fengyueqiao.marscenter.service;

import io.github.fengyueqiao.marscenter.api.FileInfoServiceI;
import io.github.fengyueqiao.marscenter.api.dto.FileInfo;
import io.github.fengyueqiao.marscenter.api.dto.FileInfoCreateReq;
import io.github.fengyueqiao.marscenter.api.req.FileInfoDownloadReq;
import io.github.fengyueqiao.marscenter.api.req.FileInfoUploadReq;
import io.github.fengyueqiao.marscenter.common.Constants;
import io.github.fengyueqiao.marscenter.common.dto.Response;
import io.github.fengyueqiao.marscenter.common.dto.SingleResponse;
import io.github.fengyueqiao.marscenter.common.exception.ErrorCode;
import io.github.fengyueqiao.marscenter.common.utils.CommonUtil;
import io.github.fengyueqiao.marscenter.common.utils.FileUtil;
import io.github.fengyueqiao.marscenter.dao.database.FileInfoTunnel;
import io.github.fengyueqiao.marscenter.dao.database.dataobject.FileInfoDO;
import io.github.fengyueqiao.marscenter.dao.file.LocalFileTunnel;
import io.github.fengyueqiao.marscenter.dao.config.FileConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author QinWei on 2020/7/13 0013.
 */

@Service
public class FileInfoServiceImpl implements FileInfoServiceI {

    @Autowired
    LocalFileTunnel localFileTunnel;

    @Autowired
    FileInfoTunnel fileInfoTunnel;

    @Autowired
    FileConfig fileConfig;


    /**
     * 创建文件，只保存到数据库，文件数据访问由其他服务完成
     */
    @Override
    public SingleResponse<FileInfo> createFileInfo(FileInfoCreateReq req) {
        // 检查请求参数
        if(StringUtils.isEmpty(req.getName())) {
            return SingleResponse.buildFailure(ErrorCode.E_RequestParameterError.getErrCode(), "文件名称不能为空");
        }
        if(StringUtils.isEmpty(req.getUrl())) {
            return SingleResponse.buildFailure(ErrorCode.E_RequestParameterError.getErrCode(), "路径名不能为空");
        }

        // 保存文件到数据库
        FileInfoDO fileInfoDO = new FileInfoDO();
        fileInfoDO.setName(req.getName());
        fileInfoDO.setPath(req.getUrl());
        fileInfoDO.setSize(req.getSize());
        fileInfoDO.setIsExist(Constants.YES);
        fileInfoDO.setIsZip(Constants.NO);
        fileInfoDO.setMd5sum("");
        fileInfoDO.setIsFullPath(Constants.YES);
        fileInfoTunnel.create(fileInfoDO);

        // 构造返回值
        FileInfo fileInfo = new FileInfo();
        BeanUtils.copyProperties(fileInfoDO, fileInfo);

        return SingleResponse.of(fileInfo);
    }
    /**
     * 上传文件
     */
    @Override
    public SingleResponse<FileInfo> uploadFileInfo(FileInfoUploadReq req) {
        // 检查请求参数
        if(StringUtils.isEmpty(req.getName())) {
            return SingleResponse.buildFailure(ErrorCode.E_RequestParameterError.getErrCode(), "文件名称不能为空");
        }
        if(StringUtils.isEmpty(req.getDir())) {
            return SingleResponse.buildFailure(ErrorCode.E_RequestParameterError.getErrCode(), "路径名不能为空");
        } else if(req.getDir().length() > 0 && !StringUtils.isAlphanumeric(req.getDir())){
            return SingleResponse.buildFailure(ErrorCode.E_RequestParameterError.getErrCode(), "路径名必须是字母和数字组成");
        }

        // 生成本地随机文件名
        String fileExt = FileUtil.getExtention(req.getName());
        String localFileName = CommonUtil.genUniCode();
        if(!fileExt.isEmpty()) {
            localFileName += fileExt;
        }

        // 文件目录若不存在则创建
        String localDirPath = fileConfig.getFileDir() + FileConfig.SEPARATOR_CHAR+ req.getDir();
        if(!localFileTunnel.createDirIfAbsent(localDirPath)) {
            return SingleResponse.buildFailure(ErrorCode.E_FileIOError);
        }

        // 保存文件到本地路径
        String localFilePath = localDirPath + FileConfig.SEPARATOR_CHAR + localFileName;
        if(!localFileTunnel.saveFile(localFilePath, req.getInputStream())) {
            return SingleResponse.buildFailure(ErrorCode.E_FileIOError);
        }

        // 保存文件到数据库
        FileInfoDO fileInfoDO = new FileInfoDO();
        fileInfoDO.setName(req.getName());
        fileInfoDO.setPath(req.getDir() + FileConfig.SEPARATOR_CHAR + localFileName);
        fileInfoDO.setSize(req.getSize());
        fileInfoDO.setIsExist(Constants.YES);
        fileInfoDO.setIsZip(Constants.NO);
        fileInfoDO.setMd5sum("");
        fileInfoDO.setIsFullPath(Constants.NO);
        fileInfoTunnel.create(fileInfoDO);

        // 构造返回值
        FileInfo fileInfo = new FileInfo();
        BeanUtils.copyProperties(fileInfoDO, fileInfo);

        return SingleResponse.of(fileInfo);
    }
    /**
     * 下载文件
     */
    @Override
    public Response downloadFileInfo(FileInfoDownloadReq req) {
        // 检查参数
        if(req.getPath().contains(Constants.PARENT_DIR)) {
            return Response.buildFailure(ErrorCode.E_RequestParameterError);
        }
        // 构造本地目录
        StringBuilder localFilePath = new StringBuilder();
        localFilePath.append(fileConfig.getFileDir());
        localFilePath.append(FileConfig.SEPARATOR_CHAR);
        localFilePath.append(req.getPath());

        // 读取本地文件
        if (localFileTunnel.readFile(localFilePath.toString(), req.getOutputStream())) {
            return Response.buildSuccess();
        } else {
            return Response.buildFailure(ErrorCode.E_RequestNotExist);
        }
    }
}
