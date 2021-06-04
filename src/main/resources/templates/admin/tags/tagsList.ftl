
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>博客|标签管理</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="../../../static/layui/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="../../../static/layui/style/admin.css" media="all">
</head>
<body>

  <div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-card-header layuiadmin-card-header-auto">
        <button class="layui-btn layuiadmin-btn-tags" data-type="add">添加</button>
      </div>
      <div class="layui-card-body">    
        <table id="LAY-app-content-tags" lay-filter="LAY-app-content-tags"></table>  
        <script type="text/html" id="layuiadmin-app-cont-tagsbar">
          <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
          <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
        </script>
      </div>
    </div>
  </div>

  <script src="../../../static/layui/layui/layui.js"></script>
  <script>
  layui.config({
    base: '../../../static/layui/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'contlist', 'table'], function(){

    var table = layui.table
    form = layui.form;
    //渲染表格,发送请求
    table.render({
      elem:'#LAY-app-content-tags',
      url:'tagsList',
      cellMinWidth:80,
      page: true,
      limits : [10,15,20,25],
      limit : 10,
      cols:[[
        //渲染表头
        {type:'numbers',title:'序号', fixed:"left", width:50},
        {field: 'tagId', title: 'Id', minWidth:30, align:"center"},
        {field: 'tagName', title: '标签分类', align:'center'},
       {title: '操作', minWidth:175, templet:'#layuiadmin-app-cont-tagsbar',fixed:"right",align:"center"}
      ]]
    });
    var $ = layui.$, active = {
      add: function(){
        layer.open({
          type: 2
          ,title: '添加分类'
          ,content: 'toTagsEdit'
          ,area: ['400px', '200px']
          ,btn: ['确定', '取消']
          ,yes: function(index, layero){
             var othis = layero.find('iframe').contents().find("#layuiadmin-app-form-tags")
            ,tags = othis.find('input[name="tags"]').val();
            if(!tags.replace(/\s/g, '')) return;
            // table.reload('LAY-app-content-tags');
            // layer.close(index);
            console.log("=============开始渲染date数据==============");
            var data = {
              "tagName":tags
            };
            console.log("=============结束渲染date数据==============",data);
            $.ajax({
              type: 'POST',//方法类型
              url: "saveTags",
              data: JSON.stringify(data),
              dataType:"json",
              contentType: "application/json;charset=utf-8",
              success: function (res) {
                if (res.code === 0) {
                  layer.close(index);//关闭弹窗
                  table.reload('LAY-app-content-tags');//重载表格
                } else {
                  layer.msg(res.msg);
                  $("#img").click();
                }
              }

            });
          }
        }); 
      }
    };
    $('.layui-btn.layuiadmin-btn-tags').on('click', function(){
      var type = $(this).data('type');
      active[type] ? active[type].call(this) : '';
    });
    //监听操作
    table.on('tool(LAY-app-content-tags)', function(obj){
      var data = obj.data;
      console.log("======="+data);
      console.log(obj.event+"=======");

      if(obj.event === 'del'){
        layer.confirm('真的删除行么', function(index){
          $.ajax({
            url: "delTags",
            type: "POST",
            data:{"id":data.tagId},
            dataType: "json",
            success: function(data){
              obj.del();
              layer.close(index);
              layer.msg("删除成功", {icon: 6});
            },
            error:function (data) {
              layer.msg("删除失败", {icon: 5});
            }

          });
        });
      } else if(obj.event === 'edit'){
        layer.prompt({
          formType: 2
          ,value: data.word
        }, function(value, index){
          EidtUv(data,value,index,obj);

        });
      }
    });
    function  EidtUv(data,value,index,obj) {
      console.log(data+"..."+value+"..."+index+"..."+obj);
      $.ajax({
        url: "<%=request.getContextPath()%>/admin/modiSens",
        type: "POST",
        data:{"id":data.tagId,"word":tagName},
        dataType: "json",
        success: function(result){
          if(result>=1){
            layer.close(index);
            //同步更新表格和缓存对应的值
            obj.update({
              word: value
            });
            layer.msg("修改成功", {icon: 6});
          }else{
            layer.msg("修改失败", {icon: 5});
          }
        }
      });
    }
  });
  </script>
</body>
</html>
