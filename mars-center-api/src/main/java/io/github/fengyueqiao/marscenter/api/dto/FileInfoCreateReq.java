package io.github.fengyueqiao.marscenter.api.dto;

import lombok.Data;

/**
 * @author QinWei on 2020/7/14 0014.
 */

@Data
public class FileInfoCreateReq {
    /**
     * 文件名称
     */
    String name;
    /**
     * 文件目录
     */
    String url;
    /**
     * 文件大小
     */
    Long size;
}
