package com.framework.loippi.controller.api.common;

import lombok.Data;

/**
 * 登陆用户
 */
@Data
public class AppLoginUser {
    private Long id;
    private String phone;
    private String nickname;
    private String avatar;
    private String sessionId;
    private String shareCode;

//    public static AppLoginUser build(AppUser appUser){
//        AppLoginUser loginUser=new AppLoginUser();
//        loginUser.setId(appUser.getId());
//        loginUser.setAvatar(appUser.getAvatar());
//        loginUser.setNickname(appUser.getNickname());
//        loginUser.setPhone(appUser.getPhone());
//        RandomGUIDUtil guidUtil=new RandomGUIDUtil();
//        loginUser.setSessionId(guidUtil.valueAfterMD5);
//        loginUser.setShareCode(appUser.getShareCode());
//        return loginUser;
//    }
}
