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
   <#include "common.ftl">
    <style>
        .justify-center {
            justify-content: center;
        }
        .flex {
            display: flex;
            flex-direction: row;
        }

        .is-invisible {
            display: none;
        }

        .pagination-circle {
            display: inline-block;
            margin: 0 2px;
            width: 2.4rem;
            height: 2.4rem;
            border-radius: 50%;
            line-height: 2.4rem;
            color: #000;
            text-align: center;
        }

        .iconfont {
            font-family: iconfont;
            font-size: 16px;
            font-style: normal;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
        }

        .pagination-circle.is-current,
        .pagination-circle:hover {
            background-color: #f1404b;
            background-image: none;
            color: #fff;
        }

        .pagination-list .disabled {
            display: none;
        }

        /*.page2,
        .page3,
        .page4,
        .page5 {
            width: 70%;
            position: relative;
            margin: 0 auto;
            padding-top: .1px;
            font-family: "Microsoft YaHei";
        }*/

        /*.icon-right:before {
            content: "\E638";
        }*/
    </style>
</head>

<body >
<#include "header.ftl">
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
                                    <a href="../toBlog/${bg.blogId}" rel="bookmark">
                                        <i class="iconfont icon-zhiding zhiding"></i> ${bg.blogTitle!'这是一个标题'}
                                    </a>
                                </h2>
                                <p>
                                    ${bg.blogIntroduce!'这是一个文章简介'}
                                </p>
                                <a href="../toBlog/${bg.blogId}" class="blog-link"><span class="text">查看全文</span></a>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>

            <div class="pagination flex  justify-center" style="padding-top: 20px" role="navigation" aria-label="pagination">
                <ul class="pagination-list flex">
                    <li class="previous is-invisible">
                        <a class="pagination-circle" tabindex="0" role="button" aria-disabled="true" aria-label="Previous page"><i class="fa fa-angle-left fa-lg"></i></a>
                    </li>
                    <li class="selected"><a role="button" class="pagination-circle is-current" tabindex="0" aria-label="Page 1 is your current page" aria-current="page">1</a></li>
                    <li class="next is-invisible">
                        <a class="pagination-circle" tabindex="0" role="button" aria-disabled="false" aria-label="Next page"><span class="iconfont icon-right"> <i class="fa fa-angle-right fa-lg"></i></span>
                        </a>
                    </li>
                </ul>
            </div>
    </div>
</div>
</div>
<#include "footer.ftl">
</body>
<script>
    console.clear();
    console.log("%c 一天很短,开心了就笑,不开心了就一会在笑", "background:#24272A; color:#ffffff", "");
</script>
</html>