// 当前司机用户所属公司Id
var companyId = getQueryString("companyId");
//当前司机信息的Id
var driverId = getQueryString("driverId");
//当前司机用户所属公司名称
var companyName = getQueryString("companyName");

$(function(){
	// 获取车辆关系信息
	getJson();
	function getJson(){
		var url = getContextPath() + "driverVehicle/getDriverVehicleInfo?companyId="+companyId+"&driverId="+driverId;
		$.getJSON(url, function(data) {
			if(data.success){
				buildHtml(data);
			}
		});
	}
	
	function buildHtml(data) {
		
		var vehicleInfolist = data.vehicleInfolist;
	     
		if(vehicleInfolist != null && vehicleInfolist != ''){
			
			for(var i = 0;i<vehicleInfolist.length;i++){
				var info = $("#upload").html();
				$("#upload").html("");
				// 生成下一个绑定控件
				$("#upload").append('<a href="#" class="aui-info-box-item unbind" style="position: relative;">'
		                			+'<div class="aui-img-sms">'
		                			+'<img src="../resources/images/truc1.png" alt="">'
		                			+'</div>'
		                			+'<div class="aui-flex">'
		                			+'<h3 class="license">'+vehicleInfolist[i].licensePlate+'</h3>'
		                			+'<input type="hidden" class="vehicleId" value="'+vehicleInfolist[i].id+'">'
		                			+'</div>'
		                			+(vehicleInfolist[i].bindStatus == "1"?'<div class="j-ico"></div>':"")
		                			+'<button class="sc" data-license="'+vehicleInfolist[i].licensePlate+'" data-id="'+vehicleInfolist[i].id+'"></button>'
		                			+'</a>');
				
				var info1 = $("#upload").html();
				$("#upload").html(info1+info);
			}
		}
    }
	
	
    $('#goBack').on('click',function(){
        location.href = getContextPath() + 'frontend/personal';
    });

    $(document).on('click','.goBind',function(){
        location.href = getContextPath() + 'frontend/vehicleBind?companyId='+companyId+"&driverId="+driverId+"&companyName="+companyName;
    });
    
    var isCallParent = true;//是否调用父级事件
   
    
    $(document).on('click','.sc',function(event){
    	//event.stopPropagation();//阻止向父级冒泡
    	
    	var vehicleId = $(this).attr('data-id');
    	var licensePlate = $(this).attr('data-license');
        var tip = '确认解绑：' + licensePlate + '吗？';
        isCallParent = false;
        $.confirm(tip,function(){
        	// 解绑
      	  	var url = getContextPath() + "driverVehicle/deleteByVehicleId";
      	  	
      	  	var data = {
      	  		vehicleId : vehicleId,
      	  		driverId : driverId,
      		}
        	
      	  	$.getJSON(url,data,function(data){
	      	  	if (data.success) {
	      	  		
		      	  	$.toast('解绑成功！');
		            setTimeout(function(){
		                location.href = getContextPath() + 'frontend/mytrucks?companyId='+companyId+"&driverId="+driverId;
		                
		                //getContextPath() + 'frontend/mytrucks?companyId='+companyId+"&driverId="+driverId;
		                //$.router.load(url);
		            },500)
				} else {
					$.toast('解绑失败！');
					return false;
				}
      	  	});
        },function(){return false;});
        
        return false;
    });
    
    $(document).on('click','.unbind',function(){
    	if(isCallParent){
    		
    		var vehicleId = $(this).find('.vehicleId').val();
    		var license = $(this).find('.license').text();
    		var tip = '是否将'+ license + '设置为默认？';
    		
        	$.confirm(tip,function(){
        		// 设为默认
          	  	var url = getContextPath() + "driverVehicle/doUpdate";
          	  	
          	  	var data = {
          	  		vehicleId : vehicleId,
          	  		status : "1",
          	  		companyId : companyId,
          	  		driverId : driverId,
          		}
            	
          	  	$.getJSON(url,data,function(data){
    	      	  	if (data.success) {
    	      	  		
    		      	  	$.toast('设置成功！');
    		      	    location.href = getContextPath() + 'frontend/mytrucks?companyId='+companyId+"&driverId="+driverId+"&companyName="+companyName;
    		            
    				} else {
    					$.toast('设置失败！');
    					return false;
    				}
          	  	});
        	});
    	}
    	isCallParent = true;
    });

});

