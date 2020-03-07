//当前登录用户的用户ID
var userId;
//当前司机用户所属公司名称
var companyName = "";
//当前司机用户所属公司Id
var companyId = "";
//当前登录用户信息的数据状态
var checkStatu = "";
//当前司机信息的Id
var driverId = "";
//当前登录人的身份证号
var citizenNo;

$(function(){
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
				//预设姓名
				$("#name").text(data.tSBaseUser.realName?data.tSBaseUser.realName:"");
				
				userId = data.tSBaseUser.id;
			}
		}
	});
	
	// 获取当前登录用户的司机信息
	getJson();
	function getJson(){
		var url = getContextPath() + "driverInfo/getDriverInfoByIdCord?citizenNo="+citizenNo;
		$.getJSON(url, function(data) {
			if(data.success){
				//当前登录用户信息的数据状态
				checkStatu = data.driverInfo.checkStatu?data.driverInfo.checkStatu:"";
				//当前司机用户所属公司名称
				companyName = data.driverInfo.companyName?data.driverInfo.companyName:"";
				//当前司机用户所属公司Id
				companyId = data.driverInfo.companyId?data.driverInfo.companyId:"";
				//当前司机信息的Id
				driverId = data.driverInfo.id?data.driverInfo.id:"";
				
				if(checkStatu == "1"){
					$("#driverinfoState").text("已核对");
				}else if(checkStatu == "0"){
					$("#driverinfoState").text("未核对");
				}else{
					$("#driverinfoState").text("未提交");
				}
			}else{
				$("#driverinfoState").text("未提交");
			}
		});
	}
	
    /**
     * 跳转作业界面
     */
    $('#gowork').on('click',function(){
        location.href = getContextPath() + 'frontend/index';
    });

    /**
     * 跳转电子单页面
     */
    $('#e-bill').on('click',function(){
        location.href = getContextPath() + 'frontend/waybill';
    }); 

    /**
     * 跳转司机信息详情页面
     */
    $('#godriverinfo').on('click',function(){
        location.href = getContextPath() + 'driverInfo/detail';
	});
	
	/**
     * 跳转订单页面
     */
    $('#dispatch-bill').on('click', function () {
        location.href = getContextPath() + 'tbDispatchBill/list';
    });
    
    /**
     * 跳转承运关系页面
     */
    $('#gobind').on('click',function(){
    	if(checkStatu == "1"){
    		if(!isEmpty(companyName)){
    			location.href = getContextPath() + 'frontend/relation';
    		}else{
    			location.href = getContextPath() + 'frontend/chooseCompany';
    		}
    	}else{
    		$.toast("请等待司机信息核对通过后，再进行承运关系的操作！");
    	}
    });

    /**
     * 跳转车辆关系页面
     */
    $('#gorelation').on('click',function(){
    	if(checkStatu == "1" && !isEmpty(companyName)){
    		location.href = getContextPath() + 'frontend/mytrucks?companyId='+companyId+"&driverId="+driverId+"&companyName="+companyName;
    	}else{
    		$.toast("请在承运关系中绑定完公司后，再进行车辆关系的操作！");
    	}
    });
    /**
     * 修改密码
     */
	$("#editPassword").click(function(){
		location.href = getContextPath()+"frontend/editPassword";
	});
	
	// 清空缓存
	$("#clearcache").click( function() {
		$.toast("清理缓存完成");
		navigator.app.clearCache();
	});

    /**
     * 注销登录
     */
    $('#logout').click(function() {
		// 清除session
		$.ajax({
			url : getContextPath()+"driverlogin/logout",
			type : "post",
			async : false,
			cache : false,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					// 清除成功后退出到登录界面
					window.location.href = getContextPath()+"driverlogin/login";
					return false;
				}
			},
			error : function(data, error) {
				$.alert(error);
			}
		});
	});
});

