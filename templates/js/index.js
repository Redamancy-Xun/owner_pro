$index = {
    data: {
        page: 0,
        over: false,
        list: $('#indexList'),
        more: $('.loadmore')
    },
    init: function () {
        this.bind();
        this.loadList();
    },
    bind: function () {
        var that = this;
        // 点击加载更多
        that.data.more.click(function () {
            if (!that.data.over) {
                that.loadList();
            }
        });
    },
    loadList() {
        var that = this;
        that.data.page++;
        that.data.more.html('加载中...');
        // 获取列表数据
        $common.getHttp({
            url: 'article',
            data: {
                pageSize: 10,
                pageNum: that.data.page


            },
            ok: function (res) {
                // 渲染数据
                that.listShow(res.result.items);
                if (that.data.page >= res.result.pages) {
                    that.data.over = true;
                    that.data.more.html('已加载全部');
                } else {
                    that.data.more.html('加载更多&gt;&gt;');
                }
            }
        });
    },
    listShow: function (list) {
        var html = '';
        // 遍历数据
        for (var i = 0; i < list.length; i++) {
            html += this.listItem(list[i]);
        }
        this.data.list.append(html);
    },
    listItem: function (info) {
        // 显示数据页面
        var html = '';
        html += '<div class="recruitmentPost">';
        html += '    <div class="profile">';
        html += '            <ul>';
        html += '                <li class="base_comment"><em>' + info.type.join('</em><em>') + '</em><span>' + info.update_date + '</span></li>';
        html += '                <li class="title_comment">' + (info.top ? '<em class="top">置顶</em>' : '') + '<em class="finishe">' + (info.finishe ? '已' : '未') + '完成</em></li>';
        html += '                <li class="direction">' + info.direction.join(' • ') + ' ' + info.tag.join(' • ') + '</li>';
        html += '            </ul>';
        html += '    </div>';
        html += '    <div class="detailContent">';
        html += '        <div class="contentArea">';
        html += '            <p class="postContent">' + info.content + '<a href=""<a href="./detail.html?id=' + info.article_id + '" class="show_all">...</a></p>';
        html += '            <p class="startTime">开始时间：' + info.start_time + '</p>';
        html += '            <p class="endTime">结束时间：' + info.end_time + '</p>';
        html += '            <p class=">联系方式：' + info.contact + '</p>';
        html += '            <p><a href="./detail.html?id=' + info.article_id + '" class="show_all">查看详情</a></p>';
        html += '        </div>';
        /*html += '        <div class="content_bottom">';
        html += '            <ul>';
        html += '                <li class="content_item">';
        html += '                    <a href="#"> <i class="sprite sprite-love"></i>喜欢</a>';
        html += '                </li>';
        html += '                <li class="content_item">';
        html += '                    <a href="#"> <i class="sprite sprite-share"></i>分享</a>';
        html += '                </li>';
        html += '                <li class="content_item">';
        html += '                    <a href="#"> <i class="sprite sprite-mark"></i>收藏</a>';
        html += '                </li>';
        html += '            </ul>';
        html += '        </div>';*/
        html += '    </div>';
        html += '</div>';
        return html;
    }

};
$index.init();