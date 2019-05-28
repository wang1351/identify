// add by xujc 2019/1/3 start 
;(function (window) {
    // update by xujc 2019/2/21 start
    var cache = {};
    var pageLogic = {
        contentMethod: function () {
            var str =_.template($('#tpl_reportDetails').html())({});
            return str;
        },
        // update by xujc 2019/2/21 end
        initFile: function (reportStr, testingStr) {
            var report = $(reportStr).uploader({
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

            $(testingStr).uploader({
                server: masterpage.uploadUrl,
                limits: {
                    num: 1,
                    exts: ["docx", "pdf", "jpg"]
                },

                doc: true,
                docConfig: {
                    autoExpand: false,
                    action: "edit",
                    mode: "outer"
                }
            });
        },
        init: {
            before: function () {
                showProcess(7, pageLogic.initData.mainId);

            },

            after: function () {



                var deffer = $.postJSON(masterpage.ctxp + "/identifies/getMain/" + pageLogic.initData.mainId, {});
                deffer.done(function (data) {
                    var identifyMain = data.data;
                    // 审核不通过用
                    pageLogic.identifyMain = identifyMain;
                });
                var url = masterpage.ctxp + "/identifies/report/mainId/" + pageLogic.initData.mainId;
                var deferred = $.postJSON(url);

                deferred.done(function (response) {
                    if (!response.success) {
                        layer.msg(response.msg);
                        return;
                    }
                    var report = response.data;
                    pageLogic.report = report;
                    $("#reportId").val(report.id);
                    $("#method").text(report.method == 1 ? '分栋上传' : '汇总上传');
                    // 表格内容
                    var str = '';
                    // 汇总上传
                    if (report.method == 2) {
                        $("#reportAndTesting").show();
                        pageLogic.initFile("#identifyFile", "#testingFile");
                        $("#identifyFile").resetUploader({model: 'uploader'}, [report.identifyFileId]);
                        $("#testingFile").resetUploader({model: 'uploader'}, [report.testingFileId]);
                        $("#identifyFile").find(".btns").hide();
                        $("#testingFile").find(".btns").hide();
                        str += '<thead ><th>房屋名称</th><th>鉴定等级</th><th>鉴定结论</th><th>处理意见</th></thead>';
                        $.each(report.reportDetailList, function (i, ele) {
                            str += '<tr><td>' + ele.houseName + '</td><td>' + ele.identifyResultName + '</td><td>' + ele.conclusion + '</td><td>' + ele.opinion + '</td></tr>'
                        });
                        $("#btTable").append(str);

                    } else {
                        $("#reportAndTesting").hide();
                        str += '<thead style="text-align: center"><th>房屋名称</th><th>鉴定等级</th><th>鉴定结论</th><th>处理意见</th><th>鉴定报告</th><th>检测报告</th></thead>';
                        var identifyFileIds = [];
                        var testingFileIds = [];
                        $.each(report.reportDetailList, function (i, ele) {
                            str += '<tr><td>' + ele.houseName + '</td><td>' + ele.identifyResultName + '</td><td>' + ele.conclusion + '</td><td>' + ele.opinion + '</td><td><div id="identifyFile' + i + '"></div></td><td><div id="testingFile' + i + '"></div></td></tr>';
                            identifyFileIds.push(ele.identifyFileId);
                            testingFileIds.push(ele.testingFileId);
                        });
                        $("#btTable").append(str);
                        for (var j = 0; j < identifyFileIds.length; j++) {
                            pageLogic.initFile("#identifyFile" + j, "#testingFile" + j);

                            $("#identifyFile" + j).resetUploader({model: 'uploader'}, [identifyFileIds[j]]);
                            // 隐藏 上传btn
                            $("#identifyFile" + j).find(".btns").hide();
                            $("#testingFile" + j).resetUploader({model: 'uploader'}, [testingFileIds[j]]);
                            $("#testingFile" + j).find(".btns").hide();

                        }

                    }
                });
            },
            //页面控件事件绑定(一般为按钮的事件绑定)
            events: function () {
                $("#acceptBtn").on("click", function () {
                    var form = $("#form");
                    var data = form.serializeObject();
                    // 审核通过
                    data.result = 1;

                    var url = masterpage.ctxp + pageLogic.initData.restUrlPrefix;
                    var deferred = $.postJSON(url, data);

                    //禁用按钮，防止多次点击多次保存
                    $("#acceptBtn").attr("disabled", "disabled");

                    deferred.done(function (response) {
                        if (!response.success) {
                            layer.msg(response.msg);
                            return;
                        }

                        layer.msg("保存成功");
                        goto(masterpage.ctxp + "/tasks", {withBackBtn: false});
                    });

                });

            // update by xujc 2019/2/21 start
                // 审核不通过
                $("#refuseBtn").on("click", function () {

                    if (!cache[mainId]) {
                        $("#refuseBtn").popover({
                            trigger: 'click',
                            //template: '',
                            placement: 'bottom', //top, bottom, left or right
                            title:pageLogic.identifyMain.house.zone + pageLogic.identifyMain.house.street + pageLogic.identifyMain.house.address,
                            html: 'true',
                            content: pageLogic.contentMethod()
                        });
                        cache[mainId] = true;
                        $("#refuseBtn").click();
                    }
                });
                $("#button_area").on("click",".splitHouse", function () {
                    $obj =$(this).parent().next().next().find('textarea');
                    $obj.prop('readonly',!$obj.prop('readonly'));
                });
                //注册弹出层提交按钮点击事件
                $("#button_area").on("click",".saveBtn", function () {

                    $(this).prop("disabled", true);
                    var form = $("#form");
                    var data = form.serializeObject();

                    // 审核不通过
                    data.result = 0;
                    if($("#reason").val() && $("#reason").val().trim()){
                        data.reason = $("#reason").val();
                    }else {
                        layer.msg('不通过原因必填！');
                        $(this).prop("disabled", false);
                        return;
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

            // update by xujc 2019/2/21 end

            },

            load: function () {
            }
        }
    };

    pageLogic.formatter = {};

    window.pageLogic = pageLogic;
})(window);
// add by xujc 2019/1/3 end 
