package com.framework.loippi.utils.alipay;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import com.framework.loippi.utils.StringUtil;
import com.framework.loippi.utils.wechat.utils.AppOrder;
import com.framework.loippi.utils.wechat.utils.AzuraWechatUtil;

import net.sf.json.JSONObject;

public class AliPay {

	public static final String BASE_URL = "http://admin.mmwgxgy.com/shareflat";
	// 商户PID(合作身份者id，以2088开头的16位纯数字)(蚂蚁金服开放平台-密钥管理)
	public static final String PARTNER = "2088131201975461";// 共享公寓
	// appid(蚂蚁金服开放平台-密钥管理)
	public static final String APPID = "2018051760123831";// 共享公寓
	// 商户收款账号(收款支付宝账号)(商家中心-账户管理)
	public static final String SELLER = "971215820@qq.com";
	// 商户私钥，pkcs8格式(商户私钥，自助生成)

	//**************在（蚂蚁金服开放平台）下载公秘钥生成工具。并把生成公钥填在开放平台中start**********************//
	// app支付的 ,扫码支付都配置了同样秘钥公钥
	public static final String RSA_PRIVATE = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC7UUHvyAIEGhqviczpdnGW5W4fukns8EQ/1h8B6qmA33/M8EDwwBRNI1m8fiBhktp4rrGSLhVPlBfJsPPVv3NFimRFkAcGIkMu86x+u0cP6kZxkDzYP0LG38hU3MHyFSfy1AIRmM31oYDCEjJAqcRfQd7ZzeRf9VHOaIdHODWcNkZR7e1B4ebl4TbxXCp7/ZMD2SIWgXOcJ+VDZbcBxkHCU6iKg0XxPteMbMdEpWy2ujhbuYjYFh/LH+vfbgHJqOT+koQTUXxb+xNgQjaBvKrlQRDzaBT4+gvSRMZ+Q9BrFBSb4UjVyaUEckldp8iwF7dlBe7SBWNNu41W6tQDdtdpAgMBAAECggEAaacyfrGcWX3z0VDzUDdraEYuMB0d91Op2yvFv4oBY1wstIZKysA8cyneQpANaZuwOGOoaqAtiv4n0K5LSiO7O90MI6RSIeaPaFsuzyiIs3RbmVBW0U4VNyEf2KXPiuDd8HweUpiSito2SsCCsbBPv77imI0mI+2136tbqhMNQfbt3vzjLkoWkTO9FdD1M9JF217XF7LPA6m19yX8yj/Y7CJV+TkrqOrzgAV6Fkg2Nqpfq8phv9mkXWM64k98yi5OZfQXN/5BrhOdXQUa8PBjJkiH370T0cTH6h5ETjxAe+IZ9qePKVZgpILjUxwSjOG162PulLP8/S9m+goRJN001QKBgQD5efg9dtymHDF/ljjhh+hd7NKadzyMXIMwa2eT7qsLSZagaooFMQaWFl3hY6jIEU8nuZ/ApC6nbTGd8lz2/l0ZCFhzB/8LNqN7XaIFMxgZtSeDIQit17EegcFyO/dGQUQbJ7LqcKTm84Pw0YR/sT6uHaVVfXoMn34jtMaYZ5lUwwKBgQDANy/E+hH/Nyl/po5PoZHKuV/deTUbuf9gk1kIw1ytlTpePwT/1LU+Yu4iACK1grJfzA/QA1jU6y+BfUXnUTaWQMTqBMRqrNgHz4bBcJURPDUvfrhiZOFWiKueW6f8TRKloS+mtikuiyCVmjUnu5e+ideUJwwEFhWoyJ6AMTewYwKBgQC558cx3lJcwmOsZQBsJ//jhigGjVYkboggW9kqps71MVg6oEnLxpXW4EIDRGWw+R0YSD4gqvhNMZSo08OtFy2/CrYGum23KHc45jRnVYTfG1yfXc1+5l2Jkz3ZZ5BD3TzoU3QDOf/YTp0Cps7q7OT9kP/0rfV2VKAF8Z+QUJs1SwKBgQCmYd7WucRET+J7HZhIM8o4WfgyhM3E317AoStLm5gopWjt1c0lCmGMzCPniIiC58wxgjkZzRviymXHx8FvtuvyacRJ8bB3yoABWtEBtstCLQS4xQ8kFMC9n/Y2UI57pdeDSaAuxbWmFUYyWVazuv1iMH1MP/R1wQwkmuwgePoEkwKBgFwbVVzhkXDeuBpHXHF4AheL5+16LYNjobUfAMdeNY+R0msXLY9NzSJZOgwjk+QR5m5rcciTc8SGFKMxX6YfS4pPUUvepuTeRLyrcvMySNpKLUIID+jUCIeJ6UfcPmE4ZYlQi8sgfHoyTPIRFtF6/YRKfnUbk8vIF7rb/fvcbagD";
	// 支付宝公钥
	public static final String RSA_PUBLIC = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAu1FB78gCBBoar4nM6XZxluVuH7pJ7PBEP9YfAeqpgN9/zPBA8MAUTSNZvH4gYZLaeK6xki4VT5QXybDz1b9zRYpkRZAHBiJDLvOsfrtHD+pGcZA82D9Cxt/IVNzB8hUn8tQCEZjN9aGAwhIyQKnEX0He2c3kX/VRzmiHRzg1nDZGUe3tQeHm5eE28Vwqe/2TA9kiFoFznCflQ2W3AcZBwlOoioNF8T7XjGzHRKVstro4W7mI2BYfyx/r324Byajk/pKEE1F8W/sTYEI2gbyq5UEQ82gU+PoL0kTGfkPQaxQUm+FI1cmlBHJJXafIsBe3ZQXu0gVjTbuNVurUA3bXaQIDAQAB+IWYYlr9lTQN1EE252/dPycVBY7qkD4JlJKEjkCjIOhhcwQ/V9kJrBlfx24qYOlOXj91vrkbChxXW8tLc3rXIp9k5tFDv/EvFSyfXl2XqTrtXHkTlF7ufhTTYAt1ytsasPEEEOPMrxkTz4z5og7o9qR21liQIDAQAB";
	//**************在（蚂蚁金服开放平台）下载公秘钥生成工具。并把生成公钥填在开放平台中end**********************//
	
	
	//**************以下参数不需要修改start**********************//
	// 签名方式 不需修改
	public static String sign_type = "RSA2";
	// 字符编码-传递给支付宝的数据编码
	public static final String CHARSET = "utf-8";
	// 字符编码格式 目前支持 gbk 或 utf-8(AlipayNotify该类中使用到，主要用于检验支付宝返回信息是否被篡改)
	public static String input_charset = "utf-8";
	// 接口版本
	public static final String version = "1.0";
	// 支付宝网关
	public static final String ALIPAY_GATEWAY = "https://openapi.alipay.com/gateway.do";
	//**************以下参数不需要修改end**********************//
	/**
	 * call alipay sdk pay. 调用SDK支付 创建订单信息
	 * 
	 * @param subject
	 *            商品名称
	 * @param body
	 *            商品详情
	 * @param orderSn
	 *            商品名称
	 * @param price
	 *            商品金额
	 */
	public static String pay(AppOrder order) {
		Map<String, Object> biz_contentmap = new HashMap();
		biz_contentmap.put("timeout_express", "30m");
		biz_contentmap.put("product_code", "QUICK_MSECURITY_PAY");
		order.setAmount(new BigDecimal("0.01"));
		biz_contentmap.put("total_amount", order.getAmount());
		biz_contentmap.put("subject", order.getDescription());
		biz_contentmap.put("body", order.getDescription());
		biz_contentmap.put("out_trade_no", order.getOrderSn());
		biz_contentmap.put("enable_pay_channels", "balance,moneyFund");
		Map<String, String> keyValues = new HashMap<String, String>();

		keyValues.put("app_id", APPID);

		keyValues.put("biz_content", JSONObject.fromObject(biz_contentmap).toString());

		keyValues.put("charset", CHARSET);

		keyValues.put("method", "alipay.trade.app.pay");
		keyValues.put("sign_type", sign_type);

		keyValues.put("timestamp", StringUtil.date2StringTime(new Date()));

		keyValues.put("version", version);
		keyValues.put("notify_url",
				AzuraWechatUtil.BASE_URL + "/api/client/callback/alipay/" + order.getType() + "/" + order.getOrderUuid() + ".json");

		String orderParam = OrderInfoUtil2_0.buildOrderParam(keyValues);

		String sign = OrderInfoUtil2_0.getSign(keyValues, RSA_PRIVATE, true);
		String orderInfo = orderParam + "&" + sign;

		return orderInfo;
	}

	/**
	 * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
	 * 
	 */
	private String getOutTradeNo() {
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
		Date date = new Date();
		String key = format.format(date);

		Random r = new Random();
		key = key + r.nextInt();
		key = key.substring(0, 15);
		return key;
	}

	/**
	 * sign the order info. 对订单信息进行签名
	 * 
	 * @param content
	 *            待签名订单信息
	 */
	private String sign(String content) {
		return SignUtils.sign(content, RSA_PRIVATE);
	}
}
