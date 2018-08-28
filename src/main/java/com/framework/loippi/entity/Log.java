package com.framework.loippi.entity;

import java.util.Date;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 系统日志
 * 
 * @author Loippi Team
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_SYSTEM_LOGS")
public class Log implements GenericEntity {
	private static final long serialVersionUID = -4494144902110236826L;
	
	/** "日志内容"属性名称 */
	public static final String LOG_CONTENT_ATTRIBUTE_NAME = Log.class.getName() + ".CONTENT";
	
		/** 编号 */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 创建时间 */
	@Column(name = "create_date" )
	private java.util.Date createDate;
	
	/** 操作 */
	@Column(name = "operations" )
	private String operations;
	
	/** 操作人 */
	@Column(name = "operator" )
	private String operator;
	
	/** 内容 */
	@Column(name = "content" )
	private String content;
	
	/** 参数 */
	@Column(name = "parameter" )
	private String parameter;
	
	/** ip */
	@Column(name = "ip" )
	private String ip;
	

}
