package io.github.fengyueqiao.marscenter.dao.config;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author QinWei on 2020/7/16 0016.
 */

public class VersionConfig {
    public static String getVersionStr() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(new Date());
    }
}
