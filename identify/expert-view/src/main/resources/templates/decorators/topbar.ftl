<div id="header-topbar">
    <nav id="topbar" class="navbar navbar-default navbar-static-top">
        <div class="navbar-header">
            <button type="button" data-toggle="collapse" data-target=".sidebar-collapse" class="navbar-toggle"><span
                        class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span
                        class="icon-bar"></span><span
                        class="icon-bar"></span>
            </button>
            <a id="logo" href="${request.contextPath}" class="navbar-brand">
                <span class="logo-text"><@spring.message "logoName"/></span>
                <span class="logo-text-icon" style="display: none;"></span>
            </a>
        </div>
        <div class="topbar-main"><a id="menu-toggle" href="#" class="hidden-xs"><i class="fa fa-bars"></i></a>
            <ul class="nav navbar-nav horizontal-menu" id="first_menus_id">
                <#list Session.user.menus as menu>
                    <li id="${menu.id}" context="/${menu.projectNo}" href="${(menu.uri)!}" class="<#if Request.CURRENT_SELECT_MENU_IDS!?contains(menu.id)>active</#if>">
                    <a><span class="nav-img"><#if menu.icon??><img
                                src="<@spring.message 'frontend.url'/>/img/nav/${menu.icon}"></span></#if><span
                        class="nav-title">${menu.name}</span></a>
                    </li>
                </#list>
            </ul>

            <ul class="nav navbar navbar-top-links navbar-right mbn">
                <li class="dropdown"><a data-hover="dropdown" href="#" class="dropdown-toggle"><i
                                class="fa fa-bell fa-fw"></i><span class="badge badge-green">3</span></a>
                    <ul class="dropdown-menu dropdown-alerts">
                        <li>
                            <p>You have 14 new notifications</p>
                        </li>
                        <li>
                            <div class="dropdown-slimscroll">
                                <ul>
                                    <li><a href="extra-user-list.html" target="_blank"><span class="label label-blue"><i
                                                        class="fa fa-comment"></i></span>New Comment<span
                                                    class="pull-right text-muted small">4 mins ago</span></a>
                                    </li>
                                    <li><a href="extra-user-list.html" target="_blank"><span class="label label-violet"><i
                                                        class="fa fa-twitter"></i></span>3 New Followers<span
                                                    class="pull-right text-muted small">12 mins ago</span></a>
                                    </li>
                                    <li><a href="extra-user-list.html" target="_blank"><span class="label label-pink"><i
                                                        class="fa fa-envelope"></i></span>Message Sent<span
                                                    class="pull-right text-muted small">15 mins ago</span></a>
                                    </li>
                                    <li><a href="extra-user-list.html" target="_blank"><span
                                                    class="label label-green"><i class="fa fa-tasks"></i></span>New Task<span
                                                    class="pull-right text-muted small">18 mins ago</span></a>
                                    </li>
                                    <li><a href="extra-user-list.html" target="_blank"><span class="label label-yellow"><i
                                                        class="fa fa-upload"></i></span>Server Rebooted<span
                                                    class="pull-right text-muted small">19 mins ago</span></a>
                                    </li>
                                    <li><a href="extra-user-list.html" target="_blank"><span
                                                    class="label label-green"><i class="fa fa-tasks"></i></span>New Task<span
                                                    class="pull-right text-muted small">2 days ago</span></a>
                                    </li>
                                    <li><a href="extra-user-list.html" target="_blank"><span class="label label-pink"><i
                                                        class="fa fa-envelope"></i></span>Message Sent<span
                                                    class="pull-right text-muted small">5 days ago</span></a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                        <li class="last"><a href="extra-user-list.html" class="text-right">See all alerts</a>
                        </li>
                    </ul>
                </li>
                <#-- <li class="dropdown"><a data-hover="dropdown" href="#" class="dropdown-toggle"><i
                         class="fa fa-envelope fa-fw"></i><span class="badge badge-orange">7</span></a>
                     <ul class="dropdown-menu dropdown-messages">
                         <li>
                             <p>You have 14 new messages</p>
                         </li>
                         <li>
                             <div class="dropdown-slimscroll">
                                 <ul>
                                    <li><a href="email-view-mail.html" target="_blank"><span class="avatar"><img src="https://s3.amazonaws.com/uifaces/faces/twitter/kolage/48.jpg" alt="" class="img-responsive img-circle"/></span><span class="info"><span class="name">Jessica Spencer</span><span class="desc">Lorem ipsum dolor sit amet...</span></span></a>
                                     </li>
                                     <li><a href="email-view-mail.html" target="_blank"><span class="avatar"><img src="https://s3.amazonaws.com/uifaces/faces/twitter/kolage/48.jpg" alt="" class="img-responsive img-circle"/></span><span class="info"><span class="name">John Smith<span class="label label-blue pull-right">New</span></span><span class="desc">Lorem ipsum dolor sit amet...</span></span></a>
                                     </li>
                                     <li><a href="email-view-mail.html" target="_blank"><span class="avatar"><img src="https://s3.amazonaws.com/uifaces/faces/twitter/kolage/48.jpg" alt="" class="img-responsive img-circle"/></span><span class="info"><span class="name">John Doe<span class="label label-orange pull-right">10 min</span></span><span class="desc">Lorem ipsum dolor sit amet...</span></span></a>
                                     </li>
                                     <li><a href="email-view-mail.html" target="_blank"><span class="avatar"><img src="https://s3.amazonaws.com/uifaces/faces/twitter/kolage/48.jpg" alt="" class="img-responsive img-circle"/></span><span class="info"><span class="name">John Smith</span><span class="desc">Lorem ipsum dolor sit amet...</span></span></a>
                                     </li>
                                     <li><a href="email-view-mail.html" target="_blank"><span class="avatar"><img src="https://s3.amazonaws.com/uifaces/faces/twitter/kolage/48.jpg" alt="" class="img-responsive img-circle"/></span><span class="info"><span class="name">John Smith</span><span class="desc">Lorem ipsum dolor sit amet...</span></span></a>
                                     </li>
                                 </ul>
                             </div>
                         </li>
                         <li class="last"><a href="email-view-mail.html" target="_blank">Read all messages</a>
                         </li>
                     </ul>
                 </li>
                 <li class="dropdown"><a data-hover="dropdown" href="#" class="dropdown-toggle"><i
                         class="fa fa-tasks fa-fw"></i><span class="badge badge-yellow">8</span></a>
                     <ul class="dropdown-menu dropdown-tasks">
                         <li>
                             <p>You have 14 pending tasks</p>
                         </li>
                         <li>
                             <div class="dropdown-slimscroll">
                                 <ul>
                                     <li><a href="page-blog-item.html" target="_blank"><span class="task-item">Fix the HTML code<small
                                             class="pull-right text-muted">40%</small></span>
                                         <div class="progress progress-sm">
                                             <div role="progressbar" aria-valuenow="40" aria-valuemin="0"
                                                  aria-valuemax="100" style="width: 40%;"
                                                  class="progress-bar progress-bar-orange"><span class="sr-only">40% Complete (success)</span>
                                             </div>
                                         </div>
                                     </a>
                                     </li>
                                     <li>
                                         <a href="page-blog-item.html" target="_blank"> <span class="task-item">Make a wordpress theme<small
                                                 class="pull-right text-muted">60%</small></span>
                                             <div class="progress progress-sm">
                                                 <div role="progressbar" aria-valuenow="60" aria-valuemin="0"
                                                      aria-valuemax="100" style="width: 60%;"
                                                      class="progress-bar progress-bar-blue"><span class="sr-only">60% Complete (success)</span>
                                                 </div>
                                             </div>
                                         </a>
                                     </li>
                                     <li>
                                         <a href="page-blog-item.html" target="_blank"> <span class="task-item">Convert PSD to HTML<small
                                                 class="pull-right text-muted">55%</small></span>
                                             <div class="progress progress-sm">
                                                 <div role="progressbar" aria-valuenow="55" aria-valuemin="0"
                                                      aria-valuemax="100" style="width: 55%;"
                                                      class="progress-bar progress-bar-green"><span class="sr-only">55% Complete (success)</span>
                                                 </div>
                                             </div>
                                         </a>
                                     </li>
                                     <li>
                                         <a href="page-blog-item.html" target="_blank"> <span class="task-item">Convert HTML to Wordpress<small
                                                 class="pull-right text-muted">78%</small></span>
                                             <div class="progress progress-sm">
                                                 <div role="progressbar" aria-valuenow="78" aria-valuemin="0"
                                                      aria-valuemax="100" style="width: 78%;"
                                                      class="progress-bar progress-bar-yellow"><span class="sr-only">78% Complete (success)</span>
                                                 </div>
                                             </div>
                                         </a>
                                     </li>
                                 </ul>
                             </div>
                         </li>
                         <li class="last"><a href="page-blog-item.html" target="_blank">See all tasks</a>
                         </li>
                     </ul>
                 </li>-->
                <li class="dropdown topbar-user">
                    <a data-hover="dropdown" href="#" class="dropdown-toggle no-link"><i
                                class="fa fa-user"></i>&nbsp;${Session.user.fullName}&nbsp;<span id="msg1" class="ii" style="display: none;">3</span><span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu dropdown-user pull-right">
                        <li><a href="#" data-hover="tooltip" title="修改密码" data-toggle="modal"
                               data-target="#change-password"><i class="fa fa-key"></i>修改密码</a></li>
                        <li><a id="unreadMsg" href="javascript:void();" title="未读消息">
                                <i class="fa fa-key"></i>未读消息</a><span id="msg2" class="ii" style="display: none;">3</span></li>
                        <li class="divider"></li>
                        <li><a href="${request.contextPath}/logout"><i class="fa fa-sign-out"></i>退出</a></li>
                    </ul>
                </li>
                <li class="dropdown hidden-xs">
                    <a id="theme-setting" href="javascript:void(0);" data-hover="dropdown"
                       data-step="1"
                       data-intro="&lt;b&gt;Header&lt;/b&gt;, &lt;b&gt;sidebar&lt;/b&gt;, &lt;b&gt;border style&lt;/b&gt; and &lt;b&gt;color&lt;/b&gt;, all of them can change for you to create the best"
                       data-position="left" class="dropdown-toggle"><i class="fa fa-cogs"></i></a>
                    <ul class="dropdown-menu dropdown-theme-setting pull-right">
                        <li>
                            <h4 class="mtn">主题</h4>
                            <select id="list_theme" class="form-control">
                                <option value="default">默认主题</option>
                                <option value="a">主题A</option>
                                <option value="b">主题B</option>
                                <option value="c">主题C</option>
                                <option value="d">主题D</option>
                                <option value="e">主题E</option>
                                <option value="f">主题F</option>
                                <option value="g">主题G</option>
                                <option value="h">主题H</option>
                                <option value="i">主题I</option>
                            </select>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
    <div id="change-password" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" data-dismiss="modal" class="close">&times;</button>
                    <h4 class="modal-title">修改密码</h4>
                </div>
                <div class="modal-body">
                    <form id="changePasswordForm" method="post"
                          action="${request.contextPath}/sys/users/${Session.user.id}/changePwd"
                          class="form">
                        <div class="form-group">
                            <div class="input-icon right">
                                <span style="float: left; padding: 6px">原密码</span>
                                <i class="fa fa-key"></i>
                                <input type="password" id="password" value="" name="password" class="form-control"
                                       datatype="*6-16"
                                       nullmsg="请设置密码！" errormsg="密码范围在6~16位之间！" style="float: right;width: 87%"/>
                                <label class="Validform_checktip"></label>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="input-icon right">
                                <span style="float: left; padding: 6px">新密码</span>
                                <i class="fa fa-key"></i>
                                <input type="password" id="newPassword" value="" name="newPassword" class="form-control"
                                       datatype="*6-16"
                                       nullmsg="请设置密码！" errormsg="密码范围在6~16位之间！" style="float: right;width: 87%"/>
                                <label class="Validform_checktip"></label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-icon right">
                                <span style="float: left; padding: 6px">确认密码</span>
                                <i class="fa fa-key"></i>
                                <input type="password" id="newPassword2" value="" name="newPassword2"
                                       class="form-control"
                                       datatype="*6-16"
                                       nullmsg="请设置密码！" errormsg="密码范围在6~16位之间！" style="float: right;width: 87%"/>
                                <label class="Validform_checktip"></label>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default" id="closePwd">关闭</button>
                    <button type="button" class="btn btn-primary" id="changePwd">修改密码</button>
                </div>
            </div>
        </div>
    </div>

</div>
