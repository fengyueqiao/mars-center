package io.github.fengyueqiao.marscenter.dao.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * @author QinWei on 2020/7/14 0014.
 */

@Configuration
@Data
public class FileConfig {
    @Value("${app.file.file-dir}")
    private String fileDir;

    @Value("${app.file.file-download-url}")
    private String fileDownloadUrl;

    public final static char SEPARATOR_CHAR = '/';

    public String getFullUrl(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return "";
        }
        return fileDownloadUrl + filePath;
    }
}
