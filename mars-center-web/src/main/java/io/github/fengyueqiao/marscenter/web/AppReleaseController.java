package io.github.fengyueqiao.marscenter.web;

import io.github.fengyueqiao.marscenter.api.AppInstanceServiceI;
import io.github.fengyueqiao.marscenter.api.AppReleaseServiceI;
import io.github.fengyueqiao.marscenter.api.dto.AppInstance;
import io.github.fengyueqiao.marscenter.api.dto.AppRelease;
import io.github.fengyueqiao.marscenter.api.req.*;
import io.github.fengyueqiao.marscenter.common.dto.MultiResponse;
import io.github.fengyueqiao.marscenter.common.dto.Response;
import io.github.fengyueqiao.marscenter.common.dto.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author QinWei on 2020/7/16 0016.
 */
@RestController
@RequestMapping("/api/release/v1")
public class AppReleaseController {
    @Autowired
    AppReleaseServiceI appReleaseService;

    @PostMapping(value = "/createAppRelease")
    public SingleResponse<AppRelease> createAppRelease(@RequestBody AppReleaseCreateReq req) {
        return appReleaseService.createAppRelease(req);
    }

    @PostMapping(value = "/deleteAppRelease")
    public Response deleteAppRelease(@RequestBody AppReleaseDeleteReq req) {
        return appReleaseService.deleteAppRelease(req);
    }

    @PostMapping(value = "/publishAppRelease")
    public Response publishAppRelease(@RequestBody AppReleasePublishReq req) {
        return appReleaseService.publishAppRelease(req);
    }

    @PostMapping(value = "/listAppRelease")
    public MultiResponse<AppRelease> listAppRelease(@RequestBody AppReleaseListReq req) {
        return appReleaseService.listAppRelease(req);
    }

}
