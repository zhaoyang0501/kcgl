<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
</head>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div class="wrapper wrapper-content animated fadeInRight">
       <div class="row">
            <div class="col-sm-12">
                <div class="ibox ">
                 
      		 	<div class="ibox-content">
 		 			<div class="row">
                            <div class="col-sm-12 b-r">
                            <form class="form-horizontal" action="/sys/in/foodIn" method="post">
		                           	<table class='table table-bordered'>
		                           		<thead>
		                           		<tr style="text-align: center;" ><td colspan="6" ><h3>入库<h3></td></tr>
		                           		</thead>
		                           		<tbody>
		                           			<tr>
		                           				<td>货物编码（可用扫描枪）</td>
		                           				<td> <input  name='foodCode' onblur="fun_initfood()" id="foodCode" class="form-control"/></td>
		                           				
		                           				
		                           				
		                           				<td>货物名称</td>
		                           				<td> <input name="foodName" class="form-control" value=""/></td>
		                           			</tr>
		                           			<tr>
			                           			<td>规格型号  </td>
			                           			<td>
			                           			<input name="foodStandard" class="form-control" value=""/>
			                           			 </td>
		                           			
		                           			
		                           				<td>主计量单位</td>
		                           			
		                           				<td>
		                           			
		                           				 	<select name='unit' class="form-control">
				                           				<option value="只">只</option>
				                           				<option value="盒">盒</option>
				                           				<option value="箱">箱</option>
				                           			</select>
				                           		</td>
		                           			
		                           			</tr>
		                           			<tr>
		                           				<td>备注</td>
		                           				<td ><input  name="foodRemark" value="无" class="form-control"/></td>
		                           			
											   <td > 数量</td>
		                           				<td> <input name="num" class="form-control"/></td>
		                           					
		                           			</tr>
		                           			<tr>
		                           				
		                           				<td > 单价</td>
		                           			
		                           				<td> <input name="price" class="form-control"/></td>
		                           			
		                           				<td > 总金额</td>
		                           				<td><input name="totalPrice" class="form-control"/></td>
		                           			</tr>
		                           			<tr>
		                           				<td > 入库类别</td>
		                           				<td> 
		                           					<select name='type' class="form-control">
		                           						<option value="产品入库">产品入库</option>
		                           						<option value="材料入库">材料入库</option>
		                           					</select>
		                           				</td>
		                           			
		                           				<td > 记账人</td>
		                           				<td> <input name="recordUser" class="form-control"/></td>
		                           			</tr>
		                           			
		                           			<tr>
		                           				<td > 供应商</td>
		                           				<td><input name="supply" class="form-control"/></td>
		                           			
		                           				<td > 操作人</td>
		                           				<td> <input  name="opUser" class="form-control"/></td>
		                           		</tr>
		                           			<tr>
		                           				<td > 入库日期</td>
		                           				<td> <input name="inDate"  class="form-control"/></td>
		                           				
		                           				<td > 所属仓库</td>
		                           				<td>
		                           					<select class='form-control'>
		                           						<c:forEach var="bean" items="${categorys}">
									 							<option value="${bean.id }">${bean.name }</option>
									 					</c:forEach>
		                           					</select>
		                           				</td>
		                           			</tr>
		                           		</tbody>
		                           	</table>
		                           	<div class="text-center">
			                        	<button type="submit" class="btn btn-primary"><i ></i> 立即入库</button>
			                    	</div>
		                           	</form>
                            </div>
                        </div>
                        </div>
                    
                </div>
            </div>
        </div>
   </div>
   
   <script>
	   function fun_initfood(){
		   $.ajax({
	 		   url: $.common.getContextPath() + "/sys/food/getByCode?code="+$("#foodCode").val(),
	 		   success: function(msg){
	 		     if(msg.code==1){
	 		    	 $("input[name='foodName']").val(msg.datas.name);
	 		    	 $("input[name='foodStandard']").val(msg.datas.standard);
	 		    	 $("input[name='foodRemark']").val(msg.datas.remark);
	 		     }else{
	 		    	 alert("货物条码不存在！");
	 		     }
	 		   }
	 	});
	   }
   </script>
 </body>
</html>
