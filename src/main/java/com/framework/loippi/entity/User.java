package com.framework.loippi.entity;

import java.util.Date;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 系统用户
 * 
 * @author Loippi Team
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_SYSTEM_USER")
public class User implements GenericEntity {

	private static final long serialVersionUID = -7404471728192141480L;

	/** 主键 */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 创建时间 */
	@Column(name = "create_date" )
	private java.util.Date createDate;
	
	/** 用户名 */
	@Column(name = "user_name" )
	private String userName;
	
	/** 密码 */
	@Column(name = "password" )
	private String password;
	
	/** 昵称 */
	@Column(name = "nickname" )
	private String nickname;
	
	/** 是否启用 */
	@Column(name = "is_enabled")
	private Boolean isEnabled;

	/** 是否锁定 */
	@Column(name = "is_locked")
	private Boolean isLocked;
	
	/** 登录失败次数 */
	@Column(name = "login_failure_count" )
	private Integer loginFailureCount;
	
	/** 锁定时间 */
	@Column(name = "locked_date" )
	private java.util.Date lockedDate;
	
	/** 登录时间 */
	@Column(name = "login_date" )
	private java.util.Date loginDate;
	
	/** 登录ip */
	@Column(name = "login_ip" )
	private String loginIp;
	
	/** 邮箱 */
	@Column(name = "email" )
	private String email;
	
	/** 角色 */
	@Column(name = "role_id" )
	private Long roleId;
	
	/** 头像 */
	@Column(name = "avatar" )
	private String avatar;
	
	/** 修改时间 */
	@Column(name = "update_date" )
	private java.util.Date updateDate;
	
	/** 主题默认1，2为classic */
	@Column(name = "theme" )
	private Integer theme;
	
	/** 角色 */
	private Role role = new Role();

}
