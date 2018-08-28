package com.framework.loippi.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 代码生成器:业务表
 * 
 * @author Loippi Team
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_GEN_TABLE")
public class GenTable implements GenericEntity {
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
	
	/** 表名 */
	private String name;
	
	/** 表名 */
	@Column(name = "table_name" )
	private String tableName;
	
	/** 描述 */
	@Column(name = "description" )
	private String description;
	
	/** 对应java类名 */
	@Column(name = "class_name" )
	private String className;
	
	/** 父级表 */
	@Column(name = "parent_table" )
	private String parentTable;
	
	/** 外键关联字段 */
	@Column(name = "parent_table_fk" )
	private String parentTableFk;
	
	/** 备注 */
	@Column(name = "remarks" )
	private String remarks;
	
	
	/** 字段列表 */
	private List<GenTableColumn> columns = new ArrayList<GenTableColumn>();
	
	
	
	public String getClassPk(){
		if(CollectionUtils.isNotEmpty(getColumns())){
			for (GenTableColumn genTableColumn : getColumns()) {
				if(genTableColumn.getIsPk() == 1){return genTableColumn.getJavaField();}
			}
		}
		return null;
	}
}
