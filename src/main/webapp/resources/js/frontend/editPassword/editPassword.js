$(function () {

	document.getElementById("password").value = "";
	document.getElementById("newPassword").value = "";
	document.getElementById("newAgainPassword").value = "";

	/**
	 * 为后退键绑定返回事件
	 */
	$(".goBack").click(function () {
		var url = getContextPath() + 'frontend/personal';
		$.router.load(url);
	});

});

//验证密码复杂度
function checkpassword() {
	var a = true;
	var txt = $("#newPassword").val();

	if (txt.length < 6 || txt.length > 18) {
		$.toast("密码长度必须在6-18位");
		a = false;
	};

	return a;
}

//保存密码
function toUpdatePassword() {
	var password = $.trim($("#password").val());
	var newPassword = $.trim($("#newPassword").val());
	var newAgainPassword = $.trim($("#newAgainPassword").val());
	var verifyCodeActual = $('#j_captcha').val();

	if (isEmpty(password)) {
		$.toast("请输入原始密码");
		return;
	}
	if (!checkpassword()) {
		return;
	}
	if (newPassword != newAgainPassword) {
		$.toast("两次输入的密码不一致！");
		return;
	}

	if(!verifyCodeActual){
		$.toast('请输入验证码！');
		return;
	}
	
	$.ajax({
		type: 'post',
		url: getContextPath() + "driverlogin/changePwd",
		data: {
			password: password,
			newPassword: newPassword,
			verifyCodeActual: verifyCodeActual,
		},
		async: false,
		dataType: 'json',
		success: function (data) {
			if(data.resultFlag == "1"){
				$.toast("不存在当前用户！");
			}else if(data.resultFlag == "2"){
				$.toast("原始密码输入错误！");
			}else if (data.success) {
				$.toast('修改成功！');
				setTimeout(function () {
					var url = getContextPath() + 'frontend/personal';
					$.router.load(url);
				}, 1000);
			} else {
				$.toast(data.errMsg);
				if (data.login) {
					setTimeout(function () {
						var url = getContextPath() + 'driverlogin/login';
						$.router.load(url);
					}, 1000);
				}else {
					changeVerifyCode(document.getElementById("captcha_img"));
				}
			}
		}
	});
}