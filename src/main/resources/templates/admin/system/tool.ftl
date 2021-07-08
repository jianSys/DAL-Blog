

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>小工具</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="../../static/admin/plugins/layui/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="../../static/admin/plugins/layui/style/admin.css" media="all">
</head>
<body>

  <div class="layui-fluid">
    <div class="layui-row layui-col-space15">
      <div class="layui-col-sm6 layui-col-md3">
        <div class="layui-card">
          <div class="layui-card-header">
            Markdown 文章导入
          </div>
          <div class="layui-card-body layuiadmin-card-list">
            <p>支持 Hexo/Jekyll 文章导入并解析元数据</p>
            <button class="layui-btn layui-btn-radius  layui-btn-sm layui-inline" id="open_upload">上传</button>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div id="file_upload_div" class="text-center" style="display: none">
      <div class="layui-upload-drag" id="file_upload" style="width: 80%">
          <i class="layui-icon"></i>
          <p>点击上传，或将文件拖拽到此处</p>
          <p>仅支持选择单一文件</p>
      </div>
      <div class="mt-2 mb-2" id="file_name"></div>
  </div>
  <script src="../../static/admin/plugins/layui/layui/layui.js"></script>
  <script>
  layui.config({
    base: '../../static/admin/plugins/layui/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'sample','upload','jquery'],function () {
      var $ = layui.$
          , admin = layui.admin
          , element = layui.element
          , layer = layui.layer
          , upload = layui.upload;

    var up;
    $("#open_upload").click(function () {
         up= layer.open({
          type: 1,
          offset: 'auto',
          //area: 'auto',
          area: ["400px", "300px"],
          title: "上传文件",
          content: $("#file_upload_div")
        })
      });
    //执行实例
    var uploadInst = upload.render({
      elem: '#file_upload' //绑定元素
      //, auto: false
      //, bindAction: "#upload"
      , accept: 'file'
      , exts: 'md' //只允许上传压缩文件
      , url: './upload/file' //上传接口
      , done: function (res) {
        layer.msg("上传成功",{time: 1000}, function () {
          layer.closeAll();
        });

      }
      , error: function () {
        layer.msg("上传失败，请重新上传")
      }
    })



      //拖拽上传
      /* upload.render({
          elem: '#file_upload'
          , url: './upload/file'
          , accept: 'file'
          , exts: 'md' //只允许上传压缩文件
          , done: function (res) {
              console.log(res)
          }
      });*/


     /* $("#open_upload").click(function () {
        //alert("我出来了")
          layer.open({
              title: '页面层'
              , type: 1
              //,skin: 'layui-layer-rim'
              , shadeClose: false
              , area: 'auto'
              ,resize: false
              ,scrollbar: false //关闭滚动条
              , content: $("#uplod_file").html()
            ,yes: function (index, layero) {
            }

          });

          /!*top.layui.admin.popupRight({
              id: 'LAY_adminPopupLayerTest'
              ,area:'400px'
              ,content:$("#uplod_file").html()
              ,success: function(){
                  //$('#'+ this.id).html('<div style="padding: 20px;">放入内容</div>');
                  //$('#open_upload').html();

                  //admin.view(this.id).render('system/xxx')
                  //top.layui.view(this.id).render('../../../admin/article/toListForm');
              }
          });*!/
          //$("#open_upload").css("display","block");
      });*/
  })
  </script>
</body>
</html>


