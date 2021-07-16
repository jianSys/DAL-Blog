<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>博客|文章管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="../../../static/admin/plugins/layui/layui/css/layui.css" media="all">
</head>
<body>

<div class="layui-form" lay-filter="layuiadmin-app-form-list" id="blogEditFrom" style="padding: 20px 30px 0 0;">
    <#--<div id="layui-layer-photos" class="layui-layer-content" style="height: 239px;">
        <div class="layui-layer-phimg">
            <img src="${file.fileUrl}" alt layer-pid="" style="cursor: move;">
        </div>
    </div>-->
    <div class="layui-form-item">
        <label class="layui-form-label">封面图</label>
        <div class="layui-input-inline">
            <input name="avatar" lay-verify="required" id="photoUrl" placeholder="图片地址" value="${file.fileUrl}" class="layui-input">
        </div>
        <div class="layui-input-inline layui-btn-container" style="width: auto;">
            <button type="button" class="layui-btn layui-btn-primary" id="uploadImages">
                <i class="layui-icon">&#xe67c;</i>上传图片
            </button>
<#--            <button class="layui-btn layui-btn-primary" layadmin-event="avartatPreview">查看图片</button >-->
        </div>
    </div>
</div>

<script src="../../../static/admin/plugins/layui/layui/layui.js"></script>
<script src="../../../static/admin/plugins/jquery/jquery-3.2.1.min.js"></script>
<script>
    layui.config({
        base: '../../../static/admin/plugins/layui/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'form', 'set', 'upload'], function () {
        var $ = layui.$
            , form = layui.form
            , layer = layui.layer
            , upload = layui.upload;


        $(function () {
            var parent_json = eval('('+parent.json+')');
            console.dir("+++++++++++++++++============"+parent_json)
            console.log("父页面获取的值==============="+parent_json);
        })
    });
</script>
</body>
</html>