<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
<link rel="shortcut icon" href="${request.contextPath}/img/favicon.ico" type="image/x-icon">
<title><sitemesh:write property="title"/></title>

<!-- vendor components css -->
<link type="text/css" rel="stylesheet" href="<@spring.message "frontend.url"/>/css/vendor/bootstrap/css/bootstrap.css"/>
<link type="text/css" rel="stylesheet" href="<@spring.message "frontend.url"/>/css/vendor/font-awesome/css/font-awesome.css"/>
<link type="text/css" rel="stylesheet" href="<@spring.message "frontend.url"/>/css/vendor/animate/css/animate.css"/>
<link type="text/css" rel="stylesheet" href="<@spring.message "frontend.url"/>/css/vendor/ztree/metroStyle/metroStyle.css">
<link type="text/css" rel="stylesheet" href="<@spring.message "frontend.url"/>/css/vendor/pace/pace-theme-flash.css">
<link type="text/css" rel="stylesheet" href="<@spring.message "frontend.url"/>/js/vendor/layer/skin/layer.css" id="layui_layer_skinlayercss">
<link type="text/css" rel="stylesheet" href="<@spring.message "frontend.url"/>/css/vendor/uploader/webuploader.css">


<!-- plugins css -->
<link type="text/css" rel="stylesheet" href="<@spring.message "frontend.url"/>/css/plugins/jquery-ui/jquery-ui-1.10.4.min.css">
<link type="text/css" rel="stylesheet" href="<@spring.message "frontend.url"/>/css/plugins/bootstrap/bootstrap-table.min.css">
<link type="text/css" rel="stylesheet" href="<@spring.message "frontend.url"/>/css/plugins/bootstrap/bootstrap-datetimepicker.css">
<link type="text/css" rel="stylesheet" href="<@spring.message "frontend.url"/>/js/plugins/bootstrap/select/css/bootstrap-select.css">
<link type="text/css" rel="stylesheet" href="<@spring.message "frontend.url"/>/js/plugins/bootstrap/bootstrap-switch.css">

<!-- custom css -->
<link type="text/css" rel="stylesheet" href="<@spring.message "frontend.url"/>/css/main/core.css?v=<@spring.message 'css.version'/>">
<link type="text/css" rel="stylesheet" href="<@spring.message "frontend.url"/>/css/skin/theme-default/main.css?v=<@spring.message 'css.version'/>" id="theme-change">
<link type="text/css" rel="stylesheet" href="<@spring.message "frontend.url"/>/css/main/layout.css">

<link type="text/css" rel="stylesheet" href="${request.contextPath}/css/main/common.css">
<link type="text/css" rel="stylesheet" href="${request.contextPath}/css/modules/error.css">


<style>
    #side-menu > li {
        cursor: pointer;
    }

    #page-wrapper .page-title-breadcrumb{
        display: block!important;
    }

    .arrow-down{
        float:left;
        margin-top:4px;
        width:12px;
        height:12px;
        line-height:12px;
        margin-right:10px;
        border-radius:6px;
        background-color:#fff;
        text-align:center;
        color:#3498db;

    }

    .arrow-down:before{
        content: "\f107";
    }

    li.menu_hide{
        display: none;
    }

    .richSelect > .btn,
    .richSelect > .btn:hover{
        background-color: #fff!important;
    }


</style>
<#--
<link type="text/css" rel="stylesheet" href="<@spring.message "frontend.url"/>/css/main/common.css">
-->

<sitemesh:write property='ex-section.CSS'/>