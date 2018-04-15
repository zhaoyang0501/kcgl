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
						<h5>运单管理</h5>
						<div class="ibox-tools"></div>
					</div>

					<div class="ibox-content">
						<form role="form" class="form-inline">
							<div class="form-group">
								<label for="exampleInputEmail2" class="sr-only">单号</label> <input
									type="text" placeholder="单号" id="_name" class="form-control">
							</div>
							<button class="btn btn-primary" type="button" id='_search'>查询</button>
							<button class="btn btn-primary" type="button" id='_new'>填写运单</button>
						</form>
					</div>

					<div class="ibox-content ">
						<table ID='dt_table_view'
							class="table table-striped table-bordered table-hover ">
							<thead>
								<tr>
									<th>运单号</th>
									<th>下单日期</th>
									<th>货物</th>
									<th>数量</th>
									<th>运费</th>
									<th>运输人</th>
									<th>发出地</th>
									<th>目的地</th>
									<th>运单状态</th>
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
									<td colspan="6"><h3>运单信息</h3></td>
								</tr>
							</thead>
							<tbody>
							
								<tr>
									<td>运单号</td>
									<td><input name='sn' type="text" class="form-control"></td>
								</tr>
								
								<tr>
									<td>下单日期</td>
									<td><input name='orderDate' type="text" class="form-control"></td>
								</tr>
								
								<tr>
									<td>货物</td>
									<td>
											<select  name='food.id' class='form-control'>
                          						<c:forEach var="bean" items="${foods}">
						 							<option value="${bean.id }">${bean.name }</option>
						 						</c:forEach>
                          					</select>
									</td>
								</tr>
								
								
								<tr>
									<td>数量</td>
									<td><input name='num' type="text" class="form-control"></td>
								</tr>
								
								
								<tr>
									<td>运费</td>
									<td><input name='cash' type="text" class="form-control"></td>
								</tr>
								
								
								<tr>
									<td>发出地</td>
									<td><input name='from' type="text" class="form-control"></td>
								</tr>
								
								<tr>
									<td>目的地</td>
									<td><input name='to' type="text" class="form-control"></td>
								</tr>
								
								<tr>
									<td>运输人</td>
									<td>
											<select  name='driver.id' class='form-control'>
                          						<c:forEach var="bean" items="${drivers}">
						 							<option value="${bean.id }">${bean.name }</option>
						 						</c:forEach>
                          					</select>
									</td>
								</tr>
								
								<tr>
									<td>运输状态</td>
									<td>
											<select  name='state' class='form-control'>
						 							<option value="已发货">已发货</option>
						 							<option value="运输在途">运输在途</option>
						 							<option value="已送达">已送达</option>
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
    		   url:  $.common.getContextPath() + "/sys/bill/save",
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
    	layer.confirm('确定删除当前运单？', {
    		  btn: ['确定','取消'] //按钮
    		}, function(){
    			$.ajax({
    		 		   url:  $.common.getContextPath() + "/sys/bill/delete?id="+id,
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
 		   url:  $.common.getContextPath() + "/sys/bill/get?id="+id,
 		   success: function(msg){
 		     if(msg.code==1){
 		    	$("input[name='id']").val(msg.datas.id);
 		    	$("input[name='cash']").val(msg.datas.cash);
 		    	$("select[name='drvier']").val(msg.datas.driver.id);
 		    	$("input[name='endDate']").val(msg.datas.endDate);
 		    	$("select[name='food.id']").val(msg.datas.food.id);
 		    	$("input[name='from']").val(msg.datas.from);
 		    	$("input[name='to']").val(msg.datas.to);
 		    	$("input[name='num']").val(msg.datas.num);
 		    	$("input[name='orderDate']").val(msg.datas.orderDate);
 		    	$("input[name='sn']").val(msg.datas.sn);
 		    	$("select[name='state']").val(msg.datas.state);
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
	                "url":  $.common.getContextPath() + "/sys/bill/list",
	                "type": "POST",
	                "async":false,
	                "dataSrc": "datas"
	              },

	              
				"columns" : [{
					"data" : "sn"
				}, {
					"data" : "orderDate"
				}, {
					"data" : "food.name"
				}, {
					"data" : "num"
				},{
					"data" : "cash",
				},{
					"data" : "driver.name",
				}, {
					"data" : "from"
				},{
					"data" : "to",
				},{
					"data" : "state",
				},{
					"data" : "id",
				}] ,
				 "columnDefs": [
				                
				                {
				                    "render": function ( data, type, row ) {
				                        return "<a tager='_blank' href='javascript:void(0)' onclick='fun_delete("+data+")'>删除 </a>"+
				                        "<a tager='_blank' href='javascript:void(0)' onclick='fun_update("+data+")'>编辑 </a>";
				                    },
				                    "targets":9
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
		        	data.columnname = 'sn';
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