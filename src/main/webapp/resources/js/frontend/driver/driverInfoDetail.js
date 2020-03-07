//数据状态
var checkStatu;
//当前登录人的身份证号
var citizenNo;
//当前登录用户的用户ID
var userId;
//当前司机用户所属公司
var companyName = "";

$(function() {
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
				//预设姓名
				$("#name").text(data.tSBaseUser.realName?data.tSBaseUser.realName:"");
				
				citizenNo = data.tSBaseUser.idCord;
				//预设身份证号
				$("#idCord").text(citizenNo);
				//预设驾驶证号
				$("#licenseNumber").text(citizenNo);
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
				buildHtml(data);
			}else{
				$("#goEdit").css("display","flex");
				stringToDate(citizenNo);
			}
		});
	}
	
	// 构建content中的html
	function buildHtml(json){
		var driverInfo = json.driverInfo; // 司机信息
		//var sfzlist = json.sfzList; // 身份证证件
		//var jszlist = json.jszList; // 驾驶证证件
		checkStatu =driverInfo.checkStatu;
		//如果不是草稿就只能查看，不能再进入编辑模式
		if(driverInfo.checkStatu == "3"){
			$("#goEdit").css("display","flex");
		}
		
		//当前司机用户所属公司
		companyName = driverInfo.companyName?driverInfo.companyName:"";
		
		var idCord = driverInfo.idCord?driverInfo.idCord:"";
		if(isEmpty(idCord)){
			stringToDate(citizenNo);
		}else{
			stringToDate(idCord);
		}
		
		
		// 格式化日期数据
		driverInfo.birthday = driverInfo.birthday?new Date(driverInfo.birthday).Format("yyyy-MM-dd"):"";
		driverInfo.licensingDate = driverInfo.licensingDate?new Date(driverInfo.licensingDate).Format("yyyy-MM-dd"):"";
		driverInfo.periodValidity = driverInfo.periodValidity?new Date(driverInfo.periodValidity).Format("yyyy-MM-dd"):"";
		driverInfo.sex = driverInfo.sex=='1'?"女":"男";
		driverInfo.practitionerLicenseDate = driverInfo.practitionerLicenseDate? new Date(driverInfo.practitionerLicenseDate).Format("yyyy-MM-dd") : "";
		
		// 显示购销合同信息
		for(item in driverInfo){
			$("#"+item).text(driverInfo[item]?driverInfo[item]:"");
		}
		
		// 图片信息
		/*var html = "";
		var photos = [];
		sfzlist.map(function(item,index){
			var path = getContextPath()+item.attaPath;
			html += '<div class="aui-flex b-line">'
					+'<div class="aui-img-ico">'
					+'<img class="contract-img" style="height:28px;" src="'+path+'" alt=""/>'
					+'</div>'
					+'<div class="aui-flex-box aui-flex-box-epx">'
					+'<p style="text-align: center">身份证</p>'
					+'</div>'
					+'<div class="aui-next">'
					+'<span class="preview" style="margin-right:0;">预览</span>'
					+'</div>'
					+'</div>'
			
			photos.push(path)
		});
		$("#sfzInfo").append(html);*/

		/*var html2 = "";
		jszlist.map(function(item,index){
			var path = getContextPath()+item.attaPath;
			html2 += '<div class="aui-flex b-line">'
					+'<div class="aui-img-ico">'
					+'<img class="contract-img" src="'+path+'" alt="" height="8" />'
					+'</div>'
					+'<div class="aui-flex-box aui-flex-box-epx">'
					+'<p style="text-align: center">驾驶证</p>'
					+'</div>'
					+'<div class="aui-next">'
					+'<span class="preview" style="margin-right:0;">预览</span>'
					+'</div>'
					+'</div>'
			
			photos.push(path)
		});
		$("#jszInfo").append(html2);

		var myPhotoBrowserStandalone = $.photoBrowser({
			photos : photos
		});

		$(document).on('click','.preview',function () {
			var index = $(".preview").index(this);
			myPhotoBrowserStandalone.open(index);
		});*/
	}
	
	/**
	 * 为编辑按钮绑定跳转事件
	 */
	$("#goEdit").click(function(){
		/*if(isEmpty(companyName)){
			location.href = getContextPath() + 'driverInfo/chooseCompany?citizenNo='+citizenNo+"&userId="+userId;
		}else{
			location.href = getContextPath() + "driverInfo/edit?citizenNo="+citizenNo+"&userId="+userId;
		}*/
		
		location.href = getContextPath() + "driverInfo/edit?citizenNo="+citizenNo+"&userId="+userId;
		
	});
	
	/**
	 * 为后退键绑定返回事件
	 */
	$('#goBack').on('click',function(){
        location.href = getContextPath() + 'frontend/personal';
    });
	
	
	//截取身份证号中的出生年月
	function stringToDate(numberOwner){
		//var numberOwner = document.getElementById('numberOwner').value;
		if(numberOwner.length == 15){
			
	    	var subNumberOwer = numberOwner.substring(6,12);
	    	var year = "19"+subNumberOwer.substring(0,2);
	    	var month = subNumberOwer.substring(2,4);
	    	var day = subNumberOwer.substring(4,6);
	    	var newSubNumberOwer = year +"-"+month+"-"+day;
	    	
	    	var dtArr = newSubNumberOwer.split("-");
	    	var dtDate = new Date(dtArr[0],dtArr[1]-1,dtArr[2]).Format("yyyy-MM-dd");
	    	
	    	//$("#bornOwner").val(dtDate);
	    	$("#birthday").text(dtDate);
	    	
	    }else if(numberOwner.length == 18){
	    	
	    	var subNumberOwer = numberOwner.substring(6,14);
	    	var year = subNumberOwer.substring(0,4);
	    	var month = subNumberOwer.substring(4,6);
	    	var day = subNumberOwer.substring(6,8);
	    	var newSubNumberOwer = year +"-"+month+"-"+day;
	    	
	    	var dtArr = newSubNumberOwer.split("-");
	    	var dtDate = new Date(dtArr[0],dtArr[1]-1,dtArr[2]).Format("yyyy-MM-dd");
	    	
	    	//$("#bornOwner").val(dtDate);
	    	$("#birthday").text(dtDate);
	    	
	    }
	}

});
