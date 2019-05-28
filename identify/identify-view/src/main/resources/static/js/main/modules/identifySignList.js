;(function (window) {
    //    update by wangwj 20181211 start
    // update by xujc 2019/1/4 start
    var pageLogic = {
        
        initFile: function (reportStr,testingStr) {
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
                    exts: ["docx","pdf","jpg"]
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
                showProcess(8, pageLogic.initData.mainId);

            },

            after: function () {
                var url = masterpage.ctxp +  "/identifies/report/mainId/" + pageLogic.initData.mainId;
                var deferred = $.postJSON(url);

                deferred.done(function (response) {
                    if (!response.success) {
                        layer.msg(response.msg);
                        return;
                    }

                    var report = response.data;
                    $("#reportId").val(report.id);

                    $("#method").text(report.method == 1 ? '分栋上传' : '汇总上传');
                    // 表格内容
                    var str ='';
                    // 汇总上传
                    if(report.method == 2) {
                        $("#reportAndTesting").show();
                        pageLogic.initFile("#identifyFile","#testingFile");
                        $("#identifyFile").resetUploader({model: 'uploader'},[report.identifyFileId]);
                        $("#testingFile").resetUploader({model: 'uploader'},[report.testingFileId]);
                        $("#identifyFile").find(".btns").hide();
                        $("#testingFile").find(".btns").hide();

                        str += '<thead ><th>房屋名称</th><th>鉴定等级</th><th>鉴定结论</th><th>处理意见</th></thead>';
                        $.each(report.reportDetailList,function (i,ele) {
                            str += '<tr><td>' +ele.houseName + '</td><td>' +ele.identifyResultName + '</td><td>' +ele.conclusion + '</td><td>' +ele.opinion + '</td></tr>'
                        });
                        $("#btTable").append(str);

                    } else {
                        $("#reportAndTesting").hide();
                        str += '<thead style="text-align: center"><th>房屋名称</th><th>鉴定等级</th><th>鉴定结论</th><th>处理意见</th><th>鉴定报告</th><th>检测报告</th></thead>';
                        var identifyFileIds = [];
                        var testingFileIds = [];
                        $.each(report.reportDetailList,function (i,ele) {
                            str += '<tr><td>' +ele.houseName + '</td><td>' +ele.identifyResultName + '</td><td>' +ele.conclusion + '</td><td>' +ele.opinion + '</td><td><div id="identifyFile'+i+'"></div></td><td><div id="testingFile'+i+'"></div></td></tr>';
                            identifyFileIds.push(ele.identifyFileId);
                            testingFileIds.push(ele.testingFileId);
                        });
                        $("#btTable").append(str);
                        for ( var j = 0;j<identifyFileIds.length;j++ ){

                            pageLogic.initFile("#identifyFile"+j,"#testingFile" +j);
                            $("#identifyFile"+j).resetUploader({model: 'uploader'},[identifyFileIds[j]]);
                            $("#testingFile" +j).resetUploader({model: 'uploader'},[testingFileIds[j]]);
                            // 隐藏 上传btn
                            $("#identifyFile"+j).find(".btns").hide();
                            // 隐藏 上传btn
                            $("#testingFile" +j).find(".btns").hide();
                        };

                    }

                });
            },
            //    update by wangwj 20181211 end
            //upadate by wangwj 20181204 start
            //页面控件事件绑定(一般为按钮的事件绑定)
            events: function () {
                $("#saveBtn").on("click", function () {
                    layer.confirm("签发报告后，将不能进行任何修改，确定要签发吗？", {btn: ["确定签发", "暂不签发"]}, function () {
                        $("a:contains('确定签发')").off('click');
                        var form = $("#form");
                        var data = form.serializeObject();
                        var url = masterpage.ctxp + pageLogic.initData.restUrlPrefix;
                        var deferred = $.postJSON(url, data);
                        deferred.done(function (response) {
                            if (!response.success) {
                                layer.msg(response.msg);
                                return;
                            }

                            layer.msg("签发成功！", {icon: 1, time: 1000}, function () {
                                goto(masterpage.ctxp + "/tasks", {withBackBtn: false});
                            });
                        });
                    });
                });
                //upadate by wangwj 20181204 end
                // 给返回按钮加点击事件
                $("#backBtn").on("click",function () {
                    goto(masterpage.ctxp + "/tasks", {withBackBtn: false});
                });





            },

            load: function () {
            }
        }
    };

    pageLogic.formatter = {};

    window.pageLogic = pageLogic;
})(window);
// update by xujc 2019/1/4 end
