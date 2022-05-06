
let audidDOM = document.getElementById("audios");

let min = 0;

let left=document.querySelector(".m-left");
let right=document.querySelector(".m-right");
let progress=document.querySelector(".m-progressBar");
let bar=document.querySelector(".bar");
var lyric_txt=document.getElementById("div11");


let timer=null;

document.body.onselectstart = document.body.ondrag = function(){
	return false;
}

var flag;
var sx;
var tx;
var res;
var n=0;			


//进度条前进多少
document.body.onmousemove=function(event)
{

	if(flag)
	{	

		var event= event || window.event;
		var x=event.clientX;
		res=tx+x-sx;


		if(res<0)
		{
			res=0;
		}

		bar.style.left=res+'px';

	}
}


//进度条拉去后 音乐该跳转到什么时候
document.body.onmouseup=function()
{
	flag=false;
	if(n==1)
	{


		var final=Math.floor((res/progress.offsetWidth)*audidDOM.duration);

		audidDOM.currentTime=final;
	}
	n=0;


}

let vue = new Vue({
	el: "#app",
	data:{
		music: [],
		arrs:[],
		user:[],
		list: [],
		list1: [],
		mid: "",
		mids: "",
		tid: "",
		mphoto: "",
		lrc: "",
		index: 0,
		less: 0,
		lrcs: "",
		musiclist: [],
		isShowGeCi: true,
		isarrays: false,
		ischunjing: true,
		currenIndex: 0,
		counts: 0,
		lenthd: 0,
		username: "",
		isLike: false,
	},
	watch: { // watch监听 根据currenIndex 属性是否改变执行  实现歌词滚动
		"currenIndex": function (n, o) {
			this.currenIndex = n;  
			this.$nextTick(() => {
				this.$refs.lyric.scrollTop = 40 * n; //   实现歌词滚动
				this.$refs.lyric_1.scrollTop = 65 * n; //   实现歌词滚动

			})


		},
	},

	directives:{
		drag(el, binding){
			el.onmousedown = function(e){

				let t = null;

				let disy = e.pageY - el.offsetTop;




				document.onmousemove = function(e){
					clearTimeout(t);
					clearInterval(timer);

					if((e.pageY - disy) > 0){
						el.scrollTop = el.scrollTop - 10; 
					}else{
						el.scrollTop = el.scrollTop + 10; 
					}

				}
				document.onmouseup = function(){
					document.onmousemove = document.onmouseup = null;
				}

				t = setTimeout(function(){

					vue.timess();

				}, 3000)

			}
		}
	},

	mounted(){
		// 默认请求  查询用户临时播放详情表数据

		this.userinfo();



		axios.post("TemporarysdetalsServlet/show").then(result=>{
			console.log(result);
			this.music = result.data.data;
			console.log(this.music);
			this.tid = this.music[0].tid;
			this.mid = localStorage.getItem("mid");// 获取上一个界面用户点击的音乐id  TODO 后续做判断  1 用户如果点击的是播放全部 则 这里数据为空

			console.log(this.mid);

			// 用户如果点击的是播放全部 则 这里mid数据为空  那将第一首查询的音乐赋值
			if(this.mid == "" || this.mid == null){
				this.mid = this.music[0].mid;
			}

			this.music.forEach((item, i)=>{  
				this.list.push('../' + item.musics); // 拼接音乐路径 并存储到新的数组里面
				this.$set(this.music[i], 'mno', '0');// 讲这个属性添加到数组李
				if(item.mid == this.mid){// 获取当前应该播放的音乐id
					this.index = i;

				}

			})


			this.less = this.aodiosss(); // 生成随机数 用于背景切换
			this.show(this.index); // 播放音乐
			return;
		})
	},
	methods:{

		userinfo(){
			axios.post("user/info").then(result=>{
				console.log(result);
				if(result.data.code == 200 && result.status == 200){
					this.user = result.data.data;
					this.name =  result.data.data.uname;
					console.log(this.user);
					return;
				}

			})
		},

		audio_a(){// 播放模式

			if(this.lenthd == 2){
				this.lenthd = 0;	
			}else{
				this.lenthd ++;
			}



			//顺序播放
			if(this.lenthd == 0){

				$("#div_audio_a04").css('background-position', '0 -205px');//设置属性

			}
			//单曲循环
			if(this.lenthd == 1){

				$("#div_audio_a04").css('background-position', '0 -232px');//设置属性

			}
			if(this.lenthd == 2){

				$("#div_audio_a04").css('background-position', '0 -74px');//设置属性
			}

		},

		enter(index){ // 音乐列表控件

			//isxml = true;
			$(".i2").eq(index).css('display', 'block');//设置属性
			$(".i1").eq(index).css('display', 'block');//设置属性
			$(".i3").eq(index).css('display', 'block');//设置属性
			$(".i4").eq(index).css('display', 'block');//设置属性
			$(".i6").eq(index).css('display', 'block');//设置属性
			$(".i5").eq(index).css('display', 'none');//设置属性
		},
		leave(index){
			//isxml = false;
			$(".i2").eq(index).css('display', 'none');//设置属性
			$(".i1").eq(index).css('display', 'none');//设置属性
			$(".i3").eq(index).css('display', 'none');//设置属性
			$(".i5").eq(index).css('display', 'block');//设置属性
			$(".i4").eq(index).css('display', 'none');//设置属性
			$(".i6").eq(index).css('display', 'none');//设置属性
		},
		//播放
		show(index){

			this.mids =  this.music[index].mid;//获取当前播放的音乐id

			audidDOM.pause();// 暂停上一首
			audidDOM.src = this.list[index];//获取音乐路径
			audidDOM.load(); //重新加载

			audidDOM.play();//播放
			this.list1 = this.music[index];// 
			this.mphoto = '../' + this.music[index].mphoto; // 获取音乐封面路径
			this.lrc = '../' + this.music[index].lyrlce; // 获取歌词文件路径
			let file = this.loadfile(this.lrc); // 解析获取歌词

			$("#div09_a2").css("background-image", "url(" + this.mphoto + ")");

			if(this.less == 0){
				document.querySelector('body').setAttribute('style', 'background-color: rgb(90, 117, 143);')
			}

			if(this.less == 1){
				document.querySelector('body').setAttribute('style', 'background-color:  rgb(224, 224, 224);')
			}

			if(this.less == 2){
				document.querySelector('body').setAttribute('style', 'background-color: rgb(132, 187, 187);')
			}

			if(this.less == 3){
				document.querySelector('body').setAttribute('style', 'background-color: rgb(64, 64, 32);')
			}

			if(this.less == 4){
				document.querySelector('body').setAttribute('style', 'background-color: rgb(126, 34, 4);')
			}


			$(".bg_player").css("background-image", "url(" + this.mphoto + ")")

			$("#bofang").attr("src", "images/24gf-pause2.png");

			this.likemusic(this.mids);

			this.addmusicplayvolume(this.mids);

			// 播放音乐是判断用户对这首音乐点赞没有
			clearInterval(timer);

			audidDOM.ondurationchange = function (){

				vue.timess();
				//this.timess();

			}

			audidDOM.onended = function(){


				//顺环播放
				if(vue.$data.lenthd == 0){
					if(vue.$data.index == vue.$data.list.length - 1){
						vue.$data.index = 0;
					}else{
						vue.$data.index ++;
					}
				}

				//单曲循环
				if(vue.$data.lenthd == 1){
					vue.$data.index = vue.$data.index;
				}

				//随机播放
				if(vue.$data.lenthd == 2){
					let b = vue.$data.list.length - 1;
					let a = Math.floor(Math.random()*b)

					vue.$data.index = a;
				}

				// vm.$data.loginId

				vue.$data.less = vue.aodiosss();
				vue.show(vue.$data.index);
			}

		},

		addmusicplayvolume(mid){
			axios.post("MusicServletShow/updatemusiclplayvloume", qs.stringify({mid})).then(result=>{
				console.log(result);
			})
		},

		// 播放音乐是判断用户对这首音乐点赞没有
		likemusic(mid){
			axios.post("LikesmusicServlet/showLikeMusic", qs.stringify({mid})).then(result=>{

				if(result.data.code == 500){// 说明当前用户没有点赞 background-position: 0 -96px;
					$("#div_audio_a05").css("background-position", "0 -96px");
					this.isLike = false;
					return;
				}

				this.isLike = true;
				$("#div_audio_a05").css("background-position", "-30px -96px");
			})
		},

		aodiosss(){
			let a = Math.floor(Math.random()*5)

			return a;
		},

		loadfile(lrc){ // 发送请求拿到歌词的lrc文件
			$.ajax({async: true, url:lrc,success:function(result){

				vue.$data.lrcs = result;


				vue.$data.musiclist = vue.parseLyric(vue.$data.lrcs)

			}});


		},

		timeupdate() { // 歌词滚动根据 音乐播放时间 和 歌词文件前面时间比较   currentTime 更改
			const currentTime = audidDOM.currentTime.toFixed(1) - 0;

			const arr = this.musiclist;
			if (this.isShowGeCi) {
				for (let i = 0; i < arr.length; i++) {
					if (currentTime > arr[i].time) {
						this.currenIndex = i;
					}
				}
			}
		},

		parseLyric(lrcs){// 把歌词文件解析为固定格式

			let tempArr = lrcs.match(/\[(.*?):(.*?)\](.*?)\n/g);
			let arr = [];
			for (let temp of tempArr) {
				//对每一行进行拆分得到每一行中各个数据
				let tempItem = temp.match(/\[(.*?):(.*?)\](.*?)\n/);
				//将拆分好的数据push到新的数组中
				arr.push({
					time: tempItem[1] + ":" + tempItem[2],
					str: tempItem[3],
				});
			}

			arr.forEach((item, i)=>{ // 将歌词文件时间部分解析为秒

				item.time = this.timeEvent(item.time);
			})

			return arr;

		},

		dblclickmusic(time){
			audidDOM.currentTime = time;
		},

		timeEvent(e) { // 将歌词文件时间部分解析为秒
			let time = e;
			var len = time.split(':');
			if (len.length == 3) {
				var hour = time.split(':')[0];
				var min = time.split(':')[1];
				var sec = time.split(':')[2];
				return Number(hour * 3600) + Number(min * 60) + Number(sec);
			}
			if (len.length == 2) {
				var min = time.split(':')[0];
				var sec = time.split(':')[1];
				return Number(min * 60) + Number(sec);
			}
			if (len.length == 1) {
				var sec = time.split(':')[0];
				return Number(sec);
			}
		},


		shangyishou(){ // 上一首
			if(this.index == 0){
				this.index = this.list.length - 1;
			}else{
				this.index --;
			}
			this.less = this.aodiosss();

			this.show(this.index);
		},
		zzbf(){ // 暂停播放
			this.bofang();
		},
		zjbf(indexs){ // 暂停播放
			if(indexs == this.index){
				this.bofang();
			}else{
				this.isarrays = false;
				this.index = indexs;
				this.less = this.aodiosss();
				this.show(this.index);
			}

		},
		bofang(){// 暂停播放
			if(audidDOM.paused){
				this.isarrays = false;
				audidDOM.play();
				$("#bofang").attr("src", "images/24gf-pause2.png");
				this.timess();
			}else{
				audidDOM.pause();
				this.isarrays = true;
				$("#bofang").attr("src", "images/播放2.png");
				clearInterval(timer);
			}
		},
		xiayishou(){ // 下一首
			if(this.index == this.list.length - 1){
				this.less = this.aodiosss();
				this.index = 0;
			}else{
				this.less = this.aodiosss();
				this.index ++;
			}


			this.show(this.index);
		},
		timess(){ // 进度条部分
			timer=setInterval(function(){


				//时间划分取整操作

				let min=Math.floor(audidDOM.currentTime/60);
				let sec=Math.floor(audidDOM.currentTime%60);
				vue.timeupdate(); // 歌词滚动标准
				//进度条操作


				left=document.querySelector(".m-left");
				right=document.querySelector(".m-right");
				progress=document.querySelector(".m-progressBar");
				bar=document.querySelector(".bar");


				//进度条位置 以百分比
				bar.style.left=(audidDOM.currentTime/audidDOM.duration)*100+'%'; 

				left.style.width=(audidDOM.currentTime/audidDOM.duration)*100+"%";
				right.style.width=(1-(audidDOM.currentTime/audidDOM.duration))*100+'%';

			},100);
		},
		deletemid(tddid, indexs){ // 删除临时播放表

			axios.post("TemporarysdetalsServlet/showlist", qs.stringify({tddid})).then(result=>{
				this.music.splice(indexs,1);
				this.list.splice(indexs,1);
			})

		},
		chunjingmoshi(){ // 纯洁模式背景修改

			if(this.ischunjing){

				if(this.less == 0){
					document.querySelector('#cunjing').setAttribute('style', 'background-color: rgb(90, 117, 143);')
				}

				if(this.less == 1){
					document.querySelector('#cunjing').setAttribute('style', 'background-color:  rgb(224, 224, 224);')
				}

				if(this.less == 2){
					document.querySelector('#cunjing').setAttribute('style', 'background-color: rgb(132, 187, 187);')
				}

				if(this.less == 3){
					document.querySelector('#cunjing').setAttribute('style', 'background-color: rgb(64, 64, 32);')
				}

				if(this.less == 4){
					document.querySelector('#cunjing').setAttribute('style', 'background-color: rgb(126, 34, 4);')
				}


				$("#div_audio_a09").css("background-position", "0 -311px");

				$("#cunjing").css("display", "block");




				this.ischunjing = false;
			}else{
				$("#div_audio_a09").css("background-position", "0 -282px");
				$("#cunjing").css("display", "none");
				this.ischunjing = true;
			}


		},
		mousedownbar(){ // 监听鼠标事件


			flag=true;
			n=1; //使得鼠标抬起时间只能配合按下使用，否则页面鼠标抬起就会响应改变播放时间操作
			sx=event.clientX;
			tx=bar.offsetLeft;
		},
		// 多选框监听事件
		changeChecked(event, obj){//单个商品的选中
			if(event.target.checked){//该商品被选中
				obj.mno = 1;
				let checkboxs = $("#cart_list input[type='checkbox']");//获取所有的多选框
				/* for(let i = 0, len = checkboxs.length; i < len; i++){
    			     if(!checkboxs[i].checked){
    			    	 $("#all").prop("checked", false);
    			    	 this.getTotal();
    			    	 return;
    			     }
    			}*/


			}else{
				obj.mno = 0;

			}


		},

		// 批量删除
		gotoPay(){
			let cnos = [];//
			this.music.forEach((item, index)=>{
				//获取你复选框里面的data-cno属性的值 或item.cno
				if(item.mno == 1){
					cnos.push(item.mid);//取出当前对象的cno 属性值即购物车便号
				}
			})

			if(cnos.length <= 0){//说明未勾选

				return;
			}

			let midss = cnos.join(",");

			this.gotoPaydelete(midss);
		},
		gotoPaydelete(mid){
			axios.post("TemporarysdetalsServlet/deletemusic", qs.stringify({tid:this.tid, mid})).then(result=>{

				this.arrs = result.data.data;

				this.arrs.forEach((arr)=>{  

					this.music.forEach((item, i)=>{

						if(item.mid == arr){
							this.music.splice(i,1);
							this.list.splice(i,1);
						}
					})



				})
			})
		},

		// 情空列表
		deleteshowmusic(){

			axios.post("TemporarysdetalsServlet/deleteshow", qs.stringify({tid:this.tid})).then(result=>{

				if(result.data.code == 200){// 说明清空成功

					this.music = [];
					this.list = [];

					return;
				}
			})

		},

		// 收藏
		findcollect(mid){
			axios.post("CollectServlet/find", qs.stringify({mid})).then(result=>{

				if(result.data == 500){// 说明没有被收藏过
					this.addcollect(mid);
					return;
				}
				alert("您已经收藏了喔");
			})
		},
		// 收藏
		addcollect(mid){
			axios.post("CollectServlet/add", qs.stringify({mid})).then(result=>{
				alert("收藏成功！！！");
			})
		},
		// 为音乐点赞  background-position: -30px -96px;
		addlikes(){


			if(this.isLike){// 若这个位true 这表示用户取消点赞  若为false 则为用户为音乐点赞
				axios.post("LikesmusicServlet/deleteLikeMusic", qs.stringify({mid:this.mids})).then(result=>{
					if(result.data == 200){
						$("#div_audio_a05").css("background-position", "0 -96px");
						this.isLike = false;
					}
				})
			}else{
				axios.post("LikesmusicServlet/add", qs.stringify({mid:this.mids})).then(result=>{

					if(result.data == 200){
						$("#div_audio_a05").css("background-position", "-30px -96px");
						this.isLike = true;
					}

				})
			}


		},

        //播放mv
		bfmv(mvid, index){
			localStorage.removeItem("mvid");//先从本地存储中移除这个属性
			localStorage.setItem("mvid", mvid);//
			location.href = 'html/mvplay.html';
		}


	}
})




