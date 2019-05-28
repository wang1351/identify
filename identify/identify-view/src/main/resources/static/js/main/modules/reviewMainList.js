<!-- add by xujc 2018/12/5 start -->
;(function (window) {
// update by xujc 2019/1/23 start
    var pageLogic = {
        init: {
            before: function () {
                var isJdc = $("#jdc").val();
                if(isJdc ==1){
                    $("#org").empty();
                };
                pageLogic.columns = [
                    {field: "num", title: "序号", width: 5, align: "center", formatter: common.formatter.index},
                    {
                        field: "caseNo",
                        title: "编号",
                        width: 50,
                        align: "center",
                        formatter: pageLogic.formatter.formatCaseNo
                    },
                    {field: "zone", visible: false},
                    {field: "street", visible: false},
                    {
                        field: "address",
                        title: "房屋地址",
                        width: 120,
                        align: "center",
                        formatter: pageLogic.formatter.formatAddress
                    },
                    {field: "orgName", title: "鉴定单位", width: 100, align: "center"},
                    //update by wangwj 20190116 start
                    {field: "requestTime", title: "申请时间", width: 80, align: "center"},
                    //update by wangwj 20190116 end
                    {
                        field: "status",
                        title: "当前状态",
                        width: 80,
                        align: "center",
                        formatter: pageLogic.formatter.formatStatus
                    },
                    {field: "id", title: "操作", width: 100, align: "center", formatter: pageLogic.formatter.operate}
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
                common.initTable(pageLogic.columns);

                // 加载搜索框中的区属
                //update by wangwj 20190221 start
                common.dictSelect({
                    id: "zoneSearch",
                    key: "ZONE"
                });
                // 加载搜索框中的街道
                common.dictSelect({
                    id: "streetSearch",
                    key: "STREET"
                });

                // 加载搜索框中的鉴定机构
                common.select({
                    id: "orgSearch",
                    url: "/identifies/getOrgListByPid",
                    textField: "fullName",
                    valueField: "id"
                });

            },

            layout: function () {
                // common.layout();
            },
            after: function () {

            },
            //页面控件事件绑定(一般为按钮的事件绑定)
            events: function () {
                // 注册查询按钮点击事件
                $("#searchBtn").on("click", function () {
                    common.search();
                });
                // 详细按钮点击事件
                $("#btTable").on("click", ".reviewDetail", function (event) {
                    var id = $(event.target).attr("id");
                    var status = $(event.target).attr("name");
                    goto(masterpage.ctxp + "/reviewMain/detail?reviewId=" + id + "&status=" + status, {withBackBtn: false});
                });

                // 编号点击事件
                $("#btTable").on("click", ".caseNo", function (event) {
                    var mainId = $(event.target).attr("id");

                    goto(masterpage.ctxp + "/identifies/detail?mainId=" + mainId + "&from=1&type=2", {withBackBtn: false});
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

            },
            //update by wangwj 20190221 end
            load: function () {
                common.search();
            }
        }
    };

// 列表格式化
    pageLogic.formatter = {
        operate: function (cellValue, row) {
            return "<a href='javascript:void(0)'  class='reviewDetail' id='" + row.id + "' name='" + row.status + "' style='color: blue'>查看详细</a>"
        },
        formatStatus: function (value, row) {
            var processStatus = row["suspend"];

            if (processStatus === 1 || processStatus === 2) {
                return processStatus === 1 ? "已挂起" : "已终止";
            }
            if (value === 1) {
                return "已申请";
            } else if (value === 2) {
                return "已受理";
            } else if (value === 3) {
                return "已选择专家";
            } else if (value === 4) {
                return "已确认专家";
            } else if (value === 5) {
                return "已填写意见";
            } else if (value === 6) {
                return "已办结";
            } else if(value === 10){
                return "不受理";
            }


        },
        formatAddress: function (cellValue, row) {
            return row.zone + row.street + cellValue;
        },
        formatCaseNo: function (cellValue, row) {
            return "<a style='color: blue' class='caseNo' id='" + row.mainId + "'>" + cellValue + "</a>"
        }

    };

    window.pageLogic = pageLogic;
})(window);
// update by xujc 2019/1/24 end
<!-- add by xujc 2018/12/5 end -->