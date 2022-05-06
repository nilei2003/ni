window.onload = function(){
	var play = document.getElementsByClassName("play_pic");
	var js_play = document.getElementsByClassName("js_play");
	var mod = document.getElementsByClassName("mod_cover__mask");
	var input_s = document.getElementById("search_input");
	var mod_search = document.getElementById("mod_search");
	
	// 鼠标移入
	
	for (var i = 0; i < play.length; i++) {
		mod[i].setAttribute('index', i);
		js_play[i].setAttribute('index', i);
		mod[i].onmouseover = function(){
			for(var j = 0; j < js_play.length;j++){
				let index = this.getAttribute("index");
				$('.js_play').eq(index).css('opacity',1);
				$('.js_play').eq(index).css('transform','scale(.9)');
				$('.play_pic').eq(index).css('transform','scale(1.1)');
				
			}
		}
		mod[i].onmouseout = function(){
			for(var j = 0; j < js_play.length;j++){
				let index = this.getAttribute("index");
				$('.js_play').eq(index).css('opacity',0);
				$('.js_play').eq(index).css('transform','scale(1.0)');
				$('.play_pic').eq(index).css('transform','scale(1.0)');
			}
		}
	}
	for(var j = 0;j < js_play.length;j++){
		js_play[j].onmouseover = function(){
			for(var x = 0;x < js_play.length;x++){
				let index = this.getAttribute("index");
				$('.js_play').eq(index).css('opacity',1);
				$('.js_play').eq(index).css('transform','scale(.9)');
				$('.play_pic').eq(index).css('transform','scale(1.1)');
			}
		}
		js_play[j].onmouseout = function(){
			for(var x = 0;x < js_play.length;x++){
				let index = this.getAttribute("index");
				$('.js_play').eq(index).css('opacity',1);
				$('.js_play').eq(index).css('transform','scale(1.0)');
				$('.play_pic').eq(index).css('transform','scale(1.0)');
			}
		}
	}
	// 输入框获取焦点事件
	$('#search_input').focus(function(){
		console.log(1);
		// 设置最大长度
		$('#mod_search').css('max-height',580);
	})
	$('#search_input').blur(function(){
		console.log(2);
		$('#mod_search').css('max-height',0);
	})

}