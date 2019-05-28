<!--add(update) by yanyj 20180926 start-->
<#list Session.user.menus as firstMenu>
    <#list firstMenu.children as secondMenu>
        <li name="${secondMenu.pId}" id = "url_${secondMenu.id}" class="<#if Request.CURRENT_SELECT_MENU_ID! == secondMenu.id>active </#if><#if Request.CURRENT_SELECT_MENU_IDS!?contains(secondMenu.id) || Request.CURRENT_SELECT_MENU_IDS!?contains(secondMenu.pId)> <#else>menu_hide</#if>">

        <#if secondMenu.children?size gt 0>
            <a href="#" <#if Request.CURRENT_SELECT_MENU_IDS!?contains(secondMenu.id)>aria-expanded="true"</#if>><i class="fa ${secondMenu.icon!}"></i>
            <span class="submenu-title">${secondMenu.name}</span><span class="fa <#if Request.CURRENT_SELECT_MENU_IDS!?contains(secondMenu.id)>arrow-down<#else>arrow</#if> arrow-span"></span></a>
            <ul class="nav nav-second-level collapse <#if Request.CURRENT_SELECT_MENU_IDS!?contains(secondMenu.id)>in</#if>">
            <#list secondMenu.children as thirdMenu>
                <li name="${thirdMenu.pId}" id = "url_${thirdMenu.id}" <#if Request.CURRENT_SELECT_MENU_ID! == thirdMenu.id>class="active"</#if>>

                <#if thirdMenu.children?size gt 0>
                    <a href="#"><i class="fa ${thirdMenu.icon!}"></i>
                    <span class="submenu-title">${thirdMenu.name}</span><span class="fa <#if Request.CURRENT_SELECT_MENU_IDS!?contains(thirdMenu.id)>arrow-down<#else>arrow</#if> arrow-span"></span></a>
                    <ul class="nav nav-third-level collapse <#if Request.CURRENT_SELECT_MENU_IDS!?contains(thirdMenu.id)>in</#if>">

                    <#list thirdMenu.children as fourMenu>
                        <li class="<#if Request.CURRENT_SELECT_MENU_ID! == fourMenu.id>active</#if> id="url_${fourMenu.id}">
                        <a <#if Request.CURRENT_SELECT_MENU_ID! == fourMenu.id>aria-expanded="true"</#if> _href="${fourMenu.uri}"><i class="fa ${fourMenu.icon!}"></i>
                        <span class="submenu-title">${fourMenu.name}</span></a>
                        </li>
                    </#list>
                    </ul>
                <#else>
                    <a _href="${thirdMenu.uri}" <#if Request.CURRENT_SELECT_MENU_ID! == thirdMenu.id>aria-expanded="true"</#if>><i class="fa ${thirdMenu.icon!}"></i>
                    <span class="submenu-title">${thirdMenu.name}</span></a>
                </#if>
                </li>
            </#list>

            </ul>
        <#else>
            <a _href="${secondMenu.uri}" <#if Request.CURRENT_SELECT_MENU_ID! == secondMenu.id>aria-expanded="true"</#if>><i class="fa ${secondMenu.icon!}"></i>
            <span class="submenu-title">${secondMenu.name}</span></a>
        </#if>

        </li>
    </#list>
</#list>
<!--add(update) by yanyj 20180926 end-->
