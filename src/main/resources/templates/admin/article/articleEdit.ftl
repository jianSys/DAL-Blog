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

                    <div class="layui-inline">
                        <label class="layui-form-label">文章分类</label>
                        <div class="layui-input-inline">
                            <select id="category" name="label" lay-verify="required" lay-filter = "category_test" id="category">
                                <option value="" ></option>
                            </select>
                        </div>
                    </div>

                </div>

                <!-- 富文本编辑器 -->
                <div id="test-editormd"></div>
                <!--底部按钮-->
                <div class="layui-form-item layui-layout-admin">
                    <div class="layui-input-block">
                        <div class="layui-footer" style="left: 0;z-index:99;text-align: right">
                            <button id="blog-submit" class="layui-btn" lay-submit="" lay-filter="component-form-demo1">
                                立即提交
                            </button>
                            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 弹出框 -->
<!--
<div  class="layui-form" lay-filter="layuiadmin-app-form-list" id="layuiadmin-app-form-list" style="padding: 20px 30px 0 0;">

</div>
-->
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
    layui.use(['layer', 'jquery','form', 'laydate'], function () {
        var layer = layui.layer
            , $ = layui.jquery
            ,form = layui.form;

        layer.ready(function(data){
            //console.log(data['value']);
            //var value = data['value'];
            $.ajax({
                url: "getCategory",
                type: 'GET',
                dataType: 'json',
                //data:{id: value},
                contentType: "application/json;charset=utf-8",
                success: function(datas) {
                    console.log('成功后返回的参数=========='+datas);
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
        );

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
        /*$('#showEditor').on('click', function(){
            // 弹出框
            layer.open({
                type: 1
                ,content: $('#myeditor')
                ,btn: ['确定','关闭']
                ,btnAlign: 'c' //按钮居中
                ,maxmin: true
                ,shade: 0 //不显示遮罩
                ,area: ['1000px', '800px']
                ,yes: function(){
                    layer.closeAll();
                },
                success:function () {

                }
            });
        });*/

        //提交表单
        $('#blog-submit').click(function () {
            var a = testEditor.getMarkdown();
            var title = $('#title').val();
            var tag = $('#tag').val();
            var category = $('#category').val();
            console.log(title+"========================"+tag+"===================="+category+"============="+a);
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
                            window.location = '/admin/article/toList';
                        });
                    } else {
                        layer.msg(res.msg);
                        $("#img").click();
                    }
                }

            });
        });
        function save(){
            layer.msg("成功")
        }
        // 获取markdown源码
        $('#getMarkdownContent').on('click', function () {
            var mdContent = $('.editormd-markdown-textarea').val();
            console.log(mdContent);
            var content = testEditor.getMarkdown();
            console.log(content);
        });
        // 获取解析后的html
        $('#getHtmlContent').on('click', function () {
            var content = testEditor.getHTML();

            console.log(content);
        });

        // 页面解析markdown为html进行显示
        $('#showHTML').on('click', function () {
            // 模拟从数据库中取内容
            $.get('test', function (md) {
                // 给textarea赋值
                $('#content').val(md);
                // 解析
                editormd.markdownToHTML("markdownToHTML", {
                    htmlDecode: "style,script,iframe",
                    emoji: true,  // 解析表情
                    taskList: true,  // 解析列表
                    tex: true,  // 默认不解析
                    flowChart: true,  // 默认不解析
                    sequenceDiagram: true  // 默认不解析
                });
            });
        });
    });
</script>
</body>
</html>