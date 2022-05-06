let x = null;
let app = new Vue({
	el: "#app",
	data:{
		eid: "",
		specil: [],
		music:[],
		sepcial:[],
		sname:"",
		tid: "",
		mid: "",
		uid: "",
	},
	mounted(){
		this.eid = localStorage.getItem("eid");//
		this.specilmusicshow(this.eid);
		this.specilshow(this.eid);
	},
	methods:{
		
		//下载
		download(mid, index, yesnopay, sname, eid, musics){
            console.log(eid);
            
            let music = '../' + musics;
            
            console.log(music);
            
			// downloadadd
            if(vm.$data.uid == "" || vm.$data.uid == null){// 说明当前用户为临时用户
				alert("请先登录");
				location.href = 'html/login.html';

			}
			
			if(yesnopay == 1){

				axios.post("MoneySepcialSelvlet/show", qs.stringify({eid})).then(result=>{
					console.log(result);
					if(result.data.code == 500){ // 说明用户还没有购买
						$(".list_menu__down").eq(index).attr("href", "javascript:void(0)");
						alert("该音乐为付费音乐，您需要购买才可下载哟！！！");
						
					 
					}else if(result.data.code == 200){
						$(".list_menu__down").eq(index).attr("href", music);
					}
				})
				
				
				
			}else{
				
				$(".list_menu__down").eq(index).attr("href", music);
			}
			
			
			
			
		},
		
		  //播放mv
		bfmv(mvid, index){
			localStorage.removeItem("mvid");//先从本地存储中移除这个属性
			localStorage.setItem("mvid", mvid);//
			location.href = 'html/mvplay.html';
		},
		
		specilmusicshow(eid){
			axios.post("SepcialServletShow/showfind", qs.stringify({eid})).then(result=>{
				console.log(result);
				this.music = result.data.data;
				console.log(this.music);
			})
		},
		specilshow(eid){
			axios.post("SepcialServletShow/find", qs.stringify({eid})).then(result=>{
				console.log(result);
				this.specil = result.data.data;
			})
		},
		immediatepurchase(eid, sname){
			axios.post("MoneySepcialSelvlet/show", qs.stringify({eid})).then(result=>{
				console.log(result);
				if(result.data.code == 500){ // 说明用户还没有购买
					this.moneymusicspelis(eid, sname);
				}else if(result.data.code == 200){
					alert("您已经购买,可以直接播放");
				}
			})



		},
		immediatepurchase01(eid, sname){
			axios.post("MoneySepcialSelvlet/show", qs.stringify({eid})).then(result=>{
				console.log(result);
				if(result.data.code == 500){ // 说明用户还没有购买
					this.moneymusicspelis(eid, sname);
				}else if(result.data.code == 200){
					this.mucicshow(eid);
				}
			})

		},

		moneymusicspelis(eid, sname){
			this.sname = sname;
			axios.post("SepcialServletShow/showLists", qs.stringify({eid})).then(result=>{

				this.sepcial = result.data.data;


			})

			$("#zhezhao").css("display", 'block');
			$("#divss_01_1").css("display", 'block');//重新设置背景颜色
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

		mucicshow(eid){
			// 第一步判断当前用户有没临时播放表
			if(vm.$data.uid == "" || vm.$data.uid == null){// 说明当前用户为临时用户
				alert("请先登录");
				location.href = 'login.html';
				return;
			}else{
				
				axios.post("MoneySepcialSelvlet/show", qs.stringify({eid})).then(result=>{
					console.log(result);
					if(result.data.code == 500){ // 说明用户还没有购买
						this.moneymusicspelis(eid, sname);
					}else if(result.data.code == 200){
						this.bfzhuanji(eid);
					}
				})

				
				
			}
		},
		
		bfzhuanji(eid){
			axios.post("TemporarysdetalsServlet/finds").then(result=>{
				if(result.data.code == 500){
					// 说明用户没有临时播放列表
					this.showlists01s(eid);
					return;

				}

				// 说明当前用户有临时播放表

				// 获取临时播放表ID
				this.tid = result.data.data.tid;
				// 判断用户  临时播放表有木有数据
				this.showmusicyesno(this.tid, eid);

			})
		},
		
		// 说明用户没有临时播放列表
		showlists01s(eid){
			axios.post("TemporarysdetalsServlet/showlists01s", qs.stringify({eid})).then(result=>{
				if(result.data.code == 200){
					//添加成功
				
					localStorage.removeItem("mid");//先从本地存储中移除这个属性
//					location.href = 'musicbf.html';

					  if(x == null){
		                	x =  window.open('musicbf.html');
		                }else{
		                	x.close();
		                	x =  window.open('musicbf.html');
		                }
					return;
				}
				
				alert("播放失败");
			})
		},
		showmusicyesno(tid, eid){
			axios.post("TemporarysdetalsServlet/findAll", qs.stringify({tid})).then(result=>{

				// 说明没有数据
				if(result.data.code == 200){
					//有表无数据
					this.showlists01(tid, eid);
					return;
				}
				this.showlists01ss(tid, eid);
				// 说明有数据
			})
		},
		//有表无数据
		showlists01(tid, eid){
			axios.post("TemporarysdetalsServlet/showlists01", qs.stringify({tid, eid})).then(result=>{
				if(result.data.code == 200){
					//添加成功
					
					localStorage.removeItem("mid");//先从本地存储中移除这个属性
//					location.href = 'musicbf.html';

					  if(x == null){
		                	x =  window.open('musicbf.html');
		                }else{
		                	x.close();
		                	x =  window.open('musicbf.html');
		                }
					return;
				}
				
				alert("播放失败");
			})
		},
		// 说明有数据
		showlists01ss(tid, eid){
			axios.post("TemporarysdetalsServlet/showlists01ss", qs.stringify({tid, eid})).then(result=>{
				if(result.data.code == 200){
					//添加成功
				
					localStorage.removeItem("mid");//先从本地存储中移除这个属性
//					location.href = 'musicbf.html';

					  if(x == null){
		                	x =  window.open('musicbf.html');
		                }else{
		                	x.close();
		                	x =  window.open('musicbf.html');
		                }
					return;
				}
				
				alert("播放失败");
			})
		},
		
		enter(index){
			$(".list_menu__icon_play").eq(index).css('display', 'block');//设置属性
			$(".list_menu__icon_add").eq(index).css('display', 'block');//设置属性
			$(".list_menu__icon_down").eq(index).css('display', 'block');//设置属性
		},
		leave(index){
			$(".list_menu__icon_play").eq(index).css('display', 'none');//设置属性
			$(".list_menu__icon_add").eq(index).css('display', 'none');//设置属性
			$(".list_menu__icon_down").eq(index).css('display', 'none');//设置属性
		},
		
		musicshow(mid, index, yesnopay, sname, eid){
			if(vm.$data.uid == "" || vm.$data.uid == null){// 说明当前用户为临时用户
				alert("请先登录");
				location.href = 'login.html';
				return;
			}else{
				axios.post("MoneySepcialSelvlet/show", qs.stringify({eid})).then(result=>{
					console.log(result);
					if(result.data.code == 500){ // 说明用户还没有购买
						this.moneymusicspelis(eid, sname);
						this.mid = mid;

					}else if(result.data.code == 200){
					    this.musicshpw01(mid);// 判断用户有没有临时播放表
					}
				})
			}
			
			
			
			
		},
		
		musicshpw01(mid){
			axios.post("TemporarysdetalsServlet/finds").then(result1=>{
				if(result1.data.code == 500){

					this.musicadd01(mid);
					return;

				}	
				this.tid = result1.data.data.tid;
				//判断播放列表有没有这首歌
				this.musicshow01(this.tid, mid);

			})
		},
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
		musicshow01(tid, mid){

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

	}
})