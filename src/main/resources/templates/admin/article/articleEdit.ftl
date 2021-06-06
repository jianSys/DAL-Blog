<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>博客|文章添加</title>
    <link rel="stylesheet" href="../../../static/layui/layui/css/layui.css">
    <link rel="stylesheet" href="../../../static/editormd/css/editormd.css"/>
    <!-- 页面解析markdown为HTML显示需要的css -->
    <link rel="stylesheet" href="../../../static/editormd/css/editormd.preview.css"/>
</head>
<body>

<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-card-body" style="padding: 15px;">
            <div class="layui-form" method="post" action="" lay-filter="component-form-group">
                <div class="layui-form-item">
                    <label class="layui-form-label">文章标题</label>
                    <div class="layui-input-block">
                        <input id="title" type="text" name="title" lay-verify="title" autocomplete="off"
                               placeholder="请输入文章标题" class="layui-input">
                    </div>
                </div>
                <!-- 富文本编辑器 -->
                <div id="test-editormd"></div>
                <!--底部按钮-->
                <div class="layui-form-item layui-layout-admin">
                    <div class="layui-input-block">
                        <div class="layui-footer" style="left: 0;z-index:99;text-align: right">
                            <button id="blog-submit" data-type="test" class="layui-btn" lay-submit=""
                                    lay-filter="component-form-demo1">
                                发布
                            </button>
                            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="../../../static/jquery/jquery-3.2.1.min.js"></script>
<script src="../../../static/layui/layui/layui.js"></script>
<script src="../../../static/editormd/js/editormd.min.js"></script>
<!-- 页面markdown解析成HTML需要的js -->
<script src="../../../static/editormd/lib/marked.min.js"></script>
<script src="../../../static/editormd/lib/prettify.min.js"></script>
<script src="../../../static/editormd/lib/raphael.min.js"></script>
<script src="../../../static/editormd/lib/underscore.min.js"></script>
<script src="../../../static/editormd/lib/sequence-diagram.min.js"></script>
<script src="../../../static/editormd/lib/flowchart.min.js"></script>
<script src="../../../static/editormd/lib/jquery.flowchart.min.js"></script>
<script src="../../../static/admin/js/articleedit.js"></script>
</body>
</html>