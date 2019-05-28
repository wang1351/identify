<html>
<head>
    <title></title>
</head>
<body>

<div>
<#include "detail.ftl" />
    <form class="form-horizontal" id="form" style="margin-top: 10px;">
        <input type="hidden" name="mainId" id="mainId" value="${mainId}"/>
        <div class="form-group">
            <label class="col-sm-5 text-right">
                <span><span style="color: red;">* </span>出具方案</span>
            </label>
            <div class="col-sm-4">
                <div id="planFile"></div>
                <span style="color: red" id="remarks">只支持docx格式</span>
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
</div>

<ex-section id="ScriptBody">
    <script type="text/javascript" src="${request.contextPath}${urls.getForLookupPath('/js/main/modules/identifyPlanList.js')}"></script>
    <script type="text/javascript">
        pageLogic.initData = {
            restUrlPrefix: "/identifies/plan",
            mainId: "${mainId}"
        };

    </script>
</ex-section>
</body>
</html>
