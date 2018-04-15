<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
</head>
<body>
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox ">
					<div class="ibox-title">
						<h5>驾驶员管理</h5>
						<div class="ibox-tools"></div>
					</div>

					<div class="ibox-content">
						<form role="form" class="form-inline">
							<div class="form-group">
								<label for="exampleInputEmail2" class="sr-only">姓名</label> <input
									type="text" placeholder="姓名" id="_name" class="form-control">
							</div>
							<button class="btn btn-primary" type="button" id='_search'>查询</button>
							<button class="btn btn-primary" type="button" id='_new'>新建</button>
						</form>
					</div>

					<div class="ibox-content ">
						<table ID='dt_table_view'
							class="table table-striped table-bordered table-hover ">
							<thead>
								<tr>
									<th>姓名</th>
									<th>年龄</th>
									<th>驾驶证号码</th>
									<th>驾照状态</th>
									<th>驾驶年龄</th>
									<th>电话</th>
									<th>地址</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
						<img alt="" src="" id='barcode'>
					</div>

				</div>
			</div>
		</div>
	</div>

	<div id='_form' style="margin-top: 30000px " >
		<div class="ibox-content" >
			<div class="row">
				<div class="col-sm-12 b-r">
					<form class="form-horizontal" action="" method="get">
						<input name='id' type="hidden" />
						<table class='table table-bordered'>
							<thead>
								<tr style="text-align: center;">
									<td colspan="6"><h3>驾驶员信息</h3></td>
								</tr>
							</thead>
							<tbody>
							
								<tr>
									<td>驾驶员姓名</td>
									<td><input name='name' type="text" class="form-control"></td>
								</tr>
								
								<tr>
									<td>驾驶员年龄</td>
									<td><input name='age' type="text" class="form-control"></td>
								</tr>
								
								<tr>
									<td>驾龄</td>
									<td><input name='driveryear' type="text" class="form-control"></td>
								</tr>
								<tr>
									<td>驾驶证号</td>
									<td><input name='cardno' type="text" class="form-control"></td>
								</tr>
								<tr>
									<td>驾照状态</td>
									<td>
										<select name='cardstate' class="form-control">
											<option value="正常">正常</option>
											<option value="有违章未处理">有违章未处理</option>
											<option value="已吊销">已吊销</option>
										</select>
									</td>
								</tr>
								<tr>
									<td>驾驶员电话</td>
									<td><input name='tel' type="text" class="form-control"></td>
								</tr>
								
								<tr>
									<td>地址</td>
									<td><input name='addr' type="text" class="form-control"></td>
								</tr>
								
								
								<tr>
									<td colspan="6">
										<div class="col-sm-4 col-sm-offset-2">
											<button class="btn btn-primary" type="button"
												onclick="submit_form()">提交</button>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script>
    var table=null;
    
    function submit_form(){
    	$.ajax({
    		   type: "POST",
    		   url:  $.common.getContextPath() + "/sys/driver/save",
    		   data: $("form").serialize(),
    		   success: function(msg){
    		     if(msg.code==1){
    		    	 toastr.success('操作成功');
    		    	 table.draw();
    		    	 initImgCode();
    		     }
    		     layer.closeAll() ;
    		   }
    		});
     }
    
    function fun_delete(id){
    	layer.confirm('确定删除当前驾驶员？', {
    		  btn: ['确定','取消'] //按钮
    		}, function(){
    			$.ajax({
    		 		   url:  $.common.getContextPath() + "/sys/driver/delete?id="+id,
    		 		   success: function(msg){
    		 		     if(msg.code==1){
    		 		    	 toastr.success('操作成功');
    		 		    	 table.draw();
    		 		     }
    		 		     layer.closeAll() ;
    		 		   }
    		 	});
    		}, function(){
    			 layer.closeAll() ;
    		});
     }
    
    function fun_update(id){
    	$.ajax({
 		   url:  $.common.getContextPath() + "/sys/driver/get?id="+id,
 		   success: function(msg){
 		     if(msg.code==1){
 		    	$("input[name='id']").val(msg.datas.id);
 		    	$("input[name='addr']").val(msg.datas.addr);
 		    	$("input[name='age']").val(msg.datas.age);
 		    	$("input[name='cardno']").val(msg.datas.cardno);
 		    	$("select[name='cardstate']").val(msg.datas.cardstate);
 		    	$("input[name='driveryear']").val(msg.datas.driveryear);
 		    	$("input[name='name']").val(msg.datas.name);
 		    	$("input[name='tel']").val(msg.datas.tel);
 				$("textarea[name='remark']").val(msg.datas.remark);
        		layer.open({
        			  type: 1,
        			  skin: 'layui-layer-rim', //加上边框
        			  content: $("#_form"),
        			  area: "800px"
        			});
        		$("#_form").css("margin-top","0px");
 		     }
 		   }
 		});
     }
    $(document).ready(function(){
    	
    	////仓库编码	仓库名称	存货编码	规格型号	批号	现存数量	到货在检数量	调拨在途数量	预计入库数量合计	待发货数量	调拨待发数量	可用数量

        	$("#_new").click(function(){
        		$("input[name='id']").val("");
 		    	$("input[name='name']").val("");
 		    	$("radio[name='sex']").val("");
 		   		$("input[name='price']").val("");
 		   		$("input[name='seat']").val("");
 				$("textarea[name='remark']").val("");
        		layer.open({
        			  type: 1,
        			  skin: 'layui-layer-rim', //加上边框
        			  content: $("#_form"),
        			  area: "800px"
        			});
        		$("#_form").css("margin-top","0px");
        	});
        	
        	table=$('#dt_table_view').DataTable( {
        		"dom": "rt<'row'<'col-sm-5'i><'col-sm-7'p>>",
	            "ajax": {
	                "url":  $.common.getContextPath() + "/sys/driver/list",
	                "type": "POST",
	                "async":false,
	                "dataSrc": "datas"
	              },
					
				"columns" : [{
					"data" : "name"
				}, {
					"data" : "age"
				}, {
					"data" : "cardno"
				}, {
					"data" : "cardstate"
				}, {
					"data" : "driveryear"
				},{
					"data" : "tel",
				},{
					"data" : "addr",
				},{
					"data" : "id",
				}] ,
				 "columnDefs": [
				                
				                {
				                    "render": function ( data, type, row ) {
				                        return "<a tager='_blank' href='javascript:void(0)' onclick='fun_delete("+data+")'>删除 </a>"+
				                        "<a tager='_blank' href='javascript:void(0)' onclick='fun_update("+data+")'>编辑 </a>";
				                    },
				                    "targets":7
				                }
				               
				            ],
        		"initComplete": function () {
        			var api = this.api();
        			$("#_search").on("click", function(){
            		 	api.draw();
        			} );
        		} 
        	 } ).on('preXhr.dt', function ( e, settings, data ) {
		        	data.value = $("#_name").val();
		        	data.columnname = 'name';
		        	return true;
		     } ).on('xhr.dt', function ( e, settings, json, xhr ) {
		    		 $(".dataTables_processing").hide();
		     } )
        });
    
    
    function initImgCode(){
	        options = {
	            format:"CODE128",
	            displayValue:true,
	            fontSize:18,
	            height:20
	        };
	       
	        $('.img-code').each(function (){
	        	$(this).JsBarcode($(this).attr("code"),options);
	        });
    }
    initImgCode();
    </script>
<script src="${pageContext.request.contextPath}/js/JsBarcode.all.js "></script>
</body>
</html>
