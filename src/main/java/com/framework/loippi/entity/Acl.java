package com.framework.loippi.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Entity - 资源
 * 
 * @author Loippi Team
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_SYSTEM_ACL")
public class Acl implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/** 编号 */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 创建时间 */
	@Column(name = "create_date" )
	private java.util.Date createDate;
	
	/** url */
	@Column(name = "url" )
	private String url;
	
	/** 资源名称 */
	@Column(name = "acl_name" )
	private String aclName;
	
	/** 资源类型 */
	@Column(name = "acl_type" )
	private Integer aclType;
	
	/** 权限标识 */
	@Column(name = "permission" )
	private String permission;
	
	/** 图标 */
	@Column(name = "icon" )
	private String icon;
	
	/** 上级编号 */
	@Column(name = "parent_id" )
	private Long parentId;
	
	/** 修改时间 */
	@Column(name = "update_date" )
	private java.util.Date updateDate;
	
	/** 创建者 */
	@Column(name = "creator" )
	private Long creator;
	
	/** 修改者 */
	@Column(name = "updator" )
	private Long updator;
	
	/** 排序 */
	@Column(name = "sorts" )
	private Integer sorts;
	
	/** 上级菜单 */
	private Acl parent;

	/** 下级菜单 */
	private List<Acl> children = new ArrayList<Acl>();

}
