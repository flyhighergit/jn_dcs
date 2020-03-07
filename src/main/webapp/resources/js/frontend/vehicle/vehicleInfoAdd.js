// 当前司机用户所属公司Id
var companyId = getQueryString("companyId");
//当前司机用户所属公司名称
var companyName = getQueryString("companyName");

$(function() {
	// 要操作的车辆信息ID，在提交基本信息后获取
	var vehicleInfoId = "";
	// 图片路径
	var photos = [];
	
	// 预设所属单位
	$("input[name='sysCompanyName']").val(companyName);
	$("input[name='sysCompanyCode']").val(companyId);
	
	
	// 做添加的URL
	var addUrl = getContextPath()+"vehicleInfo/doAdd";
	// 做编辑的URL
	var editUrl = getContextPath()+"vehicleInfo/doEdit";
	// 标识是否修改过当前页
	var isChanged1 = false;//基本信息
	var isChanged2 = false;//从业证
	var isChanged3 = false;//道路运输证
	var isChanged4 = false;//行驶证
	var isChanged5 = false;//驾驶证
	var isChanged6 = false;//身份证
	
	/**
	 * 给所有的input添加change事件，如果有变化，将isChanged标识置为true
	 */
	$(document).on("change",".baseInfo-input",function(){
		isChanged1 = true;
	});
	$(document).on("change",".zgz-input",function(){
		isChanged2 = true;
	});
	$(document).on("change",".dlysz-input",function(){
		isChanged3 = true;
	});
	$(document).on("change",".xsz-input",function(){
		isChanged4 = true;
	});
	$(document).on("change",".jsz-input",function(){
		isChanged5 = true;
	});
	$(document).on("change",".sfz-input",function(){
		isChanged6 = true;
	});
	
	
	// 从业证  初次发证时间
	var date = new Date();
	$("#issuingTime").calendar({
		value : [ date.getFullYear() + "-" + (date.getMonth() + 1)
				+ "-" + date.getDate() ],
		maxDate : [ date.getFullYear() + "-" + (date.getMonth() + 1)
				+ "-" + date.getDate() ]
	});
	// 从业证  有效期限
	$("#practitionerLicenseDate").calendar({
		value : [ date.getFullYear() + "-" + (date.getMonth() + 1)
				+ "-" + date.getDate() ],
		minDate : [ date.getFullYear() + "-" + (date.getMonth() + 1)
				+ "-" + date.getDate() ]
	});
	
	// 道路运输证  发证日期
	$("#transportIssueDate").calendar({
		value : [ date.getFullYear() + "-" + (date.getMonth() + 1)
				+ "-" + date.getDate() ],
		maxDate : [ date.getFullYear() + "-" + (date.getMonth() + 1)
				+ "-" + date.getDate() ]
	});
	// 道路运输证  有效期限
	$("#transportLicenseDate").calendar({
		value : [ date.getFullYear() + "-" + (date.getMonth() + 1)
				+ "-" + date.getDate() ],
		minDate : [ date.getFullYear() + "-" + (date.getMonth() + 1)
				+ "-" + date.getDate() ]
	});
	
	// 行驶证  注册日期
	$("#numberDate").calendar({
		value : [ date.getFullYear() + "-" + (date.getMonth() + 1)
				+ "-" + date.getDate() ],
		maxDate : [ date.getFullYear() + "-" + (date.getMonth() + 1)
				+ "-" + date.getDate() ]
	});
	// 行驶证  有效期限
	$("#travelLicenseDate").calendar({
		value : [ date.getFullYear() + "-" + (date.getMonth() + 1)
				+ "-" + date.getDate() ],
		minDate : [ date.getFullYear() + "-" + (date.getMonth() + 1)
				+ "-" + date.getDate() ]
	});
	
	// 驾驶证  初次领证日期
	$("#licensingDate").calendar({
		value : [ date.getFullYear() + "-" + (date.getMonth() + 1)
				+ "-" + date.getDate() ],
		maxDate : [ date.getFullYear() + "-" + (date.getMonth() + 1)
				+ "-" + date.getDate() ]
	});
	// 驾驶证  有限期限
	$("#driveLicenseDate").calendar({
		value : [ date.getFullYear() + "-" + (date.getMonth() + 1)
				+ "-" + date.getDate() ],
		minDate : [ date.getFullYear() + "-" + (date.getMonth() + 1)
				+ "-" + date.getDate() ]
	});
	
	
	/**
	 * 选择车辆类型
	 */
	$("#vehicleTypeName").click(function(){
		$.popup("#carStyle");
		doSearchCar();
	});
	
	/**
	 * 选择准驾车型
	 */
	$("#models").click(function(){
		$.popup("#modelsDiv");
		doSearchModels();
	});
	
	/**
	 * 选择车主性别
	 */
	$("#sexOwnerName").click(function(){
		$.popup("#sex");
		doSearchSex();
	});
	
	
	/**
	 * 返回事件
	 */
	$("#goBack").click(function(){
		// 如果有修改痕迹，那么就提示是否退出编辑，否则就直接返回到详情页
		if($("#id").val()||isChanged1||isChanged2||isChanged3||isChanged4||isChanged5||isChanged6){
			$.confirm("确定放弃本次录入吗？",function(){
				
				location.href = getContextPath() + "vehicleInfo/list";
			});
		}else{
			location.href = getContextPath() + "vehicleInfo/list";
		}
	});
	
	
	/**
	 * 保存草稿
	 */
	$("#goSave").click(function(){
		
		if($(".active").attr("id")=="module1"){
			if(isEmpty($("#licensePlate").val())){
				$.toast("请填写车牌号！")
				return;
			}
			
			//验证车牌号
			$("#licensePlate").val($("#licensePlate").val().toLocaleUpperCase());
			var licensePlate = $("#licensePlate").val();
			var oldLicensePlate = $("#oldLicensePlate").val().toLocaleUpperCase();
			
			licensePlate = $.trim(licensePlate);
			oldLicensePlate = $.trim(oldLicensePlate);
			
			if(licensePlate != oldLicensePlate){
				var flag = true;
				var validFlag = isVehicleNumber(licensePlate)
				if(!validFlag){
					$.toast("车牌号格式不正确！");
					document.getElementById("licensePlate").value="";
					flag = false;;
				}else{
					$.ajax({
						type: 'post',
						url: getContextPath() + "vehicleInfo/validLicensePlate",
						data: {"licensePlate":licensePlate},
						async: false,  
						dataType: 'json',
						success: function(data){
							if(data.number != '0'){
								$.toast("此车牌号已被使用！")
								document.getElementById("licensePlate").value="";
								flag = false;
							}
						}
					});
				}
				if(!flag){
					return false;
				}else{
					$("#oldLicensePlate").val(licensePlate);
				}
			}
				
			// 如果已有ID且信息被修改过，那么就去按照编辑的逻辑保存
			var url = "";
			if($("#id").val()&&isChanged1){
				url = editUrl;
			}else if(!$("#id").val()){
				// 如果没有记录ID，说明还没有进行过保存，那么就按新增的逻辑保存
				url = addUrl;
			}
			
			$.showPreloader();

			$.ajax({
				url : url,
				data:$("#baseInfo").serialize(),
				cache:false,
				processData:false,
				dataType:"JSON",
				type:"GET",
				cache:false,
				success:function(data){
					data = JSON.parse(data);
					if(data.success){
						$.toast("保存成功！");
						$("#id").val(data.id);
						vehicleInfoId = data.id;
						isChanged = false;
						$.hidePreloader();
					}else {
						$.hidePreloader();
						$.toast(data.errMsg);
					}
				}
			});
		}
		else if($(".active").attr("id")=="module2"){
			$.showPreloader();

			$.getJSON(editUrl,$("#upload-zgz").serialize()+"&id="+$("#id").val(),function(data){
				if(data.success){
					$.toast("保存成功！");

					// 重置标识
					isChanged2 = false;

					$.hidePreloader();
				}else {
					$.hidePreloader();
					
					$.toast(data.errMsg);
				}
			});
		}
		else if($(".active").attr("id")=="module3"){
			$.showPreloader();

			$.getJSON(editUrl,$("#upload-dlysz").serialize()+"&id="+$("#id").val(),function(data){
				if(data.success){
					$.toast("保存成功！");

					// 重置标识
					isChanged3 = false;

					$.hidePreloader();
				}else {
					$.hidePreloader();
					
					$.toast(data.errMsg);
				}
			});
		}
		else if($(".active").attr("id")=="module4"){
			$.showPreloader();

			$.getJSON(editUrl,$("#upload-xsz").serialize()+"&id="+$("#id").val(),function(data){
				if(data.success){
					$.toast("保存成功！");

					// 重置标识
					isChanged4 = false;

					$.hidePreloader();
				}else {
					$.hidePreloader();
					
					$.toast(data.errMsg);
				}
			});
		}
		else if($(".active").attr("id")=="module5"){
			$.showPreloader();

			$.getJSON(editUrl,$("#upload-jsz").serialize()+"&id="+$("#id").val(),function(data){
				if(data.success){
					$.toast("保存成功！");
					// 重置标识
					isChanged5 = false;
					$.hidePreloader();
				}else {
					$.hidePreloader();
					$.toast(data.errMsg);
				}
			});
		}
		else {

			$.showPreloader();
			var data = $("#upload-sfz").serialize()+"&id="+$("#id").val();
			$.getJSON(editUrl,data,function(data){
				if(data.success){
					$.toast("保存成功！");
					isChanged6 = false;
					$.hidePreloader();
					
				}else {
					$.hidePreloader();
					$.toast(data.errMsg);
				}
			});
		}
		
	});
	
	
	/**
	 * 提交基本信息并保存
	 * 如果有ID，说明已经添加过本次信息
	 * 那么就作为修改提交
	 */
	$("#submit-baseInfo").click(function(){
		var valid = true;
		$("#baseInfo input[required='required']").each(function(){
             var value = $.trim($(this).val());
             if(value==''){
                 valid =  false;
             }
        });
		
		if(valid){
			//验证车牌号
			$("#licensePlate").val($("#licensePlate").val().toLocaleUpperCase());
			var licensePlate = $("#licensePlate").val();
			var oldLicensePlate = $("#oldLicensePlate").val().toLocaleUpperCase();
			
			licensePlate = $.trim(licensePlate);
			oldLicensePlate = $.trim(oldLicensePlate);
			
			if(licensePlate != oldLicensePlate){
				var flag = true;
				var validFlag = isVehicleNumber(licensePlate)
				if(!validFlag){
					$.toast("车牌号格式不正确，请重填！");
					flag = false;;
				}else{
					$.ajax({
						type: 'post',
						url: getContextPath() + "vehicleInfo/validLicensePlate",
						data: {"licensePlate":licensePlate},
						async: false,  
						dataType: 'json',
						success: function(data){
							if(data.number != '0'){
								$.toast("此车牌号已被使用，请重填！")
								flag = false;
							}
						}
					});
				}
				if(!flag){
					return false;
				}
				else{
					$("#oldLicensePlate").val(licensePlate);
				}
			}
			
			// 如果已有ID且信息被修改过，那么就去按照编辑的逻辑保存
			var url = "";
			if($("#id").val()&&isChanged1){
				url = editUrl;
			}else if(!$("#id").val()){
				// 如果没有记录ID，说明还没有进行过保存，那么就按新增的逻辑保存
				url = addUrl;
			}else {
				// 如果没有输入新的值，那么就不执行保存，直接进入下一步，避免频繁保存，占用资源
				$("#module1").removeClass().addClass("hidden");
				$("#module2").removeClass().addClass("active");
				return
			}
			
			$.showPreloader();
	
			$.ajax({
				url : url,
				data:$("#baseInfo").serialize(),
				cache:false,
				processData:false,
				dataType:"JSON",
				cache:false,
				success:function(data){
					data = JSON.parse(data);
					if(data.success){
						$("#id").val(data.id);
						vehicleInfoId = data.id;
						isChanged = false;
						$("#module1").removeClass().addClass("hidden");
						$("#module2").removeClass().addClass("active");
						$.hidePreloader();
					}else {
						$.hidePreloader();
						$.toast(data.errMsg);
					}
				}
			});
		}
		else{
			$.toast("请检查有*必填项是否填写完整，或信息格式是否正确!");
			 return false;
		}
	});
	
	
	/**
	 * 保存并提交从业证相关信息
	 */
	$("#submit-zgz").click(function(){
		var src = "../resources/images/list-ico/no-img.png";
		var notUploaded = ($("#zgzPositiveImg").attr("src") == src) || ($("#zgzNegativeImg").attr("src") == src)
		if(notUploaded){
			$.toast("请上传从业证附件！");
			return;
		}
		
		var valid = true;
		$("#upload-zgz input[required='required']").each(function(){
             var value = $.trim($(this).val());
             if(value==''){
                 valid =  false;
             }
        });
		if(valid){
			$.showPreloader();
	
			if(isChanged2){
				$.getJSON(editUrl,$("#upload-zgz").serialize()+"&id="+$("#id").val(),function(data){
					if(data.success){
						// 翻页
						$("#module2").removeClass().addClass("hidden");
						$("#module3").removeClass().addClass("active");
	
						// 重置标识
						isChanged2 = false;
	
						$.hidePreloader();
					}else {
						$.hidePreloader();
						
						$.toast(data.errMsg);
					}
				});
			}else {
				$("#module2").removeClass().addClass("hidden");
				$("#module3").removeClass().addClass("active");
	
				$.hidePreloader();
			}
		}
		else{
			$.toast("请检查有*必填项是否填写完整，或信息格式是否正确!");
			 return false;
		}
	});
	/**
	 * 上传从业证附件
	 */
	$("#upload-zgz").on('change','.file-btn',function(){
		var vehicleInfoId = $("#id").val();
		$.showPreloader();

		var obj = $(this);
		var file = obj[0].files[0];
		var imgId= obj.attr("id");
		var formData = new FormData();
		formData.append("img",file);
		formData.append("vehicleInfoId",vehicleInfoId);
		formData.append("oldImgId",imgId);
		
		$.ajax({
			url : getContextPath() + "tbAttachmentZgz/uploadSingle",
			data:formData,
			type:"POST",
			contentType : false,
			processData : false,
			cache : false,
			success:function(data){
				if(data.success){
					// 获取新的图片路径，替换旧图片在photos中的位置
					obj.val('');
					var img = obj.parents(".b-line").find("img");
					var src = getContextPath()+data.result.attaPath;
					photos.replace(img.attr("src"),src);
					img.attr("src",src);
					
					obj.attr("id",data.result.id);

					// 重新生成图片浏览器
					var myPhotoBrowserStandalone = $.photoBrowser({
						photos : photos
					});
					// 为防止出现冲突，先去除掉原有的点击事件
					$('.preview').off('click');
					// 再重新添加新的事件
					$('.preview').on('click',function () {
						myPhotoBrowserStandalone.open($(".preview").index(this));
					});

					$.hidePreloader();
				}else{
					$.hidePreloader();

					$.toast(data.errMsg);
				}
			}
		});
	});
	
	
	/**
	 * 保存并提交道路运输证相关信息
	 */
	$("#submit-dlysz").click(function(){
		var src = "../resources/images/list-ico/no-img.png";
		var notUploaded = ($("#dlyszPositiveImg").attr("src") == src) || ($("#dlyszNegativeImg").attr("src") == src)
		if(notUploaded){
			$.toast("请上传道路运输证附件！");
			return;
		}
		
		var valid = true;
		$("#upload-dlysz input[required='required']").each(function(){
             var value = $.trim($(this).val());
             if(value==''){
                 valid =  false;
             }
        });
		if(valid){
			$.showPreloader();
	
			if(isChanged3){
				$.getJSON(editUrl,$("#upload-dlysz").serialize()+"&id="+$("#id").val(),function(data){
					if(data.success){
						// 翻页
						$("#module3").removeClass().addClass("hidden");
						$("#module4").removeClass().addClass("active");
	
						// 重置标识
						isChanged3 = false;
	
						$.hidePreloader();
					}else {
						$.hidePreloader();
						
						$.toast(data.errMsg);
					}
				});
			}else {
				$("#module3").removeClass().addClass("hidden");
				$("#module4").removeClass().addClass("active");
	
				$.hidePreloader();
			}
		}
		else{
			$.toast("请检查有*必填项是否填写完整，或信息格式是否正确!");
			 return false;
		}
	});
	/**
	 * 上传道路运输证附件
	 */
	$("#upload-dlysz").on('change','.file-btn',function(){
		var vehicleInfoId = $("#id").val();
		$.showPreloader();

		var obj = $(this);
		var file = obj[0].files[0];
		var imgId= obj.attr("id");
		var formData = new FormData();
		formData.append("img",file);
		formData.append("vehicleInfoId",vehicleInfoId);
		formData.append("oldImgId",imgId);
		
		$.ajax({
			url : getContextPath() + "tbAttachmentDlysz/uploadSingle",
			data:formData,
			type:"POST",
			contentType : false,
			processData : false,
			cache : false,
			success:function(data){
				if(data.success){
					// 获取新的图片路径，替换旧图片在photos中的位置
					obj.val('');
					var img = obj.parents(".b-line").find("img");
					var src = getContextPath()+data.result.attaPath;
					photos.replace(img.attr("src"),src);
					img.attr("src",src);
					
					obj.attr("id",data.result.id);

					// 重新生成图片浏览器
					var myPhotoBrowserStandalone = $.photoBrowser({
						photos : photos
					});
					// 为防止出现冲突，先去除掉原有的点击事件
					$('.preview').off('click');
					// 再重新添加新的事件
					$('.preview').on('click',function () {
						myPhotoBrowserStandalone.open($(".preview").index(this));
					});

					$.hidePreloader();
				}else{
					$.hidePreloader();

					$.toast(data.errMsg);
				}
			}
		});
	});
	
	
	/**
	 * 保存并提交行驶证相关信息
	 */
	$("#submit-xsz").click(function(){
		var src = "../resources/images/list-ico/no-img.png";
		var notUploaded = ($("#xszPositiveImg").attr("src") == src) || ($("#xszNegativeImg").attr("src") == src)
		if(notUploaded){
			$.toast("请上传行驶证附件！");
			return;
		}
		
		var valid = true;
		$("#upload-xsz input[required='required']").each(function(){
             var value = $.trim($(this).val());
             if(value==''){
                 valid =  false;
             }
        });
		if(valid){
			//验证车辆识别代号
			$("#vin").val($("#vin").val().toLocaleUpperCase());
			var vin = $("#vin").val();
			var oldVin = $("#oldVin").val().toLocaleUpperCase();
			
			vin = $.trim(vin);
			oldVin = $.trim(oldVin);
			
			if(vin != oldVin){
				var flag = true;
				var validFlag = CheckVin(vin);
				if(!validFlag){
					$.toast("车辆识别代号格式不正确，请重填!");
					return;
				}else{
					$.ajax({
						type: 'post',
						url: getContextPath() + "vehicleInfo/validVin",
						data: {"vin":vin},
						async: false,  
						dataType: 'json',
						success: function(data){
							if(data.number != '0'){
								$.toast("此车辆识别代号已被使用，请重填！")
								flag = false;
							}
						}
					});
				}
				if(!flag){
					return false;
				}else{
					$("#oldVin").val(vin);
				}
			}
			
			//验证发动机号
			var engineNum = document.getElementById('engineNum').value;
			var oldEngineNum = $("#oldEngineNum").val();
			
			engineNum = $.trim(engineNum);
			oldEngineNum = $.trim(oldEngineNum);
			
			if(engineNum != oldEngineNum){
				var flag = true;
				if(!(/^[A-Z\d]+$/.test(engineNum))){ 
			    	$.toast("发动机号格式错误，请重填!"); 
			        return; 
			    }else{
					$.ajax({
						type: 'post',
						url: getContextPath() + "vehicleInfo/validEngineNum",
						data: {"engineNum":engineNum},
						async: false,  
						dataType: 'json',
						success: function(data){
							if(data.number != '0'){
								$.toast("此发动机号已被使用，请重填！")
								flag = false;
							}
						}
					});
				}
				if(!flag){
					return false;
				}else{
					$("#oldEngineNum").val(engineNum);
				}
			}
			
			
			$.showPreloader();
	
			if(isChanged4){
				$.getJSON(editUrl,$("#upload-xsz").serialize()+"&id="+$("#id").val(),function(data){
					if(data.success){
						// 翻页
						$("#module4").removeClass().addClass("hidden");
						$("#module5").removeClass().addClass("active");
	
						// 重置标识
						isChanged4 = false;
	
						$.hidePreloader();
					}else {
						$.hidePreloader();
						
						$.toast(data.errMsg);
					}
				});
			}else {
				$("#module4").removeClass().addClass("hidden");
				$("#module5").removeClass().addClass("active");
	
				$.hidePreloader();
			}
		}
		else{
			$.toast("请检查有*必填项是否填写完整，或信息格式是否正确!");
			 return false;
		}
	});
	/**
	 * 上传行驶证附件
	 */
	$("#upload-xsz").on('change','.file-btn',function(){
		var vehicleInfoId = $("#id").val();
		$.showPreloader();

		var obj = $(this);
		var file = obj[0].files[0];
		var imgId= obj.attr("id");
		var formData = new FormData();
		formData.append("img",file);
		formData.append("vehicleInfoId",vehicleInfoId);
		formData.append("oldImgId",imgId);
		
		$.ajax({
			url : getContextPath() + "tbAttachmentXsz/uploadSingle",
			data:formData,
			type:"POST",
			contentType : false,
			processData : false,
			cache : false,
			success:function(data){
				if(data.success){
					// 获取新的图片路径，替换旧图片在photos中的位置
					obj.val('');
					var img = obj.parents(".b-line").find("img");
					var src = getContextPath()+data.result.attaPath;
					photos.replace(img.attr("src"),src);
					img.attr("src",src);
					
					obj.attr("id",data.result.id);

					// 重新生成图片浏览器
					var myPhotoBrowserStandalone = $.photoBrowser({
						photos : photos
					});
					// 为防止出现冲突，先去除掉原有的点击事件
					$('.preview').off('click');
					// 再重新添加新的事件
					$('.preview').on('click',function () {
						myPhotoBrowserStandalone.open($(".preview").index(this));
					});

					$.hidePreloader();
				}else{
					$.hidePreloader();

					$.toast(data.errMsg);
				}
			}
		});
	});
	
	
	/**
	 * 保存并提交驾驶证相关信息
	 */
	$("#submit-jsz").click(function(){
		var src = "../resources/images/list-ico/no-img.png";
		var notUploaded = ($("#jszPositiveImg").attr("src") == src) || ($("#jszNegativeImg").attr("src") == src)
		if(notUploaded){
			$.toast("请上传驾驶证附件！");
			return;
		}
		
		var valid = true;
		$("#upload-jsz input[required='required']").each(function(){
             var value = $.trim($(this).val());
             if(value==''){
                 valid =  false;
             }
        });
		if(valid){
			$.showPreloader();
			var data = $("#upload-jsz").serialize()+"&id="+$("#id").val()+"&checkStatus=0";
			if(isChanged5){
				$.getJSON(editUrl,data,function(data){
					if(data.success){
						// 重置标识
						isChanged5 = false;
						$.hidePreloader();
						
						$.toast("提交成功，等待核对！");
						
						setTimeout(function(){
							location.href = getContextPath() + "vehicleInfo/list";
						},3000)
						
					}else {
						$.hidePreloader();
						
						$.toast(data.errMsg);
					}
				});
			}else {
				location.href = getContextPath() + "vehicleInfo/list"; 
			}
		}
		else{
			$.toast("请检查有*必填项是否填写完整，或信息格式是否正确!");
			 return false;
		}
	});
	/**
	 * 上传驾驶证附件
	 */
	$("#upload-jsz").on('change','.file-btn',function(){
		var vehicleInfoId = $("#id").val();
		$.showPreloader();

		var obj = $(this);
		var file = obj[0].files[0];
		var imgId= obj.attr("id");
		var formData = new FormData();
		formData.append("img",file);
		formData.append("vehicleInfoId",vehicleInfoId);
		formData.append("oldImgId",imgId);
		
		$.ajax({
			url : getContextPath() + "tbAttachmentJsz/uploadSingle",
			data:formData,
			type:"POST",
			contentType : false,
			processData : false,
			cache : false,
			success:function(data){
				if(data.success){
					// 获取新的图片路径，替换旧图片在photos中的位置
					obj.val('');
					var img = obj.parents(".b-line").find("img");
					var src = getContextPath()+data.result.attaPath;
					photos.replace(img.attr("src"),src);
					img.attr("src",src);
					
					obj.attr("id",data.result.id);

					// 重新生成图片浏览器
					var myPhotoBrowserStandalone = $.photoBrowser({
						photos : photos
					});
					// 为防止出现冲突，先去除掉原有的点击事件
					$('.preview').off('click');
					// 再重新添加新的事件
					$('.preview').on('click',function () {
						myPhotoBrowserStandalone.open($(".preview").index(this));
					});

					$.hidePreloader();
				}else{
					$.hidePreloader();

					$.toast(data.errMsg);
				}
			}
		});
	});
	
	
	/**
	 * 保存并提交身份证相关信息
	 */
	$("#submit-sfz").click(function(){
		var src = "../resources/images/list-ico/no-img.png";
		var notUploaded = ($("#sfzPositiveImg").attr("src") == src) || ($("#sfzNegativeImg").attr("src") == src)
		if(notUploaded){
			$.toast("请上身份证证附件！");
			return;
		}
		
		var valid = true;
		$("#upload-sfz input[required='required']").each(function(){
             var value = $.trim($(this).val());
             if(value==''){
                 valid =  false;
             }
        });
		if(valid){
			//验证身份证号
			var numberOwner = document.getElementById('numberOwner').value;
			var validFlag = checkNumberOwner(numberOwner);
			if(!validFlag){
				$.toast("身份证号格式不正确，请重填！");
				document.getElementById("numberOwner").value="";
				return;
			}
			
			$.showPreloader();
			var data = $("#upload-sfz").serialize()+"&id="+$("#id").val()+"&checkStatus=0";
			if(isChanged6){
				$.getJSON(editUrl,data,function(data){
					if(data.success){
						isChanged6 = false;
						$.hidePreloader();
						
						$.toast("提交成功，等待核对！");
						
						setTimeout(function(){
							location.href = getContextPath() + "vehicleInfo/list";
						},500)
						
					}else {
						$.hidePreloader();
						
						$.toast(data.errMsg);
					}
				});
				
			}else{
				location.href = getContextPath() + "vehicleInfo/list"; 
			}
		}
		else{
			$.toast("请检查有*必填项是否填写完整，或信息格式是否正确!");
			 return false;
		}
	});
	/**
	 * 上传身份证附件
	 */
	$("#upload-sfz").on('change','.file-btn',function(){
		var vehicleInfoId = $("#id").val();
		$.showPreloader();

		var obj = $(this);
		var file = obj[0].files[0];
		var imgId= obj.attr("id");
		var formData = new FormData();
		formData.append("img",file);
		formData.append("vehicleInfoId",vehicleInfoId);
		formData.append("oldImgId",imgId);
		
		$.ajax({
			url : getContextPath() + "tbAttachmentSfz/uploadSingle",
			data:formData,
			type:"POST",
			contentType : false,
			processData : false,
			cache : false,
			success:function(data){
				if(data.success){
					obj.val('');
					var img = obj.parents(".b-line").find("img");
					var src = getContextPath()+data.result.attaPath;
					photos.replace(img.attr("src"),src);
					img.attr("src",src);
					
					obj.attr("id",data.result.id);

					// 重新生成图片浏览器
					var myPhotoBrowserStandalone = $.photoBrowser({
						photos : photos
					});

					$('.preview').off('click');
					
					$('.preview').on('click',function () {
						myPhotoBrowserStandalone.open($(".preview").index(this));
					});

					$.hidePreloader();
				}else{
					$.hidePreloader();

					$.toast(data.errMsg);
				}
			}
		});
	});
	
	
	/**
	 * 返回到基本信息页面
	 */
	$("#backto-baseinfo").click(function(){
		$("#module2").removeClass().addClass("hidden");
		$("#module1").removeClass().addClass("active");
	});
	/**
	 * 返回到从业证信息页面
	 */
	$("#backto-zgz").click(function(){
		$("#module3").removeClass().addClass("hidden");
		$("#module2").removeClass().addClass("active");
	});
	/**
	 * 返回到道路运输证信息页面
	 */
	$("#backto-dlysz").click(function(){
		$("#module4").removeClass().addClass("hidden");
		$("#module3").removeClass().addClass("active");
	});
	/**
	 * 返回到行驶证信息页面
	 */
	$("#backto-xsz").click(function(){
		$("#module5").removeClass().addClass("hidden");
		$("#module4").removeClass().addClass("active");
	});
	/**
	 * 返回到驾驶证信息页面
	 */
	$("#backto-jsz").click(function(){
		$("#module6").removeClass().addClass("hidden");
		$("#module5").removeClass().addClass("active");
	});
	
});




/**
 * 查询车型列表
 * @returns
 */
function doSearchCar(){
	
	// 根据用户输入的关键字查询公司列表，并展示
	$.ajax({
		url:getContextPath()+"baseCx/getBaseCxinfo",
		data:{
			pageIndex:"-1",// 分页信息-1表示不分页
			pageSize:"-1",
		},
		type:"POST",
		dataType:"JSON",
		success:function(data){
			data = JSON.parse(data);
			if(data.success){
				var baseCxs = data.list;
				var html = "";
				baseCxs.map(function(item, index) {
					// popup模板内容
					var popTemplate = "<div class=\"aui-flex b-line carlist\""
							+ " onclick=\"setCar('" + item.id + "','" + item.cxmc + "','" + item.cxzs + "','" + item.cxhz
							+ "');\">" + "<div class=\"aui-flex-box\">" + "<p>"
							+ item.cxmc + "</p></div>" 
							+"</div>";
					
					html += popTemplate;
				});
				
				if ($.trim(html) == "") {
					$.toast("暂无相关信息！");
				}else{
					$("#carStyle").append(html);
				}
				$.popup("#carStyle");
			}else if(data.success==false){
				$.toast("暂无相关车型！");
			}
			
		}
	});
}
/**
 * 设置车型信息
 * @param id
 * @param name
 * @returns
 */
function setCar(id,name,cxzs,cxhz){
	$("#vehicleTypeName").text(name);
	$("input[name='vehicleTypeName']").val(name);
	$("input[name='vehicleType']").val(id);
	
	$("input[name='axles']").val(cxzs);
	$("input[name='maxPayload']").val(cxhz);
	
	$.closeModal("#carStyle");
	$("#titleCar").val("");
	$(".carlist").remove();
	
}
/**
 * 关闭车型选择
 * @param 
 * @param 
 * @returns
 */
function closeCar(style){
	if(style=="cx"){
		$.closeModal("#carStyle");
		$(".carlist").remove();
	}
}


/**
 * 查询准驾车型信息列表
 * @returns
 */
function doSearchModels(){
	
	// 查询品种信息，并展示
	$.ajax({
		url:getContextPath()+"dictionary/getByType?typeCode=Car_Model",
		data:{
			pageIndex:"-1",// 分页信息-1表示不分页
			pageSize:"-1",
		},
		type:"POST",
		dataType:"JSON",
		success:function(data){
			data = JSON.parse(data);
			if(data.success){
				var baseCxs = data.result;
				var html = "";
				baseCxs.map(function(item, index) {
					// popup模板内容
					var popTemplate = "<div class=\"aui-flex b-line modelslist\""
							+ " onclick=\"setModels('" + item.typecode + "','" + item.typename
							+ "');\">" + "<div class=\"aui-flex-box\">" + "<p>"
							+ item.typename + "</p></div>" 
							+"</div>";
					
					html += popTemplate;
				});
				
				if ($.trim(html) == "") {
					$.toast("暂无相关信息！");
				}else{
					$("#modelsType").append(html);
				}
				$.popup("#modelsDiv");
			}else if(data.success==false){
				$.toast("暂无相关信息！");
			}
			
		}
	});
}
/**
 * 设置准驾车型信息
 * @param id
 * @param name
 * @returns
 */
function setModels(id,name){
	$("#models").text(name);
	$("input[name='models']").val(name);
	$("input[name='modelsCode']").val(id);
	
	$.closeModal("#modelsDiv");
	$(".modelslist").remove();
	
}
/**
 * 关闭准驾车型选择
 * @param 
 * @param 
 * @returns
 */
function closeModels(style){
	$.closeModal("#modelsDiv");
	$(".modelslist").remove();
	
}


/**
 * 查询性别信息列表
 * @returns
 */
function doSearchSex(){
	
	// 查询品种信息，并展示
	$.ajax({
		url:getContextPath()+"dictionary/getByType?typeCode=sex_owner",
		data:{
			pageIndex:"-1",// 分页信息-1表示不分页
			pageSize:"-1",
		},
		type:"POST",
		dataType:"JSON",
		success:function(data){
			data = JSON.parse(data);
			if(data.success){
				var baseCxs = data.result;
				var html = "";
				baseCxs.map(function(item, index) {
					// popup模板内容
					var popTemplate = "<div class=\"aui-flex b-line sexlist\""
							+ " onclick=\"setSex('" + item.typecode + "','" + item.typename
							+ "');\">" + "<div class=\"aui-flex-box\">" + "<p>"
							+ item.typename + "</p></div>" 
							+"</div>";
					
					html += popTemplate;
				});
				
				if ($.trim(html) == "") {
					$.toast("暂无相关信息！");
				}else{
					$("#sexType").append(html);
				}
				$.popup("#sex");
			}else if(data.success==false){
				$.toast("暂无相关信息！");
			}
			
		}
	});
}
/**
 * 设置性别信息
 * @param id
 * @param name
 * @returns
 */
function setSex(id,name){
	$("#sexOwnerName").text(name);
	$("input[name='sexOwnerName']").val(name);
	$("input[name='sexOwner']").val(id);
	
	$.closeModal("#sex");
	$(".sexlist").remove();
	
}
/**
 * 关闭性别选择
 * @param 
 * @param 
 * @returns
 */
function closeSex(style){
	$.closeModal("#sex");
	$(".sexlist").remove();
	
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

//验证身份证号
function checkNumberOwner(numberOwner){
	//第一代身份证号验证规则
	var xreg=/^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}$/;
	//第二代身份证号验证规则
	var creg=/^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
    
    if(numberOwner.length == 15){
    	return xreg.test(numberOwner);
    }else if(numberOwner.length == 18){
    	return creg.test(numberOwner);
    }else{
    	return false;
    }
}

//验证车辆识别代号
function CheckVin(vin){

	if(vin.length>0 && vin.length!=17){
		return false;
	}else{
		var vinVal = vin.toUpperCase();
		var charToNum = {'A':1,'B':2,'C':3,'D':4,'E':5,'F':6,'G':7,'H':8,'J':1,'K':2,'L':3,'M':4,'N':5,'P':7,'R':9,'S':2,'T':3,'U':4,'V':5,'W':6,'X':7,'Y':8,'Z':9};
		var obj = 0;
		var arr = new Array();
		for (var i = 0 ; i < vinVal.length; i++) {
			var temp = vinVal.charAt(i);

			if(charToNum[temp]){
				arr[i] = charToNum[temp];
			}else{
				arr[i] = Number(temp);
			}
			if(i==8){
				arr[i] = vinVal.charAt(i);
			}

		};	
		var a1 = 8;
		for (var i = 0; i < 7; i++) {
			obj += Number(arr[i]) * a1 ;
			a1--;
		};

		obj += Number(arr[7])*10;

		var a2 = 9;
		for (var i = 9; i < 17; i++) {
			obj += Number(arr[i]) * a2;
			a2--;
		};

		var result = Number(obj)%11; 
		if(parseInt(result) === 10){
			result = 'X';
		}
		if(result == arr[8]){
			//成功
			return true;
		}else{
			//失败
			return false;
		}
	}
}

//截取身份证号中的出生年月
function stringToDate(){
	var numberOwner = document.getElementById('numberOwner').value;
	if(numberOwner.length == 15){
		
    	var subNumberOwer = numberOwner.substring(6,12);
    	var year = "19"+subNumberOwer.substring(0,2);
    	var month = subNumberOwer.substring(2,4);
    	var day = subNumberOwer.substring(4,6);
    	var newSubNumberOwer = year +"-"+month+"-"+day;
    	
    	var dtArr = newSubNumberOwer.split("-");
    	var dtDate = new Date(dtArr[0],dtArr[1]-1,dtArr[2]).Format("yyyy-MM-dd");
    	
    	$("#bornOwner").val(dtDate);
    	
    }else if(numberOwner.length == 18){
    	
    	var subNumberOwer = numberOwner.substring(6,14);
    	var year = subNumberOwer.substring(0,4);
    	var month = subNumberOwer.substring(4,6);
    	var day = subNumberOwer.substring(6,8);
    	var newSubNumberOwer = year +"-"+month+"-"+day;
    	
    	var dtArr = newSubNumberOwer.split("-");
    	var dtDate = new Date(dtArr[0],dtArr[1]-1,dtArr[2]).Format("yyyy-MM-dd");
    	
    	$("#bornOwner").val(dtDate);
    	
    }
}
