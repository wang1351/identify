<html>
<head>
    <title></title>
    <style>
        #btTable th,#btTable td{
            text-align: center;
        }
        .file-options .delete {
            display: none;
        }

    </style>
</head>
<body>

<div>
<#include "detail.ftl" />
    <form class="form-horizontal" id="form" style="margin-top: 10px;">
        <input type="hidden" name="mainId" id="mainId" value="${mainId}" />
        <input type="hidden" name="reportId" id="reportId" />

        <div class="form-group">
            <div class="col-sm-4"></div>
            <div class="col-sm-4">
                <span>上传报告方式:</span>
                <span id="method"></span>
            </div>
            <div class="col-sm-4"></div>
        </div>

        <div class="form-group">
            <div class="row">
                <div class="col-sm-12">
                    <div>
                        <table id="btTable" class="table table-bordered table-condensed table-hover table-striped" ></table>
                    </div>
                </div>
            </div>
        </div>

        <div id="reportAndTesting" style="display:none;">
            <div class="form-group">
                <label class="col-sm-2 text-right">
                    <span>编制鉴定报告</span>
                </label>
                <div class="col-sm-4">
                    <div id="identifyFile"></div>
                </div>
            </div>


            <div class="form-group">
                <label class="col-sm-2 text-right">
                    <span>检测报告</span>
                </label>
                <div class="col-sm-4">
                    <div id="testingFile"></div>
                </div>
            </div>

        </div>

    </form>

    <div class="button_area" id="button_area">
        <button class="btn btn-default btn-blue" id="acceptBtn">
            <i class="fa fa-save"></i> 审核通过
        </button>
        <button class="btn btn-default btn-danger" id="refuseBtn">
            <i class="fa fa-reply"></i> 审核不通过
        </button>
    </div>
<!-- update by xujc 2019/2/21 start -->
    <script type="text/template" id="tpl_reportDetails">
        <div>
            <form class="form-horizontal">
                <div class="form-group">
                    <div class="col-md-2 text-right">
                        <span><span style="color: red;">* </span>审核不通过原因</span>
                    </div>
                    <div class="col-md-10">
                        <textarea class="form-control" rows="7" id="reason" name="reason"></textarea>
                    </div>
                </div>
                <div style="text-align: center">
                    <button type="button"  class="btn saveBtn" style="width: 80px;margin-right: 30px;background-color: #3598db" >提交</button>
                </div>
            </form>
        </div>
    </script>
    <!-- update by xujc 2019/2/21 end -->
</div>

<ex-section id="ScriptBody">
    <script type="text/javascript" src="${request.contextPath}${urls.getForLookupPath('/js/main/modules/identifyVerifyList.js')}"></script>
    <script type="text/javascript">
        pageLogic.initData = {
            restUrlPrefix: "/identifies/verify",
            mainId: "${mainId}"
        };

    </script>
</ex-section>
</body>
</html>
