let app = new Vue({
	el: "#app",
	data:{
		mv:[],
	},
	mounted(){
		var mvid = localStorage.getItem("mvid");//mvid
		if(mvid == ""){//获取地址栏中的mvid
			location.href = "index.html";
			return;
		}
		axios.post("MusicMVServletShow/show", qs.stringify({mvid})).then(result=>{
			console.log(result);
			this.mv = result.data.data;
		})
	}
})