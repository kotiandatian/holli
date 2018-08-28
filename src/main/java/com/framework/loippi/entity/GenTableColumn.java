package com.framework.loippi.entity;

import java.util.Date;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Entity - 代码生成器:表字段
 * 
 * @author Loippi Team
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_GEN_TABLE_COLUMN")
public class GenTableColumn implements GenericEntity {
	
	private static final long serialVersionUID = -6433370184013560940L;

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
	
	/** 表名称 */
	@Column(name = "gen_table_id" )
	private Long genTableId;
	
	/** 列名 */
	@Column(name = "name" )
	private String name;
	
	/** 列说明 */
	@Column(name = "comments" )
	private String comments;
	
	/** jdbc类型 */
	@Column(name = "jdbc_type" )
	private String jdbcType;
	
	/** mybatis对应类型 */
	@Column(name = "mybatis_jdbc_type" )
	private String mybatisJdbcType;
	
	/** java类型 */
	@Column(name = "java_type" )
	private String javaType;
	
	/** java字段 */
	@Column(name = "java_field" )
	private String javaField;
	
	/** 是否为主键 */
	@Column(name = "is_pk" )
	private Integer isPk = 0;
	
	/** 是否为空 */
	@Column(name = "is_null" )
	private Integer isNull = 0;
	
	/** 可插入 */
	@Column(name = "is_insert" )
	private Integer isInsert;
	
	/** 可修改 */
	@Column(name = "is_edit" )
	private Integer isEdit;
	
	/** 列表 */
	@Column(name = "is_list" )
	private Integer isList;
	
	/** 查询 */
	@Column(name = "is_query" )
	private Integer isQuery;
	
	/** 查询类型（等于、不等于、大于、小于、范围、左LIKE、右LIKE、左右LIKE）*/
	@Column(name = "query_type" )
	private String queryType;
	
	/** 显示类型 字段生成方案（文本框、文本域、下拉框、复选框、单选框、字典选择、人员选择、部门选择、区域选择）*/
	@Column(name = "show_type" )
	private String showType;
	
	/** 排序 */
	@Column(name = "sorts" )
	private Integer sorts;
	
	/** 备注 */
	@Column(name = "remarks" )
	private String remarks;
}
