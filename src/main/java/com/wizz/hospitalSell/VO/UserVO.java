package com.wizz.hospitalSell.VO;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用户有关
 * Created By Cx On 2018/8/3 16:56
 */
@Data
@AllArgsConstructor
public class UserVO {

    //用户id
    private Integer userId;
    //用户的微信名
    private String userName;
    //用户的openid
    private String userOpenid;
    //用户头像
    private String userIcon;

    public UserVO(){

    }
}
