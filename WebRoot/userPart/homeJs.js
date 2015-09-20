/**
 * Created by Administrator on 2014/12/17.
 */
$(document).ready(function(){
	
	$.ajax({
		  url: "getSession.jsp",
		  cache:false,
	      success:function(responseText){
	    	  if(responseText =="no"){
	    		  $(".topBar span:first-child a").html("登陆/注册");
	    	      $(".topBar span:first-child a").attr('href',"logIn/logIn.html");
	    	  }else{
	    		  $(".topBar span:first-child a").html(responseText);
	    	      $(".topBar span:first-child a").attr('href',"userInfro/userInfro.html");
	    	  }
	      }
		 });
	
	$("#department").find("option").remove();
    $.ajax({
        url:'homeJSP/getDepartName.jsp',
        type:'get',
        success:function(responseText){
        	$.each(responseText.split("#"),function(){
        		if(this!="")
                    $("#department").append("<option>"+this+"</option>");
             });
        }
    });
    $("#department").change(function(){
        $("#docTitle").empty();
        $.ajax({
            url:'homeJSP/getDocTitle.jsp',
            type:'get',
            data:'department='+$("#department option:selected").text(),
            success:function(responseText){
            	$.each(responseText.split("#"),function(){
            		if(this!="")
                        $("#docTitle").append("<option>"+this+"</option>");
                 });
            }
        });
    });
    
    function hideNum(num){
        switch(num){
            case 1:{
                $("#homePage").hide();
                break;
            }
            case 2:{
                $("#servicePage").hide();
                break;
            }
            case 3:{
                $("#departPage").hide();
                break;
            }
            case 4:{
                $("#aboutPage").hide();
                break;
            }
        }
    }
    function showNum(num){
        switch(num){
            case 1:{
                $("#homePage").show();
                break;
            }
            case 2:{
                $("#servicePage").show();
                break;
            }
            case 3:{
                $("#departPage").show();
                break;
            }
            case 4:{
                $("#aboutPage").show();
                break;
            }
        }
    }
    var navNum=1;
    $(".nav-menu li").click(function(){
        $(".nav-menu li:nth-child("+navNum+") a:first-child").removeClass(".active");
        hideNum(navNum);
        navNum=$(this).index()+1;
        showNum(navNum);
        $(".nav-menu li:nth-child("+navNum+") a:first-child").addClass(".active");
    });
    //点击科室选项，加载科室信息
    
    
   
});