;(function (window) {

var pageLogic = {
    init: {
        before: function () {
            var columns = [
                {field: "checked", checkbox: true},
                {field: "num", title: "序号", width: 5, formatter: common.formatter.index},
                {field: "id", visible: false},
                {field: "mainId", title: "房屋鉴定业务主表ID", width: 80, align: "center"},
                {field: "operatorTime", title: "操作时间", width: 80, align: "center"},
                {field: "operatorUserName", title: "操作者姓名", width: 80, align: "center"},
                {field: "reportId", title: "鉴定报告编制表ID", width: 80, align: "center"}
            ];
            //update by wangwj 20190227 start
            common.initTable(columns);
        },

        layout: function() {
            common.layout();
        },
        after: function () {
            //创建模态窗口
            common.modal(pageLogic.initData.modalParams[0], {
            rules: {
            },

            messages: {}
            });

        },
        //页面控件事件绑定(一般为按钮的事件绑定)
        events: function () {
            common.registerEvents();
        },

        load: function () {
            common.search();
        }
    }
};
    //update by wangwj 20190227 end
pageLogic.formatter = {};

window.pageLogic = pageLogic;
})(window);
