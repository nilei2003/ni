let vm = new Vue({
		el:"#ass",
		data:{
			total:{},
			singers:{},//歌手
			sepcials:{},// 专辑
			musics:{},//单曲
			MVs:{},//mv
			uid:"",
			history:{},
			x_search:"",
			//头部
			head_show:false,
			head_result:false,
			hots:{},
			user:{},
			quit:false,
		},
		mounted(){
			//头部
			axios.post("singer/findHot").then(result=>{
				this.hots = result.data;
			})
			//查询用户信息
			axios.post("user/info").then(result=>{
				if(result.data.code == 500 || result.data.data == null){
					return;
				}
				this.uid = result.data.data.uid;
				this.user = result.data.data;
				console.log(this.user);
			})
			this.historyData()
		},
		watch:{
			search:function(){
				this.changeback()
			},
			//头部
			x_search:function(){
				this.change_back()
			}
		},
		methods:{
			speclishow(sid, eid, index){
				localStorage.removeItem("sid");
				localStorage.removeItem("eid");//先从本地存储中移除这个属性
				localStorage.setItem("eid", eid);//
				localStorage.setItem("sid", sid);//
				location.href = 'speclishow.html';
			},
			
			  bfmv(mvid, index){
				   localStorage.removeItem("mvid");//先从本地存储中移除这个属性
				   localStorage.setItem("mvid", mvid);//
				   location.href = 'html/mvplay.html';
			   },
			quite_login(){
				axios.post("user/quit",qs.stringify({uid:this.uid})).then(result=>{
					if(result.data == 200){
						location.href = "html/login.html";
					}
				})
			},
			changes_quit(){
				let timer = setTimeout(() => {
				   //需要定时执行的代码
				   this.quit = false;	
				}, 3000)
			},
			change_quit(){
				this.quit = true;	
			},
			//头部
			change_back(){//是否显示
				//改变搜索结果是否显示
				if(this.x_search != ""){
					this.head_result = true;
					this.head_show = false;
					this.find_Input();
				}else{
					this.head_result = false;
					this.head_show = true;
				}
				this.changer_headerhis()
			},
			clearStore(){//清楚历史记录
				localStorage.removeItem('data');
				$('#lists').html('');
			},
			changer_headerhis(){
				if(this.x_search != ""){
					$('#mod_search').css('max-height',0);
				}else{
					$('#mod_search').css('max-height',580);
					changeList();
				}
				changeHot();
			},
			find_Input(){
				axios.post("search/findData",qs.stringify({sname:this.x_search})).then(result=>{
					this.total = result.data.data;
					this.musics = this.total[0];
					this.singers = this.total[1];
					this.sepcials = this.total[2];
					this.MVs = this.total[3];
				})
			},
			con(historys){
				this.x_search = historys;
			},
			historyData(){
				this.history = JSON.parse(localStorage.getItem('data'));
			},
		}
	})
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
	function changeHot(){
		var search = document.getElementsByClassName("search_hot__link");
		var number = document.getElementsByClassName("search_hot__number");
		var name =  document.getElementsByClassName("search_hot__name");
		var listen = document.getElementsByClassName("search_hot__listen");
		for (var i = 0; i < search.length; i++) {
			search[i].setAttribute('index',i);
			search[i].onmouseover = function(){
				let index = this.getAttribute("index");
				$('.search_hot__link').eq(index).css('background-color',"#31C27C");
				$('.search_hot__number').eq(index).css('color',"white");
				$('.search_hot__name').eq(index).css('color',"white");
				$('.search_hot__listen').eq(index).css('color',"white");
			}
			search[i].onmouseout = function(){
				let index = this.getAttribute("index");
				$('.search_hot__link').eq(index).css('background-color',"white");
				$('.search_hot__number').eq(index).css('color',"#ff4222");
				$('.search_hot__name').eq(index).css('color',"#333");
				$('.search_hot__listen').eq(index).css('color',"#999");
			}
		}
	}
	
	
		//mv
		function block2() {
			$("#divss_01_3").css("display", 'none');//重新设置背景颜色
			$("#zhezhao").css("display", 'none');
		}
		
		function block1() {
			$("#divss_01_1").css("display", 'none');//重新设置背景颜色
			$("#zhezhao").css("display", 'none');
		}