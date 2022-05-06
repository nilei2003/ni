
let x = null;
let app = new Vue({
	el: "#app",
	data:{
		music:[],
		user:[],
		specli:[],
		name: "",
		musicnv:[],
		sepcial:[],
		sname:"",
		msid: "",
		uid: "",
		midss: "",
	},
	mounted(){
		
		this.userinfo();
		
		this.musicshow();

		this.musicMVshow();
		
		this.specilshow();
	},
	methods:{
		show01(){
			//style="color: #31C27C;"  div_05
			
			$("#a1").css('color', '#31C27C');//设置属性
			$("#a2").css('color', '#999');//设置属性
			$(".div_05").css('display', 'block');//设置属性
			$(".article_div03").css('display', 'none');//设置属性
		},
        show02(){
			
			$("#a1").css('color', '#999');//设置属性
			$("#a2").css('color', '#31C27C');//设置属性
			$(".div_05").css('display', 'none');//设置属性
			$(".article_div03").css('display', 'block');//设置属性
		},
		speclishow(sid, eid, index){
			localStorage.removeItem("sid");
			localStorage.removeItem("eid");//先从本地存储中移除这个属性
			localStorage.setItem("eid", eid);//
			localStorage.setItem("sid", sid);//
			location.href = 'speclishow.html';
			console.log(eid);
			
		},
		
		specilshow(){
			
			axios.post("SepcialServletShow/findAll").then(result=>{
				this.specli = result.data.data;
			})
		},
		userinfo(){
			axios.post("user/infoto").then(result=>{
				console.log(result);
				if(result.data.code == 200 && result.status == 200){
					this.user = result.data.data;
					this.name =  result.data.data.uname;
					console.log(this.user);
					return;
			    }
				
			})
		},

		enter(index){ // 音乐列表控件

			//isxml = true;
			$(".i2").eq(index).css('display', 'block');//设置属性
			$(".i1").eq(index).css('display', 'block');//设置属性
			$(".i3").eq(index).css('display', 'block');//设置属性


		},
		leave(index){
			//isxml = false;
			$(".i2").eq(index).css('display', 'none');//设置属性
			$(".i1").eq(index).css('display', 'none');//设置属性
			$(".i3").eq(index).css('display', 'none');//设置属性

		},

		// 音乐收藏
		musicshow(){
			axios.post("CollectServlet/findAll").then(result=>{
				console.log(result);
				if(result.data.code == 200){
					this.music = result.data.data;
				}


				this.music.forEach((item, i)=>{  
					
					this.$set(this.music[i], 'mno', '0');// 将这个属性添加到数组里 默认不选择
				
				})


			})
		},

		// 点击音乐名播放
		musicshow01(mid, index, yesnopay, sname, eid){

			console.log(eid);

			if(add.$data.username == "" || add.$data.username == null){// 说明当前用户为临时用户
				alert("请先登录");
				location.href = 'login.html';
				return;
			}

			if(yesnopay == 1){

				axios.post("MoneySepcialSelvlet/show", qs.stringify({eid})).then(result=>{
					console.log(result);
					if(result.data.code == 500){ // 说明用户还没有购买

						$("#divss_01_3_1").css("display", 'block');//
						$("#zhezhao").css("display", 'block');
						this.mid = mid;
						this.sname = sname;
					}else if(result.data.code == 200){
						axios.post("TemporarysdetalsServlet/finds").then(result1=>{
							if(result1.data.code == 500){

								this.musicadd01(mid);
								return;

							}	
							this.tid = result1.data.data.tid;
							//判断播放列表有没有这首歌
							this.musicshow03(this.tid, mid);

						})
					}
				})



			}else{
				axios.post("TemporarysdetalsServlet/finds").then(result=>{
					if(result.data.code == 500){

						this.musicadd01(mid);
						return;

					}


					this.tid = result.data.data.tid;
					//判断播放列表有没有这首歌
					this.musicshow03(this.tid, mid);

				})
			}


		},

		// 支付窗口
		immediatepurchase(){

			console.log("=========");

			axios.post("SepcialServletShow/showList", qs.stringify({mid : this.mid})).then(result=>{

				this.sepcial = result.data.data;


			})



			$("#divss_01_3_1").css("display", 'none');//重新设置背景颜色
			$("#divss_01_1_1").css("display", 'block');//重新设置背景颜色
		},

		//购买专辑
		moneySepcial(eid, emoney){
			console.log(eid);
			console.log(emoney);

			this.gomoneySepaial(eid, emoney);//购买

//			location.href = 'alipay?msid=' + this.msid + "&emoney=" + emoney;
		},
		// 购买专辑
		gomoneySepaial(eid, emoney){
			axios.post("MoneySepcialSelvlet/showList", qs.stringify({eid, emoney})).then(result=>{
				console.log(result);
				this.msid = result.data.data.msid;
				this.uid =  result.data.data.uid;
				location.href = 'alipay?msid=' + this.msid + "&emoney=" + emoney + "&uid=" + this.uid + "&eid=" + eid;
			})
		},

		// 无播放表
		musicadd01(mid){
			axios.post("TemporarysdetalsServlet/add", qs.stringify({mid})).then(result=>{



				localStorage.removeItem("mid");//先从本地存储中移除这个属性
				localStorage.setItem("mid", mid);//
				//location.href = 'musicbf.html';



				if(x == null){
					x =  window.open('musicbf.html');
				}else{
					x.close();
					x =  window.open('musicbf.html');
				}
				//this.musicshow01(mid);

			})

		},
		// 判断有没有这首歌
		musicshow03(tid, mid){

			axios.post("TemporarysdetalsServlet/find", qs.stringify({mid})).then(result=>{

				if(result.data.code == 500){
					// 没有这首歌

					this.musicadd02(tid, mid);
					return;

				}


				localStorage.removeItem("mid");//先从本地存储中移除这个属性
				localStorage.setItem("mid", mid);//
//				location.href = 'musicbf.html';

				if(x == null){
					x =  window.open('musicbf.html');
				}else{
					x.close();
					x =  window.open('musicbf.html');
				}
				return;

 
				
				
				//this.musicshow01(mid);

			})


		},
		//没有这首歌 添加到临时播放详情表
		musicadd02(tid, mid){
			axios.post("TemporarysdetalsServlet/add02", qs.stringify({tid, mid})).then(result=>{

				localStorage.removeItem("mid");//先从本地存储中移除这个属性
				localStorage.setItem("mid", mid);//
//				location.href = 'musicbf.html';
				if(x == null){
					x =  window.open('musicbf.html');
				}else{
					x.close();
					x =  window.open('musicbf.html');
				}

				//this.musicshow01(mid);

			})
		},
		
		changeChecked(event, obj){//
			if(event.target.checked){//
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
			
			

		   this.midss = cnos.join(",");
            console.log(this.midss);
          
            $("#divss_02_6").css("display", 'block');//
			//this.gotoPaydelete(midss);
		},
		
		block6(){
			$("#zhezhao").css("display", 'none');
			$("#divss_02_6").css("display", 'none');//
		},
		block7(){
			$("#zhezhao").css("display", 'none');
			$("#divss_01_3_1").css("display", 'none');//
		},
		block8(){
			$("#zhezhao").css("display", 'none');
			$("#divss_01_1_1").css("display", 'none');//
		},
		
		showimgupdate(){
			$("#zhezhao").css("display", 'block');
			$("#divss_01_7").css("display", 'block');//
		},

		block9(){
			$("#zhezhao").css("display", 'none');
			$("#divss_01_7").css("display", 'none');//
		},
		// 歌手详情
		geshoufind(sid, index){

		},
		//专辑详情
		speclifind(eid, index){

		},

		// mv收藏
		musicMVshow(){

		},


	}
})