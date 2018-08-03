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
    private String appid;

    /**
     * 小程序appSecret
     */
    private String secret;

    /**
     * 小程序令牌
     */
    private String token;

    /**
     * 加密密钥
     */
    private String aesKey;

    /**
     * 数据格式
     */
    private String msgDataFormat;

    /**
     * 微信模板ID
     */
    private Map<String, String> templateId;
}
