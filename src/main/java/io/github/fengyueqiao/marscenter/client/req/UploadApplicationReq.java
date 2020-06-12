package io.github.fengyueqiao.marscenter.client.req;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Administrator on 2019/9/9 0009.
 */

@Data
public class UploadApplicationReq {
    // 应用名称
    String appName;

    // 应用版本号
    String version;

    // 文件数据
    MultipartFile file;
}
