

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>网站设置</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="../../../static/layui/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="../../../static/layui/style/admin.css" media="all">
</head>
<body>

  <div class="layui-fluid">
    <div class="layui-row layui-col-space15">
      <div class="layui-col-md12">
        <div class="layui-card">
          <div class="layui-card-header">网站设置</div>
          <div class="layui-card-body" pad15>
            
            <div class="layui-form" wid100 lay-filter="">
              <div class="layui-form-item">
                <label class="layui-form-label">网站名称</label>
                <div class="layui-input-block">
                  <input type="text" name="sitename" value="layuiAdmin" class="layui-input">
                </div>
              </div>
              <div class="layui-form-item">
                <label class="layui-form-label">网站描述</label>
                <div class="layui-input-block">
                  <input type="text" name="description" value="layuiAdmin" class="layui-input">
                </div>
              </div>
              <div class="layui-form-item">
                <label class="layui-form-label">网站logo</label>
                <div class="layui-input-block">
                  <input type="text" name="logo" value="layuiAdmin" class="layui-input">
                </div>
              </div>
              <div class="layui-form-item">
                <label class="layui-form-label">favicon.ico</label>
                <div class="layui-input-block">
                  <input type="text" name="icon" value="layuiAdmin" class="layui-input">
                </div>
              </div>
              <div class="layui-form-item">
                <label class="layui-form-label">底部About</label>
                <div class="layui-input-block">
                  <input type="text" name="footerAbout" value="layuiAdmin" class="layui-input">
                </div>
              </div>
              <div class="layui-form-item">
                <label class="layui-form-label">底部备案号</label>
                <div class="layui-input-block">
                  <input type="text" name="footerICP" value="layuiAdmin" class="layui-input">
                </div>
              </div>
              <div class="layui-form-item">
                <label class="layui-form-label">底部Copy Right</label>
                <div class="layui-input-block">
                  <input type="text" name="footerCopyRight" value="layuiAdmin" class="layui-input">
                </div>
              </div>
              <div class="layui-form-item">
                <label class="layui-form-label">底部Powered By</label>
                <div class="layui-input-block">
                  <input type="text" name="footerPoweredBy" value="layuiAdmin" class="layui-input">
                </div>
              </div>
              <div class="layui-form-item">
                <label class="layui-form-label">底部Powered By URL</label>
                <div class="layui-input-block">
                  <input type="text" name="footerPoweredByURL" value="layuiAdmin" class="layui-input">
                </div>
              </div>
              <div class="layui-form-item">
                <div class="layui-input-block">
                  <button class="layui-btn" lay-submit lay-filter="set_website" id="submit">确认保存</button>
                </div>
              </div>
            </div>
            
          </div>
        </div>
      </div>
    </div>
  </div>

  <script src="../../../static/layui/layui/layui.js"></script>
  <script>
  layui.config({
    base: '../../../static/layui/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'set'],function () {
    var $ = layui.$
            , admin = layui.admin
            , element = layui.element
            , layer = layui.layer
            , upload = layui.upload;

    $(document).ready(function () {
      $.ajax({
        type: 'get',//方法类型
        url: "./getWebSite",
        //data: JSON.stringify(map),
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success: function (res) {
          if (res.code === 0) {
            $("input[name='sitename']").val(res.data.websiteName);
            $("input[name='description']").val(res.data.websiteDescription);
            $("input[name='logo']").val(res.data.websiteLogo);
            $("input[name='icon']").val(res.data.websiteIcon);
            $("input[name='footerAbout']").val(res.data.footerAbout);
            $("input[name='footerICP']").val(res.data.footerICP);
            $("input[name='footerCopyRight']").val(res.data.footerCopyRight);
            $("input[name='footerPoweredBy']").val(res.data.footerPoweredBy);
            $("input[name='footerPoweredByURL']").val(res.data.footerPoweredByURL);
          //  console.log("看看请求到的数据是啥"+JSON.stringify(res));
          } else {
            layer.msg(res.msg);
            $("#img").click();
          }
        },
        error: function () {
          //请求出错处理
          layer.msg('发送失败', {icon: 5});
        }
      })
    });

    $("#submit").on("click",function () {
      var sitename = $("input[name='sitename']").val();
      var description = $("input[name='description']").val();
      var logo = $("input[name='logo']").val();
      var icon = $("input[name='icon']").val();
      var about = $("input[name='footerAbout']").val();
      var icp = $("input[name='footerICP']").val();
      var copyRight = $("input[name='footerCopyRight']").val();
      var poweredBy = $("input[name='footerPoweredBy']").val();
      var poweredByUrl = $("input[name='footerPoweredByURL']").val();

      var map={
        "websiteName":sitename,
        "websiteDescription":description,
        "websiteLogo":logo,
        "websiteIcon":icon,
        "footerAbout":about,
        "footerICP":icp,
        "footerCopyRight":copyRight,
        "footerPoweredBy":poweredBy,
        "footerPoweredByURL":poweredByUrl
      };
      //console.log("==================================="+JSON.stringify(map));
      $.ajax({
        type: 'POST',//方法类型
        url: "./saveSite",
        data: JSON.stringify(map),
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success: function (res) {
          if (res.code === 0) {
            //跳转文章列表页面
            layer.msg('添加成功', {time: 1000}, function () {
              //window.location = '/admin/article/toArticleList';
            });
          } else {
            layer.msg(res.msg);
            $("#img").click();
          }
        },
        error: function () {
          //请求出错处理
          layer.msg('发送失败', {icon: 5});
        }
      })
    })

  });
  </script>
</body>
</html>