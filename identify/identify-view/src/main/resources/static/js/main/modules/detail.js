
// add by xujc 2018/11/30 start
;(function (window) {
    // update by xujc 2019/1/31 start
    var pageLogic = {
        /**
         * @author : xujc
         * @date :2018/11/27
         * @Description : 获取url上的参数
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
        /**
         * @author : xujc
         * @date :2019/2/21
         * @Description : 初始化组件并回填
         */
        toFile: function (div,name,arr) {
            pageLogic.initViewFile(div.find('[name="'+name+'"]'), arr);
            div.find('[name="'+name+'"]').resetUploader({model: "viewer"}, arr);
        },
        showThis: function ($obj) {
            var li = $obj.parent('li');
            var href =$obj.attr('href');
            //var liIndex = li.index() + 1;
            if (li.hasClass('open')) {
                $(href+'_cont').hide();
                li.css('height', '');
            } else {
                $(href+'_cont').show();
                a = $(li).offsetHeight;
                b = $(href).height();
                var liH = a > b ? a : b;
                li.css('height', liH);
            }
            li.toggleClass('open');
        },
        showCorrectApply: function (recoud,from) {
            var div = $("<div></div>");
            $("#CorrectDetail").append(div);
            var deffered = $.postJSON(masterpage.ctxp + "/correctMain/getApply/" + recoud.correctId, {});
            deffered.done(function (data) {
                var correct = data.data;
                // 将补正状态赋值到页面
                $("#correctStatus").val(correct.status);
                var obj ={
                    correct:correct,
                    recoud:recoud
                };
                var html = _.template($('#tpl_correctApply').html())({data: obj});
                div.append(html);

                div.find('[name="address"]').text(correct.identifyMain.house.zone + correct.identifyMain.house.street + correct.identifyMain.house.address);

                if (correct.identifyMain.method == "1") {
                    div.find('[name="method"]').text("人工窗口");
                } else if (correct.identifyMain.method == "2") {
                    div.find('[name="method"]').text("我的南京");
                } else if (correct.identifyMain.method == "3") {
                    div.find('[name="method"]').text("房产微政务");
                }

                // update by xujc 2019/2/21 start
                // 找到申請步驟的鑒定報告明細ids
                var ids = correct.reportDetailIds;
                var str = '';
                // 如果是汇总上传，编制报告id在report表中能找到
                if(correct.identifyReport.identifyFileId){
                    // 房屋名称从详细表中找，文件id从report表中找
                    $.each(correct.identifyReport.reportDetailList,function (i,reportDetail) {
                        if(ids.indexOf(reportDetail.id) !=-1){
                            if(i==0){
                                str +='<tr><td>'+reportDetail.houseName+'</td><td>'+reportDetail.identifyResultName+'</td><td name="identifyFileId" rowspan="'+correct.identifyReport.reportDetailList.length+'"></td></tr>';
                            }else {
                                str +='<tr><td>'+reportDetail.houseName+'</td><td>'+reportDetail.identifyResultName+'</td></tr>';
                            }
                        }
                    });
                    div.find("[name='correctApply']").append(str);

                    pageLogic.initViewFile(div.find("[name='identifyFileId']"),[correct.identifyReport.identifyFileId]);
                    div.find("[name='identifyFileId']").resetUploader({model: 'viewer'},[correct.identifyReport.identifyFileId]);
                }else {

                    $.each(correct.identifyReport.reportDetailList,function (i,reportDetail) {
                        if(ids.indexOf(reportDetail.id) !=-1){
                            var str = '';
                            str +='<tr><td>'+reportDetail.houseName+'</td><td>'+reportDetail.identifyResultName+'</td>';
                            str +='<td name="identifyFileId'+i+'"></td></tr>';
                            div.find("[name='correctApply']").append(str);
                            pageLogic.initViewFile( div.find("[name='identifyFileId"+i+"']"),[reportDetail.identifyFileId]);
                            div.find("[name='identifyFileId"+i+"']").resetUploader({model: 'viewer'},[reportDetail.identifyFileId]);
                        }

                    })
                }
                // update by xujc 2019/2/21 end
                if(from ==2){
                    $("#ul li:eq(2) a").trigger("click");
                }

            })
        },
        showCorrectCheck: function (recoud) {
            var div = $("<div></div>");
            $("#CorrectDetail").append(div);

            var deffered = $.postJSON(masterpage.ctxp + "/correctMain/getCheck/" + recoud.correctId, {});
            deffered.done(function (data) {
                var correctVerify = data.data;
                var obj = {
                    correctVerify: correctVerify,
                    recoud: recoud
                };
                var html = _.template($('#tpl_correctCheck').html())({data: obj});
                div.append(html);
                if (correctVerify.checkResult == 1) {
                    div.find('[name="checkResult"]').text("审核通过");
                } else {
                    div.find('[name="checkResult"]').text("审核不通过");
                }

            });
        },
        showCorrrectFiles: function (recoud) {
            var div = $("<div></div>");
            $("#CorrectDetail").append(div);

            var deffered = $.postJSON(masterpage.ctxp + "/correctMain/getApply/" + recoud.correctId, {});
            deffered.done(function (data) {
                var correct = data.data;
                var obj = {
                    correct: correct,
                    recoud: recoud
                };
                var html = _.template($('#tpl_corrrectFiles').html())({data: obj});
                div.append(html);
                pageLogic.initViewFile(div.find('[name="correctFiles"]'), correct.correctFileIds);
                div.find('[name="correctFiles"]').resetUploader({model: 'viewer'}, correct.correctFileIds);
            });
        },
        showReviewApply: function (recoud,from) {

            var div = $("<div></div>");
            $("#ReviewMain").append(div);
            var deffered = $.postJSON(masterpage.ctxp + "/reviewMain/getApply/" + recoud.reviewId, {});
            deffered.done(function (data) {
                var review = data.data;
                 // 将复审状态赋值到页面
                $("#reviewStatus").val(review.status);
                var obj = {
                    review: review,
                    recoud: recoud
                };
                var html = _.template($('#tpl_reviewApply').html())({data: obj});
                div.append(html);
                div.find("[name='address']").text(review.identifyMain.house.zone + review.identifyMain.house.street + review.identifyMain.house.address);
                pageLogic.initViewFile(div.find("[name='sheetId']"), [review.sheetId]);
                div.find("[name='sheetId']").resetUploader({model: 'viewer'}, [review.sheetId]);
                $("#orgName").text(review.identifyMain.orgName);
                if (review.identifyMain.method == "1") {
                    div.find("[name='method']").text("人工窗口");
                } else if (review.identifyMain.method == "2") {
                    div.find("[name='method']").text("我的南京");
                } else if (review.identifyMain.method == "3") {
                    div.find("[name='method']").text("房产微政务");
                }


                // update by xujc 2019/2/21 start
                // 找到申請步驟的鑒定報告明細ids
                var ids = review.reportDetailIds;
                var str = '';
                // 如果是汇总上传，编制报告id在report表中能找到
                if(review.identifyReport.identifyFileId){
                    // 房屋名称从详细表中找，文件id从report表中找
                    $.each(review.identifyReport.reportDetailList,function (i,reportDetail) {
                        if(ids.indexOf(reportDetail.id) !=-1){
                            if(i==0){
                                str +='<tr><td>'+reportDetail.houseName+'</td><td>'+reportDetail.identifyResultName+'</td><td name="identifyFileId" rowspan="'+review.identifyReport.reportDetailList.length+'"></td></tr>';
                            }else {
                                str +='<tr><td>'+reportDetail.houseName+'</td><td>'+reportDetail.identifyResultName+'</td></tr>';
                            }
                        }
                    });
                    div.find("[name='reviewApply']").append(str);

                    pageLogic.initViewFile(div.find("[name='identifyFileId']"),[review.identifyReport.identifyFileId]);
                    div.find("[name='identifyFileId']").resetUploader({model: 'viewer'},[review.identifyReport.identifyFileId]);
                }else {

                    $.each(review.identifyReport.reportDetailList,function (i,reportDetail) {
                        if(ids.indexOf(reportDetail.id) !=-1){
                            var str = '';
                            str +='<tr><td>'+reportDetail.houseName+'</td><td>'+reportDetail.identifyResultName+'</td>';
                            str +='<td name="identifyFileId'+i+'"></td></tr>';
                            div.find("[name='reviewApply']").append(str);
                            pageLogic.initViewFile( div.find("[name='identifyFileId"+i+"']"),[reportDetail.identifyFileId]);
                            div.find("[name='identifyFileId"+i+"']").resetUploader({model: 'viewer'},[reportDetail.identifyFileId]);
                        }

                    })
                }
                // update by xujc 2019/2/21 end
                // 专家数
                var expertCount = "";
                $.each(review.expertAppoints, function (i, ele) {
                    expertCount += ele.expertFieldName + " : " + ele.expertNo + " ; ";
                })
                div.find("[name='expertCount']").text(expertCount);
                // 指定回避专家
                var expertExcepts = "";
                $.each(review.experts, function (i, ele) {
                    expertExcepts += ele.name + ",";
                })
                if (expertExcepts) {
                    div.find("[name='expertExcepts']").text(expertExcepts.substr(0, expertExcepts.length - 1));
                }
                if(from ==1){
                    $("#ul li:eq(1) a").trigger("click");
                }

            })
        },
        showReviewAccept: function (recoud) {
            var div = $("<div></div>");
            $("#ReviewMain").append(div);
            var deffered = $.postJSON(masterpage.ctxp + "/reviewMain/getAccept/" + recoud.reviewId, {});
            deffered.done(function (data) {
                var reviewAccept = data.data;
                var obj = {
                    reviewAccept: reviewAccept,
                    recoud: recoud
                };
                var html = _.template($('#tpl_reviewAccept').html())({data: obj});
                div.append(html);
            });

        },
        showReviewExpert: function (recoud) {
            var div = $("<div></div>");
            $("#ReviewMain").append(div);
            var deffered = $.postJSON(masterpage.ctxp + "/reviewMain/getReviewExpert", {
                reviewId: recoud.reviewId,
                type: 1
            });
            deffered.done(function (data) {
                var reviewExperts = data.data;
                var obj = {
                    reviewExperts: reviewExperts,
                    recoud: recoud
                };
                var html = _.template($('#tpl_reviewExpert').html())({data: obj});
                div.append(html);
                var reviewExpertsMsg = '';
                $.each(reviewExperts, function (i, reviewExpert) {
                    reviewExpertsMsg += reviewExpert.fieldName + ":";
                    reviewExpertsMsg += reviewExpert.expertMsg + "<br/>";
                })
                div.find("[name='reviewExpertsMsg']").html(reviewExpertsMsg);
            });

        },
        showReviewConfirm: function (recoud) {
            var div = $("<div></div>");
            $("#ReviewMain").append(div);
            var deffered = $.postJSON(masterpage.ctxp + "/reviewMain/getReviewExpert", {
                reviewId: recoud.reviewId,
                type: 2
            });
            deffered.done(function (data) {
                var reviewExperts = data.data;
                var obj = {
                    reviewExperts: reviewExperts,
                    recoud: recoud
                };
                var html = _.template($('#tpl_reviewConfirm').html())({data: obj});
                div.append(html);

                var reviewExpertsMsg = '';
                $.each(reviewExperts, function (i, reviewExpert) {
                    reviewExpertsMsg += reviewExpert.fieldName + ":";
                    reviewExpertsMsg += reviewExpert.expertMsg + "<br/>";
                })
                div.find("[name='reviewExpertsMsg']").html(reviewExpertsMsg);
            });
        },
        showExpertOpinion: function (recoud) {
            var div = $("<div></div>");
            $("#ReviewMain").append(div);
            var deffered = $.postJSON(masterpage.ctxp + "/reviewMain/getReviewOpinion", {reviewId: recoud.reviewId});
            deffered.done(function (data) {
                var reviewOpinion = data.data;
                var obj = {
                    reviewOpinion: reviewOpinion,
                    recoud: recoud
                };
                var html = _.template($('#tpl_reviewOpinion').html())({data: obj});
                div.append(html);

                pageLogic.initViewFile(div.find("[name='opinionFileIds']"), reviewOpinion.opinionFileIds);
                div.find("[name='opinionFileIds']").resetUploader({model: 'viewer'}, reviewOpinion.opinionFileIds);
            })

        },
        /**
         * @author : xujc
         * @date :2018/11/26
         * @Description : 获取申请信息
         *
         */

        showMain: function (recoud,from) {
            var param = pageLogic.getUrlParams();
            if (param.type == 0) {
                // 返回按钮隐藏
                $("#fanhui").parent('.button_area').remove();
                $("div#form").removeAttr("id");
            }
            var div = $("<div></div>");
            div.appendTo($("#IdentifyMain"));

            var deffered = $.postJSON(masterpage.ctxp + "/identifies/getMain/" + param.mainId, {});
            deffered.done(function (data) {
                var identifyMain = data.data;
                var obj = {
                    identifyMain: identifyMain,
                    recoud: recoud
                }
                var html = _.template($('#tpl_main').html())({data: obj});
                div.append(html);
                // update by xujc 2019/2/22 start
                // 回填鉴定内容
                var jdnr = identifyMain.jd;
                if(identifyMain.ratingLevelName){
                    jdnr +='-' +identifyMain.ratingLevelName.trim();
                }
                if(identifyMain.ratingTypeName){
                    jdnr +='-' +identifyMain.ratingTypeName.trim();
                }
                if('其他' ==jdnr){
                    jdnr+='('+identifyMain.otherContent+')';
                }
                div.find("[name='jdnr']").text(jdnr);
                // update by xujc 2019/2/22 end
                div.find("[name='idengtifyAddress']").text(identifyMain.house.zone + identifyMain.house.street + identifyMain.house.address);
                var arr1 = [];
                var arr2 = [];
                var arr3 = [];
                $.each(identifyMain.client.clientFileList, function (i, ele) {
                    if (ele.type === 1) {
                        arr1.push(ele.fileId);
                    } else if (ele.type === 2) {
                        arr2.push(ele.fileId);
                    } else {
                        arr3.push(ele.fileId);
                    }
                });
                pageLogic.initViewFile(div.find('[name="cFileId"]'), arr1);
                pageLogic.initViewFile(div.find('[name="sFileId"]'), arr2);
                pageLogic.initViewFile(div.find('[name="qFileId"]'), arr3);
                pageLogic.initViewFile(div.find('[name="clientIdentifyFile"]'), [identifyMain.client.clientIdentifyFileId]);
                div.find('[name="cFileId"]').resetUploader({model: "viewer"}, arr1);
                div.find('[name="sFileId"]').resetUploader({model: "viewer"}, arr2);
                div.find('[name="qFileId"]').resetUploader({model: "viewer"}, arr3);
                div.find('[name="clientIdentifyFile"]').resetUploader({model: "viewer"}, [identifyMain.client.clientIdentifyFileId]);
                var arrs = [];
                $.each(identifyMain.house.houseSplitList, function (i, ele) {
                    //add by wangwj 20181214 start
                    var jg = ele.jg;
                    if (jg === "其他") {
                        jg = ele.otherContent;
                    }
                    var layerUnder = ele.layerUnder;
                    if (layerUnder == null) {
                        layerUnder = '';
                    }
                    //add by wangwj 20181214 end
                    var str = '<table class="table table-bordered table-data"><colgroup><col width="20%"><col width="25%"><col width="20%"><col width="25%"></colgroup>';
                    str += "<thead><tr><th style='text-align: right'>房屋名称</th><td colspan='3'>" + ele.houseName + "</td><td style='border-top: hidden;border-right:hidden;border-bottom: hidden '><a class=\"btn btn-link btn-toggle\"> <i class='fa'></i></a></td></tr></thead>";

                    str += "<tbody><tr><th>结构类型</th><td>" + jg + "</td><th>房屋层数(地上)</th><td>" + ele.layerAbove + "</td></tr>";
                    str += "<tr><th>房屋层数(地下)</th><td colspan='3'>" + layerUnder + "</td></tr>";
                    str += "<tr><th>建筑年代</th><td>" + ele.buildYear + "</td><th>设计用途</th><td>" + ele.purpose + "</td></tr>";
                    str += "<tr><th>建筑面积</th><td>" + ele.area + "㎡</td><th>鉴定面积</th><td>" + ele.identifyArea + "㎡</td></tr>";
                    str += "<tr><th>鉴定部位</th><td>" + ele.position + "</td><th>权利性质</th><td>" + ele.nature + "</td></tr>";
                    str += "<tr><th>产权人</th><td>" + ele.holderPerson + "</td><th>使用人</th><td>" + ele.person + "</td></tr>";
                    str += "<tr><th>施工单位</th><td>" + ele.constructOrg + "</td><th>设计单位</th><td>" + ele.designOrg + "</td></tr>";
                    //add by wangwj 20181214 start
                    str += "<tr><th>勘查单位</th><td>" + ele.prospectOrg + "</td><th>监理单位</th><td>" + ele.supervisionOrg + "</td></tr>";
                    //add by wangwj 20181214 end
                    str += '<tr><th>图纸材料</th><td colspan="3"><div class="tFileId"></div></td></tr></tbody></table>';
                    $("#d2").append(str);
                    var arr = [];
                    $.each(ele.houseSplitFileList, function (k, e) {
                        arr.push(e.fileId);
                    });
                    arrs.push(arr);
                });
                $.each($(".tFileId"), function (i, ele) {
                    pageLogic.initViewFile($(this), arrs[i]);
                    $(this).resetUploader({model: 'viewer'}, arrs[i]);
                });
                // 回填新建工程信息中的文件
                if(identifyMain.newProject !=null){
                    $.each(identifyMain.newProject.newProFileList,function (i,newProFile) {
                        if(newProFile.type ==1){
                            pageLogic.toFile(div,"clientFile", [newProFile.fileId]);
                        }else if(newProFile.type ==2){
                            pageLogic.toFile(div,"reportFile", [newProFile.fileId]);
                        }else if(newProFile.type ==3){
                            pageLogic.toFile(div,"structureFile", [newProFile.fileId]);
                        }else if(newProFile.type ==4){
                            pageLogic.toFile(div,"foundationFile", [newProFile.fileId]);
                        }else if(newProFile.type ==5){
                            pageLogic.toFile(div,"identifyHouseFile", [newProFile.fileId]);
                        }
                    })
                }

                // 加载鉴定申请内容结束
                // 将鉴定状态赋值到页面
                $("#mainStatus").val(identifyMain.status);
                // from ==  3 是我的待办里的鉴定详细，不需要展示复审以及补正
                if(from == 3){
                    return;
                }
                // 判断有无复审和审核
                var isReview= identifyMain.isReview;
                var isCorrect = identifyMain.isCorrect;
                if(isCorrect ===1){
                    // 获取补正详细
                    var correctDeffered = $.postJSON(masterpage.ctxp + "/correctMain/getRecoud", {'mainId': param.mainId});
                    correctDeffered.done(function (data) {
                        var recouds = data.data;
                        pageLogic.correctRecouds=recouds;
                        $.each(recouds, function (i, ele) {
                            var status = ele.status;
                            if (status == 1) {
                                pageLogic.showCorrectApply(ele,from);
                            } else if (status == 2) {
                                pageLogic.showCorrectCheck(ele);
                            } else if (status == 3) {
                                pageLogic.showCorrrectFiles(ele);
                            }else if(status === 11){
                                pageLogic.showHasReasonSuspend(ele, 'correctMain','审核不通过','审核不通过');
                            }else if(status === 12){
                                pageLogic.showHasReasonSuspend(ele, 'correctMain','终止流程','已终止');
                            }else if(status === 13){
                                pageLogic.showHasReasonSuspend(ele, 'correctMain','挂起流程','已挂起');
                            } else if (status == 14) {
                                pageLogic.showNotReasonSuspend(ele, 'correctMain','办结');
                            }

                        });

                    });
                }else {
                    $("#ul a[name='3']").parent().hide();
                }
                if(isReview ===1){
                    // 获取复审详细
                    var reviewDeffered = $.postJSON(masterpage.ctxp + "/reviewMain/getReviewRe", {'mainId': param.mainId});
                    reviewDeffered.done(function (data) {
                        var recouds = data.data;
                        pageLogic.reviewRecouds = recouds;
                        $.each(recouds, function (i, ele) {
                            var status = ele.status;
                            if (status == 1) {
                                pageLogic.showReviewApply(ele,from);
                            } else if (status == 2) {
                                pageLogic.showReviewAccept(ele);
                            } else if (status == 3) {
                                pageLogic.showReviewExpert(ele);
                            } else if (status == 4) {
                                pageLogic.showReviewConfirm(ele);
                            } else if (status == 5) {
                                pageLogic.showExpertOpinion(ele);

                            } else if (status == 6 ||status == 14) {
                                // 使用无原因模板
                                pageLogic.showNotReasonSuspend(ele, 'reviewMain','办结');
                            }else if(status === 10){
                                pageLogic.showHasReasonSuspend(ele, 'reviewMain','不受理','不受理');
                            }else if(status === 12){
                                pageLogic.showHasReasonSuspend(ele, 'reviewMain','终止流程','已终止');
                            }else if(status === 13){
                                pageLogic.showHasReasonSuspend(ele, 'reviewMain','挂起流程','已挂起');
                            }

                        })


                    });
                }else {
                    $("#ul a[name='2']").parent().hide();
                }

                // 根据来源判断点击哪个tab
                if(!from){
                    $("#ul li:eq(0) a").trigger("click");
                }
            });

        },
        /**
         * @author : xujc
         * @date :2018/11/26
         * @Description : 获取受理信息
         */
        showIdentifyAccept: function (recoud) {
            var div = $("<div></div>");
            div.appendTo($("#IdentifyMain"));

            var deffered = $.postJSON(masterpage.ctxp + "/identifies/getAccept/" + recoud.mainId, {});
            deffered.done(function (data) {
                var identifyAccept = data.data;
                var obj = {
                    identifyAccept: identifyAccept,
                    recoud: recoud
                };
                var html = _.template($('#tpl_accept').html())({data: obj});
                div.append(html);
            });

        },
        /**
         * @author : xujc
         * @date :2018/11/26
         * @Description : 获取初勘信息
         */
        //update by wangwj 20181206 删除险情照片的列表显示
        showPreview: function (recoud) {
            var div = $("<div></div>");
            div.appendTo($("#IdentifyMain"));

            var deffered = $.postJSON(masterpage.ctxp + "/identifies/getPreview/" + recoud.mainId, {});
            deffered.done(function (data) {
                var identifyPreview = data.data;
                var obj = {
                    identifyPreview: identifyPreview,
                    recoud: recoud
                };
                var html = _.template($('#tpl_preview').html())({data: obj});
                div.append(html);


                var arr1 = [];
                // var arr2=[];
                var arr3 = [];
                $.each(identifyPreview.identifyPreviewFileList, function (i, ele) {
                    if (ele.type == "1") {
                        arr1.push(ele.fileId);
                    } else if (ele.type == "3") {
                        arr3.push(ele.fileId);
                    }
                });
                pageLogic.initViewFile(div.find('[name="photo1"]'), arr1);
                // $("#photo2").uploader({server: masterpage.uploadUrl});
                pageLogic.initViewFile(div.find('[name="photo3"]'), arr3);
                div.find('[name="photo1"]').resetUploader({model: 'viewer'}, arr1);
                // $("#photo2").resetUploader({model: 'viewer'},arr2);
                div.find('[name="photo3"]').resetUploader({model: 'viewer'}, arr3);

            });

        },
        /**
         * @author : wangwj
         * @date :2018/11/26
         * @Description: 显示出具方案信息
         */
        showPlan: function (recoud) {
            var div = $("<div></div>");
            div.appendTo($("#IdentifyMain"));
            var deffered = $.postJSON(masterpage.ctxp + "/identifies/getPlan/" + recoud.mainId, {});
            deffered.done(function (data) {
                var identifyPlan = data.data;
                var obj = {
                    identifyPlan: identifyPlan,
                    recoud: recoud
                };
                var html = _.template($('#tpl_plan').html())({data: obj});
                div.append(html);
                pageLogic.initViewFile(div.find("[name='fangAn1']"), [identifyPlan.fileId]);
                div.find("[name='fangAn1']").resetUploader({model: 'viewer'}, [identifyPlan.fileId]);
            });

        },
        /**
         * @author : xujc
         * @date :2018/11/26
         * @Description : 获取签订合同信息
         */
        //update by wangwj 20181205
        showContract: function (recoud) {

            var div = $("<div></div>");
            div.appendTo($("#IdentifyMain"));

            var deffered = $.postJSON(masterpage.ctxp + "/identifies/getContract/" + recoud.mainId, {});
            deffered.done(function (data) {
                var identifyContract = data.data;
                var obj = {
                    identifyContract: identifyContract,
                    recoud: recoud
                }
                var compiled = _.template($('#tpl_contract').html());
                var html = compiled({data:obj});
                div.append(html);

                var identifyContractFiles = [];
                $.each(identifyContract.identifyContractFileList, function (i, ele) {
                    identifyContractFiles.push(ele.fileId);
                });

                pageLogic.initViewFile(div.find("div[name=contractFile]"), identifyContractFiles);
                div.find("div[name=contractFile]").resetUploader({model: 'viewer'}, identifyContractFiles);

            });
        },

        /**
         * @author : xujc
         * @date :2018/11/26
         * @Description : 获取编制鉴定报告信息
         */
        showReport: function (recoud) {
            var div = $("<div></div>");
            div.appendTo($("#IdentifyMain"));

            var deffered = $.postJSON(masterpage.ctxp + "/identifies/getReport/" + recoud.businessKey);
            deffered.done(function (data) {
                var identifyReport = data.data;
                var obj={
                    identifyReport:identifyReport,
                    recoud:recoud
                };
                var compiled = _.template($('#tpl_report').html());
                var html = compiled({data:obj});
                div.append(html);

                // 分栋上传
                if (identifyReport.method === 1) {
                    div.find("table tr[name=fileTr]").hide();
                } else {
                    pageLogic.initViewFile(div.find("table tr[name=fileTr] div[name=identifyFileId]"), [identifyReport.identifyFileId]);
                    div.find("table tr[name=fileTr] div[name=identifyFileId]").resetUploader({model: 'viewer'}, [identifyReport.identifyFileId]);

                    pageLogic.initViewFile(div.find("table tr[name=fileTr] div[name=testingFileId]"), [identifyReport.testingFileId]);
                    div.find("table tr[name=fileTr] div[name=testingFileId]").resetUploader({model: 'viewer'}, [identifyReport.testingFileId]);
                    //汇总上传隐藏鉴定报告和测试报告列
                    div.find("[name='detailTable'] tr>th:eq(4)").hide();
                    div.find("[name='detailTable'] tr>th:eq(5)").hide();
                    div.find("[name='detailTable'] tr").find("td:eq(4)").hide();
                    div.find("[name='detailTable'] tr").find("td:eq(5)").hide();
                }

                $.each(identifyReport.reportDetailList, function (index, item) {
                    pageLogic.initViewFile(div.find("div[name=identifyFileId_" + index + "]"), [item.identifyFileId]);
                    div.find("div[name=identifyFileId_" + index + "]").resetUploader({model: 'viewer'}, [item.identifyFileId]);

                    pageLogic.initViewFile(div.find("div[name=testingFileId_" + index + "]"), [item.testingFileId]);
                    div.find("div[name=testingFileId_" + index + "]").resetUploader({model: 'viewer'}, [item.testingFileId]);
                });

            });
        },
        showVerify: function (recoud) {
            var div = $("<div></div>");
            div.appendTo($("#IdentifyMain"));
            var deffered = $.postJSON(masterpage.ctxp + "/identifies/getVerify/" + recoud.businessKey, {});
            deffered.done(function (data) {
                var verify = data.data;
                var obj={
                    verify:verify,
                    recoud:recoud
                };
                var compiled = _.template($('#tpl_verify').html());
                var html = compiled({data:obj});
                div.append(html);

            })
        },

        /**
         * @author : xujc
         * @date :2018/12/11
         * @Description : 获取非正常信息(不含原因)
         */
        showNotReasonSuspend: function (recoud, title,status) {
            var div = $("<div></div>");
            var divId ='';
            if(title =='identifyMain'){
                divId ='IdentifyMain';
            }else if(title =='reviewMain'){
                divId ='ReviewMain';
            }else {
                divId ='CorrectMain';
            }
            div.appendTo($("#"+divId));

            var obj = {
                title: title,
                recoud: recoud,
                status:status
            };
            var html = _.template($('#tpl_notReason').html())({data: obj});
            div.append(html);

        },
        /**
         * @author : xujc
         * @date :2018/12/11
         * @Description : 获取非正常信息(含原因)
         * @param: headText 什么原因
         * @param: title (identifyMain,reviewMain,correctMain)
         * @param: status 办结/不受理/已受理等
         */
        showHasReasonSuspend: function (recoud, title,headText,status) {
            var div = $("<div></div>");
            var divId ='';
            if(title =='identifyMain'){
                divId ='IdentifyMain';
            }else if(title =='reviewMain'){
                divId ='ReviewMain';
            }else {
                divId ='CorrectMain';
            }
            div.appendTo($("#"+divId));
            var obj = {
                title: title,
                recoud: recoud,
                headText:headText,
                status:status
            };
            var html = _.template($('#tpl_hasReason').html())({data: obj});
            div.append(html);

        },

        init: {
            before: function () {
               var param = pageLogic.getUrlParams();
                // from 1代表 复审 // 2代表补正 无代表鉴定
                var from =param.from;
                // 获取鉴定详细
                pageLogic.mainDeferred = $.postJSON(masterpage.ctxp + "/identifies/getRecoud/" + param.mainId, {});

                pageLogic.mainDeferred.done(function (data) {
                    var recouds = data.data;
                    pageLogic.mainRecouds = recouds;
                    // 只有鉴定业务时
                    if(param.onlyMain){
                        from ==3;
                        $('#IdentifyMain').show();
                        $('#ReviewMain').hide();
                        $('#CorrectDetail').hide();
                        $("#ul").remove();
                        var data = {};
                        data.name = 'identifyMain';
                        $.each(pageLogic.mainRecouds, function (i, recoud) {
                            var status = recoud.status;
                            if (status === 1) {
                                recoud.title = '鉴定委托';
                            } else if (status === 2) {
                                recoud.title = '受理';
                            } else if (status === 3) {
                                recoud.title = '初勘';
                            } else if (status === 4) {
                                recoud.title = '出具方案';
                            } else if (status === 5) {
                                recoud.title = '签订合同';
                            } else if (status === 6) {
                                recoud.title = '编制鉴定报告';
                            } else if (status === 7 || status ===11) {
                                recoud.title = '审核鉴定报告';
                            } else if (status == 8) {
                                recoud.title = '签发鉴定报告';
                            } else if (status == 9) {
                                recoud.title = '发放鉴定报告';
                            } else if (status === 10) {
                                recoud.title = '不受理信息';
                            }  else if (status == 12) {
                                recoud.title = '终止流程';
                            } else if (status == 13) {
                                recoud.title = '挂起流程';
                            } else if (status === 14) {
                                recoud.title = '恢复流程';
                            }
                        })
                        data.recouds = pageLogic.mainRecouds;
                        var html = _.template($('#tpl_title').html())({data: data});
                        $("#title").empty().append(html);
                    }
                    $.each(recouds, function (i, ele) {
                        var status = ele.status;
                        if (status === 1) {
                            pageLogic.showMain(ele,from);
                        } else if (status === 2) {
                            pageLogic.showIdentifyAccept(ele);
                        } else if (status === 3) {
                            pageLogic.showPreview(ele);
                        } else if (status === 4) {
                            pageLogic.showPlan(ele);
                        } else if (status === 5) {
                            pageLogic.showContract(ele);
                        } else if (status === 6) {
                            pageLogic.showReport(ele);
                        } else if (status === 7) {
                            pageLogic.showVerify(ele);
                        } else if (status === 8 || status === 9 || status === 14) {
                            pageLogic.showNotReasonSuspend(ele, 'identifyMain','办结');
                        }else if (status === 10) {
                            pageLogic.showHasReasonSuspend(ele, 'identifyMain','不受理','不受理');
                        }else if(status === 11){
                           pageLogic.showHasReasonSuspend(ele,'identifyMain','审核不通过','审核不通过');
                        }else if(status === 12){
                            pageLogic.showHasReasonSuspend(ele, 'identifyMain','终止流程','已终止');
                        }else if(status === 13){
                            pageLogic.showHasReasonSuspend(ele, 'identifyMain','挂起流程','已挂起');
                        }
                    });
                });

            },
            after: function () {
            },

            //页面控件事件绑定(一般为按钮的事件绑定)
            events: function () {
                var param = pageLogic.getUrlParams();
                //給返回按钮添加点击事件
                // type 0隐藏返回按钮; 1返回我的待办任务; 2返回复审列表;3返回补正列表
                if (param.type === "1") {
                    $("#fanhui").on("click", function () {
                        goto(masterpage.ctxp + "/tasks", {withBackBtn: false});
                    });
                } else if (param.type === "2") {
                    $("#fanhui").on("click", function () {
                        goto(masterpage.ctxp + "/reviewMain", {withBackBtn: false});
                    });
                } else if (param.type === "3") {
                    $("#fanhui").on("click", function () {
                        goto(masterpage.ctxp + "/correctMain", {withBackBtn: false});
                    });
                } else {
                    $("#fanhui").on("click", function () {
                        goto(masterpage.ctxp + "/identifies", {withBackBtn: false});
                    });
                }
                // 给tab标签加点击事件
                $("#pageContent").find(".toContainText").on('click', function (e) {
                    $(this).parent('li').addClass('active').siblings('li').removeClass('active');
                      // 显示自己的内容 隐藏其他的内容
                    if(!$(this).parent().attr('style')){
                            var value = $(this).attr('name');
                            if(value ==='1'){
                                $('#IdentifyMain').show();
                                $('#ReviewMain').hide();
                                $('#CorrectDetail').hide();
                            }else if(value ==='2'){
                                $('#IdentifyMain').hide();
                                $('#ReviewMain').show();
                                $('#CorrectDetail').hide();
                            }else {
                                $('#IdentifyMain').hide();
                                $('#ReviewMain').hide();
                                $('#CorrectDetail').show();
                            }
                        }
                    // 显示自己的横条，删除其他的横条
                    $('.portlet-head').remove();
                    // 填充title 并显示横条
                    var param = pageLogic.getUrlParams();
                    var data = {};
                    var value = $(e.target).attr('name');
                    if (value == 1) {
                        // 1代表鉴定委托
                        // 获取主表状态
                        var status = $("#mainStatus").val();
                        // 加载标头
                        showProcess(parseInt(status),'isDetail');

                        data.name = 'identifyMain';
                        $.each(pageLogic.mainRecouds, function (i, recoud) {
                            var status = recoud.status;
                            if (status === 1) {
                                recoud.title = '鉴定委托';
                            } else if (status === 2) {
                                recoud.title = '受理';
                            } else if (status === 3) {
                                recoud.title = '初勘';
                            } else if (status === 4) {
                                recoud.title = '出具方案';
                            } else if (status === 5) {
                                recoud.title = '签订合同';
                            } else if (status === 6) {
                                recoud.title = '编制鉴定报告';
                            } else if (status === 7 || status ===11) {
                                recoud.title = '审核鉴定报告';
                            } else if (status == 8) {
                                recoud.title = '签发鉴定报告';
                            } else if (status == 9) {
                                recoud.title = '发放鉴定报告';
                            } else if (status === 10) {
                                recoud.title = '不受理信息';
                            }  else if (status == 12) {
                                recoud.title = '终止流程';
                            } else if (status == 13) {
                                recoud.title = '挂起流程';
                            } else if (status === 14) {
                                recoud.title = '恢复流程';
                            }

                        })
                        data.recouds = pageLogic.mainRecouds;
                    } else if (value == 2) {
                        // 2代表复核
                        // 获取主表状态
                        var status = $("#reviewStatus").val();
                        showReviewProcess(parseInt(status),'isDetail');
                        data.name = 'reviewMain';
                        $.each(pageLogic.reviewRecouds, function (i, recoud) {
                            var status = recoud.status;
                            if (status == 1) {
                                recoud.title = '复核申请';
                            } else if (status == 2) {
                                recoud.title = '受理意见';
                            } else if (status == 3) {
                                recoud.title = '选择专家';
                            } else if (status == 4) {
                                recoud.title = '确认专家';
                            } else if (status == 5) {
                                recoud.title = '填写意见';
                            } else if (status == 6) {
                                recoud.title = '办结';
                            } else if (status == 10) {
                                recoud.title = '不受理信息';
                            } else if (status == 12) {
                                recoud.title = '终止流程';
                            } else if (status == 13) {
                                recoud.title = '挂起流程';
                            } else if (status == 14) {
                                recoud.title = '恢复流程';
                            }
                        })
                        data.recouds = pageLogic.reviewRecouds;
                    } else {
                        // 3补正变更
                            var status =$("#correctStatus").val();
                            showCorrectProcess(parseInt(status),'isDetail');
                        //update by wangwj 20190124 start
                        data.name = 'correctMain';
                        $.each(pageLogic.correctRecouds, function (i, recoud) {
                            var status = recoud.status;
                            if (status == 1) {
                                recoud.title = '变更申请';
                            } else if (status == 2) {
                                recoud.title = '审核';
                            } else if (status == 3) {
                                recoud.title = '上传资料';
                            } else if (status == 11) {
                                recoud.title = '审核不通过';
                            } else if (status == 12) {
                                recoud.title = '终止流程';
                            } else if (status == 13) {
                                recoud.title = '挂起流程';
                            } else if (status == 14) {
                                recoud.title = '恢复流程';
                            }

                        });
                        data.recouds = pageLogic.correctRecouds;
                    }
                    //update by wangwj 20190124 end
                    var html = _.template($('#tpl_title').html())({data: data});
                    $("#title").empty().append(html);
                });

                // 给标题列的链接加点击事件

                $("#pageContent").on('click', '.lc_side a', function () {

                    pageLogic.showThis($(this));
                    return false;
                });

                // 分栋信息收缩
                $('#IdentifyMain').on('click', "a.btn-toggle", function () {
                    $(this).parent().parent().parent().next().toggle();
                   // $(this).find("i").toggleClass("invisible");
                });

                //    add by wangwj 20181212 start
                //    给保存导出链接添加事件
                $("#saveExport").on("click", function () {
                    var param = pageLogic.getUrlParams();
                    layer.msg("信息导出成功！");
                    location.href = masterpage.ctxp + "/tasks/export/" + param.mainId;
                });


            },
            // add by wangwj 20181212 end
            layout: function () {

            },
            load: function () {

                var param = pageLogic.getUrlParams();
                if (param.type !== 0) {
                    //操作按钮悬浮
                    function btnAreaFixed() {
                        var buttonH = $('.button_area').outerHeight(true);
                        var btnAfter = '<div class="button_area_after"></div>';

                        var scroll = $('.portlet-body');
                        scroll.scroll(function (event) {
                            var scrollH = scroll[0].scrollHeight;
                            var scrollC = scroll[0].clientHeight;
                            var scrollT = scroll[0].scrollTop;

                            var scrollB = scrollH - scrollT - scrollC;
                            if (scrollB > buttonH) {
                                if($('.button_area').hasClass('button-fixed-bottom') != 'true'){
                                    $('.button_area').addClass('button-fixed-bottom');
                                    $('.button_area').after(btnAfter);
                                    $('.button_area_after').height(buttonH);
                                }
                            } else {
                                    if ($('.button_area').hasClass('button-fixed-bottom')) {
                                        $('.button_area').removeClass('button-fixed-bottom');
                                        $('.button_area_after').remove();
                                    }
                            }
                        });
                    }

                    btnAreaFixed();
                }

            }
        }
    };

    pageLogic.formatter = {};


    if (window.pageLogic) {
        window.pageLogic = pageLogic;
    } else {
        window.myPageLogic = pageLogic;
    }

})(window);

/**
 * masterpage: 主页面对象
 */
;(function (window) {
    var masterpage = {
        init: function () {
            var that = this;

            $(document).ready(function () {
                if (window.myPageLogic) {
                    that.pageInit(myPageLogic.init, myPageLogic);
                }
            });

            //定位到底部
            $('.portlet-body').css('visibility','hidden');
            layer.load(2);
            setTimeout(function () {
                layer.closeAll('loading');
                $('.portlet-body').css('visibility','');
                $('.portlet-body').scrollTop($('.portlet-body')[0].scrollHeight);
            }, 500);
        },

        pageInit: function (initObj, caller) {
            var func = null,
                caller = caller || this;

            var container = $("body");
            if ($.isPlainObject(initObj)) {
                container.css("visibility", "hidden");
                setTimeout(function () {
                    for (var key in initObj) {
                        func = initObj[key];
                        if ($.isFunction(func)) {
                            try {
                                func.call(caller);
                            } catch (e) {
                                throw new Error("页面初始化[" + key + "]发生错误：" + e);
                            }
                        }
                    }
                    container.css("visibility", "visible");
                }, 0);
            }
        }
    };
    window.myMasterpage = masterpage;
})(window);
// update by xujc 2019/1/31 end
// add by xujc 2018/11/30 end