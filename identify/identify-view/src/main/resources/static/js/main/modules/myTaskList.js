;(function (window) {

    var pageLogic = {
        /**
         * @author : xujc
         * @date :2019/3/6
         * @Description : 获取url上的参数
         */
        getUrlParams: function () {
            var url = location.search;
            var theParam = new Object();
            if (url.indexOf("?") != -1) { //确保‘？’不是最后一个字符串，即携带的参数不为空
                var str = url.substr(1); //substr是字符串的用法之一，抽取指定字符的数目，这里抽取？后的所有字符
                var strs = str.split("&"); //将获取到的字符串从&分割，输出参数数组，即输出[参数1=xx,参数2=xx,参数3=xx,...]的数组形式
                for(var i = 0; i < strs.length; i ++) { //遍历参数数组
                    theParam[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]); //这里意思是抽取每个参数等号后面的值，unescape是解码的意思
                }
            }
            return theParam;
        },
        init: {
            before: function () {

                var columns = [
                    {field: "checked", checkbox: true},
                    {field: "num", title: "序号", width: 5, align: "center", formatter: common.formatter.index},
                    {field: "caseNo", title: "编号", width: 40, align: "left", formatter: pageLogic.formatter.formatCaseNo1},
                    {field: "zone", visible: false},
                    {field: "street", visible: false},
                    {field: "orgName", title: "鉴定单位", width: 120, align: "center"},
                    {field: "address", title: "房屋地址", width: 150, align: "left", formatter: pageLogic.formatter.address},
                    {field: "operatorTime", title: "操作时间", width: 100, align: "center", formatter: pageLogic.formatter.applyTime},
                    {field: "method", title: "申请方式", width: 50, align: "center", formatter: pageLogic.formatter.applyMethod},
                    {field: "status", title: "当前状态", width: 50, align: "center", formatter: pageLogic.formatter.status},
                    {field: "id", title: "操作", width: 100, align: "center", formatter: pageLogic.formatter.operate}
                ];

                // 创建鉴定业务-代办任务列表
                pageLogic.btTable = common.initTable(columns);

                common.dictSelects([
                    // 鉴定列表的鉴定内容
                    {id: "identifyContent", key: "IDENTIFY_TYPE"},
                    // 复核列表的鉴定内容
                    {id: "reviewContent", key: "IDENTIFY_TYPE"},
                    // 补正变更的鉴定内容
                    {id: "correctContent", key: "IDENTIFY_TYPE"}
                ]);

            },

            layout: function () {
                common.layout();
            },
            after: function () {
                //创建模态窗口(填写流程终止原因)
                common.modal(pageLogic.initData.modalParams[0], {
                    rules: {
                        reason: {required: true, maxLen: 500}
                    }
                });
            },
            //页面控件事件绑定(一般为按钮的事件绑定)
            // update by xujc 2019/3/18 start
            events: function () {
                // 注册查询按钮点击事件
                $("#searchBtn").on("click", function () {
                    common.search();


                    var message ="<div style='margin: 15px 10px 0' ><span class='fu small' style='background-color:red; color: white;line-height:18px;padding:0px 3px;margin-left:4px'>超</span> 表示待办事项即将超期</div>";
                    $("#btTable").after(message);
                    pageLogic.showCounts();
                });
                // update by xujc 2019/3/18 end
                // 注册鉴定申请按钮点击事件
                $("#addBtn").on("click", function () {
                    goto(masterpage.ctxp + "/tasks/identifyRequest", {withBackBtn: false});
                });

                /******************************* 复核业务事件 *************************/
                // 注册复核业务TAB页点击事件
                $("#reviewList").on("click", function () {

                    var reviewColumns = [
                        {field: "num", title: "序号", width: 5, align: "center", formatter: common.formatter.index},
                        {field: "caseNo", title: "编号", width: 40, align: "center", formatter: pageLogic.formatter.formatCaseNo},
                        {field: "zone", visible: false},
                        {field: "street", visible: false},
                        {field: "orgName", title: "鉴定单位", width: 120, align: "center"},
                        {field: "address", title: "房屋地址", width: 150, align: "center", formatter: pageLogic.formatter.address},
                        {field: "operatorTime", title: "操作时间", width: 100, align: "center", formatter: pageLogic.formatter.applyTime},
                        {field: "status", title: "当前状态", width: 50, align: "center", formatter: pageLogic.formatter.reviewStatus},
                        {field: "id", title: "操作", width: 100, align: "center", formatter: pageLogic.formatter.reviewOperate}
                    ];

                    pageLogic.reviewTable = common.initTable(reviewColumns, {
                        id: "reviewTable"
                    });

                    refreshReviewTable();
                });

                // 注册查询按钮点击事件
                $("#reviewSearchBtn").on("click", function () {
                    refreshReviewTable();
                });

                // 注册复核业务申请点击事件
                $("#reviewAddBtn").on("click", function () {
                    goto(masterpage.ctxp + "/tasks/review/reviewRequest", {withBackBtn: false});
                });

                /******************************* 补正变更业务事件 *************************/
                // 注册补正变更业务TAB页点击事件
                $("#correctList").on("click", function () {
                    var correctColumns = [
                        {field: "num", title: "序号", width: 5, align: "center", formatter: common.formatter.index},
                        {field: "caseNo", title: "编号", width: 40, align: "center", formatter: pageLogic.formatter.formatCaseNo},
                        {field: "zone", visible: false},
                        {field: "street", visible: false},
                        {field: "orgName", title: "鉴定单位", width: 120, align: "center"},
                        {field: "address", title: "房屋地址", width: 150, align: "center", formatter: pageLogic.formatter.address},
                        {field: "operatorTime", title: "操作时间", width: 100, align: "center", formatter: pageLogic.formatter.applyTime},
                        {field: "status", title: "当前状态", width: 50, align: "center", formatter: pageLogic.formatter.correctStatus},
                        {field: "id", title: "操作", width: 100, align: "center", formatter: pageLogic.formatter.correctOperate}
                    ];

                    pageLogic.correctTable = common.initTable(correctColumns, {
                        id: "correctTable"
                    });

                    refreshCorrectTable();
                });

                // 注册查询按钮点击事件
                $("#correctSearchBtn").on("click", function () {
                    refreshCorrectTable();
                });

                /******************************* 手机申请业务事件 *************************/
                // 注册补正变更业务TAB页点击事件
                $("#phoneList").on("click", function () {
                    initDatePicker({
                        id: "startTimeSearch",
                        type: "day",
                        formatter: "yyyy-mm-dd"
                    });
                    initDatePicker({
                        id: "endTimeSearch",
                        type: "day",
                        formatter: "yyyy-mm-dd"
                    });

                    var phoneColumns = [
                        {field: "num", title: "序号", width: 5, align: "center", formatter: common.formatter.index},
                        {field: "identifyCode", title: "编号", width: 40, align: "center"},
                        {field: "identifySource", title: "来源", width: 120, align: "center",formatter: pageLogic.formatter.formatIdentifySource},
                        {field: "createUsername", title: "申请人", width: 50, align: "center"},
                        {field: "identifyContacts", title: "联系人", width: 100, align: "center"},
                        {field: "identifyContactsTel", title: "联系电话", width: 50, align: "center"},
                        {field: "createTime", title: "申请时间", width: 100, align: "center"},
                        {field: "id", title: "操作", width: 150, align: "center", formatter: pageLogic.formatter.phoneOperate}
                    ];

                    pageLogic.phoneTable = common.initTable(phoneColumns, {
                        id: "phoneTable"
                    });

                    refreshPhoneTable();
                });

                // 注册查询按钮点击事件
                $("#phoneSearchBtn").on("click", function () {
                    refreshPhoneTable();
                });
                $("#saveBtn").on('click',function () {
                    $("#phoneDetailModal").modal('hide');
                    var id = $(this).attr("name");
                    goto(masterpage.ctxp + "/tasks/identifyRequest?id="+id, {withBackBtn: false});
                })

                // 注册补正业务申请点击事件
                $("#correctAddBtn").on("click", function () {
                    goto(masterpage.ctxp + "/corrects/correctRequest", {withBackBtn: false});
                });

                // 编号点击事件
                $("#reviewTable").on("click", ".caseNo", function (event) {
                    var mainId = $(event.target).attr("name");

                    goto(masterpage.ctxp + "/identifies/detail?mainId=" + mainId + "&type=1&from=1", {withBackBtn: false});
                });
                $("#btTable").on("click", ".caseNo", function (event) {
                    var mainId = $(event.target).attr("name");

                    goto(masterpage.ctxp + "/identifies/detail?mainId=" + mainId + "&type=1", {withBackBtn: false});
                });

                $("#correctTable").on("click", ".caseNo", function (event) {
                    var mainId = $(event.target).attr("name");
                    goto(masterpage.ctxp + "/identifies/detail?mainId=" + mainId + "&type=1&from=2", {withBackBtn: false});
                });

                //编号点击事件
                $("#correctTable").on("click", ".caseNo1", function (event) {
                    var correctId = $(event.target).attr("name");
                    var deffer = $.getJSON(masterpage.ctxp + "/corrects/" + correctId);
                    deffer.done(function (data) {
                        var mainId = data.data.mainId;
                        goto(masterpage.ctxp + "/identifies/detail?mainId=" + mainId + "&type=1", {withBackBtn: false});
                    })


                });

                // 挂起或终止流程事件
                $("#suspendProcess").on("click", function (e) {
                    var selects = pageLogic.btTable.bootstrapTable('getSelections');
                    if (selects.length === 0) {
                        layer.msg("需要先选中一个");
                        return;
                    }

                    if (selects.length > 1) {
                        layer.msg("只能选中一个");
                        return;
                    }

                    var selectedRow = selects[0];
                    pageLogic.stopOrSuspendProcess(selectedRow["id"], "suspend");

                });
                $("#stopProcess").on("click", function (e) {
                    var selects = pageLogic.btTable.bootstrapTable('getSelections');
                    if (selects.length === 0) {
                        layer.msg("需要先选中一个");
                        return;
                    }

                    if (selects.length > 1) {
                        layer.msg("只能选中一个");
                        return;
                    }

                    var selectedRow = selects[0];
                    pageLogic.stopOrSuspendProcess(selectedRow["id"], "stop");
                });

            },

            load: function () {
                // 是否需要点击tab
               var param = pageLogic.getUrlParams();
               if(param.tab =='correct'){
                   $("#correctList").click();
               }else if(param.tab =='phone'){
                   $("#phoneList").click();
               }

                if ($("#identifyList").length > 0) {
                    common.search();
                    // update by xujc 2019/1/31 start
                    var message ="<div style='margin: 15px 10px 0' ><span class='fu small' style='background-color:red; color: white;line-height:18px;padding:0px 3px;margin-left:4px'>超</span> 表示待办事项即将超期</div>";
                    $("#btTable").after(message);
                    // update by xujc 2019/1/31 end
                } else if ($("#reviewList").length > 0) {
                    $("#reviewList").trigger("click");
                } else if ($("#correctList").length > 0) {
                    $("#correctList").trigger("click");
                }
                pageLogic.showCounts();
            }
        },

        // 显示为处理任务数量
        showCounts: function () {
            //add by wangwj 20181212 start
            //显示鉴定业务数量
            $.postJSON(masterpage.ctxp + "/tasks/export/data")
            .done(function (data) {
                var identifyMainList = data.data;
                if (identifyMainList.length) {
                    $("#dDing").show().text(identifyMainList.length);
                }
            });

            //显示复核业务数量
            $.postJSON(masterpage.ctxp + "/tasks/review/search/data")
            .done(function (data) {
                var reviewMainController = data.data;
                if (reviewMainController.length) {
                    $("#fSheng").show().text(reviewMainController.length);
                }
            });

            //显示补正业务数量
            $.postJSON(masterpage.ctxp + "/corrects/search/data")
            .done(function (data) {
                var correctMainController = data.data;
                if (correctMainController.length) {
                    $("#bZheng").show().text(correctMainController.length);
                }
            });
            //add by wangwj 20181212 end

            // add by xujc 2019/5/14 start
            //显示手机申请数量
            $.postJSON(masterpage.ctxp + "/phone/search/data")
                .done(function (data) {
                    var lists = data.data;
                    if (lists.length) {
                        $("#pApply").show().text(lists.length);
                    }
                });

            // add by xujc 2019/5/14 end
        }
    };

    pageLogic.formatter = {
        // 复核和补正的
        formatCaseNo: function (cellValue, row) {
            return "<a style='color: blue' class='caseNo' name='" + row.mainId + "'>" + cellValue + "</a>"
        },
        // 鉴定编号
        formatCaseNo1: function (cellValue, row, index) {
            if (row["status"] === 2) {
                common.postJSON({
                    url: pageLogic.initData.restUrlPrefix + "/" + row.id + "/overdue"
                }, function (data) {
                    if (data === 1) {
                        var reviewPic = "<span class='fu small' style='background-color:red; color: white;line-height:18px;padding:0px 3px;float:right;margin-left:4px'>超</span>";
                        pageLogic.btTable.find("tbody tr:eq(" + index + ") a[name=" + row.id + "]").parent().empty().html(reviewPic + "<a style='color: blue' class='caseNo' name='" + row.id + "'>" + cellValue + "</a>");
                    }
                });
            }

            return  "<a style='color: blue' class='caseNo' name='" + row.id + "'>" + cellValue + "</a>"
        },
        address: function (value, row, index) {
            var zone = row["zone"];
            var street = row["street"];
            if (zone && street) {
                return zone + street + value;
            } else {
                return value;
            }
        },
        status: function (value, row, index) {
            if (value === 1) {
                return "已申请";
            } else if (value === 2) {
                return "已受理";
            } else if (value === 3) {
                return "已初勘";
            } else if (value === 4) {
                return "已出具方案";
            } else if (value === 5) {
                return "已签订合同";
            } else if (value === 6) {
                return "已编制报告";
            } else if (value === 7) {
                return "已审核";
            } else if (value === 8) {
                return "已签发";
            } else if (value === 9) {
                return "已发放";
            } else {
                return "-";
            }
        },
        reviewStatus: function (value, row, index) {
            if (value === 1) {
                return "已申请";
            } else if (value === 2) {
                return "已受理";
            } else if (value === 3) {
                return "已选择专家";
            } else if (value === 4) {
                return "已确认专家";
            }else if (value === 5) {
                return "已填写意见";
            }else if (value === 6) {
                return "已办结";
            } else {
                return "-";
            }
        },
        correctStatus: function (value, row, index) {
            if (value === 1) {
                return "已申请";
            } else if (value === 2) {
                return "已审核";
            } else if (value === 3) {
                return "已补正";
            } else {
                return "-";
            }
        },
        applyTime: function (value, row, index) {
            if (value) {
                return value.substr(0, 16);
            }
            return "-";
        },
        applyMethod: function (value, row, index) {
            if (value === 1) {
                return "人工窗口";
            } else if (value === 2) {
                return "我的南京";
            } else if (value === 3) {
                return "房产微政务";
            } else {
                return "-";
            }
        },
        operate: function (value, row, index) {
            var status = row["status"];

            var dealOperate = "<a href='javascript:void(0)' onclick=\"pageLogic.deal('" + value + "','" + status + "')\" style='color: #6495ed'>受理</a>";
            var previewOperate = "<a href='javascript:void(0)' onclick=\"pageLogic.preview('" + value + "','" + status + "')\" style='color: #6495ed'>初勘</a>";
            var planOperate = "<a href='javascript:void(0)' onclick=\"pageLogic.plan('" + value + "','" + status + "')\" style='color: #6495ed'>出具方案</a>";
            var contractOperate = "<a href='javascript:void(0)' onclick=\"pageLogic.contract('" + value + "','" + status + "')\" style='color: #6495ed'>签订合同</a>";
            var reportOperate = "<a href='javascript:void(0)' onclick=\"pageLogic.report('" + value + "','" + status + "')\" style='color: #6495ed'>编制报告</a>";
            var checkOperate = "<a href='javascript:void(0)' onclick=\"pageLogic.check('" + value + "','" + status + "')\" style='color: #6495ed'>审核报告</a>";
            var visaOperate = "<a href='javascript:void(0)' onclick=\"pageLogic.visa('" + value + "','" + status + "')\" style='color: #6495ed'>签发报告</a>";
            var grantOperate = "<a href='javascript:void(0)' onclick=\"pageLogic.grant('" + value + "','" + status + "')\" style='color: #6495ed'>发放报告</a>";

            var suspendOrEnd = "终止流程";

            if (status === 1) {
                return dealOperate;
            }
            if (status === 2) {
                return previewOperate;
            }
            if (status === 3) {
                return planOperate;
            }
            if (status === 4) {
                return contractOperate;
            }
            if (status === 5) {
                return reportOperate;
            }
            if (status === 6) {
                return checkOperate;
            }
            if (status === 7) {
                return visaOperate;
            }
            if (status === 8) {
                return grantOperate;
            }

            return "-";
        },
        reviewOperate: function (value, row, index) {
            var status = row["status"];
            var applyCheckOperate = "<a href='javascript:void(0)' onclick=\"pageLogic.reviewCheck('" + value + "', '" + status + "')\" style='color: #6495ed'>受理</a>";
            var expertOperate = "<a href='javascript:void(0)' onclick=\"pageLogic.expert('" + value + "', '" + status + "')\" style='color: #6495ed'>选择专家</a>";
            var confirmExpertOperate = "<a href='javascript:void(0)' onclick=\"pageLogic.confirmExpert('" + value + "', '" + status + "')\" style='color: #6495ed'>确认专家</a>";
            var opinionOperate = "<a href='javascript:void(0)' onclick=\"pageLogic.opinion('" + value + "', '" + status + "')\" style='color: #6495ed'>填写意见</a>";
            var sendOperate = "<a href='javascript:void(0)' onclick=\"pageLogic.send('" + value + "', '" + status + "')\" style='color: #6495ed'>办结</a>";
            var reviewDetail = "<a href='javascript:void(0)' onclick=\"pageLogic.reviewDetail('" + value + "', '" + status + "')\" style='color: #6495ed'>查看详细</a>";
            if (status === 1) {
                return applyCheckOperate;
            } else if (status === 2) {
                return expertOperate;
            } else if (status === 3) {
                return confirmExpertOperate;
            } else if (status === 4){
                return opinionOperate;
            }else if(status === 5){
                return sendOperate;
            }
        },
        //update by wangwj 20181220 start
        //直接在编号上可以看详细删除详细页面
        correctOperate: function (value, row, index) {
            var status = row["status"];

            var checkOperate = "<a href='javascript:void(0)' onclick=\"pageLogic.correctCheck('" + value + "', '" + status + "')\" style='color: #6495ed'>审核</a>";
            var uploadOperate = "<a href='javascript:void(0)' onclick=\"pageLogic.correctUpload('" + value + "', '" + status + "')\" style='color: #6495ed'>上传补正资料</a>";
            // var correctDetail = "<a href='javascript:void(0)' onclick=\"pageLogic.correctDetail('" + value + "', '" + status + "')\" style='color: #6495ed'>查看详细</a>";

            if (status === 1) {
                return checkOperate;
            } else if (status === 2) {
                return uploadOperate;
            } else if (status === 3) {
                return "——";
            }
        },
        //update by wangwj 20181220 end
        // 手机申请
        formatIdentifySource: function (cellValue, row) {
            if(cellValue == 1){
                return "我的南京";
            }else if (cellValue == 2){
                return "房产政务微服务";
            }
        },

        phoneOperate: function (value, row) {

            var applyOperate = "<a href='javascript:void(0)' onclick=\"pageLogic.toApply('" + value + "')\" style='color: #6495ed'>发起申请</a>";
            var refuseOperate = "<a href='javascript:void(0)' onclick=\"pageLogic.refuse('" + value + "')\" style='color: #6495ed'>拒绝申请</a>";
            var detailOperate = "<a href='javascript:void(0)' onclick=\"pageLogic.phoneDetail('" + value + "')\" style='color: #6495ed'>详细</a>"

                return applyOperate + " | " +refuseOperate +' | ' + detailOperate;

        }
    };

    /************************************鉴定业务操作开始******************************************/
    // 受理流程
    pageLogic.deal = function (id, status) {
        // type=0代表详细页面不显示返回按钮
        goto(masterpage.ctxp + "/identifies/accept?mainId=" + id + "&status=" + status + "&type=0&onlyMain=1", {withBackBtn: false});
    };

    // 初勘流程
    pageLogic.preview = function (id, status) {
        goto(masterpage.ctxp + "/identifies/preview?mainId=" + id + "&status=" + status + "&type=0&onlyMain=1", {withBackBtn: false});
    };

    // 出具方案流程
    pageLogic.plan = function (id, status) {
        goto(masterpage.ctxp + "/identifies/plan?mainId=" + id + "&status=" + status + "&type=0&onlyMain=1", {withBackBtn: false});
    };

    // 签订合同流程
    pageLogic.contract = function (id, status) {
        goto(masterpage.ctxp + "/identifies/contract?mainId=" + id + "&status=" + status + "&type=0&onlyMain=1", {withBackBtn: false});
    };

    // 编制鉴定报告流程
    pageLogic.report = function (id, status) {
        goto(masterpage.ctxp + "/identifies/report?mainId=" + id + "&status=" + status + "&type=0&onlyMain=1", {withBackBtn: false});
    };

    // 审核鉴定报告流程
    pageLogic.check = function (id, status) {
        goto(masterpage.ctxp + "/identifies/verify?mainId=" + id + "&status=" + status + "&type=0&onlyMain=1", {withBackBtn: false});
    };

    // 签发鉴定报告流程
    pageLogic.visa = function (id, status) {
        goto(masterpage.ctxp + "/identifies/sign?mainId=" + id + "&status=" + status + "&type=0&onlyMain=1", {withBackBtn: false});
    };

    // 发放鉴定报告流程
    //update by wangwj 20181204
    pageLogic.grant = function (id) {
        layer.confirm("确定将鉴定报告发放至房屋使用安全责任人吗？", {
            btn: ["确认发放", "暂不发放"]
        }, function () {
            // 防止重复提交
            $("a:contains('确定发放')").off('click');
            var url = masterpage.ctxp + "/identifies/send";
            var deferred = $.postJSON(url, {
                mainId: id
            });

            deferred.done(function (response) {
                if (!response.success) {
                    layer.msg(response.msg);
                    return;
                }

                layer.msg("发放成功！", {icon: 1, time: 1000}, function () {
                    common.search();
                    pageLogic.showCounts();
                });
            });
        });
    };

    // 终止or挂起流程
    pageLogic.stopOrSuspendProcess = function (id, action) {

        var modal = pageLogic.modals[0];
        common.resetForm({
            modal: modal
        });
        modal.show();
        modal.title(action === "stop" ? "终止流程" : "挂起流程");
        modal.btn[0].text(action === "stop" ? "终止" : "挂起");

        //注册弹出层保存按钮点击事件
        modal.btn[0].on("click", function () {
            if (!modal.form.valid()) {
                return;
            }

            var url = masterpage.ctxp + "/tasks/" + id + "/process";

            var deferred = $.postJSON(url, {
                action: action,
                reason: $("#reason").val()
            });
            deferred.done(function (response) {
                if (!response.success) {
                    layer.msg(response.msg);
                    return;
                }
                modal.hide();
                layer.msg("操作成功！", {icon: 1, time: 1000}, function () {
                    common.search();
                    pageLogic.showCounts();
                });
            });
        });
    };
    /************************************鉴定业务操作结束******************************************/

    /************************************复核业务操作开始******************************************/
        // 刷新复核业务列表
    var refreshReviewTable = function () {
        var queryMsg = {
            content: $("#reviewContent").val(),
            status: $("#reviewStatus").val()
        };
        pageLogic.reviewTable.refresh({
            url: masterpage.ctxp + "/tasks/review" + "/search/page",
            queryMsg: queryMsg
        });
    };

     // 申请审核
    pageLogic.reviewCheck = function (id, status) {
        goto(masterpage.ctxp + "/tasks/review/applyCheck?reviewId=" + id + "&status=" + status + "&type=0&toTasks=1", {withBackBtn: false});
    };
    // 选择专家
    pageLogic.expert = function (id, status) {
        //  type = 0 不显示详细页面的按钮
        goto(masterpage.ctxp + "/tasks/review/selectExpert?reviewId=" + id + "&status=" + status + "&type=0&toTasks=1", {withBackBtn: false});
    };
    // 确认专家
    pageLogic.confirmExpert = function (id, status) {
        goto(masterpage.ctxp + "/tasks/review/confirmExpert?reviewId=" + id + "&status=" + status + "&type=0&toTasks=1", {withBackBtn: false});
    }

    // 填写意见
    pageLogic.opinion = function (id, status) {
        goto(masterpage.ctxp + "/tasks/review/writeOpinion?reviewId=" + id + "&status=" + status + "&type=0&toTasks=1", {withBackBtn: false});
    };

    // 办结复核业务资料流程
    pageLogic.send = function (id) {
        layer.confirm("此复核申请是否确认办结？", {
            btn: ["办结", "暂不办结"]
        }, function () {
            var url = masterpage.ctxp + "/tasks/review/send/save";
            var deferred = $.postJSON(url, {
                reviewId: id
            });

            // update by xujc 2019/3/20 start
            deferred.done(function (response) {
                if (!response.success) {
                    layer.msg(response.msg);
                    return;
                }
                layer.msg("办结成功！", {icon: 1, time: 1000}, function () {
                    refreshReviewTable();
                    pageLogic.showCounts();
                });

            });
            // update by xujc 2019/3/20 end
        });
    };



    /************************************复核业务操作结束******************************************/

    /************************************补正变更操作开始******************************************/
        // 刷新补正变更列表
    var refreshCorrectTable = function () {
        var queryMsg = {
            content: $("#correctContent").val(),
            status: $("#correctStatus").val()
        };
        pageLogic.correctTable.refresh({
            url: masterpage.ctxp + "/corrects/search/page",
            queryMsg: queryMsg
        });
    };

    // 审核
    pageLogic.correctCheck = function (id, status) {
        goto(masterpage.ctxp + "/corrects/check?correctId=" + id + "&status=" + status + "&type=0&toTasks=1", {withBackBtn: false});
    };

    // 上传补正资料
    pageLogic.correctUpload = function (id, status) {
        goto(masterpage.ctxp + "/corrects/upload?correctId=" + id + "&status=" + status + "&type=0&toTasks=1", {withBackBtn: false});
    };

    // 流程详细
    pageLogic.correctDetail = function (id, status) {
        goto(masterpage.ctxp + "/correctMain/detail?correctId=" + id + "&status=" + status + "&type=1&toTasks=1", {withBackBtn: false});
    };

    /************************************补正变更操作结束******************************************/

    /************************************手机申请操作开始******************************************/
        // 刷新手机申请列表
    var refreshPhoneTable = function () {
            var queryMsg = {
                identifySource: $("#identifySourceSearch").val(),
                createUsername: $("#createUserNameSearch").val(),
                identifyContactsTel: $("#identifyContactsTelSearch").val(),
                startTimeSearch: $("#startTimeSearch").val(),
                endTimeSearch: $("#endTimeSearch").val()
            };
            pageLogic.phoneTable.refresh({
                url: masterpage.ctxp + "/phone/search/page",
                queryMsg: queryMsg
            });
        };
    pageLogic.toApply = function (id) {
        goto(masterpage.ctxp + "/tasks/identifyRequest?id="+id, {withBackBtn: false});
    };
    pageLogic.refuse = function (id) {
        var deffered = $.putJSON(masterpage.ctxp + '/phone/' +id);
        deffered.done(function (data) {
            if(data.success){
                layer.msg('拒绝成功！');
                $("#phoneList").click();
            }
        })
    };
    pageLogic.phoneDetail = function (id) {
        // 将id放在saveBtn上
        $("#saveBtn").attr('name',id);
        deffer = $.getJSON(masterpage.ctxp + '/phone/'+id);
        deffer.done(function (data) {
            var apply =  data.data;
            if(apply.identifyStatue==1){
                $("#saveBtn").hide();
            }else {
                $("#saveBtn").show();
            }
            var html = _.template($('#tpl_phoneDetail').html())({apply: apply});
            $("#detailBody").empty().append(html);
            $("#phoneDetailModal").modal("show");
        })
    };



    window.pageLogic = pageLogic;
})(window);