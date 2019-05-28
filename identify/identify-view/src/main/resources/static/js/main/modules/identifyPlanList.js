;(function (window) {

    var pageLogic = {
        init: {
            before: function () {
                showProcess(4, pageLogic.initData.mainId);

                $('#planFile').uploader({
                    server: masterpage.uploadUrl,
                    limits: {
                        num: 1,
                        exts: ["docx"]
                    },

                    doc: true,
                    docConfig: {
                        autoExpand: false,
                        action: "edit",
                        mode: "outer"
                    }
                });

                var setinvalId = setInterval(function () {
                    if($('#planFile').find('input').length) {
                        $('#planFile').find('input').removeAttr('multiple');
                        clearInterval(setinvalId);
                    }
                }, 500)
            },

            after: function () {

            },
            //页面控件事件绑定(一般为按钮的事件绑定)
            events: function () {
                $("#saveBtn").on("click", function () {
                    var form = $("#form");
                    var data = form.serializeObject();
                    data.planFile = $("#planFile").getUploaderFileIds();

                    if (data.planFile.length === 0) {
                        layer.msg("请上传方案文件！");
                        return;
                    }

                    //禁用按钮，防止多次点击多次保存，Ajax请求结束后放开
                    $("#saveBtn").attr("disabled", "disabled");
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
