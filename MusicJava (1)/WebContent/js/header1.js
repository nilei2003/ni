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