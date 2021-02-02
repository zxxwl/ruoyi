package com.ruoyi.buyer.sdk;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 *   微信小程序 配置
 *
 *
 * @author majker
 * @date 2019-02-25 10:17
 * @version 1.0
 **/
@Component
@ConfigurationProperties(prefix = "wx.pay")
public class WxProgramPayConfig {

    /**
     * AppID(小程序ID)
     */
    public static String appId;

    /**
     * 你自己的商户ID号
     */
    public static String mchId;

    /**
     * 商户证书位置设置的密钥
     */
    public static String mchKey;

    /**
     * 回调地址
     */
    public static String notify_url;

    public static String getNotify_url() {
        return notify_url;
    }

    public static void setNotify_url(String notify_url) {
        WxProgramPayConfig.notify_url = notify_url;
    }

    public static String getAppId() {
        return appId;
    }

    public static void setAppId(String appId) {
        WxProgramPayConfig.appId = appId;
    }

    public static String getMchId() {
        return mchId;
    }

    public static void setMchId(String mchId) {
        WxProgramPayConfig.mchId = mchId;
    }

    public static String getMchKey() {
        return mchKey;
    }

    public static void setMchKey(String mchKey) {
        WxProgramPayConfig.mchKey = mchKey;
    }
}

