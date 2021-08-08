<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>DAL</title>

    <link href="../../../static/blog/amaze/css/animate.min.css" rel="stylesheet" type="text/css">
    <link href="../../../static/blog/plugins/layui/css/layui.css" rel="stylesheet" type="text/css">
    <!-- 页面解析markdown为HTML显示需要的css -->
    <link rel="stylesheet" href="../../../static/admin/plugins/editormd/css/editormd.preview.min.css" />
    <link href="../../../static/blog/amaze/css/base.css" rel="stylesheet" type="text/css">
    <link href="../../../static/blog/amaze/css/details.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/font-awesome/css/font-awesome.min.css">
</head>

<body class="">
<#include "header.ftl">
<div class="blog-section" id="blog-section1" style="background-image:url(../../../static/blog/amaze/image/md1.jpeg)!important;">
    <div class="fp-tablecell">
        <div class="page1">
            <div class="nav wow zoomIn" data-wow-duration="2s">
                <h1>归档</h1>
            </div>
        </div>
    </div>
</div>
<div class="blog-section" id="blog-section2">
    <div class="fp-tablecell">
        <div class="page2">
            <div class="warp-box" >
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-header">归档</div>
                        <div class="layui-card-body">
                            <ul class="layui-timeline">
                                <#list blog as bg>
                                <li class="layui-timeline-item">
                                    <i class="layui-icon layui-timeline-axis"></i>
                                    <div class="layui-timeline-content layui-text">
                                        <h3 class="layui-timeline-title">${bg.time}</h3>
                                        <p>
                                            <a href="../toBlog/${bg.blogId}">${bg.blogIntroduce!'文章简介'}</a>
                                        </p>
                                    </div>
                                </li>
                                </#list>
                            </ul>

                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
<#include "footer.ftl">
<script src="../../../static/blog/amaze/js/jquery.min.js"></script>
<script src="../../../static/admin/plugins/editormd/js/editormd.min.js"></script>
<script src="../../../static/blog/plugins/layui/layui.js"></script>
<script src="../../../static/blog/amaze/js/hearder.js"></script>

<!-- 页面markdown解析成HTML需要的js -->
<script src="../../../static/admin/plugins/editormd/lib/marked.min.js"></script>
<script src="../../../static/admin/plugins/editormd/lib/prettify.min.js"></script>
<script src="../../../static/admin/plugins/editormd/lib/raphael.min.js"></script>
<script src="../../../static/admin/plugins/editormd/lib/underscore.min.js"></script>
<script src="../../../static/admin/plugins/editormd/lib/sequence-diagram.min.js"></script>
<script src="../../../static/admin/plugins/editormd/lib/flowchart.min.js"></script>
<script src="../../../static/admin/plugins/editormd/lib/jquery.flowchart.min.js"></script>
<#--<script src="https://l2dwidget.js.org/lib/L2Dwidget.min.js"></script>-->
<#--<script src="https://eqcn.ajz.miesnfu.com/wp-content/plugins/wp-3d-pony/live2dw/lib/L2Dwidget.min.js"></script>-->
<script>
    layui.config({
        base: '../../../static/admin/plugins/layui/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'set'],function () {
        var $ = layui.$
            , admin = layui.admin
            , element = layui.element
            , layer = layui.layer
            , upload = layui.upload;
    })
</script>
<script>
    console.clear();
    console.log("%c 一天很短,开心了就笑,不开心了就一会在笑", "background:#24272A; color:#ffffff", "");
</script>
</body>
</html>