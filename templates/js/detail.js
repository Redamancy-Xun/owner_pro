$index = {
    data: {
        id: 0,
        html: $('#content')
    },
    init: function () {
        this.bind();
        this.loadList();
    },
    bind: function () {
        var that = this;
        that.data.id = $common.getQuery('id');
        console.log('id', that.data.id)
    },
    loadList() {
        var that = this;
        // 获取详情数据
        $common.getHttp({
            url: './json/detail.json',
            data: {
                id: that.data.id
            },
            ok: function (res) {
                // 渲染数据
                that.infoShow(res.data);
            }
        });
    },
    infoShow: function (info) {
        // 显示数据页面
        document.title = info.title;
        var html = '';
        html += '<div class="comment">';
        html += '    <div class="peo_info">';
        html += '        <div class="peo_desc">';
        html += '            <img src="' + info.usericon + '" class="comment_logo" />';
        html += '            <ul>';
        html += '                <li class="base_comment"><a href="#">' + info.username + '</a> • ' + info.time + '</li>';
        html += '                <li class="title_comment">' + info.title + '</li>';
        html += '            </ul>';
        html += '        </div>';
        html += '    </div>';
        html += '    <div class="comment_content">';
        html += '        <div class="content_detail">' + info.content + '</div>';
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
        this.data.html.html(html);
    }

};
$index.init();