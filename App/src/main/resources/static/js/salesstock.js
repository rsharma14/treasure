function attachMenuEvents() {
	dropdownMenu();
	ajaxSetup();
	var that=this;
	$("#menu-url a").on("click", function(e) {
		e.preventDefault();
		triggerMenu(this);
	});
	$("#menu-url a:first").click();
}

function triggerMenu(context,callback){
	if (parseInt($(context).attr("is-link")) === 1){
		this.currentMenuUrl=$(context).attr("href");
		var subUrl=$(context).attr("sub-url");
		var data={
				url:$(context).attr("href")+"/"+((subUrl && subUrl.length>0)?subUrl:CONSTANTS.COMMON_URL.view)
		}
		processMenuEvent(data,callback);
		
	}	
}

function dropdownMenu(){
	window.onclick = function(e) {
		  if (!e.target.matches('.menu-dropbtn')) {
		    $(".menu-clickable-dropdown").siblings(".menu-dropdown-content").removeClass("menu-show");
		      }
		  }
	
	$(".menu-clickable-dropdown").on("click",function(){
		$(this).siblings(".menu-dropdown-content").toggleClass("menu-show");
	});
}

function processMenuEvent(formdata,callback){
	doAjaxCall(formdata.url,"GET",formdata,function(data){
		$("#main-content").html(data);
		if(callback && typeof callback==='function')
			callback();
	});
}


function getModule(moduleId,actionId,selectedData){
	var ret=true;
	var data={
			moduleId:moduleId,
			actionId:actionId,
			selectedData:selectedData
	}
	
	switch (parseInt(moduleId)) {
	case (CONSTANTS.MODULE.SHOPPING_BILL.ID+CONSTANTS.MODULE.SHOPPING_BILL.BILL):
		this.shoppingBill={};
		ret=processShoppingBill(data);
	break;
	case (CONSTANTS.MODULE.MANAGE_DATA.ID+CONSTANTS.MODULE.MANAGE_DATA.PRODUCT_CATEGORY):
		this.productCategory={};
		ret=processProductCategory(data);
		break;
	case (CONSTANTS.MODULE.MANAGE_DATA.ID+CONSTANTS.MODULE.MANAGE_DATA.PRODUCT_TYPE):
		this.productType={};
		ret=processProductType(data);
		break;	
	case (CONSTANTS.MODULE.MANAGE_DATA.ID+CONSTANTS.MODULE.MANAGE_DATA.PRODUCT_SUBTYPE):
		this.productSubType={};
		ret=processProductSubType(data);
		break;
	case (CONSTANTS.MODULE.MANAGE_DATA.ID+CONSTANTS.MODULE.MANAGE_DATA.QUANTITY_UNIT):
		this.quantityUnit={};
		ret=processQuantityUnit(data);
	break;
	case (CONSTANTS.MODULE.MANAGE_DATA.ID+CONSTANTS.MODULE.MANAGE_DATA.BRANDS):
		this.brands={};
		ret=processBrands(data);
	break;
	case (CONSTANTS.MODULE.MANAGE_DATA.ID+CONSTANTS.MODULE.MANAGE_DATA.PRODUCTS):
		this.products={};
		ret=processProducts(data);
	break;
	default:
		break;
	}
	return ret;
}

function processShoppingBill(formData){
	var ret=true;

	switch (parseInt(formData.actionId)) {
	case CONSTANTS.ACTION.VIEW:
		formData.caseIds=[1,2,3,4];
		handleCombobox(formData);
		attachSearchEvents();
		$("#search-product-field").focus();
		
		break;
	}
}


function attachSearchEvents(){
	
	rangeController();
	$(".currency-label1").append("("+getCurrency().trim()+")");
	$(".currency-label2").prepend(getCurrency());
	
	var _self=this;
	$("#search-product-field").on("keyup",function(){
		searchProducts();
	});
	
	this.shoppingBill.filter=0;
	$("#filter-search-check").on("change",function(){
		if($(this).is(":checked")){
		    _self.shoppingBill.filter=1;
			$("#filter-option").css("display","block");
		}else{
			_self.shoppingBill.filter=0;
			$("#filter-option").css("display","none");
		}
			
	});
	$("#showSearchProducts").on("click","table tr[data-val='1']",function(e){
		if($(this).hasClass('product-tr-0')){
			return;
		}
		processSearchProducts(this);
	});
	$(document).on("click",function(){
		$("#showSearchProducts").html("");
	});
}
function searchProducts(){
	if($("#search-product-field").val().trim().length==0){
		showMessage("Provide search key...","error");
		return;
	}
	var formContext = $("#searchProducts");

//	var formData = new FormData(formContext[0]);  
//	formData.append("filter",this.shoppingBill.filter);
	
	var formData = $("#searchProducts").serialize()
	formData+="&filter="+this.shoppingBill.filter;
	
	doAjaxCall(this.currentMenuUrl+"/search-item","GET",formData,function(data){
		showSearchProducts(data);
		
	});
	
}
function showSearchProducts(data){
	var html="<table>";
	if(data.length==0){
		html="<tr><td>No items found</td></tr>";
	}else{
		for(var i in data){
		html+="<tr data-val='1' class='show-search-products product-tr-"+data[i]['quantity']+"' data-json='"
		+JSON.stringify(data[i])+"'><td>"+data[i]['productName']+" - "+data[i]['brandName']+"<span class='product-td-"+data[i]['quantity']
		+"' style='display:none;'>Out of stock</span>"+"</td></tr>";
		}
	}
	html+="</table>";
	$("#showSearchProducts").html(html);
	$(".product-tr-0").prop("readonly",true);
	$(".product-td-0").removeAttr("style");
}

function processSearchProducts(ctx){
	var json= $(ctx).attr("data-json");
	var data=JSON.parse(json);
	console.log(data);
	if(!this.shoppingBill.selectedProduct)
		this.shoppingBill.selectedProduct={};
	
	var price=parseFloat(data['pricePerUnit']).toFixed(2);
	var pId=data['productId'].trim();
	var newItem=true;
	if(this.shoppingBill.selectedProduct.hasOwnProperty([pId])){
		newItem=false;
		var old=this.shoppingBill.selectedProduct[pId];
		old.selectedQuantity++;
		$("#"+pId).children().each(function(){
			if($(this).attr('data-qty')){
				
				$(this).children().first().val(old.selectedQuantity);
				$(this).children().first().trigger('change');
				return false;
			}
		});
	}

	if(newItem){
	var row="<tr data-json='"+json+"' id='"+data['productId']+"'><td data-sl='1'>"+1+"</td><td data-pn='1'>"+data['productName']+" -"+data['brandName']+"</td><td data-ppu='1'>"
			+price+"/"+data['qname']+"</td><td data-qty='1'><input type='number' onchange='calTotal(this)' value='1' min='0' max='"+data['quantity']
	        +"' style='width: 20%;'>"+data['qname']+"</td><td data-total='1'>"+price+"</td><td data-action='1'><a class='btn btn-xs btn-primary' " 
	        +"onclick='removeProduct(this,\""+pId+"\")'><i class='fa fa-trash'> Delete</a></td></tr>";
	
	data.selectedQuantity=1;
	data.totalPrice=price;
	this.shoppingBill.selectedProduct[pId]=data;
	$("#selected-product-list").prepend(row);
	reAssignSlNo();

	}
	calGrandTotal();
}

function calTotal(ctx){
	var json= $(ctx).parents('tr').attr("data-json");;
	var data=JSON.parse(json);
	console.log(data);
	var total=parseInt($(ctx).val())*parseFloat(data['pricePerUnit']);
	total=parseFloat(isNaN(total)?0:total).toFixed(2);
	
	$(ctx).parent().next().html(getCurrency()+total);
	data.selectedQuantity=parseInt($(ctx).val());
	data.totalPrice=total;
	this.shoppingBill.selectedProduct[data['productId']]=data;
	calGrandTotal();
	
}
function calGrandTotal(){
	var data=this.shoppingBill.selectedProduct;
	this.grandTotal=0;
	for(var i in data){
		this.grandTotal+=parseFloat(data[i]['totalPrice']);
		
	}
	this.grandTotal=parseFloat(this.grandTotal).toFixed(2);
	$("#grand-total").val(this.grandTotal);
}

function removeProduct(ctx,pId){
$(ctx).parents('tr').remove();	
delete this.shoppingBill.selectedProduct[pId];
reAssignSlNo();
calGrandTotal();
}

function reAssignSlNo(){

	var i=1;
	$("#selected-product-list td").each(function(){
		if($(this).attr('data-sl'))
			$(this).html(i++);
	});
}

function getCurrency(){
	return "Rs. ";
}
function resetSearchProducts(){
	$("#searchProducts")[0].reset();
}

function printProductBill(){
	if(Object.keys(this.shoppingBill.selectedProduct).length>0){
	$("#selected-product-div").find('input').each(function(){
		$(this).attr('value',$(this).val()); 
	});
	
	doAjaxCall(this.currentMenuUrl+"/print-save","POST",JSON.stringify({json:this.shoppingBill.selectedProduct,total:this.grandTotal}),function(data){
		
		if(data){
			var divToPrint = $("#selected-product-div").clone().html();
		    var newWin = window.open("");
		    newWin.document.write("<html><body><head><link rel='stylesheet' type='text/css' href='../css/print/printbill.css'></head>"
		    		+"<body><div>Invoice No. "+data['invoiceNo']+"</div><br>"+divToPrint+"</body></html>");
		    newWin.print();
		    setTimeout(function(){ newWin.close();});
		   
	   }
		},function(data){
			console.log(data);
	},'application/json');
	}
    
}
function processProductCategory(formData){
	var ret=true;

	switch (parseInt(formData.actionId)) {
	case CONSTANTS.ACTION.SHOW_ADD_FORM:
		formData.url=CONSTANTS.COMMON_URL.showAddEditFormUrl;
		handleEditShow(formData,function(){
			formData.actionId=CONSTANTS.ACTION.ADD_DETAILS;  
		});
		break;
	case CONSTANTS.ACTION.SHOW_EDIT_FORM:
		formData.url=CONSTANTS.COMMON_URL.showAddEditFormUrl;
		handleEditShow(formData,function(){
			formData.actionId=CONSTANTS.ACTION.EDIT_DETAILS;  
		});
		break;
	case CONSTANTS.ACTION.ADD_DETAILS:
		formData.form= $("#modelGenericSave").serialize();
		formData.formId="modelGenericSave";
		formData.url=CONSTANTS.COMMON_URL.addDetails;
		ret=handleGenericSave(formData);
		break;
	case CONSTANTS.ACTION.EDIT_DETAILS:
		formData.form= $("#modelGenericSave").serialize();
		formData.formId="modelGenericSave";
		formData.url=CONSTANTS.COMMON_URL.editDetails;
		ret=handleGenericSave(formData);
		break;
	case CONSTANTS.ACTION.DELETE:
		formData.url=CONSTANTS.COMMON_URL.deleteDetails;
		handleDelete(formData);
		break;
	default:
		break;
	}	
	return ret;
}

function processProductType(formData){
	var ret=true;
	switch (parseInt(formData.actionId)) {
	case CONSTANTS.ACTION.SHOW_ADD_FORM:
		formData.url=CONSTANTS.COMMON_URL.showAddEditFormUrl;
		handleEditShow(formData,function(){
			formData.actionId=CONSTANTS.ACTION.ADD_DETAILS;  
		});
		break;
	case CONSTANTS.ACTION.SHOW_EDIT_FORM:
		formData.url=CONSTANTS.COMMON_URL.showAddEditFormUrl;
		handleEditShow(formData,function(){
			formData.actionId=CONSTANTS.ACTION.EDIT_DETAILS;  
		});
		break;
	case CONSTANTS.ACTION.ADD_DETAILS:
		formData.form= $("#modelGenericSave").serialize();
		formData.formId="modelGenericSave";
		formData.url=CONSTANTS.COMMON_URL.addDetails;
		ret=handleGenericSave(formData);
		break;
	case CONSTANTS.ACTION.EDIT_DETAILS:
		formData.form= $("#modelGenericSave").serialize();
		formData.formId="modelGenericSave";
		formData.url=CONSTANTS.COMMON_URL.editDetails;
		ret=handleGenericSave(formData);
		break;
	case CONSTANTS.ACTION.DELETE:
		formData.url=CONSTANTS.COMMON_URL.deleteDetails;
		handleDelete(formData);
		break;
	default:
		break;
	}	
	return ret;
}

function processProductSubType(formData){
	var ret=true;

	switch (parseInt(formData.actionId)) {
	case CONSTANTS.ACTION.SHOW_ADD_FORM:
		formData.url=CONSTANTS.COMMON_URL.showAddEditFormUrl;
		handleEditShow(formData,function(){
			formData.actionId=CONSTANTS.ACTION.ADD_DETAILS;  
		});
		break;
	case CONSTANTS.ACTION.SHOW_EDIT_FORM:
		formData.url=CONSTANTS.COMMON_URL.showAddEditFormUrl;
		handleEditShow(formData,function(){
			formData.actionId=CONSTANTS.ACTION.EDIT_DETAILS;  
		});
		break;
	case CONSTANTS.ACTION.ADD_DETAILS:
		formData.form= $("#modelGenericSave").serialize();
		formData.formId="modelGenericSave";
		formData.url=CONSTANTS.COMMON_URL.addDetails;
		ret=handleGenericSave(formData);
		break;
	case CONSTANTS.ACTION.EDIT_DETAILS:
		formData.form= $("#modelGenericSave").serialize();
		formData.formId="modelGenericSave";
		formData.url=CONSTANTS.COMMON_URL.editDetails;
		ret=handleGenericSave(formData);
		break;
	case CONSTANTS.ACTION.DELETE:
		formData.url=CONSTANTS.COMMON_URL.deleteDetails;
		handleDelete(formData);
		break;
	default:
		break;
	}	
	return ret;

}


function processBrands(formData){
	var ret=true;

	switch (parseInt(formData.actionId)) {
	case CONSTANTS.ACTION.SHOW_ADD_FORM:
		formData.url=CONSTANTS.COMMON_URL.showAddEditFormUrl;
		handleEditShow(formData,function(){
			formData.actionId=CONSTANTS.ACTION.ADD_DETAILS;  
		});
		break;
	case CONSTANTS.ACTION.SHOW_EDIT_FORM:
		formData.url=CONSTANTS.COMMON_URL.showAddEditFormUrl;
		handleEditShow(formData,function(){
			formData.actionId=CONSTANTS.ACTION.EDIT_DETAILS;  
		});
		break;
	case CONSTANTS.ACTION.ADD_DETAILS:
		formData.form= $("#modelGenericSave").serialize();
		formData.formId="modelGenericSave";
		formData.url=CONSTANTS.COMMON_URL.addDetails;
		ret=handleGenericSave(formData);
		break;
	case CONSTANTS.ACTION.EDIT_DETAILS:
		formData.form= $("#modelGenericSave").serialize();
		formData.formId="modelGenericSave";
		formData.url=CONSTANTS.COMMON_URL.editDetails;
		ret=handleGenericSave(formData);
		break;
	case CONSTANTS.ACTION.DELETE:
		formData.url=CONSTANTS.COMMON_URL.deleteDetails;
		handleDelete(formData);
		break;
	default:
		break;
	}	
	return ret;
}


function processProducts(formData){
	var ret=true;

	switch (parseInt(formData.actionId)) {
	case CONSTANTS.ACTION.SHOW_ADD_FORM:
		formData.url=CONSTANTS.COMMON_URL.showAddEditFormUrl;
		handleEditShow(formData,function(){
			formData.caseIds=[1,2,3,4];
			handleCombobox(formData);
			formData.actionId=CONSTANTS.ACTION.ADD_DETAILS;  
		});
		break;
	case CONSTANTS.ACTION.SHOW_EDIT_FORM:
		formData.url=CONSTANTS.COMMON_URL.showAddEditFormUrl;
		handleEditShow(formData,function(){
			formData.caseIds=[1,2,3,4];
			handleCombobox(formData);
			updateRadioStatus('radio-status');
			formData.actionId=CONSTANTS.ACTION.EDIT_DETAILS;  
		});
		break;
	case CONSTANTS.ACTION.ADD_DETAILS:
		formData.form= $("#modelGenericSave").serialize();
		formData.formId="modelGenericSave";
		formData.url=CONSTANTS.COMMON_URL.addDetails;
		ret=handleGenericSave(formData);
		break;
	case CONSTANTS.ACTION.EDIT_DETAILS:
		formData.form= $("#modelGenericSave").serialize();
		formData.formId="modelGenericSave";
		formData.url=CONSTANTS.COMMON_URL.editDetails;
		ret=handleGenericSave(formData);
		break;
	case CONSTANTS.ACTION.DELETE:
		formData.url=CONSTANTS.COMMON_URL.deleteDetails;
		handleDelete(formData);
		break;
	default:
		break;
	}	
	return ret;

}

function rangeController(){
	$('[id^="rangeslider"] input[type="range"]').on('input',function(){
		
		var minCtx=$(this).attr('data-type')=='min'?$(this):$(this).prev();
		var maxCtx=$(this).attr('data-type')=='max'?$(this):$(this).next();
		
		var min=parseInt($(minCtx).val());
		var max=parseInt($(maxCtx).val());
		
		if(min>max ){
			min=max;
		}
		$(maxCtx).val(max);
		$(minCtx).val(min);
		
		$(maxCtx).next().find("[class='max-box-show']").html(max);
		$(minCtx).prev().find("[class='min-box-show']").html(min);
		
	});
}

function selectingCombobox(moduleId,ids){
	var formData={caseIds:ids.split(","),moduleId:moduleId	};
	handleCombobox(formData);
}

function handleCombobox(formData){

	switch (parseInt(formData.moduleId)) {
	case (CONSTANTS.MODULE.SHOPPING_BILL.ID+CONSTANTS.MODULE.SHOPPING_BILL.BILL):
	case (CONSTANTS.MODULE.MANAGE_DATA.ID+CONSTANTS.MODULE.MANAGE_DATA.PRODUCTS):		
		getProductsCombo(formData);
		formData.caseIds.splice(0,1);

		break;

	default:
		break;
	}
}

function getProductsCombo(formData){
	switch (parseInt(formData.caseIds[0])) {
	case 1:
		
	getCombobox("../combobox/quantity-unit","quantityUnit");
	getCombobox("../combobox/product-category","productCategoryId",null,function(){
		handleCombobox(formData);
	});
	break;
	case 2:
	getCombobox("../combobox/product-type/"+$("#productCategoryId").val(),"productTypeId",null,function(){
		handleCombobox(formData);
	});
	break;
	case 3:
	getCombobox("../combobox/product-subtype/"+$("#productTypeId").val(),"productSubTypeId",null,function(){
		handleCombobox(formData);
	});
	break;
	case 4:
		getCombobox("../combobox/brands/"+$("#productCategoryId").val(),"brandId",null,function(){
			handleCombobox(formData);
		});
	break;		
	default:
		break;
	}
}

function processQuantityUnit(formData){
	var ret=true;
	
	switch (parseInt(formData.actionId)) {
	case CONSTANTS.ACTION.SHOW_ADD_FORM:
	case CONSTANTS.ACTION.SHOW_EDIT_FORM:

		formData.url=CONSTANTS.COMMON_URL.showAddEditFormUrl;
		handleEditShow(formData,function(){
			if(parseInt(formData.actionId)==CONSTANTS.ACTION.SHOW_ADD_FORM)
				formData.actionId=CONSTANTS.ACTION.ADD_DETAILS; 
			else
				formData.actionId=CONSTANTS.ACTION.EDIT_DETAILS; 
			
			formData.isGenericSave=true;
			formData.formId="modelGenericSave";
		});
		break;
		
	case CONSTANTS.ACTION.ADD_DETAILS:
	case CONSTANTS.ACTION.EDIT_DETAILS:
		formData.formId="modelGenericSave";
		
		if(parseInt(formData.actionId)==CONSTANTS.ACTION.ADD_DETAILS)
			formData.url=CONSTANTS.COMMON_URL.addDetails;
		else 
			formData.url=CONSTANTS.COMMON_URL.editDetails;
		
		formData.form= $("#modelGenericSave").serialize();
		if(!validateGenericSave(formData))
			return false;
		ret=handleGenericSave(formData);
		break;
	case CONSTANTS.ACTION.DELETE:
		formData.url=CONSTANTS.COMMON_URL.deleteDetails;
		handleDelete(formData);
		break;
	default:
		break;
	}	
	return ret;

}

function handleEditShow(formData,shownCallback,hiddenCallback){
	doAjaxCall(this.currentMenuUrl+"/"+formData.url,"GET",formData,function(data){
		var buttons= [
		    {
		        name: "Save",
		        className: 'btn btn-primary btn-sm',
				label : "<span class='glyphicon glyphicon-ok-circle' aria-hidden='true'></span>Save",
		        callback: function(){
		        	return getModule(formData.moduleId,formData.actionId,formData.selectedData);
		        }
		    },
		    {
		        name: "Cancel",
		        className: 'btn btn-danger btn-sm',
				label : "<span class='glyphicon glyphicon-remove' aria-hidden='true'></span>Cancel",
		        callback: function(){
		            return true;
		        }
		    }
		];
		if(formData.buttons)
			buttons=formData.buttons;
		
		getModalDialog(formData.title,data,buttons,formData.size,shownCallback,hiddenCallback);
	})
}

function validateGenericSave(formData){
	
	var valid=true;
	
	switch (formData.moduleId) {
	case CONSTANTS.MODULE.PRODUCT_CATEGORY:
		break;
	case CONSTANTS.MODULE.QUANTITY_UNIT:
		break;
	default:
		break;
	}
	return (valid && $("#"+formData.formId).valid());
}

function handleGenericSave(formData){
	if($("#"+formData.formId).valid()){
	doAjaxCall(this.currentMenuUrl+"/"+formData.url,"GET",formData.form,function(data){
		showMessageStatus(data,function(){
			refreshMenu();
			modalDialog.modal('hide');
		});
	},function(data){
		handleFormErrorFromServer(data,formData.formId);
	});
	//return true;
	}
	return false;
}

function handleDelete(formData){
	doAjaxCall(this.currentMenuUrl+"/"+formData.url,"GET",formData,function(data){
		showMessageStatus(data,refreshMenu);

	});
}

function handleFormErrorFromServer(data,formId){
	var resp=data.responseJSON;
	switch(resp.status){
	case 400:
for(var i in resp.errors){
	$('#'+formId+' input[name="'+resp.errors[i]['field']+'"]').next().append('<label id="'+resp.errors[i]['field']+'-error" class="error" for="'+resp.errors[i]['field']+'" style="">'+resp.errors[i].defaultMessage+'</label>');
		
	}
		break;
	}

}

