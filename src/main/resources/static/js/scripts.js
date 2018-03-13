$(document).ready(function() {
	setTimeout(function(){
		$('.log-msg').fadeOut('5000');        
	}, 1000);
	
	setTimeout(function(){
		$('.log-msg').remove();
	}, 1400);
});

