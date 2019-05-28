<#-- add by panqh 2018-12-05 start -->
<html>
<head>
    <title>补正变更申请</title>
</head>
<body>

<div>
  <#--update by wangwj 20190118 start-->
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
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </span>
                    <span>至</span>
                    <span class="input-group date form_datetime">
                                <input class="form-control" id="endTime" name="endTime"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
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
              <h4 class="title"><span>补正申请信息</span></h4>
              <form class="form-horizontal" id="applyForm">
                  <table class="table table-bordered table-data">
                      <colgroup>
                          <col width="16.7%">
                          <col width="33.3%">
                          <col width="16.7%">
                      </colgroup>
                      <#--动态生成房屋名称和鉴定报告-->
                      <tbody id="splitContain">

                      </tbody>
                      <tr>
                          <th style="vertical-align:middle"><span style="color: red;">* </span>申请原因</th>
                          <td colspan="3"><textarea class="form-control" rows="10" id="reason" name="reason"></textarea></td>
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
    <script type="text/javascript" src="${request.contextPath}${urls.getForLookupPath('/js/main/modules/correctList.js')}"></script>
    <script type="text/javascript">
        pageLogic.initData = {
            modalParams: [
                {
                    width: 450,
                    height: 280,
                    title: ""
                }
            ],
            restUrlPrefix: "/identifies",
            queryMsg: {
                caseNo: "caseNoSearch",
                address: "addressSearch",
                startTime: "startTime",
                endTime: "endTime",
                status: "value:9",
                type: "value:2"
            }
        };

    </script>
</ex-section>
</body>
</html>
<#--update by wangwj 20190118 end -->
<#-- add by panqh 2018-12-05 end -->