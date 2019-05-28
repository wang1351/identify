;(function (window) {
    /**
     * @author : wangwj
     * @date :2018/11/15
     *显示鉴定列表页面
     */
    var pageLogic = {
        init: {
            before: function () {
                var isJdc = $("#jdc").val();
                if(isJdc ==1){
                    $("#org").empty();
                };

                var columns = [
                    {field: "num", title: "序号", width: 5, align: "center", formatter: common.formatter.index},
                    {field: "id", visible: false},
                    {field: "unfinishedCount", visible: false},
                    {
                        field: "caseNo",
                        title: "编号",
                        width: 60,
                        align: "left",
                        formatter: pageLogic.formatter.formatCaseNo
                    },
                    {field: "orgName", title: "鉴定单位", width: 80, align: "center"},
                    {
                        field: "address",
                        title: "房屋地址",
                        width: 150,
                        align: "left",
                        formatter: pageLogic.formatter.formatAddress
                    },
                    //update by wangwj 20190116 start
                    {field: "requestTime", title: "申请时间", width: 100, align: "center"},
                    //update by wangwj 20190116 end
                    {
                        field: "method",
                        title: "申请方式",
                        width: 80,
                        align: "center",
                        formatter: pageLogic.formatter.formatMethod
                    },
                    //update by wangwj 20190225 start
                    {
                        field: "status",
                        title: "当前状态",
                        width: 80,
                        align: "center",
                        formatter: pageLogic.formatter.formatStatus
                    },

                    {field: "", title: "操作", width: 100, align: "center", formatter: pageLogic.formatter.formatDo}

                ];


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

                common.select({
                    id: "orgSearch",
                    url: "/identifies/getOrgListByPid",
                    textField: "fullName",
                    valueField: "id"
                });
                common.initTable(columns, {
                    onLoadSuccess: function () {
                    }
                });

                // 加载搜索框中的区属
                common.dictSelect({
                    id: "zoneSearch",
                    key: "ZONE"
                });
                // 加载搜索框中的街道
                common.dictSelect({
                    id: "streetSearch",
                    key: "STREET"
                });
            },

            layout: function () {
                common.layout();
            },
            after: function () {

            },
            //页面控件事件绑定(一般为按钮的事件绑定)
            events: function () {
                // 注册查询按钮点击事件
                $("#searchBtn").on("click", function () {
                    common.search();
                    var message1 = "<div style='margin: 15px 10px 0' ><span class='fu small' style='line-height:18px;background-color:green;color: white;padding:0px 3px'>复</span>" + "表示进行过复核，" + "<span class='zheng small' style='line-height:18px;background-color:red;color: white;padding:0px 3px'>正</span>" + "表示进行过补正变更</div>";
                    $("#btTable").after(message1);
                });

                $("#btTable").on("click", ".mainDetail", function () {
                    var mainId = $(event.target).attr("name");
                    goto(masterpage.ctxp + pageLogic.initData.restUrlPrefix + "/detail?mainId=" + mainId, {withBackBtn: false});
                });
                // 行政区属与街道联动
                $("#zoneSearch").on("change", function () {
                    common.select({
                        id: "streetSearch",
                        jqueryObj: $("#streetSearch"),
                        url: "/sys/dicts/groups/keys/STREET/" + this.value + "/items",
                        data: {},
                        textField: "name",
                        valueField: "value",
                        headerKey: "请选择",
                        headerValue: ""
                    });
                });
                //update by wangwj 20190225 end
            },
            //update by wangwj 20181207
            load: function () {
                common.search();
                var message1 = "<div style='margin: 15px 10px 0' ><span class='fu small' style='line-height:18px;background-color:green;color: white;padding:0px 3px'>复</span>" + "表示进行过复核，" + "<span class='zheng small' style='line-height:18px;background-color:red;color: white;padding:0px 3px'>正</span>" + "表示进行过补正变更</div>";
                $("#btTable").after(message1);
            }
        }
    };

    pageLogic.formatter = {
        formatStatus: function (cellValue, row) {

            // 0: 进行中；1:已挂起；2:已终止
            var processStatus = row["suspend"];

            if (processStatus === 1  ) {
                return  "已挂起";
            }
            if(processStatus === 2){
                return  "已终止";
            }
            if (cellValue === 1) {
                return "已申请";
            }
            if (cellValue === 2) {
                return "已受理";
            }
            if (cellValue === 3) {
                return "已初勘";
            }
            if (cellValue === 4) {
                return "已出具方案";
            }
            if (cellValue === 5) {
                return "已签订合同";
            }
            if (cellValue === 6) {
                return "已编制报告";
            }
            if (cellValue === 7) {
                return "已审核";
            }
            if (cellValue === 8) {
                return "已签发";
            }
            if (cellValue === 9) {
                return "已发放";
            }
            if(cellValue === 10){
                return "不受理";
            }
            if(cellValue === 15){
                return "拒绝申请";
            }

        },
        formatMethod: function (cellValue, row) {
            if (cellValue === 1) {
                return "人工窗口";
            }
            if (cellValue === 2) {
                return "我的南京";
            }
            if (cellValue === 3) {
                return "房产微政务";
            }
        },

        formatDo: function (cellValue, row) {
            var message = "";

            // status: 9 -> 已发放
            var status = row["status"];

            var unfinishedCount = row["unfinishedCount"];

            if (status === 9 && unfinishedCount > 0) {
                message += "<a href='javascript:void(0)' onclick=\"pageLogic.activeIdentify('" + row["id"] + "')\" style='color:blue'>激活鉴定</a>"
            }

            // 0: 进行中；1:已挂起；2:已终止
            var processStatus = row["suspend"];
            // 当前状态为已挂起，可恢复流程
            if (processStatus === 1) {
                message += "<a href='javascript:void(0)' onclick=\"pageLogic.resume('" + row["id"] + "')\"  style='color: blue'>恢复流程</a>";
            } else {
            }

            return message === "" ? undefined : message;
        },
        formatAddress: function (cellValue, row) {
            return row.zone + row.street + cellValue;
        },
        //update by wangwj 20181207 start
        formatCaseNo: function (cellValue, row) {
            // 是否复审
            var isReview = row.isReview;

            // 是否补正变更
            var isCorrect = row.isCorrect;

            var reviewPic = "<span class='fu small' style='background-color:green; color: white;line-height:18px;padding:0px 3px;float:right;margin-left:4px'>复</span>";
            var correctPic = "<span class='zheng small' style='background-color:red;color:white;line-height:18px;padding:0px 3px;float:right'>正</span>";
            //add by wangwj 20190123 start
            //增加占位符（无业务作用，美观考虑）
            var seat = "<span class='fu small' style='background-color:white; color: white;line-height:18px;padding:0px 3px;float:right;margin-left:4px;visibility:hidden'>复</span>";

            if (row.isReview == 1 && row.isCorrect == 1) {
                return reviewPic + correctPic + "<a style='color: blue' class='mainDetail' name='" + row.id + "'>" + cellValue + "</a>";
            } else if (row.isCorrect == 1) {
                return seat + correctPic +  "<a style='color: blue' class='mainDetail' name='" + row.id + "'>" + cellValue + "</a>";
            } else if (row.isReview == 1) {
                return reviewPic + "<a style='color: blue' class='mainDetail' name='" + row.id + "'>" + cellValue + "</a>";
            } else {
                return "<a style='color: blue' class='mainDetail' name='" + row.id + "'>" + cellValue + "</a>";
            }
        }
        //update by wangwj 20181207 end
        //add by wangwj 20190123 end
    };

    /**
     * 恢复流程
     * @param mainId
     */
    pageLogic.resume = function (mainId) {
        layer.confirm("确认恢复本流程吗？", {
            btn: ["确定", "取消"]
        }, function () {
            var url = masterpage.ctxp + "/tasks/" + mainId + "/process";

            var deferred = $.postJSON(url, {
                action: "resume"
            });
            deferred.done(function (response) {
                if (!response.success) {
                    layer.msg(response.msg);
                    return;
                }

                layer.msg("操作成功！", {icon: 1, time: 1000}, function () {
                    common.search();
                });
            });
        });


    };
    // update by xujc 2019/1/7 start
    /**
     * 激活鉴定流程
     * @param mainId
     */
    pageLogic.activeIdentify = function (mainId) {
        var status = 9;
        
        $("#side-menu>li:eq(1)>ul>li:eq(0)").addClass('active');
        $("#side-menu>li:eq(1)>ul>li:eq(0)").siblings().removeClass('active');
        // update by xujc 2019/1/7 end
        goto(masterpage.ctxp + "/identifies/report?mainId=" + mainId + "&isActive=" + true + "&type=0", {withBackBtn: false});
    };

    window.pageLogic = pageLogic;
})(window);
