package io.github.fengyueqiao.marscenter.infrastructure.tunnel.http;


import com.alibaba.fastjson.JSON;
import io.github.fengyueqiao.marscenter.common.dto.Response;
import io.github.fengyueqiao.marscenter.common.util.HttpUtil;
import io.github.fengyueqiao.marscenter.infrastructure.tunnel.file.LocalFileTunnel;
import io.github.fengyueqiao.marscenter.infrastructure.tunnel.http.dataobject.DeployApplicationReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Administrator on 2019/9/12 0012.
 */

@Component
public class MarsNodeTunnel {
    private Logger logger = LoggerFactory.getLogger(LocalFileTunnel.class);

    public boolean deployApp(String url, String appName, String scriptTemplate, Map<String, String> placeHolderMap, String fileUrl, boolean autoStart) {
        DeployApplicationReq req = new DeployApplicationReq();
        req.setAppName(appName);
        req.setScriptTemplate(scriptTemplate);
        req.setPlaceHolderMap(placeHolderMap);
        req.setFileUrl(fileUrl);
        req.setAutoStart(autoStart);

        String retStr;
        try {
            String jsonParam = JSON.toJSONString(req);
            logger.info("deployApp:"+ url + " param:" + jsonParam);
            retStr = HttpUtil.sendPostRequestReturnStr(url, jsonParam);
            logger.info("deployApp:" + retStr);
        } catch (Exception ex) {
            logger.info("deployApp", ex );
            return false;
        }
        Response rsp = JSON.parseObject(retStr, Response.class);
        return rsp.isSuccess();
    }
}
