<html xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
<head>
    <title>鉴定申请</title>
</head>
<body>

<div>
    <h4 class="title"><span>申请鉴定内容</span></h4>
    <form class="form-horizontal" style="margin:0 10%;">
        <div id="identifyContent" class="form-group form-group-padding"></div>
        <script type="text/template" id="tpl_identifyContent">
            <% _.each(data, function(item){ %>

            <% if (item.value === "8") { %>
            <div class='col-sm-6 col-md-4 col-lg-3'>
                <label class='radio-inline'>
                    <input type='radio' name='identifyType' value='<%= item.value%>' onchange='pageLogic.showOrHide()'/><%=item.name%>
                    <input id='otherContent' type='text' name='otherContent'
                           style="width:calc(100% - 35px); display: none;"/>
                </label>
            </div>
            <% } else { %>

            <div class='col-xs-6 col-md-4 col-lg-3'>
                <label class='radio-inline'>
                    <input type='radio' name='identifyType' value='<%= item.value%>' onchange='pageLogic.showOrHide()'/><%=
                    item.name%>
                </label>
            </div>
            <% } %>
            <%  }); %>
        </script>

        <div class="group-box" id="ratingLevelArea" hidden="hidden"></div>
        <script type="text/template" id="tpl_groupBox">
            <div class='label-group'>
                <% _.each(data, function(item){ %>
                <label class='label-inline radio-inline'>
                    <input type='radio' name='ratingLevel' value='<%= item.value%>'
                           onchange='pageLogic.showRatingType()'/><%= item.name%>
                </label>
                <% }); %>
            </div>
        </script>

        <script type="text/template" id="tpl_typeArea">
            <div class='label-group-box' id='ratingTypeArea'>
                <% _.each(data, function(item){ %>
                <label class='label-inline radio-inline'>
                    <input type='radio' name='ratingType' value='<%= item.value%>'/>
                    <span><%= item.name%></span>
                </label>
                <% }); %>
            </div>
        </script>

    </form>

    <h4 class="title"><span>委托人信息</span></h4>
    <form class="form-horizontal" id="clientForm">
        <div class="form-group">
            <label class="col-xs-2 text-right">
                <span><span style="color: red;">* </span>委托人</span>
            </label>
            <div class="col-xs-3">
                <input class="form-control" id="clientName" name="clientName">
            </div>
            <label class="col-xs-2 text-right">
                <span><span style="color: red;">* </span>委托人性质</span>
            </label>
            <div class="col-xs-4">
                <label class="radio-inline">
                    <input type="radio" class="styled" id="owner" name="nature" value="1" checked> 产权人</input>
                </label>
                <label class="radio-inline">
                    <input type="radio" class="styled" id="user" name="nature" value="2"> 使用人</input>
                </label>
                <label class="radio-inline">
                    <input type="radio" class="styled" id="third" name="nature" value="3"> 第三方</input>
                </label>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-2 text-right">
                <span><span style="color: red;">* </span>联系人</span>
            </label>
            <div class="col-xs-3">
                <input class="form-control" id="contactName" name="contactName">
            </div>
            <label class="col-xs-2 text-right">
                <span><span style="color: red;">* </span>联系电话</span>
            </label>
            <div class="col-xs-2">
                <input class="form-control" id="phone" name="phone">
            </div>
        <#--update by wangwj 20181213 start-->
            <div class="col-xs-2">
                <input class="form-control" id="phone2" name="phone2">
            </div>
        </div>
    <#--update by wangwj 20181213 end-->
        <div class="form-group">
            <label class="col-xs-2 text-right">
                <span>房屋产权证号</span>
            </label>
            <div class="col-xs-3">
                <input class="form-control" id="propertyNum" name="propertyNum"/>
            </div>
            <label class="col-xs-2 text-right">
                <span>房屋产权证</span>
            </label>
            <div class="col-xs-5">
                <div id="certificateFile"></div>
            </div>
        </div>
    <#--身份证号及其附件-->
        <div class="form-group">
            <label class="col-xs-2 text-right">
                <span><span style="color: red;">* </span>委托人身份证号</span>
            </label>
            <div class="col-xs-3">
                <input class="form-control" id="idNum" name="idNum">
            </div>
            <label class="col-xs-2 text-right">
                <span>委托人身份证</span>
            </label>
            <div class="col-xs-5">
                <div id="IDFile"></div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-2 text-right">
                <span>其他</span>
            </label>
            <div class="col-xs-10">
                <div id="otherFile"></div>
            </div>
        </div>
    </form>

    <h4 class="title"><span>房屋概况</span></h4>
    <div id="houseContainer"></div>

    <script type="text/template" id="tpl_house">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <%
                    var opt = "minus";
                    var color = "red";
                    if (index == 0) {
                    opt = "plus";
                    color = "#3598db";
                    }
                    %>
                    <i name="houseOpt" class="fa fa-<%= opt%>-circle"
                       style="color: <%= color%>; font-size: 1.5em"></i>&nbsp;&nbsp;<span><span
                        style="color: red;">* </span>房屋地址</span>
                </label>
                <div class="col-xs-1" style="padding-right:0">
                    <select class="form-control" name="zone"></select>
                </div>
                <div class="col-xs-1" style="padding-right:0">
                    <select class="form-control" name="street"></select>
                </div>
                <div class="col-xs-2">
                    <input class="form-control" name="address">
                </div>
                <label class="col-xs-1 text-right">
                    <span>丘权号</span>
                </label>
                <div class="col-xs-3">
                    <input class="form-control" name="hillock">
                </div>
            </div>
        </form>

        <div name="houseSplitInfo" class="box_group"></div>
    </script>

    <script type="text/template" id="tpl_houseSplit">
        <form class="form-horizontal" name="houseSplitForm">
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span><span style="color: red;">* </span>房屋名称</span>
                </label>
                <div class="col-xs-9">
                    <input class="form-control" name="houseName">
                </div>
                <div class="pull-right col-xs-1 text-right">
                    <a class="btn btn-link btn-toggle"><i class="fa"></i></a>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span><span style="color:red;">* </span>房屋结构</span>
                </label>
                <div class="col-xs-3">
                    <select class="form-control" name="structure"></select>
                    <input class="form-control" style="display: none;" name="otherContent">
                </div>
                <label class="col-xs-2 col-xs-offset-1 text-right">
                    <span><span style="color: red;">* </span>地上</span>
                </label>
                <div class="col-xs-3">
                    <div class="col-xs-4" style="padding-left:0;padding-right:0">
                        <div class="input-group">
                            <input class="form-control" name="layerAbove">
                            <span class="input-group-addon" style="padding:6px">层</span>
                        </div>
                    </div>
                    <label class="col-xs-4 text-right" style="padding:5px;line-height:24px">
                        <span>/地下</span>
                    </label>
                    <div class="col-xs-4" style="padding-left:0;padding-right:0">
                        <div class="input-group">
                            <input class="form-control" name="layerUnder">
                            <span class="input-group-addon" style="padding:6px">层</span>
                        </div>
                    </div>
                </div>

            </div>
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span><span style="color: red;">* </span>建筑年代</span>
                </label>
                <div class="col-xs-3">
                    <input class="form-control" name="buildYear"/>
                </div>
                <label class="col-xs-offset-1 col-xs-2 text-right">
                    <span><span style="color: red;">* </span>设计用途</span>
                </label>
                <div class="col-xs-3">
                    <select class="form-control" id="propertyNature<%= index%>" name="purpose"></select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span><span style="color: red;">* </span>建筑面积</span>
                </label>
                <div class="col-xs-3">
                    <div class="input-group">
                        <input class="form-control" name="area">
                        <span class="input-group-addon">M²</span>
                    </div>
                </div>
                <label class="col-xs-offset-1 col-xs-2 text-right">
                    <span><span style="color: red;">* </span>鉴定面积</span>
                </label>
                <div class="col-xs-3">
                    <div class="input-group">
                        <input class="form-control" name="identifyArea">
                        <span class="input-group-addon">M²</span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span>鉴定部位</span>
                </label>
                <div class="col-xs-3">
                    <input class="form-control" name="position">
                </div>
                <label class="col-xs-offset-1 col-xs-2 text-right">
                    <span>权利性质</span>
                </label>
                <div class="col-xs-3">
                    <input class="form-control" name="nature">
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span>产权人</span>
                </label>
                <div class="col-xs-3">
                    <input class="form-control" name="holderPerson">
                </div>
                <label class="col-xs-offset-1 col-xs-2 text-right">
                    <span>使用人</span>
                </label>
                <div class="col-xs-3">
                    <input class="form-control" name="person">
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span>施工单位</span>
                </label>
                <div class="col-xs-3">
                    <input class="form-control" name="constructOrg">
                </div>
                <label class="col-xs-offset-1 col-xs-2 text-right">
                    <span>设计单位</span>
                </label>
                <div class="col-xs-3">
                    <input class="form-control" name="designOrg">
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span>勘查单位</span>
                </label>
                <div class="col-xs-3">
                    <input class="form-control" name="prospectOrg">
                </div>
                <label class="col-xs-offset-1 col-xs-2 text-right">
                    <span>监理单位</span>
                </label>
                <div class="col-xs-3">
                    <input class="form-control" name="supervisionOrg">
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span>图纸材料</span>
                </label>
                <div class="col-xs-9">
                    <div name="constructFile"></div>
                </div>
            </div>
        </form>

        <%
        var display = "";
        if (index == 0) {
        display = "display: none";
        }
        %>

        <div class="pull-right" style="width:10%;margin-top:-50px;position: relative">
                <span style="position:absolute;margin-top:-34px;">
                    <button class="btn btn-md btn-link" name="delBtn">
                        <i class="fa fa-minus" style="font-size: 1.2em; color: red; <%=display%>"></i>
                    </button>
                </span>
            <button class="btn btn-md btn-link" name="addBtn">
                <i class="fa fa-plus" style="font-size: 1.2em"></i>
            </button>
        </div>
    </script>

    <div id="newProjectInfo" style="display: none">
        <h4 class="title"><span>新建工程调查表</span></h4>
        <form class="form-horizontal" id="newProForm">
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span><span style="color: red;">* </span>工程名称</span>
                </label>
                <div class="col-xs-3">
                    <input class="form-control" id="projectName" name="projectName">
                </div>
                <label class="col-xs-2 text-right">
                    <span>总建筑面积</span>
                </label>
                <div class="col-xs-3">
                    <div class="input-group">
                        <input class="form-control" id="totalArea" name="totalArea">
                        <span class="input-group-addon">M²</span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span><span style="color: red;">* </span>建设单位</span>
                </label>
                <div class="col-xs-3">
                    <input class="form-control" id="buildOrg" name="buildOrg">
                </div>
                <label class="col-xs-2 text-right">
                    <span>地上</span>
                </label>
                <div class="col-xs-3">
                    <div class="input-group">
                        <input class="form-control" id="layerGround" name="layerGround">
                        <span class="input-group-addon">层</span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span>设计单位</span>
                </label>
                <div class="col-xs-3">
                    <input class="form-control" id="designOrg" name="designOrg">
                </div>
                <label class="col-xs-2 text-right">
                    <span>地下</span>
                </label>
                <div class="col-xs-3">
                    <div class="input-group">
                        <input class="form-control" id="layerUnderGround" name="layerUnderGround">
                        <span class="input-group-addon">层</span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span>施工单位</span>
                </label>
                <div class="col-xs-3">
                    <input class="form-control" id="constructionOrg" name="constructionOrg">
                </div>
                <label class="col-xs-2 text-right">
                    <span>结构</span>
                </label>
                <div class="col-xs-3">
                    <input class="form-control" id="structure" name="structure">
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span>监理单位</span>
                </label>
                <div class="col-xs-3">
                    <input class="form-control" id="supervisionOrg" name="supervisionOrg">
                </div>
                <label class="col-xs-2 text-right">
                    <span>用途</span>
                </label>
                <div class="col-xs-3">
                    <input class="form-control" id="purpose" name="purpose">
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span>勘查单位</span>
                </label>
                <div class="col-xs-3">
                    <input class="form-control" id="prospectedOrg" name="prospectedOrg">
                </div>

            </div>
            <br>
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span>工程桩施工单位</span>
                </label>
                <div class="col-xs-3">
                    <input class="form-control" id="pileConstructionOrg" name="pileConstructionOrg">
                </div>
                <label class="col-xs-2 text-right">
                    <span>桩型</span>
                </label>
                <div class="col-xs-3">
                    <input class="form-control" id="pileModel" name="pileModel">
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span>工程桩设计单位</span>
                </label>
                <div class="col-xs-3">
                    <input class="form-control" id="pileDesignOrg" name="pileDesignOrg">
                </div>
                <label class="col-xs-2 text-right">
                    <span>桩长</span>
                </label>
                <div class="col-xs-3">
                    <div class="input-group">
                        <input class="form-control" id="pileLength" name="pileLength">
                        <span class="input-group-addon">米</span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span>开工时间</span>
                </label>
                <div class="col-xs-3">
                    <div class="input-group date form_datetime">
                        <input class="form-control" id="pileStartDate" name="pileStartDate" readonly="readonly"/>
                        <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                    </div>
                </div>
                <label class="col-xs-2 text-right">
                    <span>直径</span>
                </label>
                <div class="col-xs-3">
                    <div class="input-group">
                        <input class="form-control" id="pileDiameter" name="pileDiameter">
                        <span class="input-group-addon">米</span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span>竣工时间</span>
                </label>
                <div class="col-xs-3">
                    <div class="input-group date form_datetime">
                        <input class="form-control" id="pileEndDate" name="pileEndDate" readonly="readonly"/>
                        <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span>支护桩施工单位</span>
                </label>
                <div class="col-xs-3">
                    <input class="form-control" id="supportConstructionOrg" name="supportConstructionOrg">
                </div>
                <label class="col-xs-2 text-right">
                    <span>桩型</span>
                </label>
                <div class="col-xs-3">
                    <input class="form-control" id="supportModel" name="supportModel">
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span>支护桩设计单位</span>
                </label>
                <div class="col-xs-3">
                    <input class="form-control" id="supportDesignOrg" name="supportDesignOrg">
                </div>
                <label class="col-xs-2 text-right">
                    <span>桩长</span>
                </label>
                <div class="col-xs-3">
                    <div class="input-group">
                        <input class="form-control" id="supportLength" name="supportLength">
                        <span class="input-group-addon">米</span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span>开工时间</span>
                </label>
                <div class="col-xs-3">
                    <div class="input-group date form_datetime">
                        <input class="form-control" id="supportStartDate" name="supportStartDate" readonly="readonly"/>
                        <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                    </div>
                </div>
                <label class="col-xs-2 text-right">
                    <span>直径</span>
                </label>
                <div class="col-xs-3">
                    <div class="input-group">
                        <input class="form-control" id="supportDiameter" name="supportDiameter">
                        <span class="input-group-addon">米</span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span>竣工时间</span>
                </label>
                <div class="col-xs-3">
                    <div class="input-group date form_datetime">
                        <input class="form-control" id="supportEndDate" name="supportEndDate" readonly="readonly"/>
                        <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span>支撑结构</span>
                </label>
                <div class="col-xs-3">
                    <input class="form-control" id="supStructure" name="supStructure">
                </div>
                <label class="col-xs-2 text-right">
                    <span>间距</span>
                </label>
                <div class="col-xs-3">
                    <div class="input-group">
                        <input class="form-control" id="spacing" name="spacing">
                        <span class="input-group-addon">米</span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span>道路沉降量</span>
                </label>
                <div class="col-xs-3">
                    <input class="form-control" id="roadSettlement" name="roadSettlement">
                </div>
                <label class="col-xs-2 text-right">
                    <span>桩位移量</span>
                </label>
                <div class="col-xs-3">
                    <input class="form-control" id="pileDisplacement" name="pileDisplacement">
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span>建筑物沉降量</span>
                </label>
                <div class="col-xs-3">
                    <input class="form-control" id="buildingSettlement" name="buildingSettlement">
                </div>
            </div>
            <br>
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span>基坑面积</span>
                </label>
                <div class="col-xs-2">
                    <div class="input-group">
                        <input class="form-control" id="foundationArea" name="foundationArea">
                        <span class="input-group-addon">M²</span>
                    </div>
                </div>
                <label class="col-xs-1 text-right">开挖深度</label>
                <div class="col-xs-2">
                    <div class="input-group">
                        <input class="form-control" id="depth" name="depth">
                        <span class="input-group-addon">米</span>
                    </div>
                </div>
                <label class="col-xs-1 text-right">距邻房</label>
                <div class="col-xs-2">
                    <div class="input-group">
                        <input class="form-control" id="margin" name="margin">
                        <span class="input-group-addon">米</span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span>开挖时间</span>
                </label>
                <div class="col-xs-3">
                    <div class="input-group date form_datetime">
                        <input class="form-control" id="workDate" name="workDate" readonly="readonly"/>
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
                </div>
                <label class="col-xs-2 text-right">
                    <span>竣工时间</span>
                </label>
                <div class="col-xs-3">
                    <div class="input-group date form_datetime">
                        <input class="form-control" id="endDate" name="endDate" readonly="readonly"/>
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span>排水方式</span>
                </label>
                <div class="col-xs-3">
                    <input class="form-control" id="drainageMode" name="drainageMode">
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span>止水方案</span>
                </label>
                <div class="col-xs-8">
                    <textarea class="form-control" id="stopMode" name="stopMode"></textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span>房屋安全鉴定委托书附表</span>
                </label>
                <div class="col-xs-3">
                    <div id="clientFile"></div>
                </div>
                <div class="col-xs-2 text-right">
                    <span>岩土工程勘察报告</span>
                </div>
                <div class="col-xs-3">
                    <div id="reportFile"></div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span>新建工程建筑及结构图纸</span>
                </label>
                <div class="col-xs-3">
                    <div id="structureFile"></div>
                </div>
                <label class="col-xs-2 text-right">
                    <span>新建工程基坑支护方案</span>
                </label>
                <div class="col-xs-3">
                    <div id="foundationFile"></div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 text-right">
                    <span>鉴定房屋建筑及结构图纸</span>
                </label>
                <div class="col-xs-3">
                    <div id="identifyHouseFile"></div>
                </div>
            </div>
        </form>
    </div>
<#--update by wangwj 20181214 end-->
    <h4 class="geli_solid"></h4>

    <form class="form-horizontal" id="endClientForm">
        <div class="form-group">
            <label class="col-xs-2 text-right">
                <span><span style="color: red;">* </span>鉴定委托书</span>
            </label>
            <div class="col-xs-10">
                <div id="clientIdentifyFile"></div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-2 text-right">
                <span><span style="color: red;">* </span>房屋使用变迁、<br>改建、维修状况</span>
            </label>
            <div class="col-xs-3">
                <textarea class="form-control" id="condition" name="condition"></textarea>
            </div>
            <div class="col-xs-2 text-right">
                <span><span style="color: red;">* </span>委托鉴定目的及内容</span>
            </div>
            <div class="col-xs-3">
                <textarea class="form-control" id="content" name="content"></textarea>
            </div>
        </div>
    </form>
    <div class="button_area">
        <button class="btn btn-default btn-blue" id="saveBtn">
            <i class="fa fa-save"></i> 保存
        </button>
        <button class="btn btn-default btn-danger" id="backBtn">
            <i class="fa fa-reply"></i> 返回
        </button>
    </div>
</div>

<ex-section id="ScriptBody">
    <script type="text/javascript"
            src="${request.contextPath}${urls.getForLookupPath('/js/main/modules/identifyRequest.js')}"></script>
    <script type="text/javascript">
        pageLogic.initData = {
            modalParams: [
                {}
            ],
            restUrlPrefix: "/tasks"
        };
    </script>
</ex-section>
</body>
</html>
