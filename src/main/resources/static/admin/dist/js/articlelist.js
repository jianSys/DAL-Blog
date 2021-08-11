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
        url: 'articleList',
        cellMinWidth: 80,
        page: true,
        limits: [10, 15, 20, 25],
        limit: 10,
        cols: [[
            //渲染表头
            {type: "checkbox", fixed: "left", width: 30},
            {field: 'blogId', title: 'id', minWidth: 30, align: "center"},
            {field: 'blogTitle', title: '文章标题', align: 'center'},
            {field: 'blogCategoryName', title: '文章分类', align: 'center'},
            {field: 'blogTags', title: '文章标签', align: 'center'},
            {
                field: 'createTime',
                title: '发布时间',
                templet: '<div>{{layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss")}}</div>',
                align: 'center',
                minWidth: 150
            },
            {title: '置顶', minWidth: 30, templet: "#buttonTop", fixed: "right", align: 'center'},
            {title: '状态', minWidth: 30, templet: "#buttonTpl", fixed: "right", align: 'center'},
            {title: '删除', minWidth: 30, templet: "#buttonDel", fixed: "right", align: 'center'},
            {title: '操作', minWidth: 30, templet: '#table-content-list', fixed: "right", align: "center"}
        ]]
        //data:data
        //id:'article'
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
                    url: "delArticle/" + id,
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