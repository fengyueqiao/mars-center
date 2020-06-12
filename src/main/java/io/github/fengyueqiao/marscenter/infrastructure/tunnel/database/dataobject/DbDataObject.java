package io.github.fengyueqiao.marscenter.infrastructure.tunnel.database.dataobject;

import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2019/9/9 0009.
 */

@Data
public abstract class DbDataObject {

    protected String id;

    protected Date gmtCreate;

    protected Date gmtModified;

}
