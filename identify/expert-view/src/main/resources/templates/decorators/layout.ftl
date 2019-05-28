<!--add(update) by yanyj 20180926 start-->
<#if Request.onlyBody??>
    <#include "breadcrumb.ftl">

   <div class="page-content" id="pageContent">
       <div class="portlet box">
           <div class="portlet-body">
             <#include Request.ftlPath>
           </div>
       </div>
   </div>

<#else>
<!doctype html>
<html>
<head>
    <title></title>
    <#include "header-css.ftl">
</head>

<body>
<a id="totop" href="#"><i class="fa fa-angle-up"></i></a>

    <#include "topbar.ftl">

<div id="wrapper">
        <#include "left.ftl">
    <div id="page-wrapper">
        <#include "breadcrumb.ftl">

        <div class="page-content" id="pageContent">
            <div class="portlet box">
                <div class="portlet-body">
                     <#include Request.ftlPath>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="footer">
    <div class="copyright"><@spring.message "copyright"/></div>
</div>

<#include "footer-js.ftl">
<!-- 页面js代码-->
</body>
</html>

</#if>

<!--add(update) by yanyj 20180926 end-->
