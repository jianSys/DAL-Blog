

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>设置我的资料</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="../../../static/admin/plugins/layui/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="../../../static/admin/plugins/layui/style/admin.css" media="all">
</head>
<body>

  <div class="layui-fluid">
    <div class="layui-row layui-col-space15">
      <div class="layui-col-md12">
        <div class="layui-card">
          <div class="layui-card-header">设置我的资料</div>
          <div class="layui-card-body" pad15>
            
            <div class="layui-form" lay-filter="">
              <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-inline">
                  <input type="text" name="username" value="xianxin" <#--readonly--> class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">谨慎修改,用于后台登录</div>
              </div>
              <div class="layui-form-item">
                <label class="layui-form-label">昵称</label>
                <div class="layui-input-inline">
                  <input type="text" name="nickname" value="贤心" lay-verify="nickname" autocomplete="off" placeholder="请输入用户名" class="layui-input">
                </div>
              </div>
              <#--<div class="layui-form-item">
                <label class="layui-form-label">头像</label>
                <div class="layui-input-inline">
                  <input name="avatar" lay-verify="required" id="LAY_avatarSrc" placeholder="图片地址" value="http://cdn.layui.com/avatar/168.jpg" class="layui-input">
                </div>
                <div class="layui-input-inline layui-btn-container" style="width: auto;">
                  <button type="button" class="layui-btn layui-btn-primary" id="LAY_avatarUpload">
                    <i class="layui-icon">&#xe67c;</i>上传图片
                  </button>
                  <button class="layui-btn layui-btn-primary" layadmin-event="avartatPreview">查看图片</button >
                </div>
             </div>-->
              <div class="layui-form-item">
                <label class="layui-form-label">邮箱</label>
                <div class="layui-input-inline">
                  <input type="text" name="email" value="" lay-verify="email" autocomplete="off" class="layui-input">
                </div>
              </div>
              <div class="layui-form-item">
                <div class="layui-input-block">
                  <button class="layui-btn" lay-submit lay-filter="setmyinfo">确认修改</button>
                  <button type="reset" class="layui-btn layui-btn-primary">重新填写</button>
                </div>
              </div>
            </div>
            
          </div>
        </div>
      </div>
    </div>
  </div>

  <script src="../../../static/admin/plugins/layui/layui/layui.js"></script>
  <script>
  layui.config({
    base: '../../../static/admin/plugins/layui/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'set'],function () {
    var $ = layui.$
            , form = layui.form
            , layer = layui.layer
            , upload = layui.upload;

    $(function () {
      $.ajax({
        type: 'get',//方法类型
        url: "./getUserInfo",
        //data: JSON.stringify(map),
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success: function (res) {
          if (res.code === 0) {
            $("input[name='username']").val(res.data.loginUserName);
            $("input[name='nickname']").val(res.data.nickName);
            $("input[name='email']").val(res.data.email);
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
  });
  </script>
</body>
</html>