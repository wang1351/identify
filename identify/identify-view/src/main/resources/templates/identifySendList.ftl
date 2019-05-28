<html>
<head>
    <title></title>
</head>
<body>

<div>
    <!-- 查询条件 -->
    <div class="form-inline search_area">
        <div class="form-group">
            <label class="control-label">名称：</label>
            <input placeholder="名称" id="nameSearch" class="form-control">
        </div>
    </div>

    <!-- 工具栏 -->
    <div class="tool_area clearfix">
        <div class="pull-left">
            <button class="btn btn-sm btn-primary" id="addBtn">
                <i class="fa fa-plus"></i> 添加
            </button>
            <button class="btn btn-sm btn-success" id="modifyBtn">
                <i class="fa fa-file-text-o"></i> 修改
            </button>
            <button class="btn btn-sm btn-danger" id="delBtn">
                <i class="fa fa-trash-o"></i> 删除
            </button>
        </div>
        <div class="pull-right">
            <button class="btn btn-sm btn-info" id="searchBtn">
                <i class="fa fa-search"></i> 搜索
            </button>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <div>
                <table id="btTable" class="table table-condensed table-hover table-striped"></table>
            </div>
        </div>
    </div>


    <div id="modal">
        <form class="form-horizontal">
                        <input type="hidden" id="id"/>
                <div class="form-group">
                    <div class="col-md-2 text-right">
                        <span><span style="color: red;">* </span>房屋鉴定业务主表ID</span>
                    </div>
                    <div class="col-md-10">
                        <input class="form-control" id="mainId" name="mainId">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-2 text-right">
                        <span><span style="color: red;">* </span>操作时间</span>
                    </div>
                    <div class="col-md-10">
                        <input class="form-control" id="operatorTime" name="operatorTime">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-2 text-right">
                        <span><span style="color: red;">* </span>操作者姓名</span>
                    </div>
                    <div class="col-md-10">
                        <input class="form-control" id="operatorUserName" name="operatorUserName">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-2 text-right">
                        <span><span style="color: red;">* </span>鉴定报告编制表ID</span>
                    </div>
                    <div class="col-md-10">
                        <input class="form-control" id="reportId" name="reportId">
                    </div>
                </div>
        </form>
    </div>


</div>
<#include "detail.ftl" />
<ex-section id="ScriptBody">
    <script type="text/javascript" src="${request.contextPath}${urls.getForLookupPath('/js/main/modules/identifySendList.js')}"></script>
    <script type="text/javascript">
        pageLogic.initData = {
            modalParams: [
                {
                    width: 800,
                    height: 400,
                    title: "T_IDENTIFY_SEND"
                }
            ],
            restUrlPrefix: "/identifies",
            queryMsg: {
                fullName: "nameSearch"
            }
        };

    </script>
</ex-section>
</body>
</html>
