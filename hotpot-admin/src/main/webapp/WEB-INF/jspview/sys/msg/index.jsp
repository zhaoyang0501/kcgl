<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<link href="${pageContext.request.contextPath}/css/plugins/webuploader/webuploader.css" rel="stylesheet">
</head>
<body>
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox ">
					<div class="ibox-title">
						<h5>留言管理</h5>
						<div class="ibox-tools"></div>
					</div>

					<div class="ibox-content">
						<form role="form" class="form-inline">
							<div class="form-group">
								<label for="exampleInputEmail2" class="sr-only">标题</label> <input
									type="text" placeholder="标题" id="_name" class="form-control">
							</div>
							<button class="btn btn-primary" type="button" id='_search'>查询</button>
						</form>
					</div>

					<div class="ibox-content ">
						<table ID='dt_table_view'
							class="table table-striped table-bordered table-hover ">
							<thead>
								<tr>
									<th>发布日期</th>
									<th>评论菜品</th>
									<th>内容</th>
									<th>发表人</th>
									<th>回复</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>

				</div>
			</div>
		</div>
	</div>

	<script>
    var table=null;
    
    function submit_form(){
    	$.ajax({
    		   type: "POST",
    		   url:  $.common.getContextPath() + "/sys/food/save",
    		   data: $("form").serialize(),
    		   success: function(msg){
    		     if(msg.code==1){
    		    	 toastr.success('操作成功');
    		    	 table.draw();
    		     }
    		     layer.closeAll() ;
    		   }
    		});
     }
    
    function fun_replay(id){
    	layer.prompt({title: '回复内容', formType: 2}, function(text){
    		$.ajax({
		 		   url:  $.common.getContextPath() + "/sys/msg/reply",
		 		   "type": "POST",
		 		   data:{"reply":text,"id":id},
		 		   success: function(msg){
		 		     if(msg.code==1){
		 		    	 toastr.success('回复成功');
		 		    	 table.draw();
		 		     }
		 		     layer.closeAll() ;
		 		   }
		 	});
		  });
    }
    
    function fun_delete(id){
    	layer.confirm('确定删除当前留言？', {
    		  btn: ['确定','取消'] //按钮
    		}, function(){
    			$.ajax({
    		 		   url:  $.common.getContextPath() + "/sys/msg/delete?id="+id,
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
    
   
    
    $(document).ready(function(){
        	table=$('#dt_table_view').DataTable( {
        		"dom": "rt<'row'<'col-sm-5'i><'col-sm-7'p>>",
	            "ajax": {
	                "url":  $.common.getContextPath() + "/sys/msg/list",
	                "type": "POST",
	                "dataSrc": "datas"
	              },
				"columns" : [{
					"data" : "createDate"
				}, {
					"data" : "food.name"
				},{
					"data" : "body",
				},{
					"data" : "frontUser.name",
				},{
					"data" : "reply",
				},{
					"data" : "state",
				},{
					"data" : "id",
				}] ,
				 "columnDefs": [
								{
								    "render": function ( data, type, row ) {
								        if(data=='1'){
								        	return "<span class='label label-danger'>未回复</span>";
								        }else{
								        	return "<span class='label label-primary'>已回复</span>";
								        }
								    },
								    "targets":5
								},
				                {
				                    "render": function ( data, type, row ) {
				                        return "<a tager='_blank' href='javascript:void(0)' onclick='fun_replay("+data+")'>回复 </a>   "+
				                        "<a tager='_blank' href='javascript:void(0)' onclick='fun_delete("+data+")'>删除 </a>";
				                    },
				                    "targets":6
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
		        	data.columnname = 'body';
		        	return true;
		     } ).on('xhr.dt', function ( e, settings, json, xhr ) {
		    		 $(".dataTables_processing").hide();	
		     } )
        });
    </script>
	<script
		src="${pageContext.request.contextPath}/plugins/webuploader/webuploader.js "></script>

</body>

</html>
