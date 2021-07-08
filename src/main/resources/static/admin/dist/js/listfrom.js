layui.config({
    base: '../../../static/admin/plugins/layui/' //静态资源所在路径
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