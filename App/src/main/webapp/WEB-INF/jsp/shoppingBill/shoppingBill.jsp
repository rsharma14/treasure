<%@page import="com.salesstock.util.Utils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String id = Utils.getUUID();
%>
<div class="container-fluid">
	<div class="row">
		<div class="wrapper-content-area-pad">
			<div class="panel panel-primary flat-panel">
				<div class="panel-heading flat-panel clearfix">
					<h4>Shopping Bill Management System</h4>
				</div>
				<div class="panel-body inuptText">
					<div id="search-content">
						<fieldset class="field-border">
						<legend>Search Products</legend>
						<form action="#" id="searchProducts">
							<div class="form-horizontal">
								<div class="form-group">
									<div class="col-md-12"></div>
								</div>				
								<div class="col-md-6">
									<div class="form-group">
									<input type="text" class="form-control enter-search-key" id="search-product-field" name="productName" placeholder="Search your Items" style="display: inline;width: 70%;" autocomplete="off">
									<a href="javascript:searchProducts()" class="btn btn-default btn-sm" style="height: 34px;left: 67%;position: absolute;"><i class="fa fa-search"></i></a>
									<a href="javascript:resetSearchProducts()" class="btn btn-default btn-sm" style="height: 34px;left: 72%;position: absolute;">Clear</a>
									</div>
									<div class="form-group" id="showSearchProducts">
									</div>

								</div>
								<fieldset class="field-border">
								
						<legend><input type="checkbox" id="filter-search-check" >Filters</legend>
						<div id="filter-option" style="display: none;">
								<div class="col-md-6">
								<div class="form-group">
									<label class="col-md-3 control-label" for="quota">Category</label>
									<div class="col-md-9">
										<select id="productCategoryId" name="productCategoryId" class="form-control" onchange="selectingCombobox(601,'2,4')">
						                </select>
									</div>
								</div>
								
							<div class="form-group">
									<label class="col-md-3 control-label" for="quota">Type</label>
									<div class="col-md-9">
										<select id="productTypeId" name="productTypeId" class="form-control" onchange="selectingCombobox(601,'')">
						</select>
									</div>
								</div>
								
								</div>
								
								<div class="col-md-6">
								
								<div class="form-group">
									<label class="col-md-3 control-label" for="quota">Brand</label>
									<div class="col-md-9">
										<select id="brandId" name="brandId" class="form-control" onchange="selectingCombobox(601,'')" multiple>
						                </select>
									</div>
								</div>
								
								<div class="form-group">
									<div  id="rangeslider-search-price">
        <span style="float: left;">Min: <span class="min-box-show">200</span></span>
        <input type="range" class="range-type-min" data-type="min" name="rangeTypeMin" value="0" min="0" max="100000">
        <input type="range" class="range-type-max" data-type="max" name="rangeTypeMax" value="100000" min="0" max="100000">
        <span style="float: right;">Max: <span class="max-box-show" >100000</span></span>
      </div>
								</div>
								</div>
								</div>
								</fieldset>
							</div>
							</form>
						</fieldset>
					</div>
					
					
					<div id="selected-product-div">
					<table id="<%=id%>" class="display" style="width:100%">
					<thead>
					<tr>
					<th>Sl.</th><th>Name</th><th class='currency-label1'>Unit Price</th><th>Quantity</th><th class='currency-label1'>Total</th><th></th>
					</tr>
					</thead>
					<tbody id="selected-product-list">
					<tr>
					<td></td><td></td><td></td><td><span style="font-weight: bold;">Grand Total:</span></td>
					<td><span class='currency-label2'></span><input type="text" size="8" min="0" step="" id="grand-total" readonly></td>
					
					<td data-action='1'><a class='btn btn-xs btn-primary' href="javascript:printProductBill()"><i class="fa fa-print"> Print</i></a></td>
					</tr>
					
					</tbody>
					</table>
					
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
$("#<%=id%>").DataTable({
	paging:false,
	searching:false,
	ordering:false,
	dom:''
});

$(document).ready(function(){
	getModule(CONSTANTS.MODULE.SHOPPING_BILL.ID+CONSTANTS.MODULE.SHOPPING_BILL.BILL,CONSTANTS.ACTION.VIEW,"selectedData");
});
</script>