window.onload = function(){
	var play = document.getElementsByClassName("playlist__pic");
	var js_play = document.getElementsByClassName("js_play");
	
	// 鼠标移入
	
	for (var i = 0; i < play.length; i++) {
		play[i].setAttribute('index',i);
		js_play[i].setAttribute('index',i);
		
		play[i].onmouseover = function(){
			for(var j = 0; j < js_play.length;j++){
				let index = this.getAttribute("index");
				$('.js_play').eq(index).css('opacity',1.0);
			}
		}
		play[i].onmouseout = function(){
			for(var j = 0; j < js_play.length;j++){
				let index = this.getAttribute("index");
				$('.js_play').eq(index).css('opacity',0);
			}
		}
	}
	for(var j = 0;j < js_play.length;j++){
		js_play[j].onmouseover = function(){
			for(var x = 0;x < js_play.length;x++){
				let index = this.getAttribute("index");
				$('.js_play').eq(index).css('opacity',1.0);
			}
		}
	}
}