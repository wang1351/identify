// add by xujc 2018/12/31 start 
;(function (window) {

    var pageLogic = {
        init: {
            before: function () {
                showReviewProcess(5);

                // 专家意见相关附件
                $('#opinionFile').uploader({
                    server: masterpage.uploadUrl,
                    doc: true,
                    docConfig: {
                        autoExpand: false,
                        action: 'view',
                        mode: 'outer'
                    }
                });
                initDatePicker({
                    id: "meetingTime",
                    type: "day"
                });
                // 回填参会专家
                var deffered = $.postJSON(masterpage.ctxp + "/reviewMain/getReviewExpert", {
                    reviewId: pageLogic.initData.reviewId,
                    type: 2
                });
                deffered.done(function (data) {
                    var reviewExperts = data.data;
                    var reviewExpertNames = '';
                    $.each(reviewExperts, function (i, reviewExpert) {
                        var arr = reviewExpert.expertMsg.split("、");
                        $.each(arr,function (i,ele) {
                            var split = ele.split('(');
                            reviewExpertNames += split[0] + ",";
                        })

                    });
                    reviewExpertNames = reviewExpertNames.substring(0, reviewExpertNames.length - 1);
                    $("#experts").val(reviewExpertNames);
                })
            },

            layout: function () {

            },
            after: function () {
                $("#opinionForm").validator({
                    rules: {
                        opinion: {required: true, maxLen: 500},
                        meetingTime: {required: true},
                        address: {required: true},
                        deptName: {required: true},
                        experts: {required: true},
                        others: {required: true}
                    },
                    messages: {}
                });

            },
            //页面控件事件绑定(一般为按钮的事件绑定)
            events: function () {

                // 保存按钮添加点击事件
                $("#saveBtn").on("click", function () {
                    // 验证信息
                    if (!validatorInfo()) {
                        return;
                    }

                    $("#saveBtn").attr("disabled", "disabled");
                    common.postJSON({
                        url: "/tasks/review/opinion/save",
                        data: {
                            optionData: $("#opinionForm").serializeObject(),
                            fileIdS: $('#opinionFile').getUploaderFileIds()
                        }
                    }, function () {
                        layer.msg("信息保存成功！");
                        history.back();
                    });
                });

                // 给返回按钮加点击事件
                $("#backBtn").on("click", function () {
                    history.back();
                });
            },

            load: function () {

            }
        }
    };

    var validatorInfo = function () {
        if (!$("#opinionForm").valid()) {
            return false;
        }

        return true;
    }

    pageLogic.formatter = {};

    window.pageLogic = pageLogic;
})(window);
// add by xujc 2018/12/31 end 
