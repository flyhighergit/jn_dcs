// 当前派车单ID
var dispatchingId = getQueryString("dispatchingId");

// 当前车辆车牌号
var licensePlate = getQueryString("licensePlate");

//详情内容模板
var template = $("#content").html(); 

$(function(){
	//加载数据
	$("#licensePlate").text(licensePlate);
	$.ajax({
		url:getContextPath()+"dispatchingInfo/getDetail",
		data:{
			dispatchingId : dispatchingId
		},
		dataType:"JSON",
		type:"GET",
		success:function(data){
			data = JSON.parse(data);
			if(data.success){
				buildHtml(data);
			}else{
				$.toast(data.errMsg);
				var cont= '<div align="center" style="margin-top: 20%;"><img style="width:100px;position: absolute;left: 50%;margin-left: -50px;" src="../resources/images/nodata.png"/></div>';
				$("#content").html(cont);
			}
		}
	});
	
	/**
	 * 返回按钮触发
	 */
	$("#goBack").click(function(){
		location.href = getContextPath()+"dispatchingInfo/historylist?licensePlate="+licensePlate;
	});
	
});


/**
 * 构建页面
 * @param data 
 * @returns
 */
function buildHtml(data){
	var obj = data.map;
	
	if(obj){
		for(prop in obj){
			template = template.replace("content-"+prop,obj[prop]);
		}
		
		$("#content").html(template);
	}else{
		$.toast("未获取到派单信息！")
		var cont= '<div align="center" style="margin-top: 20%;"><img style="width:100px;position: absolute;left: 50%;margin-left: -50px;" src="../resources/images/nodata.png"/></div>';
		$("#content").html(cont);
	}
	
}




