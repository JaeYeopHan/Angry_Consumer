/**
 * Created by Jbee on 2016. 10. 16..
 */
$('.top-btn-area').on('click', 'a', function(event){
    event.preventDefault();
    $('html, body').animate({
        scrollTop: 0
    }, 300);
});