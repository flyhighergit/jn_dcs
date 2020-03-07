$(function () {
	// 登录验证的controller url
	var loginUrl = getContextPath() + 'driverlogin/logincheck';
	// 登录次数，累积登录三次失败之后自动弹出验证码要求输入
	var loginCount = 0;

	$("#submit").on('click', function () {
		var idCord = $('#idCord').val();
		var password = $('#password').val();

		if (!idCord) {
			$.toast('请输入身份证号！');
			return
		}

		if (!password) {
			$.toast('请输入密码！');
			return
		}

		// 获取验证码信息
		var verifyCodeActual = $('#j_captcha').val();
		// 是否需要验证码验证，默认为false,即不需要
		var needVerify = false;
		// 如果登录三次都失败
		if (loginCount >= 3) {
			// 那么就需要验证码校验了
			if (!verifyCodeActual) {
				$.toast('请输入验证码！');
				return;
			} else {
				needVerify = true;
			}
		}

		//登录校验
		$.ajax({
			url: loginUrl,
			async: false,
			cache: false,
			type: "post",
			dataType: 'json',
			data: {
				idCord: idCord,
				password: password,
				verifyCodeActual: verifyCodeActual,
				//是否需要做验证码校验
				needVerify: needVerify
			},
			success: function (data) {
				data = $.isEmptyObject(data) ? {
					success: false,
					errMsg: "未知错误"
				} : data;
				if (data.success) {
					location.href = getContextPath() + 'driverlogin/index';
				} else {
					$.toast('登录失败！' + data.errMsg);
					loginCount++;
					if (loginCount >= 3) {
						changeVerifyCode(document.getElementById("captcha_img"));
						// 登录失败三次，需要做验证码校验
						$('#verifyPart').css('display', 'flex');
					}
				}
			},
			error: function (jqXHR, textStatus, errorThrown) {
				$.toast("未知错误");
			}
		});
	});

	/**
	 * 跳转
	 */
	$('#signin').on('click', function () {
		var url = getContextPath() + 'frontend/protocol';
		//location.href = getContextPath() + 'frontend/protocol';
		$.router.load(url);
	});

	/**
	 * 敲击回车后触发登录事件
	 */
	$(document).on('keydown', function (event) {
		if (event.keyCode == 13) {
			$("#submit").click();
		}
	});
});