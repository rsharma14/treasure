<%@page import="com.salesstock.util.Utils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>


<div class="form-container">

	<form:form method="POST" modelAttribute="quantityUnitBean"
		class="form-horizontal" id="modelGenericSave">

		<div class="">

			<form:input type="hidden" path="id" id="id"
				class="form-control input-sm" />

			<div class="form-group">
				<label class="col-md-4 control-lable" for="name">Name</label>
				<div class="col-md-7">
					<form:input type="text" path="name" id="name"
						class="form-control input-sm" />
					<div class="has-error">
						<form:errors path="name" class="help-inline" />
					</div>
				</div>
			</div>
			<%--  <%=Utils.getFormInput("text", "name", "label", "placeholder", null, true, null) %> --%>
			<div class="form-group">
				<label class="col-md-4 control-lable" for="description">Description</label>
				<div class="col-md-7">
					<form:input type="text" path="description" id="description"
						class="form-control input-sm" required="true"/>
					<div class="has-error">
						<form:errors path="description" class="help-inline" />
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-lable" for="status">Status</label>
				<div class="col-md-7" class="form-control input-sm">
					<form:radiobutton path="status" value="1" />
					Yes
					<form:radiobutton path="status" value="0" />
					No
					<div class="has-error">
						<form:errors path="status" class="help-inline" />
					</div>
				</div>
			</div>
		</div>
	</form:form>

</div>
