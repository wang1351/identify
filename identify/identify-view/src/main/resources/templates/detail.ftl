<#--add by xujc 2018-11-17 start -->
<!-- update by xujc 2019/1/29 start -->
<html>
<head>
    <title></title>
    <#-- update by xujc 2019/2/21 start -->
    <style>
        #mainTable th,#mainTable td,#correctTable th,#correctTable td,#reviewTable th,#reviewTable td{
            vertical-align:middle!important;/*设置垂直居中*/
            text-align: center;
        }
    </style>
    <#-- update by xujc 2019/2/21 end -->
</head>
<body>

<div id="form">
    <#--这三个存主表状态，展示标头用，分别在showMain，和showApply赋值-->
    <input type="hidden" id="mainStatus" />
    <input type="hidden" id="reviewStatus" />
    <input type="hidden" id="correctStatus" />
    <ul class="nav nav-tabs nav-top-lc" id="ul">
        <li role="presentation" class="active"><a href="javascript:void(0);" class="toContainText" name="1">鉴定委托</a>
        </li>
        <li role="presentation"><a href="javascript:void(0);" class="toContainText" name="2">复核</a></li>
        <li role="presentation"><a href="javascript:void(0);" class="toContainText" name="3">补正变更</a></li>
    </ul>
    <div class="lc_content">
        <!-- 流程左侧列表 -->
        <div class="lc_side">
            <div class="lc_side_inner">
                <ul id="title">

                </ul>
            </div>
        </div>
        <!-- 流程内容 -->
        <div class="lc_main">
            <div id="IdentifyMain"></div>
            <div id="ReviewMain"></div>
            <div id="CorrectDetail"></div>

        </div>
    </div>

    <div class="button_area">
        <button type="button" class="btn btn-default btn-danger" id="fanhui"><i class="fa fa-reply"></i> 返回
        </button>
    </div>
</div>
<script type="text/template" id="tpl_title">
    <%_.each(data.recouds,function(item,index){%>
    <li>
        <a href="#<%= data.name%><%= item.status%>_<%= item.sort%>">
            <span class="lc_title" style="color: #3399DD"><%= item.title%></span>
            <em class="lc_time"><%= item.operatorTime%></em>
        </a>
    </li>
    <%})%>
</script>
<script type="text/template" id="tpl_main">
    <div id="identifyMain<%= data.recoud.status%>_<%= data.recoud.sort%>">
        <table class="table table-bordered table-data">
            <colgroup>
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
            </colgroup>
            <tbody>
            <th>经办人</th>
            <td><%=data.recoud.operatorUserName%></td>
            <th>状态</th>
            <td>办结</td>
            <th>操作时间</th>
            <td><%=data.recoud.operatorTime%></td>
            </tbody>
        </table>
        <#-- update by xujc 2019/2/21 start -->
        <div id="identifyMain<%= data.recoud.status%>_<%= data.recoud.sort%>_cont" style="display: none;">
            <ul id="myTab" class="nav nav-tabs">
                <li class="active"><a href="#d1" data-toggle="tab">委托人申请鉴定</a></li>
                <li><a href="#d2" data-toggle="tab">鉴定房屋分栋信息表</a></li>
                <% if(data.identifyMain.newProject !=null){%>
                <li><a href="#d3" data-toggle="tab">新建工程调查表</a></li>
                <% }%>
            </ul>
            <div id="myTabContent" class="tab-content">
                <div class="tab-pane fade in active" id="d1">
                    <table class="table table-bordered table-data">
                        <colgroup>
                            <col width="20%">
                            <col width="30%">
                            <col width="20%">
                        </colgroup>
                        <caption style="text-align: center;font-weight: bold;font-size: 200%; display:none;">
                            房屋鉴定委托书&nbsp&nbsp&nbsp<span style="font-size: 15px;color: #2a80b9!important;"><button
                                id="saveExport" style="display: none;">下载</button></span></caption>
                        <tbody>
                        <tr>
                            <th colspan="4" style="text-align: center">委托人信息</th>
                        </tr>
                        <tr>
                            <th>委托人</th>
                            <td><%=data.identifyMain.client.clientName%></td>
                            <th>委托人性质</th>
                            <td>
                                <% if(data.identifyMain.client.nature ===1){%>
                                产权人
                                <% }else if(data.identifyMain.client.nature ===2){%>
                                使用人
                                <% }else {%>
                                第三方
                                <% }%>
                            </td>
                        </tr>
                        <tr>
                            <th>联系人</th>
                            <td><%=data.identifyMain.client.contactName%></td>
                            <th>联系电话一</th>
                            <td><%=data.identifyMain.client.phone%></td>
                        </tr>
                        <tr>
                            <th>联系电话二</th>
                            <td colspan="3"><%=data.identifyMain.client.phone2%></td>
                        </tr>
                        <tr>
                            <th>委托鉴定目的及内容</th>
                            <td><%=data.identifyMain.client.content%></td>
                            <th>申请鉴定内容</th>
                            <td name="jdnr"></td>
                        </tr>
                        <tr>
                            <th>房屋使用变迁改建、维修概况</th>
                            <td colspan="3"><span style="float:left;"><%=data.identifyMain.client.condition%></td>
                        </tr>
                        <tr>
                            <th>房屋产权证号</th>
                            <td>
                                <div><%=data.identifyMain.client.propertyNum%></div>
                            </td>
                            <th>委托人身份证号</th>
                            <td>
                                <div><%=data.identifyMain.client.idNum%></div>
                            </td>
                        </tr>
                        <tr>
                            <th>房屋产权证</th>
                            <td>
                                <div name="cFileId"></div>
                            </td>
                            <th>委托人身份证</th>
                            <td>
                                <div name="sFileId"></div>
                            </td>
                        </tr>
                        <tr>
                            <th>其他</th>
                            <td>
                                <div name="qFileId"></div>
                            </td>
                            <th>鉴定委托书</th>
                            <td>
                                <div name="clientIdentifyFile"></div>
                            </td>
                        </tr>
                        <tr>
                            <th colspan="4" style="text-align: center">房屋概况</th>
                        </tr>
                        <tr>
                            <th>丘权号</th>
                            <td><%=data.identifyMain.house.hillock%></td>
                            <th><span style="float: right">房屋地址</th>
                            <td><span name="idengtifyAddress" style="float:left;"></span></td>
                        </tr>
                        <tr>
                            <th>备注</th>
                            <td colspan="3"><span style="float:left;">1、申请受理室加盖“已登记”章为有效。</span><br/><span
                                    style="float:left;">2、委托人因故撤销鉴定时，必须书面通知鉴定单位。</span><br/><span
                                    style="float:left;">3、鉴定单位对鉴定项目的资料及鉴定报告负有保密责任。</span><br/></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="tab-pane fade" id="d2">
                    <div style="text-align: center">房屋分栋信息</div>
                </div>
                <div class="tab-pane fade" id="d3">
                    <div style="text-align: center">新建工程信息</div>
                    <table class="table table-bordered table-data">
                        <colgroup>
                            <col width="25%">
                            <col width="25%">
                            <col width="25%">
                        </colgroup>
                        <% if(data.identifyMain.newProject !=null){%>
                        <tbody>
                        <tr>
                            <th>工程名称</th>
                            <td><%= data.identifyMain.newProject.projectName%></td>
                            <th>总建筑面积</th>
                            <td><%= data.identifyMain.newProject.totalArea%> ㎡</td>
                        </tr>
                        <tr>
                            <th>建设单位</th>
                            <td><%= data.identifyMain.newProject.buildOrg%></td>
                            <th>地上</th>
                            <td> <%= data.identifyMain.newProject.layerGround%>层</td>
                        </tr>
                        <tr>
                            <th>设计单位</th>
                            <td><%= data.identifyMain.newProject.designOrg%></td>
                            <th>地下</th>
                            <td> <%= data.identifyMain.newProject.layerUnderGround%>层</td>
                        </tr>
                        <tr>
                            <th>施工单位</th>
                            <td><%= data.identifyMain.newProject.constructionOrg%></td>
                            <th>结构</th>
                            <td><%= data.identifyMain.newProject.structure%></td>
                        </tr>
                        <tr>
                            <th>监理单位</th>
                            <td><%= data.identifyMain.newProject.supervisionOrg%></td>
                            <th>用途</th>
                            <td><%= data.identifyMain.newProject.purpose%></td>
                        </tr>
                        <tr>
                            <th>勘查单位</th>
                            <td colspan="3"><%= data.identifyMain.newProject.prospectedOrg%></td>

                        </tr>
                        <tr>
                            <th>工程桩施工单位</th>
                            <td><%= data.identifyMain.newProject.pileConstructionOrg%></td>
                            <th>桩型</th>
                            <td><%= data.identifyMain.newProject.pileModel%></td>
                        </tr>
                        <tr>
                            <th>工程桩设计单位</th>
                            <td><%= data.identifyMain.newProject.pileDesignOrg%></td>
                            <th>桩长</th>
                            <td> <%= data.identifyMain.newProject.pileLength%>米</td>
                        </tr>
                        <tr>
                            <th>开工时间</th>
                            <td><%= data.identifyMain.newProject.pileStartDate%></td>
                            <th>直径</th>
                            <td> <%= data.identifyMain.newProject.pileDiameter%>米</td>
                        </tr>
                        <tr>
                            <th>竣工时间</th>
                            <td colspan="3"><%= data.identifyMain.newProject.pileEndDate%></td>

                        </tr>
                        <tr>
                            <th>支护桩施工单位</th>
                            <td><%= data.identifyMain.newProject.supportConstructionOrg%></td>
                            <th>桩型</th>
                            <td><%= data.identifyMain.newProject.supportModel%></td>
                        </tr>
                        <tr>
                            <th>支护桩设计单位</th>
                            <td><%= data.identifyMain.newProject.supportDesignOrg%></td>
                            <th>桩长</th>
                            <td><%= data.identifyMain.newProject.supportLength%>米</td>
                        </tr>
                        <tr>
                            <th>开工时间</th>
                            <td><%= data.identifyMain.newProject.supportStartDate%></td>
                            <th>直径</th>
                            <td><%= data.identifyMain.newProject.supportDiameter%>米</td>
                        </tr>
                        <tr>
                            <th>竣工时间</th>
                            <td colspan="3"><%= data.identifyMain.newProject.supportEndDate%></td>

                        </tr>
                        <tr>
                            <th>支撑结构</th>
                            <td><%= data.identifyMain.newProject.supStructure%></td>
                            <th>间距</th>
                            <td><%= data.identifyMain.newProject.spacing%>米</td>
                        </tr>
                        <tr>
                            <th>道路沉降量</th>
                            <td><%= data.identifyMain.newProject.roadSettlement%></td>
                            <th>桩位移量</th>
                            <td><%= data.identifyMain.newProject.pileDisplacement%></td>
                        </tr>
                        <tr>
                            <th>建筑物沉降量</th>
                            <td><%= data.identifyMain.newProject.buildingSettlement%></td>
                            <th>基坑面积</th>
                            <td><%= data.identifyMain.newProject.foundationArea%>㎡</td>
                        </tr>
                        <tr>
                            <th>开挖深度</th>
                            <td><%= data.identifyMain.newProject.depth%>米</td>
                            <th>距邻房</th>
                            <td><%= data.identifyMain.newProject.margin%>米</td>
                        </tr>
                        <tr>
                            <th>开挖时间</th>
                            <td><%= data.identifyMain.newProject.workDate%></td>
                            <th>竣工时间</th>
                            <td><%= data.identifyMain.newProject.endDate%></td>
                        </tr>
                        <tr>
                            <th>排水方式</th>
                            <td colspan="3"><%= data.identifyMain.newProject.drainageMode%></td>
                        </tr>
                        <tr>
                            <th>止水方案</th>
                            <td colspan="3"><%= data.identifyMain.newProject.stopMode%></td>
                        </tr>
                        <tr>
                            <th>房屋安全鉴定委托书附表</th>
                            <td name="clientFile"></td>
                            <th>岩土工程勘察报告</th>
                            <td name="reportFile"></td>
                        </tr>
                        <tr>
                            <th>新建工程建筑及结构图纸</th>
                            <td name="structureFile"></td>
                            <th>新建工程基坑支护方案</th>
                            <td name="foundationFile"></td>
                        </tr>
                        <tr>
                            <th>鉴定房屋建筑及结构图纸</th>
                            <td name="identifyHouseFile" colspan="3"></td>
                        </tr>
                        </tbody>
                        <%}%>
                    </table>
                </div>
            </div>
        </div>
    </div>
</script>

<#-- update by wangwj 20190219 start -->
<script type="text/template" id="tpl_accept">
    <div id="identifyMain<%= data.recoud.status%>_<%= data.recoud.sort%>">
        <table class="table table-bordered table-data">
            <colgroup>
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
            </colgroup>
            <tbody>
            <th>经办人</th>
            <td><%=data.recoud.operatorUserName%></td>
            <th>状态</th>
            <td>预受理</td>
            <th>操作时间</th>
            <td><%=data.recoud.operatorTime%></td>
            </tbody>
        </table>
        <table class="table table-bordered table-data" id="identifyMain<%= data.recoud.status%>_<%= data.recoud.sort%>_cont" style="display: none;">
            <colgroup>
                <col width="20%">
                <col width="30%">
                <col width="20%">
            </colgroup>
            <tbody >
            <tr>
                <th>分派对象</th>
                <td><p>负责人:<span>&nbsp;&nbsp;&nbsp;<%=data.identifyAccept.userName%></span></p>
                    <p>参与人员:<span>&nbsp;&nbsp;&nbsp;<%=data.identifyAccept.joinPersonName%></span></p></td>
                <th>紧急程度</th>
                <td>
                    <% if(data.identifyAccept.emergency === 1){%>
                    一般
                    <%}else{%>
                    紧急
                    <%}%>
                </td>
            </tr>
            <tr>
                <th>要求完成时间</th>
                <td colspan="3"><%=data.identifyAccept.requireDate%></td>
            </tr>
            </tbody>
        </table>
    </div>
</script>
<script type="text/template" id="tpl_preview">
    <div id="identifyMain<%= data.recoud.status%>_<%= data.recoud.sort%>">
        <table class="table table-bordered table-data">
            <colgroup>
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
            </colgroup>
            <tbody>
            <th>经办人</th>
            <td><%=data.recoud.operatorUserName%></td>
            <th>状态</th>
            <td>办结</td>
            <th>操作时间</th>
            <td><%=data.recoud.operatorTime%></td>
            </tbody>
        </table>
        <table class="table table-bordered table-data" id="identifyMain<%= data.recoud.status%>_<%= data.recoud.sort%>_cont" style="display: none;">
            <colgroup>
                <col width="20%">
                <col width="30%">
                <col width="20%">
            </colgroup>
            <tbody >
            <tr>
                <th>实施时间</th>
                <td><%=data.identifyPreview.implementTime%></td>
                <th>房屋检查意见</th>
                <td><%=data.identifyPreview.opinion%></td>
            </tr>
            <tr>
                <th>现场照片</th>
                <td>
                    <div name="photo1"></div>
                </td>
                <th>附档文件</th>
                <td colspan="3">
                    <div name="photo3"></div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</script>
<script type="text/template" id="tpl_plan">
    <div id="identifyMain<%= data.recoud.status%>_<%= data.recoud.sort%>">

        <table class="table table-bordered table-data">
            <colgroup>
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
            </colgroup>
            <tbody>
            <th>经办人</th>
            <td><%=data.recoud.operatorUserName%></td>
            <th>状态</th>
            <td>办结</td>
            <th>操作时间</th>
            <td><%=data.recoud.operatorTime%></td>
            </tbody>
        </table>
        <table class="table table-bordered table-data" id="identifyMain<%= data.recoud.status%>_<%= data.recoud.sort%>_cont" style="display: none;">
            <colgroup>
                <col width="20%">
                <col width="30%">
                <col width="20%">
            </colgroup>

            <tr>
                <th>出具方案时间</th>
                <td colspan="3"><%=data.recoud.operatorTime%></td>
            </tr>
            <tr>
                <th>方案</th>
                <td colspan="3">
                    <div name="fangAn1"></div>
                </td>
            </tr>

        </table>
    </div>
</script>
<script type="text/template" id="tpl_contract">
    <div id="identifyMain<%= data.recoud.status%>_<%= data.recoud.sort%>">
        <table class="table table-bordered table-data">
            <colgroup>
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
            </colgroup>
            <tbody>
            <th>经办人</th>
            <td><%=data.recoud.operatorUserName%></td>
            <th>状态</th>
            <td>办结</td>
            <th>操作时间</th>
            <td><%=data.recoud.operatorTime%></td>
            </tbody>
        </table>
        <table class="table table-bordered table-data"  id="identifyMain<%= data.recoud.status%>_<%= data.recoud.sort%>_cont" style="display: none;">
            <colgroup>
                <col width="20%">
                <col width="30%">
                <col width="20%">
            </colgroup>
            <tbody>
            <tr>
                <th>合同金额</th>
                <td><%= data.identifyContract.contractAmount%> 元</td>
                <th>鉴定次数</th>
                <td><%= data.identifyContract.identifyCount%></td>
            </tr>
            <tr>
                <th>合同</th>
                <td>
                    <div name="contractFile"></div>
                </td>
                <th>说明</th>
                <td><%=data.recoud.remarks%></td>
            </tr>
            </tbody>
        </table>
    </div>
</script>

<script type="text/template" id="tpl_report">
    <div id="identifyMain<%= data.recoud.status%>_<%= data.recoud.sort%>">
        <table class="table table-bordered table-data">
            <colgroup>
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
            </colgroup>
            <tbody>
            <th>经办人</th>
            <td><%=data.recoud.operatorUserName%></td>
            <th>状态</th>
            <td>办结</td>
            <th>操作时间</th>
            <td><%=data.recoud.operatorTime%></td>
            </tbody>
        </table>

        <div id="identifyMain<%= data.recoud.status%>_<%= data.recoud.sort%>_cont" style="display: none;">
            <table class="table table-bordered table-data">
                <colgroup>
                    <col width="20%">
                    <col width="30%">
                    <col width="20%">
                </colgroup>
                <tbody>
                <tr>
                    <th>上传报告方式</th>
                    <td colspan="3">
                        <%
                        if (data.identifyReport.method == 1) {
                        %>
                        分栋上传
                        <%
                        } else {
                        %>
                        汇总上传
                        <%
                        }
                        %>
                    </td>
                </tr>
                <tr name="fileTr">
                    <th>鉴定报告</th>
                    <td>
                        <div name="identifyFileId"></div>
                    </td>
                    <th>检测报告</th>
                    <td>
                        <div name="testingFileId"></div>
                    </td>
                </tr>
                </tbody>
            </table>

            <table class="table table-bordered table-data" name="detailTable" id="mainTable">
                <thead>
                <tr>
                    <th style="text-align: center;">房屋名称</th>
                    <th style="text-align: center;">鉴定等级</th>
                    <th style="text-align: center;">鉴定结论</th>
                    <th style="text-align: center;">处理意见</th>
                    <th style="text-align: center;">鉴定报告</th>
                    <th style="text-align: center;">检测报告</th>
                </tr>
                </thead>
                <tbody>
                <%
                _.each(data.identifyReport.reportDetailList, function(item, index) {
                %>
                <tr>
                    <td><%= item.houseName%></td>
                    <td><%= item.identifyResultName%></td>
                    <td><%= item.conclusion%></td>
                    <td><%= item.opinion%></td>
                    <td>
                        <div name="identifyFileId_<%= index%>"></div>
                    </td>
                    <td>
                        <div name="testingFileId_<%= index%>"></div>
                    </td>
                </tr>
                <%
                });
                %>
                </tbody>
            </table>
        </div>
    </div>
</script>

<script type="text/template" id="tpl_verify">
    <div id="identifyMain<%= data.recoud.status%>_<%= data.recoud.sort%>">
        <table class="table table-bordered table-data">
            <colgroup>
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
            </colgroup>
            <tbody>
            <tr>
                <th>经办人</th>
                <td><%= data.recoud.operatorUserName%></td>
                <th>状态</th>
                <td>审核通过</td>
                <th>操作时间</th>
                <td><%= data.recoud.operatorTime%></td>
            </tr>
            </tbody>

        </table>
    </div>
</script>


<script type="text/template" id="tpl_notReason">
    <div id="<%= data.title%><%= data.recoud.status%>_<%= data.recoud.sort%>">
        <table class="table table-bordered table-data">
            <colgroup>
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
            </colgroup>
            <tbody>
            <th>经办人</th>
            <td><%=data.recoud.operatorUserName%></td>
            <th>状态</th>
            <td><%=data.status%></td>
            <th>操作时间</th>
            <td><%=data.recoud.operatorTime%></td>
            </tbody>
        </table>

    </div>
</script>

<script type="text/template" id="tpl_hasReason">
    <div id="<%= data.title%><%= data.recoud.status%>_<%= data.recoud.sort%>">
        <table class="table table-bordered table-data">
            <colgroup>
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
            </colgroup>
            <tbody>
            <th>经办人</th>
            <td><%=data.recoud.operatorUserName%></td>
            <th>状态</th>
            <td><%=data.status%></td>
            <th>操作时间</th>
            <td><%=data.recoud.operatorTime%></td>
            </tbody>
        </table>
        <table class="table table-bordered table-data" id="<%= data.title%><%= data.recoud.status%>_<%=data.recoud.sort%>_cont" style="display: none;">
            <colgroup>
                <col width="20%">
                <col width="30%">
                <col width="20%">
            </colgroup>
            <tr>
                <th><%= data.headText%>原因</th>
                <td colspan="3"><%=data.recoud.remarks%></td>
            </tr>
        </table>
    </div>
</script>

<script type="text/template" id="tpl_reviewApply">
    <div id="reviewMain<%= data.recoud.status%>_<%= data.recoud.sort%>">
        <table class="table table-bordered table-data">
            <colgroup>
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
            </colgroup>
            <tbody>
            <th>经办人</th>
            <td><%=data.recoud.operatorUserName%></td>
            <th>状态</th>
            <td>办结</td>
            <th>操作时间</th>
            <td><%=data.recoud.operatorTime%></td>
            </tbody>
        </table>

        <div id="reviewMain<%= data.recoud.status%>_<%= data.recoud.sort%>_cont" style="display: none;">
            <table class="table table-bordered table-data" >
                <colgroup>
                    <col width="20%">
                    <col width="30%">
                    <col width="20%">
                </colgroup>

                <tbody>
                <tr>
                    <th>编号</th>
                    <td><span><%=data.review.identifyMain.caseNo%></span></td>
                    <th>房屋地址</th>
                    <td name="address"></td>
                </tr>
                <tr>
                    <th>鉴定单位</th>
                    <td><%=data.review.identifyMain.orgName%></td>
                    <th>申请方式</th>
                    <td name="method"></td>
                </tr>
                <tr>
                    <th>鉴定申请时间</th>
                    <td><%=data.review.requestTime%></td>
                    <th>复审申请原因</th>
                    <td><%=data.review.reason%></td>
                </tr>
                <tr>
                    <th>申请表</th>
                    <td name='sheetId' colspan="3"></td>
                </tr>
                </tbody>
            </table>
            <table class="table table-bordered table-data" id="reviewTable">
                <colgroup>
                    <col width="25%">
                    <col width="25%">
                </colgroup>
                <thead>
                <tr>
                    <th>房屋名称</th>
                    <th>鉴定等级</th>
                    <th>鉴定报告</th>
                </tr>
                </thead>
                <tbody name="reviewApply" >

                </tbody>
            </table>
        </div>

    </div>
</script>
<#-- update by wangwj 20190219 end -->
<script type="text/template" id="tpl_reviewAccept">
    <div id="reviewMain<%= data.recoud.status%>_<%= data.recoud.sort%>">
        <table class="table table-bordered table-data">
            <colgroup>
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
            </colgroup>
            <tbody>
            <th>经办人</th>
            <td><%=data.recoud.operatorUserName%></td>
            <th>状态</th>
            <td>受理</td>
            <th>操作时间</th>
            <td><%=data.recoud.operatorTime%></td>
            </tbody>
        </table>
        <table class="table table-bordered table-data" id="reviewMain<%= data.recoud.status%>_<%= data.recoud.sort%>_cont" style="display: none;">
            <colgroup>
                <col width="20%">
                <col width="30%">
                <col width="20%">
            </colgroup>
            <tr>
                <th>受理原因</th>
                <td colspan="3"><%=data.reviewAccept.reason%></td>
            </tr>
        </table>
    </div>
</script>
<script type="text/template" id="tpl_reviewExpert">
    <!--选择专家-->
    <div id="reviewMain<%= data.recoud.status%>_<%= data.recoud.sort%>">
        <table class="table table-bordered table-data">
            <colgroup>
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
            </colgroup>
            <tbody>
            <th>经办人</th>
            <td><%=data.recoud.operatorUserName%></td>
            <th>状态</th>
            <td>办结</td>
            <th>操作时间</th>
            <td><%=data.recoud.operatorTime%></td>
            </tbody>
        </table>
        <table class="table table-bordered table-data" id="reviewMain<%= data.recoud.status%>_<%= data.recoud.sort%>_cont" style="display: none;">
            <colgroup>
                <col width="20%">
                <col width="30%">
                <col width="20%">
            </colgroup>
            <tr>
                <th>选择专家</th>
                <td name="reviewExpertsMsg" colspan="3"></td>
            </tr>
        </table>
    </div>
</script>
<script type="text/template" id="tpl_reviewConfirm">
    <div id="reviewMain<%= data.recoud.status%>_<%= data.recoud.sort%>">
        <table class="table table-bordered table-data">
            <colgroup>
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
            </colgroup>
            <tbody>
            <th>经办人</th>
            <td><%=data.recoud.operatorUserName%></td>
            <th>状态</th>
            <td>办结</td>
            <th>操作时间</th>
            <td><%=data.recoud.operatorTime%></td>
            </tbody>
        </table>
        <table class="table table-bordered table-data" id="reviewMain<%= data.recoud.status%>_<%= data.recoud.sort%>_cont" style="display: none;">
            <colgroup>
                <col width="20%">
                <col width="30%">
                <col width="20%">
            </colgroup>
            <tr>
                <th>确认专家</th>
                <td name="reviewExpertsMsg" colspan="3"></td>
            </tr>
        </table>
    </div>
</script>
<script type="text/template" id="tpl_reviewOpinion">
    <div id="reviewMain<%= data.recoud.status%>_<%= data.recoud.sort%>">
        <table class="table table-bordered table-data">
            <colgroup>
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
            </colgroup>
            <tbody>
            <th>经办人</th>
            <td><%=data.recoud.operatorUserName%></td>
            <th>状态</th>
            <td>办结</td>
            <th>操作时间</th>
            <td><%=data.recoud.operatorTime%></td>
            </tbody>
        </table>
        <table class="table table-bordered table-data" id="reviewMain<%= data.recoud.status%>_<%= data.recoud.sort%>_cont" style="display: none;">
            <colgroup>
                <col width="20%">
                <col width="30%">
                <col width="20%">
            </colgroup>
            <tr>
                <th>会议时间</th>
                <td><%=data.reviewOpinion.meetingTime%></td>
                <th>会议地点</th>
                <td><%=data.reviewOpinion.address%></td>
            </tr>
            <tr>
                <th>参会部门</th>
                <td><%=data.reviewOpinion.deptName%></td>
                <th>参会专家</th>
                <td><%=data.reviewOpinion.experts%></td>
            </tr>
            <tr>
                <th>其他参会人员</th>
                <td><%=data.reviewOpinion.others%></td>
                <th>复核意见</th>
                <td>
                    <%
                    if (data.reviewOpinion.decision == '0') {
                    %>
                    与鉴定报告不一致
                    <%
                    } else {
                    %>
                    与鉴定报告一致
                    <%
                    }
                    %>
                </td>
            </tr>
            <tr>
                <th>专家复核结论</th>
                <td colspan="3"><%=data.reviewOpinion.opinion%></td>
            </tr>
            <tr>
                <th>相关文件</th>
                <td colspan="3" name="opinionFileIds"></td>
            </tr>
        </table>
    </div>
</script>

<script type="text/template" id="tpl_correctApply">
    <div id="correctMain<%= data.recoud.status%>_<%= data.recoud.sort%>">
        <table class="table table-bordered table-data">
            <colgroup>
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
            </colgroup>
            <tbody>
            <th>经办人</th>
            <td><%=data.recoud.operatorUserName%></td>
            <th>状态</th>
            <td>办结</td>
            <th>操作时间</th>
            <td><%=data.recoud.operatorTime%></td>
            </tbody>
        </table>
        <div id="correctMain<%= data.recoud.status%>_<%= data.recoud.sort%>_cont" style="display: none">
            <table class="table table-bordered table-data" >
                <colgroup>
                    <col width="20%">
                    <col width="30%">
                    <col width="20%">
                </colgroup>

                <tbody>
                <tr>
                    <th>编号</th>
                    <td><span><%=data.correct.identifyMain.caseNo%></span></td>
                    <th>房屋地址</th>
                    <td name="address"></td>
                </tr>
                <tr>
                    <th>鉴定单位</th>
                    <td><%=data.correct.identifyMain.orgName%></td>
                    <th>申请方式</th>
                    <td name="method"></td>
                </tr>
                <tr>
                    <th>鉴定申请时间</th>
                    <td><%=data.correct.requestTime%></td>
                    <th>补正申请原因</th>
                    <td><%=data.correct.reason%></td>
                </tr>
                </tbody>
            </table>
            <table class="table table-bordered table-data" id="correctTable">
                <colgroup>
                    <col width="25%">
                    <col width="25%">
                </colgroup>
                <thead>
                <tr>
                    <th>房屋名称</th>
                    <th>鉴定等级</th>
                    <th>鉴定报告</th>
                </tr>
                </thead>
                <tbody name="correctApply" >

                </tbody>
            </table>
        </div>
    </div>
</script>
<script type="text/template" id="tpl_correctCheck">
    <div id="correctMain<%= data.recoud.status%>_<%= data.recoud.sort%>">
        <table class="table table-bordered table-data">
            <colgroup>
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
            </colgroup>
            <tbody>
            <th>经办人</th>
            <td><%=data.recoud.operatorUserName%></td>
            <th>状态</th>
            <td>审核通过</td>
            <th>操作时间</th>
            <td><%=data.recoud.operatorTime%></td>
            </tbody>
        </table>
        <table class="table table-bordered table-data" id="correctMain<%= data.recoud.status%>_<%= data.recoud.sort%>_cont" style="display: none">
            <colgroup>
                <col width="16.5%">
            </colgroup>
            <tr>
                <th>审核意见</th>
                <td><%=data.correctVerify.checkOpinion%></td>
            </tr>
        </table>
    </div>
</script>
<script type="text/template" id="tpl_corrrectFiles">
    <div id="correctMain<%= data.recoud.status%>_<%= data.recoud.sort%>">
        <table class="table table-bordered table-data">
            <colgroup>
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
                <col width="16.5%">
            </colgroup>
            <tbody>
            <th>经办人</th>
            <td><%=data.recoud.operatorUserName%></td>
            <th>状态</th>
            <td>办结</td>
            <th>操作时间</th>
            <td><%=data.recoud.operatorTime%></td>
            </tbody>
        </table>
        <table class="table table-bordered table-data" id="correctMain<%= data.recoud.status%>_<%= data.recoud.sort%>_cont" style="display: none">
            <colgroup>
                <col width="20%">
                <col width="30%">
                <col width="20%">
            </colgroup>
            <tr>
                <th>补正资料</th>
                <td colspan="3">
                    <div name="correctFiles"></div>
                </td>
            </tr>
            <tr>
                <th>说明</th>
                <td colspan="3"><%=data.correct.correctUpload.description%></td>
            </tr>
        </table>
    </div>
</script>
<#-- update by xujc 2019/2/21 end -->
<ex-section id="ScriptBody">
    <link type="text/css" rel="stylesheet" href="${request.contextPath}${urls.getForLookupPath('/css/modules/lc.css')}">
    <link type="text/css" rel="stylesheet" href="${request.contextPath}${urls.getForLookupPath('/css/modules/orgCreditScore.css')}"/>
    <script type="text/javascript" src="${request.contextPath}${urls.getForLookupPath('/js/main/modules/detail.js')}"></script>
    <script type="text/javascript">

        if (window.myPageLogic) {
            myPageLogic.initData = {
                restUrlPrefix: "/setting/scores"
            };
        } else {
            pageLogic.initData = {
                restUrlPrefix: "/setting/scores"
            };
        }

    </script>
</ex-section>

</body>
</html>
<#-- update by xujc 2019/1/29 end -->
<#--add by xujc 2018-11-17 end -->