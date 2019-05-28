<!-- add by xujc 2018/12/6 start -->
<!-- update by xujc 2019/1/24 start -->
<html>
<head>
    <title>补正变更详细</title>
    <!-- update by xujc 2019/2/21 start -->
    <style>
        #correctTable th,#correctTable td{
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

            <div id="CorrectDetail"></div>

            <div class="button_area">
                <button type="button" class="btn btn-default btn-danger" id="fanhui"><i class="fa fa-reply"></i> 返回
                </button>
            </div>
        </div>
    </div>
</div>

<#--add by wangwj 20190116 start-->
<script type="text/template" id="tpl_title">
    <%_.each(data.recouds,function(item,index){%>
    <li>
        <a href="#<%= data.name%><%=item.status%>_<%= item.sort%>">
            <span class="lc_title" style="color: #3399DD"><%= item.title%></span>
            <em class="lc_time"><%= item.operatorTime%></em>
        </a>
    </li>
    <%})%>
</script>

<script type="text/template" id="tpl_notReason">
    <div id="identifyMain<%= data.recoud.sort%>">
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
    <div id="identifyMain<%= data.recoud.sort%>">
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
        <table class="table table-bordered table-data" id="correctMain<%=data.recoud.status%>_<%=data.recoud.sort%>_cont" style="display: none;">
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
<!-- update by xujc 2019/2/21 start -->
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
<!-- update by xujc 2019/2/21 end -->
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

            <tbody >
            <tr>
                <th>审核意见</th>
                <td><%=data.correctVerify.checkOpinion%></td>
            </tr>
            </tbody>
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
            <td>已上传资料</td>
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
            <tbody >
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
            </tbody>
        </table>
    </div>
</script>
<#--add by wangwj 20190116 end-->


<ex-section id="ScriptBody">
    <link type="text/css" rel="stylesheet" href="${request.contextPath}${urls.getForLookupPath('/css/modules/lc.css')}">
    <link type="text/css" rel="stylesheet" href="${request.contextPath}${urls.getForLookupPath('/css/modules/orgCreditScore.css')}"/>
    <script type="text/javascript" src="${request.contextPath}${urls.getForLookupPath('/js/main/modules/correctDetail.js')}"></script>


</ex-section>

</body>
</html>
<!-- update by xujc 2019/1/24 end -->
<!-- add by xujc 2018/12/6 end -->