<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>设置我的密码</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="../../../static/layui/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="../../../static/layui/style/admin.css" media="all">
</head>
<body>

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">修改密码</div>
                <div class="layui-card-body" pad15>

                    <div class="layui-form" lay-filter="passwordForm" id="passwordForm">
                        <div class="layui-form-item">
                            <label class="layui-form-label">当前密码</label>
                            <div class="layui-input-inline">
                                <input id="oldPassword" type="password" name="oldPassword" lay-verify="required"
                                       lay-verType="tips" class="layui-input">
                                <div id="old" class="layui-form-mid layui-word-aux"
                                     style="color: red !important;display: none">密码不正确,请重新输入
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">新密码</label>
                            <div class="layui-input-inline">
                                <input type="password" name="password" lay-verify="pass" lay-verType="tips"
                                       autocomplete="off" id="LAY_password" class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">6到16个字符</div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">确认新密码</label>
                            <div class="layui-input-inline">
                                <input type="password" id="newPassword" name="repassword" lay-verify="repass"
                                       lay-verType="tips" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button id="submit" class="layui-btn" lay-submit lay-filter="setmypass">确认修改</button>
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
    }).use(['index', 'set'], function () {
        var $ = layui.$
            , form = layui.form
            , layer = layui.layer
            , upload = layui.upload;
        $("#oldPassword").blur(function () {
            var data = {"oldPassword": $(this).val()}
            $.ajax({
                type: 'POST',//方法类型
                url: "./validation",
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json;charset=utf-8",
                success: function (res) {
                    if (res.code === 0) {
                        $("#old").hide();
                    } else {
                        $("#old").show();
                    }
                },
                error: function () {
                    //请求出错处理
                    layer.msg('发送失败', {icon: 5});
                }
            })
        }),

            form.on('submit(setmypass)', function (data) {
                $.ajax({
                    type: 'POST',//方法类型
                    url: "./updatePassword",
                    data: JSON.stringify(data.field),
                    dataType: "json",
                    contentType: "application/json;charset=utf-8",
                    success: function (res) {
                        if (res.code === 0) {
                            layer.msg('修改成功', {time: 1000},function () {
                                //window.location.href= '/admin/login';
                                parent.location.reload();//刷新整个页面
                            })
                        } else {
                            layer.msg('修改失败', {time: 1000});
                        }
                    },
                    error: function () {
                        //请求出错处理
                        layer.msg('发送失败', {icon: 5});
                    }
                })
            })
        /*$("#submit").on("click",function () {
          var data = {"newPassword":$("#newPassword").val()}
          $.ajax({
            type: 'POST',//方法类型
            url: "./updatePassword",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            success: function (res) {
              if (res.code === 0) {
                $("#old").hide();
              } else {
                $("#old").show();
              }
            },
            error: function () {
              //请求出错处理
              layer.msg('发送失败', {icon: 5});
            }
          })
        })*/
        /*//监听提交
        form.on('submit(passwordForm)', function (data) {
          var field = data.field; //获取提交的字段
          var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
          alert("你快看我")
          alert(JSON.stringify(field));
          //提交 Ajax 成功后，关闭当前弹层并重载表格
          //$.ajax({});
          parent.layui.table.reload('LAY-app-content-list'); //重载表格
          parent.layer.close(index); //再执行关闭
        });*/
    });
</script>
</body>
</html>