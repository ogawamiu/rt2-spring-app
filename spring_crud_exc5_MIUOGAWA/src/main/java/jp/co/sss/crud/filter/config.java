package jp.co.sss.crud.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration 
	public class config implements WebMvcConfigurer { 
	@Bean 
	public FilterRegistrationBean<LoginCheckFilter> configMessage02Filter() { 
	FilterRegistrationBean<LoginCheckFilter> bean = 
	new FilterRegistrationBean<LoginCheckFilter>(); 
	 
	bean.setFilter(new LoginCheckFilter());
	bean.setOrder(1); 
	return bean;
	} 
@Bean 
		public FilterRegistrationBean<AccountCheckFilter> configMessage03Filter() { 
		FilterRegistrationBean<AccountCheckFilter> bean = 
		new FilterRegistrationBean<AccountCheckFilter>(); 
		 
		bean.setFilter(new AccountCheckFilter()); 
		bean.setOrder(2); 
		return bean; 
		}
}