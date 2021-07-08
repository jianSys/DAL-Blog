<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>博客|后台管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="../static/admin/plugins/layui/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="../static/admin/plugins/layui/style/admin.css" media="all">

</head>
<body class="layui-layout-body">

<div id="LAY_app">
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header">
            <!-- 头部区域 -->
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item layadmin-flexible" lay-unselect>
                    <a href="javascript:;" layadmin-event="flexible" title="侧边伸缩">
                        <i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
                    </a>
                </li>
                <#--<li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="http://www.layui.com/admin/" target="_blank" title="前台">
                        <i class="layui-icon layui-icon-website"></i>
                    </a>
                </li>-->
                <li class="layui-nav-item" lay-unselect>
                    <a href="javascript:;" layadmin-event="refresh" title="刷新">
                        <i class="layui-icon layui-icon-refresh-3"></i>
                    </a>
                </li>
               <#-- <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <input type="text" placeholder="搜索..." autocomplete="off" class="layui-input layui-input-search" layadmin-event="serach" lay-action="template/search.html?keywords=">
                </li>-->
            </ul>
            <ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right">

                <#--<li class="layui-nav-item" lay-unselect>
                    <a lay-href="app/message/index.html" layadmin-event="message" lay-text="消息中心">
                        <i class="layui-icon layui-icon-notice"></i>

                        <!-- 如果有新消息，则显示小圆点 &ndash;&gt;
                        <span class="layui-badge-dot"></span>
                    </a>
                </li>-->
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="javascript:;" layadmin-event="theme">
                        <i class="layui-icon layui-icon-theme"></i>
                    </a>
                </li>
                <#--<li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="javascript:;" layadmin-event="note">
                        <i class="layui-icon layui-icon-note"></i>
                    </a>
                </li>-->
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="javascript:;" layadmin-event="fullscreen">
                        <i class="layui-icon layui-icon-screen-full"></i>
                    </a>
                </li>
                <li class="layui-nav-item" lay-unselect>
                    <a href="javascript:;">
                        <cite id="login_name">${loginUser}</cite>
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a lay-href="toUserInfo">基本资料</a></dd>
                        <dd><a lay-href="toPassword">修改密码</a></dd>
                        <hr>
                        <dd id="logout" <#--layadmin-event="logout"--> style="text-align: center;"><a>退出</a></dd>
                    </dl>
                </li>

                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="javascript:;" layadmin-event="about"><i class="layui-icon layui-icon-more-vertical"></i></a>
                </li>
                <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-unselect>
                    <a href="javascript:;" layadmin-event="more"><i class="layui-icon layui-icon-more-vertical"></i></a>
                </li>
            </ul>
        </div>

        <!-- 侧边菜单 -->
        <div class="layui-side layui-side-menu">
            <div class="layui-side-scroll">
                <div class="layui-logo" lay-href="home/console.html">
                    <span>博客管理系统</span>
                </div>

                <ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu" lay-filter="layadmin-system-side-menu">
                    <li data-name="home" class="layui-nav-item">
                        <a href="javascript:;" lay-tips="导航栏" lay-direction="2" lay-href="home/console.html">
                            <i class="layui-icon layui-icon-home"></i>
                            <cite>仪表盘</cite>
                        </a>
                    </li>
                    <li data-name="component" class="layui-nav-item">
                        <a href="javascript:;" lay-tips="文章" lay-direction="2">
                            <i class="layui-icon layui-icon-component"></i>
                            <cite>文章</cite>
                        </a>
                        <dl class="layui-nav-child">
                            <dd data-name="button">
                                <a lay-href="article/toArticleList">所有文章</a>
                            </dd>
                            <dd data-name="nav">
                                <a lay-href="article/toArticleEdit">写文章</a>
                            </dd>
                            <dd data-name="tabs">
                                <a lay-href="category/toCategoryList">分类目录</a>
                            </dd>
                            <dd data-name="progress">
                                <a lay-href="tags/toTagsList">标签</a>
                            </dd>
                        </dl>
                    </li>
                    <li data-name="template" class="layui-nav-item">
                        <a href="javascript:;" lay-tips="页面" lay-direction="2">
                            <i class="layui-icon layui-icon-template"></i>
                            <cite>页面</cite>
                        </a>
                        <dl class="layui-nav-child">
                            <dd><a lay-href="page/toPageList">所有页面</a></dd>
                            <dd><a lay-href="page/toPageAdd">新建页面</a></dd>
                        </dl>
                    </li>
                    <li data-name="app" class="layui-nav-item">
                        <a href="javascript:;" lay-href="file/toFileList" lay-tips="应用" lay-direction="2">
                            <i class="layui-icon layui-icon-app"></i>
                            <cite>附件</cite>
                        </a>
                    </li>
                    <li data-name="senior" class="layui-nav-item">
                        <a href="javascript:;" lay-tips="高级" lay-direction="2" lay-href="comments/toCommentsList">
                            <i class="layui-icon layui-icon-senior"></i>
                            <cite>评论</cite>
                        </a>
                    </li>
                    <li data-name="user" class="layui-nav-item">
                        <a href="javascript:;" lay-tips="用户" lay-direction="2">
                            <i class="layui-icon layui-icon-user"></i>
                            <cite>外观</cite>
                        </a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a lay-href="theme/toThemeList">主题</a>
                            </dd>
                          <#--  <dd>
                                <a lay-href="user/administrators/list.html">菜单</a>
                            </dd>
                            <dd>
                                <a lay-href="user/administrators/role.html">主题编辑</a>
                            </dd>-->
                        </dl>
                    </li>
                    <li data-name="set" class="layui-nav-item">
                        <a href="javascript:;" lay-tips="设置" lay-direction="2">
                            <i class="layui-icon layui-icon-set"></i>
                            <cite>系统</cite>
                        </a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a lay-href="toWebSite">博客设置</a>
                            </dd>
                            <dd>
                                <a lay-href="toTool">小工具</a>
                            </dd>
                           <#-- <dd>
                                <a lay-href="user/administrators/role.html">关于</a>
                            </dd>-->
                        </dl>
                    </li>
                   <#-- <li data-name="get" class="layui-nav-item">
                        <a href="javascript:;" lay-href="http://www.layui.com/admin/#get" lay-tips="授权" lay-direction="2">
                            <i class="layui-icon layui-icon-auz"></i>
                            <cite>授权</cite>
                        </a>
                    </li>-->
                </ul>
            </div>
        </div>

        <!-- 页面标签 -->
        <div class="layadmin-pagetabs" id="LAY_app_tabs">
            <div class="layui-icon layadmin-tabs-control layui-icon-prev" layadmin-event="leftPage"></div>
            <div class="layui-icon layadmin-tabs-control layui-icon-next" layadmin-event="rightPage"></div>
            <div class="layui-icon layadmin-tabs-control layui-icon-down">
                <ul class="layui-nav layadmin-tabs-select" lay-filter="layadmin-pagetabs-nav">
                    <li class="layui-nav-item" lay-unselect>
                        <a href="javascript:;"></a>
                        <dl class="layui-nav-child layui-anim-fadein">
                            <dd layadmin-event="closeThisTabs"><a href="javascript:;">关闭当前标签页</a></dd>
                            <dd layadmin-event="closeOtherTabs"><a href="javascript:;">关闭其它标签页</a></dd>
                            <dd layadmin-event="closeAllTabs"><a href="javascript:;">关闭全部标签页</a></dd>
                        </dl>
                    </li>
                </ul>
            </div>
            <div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
                <ul class="layui-tab-title" id="LAY_app_tabsheader">
                    <li lay-id="home/console.html" lay-attr="home/console.html" class="layui-this"><i class="layui-icon layui-icon-home"></i></li>
                </ul>
            </div>
        </div>


        <!-- 主体内容 -->
        <div class="layui-body" id="LAY_app_body">
            <div class="layadmin-tabsbody-item layui-show">
                <iframe src="home" frameborder="0" class="layadmin-iframe"></iframe>
            </div>
        </div>

        <!-- 辅助元素，一般用于移动设备下遮罩 -->
        <div class="layadmin-body-shade" layadmin-event="shade"></div>
    </div>
</div>

<script src="../../../static/admin/plugins/layui/layui/layui.js"></script>
<script>
    //console.log("%c Gitee %c", "background:#24272A; color:#ffffff", "", "https://github.com/xzhuz");
</script>
<script>
    layui.config({
        base: '../../../static/admin/plugins/layui/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index'],function () {
        var $ = layui.$
            , form = layui.form
            , layer = layui.layer
            , upload = layui.upload
        ,admin = layui.admin;
        $("#logout").on("click",function () {
            $.ajax({
                type: 'get',//方法类型
                url: "./logout",
                //data: JSON.stringify(data),
                //dataType: "json",
                contentType: "application/json;charset=utf-8",
                success: function (res) {
                    if (res.code === 0) {
                        layer.msg('退出登陆成功', {time: 1000},function () {
                            //window.location.href= '/admin/login';
                            parent.location.reload();//刷新整个页面
                        })
                    } else {
                    }
                },
                error: function () {
                    //请求出错处理
                    layer.msg('发送失败', {icon: 5});
                }
            })
        })
    });
</script>
<script>
    console.clear();
    console.log("%c 一天很短,开心了就笑,不开心了就一会在笑", "background:#24272A; color:#ffffff", "");
</script>
</body>
</html>


