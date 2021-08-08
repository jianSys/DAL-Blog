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
    <link rel="stylesheet" href="../../../static/admin/plugins/editormd/css/editormd.preview.min.css"/>
    <link href="../../../static/blog/amaze/css/base.css" rel="stylesheet" type="text/css">
    <link href="../../../static/blog/amaze/css/details.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/font-awesome/css/font-awesome.min.css">
    <style>
        .comment-editor {
            box-sizing: border-box;
            position: relative;
            z-index: 1;
            -webkit-animation: top20 .5s;
            animation: top20 .5s;
        }

        .inner {
            margin: auto;
            padding: 40px 20px;
        }

        .comment-reply-title {
            font-size: 18px;
            margin-bottom: 15px;
        }

        .comment-form {
            border-radius: 4px;
            overflow: hidden;
            position: relative;
        }

        .comment-textarea {
            position: relative;
            width: 100%;
        }

        .comment-textarea label {
            font-weight: 400;
            display: inline-block;
            max-width: 100%;
            margin-bottom: 5px;
        }

        .comment-textarea label span {
            color: #f05050;
            -webkit-box-sizing: border-box;
            box-sizing: border-box;
        }

        .comment-textarea .comment-preview {
            background: url(https://cdn.jsdelivr.net/gh/cetr/halo-comment-yu@master/cdn/img/background/comment_bg.webp) 96% 100% no-repeat;
            background-color: #fff;
            border: 1px solid #efefef;
            border-radius: 2px;
            background-size: 180px;
            font-size: 14px;
            line-height: 18px;
            width: 100%;
            min-height: 120px;
            color: #000;
            overflow: hidden;
            overflow-wrap: break-word;
            -webkit-transition: all .25s ease-in-out 0s;
            transition: all .25s ease-in-out 0s;
            resize: none;
            font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Helvetica, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol;
            padding-left: 10px;
            padding-top: 10px;
        }

        .comment-buttons {
            font-size: 12px;
            text-align: right;
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            display: -webkit-flex;
            -webkit-box-pack: justify;
            -ms-flex-pack: justify;
            justify-content: space-between;
        }

        .middle {
            display: inline-block;
            vertical-align: middle;
        }

        .author-info {
            position: relative;
            overflow: hidden;
            margin: 0 -10px 15px;
        }

        .commentator {
            position: relative;
            float: left;
            padding: 0 10px;
            width: 30.333333%;
        }

        .commentator label {
            font-weight: 400;
            display: inline-block;
            max-width: 100%;
            margin: 15px 0 5px;
        }

        .commentator label span {
            color: #f05050;
            -webkit-box-sizing: border-box;
            box-sizing: border-box;
        }

        /*span.input-avatar {
            display: block;
            position: absolute;
            left: 20px;
            bottom: 0;
        }*/

        /*span.input-avatar img.avatar-img {
            width: 22px;
            height: 22px;
            border-radius: 100%;
            cursor: pointer;
            -webkit-transition: all .8s;
            transition: all .8s;
        }*/

        span,
        label {
            border: 0;
            font-size: 100%;
            font-style: inherit;
            font-weight: inherit;
            margin: 0;
            outline: 0;
            padding: 0;
            vertical-align: baseline;
        }

        /*#author {
            padding-left: 40px;
        }*/

        .commentator input {
            font-size: 12px;
            width: 90%;
            padding: 6px 12px;
            border: 1px solid #ccc;
            border-color: rgba(135, 150, 165, .15);
            color: inherit;
            border-radius: 2px;
        }

        .comment-editor button,
        .comment-editort input,
        .comment-editor textarea {
            -webkit-appearance: none;
            outline: none;
            -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
        }

        .comment-buttons.SubmitBtn {
            margin-bottom: 15px;
            float: right;
        }

        .comment-buttons {
            font-size: 12px;
            text-align: right;
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            display: -webkit-flex;
            -webkit-box-pack: justify;
            -ms-flex-pack: justify;
            justify-content: space-between;
        }

        .button-submit {
            color: #fff;
            border: none;
            background: #448bff linear-gradient(45deg, #448bff, #44e9ff);
            padding-top: 6px;
            padding-bottom: 6px;
            -webkit-transition: all .2s ease;
            transition: all .2s ease;
            border-radius: 50px;
            padding-left: 30px;
            padding-right: 30px;
            cursor: pointer;
        }
    </style>
</head>

<body class="">
<#include "header.ftl">
<div class="blog-section" id="blog-section1" style="background-image:url(${blog.blogCoverImage})!important;">
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
            <div class="warp-box">
                <!-- 页面解析markdown为HTML显示 -->
                <div id="test-editormd">
                    <textarea style="display: none" name="test-editormd-markdown-doc">${blog.blogContent}</textarea>
                </div>
                <section class="comment-editor">
                    <div class="inner">
                        <h4 class="comment-reply-title">发表评论</h4>
                        <form action="" class="comment-form">
                            <div class="comment-textarea">
                                <label>
                                    评论
                                    <span>
                                    *
                                </span>
                                </label>
                                <textarea id="commentBody" required="required" aria-required="true" tabindex="4"
                                          placeholder="说点啥吧" class="comment-preview"
                                          style="display: block; margin: 0px;"></textarea>
                            </div>
                            <ul class="comment-buttons">
                                <li class="middle" style="margin-right: 5px;">
                                    <div href=""></div>
                                </li>
                                <li class="middle"></li>
                            </ul>
                            <div class="author-info">
                                <div class="commentator">
                                    <label for="author">
                                        名称
                                        <span>
                                        *
                                    </span>
                                    </label>
                                    <#--<span class="input-avatar">
                                    <img src="src=//sdn.geekzu.org/avatar/?d=" class="avatar-img">
                                </span>-->
                                    <input type="text" id="author" tabindex="1" required="required" aria-required="true"
                                           placeholder="填写昵称" class="comment-input author ">
                                </div>
                                <div class="commentator commentator-email">
                                    <label for="email"> 邮箱
                                        <span>*</span>
                                    </label>
                                    <input type="text" id="email" tabindex="2" required="required" aria-required="true"
                                           placeholder="用于获取头像和接收回复通知" class="comment-input email">
                                </div>
                                <div class="commentator commentator-authorUrl">
                                    <label for="authorUrl">地址
                                    </label>
                                    <input type="text" id="websiteUrl" tabindex="3" placeholder="网站或博客地址"
                                           class="comment-input link">
                                </div>
                            </div>
                            <div id="comment-sub" class="comment-buttons SubmitBtn">
                                <button href="javascript:void(0)" tabindex="5" rel="nofollow noopener" type="button"
                                        class="button-submit">发表评论
                                </button>
                            </div>
                        </form>
                    </div>
                </section>
                <aside class="blog-rights">
                    <p>本站文章除注明转载/出处外，皆为作者原创，欢迎转载，但未经作者同意必须保留此段声明，且在文章页面明显位置给出原文链接，否则保留追究法律责任的权利。</p>
                </aside>
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
    }).use(['index', 'set'], function () {
        var $ = layui.$
            , admin = layui.admin
            , element = layui.element
            , layer = layui.layer
            , upload = layui.upload;
        $("#comment-sub").click(function () {
            let commentator = $("#author").val();
            let email = $("#email").val();
            let commentBody = $("#commentBody").val();
            let websiteUrl = $("#websiteUrl").val();
            let data = {
                "commentator": commentator,
                "email": email,
                "commentBody": commentBody,
                "websiteUrl": websiteUrl,
                "blogId": ${blog.blogId}
            }
            $.ajax({
                //几个参数需要注意一下
                type: "POST",//方法类型
                //dataType: "json",//预期服务器返回的数据类型 如果是对象返回的是json 如果是字符串这里一定要定义text 之前我就是定义json 结果字符串的返回一直到额error中去
                url: "../admin/comments/save",//url
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json;charset=utf-8",//这个是form表单中的id   jQuery的serialize()方法通过序列化表单值
                success: function (res) {
                    if (res.code === 0) {
                        layer.msg(res.msg, {time: 1000}, function () {
                            //window.location = '/admin/article/toArticleList';
                            location.reload();
                        });
                    } else {
                        //请求出错处理
                        layer.msg('提交失败', {icon: 5});
                    }
                }
            })
        })
    })
</script>
<script>
    $(function () {
        var testEditor;
        //页面加载完成调用此方法
        // 页面解析markdown为html进行显示
        $(document).ready(function () {
            testEditor = editormd.markdownToHTML("test-editormd", {
                htmlDecode: "style, script, iframe",
                //markdownSourceCode : true,
                tocm: true, //对目录解析
                emoji: true,
                taskList: true,
                tex: true,  // 默认不解析
                flowChart: true,  // 默认不解析
                sequenceDiagram: true,  // 默认不解析
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
<#--看版娘升级版-->
<#--<script src="https://cdn.jsdelivr.net/gh/stevenjoezhang/live2d-widget@latest/autoload.js"></script>-->
<script>
    console.clear();
    console.log("%c 一天很短,开心了就笑,不开心了就一会在笑", "background:#24272A; color:#ffffff", "");
</script>
</body>
</html>