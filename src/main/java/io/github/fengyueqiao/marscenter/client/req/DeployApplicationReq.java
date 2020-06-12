package io.github.fengyueqiao.marscenter.client.req;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Administrator on 2019/9/3 0003.
 */

@Data
public class DeployApplicationReq {
    // 服务名称
    String appName;

    // 部署版本
    String deployVersionId;

    // 部署完成后是否自动启动
    boolean autoStart;
}
