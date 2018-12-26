<%@page import="com.salesstock.bean.ProductsBean"%>
<%@page import="com.salesstock.bean.ProductSubTypeBean"%>
<%@page import="com.salesstock.util.Utils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="form-container">

	<form method="POST" class="form-horizontal" id="modelGenericSave">

		<div class="">

			<input type="hidden" id="id" name="id" value="${ProductsBean.id}" class="form-control input-sm" />

			<div class="form-group">
				<label class="col-md-4 control-lable" for="name">Name</label>
				<div class="col-md-7">
					<input type="text" id="name" name="name" value="${ProductsBean.name}" class="form-control input-sm" required/>
					<div class="has-error">
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-lable" for="description">Description</label>
				<div class="col-md-7">
					<input type="text" id="description"	name="description" value="${ProductsBean.description}" class="form-control input-sm" required/>
					<div class="has-error">
					</div>
				</div>
			</div>
			
			<div class="form-group">
					<label class="col-md-4 control-label text-left">Product Category</label>
					<div class="col-md-7">
						<select id="productCategoryId" name="productCategoryId" data-selected="${ProductsBean.productCategoryId}" class="form-control" onchange="selectingCombobox(506,'2,3,4')" required>
						</select>
					</div>
			</div>
			
			<div class="form-group">
					<label class="col-md-4 control-label text-left">Product Type</label>
					<div class="col-md-7">
						<select id="productTypeId" name="productTypeId" data-selected="${ProductsBean.productTypeId}"	class="form-control" onchange="selectingCombobox(506,'3')" required>
						</select>
					</div>
			</div>
			
			<div class="form-group">
					<label class="col-md-4 control-label text-left">Product Subtype</label>
					<div class="col-md-7">
						<select id="productSubTypeId" name="productSubTypeId" data-selected="${ProductsBean.productSubTypeId}"	class="form-control" onchange="selectingCombobox(506,'')">
						</select>
					</div>
			</div>

			<div class="form-group">
					<label class="col-md-4 control-label text-left">Brands</label>
					<div class="col-md-7">
						<select id="brandId" name="brandId" data-selected="${ProductsBean.brandId}"	class="form-control" onchange="selectingCombobox(506,'')" multiple>
						</select>
					</div>
			</div>
			<div class="form-group">
					<label class="col-md-4 control-label text-left">Unit Type</label>
					<div class="col-md-7">
						<select id="quantityUnit" name="quantityUnit" data-selected="${ProductsBean.quantityUnit}" class="form-control" onchange="" required>
						</select>
					</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-lable" for="description">Quantity</label>
				<div class="col-md-7">
					<input type="number" id="quantity" name="quantity" value="${ProductsBean.quantity}" placeholder="How many?" class="form-control input-sm" required/>
					<div class="has-error">
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-4 control-lable" for="description">Price/Unit</label>
				<div class="col-md-7">
					<input type="text" id="pricePerUnit" name="pricePerUnit" value="${ProductsBean.pricePerUnit}" class="form-control input-sm" required/>
					<div class="has-error">
					</div>
				</div>
			</div>
						
			<div class="form-group" id="radio-status">
				<label class="col-md-4 control-lable" for="status">Status</label>
				<div class="col-md-7" class="form-control input-sm">
				<input type="radio" name="status" data-val="${ProductsBean.status}" value="1" />Yes
				<input type="radio" name="status" data-val="${ProductsBean.status}" value="0" checked/>No
					<div class="has-error">
					</div>
				</div>
			</div>
		</div>
	</form>

</div>
