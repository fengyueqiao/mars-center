package io.github.fengyueqiao.marscenter.web;

import io.github.fengyueqiao.marscenter.api.FileInfoServiceI;
import io.github.fengyueqiao.marscenter.api.dto.FileInfo;
import io.github.fengyueqiao.marscenter.api.dto.FileInfoCreateReq;
import io.github.fengyueqiao.marscenter.api.req.FileInfoDownloadReq;
import io.github.fengyueqiao.marscenter.api.req.FileInfoUploadReq;
import io.github.fengyueqiao.marscenter.common.dto.Response;
import io.github.fengyueqiao.marscenter.common.dto.SingleResponse;
import io.github.fengyueqiao.marscenter.dao.config.FileConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author QinWei on 2020/7/14 0014.
 */

@Slf4j
@RestController
    @RequestMapping("/api/file/v1")
public class FileInfoController {
    @Autowired
    FileInfoServiceI fileInfoService;


    @PostMapping(value = "/createFile")
    public SingleResponse<FileInfo> createFileInfo(@RequestBody FileInfoCreateReq req) {
        return fileInfoService.createFileInfo(req);
    }

    @PostMapping(value = "/uploadFile")
    public SingleResponse<FileInfo> uploadFileInfo(@RequestParam("pathName") String pathName,
                                                      @RequestParam("file") MultipartFile file) throws IOException {
        FileInfoUploadReq req = new FileInfoUploadReq();
        req.setName(file.getOriginalFilename());
        req.setDir(pathName);
        req.setSize(file.getSize());
        req.setInputStream(file.getInputStream());

        return fileInfoService.uploadFileInfo(req);
    }

    @RequestMapping(value = "/f/{pathName}/{fileName}",method= RequestMethod.GET)
    @ResponseBody
    public void getFile(@PathVariable String pathName, @PathVariable String fileName, HttpServletResponse response) {
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
            FileInfoDownloadReq req = new FileInfoDownloadReq();
            req.setPath(pathName + FileConfig.SEPARATOR_CHAR + fileName);
            req.setOutputStream(response.getOutputStream());
            Response rsp = fileInfoService.downloadFileInfo(req);
            if(!rsp.isSuccess()) {
                response.setStatus(HttpStatus.NOT_FOUND.value());
            }
        } catch (Exception ex) {
            log.error("downloadFileElement:", ex);
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
    }

    @RequestMapping(value = "/downloadFile",method= RequestMethod.GET)
    @ResponseBody
    public void downloadFileInfo(@RequestParam String filePath, @RequestParam String fileName, HttpServletResponse response) {
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
            FileInfoDownloadReq qry = new FileInfoDownloadReq();
            qry.setPath(filePath);
            qry.setOutputStream(response.getOutputStream());
            Response rsp = fileInfoService.downloadFileInfo(qry);
            if(!rsp.isSuccess()) {
                response.setStatus(HttpStatus.NOT_FOUND.value());
            }
        } catch (Exception ex) {
            log.error("downloadFileElement:", ex);
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
    }

}
