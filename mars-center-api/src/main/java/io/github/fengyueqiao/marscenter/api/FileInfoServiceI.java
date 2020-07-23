package io.github.fengyueqiao.marscenter.api;

import io.github.fengyueqiao.marscenter.api.dto.FileInfo;
import io.github.fengyueqiao.marscenter.api.dto.FileInfoCreateReq;
import io.github.fengyueqiao.marscenter.api.req.FileInfoDownloadReq;
import io.github.fengyueqiao.marscenter.api.req.FileInfoUploadReq;
import io.github.fengyueqiao.marscenter.common.dto.Response;
import io.github.fengyueqiao.marscenter.common.dto.SingleResponse;

/**
 * @author QinWei on 2020/7/10 0010.
 */

public interface FileInfoServiceI {
    /**
     * 创建文件，文件通过其他服务已经上传
     */
    SingleResponse<FileInfo> createFileInfo(FileInfoCreateReq req);
    /**
     * 上传文件
     */
    SingleResponse<FileInfo> uploadFileInfo(FileInfoUploadReq req);
    /**
     * 下载文件
     */
    Response downloadFileInfo(FileInfoDownloadReq req);
}
