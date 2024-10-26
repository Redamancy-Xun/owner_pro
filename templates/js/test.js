$(document).ready(() => {
    $('button').click(() => {
        $.ajax({
            type: "GET",
            url: "http://116.62.103.210:8080/userinfo",
            data: {
                pageSize: 10,
                pageNum: 1
            },
            dataType: "json",
            contentType: 'application/json',
            crossDomain: true,
            xhrFields: { withCredentials: true },
            success: function (data) {
                console.log(data)
            },
            error: function (xhr, status, error) {
                console.error(error)
            }
        });

    })
})



