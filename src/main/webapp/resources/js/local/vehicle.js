var listTemplate; // 承运合同html模板
var licensePlate = getQueryString("licensePlate");
var id = getQueryString("id");
$(function() {
	// 获取到html并清除content中的模板内容
	listTemplate = $("#content").html();
	$("#content").empty();
	
	// 获取记录信息
	getJson();
	/**
	 * 为编辑按钮绑定跳转事件
	 */
/*	$("#goEdit").click();*/
	/**
	 * 为后退键绑定返回事件
	 */
	$("#goBack").click(function(){
		window.location.href = getContextPath() + "dispatchingInfo/init?id="+id;
	});
});
function getJson(){
	var url = getContextPath() + "vehicleInfo/getInfomationForAppJN?licensePlate="+getQueryString("licensePlate");
	$.post(url, function(data) {
		if(data.success){
			buildHtml(data);
			$("#content").show();
		}else{
			$.toast("获取信息失败！");
			var cont= '<div align="center" style="margin-top: 20%;"><img style="width:100px;position: absolute;left: 50%;margin-left: -50px;" src="../resources/images/nodata.png"/></div>';
			$("#content").html(cont);
		}
	});
}



// 构建content中的html
function buildHtml(json){
	var obj = json.vehicleInfo;
	for(var prop in obj){
		var reg = "/{\{3}"+prop+"}\{3}/g";
		listTemplate =listTemplate.replace(eval(reg),obj[prop]);
	}
	
	// 如果没有获取到数据，显示‘没有数据’图片
	if($.trim(listTemplate)==""){
		var cont= '<div align="center" style="margin-top: 20%;"><img style="width:100px;position: absolute;left: 50%;margin-left: -50px;" src="../resources/images/nodata.png"/></div>';
		$("#content").html(cont);
		return;
	}
	
	$("#content").html(listTemplate);
	$("#content").show();
	// 如果有合同照片的路径，就显示出预览按钮
	/*if(!isEmpty(obj.annexPath)){
		listTemplate = listTemplate.replace("content-annexPath",obj.annexPath);
		$("#upload").show();
	}*/
}
/**
 * 预览图片
 * @param path
 * @returns
 */
function preview(path){
	alert("123");
}
