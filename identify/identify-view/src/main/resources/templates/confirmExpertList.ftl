<!-- add by xujc 2019/1/4 start -->
<!-- update by xujc 2019/1/23 start -->
<html>
<head>
    <title>确认专家</title>
</head>
<body>

<div>
<#include "reviewDetail.ftl" />
    <form class="form-horizontal" style="margin-top: 10px;" action="" id="expertForm">
        <input type="hidden" name="reviewId" id="reviewId" value="${reviewId}" />

        <div class="form-group">
            <div >
                <input type="hidden" id="hideMethod">
                <a class="btn btn-blue" id="chooceBtn">

                </a>
                <hr style="BORDER-BOTTOM-STYLE: dotted; BORDER-LEFT-STYLE: dotted; BORDER-RIGHT-STYLE: dotted; BORDER-TOP-STYLE: dotted" color=#111111 size=1>
                <input type="hidden" id="selectedId">
                <div id="fieldExperts">

                </div>

            </div>


        </div>

    </form>

    <div class="button_area">
        <button class="btn btn-default btn-blue" id="saveBtn">
            <i class="fa fa-save"></i> 确定
        </button>
        <button class="btn btn-default btn-danger" id="backBtn">
            <i class="fa fa-reply"></i> 返回
        </button>
    </div>
    <script type="text/template" id="tpl_reviewExperts">
        <% _.each(data, function(item){ %>
        <table class="table"  style="width: 60%">
            <colgroup>
                <col width="20%">
                <col width="10%">
                <col width="30%">
                <col width="10%">
            </colgroup>
            <tbody>
                <% _.each(item.experts, function(expert){ %>
                <tr id="<%=expert.expertId%>">
                    <td><%=expert.expertFieldName %></td>
                    <td><input type="checkbox" class="delShow" value="<%=expert.expertField%>"></td>
                    <td><%=expert.fullName%></td>
                    <td><span class="glyphicon glyphicon-trash" style="display: none"></span></td>
                </tr>
                <%  }); %>
            </tbody>
        </table>
        <%  }); %>

    </script>
</div>
<#--<#include "reviewDetail.ftl" />-->
<ex-section id="ScriptBody">
    <link type="text/css" rel="stylesheet" href="${request.contextPath}${urls.getForLookupPath('/css/modules/orgCreditScore.css')}" />
    <script type="text/javascript" src="${request.contextPath}${urls.getForLookupPath('/js/main/modules/confirmExpertList.js')}"></script>
    <script type="text/javascript">
        pageLogic.initData = {
            restUrlPrefix: "/tasks/review",
            reviewId: "${reviewId}"
        };
    </script>
</ex-section>
</body>
</html>
<!-- update by xujc 2019/1/23 end -->
<!-- add by xujc 2019/1/4 end -->
