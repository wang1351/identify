<html>
<head>
    <title>选择专家</title>
</head>
<body>

<div>
<#include "reviewDetail.ftl" />
    <form class="form-horizontal" style="margin-top: 10px;" action="" id="expertForm">
        <input type="hidden" name="reviewId" id="reviewId" value="${reviewId}" />
        <div class="form-group" id="chooceType">
            <div class="col-xs-4 text-right">
                <span style="font-weight: bold">选择专家方式</span>
            </div>

            <div class="col-xs-6">
                <label class="radio-inline">
                    <input class="accept" name="chooceType" type="radio" value="1" checked> 手动选择
                </label>
                <label class="radio-inline">
                    <input class="unAccept"  name="chooceType" type="radio" value="2"> 随机选择
                </label>
            </div>
        </div>
        <div class="form-group" id="handChooce">
            <div class="col-xs-6 col-md-offset-1 col-md-5 col-lg-offset-2 col-lg-4">
                <div class="panel panel-default">
                    <div class="panel-heading"><span class="small">请选择专家</span></div>
                    <div class="panel-body" style="height:300px;overflow:auto">
                        <ul class="ztree" id="expertTree" name="expertTree"></ul>
                    </div>
                </div>
            </div>

            <div class="col-xs-6 col-md-5 col-lg-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <span class="small">已选择</span>
                        <span class="small text-primary" style="margin-left: 30px" id="clearCheck"><i class="glyphicon glyphicon-refresh icon-refresh"></i>清空</span>
                    </div>
                    <div class="panel-body" style="height:300px">
                        <textarea style="height:100%" class="form-control" id="selected" name="selected"></textarea>
                        <input id="selectedId" type="hidden" value=""/>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-group" style="display: none;" id="autoDiv">

            <div class="col-xs-4 text-right">
                <span id="textCon"></span>
            </div>
            <div class="col-xs-8">
                <a class="btn btn-blue" id="randomBtn">
                    <i class="fa fa-file-text-o"></i> 随机选择专家
                </a>
            </div>
            <div class="col-xs-12">
                <hr class="clear" style="border-top-style:dotted" color="#111111" size="1">
                <div class="col-md-8 col-md-offset-2">
                    <span id="autoSelected"></span>
                    <input id="autoSelectedId" type="hidden" value=""/>
                </div>
            </div>
        </div>
    </form>

    <div class="button_area">
        <button class="btn btn-default btn-blue" id="saveBtn">
            <i class="fa fa-save"></i> 确定
        </button>
        <button class="btn btn-default btn-danger" id="backBtn">
            <i class="fa fa-reply"></i> 返回
        </button>
    </div>
</div>
<#--<#include "reviewDetail.ftl" />-->
<ex-section id="ScriptBody">
    <link type="text/css" rel="stylesheet" href="${request.contextPath}${urls.getForLookupPath('/css/modules/orgCreditScore.css')}" />
    <script type="text/javascript" src="${request.contextPath}${urls.getForLookupPath('/js/main/modules/reviewExpertList.js')}"></script>
    <script type="text/javascript">
        pageLogic.initData = {
            restUrlPrefix: "/tasks/review",
            reviewId: "${reviewId}"
        };
    </script>
</ex-section>
</body>
</html>
