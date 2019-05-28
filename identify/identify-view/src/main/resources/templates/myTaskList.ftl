<html xmlns="http://www.w3.org/1999/html">
<head>
    <title>我的待办任务</title>
</head>
<body>
<#--update by wangwj start-->
<ul id="myTab" class="nav nav-tabs">
<#if hasRequestPermission>
    <li class="active"><a href="#identify" data-toggle="tab" id="identifyList">鉴定业务&nbsp;&nbsp;<span id="dDing"
                                                                                                     class="ii"></span></a>
    </li>
</#if>
<#if hasReviewPermission>
    <li><a href="#review" data-toggle="tab" id="reviewList">复核业务&nbsp;&nbsp;<span id="fSheng" class="ii"></span></a>
    </li>
</#if>
<#if hasCorrectPermission>
    <li><a href="#correction" data-toggle="tab" id="correctList">补正变更&nbsp;&nbsp;<span id="bZheng"
                                                                                       class="ii"></span></a></li>
</#if>
    <li><a href="#phone" data-toggle="tab" id="phoneList">手机申请&nbsp;&nbsp;<span id="pApply" class="ii"></span></a></li>
</ul>
<#--update by wangwj end-->
<div id="myTabContent" class="tab-content">
<#-- 鉴定业务 -->
    <div class="tab-pane fade in active" id="identify">
        <!-- 查询条件 -->
        <div class="form-inline search_area">
            <div class="form-group">
                <label class="control-label">鉴定内容：</label>
                <select class="form-control" id="identifyContent"></select>
            </div>
            <div class="form-group">
                <label class="control-label">申请方式：</label>
                <select class="form-control" id="applyMethod">
                    <option value="">请选择</option>
                    <option value="1">人工窗口</option>
                    <option value="2">我的南京</option>
                    <option value="3">房产微政务</option>
                </select>
            </div>
            <div class="form-group">
                <label class="control-label">当前状态：</label>
                <select class="form-control" id="status">
                    <option value="">请选择</option>
                    <option value="1">已申请</option>
                    <option value="2">已受理</option>
                    <option value="3">已初勘</option>
                    <option value="4">已出具方案</option>
                    <option value="5">已签订合同</option>
                    <option value="6">已编制报告</option>
                    <option value="7">已审核</option>
                    <option value="8">已签发</option>
                </select>
            </div>
            <div class="form-group">
                <button class="btn btn-sm btn-info" id="searchBtn">
                    <i class="fa fa-search"></i> 搜索
                </button>
            </div>

            <div class="pull-right">
                <div class="btn-group">
                    <button type="button" class="btn btn-sm btn-primary dropdown-toggle" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="true">
                        流程 <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a id="suspendProcess" href="javascript:void(0)">挂起</a></li>
                        <li><a id="stopProcess" href="javascript:void(0)">终止</a></li>
                    </ul>
                </div>
            <#--update by wangwj 20190221 start -->
            <#if hasRequestPermission>
                <button class="btn btn-sm btn-primary" id="addBtn">
                    <i class="fa fa-plus"></i> 鉴定委托
                </button>
            </#if>
            </div>
        <#--update by wangwj 20190221 end -->
        </div>

        <div class="row">
            <div class="col-sm-12">
                <div>
                    <table id="btTable" class="table table-condensed table-hover table-striped"></table>
                </div>
            </div>
        </div>
    </div>

<#-- 复核业务 -->
    <div class="tab-pane fade" id="review">
        <!-- 查询条件 -->
        <div class="form-inline search_area">
            <div class="form-group">
                <label class="control-label">鉴定内容：</label>
                <select class="form-control" id="reviewContent"></select>
            </div>
            <div class="form-group">
                <label class="control-label">当前状态：</label>
                <select class="form-control" id="reviewStatus">
                    <option value="">请选择</option>
                    <option value="1">已申请</option>
                    <option value="2">已受理</option>
                    <option value="3">已选择专家</option>
                    <option value="4">已确认专家</option>
                    <option value="5">已填写意见</option>
                </select>
            </div>
            <div class="form-group">
                <button class="btn btn-sm btn-info" id="reviewSearchBtn">
                    <i class="fa fa-search"></i> 搜索
                </button>
            </div>
            <div class="pull-right">
                <button class="btn btn-sm btn-primary" id="reviewAddBtn">
                    <i class="fa fa-plus"></i> 复核申请
                </button>
            </div>
        </div>

        <!-- 列表 -->
        <div class="row">
            <div class="col-sm-12">
                <div>
                    <table id="reviewTable" class="table table-condensed table-hover table-striped"></table>
                </div>
            </div>
        </div>
    </div>

<#-- 补正变更 -->
    <div class="tab-pane fade" id="correction">
        <!-- 查询条件 -->
        <div class="form-inline search_area">
            <div class="form-group">
                <label class="control-label">鉴定内容：</label>
                <select class="form-control" id="correctContent"></select>
            </div>
            <div class="form-group">
                <label class="control-label">当前状态：</label>
                <select class="form-control" id="correctStatus">
                    <option value="">全部</option>
                    <option value="1">待审核</option>
                    <option value="2">已审核</option>
                    <option value="3">已办结</option>
                </select>
            </div>
            <div class="form-group">
                <button class="btn btn-sm btn-info" id="correctSearchBtn">
                    <i class="fa fa-search"></i> 搜索
                </button>
            </div>
            <div class="pull-right">
                <button class="btn btn-sm btn-primary" id="correctAddBtn">
                    <i class="fa fa-plus"></i> 补正变更申请
                </button>
            </div>
        </div>

        <!-- 列表 -->
        <div class="row">
            <div class="col-sm-12">
                <div>
                    <table id="correctTable" class="table table-condensed table-hover table-striped"></table>
                </div>
            </div>
        </div>
    </div>
<#-- 手机申请 -->
    <div class="tab-pane fade" id="phone">
        <!-- 查询条件 -->
        <div class="form-inline search_area">
            <div class="form-group">
                <label class="control-label">来源：</label>
                <select class="form-control" id="identifySourceSearch">
                    <option value="">请选择</option>
                    <option value="1">我的南京</option>
                    <option value="2">房产微政务</option>
                </select>
            </div>
            <div class="form-group">
                <label class="control-label">申请人：</label>
                <input class="form-control" id="createUserNameSearch"/>
            </div>
            <div class="form-group">
                <label class="control-label">联系电话：</label>
                <input type="text" class="form-control" id="identifyContactsTelSearch">
            </div>
            <div class="form-group">
                <label class="control-label">申请时间：</label>
                <span class="input-group date form_datetime">
                <input id="startTimeSearch" class="form-control"/>
                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </span>
                <span>至</span>
                <span class="input-group date form_datetime">
                <input id="endTimeSearch" class="form-control">
                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </span>
            </div>

            <div class="form-group">
                <button class="btn btn-sm btn-info" id="phoneSearchBtn">
                    <i class="fa fa-search"></i> 搜索
                </button>
            </div>

        </div>

        <!-- 列表 -->
        <div class="row">
            <div class="col-sm-12">
                <div>
                    <table id="phoneTable" class="table table-condensed table-hover table-striped"></table>
                </div>
            </div>
        </div>
    </div>


    <div id="modal">
        <form class="form-horizontal">
            <div class="form-group">
                <div class="col-md-2 text-right">
                    <span><span style="color: red;">* </span>原因</span>
                </div>
                <div class="col-md-10">
                    <textarea class="form-control" rows="5" id="reason" name="reason"></textarea>
                </div>
            </div>
        </form>
    </div>

    <div class="modal" id="phoneDetailModal">
        <div class="modal-dialog" style="width: 800px">
            <div class="modal-content" style="width: 800px">
                <!--模态框的头部-->
                <div class="modal-header">
                    <!--data-dismiss="modal"该属性用于关闭模态框-->
                    <button class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">手机申请详细</h4>
                </div>
                <!--模态框的身体-->
                <div class="modal-body" style="overflow: auto;width: 796px;height: 300px" id="detailBody">


                </div>
                <!--模态框的脚-->
                <div class="modal-footer">
                    <div class="button_area">

                        <button class="btn btn-default btn-blue" data-dismiss="modal" id="saveBtn">
                            鉴定委托
                        </button>

                        <button class="btn btn-default btn-danger" data-dismiss="modal">
                            关闭
                        </button>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <script type="text/template" id="tpl_phoneDetail">
        <table class="table table-bordered table-data">
            <tbody>
            <tr>
                <th>房屋地址:</th>
                <td colspan="3"><%=apply.identifyHouseAddress%></td>
            </tr>
            <tr>
                <th>编号:</th>
                <td><%=apply.identifyCode%></td>
                <th>来源:</th>
                <td>
                    <%if(apply.identifySource ==1){%>
                    我的南京
                    <%}else{%>
                    房产微政务
                    <%}%>
                </td>
            </tr>
            <tr>
                <th>鉴定机构:</th>
                <td><%=apply.orgName%></td>
                <th>鉴定类型:</th>
                <td><%=apply.typeName%></td>

            </tr>
            <tr>
                <th>申请人:</th>
                <td><%=apply.createUsername%></td>
                <th>联系人:</th>
                <td><%=apply.identifyContacts%></td>
            </tr>
            <tr>
                <th>申请时间:</th>
                <td><%=apply.createTime%></td>
                <th>联系电话:</th>
                <td><%=apply.identifyContactsTel%></td>
            </tr>
            <tr>
                <th>鉴定目的:</th>
                <td colspan="3"><%=apply.identifyObjective%></td>
            </tr>
            </tbody>
        </table>
    </script>
</div>

<ex-section id="ScriptBody">
    <script type="text/javascript"
            src="${request.contextPath}${urls.getForLookupPath('/js/main/modules/myTaskList.js')}"></script>
    <script type="text/javascript">
        pageLogic.initData = {
            modalParams: [
                {
                    width: 600,
                    height: 300,
                    title: ""
                }

            ],
            restUrlPrefix: "/tasks",
            queryMsg: {
                content: "identifyContent",
                method: "applyMethod",
                status: "status"
            }
        };

    </script>
</ex-section>
</body>
</html>
