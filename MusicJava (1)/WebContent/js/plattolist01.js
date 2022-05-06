
		let page = 1;
		let rows = 18;
        let x = null;
		
			let app = new Vue({
				el:"#topList_mod_songlist",
				data:{
					musics:[],
					sepcial:[],
					sname: "",
					data:"",
				},
				mounted(){
					axios.post("music/findBymusic").then(result=>{
						console.log(result);
						this.musics = result.data.data;
						
					})	
				},
				methods:{
					
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
						location.href = 'mvplay.html';
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
						
//						location.href = 'alipay?msid=' + this.msid + "&emoney=" + emoney;
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
//							location.href = 'musicbf.html';
						
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
//							location.href = 'musicbf.html';
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
							location.href = 'html/login.html';

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
//								location.href = 'musicbf.html';

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
//								location.href = 'musicbf.html';

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
//								location.href = 'musicbf.html';

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
