<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layuiAdmin 主页示例模板二</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="../../static/layui/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="../../static/layui/style/admin.css" media="all">
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

    </div>
</div>
</div>

<script src="../../static/layui/layui/layui.js"></script>
<script>
    layui.config({
        base: '../../static/layui/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'sample'], function () {
        var layer = layui.layer
            , $ = layui.jquery
            , form = layui.form
            , admin = layui.admin
            , upload = layui.upload;
        $(document).ready(function () {
            $.ajax({
                url: "../getIndexShow",
                type: 'GET',
                dataType: 'json',
                contentType: "application/json;charset=utf-8",
                success: function (res) {
                    if (res.code === 0) {
                        var data = res.data;
                        //console.log("返回的数据是=================+" + JSON.stringify(data))

                    } else {
                        layer.msg("查询失败")
                    }
                }
            });
        });
      /**
       * 计算时间
       * @param time
       * @returns {string|*}
       */
      function timeAgo(time) {
        var currentTime = new Date().getTime()
        var between = currentTime - time
        var days = Math.floor(between / (24 * 3600 * 1000))
        if (days === 0) {
          var leave1 = between % (24 * 3600 * 1000)
          var hours = Math.floor(leave1 / (3600 * 1000))
          if (hours === 0) {
            var leave2 = leave1 % (3600 * 1000)
            var minutes = Math.floor(leave2 / (60 * 1000))
            if (minutes === 0) {
              var leave3 = leave2 % (60 * 1000)
              var seconds = Math.round(leave3 / 1000)
              return seconds + ' 秒前'
            }
            return minutes + ' 分钟前'
          }
          return hours + ' 小时前'
        }
        if (days < 0) {
          return '刚刚'
        }
        if (days < 1) {
          return days + ' 天前'
        } else {
          return formatDate(time, 'yyyy/MM/dd hh:mm');
        }
      }
    });
</script>
</body>
</html>