package io.github.fengyueqiao.marscenter.api.req;

import lombok.Data;

import java.io.InputStream;

/**
 * @author QinWei on 2020/7/13 0013.
 */

@Data
public class FileInfoUploadReq {
    /**
     * 文件名称
     */
    String name;
    /**
     * 文件目录
     */
    String dir;
    /**
     * 文件大小
     */
    Long size;
    /**
     * 文件数据
     */
    InputStream inputStream;
}
