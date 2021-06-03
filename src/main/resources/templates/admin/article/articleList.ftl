

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>博客|文章列表</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="../../../static/layui/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="../../../static/layui/style/admin.css" media="all">
</head>
<body>

<div class="layui-fluid">
  <div class="layui-card">
    <div class="layui-form layui-card-header layuiadmin-card-header-auto">
      <div class="layui-form-item">
        <div class="layui-inline">
          <label class="layui-form-label">文章ID</label>
          <div class="layui-input-inline">
            <input type="text" name="id" placeholder="请输入" autocomplete="off" class="layui-input">
          </div>
        </div>
        <#--<div class="layui-inline">
          <label class="layui-form-label">作者</label>
          <div class="layui-input-inline">
            <input type="text" name="author" placeholder="请输入" autocomplete="off" class="layui-input">
          </div>
        </div>-->
        <div class="layui-inline">
          <label class="layui-form-label">标题</label>
          <div class="layui-input-inline">
            <input type="text" name="title" placeholder="请输入" autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-inline">
          <label class="layui-form-label">分类</label>
          <div class="layui-input-inline">
            <input type="text" name="blogCategoryName" placeholder="请输入" autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-inline">
          <button class="layui-btn layuiadmin-btn-list" lay-submit lay-filter="LAY-app-contlist-search">
            <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
          </button>
        </div>
      </div>
    </div>

    <div class="layui-card-body">
      <div style="padding-bottom: 10px;">
        <button class="layui-btn layuiadmin-btn-list" data-type="batchdel">删除</button>
        <button class="layui-btn layuiadmin-btn-list" data-type="add">添加</button>
      </div>


      <table id="LAY-app-content-list" lay-filter="LAY-app-content-list"></table>

      <script type="text/html" id="buttonTpl">
        {{#  if(d.blogStatus){ }}
        <button class="layui-btn layui-btn-xs">已发布</button>
        {{#  } else { }}
        <button class="layui-btn layui-btn-primary layui-btn-xs">待修改</button>
        {{#  } }}
      </script>
      <script type="text/html" id="table-content-list">
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
            , $ = layui.jquery
            ,form = layui.form;
    console.log("渲染表格开始")
    table.render({
      elem:'#LAY-app-content-list',
      url:'articleList',
      cellMinWidth:80,
      page: true,
      limits : [10,15,20,25],
      limit : 10,
      cols:[[
        //渲染表头
        {type: "checkbox", fixed:"left", width:50},
        {field: 'blogId', title: 'id', minWidth:30, align:"center"},
        {field: 'blogTitle', title: '文章标题', align:'center'},
        {field: 'blogCategoryName', title: '文章分类', align:'center'},
        {field: 'blogTags', title: '文章标签', align:'center'},
        {field: 'createTime', title: '发布时间', align:'center',minWidth:150},
        {title: '状态', minWidth:30,templet:"#buttonTpl", fixed:"right",align:'center'},
        {title: '操作', minWidth:175, templet:'#table-content-list',fixed:"right",align:"center"}
      ]]
      //data:data
      //id:'article'
    });
    //监听编辑或者删除
    console.log("监听表格时间")
    table.on('tool(LAY-app-content-list)',function(obj){
      var id = obj.data.blogId;
      var data = obj.data;
      console.log("选中的id为========"+id);
      console.log("操作为========="+obj.event)
      if (obj.event === 'del'){
        layer.confirm("确定要删除此文章吗?",function(index){
          var index = layer.load(1);
          deleteArticle(id);
        })
      }else if(obj.event === 'edit'){
        console.log(obj.data);
        layer.open({
          type: 2,
          content: 'toArticleEdit',
          area: ['500px', '500px'],
          title: "更改信息",
          //fixed: false, //不固定
          maxmin: true,
          shadeClose:false,
          btn: ['修改','取消'],

        });
        //表单值
        form.val("layuiadmin-app-form-list",{
          "blogTitle":data.blogTitle
        });

      }
    });
    //监听搜索
    form.on('submit(LAY-app-contlist-search)', function(data){
      var field = data.field;

      //执行重载
      table.reload('LAY-app-content-list', {
        where: field
      });
    });

    var $ = layui.$, active = {
      batchdel: function(){
        var checkStatus = table.checkStatus('LAY-app-content-list')
                ,checkData = checkStatus.data; //得到选中的数据

        if(checkData.length === 0){
          return layer.msg('请选择数据');
        }

        layer.confirm('确定删除吗？', function(index) {
          //执行 Ajax 后重载
          alert(index);
          admin.req({
            url: 'delBlog',
            method:'POST',
            dataType:'json',
            data:{"id":data.blogId},
            done: function (res) {
              layer.msg(res);
            }
          });
          table.reload('LAY-app-content-list');
          layer.msg('已删除');
        });
      },
      add: function(){
        window.location.href = 'toArticleEdit'
        /*layer.open({
          type: 2
          ,title: '添加文章'
          ,content: 'toEdit'
          ,maxmin: true
          ,area: ['550px', '550px']
          ,btn: ['确定', '取消']
          ,yes: function(index, layero){
            //点击确认触发 iframe 内容中的按钮提交
            var submit = layero.find('iframe').contents().find("#layuiadmin-app-form-submit");
            submit.click();
          }
        }); */
      }
    };

    $('.layui-btn.layuiadmin-btn-list').on('click', function(){
      var type = $(this).data('type');
      active[type] ? active[type].call(this) : '';
    });


    //删除列
    function deleteArticle (blogId){
      $.ajax({
        type: 'delete',//方法类型
        url: "delArticle/"+blogId,
        //data: JSON.stringify(data),
        dataType:"json",
        contentType: "application/json;charset=utf-8",
        success: function (res){
          if (res.code===0){
            table.reload('LAY-app-content-list');
            layer.msg('已删除');
          }else {
            table.reload('LAY-app-content-list');
          }
        }
      })
    }
  });
</script>
</body>
</html>
