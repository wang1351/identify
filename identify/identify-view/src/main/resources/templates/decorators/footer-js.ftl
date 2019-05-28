<!-- jquery & plugins -->
<script src="<@spring.message "frontend.url"/>/js/vendor/jquery.min.js"></script>
<script src="<@spring.message "frontend.url"/>/js/plugins/jquery/jquery-ui-1.10.1.custom.min.js"></script>
<script src="<@spring.message "frontend.url"/>/js/plugins/jquery/jquery-validate-1.16.0.js"></script>
<script src="<@spring.message "frontend.url"/>/js/plugins/jquery/jquery.ztree.all.min.js"></script>
<script src="<@spring.message "frontend.url"/>/js/plugins/jquery/jquery.metisMenu.js"></script>
<script src="<@spring.message "frontend.url"/>/js/plugins/jquery/jquery.slimscroll.min.js"></script>
<script src="<@spring.message "frontend.url"/>/js/plugins/jquery/jquery.cookie.js"></script>
<script src="<@spring.message "frontend.url"/>/js/plugins/jquery/jquery-migrate-1.2.1.min.js"></script>
<script src="<@spring.message "frontend.url"/>/js/plugins/jquery/jquery.menu.js"></script>
<script src="<@spring.message "frontend.url"/>/js/plugins/jquery/qr/jquery.qrcode.js"></script>
<!-- bootstrap & plugins -->
<script src="<@spring.message "frontend.url"/>/js/vendor/bootstrap/bootstrap.min.js"></script>
<script src="<@spring.message "frontend.url"/>/js/plugins/bootstrap/bootstrap-switch.js"></script>
<script src="<@spring.message "frontend.url"/>/js/plugins/bootstrap/bootstrap-table.js"></script>
<script src="<@spring.message "frontend.url"/>/js/plugins/bootstrap/bootstrap-table-zh-CN.min.js"></script>
<script src="<@spring.message "frontend.url"/>/js/plugins/bootstrap/bootstrap-hover-dropdown.js"></script>
<script src="<@spring.message "frontend.url"/>/js/plugins/bootstrap/bootstrap-datetimepicker.js"></script>
<script src="<@spring.message "frontend.url"/>/js/plugins/bootstrap/select/js/bootstrap-select.js"></script>

<!-- upload -->
<script src="<@spring.message "frontend.url"/>/js/vendor/webuploader.js"></script>

<!-- underscore -->
<script src="<@spring.message "frontend.url"/>/js/vendor/underscore-1.9.1.js"></script>

<!-- 模拟浏览器进度条组件 -->
<script src="<@spring.message "frontend.url"/>/js/vendor/pace.min.js"></script>

<!-- layer 组件，消息提示框用 -->
<script src="<@spring.message "frontend.url"/>/js/vendor/layer/layer.min.js"></script>

<!-- custom -->
<script src="${myUrls.getForLookupRemotePath('frontend.url', '/js/main/core.js')}"></script>
<script src="${myUrls.getForLookupRemotePath('frontend.url', '/js/main/common.js')}"></script>

<script>
    (function() {
        if (!masterpage.ctxp) {
            masterpage.ctxp = "${request.contextPath}";
        }

        if (!masterpage.userId) {
            masterpage.userId = "${user.id}";
            masterpage.orgId = "${user.orgList[0].id}";
        }

        if(!masterpage.ucUrl) {
            masterpage.ucUrl = "<@spring.message 'uc.url'/>";

        }

        if (!masterpage.uploadUrl) {
            masterpage.uploadUrl = "<@spring.message 'upload.url'/>/files";
            masterpage.uploadUrlHost = "<@spring.message 'upload.url'/>";
        }

        if (!masterpage.onlineOffice) {
            masterpage.onlineOffice = "<@spring.message 'onlineOffice.url'/>";
        }
        masterpage.init();
        if(window.myMasterpage) {
            myMasterpage.init();
        }

        $('.title2 .fa-angle-down').click(function(){
            $(this).parent().parent().toggleClass('tab-content-hide tab-content-show');
        });


        $("#unreadMsg").on("click", function () {
            goto(masterpage.ctxp + "/sys/msgs", {withBackBtn: false});
        });


        setInterval(function () {
            common.postJSON({
                url: "/sys/msgs/unread/counts"
            }, function (data) {
                if (data > 0) {
                    $("#msg1").show().text(data);
                    $("#msg2").show().text(data);
                } else {
                    $("#msg1").hide();
                    $("#msg2").hide();
                }

            });
        }, 5000);

    })();
    var getLi= function (seq,mySort,id) {
        // 不受理，受理流程也是走过了的。只有列表才能看到不受理的任务
        if(seq === 10){
            seq =2;
        }
        var text ='';
        if(mySort ===1){
            text ='鉴定委托';
        }else if(mySort ===2){
            text ='受理';
        }else if(mySort ===3){
            text ='初勘';
        }else if(mySort ===4){
            text ='出具方案';
        }else if(mySort ===5){
            text ='签订合同';
        }else if(mySort ===6){
            text ='编制鉴定报告';
        }else if(mySort ===7){
            text ='审核鉴定报告';
        }else if(mySort ===8){
            text ='签发鉴定报告';
        }else if(mySort ===9){
            text ='发放鉴定报告';
        }
        var myClass ='';
        if(seq === mySort){
            if(id=='isDetail'){
                myClass ='before';
            }else {
                myClass ='active';
            }
        }else if(seq > mySort){
            myClass ='before';
        }
        return "<li class='" + myClass + "'><a href='#identifyMain"+mySort +"'><span class=\"process_num\">"+mySort+"</span><span class=\"process_tit\">"+text+"</span></a></li>";

    };

    var showProcess = function (seq, id) {
        var portleHead = "<div class=\"portlet-head\">" +
            "<div class=\"process\">" +
            "<ul>" +
                getLi(seq,1,id)+
                getLi(seq,2,id)+
                getLi(seq,3,id)+
                getLi(seq,4,id)+
                getLi(seq,5,id)+
                getLi(seq,6,id)+
                getLi(seq,7,id)+
                getLi(seq,8,id)+
                getLi(seq,9,id);

                if (id) {
                   // portleHead += "<a target='_blank' href='" + masterpage.ctxp + "/processes/" + id + "/diagram" + "' class=\"btn btn-blue\">查看工作流</a>";
                }

            portleHead += "</ul></div></div>";

        $('.portlet-body').before(portleHead);

        portletBodyScroll();

        $('.process').on("click","a",function(){
            var div = $(this).attr('href');
            var $targetDiv = $("div.lc_main [id^='" + div.substr(1) + "']");

            if($targetDiv.length > 0){

                var $div1 = $($targetDiv.get(0));
                var $div2 = $($targetDiv.get(1));
                var $li = $('.lc_side a[href="#'+ $div1[0].id +'"]').parent('li');

                $('.portlet-body').scrollTop($div1[0].offsetTop - 15);
                $div2.toggle();
                if($div2.css('display') == 'none'){
                    //alert(2)
                    $li.removeClass('open').height('');
                } else {
                    //alert(1)
                    $li.addClass('open').height($div1.height() - 20);
                }
            }

            return false;
        });
    };
    var getReviewLi= function (seq,mySort,id) {
        if(seq === 10){
            seq =2;
        }
        var text ='';
        if(mySort ===1){
            text ='复核申请';
        }else if(mySort ===2){
            text ='受理意见';
        }else if(mySort ===3){
            text ='选择专家';
        }else if(mySort ===4){
            text ='确认专家';
        }else if(mySort ===5){
            text ='专家意见';
        }else if(mySort ===6){
            text ='办结';
        }
        var myClass ='';
        if(seq === mySort){
            if(id=='isDetail'){
                myClass ='before';
            }else {
                myClass ='active';
            }
        }else if(seq > mySort){
            myClass ='before';
        }
        return "<li class='" + myClass + "'><a href='#reviewMain"+mySort +"'><span class=\"process_num\">"+mySort+"</span><span class=\"process_tit\">"+text+"</span></a></li>";

    };

    var showReviewProcess = function (seq, id) {
        var portleHead = "<div class=\"portlet-head\">" +
            "<div class=\"process\">" +
            "<ul>" +
                getReviewLi(seq,1,id) +
                getReviewLi(seq,2,id) +
                getReviewLi(seq,3,id) +
                getReviewLi(seq,4,id) +
                getReviewLi(seq,5,id) +
                getReviewLi(seq,6,id);


        if (id) {
           // portleHead += "<a target='_blank' href='" + masterpage.ctxp + "/processes/" + id + "/diagram" + "' class=\"btn btn-blue\">查看工作流</a>";
        }

        portleHead += "</ul></div></div>";
        $('.portlet-body').before(portleHead);

        portletBodyScroll();

        $('.process').on("click","a",function(){
            var div = $(this).attr('href');
            var $targetDiv = $("div.lc_main [id^='" + div.substr(1) + "']");

            if($targetDiv.length > 0){

                var $div1 = $($targetDiv.get(0));
                var $div2 = $($targetDiv.get(1));
                var $li = $('.lc_side a[href="#'+ $div1[0].id +'"]').parent('li');

                $('.portlet-body').scrollTop($div1[0].offsetTop - 15);
                $div2.toggle();
                if($div2.css('display') == 'none'){
                    //alert(2)
                    $li.removeClass('open').height('');
                } else {
                    //alert(1)
                    $li.addClass('open').height($div1.height() - 20);
                }
            }

            return false;
        });
    };

    var getCorrectLi= function (seq,mySort,id) {
        if(seq === 11){
            seq =2;
        }
        var text ='';
        if(mySort ===1){
            text ='变更申请';
        }else if(mySort ===2){
            text ='审 核';
        }else if(mySort ===3){
            text ='上传资料';
        }
        var myClass ='';
        if(seq === mySort){
            if(id=='isDetail'){
                myClass ='before';
            }else {
                myClass ='active';
            }
        }else if(seq > mySort){
            myClass ='before';
        }
        return "<li class='" + myClass + "'><a href='#correctMain"+mySort +"'><span class=\"process_num\">"+mySort+"</span><span class=\"process_tit\">"+text+"</span></a></li>";

    };
    var showCorrectProcess = function (seq, id) {
        var portleHead = "<div class=\"portlet-head\">" +
            "<div class=\"process\">" +
            "<ul>" +
                getCorrectLi(seq,1,id) +
                getCorrectLi(seq,2,id) +
                getCorrectLi(seq,3,id) ;

        if (id) {
          //  portleHead += "<a target='_blank' href='" + masterpage.ctxp + "/processes/" + id + "/diagram" + "' class=\"btn btn-blue\">查看工作流</a>";
        }

        portleHead += "</ul></div></div>";
        $('.portlet-body').before(portleHead);

        portletBodyScroll();
        $('.process').on("click","a",function(){
            var div = $(this).attr('href');
            var $targetDiv = $("div.lc_main [id^='" + div.substr(1) + "']");

            if($targetDiv.length > 0){

                var $div1 = $($targetDiv.get(0));
                var $div2 = $($targetDiv.get(1));
                var $li = $('.lc_side a[href="#'+ $div1[0].id +'"]').parent('li');

                $('.portlet-body').scrollTop($div1[0].offsetTop - 15);
                $div2.toggle();
                if($div2.css('display') == 'none'){
                    $li.removeClass('open').height('');
                } else {
                    $li.addClass('open').height($div1.height() - 20);
                }
            }

            return false;
        });
    };

    function portletBodyScroll() {
        var portletBodyHeight = $(window).height() - 90 - 40;
        if($('.portlet-head').length > 0 && $('.portlet-head').css('display') != 'none'){
            portletBodyHeight = portletBodyHeight - $('.portlet-head').outerHeight(true)
        }
        if($('.page-title-breadcrumb').length > 0 && $('.page-title-breadcrumb').css('display') != 'none'){
            portletBodyHeight = portletBodyHeight - $('.page-title-breadcrumb').height();
        }
        $('.portlet-body').css({'overflow':'auto','height':portletBodyHeight+'px'});
    }

</script>

<script src="${request.contextPath}${urls.getForLookupPath('/js/main/main.js')}"></script>
