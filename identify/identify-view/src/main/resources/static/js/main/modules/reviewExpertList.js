;(function (window) {
// update by xujc 2019/1/21 start
var pageLogic = {
    // update by xujc 2019/2/26 start
    init: {
        before: function () {
            showReviewProcess(3);

        },

        layout: function() {

        },
        after: function () {
            // 专家选择树形结构
            var expert_setting = {
                treeId : 'expert_tree',
                async : {
                    enable : true,
                    url : masterpage.ctxp + '/tasks/review/' + pageLogic.initData.reviewId + '/tree'
                },
                check : {
                    enable: true,
                    chkboxType : {"Y" : "", "N" : ""}
                },
                data : {
                    key : {name : "name"},
                    simpleData : {
                        enable : true,
                        idKey : "id",
                        pIdKey : "pId"
                    }
                },
                view : {
                    nameIsHTML : true,
                    showLine : true
                },
                callback : {
                    beforeClick: beforeClick,
                    beforeCheck: beforeCheck,
                    onCheck : zTreeOnCheck
                }
            };
            $.fn.zTree.init($("#expertTree"), expert_setting, null);
        },
        //页面控件事件绑定(一般为按钮的事件绑定)
        events: function () {

            // 随机选择专家按钮添加点击事件
            $("#randomBtn").on("click", function () {
                common.postJSON({
                    url: "/tasks/review/expert/random",
                    data: {
                        reviewId: pageLogic.initData.reviewId
                    }
                }, function (response) {
                    var str ='';
                    Object.keys(response).forEach(function (key) {
                        str +=key +':';
                        $.each(response[key],function (i,ele) {
                            str +=ele+',';
                        });
                        str = str.substring(0,str.length -1);
                        str+='@';

                    })
                    str =str.substring(0,str.length -1);
                    $("#autoSelectedId").val(str);
                    // 获取选中信息
                    var deffer = $.postJSON(masterpage.ctxp + '/tasks/review/expert/getAutoChecked',{str:str});
                    deffer.done(function (data) {
                        var list = data.data;
                        var result = "";
                        $.each(list,function (i,ele) {
                            Object.keys(ele).forEach(function (key) {
                                result += key +'： ' +ele[key] +'<br/>';
                            })
                        });
                        $('#autoSelected').html(result);
                    })
                });
            });


            // 保存按钮添加点击事件
            $("#saveBtn").off("click").on("click", function () {

                if ($("#selectedId").val() == "" && $("#autoSelectedId").val() == '') {
                    layer.msg("请先选择专家！");
                    return;
                }
                var ids = $("#selectedId").val() ==''?$("#autoSelectedId").val():$("#selectedId").val();
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
                            url: "/tasks/review/expert/save",
                            data: {
                                reviewId: pageLogic.initData.reviewId,
                                expertInfo: ids,
                                method: $("input[name='chooceType']:checked").val()
                            }
                        }, function () {
                            layer.msg("信息保存成功！");
                            history.back();
                        });
                    }
                });

            });

            // 给返回按钮加点击事件
            $("#backBtn").on("click",function () {
                history.back();
            });
            // 给选择方式单选按钮加点击事件
            $("input[name='chooceType']").on('click',function (event) {
                $("#clearCheck").click();
                $("#autoSelected").text('');
                $("#autoSelectedId").val('');

                var method = $(event.target).val();
                // 手动
                if(method == 1){
                    $("#autoDiv").hide();
                    $("#handChooce").show();
                }else {
                    $("#autoDiv").show();
                    $("#handChooce").hide();
                    var deffered =  $.postJSON(masterpage.ctxp + '/tasks/review/expert/getExpertAppoints',{reviewId: $('#reviewId').val()});
                    deffered.done(function (data) {
                        var str = '本次所需专家: ';
                        $.each(data.data,function (i,ele) {
                            str += ele.expertFieldName +' <span style="color: #3598db">'+ele.expertNo+'</span> 人,'
                        })
                        str= str.substring(0,str.length-1);
                        $("#textCon").empty().append(str);
                    })
                }
            });
            // 清空按钮点击事件
            $("#clearCheck").on('click',function () {
                var treeObj = $.fn.zTree.getZTreeObj("expertTree");
                treeObj.checkAllNodes(false);
                treeObj.cancelSelectedNode();
                $('#selected').val("");
                $('#selectedId').val("");
            })
        },

        load: function () {

        }
    }
};

var beforeClick = function(treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("expertTree");
    zTree.checkNode(treeNode, !treeNode.checked, null, true);
    return false;
};

var beforeCheck = function(treeId, treeNode) {
    return !treeNode.isParent;
}

var zTreeOnCheck = function(event, treeId, treeNode) {
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

    if (selectedExpertId != null && selectedExpertId!= "") {
        selectedExpertId = selectedExpertId.substr(0, selectedExpertId.length - 1);
    }

    $("#selected").attr("value", selectedExpertName);
    $("#selectedId").attr("value", selectedExpertId);
};
// update by xujc 2019/2/26 end


pageLogic.formatter = {};

window.pageLogic = pageLogic;
})(window);
// update by xujc 2019/1/24 end
