<%@page import="com.salesstock.dto.BrandsDto"%>
<%@page import="com.salesstock.util.Utils"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
List<BrandsDto> list=(List<BrandsDto>)request.getAttribute("LIST");
String id=Utils.getUUID();
%>

<div>

<div class="menu-page-info">
<span class="menu-desc">Brands</span>
<span class="menu-action-btn">
<a href="javascript:getModule(505,1,'a#b#c#')" class="btn btn-default btn-sm">Add</a>
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
<%for(BrandsDto obj:list){ %>
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
      <li><a href="javascript:getModule(505,2,'<%=Utils.clearNull(obj.getId()) %>#b#c#')" class="dropdown-item">Edit</a></li>
      <li><a href="javascript:getModule(505,5,'<%=Utils.clearNull(obj.getId()) %>#b#c#')" class="dropdown-item">Delete</a></li>
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