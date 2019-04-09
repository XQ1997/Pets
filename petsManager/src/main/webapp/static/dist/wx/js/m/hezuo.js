$(function(){

	$(".j-hezuo-form").Validform({
		ajaxPost:false,
		tiptype:function(msg,o,cssctl,s){
			if(o.type == 3){
				layer.msg(msg,{
					time: 1000
				})
			}
		}
	})

	$(".j-hehuo-form").Validform({
		ajaxPost:false,
		tiptype:function(msg,o,cssctl,s){
			if(o.type == 3){
				layer.msg(msg,{
					time: 1000
				})
			}
		}
	})
});