// $("#divs01").on({
			// 	mouseover: ()=>{//鼠标移入事件 opacity: 0;
			// 		$("#i1").css('display', 'block');//设置属性
			// 		$("#i1").css('opacity', '0.1');//设置属性
			// 	},
				
			// 	mouseout:()=>{//鼠标移出事件
			// 		$("#i1").css('display', 'none');//设置属性
			// 		$("#i1").css('opacity', '0');//设置属性
			// 	},
				
				
			// })
			
			let lists1 = document.getElementsByClassName("article_div04");
			let lists2 = document.getElementsByClassName("zz");
			
			
			for(let i = 0; i < lists1.length; i ++){
				lists1[i].setAttribute('index', i);
				lists2[i].setAttribute('index', i);
				
				
				lists1[i].onmouseover=function(){
					let x = this.getAttribute('index');
					$(".zz").eq(x).css('display', 'block');//设置属性
					$(".zz").eq(x).css('opacity', '0.1');//设置属性
				}
				lists1[i].onmouseout=function(){
					 let x = this.getAttribute('index');
				  $(".zz").eq(x).css('display', 'none');//设置属性
				  $(".zz").eq(x).css('opacity', '0');//设置属性
				}
			}
			
			
			
		
		$("#you01").click( function(){//点击事件

			$("#divph01").css("transform", 'translate(-1255px) ');//重新设置背景颜色
		})
		
		$("#zuo01").click( function(){//点击事件
		
			$("#divph01").css("transform", 'translate(0px) ');//重新设置背景颜色
		})
		
		
		$("#you02").click( function(){//点击事件
		
			$("#divph02").css("transform", 'translate(-1230px) ');//重新设置背景颜色
		})
		
		$("#zuo02").click( function(){//点击事件
		
			$("#divph02").css("transform", 'translate(0px) ');//重新设置背景颜色
		})
		
		
		$("#you03").click( function(){//点击事件
		
			$("#divph03").css("transform", 'translate(-1240px) ');//重新设置背景颜色
		})
		
		$("#zuo03").click( function(){//点击事件
		
			$("#divph03").css("transform", 'translate(0px) ');//重新设置背景颜色
		})
		
		
		$("#you04").click( function(){//点击事件
		
			$("#divph04").css("transform", 'translate(-1270px) ');//重新设置背景颜色
		})
		
		
		$("#zuo04").click( function(){//点击事件
		
			$("#divph04").css("transform", 'translate(0px) ');//重新设置背景颜色
		})
		
		
		$("#you05").click( function(){//点击事件
		
			$("#divph05").css("transform", 'translate(-1270px) ');//重新设置背景颜色
		})
		
		
		$("#zuo05").click( function(){//点击事件
		
			$("#divph05").css("transform", 'translate(0px) ');//重新设置背景颜色
		})
		
		
		function ssss(){
			window.location.href = "musicbf.html";
		}
		
		function ssss1(){
			window.location.href = "musicbf.html";
		}