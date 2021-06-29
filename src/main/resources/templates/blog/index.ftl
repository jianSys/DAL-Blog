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
   <#include "./common.ftl">
</head>

<body >
<#include "./header.ftl">
<div class="section" id="section1">
    <div class="fp-tablecell">
        <div class="page1">
            <div class="nav wow zoomIn" data-wow-duration="2s">
                <h1>${config["websiteName"]}</h1>
                <p>${config['websiteDescription']}</p>
<#--                <a class="layui-btn layui-btn-normal" style="margin-top: 20px" href="article.html">Enter Blog</a>-->
            </div>
            <a class="next wow fadeInUp" data-wow-duration="2s" id="next"></a>
        </div>
    </div>
</div>
<div class="section" id="section2">
    <div class="fp-tablecell">
        <div class="page2">
            <div class="warp-box">
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
            </div>
                <div class="new-article">
                    <div class="inner wow fadeInDown" data-wow-delay=".2s">
                        <h3><i class="fa fa-list" aria-hidden="true" style="font-size: 16px; color: #f1404b"></i> 所有文章</h3>
                    </div>
                </div>
                <#list allBlog as bg>
                    <div class="layui-row">
                        <div class="blog-article">
                            <div class="blog-article-img">
                                <div class="post-date">
                                    ${bg.day!''}
                                    <span><#--${bg.createTime?date('yyyy-MM-dd')}-->${bg.time!''}</span>
                                </div>
                                <div class="post-preview">
                                    <a href="javaScript:;"><img src="${bg.blogCoverImage!'../../static/blog/image/md.jpeg'}" alt=""></a>
                                </div>

                            </div>
                            <div class="content">
                                <input id="id" value="${bg.blogId}" type="hidden" name="id">
                                <div class="mate" id="mate">
                                    <span class="categories">#${bg.blogCategoryName!'分类不存在'}</span>
                                </div>
                                <h2>
                                    <a href="./toBlog/${bg.blogId}" rel="bookmark">
                                        <i class="iconfont icon-zhiding zhiding"></i> ${bg.blogTitle!'这是一个标题'}
                                    </a>
                                </h2>
                                <p>
                                    ${bg.blogIntroduce!'这是一个文章简介'}
                                </p>
                                <a href="./toBlog/${bg.blogId}" class="blog-link"><span class="text">查看全文</span></a>
                            </div>
                        </div>
                    </div>
                </#list>
        </div>
    </div>
</div>
</div>
<#include "./footer.ftl">
</body>
<script>
    console.clear();
    console.log("%c 一天很短,开心了就笑,不开心了就一会在笑", "background:#24272A; color:#ffffff", "");
</script>
</html>