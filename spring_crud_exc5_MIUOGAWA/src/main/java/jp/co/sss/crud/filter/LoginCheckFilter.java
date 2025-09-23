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
public class LoginCheckFilter extends HttpFilter {
	@Override
	public void doFilter (
	HttpServletRequest request,HttpServletResponse response,FilterChain chain)
	throws IOException, ServletException {
		String requestURL = request.getRequestURI();
		if(requestURL.endsWith("/login") || requestURL.endsWith("/") || requestURL.indexOf("/html") != -1
				||requestURL.indexOf("/css") != -1 || requestURL.indexOf("/img") != -1 || requestURL.indexOf("/js") != -1) {
			chain.doFilter(request, response);//URLの終わりが「/login」であれば各フィルターをかける
		}else {
			//リクエスト先のログインチェックの対象であればセッションスコープからユーザー情報を取得
			HttpSession session = request.getSession();
			
			//セッションスコープに入っているloginUserの情報を渡している
			EmployeeBean empId = (EmployeeBean)session.getAttribute("loginUser");
			//該当するユーザーがいなければNULLとして値が返されるためif分に移行
			if(empId == null) {
				//IdがNULLであればすべてのセッション情報を削除
				session.invalidate();
				response.sendRedirect("/spring_crud/");
				return;
			}else {
				//NULLでなければフィルターをかける
				chain.doFilter(request, response);
			}
		}
		
	}

}
