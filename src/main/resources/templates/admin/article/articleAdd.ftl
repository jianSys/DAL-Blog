<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>博客|文章编辑</title>
    <link rel="stylesheet" href="../../../static/layui/layui/css/layui.css">
    <link rel="stylesheet" href="../../../static/editormd/css/editormd.css"/>
    <!-- 页面解析markdown为HTML显示需要的css -->
    <link rel="stylesheet" href="../../../static/editormd/css/editormd.preview.css"/>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-card-body" style="padding: 15px;">
            <div id="blogArticleForm" class="layui-form" method="post" action="" lay-filter="component-form-group">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">文章标题</label>
                        <div class="layui-input-inline">
                            <input id="blogId" value="" type="hidden" name="id">
                            <input id="title" type="text" name="title" lay-verify="title" autocomplete="off"
                                   placeholder="请输入文章标题" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">封面图</label>
                        <div class="layui-input-inline">
                            <input name="avatar" lay-verify="required" id="photoUrl" placeholder="图片地址" value=""
                                   class="layui-input">
                        </div>
                        <div class="layui-input-inline layui-btn-container" style="width: auto;">
                            <button type="button" class="layui-btn layui-btn-primary" id="uploadImages">
                                <i class="layui-icon">&#xe67c;</i>上传图片
                            </button>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">分类</label>
                        <div class="layui-input-inline">
                            <select name="label" lay-verify="required" lay-filter="category_test" id="category">
                                <option value=""></option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">标签</label>
                        <div class="layui-input-block" id="tags">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">文章简介</label>
                    <div class="layui-input-block">
                        <textarea id="introduce" name="introduce" placeholder="请输入内容" class="layui-textarea"></textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">允许评论</label>
                        <div class="layui-input-inline">
                            <input type="radio" lay-filter="comments" name="comments" value="0" title="是" checked="">
                            <input type="radio" lay-filter="comments" name="comments" value="1" title="否">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">是否置顶</label>
                        <div class="layui-input-inline">
                           <input type="radio" lay-filter="top" name="top" value="0" title="否" checked="">
                            <input type="radio" lay-filter="top" name="top" value="1" title="是">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">发布状态</label>
                        <div class="layui-input-inline">
                            <input type="radio" lay-filter="status" name="status" value="0" title="草稿" checked="">
                            <input type="radio" lay-filter="status" name="status" value="1" title="发布">
                        </div>
                    </div>
                </div>
                <!-- 富文本编辑器 -->
                <div id="test-editormd"></div>
            </div>
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
<script>
    layui.config({
        base: '../../../static/layui/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'layer', 'jquery', 'form', 'laydate','upload'], function () {
        var layer = layui.layer
            , $ = layui.jquery
            , form = layui.form
            , admin = layui.admin
        ,upload = layui.upload;




        var testEditor;
        var categoryId;
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
                imageUploadURL: "../upload/uploadFile", // 文件上传路径，返回值为图片加载的路径
                onload: function () {
                    // 加载后富文本编辑器成功后的回调
                    console.log('onload', this);
                    //加载完成后生成目录
                    testEditor.config("tocDropdown", true);
                    // initPasteDragImg(this); //允许粘贴和拖拉图片到editormd
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
            //从父层获取值，json是父层的全局js变量。eval是将该string类型的json串变为标准的json串
            var parent_json = eval('('+parent.json+')');
            console.dir("+++++++++++++++++============"+parent_json)
            console.log("父页面获取的值==============="+parent_json);
            //alert(parent_json.blogId);
            $("#blogId").val(parent_json.blogId);
            $("#title").val(parent_json.blogTitle);
            $("#photoUrl").val(parent_json.blogCoverImage);
            $("#introduce").val(parent_json.blogIntroduce);

            $("input[name='comments'][value="+parent_json.enableComment+"]").prop("checked","true");
            $("input[name='top'][value="+parent_json.blogTop+"]").prop("checked","true");
            $("input[name='status'][value="+parent_json.blogStatus+"]").prop("checked","true");

            var unitType = parent_json.blogTags.split(",");
            //console.log("这是获取到的arr"+arr);
            for (var j = 0; j <unitType.length ; j++) {
                var unitTypeCheckbox=$("input[name='tags']");
                for(var i=0;i<unitTypeCheckbox.length;i++){
                    if(unitTypeCheckbox[i].title===unitType[j]){
                        unitTypeCheckbox[i].value=unitType[j];
                        unitTypeCheckbox[i].checked=true;
                    }
                }
                form.render();
            }

            $('.editormd-markdown-textarea').val(decodeURIComponent(parent_json.blogContent));
            //testEditor.setMarkdown(parent_json.blogContent)
            categoryId = parent_json.blogCategoryId;
        });


        layer.ready(function (data) {
            //console.log(data['value']);
            //var value = data['value'];
            $.ajax({
                url: "getCategory",
                type: 'GET',
                dataType: 'json',
                //data:{id: value},
                contentType: "application/json;charset=utf-8",
                success: function (datas) {
                    var data = datas.data;
                    console.log('获取中的数据==================' + data);
                    $("#category").empty();
                    for (var i = 0; i < data.length; i++) {
                        console.log('循环开始============' + datas.data[i]);
                        $("#category").append('<option value="' + data[i].categoryId + '">' + data[i].categoryName + '</option>');
                        //$('#category').append(new Option(data[i].categoryName,data[i].categoryId));//往下拉菜单里添加元素
                        $('#category').val(categoryId);
                    }

                    console.log("循环结束=========================");
                    //注意：最后必须重新渲染下拉框，否则没有任何效果。
                    //重新渲染
                    form.render("select");
                }
            });
        });
        $(document).ready(function () {
            $.ajax({
                url: "../tags/getAllTags",
                type: 'GET',
                dataType: 'json',
                contentType: "application/json;charset=utf-8",
                success: function (res) {
                    if (res.code === 0) {
                        var data = res.data;
                        console.log(data)
                        $("#tags").empty();
                        for (var i = 0; i < data.length; i++) {
                            $("#tags").append('<input type="checkbox" value="' + data[i].tagId + '"name="tags" lay-skin="primary" title="' + data[i].tagName + '">')
                        }
                        //渲染表单
                        form.render();
                    } else {
                        layer.msg("查询失败")
                    }
                }
            });
        });






        upload.render({
            elem: '#uploadImages'
            , url: "../upload/images"
            , accept: 'images'
            , method: 'post'
            , acceptMime: 'image/*'
            , done: function (res) {
                if (res.code === 0) {
                    $("#photoUrl").prop("value", res.data);
                }
                //$(this.item).prev("div").children("input").val(res.data.data)
            }
        });

        /*form.verify({
            title: function(value){
                if(value.length < 5){
                    return '标题至少得5个字符啊';
                }
            }
        });*/

        $('#blog-submit').on('click', function () {
            var arr = [];
            layer.open({
                type: 2,
                content: 'toListForm',
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
                        status = othis.find('input[name="status"]').val(),
                        tags = othis.find('input[name="tags"]:checked').each(function () {
                            arr.push($(this).val());
                        }),
                        top = othis.find('input[name="top"]').val(),
                        category = othis.find('#category').val();
                    console.log("组装请求数据开始");
                    var content = testEditor.getMarkdown();
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
                        "blogTitle": title, "blogCategoryId": category, "blogCoverImage": photoUrl, "blogContent": content,
                        "blogTags": arr.toString(), "blogStatus": status === "on" ? 1 : 0, "enableComment": comments,
                        "blogTop": status === "on" ? 1 : 0
                    };
                    console.log("组装请求数据成功=============开始发送请求" + data)
                    $.ajax({
                        type: 'POST',//方法类型
                        url: "saveArticle",
                        data: JSON.stringify(data),
                        dataType: "json",
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
                        error: function () {
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

        $('#LAY-component-layer-theme .layadmin-layer-demo .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] && active[type].call(this);
        });

    });

</script>
</body>
</html>