/* add by panqh 2018-11-14 start */
;(function (window) {
    /**
     * @author : xujc
     * @date :2019/5/14
     * @Description : TODO
     */
    var pageLogic = {
        /**
         * @author : xujc
         * @date :2019/01/08
         * @Description : 初始化文件，可以下载跟预览
         */
        initViewFile: function ($obj) {
            $obj.uploader({
                doc:true,
                docConfig: {
                    autoExpand: false,
                    action: 'view',
                    mode: 'outer'
                }
            });

        },
        /**
         * @author : xujc
         * @date :2019/5/14
         * @Description : 获取url的参数
         */
        getUrlParams: function () {
            var url = location.search;
            var theParam = {};
            if (url.indexOf("?") !== -1) { //确保‘？’不是最后一个字符串，即携带的参数不为空
                var str = url.substr(1); //substr是字符串的用法之一，抽取指定字符的数目，这里抽取？后的所有字符
                var strs = str.split("&"); //将获取到的字符串从&分割，输出参数数组，即输出[参数1=xx,参数2=xx,参数3=xx,...]的数组形式
                for (var i = 0; i < strs.length; i++) { //遍历参数数组
                    theParam[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]); //这里意思是抽取每个参数等号后面的值，unescape是解码的意思
                }
            }
            return theParam;

        },
        init: {
            before: function () {
                showProcess(1);

                // add by xujc 2019/5/14 start
                var params = pageLogic.getUrlParams();
                // 如果有id这个参数就是由手机申请过来的
                if(params.id){
                    deffer = $.getJSON(masterpage.ctxp + '/phone/'+params.id);
                    deffer.done(function (data) {
                        var apply =  data.data;
                        $('#clientName').val(apply.identifyClientete);
                        $('#contactName').val(apply.identifyContacts);
                        $('#phone').val(apply.identifyContactsTel);
                        $('#content').val(apply.identifyObjective);
                    })
                }
             // add by xujc 2019/5/14 end

                // 获取鉴定内容信息
                common.postJSON({
                    url: "/sys/dicts/groups/keys/" + "IDENTIFY_TYPE" + "/items"
                }, function (response) {
                    var compiled = _.template($('#tpl_identifyContent').html());
                    var html = compiled({data: response});
                    $("#identifyContent").append(html);
                    // add by xujc 2019/5/14 start 
                    var params = pageLogic.getUrlParams();
                    // 如果有id这个参数就是由手机申请过来的
                    if(params.id){
                        deffer = $.getJSON(masterpage.ctxp + '/phone/'+params.id);
                        deffer.done(function (data) {
                            var identifyApply =  data.data;
                            $.each($("input[name='identifyType']"),function (i,ele) {
                                if($(this).val()==identifyApply.typeValue){
                                    $(this).prop('checked',true);
                                    if(identifyApply.typeValue == 8){
                                        $("#otherContent").text(identifyApply.otherContent);
                                    }
                                }

                            })
                        })
                    }

                    // add by xujc 2019/5/14 end

                });

                // 房屋产权证上传文件组件
                pageLogic.initViewFile( $('#certificateFile'));
                // 身份证上传文件组件
                pageLogic.initViewFile($('#IDFile'));
                //其他上传组件
                pageLogic.initViewFile($('#otherFile'));
                // 房屋安全鉴定委托书附表上传组件
                pageLogic.initViewFile($('#clientFile'));
                // 岩土工程勘察报告上传组件
                pageLogic.initViewFile( $('#reportFile'));
                // 新建工程建筑及结构图纸上传组件
                pageLogic.initViewFile($('#structureFile'));
                // 新建工程基坑支护方案上传组件
                pageLogic.initViewFile($('#foundationFile'));
                // 鉴定房屋建筑及结构图纸上传组件
                pageLogic.initViewFile( $('#identifyHouseFile'));
                // 鉴定委托书上传组件
                pageLogic.initViewFile( $('#clientIdentifyFile'));


                //时间控件
                initDatePickers([
                    {id: "pileStartDate"},
                    {id: "pileEndDate"},
                    {id: "supportStartDate"},
                    {id: "supportEndDate"},
                    {id: "workDate"},
                    {id: "endDate"}
                ]);


            },

            after: function () {
                $("#clientForm").validator({
                    rules: {
                        clientName: {required: true},
                        nature: {required: true, maxLen: 18},
                        contactName: {required: true, maxLen: 20},
                        phone: {required: true, phone: true},
                        idNum: {required: true, checkID: true},
                        phone2: {required: false, phone: true}
                    }
                });
                // update by xujc 2019/3/1 start
                $("#newProForm").validator({
                    rules: {
                        projectName: {required: true, maxLen: 100},
                        buildOrg: {required: true, maxLen: 200},
                        // 必须输入整数
                        layerGround: {required: false, maxLen: 2, digits: true},
                        layerUnderGround:{required: false, maxLen:2, digits: true},
                        totalArea:{number: true,min:0},
                        pileLength:{number: true,min:0},
                        supportDiameter:{number: true,min: true},
                        foundationArea:{number: true,min:0},
                        depth:{number: true,min: 0},
                        margin:{number: true,min: 0},
                        pileDiameter:{number:true,min:0},
                        supportLength:{number:true,min:0},
                        spacing:{number:true,min:0}
                    }
                });
                // update by xujc 2019/3/1 end
                
                $("#endClientForm").validator({
                    rules: {
                        content: {required: true, maxLen: 500},
                        condition: {required: true, maxLen: 500}
                    }
                });


            },
            //页面控件事件绑定(一般为按钮的事件绑定)
            events: function () {
                // 房屋 添加/删除 事件
                $("#houseContainer").on("click", "i[name=houseOpt]", function () {
                    if ($(this).hasClass("fa-plus-circle")) {
                        pageLogic.newHouse();
                        window.scrollTo(1000, document.body.scrollHeight);
                    } else {
                        var $houseForm = $(this).parent().parent().parent();

                        $houseForm.next().hide("slow", function () {
                            $(this).remove();
                        });

                        $houseForm.hide("slow", function () {
                            $(this).remove();
                        });
                    }
                })
                    // 房屋分栋添加事件
                    .on("click", "button[name=addBtn]", function () {
                        pageLogic.newSplit($(this).parent().parent().prev());
                    })
                    // 房屋分栋删除事件
                    .on("click", "button[name=delBtn]", function () {
                        var $pullRightDiv = $(this).parent().parent();

                        $pullRightDiv.prev().hide("slow", function () {
                            $pullRightDiv.remove();
                            $(this).remove();

                        });

                    })
                    // 分栋信息 收缩/展开
                    .on("click", ".btn-toggle", function () {
                        $(this).parents('form.form-horizontal').toggleClass('box-toggle-hide');
                    })
                    .on("change", "select[name=structure]", function () {
                        var $input = $(this).next();
                        if ($(this).val() === "5") {
                            $input.show("slow");
                        } else {
                            $input.hide("slow");
                        }
                    });


                // 保存按钮点击事件
                $("#saveBtn").on("click", function () {
                    if (!validatorInfo()) {
                        return;
                    }

                    $("#saveBtn").attr("disabled", "disabled");
                    var params = pageLogic.getUrlParams();
                    common.postJSON({
                        url: "/tasks/save/identifyRequest",
                        data: {
                            identifyContent: getIdentifyContent(),
                            client: getClientInfo(),
                            house: getHouseInfo(),
                            newPro: getNewProjectInfo(),
                            id: params.id
                        }
                    }, function () {
                        layer.msg("信息保存成功！");
                        goto(masterpage.ctxp + "/tasks", {withBackBtn: false});

                    }, function () {
                        $("#saveBtn").removeAttr("disabled");
                    });
                });


                // 返回按钮点击事件
                $("#backBtn").on("click", function () {
                    var params = pageLogic.getUrlParams();
                    if(params.id){
                        goto(masterpage.ctxp + "/tasks?tab=phone", {withBackBtn: false});
                    }else {
                        goto(masterpage.ctxp + "/tasks", {withBackBtn: false});
                    }

                });

            },

            load: function () {
                pageLogic.newHouse();
            }
        }
    };

    // 新建房屋
    pageLogic.newHouse = function () {
        var seq = $("#houseContainer form").length;
        var html = _.template($('#tpl_house').html())({index: seq});
        $("#houseContainer").append(html);
        
        // add by xujc 2019/5/14 start
        var params = pageLogic.getUrlParams();
        // 如果有id这个参数就是由手机申请过来的
        if(params.id){
            deffer = $.getJSON(masterpage.ctxp + '/phone/'+params.id);
            deffer.done(function (data) {
                var apply =  data.data;
                    $('#houseContainer input[name="address"]').val(apply.identifyHouseAddress);
            })
        }

       // add by xujc 2019/5/14 end
        var $targetForm = $("#houseContainer form:eq(" + seq + ")");

        //房屋结构下拉框
        common.dictSelects([
            {jqueryObj: $targetForm.find("select[name=zone]"), key: "ZONE"},
            {jqueryObj: $targetForm.find("select[name=street]"), key: "STREET"}
        ]);

        // 行政区属与街道联动
        $targetForm.find("select[name=zone]").on("change", function () {
            common.select({
                jqueryObj: $targetForm.find("select[name=street]"),
                url: "/sys/dicts/groups/keys/STREET/" + this.value + "/items",
                data: {},
                textField: "name",
                valueField: "value",
                headerKey: "请选择",
                headerValue: ""
            });
        });

        $targetForm.validator({
            rules: {
                zone: {required: true},
                street: {required: true},
                address: {required: true, maxLen: 500},
                hillock: {maxLen: 100}
            }
        });

        pageLogic.newSplit($targetForm);
    };

    // 新建分栋
    pageLogic.newSplit = function ($form) {
        var $splitInfoDiv = $form.next();
        var seq = $splitInfoDiv.find("form").length;
        var html = _.template($('#tpl_houseSplit').html())({index: seq});
        $splitInfoDiv.append(html);

        var $targetForm = $splitInfoDiv.find("form:eq(" + seq + ")");

        //房屋结构下拉框
        var $structure = common.dictSelect({
            jqueryObj: $targetForm.find("select[name=structure]"), key: "HOUSE_STRUCTURE"
        });

        // 获取设计用途下拉框
        common.dictSelect({
            id: "propertyNature"+seq,
            key: "PROPERTY_NATURE"
        });

        $structure.deferred.done(function () {
            // 复制第一个分栋信息
            if ($splitInfoDiv.find("form").length === 0) return;

            var $firstForm = $splitInfoDiv.find("form:eq(0)");
            $targetForm.find("input[name=houseName]").val($firstForm.find("input[name=houseName]").val());
            $targetForm.find("select[name=structure]").val($firstForm.find("select[name=structure]").val());
            $targetForm.find("input[name=layerAbove]").val($firstForm.find("input[name=layerAbove]").val());
            $targetForm.find("input[name=layerUnder]").val($firstForm.find("input[name=layerUnder]").val());
            $targetForm.find("input[name=buildYear]").val($firstForm.find("input[name=buildYear]").val());
            $targetForm.find("input[name=purpose]").val($firstForm.find("input[name=purpose]").val());
            $targetForm.find("input[name=area]").val($firstForm.find("input[name=area]").val());
            $targetForm.find("input[name=identifyArea]").val($firstForm.find("input[name=identifyArea]").val());
            $targetForm.find("input[name=position]").val($firstForm.find("input[name=position]").val());
            $targetForm.find("input[name=position]").val($firstForm.find("input[name=position]").val());
            $targetForm.find("input[name=nature]").val($firstForm.find("input[name=nature]").val());
            $targetForm.find("input[name=holderPerson]").val($firstForm.find("input[name=holderPerson]").val());
            $targetForm.find("input[name=person]").val($firstForm.find("input[name=person]").val());
            $targetForm.find("input[name=constructOrg]").val($firstForm.find("input[name=constructOrg]").val());
            $targetForm.find("input[name=designOrg]").val($firstForm.find("input[name=designOrg]").val());
            $targetForm.find("input[name=prospectOrg]").val($firstForm.find("input[name=prospectOrg]").val());
            $targetForm.find("input[name=supervisionOrg]").val($firstForm.find("input[name=supervisionOrg]").val());
        });
        //图纸材料上传组件
        pageLogic.initViewFile( $targetForm.find("div[name=constructFile]"));
        // form验证信息
        //update by wangwj 20190116 start
        $targetForm.validator({
            rules: {
                houseName: {required: true, maxLen: 100},
                structure: {required: true},
                layerAbove: {required: true, maxLen: 2, digits: true},
                layerUnder:{required:false, maxLen:2, digits: true},
                buildYear: {required: true, maxLen: 10},
                purpose: {required: true, maxLen: 100},
                area: {required: true, number: true, twoDecimal: $targetForm.find("input[name=area]").val()},
                identifyArea: {required: true, number: true, twoDecimal: $targetForm.find("input[name=identifyArea]").val()},
                position: {maxLen: 100},
                nature: {maxLen: 100},
                holderPerson: {required: false, maxLen: 100},
                person: {required: false, maxLen: 100}
            }
        });
    };
    //update by wangwj 20190116 end

    // 申请鉴定内容click
    pageLogic.showOrHide = function () {
        $("#ratingLevelArea").hide();
        $("#ratingLevelArea div").remove();

        var identifyValue = $("input:radio[name='identifyType']:checked").val();

        var $otherContent = $("#otherContent");

        if (identifyValue === "8") {
            $otherContent.show();
            return;
        }

        $otherContent.hide();

        if (identifyValue === "7") {
            $("#newProjectInfo").show();
        } else {
            $("#newProjectInfo").hide();
        }

        common.postJSON({
            url: "/sys/dicts/groups/keys/RATING_LEVEL/" + identifyValue + "/items"
        }, function (response) {
            if (!Array.isArray(response) || response.length === 0) return;
            $("#ratingLevelArea").show().append(_.template($("#tpl_groupBox").html())({data: response}));
        });
    };

    // 显示评级类型选择框
    pageLogic.showRatingType = function () {
        $("#ratingTypeArea").remove();
        var ratingLevel = $("input:radio[name='ratingLevel']:checked").val();

        common.postJSON({
            url: "/sys/dicts/groups/keys/RATING_TYPE/" + ratingLevel + "/items",
            data: {}
        }, function (response) {
            if (!Array.isArray(response) || response.length === 0) return;
            $("#ratingLevelArea").show().append(_.template($("#tpl_typeArea").html())({data: response}));
        });
    };

    // 鉴定内容信息
    var getIdentifyContent = function () {
        return {
            content: $("input:radio[name='identifyType']:checked").val(),
            ratingLevel: $("input:radio[name='ratingLevel']:checked").val(),
            ratingType: $("input:radio[name='ratingType']:checked").val(),
            otherContent: $("#otherContent").val()
        };
    };

    // 委托人信息
    var getClientInfo = function () {
        var clientObject = $("#clientForm").serializeObject();
        clientObject.certificateFile = $('#certificateFile').getUploaderFileIds();
        clientObject.IDFile = $('#IDFile').getUploaderFileIds();
        clientObject.otherFile = $('#otherFile').getUploaderFileIds();
        clientObject.content = $("#content").val();
        clientObject.condition = $("#condition").val();
        clientObject.clientIdentifyFile = $("#clientIdentifyFile").getUploaderFileIds();

        return clientObject;
    };

    /*
     *房屋信息
     */
    var getHouseInfo = function () {
        var houseInfoArray = [];
        var flag = false;
        $("#houseContainer > form").each(function () {
            var houseInfo = $(this).serializeObject();
            houseInfo.splitArray = [];

            $(this).next().find("form").each(function (i) {
                var splitInfo = $(this).serializeObject();

                splitInfo.constructFile = $(this).find("div[name=constructFile]").getUploaderFileIds();
                houseInfo.splitArray.push(splitInfo);
            });

            houseInfoArray.push(houseInfo);
        });
        if(flag){
            return false;
        }else {
            return houseInfoArray;
        }

    };

    // 新建工程信息
    var getNewProjectInfo = function () {
        var newProObject = $("#newProForm").serializeObject();
        newProObject.clientFile = $('#clientFile').getUploaderFileIds();
        newProObject.reportFile = $('#reportFile').getUploaderFileIds();
        newProObject.structureFile = $('#structureFile').getUploaderFileIds();
        newProObject.foundationFile = $('#foundationFile').getUploaderFileIds();
        newProObject.identifyHouseFile = $('#identifyHouseFile').getUploaderFileIds();

        return newProObject;
    };

    var validatorInfo = function () {
        var content = $("input:radio[name='identifyType']:checked").val();
        if (!content) {
            layer.msg("鉴定内容不能为空！");
            return false;
        }

        var ratingLevelObject = $("input:radio[name='ratingLevel']:checked");
        if ($("#ratingLevelArea div").length > 0) {
            if (!ratingLevelObject.val()) {
                layer.msg("鉴定内容二级类型不能为空！");
                return false;
            }
        }

        var ratingTypeObject = $("input:radio[name='ratingType']:checked");
        if ($("#ratingTypeArea label").length > 0) {
            if (!ratingTypeObject.val()) {
                layer.msg("鉴定内容三级类型不能为空！");
                return false;
            }
        }

        if ("8" === content) {
            var otherContent = $("#otherContent").val();
            if (!otherContent) {
                layer.msg("鉴定内容选择其他时，请填写相关内容！");
                return false;
            }
        }


        var shouldReturn = false;
        $("#houseContainer > form").each(function () {
            if (!$(this).valid()) {
                if (!shouldReturn) { shouldReturn = true;}
            }

            $(this).next().find("form").each(function () {
                if (!$(this).valid()) {
                    if (!shouldReturn) { shouldReturn = true;}
                }

                var p = $(this).find("input[name=person]").val();
                var hp = $(this).find("input[name=holderPerson]").val();

                if (!p && !hp) {
                    layer.msg("产权人与使用人至少填写一个！");
                    if (!shouldReturn) { shouldReturn = true;}
                }
            });

        });


        if (shouldReturn) {return false;}


        if (!$("#clientForm").valid()) {
            return false;
        }




        if ("7" === content) {
            if (!$("#newProForm").valid()) {
                return false;
            }
        }

        if (!$("#endClientForm").valid()) {
            return false;
        }

        var clientIds = $("#clientIdentifyFile").getUploaderFileIds();
        if (clientIds.length === 0) {
            layer.msg("请上传鉴定委托书");
            return false;
        }

        return true;
    };

    window.pageLogic = pageLogic;
})(window);
/* add by panqh 2018-11-14 end */