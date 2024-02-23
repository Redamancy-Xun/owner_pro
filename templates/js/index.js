//头像获取
let headProtrait = localStorage.getItem('sessionData.headprotrait');
if (headProtrait) {
    let img = document.getElementsByClassName('logo');
    img.src = 'http://116.62.103.210/image/' + headProtrait;
}
let role = localStorage.getItem('sessionData.role');

let userName = localStorage.getItem('sessionData.username');
let Name = document.querySelector('.name');
if (userName) {
    Name.innerHTML = userName;
}
//筛选组件对应逻辑
let selectedDisplayType = document.getElementById('selectDisplayType');
let selectedDisplayDirection = document.getElementById('selectDisplayDirection');
let selectedDisplayTag = document.getElementById('selectDisplayTag');

function addSelectEventListener(customSelect, selectOptions, selectedOptions, selectedDisplay) {
    customSelect.querySelector('p').addEventListener('click', function () {
        this.nextElementSibling.style.display = 'block';
    });

    customSelect.addEventListener('mouseleave', function () {
        this.querySelector('ul').style.display = 'none';
    });

    function updateSelectedOptionsDisplay() {
        selectedDisplay.textContent = selectedOptions.join(", "); //使用逗号分隔已选中的选项
    }

    selectOptions.forEach(function (option) {
        option.addEventListener('click', function () {
            if (this.classList.contains('selected')) {
                this.classList.remove('selected');
                //从已选中选项数组中移除取消选中的选项
                let index = selectedOptions.indexOf(this.textContent);
                if (index > -1) {
                    selectedOptions.splice(index, 1);
                }
            } else {
                this.classList.add('selected');
                //将选中的选项加入选项数组
                selectedOptions.push(this.textContent);
            }

            updateSelectedOptionsDisplay(); //更新显示框内容

        });
    });
}

let customSelectType = document.getElementById("selectType");
let selectOptionsType = customSelectType.querySelectorAll('li');
let selectedOptionsType = [];
addSelectEventListener(customSelectType, selectOptionsType, selectedOptionsType, selectedDisplayType);

let customSelectDirection = document.getElementById("selectDirection");
let selectOptionsDirection = customSelectDirection.querySelectorAll('li');
let selectedOptionsDirection = [];
addSelectEventListener(customSelectDirection, selectOptionsDirection, selectedOptionsDirection, selectedDisplayDirection);

let customSelectTag = document.getElementById("selectTag");
let selectOptionsTag = customSelectTag.querySelectorAll('li');
let selectedOptionsTag = [];
addSelectEventListener(customSelectTag, selectOptionsTag, selectedOptionsTag, selectedDisplayTag);


//显示框显示选项
let selectedOptionsDisplay = document.getElementById('selectedOptionsDisplay');

let type = selectedOptionsType;
let direction = selectedOptionsDirection;
let tag = selectedOptionsTag;

//上面三个变量存储对应值，只有筛选未完成就算了
// console.log(type);
// console.log(direction);
// console.log(tag);
//需要在这里面补充发送请求的逻辑，建议把这里的js都移动到index里面，避免混淆;


document.getElementById('create-form').addEventListener('submit', function (event) {
    event.preventDefault(); //阻止表单默认提交行为

    $index.loadList();

    console.log(type);
    console.log(direction);
    console.log(tag);
})




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
        let a={
            pageSize: 10,
            pageNum: that.data.page,
            ...(type.length > 0 && { type: type }),
            ...(direction.length > 0 && { direction: direction }),
            ...(tag.length > 0 && { tag: tag })
        }
        jsondata=JSON.stringify(a);
        // 获取列表数据
        $common.getHttp({
            url: 'article',
            data: jsondata,
            ok: function (res) {
                // 渲染数据
                that.listShow(res.result.items);
                if (that.data.page >= res.result.pages) {
                    that.data.over = true;
                    that.data.more.html('已加载全部');
                } else {
                    that.data.more.html('加载更多&gt;&gt;');
                }

                console.log(res);
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
        let role = JSON.parse(localStorage.getItem("sessionData")).role;
        var html = '';
        html += '<div class="comment">';
        html += '    <div class="peo_info">';
        html += '        <div class="peo_desc">';
        html += '            <ul>';
        html += '                <li class="base_comment"><em>' + info.type.join('</em><em>') + '</em><span>' + info.update_date.substring(0, 10) + '</span></li>';
        html += '                <li class="title_comment">' + (info.top ? '<em class="top">置顶</em>' : '') + '<em class="finishe">' + (info.finishe ? '已' : '未') + '完成</em>' + info.direction.join(' • ') + ' • ' + info.tag.join(' • ') + '</li>';
        html += '            </ul>';
        html += '        </div>';
        html += '    </div>';
        html += '    <div class="comment_content">';
        html += '        <div class="content_area">';
        html += '            <p class="ellipsis3">' + info.content + '<a href="./detail.html?id=' + info.article_id + '" class="show_all">...</a></p>';
        html += '            <p><em>开始时间：</em>' + info.start_time.substring(0, 10) + '</p>';
        html += '            <p><em>结束时间：</em>' + info.end_time.substring(0, 10) + '</p>';
        html += '            <p><em>联系方式：</em>' + info.contact + '</p>';
        html += '            <p><a href="./detail.html?id=' + info.article_id + '" class="show_all">查看详情</a></p>';
        if (role) {
            html += '<button class="delete-button" onclick="deletePost(this)"> < img src="./image/delete.jpeg" alt = "删除" > </button >'
            html += ' <button  class="sticky-button"><img src="./image/up.jpeg" alt="置顶"</button>'
        }
        function deletePost(button) {
            let post = button.closest('.comment')
            $.ajax({
                url: 'http://116.62.103.210:8080/deleteArticle/${info.article_id}',
                type: 'GET',
                contentType: 'application/json',
                data: info.article_id,
                success: function () {
                    // 请求成功后刷新页面
                    location.reload();
                },
                error: function () {
                    console.error('删除请求出错');
                }
            });
        }
        $('.sticky-button').on('click', function () {
            let currentTopValue = info.top;
            const newTopValue = currentTopValue === 0 ? 1 : 0;
            if (newTopValue === 1) {
                $.ajax({
                    url: 'http://116.62.103.210:8080/topArticle/${info.article_id}',
                    type: 'GET',
                    contentType: 'application/json',
                    data: info.article_id,
                    success: function () {
                        // 请求成功后更新当前 top 值
                        currentTopValue = newTopValue;
                        // 请求成功后刷新页面
                        location.reload();
                    },
                    error: function () {
                        console.error('置顶请求出错');
                    }
                });
            }
            if (newTopValue === 0) {
                $.ajax({
                    url: 'http://116.62.103.210:8080/untopArticle/${info.article_id}',
                    type: 'GET',
                    contentType: 'application/json',
                    data: info.article_id,
                    success: function () {
                        // 请求成功后更新当前 top 值
                        currentTopValue = newTopValue;
                        // 请求成功后刷新页面
                        location.reload();
                    },
                    error: function () {
                        console.error('置顶请求出错');
                    }
                });
            }
            // 发送改变top值的请求

        });
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