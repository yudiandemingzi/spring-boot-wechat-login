package com.oujiong.wechat.service;


import com.oujiong.wechat.domain.User;

/**
 *用户业务接口类
 */
public interface UserService {


     User saveWeChatUser(String code);

}
