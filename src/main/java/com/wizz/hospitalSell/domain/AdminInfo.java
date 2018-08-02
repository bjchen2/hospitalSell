package com.wizz.hospitalSell.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 管理员实体类
 * Created By Cx On 2018/8/1 22:37
 */
@Entity
@DynamicInsert
@Data
public class AdminInfo {
    //管理员id
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)//JPA提供四种方法，自增性用identity.
    private Integer adiminId;//integer是int的封装类，为什么要用integer来存储。
    //管理员用户名
    private String adminName;
    //管理员密码
    private String adminPass;
    //注册时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//使用这个注解，框架自动将前台发来的日期转化为Date类型。
    private Date createTime;

    public AdminInfo(String adminName, String adminPass) {
        this.adminName = adminName;
        this.adminPass = adminPass;
    }

    public AdminInfo() {
    }
}
