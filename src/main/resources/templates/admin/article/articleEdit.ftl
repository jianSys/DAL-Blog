<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>layui 集成 editormd 富文本编辑器</title>
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
                    <div class="layui-inline">
                        <label class="layui-form-label">文章标题</label>
                        <div class="layui-input-inline">
                            <input type="hidden" id="blogId" name="blogId">
                            <input id="title" type="text" name="title"  placeholder="请输入文章标题"
                                   autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">文章标签</label>
                        <div class="layui-input-inline">
                            <input id="tag" type="text" name="author"  placeholder="请输入标签"
                                   autocomplete="off" class="layui-input">
                        </div>
                    </div>

<#--                    <div class="layui-inline">-->
<#--                        <label class="layui-form-label">文章分类</label>-->
<#--                        <div class="layui-input-inline">-->
<#--                            <select id="category" name="label" lay-verify="required" lay-filter = "category_test" id="category">-->
<#--                                <option value="" ></option>-->
<#--                            </select>-->
<#--                        </div>-->
<#--                    </div>-->

                </div>

                <!-- 富文本编辑器 -->
                <div id="test-editormd"></div>
                <!--底部按钮-->
                <div class="layui-form-item layui-layout-admin">
                    <div class="layui-input-block">
                        <div class="layui-footer" style="left: 0;z-index:99;text-align: right">
                            <button id="blog-submit" data-type="test" class="layui-btn" lay-submit="" lay-filter="component-form-demo1">
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
<#--模态框-->
<div id="blog-other" style="display: none">
    <div class="layui-form" lay-filter="layuiadmin-app-form-list" id="layuiadmin-app-form-list" style="padding: 20px 30px 0 0;">
        <div class="layui-form-item">
            <label class="layui-form-label">文章标题</label>
            <div class="layui-input-inline">
                <input type="text" name="blogTitle" lay-verify="required" placeholder="请输入文章标题" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">发布人</label>
            <div class="layui-input-inline">
                <input type="text" name="author" lay-verify="required" placeholder="请输入号码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">文章内容</label>
            <div class="layui-input-inline">
                <textarea name="content" lay-verify="required" style="width: 400px; height: 150px;" autocomplete="off" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">分类</label>
            <div class="layui-input-inline">
                <select name="label" lay-verify="required" lay-filter = "category_test" id="category">
                    <option value="" ></option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">发布状态</label>
            <div class="layui-input-inline">
                <input type="checkbox" lay-verify="required" lay-filter="status" name="status" lay-skin="switch" lay-text="已发布|待修改">
            </div>
        </div>
        <div class="layui-form-item layui-hide">
            <input type="button" lay-submit lay-filter="layuiadmin-app-form-submit" id="layuiadmin-app-form-submit" value="确认添加">
            <input type="button" lay-submit lay-filter="layuiadmin-app-form-edit" id="layuiadmin-app-form-edit" value="确认编辑">
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

<script type="text/javascript">
    layui.config({
        base: '../../../static/layui/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index','layer', 'jquery','form', 'laydate'], function () {
        var layer = layui.layer
            , $ = layui.jquery
            ,form = layui.form
        ,admin = layui.admin;

       /* layer.ready(function(data){
            $.ajax({
                url: "getCategory",
                type: 'GET',
                dataType: 'json',
                contentType: "application/json;charset=utf-8",
                success: function(datas) {
                    var data = datas.data;
                    console.log('获取中的数据=================='+data)
                    $("#category").empty();
                    for(var i = 0;i< data.length;i++){
                        console.log('循环开始============'+datas.data[i]);
                        $("#category").append('<option value="' + data[i].categoryId + '">'+ data[i].categoryName  + '</option>');
                    }
                    console.log("循环结束=========================")
                    //注意：最后必须重新渲染下拉框，否则没有任何效果。
                    //重新渲染
                    form.render("select");
                }});}
        );*/

        var testEditor;
        //页面加载完成调用此方法
        $(document).ready(function () {
            console.log("===================页面加载完成=============开始渲染md文档");
            testEditor = editormd("test-editormd", {
                width: "100%",
                height: 640,
                path: '../../static/editormd/lib/',
                theme: "default",
                previewTheme: "default",
                editorTheme: "default",
                //markdown : md,             // 初始化编辑区的内容
                codeFold: true,
                //syncScrolling : false,
                saveHTMLToTextarea: true,    // 保存 HTML 到 Textarea
                searchReplace: true,
                //watch : false,                // 关闭实时预览
                htmlDecode: "style,script,iframe|on*",            // 开启 HTML 标签解析，为了安全性，默认不开启
                //toolbar  : false,             //关闭工具栏
                //previewCodeHighlight : false, // 关闭预览 HTML 的代码块高亮，默认开启
                emoji: true,
                taskList: true,
                tocm: true,         // Using [TOCM]
                tex: true,                   // 开启科学公式TeX语言支持，默认关闭
                flowChart: true,             // 开启流程图支持，默认关闭
                sequenceDiagram: true,       // 开启时序/序列图支持，默认关闭,
                //dialogLockScreen : false,   // 设置弹出层对话框不锁屏，全局通用，默认为true
                //dialogShowMask : false,     // 设置弹出层对话框显示透明遮罩层，全局通用，默认为true
                //dialogDraggable : false,    // 设置弹出层对话框不可拖动，全局通用，默认为true
                //dialogMaskOpacity : 0.4,    // 设置透明遮罩层的透明度，全局通用，默认值为0.1
                //dialogMaskBgColor : "#000", // 设置透明遮罩层的背景颜色，全局通用，默认为#fff
                imageUpload: true,
                imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
                imageUploadURL: "./php/upload.php", // 文件上传路径，返回值为图片加载的路径
                onload: function () {
                    // 加载后富文本编辑器成功后的回调
                    console.log('onload', this);
                    //this.fullscreen();
                    //this.unwatch();
                    //this.watch().fullscreen();

                    //this.setMarkdown("#PHP");
                    //this.width("100%");
                    //this.height(480);
                    //this.resize("100%", 640);

                    // 异步请求md文件，用于编辑时的数据回显
                    /* $.get('test.md', function(md) {
                         testEditor.setMarkdown(md);
                     });*/
                }
            });
            console.log("=================md页面渲染完毕=======================")
        });

        $('#blog-submit').on('click', function(){
            var arr = [];
            layer.open({
                type: 2,
                content: 'toListForm',
                area: ['500px', '500px'],
                title: "更改信息",
                //fixed: false, //不固定
                maxmin: true,
                shadeClose:false,
                btn: ['保存','取消'],
                yes:function (index, layero) {
                    var othis = layero.find('iframe').contents().find("#blogEditFrom"),
                        photoUrl = othis.find('#photoUrl').val(),
                        comments = othis.find('input[name="comments"]:checked').val(),
                        status = othis.find('input[name="status"]').val(),
                        tags = othis.find('input[name="tags"]:checked').each(function () {
                            arr.push($(this).val());
                        }),
                    top = othis.find('input[name="top"]').val(),
                    category = othis.find('#category').val();
                    console.log("组装请求数据开始");
                    var content = testEditor.getMarkdown();
                    var title = $('#title').val();
                    var data = {
                        "blogTitle": title, "blogCategoryId": category,"blogCoverImage":photoUrl,"blogContent": content,
                        "blogTags": arr.toString(), "blogStatus":status === "on"?1:0,"enableComment":comments,
                        "blogTop":status === "on"?1:0
                    };
                    console.log("组装请求数据成功=============开始发送请求"+data)
                    $.ajax({
                        type: 'POST',//方法类型
                        url: "saveArticle",
                        data: JSON.stringify(data),
                        dataType:"json",
                        contentType: "application/json;charset=utf-8",
                        success: function (res) {
                            if (res.code === 0) {
                                //跳转文章列表页面
                                layer.msg('添加成功', {time: 1000}, function () {
                                    window.location = '/admin/article/toArticleList';
                                });
                            } else {
                                layer.msg(res.msg);
                                $("#img").click();
                            }
                        },
                        error:function(){
                            //请求出错处理
                            layer.msg('发送失败', {icon: 5});
                        }
                    });
                }

            });
            /*top.layui.admin.popupRight({
                id: 'LAY_adminPopupLayerTest'
                ,area:'400px'
                //,content:"toListForm"
                ,btn:["保存","取消"]
                ,success: function(){
                    //$('#'+ this.id).html('<div style="padding: 20px;">放入内容</div>');
                    //admin.view(this.id).render('system/xxx')
                    top.layui.view(this.id).render('../../../admin/article/toListForm');
                }
            });*/
        });

        //提交表单
        /*$('#blog-submit').click(function () {
            var a = testEditor.getMarkdown();
            var title = $('#title').val();
            var tag = $('#tag').val();
            var category = $('#category').val();
               var data = {
                "blogTitle": title, "blogCategoryId": category,
                "blogTags": tag, "blogContent": a
            };
            $.ajax({
                type: 'POST',//方法类型
                url: "saveArticle",
                data: JSON.stringify(data),
                dataType:"json",
                contentType: "application/json;charset=utf-8",
                success: function (res) {
                    if (res.code === 0) {
                        //登陆成功跳转页面
                        layer.msg('添加成功', {time: 1000}, function () {
                            window.location = '/admin/article/toArticleList';
                        });
                    } else {
                        layer.msg(res.msg);
                        $("#img").click();
                    }
                },
                error:function(){
                    //请求出错处理
                    layer.msg('发送失败', {icon: 5});
                }
            });
        });*/

        $('#LAY-component-layer-theme .layadmin-layer-demo .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] && active[type].call(this);
        });
    });
</script>
</body>
</html>