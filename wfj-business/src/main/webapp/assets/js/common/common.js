var config = {
    basePath: $("#ctx").text()
}

var opt = {
    confirm: function (url, msg) {
        var msg = msg ? msg : "你确认删除该记录吗？";
        layer.confirm(msg, function (index) {
            window.location = url;
        });
    },

    dialog: function (message, messageType) {
        if (message != '') {
            if (messageType == '1') {
                layer.msg(message, {icon: 1});
            } else {
                layer.alert(message, {icon: 2});
            }
        }
    },

    openWin: function (url, title, width, height) {
        var title = title ? title : false;
        layer.open({
            type: 2,
            title: title,
            maxmin: true,
            shadeClose: true, //点击遮罩关闭层
            area: [width + "px", height + "px"],
            content: url
        });
    },

    closeWin: function (refresh) {
        var index = parent.layer.getFrameIndex(window.name);
        if (refresh) {
            parent.location.reload();
        }
        parent.layer.close(index); //执行关闭
    }
}

var ajax = {
    get: function (url, param, successCallback, errorCallback) {
        if (typeof param == 'function') {
            errorCallback = successCallback;
            successCallback = param;
            param = {};
        }

        $.ajax({
            url: url,
            contentType: "application/json; charset=utf-8",
            type: "GET",
            timeout: 120000,//超时时间设定
            data: param,
            dataType: "json",
            success: function (data) {
                console.log(data);
                var status = data.status;
                var status = data.status;
                if (status === 500) {
                    alert(data.message);
                }
                else if (status === 208) {
                    console.log('用户尚未登录，请重新登录后在操作.');
                    //alert('用户尚未登录，请重新登录后在操作.');
                    //如果是登录页面
                    window.location.href = config.basePath + '/acl/login';
                }

                if (status === 200) {
                    if (typeof successCallback == 'function') {
                        successCallback(data);
                    }
                }

                if (typeof errorCallback == 'function' && status != 200) {
                    errorCallback(data);
                }

            },
            error: function (data) {
                console.log(data);
                console.log('服务器繁忙,请稍后!');
                if (typeof errorCallback == 'function') {
                    errorCallback(data);
                }
            }
        });
    },

    post: function (url, param, successCallback, errorCallback) {
        if (typeof param == 'function') {
            errorCallback = successCallback;
            successCallback = param;
            param = {};
        }

        $.ajax({
            url: url,
            contentType: "application/json; charset=utf-8",
            type: "POST",
            timeout: 20000,//超时时间设定
            data: JSON.stringify(param),
            dataType: "json",
            success: function (data) {
                console.log(data);
                var status = data.status;
                if (status === 500) {
                    console.log(data.message);
                } else if (status === 208) {
                    console.log('用户尚未登录，请重新登录后在操作.');
                    window.location.href = config.basePath + '/acl/login';
                }

                if (status === 200) {
                    if (typeof successCallback == 'function') {
                        successCallback(data);
                    }
                }

                if (typeof errorCallback == 'function' && status != 200) {

                    errorCallback(data);
                }
            },
            error: function (data) {
                console.log(data);
                console.log('服务器繁忙,请稍后!');
                if (typeof errorCallback == 'function') {
                    errorCallback(data);
                }
            }
        });
    }
};


function RequestObjectSerializer(object) {

    var returnString = "";

    for (var field in object) {

        if(object[field]!=undefined) {
            returnString += "&" + field + "=" + object[field];
        }

    }

    returnString = "?" + returnString.substring(1);

    return returnString;

}



