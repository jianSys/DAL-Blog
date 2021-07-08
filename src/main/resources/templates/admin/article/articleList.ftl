<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>博客|文章列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="../../../static/admin/plugins/layui/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="../../../static/admin/plugins/layui/style/admin.css" media="all">
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
                <div class="layui-inline">
                    <label class="layui-form-label">标题</label>
                    <div class="layui-input-inline">
                        <input type="text" name="title" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">分类</label>
                    <div class="layui-input-inline">
                        <input type="text" name="blogCategoryName" placeholder="请输入" autocomplete="off"
                               class="layui-input">
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
            <script type="text/html" id="buttonTop">
                {{#  if(d.blogTop){ }}
                <button class="layui-btn layui-btn-xs">已置顶</button>
                {{#  } else { }}
                <button class="layui-btn layui-btn-primary layui-btn-xs">未置顶</button>
                {{#  } }}
            </script>
            <script type="text/html" id="table-content-list">
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i
                            class="layui-icon layui-icon-edit"></i>编辑</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i
                            class="layui-icon layui-icon-delete"></i>删除</a>
            </script>
        </div>
    </div>
</div>

<script src="../../../static/admin/plugins/layui/layui/layui.js"></script>
<script src="../../../static/admin/dist/js/articlelist.js"></script>
</body>
</html>
