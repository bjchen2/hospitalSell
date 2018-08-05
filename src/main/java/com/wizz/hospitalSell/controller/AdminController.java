package com.wizz.hospitalSell.controller;


import com.wizz.hospitalSell.config.ProjectConfig;
import com.wizz.hospitalSell.constant.CookieConstant;
import com.wizz.hospitalSell.constant.RedisConstant;
import com.wizz.hospitalSell.domain.AdminInfo;
import com.wizz.hospitalSell.domain.UserInfo;
import com.wizz.hospitalSell.enums.ResultEnum;
import com.wizz.hospitalSell.form.LoginForm;
import com.wizz.hospitalSell.form.RegisterForm;
import com.wizz.hospitalSell.service.AdminService;
import com.wizz.hospitalSell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 用户有关
 * Created By Cx On 2018/7/30 9:45
 */
@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    ProjectConfig projectConfig;

    /**
     * 登录页面跳转路由
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("common/index");
    }

    /**
     * 管理员登录路由
     */
    @PostMapping("/login")
    public ModelAndView login(@Valid LoginForm loginForm, BindingResult bindingResult,HttpServletResponse response) {
        Map<String, Object> m = new HashMap<>();
        AdminInfo adminInfo = new AdminInfo();
        if (bindingResult.hasErrors()) {
            log.error("[用户登录]参数不正确,{}", bindingResult.getFieldError().getDefaultMessage());
            m.put("error", bindingResult.getFieldError().getDefaultMessage());
            return new ModelAndView("common/index", m);
        }
        BeanUtils.copyProperties(loginForm,adminInfo);
        //1.在数据库中查询该用户是否存在
        if (!adminService.isAdminExist(adminInfo)) {
            m.put("error", ResultEnum.LOGIN_FAIL.getMsg());
            return new ModelAndView("common/index", m);
        }

        //2.设置token 到 redis
        //生成随机数，官方解释：使用加密的强伪随机数生成器生成该 UUID
        String token = UUID.randomUUID().toString();
        //过期时间，expire:期满
        Integer expire;
        if (loginForm.getRemember() != null){
            //如果记住登录状态
            expire = RedisConstant.REMEMBER;
        }else {
            expire = RedisConstant.EXPIRE;
        }
        //opsForValue表示对某个值进行操作，set参数：key、value、过期时间、时间单位
        //String.format(RedisConstant.TOKEN_PREFIX,token):将token按前者的格式，格式化
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), adminInfo.getAdminName(), expire, TimeUnit.SECONDS);

        //3.设置token 到 cookie
        CookieUtil.setCookie(response, CookieConstant.TOKEN, token, expire);
        return new ModelAndView("redirect:" + projectConfig.getSell() + "/seller/order/list");
    }

    /**
     * 管理员登出路由
     */
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> m = new HashMap<>();
        //获取cookie
        Cookie cookie = CookieUtil.getCookie(request, CookieConstant.TOKEN);
        if (cookie != null) {
            //若cookie存在，清除redis和cookie
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
            CookieUtil.setCookie(response, CookieConstant.TOKEN, null, 0);
        }
        m.put("msg", ResultEnum.LOGOUT_SUCCESS.getMsg());
        m.put("url", "/admin/index");
        return new ModelAndView("common/success", m);
    }

    /**
     * 新增管理员页面跳转
     */
    @GetMapping("/register")
    public ModelAndView register(@RequestParam(required = false) String error, HttpServletRequest request) {
        Map<String, Object> m = new HashMap<>();
        if (error != null) m.put("error",error);
        return new ModelAndView("common/register",m);
    }

    /**
     * 管理员注册操作
     */
    @PostMapping("/register")
    public ModelAndView doRegister(@Valid RegisterForm registerForm,BindingResult bindingResult) {
        Map<String,Object> m = new HashMap<>();
        AdminInfo adminInfo = new AdminInfo();
        if (bindingResult.hasErrors()) {
            log.error("[新增管理员]参数不正确,{}", bindingResult.getFieldError().getDefaultMessage());
            m.put("error", bindingResult.getFieldError().getDefaultMessage());
            return new ModelAndView("common/register", m);
        }
        BeanUtils.copyProperties(registerForm,adminInfo);
        if (!adminService.isAdminExist(adminInfo)){
            //若已有管理员不存在
            log.error("[新增管理员]已有管理员账号或密码有误,adminName={},adminPass={}",adminInfo.getAdminName(),adminInfo.getAdminPass());
            m.put("error","已有管理员账号或密码有误");
            return new ModelAndView("common/register",m);
        }
        if (adminService.isAdminNameExist(registerForm.getNewAdminName())){
            log.error("[新增管理员]新增管理员用户名已存在,adminName={}",registerForm.getNewAdminName());
            m.put("error","新增管理员用户名已存在");
            return new ModelAndView("common/register",m);
        }
        adminInfo = new AdminInfo(registerForm.getNewAdminName(),registerForm.getNewAdminPass());
        adminService.create(adminInfo);
        m.put("url", "/admin/index");
        m.put("msg","添加管理员成功");
        return new ModelAndView("common/success",m);
    }
}
