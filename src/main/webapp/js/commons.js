$(function(){
	var isHidden = false;
	
	$("input[type=button].go").click(function(e) {
		$(this).parent().parent().submit();
	});
	
	$(".formulario").focus(function(e) {
		$(this).parent().find("label").hide();	
	});
	
	$(".formulario").focusout(function(e){
		if($(this).val().length == 0 || $(this).val().length == 0){
			$(this).parent().find("label").show();
		}
	});
});

