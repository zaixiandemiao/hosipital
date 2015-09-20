/**
 * Created by Administrator on 2014/12/21.
 */

$(function() {
	    /*$(".tab:nth-child(2)").load("getTable.jsp");*/
        $("#sidebar1 ul li").click(function () {
                var num = $(this).attr("id");
                var target = $(".tab:nth-child(" + num + ")");
                $(target).show();
                $(target).siblings().hide();
            }
        );
        $(".tab_3").click(function(){
           $(this).next().slideToggle("slow");
        });
        $(".tab_2").click(function(){
        	if($(this).is(":first-child")){
        		if($(this).next().is(":visible")==false){
                    $(this).next().load("getDepart.jsp");
            	}	
        	}else if($(this).is(":nth-child(3)")){
        		if($(this).next().is(":visible")==false){
                    $(this).next().load("getDoc.jsp");
            	}
            }
        	$(this).next().slideToggle("slow"); 	
        });
        $.ajax({
            url:'/dbDesign/userPart/homeJSP/getDepartName.jsp',
            type:'get',
            success:function(responseText){
            	$.each(responseText.split("#"),function(){
            		if(this!="")
                        $("#loadDepart").append("<option>"+this+"</option>");
                 });
            }
        });
        $("#loadDepart").change(function(){
            $("#loadDoc").empty();
            $.ajax({
                url:'getDocName.jsp',
                type:'get',
                data:'department='+$("#loadDepart option:selected").text(),
                success:function(responseText){
                	$.each(responseText.split("#"),function(){
                		if(this.trim()!="")
                            $("#loadDoc").append("<option>"+this+"</option>");
                     });
                }
            });
        });
        $("#searchDocByDepart").click(function(){
        	$("#docBeLoaded").load("getDocAssign.jsp",{docName:$("#loadDoc option:selected").text()});
        });
        $.ajax({
        	url:'getRegistorCount.jsp',
        	type:'get',
        	success:function(responseText){
        		$("#tab3_2").append(responseText);
        	},error:function(){
        		alert("load tab3_2 error");
        	}
        	
        });
        $.ajax({
        	url:'getDocCount.jsp',
        	type:'get',
        	success:function(responseText){
        		$("#tab3_3").append(responseText);
        	},error:function(){
        		alert("load tab3_3 error");
        	}
        	
        });
        
    }
);