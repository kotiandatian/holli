package com.framework.loippi.entity;

import java.util.Date;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 代码生成器生成方案
 * 
 * @author Loippi Team
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_GEN_SCHEME")
public class GenScheme implements GenericEntity {
	private static final long serialVersionUID = -5306923026754059704L;
	
	/** 编号 */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 创建时间 */
	@Column(name = "create_date" )
	private java.util.Date createDate;
	
	/** 创建者 */
	@Column(name = "creator" )
	private Long creator;
	
	/** 修改时间 */
	@Column(name = "update_date" )
	private java.util.Date updateDate;
	
	/** 修改者 */
	@Column(name = "updator" )
	private Long updator;
	
	/** 生成策略 */
	@Column(name = "strategy" )
	private String strategy;
	
	/** 方案名称 */
	@Column(name = "scheme_name" )
	private String schemeName;
	
	/** 方案模版 */
	@Column(name = "scheme_template" )
	private String schemeTemplate;
	
	/** 包名 */
	@Column(name = "package_name" )
	private String packageName;
	
	/** 描述 */
	@Column(name = "description" )
	private String description;
	
	/** 作者 */
	@Column(name = "author" )
	private String author;
	
	/** 关联表 */
	@Column(name = "gen_table_id" )
	private Long genTableId;
	
	/** 模块名称 */
	@Column(name = "module_name" )
	private String moduleName;
}
