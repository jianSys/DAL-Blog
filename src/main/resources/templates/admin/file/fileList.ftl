<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>主题</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="../../static/layui/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="../../static/layui/style/admin.css" media="all">
    <style>
        .theme_open,.theme_set{
            width: 48%;display: inline-block;
        }
        .theme_open:hover,.theme_set:hover{
            cursor: pointer;
            color: #01AAED;
        }
        /*.layui-card:hover{
            border: 1px solid rgba(30, 35, 42, .06);
            box-shadow: 0 1px 1px 0px rgb(0 0 0 / 5%);
        }*/
    </style>
</head>
<body>

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <#list  fileList as files>
            <div class="layui-col-sm6 layui-col-md2">
            <div class="layui-card">
                <div class="layui-card-body layuiadmin-card-list" style="padding: 3px;height: 115px;">
                    <img class="" src="${files.fileUrl}" style="width: 100%;background-size: cover">
                </div>
                <div class="layui-card-body layuiadmin-card-list" style="border-top: #ccc;padding: 10px 10px;font-size: 15px;">
                    <p style="text-align: center">
                        ${files.fileName}
                    </p>
                </div>
            </div>
        </div>

        </#list>
    </div>
</div>
</div>

<script src="../../static/layui/layui/layui.js"></script>
<script>
    layui.config({
        base: '../../static/layui/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'sample','laypage'], function () {
        var layer = layui.layer
            , $ = layui.jquery
            , form = layui.form
            , admin = layui.admin
            , upload = layui.upload
        ,laypage=layui.laypage;

        $(".theme_set").on('click',function (){
            layer.alert('拼命开发中', {icon: 6});
        })
        $(".theme_open").on('click',function (){
            var id = $(this).parent().parent().siblings("input").val()
            console.log(id)
           $.ajax({
               type:"get",
               url:"replaceTheme/"+id,
               dataType:"JSON",
               contentType: "application/json",
               success: function (res) {
                   if (res.code ===0){
                       layer.msg("修改成功", {icon:1,time:1000},function(){
                           window.location.reload()
                       });
                   }else{
                       layer.msg("修改失败");
                   }
               }
           })
        })
    });
</script>
</body>
</html>