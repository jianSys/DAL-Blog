<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link href="../../static/blog/css/base.css" rel="stylesheet" type="text/css">
    <link href="../../static/layui/layui/css/layui.css" rel="stylesheet" type="text/css">
    <link href="../../static/blog/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="../../static/blog/css/animate.min.css" rel="stylesheet" type="text/css">
    <!-- 页面解析markdown为HTML显示需要的css -->
    <link rel="stylesheet" href="../../static/editormd/css/editormd.preview.min.css" />
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        ul li {
            list-style: none;
        }

        a,
        img {
            text-decoration: none;
            border: none;
        }

        .night {
            background-color: #292a2d;
            color: #a9a9b3!important;
        }

        .container {
            margin: auto;
            width: 70%;
        }

        .section {
            overflow: hidden;
            display: table;
            table-layout: fixed;
            width: 100%;
            position: relative;
            padding: 30px 0 0 0;
        }

        .post-header {
            width: 100px;
            height: 100px;
            background-color: pink;
        }

        .fp-tablecell {
            display: table-cell;
            vertical-align: middle;
            width: 100%;
            height: 100%;
            position: relative;
            z-index: 1
        }

        #section1 {
            height: 400px;
            padding: 0;
            background-color: #151515;
            background-image: url(../../static/blog/image/bgc.jpg);
            background-position: center;
            background-attachment: local;
            background-repeat: no-repeat
        }

        #section1,
        #section2 {
            background-size: cover
        }
        /*第一屏 开始*/

        .page1 .nav {
            text-align: center;
            color: #FFF
        }

        .page1 p {
            letter-spacing: 5px;
            margin: 20px 0
        }
        .page1 span{
            font-size: 19px;
            display: block;
            margin: 20px;
        }
        .page1 .blog-views{
            display: inline-block;
        }
        /*第二屏 开始*/

        .page2{
            width: 90%;
            position: relative;
            margin: 0 auto;
            padding-top: .1px;
            font-family: "Microsoft YaHei"
        }

        .warp-box {
            width: 100%;
            height: 100%;
            position: relative
        }

        .article {
            margin: 0 auto;
            width: 60%;
        }

        .warp {
            display: table;
            margin: 0 auto;
            height: 100%;
            position: relative
        }

        .page2 .single-news {
            /* margin-top: 50px; */
            background: #faf9f9;
            border-radius: 7px
        }

        .page2 .single-news:hover .news-head img {
            -webkit-transform: scale(1.2);
            -moz-transform: scale(1.2);
            transform: scale(1.2)
        }

        .page2 .single-news:hover .news-head:before {
            opacity: 1;
            visibility: visible;
            -webkit-transform: translateY(0);
            -moz-transform: translateY(0);
            transform: translateY(0)
        }

        .page2 .single-news:hover .link {
            top: 50%;
            opacity: 1;
            visibility: visible
        }

        .page2 .news-head {
            position: relative;
            overflow: hidden;
        }

        .page2 .news-head::before {
            position: absolute;
            width: 100%;
            height: 100%;
            left: 0;
            top: 0;
            background: rgba(255, 255, 255, .51);
            content: "";
            opacity: 0;
            visibility: hidden;
            -webkit-transition: all .4s ease;
            -moz-transition: all .4s ease;
            transition: all .4s ease;
            -webkit-transform: translateY(-100%);
            -moz-transform: translateY(-100%);
            transform: translateY(-100%);
            z-index: 8
        }

        .page2 .news-head img {
            width: 100%;
            height: 150px;
            object-fit: cover;
            border-radius: 7px;
            -webkit-transition: all .4s ease;
            -moz-transition: all .4s ease;
            transition: all .4s ease
        }

        .page2 .link {
            position: absolute;
            left: 0;
            top: 0;
            width: 40px;
            height: 40px;
            background: #333;
            color: #fff;
            opacity: 0;
            visibility: hidden;
            text-align: center;
            left: 50%;
            margin: -20px 0 0 -20px;
            line-height: 40px;
            z-index: 9;
            -webkit-transition: all .4s ease;
            -moz-transition: all .4s ease;
            transition: all .4s ease
        }

        .page2 .news-content {
            padding: 30px 15px
        }

        .page2 .news-content h4 {
            margin-bottom: 10px
        }

        .page2 .news-content .date {
            color: #bbb;
            font-size: 12px;
            margin-bottom: 15px
        }

        .page2 .news-content p {
            color: #999;
            height: 55px;
            overflow: hidden
        }

        .page2 .news-content .btn {
            display: inline-block;
            margin-top: 15px;
            padding: 0;
            background: 0 0;
            border: none;
            color: #29B6F6;
            -webkit-transition: all .4s ease;
            -moz-transition: all .4s ease;
            transition: all .4s ease
        }

        .page2 .news-content .btn:hover {
            color: #333
        }

        .page2 .new-article {
            text-align: left;
            padding: 20px 0 20px 10px;
        }

        .page2 .inner {
            display: inline-block;
            vertical-align: middle
        }

        .page2 .new-article h1 {
            font-size: 32px;
            /* padding-bottom: 30px; */
            position: relative;
            font-weight: 500
        }

        .page2 .new-article p {
            margin-top: 20px;
            line-height: 22px;
            color: #888
        }

        .blog-article {
            border-top: 1px solid #e6e6e670;
            width: 100%;
            padding-top: 25px;
            padding-bottom: 15px;
            margin-top: 0;
            display: inline-block;
        }

        .blog-article .content {
            padding-top: 0;
            float: revert;
            width: 100%;
            transition: all .35s ease-in-out;
        }

        .content .mate {
            height: 14px;
            display: flex;
            flex-wrap: wrap;
            align-items: center;
            margin-bottom: 5px;
        }

        .content .categories {
            color: #718096;
            font-size: 12px;
            margin-right: 5px;
        }

        .content h2 {
            margin-bottom: 8px;
            font-weight: 700;
            position: relative;
            padding-bottom: 15px;
            line-height: initial;
            margin-top: 0;
        }

        .content p {
            overflow: hidden;
            text-overflow: ellipsis;
            display: -webkit-box;
            -webkit-box-orient: vertical;
            -webkit-line-clamp: 2;
            margin: 0;
        }

        .content a {
            display: none;
            position: relative;
            bottom: -25px;
            color: #be0b0b
        }

        .content h2::after {
            content: "";
            width: 120px;
            height: 1px;
            bottom: 2.5px;
            left: 150px;
            position: absolute;
            background: linear-gradient(to right, #e0e0e0 0, #e0e0e0 35%, #e0e0e0 65%, #fff 100%);
            background: -moz-linear-gradient(left, #e0e0e0 0, #e0e0e0 35%, #e0e0e0 65%, #fff 100%);
            background: -webkit-gradient(linear, left top, right top, color-stop(0, #e0e0e0), color-stop(35%, #e0e0e0), color-stop(65%, #e0e0e0), color-stop(100%, #fff));
        }

        .blog-article-img {
            position: relative;
            margin-right: 20px;
            flex-shrink: 0;
            height: 130px;
            width: 130px;
            overflow: hidden;
            float: left;
            border: 1px solid #eae8e842;
            background-color: #eae8e842;
        }

        .blog-article-img img {
            width: 100%;
            height: 100%;
            object-fit: cover
        }
        /*底部*/

        .footer .container {
            width: 90%;
            padding-right: 15px;
            padding-left: 15px;
            margin-right: auto;
            margin-left: auto
        }

        .footer .footer-top {
            background: #232328;
            padding: 60px 0 90px
        }

        .footer .single-widget {
            margin-top: 30px
        }

        .footer .footer-logo {
            margin-bottom: 20px
        }

        .footer .single-widget h2 {
            color: #fff;
            font-size: 18px;
            margin-bottom: 20px;
            text-transform: uppercase
        }

        .footer .about p {
            color: #eee
        }

        .footer .button .btn {
            margin-top: 20px
        }

        .footer .button .btn:hover {
            border-color: #fff
        }

        .footer .contact ul li {
            color: #eee;
            position: relative;
            padding-left: 40px;
            font-family: Roboto, sans-serif;
            margin-bottom: 10px
        }

        .footer .contact ul li:last-child {
            margin: 0
        }

        .footer .contact ul li i {
            position: absolute;
            top: 50%;
            width: 30px;
            height: 30px;
            text-align: center;
            line-height: 30px;
            left: 0;
            margin-top: -15px;
            font-size: 16px
        }

        .footer .contact ul li a {
            color: #fff
        }

        .footer .social-icon li {
            float: left;
            width: 50%;
            margin-bottom: 10px
        }

        .footer .social-icon li:last-child {
            margin: 0
        }

        .footer .social-icon li a {
            color: #eee
        }

        .footer .social-icon li a:hover {
            color: #82B440
        }

        .footer .social-icon li a i {
            margin-right: 10px;
            width: 20px;
            font-size: 16px
        }

        .footer .copyright {
            padding: 12px 0;
            background: #1D1D21
        }

        .footer .copyright .text-center {
            text-align: center
        }

        .footer .copyright p {
            color: #bbb;
            font-size: 14px
        }
        /*底部*/

        /*#test-editormd{
            height: 100%;
            overflow: hidden;
        }*/
        .blog-rights {
            padding-top: 5px;
            padding-right: 10px;
            padding-bottom: 10px;
            padding-left: 15px;
            font-family: 微软雅黑;
            font-size: 12px;
        }

        .blog-rights p {
            margin-top: 8px;
            text-align: center;
            font-size: 12px;
            color: #9199a1;
            line-height: 18px;
            display: inline-block;
        }
        .blog-header{
            margin: 0 auto;
            width: 100%;
            height: 100%;
            background-color: #2E2E2E;
            position: fixed!important;
            background-size: 100% 100%
        }
    </style>
</head>

<body class="night">
<div class="section" id="section1">
    <div class="fp-tablecell">
        <div class="page1">
            <div class="nav wow zoomIn" data-wow-duration="2s">
                <h1>${blog.blogTitle!''}</h1>
                <span>${blog.createTime!'时间未知'}
                    <div class="blog-views">${blog.blogViews!'0'}浏览</div>
                </span>
            </div>
        </div>
    </div>
</div>
<div class="section" id="section2">
    <div class="fp-tablecell">
        <div class="page2">
            <div class="warp-box article" >
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
<footer class="footer wow fadeInUp " id="contact ">
    <div class="copyright ">
        <div class="container ">
            <div class="layui-row ">
                <div class="layui-col-xs12 layui-col-sm12 layui-col-md12 text-center ">
                    <p>Copyright &copy; 2019-2020 ZQ个人博客 All Rights Reserved V.5.1.3 粤ICP备1231100号</p>
                </div>
            </div>
        </div>
    </div>
</footer>
<script src="../../static/blog/js/jquery.min.js"></script>
<script src="../../static/editormd/js/editormd.min.js"></script>
<!-- 页面markdown解析成HTML需要的js -->
<script src="../../static/editormd/lib/marked.min.js"></script>
<script src="../../static/editormd/lib/prettify.min.js"></script>
<script src="../../static/editormd/lib/raphael.min.js"></script>
<script src="../../static/editormd/lib/underscore.min.js"></script>
<script src="../../static/editormd/lib/sequence-diagram.min.js"></script>
<script src="../../static/editormd/lib/flowchart.min.js"></script>
<script src="../../static/editormd/lib/jquery.flowchart.min.js"></script>

<script>
   $(function () {
        var testEditor;
        //页面加载完成调用此方法
        // 页面解析markdown为html进行显示
        $(document).ready( function() {
            testEditor = editormd.markdownToHTML("test-editormd", {
                htmlDecode :"style, script, iframe",
                //markdownSourceCode : true,
                emoji           : true,
                taskList        : true,
                tex             : true,  // 默认不解析
                flowChart       : true,  // 默认不解析
                sequenceDiagram : true,  // 默认不解析
            });
        });
    });
</script>
</body>

</html>