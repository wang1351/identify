<html>
<head>
    <title>站内消息</title>
</head>
<body>

<div>
    <div class="row" style="margin-top: 10px;">
        <div class="col-sm-12">
            <div>
                <table id="btTable" class="table table-condensed table-hover table-striped"></table>
            </div>
        </div>
    </div>
</div>

<ex-section id="ScriptBody">
    <script type="text/javascript" src="${request.contextPath}${urls.getForLookupPath('/js/main/modules/msgList.js')}"></script>
    <script type="text/javascript">
        pageLogic.initData = {
            restUrlPrefix: "/sys/msgs",
            queryMsg: {
            }
        };

    </script>
</ex-section>
</body>
</html>
