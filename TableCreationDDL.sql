CREATE TABLE `mars_app_group` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '分组名称',
  `comment` varchar(512) NOT NULL COMMENT '分组描述',

  `creator` varchar(64) DEFAULT '' NOT NULL COMMENT '创建人',
  `modifier` varchar(64) DEFAULT '' NOT NULL COMMENT '修改人',
  `created_time` bigint(20) unsigned DEFAULT 0 NOT NULL COMMENT '创建时间(ms)',
  `modified_time` bigint(20) unsigned DEFAULT 0 NOT NULL COMMENT '修改时间(ms)',
  `is_deleted` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '逻辑删除 0：未删除 非0：已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_app_name` (`is_deleted`, `group_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='应用分组信息表';

CREATE TABLE `mars_app_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `type` varchar(64) NOT NULL COMMENT '类型',
  `comment` varchar(512) NOT NULL COMMENT '描述',
  `template_id` varchar(512) NOT NULL COMMENT '使用模板id',
  `extend_template_profile` text COMMENT '扩展模板内容',
  `group_id` varchar(512) NOT NULL COMMENT '分组id',

  `creator` varchar(64) DEFAULT '' NOT NULL COMMENT '创建人',
  `modifier` varchar(64) DEFAULT '' NOT NULL COMMENT '修改人',
  `created_time` bigint(20) unsigned DEFAULT 0 NOT NULL COMMENT '创建时间(秒)',
  `modified_time` bigint(20) unsigned DEFAULT 0 NOT NULL COMMENT '修改时间(秒)',
  `is_deleted` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '逻辑删除 0：未删除 非0：已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`is_deleted`, `name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='应用信息表';


CREATE TABLE `mars_node_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `endpoint` varchar(512) NOT NULL COMMENT '访问地址',
  `version` varchar(64) NOT NULL COMMENT '版本号',
  `comment` varchar(512) NOT NULL COMMENT '节点描述',
  `heartbeat_time` bigint(20) unsigned DEFAULT 0 NOT NULL COMMENT '最近一次心跳时间(秒)',

  `created_time` bigint(20) unsigned DEFAULT 0 NOT NULL COMMENT '创建时间(秒)',
  `modified_time` bigint(20) unsigned DEFAULT 0 NOT NULL COMMENT '修改时间(秒)',
  `is_deleted` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '逻辑删除 0：未删除 非0：已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_node_name` (`is_deleted`, `name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='节点信息表';


CREATE TABLE `mars_app_instance` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_id` bigint(20) unsigned NOT NULL COMMENT '应用ID',
  `node_id` bigint(20) unsigned NOT NULL COMMENT '节点ID',
  `pid` varchar(64) NOT NULL COMMENT '进程ID',
  `setting_state` varchar(64) NOT NULL COMMENT '0-未运行 1-运行中',
  `present_state` varchar(64) NOT NULL COMMENT '0-未运行 1-运行中',
  `version` varchar(64) NOT NULL COMMENT '版本号',

  `created_time` bigint(20) unsigned DEFAULT 0 NOT NULL COMMENT '创建时间(秒)',
  `modified_time` bigint(20) unsigned DEFAULT 0 NOT NULL COMMENT '修改时间(秒)',
  `is_deleted` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '逻辑删除 0：未删除 非0：已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_node_id` (`is_deleted`,`app_id`, `node_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='应用在一个节点的实例表';

CREATE TABLE `mars_app_release` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_id` bigint(20) unsigned NOT NULL COMMENT '应用ID',
  `version` varchar(64) NOT NULL COMMENT '版本号',
  `file_id` bigint(20) unsigned NOT NULL COMMENT '文件ID',

  `creator` varchar(64) DEFAULT '' NOT NULL COMMENT '创建人',
  `created_time` bigint(20) unsigned DEFAULT 0 NOT NULL COMMENT '创建时间(秒)',
  `modified_time` bigint(20) unsigned DEFAULT 0 NOT NULL COMMENT '修改时间(秒)',
  `is_deleted` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '逻辑删除 0：未删除 非0：已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_app_id` (`is_deleted`, `app_id`, `version`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='应用发行版';

CREATE TABLE `mars_file_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '原始文件名称',
  `path` varchar(512) NOT NULL COMMENT '文件路径',
  `size` varchar(64) NOT NULL COMMENT '文件大小Bytes',
  `md5sum` varchar(64) NOT NULL COMMENT 'md5sum',
  `is_exist` tinyint NOT NULL DEFAULT 1 COMMENT '文件是否存在 0:不存在 1:存在',
  `is_zip` tinyint NOT NULL DEFAULT 1 COMMENT '文件是否压缩 0：未压缩 1：已压缩',
  `is_full_path` tinyint NOT NULL DEFAULT 1 COMMENT '文件路径是是全路径，还是相对路径 0：相对路径 1：全路径',

  `creator` varchar(64) DEFAULT '' NOT NULL COMMENT '创建人',
  `created_time` bigint(20) unsigned DEFAULT 0 NOT NULL COMMENT '创建时间(秒)',
  `modified_time` bigint(20) unsigned DEFAULT 0 NOT NULL COMMENT '修改时间(秒)',
  `is_deleted` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '逻辑删除 0：未删除 非0：已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='文件信息';

CREATE TABLE `mars_template_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '模板名称',
  `content` text NOT NULL COMMENT '内容',

  `creator` varchar(64) DEFAULT '' NOT NULL COMMENT '创建人',
  `modifier` varchar(64) DEFAULT '' NOT NULL COMMENT '修改人',
  `created_time` bigint(20) unsigned DEFAULT 0 NOT NULL COMMENT '创建时间(秒)',
  `modified_time` bigint(20) unsigned DEFAULT 0 NOT NULL COMMENT '修改时间(秒)',
  `is_deleted` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '逻辑删除 0：未删除 非0：已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_name` (`is_deleted`, `name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='模板信息表';


CREATE TABLE `mars_property` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `prop_name` varchar(64) NOT NULL COMMENT '属性名称',
  `prop_value` varchar(64) NOT NULL COMMENT '属性值',
  `target_id` bigint(20) unsigned NOT NULL COMMENT '目标ID',
  `target_type` tinyint NOT NULL DEFAULT 0 COMMENT '目标类型 1-模板属性，2-应用属性，3-Node属性 4-应用实例属性',

  `creator` varchar(64) DEFAULT '' NOT NULL COMMENT '创建人',
  `modifier` varchar(64) DEFAULT '' NOT NULL COMMENT '修改人',
  `created_time` bigint(20) unsigned DEFAULT 0 NOT NULL COMMENT '创建时间(秒)',
  `modified_time` bigint(20) unsigned DEFAULT 0 NOT NULL COMMENT '修改时间(秒)',
  `is_deleted` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '逻辑删除 0：未删除 非0：已删除',
  PRIMARY KEY (`id`),
  KEY `idx_node_id` (`prop_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='应用属性配置表';