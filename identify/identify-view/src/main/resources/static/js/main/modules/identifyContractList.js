;(function (window) {

    var pageLogic = {
        save: function (data) {
            var url = masterpage.ctxp + pageLogic.initData.restUrlPrefix;
            var deferred = $.postJSON(url, data);
            deferred.done(function (response) {

                if (!response.success) {
                    layer.msg(response.msg);
                    return;
                }
                layer.msg("保存成功");
                goto(masterpage.ctxp + "/tasks", {withBackBtn: false});
            });
        },
        init: {
            before: function () {
                showProcess(5, pageLogic.initData.mainId);

                $("#contractFile").uploader({
                    server: masterpage.uploadUrl,
                    limits: {
                        // num: 1,
                        // exts: ["docx"]
                    },

                    doc: true,
                    docConfig: {
                        autoExpand: false,
                        action: "edit",
                        mode: "outer"
                    }
                });

            },

            after: function () {


            },
            //页面控件事件绑定(一般为按钮的事件绑定)
            events: function () {



                $("#saveBtn").on("click", function () {
                    $("#form").validator({
                        rules: {
                            contractAmount: {required: true, number: true, range: [1, 10000000]},
                            identifyCount: {required: true, digits: true, range: [1, 99]}
                        }
                    });
                    //禁用按钮，防止多次点击多次保存，Ajax请求结束后放开
                    $("#saveBtn").attr("disabled", "disabled");
                    var form = $("#form");
                    var data = form.serializeObject();
                    pageLogic.data = data;
                    data.contractFile = $("#contractFile").getUploaderFileIds();

                    if (!form.valid()) {
                        $("#saveBtn").removeAttr('disabled');
                        return;
                    }
                    if (data.contractFile.length === 0 ) {
                        $("#saveBtn").removeAttr('disabled');
                        $("#modal").modal('show');
                    }else {
                        pageLogic.save(data);
                    }
                });
                $("#save").on("click",function () {
                    $(this).attr("disabled", "disabled");
                   var remarks = $("#remarks").val();
                   if(!remarks || !remarks.trim()){
                       layer.msg("说明为必填项！");
                       $(this).removeAttr("disabled", "disabled");
                       return;
                   }
                  pageLogic.data.remarks=remarks;
                   // 成功了就隐藏模态框
                   $("#modal").modal('hide');
                   pageLogic.save(pageLogic.data);
                });


                // 给返回按钮加点击事件
                $("#backBtn").on("click",function () {
                    goto(masterpage.ctxp + "/tasks", {withBackBtn: false});
                });
            },

            load: function () {
                // 加载详细信息
            }
        }
    };

    pageLogic.formatter = {};

    window.pageLogic = pageLogic;
})(window);
