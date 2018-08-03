<html>
    <#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
<#--边栏sidebar-->
        <#include "../common/nav.ftl">
<#--主要内容-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                        <#--商品概要表-->
                            <table class="table table-bordered table-condensed">
                                <thead>
                                <tr>
                                    <th>类目id</th>
                                    <th>名称</th>
                                    <th>类目编号</th>
                                    <th>创建时间</th>
                                    <th>修改时间</th>
                                    <th colspan="2">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                            <#list categories as category>
                            <tr>
                                <td>${category.categoryId}</td>
                                <td>${category.categoryName}</td>
                                <td>${category.categoryType}</td>
                                <td>${category.createTime}</td>
                                <td>${category.updateTime}</td>
                                <td>
                                    <#--如果类目编号为0，不能修改-->
                                    <#if category.categoryType != 0>
                                    <a href="/seller/category/index?categoryId=${category.categoryId}">修改</a>
                                    </#if>
                                </td>
                            </tr>
                            </#list>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>