/**
 * 主页JS
 * 
 * 1. 进入页面后先查找是否有正在进行的称重（在tb_pound_records表中查找有第一次称重但没有第二次称重信息的记录）
 * 2. 如果有正在进行的称重，直接显示未完成的称重
 * 3. 如果没有正在进行的称重，扫磅秤上的二维码来生成称重记录
 */

// 当前登录车牌号
var licensePlate = getQueryString("licensePlate");
// 数据集
var info = {
		"licensePlate":licensePlate
	}
// 派单ID
var dispatchingId = getQueryString("dispatchingId");

// 标识是否为第一次称重
var isSecond = false;

$(function(){
	$("#licensePlate").text(licensePlate);
	// 打开页面后，查询是否有正在进行的派单，如果有，直接显示未完成的称重记录
	/*$.ajax({
		url:getContextPath()+"poundrecord/getUnderWay",
		data:{
			licensePlate : licensePlate,
		},
		dataType:"JSON",
		type:"GET",
		success:function(data){
			data = JSON.parse(data);
			if(data.success){
				if(data.list.length == 1){
					buildUnderWay(data); // 填充称重信息
					dispatchingId = data.list[0].dispatchingId; // 获取当前派单ID
					info.dispatchingId = dispatchingId;
					$("#poundId").val(data.list[0].id); // 当前称重记录ID
					showDetail(); // 根据派单ID显示派单（承运）信息
					nothing = false; // 标识当前已有派单信息
				}
			}else{
				// 没有获取到数据，称重记录显示“暂无”
				$("#no-state").css("display","flex");
			}
		}
	});*/
	
	// 展示承运信息
	showDetail();
	showPoundRecord();
	
	/**
	 * 点击扫描二维码按钮触发
	 */
	$("#scan").click(function(){
		if($("#second-time").text()){
			$.toast("已完成称重，无需再次扫描！");
			return;
		}
		// 从二维码中获取涉煤企业编号
		mclient.scanQRCode();
		
		// 如果没有正在进行的派单，就去查还未进行的派单
		/*if(nothing){
			// 查出在该煤企下最早一笔未完成的派单，作为当前默认派单；展示派单详情
			$.ajax({
				url:getContextPath()+"dispatchingInfo/getUnderWay",
				data:{
					licensePlate : licensePlate,
					coalRelatedCode : info.coalRelatedCode
				},
				dataType:"JSON",
				type:"GET",
				success : function(data){
					data = JSON.parse(data);
					if(data.success){
						buildHtml(data);
						dispatchingId = data.map.dispatchingId;
						uploadInfo();
						//saveWeightRecord();
					}else{
						$.hidePreloader();
						$.toast("没有派单信息");
					}
				}
			});
		}else{
			saveWeightRecord();
		}*/
	});
	
	/**
	 * 点击承运历史按钮触发
	 */
	$("#goHistory").click(function(){
		location.href = getContextPath()+"dispatchingInfo/historylist?licensePlate="+licensePlate+"&dispatchingId="+dispatchingId;
	});
	
	/**
	 * 点击结束按钮触发
	 * 用于手动结束正在进行的派单
	 */
	$("#over").click(function(){
		if(!$("#poundId").val()){
			$.toast("无当前作业信息！");
			return;
		}
		
		$.confirm("确定手动结束当前作业吗？",function(){
			$.ajax({
				//url:getContextPath()+"dispatchingInfo/doOff",
				url:getContextPath()+"poundrecord/doOff",
				data:{
					//dispatchingId:dispatchingId,
					id : $("#poundId").val(),
				},
				dataType:"JSON",
				type:"GET",
				success:function(data){
					data = JSON.parse(data);
					if(data.success){
						$.alert("结束成功！",function(){
							location.reload();
						});
					}else{
						$.toast(data.errMsg);
					}
				}
			});
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
	
	/**
	 * 登出并跳转至登录页，不进行绑定自动跳转
	 */
	$("#logout").click(function(){
		//location.href = "./login.html?notauto=true"
		
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
	$("#exit").click(function(){
		$.confirm("确定退出吗？",function(){
			navigator.app.exitApp();
		});
	});
});

/**
 * 构建页面
 * @param data 
 * @returns
 */
function buildHtml(obj){
	
	if(!$.isEmptyObject(obj)){
		$("#buyerName").text(obj.buyer_name);
		$("#sellerName").text(obj.seller_name);
		$("#contractTypeCode").text(getContractType(obj.contract_type_code));
		$("#goodsTypeName").text(obj.goods_type_name);
	}
	
	$("#cyxx").show();
}

/**
 * 构建正在进行的称重记录页面
 * @param data
 * @returns
 */
function buildUnderWay(data){
	var date = new Date(data.result.firstTime).Format("yyyy-MM-dd hh:mm");
	$("#no-state").hide();
	$("#empty-weight").css("display","flex");
	$("#first-time").text(date);
	$("#first-weight").text(data.result.firstWeight);
	
	isSecond = true;
}

/**
 * 保存一条称重记录
 * @returns
 */
function saveWeightRecord(){
	var status = $("#first-time").text();
	
	$.ajax({
		url : getContextPath() + "tbWeighingRecord/save",
		type : 'POST',
		data : {
			dispatchingId:dispatchingId,
			companyCode : info.coalRelatedCode,
			weighbridgeCode : info.weightBridgeCode,
			vehicleCode : info.licensePlate,
			status : status?"1":"0", // 如果有第一次称重记录，那么这次保存称重后，当前派单应记录为完成
		},
		dataType : "json",
		success : function(data) {
			if (data.success) {
				getWeight(data.weightRecordId);
			}else{
				$.toast(data.errMsg);
			}
			$.hidePreloader();
		}
	});
}

/**
 * 获取称重信息
 * @param weightRecordId
 * @returns
 */
function getWeight(weightRecordId){
	// 判断空车称重信息是否有数据
	var isFirst = $("#first-weight").text()?false:true;
	
	// 获取称重并展示
	$.ajax({
		url : getContextPath() + "tbWeighingRecord/getWeight",
		type : 'POST',
		data : {
			id : weightRecordId,
			poundId : $("#poundId").val()
		},
		dataType : "json",
		success : function(data) {
			if (data.success) {
				$("poundId").val(data.poundId);
				// 日期格式处理
				var date = new Date(data.result.createDate).Format("yyyy-MM-dd hh:mm");
				
				// 展示称重
				// 如果是第一次称重，就填到空车称重信息，否则填到载货称重信息
				if(isFirst){
					$("#empty-weight").css("display","flex");
					$("#first-time").text(date);
					$("#first-weight").text(data.result.weight);
				}else{
					$("#fillin-weight").css("display","flex");
					$("#second-time").text(date);
					$("#second-weight").text(data.result.weight);
				}
				$("#no-state").hide();
			}else{
				$.alert(data.errMsg);
			}
			$.hidePreloader();
		}
	});
}

/**
 * 显示详细信息
 * @returns
 */
function showDetail(){
	if(dispatchingId){
		$.ajax({
			url : getContextPath()+"dispatchingInfo/getDetail",
			type : "POST",
			data : {
				dispatchingId:dispatchingId
			},
			dataType : "JSON",
			success : function(data){
				data = JSON.parse(data);
				if(data.success){
					buildHtml(data.purchase);
				}
			}
		});
	}
	
}

/**
 * 把数据上传到缓存中
 * @returns
 */
function uploadInfo(realKey){
	$.showPreloader();

	var value = JSON.stringify({
			licensePlate: licensePlate,
			dispatchingId:dispatchingId?dispatchingId:"",
		});
	
	$.ajax({
		url:getContextPath()+"dealMsg/setdata",
		data:{
			key:realKey,
			value : value,
		},
		dataType:"JSON",
		type:"POST",
		success : function(data){
			data = JSON.parse(data);
			if(data.success){
				var date = new Date().Format("yyyy-MM-dd hh:mm");
				if(isSecond){
					$("#second-time").text(date);
					$("#fillin-weight").css("display","flex");
				}else{
					$("#first-time").text(date);
					$("#empty-weight").css("display","flex");
				}

				$.hidePreloader();

				$.toast("扫描成功，运营人员正在处理！");
			}else{
				$.hidePreloader();

				$.alert("信息上传失败！请重新扫描二维码");
			}
		},
		complete:function(){
			$.hidePreloader();
		}
	});
}

/**
 * 展示称重记录
 * @returns
 */
function showPoundRecord(){
	$.ajax({
		url:getContextPath()+"poundrecord/getUnderWay",
		data:{
			licensePlate : licensePlate
		},
		dataType:"JSON",
		type:"GET",
		success:function(data){
			data = JSON.parse(data);
			if(data.success){
				$("#poundId").val(data.result.id);
				buildUnderWay(data); // 填充称重信息
			}else{
				// 没有获取到数据，称重记录显示“暂无”
				$("#no-state").css("display","flex");
			}
		}
	});
}

/**
 * 返回数据
 * @param arg
 * @returns
 */
function getScanMessage(arg){
	$.showPreloader('数据处理中，请稍后...');
	
	$.ajax({
		url: getContextPath() + "dealMsg/checkCache",
		data:{
			key : arg
		},
		dataType:"TEXT",
		type:"GET",
		success:function(data){
			data = JSON.parse(data);
			if(data.success){
				// 上传当前车牌号及派车单到缓存中
				uploadInfo(data.realKey);
				$("#no-state").hide();
				$("#empty-weight").css("display","flex");
			}else{
				$.hidePreloader();
				$.toast(data.errMsg);
			}
		},
		complete:function(){
			$.hidePreloader();
		}
	});
	
}

/**
 * 分析并返回合同类型
 * @param type
 * @returns
 */
function getContractType(type){
	switch(type){
		case "0":
			return "普通购销合同";
		case "1":
			return "原煤调拨合同";
		case "2":
			return "排矸合同";
		case "3":
			return "代加工合同";
		default :
			return "";
	}
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
