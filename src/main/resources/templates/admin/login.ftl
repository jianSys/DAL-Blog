<!doctype html>
<html  class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="stylesheet" href="../../static/x-admin/css/font.css">
    <link rel="stylesheet" href="../../static/x-admin/css/login.css">
    <link rel="stylesheet" href="../../static/x-admin/css/xadmin.css">
    <script type="text/javascript" src="../../static/jquery/jquery-3.2.1.min.js"></script>
    <script src="../../static/layui/layui.js" charset="utf-8"></script>
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="login-bg">

<div class="login layui-anim layui-anim-up">
    <div class="message">后台登录</div>
    <div id="darkbannerwrap"></div>

    <form method="post" class="layui-form" >
        <input name="username" placeholder="用户名"  type="text" lay-verify="required" class="layui-input" >
        <hr class="hr15">
        <input name="password" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
        <hr class="hr15">
        <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
        <hr class="hr20" >
    </form>
</div>

<script>
    $(function  () {
        layui.use(['form'], function(){
            var form = layui.form,
            layer = layui.layer,
                $ = layui.jquery;
            // layer.msg('玩命卖萌中', function(){
            //   //关闭后的操作
            //   });
            //监听提交
            form.on('submit(login)', function(data){
                // data = data.field;
                // if (data.captcha == '') {
                //     layer.msg('请输入验证码');
                //     return false;
                // }
                //校验数据
                if (data.username == '') {
                    layer.msg('用户名不能为空');
                    return false;
                }
                if (data.password == '') {
                    layer.msg('密码不能为空');
                    return false;
                }
                console.log("数据......."+data.field);
                //发送ajax请求
                $.ajax({
                    type: 'POST',
                    url: '/admin/login',
                    dataType: "JSON",
                    data: JSON.stringify(data.field),
                    contentType: "application/json",
                    success: function (res) {
                        console.log(res);
                        if (res.code === 200) {
                            layui.data('LocalData', {
                                key: "access_token",
                                value: res.data.accessToken
                            });
                            //登陆成功跳转页面
                            layer.msg('登录成功', {time: 1000}, function () {
                                window.location = '/admin/index';
                            });
                        } else {
                            layer.msg(res.msg);
                            $("#img").click();
                        }
                    }

                });
                return false;
            });
        });
    })
</script>
</body>
</html>