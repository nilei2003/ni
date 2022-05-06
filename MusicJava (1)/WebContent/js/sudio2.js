   let lists1 = document.getElementsByClassName("li02");
			let i1 = document.getElementsByClassName("i1");
			let i2 = document.getElementsByClassName("i2");
			let i3 = document.getElementsByClassName("i3");
			
			
			
			for(let i = 0; i < lists1.length; i ++){
				lists1[i].setAttribute('index', i);
				i1[i].setAttribute('index', i);
				i2[i].setAttribute('index', i);
				i3[i].setAttribute('index', i);
				
				
				lists1[i].onmouseover=function(){
					let x = this.getAttribute('index');
					$(".i2").eq(x).css('display', 'block');//设置属性
					$(".i1").eq(x).css('display', 'block');//设置属性
					$(".i3").eq(x).css('display', 'block');//设置属性
				}
				lists1[i].onmouseout=function(){
					 let x = this.getAttribute('index');
				$(".i2").eq(x).css('display', 'none');//设置属性
				$(".i1").eq(x).css('display', 'none');//设置属性
				$(".i3").eq(x).css('display', 'none');//设置属性
				}
			}
					