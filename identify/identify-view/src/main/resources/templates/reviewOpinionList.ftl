<html>
<head>
    <title>专家意见</title>
</head>
<body>
<div>
<#include "reviewDetail.ftl" />
<#-- update by xujc 2019/3/12 start -->
    <form class="form-horizontal" id="opinionForm" style="margin-top: 10px;">
        <input type="hidden" name="reviewId" id="reviewId" value="${reviewId}" />
        <div class="form-group">
            <label class="col-xs-2 text-right">
                <span><span style="color: red;">* </span> 会议时间</span>
            </label>

            <div class="col-xs-4">
                <div class="input-group date form_datetime">
                    <input class="form-control" id="meetingTime" name="meetingTime" readonly/>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
            </div>

            <label class="col-xs-2 text-right">
                <span><span style="color: red;">* </span> 会议地点</span>
            </label>
            <div class="col-xs-4">
                <input class="form-control" name="address" id="address">
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-2 text-right">
                <span><span style="color: red;">* </span> 参会部门</span>
            </label>
            <div class="col-xs-4">
                <input class="form-control" name="deptName" id="deptName">
            </div>

        </div>
        <div class="form-group">
            <label class="col-xs-2 text-right">
                <span><span style="color: red;">* </span> 参会专家</span>
            </label>
            <div class="col-xs-4">
                <input class="form-control" name="experts" id="experts">
            </div>
            <label class="col-xs-2 text-right">
                <span><span style="color: red;">* </span> 其他参会人员</span>
            </label>
            <div class="col-xs-4">
                <input class="form-control" name="others" id="others" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-2 text-right">
                <span><span style="color: red;">* </span> 复核意见</span>
            </label>
            <div class="col-xs-4">
                    <label class="radio-inline">
                        <input  name="decision" type="radio" value="1" checked> 与鉴定报告一致
                    </label>
                    <label class="radio-inline">
                        <input   name="decision" type="radio" value="0"> 与鉴定报告不一致
                    </label>
            </div>
            <label class="col-xs-2 text-right">
                <span> 相关文件</span>
            </label>
            <div class="col-xs-4">
                <div id="opinionFile"></div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-2 text-right">
                <span><span style="color: red;">* </span> 专家复核结论</span>
            </label>
            <div class="col-xs-10">
                <textarea style="height: 150px" class="form-control" id="opinion" name="opinion"></textarea>
            </div>
        </div>
    </form>
         <#--update by wangwj 20181213 end-->
    <div class="button_area">
        <button class="btn btn-default btn-blue" id="saveBtn">
            <i class="fa fa-save"></i> 确定
        </button>
        <button class="btn btn-default btn-danger" id="backBtn">
            <i class="fa fa-reply"></i> 返回
        </button>
    </div>
</div>
<#-- update by xujc 2019/3/12 end -->
<#--<#include "reviewDetail.ftl" />-->
<ex-section id="ScriptBody">
    <script type="text/javascript" src="${request.contextPath}${urls.getForLookupPath('/js/main/modules/reviewOpinionList.js')}"></script>
    <script type="text/javascript">
        pageLogic.initData = {
            restUrlPrefix: "/tasks/review",
            reviewId: "${reviewId}"
        };
    </script>
</ex-section>
</body>
</html>
