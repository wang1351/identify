<nav id="sidebar" class="navbar-default navbar-static-side">
    <div class="sidebar-collapse menu-scroll">
        <ul id="side-menu" class="nav">
            <li class="user-panel" style="display:none;">
                <div class="thumb"><img src="${request.contextPath}/${user.icon!''}" alt="" class="img-circle" />
                </div>
                <div class="info">
                    <p>欢迎：</p>
                    <p><span>${user.fullName!''}</span> | <a href="${request.contextPath}/logout" class="sys-logout" data-hover="tooltip" title="退出">退出</a></p>

                    <ul class="list-inline list-unstyled" style="display:none;">
                        <li><a href="#" data-hover="tooltip" title="个人信息"><i class="fa fa-user"></i></a></li>
                        <li><a href="/sys/user/${user.id!''}/avatar" data-hover="tooltip" title="修改头像"><i class="fa fa-camera-retro"></i></a></li>
                        <li><a href="#" data-hover="tooltip" title="修改密码" data-toggle="modal" data-target="#change-password"><i class="fa fa-key"></i></a></li>
                        <li><a href="${request.contextPath}/logout" data-hover="tooltip" title="退出">退出</a></li>
                    </ul>
                </div>
                <div class="clearfix"></div>
            </li>
            <#include "menu.ftl">
        </ul>
    </div>
</nav>
