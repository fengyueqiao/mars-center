CREATE TABLE `mars_app_group` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_name` varchar(64) NOT NULL COMMENT '分组名称',
  `group_desc` varchar(512) NOT NULL COMMENT '分组描述',

  `creator` varchar(64) NOT NULL COMMENT '创建人',
  `modifier` varchar(64) NOT NULL COMMENT '修改人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `idx_app_name` (`group_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT='应用分组信息表';

CREATE TABLE `mars_app_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_name` varchar(64) NOT NULL COMMENT '应用名称',
  `app_desc` varchar(512) NOT NULL COMMENT '应用描述',
  `template_id` varchar(512) NOT NULL COMMENT '使用模板id',
  `extend_template_profile` text NOT NULL COMMENT '扩展模板内容',
  `group_id` varchar(512) NOT NULL COMMENT '分组id',

  `creator` varchar(64) NOT NULL COMMENT '创建人',
  `modifier` varchar(64) NOT NULL COMMENT '修改人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `idx_app_name` (`app_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT='应用信息表';


CREATE TABLE `mars_node_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `node_name` varchar(64) NOT NULL COMMENT '名称名称',
  `node_desc` varchar(512) NOT NULL COMMENT '节点描述',
  `endpoint` varchar(512) NOT NULL COMMENT '访问地址',
  `gmt_heartbeat` datetime NOT NULL COMMENT '最近一次心跳时间',
  `version` varchar(64) NOT NULL COMMENT '版本号',

  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `idx_node_name` (`node_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT='节点信息表';


CREATE TABLE `mars_app_instance` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_id` bigint(20) unsigned NOT NULL COMMENT '应用ID',
  `node_id` bigint(20) unsigned NOT NULL COMMENT '节点ID',
  `setting_state` tinyint NOT NULL DEFAULT 0 COMMENT '0-未运行 1-运行中',
  `present_state` tinyint NOT NULL DEFAULT 0 COMMENT '0-未运行 1-运行中',

  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_node_id` (`node_id`),
  KEY `idx_app_id` (`app_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT='应用在一个节点的实例表';

CREATE TABLE `mars_property` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `prop_name` varchar(64) NOT NULL COMMENT '属性名称',
  `prop_value` varchar(64) NOT NULL COMMENT '属性值',
  `target_id` bigint(20) unsigned NOT NULL COMMENT '目标ID',
  `target_type` tinyint NOT NULL DEFAULT 0 COMMENT '目标类型 1-模板属性，2-应用属性，3-Node属性 4-应用实例属性',

  `creator` varchar(64) NOT NULL COMMENT '创建人',
  `modifier` varchar(64) NOT NULL COMMENT '修改人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `idx_node_id` (`prop_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT='应用属性配置表';


CREATE TABLE `mars_deploy_version` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_id` varchar(64) NOT NULL COMMENT '应用ID',
  `file_name` varchar(64) NOT NULL COMMENT '文件名称',
  `tgz_name` varchar(64) NOT NULL COMMENT '压缩包文件名称',
  `version` varchar(64) NOT NULL COMMENT '版本号',
  `is_file_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '文件是否删除',
  `md5sum` varchar(64) NOT NULL COMMENT 'md5',

  `creator` varchar(64) NOT NULL COMMENT '创建人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `idx_app_id` (`app_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT='部署版本';

CREATE TABLE `mars_template_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `template_name` varchar(64) NOT NULL COMMENT '模板名称',
  `profile` text NOT NULL COMMENT '内容',

  `creator` varchar(64) NOT NULL COMMENT '创建人',
  `modifier` varchar(64) NOT NULL COMMENT '修改人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `idx_template_name` (`template_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT='模板信息表';