//当前登录用户的用户ID
var userId;
//当前登录人的身份证号
var citizenNo;

$(function(){
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
				citizenNo = data.tSBaseUser.idCord;
				userId = data.tSBaseUser.id;
			}
		}
	});
	
   
    
    /**
	 * 输入公司名称就进行查询
	 */
	$("#title").keyup(function(){
		doSearch();
	});

    $('.goBack').on('click',function(){
    	location.href = getContextPath() + 'frontend/personal'; 
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
					var popTemplate = '<a href="javascript:;" class="aui-flex b-line">'
	                                +'<div class="aui-img-build">'
	                                +'<img src="../resources/images/list-ico/build.png" alt="" />'
					                +'</div>'
					                +'<div class="aui-flex-box">'
					                +'<p>' + item.name + '</p>'
					                +'</div>'
					                +'<div class="aui-next" onclick="bindCompany('+ item.id +')">'
					                +'<i class=" fa fa-chain-broken"></i>'
					                +'绑定'
					                +'</div>'
					                +'</a>'
					
					html += popTemplate;
				});
				
				if ($.trim(html) == "") {
					$.toast("暂无相关公司信息！");
				}else{
					$("#companysDiv").append(html);
				}
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
	
	location.href = getContextPath() + 'frontend/personal';
}

function bindCompany(companyId){
	
    $.confirm('确定与该公司绑定吗？',function(){
    	var url = getContextPath() + "driverInfo/bindDriverInfo?userId="+userId+"&companyId="+companyId;
		$.getJSON(url, function(data) {
			if(data.success){
				$.toast('绑定成功！');
	            setTimeout(function(){
	            	location.href = getContextPath() + 'frontend/relation'; 
	            },3000);
			}else{
				$.toast('绑定失败！');
			}
		});
    });
}