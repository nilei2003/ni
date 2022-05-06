 (function () {
	window.onload=function () {
		var video = document.getElementsByTagName("video")[0]
		var cav = document.getElementsByTagName("canvas")[0]
		//设置常量canvas的高度以及宽度
		var cavWidth = 845 
		var cavHeight = 420
		cav.width=cavWidth
		cav.height=cavHeight 
		var ctx = cav.getContext("2d")
		
		
		var mvid = decodeURI(location.search);//mvid
		if(mvid == ""){//获取地址栏中的mvid
			location.href = "index.html";
			return;
		}
		mvid = mvid.replace("?","");
		
		//存储弹幕对象的数组
		var capObjs = []
		var lastItemTime
		var capHeight = 20 
		var inputEle = document.getElementsByClassName("caption-input-text")[0]
		var sendEle = document.getElementsByClassName("caption-sendButton")[0]
		var colorUl = document.getElementsByClassName("colorItems")[0]
		var ismoveInputEle = document.getElementsByClassName("caption-input-ismove")[0]
		//弹幕颜色
		var colors=["#fff","#000","#00F","#FF0","#0FF","#F0F"]
		var selectedColorIndex = 0
		var prevPlayTime = 0
		//测试数据的数组
		var testArrayCopy = []
		
		var capobjId = 0
		//弹幕在画布中高度可能值组成的数组
		var topObjs = [{blank:true , value : 20 ,index:0},
						{blank:true , value : 50 ,index:1},
						{blank:true , value : 80 ,index:2},
						{blank:true , value : 110 ,index:3},
						{blank:true , value : 140 ,index:4},
						{blank:true , value : 170 ,index:5},
						{blank:true , value : 200 ,index:6},
						{blank:true , value : 230 ,index:7},
						{blank:true , value : 260 ,index:8},
						{blank:true , value : 290 ,index:9},
						{blank:true , value : 320 ,index:10},
						{blank:true , value : 350 ,index:11},
						{blank:true , value : 380 ,index:12},
						{blank:true , value : 410 ,index:13}]
		//test data 数据
		var testArray = [];
		axios.post("../mvplay/findBullet",qs.stringify({mvid:mvid})).then(result=>{
			result.data.data.forEach((item,index)=>{
				testArray.push(item);
			})
		})
		//将测试数据备份
		copyArray(testArray , testArrayCopy)
		/*弹幕对象的构造函数，参数分别是：1.ismove：弹幕是否是移动的弹幕,2.spe:弹幕的移动速度，3.col：弹幕的颜色，4.text：弹幕的文本*/
		/*原型链方法 setTopValue设置纵坐标，setLeftValue设置横坐标，moving完成坐标的改变，setId完成id值的设置*/
		function Caption( ismove , spe , col , text ) {
			this.isMove = ismove
			this.speed = spe
			this.color = col || "#ff0"
			this.content = text
			this.latestTime = 0 
			this.width = text.length * 20 
			this.id = 0
			this.topIndex = 0
			this.occupyPos = true 
			this.top = 300
			this.left = 0
			this.setLeftValue()
			this.setTopValue()
		}
		Caption.prototype.setTopValue = function  () {
			for(var i = 0 ,len = topObjs.length ; i < len ; i++){
				if (topObjs[i].blank) {
					this.top = topObjs[i].value
					this.topIndex = i
					topObjs[i].blank = false 
					break
				}
			}
		}
		Caption.prototype.setLeftValue = function  () {
			if (this.isMove) {
				this.left = cavWidth
			}
			else {
				var contentLength = this.content.length
				var nowItemLeft = 420 - contentLength * 9
				this.left = nowItemLeft
			}
		}
		Caption.prototype.moving = function () {
			if (this.isMove) {
				this.left-=this.speed
				if ( this.left + this.width < cavWidth && this.occupyPos) {
					this.occupyPos = false 
					topObjs[this.topIndex].blank = true 
				}
			} 
			else{
				this.latestTime += 1
				if (this.latestTime > 450) {
					topObjs[this.topIndex].blank = true 
				}
			}
		}
		Caption.prototype.setId = function  () {
			this.id = capobjId
			capobjId++
		}

		var cap1 = new Caption(  false , 1 , 0 , "小礼物走一波，双击6666。。。。")
		capObjs.push(cap1)
		cap1.setId()
		
		//循环遍历数组，根据对象的属性绘制在画布上
		function drawAllText () {
			ctx.clearRect( 0 , 0 , cavWidth , cavHeight)
			ctx.beginPath()
			for(var i=0 , len = capObjs . length ; i < len ; i++ ){
				ctx.fillStyle = capObjs[i].color
				ctx.font = "bold 20px Courier New"
				ctx.fillText( capObjs[i].content , capObjs[i].left , capObjs[i].top )
				ctx.closePath()
				capObjs[i].moving()
			}
		}
		
		//更新数组，当对象已经超出范围的时候从数组删除这个对象
		function refreshObjs(objs) {
			for (var i = objs.length - 1; i >= 0; i--) {
				if (objs[i].left < - cavWidth || objs[i].latestTime > 450 ) {
					objs.splice(i , 1)
				}

			}
		}
		
		//更新保存弹幕对象的数组
		function updateArray () {
			var now = parseInt( video.currentTime )

			for (var i = testArray.length - 1; i >= 0; i--) {
				var nowItemTime = parseInt(testArray[i].time) 
				if ( nowItemTime == now ) {
					//首次写的控制高度的方式，空间利用不充分，后来改为setTopValue中的方式
					var temcolor = colors[testArray[i].colorIndex]
					var temcap = new Caption (  testArray[i].ismove , 1 , temcolor , testArray[i].content  )
					capObjs.push(temcap)
					capObjs[capObjs.length - 1].setId()
					temcap = null
					testArray.splice(i,1)
				}
			}
		}
		
		function updateDanmu(){
			consolt.log(1);
		}

		//当用户点击send发送弹幕的回调函数
		function sendCaption (argument) {
			var inputEleTxt = inputEle.value
			var now = parseInt( video.currentTime )
			var inputIsmoveValue = ismoveInputEle.checked
			var temObj = {content:inputEleTxt,time:now,ismove:inputIsmoveValue,colorIndex:selectedColorIndex}
			testArray.push(temObj)
//			axios.post("../mvplay/addBullet",qs.stringify({text:inputEleTxt,time:now,ismove:inputIsmoveValue,color:"1",mvid:mvid})).then(result =>{
//				if(result.data == 200){
//					return;
//				}
//			})
			inputEle.value = ""
		}

		
		//重新启动canvas，用在人为导致进度条时间的改变
		function reinitCav (argument) {
			// testArray = testArrayCopy
			copyArray(testArrayCopy , testArray)
			capObjs = []
			capHeight = 0
			clearInterval(canvasTimer)
			canvasTimer = null
			initCanvas()
		}

		var canvasTimer = null 
		
		//初始化canvas，用在开始播放时
 		function initCanvas () {
 			if (canvasTimer == null ) {
				canvasTimer = setInterval(function (argument) {
					drawAllText()
					updateArray()
					refreshObjs(capObjs)
				},10)
 			}
			
		}//end function initCanvas
		
		//复制数组
		function copyArray (arr1 , arr2) {
			for (var i =0 , len=arr1.length ; i < len ; i++) {
					arr2[i] = arr1[i]
				}	
		}

		video.addEventListener("playing" , function () {
			initCanvas()
		})
		
		//进度条改变执行代码
		video.addEventListener("timeupdate", function  () {
			var nowPlayTime = video.currentTime
			var diffTime = Math.abs(nowPlayTime - prevPlayTime)
			prevPlayTime = nowPlayTime
			if (diffTime > 1) {
				reinitCav()
			}
		}, false)
		
		//视频暂停执行代码
		video.addEventListener("pause" , function () {
			clearInterval(canvasTimer)
			canvasTimer = null 
		})
		
		//点击send的监听事件
		sendEle.addEventListener("click" , sendCaption)
		
		//input的回车监听事件
		inputEle.addEventListener("keydown", function(e) {
			var keynum = 0
			keynum = window.event ? e.keyCode : e.which
			if (keynum == 13) {
				sendCaption()
			}
		})
	}//end
})()
