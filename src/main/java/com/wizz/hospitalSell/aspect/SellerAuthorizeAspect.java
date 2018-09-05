package com.wizz.hospitalSell.aspect;

import com.wizz.hospitalSell.constant.CookieConstant;
import com.wizz.hospitalSell.constant.RedisConstant;
import com.wizz.hospitalSell.exception.SellerAuthorizeException;
import com.wizz.hospitalSell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户登录信息检测
 * Created By Cx On 2018/8/1 14:03
 */
@Aspect
@Slf4j
@Component
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    //切点范围为com.wizz.hospitalSell.controller包下所有Seller*
    @Pointcut("execution(public * com.wizz.hospitalSell.controller..Seller*.*(..))")
    public void verify() {
    }

    @Before("verify()")
    public void doVerify() {
        //获取HttpHttpServletRequest
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //获取cookie
        Cookie cookie = CookieUtil.getCookie(request, CookieConstant.TOKEN);
        if (cookie == null) {
            //如果cookie不存在
            log.warn("【登陆校验】登录失败，cookie为空");
            throw new SellerAuthorizeException();
        }
        //从redis中获取token值
        String token = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(token)) {
            //如果redis中不存在,说明该token已被篡改
            log.warn("【登陆校验】登录失败，redis查询为空");
            throw new SellerAuthorizeException();
        }
    }
}
