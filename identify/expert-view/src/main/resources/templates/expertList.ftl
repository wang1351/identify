
<!--add by xujc 20181117 start -->
<html>
<head>
    <title></title>
    <style type="text/css">
        .richSelect .inner {
            max-height: 250px;
        }
    </style>
</head>
<body>

<div>
    <!-- 查询条件 -->
    <div class="form-inline search_area">
        <div class="form-group">
            <label class="control-label">姓名：</label>
            <input placeholder="姓名" id="nameSearch" class="form-control" type="text" style="width: 100px">
        </div>
        <div class="form-group">
            <label class="control-label">职称：</label>
            <input class="form-control" id="titleSearch">
            </input>
        </div>
        <div class="form-group">
            <label class="control-label">行业执业资格：</label>
            <input class="form-control" id="practiceSearch">
            </input>
        </div>
        <div class="form-group">
            <label class="control-label">所在单位名称：</label>
            <input placeholder="所在单位名称" id="companyNameSearch" class="form-control" type="text">
        </div>
        <div class="form-group">
            <label class="control-label">状态：</label>
            <select class="form-control" id="statusSearch">
            </select>
        </div>
        <#--update by wangwj 20181214 start-->
        <div class="pull-right">
            <button class="btn btn-sm btn-info" id="searchBtn">
                <i class="fa fa-search"></i> 搜索
            </button>
        </div>
        <#--update by wangwj 20181214 end-->

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
                    <span><span style="color: red;">* </span>姓名</span>
                </div>
                <div class="col-md-3">
                    <input class="form-control" id="name" name="name">
                </div>
                <div class="col-md-2 text-right">
                    <span><span style="color: red;">* </span>身份证号</span>
                </div>
                <div class="col-md-3">
                    <input class="form-control" id="identifiedCode" name="identifiedCode">
                </div>


                <div class="col-md-2" style="position: absolute;right: 12px;top: 4px;">

                    <div id="photo"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="col-md-2 text-right">
                    <span><span style="color: red;">* </span>民族</span>
                </div>
                <div class="col-md-3">
                    <select class="form-control" id="nation" name="nation"></select>
                </div>

                <div class="col-md-2 text-right">
                    <span><span style="color: red;">* </span>性别</span>
                </div>
                <div class="col-md-3">
                    <select class="form-control" id="sex" name="sex"></select>
                </div>
            </div>

            <div class="form-group">
                <div class="col-md-2 text-right">
                    <span><span style="color: red;">* </span>学历</span>
                </div>
                <div class="col-md-3">
                    <select class="form-control" id="education" name="education"></select>

                </div>

                <div class="col-md-2 text-right">
                    <span><span style="color: red;">* </span>学位</span>
                </div>
                <div class="col-md-5">
                    <select class="form-control" id="degree" name="degree"></select>
                </div>
            </div>

            <div class="form-group">
                <div class="col-md-2 text-right">
                    <span><span style="color: red;">* </span>健康状况</span>
                </div>
                <div class="col-md-3">
                    <select class="form-control" id="health" name="health"></select>
                </div>
                <div class="col-md-2 text-right">
                    <span><span style="color: red;">* </span>状态</span>
                </div>
                <div class="col-md-5">
                    <select id="useStatus" class="form-control" name="useStatus"></select>
                </div>

            </div>

            <div class="form-group">
                <div class="col-md-2 text-right">
                    <span>行业执业资格</span>
                </div>
                <div class="col-md-3">
                    <input id="practice" class="form-control" name="practice"></input>
                </div>

                <div class="col-md-2 text-right">
                    <span>职称</span>
                </div>
                <div class="col-md-5">
                    <input id="title" class="form-control" name="title"></input>
                </div>
            </div>

            <div class="form-group">
                <div class="col-md-2 text-right">
                    <span><span style="color: red;">* </span>所在单位名称</span>
                </div>
                <div class="col-md-3">
                    <input class="form-control" id="companyName" name="companyName">
                </div>

                <div class="col-md-2 text-right">
                    <span><span style="color: red;">* </span>执业证号</span>
                </div>
                <div class="col-md-5">
                    <input class="form-control" id="practiceNum" name="practiceNum">
                </div>
            </div>

            <div class="form-group">
                <div class="col-md-2 text-right">
                    <span><span style="color: red;">* </span>专家业务领域</span>
                </div>
                <div class="col-md-10">
                    <select class="form-control" id="expertBusinessArea" name="expertBusinessArea"></select>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-2 text-right">
                    <span><span style="color: red;">* </span>手机号码</span>
                </div>
                <div class="col-md-3">
                    <input class="form-control" id="phone" name="phone">
                </div>

                <div class="col-md-2 text-right">
                    <span><span style="color: red;">* </span>e-mail</span>
                </div>
                <div class="col-md-5">
                    <input class="form-control" id="email" name="email">
                </div>
            </div>


            <div class="form-group">
                <div class="col-md-2 text-right">
                    <span><span style="color: red;">* </span>申请表</span>
                </div>
                <div class="col-md-10">
                    <div id="pick"></div>
                </div>
            </div>

        </form>
    </div>
    <div class="modal fade" id="detailModal">
        <div class="modal-dialog" style="width: 800px">
            <div class="modal-content" style="width: 800px">
                <!--模态框的头部-->
                <div class="modal-header">
                    <!--data-dismiss="modal"该属性用于关闭模态框-->
                    <button class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">鉴定专家详细</h4>
                </div>
                <!--模态框的身体-->
                <div class="modal-body" style="overflow: auto;width: 796px;height: 300px">

                    <table class="table table-bordered table-data">
                        <tbody id="tbody">
                        <tr>
                            <td>姓名:</td>
                            <td id="dname"></td>
                            <td>身份证号:</td>
                            <td id="didentifiedCode" colspan="1"></td>
                            <td class="td-nopadding" rowspan="2">
                                <div id="dphoto"></div>
                            </td>
                        </tr>
                        <tr>
                            <td>民族:</td>
                            <td id="dnation"></td>
                            <td>性别:</td>
                            <td id="dsex" colspan="1"></td>
                        </tr>
                        <tr>
                            <td>学历:</td>
                            <td id="deducation"></td>
                            <td>学位:</td>
                            <td id="ddegree" colspan="2"></td>

                        </tr>
                        <tr>
                            <td>健康状况:</td>
                            <td id="dhealth"></td>
                            <td>状态:</td>
                            <td id="duseStatus" colspan="2"></td>
                        </tr>
                        <tr>
                            <td>行业执业资格:</td>
                            <td id="dpractice"></td>
                            <td>职称:</td>
                            <td id="dtitle" colspan="2"></td>

                        </tr>
                        <tr>
                            <td>所在单位名称:</td>
                            <td id="dcompanyName"></td>
                            <td>执业证号:</td>
                            <td id="dpracticeNum" colspan="2"></td>
                        </tr>
                        <tr>
                            <td>手机号码:</td>
                            <td id="dphone"></td>
                            <td>e-mail:</td>
                            <td id="demail" colspan="2"></td>
                        </tr>
                        <tr>
                            <td>专家业务领域:</td>
                            <td id="dexpertBusinessArea" colspan="4"></td>
                        </tr>
                        <tr>
                            <td style="padding-top: 48px;">申请表:</td>
                            <td colspan="4">
                                <div id="dpick"></div>
                            </td>
                        </tr>

                        </tbody>
                    </table>
                </div>
                <!--模态框的脚-->
                <div class="modal-footer">
                    <button class="btn btn-grey" data-dismiss="modal">关闭</button>
                </div>

            </div>
        </div>
    </div>


</div>

<ex-section id="ScriptBody">
    <link type="text/css" rel="stylesheet" href="${request.contextPath}${urls.getForLookupPath('/css/modules/expertList.css')}" />

    <script type="text/javascript" src="${request.contextPath}${urls.getForLookupPath('/js/main/modules/expertList.js')}"></script>

    <script type="text/javascript">
        pageLogic.initData = {
            modalParams: [
                {
                    width: 800,
                    height: 600,
                    title: "鉴定专家"
                }
            ],
            restUrlPrefix: "/library/experts",
            queryMsg: {
                fullName: "nameSearch",
                title: "titleSearch",
                practice: "practiceSearch",
                companyName: "companyNameSearch",
                status: "statusSearch"
            }
        };

    </script>
</ex-section>
</body>
</html>
<!--add by xujc 20181117 end -->