// add by xujc 2019/1/18 start
;(function (window) {

var pageLogic = {
    init: {
        before: function () {
            showCorrectProcess(2);
        },

        layout: function() {

        },
        after: function () {

        },
        //页面控件事件绑定(一般为按钮的事件绑定)
        events: function () {

            // 给审核通过按钮加点击事件
            $("#passBtn").on("click",function () {
                $("#passBtn").attr("disabled", "disabled");
                common.postJSON({
                    url: "/corrects/check/save",
                    data: {
                        correctId: pageLogic.initData.correctId,
                        checkResult: "1",
                        checkOpinion: $("#checkOpinion").val()
                    }
                }, function () {
                    layer.msg("信息审核成功！");
                    goto(masterpage.ctxp + "/tasks?tab=correct", {withBackBtn: false});
                });
            });

            // 给审核不通过按钮加点击事件
            $("#failBtn").on("click",function () {
                if ($("#checkOpinion").val() == "" || $("#checkOpinion").val() == null) {
                    layer.msg("审核不通过时，请填写审核意见！");
                    return;
                }
                $("#failBtn").attr("disabled", "disabled");
                common.postJSON({
                    url: "/corrects/check/save",
                    data: {
                        correctId: pageLogic.initData.correctId,
                        checkResult: "2",
                        checkOpinion: $("#checkOpinion").val()
                    }
                }, function () {
                    layer.msg("信息审核成功！");
                    goto(masterpage.ctxp + "/tasks?tab=correct", {withBackBtn: false});
                });
            });
        },

        load: function () {

        }
    }
};

pageLogic.formatter = {};

window.pageLogic = pageLogic;
})(window);
// add by xujc 2019/1/18 end
