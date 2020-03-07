$(function () {
	$('#next-btn').on('click', function () {
		// 表单校验
		//var username = $.trim($('#username').val());
		//var realName = $.trim($('#realName').val());
		var password = $.trim($('#password').val());
		var idCord = $.trim($('#idCord').val());
		var verifyCodeActual = $('#j_captcha').val();
		//var noHan = /^[^\u4e00-\u9fa5]{0,}$/;  // 不含有中文
		//var nameReg = /^[\u4e00-\u9fa5]{2,6}$/;  // 必须中文（不含有生僻字）且长度2-6位
		var passwordReg = /^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]{6,18}$/; //密码规则校验（字母，数字，区分大小写）
		/* if (!username) {
			$.toast('请输入登录用户名！');
			return;
		} 

		if(!noHan.test(username)){
			$.toast("登录用户名不能包含汉字！")
			return;
		}

		if(username.length < 4){
			$.toast("登录用户名不能小于4个字符！")
			return;
		}

		if (!realName) {
			$.toast('请输入真实姓名！');
			return;
		}
		if (!nameReg.test(realName)) {
			$.toast('真实姓名应当为2-6位汉字！');
			return;
		} */
		if (!password) {
			$.toast('请输入密码！');
			return;
		}
		if (!passwordReg.test(password)) {
			$.toast('密码应当输入6-18位数字或字母！');
			return;
		}
		if (!idCord) {
			$.toast('请输入您的身份证号码！');
			return;
		}

		if (!isCardNo(idCord)) {
			$.toast('身份证格式不正确，请核对后重新输入！');
			return;
		}

		if(!verifyCodeActual){
			$.toast('请输入验证码！');
			return;
		}

		//注册账号
		$.ajax({
			url: getContextPath() + 'driverlogin/signinUser',
			async: false,
			cache: false,
			type: "post",
			dataType: 'json',
			data: {
				//userName: username,
				//realName: realName,
				password: password,
				idCord: idCord,
				verifyCodeActual: verifyCodeActual,
			},
			success: function (data) {
				if (data.success) {
					$.toast(data.msg);
					location.href = getContextPath() + 'driverlogin/index';
				} else {
					$.toast(data.errMsg);
					changeVerifyCode(document.getElementById("captcha_img"));
				}
			},
			error: function (jqXHR, textStatus, errorThrown) {
				$.toast("未知错误");
			}
		});

	});

	/**
	 * 返回
	 */
	$('.goBack').on('click', function () {
		var url = getContextPath() + 'frontend/protocol';
		$.router.load(url);
	});
});


/**
 * 验证用户名
 */
function usernameChange(){
	var noHan = /^[^\u4e00-\u9fa5]{0,}$/;  // 不含有中文
	var username = $.trim($('#username').val());
	if (!username) {
		$.toast('请输入登录用户名！');
		return;
	}
	if(!noHan.test(username)){
		$.toast("登录用户名不能包含汉字！")
	}
}

/**
 * 验证真实姓名
 */
function realNameChange(){
	var nameReg = /^[\u4e00-\u9fa5]{2,6}$/;  // 必须中文（不含有生僻字）且长度2-6位
	
	var realName = $.trim($('#realName').val());
	if (!realName) {
		$.toast('请输入真实姓名！');
		return;
	}
	if (!nameReg.test(realName)) {
		$.toast('真实姓名应当为2-6位汉字！');
		return;
	}
}

/**
 * 验证密码
 */
function passwordChange(){
	var passwordReg = /^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]{6,18}$/; //密码规则校验（字母，数字，区分大小写）
	
	var password = $.trim($('#password').val());
	if (!password) {
		$.toast('请输入密码！');
		return;
	}
	if (!passwordReg.test(password)) {
		$.toast('密码应当输入6-18位数字或字母！');
		return;
	}
}

/**
 * 验证身份证号码
 */
function idCordChange(){
	var idCord = $.trim($('#idCord').val());
	
	if (!idCord) {
		$.toast('请输入您的身份证号码！');
		return;
	}
	if (!isCardNo(idCord)) {
		$.toast('身份证格式不正确，请核对后重新输入！');
		return;
	}
}