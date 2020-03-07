$(function(){
	//当前登录人的身份证号
	var citizenNo;
	//当前登录用户的用户ID
	var userId;
	
	// 个人信息展示
	$.ajax({
		url:getContextPath()+"frontend/getMyInfo",
		dataType:"JSON",
		cache:false,
		type:"GET",
		async:false,
		success:function(data){
			data = JSON.parse(data);
			if(data.success){
				
				citizenNo = data.tSBaseUser.idCord;
				userId = data.tSBaseUser.id;
			}
		}
	});
	
	// 获取记录信息
	getJson();
	function getJson(){
		var url = getContextPath() + "driverInfo/getDriverInfoByIdCord?citizenNo="+citizenNo;
		$.getJSON(url, function(data) {
			if(data.success){
				var driverInfo = data.driverInfo; // 司机信息
				$("#bindCompany").text(driverInfo.companyName);
			}
		});
	}
	
    $('.goBack').on('click',function(){
        location.href = getContextPath() + 'frontend/personal';
    });

    $('#unbind').on('click',function(){
        $.confirm('确定解除绑定吗？',function(){
        	var url = getContextPath() + "driverInfo/unbindDriverInfo?userId="+userId;
    		$.getJSON(url, function(data) {
    			if(data.success){
    				$.toast('解除成功！');
    	            setTimeout(function(){
    	                location.href = getContextPath() + 'frontend/chooseCompany';
    	            },3000);
    			}else{
    				$.toast('解除失败！');
    			}
    		});
        });
    });
});