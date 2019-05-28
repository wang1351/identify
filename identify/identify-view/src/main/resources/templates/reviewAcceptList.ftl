
<!-- add by xujc 2018/12/25 start -->
<html>
<head>
    <title>复核审核</title>

</head>
<body>
<#include "reviewDetail.ftl" />

    <form class="form-horizontal" style="margin-top: 10px;">
        <div class="form-group">
            <div class="col-md-4 text-right">
                <span><span style="color: red;">* </span>原因</span>
            </div>
            <div class="col-md-4">
                <textarea class="form-control" rows="5" id="remarks" name="remarks"></textarea>
            </div>
        </div>
    </form>
    <!-- 按钮栏 -->
    <div class="button_area">
        <button class="btn btn-default btn-blue" id="acceptBtn">
            <i class="fa fa-save"></i> 受理
        </button>
        <button class="btn btn-default btn-danger" id="refuseBtn">
            <i class="fa fa-reply"></i> 不受理
        </button>
    </div>

<ex-section id="ScriptBody">
    <link type="text/css" rel="stylesheet" href="${request.contextPath}${urls.getForLookupPath('/css/modules/orgCreditScore.css')}" />
    <script type="text/javascript" src="${request.contextPath}${urls.getForLookupPath('/js/main/modules/reviewAcceptList.js')}"></script>
    <script type="text/javascript">
        pageLogic.initData = {
            restUrlPrefix: "/identifies/accept",
            reviewId: "${reviewId}"
        };

    </script>
</ex-section>
</body>
</html>
<!-- add by xujc 2018/12/25 end -->