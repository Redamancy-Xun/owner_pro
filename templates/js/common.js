$common = {
    data: {
        site: 'http://116.62.103.210:8080/',
        relogin: false
    },
    init: function(){
        //this.userLogin();
    },
    userLogin: function(cb){
        this.getHttp({
            url: 'login?username=123456&password=123456',
            type: 'post',
            ok: function(res){
                if (res.code == 0) { // 判断状态码是否为0
                    var cookies = document.cookie;
                    console.log(cookies)
                    typeof cb == 'function' && cb();
                } else {
                    alert(res.message);
                }
            }
        });
    },
    getHttp: function(obj){
        var that = this;
        $.ajax({
            type: obj.type || 'post',
            url: this.data.site + obj.url,
            data: obj.data || {},
            dataType: 'json',
            contentType: 'application/json',
            crossDomain: true,
            xhrFields: { withCredentials: true },
            success: function (data) { // 获取数据成功
                if (data.code == 0) { // 判断状态码是否为0
                    typeof obj.ok == 'function' && obj.ok(data); // 执行回调方法，并返回数据
                } else if(!that.data.relogin && data.code == 1){
                    that.data.relogin = true;
                    that.userLogin(function(){
                        that.getHttp(obj);
                    });
                } else {
                    alert('获取数据失败');
                }
            },
            error: function (data) { // 获取数据失败
                alert('网络请求错误');
            }
        });
    },
    getQuery: function(name) {
        var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return unescape(r[2]);
        }
        return '';
    }
}
$common.init();