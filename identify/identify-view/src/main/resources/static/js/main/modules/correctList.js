/* add by panqh 2018-12-05 start */
;(function (window) {
    //add by wangwj 20190118 start
    var cache = {};
// update by xujc 2019/1/9 start
var pageLogic = {
    
    //负责初始化确定的小选择框
    contentMethod: function (reportDetailList, identifyMain,report) {
        var str = '<table>';
        // str += '<thead>';
        // str += '<tr><th colspan="3">'+identifyMain.house.zone + identifyMain.house.street + identifyMain.house.address+'</th></tr></thead>';
        str += '<tbody><tr><td><input  type="checkbox" class="allCheck"></td><td style="color: blue">全选</td></tr>';
        $.each(reportDetailList, function (i, ele) {
            str += '<tr><td><input  type="checkbox" class="splitDetail" value="' + ele.identifyFileId + '" name="' + ele.id + '"></td><td>' + ele.houseName + '</td><td></td></tr>';
        });
        str += '<tr>';
        str += '<td colspan="3" style="text-align: center"><button type="button"  class="btn splitBtn" style="width: 80px;margin-right: 30px;background-color: #3598db" name="' + identifyMain.id + '">确定</button></td>';
        str += '<input type="hidden" id="allIdentifyFileId" value="'+report.identifyFileId+'"/>';
        str += '</tr>';
        str += '</tbody>';
        str += '</table>';
        return str;
    },
    toApply: function (mainId, obj) {
        var deffered = $.postJSON(masterpage.ctxp + '/identifies/report/mainId/' + mainId, {});
        deffered.done(function (data) {
            // 先根据mainId 取出编制报告详细息
            var report = data.data;
            var reportDetailList = report.reportDetailList;
            // 根据mainId取出鉴定主表信息
            var deffer = $.postJSON(masterpage.ctxp + '/identifies/getMain/' + mainId, {});
            deffer.done(function (data) {
                var identifyMain = data.data;
                // 如果有一个分栋 或者点击弹窗里面的确定
                if (reportDetailList.length == 1 || obj == 1) {
                    if (reportDetailList.length == 1) {
                        // 1分栋上传（编制报告id在详细表） 2汇总上传（编制报告id在标志报告表）
                        var identifyFileId ='';
                        if (report.method == 2) {
                            //
                            identifyFileId =report.identifyFileId;
                        } else {
                            identifyFileId =report.reportDetailList[0].identifyFileId;
                        }
                        // 单个分栋的鉴定报告id是identifyReport0
                        var appendStr = '<tr><th>房屋名称</th><td>' + identifyMain.house.houseSplitList[0].houseName + '</td><th>鉴定报告<input type="hidden" name="reportDetailId" value="' + reportDetailList[0].id + '"></th><td id="identifyReport0"></td></tr>';
                        $("#splitContain").empty().append(appendStr);
                        $("#identifyReport0").uploader({
                            doc: true,
                            docConfig: {
                                autoExpand: false,
                                action: 'view',
                                mode: 'outer'
                            }
                        });
                        $("#identifyReport0").resetUploader({model: 'viewer'}, [identifyFileId]);

                    } else {
                        $("#splitContain").empty();
                        var $splits = $(".splitDetail:checked");
                        if ($splits.length == 0) {
                            layer.msg('请选择房屋分栋信息！');
                            return;
                        }
                        $.each($splits, function (i) {
                            var houseName = $(this).parent().next().text();
                            var identifyFileId = $(this).val();
                            var reportDetailId = $(this).attr('name');
                            var appendStr = '<tr></tr>';
                            // identifyFileId 有就是分栋上传
                            if(identifyFileId){
                                var   appendStr = '<tr><th>房屋名称</th><td>' + houseName + '</td><th>鉴定报告</th><td id="identifyReport' + (i + 1) + '"></td></tr>';
                            }else {
                                var appendStr = '<tr><th>房屋名称</th><td colspan="3">' + houseName + '</td></tr>';
                            }
                            appendStr+='<input type="hidden" name="reportDetailId" value="' + reportDetailId + '">';
                            $("#splitContain").append(appendStr);

                            if(identifyFileId){
                                $("#identifyReport" + (i + 1)).uploader({
                                    doc: true,
                                    docConfig: {
                                        autoExpand: false,
                                        action: 'view',
                                        mode: 'outer'
                                    }
                                });
                                $("#identifyReport" + (i + 1)).resetUploader({model: 'viewer'}, [identifyFileId]);
                            }
                        });
                        // 汇总上传编制报告id，多个分栋也分汇总和分栋
                        var allIdentifyFileId =$('#allIdentifyFileId').val();
                        if(allIdentifyFileId){
                            // 多个分栋汇总上传
                            $("#splitContain").append('<tr><th>鉴定报告</th><td colspan="3" id="identifyReport0"></td></tr>');
                            $("#identifyReport0").uploader({
                                doc: true,
                                docConfig: {
                                    autoExpand: false,
                                    action: 'view',
                                    mode: 'outer'
                                }
                            });
                            $("#identifyReport0").resetUploader({model: 'viewer'}, [allIdentifyFileId]);

                        }
                        $("a[name='" + mainId + "']:eq(1)").click();

                    }

                    $("#caseNo").text(identifyMain.caseNo);
                    $("#address").text(identifyMain.house.zone + identifyMain.house.street + identifyMain.house.address);
                    $("#orgName").text(identifyMain.orgName);
                    if (identifyMain.method == "1") {
                        $("#method").text("人工窗口");
                    } else if (identifyMain.method == "2") {
                        $("#method").text("我的南京");
                    } else if (identifyMain.method == "3") {
                        $("#method").text("房产微政务");
                    }


                    $(".title2 .fa").click();
                } else {
                    // 如果有多个分栋，点击确定的时候就是弹出框
                    if (!cache[mainId]) {
                        obj.popover({
                            trigger: 'click',
                            // template: '',
                            placement: 'bottom', //top, bottom, left or right
                            title: identifyMain.house.zone + identifyMain.house.street + identifyMain.house.address,
                            html: 'true',
                            content: pageLogic.contentMethod(reportDetailList, identifyMain,report)
                        });

                        cache[mainId] = true;
                        obj.click();
                    }

                }
            });
        })


    },
    init: {
        before: function () {
            showCorrectProcess(1);

            var columns = [
                {field: "num", title: "序号", width: 5, align: "center", formatter: common.formatter.index},
                {field: "caseNo", title: "编号", width: 50, align: "center",formatter:pageLogic.formatter.caseNo},
                {field: "zone", visible: false},
                {field: "street", visible: false},
                {field: "address", title: "房屋地址", width: 120, align: "center", formatter: pageLogic.formatter.address},
                {field: "orgName", title: "鉴定单位", width: 100, align: "center"},
                {field: "operatorTime", title: "操作时间", width: 80, align: "center", formatter: pageLogic.formatter.requestTime},
                {field: "method", title: "申请方式", width: 50, align: "center", formatter: pageLogic.formatter.method},
                {field: "id", title: "操作", width: 100, align: "center", formatter: pageLogic.formatter.operate}
            ];

            common.initTable(columns, {
                onLoadSuccess: function () {

                }
            });

            //时间控件
            initDatePicker({
                id:"startTime",
                type:"day",
                formatter:"yyyy-mm-dd"
            });

            initDatePicker({
                id:"endTime",
                type:"day",
                formatter:"yyyy-mm-dd"
            });


            // 验证身份证号是否符合要求
            $.validator.addMethod("checkID", function (value, element) {
                var reg = /(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
                return this.optional(element) || reg.test(value);
            }, "身份证号输入不合法");
        },

        layout: function() {
            //common.layout();
        },
        after: function () {
            //设定鉴定信息和补正变更申请的验证必填
            $("#applyForm").validator({
                rules: {
                    requestName: {required: true},
                    idNum: {required: true, checkID: true},
                    contactName: {required: true, maxLen: 20},
                    phone: {required: true, phone: true},
                    contactAddress: {required: true},
                    reason: {required: true}
                },
                messages: {}
            });
        },
        //页面控件事件绑定(一般为按钮的事件绑定)
        events: function () {
            $("#searchBtn").on("click", function () {
                common.search();
            });

            //add by wangwj 20190118 start
            $(".title2 .fa-angle-down").off('click').on("click", function (event) {
                $(this).parent().parent().parent().next().toggle();
                $(this).parent().parent().toggleClass('tab-content-hide tab-content-show');
            });

            //鉴定列表确定链接点击事件
            $("#btTable").on('click', '[data-toggle="popover"]', function () {
                var mainId = $(event.target).attr('name');
                // 将mainId 存入申请button的name属性里
                $("#saveBtn").attr('name', mainId);
                pageLogic.toApply(mainId, $(event.target));
            });
            // 弹出框里确定按钮点击事件
            $("#btTable").on('click', '.splitBtn', function () {
                var mainId = $(event.target).attr('name');
                // obj 为1时 代表弹出框里的确定
                pageLogic.toApply(mainId, 1);
            });

            //点击编号
            $("#btTable").on("click", ".caseNo", function (event) {
                var mainId = $(event.target).attr("name");
                goto(masterpage.ctxp + "/identifies/detail?mainId=" + mainId + "&status=9&type=0");
            });
            // 全选按钮
            $("#btTable").on('click', '.allCheck', function () {
                $(event.target).parent().parent().parent().find('.splitDetail').prop('checked', $(event.target).prop('checked'));
            });
            // update by xujc 2019/3/7 start
            $("#saveBtn").on('click', function () {
                $(this).prop('disabled', true);
                var data = $("#applyForm").serializeObject();
                // reportDetailId 如果只有一个 就放成数组

                if ($("input[name='reportDetailId']").length == 1) {
                    data.reportDetailId = [data.reportDetailId];
                }
                var mainId = $(this).attr('name');

                if (!$("#applyForm").valid()) {
                    $(this).prop('disabled', false);
                    return;
                }
                var flag =false;
                $.each($("input[name='reportDetailId']"), function (i, ele) {
                    var reportDetailId = $(this).val();
                    if (!reportDetailId) {
                        flag = true;
                        return false;
                    }
                });
                if (flag || !mainId) {
                    layer.msg('请选择鉴定信息列表！');
                    $(this).prop('disabled', false);
                    return;
                }


                var deffer = $.putJSON(masterpage.ctxp + '/corrects/' + mainId, {params: data});
                deffer.done(function (data) {
                    if (data.success) {
                        layer.msg('申请成功！');
                        $("#saveBtn").prop('disabled', false);
                        goto(masterpage.ctxp + "/tasks?tab=correct", {withBackBtn: false});

                    }
                });
            });
            $("#backBtn").on('click', function () {
                // tab 定位到第几个tab
                goto(masterpage.ctxp + "/tasks?tab=correct", {withBackBtn: false});
            });
            // update by xujc 2019/3/7 end
        },

        load: function () {
            //common.search();
        }
    }
};

pageLogic.formatter = {
    address: function (value, row, index) {
        var zone = row["zone"];
        var street = row["street"];
        if (zone && street) {
            return zone + street + value;
        } else {
            return value;
        }
    },
    requestTime: function (value, row, index) {
        if (value) {
            return value.substr(0, 16);
        }
        return "-";
    },
    method: function (value, row, index) {
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
    //update by wangwj 20190114 start
    operate: function (value, row, index) {
        var submitReview = "<a href='javascript:void(0)'  style='color: #6495ed'  role=\"button\" data-toggle=\"popover\" name='" + value + "'>确定</a>";
      //  var identifyProcess = "<a href='javascript:void(0)' onclick=\"pageLogic.process('" + value + "')\" style='color: #6495ed'>查看鉴定流程</a>";
        return submitReview ;
    },
    caseNo: function (value,row,index) {
        var caseNo = "<a style='color: blue' class='caseNo' name='" + row.id + "'>" + value + "</a>";
        return caseNo;
    }
    //update by wangwj 20190114 end
};

// 提交申请
pageLogic.submit = function (mainId) {

    var modal = pageLogic.modals[0];
    common.resetForm({
        modal: modal
    });

    modal.btn[0].removeAttr("disabled");
    modal.show();
    modal.title("申请原因");

    $("#id").attr("value", mainId);
};

// 鉴定流程详细
pageLogic.process = function (mainId) {
    goto(masterpage.ctxp + "/identifies/detail?mainId=" + mainId+"&status=9&type=0");
};

window.pageLogic = pageLogic;
})(window);
// update by xujc 2019/1/9 end
//add by wangwj 20190118 end
/* add by panqh 2018-12-05 end */