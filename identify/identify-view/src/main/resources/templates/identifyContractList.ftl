<html>
<head>
    <title>签订合同</title>
</head>
<body>

<div>
<#include "detail.ftl" />
    <form class="form-horizontal" id="form" style="margin-top: 10px;">
        <input type="hidden" name="mainId" id="mainId" value="${mainId}"/>

        <div class="form-group">
            <label class="col-sm-5 text-right">
                <span><span style="color: red;">* </span>合同金额（元）</span>
            </label>

            <div class="col-sm-4">
                <input id="contractAmount" class="form-control" name="contractAmount">
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-5 text-right">
                <span><span style="color: red;">* </span>鉴定次数</span>
            </label>

            <div class="col-sm-4">
                <input id="identifyCount" class="form-control" name="identifyCount">
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-5 text-right">
                <span>签订合同</span>
            </label>
            <div class="col-sm-4">
                <div id="contractFile"></div>
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

    <div class="modal fade" id="modal">
        <div class="modal-dialog" >
            <div class="modal-content" >
                <!--模态框的头部-->
                <div class="modal-header">
                    <!--data-dismiss="modal"该属性用于关闭模态框-->
                    <button class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">不上传合同说明</h4>
                </div>
                <!--模态框的身体-->
                <div class="modal-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-4 text-right">
                                <span><span style="color: red;">* </span>说明</span>
                            </label>
                            <div class="col-sm-6">
                                <textarea class="form-control" rows="5" name="remarks" id="remarks"></textarea>
                            </div>
                        </div>
                    </form>

                </div>
                <!--模态框的脚-->
                <div class="modal-footer">
                    <button class="btn btn-danger" id="save">保存</button>
                    <button class="btn btn-grey" data-dismiss="modal">关闭</button>
                </div>

            </div>
        </div>
    </div>
</div>

<ex-section id="ScriptBody">
    <script type="text/javascript"
            src="${request.contextPath}${urls.getForLookupPath('/js/main/modules/identifyContractList.js')}"></script>
    <script type="text/javascript">
        pageLogic.initData = {
            restUrlPrefix: "/identifies/contract",
            mainId: "${mainId}"
        };

    </script>
</ex-section>
</body>
</html>
