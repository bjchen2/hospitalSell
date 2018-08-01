package com.wizz.hospitalSell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 微信小程序有关配置
 * Created By Cx On 2018/8/1 13:32
 */
@Component
@Data
@ConfigurationProperties("wechat")
public class WeChatAccountConfig {

    /**
     * 小程序appId
     */
    private String APPID;

    /**
     * 小程序appSecret
     */
    private String APPSECRET;

    /**
     * 微信模板ID
     */
    private Map<String, String> templateId;
}
