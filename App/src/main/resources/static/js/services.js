function ajaxSetup() {
	$.ajaxSetup({
		error : function(jqXHR, textStatus, errorThrown) {
			if (jqXHR.status == 404) {
				alert("Element not found.");
			} else {
				alert("Error: " + textStatus + ": " + errorThrown);
			}
		}
	});
}

function doAjaxCall(url, type, data,successCb, errorCb, contentType, headers, processData) {
	$.ajax({
		url : url,
		type : type ? type : "GET",
		data : data ? data : null,
		contentType : (typeof contentType =='undefined' || contentType==null) ? "application/x-www-form-urlencoded":contentType,
		headers : (typeof headers =='undefined' || headers==null) ? null:headers,
		processData : (typeof processData =='undefined' || processData==null) ?  true :processData,
		success : function(data, textStatus, jqXHR) {
			if (successCb && typeof successCb === 'function')
				successCb(data, textStatus, jqXHR);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			if (errorCb && typeof errorCb === 'function')
				errorCb(jqXHR, textStatus, errorThrown);
		}

	});
}

var modalDialog=null;
function getModalDialog(title,message,buttons,size,shownCallback,hiddenCallback,showCallback,hideCallback){
	var btns = {};
	if (buttons && buttons.length > 0)
		for (i = 0; i < buttons.length; i++) {
			btns[buttons[i]["name"]] = {
				label : buttons[i]["label"] ? buttons[i]["label"] : null,
				className : buttons[i]["className"] ? buttons[i]["className"]
						: "default",
				callback : buttons[i]["callback"] ? buttons[i]["callback"]
						: null
			};
		}
	
	modalDialog = bootbox.dialog({
		size:size?size:"large",
		title: title?title:"Title",
		show : false,
		message: message?message:"Message",
		buttons:btns
		});
	
	modalDialog.on("show.bs.modal", function() {
		  if(showCallback && typeof showCallback==='function')
			  showCallback();
		});
	modalDialog.on("shown.bs.modal", function() {
		 if(shownCallback && typeof shownCallback==='function')
			 shownCallback();
		});
	modalDialog.on("hide.bs.modal", function() {
		 if(hideCallback && typeof hideCallback==='function')
			 hideCallback();
		});
	modalDialog.on("hidden.bs.modal", function() {
		 if(hiddenCallback && typeof hiddenCallback==='function')
			 hiddenCallback();
		modalDialog=null;
		});
	modalDialog.modal('show');
}

function refreshMenu(){
	$("#menu-url a[href='"+this.currentMenuUrl+"']").trigger("click");
}

function showMessage(msg, mode) {
	$.notify(msg, mode);
}

function showMessageStatus(data,successFn,errorFn){

var dataArray = data.split("_");
var status = dataArray[0].toUpperCase();
var statusResp = dataArray[1];
if (status == "SUCCESS") {
	showMessage(statusResp, "success");
	if(successFn && typeof successFn ==='function')
		successFn();
} else if (status == "ERROR") {
	showMessage(statusResp, "error");
	if(errorFn && typeof errorFn ==='function')
		errorFn();
}else{
	showMessage("Please provide proper message!", "error");

}
}


function handleFormValidation(formId){
	
	var that=this;
	var ret=true;
	var val=null;
	var re =null;
	this.formErrorList=[];
	 $('#'+formId+' input, textarea').each(function(){
		 var dataVal=$(this).attr('data-validate');
		 if(dataVal){
			 var dataVals= dataVal.split(","); 
			 for(var i in dataVals){
			 switch (dataVals[i]) {
			 case Validation.Text.ONLY_ALPHA:
					 $(this).on('keyup focusout',function(){						
						var idx=that.formErrorList.indexOf($(this).attr('name'))
						if(idx>-1)
							that.formErrorList.splice(idx,1);
						val=$(this).val();	
						re = /[^a-zA-Z_ ]/gi;
						 $(this).next().remove();
						 if(re.test(val)) {
							 that.formErrorList.push($(this).attr('name'));
							 $(this).next("span.error").remove();
							 $(this).after('<span class="error">Only Characters allowed</span>');							
							}
						 if(/^\s/.test(val)) {
							 $(this).next("span.error").remove();
							 	that.formErrorList.push($(this).attr('name'));
								$(this).after('<span class="error">First space not allowed</span>');							
	 						}
					});							 			
				 break;
				 
	
			 case Validation.Text.ONLY_NUMERIC:					
					$(this).on('keyup focusout',function(){		
						var idx=that.formErrorList.indexOf($(this).attr('name'))
						if(idx>-1)
							that.formErrorList.splice(idx,1);
						val=$(this).val();
						 re =/[^0-9]/gi;
						 $(this).next().remove();
						 if (re.test(val)) {
							 $(this).next("span.error").remove();
							 $(this).after('<span class="error">Only Numbers allowed</span>');
								that.formErrorList.push($(this).attr('name'));
							}
						 if(/^\s/.test(val)) {
							 $(this).next("span.error").remove();
							 $(this).after('<span class="error">First space not allowed</span>');							
								that.formErrorList.push($(this).attr('name'));
	 						}
					});	
				 break;
			 
			 case  Validation.Text.ONLY_NUMERIC_DOT:
					$(this).on('keyup focusout',function(){
						var idx=that.formErrorList.indexOf($(this).attr('name'))
						if(idx>-1)
							that.formErrorList.splice(idx,1);
						val=$(this).val();
						var re = new RegExp("^(?=.)([+-]?([0-9]*)(\.([0-9]+))?)$")
						$(this).next().remove();
						 if (!(re.test(val))&&!(val=="")) {
							 $(this).next("span.error").remove();
								$(this).after('<span class="error">Enter in Rupee Format</span>');
								that.formErrorList.push($(this).attr('name'));
							}
					});	
				 break;
				
			 case Validation.Text.ALPHA_NUMERIC:
					$(this).on('keyup focusout',function(){
						var idx=that.formErrorList.indexOf($(this).attr('name'))
						if(idx>-1)
							that.formErrorList.splice(idx,1);
						var idx=that.formErrorList.indexOf($(this).attr('name'))
						if(idx>-1)
							that.formErrorList.splice(idx,1);
						
						val=$(this).val();
						 var re = /[^a-zA-Z_ 0-9]/gi;
						 $(this).next().remove();
						 if (re.test(val)) {
							 that.formErrorList.push($(this).attr('name'));
							 $(this).next("span.error").remove();
								$(this).after('<span class="error">Only Characters & Numbers allowed.Ex:123abc,abc123</span>');
								that.formErrorList.push($(this).attr('name'));
							}
							if(/^\s/.test(val)) {
								 $(this).next("span.error").remove();
								$(this).after('<span class="error">First space not allowed</span>');							
								that.formErrorList.push($(this).attr('name'));
	 						}
					});
				 break;
				 
			 case Validation.Text.ALPHA_PLUS:
				 $(this).on('keyup focusout',function(){						
						var idx=that.formErrorList.indexOf($(this).attr('name'))
						if(idx>-1)
							that.formErrorList.splice(idx,1);
						val=$(this).val();	
						re = /[^a-zA-Z\-_ ]/gi;
						 $(this).next().remove();
						 if(re.test(val)) {
							 that.formErrorList.push($(this).attr('name'));
							 $(this).next("span.error").remove();
							 $(this).after('<span class="error">Only Characters and allowed..Ex:123abc,abc-123</span>');							
							}
						 if(/^\s/.test(val)) {
							 $(this).next("span.error").remove();
							 	that.formErrorList.push($(this).attr('name'));
								$(this).after('<span class="error">First space not allowed</span>');							
	 						}
					});							 			
				 break;
				 
				 
			 case Validation.Text.ALPHA_NUMERIC_VALIDSPECIALCHARS:
					$(this).on('keyup focusout',function(){
						var idx=that.formErrorList.indexOf($(this).attr('name'))
						if(idx>-1)
							that.formErrorList.splice(idx,1);
						val=$(this).val();
						 var re = /[\'\"\<]/gi;
						 $(this).next().remove();
							if (re.test(val)) {
								 $(this).next("span.error").remove();
								$(this).after('<span class="error">Special characters [\', \", \<]not allowed</span>');
								that.formErrorList.push($(this).attr('name'));
							}
							if(/^\s/.test(val)) {
								 $(this).next("span.error").remove();
								$(this).after('<span class="error">First space not allowed</span>');							
								that.formErrorList.push($(this).attr('name'));
	 						}
					});
				 break;
			 case Validation.Text.ALPHA_NUMERIC_ALLOWSOMESPECIAL:
				 $(this).on('keyup focusout',function(){
						var idx=that.formErrorList.indexOf($(this).attr('name'))
						if(idx>-1)
							that.formErrorList.splice(idx,1);
						var idx=that.formErrorList.indexOf($(this).attr('name'))
						if(idx>-1)
							that.formErrorList.splice(idx,1);
						
						val=$(this).val();
						 var re = /[^a-zA-Z_ 0-9.&()\-\\]/gi;
						 $(this).next().remove();
						 if (re.test(val)) {
							 that.formErrorList.push($(this).attr('name'));
							 $(this).next("span.error").remove();
								$(this).after('<span class="error">Only Characters & Numbers allowed.Ex:123a&b.c,ab(c)d123</span>');
								that.formErrorList.push($(this).attr('name'));
							}
							if(/^\s/.test(val)) {
								 $(this).next("span.error").remove();
								$(this).after('<span class="error">First space not allowed</span>');							
								that.formErrorList.push($(this).attr('name'));
	 						}
					});
				 break;
				 
			 case Validation.Text.ALPHA_VALIDSPECIALCHARS:
					$(this).on('keyup focusout',function(){
						var idx=that.formErrorList.indexOf($(this).attr('name'))
						if(idx>-1)
							that.formErrorList.splice(idx,1);
						val=$(this).val();
						 var re = /[0-9\'\"\<]/gi;
						 $(this).next().remove();
						 if (re.test(val)) {
							 $(this).next("span.error").remove();
								$(this).after('<span class="error">Number and Special characters [\', \", \<] not allowed</span>');
								that.formErrorList.push($(this).attr('name'));
							}
							if(/^\s/.test(val)) {
								 $(this).next("span.error").remove();
								$(this).after('<span class="error">First space not allowed</span>');							
								that.formErrorList.push($(this).attr('name'));
	 						}
					});
				 break;
			 case Validation.Text.NUMERIC_VALIDSPECIALCHARS:
					$(this).on('keyup focusout',function(){
						var idx=that.formErrorList.indexOf($(this).attr('name'))
						if(idx>-1)
							that.formErrorList.splice(idx,1);
						val=$(this).val();
						 var re = /[a-zA-Z\'\"\<]/gi;
						 $(this).next().remove();
						 if (re.test(val)) {
							 $(this).next("span.error").remove();
								$(this).after('<span class="error">Number and Special characters [\', \", \<] not allowed</span>');
								that.formErrorList.push($(this).attr('name'));
							}
							if(/^\s/.test(val)) {
								 $(this).next("span.error").remove();
								$(this).after('<span class="error">First space not allowed</span>');							
								that.formErrorList.push($(this).attr('name'));
	 						}
					});
				 break;
				 
			 case Validation.Text.NUMERIC_ALPHA_NUMERIC_MANDATORY:
					$(this).on('keyup focusout',function(){
						var idx=that.formErrorList.indexOf($(this).attr('name'))
						if(idx>-1)
							that.formErrorList.splice(idx,1);
						val=$(this).val();
						 var re = /[^a-zA-Z_ 0-9]/gi;
						 $(this).next().remove();
						 if (re.test(val)) {
							 $(this).next("span.error").remove();
								$(this).after('<span class="error">Number and Special characters [\', \", \<] not allowed</span>');
								that.formErrorList.push($(this).attr('name'));
							}
							if(/^\s/.test(val)) {
								 $(this).next("span.error").remove();
								$(this).after('<span class="error">First space not allowed</span>');							
								that.formErrorList.push($(this).attr('name'));
	 						}
						 
					});
				 break;
				 
			 case Validation.Text.NUMERIC_COMMA:
				 $(this).on('keyup focusout',function()
						 {
						var idx=that.formErrorList.indexOf($(this).attr('name'))
						if(idx>-1)
							that.formErrorList.splice(idx,1);
						var idx=that.formErrorList.indexOf($(this).attr('name'))
						if(idx>-1)
							that.formErrorList.splice(idx,1);
						
						val=$(this).val();
						 var re = /[^0-9,]/gi;
						 $(this).next().remove();
						 if (re.test(val)) {
							 that.formErrorList.push($(this).attr('name'));
							 $(this).next("span.error").remove();
								$(this).after('<span class="error">Only Numbers and , allowed');
								that.formErrorList.push($(this).attr('name'));
							}
							if(/^\s/.test(val)) {
								 $(this).next("span.error").remove();
								$(this).after('<span class="error">First space not allowed</span>');							
								that.formErrorList.push($(this).attr('name'));
	 						}
					});
				 break;
			 case Validation.Text.ONLY_NUMERIC_MOBILE_NUMBER:					
				 $(this).on('keyup focusout',function(){		
						var idx=that.formErrorList.indexOf($(this).attr('name'))
						if(idx>-1)
							that.formErrorList.splice(idx,1);
						val=$(this).val();
						re =/[^0-9]/gi;
						 $(this).next().remove();
						 if (parseInt(val.length, 10) >= 10 && parseInt(val.length, 10) <= 10 ) {
							 // $(this).next().remove();
						    } else {
						    	if(val!=""){
						    	 $(this).next("span.error").remove();
								 $(this).after('<span class="error">Only Numbers allowed</span>');
									that.formErrorList.push($(this).attr('name'));
						    	}
						    }
						 
						 if (re.test(val)) {
							 
							 $(this).next("span.error").remove();
							 $(this).after('<span class="error">Only Numbers allowed</span>');
								that.formErrorList.push($(this).attr('name'));
							}
						 
						 
						/*
						 * if(/^\s/.test(val)) {
						 * $(this).next("span.error").remove(); $(this).after('<span
						 * class="error">First space not allowed</span>');
						 * that.formErrorList.push($(this).attr('name')); }
						 */
					});	
				 break;
				 
			 case Validation.Text.GROUP_OF_MAILS:
				 $(this).on('keyup focusout',function(){
						var idx=that.formErrorList.indexOf($(this).attr('name'))
						if(idx>-1)
							that.formErrorList.splice(idx,1);
						var idx=that.formErrorList.indexOf($(this).attr('name'))
						if(idx>-1)
							that.formErrorList.splice(idx,1);
						
						val=$(this).val();
						 var re = /[^a-zA-Z_ 0-9,.@!#$%&'*+-/=?^_`{|}~]/gi;
						 $(this).next().remove();
						 if (re.test(val)) {
							 that.formErrorList.push($(this).attr('name'));
							 $(this).next("span.error").remove();
								$(this).after('<span class="error">Only email format allowed</span>');
								that.formErrorList.push($(this).attr('name'));
							}
							if(/^\s/.test(val)) {
								 $(this).next("span.error").remove();
								$(this).after('<span class="error">First space not allowed</span>');							
								that.formErrorList.push($(this).attr('name'));
	 						}
					});
				 break;
			
			default:
				break;
			}
		 }
		 }
	 });

}

function getCombobox(url, id, prependlist, funccall) {
	
	doAjaxCall(url, "GET", {},  function(data) {
			processCombo(id, data, prependlist, funccall);
	});
}
function processCombo(comboName, data, prependlist, funccall) {
	var parse=(data!=null && Object.keys(data).length>0)?data:null;
	var options="";;
	if(parse!=null){
		for(var i in parse){
			options+="<option value="+i+">"+parse[i]+"</option>";
		}
	}
	$('#' + comboName).html((prependlist==null?"":prependlist) + options);
	var multiple = $('#' + comboName).attr("multiple");
	var selVal = $('#' + comboName).attr("data-selected");
	if (selVal && selVal.length>0) {
		selVal=selVal.replace("[","").replace("]","");
		if (multiple && selVal) {
			$('#' + comboName).val(selVal.split(","));
			// handleSelectedValues(selVal, comboName);
		} else {
			$('#' + comboName).val(selVal);
		}
	}
	if (typeof funccall === 'function') {
		funccall(parse,selVal);
	}
}

function updateRadioStatus(id){
	
	$('#'+id).find('input[type="radio"]').each(function(){
		if($(this).val()==$(this).attr('data-val'))
		    $(this).prop("checked",true);
	});
	
}


function attachAccordionEvent(classId){
var acc = document.getElementsByClassName(classId);

for (var i = 0; i < acc.length; i++) {
    acc[i].addEventListener("click", function() {
        /* Toggle between adding and removing the "active" class,
        to highlight the button that controls the panel */
    	$(acc).removeClass("active");
    	$(acc).next().css("display","none");
    	$(acc).addClass("active");

        /* Toggle between hiding and showing the active panel */
        var panel = this.nextElementSibling;
        if (panel.style.display === "block") {
//        	$(acc).css("display","none");
            panel.style.display = "none";
        } else {
            panel.style.display = "block";
        }
    });
}
$(acc).first().click();
}