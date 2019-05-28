;(function (window) {
    /**
     * @author : wangwj
     * @date :2018/11/8
     *显示评分细则
     */
    var pageLogic = {
        init: {
            before: function () {
                var columns = [
                    //    {field: "checked", checkbox: true},
                    {field: "num", title: "序号", width: 5, formatter: common.formatter.index, align: "center"},
                    {field: "id", visible: false, align: "center"},
                    {field: "name", title: "级别名称", width: 80, align: "center"},
                    // {field: "get", title: "大于等于（>=）", width: 80, align: "center",display:"none"},
                    // {field: "lt", title: "小于", width: 80, align: "center"},
                    {field: "jifen", title: "计分", width: 80, align: "center", formatter: pageLogic.formatter.enabled},
                    {field: "remarks", title: "备注", width: 80, align: "center"}
                    //    {field: "seq", title: "序号", width: 80, align: "center"},
                ];

                pageLogic.btTable = common.initTable(columns);
                pageLogic.editable = false;
            },

            layout: function () {
                common.layout();
            },
            after: function () {
                //创建模态窗口
                common.modal(pageLogic.initData.modalParams[0], {
                    rules: {},

                    messages: {}
                });

            },
            //页面控件事件绑定(一般为按钮的事件绑定)
            events: function () {
                $("#modifyBtn").on("click", function () {
                    var aGet, bGet, bLet, cGet, cLet, dLet;
                    if (pageLogic.editable) {
                        try {
                            aGet = parseInt($("#aGet").val());
                            bGet = parseInt($("#bGet").val());
                            bLet = parseInt($("#bLet").val());
                            cGet = parseInt($("#cGet").val());
                            cLet = parseInt($("#cLet").val());
                            dLet = parseInt($("#dLet").val());
                        } catch (err) {
                            layer.msg("填写的计分非法！");
                            return;
                        }

                        //必须满足该表达式
                        var expression = (cGet - dLet === 1) && (cLet > cGet) && (bGet - cLet === 1) && (bLet > bGet) && (aGet - bLet === 1);

                        if (!expression) {
                            layer.msg("填写的计分非法！");
                            return;
                        }

                        common.postJSON({
                            url: pageLogic.initData.restUrlPrefix + "/saveData",
                            data: {
                                aGet: aGet,
                                bGet: bGet,
                                bLet: bLet,
                                cGet: cGet,
                                cLet: cLet,
                                dLet: dLet
                            }
                        }, function () {
                            pageLogic.editable = false;
                            $("#modifyBtn span").text("修改");
                            layer.msg("修改成功");
                            pageLogic.reset(aGet, bGet, bLet, cGet, cLet, dLet);
                        });

                        return;
                    }

                    //当前状态为非编译状态，可变为可编译状态
                    pageLogic.btTable.find("tbody tr").each(function (index) {
                        var $target = $(this).find("td").eq(2);
                        if (index === 0) {
                            pageLogic.$a = $target;
                            aGet = pageLogic.$a.html().substr(pageLogic.$a.html().indexOf("=") + 1);
                            return;
                        }
                        if (index === 1) {
                            pageLogic.$b = $target;
                            bGet = pageLogic.$b.html().substr(0, pageLogic.$b.html().indexOf("~"));
                            bLet = pageLogic.$b.html().substr(pageLogic.$b.html().indexOf("~") + 1);
                            return;
                        }
                        if (index === 2) {
                            pageLogic.$c = $target;
                            cGet = pageLogic.$c.html().substr(0, pageLogic.$c.html().indexOf("~"));
                            cLet = pageLogic.$c.html().substr(pageLogic.$c.html().indexOf("~") + 1);
                            return;
                        }
                        if (index === 3) {
                            pageLogic.$d = $target;
                            dLet = pageLogic.$d.html().substr(pageLogic.$d.html().indexOf("=") + 1);

                        }
                    });

                    $("#modifyBtn span").text("保存");


                    pageLogic.$a.html(">=<input id='aGet' value='" + aGet + "' style='width: 50px;'/>");
                    pageLogic.$b.html("<input id='bGet' value='" + bGet + "' style='width: 50px;'/>~<input id='bLet' value='" + bLet + "' style='width: 50px;'/>");
                    pageLogic.$c.html("<input id='cGet' value='" + cGet + "' style='width: 50px;'/>~<input id='cLet' value='" + cLet + "' style='width: 50px;'/>");
                    pageLogic.$d.html("&lt;=<input id='dLet' value='" + dLet + "' style='width: 50px;'/>");

                    pageLogic.editable = true;
                });


            },

            load: function () {
                common.search();
            }
        }
    };
    var arr1 = [];
    pageLogic.formatter = {


        enabled: function (val, row) {
            arr1.push(row.id);
            window.arr1 = arr1;

            if (row["name"] === "A") {
                return ">=" + row.get;
            }
            if (row["name"] === "D") {
                return "<=" + row.let;
            }

            return row.get + "~" + row.let;
        }
    };

    pageLogic.reset = function (aGet, bGet, bLet, cGet, cLet, dLet) {
        pageLogic.$a.html(">=" + aGet);
        pageLogic.$b.html(bGet + "~" + bLet);
        pageLogic.$c.html(cGet + "~" + cLet);
        pageLogic.$d.html("&lt;=" + dLet);
    };

    window.pageLogic = pageLogic;
})(window);

/*
   add by wangwj 20181031 start
*/
