<!-- add by xujc 2018/11/30 start -->
<html>
<head>
    <title></title>
</head>
<body>
<div>
    <#-- update by wangwj 20190220 start-->
    <div class="panel panel-default">
        <div class="panel-heading" style="text-align: center">
            <input type="hidden" value="${(orgCredit.id)?default("")}" id="hideId">
            <input type="hidden" value="${orgCredit.identifyOrgId}" id="hideOrgId">
            <input type="hidden" value="${tab!}" id="tab">
            <div class="form-inline">
                <div class="form-group" style="margin-right: 30px">
                    <span style="font-weight: bold">机构名称:</span>
                    <span>${orgCredit.orgName}</span>
                </div>
                <div class="form-group" style="margin-right: 30px">
                    <span style="font-weight: bold">统一社会信用代码:</span>
                    <span>${orgCredit.unifiedCode}</span>
                </div>
                <div class="form-group" style="margin-right: 30px">
                    <span style="font-weight: bold">总分:</span>
                    <span id="allScore">
                            ${orgCredit.creditGradeScore!0}
                    </span>

                </div>
                <div class="form-group" style="margin-right: 30px">
                    <span style="font-weight: bold">信用等级:</span>
                    <span id="creditGradeName" style="color: #FDC561">${orgCredit.creditGradeName!""}</span>
                </div>
                <div class="form-group" style="margin-right: 30px">
                    <span style="font-weight: bold">法定代表人:</span>
                    <span>${orgCredit.legalPerson}</span>
                </div>
                <div class="form-group" style="margin-right: 30px">
                    <span id="pfnd" style="font-weight: bold">评分年度:</span>
                    <span id="hyear"></span>
                    <div class="input-group date form_datetime" id="syear">
                        <input class="form-control" id="year" name="year"  style="text-align: center" readonly/>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                    </div>
                </div>
            <#-- update by wangwj 20190220 end-->
            </div>
        </div>
        <div class="panel-body">
            <ul class="nav nav-tabs" id="ul">
                <li class="active"><a href="#" name="1" id="">基础信息</a></li>
                <li><a href="#" name="2">良好信息</a></li>
                <li><a href="#" name="3" id="bad">不良信息</a></li>
            </ul>
            <div style="color:red;font-weight: bold" id="rule"></div>

            <div style="color: red"></div>
            <div style="color: red"></div>

            <div style="text-align: center" id="contain">
                <table id="btTable" class="table table-condensed table-hover table-striped"></table>
                <img src="${request.contextPath}/img/noScore.png" id="noScore" style="display: none">
            </div>

            <div align="center" style="margin-top: 50px">

                <input type="button" class="btn btn-blue" value="保存" id="saveAndReturn" >
                <input type="button" class="btn btn-blue" value="保存并继续评分" id="saveAndContinue">
                <input type="button" class="btn btn-danger" value="返回" id="toList">
            </div>
        </div>
    </div>
    <#--弹出层显示详细 -->
    <#--add by wangwj 20190122 start-->
    <#-- add by wangwj 20190222 start-->
    <div id="modal">
        <form class="form-horizontal">
            <div class="form-group">
                <div class="" id="reason">
                </div>
            </div>
        </form>
    </div>

</div>
    <#-- add by wangwj 20190220 end-->
<ex-section id="ScriptBody">
    <link type="text/css" rel="stylesheet" href="${request.contextPath}${urls.getForLookupPath('/css/modules/orgCreditScore.css')}" />
    <script type="text/javascript" src="${request.contextPath}${urls.getForLookupPath('/js/main/modules/orgCreditScore.js')}"></script>
    <script type="text/javascript">
        pageLogic.initData = {
            restUrlPrefix: "/setting/scores",
            path: "${request.contextPath}",
            type: "${type}",
            queryMsg: {

            },
            modalParams: [
                {
                    width: 400,
                    height: 300,
                    title: ""
                }
            ]
        };

    </script>
</ex-section>

</body>
</html>
<!-- add by xujc 2018/11/30 end -->
<#--add by wangwj 20190120 end-->