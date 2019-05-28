;(function (window) {

    var pageLogic = {
        init: {
            before: function () {
                //页面初始化时显示第一个li内容
                //update by wangwj 20190121 start
                var columns = [
                    {field: "id", visible: false},
                    {field: "scoreItem", title: "记分项目", width: 100, align: "center"},
                    {field: "scoreStandard", title: "评价标准", width: 150, align: "center"},
                    {field: "scoreStandardDetail", title: "评分标准详细",width: 100, align:"center"},
                    {field: "scoreValue", title: "分值", width: 60, align: "center"},
                    {field: "operation", title: "操作", width: 60, align: "center", formatter: pageLogic.formatter.operate}
                ];

                //update by wangwj 20190121 end
                common.initTable(columns,
                    {
                        pagination: false,
                        onLoadSuccess: function (data) {
                            mergeCells(data.result, "scoreItem", "scoreItem", 1, $('#btTable'));//行合并
                        }

                    });
            },

            layout: function() {
                common.layout();
            },
            after: function () {

            },
            //页面控件事件绑定(一般为按钮的事件绑定)
            events: function () {
            //add by wangwj 20190121 start
                $("#ul a").on("click", function (event) {
                    var key = $(event.target).attr("name");
                    $(event.target).parent().siblings().removeClass("active");
                    $(event.target).parent().siblings().removeClass("bfont");
                    $(event.target).parent().addClass("active");
                    $(event.target).parent().addClass("bfont");
                    //动态给tab页增加注解
                    if(key == 1){
                        $("#rule").text("评价依据及规则: (总分95分)");
                        $("#rule").next().text("依据《南京市房屋安全鉴定单位管理办法》第五条有关规定。");
                        $("#rule").next().next().text("");
                    }else if(key == 2){
                        $("#rule").text("评价依据及规则: (总分10分)");
                        $("#rule").next().text("1、获奖情况、社会责任暂限于南京市行政区域内;");
                        $("#rule").next().next().text("2、同一事项的良好信息，加分就高，不累计计算。");
                    }else {
                        $("#rule").text("评价依据及规则:");
                        $("#rule").next().text("1、处罚情况暂限于南京市行政区域内;");
                        $("#rule").next().next().text("2、同一事项的不良信息，扣分就高，不累计计算。");
                    }
                        common.search({
                            url: pageLogic.initData.restUrlPrefix + "/scoreRule" + "/" + key + "/search/page"
                        });
                    return false;
                })
            //add by wangwj 20190121 end
            },

            load: function () {
                $("#ul a:first").click();
            }
        }
    };

    /**
     * 合并单元格
     * @param data  原始数据（在服务端完成排序）
     * @param fieldName 合并属性名称
     * @param colspan   合并列
     * @param target    目标表格对象
     */
    function mergeCells(data,scoreItem,fieldName, colspan, target) {
        //声明一个map计算相同属性值在data对象出现的次数和
        var sortMap = {};
        for(var i = 0 ; i < data.length ; i++){
            for(var prop in data[i]){
                if(prop == scoreItem){
                    var key = data[i][prop]
                    if(sortMap.hasOwnProperty(key)){
                        sortMap[key] = sortMap[key] * 1 + 1;
                    } else {
                        sortMap[key] = 1;
                    }
                    break;
                }
            }
        }
        for(var prop in sortMap){
            console.log(prop,sortMap[prop])
        }
        var index = 0;
        for(var prop in sortMap){
            var count = sortMap[prop] * 1;
            $(target).bootstrapTable('mergeCells',{index:index, field:fieldName, colspan: colspan, rowspan: count});
            index += count;
        }
    }

    //格式化操作列
    pageLogic.formatter = {
        operate: function (value, row, index) {
            var update = "<a href='javascript:void(0)' onclick=\"pageLogic.update('" + row["id"] +"', this)\"><i class='fa fa-pencil fa-fw'></i></a>";
            return update;
        }
    };

    //修改操作
    pageLogic.update = function (id, Object) {

        if ($("#btTable input").length > 0) {
            alert("请先保存！");
            return;
        }


        $score = $(Object).parent().parent().find("td").eq(3);
        //add by wangwj 20190108 start
        //取得评价标准
        $standard = $(Object).parent().parent().find("td").eq(1);

        //add by wangwj 20190121 start

        $standardDetail = $(Object).parent().parent().find("td").eq(2);

        $operate = $(Object).parent().parent().find("td").eq(4);

        var score = $score.html();

        var standard = $standard.html();

        var standardDetail = $standardDetail.html();

        $standardDetail.html("<input value ='" + standardDetail + "' style='width: 400px'/>");

        $score.html("<input id='" + id + "' value = '" + score + "' style='width: 50px'/>");

        $standard.html("<input  value = '" + standard + "' style='width: 400px'/>" );

        var save = "<a href='javascript:void(0)' onclick=\"pageLogic.save('" + id + "')\"><i class='fa fa-save fa-fw'></i></a>";
        var cancle = "<a href='javascript:void(0)' onclick=\"pageLogic.cancle()\"><i class='fa fa-times-circle-o fa-fw'></i></a>";
        var operate = save + " | " + cancle;

        $operate.html(operate);

    };

    //保存
    pageLogic.save = function (id) {
        var newScore = $("#" + id).val();
        var newStandardDetail =$("#" + id).parent().prev().find('input').val();
        var newStandard = $("#" + id).parent().prev().prev().find('input').val();


        common.postJSON({
            url: "/setting/scores/saveScore",
            data: {
                id: id,
                score: newScore,
                standard:newStandard,
                standardDetail:newStandardDetail
            }
        }, function () {
            layer.msg("信息保存成功！");
            var num =$("#ul li.active a").attr("name");
            common.search({
                url: pageLogic.initData.restUrlPrefix + "/scoreRule/" + num + "/search/page"
            });
        });

    };

    //add by wangwj 20190121 end
    //取消
    pageLogic.cancle = function () {
        var num =$("#ul li.active a").attr("name");
        common.search({
            url: pageLogic.initData.restUrlPrefix + "/scoreRule/" + num + "/search/page"
        });
    };

    window.pageLogic = pageLogic;
})(window);

//add by wangwj 20190108 end