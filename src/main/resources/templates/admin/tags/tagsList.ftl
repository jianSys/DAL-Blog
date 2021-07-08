<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>博客|标签管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="../../../static/admin/plugins/layui/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="../../../static/admin/plugins/layui/style/admin.css" media="all">
</head>
<body>

<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-card-header layuiadmin-card-header-auto">
            <button class="layui-btn layuiadmin-btn-tags" data-type="add">添加</button>
        </div>
        <div class="layui-card-body">
            <table id="LAY-app-content-tags" lay-filter="LAY-app-content-tags"></table>
            <script type="text/html" id="layuiadmin-app-cont-tagsbar">
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i
                            class="layui-icon layui-icon-edit"></i>编辑</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i
                            class="layui-icon layui-icon-delete"></i>删除</a>
            </script>
        </div>
    </div>
</div>

<script src="../../../static/admin/plugins/layui/layui/layui.js"></script>
<script src="../../../static/admin/dist/js/tagslist.js"></script>
</body>
</html>
