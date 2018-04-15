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
						<h5>汽车管理</h5>
						<div class="ibox-tools"></div>
					</div>

					<div class="ibox-content">
						<form role="form" class="form-inline">
							<div class="form-group">
								<label for="exampleInputEmail2" class="sr-only">品牌</label> <input
									type="text" placeholder="汽车品牌" id="_name" class="form-control">
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
									<th>品牌</th>
									<th>颜色</th>
									<th>里程数）</th>
									<th>车辆唯一识别码</th>
									<th>载重（吨）</th>
									<th>车重（吨）</th>
									<th>购买日期</th>
									<th>下次保养日期</th>
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
									<td colspan="6"><h3>汽车信息</h3></td>
								</tr>
							</thead>
							<tbody>
							
								<tr>
									<td>品牌</td>
									<td><input name='brand' type="text" class="form-control"></td>
								</tr>
								
								<tr>
									<td>颜色</td>
									<td><input name='color' type="text" class="form-control"></td>
								</tr>
								
								<tr>
									<td>里程数</td>
									<td><input name='mileage' type="text" class="form-control"></td>
								</tr>
								
								<tr>
									<td>车辆唯一识别码</td>
									<td><input name='sn' type="text" class="form-control"></td>
								</tr>
								
								<tr>
									<td>载重（吨）</td>
									<td><input name='load' type="text" class="form-control"></td>
								</tr>
								
								<tr>
									<td>车重（吨）</td>
									<td><input name='weight' type="text" class="form-control"></td>
								</tr>
								
								
								<tr>
									<td>购买日期</td>
									<td><input name='buyDate' type="text" class="form-control"></td>
								</tr>
								
								
								<tr>
									<td>下次保养日期</td>
									<td><input name='fixDate' type="text" class="form-control"></td>
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
    		   url:  $.common.getContextPath() + "/sys/car/save",
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
    	layer.confirm('确定删除当前汽车？', {
    		  btn: ['确定','取消'] //按钮
    		}, function(){
    			$.ajax({
    		 		   url:  $.common.getContextPath() + "/sys/car/delete?id="+id,
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
 		   url:  $.common.getContextPath() + "/sys/car/get?id="+id,
 		   success: function(msg){
 		     if(msg.code==1){
 		    	$("input[name='id']").val(msg.datas.id);
 		    	$("input[name='brand']").val(msg.datas.brand);
 		    	$("input[name='buyDate']").val(msg.datas.buyDate);
 		    	$("input[name='color']").val(msg.datas.color);
 		    	$("input[name='fixDate']").val(msg.datas.fixDate);
 		    	$("input[name='load']").val(msg.datas.load);
 		    	$("input[name='mileage']").val(msg.datas.mileage);
 		    	$("input[name='sn']").val(msg.datas.sn);
 		    	$("input[name='weight']").val(msg.datas.weight);
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
	                "url":  $.common.getContextPath() + "/sys/car/list",
	                "type": "POST",
	                "async":false,
	                "dataSrc": "datas"
	              },

				"columns" : [{
					"data" : "brand"
				}, {
					"data" : "color"
				}, {
					"data" : "mileage"
				}, {
					"data" : "sn"
				},{
					"data" : "load",
				},{
					"data" : "weight",
				}, {
					"data" : "buyDate"
				},{
					"data" : "fixDate",
				},{
					"data" : "id",
				}] ,
				 "columnDefs": [
				                
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
        			} );
        		} 
        	 } ).on('preXhr.dt', function ( e, settings, data ) {
		        	data.value = $("#_name").val();
		        	data.columnname = 'brand';
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
