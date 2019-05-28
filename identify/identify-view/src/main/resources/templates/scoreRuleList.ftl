<html xmlns="http://www.w3.org/1999/html">
<head>
    <title>评分细则管理</title>
</head>
<body>
<div>
    <#--add by wangwj 20190121 start-->
        <ul class="nav nav-tabs" id="ul">
            <li class="active"><a href="#" name="1" id="">基础信息</a></li>
            <li><a href="#" name="2">良好信息</a></li>
            <li><a href="#" name="3" id="bad">不良信息</a></li>
        </ul>
        <div style="color:red;font-weight: bold" id="rule"></div>
        <div style="color: red"></div>
        <div style="color: red"></div>

    <#--<div class="row">-->
        <#--<div id="leftArea" class="col-md-3">-->
            <#--<div class="panel panel-grey">-->
                <#--<div class="panel-heading">-->
                    <#--<h3 class="panel-title" style="text-align: center;">评分类型</h3>-->
                <#--</div>-->
                <#--<div class="panel-body">-->
                    <#--<ul id="scoreType" class="list-group">-->
                    <#--</ul>-->
                <#--</div>-->
            <#--</div>-->
        <#--</div>-->

        <#--<div id="Area" class="col-md-12">-->
            <#--<div class="panel panel-grey">-->
                <#--<div class="panel-heading">-->
                    <#--<h3 class="panel-title" style="text-align: center">评分列表</h3>-->
                <#--</div>-->
                <div class="panel-body" style="padding-top: 0px;padding-right: 0px;padding-left: 0px;padding-bottom: 0px;">
                    <div id="mainGrid">
                        <table id="btTable" class="table table-condensed table-hover table-striped" ></table>
                    </div>
                </div>
            <#--</div>-->
        <#--</div>-->


</div>

<ex-section id="ScriptBody">
    <link type="text/css" rel="stylesheet" href="${request.contextPath}${urls.getForLookupPath('/css/modules/scoreRuleList.css')}" />
    <link type="text/css" rel="stylesheet" href="${request.contextPath}${urls.getForLookupPath('/css/modules/orgCreditScore.css')}"/>
    <script type="text/javascript" src="${request.contextPath}${urls.getForLookupPath('/js/main/modules/scoreRuleList.js')}"></script>
    <script type="text/javascript">
        pageLogic.initData = {
            modalParams: [
                {}
            ],
            restUrlPrefix: "/setting/scores",
            queryMsg: {}
        };

    </script>
</ex-section>
</body>
</html>
<#--add by wangwj 20190121 start-->