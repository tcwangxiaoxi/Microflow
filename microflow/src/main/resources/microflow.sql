-- 监控平台任务表
CREATE TABLE `microflow_job` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '唯一主键,任务id',
  `job_name` varchar(64) NOT NULL DEFAULT '' COMMENT '任务名称',
  `trigger_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '任务触发规则id',
  `extra_file_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '依赖外部文件id',
  `scope` varchar(128) NOT NULL DEFAULT '' COMMENT '指定运行机,多个以分号分隔',
  `job_code` text NOT NULL COMMENT '任务代码',
  `active` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '任务状态,0未启动,1启动',
  `succeed_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '任务执行成功次数',
  `failure_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '任务执行失败次数',
  `user_name` varchar(32) NOT NULL DEFAULT '' COMMENT '创建者名称,邮箱前缀',
  `alarm_sendee` varchar(128) NOT NULL DEFAULT '' COMMENT '报警接收人邮箱前缀,多个以分号分隔',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_job_name` (`job_name`),
  KEY `idx_trigger_id` (`trigger_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='监控平台任务表';

-- 监控平台任务触发规则表
CREATE TABLE `microflow_trigger` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '唯一主键,触发规则id',
  `trigger_name` varchar(32) NOT NULL DEFAULT '' COMMENT '规则名称',
  `type` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '任务类型,0定时任务,1触发任务',
  `schedual` varchar(32) NOT NULL DEFAULT '' COMMENT '定时调度规则,任务类型为0时启用',
  `user_name` varchar(32) NOT NULL DEFAULT '' COMMENT '创建者名称,邮箱前缀',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_trigger_name` (`trigger_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='监控平台任务触发规则表';

-- 监控平台任务运行记录表
CREATE TABLE `microflow_job_trace` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `job_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '任务id',
  `trigger_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '任务触发规则id,0代表手动执行',
  `status` tinyint(4) unsigned NOT NULL DEFAULT 0 COMMENT '运行状态,0成功,1失败',
  `standard_time` datetime NOT NULL COMMENT '基准时间',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`),
  KEY `idx_job_id` (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='监控平台任务运行记录表';

-- 监控平台任务task运行记录表
CREATE TABLE `microflow_job_task_trace` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `job_trace_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '任务运行记录id',
  `task_name` varchar(64) NOT NULL DEFAULT '' COMMENT 'task名称',
  `retries_count` int(11) unsigned NOT NULL DEFAULT '0' COMMENT 'task重试次数',
  `status` tinyint(4) unsigned NOT NULL DEFAULT 0 COMMENT '运行状态,0成功,1失败',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`),
  KEY `idx_job_trace_id` (`job_trace_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='监控平台任务task运行记录表';

-- 监控平台文件池
CREATE TABLE `microflow_file_pool` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `name` varchar(128) NOT NULL DEFAULT '' COMMENT '文件名称,带后缀',
  `file` blob COMMENT '文件内容,二进制',
  `user_name` varchar(32) NOT NULL DEFAULT '' COMMENT '创建者名称,邮箱前缀',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='监控平台文件池表';