/**承运历史列表页面JS*/

// 当前车辆车牌号
var licensePlate = getQueryString("licensePlate")
var dispatchingId = getQueryString("dispatchingId");

$(function(){
	$("#licensePlate").text(licensePlate);
	
	// 获取磅房作业记录
	$.ajax({
		url : getContextPath()+"poundrecord/getHistory",
		data:{
			licensePlate:licensePlate,
		},
		dataType:"JSON",
		type:"GET",
		success:function(data){
			data = JSON.parse(data);
			if(data.success){
				buildHtml(data);
			}
		}
	});
	
	/**
	 * 返回按钮触发
	 */
	$("#goBack").click(function(){
		location.href = getContextPath()+"appLogin/index?licensePlate="+licensePlate+"&dispatchingId="+dispatchingId;
	});
	
})

/**
 * 构建页面列表内容
 * @param data
 * @returns
 */
function buildHtml(data){
	var list = data.list;
	
	var html = "";
	for(var i=0;i<list.length;i++){
		var time1 = new Date(list[i].firstTime).Format("yyyy年  MM-dd hh:mm");
		var time2 = new Date(list[i].secondTime).Format("MM-dd hh:mm");
		var status = list[i].status == "1" ? "已完成" : "进行中";
		
		html += "<div class=\"aui-flex b-line\">"
			+ "<div class=\"aui-flex-box\">"
			+ "<p>" + status + "</p>"
			+ "<p>售卖方："+ (list[i].sellerName?list[i].sellerName:"") +"</p>"
			+ "<p>买受方："+ (list[i].buyerName?list[i].buyerName:"") +"</p>"
			+ "<p>起运地点："+ (list[i].companyName?list[i].companyName:"") +"</p>"
			+ "<p>作业时间："+ (list[i].secondTime?(time1+" 至 "+time2):(time1)) +"</p></div></div>"
			/* + (list[i].secondTime?("<div class=\"aui-text\">时间："+ time1 + " - " + time2 + "</div></div>"):
				("<div class=\"aui-text\">时间："+ time1 + "</div></div>")); */
	}
	
	$("#content").append(html);
}

/**
 * 根据派车ID跳转至派单详情页面
 * @param id
 * @returns
 */
function getDispatching(id){
	location.href = getContextPath()+"dispatchingInfo/detail?dispatchingId="+id+"&licensePlate="+licensePlate;
}




