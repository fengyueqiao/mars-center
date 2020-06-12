package io.github.fengyueqiao.marscenter.infrastructure.tunnel.database.dataobject;

import lombok.Data;

/**
 * Created by Administrator on 2019/9/9 0009.
 */

@Data
public class ApplicationDO extends DbDataObject{

    private String appName;
    private String appDesc;
    private String templateId;
    private String extendTemplateProfile;
    private String groupId;
    private String creator;
    private String modifier;
}
