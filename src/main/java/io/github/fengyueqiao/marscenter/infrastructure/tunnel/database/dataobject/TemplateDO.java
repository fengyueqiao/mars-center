package io.github.fengyueqiao.marscenter.infrastructure.tunnel.database.dataobject;

import lombok.Data;

/**
 * Created by Administrator on 2019/9/12 0012.
 */

@Data
public class TemplateDO extends DbDataObject{
    String templateName;
    String profile;
    String creator;
    String modifier;
}
