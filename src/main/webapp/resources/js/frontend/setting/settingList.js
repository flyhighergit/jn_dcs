$(function() {
	// 个人信息展示
	$.ajax({
		url:getContextPath()+"frontend/getCurrentUserInfo",
		dataType:"JSON",
		cache:false,
		type:"GET",
		async:false,
		success:function(data){
			data = JSON.parse(data);
			if(data.success){
				$("#user").text(data.user.realName);
			}
		}
	});
	
	/**
	 * 返回事件
	 */
	$("#back").click(function(){
		location.href = getContextPath()+"frontend/index";
	});
	
	// 我的企业信息按钮
	$("#myCoalRelatedInfo").click(function(){
		location.href = getContextPath()+"coalRelated/myComInfo?isCheck=false";
	});
	
	//个人信息
	$("#myInfo").click(function(){
		location.href = getContextPath()+"frontend/myInfo";
	});
	
	//修改密码
	$("#editPassword").click(function(){
		location.href = getContextPath()+"frontend/editPassword";
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