// 当前司机用户所属公司Id
var companyId = getQueryString("companyId");
//当前司机信息的Id
var driverId = getQueryString("driverId");
//当前司机用户所属公司名称
var companyName = getQueryString("companyName");

$(function(){
	// 获取当前用户所属公司车辆信息
	getJson();
	function getJson(){
		var url = getContextPath() + "vehicleInfo/chooseVehicleInfo?companyId="+companyId+"&driverId="+driverId;
		$.getJSON(url, function(data) {
			if(data.success){
				buildHtml(data);
			}
		});
	}
	
	function buildHtml(data) {
		
		var vehicleList = data.list;
	     
		if(vehicleList != null && vehicleList != ''){
			
			for(var i = 0;i<vehicleList.length;i++){
				
				// 生成下一个绑定控件
				$("#companysDiv").append('<a href="javascript:;" class="aui-flex comlist b-line vehicle">'
									+'<div class="aui-img-car">'
									+'<img src="../resources/images/list-ico/ico-car.png" alt="" />'
									+'</div>'
									+'<div class="aui-flex-box">'
									+'<p><i class="license">'+vehicleList[i].licensePlate+'</i></p>'
									+'<p> <em>车型：'+vehicleList[i].vehicleTypeName+'</em></p>'
									+'<input type="hidden" class="vehicleId" value="'+vehicleList[i].id+'">'
									+'</div>'
									+'<div class="aui-text"><i>核载：'+vehicleList[i].maxPayload+'吨</i><i>轴数：'+vehicleList[i].axles+'轴</i></div>'
									+'</a>');
				
			}
		}
    }
	
	/**
	 * 输入车牌号就进行查询
	 */
	$("#select").click(function(){
		doSearch();
	});
	
    $(document).on('click','.vehicle',function(){
    	
    	var vehicleId = $(this).find('.vehicleId').val();
    	var licensePlate = $(this).find('.license').text();
        var tip = '确认绑定：' + licensePlate + '吗？';
        
        $.confirm(tip,function(){
        	// 绑定
      	  	var url = getContextPath() + "driverVehicle/doAdd";
      	  	
      	  	var data = {
      	  		driverId: driverId,
      	  		vehicleId : vehicleId,
      	  		companyId : companyId,
      		}
        	
      	  	$.getJSON(url,data,function(data){
	      	  	if (data.success) {
		      	  	$.toast('绑定成功！');
		            setTimeout(function(){
		                location.href = getContextPath() + 'frontend/mytrucks?companyId='+companyId+"&driverId="+driverId;
		            },3000)
				} else {
					$.toast('保存失败！');
				}
      	  	});
        });
    });
    
    /**
	 * 为新增按钮绑定跳转事件
	 */
	$("#goAdd").click(function(){
		
		location.href = getContextPath() + "vehicleInfo/goAdd?companyId="+companyId+"&companyName="+companyName;
	});
    
	/**
	 * 为返回按钮绑定跳转事件
	 */
	$('.goBack').on('click',function(){
        location.href = getContextPath() + 'frontend/mytrucks?companyId='+companyId;
    });
    
});


/**
 * 查询车辆列表
 * @returns
 */
function doSearch(){
	// 如果没有输入关键字则提示
	/*var title = $("#title").val().toLocaleUpperCase();
	var validFlag = isVehicleNumber(title)
	
	if(!title||!validFlag){
		return
	}*/
	
	// 如果没有输入关键字则提示
	var title = $("#title").val().toLocaleUpperCase();
	
	if(!title){
		$.toast("请输入查询关键字！");
		return;
	}
	
	// 查询前清空上一次查询结果
	$(".comlist").remove();
	
	// 根据用户输入的关键字从tb_driver_info表查询车辆信息，并展示
	$.ajax({
		url:getContextPath()+"vehicleInfo/getByLicensePlate",
		data:{
			licensePlate:$("#title").val(),
			pageIndex:"-1",// 分页信息-1表示不分页
			pageSize:"-1",
		},
		type:"POST",
		dataType:"JSON",
		success:function(data){
			data = JSON.parse(data);
			if(data.success){
				// 车辆信息
				var vehicleList = data.list;
				
				var html = "";
				if(vehicleList != null && vehicleList != ''){
					
					for(var i = 0;i<vehicleList.length;i++){
						
						// popup模板内容
						var popTemplate = '<a href="javascript:;" class="aui-flex b-line vehicle">'
										+'<div class="aui-img-car">'
										+'<img src="../resources/images/list-ico/ico-car.png" alt="" />'
										+'</div>'
										+'<div class="aui-flex-box">'
										+'<p><i class="license">'+vehicleList[i].licensePlate+'</i></p>'
										+'<p> <em>车型：'+vehicleList[i].vehicleTypeName+'</em></p>'
										+'<input type="hidden" class="vehicleId" value="'+vehicleList[i].id+'">'
										+'</div>'
										+'<div class="aui-text"><i>核载：'+vehicleList[i].maxPayload+'吨</i><i>轴数：'+vehicleList[i].axles+'轴</i></div>'
										+'</a>'
										
						html += popTemplate;
					}
				}
				
				if ($.trim(html) == "") {
					$.toast("暂无相关车辆信息！");
				}else{
					$("#companysDiv").append(html);
				}
				
			}else if(data.success==false){
				$.toast("暂无相关车辆信息！");
			}
			
		}
	});
}

/**
 * 关闭车辆选择
 * @param 
 * @param 
 * @returns
 */
function closeVehicle(){
	$.closeModal("#vehicles");
	$("#title").val("");
	$(".comlist").remove();
	
	location.href = getContextPath() + 'frontend/mytrucks?companyId='+companyId+"&driverId="+driverId;
}

/**
 * 判断字符串是否为车牌号
 * @param str
 * @returns true false
 */
function isVehicleNumber(vehicleNumber) {
	
	var xreg=/^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-HJ-NP-Za-hj-np-z]{1}(([0-9]{5}[DF]$)|([DF][A-HJ-NP-Z0-9][0-9]{4}$))/;
 
	var creg=/^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-HJ-NP-Za-hj-np-z]{1}[A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9挂学警港澳]{1}$/;
 
	if(vehicleNumber.length == 7){
		return creg.test(vehicleNumber);
	} else if(vehicleNumber.length == 8){
		return xreg.test(vehicleNumber);
	} else{
		return false;
	}
}
