package com.framework.loippi.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Entity - 角色
 * 
 * @author Loippi Team
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_SYSTEM_ROLE")
public class Role implements GenericEntity {

	private static final long serialVersionUID = -4449299600117715568L;

	/** 编号 */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 创建时间 */
	@Column(name = "create_date" )
	private java.util.Date createDate;
	
	/** 角色名称 */
	@Column(name = "role_name" )
	private String roleName;
	
	/** 是否内置 */
	@Column(name = "is_system" )
	private Integer isSystem;
	
	/** 描述 */
	@Column(name = "descriptions" )
	private String descriptions;
	
	/** 修改时间 */
	@Column(name = "update_date" )
	private java.util.Date updateDate;
	
	/** 创建者 */
	@Column(name = "creator" )
	private Long creator;
	
	/** 修改者 */
	@Column(name = "updator" )
	private Long updator;
	

	/** 权限 */
	private Set<Acl> authorities = new HashSet<Acl>();


	public List<Long> getAclIds(){
		List<Long> ids = new ArrayList<Long>();
		for (Acl acl : authorities) {
			ids.add(acl.getId());
		}
		return ids;
	}
}
