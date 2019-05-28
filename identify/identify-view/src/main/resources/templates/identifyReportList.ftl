<html>
<head>
    <title></title>
</head>
<body>

<div>
<#include "detail.ftl" />
    <form class="form-horizontal" id="form" style="margin-top: 10px;">
        <input type="hidden" name="mainId" id="mainId" value="${mainId}" />
        <input type="hidden" name="isActive" id="isActive" value="${isActive?string("true","false")}" />
        <input type="hidden" name="reportId" id="reportId" />

        <div class="form-group">
            <div class="col-sm-4"></div>
            <div class="col-sm-4">
                <span>上传报告方式：</span>
                <label class="radio-inline"><input type="radio"  name="method" value="1">分栋上传</label>
                <label class="radio-inline"><input type="radio"  name="method" value="2" checked>汇总上传</label>
            </div>
            <div class="col-sm-4"></div>
        </div>

        <div class="title">
            <span id="address"></span>
            <span class="pull-right" style="color: red; font-weight: bold; font-size: 0.9em;">请将生成的二维码插入到鉴定报告word中&nbsp;&nbsp; </span>
        </div>
        <div>
            <table id="btTable" class="table table-condensed table-hover table-striped"></table>
        </div>

        <div id="reportAndTesting" style="display:none;">
            <div class="form-group">
                <label class="col-sm-2 text-right">
                    <span><span style="color: red;">* </span>二维码</span>
                </label>
                <div class="col-sm-4">
                    <button id='summaryQrCode' class='btn btn-sm btn-blue'><i class='fa fa-qrcode'></i> 生成</button>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 text-right">
                    <span><span style="color: red;">* </span>编制鉴定报告</span>
                </label>
                <div class="col-sm-4">
                    <div id="identifyFile"></div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 text-right">
                    <span><span style="color: red;">* </span>检测报告</span>
                </label>
                <div class="col-sm-4">
                    <div id="testingFile"></div>
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
</div>

<div id="modal">
    <div class="row">
        <div class="col-md-7">
            <form class="form-horizontal">
                <input type="hidden" id="splitId">
                <div class="form-group">
                    <div class="col-md-4 text-right">
                        <span><span style="color: red;">* </span>项目名称</span>
                    </div>
                    <div class="col-md-8">
                        <input class="form-control" id="proName" name="proName">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-4 text-right">
                        <span>报告编号</span>
                    </div>
                    <div class="col-md-8">
                        <span id="caseNo"></span>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-4 text-right">
                        <span>鉴定类别</span>
                    </div>
                    <div class="col-md-8">
                        <span id="identifyType"></span>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-4 text-right">
                        <span>报告日期</span>
                    </div>
                    <div class="col-md-8">
                        <span id="reportDate"></span>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-4 text-right">
                        <span>委托人名称</span>
                    </div>
                    <div class="col-md-8">
                        <span id="clientName"></span>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-4 text-right">
                        <span>鉴定单位名称</span>
                    </div>
                    <div class="col-md-8">
                        <span id="identifyOrgName"></span>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-5">
            <div id="qrCode" style="padding: 30px 50px;">
            </div>

            <div style="text-align: center">
                <a id="downloadQr" onclick="pageLogic.downLoadCode();"><i class="fa fa-download fa-lg" alt="下载至本地"></i></a>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-7">
            <h4 style="color: red; font-size: 0.8em; font-weight: bold;">请将二维码保存至本地，再插入到鉴定报告中</h4>
        </div>
        <div class="col-md-5">
        </div>
    </div>
</div>


<ex-section id="ScriptBody">
    <script type="text/javascript" src="${request.contextPath}${urls.getForLookupPath('/js/main/modules/identifyReportList.js')}"></script>
    <script type="text/javascript">
        pageLogic.initData = {
            modalParams: [
                {
                    width: 650,
                    height: 400,
                    title: "二维码"
                }
            ],
            restUrlPrefix: "/identifies/report",
            mainId: "${mainId}",
            content: "${content}",
            queryMsg: {
                mainId: "value:${mainId}"
            }
        };

    </script>
</ex-section>

<img id="qrCodeImg" src="${request.contextPath}/img/logo.png" style="display:none;"/>

</body>
</html>
