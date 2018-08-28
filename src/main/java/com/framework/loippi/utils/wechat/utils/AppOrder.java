package com.framework.loippi.utils.wechat.utils;



import java.math.BigDecimal;
import java.util.Date;

import com.esotericsoftware.kryo.NotNull;

import lombok.Data;


/**
 * app支付订单信息表
 */

@Data
public class AppOrder  {
	
	/**
	 * 订单号（逻辑订单号）
	 */
	@NotNull
	private String orderSn;

	/**
	 * 订单随机数（用做回调查找订单使用）
	 */
	@NotNull
	private String orderUuid;
	/**
	 * 支付描述信息
	 */
	@NotNull
	private String description;
	/**
	 * 支付业务类型  
	 */
	@NotNull
	private Integer type;
	/**
	 * 总金额
	 */
	@NotNull
	private java.math.BigDecimal amount;

}
