package io.github.fengyueqiao.marscenter.web;

import io.github.fengyueqiao.marscenter.api.AppInstanceServiceI;
import io.github.fengyueqiao.marscenter.api.dto.AppInstance;
import io.github.fengyueqiao.marscenter.api.req.AppInstanceListReq;
import io.github.fengyueqiao.marscenter.api.req.AppInstanceStartReq;
import io.github.fengyueqiao.marscenter.api.req.AppInstanceStopReq;
import io.github.fengyueqiao.marscenter.common.dto.MultiResponse;
import io.github.fengyueqiao.marscenter.common.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author QinWei on 2020/7/14 0014.
 */

@RestController
@RequestMapping("/api/instance/v1")
public class AppInstanceController {
    @Autowired
    AppInstanceServiceI appInfoService;

    @PostMapping(value = "/listAppInstance")
    public MultiResponse<AppInstance> listAppInstance(@RequestBody AppInstanceListReq req) {
        return appInfoService.listAppInstance(req);
    }

    @PostMapping(value = "/startAppInstance")
    public Response startAppInstance(@RequestBody List<String> idList) {
        AppInstanceStartReq req = new AppInstanceStartReq();
        req.setIdList(idList);
        return appInfoService.startAppInstance(req);
    }

    @PostMapping(value = "/stopAppInstance")
    public Response stopAppInstance(@RequestBody List<String> idList) {
        AppInstanceStopReq req = new AppInstanceStopReq();
        req.setIdList(idList);
        return appInfoService.stopAppInstance(req);
    }
}
