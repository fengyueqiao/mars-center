package io.github.fengyueqiao.marscenter.client.api;


import io.github.fengyueqiao.marscenter.client.dto.DeployVersionDTO;
import io.github.fengyueqiao.marscenter.client.req.*;
import io.github.fengyueqiao.marscenter.common.dto.Response;
import io.github.fengyueqiao.marscenter.common.dto.SingleResponse;

import java.io.OutputStream;

public interface ApplicationServiceI {

//    // 应用发布历史版本列表
//    public MultiResponse<ApplicationDTO> listApplication(ListApplicationReq req);
//
//    // 创建应用
//    public Response createApplication(CreateApplicationReq req);
//
//    // 删除应用
//    public Response deleteApplication(DeleteApplicationReq req);

    // 上传应用版本
    public SingleResponse<DeployVersionDTO> uploadApplication(UploadApplicationReq req);

    // 下载应用版本
    public Response downloadApplication(String fileName, OutputStream req);

    // 发布应用
    public Response deployApplication(DeployApplicationReq req);

//    // 应用发布历史版本列表
//    public MultiResponse<DeployVersionDTO> listDeployVersion(ListDeployVersionReq req);
//
//    // 回滚应用
//    public Response rollbackApplication(RollbackApplicationReq req);
//
//    // 启动应用
//    public Response startApplication(StartApplicationReq req);
//
//    // 关闭应用
//    public Response stopApplication(StopApplicationReq req);

}
