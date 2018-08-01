package com.wizz.hospitalSell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 项目地址配置
 * Created By Cx On 2018/7/30 19:00
 */
@Component
@Data
@ConfigurationProperties("project-url")
public class ProjectConfig {
    /**
     * 项目访问url
     */
    private String sell;
}
