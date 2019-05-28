;(function (window) {

var pageLogic = {
    init: {
        before: function () {
            showProcess(3, pageLogic.initData.mainId);

            //时间控件
            initDatePicker({id:"implementTime", type:"day", formatter:"yyyy-mm-dd", endDate: new Date()});

            $("#livePhoto").uploader({
                server: masterpage.uploadUrl,
                limits: {
                    exts: ["png", "jpg", "gif"]
                }
            });
            /**
             * @author : wangwj
             * 移除功能
             */
            //移除险情图片 wangwj
            // $("#dangerPhoto").uploader({
            //     server: masterpage.uploadUrl,
            //     limits: {
            //         exts: ["png", "jpg", "gif"]
            //     }
            // });
            $("#files").uploader({
                server: masterpage.uploadUrl,
                doc:true,
                docConfig: {
                    autoExpand: false,
                    action: 'view',
                    mode: 'outer'
                }
            });

        },

        after: function () {
            $("#previewForm").validator({
                rules: {
                    implementTime: {required: true},
                    opinion: {required: true, maxLen: 500}
                }
            });

        },
        //页面控件事件绑定(一般为按钮的事件绑定)
        events: function () {
            $("#saveBtn").on("click", function () {

                var form = $("#previewForm");
                var data = form.serializeObject();
                data.livePhotos = $("#livePhoto").getUploaderFileIds();
                // data.dangerPhotos = $("#dangerPhoto").getUploaderFileIds();
                data.files = $("#files").getUploaderFileIds();
                if (!form.valid()) {
                    return;
                }
                //禁用按钮，防止多次点击多次保存
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
