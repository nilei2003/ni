let app = new Vue({
	el: "#app",
	data: {
		playlist: [],
		playlist1: [],
		playlist2: [],
		playlist3: [],
		playlist4: [],
		playlist5: [],
		playlist6: [],
		playlist7: [],
	},
	mounted(){
		axios.post("playlistdetalsServletshow/showList").then(result=>{
			console.log(result);
			this.playlist = result.data.data;
			
			this.playlist1 = this.playlist[0]; 
			this.playlist2 = this.playlist[1]; 
			this.playlist3 = this.playlist[2]; 
			this.playlist4 = this.playlist[3]; 
			this.playlist5 = this.playlist[4]; 
			this.playlist6 = this.playlist[5]; 
			this.playlist7 = this.playlist[6]; 
			
			console.log(this.playlist1 + "=======");
		})
	},
	methods:{
		enter(index){
			$('.js_play').eq(index).css('opacity',1);
			$('.js_play').eq(index).css('transform','scale(.9)');
			$('.play_pic').eq(index).css('transform','scale(1.1)');
		},
		leave(index){
			$('.js_play').eq(index).css('opacity',0);
			$('.js_play').eq(index).css('transform','scale(1.0)');
			$('.play_pic').eq(index).css('transform','scale(1.0)');
		},
		playlistshow(pid){
			
			localStorage.removeItem("pid");//先从本地存储中移除这个属性
			localStorage.setItem("pid", pid);//
			location.href = 'playlistshow.html';
		}
	}
})