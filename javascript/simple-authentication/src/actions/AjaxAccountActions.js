import $ from 'jquery';

export function register(userDetails, callback) {
    $.ajax({
            type: "PUT",
            dataType: "json",
            url: "http://localhost:8080/rest/account",
            data: JSON.stringify(userDetails),
            success: function (data) {
                console.log(data);
                callback(data);
            },
            error: function (data) {
                console.log(JSON.parse(data.responseText));
                callback(null);
            }
        }
    )
}

export function login(userDetails, callback) {
    $.ajax({
            type: "POST",
            dataType: "json",
            url: "http://localhost:8080/rest/account",
            data: JSON.stringify(userDetails),
            success: function (data) {
                console.log(data);
                callback(data);
            },
            error: function (data) {
                console.log(JSON.parse(data.responseText));
                callback(null);
            }
        }
    )
}

export function logout(callback) {
    $.ajax({
        type: "DELETE",
        dataType: "json",
        url: "http://localhost:8080/rest/account",
        success: function(data) {
            console.log(data);
            callback();
        },
        error: function(data) {
            console.log(JSON.parse(data.responseText));
            callback();
        }
    })
}