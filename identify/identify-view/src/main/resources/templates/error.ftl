<!--add by yanyj 20190115 start-->

<#if Request.onlyBody??>
    <#include "decorators/header-css.ftl">

    <#include "decorators/breadcrumb.ftl">

    <div class="page-content" id="pageContent">
    <div class="portlet box">
    <div class="portlet-body">
    <#include "errors/" +  Request.status_error_code + ".ftl">
    </div>
    </div>
    </div>

<#else>
    <!doctype html>
    <html>
    <head>
    <title></title>
    <#include "decorators/header-css.ftl">
</head>

<body>
<a id="totop" href="#"><i class="fa fa-angle-up"></i></a>

    <#include "decorators/topbar.ftl">

<div id="wrapper">
    <#include "decorators/left.ftl">
    <div id="page-wrapper">
    <#include "decorators/breadcrumb.ftl">

    <div class="page-content" id="pageContent">
    <div class="portlet box">
    <div class="portlet-body">
    <#include "errors/" +  Request.status_error_code + ".ftl">
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
    <#include "decorators/footer-js.ftl">
    <!-- 页面js代码-->
    </body>
    </html>

</#if>

<!--add by yanyj 20190115 end-->


