$(document).ready(function () {
    var $btn = $('.articleControlBtn')
    $btn.on('click', controlByBtn);
    var url = $btn.attr('href');
    var type = $btn.attr('id');
    function controlByBtn(e) {
        e.preventDefault();
        $.ajax({
            type: type,
            url: url,
            error:function(data) {
                console.log("error exeucute!");
            },
            success: function(data) {
                location.href = data;
            }
        });
    }
});


