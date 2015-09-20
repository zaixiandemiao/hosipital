/**
 * Created by Administrator on 2014/12/18.
 */
$(document).ready(function(){
    $("#tab-2 .states li").live('click',function(){
       var nextSibling=$(this).next();
       $(nextSibling).slideToggle();
    });
 
});