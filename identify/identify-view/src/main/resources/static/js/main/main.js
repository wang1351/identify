$(document).ready(function () {

    $('#side-menu > li').hide();

    // metisMenu
    $('#side-menu').metisMenu();

    // 注册页面加载 & resize
    $(window).on("load resize", function () {
        if ($(this).width() < 768) {
            $('div.sidebar-collapse').addClass('collapse');
        } else {
            $('div.sidebar-collapse').removeClass('collapse');
            $('div.sidebar-collapse').css('height', 'auto');
        }

       /* if ($('#sidebar').height() > $('#page-wrapper').height()) {
            $('#wrapper').css('height', $('#sidebar').height());
        }*/

       var wHeight = $(this).height();
        $('#wrapper').height(wHeight - $("#topbar").height());
    });


    $('.dropdown-slimscroll').slimScroll({
        "height": '250px',
        "wheelStep": 30
    });

    // 注册tooltip，popover
    $("[data-toggle='tooltip'], [data-hover='tooltip']").tooltip();
    $("[data-toggle='popover'], [data-hover='popover']").popover();


    // 注册top按钮事件
    $(window).scroll(function () {
        if ($(this).scrollTop() < 200) {
            $('#totop').hide();
        } else {
            $('#totop').show();
        }
    });
    $('#totop').on('click', function () {
        $('html, body').animate({scrollTop: 0}, 'fast');
        return false;
    });

    // 注册主题切换onchange事件
    var list_theme = $('.dropdown-theme-setting > li > select#list_theme');
    list_theme.on('change', function () {
        $('#theme-change').attr('href', $('#theme-change').attr("href").replace(/theme-\w+/, 'theme-' + $(this).val()));

    });


    // 注册一级菜单点击事件
    $("#first_menus_id").find("li").on("click", function () {

        if($(this).attr('href')) {
            location.href = $(this).attr('href');
        }
    });

    // 用来展开

    var activeLiMenu =  $('#side-menu').find('li.active');

    if(activeLiMenu.length === 0) {
        activeLiMenu = $('li#' + getQueryString('fromMenuId'))
        activeLiMenu.addClass('active');
    }

    while(activeLiMenu.length && !activeLiMenu.parent().is('#side-menu')) {


        activeLiMenu = activeLiMenu.parent();
        if(activeLiMenu.is('.collapse')) {
            activeLiMenu.addClass('in');
        }
    }


    activeLiMenu.children('a').children('.arrow-span').removeClass('arrow').addClass('arrow-down');


    var menuName = activeLiMenu.attr('name');

    $('#side-menu').find('li[name="'  + menuName + '"]').show();

    $('.arrow-span').parent('li').off('click');

    $('#side-menu').mouseover(function () {
        var id = $(this).find('li.active').attr('id');

        window.activeMenuId = id;
    });

    $('.arrow-span').parent().click(function () {
        $('li#' + window.activeMenuId).addClass('active');
    });

    // 注册面包屑的事件
    $('.page-breadcrumb').on('click', 'a', function (event) {
        event.preventDefault();
        var ids = $(this).parent().parent().attr('menuIds');

        var index = +$(this).attr('menuIndex');

        ids = ids.slice(1, ids.length -1).split(', ');

        var $targetMenu = $('#url_' + ids[index]).find('a');

        if($targetMenu.length === 0) {

            if($('#' + ids[length]).length > 0) {
                $('#' + ids[length]).click();
            }

            return false;
        }

        if($targetMenu.attr('_href').trim() && $targetMenu.attr('_href').trim() !== "#") {
            $targetMenu.click();
        }

        return false;
    });


    // add by yanyj 20190516 start

    if($('#side-menu').find('li.active').length === 0) {

        var id = $('#first_menus_id').find('li.active').attr('id');

        $('#side-menu').find('li[name="' + id + '"]').show().addClass('active')
    }
    // add by yanyj 20190516 end
    // $("#first_menus_id").find("li.active").click();


    // 给change-password 的form 注册validate


    //添加校验规则

    jQuery.validator.addMethod("equal", function(value, element, param) {
        return $("#newPassword").val() === value;
    });

    // add by yanyanjun 20181210 end


    $('#change-password').find('form').validator({
        rules:{
            password: {required: true},
            newPassword: {required: true},
            newPassword2: {required: true, equal: ""},
        },
        messages: {
            newPassword2: {equal: "确认密码与新密码不一致"}
        }
    })

    // 登出按钮注册事件
    $('.sys-logout').on('click', function () {
        location.href = $(this).attr('href')
    });

    // 修改密码点击事件
    $("#changePwd").on("click", function () {

        if (!$('#change-password').find('form').valid()) {

            return;
        }

        // 原密码
        var prePassword = $("#password").val();


        var newPassword = $("#newPassword").val();
        var newPassword2 = $("#newPassword2").val();

        if(newPassword !== newPassword2) {

            layer.msg('新密码与确认密码不一致！', {icon: 1});

            return;
        }

        $.ajax({
            url:"/uc/sys/users/" + masterpage.userId + "/changePwd",
            method: "POST",
            data: JSON.stringify({
                prePassword: prePassword,
                password: newPassword
            }),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (res) {
                if(res.success) {
                    layer.msg("用户密码成功！");
                    $("#closePwd").trigger("click");
                } else {
                    layer.msg("原密码输入错误！", {icon: 1});
                }
            },

            error: console.error
        })

    });


    // add by yanyanjun 20181129 start
    // 注册图标

    $('#side-menu').find('.collapse').on('hide.bs.collapse', function () {

        window.menuId = $('#side-menu').find('li.active').attr('id');

        $(this).prev('a').find('.arrow-span').addClass('arrow').removeClass('arrow-down');

        $('#' + window.menuId).addClass('active');
    });

    $('#side-menu').find('.collapse').on('show.bs.collapse', function () {
        $('#' + window.menuId).addClass('active');
        $(this).prev('a').find('.arrow-span').addClass('arrow-down').removeClass('arrow');
    });



// add by yanyanjun 20181129 end

    // add by yanyanjun 20181210 start

    $('a.no-link').click(function (e) {
        e.preventDefault();

        return false;
    });


    if(window.isErrorPage) {
        var id = $('[context="' + masterpage.ctxp + '"]').addClass('active').attr('id');

        $('li[name="' + id + '"]').show();
    }
    // add by yanyanjun 20181210 end


    $("#")
});

// add by yanyj 20181122 start
var longestCommonPrefix = function (strs) {
    var one = strs.length > 0 ? String(strs[0]).split("") : false;
    var a = "";
    if (!one) {
        return a;
    }
    ;
    for (var i = 0; i < one.length; i++) {
        var num = 0;
        strs.map(function (da) {
            return da.charAt(i) == one[i] ? num++ : null
        })
        if (num === strs.length) {
            a = a + one[i]
        } else {
            break
        }
    }
    return a
};

// add by yanyj 20181122 end


/**
 * 将表单转成json,并且name为key,value为值
 */
;(function ($) {
    $.fn.serializeObject = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');



            } else {
                o[this.name] = this.value || null;
            }

            // 添加对select Multiple 多选的支持
            if($("[name='" + this.name + "']").is('select') && $("[name='" + this.name + "']").attr('multiple') === 'multiple') {
                o[this.name] = [].concat($("[name='" + this.name + "']").val()) || [];
            }
        });

        return o;
    };

})(jQuery);

// add by yanyj 20181122 end



function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}


// add by yanyj 20181221 start

function once(func) {
    var result;

    return  function() {
        if(func) {
            result = func.apply(this, arguments);
            func = null;
        }
        return result;
    };
}

function getBack() {
    history.back();
}

function backIndex() {
    var id = $('#first_menus_id').find('li.active').attr('id');

    window.location.href = $($('li[name="' + id + '"]').find('li')[0]).find('a').attr('_href');
}
// add by yanyj 20181221 end