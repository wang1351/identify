<!--add(update) by yanyj 20180926 start-->
<#if Request.onlyBody??>
    <#include "../decorators/breadcrumb.ftl">

    <div class="page-content" id="pageContent">
    <div class="portlet box">
    <div class="portlet-body">
        <div id="error-page" class="error-404">
            <div id="error-page-content">
                <h1>出错啦...</h1>
                <p>建筑物维修中，找不到您请求的网页。</p>
                <div class="div_btn"><a href="javascript:void(0);" onclick="getBack()" class="btn btn-danger">返回</a></div>
            </div>
        </div>    </div>
    </div>
    </div>

<#else>
    <!doctype html>
    <html>
    <head>
    <title></title>
    <#include "../decorators/header-css.ftl">
</head>

<body>
<a id="totop" href="#"><i class="fa fa-angle-up"></i></a>

    <#include "../decorators/topbar.ftl">

<div id="wrapper">
    <#include "../decorators/left.ftl">
    <div id="page-wrapper">
    <#include "../decorators/breadcrumb.ftl">

    <div class="page-content" id="pageContent">
    <div class="portlet box">
    <div class="portlet-body">
        <div id="error-page" class="error-404">
            <div id="error-page-content">
                <h1>出错啦...</h1>
                <p>建筑物维修中，找不到您请求的网页。</p>
                <div class="div_btn"><a href="javascript:void(0);" onclick="getBack()" class="btn btn-danger">返回</a></div>
            </div>
        </div>
    </div>
    </div>
    </div>
    </div>
</div>
<div id="footer">
    <div class="copyright"><@spring.message "copyright"/></div>
</div>
<script type="text/javascript">
window.isErrorPage = true;

    window.errorMenuId = "${Request.errorMenuId!""}";
    window.errorMenuId1 = "${Request.CURRENT_SELECT_MENU_I1D!""}";

</script>
<#include "../decorators/footer-js.ftl">

    <!-- 页面js代码-->
    </body>
    </html>

</#if>

<!--add(update) by yanyj 20180926 end-->

