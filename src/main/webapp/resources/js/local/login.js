var licensePlateList = localStorage.getItem("licensePlateList")==null?[]:JSON.parse(localStorage.getItem("licensePlateList"));
jQuery.noConflict();
Array.prototype.indexOf = function(val) { 
	for (var i = 0; i < this.length; i++) { 
		if (this[i] == val) return i; 
	} 
	
	return -1; 
};
Array.prototype.remove = function(val) { 
	var index = this.indexOf(val); 
	if (index > -1) { 
		this.splice(index, 1); 
	} 
};

var unBind = true;
//var deviceInfo = mclient.getLocalUniqueIdentifier();

$(function() {
	init();
	
	checkBind();
	function checkBind(){/*
		
		deviceInfo = JSON.parse(deviceInfo);
		$.ajax({
			url: getContextPath() + "phonebind/check",
			data:{
				id:deviceInfo.deviceId
			},
			type:"POST",
			dataType:"JSON",
			success:function(data){
				data = JSON.parse(data);
				if(data.success){
					unbind = false;
					check(data.licencePlate)
				}
			}
		})
	*/}
		
	$('#submit').click(function() {
		// 获取输入的车牌
		var licensePlate = $('#licensePlate').val();
		if(licensePlate==null||licensePlate==""){
			$.toast("请输入车牌");
			return;
		}
		//phonebind();
		/*if(localStorage.getItem("licensePlateList")!=null&&localStorage.getItem("licensePlateList").indexOf(licensePlate)>0){
			licensePlateList.remove(licensePlate);
			licensePlateList.push(licensePlate);
		}else{
			if(licensePlateList.length>=8){
				licensePlateList.shift();
			}
			
			licensePlateList.push(licensePlate);
		}
		localStorage.setItem("licensePlateList",JSON.stringify(licensePlateList));
		licensePlateList=JSON.parse(localStorage.getItem("licensePlateList"));*/
		check(licensePlate);
		init();
	});
	
	/**
	 * 设备注册
	 */
	/*$("#deviceBind").click(function(){
		if(!$('#licensePlate').val()){
			$.alert("请先输入车牌号！")
			return
		}
		var deviceInfo = mclient.getLocalUniqueIdentifier();
		deviceInfo = JSON.parse(deviceInfo);
		if($.isEmptyObject(deviceInfo)){
			$.alert("未获取到当前设备信息！");
			return;
		}
		$.ajax({
			url:getContextPath()+"phonebind/bind",
			type:"POST",
			dataType:"JSON",
			data:{
				id : deviceInfo.deviceId,
				model : deviceInfo.model,
				product : deviceInfo.product,
				licenseplate : $('#licensePlate').val()
			},
			success:function(data){
				if(data.success){
					$.alert("设备绑定成功！如需解绑，请。。。")
				}else{
					$.alert(data.errMsg);
				}
			}
			
		});
		
	});*/
	
	$("#licensePlate").keyup(function(){
		make2DCode();
	});
});
function init(){
	if(licensePlateList==null){
		licensePlateList=[];
	}
	$("#ulId").empty();
	var ulhtml ="";
	for(var i = 0;i<licensePlateList.length;i++){
		var html = "<li style='margin-left:30px;line-height:30px' onclick='$(\"#licensePlate\").val(\""+licensePlateList[i]+"\")'>"+licensePlateList[i]+"</li>";
		$('#ulId').prepend(html);
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
				/*if(count==1){
					window.location.href = getContextPath() + "dispatchingInfo/init?id="+data.dispatchingInfo.id;
				}else{
					window.location.href =getContextPath() + "dispatchingInfo/choose?licensePlate="+licensePlate;
				}*/
				location.href = getContextPath()+"show/index.html";
			}else{
				$.toast("输入的车牌没有派单信息！请核对合输入。");
			}
		}
	})
}

//生成二维码
function make2DCode() {
	var licensePlate = $('#licensePlate').val();
	/*if(licensePlate==null||licensePlate==""){
		$.toast("请输入车牌");
		return;
	}*/
	$("#qrcode").html("");//清空二维码
	jQuery('#qrcode').qrcode({
		render:"canvas",
		width: "180",
		height: "180",
		correctLevel:0,
		text: utf16to8(licensePlate)
	}); 
}
//支持中文
function utf16to8(str) { 
	var out, i, len, c; 
	out = ""; 
	len = str.length; 
	for(i = 0; i < len; i++) { 
		c = str.charCodeAt(i); 
		if ((c >= 0x0001) && (c <= 0x007F)) { 
			out += str.charAt(i); 
		} else if (c > 0x07FF) { 
			out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F)); 
			out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F)); 
			out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F)); 
		} else { 
			out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F)); 
			out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F)); 
		} 
	} 
	return out; 
}

/**
 * 手机绑定车牌
 * @returns
 */
function phonebind(){
	if(unBind){
		$.confirm("是否绑定当前设备？",function(){
			if(!$('#licensePlate').val()){
				$.toast("请先输入车牌号！")
				return
			}
			var deviceInfo = mclient.getLocalUniqueIdentifier();
			deviceInfo = JSON.parse(deviceInfo);
			if($.isEmptyObject(deviceInfo)){
				$.toast("未获取到当前设备信息！");
				return;
			}
			doBind();
			
		});
	}
}

function doBind(){
	$.ajax({
		url:getContextPath()+"phonebind/bind",
		type:"POST",
		dataType:"JSON",
		data:{
			id : deviceInfo.deviceId,
			model : deviceInfo.model,
			product : deviceInfo.product,
			licenseplate : $('#licensePlate').val()
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