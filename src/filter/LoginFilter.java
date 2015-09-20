package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
        HttpServletRequest request2 = (HttpServletRequest)request;
        HttpServletResponse response2 = (HttpServletResponse)response;
        HttpSession session = request2.getSession();
        if(request2.getRequestURI().indexOf("manager/managerLogin.jsp")!=-1
        		||request2.getRequestURI().indexOf("manager/login.htm")!=-1){
        	chain.doFilter(request, response);
        	return;
        }
        
        if(session.getAttribute("manager")!=null){
        	chain.doFilter(request, response);
        }else{
        	response2.sendRedirect(request2.getContextPath()+"/notLogin.jsp");
        }
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
