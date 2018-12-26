<%@page import="com.salesstock.dto.ProductsViewDto"%>
<%@page import="com.salesstock.dto.ProductsDto"%>
<%@page import="com.salesstock.util.Utils"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
List<ProductsViewDto> list=(List<ProductsViewDto>)request.getAttribute("LIST");
String id=Utils.getUUID();
%>

<div>

<div class="menu-page-info">
<span class="menu-desc">Products</span>
<span class="menu-action-btn">
<a href="javascript:getModule(506,1,'a#b#c#')" class="btn btn-default btn-sm">Add</a>
</span>
</div>

<div class="menu-page-content">
<div>
<table id="<%=id%>" class="display" style="width:100%">
<thead>
<tr>
<th>Id</th>
<th>Name</th>
<th>Description</th>
<th>Status</th>
<th>Type</th>
<th>SubType</th>
<th>Quantity</th>
<th>Unit</th>
<th>Price/Unit(INR)</th>
<th>Actions</th>
</tr>
</thead>
<tbody>
<%for(ProductsViewDto obj:list){ %>
<tr>
<td><%=Utils.clearNull(obj.getProductId()) %></td>
<td><%=Utils.clearNull(obj.getProductName()) %></td>
<td><%=Utils.clearNull(obj.getProductDesc()) %></td>
<td><%=Utils.clearNull(obj.getProductStatus()) %></td>

<td><%=Utils.clearNull(obj.getPTName()) %></td>

<td><%=Utils.clearNull(obj.getPSTName()) %></td>
<td><%=Utils.clearNull(obj.getQuantity()) %></td>
<td><%=Utils.clearNull(obj.getQName()) %></td>
<td><%=Utils.clearNull(obj.getPricePerUnit()) %></td>

<td>
  <div class="btn-group">
    <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Actions
    <span class="caret"></span></button>
    <ul class="dropdown-menu dropdown-menu-right">
      <li><a href="javascript:getModule(506,2,'<%=Utils.clearNull(obj.getProductId()) %>#b#c#')" class="dropdown-item">Edit</a></li>
      <li><a href="javascript:getModule(506,5,'<%=Utils.clearNull(obj.getProductId()) %>#b#c#')" class="dropdown-item">Delete</a></li>
    </ul>
  </div>

</td>
</tr>
<%} %>
</tbody>
</table>
</div>
</div>
</div>
<script>
$("#<%=id%>").DataTable();
</script>