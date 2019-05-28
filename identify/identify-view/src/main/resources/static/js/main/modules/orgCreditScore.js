// add by xujc 2018/11/23 start
(function (window) {
// update by xujc 2019/1/25 start
    var pageLogic = {
        /**
         * @author : xujc
         * @date :2018/11/13
         * @Description : 获取基础信息的总分
         */
        getCount: function () {
            var count = 0;
            $.each($("input:checked"), function () {
                count += parseInt($(this).val());
            });
            var num = $("input[min='0']").val();
            if (num) {
                var text = $("input[min='0']").parent().next().text();
                var score = parseInt(text);
                if (score) {
                    count += parseInt(score);
                }
            }
            if (count == 0) {
                $("#count").text(count);
            } else {
                $("#count").text("+" + count);
            }
        },
        msgToTital: function () {
            var deffered = $.postJSON(masterpage.ctxp + "/enterprise/credits/getOrgCredit", {
                orgId: $("#hideOrgId").val()
            });
            deffered.done(function (data) {
                $("#allScore").text(data.data.creditGradeScore == null ? "0" : data.data.creditGradeScore);
                $("#creditGradeName").text(data.data.creditGradeName);
            })
        },
        /**
         * @author : xujc
         * @date :2018/11/13
         * @Description : 获取良好或不良的总分
         */
        getNumCount: function () {
            var total = 0;
            var obj = {};

            // update by xujc 2019/2/25 start
            $.each($("input[type='number']"), function () {
                // 取出数量
                var text = $(this).val();
                if (text) {
                    var item = $(this).attr('title');
                    // 取出分值
                    var value = $(this).attr("name");
                    if (!eval('obj.' + item)) {
                        eval('obj.' + item + '=' + parseInt(value));
                    } else {
                        // 良好信息就高
                        if (parseInt(value) > 0 && parseInt(value) > eval('obj.' + item)) {
                            eval('obj.' + item + '=' + parseInt(value));
                            // 不良信息就低
                        } else if (parseInt(value) < 0 && parseInt(value) < eval('obj.' + item)) {
                            eval('obj.' + item + '=' + parseInt(value));
                        }
                    }
                }

            });
            Object.keys(obj).forEach(function (key) {
                total += eval('obj.' + key);
            });
            // update by xujc 2019/2/25 end
            if (total > 10) {
                $("#count").text("+10");
            } else {
                $("#count").text(total);
            }


        },
        /**
         * @author : xujc
         * @date :2018/11/13
         * @Description : 获取所有总分
         */
        getAllCount: function () {
            // 当前页的合计加上数据库中其他两项的合计

            // 当前页的合计
            var count = $("#count").text();
            // 总分
            var total = parseInt(count == "" ? 0 : count);
            // 其他页的总分
            var other = $("#other").val();
            total += parseInt(other == "" ? 0 : other);
            $("#allScore").text(total);
            var flag = false;
            // 遍历单选按钮
            $.each($("input[type='radio']:checked"), function () {
                var value = $(this).val();
                if (value == 0) {
                    flag = true;
                    // 结束循环
                    return false;
                }
            })
            var deffer = $.postJSON(masterpage.ctxp + "/enterprise/credits/getByScore", {score: total});
            deffer.done(function (data) {
                if (flag) {
                    $("#creditGradeName").text("D");
                } else {
                    $("#creditGradeName").text(data.data.name);
                }
            })
        },
        /**
         * @author : xujc
         * @date :2018/11/13
         * @Description : 保存信息
         */
        save: function (callback) {

            // update by xujc 2019/2/18 start
            // 取出机构id
            var identifyOrgId = $("#hideOrgId").val();

            // 取出orgScoreType企业评分类型
            var orgScoreType = $("#ul>li.active>a").attr("name");

            // 取出企业信用id 如果空就添加
            var orgCreditId = $("#hideId").val();
            //定义个容器，用来装OrgCreditScore的对象
            var orgCreditScoreArr = [];
            // 找到总分
            var totalScore = $('#allScore').text().trim();
            // 1评分 2历史 3 上传资料
            if (pageLogic.initData.type == "1") {

                if (orgScoreType == "1") {
                    // 评价标准次数为1
                    var scoreStandardNum = 1;
                    // 取出所有的评分标准id（SCORE_STANDARD_ID）以及评价标准次数，分数统计
                    $.each($("input:checked"), function () {
                        var scoreStandardId = $(this).parent().parent().attr("data-uniqueid");
                        // 取出上传附件的id
                        var orgCreditFileIds = $(this).parent().next().next().find(".jc_picker").getUploaderFileIds();
                        var scoreStandardTotal = $(this).val();
                        var orgCreditScore = {
                            orgScoreType: orgScoreType,
                            scoreStandardId: scoreStandardId,
                            scoreStandardNum: scoreStandardNum,
                            scoreStandardTotal: scoreStandardTotal,
                            orgCreditFileIds: orgCreditFileIds
                        };
                        orgCreditScoreArr.push(orgCreditScore);
                    });
                    // 这个份数，将来信息回填要用，所以将此num存入数据库
                    var num = $("input[type='number']").val();
                    var scoreStandardId = $("input[type='number']").parent().parent().attr("data-uniqueid");
                    // 取出上传附件的id
                    var orgCreditFileIds = $("input[type='number']").parent().next().next().find(".jc_picker").getUploaderFileIds();
                    // 取出鉴定报告线上存档率的满分
                    var allScore = $("input[type='number']").parent().parent().prev().find('input').val();
                    var scoreStandardTotal = '';
                    if (num) {
                        scoreStandardTotal = allScore - num;
                    }

                    if (scoreStandardTotal <= 0) {
                        scoreStandardTotal = 0;
                    }

                    if (num || (orgCreditFileIds && orgCreditFileIds.length > 0)) {
                        var orgCreditScore = {
                            orgScoreType: orgScoreType,
                            scoreStandardId: scoreStandardId,
                            scoreStandardNum: num,
                            scoreStandardTotal: scoreStandardTotal,
                            orgCreditFileIds: orgCreditFileIds
                        };
                        orgCreditScoreArr.push(orgCreditScore);
                    }

                } else {
                    $.each($("input[type='number']"), function () {
                        var text = $(this).val();
                        var value = $(this).attr("name");
                        var scoreStandardId = $(this).parent().parent().attr("data-uniqueid");
                        // 取出上传附件的id
                        var orgCreditFileIds = $(this).parent().next().next().find(".jc_picker").getUploaderFileIds();
                        var scoreStandardTotal = '';
                        if (text) {
                            scoreStandardTotal = parseInt(text) * parseInt(value);
                        }

                        if (text || (orgCreditFileIds && orgCreditFileIds.length > 0)) {
                            var orgCreditScore = {
                                orgScoreType: orgScoreType,
                                scoreStandardId: scoreStandardId,
                                scoreStandardNum: text,
                                scoreStandardTotal: scoreStandardTotal,
                                orgCreditFileIds: orgCreditFileIds
                            };
                            orgCreditScoreArr.push(orgCreditScore);
                        }
                        // 都没选
                        if (orgCreditScoreArr.length == 0) {
                            var orgCreditScore = {
                                orgScoreType: orgScoreType
                            };
                            orgCreditScoreArr.push(orgCreditScore);
                        }

                    });

                }
            } else {
                //add by wangwj 20190102 start
                $.each($(".jc_picker"), function () {
                    if ($(this).parent().parent().css("display") != 'none') {
                        var scoreStandardId = $(this).parent().parent().attr("data-uniqueid");
                        // 取出上传附件的id
                        var orgCreditFileIds = $(this).getUploaderFileIds();
                        if (orgCreditFileIds.length != 0) {
                            var orgCreditScore = {
                                orgScoreType: orgScoreType,
                                scoreStandardId: scoreStandardId,
                                orgCreditFileIds: orgCreditFileIds
                            };
                            orgCreditScoreArr.push(orgCreditScore);
                        }
                    }
                });

            }
            // 都没选
            if (orgCreditScoreArr.length == 0) {
                var orgCreditScore = {
                    orgScoreType: 0
                };
                orgCreditScoreArr.push(orgCreditScore);
            }
            // 请求后台进行保存
            var deffered = $.postJSON(masterpage.ctxp + "/enterprise/credits/save", {
                orgCreditId: orgCreditId,
                orgCreditScores: orgCreditScoreArr,
                identifyOrgId: identifyOrgId,
                type: pageLogic.initData.type,
                totalScore: parseInt(totalScore)

            });
            deffered.done(function (data) {
                if (data.success) {
                    layer.msg("保存成功！");
                    callback();
                }
            })
            // update by xujc 2019/2/18 end
        },

        /**
         * 合并单元格
         * @param data  原始数据（在服务端完成排序）
         * @param fieldName 合并属性名称
         * @param colspan   合并列
         * @param target    目标表格对象
         */
        mergeCells: function (data, scoreItem, fieldName, colspan, target) {
            //声明一个map计算相同属性值在data对象出现的次数和
            var sortMap = {};
            for (var i = 0; i < data.length; i++) {
                for (var prop in data[i]) {
                    if (prop == scoreItem) {
                        var key = data[i][prop]
                        if (sortMap.hasOwnProperty(key)) {
                            sortMap[key] = sortMap[key] * 1 + 1;
                        } else {
                            sortMap[key] = 1;
                        }
                        break;
                    }
                }
            }
            for (var prop in sortMap) {
                console.log(prop, sortMap[prop])
            }
            var index = 0;
            for (var prop in sortMap) {
                var count = sortMap[prop] * 1;
                $(target).bootstrapTable('mergeCells', {
                    index: index,
                    field: fieldName,
                    colspan: colspan,
                    rowspan: count
                });
                index += count;
            }
        },
        /**
         * @author : xujc
         * @date :2018/11/14
         * @Description : 获取信息方法
         * @param : orgCreditId 企业信用id
         * @param ：orgScoreType 企业评分类型
         */
        msgToTab: function (orgCreditId, orgScoreType) {
            // 获取企业信用评分信息, 如果企业信用id不存在证明没有历史数据，就不需要信息回填
            var deffer = "";
            if (orgCreditId) {
                deffer = $.postJSON(masterpage.ctxp + "/enterprise/credits/getByIdAndType", {
                    orgCreditId: orgCreditId,
                    orgScoreType: orgScoreType + ""
                });

            }
            return deffer;
        },
        /**
         * @author : xujc
         * @date :2018/11/14
         * @Description : 获取信息方法
         * @param : identifyOrgId 鉴定机构id
         * @param ：orgScoreType 企业评分类型
         * @param ：year 年份
         * @param ：time 当前年份下一年
         */
        getMsgByYear: function (identifyOrgId, orgScoreType, year) {

            var deffered = $.postJSON(masterpage.ctxp + "/enterprise/credits/getByYear", {
                identifyOrgId: identifyOrgId,
                orgScoreType: orgScoreType,
                year: year,
                time: (new Date().getFullYear() + 1)
            });
            return deffered;
        },
        /**
         * @author : xujc
         * @date :2018/11/14
         * @Description : 初始化上传附件
         *
         */
        initUpload: function () {
            $('.jc_picker').map(function () {
                $(this).uploader({server: masterpage.uploadUrl})
            })
        },
        endWith: function (str, endStr) {
            var d = str.length - endStr.length;
            return (d >= 0 && str.lastIndexOf(endStr) == d);
        },
        /**
         * @author : xujc
         * @date :2019/01/08
         * @Description : 初始化文件，可以下载跟预览
         * $obj 是上传文件组件，arr是文件id数组
         */
        initViewFile: function ($obj, arr) {
            $obj.uploader({
                doc: true,
                docConfig: {
                    autoExpand: false,
                    action: 'view',
                    mode: 'outer'
                }
            });

            var interId = setInterval(function () {
                var $list = $obj.find('.file-card');
                if ($list.length == arr.length) {
                    $.each($list, function () {
                        var fileName = $(this).find('.file-name').text();
                        if (fileName && pageLogic.endWith(fileName, 'docx')) {
                            var $target = $(this).find('.file-options');
                            var fileId = $target.find(".document-view").attr('target');
                            var downLoad = '<div class="uploader-options download" title="下载" target="' + fileId + '"></div>';
                            $target.append(downLoad);
                        }
                    });
                    clearInterval(interId);
                }
            }, 500);
        },
        init: {
            // update by xujc 2019/2/25 start
            before: function () {

                // 基础信息加载的列
                var baseColumns = [
                    {field: "id", visible: false},
                    {field: "scoreItem", title: "记分项目", width: 100, align: "center"},
                    {
                        field: "scoreStandard",
                        title: "评价标准",
                        width: 150,
                        align: "center",
                        formatter: pageLogic.formatter.scoreStandard
                    },
                    {
                        field: "operation",
                        title: "操作",
                        width: 60,
                        align: "center",
                        class: "cz",
                        formatter: pageLogic.formatter.formatDo
                    },
                    {
                        field: "scoreValue",
                        title: "分值",
                        width: 60,
                        align: "center",
                        formatter: pageLogic.formatter.formatValue
                    },
                    {
                        field: "fuj",
                        title: "上传附件",
                        width: 60,
                        align: "center",
                        formatter: pageLogic.formatter.formatFuJian
                    }
                ];
                // 良好信息、不良信息加载的列
                var columns = [
                    {field: "id", visible: false},
                    {field: "scoreItem", title: "记分项目", width: 100, align: "center"},
                    {
                        field: "scoreStandard",
                        title: "评价标准",
                        width: 150,
                        align: "center",
                        formatter: pageLogic.formatter.scoreStandard
                    },
                    {
                        field: "number",
                        title: "数量",
                        width: 60,
                        align: "center",
                        formatter: pageLogic.formatter.formatNumber
                    },
                    {
                        field: "scoreValue",
                        title: "分值",
                        width: 60,
                        align: "center",
                        formatter: pageLogic.formatter.formatValue
                    },
                    {
                        field: "fuj",
                        title: "上传附件",
                        width: 60,
                        align: "center",
                        formatter: pageLogic.formatter.formatFuJian
                    }
                ];
                // 将这两个数组绑定到pageLogic上
                pageLogic.columns = columns;
                pageLogic.baseColumns = baseColumns;

                // 根据type区别页面展示
                var date = new Date();

                // type=1是评分,年份只能是当前年
                if (pageLogic.initData.type === "1") {
                    // 格式化年份
                    $("#syear").addClass("textHide");
                    $("#hyear").removeClass("textHide");
                    $("#hyear").text(date.getFullYear() + "年");
                } else if (pageLogic.initData.type === "2") {
                    // 格式化年份
                    $("#syear").removeClass("textHide");
                    $("#hyear").addClass("textHide");
                    // 保存按钮，评分有，看历史无
                    $("#saveAndReturn").addClass("textHide");
                    $("#saveAndContinue").addClass("textHide");

                } else {
                    //add by wangwj 20181227 start
                    // $("#pfnd").addClass("textHide");
                    $("#syear").addClass("textHide");
                    $("#hyear").removeClass("textHide");
                    $("#hyear").text((date.getFullYear()) + "年");
                    $("#saveAndReturn").removeClass("textHide");
                    $("#saveAndContinue").addClass("textHide");

                    //add by wangwj 20181227 end
                }

            },

            layout: function () {
                //common.layout();
            },
            after: function () {
                //创建模态窗口(显示评价详细)
                common.modal(pageLogic.initData.modalParams[0], {
                    rules: {
                        reason: {required: true, maxLen: 500}
                    }
                });

                var modal = pageLogic.modals[0];

                modal.find("div.modal-header").hide();
                modal.find("div.modal-footer").hide();

                initDatePicker({
                    id: "year",
                    type: "year",
                    formatter: "yyyy年",
                    endDate: (new Date().getFullYear())
                });
                // 默认显示当前年份
                $("#year").val(new Date().getFullYear() + "年");
                //初始化时让不良信息不显示
                //add by wangwj 20181227 start
                if (pageLogic.initData.type === "3") {
                    $("#bad").hide();
                    $(".cz").hide();

                }
                //add by wangwj 20181227 end
            },
            //页面控件事件绑定(一般为按钮的事件绑定)
            events: function () {

                //给tab链接加点击事件(包括信息回填)
                $("#ul a").on("click", function (event) {
                    // 给ul下的li加样式
                    $(event.target).parent().siblings().removeClass("active");
                    $(event.target).parent().siblings().removeClass("bfont");
                    $(event.target).parent().addClass("active");
                    // 字体加粗的样式类
                    $(event.target).parent().addClass("bfont");
                    // 取出键（1代表基础信息，2代表良好信息，3代表不良信息。键保存在a链接的name属性上）
                    var key = $(event.target).attr("name");
                    // 取出企业信用id，跳转到评分页面时，会将企业信用id，机构id保存在页面上
                    var orgCreditId = $("#hideId").val();
                    // 取出机构id
                    var identifyOrgId = $("#hideOrgId").val();
                    // 清除table对象
                    if (pageLogic.btTable) {
                        $("#btTable").bootstrapTable('destroy');
                    }

                    // 如果是评分类型
                    if (pageLogic.initData.type === "1") {
                        // 信息回填tital（目的是保证，初始化的总分永远是从数据库读取的）
                        pageLogic.msgToTital();
                        // 如果是基础信息
                        if (key === "1") {
                            common.initTable(pageLogic.baseColumns, {
                                // 不分页
                                pagination: false,
                                onLoadSuccess: function (data) {
                                    // 初始化上传文件组件（必须等table加载完毕才能进行）
                                    pageLogic.initUpload();
                                    // 合并单元格
                                    //update by wangwj 20190108 start
                                    pageLogic.mergeCells(data.result, "scoreItem", "scoreItem", 1, $('#btTable'));//行合并
                                    $("#rule").text("评价依据及规则: (总分90分)");
                                    //update by wangwj 20190108 end
                                    $("#rule").next().text("依据《南京市房屋安全鉴定单位管理办法》第五条有关规定。");
                                    $("#rule").next().next().text("");
                                    $("#btTable>tbody").append("<tr style='height: 46px'><td style='text-align: center'>合计</td><td></td><td></td><td id='count' style='text-align: center'></td><td></td></tr>");
                                    // 在table里加一行用来存放其他两个tab的总分
                                    $("#btTable>tbody").append("<input type='hidden' id='other'>");
                                    // 信息回填
                                    var deffered = pageLogic.msgToTab(orgCreditId, key);
                                    // 判断要不要信息回填（根据是否有企业信用id，有就回填，没有就不会填。没有的话肯定没有评分记录）
                                    if (deffered) {
                                        deffered.done(function (data) {
                                            // data.data 是企业信用评分集合
                                            $.each(data.data, function (i, ele) {
                                                // 遍历每一行
                                                $.each($("#btTable>tbody>tr"), function () {
                                                    // 获取标准id
                                                    var id = $(this).attr("data-uniqueid");
                                                    // 如果企业信用评分对象的评分标准id等于该tr中的评分标准id
                                                    if (ele.scoreStandardId === id) {
                                                        // 有数量才证明评分的时候选中的
                                                        if (ele.scoreStandardNum) {
                                                            // 如果该tr下找到了单选按钮
                                                            if ($(this).find("input[type='radio']").length == 1) {
                                                                // 就默认选中
                                                                $(this).find("input[type='radio']").prop("checked", true);
                                                            } else if ($(this).find("input[type='checkbox']").length == 1) {
                                                                // 如果该tr下找到了复选框
                                                                // 就默认选中
                                                                $(this).find("input[type='checkbox']").prop("checked", true);
                                                                // 数量框变不可编辑
                                                                $("input[type='number']").prop("readonly", "readonly");

                                                            } else if ($(this).find("input[type='number']").length == 1) {
                                                                // 如果该tr下找到数量框
                                                                // 这里的ele.scoreStandardNum 是排名
                                                                $(this).find("input[type='number']").val(ele.scoreStandardNum);
                                                                // num 是排名
                                                                var num = ele.scoreStandardNum;
                                                                var allScore = $(this).find("input[type='number']").parent().parent().prev().find('input').val();
                                                                // 根据分数回填分值列
                                                                var score = parseInt(allScore) - num;
                                                                // 给排名行的分值分值列格式化
                                                                // 如果分数小于等于0
                                                                if (score <= 0) {
                                                                    $(this).find("input[type='number']").parent().next().text("0");
                                                                } else {
                                                                    $(this).find("input[type='number']").parent().next().text("+" + score);
                                                                }
                                                            }
                                                        }
                                                        // 回填附件
                                                        if (ele.orgCreditFileIds.length !== 0) {
                                                            $(this).find(".jc_picker").resetUploader({model: 'uploader'}, ele.orgCreditFileIds);
                                                        }
                                                        // 结束内循环
                                                        return false;

                                                    }
                                                });

                                            });
                                            // 回填合计
                                            pageLogic.getCount();
                                            // 将其他页面的分数回填
                                            // 获取初始化的当前tab的分数合计
                                            var count = $("#count").text();
                                            // 获取初始化的总分
                                            var all = $("#allScore").text();
                                            // 回填其他页的总分
                                            $("#other").val(parseInt(all) - parseInt(count));
                                        })
                                    }


                                },

                            });

                        } else {
                            common.initTable(pageLogic.columns, {
                                pagination: false,
                                onLoadSuccess: function (data) {
                                    //update by wangwj 20190108 start
                                    // 初始化上传文件组件（必须等table加载完毕才能进行）
                                    pageLogic.initUpload();
                                    if (key === "2") {
                                        $("#rule").text("评价依据及规则: (总分10分)");
                                        $("#rule").next().text("1、获奖情况、社会责任暂限于南京市行政区域内;");
                                        $("#rule").next().next().text("2、同一事项的良好信息，加分就高，不累计计算。");
                                    } else {
                                        $("#rule").text("评价依据及规则:");
                                        $("#rule").next().text("1、处罚情况暂限于南京市行政区域内;");
                                        $("#rule").next().next().text("2、同一事项的不良信息，扣分就高，不累计计算。");
                                    }
                                    //update by wangwj 20190108 end
                                    $("#btTable>tbody").append("<tr style='height: 50px'><td style='text-align: center'>合计</td><td></td><td></td><td id='count' style='text-align: center'></td><td></td></tr>");
                                    $("#btTable>tbody").append("<input type='hidden' id='other'>");
                                    // 合并单元格
                                    pageLogic.mergeCells(data.result, "scoreItem", "scoreItem", 1, $('#btTable'));//行合并

                                    // 信息回填
                                    var deffer = pageLogic.msgToTab(orgCreditId, key);
                                    if (deffer) {
                                        deffer.done(function (data) {
                                            $.each(data.data, function (i, ele) {
                                                    $.each($("input[type='number']"), function () {
                                                        var id = $(this).parent().parent().attr("data-uniqueid");
                                                        if (ele.scoreStandardId === id) {
                                                            $(this).val(ele.scoreStandardNum);
                                                            if (ele.orgCreditFileIds.length !== 0) {
                                                                $(this).parent().next().next().find(".jc_picker").resetUploader({model: 'uploader'}, ele.orgCreditFileIds);
                                                            }

                                                        }
                                                    });
                                            });
                                            // 回填合计
                                            pageLogic.getNumCount();
                                            // 将其他页面的分数回填
                                            // 获取当前页的合计
                                            var count = $("#count").text();
                                            // 获取总分
                                            var all = $("#allScore").text();
                                            // 计算其他两个tab页的总分并填充在当前页下
                                            $("#other").val(parseInt(all) - parseInt(count));

                                        })
                                    }


                                }
                            });
                        }
                        // 加载表格中的数据
                        common.search({
                            url: pageLogic.initData.restUrlPrefix + "/scoreRule" + "/" + key + "/search/page"
                        });


                    } else if (pageLogic.initData.type === "2") {
                        // 查看历史信息
                        if (!orgCreditId) {
                            $("#btTable").hide();
                            $("#noScore").show();
                        } else {
                            if (key === "1") {
                                common.initTable(pageLogic.baseColumns, {
                                    pagination: false,
                                    onLoadSuccess: function (data) {
                                        // 将附件列标头改为附件
                                        $("#btTable>thead th:last>div:first").text("附件");
                                        // 数量框变不可编辑
                                        $("input[type='number']").prop("readonly", "readonly");
                                        // 信息回填
                                        // 获取年份
                                        var year = $("#year").val();
                                        year = year.substring(0, year.length - 1);
                                        // 请求该年份该机构该评分类型的数据
                                        var deffered = pageLogic.getMsgByYear(identifyOrgId, key, year);
                                        deffered.done(function (data) {
                                            if (data.data.length == 0) {
                                                $("#btTable").hide();
                                                $("#noScore").show();

                                            } else {
                                                $("#btTable").show();
                                                $("#noScore").hide();
                                                // 隐藏操作列的标题
                                                $("tr").find("th:eq(2)").hide();
                                                // 隐藏table以及操作列
                                                $.each($("#btTable>tbody>tr"), function () {
                                                    $(this).hide();
                                                    $(this).find("td:eq(2)").hide();
                                                })
                                                $.each(data.data, function (i, ele) {

                                                    $.each($("#btTable>tbody>tr"), function () {
                                                        var id = $(this).attr("data-uniqueid");
                                                        if (ele.scoreStandardId === id) {
                                                            $(this).show();
                                                            if ($(this).find("input[type='radio']").length == 1) {
                                                                $(this).find("input[type='radio']").prop("checked", true);
                                                            }
                                                            if ($(this).find("input[type='checkbox']").length == 1) {
                                                                $(this).find("input[type='checkbox']").prop("checked", true);
                                                            }
                                                            if ($(this).find("input[type='number']").length == 1) {
                                                                $(this).find("td:eq(1)").text("少存" + ele.scoreStandardNum + "份");
                                                                $(this).find("input[type='number']").val(ele.scoreStandardNum);
                                                                // num 是排名
                                                                var num = ele.scoreStandardNum;
                                                                // 满分
                                                                var allScore = $(this).find("input[type='number']").parent().parent().prev().find('input').val();
                                                                // 根据分数回填分值列
                                                                var score = parseInt(allScore) - num;
                                                                if (score <= 0) {
                                                                    $(this).find("input[type='number']").parent().next().text("0");
                                                                } else {
                                                                    $(this).find("input[type='number']").parent().next().text("+" + score);
                                                                }
                                                            }

                                                            if (ele.orgCreditFileIds.length !== 0) {
                                                                // 只初始化有附件的
                                                                pageLogic.initViewFile($(this).find(".jc_picker"), ele.orgCreditFileIds);
                                                                $(this).find(".jc_picker").resetUploader({model: 'viewer'}, ele.orgCreditFileIds);
                                                            }
                                                            return false;
                                                        } else {

                                                        }
                                                    });
                                                });
                                                pageLogic.getCount();
                                            }

                                        });

                                    }
                                });
                            } else {
                                common.initTable(pageLogic.columns, {
                                    pagination: false,
                                    onLoadSuccess: function (data) {

                                        // 将附件列标头改为附件
                                        $("#btTable>thead th:last>div:first").text("附件");
                                        // 数量框变不可编辑
                                        $("input[type='number']").prop("readonly", "readonly");

                                        // 信息回填
                                        var year = $("#year").val();
                                        year = year.substring(0, year.length - 1);
                                        var deffered = pageLogic.getMsgByYear(identifyOrgId, key, year);
                                        deffered.done(function (data) {
                                            if (data.data.length == 0) {
                                                $("#btTable").hide();
                                                $("#noScore").show();

                                            } else {
                                                $("#btTable").show();
                                                $("#noScore").hide();
                                                $.each($("#btTable>tbody>tr"), function () {
                                                    $(this).hide();
                                                });
                                                $.each(data.data, function (i, ele) {

                                                    $.each($("input[type='number']"), function () {
                                                        var id = $(this).parent().parent().attr("data-uniqueid");
                                                        if (ele.scoreStandardId === id) {
                                                            $(this).parent().parent().show();
                                                            $(this).val(ele.scoreStandardNum);
                                                            if (ele.orgCreditFileIds.length !== 0) {
                                                                pageLogic.initViewFile($(this).parent().next().next().find(".jc_picker"), ele.orgCreditFileIds);
                                                                $(this).parent().next().next().find(".jc_picker").resetUploader({model: 'viewer'}, ele.orgCreditFileIds);
                                                            }
                                                        }
                                                    });
                                                    pageLogic.getNumCount();
                                                })
                                            }
                                        });
                                    }
                                });

                            }
                            common.search({
                                url: pageLogic.initData.restUrlPrefix + "/scoreRule" + "/" + key + "/search/page"
                            });
                        }

                        // 上传资料
                    } else {
                        // 信息回填tital（目的是保证，初始化的总分永远是从数据库读取的）
                        pageLogic.msgToTital();
                        // 如果是基础信息
                        if (key === "1") {
                            common.initTable(pageLogic.baseColumns, {
                                // 不分页
                                pagination: false,
                                onLoadSuccess: function (data) {
                                    pageLogic.btTable.bootstrapTable('hideColumn', "operation");
                                    pageLogic.btTable.bootstrapTable('hideColumn', "scoreValue");

                                    // 初始化上传文件组件（必须等table加载完毕才能进行）
                                    pageLogic.initUpload();

                                    /* $("#rule").text("评价依据及规则: (总分95分)");
                                     $("#rule").next().text("依据《南京市房屋安全鉴定单位管理办法》第五条有关规定。");
                                     $("#rule").next().next().text("");*/

                                    // 信息回填
                                    var deffered = pageLogic.msgToTab(orgCreditId, key);
                                    // 判断要不要信息回填（根据是否有企业信用id，有就回填，没有就不会填。没有的话肯定没有评分记录）
                                    if (deffered) {
                                        deffered.done(function (data) {
                                            // data.data 是企业信用评分集合
                                            $.each(data.data, function (i, ele) {
                                                // 遍历每一行
                                                $.each($("#btTable>tbody>tr"), function () {
                                                    // 获取标准id
                                                    var id = $(this).attr("data-uniqueid");
                                                    // 如果企业信用评分对象的评分标准id等于该tr中的评分标准id
                                                    if (ele.scoreStandardId === id) {
                                                        // 回填附件
                                                        if (ele.orgCreditFileIds.length !== 0) {
                                                            $(this).find(".jc_picker").resetUploader({model: 'uploader'}, ele.orgCreditFileIds);
                                                        }
                                                        // 结束内循环
                                                        return false;
                                                    }
                                                })
                                            })
                                        })
                                    }


                                },
                                onPostBody: function () {
                                    if (pageLogic.filterArray && pageLogic.filterArray.length) {

                                        $.each(pageLogic.filterArray, function (index, id) {
                                            $('[data-uniqueid="' + id + '"]').hide()
                                        })
                                    }
                                }
                            });

                        } else {
                            common.initTable(pageLogic.columns, {
                                pagination: false,
                                onLoadSuccess: function (data) {
                                    pageLogic.btTable.bootstrapTable('hideColumn', "number");
                                    pageLogic.btTable.bootstrapTable('hideColumn', "scoreValue");
                                    // 初始化上传文件组件（必须等table加载完毕才能进行）
                                    pageLogic.initUpload();

                                    /*$("#rule").text("评价依据及规则: (总分5分)");
                                     $("#rule").next().text("1、获奖情况、社会责任暂限于南京市行政区域内;");
                                     $("#rule").next().next().text("2、同一事项的良好信息，加分就高，不累计计算。");*/


                                    // 合并单元格
                                    pageLogic.mergeCells(data.result, "scoreItem", "scoreItem", 1, $('#btTable'));//行合并
                                    // 信息回填
                                    var deffered = pageLogic.msgToTab(orgCreditId, key);
                                    // 判断要不要信息回填（根据是否有企业信用id，有就回填，没有就不会填。没有的话肯定没有评分记录）
                                    if (deffered) {
                                        deffered.done(function (data) {
                                            // data.data 是企业信用评分集合
                                            $.each(data.data, function (i, ele) {
                                                // 遍历每一行
                                                $.each($("#btTable>tbody>tr"), function () {
                                                    // 获取标准id
                                                    var id = $(this).attr("data-uniqueid");
                                                    // 如果企业信用评分对象的评分标准id等于该tr中的评分标准id
                                                    if (ele.scoreStandardId === id) {
                                                        // 回填附件
                                                        if (ele.orgCreditFileIds.length !== 0) {
                                                            $(this).find(".jc_picker").resetUploader({model: 'uploader'}, ele.orgCreditFileIds);
                                                        }
                                                        // 结束内循环
                                                        return false;
                                                    }
                                                })
                                            })
                                        })
                                    }
                                }

                            });
                        }
                        // 加载表格中的数据
                        common.search({
                            url: pageLogic.initData.restUrlPrefix + "/scoreRule" + "/" + key + "/search/page"
                        });
                    }

                    return false;
                });
                // 确定定位tab，保存并继续评分用
                var tab = $("#tab").val();
                if (tab && tab != 3) {
                    $("#ul>li:eq(" + tab + ")").find("a").trigger("click");
                } else {
                    $("#ul a:first").click();
                }
                // 给返回按钮加点击事件
                $("#toList").on("click", function () {
                    goto(pageLogic.initData.path + "/enterprise/credits", {withBackBtn: false});
                });

                // 给复选选按钮加个点击事件
                $("#btTable").on("click", "input[type='checkbox']", function (event) {
                    if ($(event.target).prop("checked")) {
                        // 清空数量框
                        $("input[type='number']").val("");
                        // 数量框变不可编辑
                        $("input[type='number']").prop("readonly", "readonly");
                    } else {
                        $("input[type='number']").prop("readonly", false);
                    }
                    // 评分可编辑否则不可编辑
                    if (pageLogic.initData.type === "1") {
                        pageLogic.getCount();

                        pageLogic.getAllCount();
                    } else {
                        return false;
                    }

                });

                // 给单选框加点击事件
                $("#btTable").on("click", "input[type='radio']", function () {
                    // 取出企业信用id
                    var orgCreditId = $("#hideId").val();
                    // 评分可编辑否则不可编辑
                    if (pageLogic.initData.type === "1") {
                        pageLogic.getCount();
                        pageLogic.getAllCount();
                    } else {
                        return false;
                    }

                });
                // 给数量框加change事件
                $("#btTable").on("change", "input[min='0']", function () {
                    // 评分类型（1是基础信息）
                    var type = $('#ul').find(".active").find('a').attr('name');
                    if (parseInt($(event.target).val()) < 1) {
                        $(event.target).val('');
                    }
                    if (type == 1) {
                        // 名次
                        var num = $(event.target).val();
                        // 满分
                        var allScore = $(event.target).parent().parent().prev().find('input').val();
                        var score = parseInt(allScore) - num;
                        // 格式化分值
                        if (score <= 0) {
                            $(event.target).parent().next().text("0");
                        } else if (score == allScore) {
                            $(event.target).parent().next().text($(event.target).parent().prev().text());

                        } else {
                            $(event.target).parent().next().text("+" + score);
                        }
                        // 获取合计
                        pageLogic.getCount();
                    } else {

                        pageLogic.getNumCount();

                    }
                    // 获取总分
                    pageLogic.getAllCount();

                });


                // 给年份加change事件
                $("#year").on("change", function () {
                    $("#ul a:first").click();
                });
                // 给保存按钮加点击事件
                $("#saveAndReturn").on("click", function () {
                    $(this).prop('disabled',true);
                    pageLogic.save(function () {
                        goto(pageLogic.initData.path + "/enterprise/credits", {withBackBtn: false});
                    });

                });

                //给保存并继续评分添加点击事件
                $("#saveAndContinue").on("click", function () {
                    $("#saveAndContinue").prop('disabled',true);
                    // 取出机构id
                    var identifyOrgId = $("#hideOrgId").val();
                    // 取出当前评分tab
                    var value = $("#ul li.active a").attr('name');

                    pageLogic.save(function () {
                        goto(pageLogic.initData.path + "/enterprise/credits/toScore?orgId=" + identifyOrgId + "&type=1&tab=" + value, {withBackBtn: false});
                        if ($("#ul li:last").hasClass("active")) {
                            $("#ul a:first").click();
                        } else {
                            $("#ul li.active").next().find("a").click();
                        }
                        $("#saveAndContinue").prop('disabled',false);
                    });
                });

                $('body').on('mouseover', '.uploader', function () {
                    var $target = $(this).find('div[id*="rt"]');
                    if ($target.length != 0) {

                        var left = parseInt($target.parent().css('width')) - parseInt($target.prev().css('width'));
                        $target.css('left', left / 2 + 'px');
                    }
                });

                //add by wangwj 20190122 start
                //add by wangwj 20190220 start
                //给评价标准添加点击事件
                $("#btTable").on("click", ".dName", function (event) {
                    var standardDetail = $(event.target).attr("name");
                    var modal = pageLogic.modals[0];
                    //清除原来弹出层的文本
                    common.resetForm({
                        modal: modal
                    });

                    $("#reason").html('<p style="text-indent: 2em">' + standardDetail + '</p>');
                    modal.show();
                })
                //add by wangwj 20190220 end
                //add by wangwj 20190122 end
            },
            // update by xujc 2019/2/25 end
            load: function () {

            }
        }
    };
    pageLogic.filterArray = [];
    pageLogic.formatter = {
        // 格式化数量列
        formatNumber: function (cellValue, row) {

            return "<input type='number'  min='0'  style='text-align: center;width: 60px' name='" + row.scoreValue + "' title='" + row.scoreItem + "'>";
        },
        // 格式化附件列
        formatFuJian: function () {

            return "<div class='jc_picker' ></div>";
        },
        // 格式化操作列
        formatDo: function (cellValue, row) {

            if (row.id == '79F97CDEY7H7C95AE05010AC0C0502FF') {
                return "<input type='checkbox' value='" + row.scoreValue + "'>"
            }
            if (row.id == '79F97CDEZ7H7C95AE05010AC0C0502FF') {
                return "<input type='number' min='0' style='text-align: center;width: 60px' name=''>份"
            }
            // name 是单选按钮的分组依据，value 是分值
            return "<input type='radio' name='" + row.scoreItem + "' value='" + row.scoreValue + "'>";
        },
        formatValue: function (cellValue, row) {
            if (+row.scoreValue <= 0) {
                pageLogic.filterArray.push(row.id);
            }
            if (cellValue == 0) {
                return 0 + "(直接为D级)";
            }
            // 根据评分标准id来找到特殊的分值
            if (row.id == '79F97CDEZ7H7C95AE05010AC0C0502FF') {
                return row.scoreStandard;
            }
            if (cellValue < 0) {
                return cellValue;
            }
            return "+" + cellValue;
        },

        //add by wangwj 20190122 start
        scoreStandard: function (cellValue, row) {
            var name = "<a name='" + row.scoreStandardDetail + "' class='dName'  style='color: blue'>" + cellValue + "</a>";
            return name;
        }


        //add by wangwj 20190122 end

    };

    //add by wangwj 20190122 start
    pageLogic.standard = function (id, action) {
        var modal = pageLogic.modals[0];
        common.resetForm({
            modal: modal
        });
        modal.show();
    };
    //add by wangwj 20190122 end
    window.pageLogic = pageLogic;
})(window);
// update by xujc 2019/1/25 end
// add by xujc 2018/11/23 end
