package io.github.fengyueqiao.marscenter.controller;

import io.github.fengyueqiao.marscenter.client.api.ApplicationServiceI;
import io.github.fengyueqiao.marscenter.client.dto.DeployVersionDTO;
import io.github.fengyueqiao.marscenter.client.req.DeployApplicationReq;
import io.github.fengyueqiao.marscenter.client.req.UploadApplicationReq;
import io.github.fengyueqiao.marscenter.common.dto.Response;
import io.github.fengyueqiao.marscenter.common.dto.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2019/9/3 0003.
 */

@RestController
public class ApplicationController {
    @Autowired
    private ApplicationServiceI applicationService;

    @PostMapping(value = "/mars/v1/deploy_app")
    public Response deployApp(@RequestBody DeployApplicationReq req ){
        return applicationService.deployApplication(req);
    }

    @PostMapping(value = "/mars/v1/upload_app")
    public SingleResponse<DeployVersionDTO> uploadApp(@RequestParam("app") String app,
                                                      @RequestParam("version") String version,
                                                      @RequestParam("file") MultipartFile file){
        UploadApplicationReq uploadApplicationReq = new UploadApplicationReq();
        uploadApplicationReq.setAppName(app);
        uploadApplicationReq.setVersion(version);
        uploadApplicationReq.setFile(file);
        return applicationService.uploadApplication(uploadApplicationReq);
    }

    @PostMapping(value = "/mars/v1/download_app")
    public SingleResponse<DeployVersionDTO> downloadApp(@RequestParam("app") String app,
                                                      @RequestParam("version") String version,
                                                      @RequestParam("file") MultipartFile file){
        UploadApplicationReq uploadApplicationReq = new UploadApplicationReq();
        uploadApplicationReq.setAppName(app);
        uploadApplicationReq.setVersion(version);
        uploadApplicationReq.setFile(file);
        return applicationService.uploadApplication(uploadApplicationReq);
    }

    @RequestMapping(value = "/mars/v1/download_app/{fileName}",method=RequestMethod.GET)
    @ResponseBody
    public void downloadApp(@PathVariable String fileName, HttpServletResponse response) {
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));

            applicationService.downloadApplication(fileName, response.getOutputStream());
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }


//    @PostMapping(value = "/service/startService")
//    public Response startService(@RequestBody String serviceName){
//        StartServiceReq startServiceReq = new StartServiceReq();
//        startServiceReq.setServiceName(serviceName);
//        return serviceService.startService(startServiceReq);
//    }
}