/**
 * Created by Administrator on 2014/12/8.
 */
$(function(){
    var curY;
    var curH;
    var curW;
    var srtY;
    var srtX;
    var objL;

    function setInitValue(obj){
        curY =obj.offset().top;
        curH =obj.height();
        curW = obj.width()+270+"px";
        srtY =curY+(curH/2)+"px";
        srtX =obj.width()+260+"px";
    }
    $(".optn").mouseover(function(){
       objL = $(this);
        setInitValue(objL);
        var allY = curY - curH +"px";
        objL.addClass("optnFocus");
        objL.next("ul").show().css({"top":allY,"left":curW});
        $("#sort").show().css({"top":srtY,"left":srtX});
    }).mouseout(function(){
        $(this).removeClass("optnFocus");
        $(this).next("ul").hide();
        $("#sort").hide();
    });

    $(".tip").mousemove(function(){
        $(this).show();
        objL = $(this).prev("li");
        setInitValue(objL);
        objL.addClass("optnFocus");
        $("#sort").show().css({"top":srtY,"left":srtX});
    }).mouseout(function(){
        $(this).hide();
        $(this).prev("li").removeClass("optnFocus");
        $("#sort").hide();
    });
    
    
    
})