// add by xujc 2018/11/30 start
;(function (window) {

var pageLogic = {
    
    init: {
        before: function () {
            var columns = [
                {field: "num", title: "序号", width: '5%',align: "center", formatter: common.formatter.index},
                {field: "id", visible: false},
                {field: "orgName", title: "机构名称", width: '20%' , align: "center"},
                {field: "unifiedCode", title: "统一社会信用代码", width:'15%', align: "center"},
                {field: "legalPerson", title: "法定代表人", width: '10%', align: "center"},
                {field: "creditGradeName", title: "信用等级", width: '10%', align: "center"},
                {field: "creditGradeScore", title: "信用评价得分", width: '10%', align: "center"},
                {field: "status", title: "发布状态", width: '20%', align: "center",formatter: pageLogic.formatter.formatStatus},
                {field: "", title: "操作", width: '10%', align: "center",formatter: pageLogic.formatter.formatDo}

            ];
            //add by wangwj 20181226 start
            //设置switch 开关初始化
            var initFlag = false;
            common.initTable(columns,{
                onPostBody: function () {
                    $('#btTable .switch').bootstrapSwitch({
                        onText: "发布",
                        offText: "未发布",
                        onColor: "success",
                        offColor: "danger",
                        size: "small"
                    });
                    $.each($('#btTable .switch'), function () {
                        // 信息回填
                        var status = $(this).attr('name');
                        if (status == 0) {
                            $(this).bootstrapSwitch('state', false);
                        } else {
                            $(this).bootstrapSwitch('state', true);
                        }

                        $(this).on('switchChange.bootstrapSwitch', function (event, state) {
                            var id = $(event.target).attr("id");
                             var deferred = $.putJSON(masterpage.ctxp + "/enterprise/credits/" + id, {status: state ? 1 : 0});
                            deferred.done(function (data) {
                                if (data.success) {
                                    layer.msg("状态修改成功！");
                                } else {
                                    layer.msg("状态修改失败！");
                                }
                                common.search();
                            });
                        })
                    });
                    initFlag = true;
                }
            });

            //add by wangwj 20181226 end
        },
            //update by wangwj 20190228 start
        layout: function() {
            common.layout();
        },
        after: function () {
            //创建模态窗口
            common.modal(pageLogic.initData.modalParams[0], {
            rules: {
            },

            messages: {}
            });

        },
        //页面控件事件绑定(一般为按钮的事件绑定)
        events: function () {
            common.registerEvents();
            // 给评分以及看历史加点击事件
            $("#btTable").on("click",".score",function (event) {
                var orgId = $(event.target).attr("name");
                goto(pageLogic.initData.path+"/enterprise/credits/toScore?orgId="+orgId+"&type=1",{withBackBtn:false});


            });
            $("#btTable").on("click",".historyScore",function (event) {
                var orgId = $(event.target).attr("name");
                goto(pageLogic.initData.path+"/enterprise/credits/toScore?orgId="+orgId+"&type=2",{withBackBtn:false});
            });
            $("#btTable").on("click",".org",function (event) {
                var orgId = $(event.target).attr("name");
                goto(pageLogic.initData.path+"/enterprise/credits/toScore?orgId="+orgId+"&type=3",{withBackBtn:false});
            })


        },
        //update by wangwj 20190228 end
        load: function () {
            common.search();
        }
    },
    formatter: {
        //update by wangwj 20181226 start
        // row 是企业信用对象，row.identifyOrgId 是鉴定机构id
        formatDo: function (cellValue,row) {
            //add by wangwj 20190109 start
            if(row.status ==1) {
                var doStr = "<a class='historyScore' style='color:blue' href='javascript:void(0)' name='" + row.identifyOrgId + "'>历史</a>";
                return doStr;
            }else{
                var doStr = "<a  class='score' style='color:blue' href='javascript:void(0)' name='" + row.identifyOrgId + "'>评分</a>";
                doStr += " | ";
                doStr += "<a class='org' style='color:blue' href='javascript:void(0)' name='" + row.identifyOrgId + "'>上传评分资料</a>";
                doStr += " | ";
                doStr += "<a class='historyScore' style='color:blue' href='javascript:void(0)' name='" + row.identifyOrgId + "'>历史</a>";
                return doStr;



            }
            //add by wangwj 20190109 end
        },
        //update by wangwj 2081226 end
        //add by wangwj 20181226 start
        //初始化发布组件
        formatStatus: function (cellvalue, options) {
            // options row对象
            var status = "";

            if(options.id){
                status += '<input class="switch" id="' + options.id + '" name="' + (options.status==null?0:options.status) + '"  type="checkbox" data-size="small">';
            }else {
                status +='-';
            }
            return status;
        }
        //add by wangwj 20181226 end
    }
};



window.pageLogic = pageLogic;
})(window);

// add by xujc 2018/11/30 end 
