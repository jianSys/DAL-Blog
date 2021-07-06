<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>博客|文章添加</title>
    <link rel="stylesheet" href="../../../static/layui/layui/css/layui.css">
    <link rel="stylesheet" href="../../../static/admin/plugins/editormd/css/editormd.css"/>
    <!-- 页面解析markdown为HTML显示需要的css -->
    <link rel="stylesheet" href="../../../static/admin/plugins/editormd/css/editormd.preview.css"/>
</head>
<body>

<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-card-body" style="padding: 15px;">
            <div class="layui-form" method="post" action="" lay-filter="component-form-group">
                <div class="layui-form-item">
                    <label class="layui-form-label">页面标题</label>
                    <div class="layui-input-block">
                        <input id="title" type="text" name="title" lay-verify="title" autocomplete="off"
                               placeholder="请输入页面标题" class="layui-input">
                    </div>
                </div>
                <!-- 富文本编辑器 -->
                <div id="test-editormd"></div>
                <!--底部按钮-->
                <div class="layui-form-item layui-layout-admin">
                    <div class="layui-input-block">
                        <div class="layui-footer" style="left: 0;z-index:99;text-align: right">
                            <button id="blog-submit" data-type="test" class="layui-btn" lay-submit=""
                                    lay-filter="component-form-demo1">
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
<script src="../../../static/jquery/jquery-3.2.1.min.js"></script>
<script src="../../../static/layui/layui/layui.js"></script>
<script src="../../../static/admin/plugins/editormd/js/editormd.min.js"></script>
<!-- 页面markdown解析成HTML需要的js -->
<script src="../../../static/admin/plugins/editormd/lib/marked.min.js"></script>
<script src="../../../static/admin/plugins/editormd/lib/prettify.min.js"></script>
<script src="../../../static/admin/plugins/editormd/lib/raphael.min.js"></script>
<script src="../../../static/admin/plugins/editormd/lib/underscore.min.js"></script>
<script src="../../../static/admin/plugins/editormd/lib/sequence-diagram.min.js"></script>
<script src="../../../static/admin/plugins/editormd/lib/flowchart.min.js"></script>
<script src="../../../static/admin/plugins/editormd/lib/jquery.flowchart.min.js"></script>
<script>
    layui.config({
        base: '../../../static/layui/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'layer', 'jquery', 'form', 'laydate'], function () {
        var layer = layui.layer
            , $ = layui.jquery
            , form = layui.form
            , admin = layui.admin;

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

        $('#blog-submit').on('click', function () {
            var arr = [];
            layer.open({
                type: 2,
                content: 'toPageForm',
                area: ['500px', '500px'],
                title: "更改信息",
                //fixed: false, //不固定
                maxmin: true,
                shadeClose: false,
                btn: ['保存', '取消'],
                yes: function (index, layero) {
                    var othis = layero.find('iframe').contents().find("#blogEditFrom"),
                        photoUrl = othis.find('#photoUrl').val(),
                        comments = othis.find('input[name="comments"]:checked').val(),
                        introduce = othis.find('input[name="introduce"]').val(),
                        subUrl = othis.find('input[name="subUrl"]').val();
                    console.log("组装请求数据开始");
                    var content = testEditor.getMarkdown();
                    //var content = encodeURIComponent(testEditor.getMarkdown());
                    var title = $('#title').val();
                    /*if (title == ''){
                        layer.msg('标题不能为空',function(){time:2000});
                        return flase;
                    }
                    if (content==''){
                        layer.msg('内容不能为空',function(){time:2000});
                        return flase;
                    }*/
                    var data = {
                        "blogTitle": title, "blogCoverImage": photoUrl, "blogContent": content,"blogSubUrl":subUrl,
                        "enableComment": comments, "blogIntroduce":introduce
                    };
                    console.log("组装请求数据成功=============开始发送请求" + data)
                    $.ajax({
                        type: 'POST',//方法类型
                        url: "savePage",
                        data: JSON.stringify(data),
                        dataType: "json",
                        contentType: "application/json;charset=utf-8",
                        success: function (res) {
                            if (res.code === 0) {
                                //跳转文章列表页面
                                layer.msg('添加成功', {time: 1000}, function () {
                                    window.location = '/admin/home/homepage2';
                                });
                            } else {
                                layer.msg(res.msg);
                                $("#img").click();
                            }
                        },
                        error: function () {
                            //请求出错处理
                            layer.msg('发送失败', {icon: 5});
                        }
                    });
                }

            });
        });
        $('#LAY-component-layer-theme .layadmin-layer-demo .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] && active[type].call(this);
        });
    });
</script>
</body>
</html>