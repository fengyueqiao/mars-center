package io.github.fengyueqiao.marscenter.dao.http;

import com.alibaba.fastjson.JSON;
import io.github.fengyueqiao.marscenter.common.dto.MultiResponse;
import io.github.fengyueqiao.marscenter.common.dto.Response;
import io.github.fengyueqiao.marscenter.common.utils.HttpUtil;
import io.github.fengyueqiao.marscenter.dao.http.dto.req.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2019/9/10 0010.
 */

@Repository
@Slf4j
public class MarsNodeTunnel {

    private final static String START_APP_INSTANCE_PATH = "/api/app/v1/startAppInstance";
    private final static String STOP_APP_INSTANCE_PATH = "/api/app/v1/stopAppInstance";
    private final static String DEPLOY_APP_INSTANCE_PATH = "/api/app/v1/deployAppInstance";
    private final static String DESTROY_APP_INSTANCE_PATH = "/api/app/v1/destroyAppInstance";

    public Boolean startAppInstance(String nodeEndpoint, String appName) {
        log.info("startAppInstance");
        AppInstanceStartReq req = new AppInstanceStartReq();
        req.setAppName(appName);
        String url = nodeEndpoint + START_APP_INSTANCE_PATH;
        String param = JSON.toJSONString(req);
        try {
            String rspStr = HttpUtil.sendPostRequestReturnStr(url, param);
            log.info("reportNodeStatus url:{}, param:{}, rsp: {}", url, param, rspStr);
            Response rsp = JSON.parseObject(rspStr, MultiResponse.class);
            return rsp.isSuccess();
        } catch (Exception ex) {
            log.error("reportNodeStatus error url:{}, param:{}", url, param, ex);
        }
        return true;
    }

    public Boolean stopAppInstance(String nodeEndpoint, String appName) {
        log.info("stopAppInstance");
        AppInstanceStopReq req = new AppInstanceStopReq();
        req.setAppName(appName);
        String url = nodeEndpoint + STOP_APP_INSTANCE_PATH;
        String param = JSON.toJSONString(req);
        try {
            String rspStr = HttpUtil.sendPostRequestReturnStr(url, param);
            log.info("reportNodeStatus url:{}, param:{}, rsp: {}", url, param, rspStr);
            Response rsp = JSON.parseObject(rspStr, MultiResponse.class);
            return rsp.isSuccess();
        } catch (Exception ex) {
            log.error("reportNodeStatus error url:{}, param:{}", url, param, ex);
        }
        return true;
    }

    public Boolean destroyAppInstance(String nodeEndpoint, String appName) {
        log.info("destroyAppInstance");
        AppInstanceStopReq req = new AppInstanceStopReq();
        req.setAppName(appName);
        String url = nodeEndpoint + DESTROY_APP_INSTANCE_PATH;
        String param = JSON.toJSONString(req);
        try {
            String rspStr = HttpUtil.sendPostRequestReturnStr(url, param);
            log.info("reportNodeStatus url:{}, param:{}, rsp: {}", url, param, rspStr);
            Response rsp = JSON.parseObject(rspStr, MultiResponse.class);
            return rsp.isSuccess();
        } catch (Exception ex) {
            log.error("reportNodeStatus error url:{}, param:{}", url, param, ex);
        }
        return true;
    }

    public Boolean deployAppInstance(String nodeEndpoint, AppInstanceDeployReq req) {
        log.info("deployAppInstance");
        String url = nodeEndpoint + DEPLOY_APP_INSTANCE_PATH;
        String param = JSON.toJSONString(req);
        try {
            String rspStr = HttpUtil.sendPostRequestReturnStr(url, param);
            log.info("reportNodeStatus url:{}, param:{}, rsp: {}", url, param, rspStr);
            Response rsp = JSON.parseObject(rspStr, MultiResponse.class);
            return rsp.isSuccess();
        } catch (Exception ex) {
            log.error("reportNodeStatus error url:{}, param:{}", url, param, ex);
        }
        return true;
    }
}
