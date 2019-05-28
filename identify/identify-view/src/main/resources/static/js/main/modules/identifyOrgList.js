;

function showModal(id) {

}
/*update by wangwj 20190309 start*/
(function (window) {

    var pageLogic = {
        /**
         * @date: identifyOrgPerson对象
         * @obj: jquery对象 （tab）
         * */
        appendHtml: function (data,obj) {
            var html = _.template($('#tpl_orgPerson1').html())({
                data: data
            });
            obj.empty().append(html);
        },
        showModal: function(orgId) {
            $("#orgId").val(orgId);
            $.getJSON(masterpage.ctxp + "/library/identify/orgs/" + orgId, function (response) {
                // 鉴定机构对象
                var orgMain = response.data;
                var html = _.template($('#tpl_orgMain').html())({
                    data: orgMain
                });
                $('#d1').empty().append(html);

                var identifyOrgTypeList = response.data.identifyOrgTypeList;
                // 鉴定业务内容
                var detailIdentifyBusiness = '';
                // 鉴定业务的代号
                var detailIdentifyBusinessKeys = '';
                $.each(identifyOrgTypeList, function (i, ele) {
                    detailIdentifyBusiness += ele.name + ',';
                    detailIdentifyBusinessKeys += ele.identifyTypeKey + ',';
                });
                if (detailIdentifyBusiness) {
                    detailIdentifyBusiness = detailIdentifyBusiness.substr(0, detailIdentifyBusiness.length - 1);
                }
                // detailIdentifyBusiness td 的id
                $("#detailIdentifyBusiness").text(detailIdentifyBusiness);
                $("#detailIdentifyBusiness").attr('keys', detailIdentifyBusinessKeys);
                // identifyOrgPersonList 包括法人和技术负责人
                $.each(response.data.identifyOrgPersonList, function (i, ele) {
                    // 如果有法人或技术负责人那么工作长度不足3的list补足3
                    if (ele.identifyOrgResumeList) {
                        var length = ele.identifyOrgResumeList.length;
                        if (length < 3) {
                            for (var i = 0; i < 3 - length; i++) {
                                ele.identifyOrgResumeList.push(1);
                            }
                        }
                    } else {
                        ele.identifyOrgResumeList = [1, 2, 3];
                    }
                     // 回填tab1中的法人
                    if (ele.type == "1") {
                        $("#detailFaPerson").text(ele.name);
                    }
                });
                   // 如果鉴定机构中法人与负责人都有
                if(orgMain.identifyOrgPersonList && orgMain.identifyOrgPersonList.length==2){
                    $.each(orgMain.identifyOrgPersonList, function (i, ele) {
                        // 依次将模板中的内容装入对应的tab中
                        if (ele.type == 1) {
                            pageLogic.appendHtml(ele,$("#d2"));
                        }else if(ele.type == 2){
                            pageLogic.appendHtml(ele,$("#d3"));
                        }
                    });
                    // 如果有法人或负责人中的一个
                }else if(orgMain.identifyOrgPersonList && orgMain.identifyOrgPersonList.length==1){
                    // 如果有法人
                    if(orgMain.identifyOrgPersonList[0].type ==1){
                        pageLogic.appendHtml(orgMain.identifyOrgPersonList[0],$("#d2"));
                        // 如果没有技术负责人那么工作长度不足3的list补足3
                        pageLogic.appendHtml({identifyOrgResumeList:[1,2,3]},$("#d3"));
                    }else {
                        pageLogic.appendHtml({identifyOrgResumeList:[1,2,3]},$("#d2"));
                        pageLogic.appendHtml(orgMain.identifyOrgPersonList[0],$("#d3"));
                    }
                }else {
                    pageLogic.appendHtml({identifyOrgResumeList:[1,2,3]},$("#d2"));
                    pageLogic.appendHtml({identifyOrgResumeList:[1,2,3]},$("#d3"));
                }

                //判断鉴定人员信息表是否为空 如果没有足够的长度就补一个空行

                if(orgMain.identifyOrgIdentifyList==null|| orgMain.identifyOrgIdentifyList.length==0 ){
                    var data1= [1];
                    var compiled = _.template($('#tpl_orgIdentify').html());
                    var html = compiled({data: data1});
                    $("#d4").empty().html(html);

                }else{
                    var data1= orgMain.identifyOrgIdentifyList;
                    var compiled = _.template($('#tpl_orgIdentify').html());
                    var html = compiled({data: data1});
                    $("#d4").empty().html(html);
                }


                //判断使用设备信息表列表是否为空 如果为空 就给他补一行
                if(orgMain.identifyOrgDeviceList==null || orgMain.identifyOrgDeviceList.length==0){
                    var data2 =[1];
                    var compiled1 = _.template($('#tpl_orgDevice').html());
                    var html1 = compiled1({data : data2});
                    $("#d5").empty().html(html1);
                }else{
                    var data1 = orgMain.identifyOrgDeviceList;
                    var compiled1 = _.template($('#tpl_orgDevice').html());
                    var html1 = compiled1({data: data1});
                    $("#d5").html(html1);
                }









            });

            $("#detailModal").modal("show").css({"margin-left": "-600px"});
        },
        init: {
            before: function () {

                var columns = [
                    {field: "checked", checkbox: true},
                    {field: "num", title: "序号", width: 5, align: "center", formatter: common.formatter.index},
                    {field: "id", visible: false},
                    {field: "name", title: "鉴定机构名称", width: 150, align: "center",formatter: pageLogic.formatter.orgName},
                    {field: "unifiedCode", title: "统一社会信用代码", width: 50, align: "center"},
                    {field: "personName", title: "法定代表人", width: 40, align: "center"},
                    {field: "phone", title: "联系电话", width: 40, align: "center",visible: false},
                    {field: "address", title: "通讯地址", width: 180, align: "center"},
                    {field: "useStatus", title: "启用状态", width: 140, align: "center", formatter: pageLogic.formatter.formatStatus}
                ];
                //add by wangwj 20181218 start
                //初始化开关组件
                var initFlag = false;
                common.initTable(columns,{
                    onPostBody: function () {
                        $('#btTable .switch').bootstrapSwitch({
                            onText: "启用",
                            offText: "禁用",
                            onColor: "success",
                            offColor: "danger",
                            size: "small",
                            onSwitchChange : function (event, state) {
                                // if(initFlag){
                                //     var idS = $(event.target).attr("id");
                            //     var type=0;
                            //     if(state==true){
                            //         type =1;
                            //     }else {
                            //         type =0;
                            //     }
                            //     var deferred = $.delJSON(masterpage.ctxp + "/library/identify/orgs/" + type + "/" + idS);
                            //     deferred.done(function (data) {
                            //         if (data.success) {
                            //             layer.msg("修改状态成功！");
                            //         } else {
                            //             layer.msg("修改状态失败！");
                            //         }
                            //     });
                            // }
                        }
                    });
                    $.each($('#btTable .switch'),function () {
                        var status = $(this).attr('name');
                        if(status==0){
                            $(this).bootstrapSwitch('state',false);
                        }else {
                            $(this).bootstrapSwitch('state',true);
                        }
                        $(this).on('switchChange.bootstrapSwitch', function (event,state) {
                            var idS = $(event.target).attr("id");
                            var type=0;
                            if(state==true){
                                type =1;
                            }else {
                                type =0;
                            }
                            var deferred = $.delJSON(masterpage.ctxp + "/library/identify/orgs/" + type + "/" + idS);
                            deferred.done(function (data) {
                                if (data.success) {
                                    layer.msg("修改状态成功！");
                                } else {
                                    layer.msg("修改状态失败！");
                                }
                            });
                        })
                    });
                    initFlag = true;
                }
            });
        },
        //add by wangwj 20181218 end
        layout: function() {
            common.layout();
        },
        after: function () {
            //创建模态窗口
            //update by wangwj 20190221 start
           /* common.modal(pageLogic.initData.modalParams[0], {
                rules: {
                    name:{required: true, maxLen: 50},
                    unifiedCode:{required: true, maxLen: 18},
                    legalPerson:{required: true, maxLen: 20},
                    useStatus:{required: true},
                    phone:{required: true, phone: true},
                    identifyBusinessKey:{required: true}
                },
                //update by wangwj 20190221 end
                messages: {}
            }, function () {

                $("#identifyBusinessKey").richSelect({
                    url: masterpage.ctxp + "/sys/dicts/groups/keys/" + "IDENTIFY_TYPE" + "/items",
                    requestMethod: "POST",
                    defaultData: {
                        header: [],
                        end: []
                    },
                    keys: {
                        value: 'value',  // option 的value
                        text: 'name', // option 的text
                        select: '' // option 的selected
                    },
                    multiple: true, // 多选
                    liveSearch: true // 查询功能
                });
            });*/

        },

        //页面控件事件绑定(一般为按钮的事件绑定)
        events: function () {
            /*common.registerEvents();*/

         /*   var modal = pageLogic.modals[0];

            modal.btn[0].off("click").on("click", function () {

                common.postJSON({
                    url: "/library/identify/orgs/existOrgCode",
                    data: {
                        identifyOrgId: $("#id").val(),
                        identifyOrgCode: $("#syncOrgCode").val()
                    }
                }, function (respones) {
                    if (respones === "true") {
                        layer.msg("组织机构编码已存在！");
                    } else {
                        common.save({
                            id: "id",
                            modal: modal,
                            url: pageLogic.initData.restUrlPrefix
                        });
                    }
                });
            });*/


            $('#detailModal').on('shown.bs.modal', function (e) {
                // 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
                $(this).css('display', 'block');
                var modalHeight=$(window).height() / 2 - $('#detailModal .modal-dialog').height() / 2;
                $(this).find('.modal-dialog').css({
                    'margin-top': modalHeight
                });
            });


            $("#btTable").on("click", ".cName", function (event) {
                $('#modalType').val(0);
                var orgId = $(event.target).attr("name");
                pageLogic.showModal(orgId);
                $('#xxxx').html("详细信息");
                $('#jdyw').html("鉴定业务");
                $('#boro').html("<button class=\"btn btn-grey\" data-dismiss=\"modal\" aria-hidden=\"true\">关闭</button>");
            });
            $('#modifyBtn').on("click",function (event) {
                $('#modalType').val(1);
                var selects = pageLogic.btTable.bootstrapTable('getSelections');
                if (selects.length === 0) {
                    layer.msg("需要先选中一个");
                    return;
                }
                if (selects.length > 1) {
                    layer.msg("只能选中一个");
                    return;
                }
                //显示需要修改的内容


                pageLogic.showModal(selects[0].id);
                $('#xxxx').html("修改信息");
                $('#jdyw').html("<span style='color: red;' >* </span>鉴定业务");
                $('#boro').html("<button id='baoC' class=\"btn  btn-success\" data-dismiss=\"modal\"" +
                    " aria-hidden=\"true\" >保存</button><button class=\"btn btn-grey\" data-dismiss=\"modal\"" +
                    " aria-hidden=\"true\">关闭</button>");

            });
            $('#detailModal').on('shown.bs.modal', function () {
                setTimeout(function () {

                if($('#modalType').val() ==1){
                    var selectValues =  $("#detailIdentifyBusiness").attr('keys');

                    var selectStr = ' <select class="form-control" id="identifyBusinessKey" name="identifyBusinessKey"></select>';
                    $('#detailIdentifyBusiness').html(selectStr);
                    $("#identifyBusinessKey").richSelect({
                        url: masterpage.ctxp + "/sys/dicts/groups/keys/" + "IDENTIFY_TYPE" + "/items",
                        requestMethod: "POST",
                        defaultSelect: {value: selectValues.split(','), text: []},
                        defaultData: {
                            header: [],
                            end: []
                        },
                        keys: {
                            value: 'value',  // option 的value
                            text: 'name', // option 的text
                            select: '' // option 的selected
                        },
                        multiple: true, // 多选
                        liveSearch: true // 查询功能
                    });
                 }
                }, 200)

            });
            //update by wangwj 20181214 end
            //设定保存功能

            $("#detailModal").on("click",'#baoC',function () {
                //获得鉴定业务列表
               var key =$('#identifyBusinessKey').val();
               //获得鉴定单位的ID值
                var orgId = $('#orgId').val();
                var deffer = $.putJSON(masterpage.ctxp + '/library/identify/orgs/' +orgId,{identifyBusinessKey:key} );
                deffer.done(function (data) {
                    if(data.success){
                        layer.msg('保存成功！');
                    }

                })


            });


            //注册按钮启用事件
            $("#useBtn").on("click", function () {
                var selects = pageLogic.btTable.bootstrapTable('getSelections');

                if (selects.length == 0) {
                    layer.msg("至少需要选中一个");
                    return;
                }

                var type = "1";

                var idS = "";
                for (var i = 0; i < selects.length; i++) {
                    idS += selects[i]["id"] + ",";
                }
                if (idS != "") {
                    idS = idS.substr(0, idS.length - 1);
                }

                var url = masterpage.ctxp + "/library/identify/orgs/" + type + "/" + idS;

                orgUseOrUnUse(type, url);
            });

            //注册按钮禁用事件
            $("#unUseBtn").on("click", function () {
                var selects = pageLogic.btTable.bootstrapTable('getSelections');

                if (selects.length == 0) {
                    layer.msg("至少需要选中一个");
                    return;
                }

                var type = "0";

                var idS = "";
                for (var i = 0; i < selects.length; i++) {
                    idS += selects[i]["id"] + ",";
                }
                if (idS != "") {
                    idS = idS.substr(0, idS.length - 1);
                }

                var url = masterpage.ctxp + "/library/identify/orgs/" + type + "/" + idS;

                orgUseOrUnUse(type, url);
            });
        },

        load: function () {
            common.search();
        }
    }
};

pageLogic.formatter = {
    status: function (value, row, index) {
        if (value == '0') {
            return "禁用";
        } else if (value == '1') {
            return "启用";
        } else {
            return "-";
        }
    },
    //add by wangwj 20181214 start
    orgName:function (cellvalue,options) {
        var orgName = "<a name='" + options.id + "' class='cName' style='color: blue'>" + cellvalue + "</a>";
        return orgName;
    },
    //add by wangwj 20181214 end

    //add by wangwj 20181218 start
    //引用开关组件状态栏
    formatStatus: function (cellvalue, options) {
        // options row对象
        var status = "";
        status += '<input class="switch" id="' + options.id + '" name="' + cellvalue + '"  type="checkbox" data-size="small">';
        return status;
    }



    //add by wangwj 20181218 end

};

var orgUseOrUnUse = function(type, url) {
    var operateName = "";
    if (type == "0") {
        operateName = "禁用";
    } else {
        operateName = "启用";
    }

    layer.confirm('确认' + operateName + '吗？', {
        btn: ['确定', '取消']
    }, function () {
        var deferred = $.delJSON(url);
        deferred.done(function (response) {
            if (!response.success) {
                layer.msg(response.msg);
                return;
            }

            layer.msg(operateName + "成功！", {icon: 1, time: 1000}, function () {
                common.search();
            });
        });
    });
};

window.pageLogic = pageLogic;
})(window);
/*update by wangwj 20190309 end*/