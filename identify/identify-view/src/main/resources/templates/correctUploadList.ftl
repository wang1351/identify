<!-- add by xujc 2018/12/09 start -->
<html>
<head>
    <title>补正</title>
</head>
<body>
<#--add by wangwj 20190115 start-->
<#include "correctDetail.ftl" />
<#--add by wangwj 20190115 end-->
<div>
    <form class="form-horizontal" style="margin-top: 10px;">
        <input type="hidden" name="correctId" id="correctId" value="${correctId}" />
        <div class="form-group">
            <label class="col-xs-2 text-right">
                <span><span style="color: red;">* </span> 补正资料</span>
            </label>
            <div class="col-xs-8">
                <div id="correctFile"></div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-2 text-right">
                <span>说明</span>
            </div>
            <div class="col-md-8">
                <textarea style="height: 100px" class="form-control" id="description" name="description"></textarea>
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
    <script type="text/javascript" src="${request.contextPath}${urls.getForLookupPath('/js/main/modules/correctUploadList.js')}"></script>
    <script type="text/javascript">
        pageLogic.initData = {
            restUrlPrefix: "/corrects",
            correctId: "${correctId}"
        };
    </script>
</ex-section>
</body>
</html>
<!-- add by xujc 2018/12/9 end -->
