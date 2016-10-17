var $loginButton = $('#login_button');
var $signupButton = $('#signup_button');
var $closeButton = $('.modal-close');
var $modal = $('.modal');
var $loginModal = $('#login-modal');
var $signupModal = $('#signup-modal');

$loginButton.on('mouseup', function(event){
    $loginModal.css('display','block');
});

$signupButton.on('mouseup', function(event){
    $signupModal.css('display','block');
});

$closeButton.on('mouseup', function(){
    $modal.css('display','none');
});