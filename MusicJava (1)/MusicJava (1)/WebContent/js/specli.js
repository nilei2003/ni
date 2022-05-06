let app = new Vue({
	el: "#app",
	data:{
		specli:[],
		specli1:[],
		sepcial:[],
		sname:"",
	},
	mounted(){
		axios.post("SepcialServletShow/show").then(result=>{
			console.log(result);
			this.specli = result.data.data;
			
			
			this.specli.forEach((item, i)=>{
				
				if(i < 8){
					this.specli1.push(item);
				}
			})
           
			
		})
	},
	methods:{
		enter(index){ // 遮罩成

			$(".zhezhao01").eq(index).css('display', 'block');//设置属性
			//isxml = true;
		},
		leave(index){
			//isxml = false;
			$(".zhezhao01").eq(index).css('display', 'none');//设置属性
		},
		
		speclishow(sid, eid, index){
			localStorage.removeItem("sid");
			localStorage.removeItem("eid");//先从本地存储中移除这个属性
			localStorage.setItem("eid", eid);//
			localStorage.setItem("sid", sid);//
			location.href = 'speclishow.html';
			console.log(eid);
			
		},
		sigershow(sid, index){
			localStorage.removeItem("sid");//先从本地存储中移除这个属性
			localStorage.setItem("sid", sid);//
			console.log(sid);
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
	}
})