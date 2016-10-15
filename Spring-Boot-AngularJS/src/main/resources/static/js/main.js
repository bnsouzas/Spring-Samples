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

function initUibTooltips(){
	$('[uib-tooltip]').mouseover(function() {
		console.log('carregou tooltip');
		var widthCalc = (($(this).attr('uib-tooltip').length) * 8) + 'px';
		var prevStyle = $(this).next('.tooltip').attr('style');
		$(this).next('.tooltip').attr('style', prevStyle + '; width: '+ widthCalc);
	});
}