<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <div class="page-header pull-left">
        <div class="page-title"><sitemesh:write property='title'/></div>
    </div>
    <#if Request.BREAD_CRUMB?? && Request.CURRENT_SELECT_MENU_IDS??>
        <#else >
            <ol class="breadcrumb page-breadcrumb" menuIds="${Request.CURRENT_SELECT_MENU_IDS!}">
                <#list Request.BREAD_CRUMB! as item>
                    <#if !item_has_next>
                        <li><a _href="#" id="breadCrumbLast" class="menu-bread" menuIndex="${item_index}"menuId="${ Request.CURRENT_SELECT_MENU_ID} ">${item}</a>&nbsp;&nbsp;</li>
                    <#else>
                        <li><a _href="#" class="" menuIndex="${item_index}">${item}</a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
                    </#if>
                </#list>
            </ol>
    </#if>

    <div class="clearfix"></div>
</div>