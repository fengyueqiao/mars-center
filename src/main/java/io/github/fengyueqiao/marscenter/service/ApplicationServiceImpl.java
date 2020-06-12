package io.github.fengyueqiao.marscenter.service;

import io.github.fengyueqiao.marscenter.client.api.ApplicationServiceI;
import io.github.fengyueqiao.marscenter.client.dto.DeployVersionDTO;
import io.github.fengyueqiao.marscenter.client.req.DeployApplicationReq;
import io.github.fengyueqiao.marscenter.client.req.UploadApplicationReq;
import io.github.fengyueqiao.marscenter.common.dto.Response;
import io.github.fengyueqiao.marscenter.common.dto.SingleResponse;
import io.github.fengyueqiao.marscenter.common.exception.ErrorCode;
import io.github.fengyueqiao.marscenter.common.util.FileZipUtil;
import io.github.fengyueqiao.marscenter.config.MarsConfig;
import io.github.fengyueqiao.marscenter.infrastructure.tunnel.database.ApplicationTunnel;
import io.github.fengyueqiao.marscenter.infrastructure.tunnel.database.DeployVersionTunnel;
import io.github.fengyueqiao.marscenter.infrastructure.tunnel.database.TemplateTunnel;
import io.github.fengyueqiao.marscenter.infrastructure.tunnel.database.dataobject.ApplicationDO;
import io.github.fengyueqiao.marscenter.infrastructure.tunnel.database.dataobject.DeployVersionDO;
import io.github.fengyueqiao.marscenter.infrastructure.tunnel.database.dataobject.TemplateDO;
import io.github.fengyueqiao.marscenter.infrastructure.tunnel.file.LocalFileTunnel;
import io.github.fengyueqiao.marscenter.infrastructure.tunnel.http.MarsNodeTunnel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/9/3 0003.
 */

@Service
public class ApplicationServiceImpl implements ApplicationServiceI {

    private Logger logger = LoggerFactory.getLogger(ApplicationServiceI.class);

    @Autowired
    MarsConfig  marsConfig;

    @Autowired
    LocalFileTunnel localFileTunnel;

    @Autowired
    ApplicationTunnel applicationTunnel;

    @Autowired
    DeployVersionTunnel deployVersionTunnel;

    @Autowired
    MarsNodeTunnel marsNodeTunnel;

    @Autowired
    TemplateTunnel templateTunnel;

    @Override
    public Response deployApplication(DeployApplicationReq req) {

        // 获取应用信息
        ApplicationDO applicationDO = applicationTunnel.getByName(req.getAppName());
        if(applicationDO == null) {
            return SingleResponse.buildFailure(ErrorCode.E_Node_requestParamError);
        }

        // 获取模板信息
        TemplateDO templateDO = templateTunnel.getById(applicationDO.getTemplateId());
        if(templateDO == null) {
            return SingleResponse.buildFailure(ErrorCode.E_Node_requestParamError);
        }

        // 获取部署信息
        DeployVersionDO deployVersionDO = deployVersionTunnel.getById(req.getDeployVersionId());
        if(deployVersionDO == null) {
            return SingleResponse.buildFailure(ErrorCode.E_Node_requestParamError);
        }
        String fileUrl = "http://192.168.10.173:9000/mars/v1/download_app/" + deployVersionDO.getTgzName();

        // 调用mars-node接口部署应用
//        String scriptTemplate = templateDO.getProfile().replaceAll("\r\n", "\n"); // DOS2UNIX

        Map<String, String> placeHolderMap = new HashMap<>();
        placeHolderMap.put("appName", applicationDO.getAppName());
        boolean isSuccess = marsNodeTunnel.deployApp("http://192.168.9.152:9001/mars/node/v1/deploy_app",
                req.getAppName(), templateDO.getProfile(), placeHolderMap, fileUrl, req.isAutoStart());
        if(isSuccess) {
            return Response.buildSuccess();
        } else {
            return Response.buildFailure(ErrorCode.E_Node_reqNodeError);
        }
    }

    @Override
    public SingleResponse<DeployVersionDTO> uploadApplication(UploadApplicationReq req) {
        logger.info(marsConfig.getTmpUploadPath());

        // 检查请求参数
        if(req.getAppName().isEmpty()) {
            return SingleResponse.buildFailure(ErrorCode.E_Node_requestParamError);
        }
        ApplicationDO applicationDO = applicationTunnel.getByName(req.getAppName());
        if(applicationDO == null) {
            return SingleResponse.buildFailure(ErrorCode.E_Node_requestParamError);
        }

        // 作为临时文件，保存上传文件
        String timeStamp = Long.toString(System.currentTimeMillis()/1000);

        String tempDirPath = marsConfig.getTmpUploadPath() + File.separatorChar + req.getAppName() + timeStamp;
        if(!localFileTunnel.createDirIfAbsent(tempDirPath)) {
            return SingleResponse.buildFailure(ErrorCode.E_Node_fileIOError);
        }
        String tempFilePath = tempDirPath + File.separatorChar + req.getAppName() + localFileTunnel.getFileSuffix(req.getFile().getOriginalFilename());

        // 保存文件内容到指定文件
        if(!localFileTunnel.saveFile(tempFilePath, req.getFile())) {
            return SingleResponse.buildFailure(ErrorCode.E_Node_fileIOError);
        }

        // 文件压缩，存储到版本管理目录
        if(!localFileTunnel.createDirIfAbsent(marsConfig.getDeployPath())) {
            return SingleResponse.buildFailure(ErrorCode.E_Node_fileIOError);
        }

        String tgzFileName = req.getAppName() + System.currentTimeMillis()/1000 + ".zip";
        String tgzFilePath = marsConfig.getDeployPath() + File.separator  + tgzFileName;
        logger.info("compress file path is " + tgzFilePath);
        FileZipUtil.zipFiles(tempFilePath, tgzFilePath);
        localFileTunnel.deleteFile(tempFilePath);
        localFileTunnel.deleteFile(tempDirPath);

        // 保存发布版本信息
        DeployVersionDO deployVersionDO = new DeployVersionDO();
        deployVersionDO.setAppId(applicationDO.getId());
        deployVersionDO.setTgzName(tgzFileName);
        deployVersionDO.setFileName(req.getFile().getOriginalFilename());
        deployVersionDO.setVersion(req.getVersion());
        deployVersionTunnel.create(deployVersionDO);
        logger.info("version_id: " + deployVersionDO.getId());

        DeployVersionDTO deployVersionDTO = new DeployVersionDTO();
        deployVersionDTO.setId(deployVersionDO.getId());
        return SingleResponse.of(deployVersionDTO);
    }

    @Override
    public Response downloadApplication(String fileName, OutputStream outputStream) {

        String filePath = marsConfig.getDeployPath() + File.separator + fileName;
        localFileTunnel.readFile(filePath, outputStream);

        return Response.buildSuccess();
    }
}
