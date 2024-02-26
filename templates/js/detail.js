// 从 localStorage 中获取 sessionData 字符串
let sessionDataString = localStorage.getItem('sessionData');
// 将 JSON 字符串解析为对象
let sessionData = JSON.parse(sessionDataString);
// 读取 headportrait和username 数据
let headPortraitData = sessionData.headportrait;
let usernameData = sessionData.username;
//选中导航栏头像和用户名
let headportraitDiv = document.getElementsByClassName('logo')[0];
let usernameDiv = document.getElementsByClassName('name')[0];
headportraitDiv.src = 'http://116.62.103.210/image/' + headPortraitData;
usernameDiv.innerHTML = usernameData;
let role = localStorage.getItem('sessionData.role');
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
            url: 'articleDetail/' + that.data.id,
            ok: function (res) {
                // 渲染数据
                that.infoShow(res.result);
            },
            type:"get"
        });
    },
    infoShow: function (info) {
        // 显示数据页面
        var html = '';
        html += '<div class="comment">';
        html += '    <div class="peo_info">';
        html += '        <div class="peo_desc">';
        html += '            <ul>';
        html += '                <li class="base_comment"><em>' + info.type.join('</em><em>') + '</em><span>' + info.update_date.slice(0,10) + '</span></li>';
        html += '                <li class="title_comment">' + (info.top ? '<em class="top">置顶</em>' : '') + '<em class="finishe">' + (info.finish ? '已' : '未') + '完成</em>' + info.direction.join(' • ') + ' • ' + info.tag.join(' • ') + '</li>';
        html += '            </ul>';
        html += '        </div>';
        html += '    </div>';
        html += '    <div class="comment_content">';
        html += '        <div class="content_area">';
        html += '            <p class="ellipsis3">' + info.content + '</p>';
        html += '            <p>开始时间：' + info.start_time + '</p>';
        html += '            <p>结束时间：' + info.end_time + '</p>';
        html += '            <p>联系方式：' + info.contact + '</p>';
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
        this.data.html.html(html);
    }

};
$index.init();