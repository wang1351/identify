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

    <div class="button_area">
        <button class="btn btn-default btn-blue" id="saveBtn">
            <i class="fa fa-save"></i> 签发
        </button>
        <button class="btn btn-default btn-danger" id="backBtn">
            <i class="fa fa-reply"></i> 返回
        </button>
    </div>
</div>


<#--弹出的对话框-->


<ex-section id="ScriptBody">
    <script type="text/javascript" src="${request.contextPath}${urls.getForLookupPath('/js/main/modules/identifySignList.js')}"></script>
    <script type="text/javascript">
        pageLogic.initData = {
            restUrlPrefix: "/identifies/sign",
            mainId: "${mainId}"
        };

    </script>
</ex-section>
</body>
</html>
