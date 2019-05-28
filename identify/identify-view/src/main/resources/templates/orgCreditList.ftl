<!-- add by xujc 2018/11/30 start -->
<html>
<head>
    <title></title>
</head>
<body>

<div id="container">
    <!-- 查询条件 -->
    <div class="form-inline search_area">
        <div class="form-group">
            <label class="control-label">机构名称：</label>
            <input placeholder="名称" id="orgNameSearch" class="form-control">
        </div>
        <div class="form-group">
            <label class="control-label">信用等级：</label>
            <select class="form-control" id="creditGradeSearch">
                //update by wangwj 20181218 start
                <option value="">请选择</option>
                //update by wangwj 20181218 end
                <option value="A">A</option>
                <option value="B">B</option>
                <option value="C">C</option>
                <option value="D">D</option>
            </select>
        </div>
        <div class="form-group">
            <label class="control-label">信用评价得分：</label>
            <input  placeholder="开始分数" id="startScoreSearch" class="form-control">
            至
            <input placeholder="结束分数" id="endScoreSearch" class="form-control">
        </div>
        <#--update by wangwj 20181218 start-->
        <div class="pull-right">
            <button class="btn btn-sm btn-info" id="searchBtn">
                <i class="fa fa-search"></i> 搜索
            </button>
        </div>
        <#--update by wangwj 20181218 end-->
    </div>

    <!-- 工具栏 -->
    <div class="tool_area clearfix">

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
                        <span><span style="color: red;">* </span>评分年度</span>
                    </div>
                    <div class="col-md-10">
                        <input class="form-control" id="scoreYear" name="scoreYear">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-2 text-right">
                        <span><span style="color: red;">* </span>鉴定机构id</span>
                    </div>
                    <div class="col-md-10">
                        <input class="form-control" id="identifyOrgId" name="identifyOrgId">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-2 text-right">
                        <span><span style="color: red;">* </span>信用级别得分</span>
                    </div>
                    <div class="col-md-10">
                        <input class="form-control" id="creditGradeScore" name="creditGradeScore">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-2 text-right">
                        <span><span style="color: red;">* </span>信用级别名称</span>
                    </div>
                    <div class="col-md-10">
                        <input class="form-control" id="creditGradeName" name="creditGradeName">
                    </div>
                </div>
        </form>
    </div>


</div>

<ex-section id="ScriptBody">
    <script type="text/javascript" src="${request.contextPath}${urls.getForLookupPath('/js/main/modules/orgCreditList.js')}"></script>
    <script type="text/javascript">
        pageLogic.initData = {
            modalParams: [
                {
                    width: 800,
                    height: 400,
                    title: "企业信用"
                }
            ],
            restUrlPrefix: "/enterprise/credits",
            queryMsg: {
                orgName: "orgNameSearch",
                creditGrade: "creditGradeSearch",
                startScore: "startScoreSearch",
                endScore: "endScoreSearch"
            },
            path: "${request.contextPath}"
        };

    </script>
</ex-section>
</body>
</html>
<!-- add by xujc 2018/11/30 end -->