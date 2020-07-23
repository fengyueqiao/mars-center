package io.github.fengyueqiao.marscenter.web;

import io.github.fengyueqiao.marscenter.api.AppInfoServiceI;
import io.github.fengyueqiao.marscenter.api.dto.AppInfo;
import io.github.fengyueqiao.marscenter.api.req.*;
import io.github.fengyueqiao.marscenter.common.dto.MultiResponse;
import io.github.fengyueqiao.marscenter.common.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author QinWei on 2020/7/14 0014.
 */

@Slf4j
@RestController
@RequestMapping("/api/app/v1")
public class AppInfoController {
    @Autowired
    AppInfoServiceI appInfoService;

    @PostMapping(value = "/createApp")
    public Response createAppInfo(@RequestBody AppInfoCreateReq req) {
        return appInfoService.createAppInfo(req);
    }

    @PostMapping(value = "/updateApp")
    public Response updateAppInfo(@RequestBody AppInfoUpdateReq req) {
        return appInfoService.updateAppInfo(req);
    }

    @PostMapping(value = "/deleteApp")
    public Response deleteAppInfo(@RequestBody AppInfoDeleteReq req) {
        return appInfoService.deleteAppInfo(req);
    }

    @PostMapping(value = "/listApp")
    public MultiResponse<AppInfo> listAppInfo(@RequestBody AppInfoListReq req) {
        return appInfoService.listAppInfo(req);
    }
}
