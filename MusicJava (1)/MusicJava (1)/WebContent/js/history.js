$(function () {
	let max_history = 7;// 存储最大历史数据
	
	$('#inpts').on('focus', function () {
		$('#mod_search_other').css('visibility','visible');
		$('#mod_search_other').css('max-height',800);
		$('#inpts').val = '';	
	})
	
	//鼠标移除定时器
	$('#inpts').on('blur', function () {
		var time = setTimeout(function(){
			$('#mod_search_other').css('visibility','hidden');
			$('#mod_search_other').css('max-height',0);
			init_history();// 初始化历史记录，清空记录
	      },1500)  //一秒钟打印出111
	})
	
	$('#inpt').on('blur', function () {
		var time = setTimeout(function(){
			$('#mod_search').css('max-height',0);
			$('#results').css('max-height',0);
			$('#result_max').css('max-height',0);
			init_history();// 初始化历史记录，清空记录
	      },1500)  //一秒钟打印出111
	})
	$('#inpt').on('focus', function () {
		$('#results').css('max-height',580);
	    
	})
	//点击搜索按钮时，将搜索内容添加到本地存储
	$('#btn').on('click', function () {
		var search = inpt.value;
		if(search == ""){
			search = inpts.value;
			console.log(1+"+"+search);
		}
		var data = localStorage.getItem('data'); //从本地存储中读取数据
		if (data) {
			var arr = JSON.parse(data); //如果有数据则转换成对象或数组
		} else {
			var arr = []; //如果没有数据，则新增一条
		}
		arr.push(search);
		removalDuplicate(arr);// 对用户输入值进行处理(去重-筛选)
		localStorage.setItem('data', JSON.stringify(arr)); //将数据写入到本地存储中
	})
	//搜索框按钮
	$('#btns').on('click', function () {
		var search = inpts.value;
		var data = localStorage.getItem('data'); //从本地存储中读取数据
		if (data) {
			var arr = JSON.parse(data); //如果有数据则转换成对象或数组
		} else {
			var arr = []; //如果没有数据，则新增一条
		}
		arr.push(search);
		removalDuplicate(arr);// 对用户输入值进行处理(去重-筛选)
		localStorage.setItem('data', JSON.stringify(arr)); //将数据写入到本地存储中
	})
	// 数组去重-筛选函数
	function removalDuplicate(arr) {
		for (let i = 0; i < arr.length; i++) {
			var arritem = arr[i].trim(); // 去除字符串两端空格
			// 如果值为空，则不添加
			if (arritem == '') {
				arr.splice(i, 1);
			}
			if (arritem !== "") {
				for (let j = i + 1; j < arr.length; j++) {
					if (arr[i] == arr[j]) {
						arr.splice(i, 1);//如果第二次输入的值与第一次相同，则添加第二次的值
					}
				}
			}
		}
		return arr;
	}
	// 初始化-清空历史记录
	function init_history() {
		$('#list').html('');
		$('#lists').html('');
	}
	function changeList(){
		var list_on = document.getElementsByClassName("on");
		for (var j = 0; j < list_on.length; j++) {
			list_on[j].setAttribute('index',j);
			list_on[j].onmouseover = function(){
				let index = this.getAttribute("index");
				$('.on').eq(index).css('background-color',"#31C27C");
				$('.active').eq(index).css('color',"white");
			}
			list_on[j].onmouseout = function(){
				let index = this.getAttribute("index");
				$('.on').eq(index).css('background-color',"white");
				$('.active').eq(index).css('color',"#333");
			}
		}
	}
	
	
})

