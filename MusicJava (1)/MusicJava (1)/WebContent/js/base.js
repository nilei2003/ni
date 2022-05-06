function showMsg(msg,color,callback){
	if(!document.getElementById("popup_info")){
		let str = `<div class="popup_con"><div class="popup"><p id="popup_info"></p></div></div>`;
		$(document.body).append( $(str));
	}
	$("#popup_info").text(msg).css("color",color);
	$(".popup_con").fadeIn('fast',function(){
		setTimeout(function(){
			$('.popup_con').fadeOut('fast',callback);//淡出后执行的函数
			
		},2000)
	});
}