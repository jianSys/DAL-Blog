<!DOCTYPE html>
<html lang="zh-Hans-CN">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width" />
    <title>DAL个人博客</title>
    <meta name="keywords" content="个人博客，个人网站，个人博客模板，好看的个人博客">
    <meta name="description" content="个人博客搭建，个人博客免费建站，个人博客网站html源码，个人博客网站制作模板，源码下载，织梦，前端，java，PHP模板源码分享">
    <link href="../../static/blog/css/base.css" rel="stylesheet" type="text/css">
    <link href="../../static/blog/layui/css/layui.css" rel="stylesheet" type="text/css">
    <link href="../../static/blog/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="../../static/blog/css/index_style.css" rel="stylesheet" type="text/css">
    <link href="../../static/blog/css/animate.min.css" rel="stylesheet" type="text/css">
</head>

<body class="night">
<div id="menu" class="hover_animation menu_open" data-mark="false">
    <span></span>
    <span></span>
    <span></span>
</div>
<div id="navgation" class="navgation navgation_close">
    <ul class="point">
        <li><a href="#">首页</a></li>
        <li><a href="article.html">博客</a></li>
        <li><a href="message.html">留言</a></li>
    </ul>
    <div class="logo"><a>jian</a></div>
</div>
<div class="section" id="section1">
    <div class="fp-tablecell">
        <div class="page1">
            <div class="nav wow zoomIn" data-wow-duration="2s">
                <h1>DAL-Blog</h1>
                <p>一天很短，开心了就笑，不开心了就过会儿再笑。</p>
                <a class="layui-btn layui-btn-normal" style="margin-top: 20px" href="article.html">Enter Blog</a>
            </div>
            <a class="next wow fadeInUp" data-wow-duration="2s" id="next"></a>
        </div>
    </div>
</div>
<div class="section" id="section2">
    <div class="fp-tablecell container1">
        <div class="page2">
            <div class="warp-box article">
                <div class="new-article">
                    <div class="inner wow fadeInDown" data-wow-delay=".2s">
                        <h3><i class="layui-icon layui-icon-fire" style="font-size: 16px; color: #f1404b"></i> 最热文章</h3>
                    </div>
                </div>
                <div class="layui-row">
                    <#list hotBlog as hot>
                    <div class="layui-col-xs6 layui-col-sm6 layui-col-md3  wow fadeInUp" style="padding: 5px">
                        <div class="single-news">
                            <div class="news-head">
                                <img src="${hot.blogCoverImage}">
                                <a href="#" class="link"><i class="fa fa-link"></i></a>
                            </div>
                            <!-- <div class="news-content">
                                <h4>
                                    <a href="#">
                                        Java专栏
                                    </a>
                                </h4>
                                <div class="date">
                                    2020年6月16日
                                </div>
                                <p>
                                    本专栏主要分享Java的各种常见问题，包括java学习路线，java基础，框架，微服务，项目，面试题，希望通过这些知识的分享能够提升自己的java水平
                                </p>
                                <a href="#" class="btn">
                                    阅读更多
                                </a>
                            </div> -->
                        </div>
                    </div>
                    </#list>
                    <#--<div class="layui-col-xs6 layui-col-sm6 layui-col-md3  wow fadeInUp" data-wow-delay=".2s" style="padding: 5px">
                        <div class="single-news">
                            <div class="news-head">
                                <img src="../../static/blog/image/md1.jpeg">
                                <a href="#" class="link"><i class="fa fa-link"></i></a>
                            </div>
                            <!-- <div class="news-content">
                                <h4>
                                    <a href="#">
                                       web前端专栏
                                    </a>
                                </h4>
                                <div class="date">
                                    2020年6月16日
                                </div>
                                <p>
                                    本栏目主要分享关于web前端的学习路线，基础知识，常用框架，面试题，项目等等
                                </p>
                                <a href="#" class="btn">
                                    阅读更多
                                </a>
                            </div> &ndash;&gt;
                        </div>
                    </div>
                    <div class="layui-col-xs6 layui-col-sm6 layui-col-md3  wow fadeInUp" data-wow-delay=".4s" style="padding: 5px">
                        <div class="single-news">
                            <div class="news-head">
                                <img src="../../static/blog/image/md.jpeg">
                                <a href="#" class="link"><i class="fa fa-link"></i></a>
                            </div>
                            <!-- <div class="news-content">
                                <h4>
                                    <a href="#">
                                        DeDeCMS专栏
                                    </a>
                                </h4>
                                <div class="date">
                                    2020年6月16日
                                </div>
                                <p>
                                    本栏目主要介绍DeDeCMS网站内容管理系统，使用dede常见的问题，常用标签，插件，提供建站帮助
                                </p>
                                <a href="#" class="btn">
                                    阅读更多
                                </a>
                            </div> &ndash;&gt;
                        </div>
                    </div>
                    <div class="layui-col-xs6 layui-col-sm6 layui-col-md3  wow fadeInUp" data-wow-delay=".4s" style="padding: 5px">
                        <div class="single-news">
                            <div class="news-head">
                                <img src="../../static/blog/image/md1.jpeg">
                                <a href="#" class="link"><i class="fa fa-link"></i></a>
                            </div>
                            <!-- <div class="news-content">
                                <h4>
                                    <a href="#">
                                        DeDeCMS专栏
                                    </a>
                                </h4>
                                <div class="date">
                                    2020年6月16日
                                </div>
                                <p>
                                    本栏目主要介绍DeDeCMS网站内容管理系统，使用dede常见的问题，常用标签，插件，提供建站帮助
                                </p>
                                <a href="#" class="btn">
                                    阅读更多
                                </a>
                            </div> &ndash;&gt;
                        </div>
                    </div>
               </div>-->
                <div class="new-article">
                    <div class="inner wow fadeInDown" data-wow-delay=".2s">
                        <h3><i class="fa fa-list" aria-hidden="true" style="font-size: 16px; color: #f1404b"></i> 所有文章</h3>
                    </div>
                </div>
                <#list allBlog as bg>
                <div class="layui-row">
                    <div class="blog-article">
                        <div class="blog-article-img">
                            <img src="${bg.blogCoverImage!'../../static/blog/image/md.jpeg'}" alt="">
                        </div>
                        <div class="content">
                            <input id="id" value="${bg.blogId}" type="hidden" name="id">
                            <div class="mate" id="mate">
                                <span class="categories">#${bg.blogCategoryName!'分类不存在'}</span>
                            </div>
                            <h2>${bg.blogTitle!'这是一个标题'}</h2>
                            <p>
                                ${bg.blogIntroduce!'这是一个文章简介'}
                            </p>
                            <a href="./toBlog/${bg.blogId}" ><span>查看全文>>></span></a>
                        </div>
                    </div>
                </div>
                </#list>
            </div>
        </div>
    </div>
</div>
</div>
<!-- <div class="section " id="section3 ">
    <div class="fp-tablecell ">
        <div class="page3 ">
            <div class="warp-box ">
                <div class="warp ">
                    <div class="inner ">
                        <div class="links ">
                            <ul>
                                <li class="wow fadeInLeft "><a href="# ">关于</a></li>
                                <li class="wow fadeInRight "><a href="# ">+友情链接</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="section " id="section4 ">
    <div class="fp-tablecell ">
        <div class="page4 ">
            <div class="warp-box ">
                <div class="about ">
                    <div class="inner ">
                        <h1 class="wow fadeInLeft ">ZQ个人博客</h1>
                        <p class="wow fadeInRight ">
                            一天很短，开心了就笑，不开心了就过会儿再笑。
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div> -->
<footer class="footer wow fadeInUp " id="contact ">
    <!-- <div class="footer-top ">
        <div class="container ">
            <div class="layui-row ">
                <div class="layui-col-xs12 layui-col-sm6 layui-col-md4 ">
                    <div class="single-widget about ">
                        <div class="footer-logo ">
                            <h2>ZQ个人博客</h2>
                        </div>
                        <p>世界那么大，我想去看看</p>
                        <div class="button ">
                            <a href="# " class="btn layui-btn layui-btn-normal ">About Me</a>
                        </div>
                    </div>
                </div>
                <div class="layui-col-xs12 layui-col-sm6 layui-col-md4 ">
                    <div class="single-widget ">
                        <h2>相关链接</h2>
                        <ul class="social-icon ">
                            <li class="active "><a href="# "><i class="fa fa-book "></i>博文</a></li>
                            <li class="active "><a href="# "><i class="fa fa-comments "></i>留言</a></li>
                            <li class="active "><a href="# "><i class="fa fa-share "></i>资源</a></li>
                            <li class="active "><a href="# "><i class="fa fa-snowflake-o "></i>日记</a></li>
                            <li class="active "><a href="# "><i class="fa fa-files-o "></i>归档</a></li>
                        </ul>
                    </div>
                </div>
                <div class="layui-col-xs12 layui-col-sm12 layui-col-md4 ">
                    <div class="single-widget contact ">
                        <h2>联系我</h2>
                        <ul class="list ">
                            <li><i class="fa fa-map "></i>地址:深圳市南山区</li>
                            <li><i class="fa fa-qq "></i>QQ: 1435469178 </li>
                            <li><i class="fa fa-envelope "></i>邮箱: 1435469178@qq.com</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div> -->
    <div class="copyright ">
        <div class="container ">
            <div class="layui-row ">
                <div class="layui-col-xs12 layui-col-sm12 layui-col-md12 text-center ">
                    <p>Copyright &copy; ${copyRight} All Rights Reserved V.5.1.3 粤ICP备1231100号</p>
                </div>
            </div>
        </div>
    </div>
</footer>
<script src="../../static/blog/layui/layui.js "></script>
<script src="../../static/blog/js/wow.min.js "></script>
<script src="../../static/blog/js/index.js"></script>
</body>

</html>