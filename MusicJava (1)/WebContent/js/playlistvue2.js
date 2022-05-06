let x = null;
let app = new Vue({
	el: "#app",
	data:{
		pid: "",
		music:[],
		playlist:[],
		sname:"",
		tid: "",
		mid: "",
		uid: "",
	},
	mounted(){
		this.pid = localStorage.getItem("pid");//
		this.updateplaylistdisplay(this.pid);
		this.playmusicshow(this.pid);
		this.playshow(this.pid);
		
		//查询用户信息
		axios.post("user/info").then(result=>{
			if(result.data.code == 500 || result.data.data == null){
				alert("请先登录");
				location.href= "login.html";
				return;
			}
			this.uid = result.data.data.uid;
		})
		
	},
	methods:{
		
		download(mid, index, yesnopay, sname, eid, musics){
            console.log(musics);
            
            let music = '../' + musics;
            
            console.log(music);
            
			// downloadadd
			if(this.uid == ""){// 说明当前用户为临时用户
				alert("请先登录");
				location.href = 'html/login.html';
				return;
			}
			
			if(yesnopay == 1){

				axios.post("../MoneySepcialSelvlet/show", qs.stringify({eid})).then(result=>{
					console.log(result);
					if(result.data.code == 500){ // 说明用户还没有购买
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

		updateplaylistdisplay(pid){
			axios.post("playlistdetalsServletshow/updateplaylist", qs.stringify({pid})).then(result=>{
				console.log(result);
			})
		},
		
		playmusicshow(pid){
			axios.post("playlistdetalsServletshow/showAll", qs.stringify({pid})).then(result=>{
				console.log(result);
				this.music = result.data.data;
				console.log(this.music);
			})
		},
		playshow(pid){
			axios.post("playlistdetalsServletshow/show", qs.stringify({pid})).then(result=>{
				console.log(result);
				this.playlist = result.data.data;
			})
		},


		mucicshow(pid){
			// 第一步判断当前用户有没临时播放表
			if(vm.$data.uid == "" || vm.$data.uid == null){// 说明当前用户为临时用户
				alert("请先登录");
				location.href = 'login.html';
				return;
			}else{
				
				
						this.bfzhuanji(pid);
				
				
			}
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
				location.href = 'html/login.html';

			}else{
				
					    this.musicshpw01(mid);// 判断用户有没有临时播放表
				
			}
			
			
			
			
		},
		
		musicshpw01(mid){
			axios.post("TemporarysdetalsServlet/finds").then(result1=>{
				
				console.log(result1);
				
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
		
		
		
		// 点击播放全部
		pallall(pid){
			// 第一步判断当前用户有没临时播放表
			if(vm.$data.uid == "" || vm.$data.uid == null){// 说明当前用户为临时用户
				alert("请先登录");
				location.href = 'html/login.html';

			}else{
				axios.post("TemporarysdetalsServlet/finds").then(result=>{
					if(result.data.code == 500){
	                    // 说明用户没有临时播放列表
						this.addUserAndMusic(pid);
						return;

					}
					
					// 说明当前用户有临时播放表
					
					// 获取临时播放表ID
					this.tid = result.data.data.tid;
					// 判断用户  临时播放表有木有数据
					this.showmusicyesno(this.tid, pid);
					
				})
			}

			
			
			
		},
		// 用户没有临时播放表  播放全部歌单
		addUserAndMusic(pid){
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
		showmusicyesno(tid, pid){
			
			axios.post("TemporarysdetalsServlet/findAll", qs.stringify({tid})).then(result=>{
				
				// 说明没有数据
				if(result.data.code == 200){
					//有表无数据
					this.addMusic(tid, pid);
					return;
				}
				this.addshow(tid, pid);
				// 说明有数据
			})
		},
		// 有表无数据 播放全部
		addMusic(tid, pid){
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
		addshow(tid, pid){
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