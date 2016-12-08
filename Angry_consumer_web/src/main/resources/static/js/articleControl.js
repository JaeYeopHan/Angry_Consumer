$(document).ready(function () {
    var $deleteBtn = $('#deleteBtn')
    $deleteBtn.on('click', controlByBtn);
    var $agreeBtn = $('.agree-btn');
    $agreeBtn.on('click', updateAgreeOfArticle);
});

function updateAgreeOfArticle(e) {
    e.preventDefault();
    var target = e.target;
    var url = $(target).parent().attr('href');
    $.ajax({
        type: 'PUT',
        url: url,
        error: function(data) {
            alert(data);
        },
        success: function(data) {
            alert(data)
        }
    });
}

function controlByBtn(e) {
    e.preventDefault();
    var target = e.target;
    var url = $(target).attr('href');
    $.ajax({
        type: 'DELETE',
        url: url,
        error: function(data) {
            alert(data);
        },
        success: function(data) {
            location.href = data;
        }
    });
}