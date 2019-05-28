// add by xujc 2018/11/27 start
;(function (window) {

    var pageLogic = {
        init: {
            before: function () {
                showProcess(2, pageLogic.initData.mainId);

                // 获取负责人员
                var deffered = $.postJSON(masterpage.ctxp + pageLogic.initData.restUrlPrefix + "/getUsers");
                deffered.done(function (data) {
                    $("#preview").empty();
                    $.each(data.data, function (i, ele) {
                        $("#preview").append("<li class='radio'><label><input type='radio' name='chargeId' value='" + ele.id + "'>" + ele.fullName + "</label></li>");
                    });
                });

                // 获取参与人员
                var deffer = $.postJSON(masterpage.ctxp + pageLogic.initData.restUrlPrefix + "/getOrgUsers");
                deffer.done(function (data) {
                    $("#orgUsers").empty();
                    $.each(data.data, function (i, ele) {
                        $("#orgUsers").append("<li class='checkbox'><label><input type='checkbox' name='joinUserId' value='" + ele.id + "'>" + ele.fullName + "</label></li>");
                    });
                });


            },

            layout: function () {

            },
            after: function () {
                initDatePicker({id: "requireDate", type: "day", formatter: "yyyy-mm-dd"});
            },
            //页面控件事件绑定(一般为按钮的事件绑定)
            events: function () {

                // 给不予受理加点击事件
                $(".unAccept").on("click", function (event) {
                    $("#form").addClass("textHide");
                    $("#unform").removeClass("textHide");
                    $(".unAccept").prop("checked", true);
                    // 将是否受理的值绑定到保存按钮的name属性
                    $("#saveBtn").attr("name", $(event.target).val());
                });
                // 给受理加点击事件
                $(".accept").on("click", function (event) {
                    $("#form").removeClass("textHide");
                    $("#unform").addClass("textHide");
                    $(".accept").prop("checked", true);
                    $("#saveBtn").attr("name", $(event.target).val());
                });
                // 给返回按钮加点击事件
                $("#backBtn").on("click", function () {
                    goto(masterpage.ctxp + "/tasks", {withBackBtn: false});
                });
                $("#saveBtn").on("click", function (event) {
                    $("#saveBtn").prop("disabled", true);
                    // 获取是否受理（ 1受理，0不受理）
                    var accept = $(event.target).attr("name");
                    // 没选不给保存
                    if (!accept) {
                        layer.msg("请选择是否受理！");
                        $("#saveBtn").prop("disabled", false);
                        return;
                    }

                    var data;

                    // 受理
                    if (accept === '1') {
                        // 获取表单保存的数据
                        data = $("#form").serializeObject();
                        if(!data.chargeId){
                            layer.msg("负责人员为必填项！");
                            $("#saveBtn").prop("disabled", false);
                            return;
                        }
                        if(!data.requireDate){
                            layer.msg("要求完成时间为必填项！");
                            $("#saveBtn").prop("disabled", false);
                            return;
                        }
                        var joinUserId =data.joinUserId;

                        if(typeof joinUserId =="string"){
                            data.joinUserId =[joinUserId];
                        }
                    } else {
                        data = $("#unform").serializeObject();
                        var reason =data.remarks;
                        if(!reason || !reason.trim()){
                            layer.msg("不受理原因为必填项！");
                            $("#saveBtn").prop("disabled", false);
                            return;
                        }
                    }

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

            },

            load: function () {
            }
        }
    };

    window.pageLogic = pageLogic;
})(window);
// add by xujc 2018/11/27 end