package io.github.fengyueqiao.marscenter.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2019/9/3 0003.
 */

@Data
@Configuration
public class MarsConfig {

    @Value("${mars.tmpUploadPath}")
    private String tmpUploadPath;

    @Value("${mars.deployPath}")
    private String deployPath;
}
