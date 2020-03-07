var listTemplate; // 合同html模板
var licensePlate = getQueryString("licensePlate"); // 当前登录车牌号

$(function() {
	// 获取到html并清除content中的模板内容
	listTemplate = $("#content").html();
	$("#content").empty();

	// 获取记录信息
	getJson();
	
	/**
	 * 跳过
	 */
	$("#skip").click(function(){
		window.location.href = getContextPath() + "appLogin/index?licensePlate="+licensePlate;
	});

	$("#dobind").click(function(){
		phonebind();
	});
	
	// 清空缓存
	$("#clearcache").click( function() {
		navigator.app.clearCache();
		$.toast("清理缓存完成");
	});
	
	// 退出应用
	$(".exit").click(function(){
		$.confirm("确定退出吗？",function(){
			navigator.app.exitApp();
		});
	});

	/**
	 * 侧边栏-设置
	 */
	$("#settings").click(function(){
		$.openPanel("#panel-js-demo");
	});

	/**
	 * 解除设备绑定
	 */
	$("#unbind").click(function(){
		$.confirm("确定解除绑定吗？",function(){
			// 获取设备信息
			var deviceInfo = mclient.getLocalUniqueIdentifier();
			deviceInfo = JSON.parse(deviceInfo);
			$.ajax({
				url:getContextPath()+"phonebind/unbind",
				data:{
					id : deviceInfo.deviceId,
					/*model : deviceInfo.model,
					product : deviceInfo.product,
					licensePlate : licensePlate*/
				},
				dataType:"JSON",
				type:"POST",
				success:function(data){
					data = JSON.parse(data);
					if(data.success){
						$.toast("解除绑定成功！");
						//location.href = getContextPath()+"login.html";
					}else{
						$.toast(data.errMsg);
					}
				}
			});
		})
	});
});

/**
 * 获取合同信息列表
 * @returns
 */
function getJson(){
	if(!licensePlate){
		$.toast("未获取到车牌号信息，请退出重新登录！");
		var cont= '<div align="center" style="margin-top: 20%;"><img style="width:100px;position: absolute;left: 50%;margin-left: -50px;" src="../resources/images/nodata.png"/></div>';
		$("#content").html(cont);
		return;
	}

	var url = getContextPath() + "dispatchingInfo/getPurchaseByLicensePlate?licensePlate="+licensePlate;
	$.post(url, function(data) {
		//data = JSON.parse(data);
		if(data.success){
			buildHtml(data);
		}else{
			$.toast("获取信息失败！");
			var cont= '<div align="center" style="margin-top: 20%;"><img style="width:100px;position: absolute;left: 50%;margin-left: -50px;" src="../resources/images/nodata.png"/></div>';
			$("#content").html(cont);
		}
	});
}

// 构建content中的html
function buildHtml(json){
	var list = json.list;
	var html = "";
	for(var i =0;i<list.length;i++){
		var serviceTime = new Date(list[i].serviceTime).Format("yyyy-MM-dd");
		html += "<a style=\"display: flex\" class=\"aui-flex b-line\" onclick=\"$('#id').val('"+list[i].dispatchingId+"')\">"
				+"<div class=\"aui-flex-box\">"
				+"<p>售卖方："+(list[i].sellerName?list[i].sellerName:"")+"</p>"
				+"<p>买受方："+(list[i].buyerName?list[i].buyerName:"")+"</p>"
				+"<p>物料种类："+(list[i].goodsTypeName?list[i].goodsTypeName:"")+"</p>"
				+"<p>收货地点："+(list[i].receiptPlace?list[i].receiptPlace:"")+"</p>"
				+"<span>司机："+(list[i].passenger?list[i].passenger:"")+"</span> <span>用车时间："+(serviceTime?serviceTime:"")+"</span>"
				+"</div>"
				+"</a>";
	}
	
	// 如果没有获取到数据，显示‘没有数据’图片
	if($.trim(html)==""){
		var cont= '<div align="center" style="margin-top: 20%;"><img style="width:100px;position: absolute;left: 50%;margin-left: -50px;" src="../resources/images/nodata.png"/></div>';
		$("#content").html(cont);
		return;
	}
	
	$("#content").html(html);
	$("#content").show();
}

/**
 * 跳转首页
 * @returns
 */
function goDispatching(){
	var id = $("#id").val();
	if(id==null||id ==""){
		$.toast("请先选择派单");
		return;
	}
	//window.location.href = getContextPath() + "dispatchingInfo/init?id="+id;
	window.location.href = getContextPath() + "appLogin/index?dispatchingId="+id+"&licensePlate="+licensePlate;
	
}

/**
 * 手机绑定车牌
 * @returns
 */
function phonebind(){
	$.confirm("是否绑定当前设备？",function(){
		if(!licensePlate){
			$.toast("请先登录！");
			return
		}
		var deviceInfo = mclient.getLocalUniqueIdentifier();
		deviceInfo = JSON.parse(deviceInfo);
		
		if($.isEmptyObject(deviceInfo)){
			$.toast("未获取到当前设备信息！");
			return;
		}
		doBind(deviceInfo);
		
	});
}

/**
 * 设备绑定
 * @returns
 */
function doBind(deviceInfo){
	$.ajax({
		url:getContextPath()+"phonebind/bind",
		type:"POST",
		dataType:"JSON",
		data:{
			id : deviceInfo.deviceId,
			model : deviceInfo.model,
			product : deviceInfo.product,
			licenseplate : $('#licensePlate').text()
		},
		success:function(data){
			data = JSON.parse(data);
			if(data.success){
				$.toast("设备绑定成功！")
			}else{
				$.toast(data.errMsg);
			}
		}
		
	});
}
