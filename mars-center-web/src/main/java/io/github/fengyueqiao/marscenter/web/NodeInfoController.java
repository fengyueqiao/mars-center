package io.github.fengyueqiao.marscenter.web;

import io.github.fengyueqiao.marscenter.api.NodeInfoServiceI;
import io.github.fengyueqiao.marscenter.api.dto.NodeInfo;
import io.github.fengyueqiao.marscenter.api.req.NodeInfoDeleteReq;
import io.github.fengyueqiao.marscenter.api.req.NodeInfoListReq;
import io.github.fengyueqiao.marscenter.api.req.NodeStatusReportReq;
import io.github.fengyueqiao.marscenter.common.dto.MultiResponse;
import io.github.fengyueqiao.marscenter.common.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author QinWei on 2020/7/16 0016.
 */

@RestController
@RequestMapping("/api/node/v1")
public class NodeInfoController {
    @Autowired
    NodeInfoServiceI nodeInfoService;

    @PostMapping(value = "/reportNodeStatus")
    public Response reportNodeStatus(@RequestBody NodeStatusReportReq req) {
        return nodeInfoService.reportNodeStatus(req);
    }

    @PostMapping(value = "/deleteNode")
    public Response deleteNodeInfo(@RequestBody NodeInfoDeleteReq req) {
        return nodeInfoService.deleteNodeInfo(req);
    }

    @PostMapping(value = "/listNode")
    public MultiResponse<NodeInfo> listNodeInfo(@RequestBody NodeInfoListReq req) {
        return nodeInfoService.listNodeInfo(req);
    }
}
