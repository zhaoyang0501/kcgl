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
						<h5>库存管理</h5>
						<div class="ibox-tools"></div>
					</div>

					<div class="ibox-content">
						<form role="form" class="form-inline">
							<div class="form-group">
								<label for="exampleInputEmail2" class="sr-only">货物名称</label> <input
									type="text" placeholder="名称" id="_name" class="form-control">
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
									<th>条码</th>
									<th>货物名称</th>
									<th>规格</th>
									<th>所属仓库</th>
									<th>入库数量</th>
									<th>入库单价</th>
									<th>总价</th>
									<th>供应商</th>
									<th>入库日期</th>
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
									<td colspan="6"><h3>货物信息</h3></td>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>名称</td>
									<td><input name='name' type="text" class="form-control"></td>
								</tr>
								
								<tr>
									<td>编码</td>
									<td><input name='code' type="text" class="form-control"></td>
								</tr>
								
								<tr>
									<td>规格</td>
									<td><input name='standard' type="text" class="form-control"></td>
								</tr>
								
								
								
								<tr>
									<td>入库数量</td>
									<td><input name='num' type="text" class="form-control"></td>
								</tr>
								
								<tr>
									<td>入库单价</td>
									<td><input name='useNum' type="text" class="form-control"></td>
								</tr>
								
								<tr>
									<td>入库总价</td>
									<td><input name='useNum' type="text" class="form-control"></td>
								</tr>
								
								<tr>
									<td>所属仓库</td>
									<td>
										<select name='foodCategory.id' class='form-control'>
									 			<c:forEach var="bean" items="${categorys}">
									 				<option value="${bean.id }">${bean.name }</option>
									 			</c:forEach>
									 	</select>
									 </td>
								</tr>
								
								

								<tr>
									<td>备注</td>
									<td><textarea name='remark' rows="4" cols=""
											style="width: 80%"></textarea></td>
								</tr>

								<tr>
									<td colspan="6">
										<div class="col-sm-4 col-sm-offset-2">
											<button class="btn btn-primary" type="button"
												onclick="submit_form()">提交</button>
											<button class="btn btn-white" type="submit">取消</button>
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
    		   url:  $.common.getContextPath() + "/sys/food/findAll",
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
    	layer.confirm('确定删除当前货物？', {
    		  btn: ['确定','取消'] //按钮
    		}, function(){
    			$.ajax({
    		 		   url:  $.common.getContextPath() + "/sys/food/delete?id="+id,
    		 		   success: function(msg){
    		 		     if(msg.code==1){
    		 		    	 toastr.success('操作成功');
    		 		    	 table.draw();
    		 		    	 initImgCode();
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
 		   url:  $.common.getContextPath() + "/sys/food/get?id="+id,
 		   success: function(msg){
 		     if(msg.code==1){
 		    	$("input[name='id']").val(msg.datas.id);
 		    	$("input[name='name']").val(msg.datas.name);
 		    	$("input[name='foodCategory.id']").val(msg.datas.foodCategory.id);
 		    	$("input[name='price']").val(msg.datas.price);
 				$("textarea[name='remark']").val(msg.datas.remark);
 				  $("#thelist").empty();
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
 				$("textarea[name='remark']").val("");
        		layer.open({
        			  type: 1,
        			  skin: 'layui-layer-rim', //加上边框
        			  content: $("#_form"),
        			  area: "800px"
        			});
        		$("#_form").css("margin-top","0px");
        	});
        	
        	/***
        	<th>条码</th>
									<th>存货编码</th>
									<th>货物名称</th>
									<th>规格</th>
									<th>所属仓库</th>
									<th>批号</th>
									<th>库存数量</th>
									<th>可用数量</th>
									<th>操作</th>
									*/
        	table=$('#dt_table_view').DataTable( {
        		"dom": "rt<'row'<'col-sm-5'i><'col-sm-7'p>>",
        		"deferLoading": 57,
	            "ajax": {
	                "url":  $.common.getContextPath() + "/sys/food/list",
	                "type": "POST",
	                "async":false,
	                "dataSrc": "datas"
	              },
				"columns" : [{
					"data" : "img"
				}, {
					"data" : "code"
				}, {
					"data" : "name"
				}, {
					"data" : "standard"
				},{
					"data" : "foodCategory.name",
				}, {
					"data" : "batchNo"
				},{
					"data" : "num",
				},{
					"data" : "useNum",
				},{
					"data" : "id",
				}] ,
				 "columnDefs": [
				                

								{
								    "render": function ( data, type, row ) {
								        return "<img alt='image'   code='"+row.code+"'  id='"+row.code+"' height: 50px;' class='img-code' >";
								    },
								    "targets":0
								}, 
				                {
				                    "render": function ( data, type, row ) {
				                        return "<a tager='_blank' href='javascript:void(0)' onclick='fun_delete("+data+")'>删除 </a>"+
				                        "<a tager='_blank' href='javascript:void(0)' onclick='fun_update("+data+")'>编辑 </a>";
				                    },
				                    "targets":8
				                }
				               
				            ],
        		"initComplete": function () {
        			var api = this.api();
        			$("#_search").on("click", function(){
            		 	api.draw();
            		 	initImgCode();
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
