package com.wizz.hospitalSell.utils;

import com.wizz.hospitalSell.VO.ResultVO;

/**
 * 响应结果集工具类
 * Created By Cx On 2018/6/10 22:47
 */
public class ResultUtil {

    public static ResultVO success(Object data) {
        return new ResultVO(0, "请求成功", data);
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(Integer code, String msg) {
        return new ResultVO(code, msg, null);
    }
}
