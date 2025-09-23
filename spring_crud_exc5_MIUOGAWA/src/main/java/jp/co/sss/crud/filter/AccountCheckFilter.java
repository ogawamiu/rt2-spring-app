package jp.co.sss.crud.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.sss.crud.bean.EmployeeBean;

//@Component
public class AccountCheckFilter extends HttpFilter{
@Override
public  void doFilter(
		HttpServletRequest request, HttpServletResponse response, FilterChain chain)
		throws IOException, ServletException{
		
		String requestURL = request.getRequestURI();
		
		if(requestURL.indexOf("/html/") != -1|| requestURL.indexOf("/css/") != -1|| requestURL.indexOf("/img/") != -1|| requestURL.indexOf("/js/") != -1) {
			chain.doFilter(request, response);
			return;
			}
		
		if(requestURL.endsWith("/regist/input") || requestURL.endsWith("/delete/check") ) {
			
			HttpSession session = request.getSession();
			EmployeeBean empId = (EmployeeBean)session.getAttribute("loginUser");
			
			if(empId.getEmpId() == 1 || empId ==null) {
				response.sendRedirect("/spring_crud/");
			}else {
				chain.doFilter(request, response);
			}
			}
}
}
