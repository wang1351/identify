<html>
<head>
    <title>鉴定业务列表</title>
</head>
<body>
<div>
    <#-- update by xujc 2019/3/12 start -->
    <!-- 查询条件 -->
    <div class="form-inline search_area">
        <input type="hidden" value="${isJdc!""}" id="jdc">
        <div class="form-group">
            <label class="control-label">编号：</label>
            <input placeholder="编号" id="caseNo" class="form-control">
        </div>

        <div class="form-group">
            <label class="control-label">区属：</label>
            <select class="form-control" id="zoneSearch">

            </select>
        </div>
        <div class="form-group">
            <label class="control-label">街道：</label>
            <select class="form-control" id="streetSearch">

            </select>
        </div>
        <div class="form-group">
            <label class="control-label">房屋地址：</label>
            <input placeholder="房屋地址" id="addressSearch" class="form-control">
        </div>
        <div class="form-group" id="org">
            <label class="control-label">鉴定单位：</label>
            <select class="form-control" id="orgSearch">
            </select>
        </div>
        <div class="form-group">
            <label class="control-label">申请方式：</label>
            <select class="form-control" id="applySearch">
                <option value="">请选择</option>
                <option value="1">人工窗口</option>
                <option value="2">我的南京</option>
                <option value="3">房产微政务</option>
            </select>
        </div>
        <div class="form-group">
            <label class="control-label">申请时间：</label>
            <span class="input-group date form_datetime">
                <input id="startTimeSearch" class="form-control">
                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </span>
            <span>至</span>
            <span class="input-group date form_datetime">
                <input id="endTimeSearch" class="form-control">
                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </span>
        </div>
        <div class="form-group">
            <label class="control-label">鉴定业务当前状态：</label>
            <select class="form-control" id="nowSearch">
                <option value="">请选择</option>
                <option value="1">已申请</option>
                <option value="2">已受理</option>
                <option value="3">已初勘</option>
                <option value="4">已出具方案</option>
                <option value="5">已签订合同</option>
                <option value="6">已编制鉴定报告</option>
                <option value="7">已审核</option>
                <option value="8">已签发</option>
                <option value="9">已发放</option>
                <option value="10">不受理</option>
                <option value="12">已终止</option>
                <option value="13">已挂起</option>
                <option value="15">拒绝申请</option>
            </select>
        </div>
        <div class="form-group">
            <button class="btn btn-sm btn-info" id="searchBtn">
                <i class="fa fa-search"></i> 搜索
            </button>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <div>
                <table id="btTable" class="table table-condensed table-hover table-striped">
                </table>

            </div>
        </div>
    </div>
<#-- update by xujc 2019/3/12 end -->
    <ex-section id="ScriptBody">
        <link type="text/css" rel="stylesheet" href="${request.contextPath}${urls.getForLookupPath('/css/modules/scoreRuleList.css')}"/>
        <script type="text/javascript"
                src="${request.contextPath}${urls.getForLookupPath('/js/main/modules/identifyMainList.js')}"></script>
        <script type="text/javascript">
            pageLogic.initData = {
                restUrlPrefix: "/identifies",
                queryMsg: {
                    caseNo: "caseNo",
                    contentSearch: "contentSearch",
                    zone: "zoneSearch",
                    street: "streetSearch",
                    addressSearch: "addressSearch",
                    applySearch: "applySearch",
                    startTimeSearch: "startTimeSearch",
                    endTimeSearch: "endTimeSearch",
                    nowSearch: "nowSearch",
                    orgSearch:"orgSearch"
                }
            };

        </script>
    </ex-section>
</div>
</body>
</html>
<#--add  by wangwj 20181113 start-->