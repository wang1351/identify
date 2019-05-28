<!-- add by xujc 2018/12/4 start -->
<html>
<head>
    <title>复核详细</title>
    <!-- update by xujc 2019/2/21 start -->
    <style>
        #reviewTable th,#reviewTable td{
            vertical-align:middle!important;/*设置垂直居中*/
            text-align: center;
        }
    </style>
    <!-- update by xujc 2019/2/21 end -->
</head>
<body>
<div id="form">

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
            <div id="ReviewMain"></div>
            <div class="button_area">
                <button type="button" class="btn btn-default btn-danger" id="fanhui"><i class="fa fa-reply"></i> 返回
                </button>
            </div>
        </div>
    </div>
</div>
<#-- update by xujc 2019/2/22 start -->
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
<script type="text/template" id="tpl_notReason">

    <#--控制标题栏的高度-->
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

    </div>
</script>

<script type="text/template" id="tpl_hasReason">
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
            <td><%= data.status%></td>
            <th>操作时间</th>
            <td><%=data.recoud.operatorTime%></td>
            </tbody>
        </table>
        <table class="table table-bordered table-data" id="reviewMain<%= data.recoud.status%>_<%=data.recoud.sort%>_cont" style="display: none;">
            <colgroup>
                <col width="20%">
                <col width="30%">
                <col width="20%">
            </colgroup>
            <tbody >
            <tr>
                <th><%= data.headText%>原因</th>
                <td colspan="3"><%=data.recoud.remarks%></td>
            </tr>
            </tbody>
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
            <td>已受理</td>
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

            <tbody >
            <tr>
                <th>受理原因</th>
                <td colspan="3"><%=data.reviewAccept.reason%></td>
            </tr>
            </tbody>
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

            <tbody >
            <tr>
                <th>选择专家</th>
                <td name="reviewExpertsMsg" colspan="3"></td>
            </tr>
            </tbody>
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

            <tbody >
            <tr>
                <th>确认专家</th>
                <td name="reviewExpertsMsg" colspan="3"></td>
            </tr>
            </tbody>
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

            <tbody >
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
                <#--update by wangwj 20190125 start-->
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
            <#--update by wangwj 20190125 end-->
            </tr>
            <tr>
                <th>专家复核结论</th>
                <td colspan="3"><%=data.reviewOpinion.opinion%></td>
            </tr>
            <tr>
                <th>相关文件</th>
                <td colspan="3" name="opinionFileIds"></td>
            </tr>
            </tbody>
        </table>
    </div>
</script>
<#-- update by xujc 2019/2/22 end -->
<ex-section id="ScriptBody">
    <link type="text/css" rel="stylesheet" href="${request.contextPath}${urls.getForLookupPath('/css/modules/lc.css')}">
    <link type="text/css" rel="stylesheet" href="${request.contextPath}${urls.getForLookupPath('/css/modules/orgCreditScore.css')}"/>
    <script type="text/javascript" src="${request.contextPath}${urls.getForLookupPath('/js/main/modules/reviewDetail.js')}"></script>
</ex-section>

</body>
</html>
<!-- add by xujc 2018/12/4 end -->