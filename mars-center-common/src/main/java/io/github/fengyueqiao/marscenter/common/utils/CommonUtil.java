package io.github.fengyueqiao.marscenter.common.utils;

import java.util.UUID;

public class CommonUtil {

    public static String genUniCode() {
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();
        uuidStr = uuidStr.replace("-", "");
//        uuidStr += System.currentTimeMillis();
        return uuidStr;
    }
}
