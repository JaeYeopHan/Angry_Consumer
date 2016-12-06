$(document).ready(function () {
    var $deleteBtn = $('#deleteBtn')
    $deleteBtn.on('click', controlByBtn);
    var $updateSubmitBtn = $('#updateSubmitBtn');
    $updateSubmitBtn.on('click', submitUpdate);
});

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