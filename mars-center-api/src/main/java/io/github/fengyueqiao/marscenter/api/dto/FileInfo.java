package io.github.fengyueqiao.marscenter.api.dto;

import lombok.Data;

/**
 * @author QinWei on 2020/7/10 0010.
 */

@Data
public class FileInfo {
    String id;
    String name;
    String path;
    Long size;
    String md5sum;
    Integer isExist;
    Integer isZip;
}
