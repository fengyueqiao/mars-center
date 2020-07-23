package io.github.fengyueqiao.marscenter.dao.config;

import io.github.fengyueqiao.marscenter.api.dto.enumeration.NodeStateEnum;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author QinWei on 2020/7/16 0016.
 */

@Configuration
@Data
public class NodeConfig {
    @Value("${app.node.expired-seconds}")
    private Long expiredSeconds;

    public String getNodeState(Long heartbeatTime) {
        long now = System.currentTimeMillis();
        if(now <= heartbeatTime + expiredSeconds * 1000L) {
            return NodeStateEnum.Active.name();
        } else {
            return NodeStateEnum.Inactive.name();
        }
    }
}
