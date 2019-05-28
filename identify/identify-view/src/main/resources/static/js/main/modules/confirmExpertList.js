// add by xujc 2019/1/4 start 
// update by xujc 2019/1/11 start
;(function (window) {
    var cache = {};

    var pageLogic = {
        /**
         * @author : xujc
         * @date :2018/12/28
         * @Description : 获取指定专家信息
         */
        getAppoints: function (selectStr) {
            var deffered = $.postJSON(masterpage.ctxp + '/tasks/review/expert/getExpertAppoints', {reviewId: $('#reviewId').val()});
            deffered.done(function (data) {
                var str = '<span>本次所需专家: ';
                $.each(data.data, function (i, ele) {
                    str += ele.expertFieldName + ' <span style="color: #3598db">' + ele.expertNo + '</span> 人,'
                })
                str = str.substring(0, str.length - 1) + '</span>';
                $(selectStr).before(str);
            })
        },
        /**
         * @author : xujc
         * @date :2018/12/28
         * @Description : 获取选择专家信息
         */
        getReviewExperts: function () {
            var deffered = $.postJSON(masterpage.ctxp + '/tasks/review/expert/getReviewExperts', {
                reviewId: $('#reviewId').val(),
                type: 1
            });
            deffered.done(function (data) {
                var arr = data.data;
                // 将该list绑定为全局数组,后面要用
                pageLogic.arr = arr;
                pageLogic.delArr = [];
                var method = arr[0].method;
                if (method == 1) {
                    $("#chooceBtn").text('手动选择专家');
                } else {
                    $("#chooceBtn").text('随机选择专家');
                }
                $("#hideMethod").val(method);
                var html = _.template($('#tpl_reviewExperts').html())({data: arr});
                $("#fieldExperts").append(html);
                // 将现有的ids保存起来
                var idStr = pageLogic.getIdStr(pageLogic.arr);
                $("#selectedId").val(idStr);
            })
        },
        /**
         * @author : xujc
         * @date :2018/12/28
         * @Description : 合并已有的id集合跟新加的id集合，并展示数据
         *
         */
        toAllAndShow: function (newStr) {
            // 合并已有的和新加的
            var oldId = $("#selectedId").val();
            var oldIdArr = [];
            if (oldId) {
                oldIdArr = oldId.split('@');
            }
            var newIdArr =[];
            if(newStr){
                newIdArr=newStr.split('@');
            }
            $.each(newIdArr, function (i, ele) {
                var splitArr = ele.split(':');
                var flag = true;
                for (var i = 0; i < oldIdArr.length; i++) {
                    if (oldIdArr[i].indexOf(splitArr[0]) == 0) {
                        oldIdArr[i] += ',' + splitArr[1];
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    oldIdArr.push(ele);
                }
            })
            var allIdStr = oldIdArr.join('@');
            // 通过id字符串集合，找到要展示的数据
            var deffered = $.postJSON(masterpage.ctxp + '/tasks/review/expert/getReviewExperts', {allIdStr: allIdStr});
            deffered.done(function (data) {
                var arr = data.data;
                // 将该list绑定为全局数组,后面删除要用
                pageLogic.arr = arr;
                var html = _.template($('#tpl_reviewExperts').html())({data: arr});
                $("#fieldExperts").empty().append(html);
            });
            $("#selectedId").val(allIdStr);
            // 清空删除数组
            pageLogic.delArr = [];
        },
        getIdStr: function (arr) {
            // 回填id
            //update by wangwj 20190221 start
            var idStr = '';
            $.each(arr, function (i, ele) {
                if(ele.experts.length>0){
                    // 找到剩下的id
                    idStr += ele.experts[0].expertField + ':';
                    $.each(ele.experts, function (k, expert) {
                        idStr += expert.expertId + ',';
                    });
                    idStr = idStr.substring(0, idStr.length - 1) + '@';
                }


            });
            if (idStr) {
                idStr = idStr.substring(0, idStr.length - 1);
            }
            return idStr;
            //update by wangwj 20190221 end
        },
        init: {
            before: function () {
                showReviewProcess(4);
                pageLogic.getReviewExperts();
                pageLogic.getAppoints("#chooceBtn");

            },

            layout: function () {

            },
            after: function () {

            },
            //页面控件事件绑定(一般为按钮的事件绑定)
            events: function () {
                // 选择专家按钮添加点击事件
                $("#chooceBtn").on("click", function () {
                    var text = $(event.target).text();
                    // 找到删除的专家
                    var idStr = pageLogic.getIdStr(pageLogic.delArr);
                    // 找到剩余的ids
                    var hasStr = pageLogic.getIdStr(pageLogic.arr);
                    var oldStr = '';
                    var split = hasStr.split('@');
                    $.each(split, function (i, ele) {
                        oldStr += ele.split(':')[1] + ',';
                    })
                    if (oldStr) {
                        oldStr = oldStr.substring(0, oldStr.length - 1);
                    }
                    if (text == '随机选择专家') {
                        if (!idStr) {
                            layer.msg('必须先删除专家才能随机！');
                            return;
                        }
                        common.postJSON({
                            url: "/tasks/review/expert/random",
                            data: {
                                reviewId: pageLogic.initData.reviewId,
                                delIds: idStr,
                                oldIds: oldStr
                            }
                        }, function (response) {
                            var str = '';
                            Object.keys(response).forEach(function (key) {
                                str += key + ':';
                                $.each(response[key], function (i, ele) {
                                    str += ele + ',';
                                });
                                str = str.substring(0, str.length - 1);
                                str += '@';

                            })
                            // 自动选择的ids
                            str = str.substring(0, str.length - 1);

                            pageLogic.toAllAndShow(str);

                        });
                    } else {

                        $(event.target).attr('data-toggle', 'popover');
                        if (!cache['isInit']) {
                            $(event.target).popover({
                                trigger: 'click',
                                // template: '',
                                placement: 'right', //top, bottom, left or right
                                title: '专家列表',
                                html: 'true',
                                content: '<ul style="height:300px;overflow:auto" class="ztree" id="expertTree" name="expertTree"></ul>'
                            });
                            cache['isInit'] = true;

                            $(event.target).click();
                            var expert_setting = {
                                treeId: 'expert_tree',
                                async: {
                                    enable: true,
                                    url: masterpage.ctxp + '/tasks/review/' + pageLogic.initData.reviewId + '/tree?type=2'
                                },
                                check: {
                                    enable: true,
                                    chkboxType: {"Y": "", "N": ""}
                                },
                                data: {
                                    key: {name: "name"},
                                    simpleData: {
                                        enable: true,
                                        idKey: "id",
                                        pIdKey: "pId"
                                    }
                                },
                                view: {
                                    nameIsHTML: true,
                                    showLine: true
                                },
                                callback: {
                                    beforeClick: beforeClick,
                                    beforeCheck: beforeCheck,
                                    onCheck: zTreeOnCheck
                                }
                            };

                            $(event.target).on('shown.bs.popover', function () {
                                $.fn.zTree.init($("#expertTree"), expert_setting, null);
                            })
                        }

                    }

                });


                // 保存按钮添加点击事件
                $("#saveBtn").off("click").on("click", function () {

                    if ($("#selectedId").val() == "") {
                        layer.msg("请先选择专家！");
                        return;
                    }
                    var ids = $("#selectedId").val();
                    common.postJSON({
                        url: "/tasks/review/expert/check",
                        data: {
                            reviewId: pageLogic.initData.reviewId,
                            expertInfo: ids
                        }
                    }, function (respones) {
                        if (respones.code == "1") {
                            layer.msg(respones.msg);
                        } else if (respones.code == "2") {
                            layer.msg(respones.msg);
                        } else if (respones.code == "3") {
                            layer.msg(respones.msg);
                        } else if (respones.code == "4") {
                            layer.msg(respones.msg);
                        } else {
                            common.postJSON({
                                url: "/tasks/review/expert/confirm",
                                data: {
                                    reviewId: pageLogic.initData.reviewId,
                                    expertInfo: ids,
                                    method: $("#hideMethod").val()
                                }
                            }, function () {
                                layer.msg("信息保存成功！");
                                history.back();
                            });
                        }
                    });

                });

                // 给返回按钮加点击事件
                $("#backBtn").on("click", function () {
                    history.back();
                });

                $("#fieldExperts").on('click', '.delShow', function (event) {
                    if ($(event.target).prop('checked')) {
                        $(event.target).parent().next().next().find('span').show();
                    } else {
                        $(event.target).parent().next().next().find('span').hide();
                    }

                });
                // 删除按钮点击事件
                $("#fieldExperts").on("click", '.glyphicon-trash', function (event) {
                    // 取出所属领域名称
                    var fieldName = $(event.target).parent().prev().prev().prev().text();
                    // 取出 领域的值
                    var field = $(event.target).parent().prev().prev().find('input').val();
                    $(event.target).parent().parent().remove();

                    if (fieldName) {
                        $('.delShow[value="' + field + '"]:eq(0)').parent().prev().text(fieldName);
                    }
                    // 删除元素

                    $.each(pageLogic.arr, function (i, ele) {
                        var delExperts = [];
                        var flag = false;
                        $.each(ele.experts, function (k, expert) {
                            // 根据删除的专家id 去pageLogic.arr里找
                            if (expert.expertId == $(event.target).parent().parent().attr('id')) {
                                var delExpert = (ele.experts).splice(k, 1);
                                var hasField = false;
                                // 如果原来删除数组有数据，并且有当前删除数据领域对象

                                $.each(pageLogic.delArr, function (j, obj) {
                                    // 在delArr中找到该领域
                                    if (obj.expertFieldName == ele.expertFieldName) {
                                        obj.experts.push(delExpert[0]);
                                        hasField = true;
                                        return false;
                                    }
                                });

                                //  !hasField 在delArr中没有找到该领域 或者delArr长度为0

                                if (!hasField) {
                                    delExperts.push(delExpert[0]);
                                    pageLogic.delArr.push({
                                        expertFieldName: ele.expertFieldName,
                                        experts: delExperts
                                    });
                                }
                                // 找到停止寻找
                                flag = true;
                                return false;
                            }
                        });
                        if (flag) {
                            return false;
                        }
                    });
                    var idStr = pageLogic.getIdStr(pageLogic.arr);
                    $("#selectedId").val(idStr);
                })


            },

            load: function () {

            }
        }
    };

    var beforeClick = function (treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("expertTree");
        zTree.checkNode(treeNode, !treeNode.checked, null, true);
        return false;
    };

    var beforeCheck = function (treeId, treeNode) {
        return !treeNode.isParent;
    }

    var zTreeOnCheck = function (event, treeId, treeNode) {
        var treeObj = $.fn.zTree.getZTreeObj("expertTree");
        var checkedNodeS = treeObj.getCheckedNodes(true);
        var pNodeId = "";
        var pNodeName = "";

        //选中专家输入框中显示名称
        var expertIdS = [];
        var expertNameS = [];
        var idObject = {};
        var nameObject = {};
        if (checkedNodeS.length == 0) {
            checkedNodeS = $.fn.zTree.getZTreeObj("expertTree").getSelectedNodes();
        }
        for (var index = 0; index < checkedNodeS.length; index++) {
            var pNode = checkedNodeS[index].getParentNode();
            //需要存储的ID及领域
            pNodeId = pNode.id;
            if (idObject.hasOwnProperty(pNodeId)) {
                expertIdS.push(checkedNodeS[index].id);
                idObject[pNodeId] = expertIdS;
            } else {
                expertIdS = [];
                expertIdS.push(checkedNodeS[index].id);
                idObject[pNodeId] = expertIdS;
            }
            //页面展示的专家名称、称谓和电话
            pNodeName = pNode.name;
            if (nameObject.hasOwnProperty(pNodeName)) {
                expertNameS.push(checkedNodeS[index].name);
                nameObject[pNodeName] = expertNameS;
            } else {
                expertNameS = [];
                expertNameS.push(checkedNodeS[index].name);
                nameObject[pNodeName] = expertNameS;
            }
        }

        var selectedExpertId = "";
        for (var key in idObject) {
            selectedExpertId += key + ":" + idObject[key] + "@";
        }

        var selectedExpertName = "";
        for (var key in nameObject) {
            var keyValue = key;
            if (key.indexOf("【") != -1) {
                keyValue = key.substr(0, key.indexOf("【"))
            }
            selectedExpertName += keyValue + "：" + nameObject[key] + "\n";
        }

        if (selectedExpertId != null && selectedExpertId != "") {
            selectedExpertId = selectedExpertId.substr(0, selectedExpertId.length - 1);
        }
        pageLogic.toAllAndShow(selectedExpertId);


    };


    pageLogic.formatter = {};

    window.pageLogic = pageLogic;
})(window);
// update by xujc 2019/1/11 end
// add by xujc 2019/1/4 end 
