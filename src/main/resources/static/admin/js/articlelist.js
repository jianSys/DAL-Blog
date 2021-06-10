layui.config({
    base: '../../../static/layui/' //静态资源所在路径
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
            {type: "checkbox", fixed: "left", width: 50},
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
            {title: '操作', minWidth: 175, templet: '#table-content-list', fixed: "right", align: "center"}
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
                deleteArticle(id);
                layer.close(index);
            })
        } else if (obj.event === 'edit') {
            console.log(obj.data);
            json = JSON.stringify(data);
            layer.open({
                type: 2,
                content: 'toArticleAdd',
                area: ['500px', '500px'],
                title: "更改信息",
                //fixed: false, //不固定
                maxmin: true,
                shadeClose: false,
                //btn: ['修改', '取消'],

            });
            //表单值
            form.val("layuiadmin-app-form-list", {
                "blogTitle": data.blogTitle
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


    //删除列
    function deleteArticle(blogId) {
        $.ajax({
            type: 'delete',//方法类型
            url: "delArticle/" + blogId,
            //data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            success: function (res) {
                if (res.code === 0) {
                    table.reload('LAY-app-content-list');
                    layer.msg('已删除');
                } else {
                    table.reload('LAY-app-content-list');
                }
            }
        })
    }
});