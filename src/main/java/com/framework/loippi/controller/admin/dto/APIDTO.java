package com.framework.loippi.controller.admin.dto;

import lombok.Data;

/**
 * API DTO
 * 
 * @author Loippi Team
 * @version 2.0
 */
@Data
public class APIDTO {

	/** 名称 */
	private String iname;
	
	/** 请求方式 */
	private String method;
	
	/** 接口地址 */
	private String url;
	
	/** 接口描述 */
	private String description;
	
	/** 分类 */
	private Long categoryId;
	
	/** 示例代码*/
	private String codes;
	
}
