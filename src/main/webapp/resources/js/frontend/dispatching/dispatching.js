var listTemplate; // 派单html模板
var licensePlate = "";
var disId = "";
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
		check(licensePlate);
	});
});

function getJson(){
	var url = getContextPath() + "dispatchingInfo/getDiapatchingInfoForAppJNById?id="+getQueryString("id");
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
	var obj = json.dispatchingInfo;
	disId = obj.id;
	licensePlate=obj.licensePlate;
	for(var prop in obj){
		var reg = "/{\{3}"+prop+"}\{3}/g";
		if("paymentTime"==prop){
			var paymentTime = new Date(obj[prop]).Format("yyyy-MM-dd");
			listTemplate =listTemplate.replace(eval(reg),paymentTime);
			
		}else{
			listTemplate =listTemplate.replace(eval(reg),obj[prop]);
		}
		
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

function goVehicle(licensePlate){
	location.href = getContextPath() + "vehicleInfo/init?licensePlate="+licensePlate+"&id="+disId;
}
function scanQRCode(){
	mclient.scanQRCode();
}
function sendScanMessage(arg){
	var argArr = arg.split("&");
	if(argArr!=null&&argArr.length==2){
		$.ajax({
			url : getContextPath()+"tbWeighingRecord/save",
			type : 'POST',
			data : {"companyCode":argArr[0],"weighbridgeCode":argArr[1],"vehicleCode":licensePlate,"dispatchingId":disId},
			dataType:"json",
			success : function(data) {
				if(data.success){
					$("#company").val(data.company);
					$("#bangcheng").val(data.bangcheng);
					$("#id").val(data.id);
					 $.popup('.popup');
				}else{
					$.toast("识别失败");
				}
			}
		});
	}
}
function check(licensePlate){
	$.ajax({
		type:"post",
		url: getContextPath() + "dispatchingInfo/checkDispatching",
		data:{licensePlate:licensePlate},
		dataType:"json",
		success:function(result){
			var data = result;
			var checked = data.success;
			if(checked){
				var count = data.count;
				if(count==1){
					window.location.href = getContextPath() + "dispatchingInfo/init?id="+data.dispatchingInfo.id;
				}else{
					window.location.href =getContextPath() + "dispatchingInfo/choose?licensePlate="+licensePlate;
				}
			}else{
				alert("输入的车牌没有派单信息！请核对合输入。");
			}
		}
	})
}