<html>
<head>
    <title></title>
</head>
<body>

<div>
<#include "detail.ftl" />
    <form class="form-horizontal" id="previewForm" style="margin-top: 10px;">
        <input type="hidden" name="mainId" id="mainId" value="${mainId}" />
        <div class="form-group">
            <label class="col-sm-4 text-right">
                <span><span style="color: red;">* </span>实施时间</span>
            </label>

            <div class="col-sm-4">
                <div class="input-group date form_datetime">
                    <input class="form-control" id="implementTime" name="implementTime" readonly/>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-4 text-right">
                <span>现场照片</span>
            </label>
            <div class="col-sm-4">
                <div id="livePhoto"></div>
            </div>
        </div>

        <#--暂时移除险情照片  wangwj-->
        <#--<div class="form-group">-->
            <#--<label class="col-sm-4 text-right">-->
                <#--<span>险情照片</span>-->
            <#--</label>-->
            <#--<div class="col-sm-4">-->
                <#--<div id="dangerPhoto"></div>-->
            <#--</div>-->
        <#--</div>-->

        <div class="form-group">
            <label class="col-sm-4 text-right">
                <span>附档文件</span>
            </label>
            <div class="col-sm-4">
                <div id="files"></div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-4 text-right">
                <span><span style="color: red;">* </span>房屋检查意见</span>
            </label>
            <div class="col-sm-4">
                <textarea class="form-control" id="opinion" name="opinion" rows="5" ></textarea>
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
    <script type="text/javascript" src="${request.contextPath}${urls.getForLookupPath('/js/main/modules/identifyPreviewList.js')}"></script>
    <script type="text/javascript">
        pageLogic.initData = {
            restUrlPrefix: "/identifies/preview",
            mainId: "${mainId}"
        };

    </script>
</ex-section>
</body>
</html>
