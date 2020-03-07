	webRoot = location.host ;
	webRoot +=location.pathname.substring(0,location.pathname.indexOf('/',1));
// 给Date对象添加Format属性，用以格式化
Date.prototype.Format = function(fmt,zone) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	
	return fmt;
}

//替换数组中的某个值
Array.prototype.replace = function(oldVal,newVal) { 
	var index = this.indexOf(oldVal); 
	if (index > -1) {
		this[index] = newVal;
	}else{
		this.push(newVal)
	}
};

/**
 * 更换验证码
 * @param img
 * @returns
 */
function changeVerifyCode(img) {
	img.src = "../Kaptcha?" + Math.floor(Math.random() * 100);
}

/**
 * 获取url中的参数
 * @param {string}} name 
 */
function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) {
		return decodeURIComponent(r[2]);
	}
	return '';
}

/**
 * 获取项目的ContextPath以便修正图片路由让其正常显示
 * @returns
 */
function getContextPath(){
	return "/dcs/";
}

/**
 * 判断字符串是否为空的方法
 * @param str
 * @returns
 */
function isEmpty(str){
    if(typeof str == "undefined" || str == null || str == ""){
        return true;
    }else{
        return false;
    }
}

/*中文名*/
function isChinaName(name) {
    var pattern = /^[\u4E00-\u9FA5]{1,6}$/;
    return pattern.test(name);
}
/*用户名 字母数字下划线，2-50位*/
function isUserName(name) {
	var pattern = /^(\w){2,50}$/;
	return pattern.test(name);
}


// 验证身份证
function isCardNo(numberOwner) {
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

// 验证手机号
function isPhoneNo(phone) {
    var pattern = /^1[34578]\d{9}$/;
    return pattern.test(phone);
}

/*用户名判断*/
function userName(inputid, spanid) {
    $(inputid).blur(function() {
        if ($.trim($(inputid).val()).length == 0) {
            $(spanid).html("× 名称没有输入");
        } else {
            if (isChinaName($.trim($(inputid).val())) == false) {
                $(spanid).html("× 名称不合法");
            }
        }
    });
    $(inputid).focus(function() {
        $(spanid).html("");
    });

};
/*身份证判断*/
function userID(inputid, spanid) {
    $(inputid).blur(function() {
        if ($.trim($(inputid).val()).length == 0) {
            $(spanid).html("× 身份证号码没有输入");
        } else {
            if (isCardNo($.trim($(inputid).val())) == false) {
                $(spanid).html("× 身份证号不正确");
            }
        }
    });
    $(inputid).focus(function() {
        $(spanid).html("");
    });
};

/*手机号判断*/
function userTel(inputid, spanid) {
    $(inputid).blur(function() {
        if ($.trim($(inputid).val()).length == 0) {
            $(spanid).html("× 手机号没有输入");
        } else {
            if (isPhoneNo($.trim($(inputid).val())) == false) {
                $(spanid).html("× 手机号码不正确");
            }
        }
        $(inputid).focus(function() {
            $(spanid).html("");
        });
    });
};