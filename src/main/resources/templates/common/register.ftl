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
                    <label>已有管理员账号</label>
                    <input name="username" type="text" class="form-control"/>
                </div>
                <div class="form-group">
                    <label>已有管理员密码</label>
                    <input name="password" type="password" class="form-control"  />
                </div>
                <div class="form-group">
                    <label>新增管理员账号</label>
                    <input name="newUsername" type="text" class="form-control" />
                </div>
                <div class="form-group">
                    <label>新增管理员密码</label>
                    <input name="newPassword" type="password" class="form-control" />
                </div>
                <div class="form-group">
                <#--如果error信息存在，说明已有管理员账号有误-->
                    <#if error??>
                        <p class="text-danger">${error}</p>
                    </#if>
                </div>
                <button type="submit" class="btn btn-default">添加新管理员</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>