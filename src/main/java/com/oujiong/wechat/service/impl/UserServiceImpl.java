package com.oujiong.wechat.service.impl;


import com.oujiong.wechat.config.WeChatConfig;
import com.oujiong.wechat.domain.User;
import com.oujiong.wechat.service.UserService;
import com.oujiong.wechat.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private WeChatConfig weChatConfig;


    @Override
    public User saveWeChatUser(String code) {

        //1、通过openAppid和openAppsecret和微信返回的code，拼接URL
        String accessTokenUrl = String.format(WeChatConfig.OPEN_ACCESS_TOKEN_URL, weChatConfig.getOpenAppid().trim(), weChatConfig.getOpenAppsecret().trim(), code);

        //2、通过URL再去回调微信接口 (使用了httpclient和gson工具）
        Map<String, Object> baseMap = HttpUtils.doGet(accessTokenUrl);

        //3、回调成功后获取access_token和oppid
        if (baseMap == null || baseMap.isEmpty()) return null;
        String accessToken = (String) baseMap.get("access_token");
        String openId = (String) baseMap.get("openid");

        //4、access_token和openid拼接URL（openid是用户唯一标志）
        String userInfoUrl = String.format(WeChatConfig.OPEN_USER_INFO_URL, accessToken, openId);

        //5、通过URL再去调微信接口获取用户基本信息
        Map<String, Object> baseUserMap = HttpUtils.doGet(userInfoUrl);

        if (baseUserMap == null || baseUserMap.isEmpty()) return null;

        //6、获取用户姓名、性别、城市、头像等基本信息
        String nickname = (String) baseUserMap.get("nickname");
        Integer sex = (Integer) baseUserMap.get("sex");
        String province = (String) baseUserMap.get("province");
        String city = (String) baseUserMap.get("city");
        String country = (String) baseUserMap.get("country");
        String headimgurl = (String) baseUserMap.get("headimgurl");

        return null;
    }
}
