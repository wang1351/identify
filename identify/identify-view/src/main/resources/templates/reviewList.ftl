<html>
<head>
    <title>复核申请</title>

</head>

<body>

<div>
    <div>
        <h6 class="title2"><span class="title_text">鉴定列表</span><span class="pull-right"><a
                class="fa fa-angle-down"></a></span></h6>
        <br/>
    </div>

    <div>
        <!-- 查询条件 -->
        <div class="row">
            <div class="col-sm-12">
                <div class="form-inline search_area">
                    <div class="form-group">
                        <label class="control-label">编号：</label>
                        <input placeholder="编号" id="caseNoSearch" class="form-control">
                    </div>
                    <div class="form-group">
                        <label class="control-label">房屋地址：</label>
                        <input placeholder="房屋地址" id="addressSearch" class="form-control">
                    </div>
                    <div class="form-group">
                        <label class="control-label">操作时间：</label>
                        <span class="input-group date form_datetime">
                                <input class="form-control" id="startTime" name="startTime"/>
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                            </span>
                        <span>至</span>
                        <span class="input-group date form_datetime">
                                <input class="form-control" id="endTime" name="endTime"/>
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                        </span>
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

    <div class="row">
        <div class="col-sm-12">
            <h4 class="title"><span>鉴定信息</span></h4>
            <table class="table table-bordered table-data">
                <colgroup>
                    <col width="16.7%">
                    <col width="33.3%">
                    <col width="16.7%">
                </colgroup>
                <tr>
                    <th>编号</th>
                    <td id="caseNo"></td>
                    <th>房屋地址</th>
                    <td id="address"></td>
                </tr>
                <tr>
                    <th>鉴定单位</th>
                    <td id="orgName"></td>
                    <th>申请方式</th>
                    <td id="method"></td>
                </tr>
            </table>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <h4 class="title"><span>复核申请信息</span></h4>
            <form class="form-horizontal" id="applyForm">
                <table class="table table-bordered table-data">
                    <colgroup>
                        <col width="16.7%">
                        <col width="33.3%">
                        <col width="16.7%">
                    </colgroup>
                    <tr>
                        <th><span style="color: red;">* </span>申请人</th>
                        <td><input class="form-control" id="requestName" name="requestName"></td>
                        <th><span style="color: red;">* </span>身份证号码</th>
                        <td><input class="form-control" id="idNum" name="idNum"></td>
                    </tr>
                    <tr>
                        <th><span style="color: red;">* </span>联系人</th>
                        <td><input class="form-control" id="contactName" name="contactName"></td>
                        <th><span style="color: red;">* </span>联系电话</th>
                        <td><input class="form-control" id="phone" name="phone"></td>
                    </tr>
                    <tr>
                        <th><span style="color: red;">* </span>联系地址</th>
                        <td colspan="3"><input class="form-control" id="contactAddress" name="contactAddress"></td>
                    </tr>
                    <tbody id="splitContain">

                    </tbody>
                    <#-- update by xujc 2019/3/12 start -->
                    <tr>
                        <th><span style="color: red;">* </span>指定专家数</th>
                        <td colspan="3" style="text-align: left">
                            <div id="appointCantent">
                                <div id="appointExpert" class="expertCount">
                                    <select class="form-control expertField" id="expertField" name="expertField0"
                                            style="display: inline-block;width: 200px">style="display:
                                        inline-block;width: 150px"></select>
                                    <input type="number" min="1" class="form-control expertNo" id="expertNo"
                                           name="expertNo0" style="width: 200px;display: inline-block" disabled>
                                    <input type="hidden" id="hideExpertNo">
                                    <div style="display: inline-block;height: 34px;line-height: 34px" align="left">人
                                    </div>
                                </div>
                            </div>
                            <div class="pull-right" style="width:10%;margin-top:-50px;">
                                <span id="delExpertNum" style="position:absolute;margin-top:-34px;">
                                <button class="btn btn-md btn-link" id="delBtn">
                                    <i class="fa fa-minus" style="color: red"></i>
                                </button>
                                </span>
                                <button class="btn btn-md btn-link" id="addBtn">
                                    <i class="fa fa-plus"></i>
                                </button>
                            </div>
                        </td>
                    </tr>
                    <#-- update by xujc 2019/3/12 end -->
                    <tr>
                        <th>指定回避专家</th>
                        <td colspan="3"><select class="form-control" id="exceptExpert" name="exceptExpert"></select>
                        </td>
                    </tr>
                    <tr>
                        <th><span style="color: red;">* </span>申请原因</th>
                        <td colspan="3"><textarea class="form-control" rows="10" id="reason" name="reason"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <th>申请表</th>
                        <td colspan="3" style="text-align: left">
                            <div id="applyFile"></div>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>

    <div class="button_area">
        <button class="btn btn-default btn-blue" id="saveBtn">
            <i class="fa fa-save"></i> 申请
        </button>
    <#--  <button class="btn btn-default btn-info" id="exportBtn">
          <i class="fa fa-download"></i> 保存并导出
      </button>-->
        <button class="btn btn-default btn-danger" id="backBtn">
            <i class="fa fa-reply"></i> 返回
        </button>
    </div>

</div>

<ex-section id="ScriptBody">

    <script type="text/javascript" src="${request.contextPath}${urls.getForLookupPath('/js/main/modules/reviewList.js')}"></script>
    <script type="text/javascript">
        pageLogic.initData = {
            modalParams: [
                {
                    width: 600,
                    height: 500,
                    title: ""
                }
            ],
            restUrlPrefix: "/identifies",
            queryMsg: {
                caseNo: "caseNoSearch",
                addressSearch: "addressSearch",
                startTime: "startTime",
                endTime: "endTime",
                /*必须是鉴定流程走完才能复核和补正*/
                status: "value:9",
            <#--根据这个来排除状态是待复核的-->
                type: "value:1"
            }
        };

    </script>
</ex-section>
</body>
</html>
