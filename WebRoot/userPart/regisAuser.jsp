<%@page import="java.text.SimpleDateFormat"%>
<%@page import="doctor.Doctor"%>
<%@page import="doctor.docControl"%>
<%@page import="department.departControl"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/common.jsp" %>
<%
   String username="";
   if(request.getSession().getAttribute("user")!=null)
      username=request.getSession().getAttribute("user").toString();
    // out.println(username);
    
    if(username==""){
        out.println("请您先登录 ");
        return;
    } 
    String name,depart;
    int title,waitnum;
    name=request.getParameter("name");
    depart=request.getParameter("department");
    title=Integer.valueOf(request.getParameter("title"));
    waitnum = Integer.valueOf(request.getParameter("waitnum")); 

    String datetime = request.getParameter("datetime");
    String idcard = request.getParameter("idcard");
    //out.println(idcard); 
    
    Date d1 = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date d2 =sdf.parse(datetime);
    
    
   long dayBetween = (d2.getTime()-d1.getTime())/(24*60*60*1000);
   if(dayBetween>31 || dayBetween<0){
        out.println("日期不在范围内 ");
        return ;
    }  
    //out.println(d1+" "+d2+" "+datetime+" "+dayBetween);
   
    //out.println(name+"#"+depart+"#"+title+" "+waitnum+" "+hcid); 
    assignControl ac = new assignControl(commondb);
    if(!ac.canAssignLevel0(idcard, datetime)){
        out.println("当月预约次数已经达到上限 ，无法预约 ");
        return ;
    }
    if(!ac.canAssignLevel1(idcard, datetime)){
        out.println("当日预约次数已经达到上限 ，无法预约 ");
        return ;
    }
    if(!ac.canAssignLevel2(idcard,depart,datetime)){
        out.println("您只能在该科室预约一次  ");
        return ;
    } 
    departControl dc = new departControl(commondb);
    double treat = dc.getTreat(depart, title);
     
    
    String classTh = request.getParameter("ClassTh");   //班次 
    docControl  docC = new docControl(commondb);
    Doctor doctor =   docC.getDoctorByName(name); 
    
   int result = ac.assignAman(treat, classTh, waitnum, depart, title,doctor.getDocId(),datetime);
   if(result>0){
       userControl rc = new userControl(commondb);
       if(rc.insertUserAssign(username,result,idcard))
            out.println("挂号成功"); 
    }else{
       out.println("挂号失败 ");
    }
 %>