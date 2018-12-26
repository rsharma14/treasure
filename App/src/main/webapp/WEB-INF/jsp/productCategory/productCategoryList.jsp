<%@page import="com.salesstock.dto.ProductCategoryDto"%>
<%@page import="com.salesstock.util.Utils"%>
<%@page import="com.salesstock.entity.ProductCategory"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
List<ProductCategoryDto> list=(List<ProductCategoryDto>)request.getAttribute("LIST");
String id=Utils.getUUID();
%>

<div>

<div class="menu-page-info">
<span class="menu-desc">Products Category</span>
<span class="menu-action-btn">
<a href="javascript:getModule(501,1,'a#b#c#')" class="btn btn-default btn-sm">Add</a>
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
<%for(ProductCategoryDto pc:list){ %>
<tr>
<td><%=Utils.clearNull(pc.getId()) %></td>
<td><%=Utils.clearNull(pc.getName()) %></td>
<td><%=Utils.clearNull(pc.getDescription()) %></td>
<td><%=Utils.clearNull(pc.getStatus()) %></td>
<td>
  <div class="btn-group">
    <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Actions
    <span class="caret"></span></button>
    <ul class="dropdown-menu dropdown-menu-right">
      <li><a href="javascript:getModule(501,2,'<%=Utils.clearNull(pc.getId()) %>#b#c#')" class="dropdown-item">Edit</a></li>
      <li><a href="javascript:getModule(501,5,'<%=Utils.clearNull(pc.getId()) %>#b#c#')" class="dropdown-item">Delete</a></li>
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