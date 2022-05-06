
let page = 1;
let rows = 18;
let x = null;
let app = new Vue({
	el: "#article",
	data:{
		musics: [],
		musics1: [],
		musics2: [],
		musicmv: [],
		musicmv1:[],
		playlists:[],
		playlists1:[],
		playlists2:[],
		tid: "",
		mid: "",
		sepcial:[],
		sname:"",
		msid: "",
	},
	mounted(){

		this.music();

		this.playlist();
		
		this.musicmvs();

	},
	methods:{
		
		music(){
			axios.post("MusicServletShow/showList", qs.stringify({page, rows})).then(result=>{
				console.log(result)
				this.musics = result.data.data;
				
				this.musics.forEach((item, i)=>{
					
					if(i < 9){
						this.musics1.push(item);
					}else if(i < 18){
						this.musics2.push(item);
					}
				})
	           
			
			})
		},
		
		playlist(){
			axios.post("playlistdetalsServletshow/showList").then(result1=>{
				console.log(result1);
				this.playlists = result1.data.data;
				
				this.playlists.forEach((item, i)=>{
					
					if(i < 5){
						this.playlists1.push(item);
					}else if(i < 10){
						this.playlists2.push(item);
					}
				})
				
			})
		},
		musicmvs(){
			 axios.post("MusicMVServletShow/showList", qs.stringify({page, rows})).then(result=>{
				   console.log(result);
				   this.musicmv = result.data.data;

					this.musicmv.forEach((item, i)=>{
						
						if(i < 10){
							this.musicmv1.push(item);
						}
					})
			   })
		},
		 bfmv(mvid, index){
			   localStorage.removeItem("mvid");//先从本地存储中移除这个属性
			   localStorage.setItem("mvid", mvid);//
			   location.href = 'html/mvplay.html';
		  },
		speclishow(sid, eid){
			localStorage.removeItem("sid");
			localStorage.removeItem("eid");//先从本地存储中移除这个属性
			localStorage.setItem("eid", eid);//
			localStorage.setItem("sid", sid);//
			location.href = 'speclishow.html';
			console.log(eid);
			
		},
		
       playlistshow(pid){
			
			localStorage.removeItem("pid");//先从本地存储中移除这个属性
			localStorage.setItem("pid", pid);//
			location.href = 'playlistshow.html';
		},
		
		
		// 这里判断用户有没有播放列表      TODO  那要是临时用户呢？ 临时用户可以播放普通音乐， 付费音乐需要登录购买才可以听
		musicshow(mid, index, yesnopay, sname, eid){
			
			console.log(eid);
			
			if(vm.$data.uid == "" || vm.$data.uid == null){// 说明当前用户为临时用户
				alert("请先登录");
				location.href = 'html/login.html';

			}
		
			if(yesnopay == 1){

				axios.post("MoneySepcialSelvlet/show", qs.stringify({eid})).then(result=>{
					console.log(result);
					if(result.data.code == 500){ // 说明用户还没有购买
					   
						$("#divss_01_3").css("display", 'block');//重新设置背景颜色
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
							this.musicshow01(this.tid, mid);

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
					this.musicshow01(this.tid, mid);

				})
			}
			
			

		},
		// 支付窗口
		immediatepurchase(){
			
			axios.post("SepcialServletShow/showList", qs.stringify({mid : this.mid})).then(result=>{
				
				this.sepcial = result.data.data;
				
				
			})
			

			
			$("#divss_01_3").css("display", 'none');//重新设置背景颜色
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
		// 点击播放全部
		pallall(){
			// 第一步判断当前用户有没临时播放表
			if(vm.$data.uid == "" || vm.$data.uid == null){// 说明当前用户为临时用户
				alert("请先登录");
				location.href = 'login.html';
				return;
			}else{
				axios.post("TemporarysdetalsServlet/finds").then(result=>{
					if(result.data.code == 500){
	                    // 说明用户没有临时播放列表
						this.addUserAndMusic();
						return;

					}
					
					// 说明当前用户有临时播放表
					
					// 获取临时播放表ID
					this.tid = result.data.data.tid;
					// 判断用户  临时播放表有木有数据
					this.showmusicyesno(this.tid);
					
				})
			}

			
			
			
		},
		// 用户没有临时播放表  播放全部
		addUserAndMusic(){
			axios.post("TemporarysdetalsServlet/addUserAndMusic").then(result=>{
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
		// 判断用户  临时播放表有木有数据
		showmusicyesno(tid){
			
			axios.post("TemporarysdetalsServlet/findAll", qs.stringify({tid})).then(result=>{
				
				// 说明没有数据
				if(result.data.code == 200){
					//有表无数据
					this.addMusic(tid);
					return;
				}
				this.addshow(tid);
				// 说明有数据
			})
		},
		// 有表无数据 播放全部
		addMusic(tid){
			axios.post("TemporarysdetalsServlet/addMusic", qs.stringify({tid})).then(result=>{
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
		// 如果用户有临时播放表， 且里面有数据 播放全部
		addshow(tid){
			axios.post("TemporarysdetalsServlet/addshow", qs.stringify({tid})).then(result=>{
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
		
		// 点击播放全部
		pallall1(pid){
			// 第一步判断当前用户有没临时播放表
			if(vm.$data.uid == "" || vm.$data.uid == null){// 说明当前用户为临时用户
				alert("请先登录");
				location.href = 'login.html';
				return;
			}else{
				axios.post("TemporarysdetalsServlet/finds").then(result=>{
					if(result.data.code == 500){
	                    // 说明用户没有临时播放列表
						this.addUserAndMusic1(pid);
						return;

					}
					
					// 说明当前用户有临时播放表
					
					// 获取临时播放表ID
					this.tid = result.data.data.tid;
					// 判断用户  临时播放表有木有数据
					this.showmusicyesno1(this.tid, pid);
					
				})
			}

			
			
			
		},
		// 用户没有临时播放表  播放全部歌单
		addUserAndMusic1(pid){
			axios.post("TemporarysdetalsServlet/showlistsplaylist1", qs.stringify(pid)).then(result=>{
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
		// 判断用户  临时播放表有木有数据
		showmusicyesno1(tid, pid){
			
			axios.post("TemporarysdetalsServlet/findAll", qs.stringify({tid})).then(result=>{
				
				// 说明没有数据
				if(result.data.code == 200){
					//有表无数据
					this.addMusic1(tid, pid);
					return;
				}
				this.addshow1(tid, pid);
				// 说明有数据
			})
		},
		// 有表无数据 播放全部
		addMusic1(tid, pid){
			axios.post("TemporarysdetalsServlet/showlistsplaylist", qs.stringify({tid, pid})).then(result=>{
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
		// 如果用户有临时播放表， 且里面有数据 播放全部
		addshow1(tid, pid){
			axios.post("TemporarysdetalsServlet/showlistsplaylist2", qs.stringify({tid, pid})).then(result=>{
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
		}
		
		
		
	}
})