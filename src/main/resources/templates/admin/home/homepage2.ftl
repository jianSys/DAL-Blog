

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>layuiAdmin 主页示例模板二</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
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
              <dd>
                <div>
                  <p>胡歌 在 <a lay-href="http://fly.layui.com/vipclub/list/layuiadmin/">layuiadmin专区</a>
                    回答问题</p>
                  <span>几秒前</span>
                </div>
              </dd>
              <dd>
                    <div>
                  <p>彭于晏 在 <a lay-href="http://fly.layui.com/vipclub/list/layuiadmin/">layuiadmin专区</a>
                    进行了 <a lay-href="http://fly.layui.com/vipclub/list/layuiadmin/column/quiz/">提问</a>
                  </p>
                  <span>2天前</span>
                </div>
              </dd>
              <dd>
                 <div>
                  <p>贤心 将 <a lay-href="http://www.layui.com/">layui</a> 更新至 2.3.0 版本</p>
                  <span>7天前</span>
                </div>
              </dd>
              <dd>
                 <div>
                  <p>吴尊 在 <a lay-href="http://fly.layui.com/">Fly社区</a> 发布了 <a
                            lay-href="http://fly.layui.com/column/suggest/">建议</a></p>
                  <span>7天前</span>
                </div>
              </dd>
              <dd>
                <div>
                  <p>许上进 在 <a lay-href="http://fly.layui.com/">Fly社区</a> 发布了 <a
                            lay-href="http://fly.layui.com/column/suggest/">建议</a></p>
                  <span>8天前</span>
                </div>
              </dd>
              <dd>
                <div>
                  <p>小蚊子 在 <a lay-href="http://fly.layui.com/vipclub/list/layuiadmin/">layuiadmin专区</a>
                    进行了 <a lay-href="http://fly.layui.com/vipclub/list/layuiadmin/column/quiz/">提问</a>
                  </p>
                  <span>8天前</span>
                </div>
              </dd>
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
  }).use(['index', 'sample'],function () {
    var layer = layui.layer
            , $ = layui.jquery
            , form = layui.form
            , admin = layui.admin
            ,upload = layui.upload;
    $(document).ready(function () {
      $.ajax({
        url: "../getIndexShow",
        type: 'GET',
        dataType: 'json',
        contentType: "application/json;charset=utf-8",
        success: function (res) {
          if (res.code === 0) {
            var data = res.data;
            console.log("返回的数据是=================+"+JSON.stringify(data))

          } else {
            layer.msg("查询失败")
          }
        }
      });
    });
  });
  </script>
</body>
</html>