<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>DAL</title>

    <link href="../../static/blog/css/animate.min.css" rel="stylesheet" type="text/css">
    <link href="../../static/layui/layui/css/layui.css" rel="stylesheet" type="text/css">
    <!-- 页面解析markdown为HTML显示需要的css -->
    <link rel="stylesheet" href="../../static/editormd/css/editormd.preview.min.css" />
    <link href="../../static/blog/css/base.css" rel="stylesheet" type="text/css">
    <link href="../../static/blog/css/details.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/font-awesome/css/font-awesome.min.css">
</head>

<body class="">
<#include "./header.ftl">
<div class="blog-section" id="blog-section1">
    <div class="fp-tablecell">
        <div class="page1">
            <div class="nav wow zoomIn" data-wow-duration="2s">
                <h1>${blog.blogTitle!''}</h1>
                <span>${blog.time!'时间未知'}
                    <div class="blog-views">${blog.blogViews!'0'}浏览</div>
                </span>
            </div>
        </div>
    </div>
</div>
<div class="blog-section" id="blog-section2">
    <div class="fp-tablecell">
        <div class="page2">
            <div class="warp-box" >
                    <!-- 页面解析markdown为HTML显示 -->
                    <div id="test-editormd">
                        <textarea style="display: none" name="test-editormd-markdown-doc">${blog.blogContent}</textarea>
                    </div>
                    <aside class="blog-rights">
                        <p>本站文章除注明转载/出处外，皆为作者原创，欢迎转载，但未经作者同意必须保留此段声明，且在文章页面明显位置给出原文链接，否则保留追究法律责任的权利。</p>
                    </aside>
            </div>
        </div>
    </div>
</div>
<#include "./footer.ftl">
<script src="../../static/blog/js/jquery.min.js"></script>
<script src="../../static/editormd/js/editormd.min.js"></script>
<script src="../../static/blog/layui/layui.js"></script>
<script src="../../static/blog/js/hearder.js"></script>

<!-- 页面markdown解析成HTML需要的js -->
<script src="../../static/editormd/lib/marked.min.js"></script>
<script src="../../static/editormd/lib/prettify.min.js"></script>
<script src="../../static/editormd/lib/raphael.min.js"></script>
<script src="../../static/editormd/lib/underscore.min.js"></script>
<script src="../../static/editormd/lib/sequence-diagram.min.js"></script>
<script src="../../static/editormd/lib/flowchart.min.js"></script>
<script src="../../static/editormd/lib/jquery.flowchart.min.js"></script>
<#--<script src="https://l2dwidget.js.org/lib/L2Dwidget.min.js"></script>-->
<#--<script src="https://eqcn.ajz.miesnfu.com/wp-content/plugins/wp-3d-pony/live2dw/lib/L2Dwidget.min.js"></script>-->

<script>
   $(function () {
        var testEditor;
        //页面加载完成调用此方法
        // 页面解析markdown为html进行显示
        $(document).ready( function() {
            testEditor = editormd.markdownToHTML("test-editormd", {
                htmlDecode :"style, script, iframe",
                //markdownSourceCode : true,
                tocm            : true, //对目录解析
                emoji           : true,
                taskList        : true,
                tex             : true,  // 默认不解析
                flowChart       : true,  // 默认不解析
                sequenceDiagram : true,  // 默认不解析
            });
        });
    });
   /*L2Dwidget.init({
       "model": {
           // jsonPath: "https://unpkg.com/live2d-widget-model-koharu@1.0.5/assets/koharu.model.json",<!--这里改模型，前面后面都要改-->
           jsonPath:"https://unpkg.com/live2d-widget-model-shizuku@1.0.5/assets/shizuku.model.json ",
           // jsonPath:"https://unpkg.com/live2d-widget-model-miku@1.0.5/assets/miku.model.json",
           "scale": 1
       },
       "display": {
           "position": "left",<!--设置看板娘的上下左右位置-->
           "width": 75,
           "height": 150,
           "hOffset": 0,
           "vOffset": -66
       },
       "mobile": {
           "show": true,
           "scale": 0.5
       },
       "react": {
           "opacityDefault": 0.7,<!--设置透明度-->
           "opacityOnHover": 0.2
       },
       "dialog": {
           "enable": true,
           "script": {
               //每20s，显示一言（调用一言Api返回的句子）
               'every idle 20s': '$hitokoto$',
               //触摸到class='star'对象
               'hover .star': '星星在天上而你在我心里 (*!/ω＼*)',
               //触摸到身体
               'tap body': '害羞⁄(⁄ ⁄•⁄ω⁄•⁄ ⁄)⁄',
               //触摸到头部
               'tap face': '~~'
           }
       }
   });*/
</script>
<script src="https://cdn.jsdelivr.net/gh/stevenjoezhang/live2d-widget@latest/autoload.js"></script>
<script>
    console.clear();
    console.log("%c 一天很短,开心了就笑,不开心了就一会在笑", "background:#24272A; color:#ffffff", "");
</script>
</body>
</html>