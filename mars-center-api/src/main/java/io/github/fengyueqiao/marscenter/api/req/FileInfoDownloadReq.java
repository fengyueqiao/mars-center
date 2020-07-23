package io.github.fengyueqiao.marscenter.api.req;

import lombok.Data;

import java.io.OutputStream;

/**
 * @author QinWei on 2020/7/13 0013.
 */

@Data
public class FileInfoDownloadReq {
    String path;
    OutputStream outputStream;
}
