

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>登录</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="../../static/admin/plugins/layui/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="../../static/admin/plugins/layui/style/admin.css" media="all">
  <link rel="stylesheet" href="../../static/admin/plugins/layui/style/login.css" media="all">
</head>
<body>

  <div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">

    <div class="layadmin-user-login-main">
      <div class="layadmin-user-login-box layadmin-user-login-header">
        <h2>Dal-Blog</h2>
      </div>
      <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
        <div class="layui-form-item">
          <label class="layadmin-user-login-icon layui-icon layui-icon-username" for="LAY-user-login-username"></label>
          <input type="text" name="username" id="LAY-user-login-username" lay-verify="required" placeholder="用户名" class="layui-input">
        </div>
        <div class="layui-form-item">
          <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-password"></label>
          <input type="password" name="password" id="LAY-user-login-password" lay-verify="required" placeholder="密码" class="layui-input">
        </div>
        <div class="layui-form-item">
          <div class="layui-row">
            <div class="layui-col-xs7">
              <label class="layadmin-user-login-icon layui-icon layui-icon-vercode" for="LAY-user-login-vercode"></label>
              <input type="text" name="vercode" id="LAY-user-login-vercode" lay-verify="required" placeholder="图形验证码" class="layui-input">
            </div>
            <div class="layui-col-xs5">
              <div style="margin-left: 10px;">
                <img src="/admin/captcha/generate" class="layadmin-user-login-codeimg" id="captchaImage">
              </div>
            </div>
          </div>
        </div>
        <div class="layui-form-item" style="margin-bottom: 20px;">
          <input type="checkbox" name="remember" lay-skin="primary" title="记住密码">
          <a href="forget.html" class="layadmin-user-jump-change layadmin-link" style="margin-top: 7px;">忘记密码？</a>
        </div>
        <div class="layui-form-item">
          <button class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-login-submit">登 入</button>
        </div>
        <div class="layui-trans layui-form-item layadmin-user-login-other">
          <label>社交账号登入</label>
          <a href="javascript:;"><i class="layui-icon layui-icon-login-qq"></i></a>
          <a href="javascript:;"><i class="layui-icon layui-icon-login-wechat"></i></a>
          <a href="javascript:;"><i class="layui-icon layui-icon-login-weibo"></i></a>
          
<#--          <a href="reg.html" class="layadmin-user-jump-change layadmin-link">注册帐号</a>-->
        </div>
      </div>
    </div>
    
    <#--<div class="layui-trans layadmin-user-login-footer">

      <p>© 2018 <a href="http://www.layui.com/" target="_blank">layui.com</a></p>
      <p>
        <span><a href="http://www.layui.com/admin/#get" target="_blank">获取授权</a></span>
        <span><a href="http://www.layui.com/admin/pro/" target="_blank">在线演示</a></span>
        <span><a href="http://www.layui.com/admin/" target="_blank">前往官网</a></span>
      </p>
    </div>
    -->
    <!--<div class="ladmin-user-login-theme">
      <script type="text/html" template>
        <ul>
          <li data-theme=""><img src="{{ layui.setter.base }}style/res/bg-none.jpg"></li>
          <li data-theme="#03152A" style="background-color: #03152A;"></li>
          <li data-theme="#2E241B" style="background-color: #2E241B;"></li>
          <li data-theme="#50314F" style="background-color: #50314F;"></li>
          <li data-theme="#344058" style="background-color: #344058;"></li>
          <li data-theme="#20222A" style="background-color: #20222A;"></li>
        </ul>
      </script>
    </div>-->
    
  </div>

  <script src="../../static/admin/plugins/layui/layui/layui.js"></script>
  <script>
  layui.config({
    base: '../../../static/admin/plugins/layui/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'user'], function(){
    var $ = layui.$
    ,setter = layui.setter
    ,admin = layui.admin
    ,form = layui.form
    ,router = layui.router()
    ,search = router.search
    ,popup = layui.popup;

    let captchaPath = "/admin/captcha/generate";


    form.render();

    //提交
    form.on('submit(LAY-user-login-submit)', function(data){
      let loader = layer.load();
      //let btn = button.load({elem: '.login'});
      //请求登入接口
      $.ajax({
        url: '/admin/login' //实际使用请改成服务端真实接口
        ,type:'Post'
        ,dataType: "JSON"
        ,data: JSON.stringify(data.field)
        ,contentType: "application/json"
        ,success: function(res){
          layer.close(loader);
          if (res.code === 0){
            layer.msg(res.msg,function(){
              location.href = "/admin/index";
            })
          }else{
            layer.msg(res.msg,function(){
              document.getElementById("captchaImage").src=captchaPath + "?" + Math.random();
            });
          }
        }
      });
      
    });
    $("#captchaImage").click(function(){
      document.getElementById("captchaImage").src= captchaPath + "?" + Math.random();
    })
    setInterval(function(){
      document.getElementById("captchaImage").src= captchaPath + "?" + Math.random();
    },30 * 1000);
  });
  </script>
</body>
</html>