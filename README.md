整个扫码登录功能根据 微信官方扫描登录API 实现

[网站应用微信登录开发指南](https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1419316505&token=&lang=zh_CN)

针对`微信扫码登录功能` 下面都有博客详细的说明

1、[扫码登录流程讲解、获取授权登陆二维码](https://www.cnblogs.com/qdhxhz/p/9671802.html)

2、[本地调试工具ngrok、微信回调ngrok域名](:https://www.cnblogs.com/qdhxhz/p/9678137.html)

3、[授权码code获取用户基本信息](https://www.cnblogs.com/qdhxhz/p/9700715.html)

#### 技术架构

项目总体技术选型

```
SpringBoot2.1.5 + Maven3.5.4 + Mybatis + lombok(插件) + ngrok
```

`注意`

为了能够实现本地调试，这里用了ngrok穿透技术，它的作用就是：

微信回调商户接口的时候，通过ngrok工具就可以回调到本地，方便本地调试。
