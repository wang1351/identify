
// add by xujc 2018/12/25 start 

(function (window) {
// update by xujc 2019/1/25 start
    var pageLogic = {
        saveCheck: function (reason,result) {
            common.postJSON({
                url: "/tasks/review/applyCheck/save",
                data: {
                    reviewId: pageLogic.initData.reviewId,
                    reason: reason,
                    result: result
                }
            }, function (resp) {
                    layer.msg('保存成功');
                    $(this).prop('disabled',false);
                    goto(masterpage.ctxp + "/tasks", {withBackBtn: false});

            });
        },
        init: {
            before: function () {
                showReviewProcess(2);
            },

            layout: function() {

        },
        after: function () {


        },
        //页面控件事件绑定(一般为按钮的事件绑定)
        events: function () {
            // 给返回按钮加点击事件
            $("#refuseBtn").on("click",function () {
                $(this).prop('disabled',true);
                var reason = $("#remarks").val();
                if(!reason){
                    layer.msg('不受理原因不能为空！');
                    $(this).prop('disabled',false);
                    return;
                }
                pageLogic.saveCheck(reason,0);
            });
            $("#acceptBtn").on("click", function () {
                var reason = $("#remarks").val();
                pageLogic.saveCheck(reason,1);
            });

        },

        load: function () {

        }
    }
};

pageLogic.formatter = {};

window.pageLogic = pageLogic;
})(window);
// update by xujc 2019/1/25 end
// add by xujc 2018/12/25 end 