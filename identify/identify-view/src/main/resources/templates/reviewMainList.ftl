<html>
<head>
    <title>复核业务列表</title>
</head>
<body>

<div>
    <!-- 查询条件 -->
    <div class="row">
        <div class="col-sm-12">
            <div class="form-inline search_area">
                <input type="hidden" value="${isJdc!""}" id="jdc">
                <div class="form-group">
                    <label class="control-label">编号：</label>
                    <input placeholder="编号" id="caseNoSearch" class="form-control">
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
                    <label class="control-label">申请时间：</label>
                    <span class="input-group date form_datetime">
                                <input class="form-control" id="startTimeSearch" name="startTime"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </span>
                    <span>至</span>
                    <span class="input-group date form_datetime">
                                <input class="form-control" id="endTimeSearch" name="endTime"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </span>
                </div>
                <div class="form-group">
                    <label class="control-label">当前状态：</label>
                    <select class="form-control" id="statusSearch">
                        <option value="">请选择</option>
                        <option value="1">已申请</option>
                        <option value="2">已受理</option>
                        <option value="3">已选择专家</option>
                        <option value="4">已确认专家</option>
                        <option value="5">已填写意见</option>
                        <option value="6">已办结</option>
                        <option value="10">不受理</option>
                    </select>
                </div>
                <div class="form-group">
                    <button class="btn btn-sm btn-info" id="searchBtn">
                        <i class="fa fa-search"></i> 搜索
                    </button>
                </div>
            </div>
        </div>
    </div>

    <div class="row" style="margin-top: 10px;">
        <div class="col-sm-12">
            <div>
                <table id="btTable" class="table table-condensed table-hover table-striped"></table>
            </div>
        </div>
    </div>


</div>
<ex-section id="ScriptBody">
    <script type="text/javascript" src="${request.contextPath}/js/main/modules/reviewMainList.js?v=1.0.1"></script>
    <script type="text/javascript">
        pageLogic.initData = {

            restUrlPrefix: "/reviewMain",
            queryMsg: {
                caseNo: "caseNoSearch",
                zone: "zoneSearch",
                street: "streetSearch",
                addressSearch: "addressSearch",
                orgSearch: "orgSearch",
                startTime: "startTimeSearch",
                endTime: "endTimeSearch",
                status: "statusSearch"
            }
        };

    </script>
</ex-section>
</body>
</html>
