<%@page import="com.salesstock.dto.ProductSubTypeDto"%>
<%@page import="com.salesstock.dto.ProductTypeDto"%>
<%@page import="com.salesstock.dto.QuantityUnitDto"%>
<%@page import="com.salesstock.util.Utils"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
List<ProductSubTypeDto> list=(List<ProductSubTypeDto>)request.getAttribute("LIST");
String id=Utils.getUUID();
%>

<div>

<div class="menu-page-info">
<span class="menu-desc">Product Sub Type</span>
<span class="menu-action-btn">
<a href="javascript:getModule(503,1,'a#b#c#')" class="btn btn-default btn-sm">Add</a>
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
<th>Actions</th>
</tr>
</thead>
<tbody>
<%for(ProductSubTypeDto obj:list){ %>
<tr>
<td><%=Utils.clearNull(obj.getId()) %></td>
<td><%=Utils.clearNull(obj.getName()) %></td>
<td><%=Utils.clearNull(obj.getDescription()) %></td>
<td><%=Utils.clearNull(obj.getStatus()) %></td>
<td>
  <div class="btn-group">
    <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Actions
    <span class="caret"></span></button>
    <ul class="dropdown-menu dropdown-menu-right">
      <li><a href="javascript:getModule(503,2,'<%=Utils.clearNull(obj.getId()) %>#b#c#')" class="dropdown-item">Edit</a></li>
      <li><a href="javascript:getModule(503,5,'<%=Utils.clearNull(obj.getId()) %>#b#c#')" class="dropdown-item">Delete</a></li>
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