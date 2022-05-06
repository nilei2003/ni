let add = new Vue({
	el: "#header",
	data:{
		username: "",
		isArray: false,
		goodstwo:[],
	},
	mounted(){
		axios.post("user/info").then(result=>{
			console.log(result);
			if(result.data.code == 200 && result.status == 200){
				this.goodstwo = result.data.data;
				this.username =  result.data.data.uname;
				this.isArray = true;
				return;
		    }
			this.isArray = false;
		})
	}
	

})