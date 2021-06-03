

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>layuiAdmin 文章管理 iframe 框</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="../../../static/layui/layui/css/layui.css" media="all">
</head>
<body>

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

  <script src="../../../static/layui/layui/layui.js"></script>
  <script src="../../../static/jquery/jquery-3.2.1.min.js"></script>
  <script>
  layui.config({
    base: '../../../static/layui/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'form'], function(){
    var $ = layui.$
    ,form = layui.form
    ,layer = layui.layer;
    
    //监听提交
    form.on('submit(layuiadmin-app-form-submit)', function(data){
      var field = data.field; //获取提交的字段
      var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引  

      //提交 Ajax 成功后，关闭当前弹层并重载表格
      //$.ajax({});
      parent.layui.table.reload('LAY-app-content-list'); //重载表格
      parent.layer.close(index); //再执行关闭 
    });

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
  })
  </script>
</body>
</html>