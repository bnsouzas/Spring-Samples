$(document).ready(function () {
	$(document).click(function(event) {
		var clickover = $(event.target);
		var _opened = $(".navbar-collapse").hasClass(
				"navbar-collapse collapse in");
		if (_opened === true
				&& !clickover.hasClass("navbar-toggle")) {
			$("button.navbar-toggle").click();
		}
	});
});

var naoDesabilita = false;
$('form').one('submit', function() {
	if (naoDesabilita == false)
		$(this).find('button').attr('disabled','disabled');
	naoDesabilita = true
});

function confirmSubmit(form, message){
	naoDesabilita=false;
	if (confirm(message)){
		return true;
	} else {
		console.log(form.getElementsByTagName('button')[0].getAttribute("disabled"));
		naoDesabilita=true;
		return false;
	}
}