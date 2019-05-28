;(function (window) {
    var pageLogic = {
        init: {
            before: function () {
                var columns = [
                    {field: "num", title: "序号", width: 5, align: "center", formatter: common.formatter.index},
                    {field: "id", visible: false, align: "center"},
                    {field: "status", visible: false, align: "center"},
                    {field: "title", title: "标题", align: "center", formatter: pageLogic.formatter.title},
                    {field: "addTime", title: "时间", align: "center"},
                    {field: "msgType", title: "类型", align: "center", formatter: pageLogic.formatter.msgType}
                ];


                common.initTable(columns, {
                    onLoadSuccess: function () {}
                });
            },

            layout: function () {
                common.layout();
            },
            after: function () {

            },
            events: function () {

            },
            load: function () {
                common.search();
            }
        },

        formatter: {
            title: function(val, item) {
                var status = item["status"];

                // 未读
                if (status == "0") {
                    return "<i class='fa fa-circle' style='color: blue;'>&nbsp;<a onclick='pageLogic.showDetail(\"" + item["id"] + "\")' style='font-weight: bold; color: blue;'>" + val + "</a>";
                }

                return "<a onclick='pageLogic.showDetail(\"" + item["id"] + "\")' style='color: blue;'>" + val + "</a>";

            },
            msgType: function (val, item) {
                if (val == 1) return "安全鉴定";

                return "-";
            }
        },

        showDetail: function (id) {
            common.postJSON({
                url: pageLogic.initData.restUrlPrefix + "/" + id + "/readed"
            });

            goto(masterpage.ctxp + pageLogic.initData.restUrlPrefix + "/detail?id=" + id);
        }
    };

    window.pageLogic = pageLogic;
})(window);
