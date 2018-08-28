package com.framework.loippi.utils.wechat.utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Plugin - 微信手机支付
 * 
 * @author Loippi Team
 * @version 2.0
 */
public class WeixinMobilePaymentPlugin {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private static HashMap<String, String> parameters = new HashMap<String, String>();

	// *******************App支付start**************************//
	// AppID(开放平台-管理中心)
	public static final String WECHAT_APP_ID = "wxffa284ef98e5ee04";// 共享公寓
	// AppSecret(开放平台-管理中心)
	public static final String WECHAT_SECRET = "b3de9aaf0e4d7a4f5a3187956b28dbde";// 共享公寓
	// 商户号（商户平台）
	public static final String PartnerKey = "1508363931";// 共享公寓
	// 在商户平台里面设置好的 api秘钥（商户平台）
	public static final String WECHAT_APP_PAY_SIGN_KEY = "Ycf5qAt9dzeQE9UqWQIVNiNRwm8VB3qv";// 共享公寓
	// 支付回调地址
	public static final String BASE_URL = "http://admin.mmwgxgy.com/shareflat";
	// *******************App支付end**************************//

	// **************以下参数不需要修改start**********************//
	// 签名方式 不需修改
	// public static String sign_type = "RSA2";
	// 支付网关（开发不需要改）
	public static final String pay_unifiedorder = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	// 支付宝网关
	public static final String refundUrl = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	// 证书路径（一般退款时需要使用到）
	public static final String certPath = "D:/apiclient_cert.p12"; // win下证书路径
	// String certPath = "/home/root/web/tomcat-a-caiqiao/apiclient_cert.p12";//linux下证书路径
	// **************以下参数不需要修改end**********************//

	public static Map getPrepaySn(AppOrder order) {

		HashMap<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("appid", WECHAT_APP_ID);
		parameterMap.put("body",
				StringUtils.abbreviate(order.getDescription().replaceAll("[^0-9a-zA-Z\\u4e00-\\u9fa5 ]", ""), 600));
		parameterMap.put("mch_id", PartnerKey);
		parameterMap.put("out_trade_no", order.getOrderSn());
		// parameterMap.put("total_fee",order.getPay().multiply(new
		// BigDecimal(100)).intValue()+"");
		parameterMap.put("total_fee", "1");
		parameterMap.put("notify_url",
				BASE_URL + "/api/client/callback/wechat/" + order.getType() + "/" + order.getOrderUuid() + ".json");
		parameterMap.put("spbill_create_ip", "120.24.230.80");
		parameterMap.put("nonce_str", WeixinUtils.createNoncestr());
		parameterMap.put("trade_type", "APP");
		// parameterMap.put("limit_pay", "no_credit");
		parameterMap.put("sign",
				WeixinUtils.sign(WeixinUtils.FormatBizQueryParaMap(parameterMap, false), WECHAT_APP_PAY_SIGN_KEY));

		Map<String, String> parameters = parameterMap;

		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("<xml>");
		Set<String> objSet = parameters.keySet();
		for (Object key : objSet) {
			if (key == null) {
				continue;
			}
			strBuilder.append("<").append(key.toString()).append(">");
			Object value = parameters.get(key);
			strBuilder.append(value.toString());
			strBuilder.append("</").append(key.toString()).append(">");
		}

		System.err.println("weixin pay strBuilder.toString()= " + strBuilder.toString());
		strBuilder.append("</xml>");
		String xml = WeixinUtils.request(pay_unifiedorder, "POST", strBuilder.toString()).toString();

		System.err.println("weixin pay=" + xml);
		if (StringUtils.isNotEmpty(xml) && xml.indexOf("SUCCESS") != -1) {
			if (xml.indexOf("prepay_id") != -1) {
				String prepayid = WeixinUtils.getJsonValue(xml, "prepay_id");

				HashMap<String, String> parameterMap2 = new HashMap<String, String>();
				parameterMap2.put("appid", WECHAT_APP_ID);
				parameterMap2.put("partnerid", PartnerKey);
				parameterMap2.put("prepayid", prepayid);
				parameterMap2.put("package", "Sign=WXPay");
				parameterMap2.put("noncestr", parameters.get("nonce_str"));
				parameterMap2.put("timestamp", WeixinUtils.getTimeStamp());
				parameterMap2.put("sign", WeixinUtils.sign(WeixinUtils.FormatBizQueryParaMap(parameterMap2, false),
						WECHAT_APP_PAY_SIGN_KEY));

				return parameterMap2;
			}
		}
		return null;
	}

	// 退款申请
	public boolean refundApply(String sn, BigDecimal payment) {
		HashMap<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("appid", WECHAT_APP_ID);
		parameterMap.put("mch_id", PartnerKey);
		parameterMap.put("out_trade_no", sn);
		parameterMap.put("total_fee", payment.multiply(new BigDecimal(100)).intValue() + "");
		parameterMap.put("refund_fee", payment.multiply(new BigDecimal(100)).intValue() + "");
		parameterMap.put("out_refund_no", System.currentTimeMillis() + "");
		parameterMap.put("op_user_id", PartnerKey);
		parameterMap.put("nonce_str", WeixinUtils.createNoncestr());
		parameterMap.put("sign",
				WeixinUtils.sign(WeixinUtils.FormatBizQueryParaMap(parameterMap, false), WECHAT_APP_PAY_SIGN_KEY));

		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("<xml>");
		Set<String> objSet = parameterMap.keySet();
		for (Object key : objSet) {
			if (key == null) {
				continue;
			}
			strBuilder.append("<").append(key.toString()).append(">");
			Object value = parameterMap.get(key);
			strBuilder.append(value.toString());
			strBuilder.append("</").append(key.toString()).append(">");
		}
		strBuilder.append("</xml>");
		String xml = WeixinUtils.requestSSL(refundUrl, certPath, PartnerKey, "POST", strBuilder.toString()).toString();
		if (StringUtils.isNotEmpty(xml) && xml.indexOf("SUCCESS") != -1) {

			String resultCode = WeixinUtils.getJsonValue(xml, "result_code");
			if ("SUCCESS".equalsIgnoreCase(resultCode)) {
				logger.info("微信退款申请成功");
				return true;
			} else {
				logger.error("微信退款申请失败--err_code_des:" + WeixinUtils.getJsonValue(xml, "err_code_des"));
				return false;

			}

		}
		return false;
	}

}
