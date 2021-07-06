<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>博客|文章管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="../../../static/layui/layui/css/layui.css" media="all">
</head>
<body>

<div class="layui-form" lay-filter="layuiadmin-app-form-list" id="blogEditFrom" style="padding: 20px 30px 0 0;">
    <div class="layui-form-item">
        <label class="layui-form-label">封面图</label>
        <div class="layui-input-inline">
            <input name="avatar" lay-verify="required" id="photoUrl" placeholder="图片地址" value="" class="layui-input">
        </div>
        <div class="layui-input-inline layui-btn-container" style="width: auto;">
            <button type="button" class="layui-btn layui-btn-primary" id="uploadImages">
                <i class="layui-icon">&#xe67c;</i>上传图片
            </button>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">别名</label>
        <div class="layui-input-inline">
            <input type="text" name="subUrl" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">简介</label>
        <div class="layui-input-block">
            <textarea name="introduce" placeholder="文章简介" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">允许评论</label>
        <div class="layui-input-block">
            <input type="radio" lay-filter="comments" name="comments" value="0" title="是" checked="">
            <input type="radio" lay-filter="comments" name="comments" value="1" title="否">
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <input type="button" lay-submit lay-filter="layuiadmin-app-form-submit" id="layuiadmin-app-form-submit"
               value="确认添加">
        <input type="button" lay-submit lay-filter="layuiadmin-app-form-edit" id="layuiadmin-app-form-edit"
               value="确认编辑">
    </div>
</div>
<script src="../../../static/layui/layui/layui.js"></script>
<script src="../../../static/jquery/jquery-3.2.1.min.js"></script>
<script>
    layui.config({
        base: '../../../static/layui/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'form', 'set', 'upload'], function () {
        var $ = layui.$
            , form = layui.form
            , layer = layui.layer
            , upload = layui.upload;

        //监听提交
        form.on('submit(layuiadmin-app-form-submit)', function (data) {
            var field = data.field; //获取提交的字段
            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引

            //提交 Ajax 成功后，关闭当前弹层并重载表格
            //$.ajax({});
            parent.layui.table.reload('LAY-app-content-list'); //重载表格
            parent.layer.close(index); //再执行关闭
        });

        /*layer.ready(function (data) {
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
                    }
                    console.log("循环结束=========================");
                    //注意：最后必须重新渲染下拉框，否则没有任何效果。
                    //重新渲染
                    form.render("select");
                }
            });
        });*/
        /*$(document).ready(function () {
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
        });*/
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
        /* form.on('select(category_test)', function(data){
           console.log(data['value']);
           var value = data['value'];
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
             }
           });

         });*/
    });
</script>
</body>
</html>