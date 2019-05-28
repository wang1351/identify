
<!-- add by xujc 2018/11/30 start -->
<html>
<head>
    <title>业务受理</title>

</head>
<body>
<#include "detail.ftl" />
    <!-- 主页面 -->
    <form class="form-horizontal" id="form" style="margin-top: 10px;">
        <input type="hidden" id="id" name="mainId" value="${mainId}"/>
        <div class="form-group">
            <div class="col-md-4 text-right">
                <span><span style="color: red;">* </span>受理意见</span>
            </div>
            <#--update  by wangwj 20181130 start-->
            <div class="col-md-4">
                <label class="radio-inline">
                    <input class="accept" name="accept" type="radio" value="1"> 预受理
                </label>
                <label class="radio-inline">
                    <input class="unAccept"  name="accept" type="radio" value="0"> 不受理
                </label>
            </div>
        </div>
                     <#--update  by wangwj 20181130 end-->

        <div class="form-group">
            <div class="col-md-4 text-right">
                <span><span style="color: red;">* </span>分派对象</span>
            </div>
            <div class="col-md-2 col-box-left">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        负责人员
                    </div>
                    <div class="panel-body">
                       <ul type="none" id="preview">
                       </ul>
                    </div>
                </div>
            </div>
            <div class="col-md-2 col-box-right">
                <div class="panel panel-default">
                    <div class="panel-heading">参与人员</div>
                    <div class="panel-body">
                        <ul id="orgUsers" type="none">
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-4 text-right">
                <span><span style="color: red;">* </span>要求完成时间</span>
            </div>
            <div class="col-md-2">
                <div class="input-group date form_datetime">
                    <input class="form-control" id="requireDate" name="requireDate" readonly/>
                    <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-4 text-right">
                <span><span style="color: red;">* </span>紧急度</span>
            </div>
            <div class="col-md-4">
            <label class="radio-inline">
                <input  name="emergency" type="radio" value="1" checked> 一般
            </label>
                <label class="radio-inline">
                    <input name="emergency" type="radio" value="2"> 紧急
                </label>
            </div>
        </div>
    </form>

    <form class="form-horizontal textHide" id="unform">
        <input type="hidden"  name="mainId" value="${mainId}"/>
        <div class="form-group">
            <div class="col-md-4 text-right">
                <span><span style="color: red;">* </span>受理意见</span>
            </div>
            <#--update by wangwj 20181212 start-->
            <div class="col-md-4">
                <label class="radio-inline">
                    <input class="accept" name="accept" type="radio" value="1"> 预受理
                </label>
                <label class="radio-inline">
                    <input class="unAccept"  name="accept" type="radio" value="0"> 不受理
                </label>
            </div>
            <#--update by wangwj 20181212 end-->
        </div>
        <div class="form-group">
            <div class="col-md-4 text-right">
                <span><span style="color: red;">* </span>不受理原因</span>
            </div>
            <div class="col-md-4">
                <textarea class="form-control" rows="5" name="remarks"></textarea>
            </div>
        </div>
    </form>
    <!-- 按钮栏 -->
    <div class="button_area">
        <button type="button"  class="btn btn-blue" style="width: 80px;margin-right: 30px" id="saveBtn">确定</button>
        <button type="button" class="btn btn-danger" style="width: 80px;" id="backBtn">返回</button>
    </div>

    <#--<div id="jdwt" class="tab-content-hide">
        <h6 class="title2"><span class="title_text">鉴定委托</span><span class="pull-right"><a class="fa fa-angle-down"></a></span></h6>
        <div class="tab-content">鉴定委托内容</div>
    </div>-->


<ex-section id="ScriptBody">
    <link type="text/css" rel="stylesheet" href="${request.contextPath}${urls.getForLookupPath('/css/modules/orgCreditScore.css')}" />
    <script type="text/javascript" src="${request.contextPath}${urls.getForLookupPath('/js/main/modules/identifyAcceptList.js')}"></script>
    <script type="text/javascript">
        pageLogic.initData = {
            restUrlPrefix: "/identifies/accept",
            mainId: "${mainId}"
        };

    </script>
</ex-section>
</body>
</html>
<!-- add by xujc 2018/11/30 end -->