<!-- add by xujc 2018/12/28 start -->
<html>
<head>
    <title>审核</title>
</head>
<body>
<#--add by wangwj 20190115 start-->
<#include "correctDetail.ftl" />
<#--add by wangwj 20190115 end-->
<div>
    <form class="form-horizontal" style="margin-top: 10px;">
        <input type="hidden" name="correctId" id="correctId" value="${correctId}" />
        <div class="form-group">
            <div class="col-md-2 text-right">
                <span>审核意见</span>
            </div>
            <div class="col-md-8">
                <textarea style="height: 150px" class="form-control" id="checkOpinion" name="checkOpinion"></textarea>
            </div>
        </div>
    </form>

    <div class="button_area">
        <button class="btn btn-default btn-blue" id="passBtn">
            <i class="fa fa-check"></i> 审核通过
        </button>
        <button class="btn btn-default btn-danger" id="failBtn">
            <i class="fa fa-close"></i> 审核不通过
        </button>
    </div>
</div>

<ex-section id="ScriptBody">
    <script type="text/javascript" src="${request.contextPath}${urls.getForLookupPath('/js/main/modules/correctCheckList.js')}"></script>
    <script type="text/javascript">
        pageLogic.initData = {
            restUrlPrefix: "/corrects",
            correctId: "${correctId}"
        };
    </script>
</ex-section>
</body>
</html>
<!-- add by xujc 2018/12/28 end -->
