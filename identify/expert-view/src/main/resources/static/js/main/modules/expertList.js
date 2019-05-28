
// add by xujc 2018/11/23 start


;(function (window) {

    var pageLogic = {


        init: {
            before: function () {
                var columns = [
                    {field: "checked", checkbox: true},
                    {field: "num", title: "序号", width: 5, align: "center", formatter: common.formatter.index},
                    {field: "name", title: "姓名", width: 50, align: "center", formatter: pageLogic.formatter.formatName},
                    {field: "id", title: "主键", visible: false},
                    {field: "title", title: "职称", width: 50, align: "center"},
                    {field: "educationName", title: "学历", width: 50, align: "center"},
                    {field: "practice", title: "行业执业资格", width: 40, align: "center"},
                    {field: "list", title: "所属领域", width: 100, align: "center", formatter: pageLogic.formatter.formatArea},
                    {field: "companyName", title: "所在单位名称", width: 160, align: "center"},
                    {field: "phone", title: "手机号码", width: 80, align: "center"},
                    {field: "useStatus", title: "启用状态", width: 50, align: "center", formatter: pageLogic.formatter.formatStatus}

                ];

                //add by wangwj 20181217 start
                //在状态栏中初始化开关组件
                var initFlag = false;
                common.initTable(columns,{
                    onPostBody: function () {
                        $('#btTable .switch').bootstrapSwitch({
                            onText: "启用",
                            offText: "禁用",
                            onColor: "success",
                            offColor: "danger",
                            size: "small"
                        });
                        $.each($('#btTable .switch'),function () {
                            var status = $(this).attr('name');
                            if(status==0){
                                $(this).bootstrapSwitch('state',false);
                            }else {
                                $(this).bootstrapSwitch('state',true);
                            }

                            $(this).on('switchChange.bootstrapSwitch', function (event,state) {
                                var id = $(event.target).attr("id");
                                var deferred = $.putJSON(masterpage.ctxp + "/library/experts/" + id, {useStatus: state ? 1 : 0});
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
                //add by wangwj 20181217 end

                // 验证身份证号
                $.validator.addMethod("checkID", function (value, element) {
                    var reg = /(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
                    return this.optional(element) || reg.test(value);
                }, "身份证输入不合法");

                // 验证执业证号
                $.validator.addMethod("checkPracticeNum", function (value, element) {
                    var reg = /^[A-z0-9]+$/;
                    return this.optional(element) || reg.test(value);
                }, "执业证号格式不正确");

                //update by wangwj 20181220 start
                // 加载搜索框中的职称
                // common.dictSelect({
                //     id: "titleSearch",
                //     key: "TITLE"
                // });

                // 加载搜索框中的状态
                common.dictSelect({
                    id: "statusSearch",
                    key: "USE_STATUS"
                });
                // 加载搜索框中的行业执业资格
                // common.dictSelect({
                //     id: "practiceSearch",
                //     key: "PRACTICE"
                // });
                //update by wangwj 20181220 end
            },

            layout: function () {
                common.layout();
            },
            after: function () {
                //创建模态窗口
                common.modal(pageLogic.initData.modalParams[0], {
                    rules: {
                        name: {required: true, maxLen: 50},
                        identifiedCode: {required: true, checkID: true},
                        companyName: {required: true, maxLen: 50},
                        practiceNum: {required: true, checkPracticeNum: true},
                        phone: {required: true, phone: true},
                        email: {required: true, email: true},
                        expertBusinessArea:{required: true}
                    },

                    messages: {}
                }, function () {
                    $("#expertBusinessArea").richSelect({
                        url: masterpage.ctxp + "/sys/dicts/groups/keys/" + "EXPERT_BUSINESS_AREA" + "/items",
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

                });
                // 初始化上传照片组件
                $("#photo").avatar({
                    // server : masterpage.ctxp + '/files'
                    width: 60,
                    height: 80


                });
                // 初始化详细页面的上传照片的组件
                $("#dphoto").avatar({
                    // server : masterpage.ctxp + '/files'
                    width: 60,
                    height: 80,
                    tip: ''
                });


                // 初始化上传文件组件
                //update by wangwj 20190221 start
                $('#pick').uploader({server: masterpage.uploadUrl});
                 var intervalId = setInterval(function () {
                    if($('#pick .uploader-list').length ) {
                        $('#pick .uploader-list').on('click', '.delete', function () {
                            $('#pick')[0].uploader.uploader.reset();
                            $('#pick')[0].uploader.files = [];
                        });

                        clearInterval(intervalId);
                    }

                }, 200);
               //update by wangwj 20190221 end


                // 初始化详细页面的上传文件组件
                $("#dpick").uploader({server: masterpage.uploadUrl, model: 'viewer'});



                // 加载模态框中的状态
                common.dictSelect({
                    id: "useStatus",
                    key: "USE_STATUS",
                    headerKey: "",
                    headerValue: ""
                });
                // 加载模态框中的民族
                common.dictSelect({
                    id: "nation",
                    key: "NATION",
                    headerKey: "",
                    headerValue: ""
                });
                // 加载模态框中的性别
                common.dictSelect({
                    id: "sex",
                    key: "SEX",
                    headerKey: "",
                    headerValue: ""
                });
                // 加载模态框中的学历
                common.dictSelect({
                    id: "education",
                    key: "EDUCATION",
                    headerKey: "",
                    headerValue: ""
                });
                // 加载模态框中的学位
                common.dictSelect({
                    id: "degree",
                    key: "DEGREE",
                    headerKey: "",
                    headerValue: ""
                });
                // 加载模态框中的健康状况
                common.dictSelect({
                    id: "health",
                    key: "HEALTH",
                    headerKey: "",
                    headerValue: ""
                });


                // 取出状态的对象集合绑定在pageLogic对象上
                var deffer = $.postJSON(masterpage.ctxp + "/sys/dicts/groups/keys/USE_STATUS/items");
                pageLogic.deffer = deffer;

            },
            //页面控件事件绑定(一般为按钮的事件绑定)
            events: function () {
                common.registerEvents();
                var modal = pageLogic.modals[0];


                // 解除并注册添加按钮点击事件
                $("#addBtn").off("click").on("click", function () {
                    common.resetForm({
                        modal: modal
                    });
                    modal.show();
                    $("#photo").resetAvatar({upload: true});
                    $('#photo')[0].avatarid = void 0;
                    $('#photo').attr('title', '点击头像上传');
                    $("#pick").resetUploader({model: 'uploader'});
                });

                // 解除并注册修改按钮点击事件
                $("#modifyBtn").off("click").on("click", function () {
                    var selects = pageLogic.btTable.bootstrapTable('getSelections');
                    if (selects.length === 0) {
                        layer.msg("需要先选中一个");
                        return;
                    }

                    if (selects.length > 1) {
                        layer.msg("只能选中一个");
                        return;
                    }

                    common.update({
                        url: pageLogic.initData.restUrlPrefix + "/" + selects[0]["id"],
                        modal: modal,
                        idField: "id"
                    }, function (data) {

                        // 复选下拉框信息回填
                        $('#expertBusinessArea').selectVal(data.list.map(function (val) {
                            return val.expertField;
                        }).join(','));
                        // 重置上传照片的组件
                        $("#photo").resetAvatar({upload: true}, data['photo']);
                        $('#photo').attr('title', '点击头像上传');
                        $("#pick").resetUploader({model: 'uploader'}, [data['applyId']]);
                    });

                });

                // 解除并注册弹出层保存按钮点击事件
                modal.btn[0].off("click").on("click", function () {

                    // 获取头像照片id
                    var photoId = $("#photo").getAvatarID();
                    if (!photoId) {
                        layer.msg("必须上传头像照片！");
                        return;
                    }

                    // 获取申请表id数组
                    var applyIds = $("#pick").getUploaderFileIds();
                    if (applyIds.length > 1) {
                        layer.msg("只能上传一个申请表");
                        return;
                    }
                    if (applyIds.length === 0) {
                        layer.msg("必须上传一个申请表");
                        return;
                    }
                    common.save({
                        id: "id",
                        modal: modal,
                        url: pageLogic.initData.restUrlPrefix,
                        // 这里的hook函数的参数data就是默认的表单数据对象
                        hook: function (data) {
                            data.photo = photoId;
                            data.applyId = applyIds[0];

                            return data;
                        }

                    });
                });

                // 操作列的状态按钮点击事件
                $("#btTable").on("click", ".c1", function (event) {

                    // 状态的id
                    var useStatus = "";
                    pageLogic.deffer.done(function (data) {

                        $.each(data.data, function (i, ele) {

                            if ($(event.target).text() == ele.name) {
                                useStatus = ele.value;
                            }
                        })
                        if ($(event.target).text() == '禁用') {
                            $(event.target).text('启用');
                        } else {
                            $(event.target).text('禁用');
                        }
                        // 要修改的专家的id
                        var id = $(event.target).attr("name");
                        var deferred = $.putJSON(masterpage.ctxp + "/library/experts/" + id, {useStatus: useStatus});
                        deferred.done(function (data) {

                            if (data.success) {
                                layer.msg("修改状态成功！");
                            } else {
                                layer.msg("修改状态失败！");
                            }
                            // 刷新
                            common.search();
                        })

                    })
                });

                // 操作列的状态按钮点击事件
                //update by wangwj 20190222 start

               // $(".switch").bootstrapSwitch("onSwitchChange",function (event,state) {
               //     if (state == true) {
               //           var id = $(event.target).attr("name");
               //           var deferred = $.putJSON(masterpage.ctxp + "/library/experts/" + id, {useStatus: 1});
               //         alert(1)
               //           deferred.done(function (data) {
               //
               //               if (data.success) {
               //                   layer.msg("修改状态成功！");
               //               } else {
               //                   layer.msg("修改状态失败！");
               //               }
               //
               //           });
               //
               //     } else {
               //         var id = $(event.target).attr("name");
               //         var deferred = $.putJSON(masterpage.ctxp + "/library/experts/" + id, {useStatus: 0});
               //         alert(0)
               //         deferred.done(function (data) {
               //
               //             if (data.success) {
               //                 layer.msg("修改状态成功！");
               //             } else {
               //                 layer.msg("修改状态失败！");
               //             }
               //
               //         });
               //     }
               // });

                //update by wangwj 20190222 end

                // 姓名链接的点击事件
                $("#btTable").on("click", ".cName", function (event) {

                    // 获取该专家对象的id
                    var id = $(event.target).attr("name");

                    // 根据id从后台获取该专家对象的详细信息
                    var deffered = $.getJSON(masterpage.ctxp + "/" + pageLogic.initData.restUrlPrefix + "/" + id);
                    deffered.done(function (data) {

                        // 取出专家对象
                        var expertObj = data.data;
                        $("#dname").text(expertObj.name);
                        $("#didentifiedCode").text(expertObj.identifiedCode);
                        $("#dnation").text(expertObj.nationName);
                        $("#dsex").text(expertObj.sexName);
                        $("#deducation").text(expertObj.educationName);
                        $("#ddegree").text(expertObj.degreeName);
                        $("#dhealth").text(expertObj.healthName);
                        $("#duseStatus").text(expertObj.useStatusName);
                        $("#dpractice").text(expertObj.practice);
                        $("#dtitle").text(expertObj.title);
                        $("#dcompanyName").text(expertObj.companyName);
                        $("#dpracticeNum").text(expertObj.practiceNum);
                        $("#dphone").text(expertObj.phone);
                        $("#demail").text(expertObj.email);


                        $("#dexpertBusinessArea").text(pageLogic.getAreas(expertObj.list));


                        // 重置上传组件
                        $("#dphoto").resetAvatar({upload: false}, expertObj['photo']);

                        $("#dpick").resetUploader({model: 'viewer'}, [expertObj['applyId']]);

                    })
                    $("#detailModal").modal("show");

                })

            },

            load: function () {

                common.search();

            }
        },
        getAreas: function (list) {
            var dexpertBusinessAreas = "";
            $.each(list, function (i, ele) {
                dexpertBusinessAreas += ele.expertBusinessArea + ",";
            });
            return dexpertBusinessAreas.substr(0, dexpertBusinessAreas.length - 1);
        }
    };

    pageLogic.formatter = {
        // 格式化姓名（姓名变成可查看详细的链接）
        formatName: function (cellvalue, options) {
            var name = "<a name='" + options.id + "' class='cName' style='color: blue'>" + cellvalue + "</a>";
            return name;
        },

        //add by wangwj 20181217 start
        // 格式化状态
        formatStatus: function (cellvalue, options) {
            // options row对象
            var status = "";
                status += '<input class="switch" id="' + options.id + '" name="' + cellvalue + '"  type="checkbox" data-size="small">';
            return status;
        },
        //add by wangwj 20181217 end

        // 格式化所属领域
        formatArea: function (cellvalue, options) {

            return pageLogic.getAreas(options.list);
        }
    };

    window.pageLogic = pageLogic;
})(window);
// add by xujc 2018/11/23 end
