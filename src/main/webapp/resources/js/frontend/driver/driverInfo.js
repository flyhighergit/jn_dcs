// 当前司机用户的身份证号
var citizenNo = getQueryString("citizenNo");
//选择绑定的公司的ID
var companyId = getQueryString("companyId");
//当前登录用户的用户ID
var userId = getQueryString("userId");

//标识是否修改过当前页
var isChanged1 = false;
var isChanged2 = false;
var isChanged3 = false;

$(function () {
	// 所属公司信息展示
	$.ajax({
		url: getContextPath() + "coalRelated/getCoalRelatedInfo?coalId=" + companyId,
		dataType: "JSON",
		cache: false,
		type: "GET",
		async: false,
		success: function (data) {
			data = JSON.parse(data);
			if (data.success) {
				//预设所属公司信息
				$("#companyId").val(companyId);
				$("#companyCode").val(data.tbCoalRelated.code ? data.tbCoalRelated.code : "");
				$("#companyName").val(data.tbCoalRelated.name ? data.tbCoalRelated.name : "");
			}
		}
	});

	// 个人信息展示
	$.ajax({
		url: getContextPath() + "frontend/getMyInfo",
		dataType: "JSON",
		cache: false,
		type: "GET",
		async: false,
		success: function (data) {
			data = JSON.parse(data);
			if (data.success) {
				//预设姓名
				//$("#name").val(data.tSBaseUser.realName?data.tSBaseUser.realName:"");
				//预设身份证号
				$("#idCord").val(data.tSBaseUser.idCord ? data.tSBaseUser.idCord : "");
				//预设驾驶证号
				$("#licenseNumber").val(data.tSBaseUser.idCord ? data.tSBaseUser.idCord : "");

				stringToDate(citizenNo);

				isChanged2 = true;
			}
		}
	});


	// 图片路径
	var photos = [];

	// 获取记录信息
	getJson();

	function getJson() {
		var url = getContextPath() + "driverInfo/getDriverInfo?userId=" + userId;
		$.getJSON(url, function (data) {
			if (data.success) {
				$("#companyNameSelect").text(data.driverInfo.companyName ? data.driverInfo.companyName : "");

				buildHtml(data);
			} else {
				var date = new Date().Format("yyyy-MM-dd");
				// 出生日期
				/*$("#birthday").calendar({
					value: [date]
				});*/
				// 驾驶证初次领证日期
				$("#licensingDate").calendar({
					value: [date],
					maxDate: [date]
				});
				// 驾驶证有效期限
				$("#periodValidity").calendar({
					value: [date],
					minDate: [date]
				});
				// 资格证有效期限
				$("#practitionerLicenseDate").calendar({
					value: [date],
					onChange: function (p, values, displayValues) {
						var time = new Date(displayValues).getTime();
						$("input[name='practitionerLicenseDate']").val(time);
					},
					minDate: [date]
				});
			}
		});
	}

	// 构建content中的html
	function buildHtml(json) {
		var driverInfo = json.driverInfo; // 司机信息
		var sfzlist = json.sfzList; // 身份证证件
		var jszlist = json.jszList; // 驾驶证证件
		var zgzlist = json.zgzList; // 资格证证件
		// 格式化日期数据
		driverInfo.birthday = driverInfo.birthday ? new Date(driverInfo.birthday).Format("yyyy-MM-dd") : "";
		driverInfo.licensingDate = driverInfo.licensingDate ? new Date(driverInfo.licensingDate).Format("yyyy-MM-dd") : "";
		driverInfo.periodValidity = driverInfo.periodValidity ? new Date(driverInfo.periodValidity).Format("yyyy-MM-dd") : "";
		driverInfo.practitionerLicenseDate = driverInfo.practitionerLicenseDate? new Date(driverInfo.practitionerLicenseDate).Format("yyyy-MM-dd") : "";
		
		// 初始化日历
		var date = new Date().Format("yyyy-MM-dd");

		// 驾驶证初次领证日期
		if (driverInfo.licensingDate) {
			var licensingDate = new Date(driverInfo.licensingDate).Format("yyyy-MM-dd");
			$("#licensingDate").calendar({
				value: [licensingDate],
				onChange: function (p, values, displayValues) {
					var time = new Date(displayValues).getTime();
					$("input[name='birthday']").val(time);
				},
				maxDate: [date]
			});
		} else {
			$("#licensingDate").calendar({
				value: [date],
				onChange: function (p, values, displayValues) {
					var time = new Date(displayValues).getTime();
					$("input[name='birthday']").val(time);
				},
				maxDate: [date]
			});
		}

		// 驾驶证有效期限
		if (driverInfo.periodValidity) {
			var periodValidity = new Date(driverInfo.periodValidity).Format("yyyy-MM-dd");
			var d = new Date();
			$("#periodValidity").calendar({
				value: [periodValidity],
				onChange: function (p, values, displayValues) {
					var time = new Date(displayValues).getTime();
					$("input[name='birthday']").val(time);
				},
				minDate: [d.getFullYear() + "-" + (d.getMonth() + 1) +
					"-" + (d.getDate() - 1)
				]
			});
		} else {
			var d = new Date();
			$("#periodValidity").calendar({
				value: [date],
				onChange: function (p, values, displayValues) {
					var time = new Date(displayValues).getTime();
					$("input[name='birthday']").val(time);
				},
				minDate: [d.getFullYear() + "-" + (d.getMonth() + 1) +
					"-" + (d.getDate() - 1)
				]
			});
		}

		// 从业资格证有效期限
		if (driverInfo.practitionerLicenseDate) {
			var practitionerLicenseDate = new Date(driverInfo.practitionerLicenseDate).Format("yyyy-MM-dd");
			var d = new Date();
			$("#practitionerLicenseDate").calendar({
				value: [practitionerLicenseDate],
			});
		} else {
			var d = new Date();
			$("#practitionerLicenseDate").calendar({
				value: [date],
				onChange: function (p, values, displayValues) {
					var time = new Date(displayValues).getTime();
					$("input[name='practitionerLicenseDate']").val(time);
				},
				minDate: [date]
			});
		}

		// 显示司机信息
		for (item in driverInfo) {
			if ("sex" != item) {
				$("#" + item).text(driverInfo[item] ? driverInfo[item] : "");
				$("input[name='" + item + "']").val(driverInfo[item] ? driverInfo[item] : "");
			}
		}

		// 如果有身份证号但是没有驾驶证号，默认将驾驶证号填写为身份证号
		if (!$("input[name='licenseNumber']").val() && $("input[name='idCord']").val()) {
			$("input[name='licenseNumber']").val($("input[name='idCord']").val());
		}

		$("#sex option").each(function () {
			if ($(this).val() == driverInfo.sex) {
				$(this).attr("selected", true);
			}
		});

		// 图片信息
		sfzlist.map(function (item, index) {
			var path = getContextPath() + (item.attaPath ? item.attaPath : "resources/images/list-ico/no-img.png");
			$(".contract-img")[index].src = path;
			$("#upload-sfz").find(".file-btn")[index].id = item.id;
			photos.push(path);
		});

		jszlist.map(function (item, index) {
			var path = getContextPath() + item.attaPath;
			$(".contract-img")[index + 2].src = path;
			$("#upload-jsz").find(".file-btn")[index].id = item.id;
			photos.push(path);
		});

		zgzlist.map(function (item, index) {
			var path = getContextPath() + item.attaPath;
			$(".contract-img")[index + 4].src = path;
			$("#upload-zgz").find(".file-btn")[index].id = item.id;
			photos.push(path);
		});

		var myPhotoBrowserStandalone = $.photoBrowser({
			photos: photos
		});

		$('.preview').on('click', function () {
			myPhotoBrowserStandalone.open($(".preview").index(this));
		});
	}

	// 做添加的URL
	var addUrl = getContextPath() + "driverInfo/doAdd";
	// 做编辑的URL
	var editUrl = getContextPath() + "driverInfo/doEdit";


	/**
	 * 给所有的input添加change事件，如果有变化，将isChanged标识置为true
	 */
	$("#module1").on("change", ".baseInfo-input", function () {
		isChanged1 = true;
	});
	$("#module2").on("change", ".sfz-input", function () {
		isChanged2 = true;
	});
	$("#module3").on("change", ".jsz-input", function () {
		isChanged3 = true;
	});
	$("#module4").on("change", ".zgz-input", function () {
		isChanged4 = true;
	});

	// 填充车型选择页面
	getCxList();

	/**
	 * 打开选择车型页面
	 */
	$("#go-type").click(function () {
		$.popup("#choose-type");
	});

	/**
	 * 关闭选择车型页面
	 */
	$("#close-type").click(function () {
		$.closeModal("#choose-type");
	});

	/**
	 * 输入公司名称就进行查询
	 */
	$("#title").keyup(function () {
		doSearch();
	});

	/**
	 * 返回事件
	 */
	$("#goBack").click(function () {
		// 如果有修改痕迹，那么就提示是否退出编辑，否则就直接返回到详情页
		if (isChanged1 || isChanged2 || isChanged3) {
			$.confirm("确定放弃本次编辑吗？", function () {
				location.href = getContextPath() + "driverInfo/detail";
			});

		} else {
			location.href = getContextPath() + "driverInfo/detail";
		}
	});

	$("#goSave").click(function () {

		if ($(".active").attr("id") == "module1") {
			var name = $("input[name='name']").val();
			if (!name) {
				$.toast("请填写司机姓名！");
				$.hidePreloader();
				return
			}
			if (!/^[\u4e00-\u9fa5]+$/.test(name)) {
				$.toast("请正确填写司机姓名！");
				$.hidePreloader();
				return
			}

			// 如果已有ID且信息被修改过，那么就去按照编辑的逻辑保存
			var url = "";
			if ($("#id").val()) {
				url = editUrl;
			} else if (!$("#id").val()) {
				// 如果没有记录ID，说明还没有进行过保存，那么就按新增的逻辑保存
				url = addUrl;
			}

			$.showPreloader();

			$.ajax({
				url: url,
				data: $("#baseInfo").serialize() + "&citizenNo=" + citizenNo,
				cache: false,
				processData: false,
				dataType: "JSON",
				type: "POST",
				cache: false,
				success: function (data) {
					data = JSON.parse(data);
					if (data.success) {
						$("#id").val(data.id);
						driverInfoId = data.id;
						isChanged = false;
						$.hidePreloader();

						$.toast("保存成功！")
					} else {
						$.hidePreloader();
						$.toast(data.errMsg);
					}
				}
			});
		} else {
			// 当前页的form
			var form = $(".active").find("form");
			// 当前页的数据
			var data = form.serialize() + "&id=" + $("#id").val();
			var url = $("#id").val() ? editUrl : addUrl;
			$.post(url, data, function (data) {
				if (data.success) {
					$("#id").val(data.id);
					$.toast("保存成功！");
					$.hidePreloader();
				} else {
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
	$("#submit-baseInfo").click(function () {
		// 司机姓名校验
		var name = $("input[name='name']").val();
		/*if(!name){
			$.toast("请填写司机姓名！");
			$.hidePreloader();
			return 
		}*/
		var nullflag = false;

		$("#module1 input[required='required']").each(function () {
			if (!$(this).val()) {
				nullflag = true;
				return
			}
		});

		if (nullflag) {
			$.toast("请检查有*必填项是否填写完整，或信息格式是否正确!");
			return
		}

		if (!/^[\u4e00-\u9fa5]+$/.test(name)) {
			$.toast("请正确填写司机姓名！");
			$.hidePreloader();
			return
		}

		//验证联系电话
		var phoneNumber = document.getElementById('phoneNumber').value;
		if (!(/^1(3|4|5|6|7|8|9)\d{9}$/.test(phoneNumber))) {
			$.toast("联系电话格式错误，请重填!");
			document.getElementById("phoneNumber").value = "";
			return false;
		}

		// 如果已有ID且信息被修改过，那么就去按照编辑的逻辑保存
		var url = "";
		if ($("#id").val() && isChanged1) {
			url = editUrl;
		} else if (!$("#id").val()) {
			// 如果没有记录ID，说明还没有进行过保存，那么就按新增的逻辑保存
			url = addUrl;
		} else {
			// 如果没有输入新的值，那么就不执行保存，直接进入下一步，避免频繁保存，占用资源
			$("#module1").removeClass().addClass("hidden");
			$("#module2").removeClass().addClass("active");
			return
		}

		$.showPreloader();

		$.ajax({
			url: url,
			data: $("#baseInfo").serialize() + "&citizenNo=" + citizenNo,
			cache: false,
			processData: false,
			dataType: "JSON",
			type: "POST",
			cache: false,
			success: function (data) {
				data = JSON.parse(data);
				if (data.success) {
					$("#id").val(data.id);
					isChanged = false;
					$("#module1").removeClass().addClass("hidden");
					$("#module2").removeClass().addClass("active");
					$.hidePreloader();
				} else {
					$.hidePreloader();
					$.toast(data.errMsg);
				}
			}
		});
	});

	$("#backto-baseinfo").click(function () {
		$("#module2").removeClass().addClass("hidden");
		$("#module1").removeClass().addClass("active");
	});

	/**
	 * 根据type获取字典信息
	 * @param {string} type 分类名称
	 */
	function getCxList() {
		$.ajax({
			url: getContextPath() + "dictionary/getByType",
			data: {
				typeCode: "Car_Model",
			},
			dataType: "JSON",
			type: "GET",
			success: function (data) {
				data = JSON.parse(data);
				if (data.success && !$.isEmptyObject(data.result)) {
					var list = data.result;
					var html = "";

					for (var i = 0; i < list.length; i++) {
						html += '<a href="javascript:;" onclick="setCx(\'' + list[i].typecode + '\')" ' +
							'class="aui-flex b-line">' +
							'<div class="aui-flex-box">' +
							'<p>' + list[i].typename + '</p></div></a>';
					}

					$("#content-type").html(html);
				} else {
					var cont = '<div align="center" style="margin-top: 20%;"><img style="width:100px;position: absolute;left: 50%;margin-left: -50px;" src="../resources/images/nodata.png"/></div>';
					$("#content-type").html(cont);
				}
			}
		});
	}

	/**
	 * 保存并提交身份证相关信息
	 */
	$("#submit-sfz").click(function () {

		var src = "../resources/images/list-ico/no-img.png";
		var notUploaded = ($("#faceImg").attr("src") == src) || ($("#emblemImg").attr("src") == src)
		if (notUploaded) {
			$.toast("请上传身份证附件！");
			return
		}

		$.showPreloader();

		if (isChanged2) {
			// 身份证信息校验
			var IDNumber = $("input[name='idCord']").val();
			if (!IDNumber) {
				$.toast("请填写身份证号！");
				return
			}

			var reg = /^[1-9]\d{5}(18|19|20|(3\d))\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
			if (reg.test(IDNumber)) {
				var params = $("#upload-sfz").serialize() + "&id=" + $("#id").val() + "&companyId=" + $('#companyId').val();
				$.getJSON(editUrl, params, function (data) {
					if (data.success) {
						$("#module2").removeClass().addClass("hidden");
						$("#module3").removeClass().addClass("active");
						isChanged2 = false;
						$.hidePreloader();
					} else {
						$.hidePreloader();

						$.toast(data.errMsg);
					}
				});
			} else {
				$.hidePreloader();
				$.toast("请填写正确的身份证号！")
			}
		} else {
			$("#module2").removeClass().addClass("hidden");
			$("#module3").removeClass().addClass("active");

			$.hidePreloader();
		}
	});

	/**
	 * 保存并提交驾驶证相关信息
	 */
	$("#submit-jsz").click(function () {
		// 驾驶证信息校验
		var jszNumber = $("input[name='licenseNumber']").val();

		if (!jszNumber) {
			$.toast("请填写驾驶证号！");
			return
		}
		var reg = /^[1-9]\d{5}(18|19|20|(3\d))\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
		if (!reg.test(jszNumber)) {
			$.toast("请正确填写 驾驶证号！");
			return
		}

		// 判断是否上传过图片
		var noImg = '../resources/images/list-ico/no-img.png';
		var jsz1 = $('#originalImg').attr('src') == noImg ? false : true;
		var jsz2 = $('#duplicateImg').attr('src') == noImg ? false : true;
		if (!jsz1 || !jsz2) {
			$.toast('请上传驾驶证！');
			return;
		}

		var licensingDate = $("input[name='licensingDate']").val();
		var periodValidity = $("input[name='periodValidity']").val();

		if (!licensingDate || !periodValidity) {
			$.toast("请检查有*必填项是否填写完整，或信息格式是否正确!");
			return
		}

		var src = "../resources/images/list-ico/no-img.png";
		var notUploaded = ($("#originalImg").attr("src") == src) || ($("#duplicateImg").attr("src") == src)
		if (notUploaded) {
			$.toast("请上传驾驶证证附件！");
			return
		}

		$.showPreloader();
		var data = $("#upload-jsz").serialize() + "&id=" + $("#id").val();
		data += "&checkStatu=0";
		if (isChanged3) {
			$.getJSON(editUrl, data, function (data) {
				if (data.success) {
					isChanged3 = false;
					$.hidePreloader();

					// 翻页
					$("#module3").removeClass().addClass("hidden");
					$("#module4").removeClass().addClass("active");

				} else {
					$.hidePreloader();

					$.toast(data.errMsg);
				}
			});
		} else {
			$("#module3").removeClass().addClass("hidden");
			$("#module4").removeClass().addClass("active");
			$.hidePreloader();
		}
	});

	/**
	 * 返回到身份证信息页面
	 */
	$("#backto-sfz").click(function () {
		$("#module3").removeClass().addClass("hidden");
		$("#module2").removeClass().addClass("active");
	});

	/**
	 * 保存并提交资格证相关信息
	 */
	$("#submit-zgz").click(function () {
		var data = $("#upload-zgz").serialize() + "&id=" + $("#id").val();

		var valid = true;
		$("#upload-zgz input[required='required']").each(function () {
			var value = $.trim($(this).val());
			if (value == '') {
				valid = false;
			}
		});

		if (!valid) {
			$.toast("请检查有*必填项是否填写完整，或信息格式是否正确!");
			return false;
		}

		//验证从业资格证号
		var qualificationCode = $("#qualificationCode").val();
		if (!(/^[a-zA-Z\d]+$/.test(qualificationCode))) {
			$.toast("请填写正确的从业资格证号!");
			return;
		}

		$.showPreloader();
		var data = $("#upload-jsz").serialize() + "&id=" + $("#id").val() + "&checkStatu=0";

		if (isChanged4) {
			$.getJSON(editUrl, data, function (data) {
				if (data.success) {
					isChanged4 = false;
					$.hidePreloader();

					$.toast("保存成功，已提交审核！");

					setTimeout(function () {
						location.href = getContextPath() + "frontend/personal";
					}, 500);

				} else {
					$.hidePreloader();

					$.toast(data.errMsg);
				}
			});
		} else {
			location.href = getContextPath() + "frontend/personal";
		}
	});

	/**
	 * 上传身份证附件
	 */
	$("#upload-sfz").on('change', '.file-btn', function () {
		$.showPreloader();

		var obj = $(this);
		var file = obj[0].files[0];
		var imgId = obj.attr("id");
		var formData = new FormData();
		formData.append("img", file);
		formData.append("driverInfoId", $("#id").val());
		formData.append("oldImgId", imgId);

		$.ajax({
			url: getContextPath() + "tbDriverInfoSfz/uploadSingle",
			data: formData,
			type: "POST",
			contentType: false,
			processData: false,
			cache: false,
			success: function (data) {
				if (data.success) {
					// 获取新的图片路径，替换旧图片在photos中的位置
					obj.val('');
					var img = obj.parents(".b-line").find("img");
					var src = getContextPath() + data.result.attaPath;
					photos.replace(img.attr("src"), src);
					img.attr("src", src);

					obj.attr("id", data.result.id);

					// 重新生成图片浏览器
					var myPhotoBrowserStandalone = $.photoBrowser({
						photos: photos
					});
					// 为防止出现冲突，先去除掉原有的点击事件
					$('.preview').off('click');
					// 再重新添加新的事件
					$('.preview').on('click', function () {
						myPhotoBrowserStandalone.open($(".preview").index(this));
					});

					$.hidePreloader();
				} else {
					$.hidePreloader();

					$.toast(data.errMsg);
				}
			}
		});
	});

	$("#upload-jsz").on('change', '.file-btn', function () {
		$.showPreloader();

		var obj = $(this);
		var file = obj[0].files[0];
		var imgId = obj.attr("id");
		var formData = new FormData();
		formData.append("img", file);
		formData.append("driverInfoId", $("#id").val());
		formData.append("oldImgId", imgId);

		$.ajax({
			url: getContextPath() + "tbDriverInfoJsz/uploadSingle",
			data: formData,
			type: "POST",
			contentType: false,
			processData: false,
			cache: false,
			success: function (data) {
				if (data.success) {
					obj.val('');
					var img = obj.parents(".b-line").find("img");
					var src = getContextPath() + data.result.attaPath;
					photos.replace(img.attr("src"), src);
					img.attr("src", src);

					obj.attr("id", data.result.id);

					// 重新生成图片浏览器
					var myPhotoBrowserStandalone = $.photoBrowser({
						photos: photos
					});

					$('.preview').off('click');

					$('.preview').on('click', function () {
						myPhotoBrowserStandalone.open($(".preview").index(this));
					});

					$.hidePreloader();
				} else {
					$.hidePreloader();

					$.toast(data.errMsg);
				}
			}
		});
	});

	$("#upload-zgz").on('change', '.file-btn', function () {
		var driverInfoId = $("#id").val();

		var obj = $(this);
		var file = obj[0].files[0];
		// 限制上传文件的格式
		var reg = /.(gif|jpg|jpeg|png|GIF|JPG|bmp)$/;
		if (!reg.test(file.name)) {
			$(this).val(''); // 如果不符合条件就清除控件中的文件流
			$.toast("请上传图片格式的文件！");
			return;
		}

		var imgId = obj.attr("id");
		var formData = new FormData();
		formData.append("img", file);
		formData.append("driverInfoId", driverInfoId);
		formData.append("oldImgId", imgId);

		$.showPreloader();

		$.ajax({
			url: getContextPath() + "tbAttachmentZgz/uploadSingle",
			data: formData,
			type: "POST",
			contentType: false,
			processData: false,
			cache: false,
			success: function (data) {
				if (data.success) {
					// 获取新的图片路径，替换旧图片在photos中的位置
					obj.val('');
					var img = obj.parents(".b-line").find("img");
					var src = getContextPath() + data.result.attaPath;
					photos.replace(img.attr("src"), src);
					img.attr("src", src);

					obj.attr("id", data.result.id);

					// 重新生成图片浏览器
					var myPhotoBrowserStandalone = $.photoBrowser({
						photos: photos
					});
					// 为防止出现冲突，先去除掉原有的点击事件
					$('.preview').off('click');
					// 再重新添加新的事件
					$('.preview').on('click', function () {
						myPhotoBrowserStandalone.open($(".preview1").index(this));
					});

					$.hidePreloader();
				} else {
					$.hidePreloader();

					$.toast(data.errMsg);
				}
			}
		});
	});

	/**
	 * 返回驾驶证页面
	 */
	$('#backto-jsz').click(function () {
		$("#module4").removeClass().addClass("hidden");
		$("#module3").removeClass().addClass("active");
	});
});

/**
 * 设置车型
 * @param {string} name 
 */
function setCx(name) {
	$("input[name='models']").val(name).change();
	$("input[name='modelsCode']").val(name);
	$("#models").text(name);
	$.closeModal("#choose-type");
}


//所属公司选择事件
function chooseCompany() {
	$.popup("#companys");
}

/**
 * 查询公司列表
 * @returns
 */
function doSearch() {
	// 如果没有输入关键字则提示
	var title = $("#title").val();
	var res = /^[\u4e00-\u9fa5]+$/;
	if (!title || !res.test(title)) {
		return
	}
	// 查询前清空上一次查询结果
	$(".comlist").remove();

	// 根据用户输入的关键字从tb_coal_related表查询公司列表，并展示
	$.ajax({
		url: getContextPath() + "coalRelated/getList",
		data: {
			name: $("#title").val(),
			pageIndex: "-1", // 分页信息-1表示不分页
			pageSize: "-1",
		},
		type: "POST",
		dataType: "JSON",
		success: function (data) {
			data = JSON.parse(data);
			if (data.success) {
				// 公司信息
				var companys = data.list;
				var html = "";
				companys.map(function (item, index) {
					// popup模板内容
					var popTemplate = "<div class=\"aui-flex b-line comlist\"" +
						" onclick=\"setCom('" + item.id + "','" + item.code + "','" + item.name +
						"');\">" + "<div class=\"aui-flex-box\">" + "<p>" +
						item.name + "</p></div>" +
						"</div>";

					html += popTemplate;
				});

				if ($.trim(html) == "") {
					$.toast("暂无相关公司信息！");
				} else {
					$("#companysDiv").append(html);
				}
				/*$.popup("#companys");*/
			} else if (data.success == false) {
				$.toast("暂无相关公司信息！");
			}

		}
	});
}
/**
 * 设置公司信息
 * @param id
 * @param name
 * @returns
 */
function setCom(id, code, name) {
	$("#companyNameSelect").text(name);
	$("#companyId").val(id);
	$("#companyCode").val(code);
	$("#companyName").val(name);
	$("#companyName").css("color", "#777");
	isChanged1 = true;
	$.closeModal("#companys");
	$("#title").val("");
	$(".comlist").remove();

}

/**
 * 关闭公司选择
 * @param 
 * @param 
 * @returns
 */
function closeCompany() {
	$.closeModal("#companys");
	$("#title").val("");
	$(".comlist").remove();

}


//截取身份证号中的出生年月
function stringToDate(numberOwner) {
	//var numberOwner = document.getElementById('numberOwner').value;
	if (numberOwner.length == 15) {

		var subNumberOwer = numberOwner.substring(6, 12);
		var year = "19" + subNumberOwer.substring(0, 2);
		var month = subNumberOwer.substring(2, 4);
		var day = subNumberOwer.substring(4, 6);
		var newSubNumberOwer = year + "-" + month + "-" + day;

		var dtArr = newSubNumberOwer.split("-");
		var dtDate = new Date(dtArr[0], dtArr[1] - 1, dtArr[2]).Format("yyyy-MM-dd");

		//$("#bornOwner").val(dtDate);
		$("#birthday").val(dtDate);

	} else if (numberOwner.length == 18) {

		var subNumberOwer = numberOwner.substring(6, 14);
		var year = subNumberOwer.substring(0, 4);
		var month = subNumberOwer.substring(4, 6);
		var day = subNumberOwer.substring(6, 8);
		var newSubNumberOwer = year + "-" + month + "-" + day;

		var dtArr = newSubNumberOwer.split("-");
		var dtDate = new Date(dtArr[0], dtArr[1] - 1, dtArr[2]).Format("yyyy-MM-dd");

		//$("#bornOwner").val(dtDate);
		$("#birthday").val(dtDate);

	}
}


//截取身份证号中的出生年月
function interceptBirthday() {
	var numberOwner = document.getElementById('idCord').value;
	if (numberOwner.length == 15) {

		var subNumberOwer = numberOwner.substring(6, 12);
		var year = "19" + subNumberOwer.substring(0, 2);
		var month = subNumberOwer.substring(2, 4);
		var day = subNumberOwer.substring(4, 6);
		var newSubNumberOwer = year + "-" + month + "-" + day;

		var dtArr = newSubNumberOwer.split("-");
		var dtDate = new Date(dtArr[0], dtArr[1] - 1, dtArr[2]).Format("yyyy-MM-dd");

		//$("#bornOwner").val(dtDate);
		$("#birthday").val(dtDate);

		//同步跟新驾驶证号
		$("#licenseNumber").val(numberOwner);

		isChanged1 = true;

	} else if (numberOwner.length == 18) {

		var subNumberOwer = numberOwner.substring(6, 14);
		var year = subNumberOwer.substring(0, 4);
		var month = subNumberOwer.substring(4, 6);
		var day = subNumberOwer.substring(6, 8);
		var newSubNumberOwer = year + "-" + month + "-" + day;

		var dtArr = newSubNumberOwer.split("-");
		var dtDate = new Date(dtArr[0], dtArr[1] - 1, dtArr[2]).Format("yyyy-MM-dd");

		//$("#bornOwner").val(dtDate);
		$("#birthday").val(dtDate);

		//同步跟新驾驶证号
		$("#licenseNumber").val(numberOwner);

		isChanged1 = true;
	}
}