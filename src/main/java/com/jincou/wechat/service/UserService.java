package com.jincou.wechat.service;


import com.jincou.wechat.domain.User;

/**
 *用户业务接口类
 */
public interface UserService {


     User saveWeChatUser(String code);

}
