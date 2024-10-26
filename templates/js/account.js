
//准备提交创作中心的页数和一页显示数

let pageSize = 3;
let pageNum = 1;


function creatcentre() {//获取个人信息，以及创作中心请求展示帖子
    $.ajax({
        type: "GET",
        url: "http://116.62.103.210:8080/useInfo",
        data: {
            pageSize: 3,
            pageNum: 1
        },
        dataType: "json",
        contentType: 'application/json',
        crossDomain: true,
        xhrFields: { withCredentials: true },
        success: function (data) {
            console.log(data)
            let username = document.getElementById('username');
            let password = document.getElementById('password');
            let studentid = document.getElementById('studentid');
            let studentname = document.getElementById('studentname');
            let email = document.getElementById('email');
            let birthday = document.getElementById('birthday');
            //展示个人信息
            username.value = data.result[1].username
            password.value = data.result[1].password
            studentid.value = data.result[1].studentid
            studentname.value = data.result[1].studentname
            email.value = data.result[1].email
            birthday.value = data.result[1].birthday

            //分别提取items中的三组帖子的数据
            let firstData = data.result[1].items[0];
            let secondData = data.result[1].items[1];
            let thirdData = data.result[1].items[2];
            //分别选取三个帖子的展示框
            let firstDiv = document.getElementById('article1');
            let secondDiv = document.getElementById('article2');
            let thirdDiv = document.getElementById('article3');
            //在各自展示框中显示帖子数据
            firstDiv.innerHTML = `
                        <p class="zhuti">${firstData.content}</p>
                        <p>开始时间: ${firstData.start_time}  截止时间: ${firstData.end_time}</p>
                        <p>任务类型：${firstData.type}  需求方向：${firstData.direction}  技术栈：${firstData.tag}</p>
                        <p>联系方式：${firstData.contact}</p>
                        <p>完成状态：${firstData.finish}</p>
                        <p>上次更新时间：${firstData.update_date}</p>
                        `;
            secondDiv.innerHTML = `
                        <p class="zhuti">${secondData.content}</p>
                        <p>开始时间: ${secondData.start_time}  截止时间: ${secondData.end_time}</p>
                        <p>任务类型：${secondData.type}  需求方向：${secondData.direction}  技术栈：${secondData.tag}</p>
                        <p>联系方式：${secondData.contact}</p>
                        <p>完成状态：${secondData.finish}</p>
                        <p>上次更新时间：${secondData.update_date}</p>
                        `;
            thirdDiv.innerHTML = `
                        <p class="zhuti">${thirdData.content}</p>
                        <p>开始时间: ${thirdData.start_time}  截止时间: ${thirdData.end_time}</p>
                        <p>任务类型：${thirdData.type}  需求方向：${thirdData.direction}  技术栈：${thirdData.tag}</p>
                        <p>联系方式：${thirdData.contact}</p>
                        <p>完成状态：${thirdData.finish}</p>
                        <p>上次更新时间：${thirdData.update_date}</p>
                        `;
        },
        error: function (error) {
            console.error(error)
        }
    });
}

//选取个人信息的各板块


// 在页面加载完毕时执行函数
window.onload = function () {
    creatcentre();
};
// 当上一页按钮被点击时再次执行函数
document.getElementById('lastp').addEventListener('click', function () {
    pageNum--;
    if (pageNum < 1) {//页码数不小于1
        pageNum = 1;
    }
    creatcentre();
});
// 当下一页按钮被点击时再次执行函数
document.getElementById('nextp').addEventListener('click', function () {
    pageNum++;
    if (pageNum > total) {//页码数不能超过页面总数
        pageNum = total;
    }
    creatcentre();
});

function toggleInput() {//切换表单可否填写的按钮 “我要修改”/“保存修改”
    let username = document.getElementById("username");
    if (username.disabled) {
        username.disabled = false;
        document.getElementById("change-btn").innerHTML = "保存修改";
    } else {
        username.disabled = true;
        document.getElementById("change-btn").innerHTML = "我要修改";
    }
    let password = document.getElementById("password");
    if (password.disabled) {
        password.disabled = false;
    } else {
        password.disabled = true;
    }
    let password2 = document.getElementById("password2");
    if (password2.disabled) {
        password2.disabled = false;
    } else {
        password2.disabled = true;
    }
    let email = document.getElementById("email");
    if (email.disabled) {
        email.disabled = false;
    } else {
        email.disabled = true;
    }
    let birthday = document.getElementById("birthday");
    if (birthday.disabled) {
        birthday.disabled = false;
    } else {
        birthday.disabled = true;
    }
    let submit = document.getElementById("submit");
    if (submit.disabled) {
        submit.disabled = false;
    } else {
        submit.disabled = true;
    }
}

document.getElementById('update-form').addEventListener('submit', function (event) {//“提交修改” 按钮
    event.preventDefault()//阻止默认提交
    //获取各参数信息
    let username = document.getElementById('username').value
    let password = document.getElementById('password').value
    let password2 = document.getElementById('password2').value

    console.log(username, password)//控制台查看

    //判断长度(暂定)
    let usernameInput = document.getElementById('username')
    if (username.length < 2 || username.length > 20) {
        console.log('用户名长度需为2~20位')
        usernameInput.style.borderColor = "red"
        usernameInput.style.backgroundColor = "pink";
        document.getElementById('feedbackMessage').innerText = '·用户名长度需为2~20位'
        return//阻止代码进行
    }
    else {
        usernameInput.style.borderColor = "black"
        usernameInput.style.backgroundColor = "white";
    }

    let passwordInput = document.getElementById('password')
    if (password.length < 6 || password.length > 20) {
        console.log('密码长度需为6~20位')
        passwordInput.style.borderColor = "red"
        passwordInput.style.backgroundColor = "pink";
        document.getElementById('feedbackMessage').innerText = '·密码长度需为6~20位'
        return//阻止代码进行
    }
    else {
        passwordInput.style.borderColor = "black"
        passwordInput.style.backgroundColor = "white";
    }

    let passwordInput2 = document.getElementById('password2')
    if (password != password2) {
        console.log('请确认两次密码输入一致')
        passwordInput.style.borderColor = "red"
        passwordInput.style.backgroundColor = "pink";
        passwordInput2.style.borderColor = "red"
        passwordInput2.style.backgroundColor = "pink";
        document.getElementById('feedbackMessage').innerText = '·请确认两次密码输入一致'
        return//阻止代码进行
    }
    else {
        passwordInput.style.borderColor = "black"
        passwordInput.style.backgroundColor = "white";
        passwordInput2.style.borderColor = "black"
        passwordInput2.style.backgroundColor = "white";
    }


    /* fetch('http://116.62.103.210:8080/update', {
         method: 'POST',
     body: new FormData(document.getElementById('update-form'))
             }) // 后端接口地址
                 .then(response => response.json())//转换json数据
                 .then(data => {
         console.log(data)// 打印 JSON 数据
                     alert(data.message)//打印message内容
                 })
                 .catch(error => {
         console.error('Error:', error)
                     alert('出错啦！请稍后再试。')
                 })
         })*/

    $.ajax({
        type: "POST",
        url: "http://116.62.103.210:8080/update",
        data: new FormData(document.getElementById('update-form')),
        dataType: "json",
        contentType: 'application/json',
        crossDomain: true,
        xhrFields: { withCredentials: true },
        success: function (data) {
            console.log(data)
            alert(data.message)
        },
        error: function (xhr, status, error) {
            console.error(error)
            alert('出错啦！请稍后再试。')
        }
    })
})