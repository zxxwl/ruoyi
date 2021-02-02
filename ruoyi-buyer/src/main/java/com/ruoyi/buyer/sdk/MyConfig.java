package com.ruoyi.buyer.sdk;

import org.apache.commons.io.IOUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author ztt
 */
@Component("myConfig")
public class MyConfig extends WXPayConfig {
    private byte[] certData;
    public MyConfig() throws Exception {
        //读取方才存放在resource里边的证书
//        InputStream certStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("apiclient_cert.p12");
//        this.certData = IOUtils.toByteArray(certStream);
//        certStream.close();
    }
    @Override
    public String getAppID() {
        return WxProgramPayConfig.getAppId();
    }

    @Override
    public String getMchID() {
        return WxProgramPayConfig.getMchId();
    }

    @Override
    public String getKey() {
        return WxProgramPayConfig.getMchKey();
    }

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    @Override
    IWXPayDomain getWXPayDomain() {
        //必须实例化，否则WxPay初始化失败
        IWXPayDomain iwxPayDomain = new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }
            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new IWXPayDomain.DomainInfo(WXPayConstants.DOMAIN_API, true);
            }
        };
        return iwxPayDomain;
    }
}

