// add by xujc 2018/12/24 start 
;(function (window) {

var pageLogic = {
    init: {
        before: function () {
            showCorrectProcess(3);

            // 专家意见相关附件
            $('#correctFile').uploader({
                server: masterpage.uploadUrl,
                doc:true,
                docConfig: {
                    autoExpand: false,
                    action: 'view',
                    mode: 'outer'
                }
            });
        },

        layout: function() {

        },
        after: function () {

        },
        //页面控件事件绑定(一般为按钮的事件绑定)
        events: function () {

            // 给审核通过按钮加点击事件
            $("#saveBtn").on("click",function () {

                if ($('#correctFile').getUploaderFileIds().length <= 0) {
                    layer.msg("补正资料不能为空！");
                    return;
                }

                $("#saveBtn").attr("disabled", "disabled");
                common.postJSON({
                    url: "/corrects/upload/save",
                    data: {
                        correctId: pageLogic.initData.correctId,
                        description: $('#description').val(),
                        fileIdS: $('#correctFile').getUploaderFileIds()
                    }
                }, function () {
                    layer.msg("信息保存成功！");
                    goto(masterpage.ctxp + "/tasks?tab=correct", {withBackBtn: false});
                });
            });

            // 给返回按钮加点击事件
            $("#backBtn").on("click",function () {
                goto(masterpage.ctxp + "/tasks?tab=correct", {withBackBtn: false});
            });
        },

        load: function () {

        }
    }
};

pageLogic.formatter = {};

window.pageLogic = pageLogic;
})(window);
// add by xujc 2018/12/24 end 
