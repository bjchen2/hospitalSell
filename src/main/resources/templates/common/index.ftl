<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <#include "../common/header.ftl">
</head>

<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form role="form" method="post" action="/user/login">
                <div class="form-group">
                    <label>用户名</label>
                    <input name="username" type="text" class="form-control" value="${(username)!''}"/>
                </div>
                <div class="form-group">
                    <label>密码</label>
                    <input name="password" type="password" class="form-control" value="${(password)!''}" />
                </div>
                <div class="form-group">
                    <#--如果error信息存在，说明登录失败-->
                    <#if error??>
                        <p class="text-danger">${error}</p>
                    </#if>
                </div>
                <div class="checkbox">
                    <label><input type="checkbox" name="remember" />十天内记住登录状态</label>
                </div>
                <button type="submit" class="btn btn-default">登录</button>
            </form>
        </div>
    </div>
</div>
<#--<form action="/user/login" method="get">-->
    <#--<table>-->
        <#--<tr>-->
            <#--<td>用户名：</td>-->
            <#--<td><input type="text" name="username" value="${userInfo.getUsername}"></td>-->
        <#--</tr>-->
        <#--<tr>-->
            <#--<td>密码：</td>-->
            <#--<td><input type="password" name="password" value="${userInfo.getPassword}"></td>-->
        <#--</tr>-->
        <#--<tr>-->
            <#--<td colspan="2">-->
                <#--<input type="checkbox" name="remeber">十天内记住登录状态-->
            <#--</td>-->
        <#--</tr>-->
        <#--<tr>-->
            <#--<td colspan="2">-->
                <#--<input type="submit" value="登录">-->
            <#--</td>-->
        <#--</tr>-->
    <#--</table>-->
<#--</form>-->
</body>
</html>