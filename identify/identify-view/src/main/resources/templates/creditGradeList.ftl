<html>
<head>
    <title>信用级别管理</title>
</head>
<body>

<div>
<#--
<!-- 查询条件
<div class="row">
    <div class="col-sm-12">
        <div class="form-inline">
            <div class="form-group">
                <label class="control-label">名称：</label>
                <input placeholder="名称" id="nameSearch" class="form-control">
            </div>
        </div>
        <br>
    </div>
</div>
        -->
    <!-- 工具栏 -->
    <div class="tool_area clearfix">
            <div class="pull-left">
            <#--<button class="btn btn-sm btn-primary" id="addBtn">-->
            <#--<i class="fa fa-plus"></i> 添加-->
            <#--</button>-->
                <button class="btn btn-sm btn-blue">
                    <span id="modifyBtn">
                        <i class="fa fa-file-text-o"></i>
                         <span>修改</span>
                    </span>
                </button>
            <#--<button class="btn btn-sm btn-danger" id="delBtn">-->
            <#--<i class="fa fa-trash-o"></i> 删除-->
            <#--</button>-->
            </div>
        <#--    <div class="pull-right">
                <button class="btn btn-sm btn-info" id="searchBtn">
                    <i class="fa fa-search"></i> 搜索
                </button>
            </div>   -->
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
                    <span><span style="color: red;">* </span>级别名称</span>
                </div>
                <div class="col-md-10">
                    <input class="form-control" id="name" name="name">
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-2 text-right">
                    <span><span style="color: red;">* </span>计分</span>
                </div>
                <div class="col-md-10">
                    <input class="form-control" id="get" name="get">
                </div>
            </div>
        <#--<div class="form-group">-->
        <#--<div class="col-md-2 text-right">-->
        <#--<span><span style="color: red;">* </span>小于</span>-->
        <#--</div>-->
        <#--<div class="col-md-10">-->
        <#--<input class="form-control" id="lt" name="lt">-->
        <#--</div>-->
        <#--</div>-->
            <div class="form-group">
                <div class="col-md-2 text-right">
                    <span><span style="color: red;">* </span>备注</span>
                </div>
                <div class="col-md-10">
                    <input class="form-control" id="remarks" name="remarks">
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-2 text-right">
                    <span><span style="color: red;">* </span>序号</span>
                </div>
                <div class="col-md-10">
                    <input class="form-control" id="seq" name="seq">
                </div>
            </div>
        </form>
    </div>


</div>

<ex-section id="ScriptBody">
    <script type="text/javascript" src="${request.contextPath}${urls.getForLookupPath('/js/main/modules/creditGradeList.js')}"></script>
    <script type="text/javascript">
        pageLogic.initData = {
            modalParams: [
                {
                    width: 800,
                    height: 400,
                    title: "企业信用级别"
                }
            ],
            restUrlPrefix: "/setting/credit/grades",
            queryMsg: {
                fullName: "nameSearch"
            }
        };

    </script>
</ex-section>
</body>
</html>
<#--add by wangwj 20181031 start-->