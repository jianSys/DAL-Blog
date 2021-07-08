layui.config({
    base: '../../../static/admin/plugins/layui/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
}).use(['index', 'contlist', 'table'], function () {

    var table = layui.table;
    //渲染表格,发送请求
    table.render({
        elem: '#LAY-app-content-tags',
        url: 'categoryList',
        cellMinWidth: 80,
        page: true,
        limits: [10, 15, 20, 25],
        limit: 10,
        cols: [[
            //渲染表头
            {type: "numbers", title: '序号', fixed: "left", width: 50},
            {field: 'categoryId', title: 'Id', minWidth: 30, align: "center"},
            {field: 'categoryName', title: '标签分类', align: 'center'},
            {title: '操作', minWidth: 175, templet: '#layuiadmin-app-cont-tagsbar', fixed: "right", align: "center"}
        ]]
    });


    table.on('tool(LAY-app-content-list)', function (obj) {
        var id = obj.data.blogId;
        var data = obj.data;
        console.log("选中的id为========" + id);
        console.log("操作为=========" + obj.event)
        if (obj.event === 'edit') {
            layer.open({
                type: 2,
                content: 'toArticleEdit',
                area: ['500px', '500px'],
                title: "更改信息",
                //fixed: false, //不固定
                maxmin: true,
                shadeClose: false,
                btn: ['修改', '取消'],

            })
        }else{

        }
    });

    var $ = layui.$, active = {
        add: function () {
            layer.open({
                type: 2
                , title: '添加分类'
                , content: 'toCategoryEdit'
                , maxmin: true
                , area: ['550px', '400px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    var othis = layero.find('iframe').contents().find("#layuiadmin-app-form-tags")
                        , tags = othis.find('input[name="tags"]').val();

                    if (!tags.replace(/\s/g, '')) return;

                    table.reload('LAY-app-content-tags');
                    layer.close(index);
                }
            });
        }
    }
    $('.layui-btn.layuiadmin-btn-tags').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});