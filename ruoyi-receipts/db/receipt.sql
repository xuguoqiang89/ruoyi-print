/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50559
Source Host           : localhost:3306
Source Database       : ry_test

Target Server Type    : MYSQL
Target Server Version : 50559
File Encoding         : 65001

Date: 2020-03-17 17:06:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `url` varchar(200) DEFAULT '#' COMMENT '请求地址',
  `target` varchar(20) DEFAULT '' COMMENT '打开方式（menuItem页签 menuBlank新窗口）',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2069 DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '0', '1', '#', '', 'M', '0', '', 'fa fa-gear', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统管理目录');
INSERT INTO `sys_menu` VALUES ('2', '系统监控', '0', '2', '#', '', 'M', '0', '', 'fa fa-video-camera', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统监控目录');
INSERT INTO `sys_menu` VALUES ('3', '系统工具', '0', '3', '#', '', 'M', '0', '', 'fa fa-bars', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统工具目录');
INSERT INTO `sys_menu` VALUES ('100', '用户管理', '1', '1', '/system/user', '', 'C', '0', 'system:user:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '用户管理菜单');
INSERT INTO `sys_menu` VALUES ('101', '角色管理', '1', '2', '/system/role', '', 'C', '0', 'system:role:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '角色管理菜单');
INSERT INTO `sys_menu` VALUES ('102', '菜单管理', '1', '3', '/system/menu', '', 'C', '0', 'system:menu:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单管理菜单');
INSERT INTO `sys_menu` VALUES ('103', '部门管理', '1', '4', '/system/dept', '', 'C', '0', 'system:dept:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '部门管理菜单');
INSERT INTO `sys_menu` VALUES ('104', '岗位管理', '1', '5', '/system/post', '', 'C', '0', 'system:post:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '岗位管理菜单');
INSERT INTO `sys_menu` VALUES ('105', '字典管理', '1', '6', '/system/dict', '', 'C', '0', 'system:dict:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '字典管理菜单');
INSERT INTO `sys_menu` VALUES ('106', '参数设置', '1', '7', '/system/config', '', 'C', '0', 'system:config:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '参数设置菜单');
INSERT INTO `sys_menu` VALUES ('107', '通知公告', '1', '8', '/system/notice', '', 'C', '0', 'system:notice:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知公告菜单');
INSERT INTO `sys_menu` VALUES ('108', '日志管理', '1', '9', '#', '', 'M', '0', '', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '日志管理菜单');
INSERT INTO `sys_menu` VALUES ('109', '在线用户', '2', '1', '/monitor/online', '', 'C', '0', 'monitor:online:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '在线用户菜单');
INSERT INTO `sys_menu` VALUES ('110', '定时任务', '2', '2', '/monitor/job', '', 'C', '0', 'monitor:job:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '定时任务菜单');
INSERT INTO `sys_menu` VALUES ('111', '数据监控', '2', '3', '/monitor/data', 'menuItem', 'C', '0', 'monitor:data:view', 'fa fa-film', 'admin', '2018-03-16 11:33:00', 'admin', '2020-01-18 16:22:38', '数据监控菜单');
INSERT INTO `sys_menu` VALUES ('112', '服务监控', '2', '3', '/monitor/server', '', 'C', '0', 'monitor:server:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '服务监控菜单');
INSERT INTO `sys_menu` VALUES ('113', '表单构建', '3', '1', '/tool/build', '', 'C', '0', 'tool:build:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '表单构建菜单');
INSERT INTO `sys_menu` VALUES ('114', '代码生成', '3', '2', '/tool/gen', '', 'C', '0', 'tool:gen:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '代码生成菜单');
INSERT INTO `sys_menu` VALUES ('115', '系统接口', '3', '3', '/tool/swagger', '', 'C', '0', 'tool:swagger:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统接口菜单');
INSERT INTO `sys_menu` VALUES ('500', '操作日志', '108', '1', '/monitor/operlog', '', 'C', '0', 'monitor:operlog:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作日志菜单');
INSERT INTO `sys_menu` VALUES ('501', '登录日志', '108', '2', '/monitor/logininfor', '', 'C', '0', 'monitor:logininfor:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '登录日志菜单');
INSERT INTO `sys_menu` VALUES ('1000', '用户查询', '100', '1', '#', '', 'F', '0', 'system:user:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1001', '用户新增', '100', '2', '#', '', 'F', '0', 'system:user:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1002', '用户修改', '100', '3', '#', '', 'F', '0', 'system:user:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1003', '用户删除', '100', '4', '#', '', 'F', '0', 'system:user:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1004', '用户导出', '100', '5', '#', '', 'F', '0', 'system:user:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1005', '用户导入', '100', '6', '#', '', 'F', '0', 'system:user:import', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1006', '重置密码', '100', '7', '#', '', 'F', '0', 'system:user:resetPwd', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1007', '角色查询', '101', '1', '#', '', 'F', '0', 'system:role:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1008', '角色新增', '101', '2', '#', '', 'F', '0', 'system:role:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1009', '角色修改', '101', '3', '#', '', 'F', '0', 'system:role:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1010', '角色删除', '101', '4', '#', '', 'F', '0', 'system:role:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1011', '角色导出', '101', '5', '#', '', 'F', '0', 'system:role:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1012', '菜单查询', '102', '1', '#', '', 'F', '0', 'system:menu:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1013', '菜单新增', '102', '2', '#', '', 'F', '0', 'system:menu:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1014', '菜单修改', '102', '3', '#', '', 'F', '0', 'system:menu:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1015', '菜单删除', '102', '4', '#', '', 'F', '0', 'system:menu:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1016', '部门查询', '103', '1', '#', '', 'F', '0', 'system:dept:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1017', '部门新增', '103', '2', '#', '', 'F', '0', 'system:dept:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1018', '部门修改', '103', '3', '#', '', 'F', '0', 'system:dept:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1019', '部门删除', '103', '4', '#', '', 'F', '0', 'system:dept:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1020', '岗位查询', '104', '1', '#', '', 'F', '0', 'system:post:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1021', '岗位新增', '104', '2', '#', '', 'F', '0', 'system:post:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1022', '岗位修改', '104', '3', '#', '', 'F', '0', 'system:post:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1023', '岗位删除', '104', '4', '#', '', 'F', '0', 'system:post:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1024', '岗位导出', '104', '5', '#', '', 'F', '0', 'system:post:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1025', '字典查询', '105', '1', '#', '', 'F', '0', 'system:dict:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1026', '字典新增', '105', '2', '#', '', 'F', '0', 'system:dict:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1027', '字典修改', '105', '3', '#', '', 'F', '0', 'system:dict:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1028', '字典删除', '105', '4', '#', '', 'F', '0', 'system:dict:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1029', '字典导出', '105', '5', '#', '', 'F', '0', 'system:dict:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1030', '参数查询', '106', '1', '#', '', 'F', '0', 'system:config:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1031', '参数新增', '106', '2', '#', '', 'F', '0', 'system:config:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1032', '参数修改', '106', '3', '#', '', 'F', '0', 'system:config:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1033', '参数删除', '106', '4', '#', '', 'F', '0', 'system:config:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1034', '参数导出', '106', '5', '#', '', 'F', '0', 'system:config:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1035', '公告查询', '107', '1', '#', '', 'F', '0', 'system:notice:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1036', '公告新增', '107', '2', '#', '', 'F', '0', 'system:notice:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1037', '公告修改', '107', '3', '#', '', 'F', '0', 'system:notice:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1038', '公告删除', '107', '4', '#', '', 'F', '0', 'system:notice:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1039', '操作查询', '500', '1', '#', '', 'F', '0', 'monitor:operlog:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1040', '操作删除', '500', '2', '#', '', 'F', '0', 'monitor:operlog:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1041', '详细信息', '500', '3', '#', '', 'F', '0', 'monitor:operlog:detail', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1042', '日志导出', '500', '4', '#', '', 'F', '0', 'monitor:operlog:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1043', '登录查询', '501', '1', '#', '', 'F', '0', 'monitor:logininfor:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1044', '登录删除', '501', '2', '#', '', 'F', '0', 'monitor:logininfor:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1045', '日志导出', '501', '3', '#', '', 'F', '0', 'monitor:logininfor:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1046', '在线查询', '109', '1', '#', '', 'F', '0', 'monitor:online:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1047', '批量强退', '109', '2', '#', '', 'F', '0', 'monitor:online:batchForceLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1048', '单条强退', '109', '3', '#', '', 'F', '0', 'monitor:online:forceLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1049', '任务查询', '110', '1', '#', '', 'F', '0', 'monitor:job:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1050', '任务新增', '110', '2', '#', '', 'F', '0', 'monitor:job:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1051', '任务修改', '110', '3', '#', '', 'F', '0', 'monitor:job:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1052', '任务删除', '110', '4', '#', '', 'F', '0', 'monitor:job:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1053', '状态修改', '110', '5', '#', '', 'F', '0', 'monitor:job:changeStatus', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1054', '任务详细', '110', '6', '#', '', 'F', '0', 'monitor:job:detail', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1055', '任务导出', '110', '7', '#', '', 'F', '0', 'monitor:job:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1056', '生成查询', '114', '1', '#', '', 'F', '0', 'tool:gen:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1057', '生成修改', '114', '2', '#', '', 'F', '0', 'tool:gen:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1058', '生成删除', '114', '3', '#', '', 'F', '0', 'tool:gen:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1059', '预览代码', '114', '4', '#', '', 'F', '0', 'tool:gen:preview', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1060', '生成代码', '114', '5', '#', '', 'F', '0', 'tool:gen:code', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('2000', '流程菜单', '0', '4', '#', 'menuItem', 'M', '0', '', 'fa fa-gears', 'admin', '2019-09-29 21:32:17', 'admin', '2019-10-09 10:29:21', '');
INSERT INTO `sys_menu` VALUES ('2001', '在线绘图(弃)', '2000', '1', '/bpmn/index.html', 'menuItem', 'C', '1', '', '#', 'admin', '2019-09-29 21:34:38', 'admin', '2019-11-19 17:28:16', '');
INSERT INTO `sys_menu` VALUES ('2002', '流程定义', '2000', '2', '/process/definition', 'menuItem', 'C', '0', 'process:definition:view', '#', 'admin', '2019-09-30 20:13:03', '', null, '');
INSERT INTO `sys_menu` VALUES ('2005', '流程用户', '2000', '3', '/process/user', 'menuItem', 'C', '0', 'process:user:view', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2019-10-02 17:30:10', '流程用户菜单');
INSERT INTO `sys_menu` VALUES ('2006', '流程用户查询', '2005', '1', '#', '', 'F', '0', 'process:user:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2007', '流程用户新增', '2005', '2', '#', '', 'F', '0', 'process:user:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2008', '流程用户修改', '2005', '3', '#', '', 'F', '0', 'process:user:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2009', '流程用户删除', '2005', '4', '#', '', 'F', '0', 'process:user:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2010', '流程用户导出', '2005', '5', '#', '', 'F', '0', 'process:user:export', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2011', '流程用户组', '2000', '4', '/process/group', 'menuItem', 'C', '0', 'process:group:view', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2019-10-02 20:35:49', '流程用户组菜单');
INSERT INTO `sys_menu` VALUES ('2012', '流程用户组查询', '2011', '1', '#', '', 'F', '0', 'process:group:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2013', '流程用户组新增', '2011', '2', '#', '', 'F', '0', 'process:group:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2014', '流程用户组修改', '2011', '3', '#', '', 'F', '0', 'process:group:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2015', '流程用户组删除', '2011', '4', '#', '', 'F', '0', 'process:group:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2016', '流程用户组导出', '2011', '5', '#', '', 'F', '0', 'process:group:export', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2017', '业务菜单', '0', '5', '#', 'menuItem', 'M', '0', null, 'fa fa-paper-plane', 'admin', '2019-10-09 10:31:41', '', null, '');
INSERT INTO `sys_menu` VALUES ('2018', '请假列表', '2024', '1', '/process/leave', 'menuItem', 'C', '0', 'process:leave:view', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2019-10-12 16:20:07', '请假业务菜单');
INSERT INTO `sys_menu` VALUES ('2019', '请假业务查询', '2018', '1', '#', '', 'F', '0', 'process:leave:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2020', '请假业务新增', '2018', '2', '#', '', 'F', '0', 'process:leave:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2021', '请假业务修改', '2018', '3', '#', '', 'F', '0', 'process:leave:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2022', '请假业务删除', '2018', '4', '#', '', 'F', '0', 'process:leave:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2023', '请假业务导出', '2018', '5', '#', '', 'F', '0', 'process:leave:export', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2024', '请假业务', '2017', '1', '#', 'menuItem', 'M', '0', null, '#', 'admin', '2019-10-12 16:19:49', '', null, '');
INSERT INTO `sys_menu` VALUES ('2025', '我的待办', '2024', '2', '/process/leave/leaveTodo', 'menuItem', 'C', '0', 'process:leave:todoView', '#', 'admin', '2019-10-12 16:34:43', 'admin', '2019-10-12 16:45:52', '');
INSERT INTO `sys_menu` VALUES ('2026', '我的待办列表', '2025', '1', '#', 'menuItem', 'F', '0', 'process:leave:taskList', '#', 'admin', '2019-10-12 16:48:56', '', null, '');
INSERT INTO `sys_menu` VALUES ('2027', '我的已办', '2024', '3', '/process/leave/leaveDone', 'menuItem', 'C', '0', 'process:leave:doneView', '#', 'admin', '2019-10-17 19:24:49', 'admin', '2019-10-17 19:25:06', '');
INSERT INTO `sys_menu` VALUES ('2028', '我的已办列表', '2027', '1', '#', 'menuItem', 'F', '0', 'process:leave:taskDoneList', '#', 'admin', '2019-10-17 19:26:02', '', null, '');
INSERT INTO `sys_menu` VALUES ('2029', '请假会签', '2017', '2', '#', 'menuItem', 'M', '0', null, '#', 'admin', '2019-10-22 16:41:06', '', null, '');
INSERT INTO `sys_menu` VALUES ('2030', '请假列表(会签)', '2029', '1', '/process/leaveCounterSign', 'menuItem', 'C', '0', 'process:leaveCounterSign:view', '#', 'admin', '2019-10-22 16:42:16', 'admin', '2019-10-22 17:03:48', '');
INSERT INTO `sys_menu` VALUES ('2031', '请假列表查询', '2030', '1', '#', 'menuItem', 'F', '0', 'process:leaveCounterSign:list', '#', 'admin', '2019-10-23 16:44:22', '', null, '');
INSERT INTO `sys_menu` VALUES ('2032', '请假列表新增', '2030', '2', '#', 'menuItem', 'F', '0', 'process:leaveCounterSign:add', '#', 'admin', '2019-10-23 16:50:38', '', null, '');
INSERT INTO `sys_menu` VALUES ('2033', '请假列表修改', '2030', '3', '#', 'menuItem', 'F', '0', 'process:leaveCounterSign:edit', '#', 'admin', '2019-10-23 16:51:23', '', null, '');
INSERT INTO `sys_menu` VALUES ('2034', '请假列表删除', '2030', '4', '#', 'menuItem', 'F', '0', 'process:leaveCounterSign:remove', '#', 'admin', '2019-10-23 16:51:50', '', null, '');
INSERT INTO `sys_menu` VALUES ('2035', '我的待办', '2029', '2', '/process/leaveCounterSign/leaveTodo', 'menuItem', 'C', '0', 'process:leaveCounterSign:todoView', '#', 'admin', '2019-10-24 17:07:31', '', null, '');
INSERT INTO `sys_menu` VALUES ('2036', '我的待办列表', '2035', '1', '#', 'menuItem', 'F', '0', 'process:leaveCounterSign:taskList', '#', 'admin', '2019-10-24 17:08:13', '', null, '');
INSERT INTO `sys_menu` VALUES ('2037', '我的已办', '2029', '3', '/process/leaveCounterSign/leaveDone', 'menuItem', 'C', '0', 'process:leaveCounterSign:doneView', '#', 'admin', '2019-10-28 21:49:49', '', null, '');
INSERT INTO `sys_menu` VALUES ('2038', '我的已办列表', '2037', '1', '#', 'menuItem', 'F', '0', 'process:leaveCounterSign:taskDoneList', '#', 'admin', '2019-10-28 21:50:28', '', null, '');
INSERT INTO `sys_menu` VALUES ('2039', '待办事项', '2045', '1', '/process/todoitem', 'menuItem', 'C', '0', 'process:todoitem:view', '#', 'admin', '2018-03-01 00:00:00', 'admin', '2019-11-08 11:54:09', '待办事项菜单');
INSERT INTO `sys_menu` VALUES ('2040', '待办事项查询', '2039', '1', '#', '', 'F', '0', 'process:todoitem:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2044', '待办事项导出', '2039', '5', '#', '', 'F', '0', 'process:todoitem:export', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO `sys_menu` VALUES ('2045', '待办已办', '2017', '3', '#', 'menuItem', 'M', '0', null, '#', 'admin', '2019-11-08 11:53:25', '', null, '');
INSERT INTO `sys_menu` VALUES ('2046', '已办事项', '2045', '2', '/process/todoitem/doneitemView', 'menuItem', 'C', '0', 'process:todoitem:doneView', '#', 'admin', '2019-11-10 19:48:43', '', null, '');
INSERT INTO `sys_menu` VALUES ('2047', '已办事项查询', '2046', '1', '#', 'menuItem', 'F', '0', 'process:todoitem:doneList', '#', 'admin', '2019-11-10 19:50:29', '', null, '');
INSERT INTO `sys_menu` VALUES ('2048', '已办事项导出', '2046', '2', '#', 'menuItem', 'F', '0', 'process:todoitem:doneExport', '#', 'admin', '2019-11-10 19:51:02', '', null, '');
INSERT INTO `sys_menu` VALUES ('2049', '流程模型', '2000', '1', '/process/modeler/modelList', 'menuItem', 'C', '0', '', '#', 'admin', '2019-11-18 20:49:12', 'admin', '2019-11-19 16:06:21', '');
INSERT INTO `sys_menu` VALUES ('2051', '数据备份', '3', '4', '/system/backup', 'menuItem', 'C', '0', 'system:backup', '#', 'admin', '2020-02-28 16:57:27', '', null, '');
INSERT INTO `sys_menu` VALUES ('2052', '响应时间', '2', '5', '/system/costTime', 'menuItem', 'C', '0', 'system:costTime', '#', 'admin', '2020-02-29 16:58:35', '', null, '');
INSERT INTO `sys_menu` VALUES ('2059', '票据管理', '0', '6', '#', 'menuItem', 'M', '0', null, 'fa fa-newspaper-o', 'admin', '2020-03-17 11:00:23', '', null, '');
INSERT INTO `sys_menu` VALUES ('2060', '票据管理', '2059', '1', '/system/receipts', 'menuItem', 'C', '0', 'system:receipts:view,system:receipts:list', 'fa fa-newspaper-o', 'admin', '2020-03-17 11:01:27', 'admin', '2020-03-17 16:17:09', '');
INSERT INTO `sys_menu` VALUES ('2061', '新增', '2060', '1', '#', 'menuItem', 'F', '0', 'system:receipts:add', '#', 'admin', '2020-03-17 15:59:38', '', null, '');
INSERT INTO `sys_menu` VALUES ('2062', '修改', '2060', '2', '#', 'menuItem', 'F', '0', 'system:receipts:edit', '#', 'admin', '2020-03-17 15:59:56', '', null, '');
INSERT INTO `sys_menu` VALUES ('2063', '删除', '2060', '3', '#', 'menuItem', 'F', '0', 'system:receipts:remove', '#', 'admin', '2020-03-17 16:06:35', '', null, '');
INSERT INTO `sys_menu` VALUES ('2067', '导出', '2060', '4', '#', 'menuItem', 'F', '0', 'system:receipts:export', '#', 'admin', '2020-03-17 16:12:53', '', null, '');
INSERT INTO `sys_menu` VALUES ('2068', '打印', '2060', '5', '#', 'menuItem', 'F', '0', 'system:receipts:print', '#', 'admin', '2020-03-17 16:13:08', '', null, '');

-- ----------------------------
-- Table structure for tb_receipts
-- ----------------------------
DROP TABLE IF EXISTS `tb_receipts`;
CREATE TABLE `tb_receipts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '交款单位(或姓名)',
  `receipt_date` datetime DEFAULT NULL COMMENT '收据日期',
  `receipt_no` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '收据号',
  `receipt_mode` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '收款方式',
  `receipt_rmb` decimal(16,4) DEFAULT NULL COMMENT '人民币',
  `receipt_rmb_upp` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '人民币大写',
  `remark_type` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注事项',
  `remark` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  `cashier` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '出纳',
  `operator` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '经办人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pk_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='收据管理';

-- ----------------------------
-- Records of tb_receipts
-- ----------------------------
INSERT INTO `tb_receipts` VALUES ('2', '张海天', '2020-03-20 00:00:00', null, '1', '1000.0000', '壹仟元整', '1', '取暖费补贴了1000元', '1', '1');
INSERT INTO `tb_receipts` VALUES ('3', '跌到', '2020-03-18 00:00:00', '1', '4', '1000.0100', '壹仟元壹分', '2', '代收', null, '1');
INSERT INTO `tb_receipts` VALUES ('6', '1', '2020-03-17 00:00:00', null, '2', '222.0000', '贰佰贰拾贰元整', '3', null, null, '3');
