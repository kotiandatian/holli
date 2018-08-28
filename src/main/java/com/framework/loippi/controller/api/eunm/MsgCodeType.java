package com.framework.loippi.controller.api.eunm;

public enum MsgCodeType {
	

	
	REGISTER("SMS_135230194", "注册"),
	QUICK_LOGIN("SMS_135230194", "绑定手机"),
	FIND_PASSWORD("SMS_135230194", "找回密码");
	
	public final String code;
	public final String alias;

	private MsgCodeType(String code, String alias) {
		this.code = code;
		this.alias = alias;
	}
	public static void main(String[] args) {
		System.out.println(MsgCodeType.REGISTER.code);
		
	}

}
