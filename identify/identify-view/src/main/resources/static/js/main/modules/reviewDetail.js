
// add by xujc 2018/11/30 start 
// update by xujc 2019/1/24 start
;(function (window) {
    var pageLogic = {
        /**
         * @author : xujc
         * @date :2018/11/27
         * @Description : 获取url上的参数
         */
        getUrlParams: function () {
            var url = location.search;
            var theParam = new Object();
            if (url.indexOf("?") != -1) { //确保‘？’不是最后一个字符串，即携带的参数不为空
                var str = url.substr(1); //substr是字符串的用法之一，抽取指定字符的数目，这里抽取？后的所有字符
                var strs = str.split("&"); //将获取到的字符串从&分割，输出参数数组，即输出[参数1=xx,参数2=xx,参数3=xx,...]的数组形式
                for(var i = 0; i < strs.length; i ++) { //遍历参数数组
                    theParam[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]); //这里意思是抽取每个参数等号后面的值，unescape是解码的意思
                }
            }
            return theParam;

        },
        endWith: function(str,endStr){
            var d=str.length-endStr.length;
            return (d>=0&&str.lastIndexOf(endStr)==d);
        },
        /**
         * @author : xujc
         * @date :2019/01/08
         * @Description : 初始化文件，可以下载跟预览
         * $obj 是上传文件组件，arr是文件id数组
         */
        initViewFile: function ($obj,arr) {
            $obj.uploader({
                doc:true,
                docConfig: {
                    autoExpand: false,
                    action: 'view',
                    mode: 'outer'
                }
            });

            var interId = setInterval(function () {
                var $list = $obj.find('.file-card');
                if($list.length ==arr.length) {
                    $.each($list,function () {
                        var fileName = $(this).find('.file-name').text();
                        if(fileName && pageLogic.endWith(fileName,'docx')){

                            var $target = $(this).find('.file-options');
                            var fileId = $target.find(".document-view").attr('target');
                            var downLoad ='<div class="uploader-options download" title="下载" target="'+fileId+'"></div>';
                            $target.append(downLoad);
                        }
                    });
                    clearInterval(interId);
                }
            }, 500);
        },

    showReviewApply: function (recoud) {
        var param = pageLogic.getUrlParams();
        var toTasks =param.toTasks;
        if(param.type==0){
            // 返回按钮隐藏
            $("#fanhui").parent(".button_area").remove();
            $(".lc_content").addClass('lc_cont_margin')
        }
        var div = $("<div></div>");
        $("#ReviewMain").append(div);
        var deffered = $.postJSON(masterpage.ctxp + "/reviewMain/getApply/" + recoud.reviewId, {});
        deffered.done(function (data) {
            var review = data.data;
            var obj = {
                review: review,
                recoud: recoud
            };
            var html = _.template($('#tpl_reviewApply').html())({data: obj});
            div.append(html);
            div.find("[name='address']").text(review.identifyMain.house.zone + review.identifyMain.house.street + review.identifyMain.house.address);
            $("#orgName").text(review.identifyMain.orgName);
            if (review.identifyMain.method == "1") {
                div.find("[name='method']").text("人工窗口");
            } else if (review.identifyMain.method == "2") {
                div.find("[name='method']").text("我的南京");
            } else if (review.identifyMain.method == "3") {
                div.find("[name='method']").text("房产微政务");
            }
            pageLogic.initViewFile(div.find("[name='sheetId']"), [review.sheetId]);
            div.find("[name='sheetId']").resetUploader({model: 'viewer'}, [review.sheetId]);
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

            if(!toTasks){
                showReviewProcess(review.status,'isDetail');
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
         * @date :2018/12/11
         * @Description : 获取非正常信息(不含原因)
         */
        showNotReasonSuspend: function (recoud, title) {
            var div = $("<div></div>");
            div.appendTo($("#ReviewMain"));
            var obj = {
                recoud: recoud
            };
            var html = _.template($('#tpl_notReason').html())({data: obj});
            div.append(html);

        },
        /**
         * @author : xujc
         * @date :2018/12/11
         * @Description : 获取非正常信息(含原因)
         * @param: headText 什么原因
         */
        showHasReasonSuspend: function (recoud,headText,status) {
            var div = $("<div></div>");
            div.appendTo($("#ReviewMain"));
            var obj = {
                recoud: recoud,
                headText:headText,
                status:status
            };
            var html = _.template($('#tpl_hasReason').html())({data: obj});
            div.append(html);

        },
        
        init: {
            // update by xujc 2019/2/19 start
            before: function () {
                var param = pageLogic.getUrlParams();
                // 获取复审详细
                var deffer = $.postJSON(masterpage.ctxp + "/reviewMain/getReviewRe", {'reviewId':param.reviewId});
                deffer.done(function (data) {
                    var recouds = data.data;
                   var data={};
                    data.name = 'reviewMain';
                    $.each(recouds, function (i, recoud) {
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

                    });

                    data.recouds =recouds;
                    var html = _.template($('#tpl_title').html())({data: data});
                    $("#title").empty().append(html);
                    $.each(recouds, function (i, ele) {
                        var status = ele.status;
                        if (status == 1) {
                            pageLogic.showReviewApply(ele);
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
                            pageLogic.showNotReasonSuspend(ele);
                        }else if(status === 10){
                            pageLogic.showHasReasonSuspend(ele,'不受理','不受理');
                        }else if(status === 12){
                            pageLogic.showHasReasonSuspend(ele,'终止流程','已终止');
                        }else if(status === 13){
                            pageLogic.showHasReasonSuspend(ele,'挂起流程','已挂起');
                        }

                    })

                });

            },
            // update by xujc 2019/2/19 end
            after: function () {
            },


            //页面控件事件绑定(一般为按钮的事件绑定)
            events: function () {
                //給返回按钮添加点击事件
                var param = pageLogic.getUrlParams();
                // type=1返回到我的待办任务
                if(param.type==1){
                    $("#fanhui").on("click",function () {
                        goto(masterpage.ctxp+"/tasks",{withBackBtn:false});
                    });
                }else {
                    //返回到复核业务列表（type = 0 隐藏返回按钮）
                    $("#fanhui").on("click",function () {
                        goto(masterpage.ctxp+"/reviewMain",{withBackBtn:false});
                    });
                }


                $("#pageContent").on('click', '.lc_side a', function () {
                    var li = $(this).parent('li');
                    var href = $(this).attr('href');
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

                    return false;
                });

            },
            layout:function () {

            },
            load: function () {
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
                            if($('.button_area').hasClass('button-fixed-bottom')){
                                $('.button_area').removeClass('button-fixed-bottom');
                                $('.button_area_after').remove();
                            }
                        }
                    });
                }
                btnAreaFixed();
            }
        }
    };

    pageLogic.formatter = {
    };

    if(window.pageLogic) {
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
// update by xujc 2019/1/24 end
// add by xujc 2018/11/30 end