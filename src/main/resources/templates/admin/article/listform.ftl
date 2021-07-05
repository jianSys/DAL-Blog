<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>博客|文章管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="../../../static/layui/layui/css/layui.css" media="all">
</head>
<body>

<div class="layui-form" lay-filter="layuiadmin-app-form-list" id="blogEditFrom" style="padding: 20px 30px 0 0;">
    <div class="layui-form-item">
        <label class="layui-form-label">封面图</label>
        <div class="layui-input-inline">
            <input name="avatar" lay-verify="required" id="photoUrl" placeholder="图片地址" value="" class="layui-input">
        </div>
        <div class="layui-input-inline layui-btn-container" style="width: auto;">
            <button type="button" class="layui-btn layui-btn-primary" id="uploadImages">
                <i class="layui-icon">&#xe67c;</i>上传图片
            </button>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">分类</label>
        <div class="layui-input-inline">
            <select name="label" lay-verify="required" lay-filter="category_test" id="category">
                <option value=""></option>
            </select>
        </div>
    </div>
    <div class="layui-form-item" pane="">
        <label class="layui-form-label">标签</label>
        <div class="layui-input-block" id="tags">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">简介</label>
        <div class="layui-input-block">
            <textarea name="introduce" placeholder="文章简介" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">允许评论</label>
        <div class="layui-input-block">
            <input type="radio" lay-filter="comments" name="comments" value="0" title="是" checked="">
            <input type="radio" lay-filter="comments" name="comments" value="1" title="否">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">是否置顶</label>
        <div class="layui-input-inline">
            <input type="checkbox" lay-verify="required" lay-filter="top" name="top" lay-skin="switch" lay-text="开启|关闭">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">发布状态</label>
        <div class="layui-input-inline">
            <input type="checkbox" lay-verify="required" lay-filter="status" name="status" lay-skin="switch"
                   lay-text="发布|草稿">
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <input type="button" lay-submit lay-filter="layuiadmin-app-form-submit" id="layuiadmin-app-form-submit"
               value="确认添加">
        <input type="button" lay-submit lay-filter="layuiadmin-app-form-edit" id="layuiadmin-app-form-edit"
               value="确认编辑">
    </div>
</div>

<script src="../../../static/layui/layui/layui.js"></script>
<script src="../../../static/admin/jquery/jquery-3.2.1.min.js"></script>
<script src="../../../static/admin/js/listfrom.js"></script>
</body>
</html>