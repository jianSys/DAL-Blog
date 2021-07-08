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

        <div class="layui-card-body">

            <table id="LAY-app-content-list" lay-filter="LAY-app-content-list">

            </table>

            <script type="text/html" id="buttonTpl">
                {{#  if(d.blogStatus){ }}
                <button class="layui-btn layui-btn-xs">已发布</button>
                {{#  } else { }}
                <button class="layui-btn layui-btn-primary layui-btn-xs">待修改</button>
                {{#  } }}
            </script>

            <script type="text/html" id="table-content-list">
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i
                            class="layui-icon layui-icon-edit"></i>编辑</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i
                            class="layui-icon layui-icon-delete"></i>删除</a>
                <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail"><i
                            class="layui-icon layui-icon-read"></i>查看</a>
            </script>
        </div>
    </div>
</div>

<script src="../../../static/admin/plugins/layui/layui/layui.js"></script>
<script>
    layui.config({
        base: '../../../static/admin/plugins/layui/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'contlist', 'table'], function () {
        var table = layui.table
            , $ = layui.jquery
            , form = layui.form;
        console.log("渲染表格开始")
        table.render({
            elem: '#LAY-app-content-list',
            url: 'getPageList',
            cellMinWidth: 80,
            page: false,
            // limits: [10, 15, 20, 25],
            // limit: 10,
            limit:10,
            cols: [[
                 {field: 'blogId', title: 'id', minWidth: 30, align: "center",hide:true},
                {field: 'blogTitle', title: '页面名称', align: 'center'},
                {field: 'blogSubUrl', title: '访问地址', align: 'center'},
                {title: '状态', minWidth: 30, templet: "#buttonTpl", fixed: "right", align: 'center'},
                {title: '操作', minWidth: 175, templet: '#table-content-list', fixed: "right", align: "center"}
            ]],
        });
        //监听编辑或者删除
        console.log("监听表格时间")
        table.on('tool(LAY-app-content-list)', function (obj) {
            var id = obj.data.blogId;
            var data = obj.data;
            console.log("选中的id为========" + id);
            console.log("操作为=========" + obj.event)
            if (obj.event === 'del') {
                layer.confirm("确定要删除此文章吗?", function (index) {
                    var index = layer.load(1);
                    $.ajax({
                        type: 'delete',//方法类型
                        url: "delPage/" + id,
                        //data: JSON.stringify(data),
                        dataType: "json",
                        contentType: "application/json;charset=utf-8",
                        success: function (res) {
                            if (res.code === 0) {
                                table.reload('LAY-app-content-list');
                                layer.msg('已删除');
                                layer.close(index);
                            } else {
                                table.reload('LAY-app-content-list');
                            }
                        }
                    });

                })
            } else if (obj.event === 'edit') {
                console.log("我是编辑页面获取到的data==================" + JSON.stringify(obj.data));
                json = JSON.stringify(data);
                var arr = [];
                layer.open({
                    type: 2,
                    content: 'toArticleAdd',
                    area: ['500px', '500px'],
                    title: "更改信息",
                    //fixed: false, //不固定
                    maxmin: true,
                    shadeClose: false,
                    btn: ['修改', '取消'],
                    yes: function (index, layero) {
                        var othis = layero.find('iframe').contents().find("#blogArticleForm"),
                            photoUrl = othis.find('#photoUrl').val(),
                            comments = othis.find('input[name="comments"]:checked').val(),
                            status = othis.find('input[name="status"]:checked').val(),
                            tags = othis.find('input[name="tags"]:checked').each(function () {
                                arr.push($(this).val());
                            }),
                            blogId = othis.find('input[name="id"]').val(),

                            title = othis.find('#title').val(),
                            content = othis.find('.editormd-markdown-textarea').val(),
                            introduce = othis.find("#introduce").val(),
                            top = othis.find('input[name="top"]:checked').val(),
                            category = othis.find('#category').val();
                        //alert("这是id================"+blogId);
                        console.log("组装请求数据开始");

                        if (title == '') {
                            layer.msg('标题不能为空', function () {
                                time:2000
                            });
                            return;
                        }
                        if (content == '') {
                            layer.msg('内容不能为空', function () {
                                time:2000
                            });
                            return;
                        }
                        //alert("开关按钮"+status);
                        var data = {
                            "blogId": blogId,
                            "blogTitle": title,
                            "blogCategoryId": category,
                            "blogCoverImage": photoUrl,
                            "blogContent": content,
                            "blogTags": arr.toString(),
                            "blogStatus": status,//=== "on" ? 1 : 0
                            "enableComment": comments,
                            "blogIntroduce":introduce,
                            "blogTop": top,
                            "blogSubUrl": obj.data.blogSubUrl === "" ? "" : obj.data.blogSubUrl,
                            "blogViews": obj.data.blogViews === 0 ? 0 : obj.data.blogViews,
                            "createTime": obj.data.createTime === "" ? "" : obj.data.createTime,
                            "isDeleted": obj.data.isDeleted === 0 ? 0 : obj.data.isDeleted,
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
                                    layer.msg('修改成功', {time: 1000}, function () {
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
                        //表单值
                        form.val("layuiadmin-app-form-list", {
                            "blogTitle": data.blogTitle
                        });

                    }
                });
            }
        });
        //监听搜索
        form.on('submit(LAY-app-contlist-search)', function (data) {
            var field = data.field;

            //执行重载
            table.reload('LAY-app-content-list', {
                where: field
            });
        });

        var $ = layui.$, active = {
            batchdel: function () {
                var checkStatus = table.checkStatus('LAY-app-content-list')
                    , checkData = checkStatus.data; //得到选中的数据

                if (checkData.length === 0) {
                    return layer.msg('请选择数据');
                }

                layer.confirm('确定删除吗？', function (index) {
                    //执行 Ajax 后重载
                    alert(index);
                    admin.req({
                        url: 'delBlog',
                        method: 'POST',
                        dataType: 'json',
                        data: {"id": data.blogId},
                        done: function (res) {
                            layer.msg(res);
                        }
                    });
                    table.reload('LAY-app-content-list');
                    layer.msg('已删除');
                });
            },
            add: function () {
                window.location.href = 'toArticleEdit'
            }

        };

        $('.layui-btn.layuiadmin-btn-list').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>
</body>
</html>
