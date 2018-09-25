package com.jincou.wechat.controller;

import com.jincou.wechat.config.WeChatConfig;
import com.jincou.wechat.domain.JsonData;
import com.jincou.wechat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


@Controller
@RequestMapping("/api/v1/wechat")
public class WechatController {

    @Autowired
    private UserService userService;

    @Autowired
    private WeChatConfig weChatConfig;


    /**
     * 拼装微信扫一扫登录url
     */
    @GetMapping("login_url")
    @ResponseBody
    public JsonData loginUrl(@RequestParam(value = "access_page", required = true) String accessPage) throws UnsupportedEncodingException {

        //官方文档说明需要进行编码
        String callbackUrl = URLEncoder.encode(weChatConfig.getOpenRedirectUrl(), "GBK"); //进行编码

        //格式化，返回拼接后的url，去调微信的二维码
        String qrcodeUrl = String.format(WeChatConfig.OPEN_QRCODE_URL, weChatConfig.getOpenAppid(), callbackUrl, accessPage);

        return JsonData.buildSuccess(qrcodeUrl);

    }

    /**
     * 用户授权成功，获取微信回调的code
     */
    @GetMapping("/user/code")
    public void wechatUserCallback(@RequestParam(value = "code",required = true) String code, String state, HttpServletResponse response){

        System.out.println(code);

        userService.saveWeChatUser(code);
    }

}