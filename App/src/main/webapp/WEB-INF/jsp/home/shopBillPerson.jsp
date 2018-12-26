<%@page import="java.util.Map"%>
<%@page import="com.salesstock.dto.BillInfoDto"%>
<%@page import="com.salesstock.dto.ProductsViewDto"%>
<%@page import="com.salesstock.dto.ProductsDto"%>
<%@page import="com.salesstock.util.Utils"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
Map<String, List<BillInfoDto>> list=(Map<String, List<BillInfoDto>>)request.getAttribute("DATA");
String id=Utils.getUUID();
boolean cond=false;
%>

<div>

<div class="menu-page-info">
<span class="menu-desc">Billing Dashboard</span>
</div>

<div class="menu-page-content">
<div>
<%for(String key:list.keySet()){ 
cond=true;
%>
<button class="accordion"><span class="fa fa-plus"></span> &nbsp;Invoice: <%=key %></button>
<div class="accord-panel">

<table class="<%=id%>" class="display" style="width:100%">
<thead>
<tr>
<th>ProductName</th>
<th>UnitPrice</th>
<th>Quantity</th>
<th>Total</th>
<th>GrandTotal</th>
<th>Date</th>
</tr>
</thead>
<tbody>
<%for(BillInfoDto obj:list.get(key)){ %>
<tr>
<td><%=Utils.clearNull(obj.getProductName()) %></td>
<td><%=Utils.clearNull(obj.getUnitPrice())+"/"+Utils.clearNull(obj.getUnit()) %></td>
<td><%=Utils.clearNull(obj.getQuantity()) %></td>
<td><%=Utils.clearNull(obj.getTotal()) %></td>
<td><%=cond?Utils.clearNull(obj.getGrandTotal()):"" %></td>
<td><%=cond?Utils.clearNull(obj.getDate()):"" %></td>
</tr>
<%cond=false;}%>
</tbody>
</table>
</div>
<%} %>
</div>
</div>
</div>
<script>
$(document).ready(function(){
	attachAccordionEvent("accordion");	
});

$(".<%=id%>").DataTable({
	paging:false,
	searching:false,
	ordering:false,
	dom:''
});

</script>