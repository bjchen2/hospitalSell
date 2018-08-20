package com.wizz.hospitalSell.domain;


import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息实体类
 * Created By Cx On 2018/7/27 18:49
 */
@Data
@DynamicInsert
@DynamicUpdate
@Entity
public class UserInfo implements Serializable{

    private static final long serialVersionUID = -6303134384477906811L;
    //用户id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    //用户的微信名
    private String userName;
    //用户的openid
    private String userOpenid;
    //用户头像
    private String userIcon;
    //用户性别
    private Integer userGender;
    //用户地址
    private String userAddress;
    //用户电话
    private String userPhone;
    //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //最近修改时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public UserInfo(String userName, String userOpenid, String userIcon,Integer userGender) {
        this.userName = userName;
        this.userOpenid = userOpenid;
        this.userIcon = userIcon;
        this.userGender = userGender;
    }

    public UserInfo() {
    }
}
