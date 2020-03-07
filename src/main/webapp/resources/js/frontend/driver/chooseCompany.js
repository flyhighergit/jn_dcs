// 当前司机用户的身份证号
var citizenNo = getQueryString("citizenNo");
//当前登录用户的用户ID
var userId = getQueryString("userId");

$(function(){
	/**
	 * 输入公司名称就进行查询
	 */
	$("#title").keyup(function(){
		doSearch();
	});
})


/**
 * 查询公司列表
 * @returns
 */
function doSearch(){
	// 如果没有输入关键字则提示
	var title = $("#title").val();
	var res=/^[\u4e00-\u9fa5]+$/;
	if(!title||!res.test(title)){
		return
	}
	// 查询前清空上一次查询结果
	$(".comlist").remove();
	
	// 根据用户输入的关键字从tb_coal_related表查询公司列表，并展示
	$.ajax({
		url:getContextPath()+"coalRelated/getList",
		data:{
			name:$("#title").val(),
			pageIndex:"-1",// 分页信息-1表示不分页
			pageSize:"-1",
		},
		type:"POST",
		dataType:"JSON",
		success:function(data){
			data = JSON.parse(data);
			if(data.success){
				// 公司信息
				var companys = data.list;
				var html = "";
				companys.map(function(item, index) {
					// popup模板内容
					var popTemplate = "<div class=\"aui-flex b-line comlist\""
							+ " onclick=\"setCom('" + item.id + "','" + item.name
							+ "');\">" + "<div class=\"aui-flex-box\">" + "<p>"
							+ item.name + "</p></div>" 
							+"</div>";
					
					html += popTemplate;
				});
				
				if ($.trim(html) == "") {
					$.toast("暂无相关公司信息！");
				}else{
					$("#companysDiv").append(html);
				}
				/*$.popup("#companys");*/
			}else if(data.success==false){
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
function setCom(id,name){
	
	$.closeModal("#companys");
	$("#title").val("");
	$(".comlist").remove();
	
	location.href = getContextPath() + "driverInfo/edit?citizenNo="+citizenNo+"&companyId="+id+"&userId="+userId;
}

/**
 * 关闭公司选择
 * @param 
 * @param 
 * @returns
 */
function closeCompany(){
	$.closeModal("#companys");
	$("#title").val("");
	$(".comlist").remove();
	
	location.href = getContextPath() + "driverInfo/detail";
}