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
                            <#--商品评价搜索框-->
                            <form role="form" method="get" action="/seller/product/comment">
                                <div class="form-group">
                                    <input name="productName" type="text" class="form-control" />
                                    <button type="submit" class="btn btn-default">按商品名称搜索</button>
                                </div>
                            </form>
                        <#--商品评价表-->
                            <table class="table table-bordered table-condensed">
                                <thead>
                                <tr>
                                    <th>商品id</th>
                                    <th>名称</th>
                                    <th>商品质量评价（单位：星-人）</th>
                                    <th>商品口味评价（单位：星-人）</th>
                                    <th>商品包装评价（单位：星-人）</th>
                                    <th>总评</th>
                                </tr>
                                </thead>
                                <tbody>
                            <#list productCommentDtoPage.getContent() as productComment>
                            <tr>
                                <td>${productComment.productId}</td>
                                <td>${productComment.productName}</td>
                                <#--freemarker不能用.get方法，且数组中的值必须是字符串-->
                                <td><p>1星：${productComment.qualityScore["1"]}，    2星：${productComment.qualityScore["2"]}，</p><p>3星：${productComment.qualityScore["3"]}，    4星：${productComment.qualityScore["4"]}，    5星：${productComment.qualityScore["5"]}</p></td>
                                <td><p>1星：${productComment.tasteScore["1"]}，    2星：${productComment.tasteScore["2"]}，</p><p>3星：${productComment.tasteScore["3"]}，    4星：${productComment.tasteScore["4"]}，    5星：${productComment.tasteScore["5"]}</p></td>
                                <td><p>1星：${productComment.packingScore["1"]}，    2星：${productComment.packingScore["2"]}，</p><p>3星：${productComment.packingScore["3"]}，    4星：${productComment.packingScore["4"]}，    5星：${productComment.packingScore["5"]}</p></td>
                                <td>${productComment.result}</td>
                            </tr>
                            </#list>
                                </tbody>
                            </table>
                        <#--分页-->
                            <ul class="pagination">
                            <#--lte表示小于等于，gte表示大于等于-->
                            <#if currentPage lte 1>
                                <li class="disabled"><a href="#">上一页</a></li>
                            <#else>
                                <li><a href="/seller/product/comment?page=${currentPage-1}&size=${size}&productName=${key!''}">上一页</a></li>
                            </#if>
                            <#--0..orderDtoPage.getTotalPages()表示一个0到orderDtoPage.getTotalPages()的list-->
                            <#list 1..productCommentDtoPage.getTotalPages() as index>
                                <#if currentPage == index>
                                    <li class="disabled"><a href="#">${index}</a></li>
                                <#else>
                                    <li><a href="/seller/product/comment?page=${index}&size=${size}&productName=${key!''}">${index}</a></li>
                                </#if>
                            </#list>
                            <#--lte表示小于等于，gte表示大于等于-->
                            <#if currentPage gte productCommentDtoPage.getTotalPages()>
                                <li class="disabled"><a href="#">下一页</a></li>
                            <#else>
                                <li><a href="/seller/product/list?page=${currentPage+1}&size=${size}&productName=${key!''}">下一页</a></li>
                            </#if>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>