/**
 * Created by Administrator on 2014/12/18.
 */
$(document).ready(function(){
    var satisfy=0;
    
    
      $("form #oPass").change(function(){
         if($(this).val().length>=6){
             $(this).next().removeClass("into");
             $(this).next().addClass("ticker");
             satisfy++;
         }else{
             $(this).next().removeClass("ticker");
             $(this).next().addClass("into");
             satisfy--;
         }
      });
     $("form #sPass").change(function(){
        if($(this).val().length>=6&&$(this).val()==$("#oPass").val()){
            $(this).next().removeClass("into");
            $(this).next().addClass("ticker");
            satisfy++;
        }else{
            $(this).next().removeClass("ticker");
            $(this).next().addClass("into");
            satisfy--;
        }
     });
    $("form #relName").change(function(){
        if($(this).val().length>0){
            $(this).next().removeClass("into");
            $(this).next().addClass("ticker");
            satisfy++;
        }else{
            $(this).next().removeClass("ticker");
            $(this).next().addClass("into");
            satisfy--;
        }
    });
    $("form #idCard").change(function(){
        if($(this).val().length==18&&!isNaN($(this).val())){
            $(this).next().removeClass("into");
            $(this).next().addClass("ticker");
            satisfy++;
        }else{
            $(this).next().removeClass("ticker");
            $(this).next().addClass("into");
            satisfy--;
        }
    });
    $("form #telNum").change(function(){
        if($(this).val().length==11&&!isNaN($(this).val())){
            $(this).next().removeClass("into");
            $(this).next().addClass("ticker");
            satisfy++;
        }else{
            $(this).next().removeClass("ticker");
            $(this).next().addClass("into");
            satisfy--;
        }
    });
    $("form input[type=checkbox]").click(function(){
        if(satisfy==5){
              $("form #submit").attr('disabled',false);
        }else{
              $("form #submit").attr('disabled',true);
        }
    });
});
