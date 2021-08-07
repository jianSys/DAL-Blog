<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>导航页</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="../../static/admin/plugins/layui/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="../../static/admin/plugins/layui/style/admin.css" media="all">
</head>
<body>

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">

        <div class="layui-col-sm6 layui-col-md3">
            <div class="layui-card">
                <div class="layui-card-header">
                    总访问量
                </div>
                <div class="layui-card-body layuiadmin-card-list">
                    <p class="layuiadmin-big-font">${viewsCount}</p>
                </div>
            </div>
        </div>
        <div class="layui-col-sm6 layui-col-md3">
            <div class="layui-card">
                <div class="layui-card-header">
                    总文章数
                </div>
                <div class="layui-card-body layuiadmin-card-list">
                    <p class="layuiadmin-big-font">${articleCount}</p>
                </div>
            </div>
        </div>
        <div class="layui-col-sm6 layui-col-md3">
            <div class="layui-card">
                <div class="layui-card-header">
                    总文章数
                </div>
                <div class="layui-card-body layuiadmin-card-list">
                    <p class="layuiadmin-big-font">${articleCount}</p>
                </div>
            </div>
        </div>
        <div class="layui-col-sm6 layui-col-md3">
            <div class="layui-card">
                <div class="layui-card-header">
                    总文章数
                </div>
                <div class="layui-card-body layuiadmin-card-list">
                    <p class="layuiadmin-big-font">${articleCount}</p>
                </div>
            </div>
        </div>
        <div class="layui-col-sm12 layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header">动态</div>
                <div class="layui-card-body">
                    <dl class="layuiadmin-card-status">
                      <#list logs as logs>
                        <dd>
                            <div>
                                <p>${logs.operation}</p>
                                <span>${logs.time}</span>
                            </div>
                        </dd>
                        </#list>
                    </dl>
                </div>
            </div>
        </div>
        <div class="layui-col-sm12 layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header">折线图</div>
                <div class="layui-card-body">

                    <div class="layui-carousel layadmin-carousel layadmin-dataview" data-anim="fade" lay-filter="LAY-index-normline">
                        <div carousel-item id="LAY-index-normline">
                            <div><i class="layui-icon layui-icon-loading1 layadmin-loading"></i></div>
                        </div>
                    </div>

                </div>
            </div>
        </div>

    </div>
</div>
</div>
<script src="../../static/admin/plugins/layui/layui/layui.js"></script>
<script>
    layui.config({
        base: '../../../static/admin/plugins/layui/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'senior']);
</script>
    </body>
</html>