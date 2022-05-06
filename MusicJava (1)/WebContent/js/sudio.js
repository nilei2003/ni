	let audidDOM = document.getElementById("audios");
		    
		//	audidDOM.addEventListener("timeupdate", audioPlugns.timeUpdate);
			
			let min = parseInt(audidDOM.duration + 1);
			console.log(min);
			let x =parseInt(min / 60);
			let y = min % 60;
			let box = document.getElementById("audio_div02");
			let span = document.createElement("span");
			span.innerText = "0" + x + ":" + y;
			box.appendChild(span)
			
			index = 0;
			// audidDOM.play();
			// $("#audio_jdt02").animate({width : '100%', opacity : 0}, min*1000);
			
			let list = new Array("audio/温岚 - 夏天的风.mp3", "audio/买辣椒也用券 - 起风了.mp3", "audio/IU - Celebrity.mp3", 
			                     "audio/IU - Coin.mp3", "audio/IU - Flu.mp3", "audio/IU - 라일락.mp3", "audio/IU - 봄 안녕 봄.mp3",
								 "audio/IU - 빈 컵 (Empty Cup).mp3", "audio/IU - 아이와 나의 바다.mp3",  "audio/IU - 어푸 (Ah puh).mp3",
								 "audio/IU - 에필로그.mp3",  "audio/IU,DEAN - 돌림노래.mp3",  "audio/IU,SUGA - 에잇(Prod.&Feat. SUGA of BTS).mp3")
			
			

			
		
			
			$("#bofang").click( function(){//点击事件
			if(audidDOM.paused){
				audidDOM.play();
				$("#bofang").attr("src", "images/24gf-pause2.png");
				$("#audio_jdt02").animate({width : '100%', opacity : 0}, min*1000);
			}else{
				audidDOM.pause();
				$("#bofang").attr("src", "images/播放2.png");
				$("#audio_jdt02").stop(); 
			}
			  
				
			})
			$("#shangyishou").click( function(){//点击事件 上一首
			if(index == 0){
						index = list.length - 1;
			}else{
						index --;
			}
			console.log(index);
						  
			show();
			
			})
			
			function show(){
				audidDOM.pause();
				audidDOM.src = list[index];
				audidDOM.load();
					
				audidDOM.play();
				$("#bofang").attr("src", "images/24gf-pause2.png");
							 
				audidDOM.ondurationchange = function (){
						 min = parseInt(audidDOM.duration + 1);
				
						 x =parseInt(min / 60);
						 y = min % 60;
						 span.innerText = "0" + x + ":" + y;
									 $("#audio_jdt02").stop(true, true);
									  
									 $("#audio_jdt02").css("width", "0%");//设置样式
									 $("#audio_jdt02").css("opacity", "1");//设置样式
									 $("#audio_jdt02").animate({width : '100%', opacity : 0}, min*1000);
				}
			}
			
			$("#xiayishou").click( function(){//点击事件 下一首
			   if(index == list.length - 1){
				  index = 0;
			   }else{
				  index ++;
			   }
			   console.log(index);
			  
			 show();

			})
			
			
			audidDOM.onended = function(){
				if(index == list.length - 1){
						index = 0;
				}else{
						index ++;
				}
				console.log(index);
				show();
			}
			