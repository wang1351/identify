// update by xujc 2019/1/5 start
;(function (window) {

    var pageLogic = {
        init: {
            before: function () {
                showProcess(6, pageLogic.initData.mainId);

                var columns = [
                    {field: "id", visible: false},
                    {field: "houseName", title: "房屋名称", width: "15%", align: "center"},
                    {
                        field: "identifyResult",
                        title: "鉴定等级",
                        width: "5%",
                        align: "center",
                        formatter: pageLogic.formatter.identifyResult
                    },
                    {
                        field: "conclusion",
                        title: "鉴定结论",
                        width: "15%",
                        align: "center",
                        formatter: pageLogic.formatter.conclusion
                    },
                    {
                        field: "opinion",
                        title: "处理意见",
                        width: "15%",
                        align: "center",
                        formatter: pageLogic.formatter.opinion
                    },
                    {field: "qrCode", title: "二维码", width: "10%", align: "center", formatter: pageLogic.formatter.qrCode},
                    {field: "report", title: "鉴定报告", width: "20%", align: "center", formatter: pageLogic.formatter.report},
                    {
                        field: "testing",
                        title: "检测报告",
                        width: "20%",
                        align: "center",
                        formatter: pageLogic.formatter.testing
                    }
                ];

                pageLogic.btTable = common.initTable(columns, {
                    pagination: false,
                    onLoadSuccess: function () {
                        
                    }
                });

                //创建模态窗口
                common.modal(pageLogic.initData.modalParams[0], {
                    rules: {
                        proName: {required: true}
                    }

                }, function () {
                    $('#qrCode').qrcode({
                        text: "http://baidu.com",
                        size: 128,
                        ecLevel: 'H',
                        mode: 4,
                        image: $("#qrCodeImg")[0],
                        mSize: 0.3
                    });

                    var canvas = $("#qrCode canvas");
                    var img = pageLogic.convertCanvasToImage(canvas);
                    $('#qrCode img').remove();
                    $("#qrCode").append(img);
                    $("#qrCode canvas").remove();
                });
                // edit 只支持doc
                $("#identifyFile").uploader({
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
                //update by wangwj 20181204 start
                $("#testingFile").uploader({
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
                //update by wangwj 20181204 end
                // add by panqh 2018-12-07 start

                if (pageLogic.initData.content === '8') {
                    $("#identifyResultInput").show();
                    $("#identifyResultSelect").hide();
                } else {
                    $("#identifyResultInput").hide();
                    $("#identifyResultSelect").show();
                }
                // add by panqh 2018-12-07 end
            },

            after: function () {
                var url = masterpage.ctxp + "/identifies/report/mainId/" + pageLogic.initData.mainId;
                var deferred = $.postJSON(url);

                deferred.done(function (response) {
                    if (!response.success) {
                        layer.msg(response.msg);
                        return;
                    }

                    var report = response.data;

                    if (report) {
                        $("#reportId").val(report.id);

                        $("#identifyFile").resetUploader({}, [report["identifyFileId"]]);
                        $("#testingFile").resetUploader({}, [report["testingFileId"]]);
                    }

                });


                // 获取房屋地址
                common.postJSON({
                    url: "/identifies/" + pageLogic.initData.mainId + "/houseAddress"
                }, function (data) {
                    $("#address").text(data.houseAddress);
                });


            },
            //页面控件事件绑定(一般为按钮的事件绑定)
            events: function () {
                $("#saveBtn").on("click", function () {
                    var form = $("#form");
                    var data = form.serializeObject();
                    // 上传报告方式
                    var method = $("input[name=method]:checked").val();

                    // 分栋ID数组
                    var resultData = pageLogic.btTable.resultData;
                    data.splitArray = [];

                    for (var index = 0; index < resultData.length; index++) {
                        var id = resultData[index]["id"];
                        var splitInfo = {};

                        splitInfo.id = id;
                        splitInfo.identifyResult = $("#identifyResult_" + id).val();
                        if (!splitInfo.identifyResult) {
                            layer.msg("请选择鉴定等级！");
                            return;
                        }
                        splitInfo.conclusion = $("#conclusion_" + id).val();
                        if (!splitInfo.conclusion) {
                            layer.msg("请填写鉴定结论！");
                            return;
                        }
                        splitInfo.opinion = $("#opinion_" + id).val();
                        if (!splitInfo.opinion) {
                            layer.msg("请填写处理意见！");
                            return;
                        }


                        // 分栋上传
                        if (method === "1") {

                            splitInfo.proName = !pageLogic.initData[id] ? undefined : pageLogic.initData[id]["proName"];
                            if (!splitInfo.proName) {
                                layer.msg("请生成二维码！");
                                return;
                            }

                            splitInfo.qrCode = pageLogic.initData[id]["qrCode"];

                            splitInfo.identifyFile = $("#report_" + id).getUploaderFileIds();
                            splitInfo.testingFile = $("#testing_" + id).getUploaderFileIds();

                            if (splitInfo.identifyFile.length === 0) {
                                layer.msg("请上传鉴定报告文件！");
                                return;
                            }
                            if (splitInfo.testingFile.length === 0) {
                                layer.msg("请上传检测报告文件！");
                                return;
                            }


                        }

                        data.splitArray.push(splitInfo);
                    }

                    // 汇总上传
                    if (method === "2") {
                        var mainId = pageLogic.initData.mainId;

                        data.proName = !pageLogic.initData[mainId] ? undefined : pageLogic.initData[mainId]["proName"];
                        if (!data.proName) {
                            layer.msg("请生成二维码！");
                            return;
                        }

                        data.qrCode = pageLogic.initData[mainId]["qrCode"];

                        data.identifyFile = $("#identifyFile").getUploaderFileIds();
                        data.testingFile = $("#testingFile").getUploaderFileIds();

                        if (data.identifyFile.length === 0) {
                            layer.msg("请上传鉴定报告文件！");
                            return;
                        }

                        if (data.testingFile.length === 0) {
                            layer.msg("请上传检测报告文件！");
                            return;
                        }
                    }

                    var url = masterpage.ctxp + pageLogic.initData.restUrlPrefix;
                    var deferred = $.postJSON(url, data);

                    //禁用按钮，防止多次点击多次保存
                    $("#saveBtn").attr("disabled", "disabled");

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
                $("#backBtn").on("click", function () {
                    goto(masterpage.ctxp + "/tasks", {withBackBtn: false});
                });


                // 注册表格加载完成事件
                pageLogic.btTable.on('load-success.bs.table', function (e, data) {
                    if (!pageLogic.btTable.resultData) {
                        pageLogic.btTable.resultData = data.result;
                    }



                    $("#form input[name=method]:eq(0)").trigger("click");
                });


                // 注册上传报告方式click事件
                $("#form input[name=method]").on("click", function (e) {
                    var method = $(this).val();
                    // 当前是分批上传
                    if (method === "1") {
                        $("#reportAndTesting").hide();
                        pageLogic.btTable.bootstrapTable('showColumn', "qrCode");
                        pageLogic.btTable.bootstrapTable('showColumn', "report");
                        pageLogic.btTable.bootstrapTable('showColumn', "testing");
                    } else { // 汇总上传
                        $("#reportAndTesting").show();
                        pageLogic.btTable.bootstrapTable('hideColumn', "qrCode");
                        pageLogic.btTable.bootstrapTable('hideColumn', "report");
                        pageLogic.btTable.bootstrapTable('hideColumn', "testing");
                    }

                    pageLogic.initSelectAndUpload(method);

                });


                // 注册二维码按钮点击事件
                var modal = pageLogic.modals[0];
                $("#btTable").on("click", "button[name=qrCode]", function (e) {
                    var modal = pageLogic.modals[0];

                    common.resetForm({
                        modal: modal
                    });

                    // 房屋分栋ID
                    var splitId = this.id.substr(7);

                    common.postJSON({
                        url: pageLogic.initData.restUrlPrefix + "/qrInfo",
                        data: {
                            mainId: pageLogic.initData.mainId
                        }
                    }, function (data) {
                        $("#splitId").val(splitId);
                        $("#caseNo").html(data["caseNo"]);
                        $("#identifyType").html(data["identifyType"]);
                        $("#reportDate").html(data["reportDate"]);
                        $("#clientName").html(data["clientName"]);
                        $("#identifyOrgName").html(data["identifyOrgName"]);



                    });

                    var splitInfo = pageLogic.initData[splitId];
                    if (splitInfo) {
                        $("#proName").val(splitInfo.proName);
                    }

                    modal.show();
                    e.preventDefault();
                });

                $("#summaryQrCode").on("click", function (e) {
                    var modal = pageLogic.modals[0];

                    common.resetForm({
                        modal: modal
                    });

                    common.postJSON({
                        url: pageLogic.initData.restUrlPrefix + "/qrInfo",
                        data: {
                            mainId: pageLogic.initData.mainId
                        }
                    }, function (data) {
                        $("#caseNo").html(data["caseNo"]);
                        $("#identifyType").html(data["identifyType"]);
                        $("#reportDate").html(data["reportDate"]);
                        $("#clientName").html(data["clientName"]);
                        $("#identifyOrgName").html(data["identifyOrgName"]);
                    });

                    var houseInfo = pageLogic.initData[pageLogic.initData.mainId];
                    if (houseInfo) {
                        $("#proName").val(houseInfo.proName);
                    }

                    modal.show();
                    e.preventDefault();
                });

                //注册弹出层保存按钮点击事件
                modal.btn[0].on("click", function () {
                    if (!modal.form.valid()) {
                        return;
                    }

                    // 上传报告方式
                    var method = $("input[name=method]:checked").val();

                    // 汇总上传
                    if (method === "2") {
                        var mainId = pageLogic.initData.mainId;

                        if (!pageLogic.initData[mainId]) {
                            pageLogic.initData[mainId] = {};
                        }

                        var houseInfo = pageLogic.initData[mainId];
                        houseInfo["proName"] = $("#proName").val();
                        houseInfo["qrCode"] = $("#qrCode img").attr("src");

                        $("#summaryQrCode").html("<i class='fa fa-qrcode'></i> 重新生成");
                    } else {
                        var splitId = $("#splitId").val();

                        if (!pageLogic.initData[splitId]) {
                            pageLogic.initData[splitId] = {};
                        }
                        var splitInfo = pageLogic.initData[splitId];
                        splitInfo["proName"] = $("#proName").val();
                        splitInfo["qrCode"] = $("#qrCode img").attr("src");

                        $("#qrCode_" + splitId).html("<i class='fa fa-qrcode'></i> 重新生成");
                    }

                    modal.hide();
                });

                //注册modal关闭时事件
                modal.on("hide.bs.modal", function (event) {
                    if ($(event.target).is("div.modal")) {
                        common.resetForm({
                            modal: modal
                        });
                    }
                });

            },

            load: function () {
                common.search();
            }
        }
    };
    // update by xujc 2019/4/12 start
    pageLogic.formatter = {
        identifyResult: function (val, row) {
            var $select = $("<select></select>");
            $select.attr("id", "identifyResult_" + row["id"]);
            return $select.prop("outerHTML");
        },

        conclusion: function (val, row, index) {
            var $textarea = $("<textarea placeholder='请输入鉴定结论'></textarea>");
            $textarea.attr("id", "conclusion_" + row["id"]);
            return $textarea.prop("outerHTML");
        },

        opinion: function (val, row, index) {
            var $textarea = $("<select></select>");
            $textarea.attr("id", "opinion_" + row["id"]);

            return $textarea.prop("outerHTML");
        },

        report: function (val, row, index) {
            var $div = $("<div></div>");
            $div.attr("id", "report_" + row["id"]);

            return $div.prop("outerHTML");
        },

        testing: function (val, row, index) {
            var $div = $("<div></div>");
            $div.attr("id", "testing_" + row["id"]);

            return $div.prop("outerHTML");
        },

        qrCode: function (val, row, index) {
            var txt = "生成";
            if (pageLogic.initData[row["id"]]) {
                txt = "重新生成";
            }

            var $btn = $("<button name='qrCode' class='btn btn-sm btn-blue'><i class='fa fa-qrcode'></i> " + txt + "</button>");
            $btn.attr("id", "qrCode_" + row["id"]);

            return $btn.prop("outerHTML");
        }
    };
    // update by xujc 2019/4/12 end
    /* 初始化鉴定等级和上传组件 */
    pageLogic.initSelectAndUpload = function (method) {
        var result = pageLogic.btTable.resultData;

        if (!result) return;

        for (var index = 0; index < result.length; index++) {

            common.dictSelect({
                id: "identifyResult_" + result[index]["id"],
                key: "IDENTIFY_RESULT"
            });
            common.dictSelect({
                id: "opinion_" + result[index]["id"],
                key: "IDENTIFY_OPINION"
            });
            if (method === "1") {
                var reportId = "report_" + result[index]["id"];

                $("#" + reportId).uploader({
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

                var testingId = "testing_" + result[index]["id"];

                $("#" + testingId).uploader({
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
            }

        }
    };


    pageLogic.convertCanvasToImage = function (canvas) {
        //新Image对象，可以理解为DOM
        var image = new Image();
        // 指定格式PNG
        image.src = canvas[0].toDataURL("image/png");
        return image;
    };

    pageLogic.downLoadCode = function () {
        var alink = document.createElement("a");
        alink.href = $("#qrCode img").attr("src");
        alink.download = "二维码.jpg";
        alink.click();
    };

    window.pageLogic = pageLogic;
})(window);
// update by xujc 2019/1/5 end