$index = {
    data: {
        page: 0,
        over: false,
        list: $('#indexList'),
        more: $('.loadmore')
    },
    init: function(){
        this.bind();
        this.loadList();
    },
    bind: function(){
        var that = this;
        // 点击加载更多
        that.data.more.click(function(){
            if(!that.data.over){
                that.loadList();
            }
        });
    },
    loadList(){
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
            ok: function(res){
                // 渲染数据
                that.listShow(res.data);
                if(that.data.page >= res.pageCount){
                    that.data.over = true;
                    that.data.more.html('已加载全部');
                }else{
                    that.data.more.html('加载更多&gt;&gt;');
                }
            }
        });
    },
    listShow: function(list){
        var html = '';
        // 遍历数据
        for(var i=0;i<list.length;i++){
            html += this.listItem(list[i]);
        }
        this.data.list.append(html);
    },
    listItem: function(info){
        // 显示数据页面
        var html = '';
        html += '<div class="comment">';
        html += '    <div class="peo_info">';
        html += '        <div class="peo_desc">';
        html += '            <img src="'+info.usericon+'" class="comment_logo" />';
        html += '            <ul>';
        html += '                <li class="base_comment"><a href="#">'+info.username+'</a> • '+info.time+'</li>';
        html += '                <li class="title_comment"><a href="./detail.html?id='+info.id+'">'+info.title+'</a></li>';
        html += '            </ul>';
        html += '        </div>';
        html += '    </div>';
        html += '    <div class="comment_content">';
        html += '       <div>';
        html += '            <img src="'+info.image+'" class="content_logo" />';
        html += '       </div>';
        html += '        <div class="content_area">';
        html += '            <p class="ellipsis3">'+info.miaoshu+'...</p>';
        html += '            <p><a href="./detail.html?id='+info.id+'" class="show_all">查看详情</a></p>';
        html += '        </div>';
        html += '        <div class="content_bottom">';
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
        html += '        </div>';
        html += '    </div>';
        html += '</div>';
        return html;
    }

};
$index.init();