<html xmlns="http://www.w3.org/1999/html">
<head>
    <title>鉴定机构名录库</title>
    <style>
        .table_datail th {
            font-weight: normal;
            text-align: right;
        }

        .table_datail > tbody + tbody {
            border-top-width: 0;
        }
    </style>
</head>
<body>


<div>
    <input type="hidden" id="modalType" />
    <!-- 查询条件 -->
    <div class="form-inline search_area">
        <div class="form-group">
            <label class="control-label">机构名称：</label>
            <input placeholder="机构名称" id="nameSearch" class="form-control">
        </div>
        <div class="form-group">
            <label class="control-label">法定代表人：</label>
            <input placeholder="法定代表人" id="legalPersonSearch" class="form-control">
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
            <#--<button class="btn btn-sm btn-primary" id="addBtn">
                <i class="fa fa-plus"></i> 添加
            </button>-->
            <button class="btn btn-sm btn-success" id="modifyBtn">
                <i class="fa fa-edit"></i> 修改
            </button>
            <#--<button class="btn btn-sm btn-danger" id="delBtn">
                <i class="fa fa-trash-o"></i> 删除
            </button>-->
        <#--<button class="btn btn-sm btn-info" id="detailBtn">-->
        <#--<i class="fa fa-file-text-o"></i> 详细-->
        <#--</button>-->
        <#--update by wangwj 20181218 start-->
        <#--<button class="btn btn-sm btn-success" id="useBtn">-->
        <#--<i class="fa fa-check"></i> 启用-->
        <#--</button>-->
        <#--<button class="btn btn-sm btn-warning" id="unUseBtn">-->
        <#--<i class="fa fa-close"></i> 禁用-->
        <#--</button>-->
        <#--update by wangwj 20181218 end-->
        </div>

    </div>

    <div class="row">
        <div class="col-sm-12">
            <div>
                <table id="btTable" class="table table-condensed table-hover table-striped"></table>
            </div>
        </div>
    </div>


    <#--<div id="modal">
        <form class="form-horizontal">
            <input type="hidden" id="id"/>
            <div class="form-group">
                <div class="col-md-2 text-right">
                    <span><span style="color: red;">* </span>鉴定机构名称</span>
                </div>
                <div class="col-md-10">
                    <input class="form-control" id="name" name="name">
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-2 text-right">
                    <span><span style="color: red;">* </span>统一社会信用代码</span>
                </div>
                <div class="col-md-4">
                    <input class="form-control" id="unifiedCode" name="unifiedCode">
                </div>
                <div class="col-md-2 text-right">
                    <span><span style="color: red;">* </span>法定代表人</span>
                </div>
                <div class="col-md-4">
                    <input class="form-control" id="legalPerson" name="legalPerson">
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-2 text-right">
                    <span><span style="color: red;">* </span>启用状态</span>
                </div>
                <div class="col-md-2">
                    <input type="radio" class="styled" id="useStatus" name="useStatus" value="1"> 启用</input>
                </div>
                <div class="col-md-2">
                    <input type="radio" class="styled" id="unUseStatus" name="useStatus" value="0"> 禁用</input>
                </div>
                <div class="col-md-2 text-right">
                    <span><span style="color: red;">* </span>联系电话</span>
                </div>
                <div class="col-md-4">
                    <input class="form-control" id="phone" name="phone">
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-2 text-right">
                    <span>组织机构编码</span>
                </div>
                <div class="col-md-4">
                    <input class="form-control" id="syncOrgCode" name="syncOrgCode">
                </div>
                <div class="col-md-2 text-right">
                    <span>通讯地址</span>
                </div>
                <div class="col-md-4">
                    <input class="form-control" id="address" name="address">
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-2 text-right">
                    <span><span style="color: red;">* </span>鉴定业务</span>
                </div>
                <div class="col-md-10">
                    <select class="form-control" id="identifyBusinessKey" name="identifyBusinessKey"></select>
                </div>
            </div>
        </form>
    </div>-->

    <div id="detailModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content" style="width:1200px; height:420px; overflow: auto">
                <!--模态框的头部&ndash;&gt;
                <div style="cursor:move" class="modal-header">
                    <!--data-dismiss="modal"该属性用于关闭模态框-->
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="xxxx">详细信息</h4>
                <div class="modal-body">
                    <input type="hidden" id="orgId">
                <#--update by wangwj 20190305 start-->
                    <ul id="myTab" class="nav nav-tabs">
                        <li class="active"><a href="#d1" data-toggle="tab">单位信息</a></li>
                        <li><a href="#d2" data-toggle="tab">法定代表人信息</a></li>
                        <li><a href="#d3" data-toggle="tab">技术负责人信息</a></li>
                        <li><a href="#d4" data-toggle="tab">鉴定人员信息</a></li>
                        <li><a href="#d5" data-toggle="tab">使用设备信息</a></li>
                    </ul>
                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane fade in active" id="d1">

                        </div>
                        <div class="tab-pane fade " id="d2">

                        </div>
                        <div class="tab-pane fade " id="d3">

                        </div>
                        <div class="tab-pane fade " id="d4">

                        </div>
                        <div class="tab-pane fade " id="d5">

                        </div>
                    </div>
                </div>
                <div class="modal-footer" id="boro">
                    <button class="btn btn-grey" data-dismiss="modal" aria-hidden="true">关闭</button>
                </div>
            </div>
        </div>

    </div>

</div>
<script type="text/template" id="moban1">
    <%
    _.each(data, function(item, index) {
    %>
    <tr>
        <td height="39px"><%= item.seq%></td>
        <td><%= item.name%></td>
        <td><%= item.identityNo%></td>
        <td><%= item.major%></td>
        <td><%= item.education%></td>
        <td><%= item.job%></td>
        <td><%= item.practise%></td>
        <td><%= item.workYear%></td>
    </tr>
    <%
    })
    %>
</script>

<script type="text/template" id="moban2">
    <%
    _.each(data, function(item1, index) {
    %>
    <tr>
        <td><%= item1.seq%></td>
        <td><%= item1.name%></td>
        <td><%= item1.specification%></td>
        <td><%= item1.num%></td>
        <td><%= item1.remarks%></td>
    </tr>
    <%
    })
    %>
</script>
<script type="text/template" id="tpl_orgMain">
    <table class="table table_datail table-bordered">
        <colgroup width="50%">
            <col width="20%">
            <col width="30%">
        </colgroup>
        <colgroup width="50%">
            <col width="20%">
            <col width="30%">
        </colgroup>
        <tbody>
        <tr>
            <th>单位名称</th>
            <td colspan="3"><%= data.name%></td>
        </tr>
        <tr>
            <th id="jdyw">鉴定业务</th>
            <td colspan="3" id="detailIdentifyBusiness"></td>
        </tr>
        <tr>
            <th>单位类型</th>
            <td id="detailType"><%= data.type%></td>
            <th>单位住所</th>
            <td id="detailAddress"><%= data.address%></td>
        </tr>
        <tr>
            <th>法定代表人</th>
            <td id="detailFaPerson"></td>
            <th>注册资本</th>
            <td id="detailRegisteredCapital"><%= data.registeredCapital%></td>
        </tr>
        <tr>
            <th>成立日期</th>
            <td id="detailBuildDate"><%= data.buildDate%></td>
            <th>营业期限</th>
            <td id="detailYinYe"><%= data.businessTermStart%>-<%= data.businessTermEnd%></td>
        </tr>
        <tr>
            <th>统一社会信息代码</th>
            <td id="detailUnifiedCode"><%= data.unifiedCode%></td>
            <th>营业执照登记机关</th>
            <td id="detailRegistrationOffice"><%= data.registrationOffice%></td>
        </tr>
        <tr>
            <th>检测检验机构资质认定书编号</th>
            <td id="detailOrgCode"><%= data.orgCode%></td>
            <th>检测检验机构资质有效日期</th>
            <td id="detailShiJian"><%= data.orgStart%>-<%= data.orgEnd%></td>
        </tr>
        <tr>
            <th>建设工程质量检测机构资质证书编号</th>
            <td id="detailQualiCode"><%= data.qualiCode%></td>
            <th>建设工程质量检测机构资质核定项目及有效日期</th>
            <td id="detailXianMu"><%= data.qualiProject%>-<%= data.qualiProjectStart%></td>
        </tr>
        <tr>
            <th>测绘资质证书编号</th>
            <td id="detailMappingCode"><%= data.mappingCode%></td>
            <th>测绘资质证书有效日期</th>
            <td id="detailZSRQ"><%= data.mappingStart%>-<%= data.mappingEnd%></td>
        </tr>
        <tr>
            <th>鉴定人员总数</th>
            <td id="detailTotalNum"><%= data.totalNum%></td>
            <th>高级工程师人数</th>
            <td id="detailAdvPersonNum"><%= data.advPersonNum%></td>
        </tr>
        <tr>
            <th>中级工程师人数</th>
            <td id="detailMidPersonNum"><%= data.midPersonNum%></td>
            <th>一级注册结构工程师人数</th>
            <td id="detaillevel1Num"><%= data.level1Num%></td>
        </tr>
        <tr>
            <th>二级注册结构工程师人数</th>
            <td id="detaillevel2Num"><%= data.level2Num%></td>
            <th>注册岩土工程师人数</th>
            <td id="detailRockNum"><%= data.rockNum%></td>
        </tr>
        <tr>
            <th>申请类型</th>
            <td id="detailRequestType">
                <% if(data.requestType == "1"){%>
                初始
                <% }else if(data.requestType == "2"){%>
                变更
                <% }else {%>
                取消
                <% }%>
            </td>
            <th>有无处分和责任事故</th>
            <td id="detailPunishment"><%= data.punishment%></td>
        </tr>

        </tbody>
    </table>
</script>
<script type="text/template" id="tpl_orgPerson1">
    <table class="table table_datail table-bordered">
        <colgroup>
            <col width="20%">
            <col width="14%">
            <col width="14%">
            <col width="14%">
            <col width="14%">
            <col width="14%">
        </colgroup>
        <tbody>
            <tr>
                <th>姓名</th>
                <td><%= data.name%></td>
                <th>性别</th>
                <td>
                    <% if(data.sex =="1"){%>
                    男
                    <% }else if(data.sex =="2"){%>
                    女
                    <% }%>

                </td>
                <th>出生年月</th>
                <td><%= data.birthday%></td>
                <td class="" rowspan="4">
                    <div id="dphoto"></div>
                </td>
            </tr>
            <tr>
                <th>身份证号</th>
                <td id="detailIdentityNo1" colspan="3"><%= data.identityNo%></td>
                <th>学历</th>
                <td id="detailEducation1"><%= data.education%></td>
            </tr>
            <tr>
                <th>技术职称等级及职称证书编号</th>
                <td id="detailZDBH" colspan="5"><%= data.titleDegree%>-<%= data.certificateNo%></td>
            </tr>
            <tr>
                <th>执业资格及注册证书编号</th>
                <td id="detailZZZS" colspan="5"><%= data.practise%>-<%= data.regCertificateNo%></td>
            </tr>
            <tr>
                <th>何时/何校/何专业毕业</th>
                <td id="detailGraduation" colspan="6"><%= data.graduation%></td>
            </tr>
            <tr>
                <th>鉴定相关工作年限</th>
                <td id="detailWorkYear"><%= data.workYear%></td>
                <th>办公电话</th>
                <td id="detailOfficePhone1"><%= data.officePhone%></td>
                <th>移动电话</th>
                <td id="detailPhone1" colspan="2"><%= data.phone%></td>
            </tr>
            <tr>
                <td rowspan="<%= data.identifyOrgResumeList.length+1%>" style="vertical-align: middle;text-align:
                                        center">
                    <div style="margin: 0 auto;width: 20px;line-height: 24px">工作简历</div>
                </td>
                <td colspan="2" style="text-align: center">由何年何月至何年何月</td>
                <td colspan="4" style="text-align: center">在何单位、从事何工作、任何职</td>
            </tr>
            <% _.each(data.identifyOrgResumeList,function(item,index){
            %>
            <tr>
                <td colspan="2" height="39px"><%= item.workWhen%></td>
                <td colspan="4" height="39px"><%= item.workWhere%></td>
            </tr>
            <% })
            %>
            <tr>
                <th>有无处分和责任事故</th>
                <td id="detailPunishment1" colspan="6"><%= data.punishment%></td>
            </tr>
        </tbody>
    </table>
</script>
<#--鉴定人员信息-->
<script type="text/template" id="tpl_orgIdentify">
    <table class="table table_datail table-bordered">
        <colgroup>
            <col width="9%">
            <col width="10%">
            <col width="12%">
            <col width="10%">
            <col width="12%">
            <col width="14%">
            <col width="12%">
            <col width="14%">
        </colgroup>
    <#--<colgroup width="50%">
    <col width="20%">
    <col width="30%">
    </colgroup>-->
        <thead>
        <tr>
            <th style="text-align: center;">序号</th>
            <th style="text-align: center;">姓名</th>
            <th style="text-align: center;">身份证号码</th>
            <th style="text-align: center;">专业</th>
            <th style="text-align: center;">学历</th>
            <th style="text-align: center;">职称</th>
            <th style="text-align: center;">执业资格</th>
            <th style="text-align: center;">鉴定相关工作年限</th>
        </tr>
        </thead>
        <tbody>
        <% _.each(data,function(item,index){
        %>
         <tr style="height: 37px">
             <td><%= item.seq%></td>
             <td><%= item.name%></td>
             <td><%= item.identityNo%></td>
             <td><%= item.major%></td>
             <td><%= item.education%></td>
             <td><%= item.job%></td>
             <td><%= item.practise%></td>
             <td><%= item.workYear%></td>
         </tr>
        <% })
        %>
        </tbody>
    </table>
</script>
<#--鉴定设备信息表-->
<script type="text/template" id="tpl_orgDevice">
    <table class="table table_datail table-bordered">
        <colgroup>
            <col width="20%">
            <col width="20%">
            <col width="20%">
            <col width="20%">
            <col width="20%">
        </colgroup>
    <#--<colgroup width="50%">
    <col width="20%">
    <col width="30%">
    </colgroup>-->
        <span style="color:red;font-weight: bold">鉴定所用的检测设备及结构计算软件统计表</span>
        <thead>
        <tr>
            <th style="text-align: center;">序号</th>
            <th style="text-align: center;">检测设备/结构计算软件名称</th>
            <th style="text-align: center;">规格型号</th>
            <th style="text-align: center;">编号</th>
            <th style="text-align: center;">备注</th>
        </tr>
        </thead>
        <tbody id="identfyDevice">
        <% _.each(data,function(item,index){
        %>
        <tr style="height: 37px">
            <td><%= item.seq%></td>
            <td><%= item.name%></td>
            <td><%= item.specification%></td>
            <td><%= item.num%></td>
            <td><%= item.remarks%></td>
        </tr>
        <% })
        %>

        </tbody>

    </table>
</script>

<ex-section id="ScriptBody">
<#--add by wangwj 20181218 start-->
<#--<link type="text/css" rel="stylesheet" href="${request.contextPath}/css/modules/bootstrap-switch.css?v=1.0.1" />-->
<#--add by wangwj 20181218 end-->
    <script type="text/javascript" src="${request.contextPath}${urls.getForLookupPath('/js/main/modules/identifyOrgList.js')}"></script>
    <script type="text/javascript">
        pageLogic.initData = {
            modalParams: [
                {
                    id: "modal",
                    width: 800,
                    height: 500,
                    title: "鉴定机构名录库"
                }
            ],
            restUrlPrefix: "/library/identify/orgs",
            queryMsg: {
                orgName: "nameSearch",
                legalPerson: "legalPersonSearch"
            }
        };

    </script>
</ex-section>
</body>
</html>
<#--update by wangwj 20190305 end-->