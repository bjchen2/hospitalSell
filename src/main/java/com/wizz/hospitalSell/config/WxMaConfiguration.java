package com.wizz.hospitalSell.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.WxMaInMemoryConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 不能叫WxMaConfig，和cn.binarywang.wx.miniapp.config包冲突
 * Created By Cx On 2018/8/3 13:15
 */
@Component
public class WxMaConfiguration {

    @Autowired
    private WeChatAccountConfig properties;

    @Bean
    @ConditionalOnMissingBean
    public WxMaConfig maConfig() {
        WxMaInMemoryConfig config = new WxMaInMemoryConfig();
        config.setAppid(this.properties.getAppid());
        config.setSecret(this.properties.getSecret());
        config.setToken(this.properties.getToken());
        config.setAesKey(this.properties.getAesKey());
        config.setMsgDataFormat(this.properties.getMsgDataFormat());
        return config;
    }

    @Bean
    @ConditionalOnMissingBean
    public WxMaService wxMaService(WxMaConfig maConfig) {
        WxMaService service = new WxMaServiceImpl();
        service.setWxMaConfig(maConfig);
        return service;
    }

}
