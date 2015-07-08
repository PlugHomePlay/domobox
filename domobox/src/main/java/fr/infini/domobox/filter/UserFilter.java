package fr.infini.domobox.filter;

import java.io.IOException;
import java.time.LocalTime;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.log4j.Log4j2;
import fr.infini.domobox.exceptions.security.SecurityException;
import fr.infini.domobox.security.service.SessionService;

@Log4j2
public class UserFilter implements Filter {
	
	private SessionService sessionService = SessionService.getInstance();
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		
		String token = request.getHeader("sessionToken");
		
		
		//Sûrement dans la partie authentification
		if(token == null){
			//authentification /domobox/rest/sso/primael/aqwzsx123
			//String pattern = "/domobox/rest/sso/[.^/]*/[.^/]*";
			
			String uri = request.getRequestURI();
			System.out.println(uri);
			if(uri.matches("^//domobox//rest//sso//[.^//]*//[.^//]*$")){
				//Normal on sort
				chain.doFilter(req, response);
				return;
			}
			System.err.println("tu t'es cru chez mémé!!!");
		}
		
		//On valide le token session
		try {
			sessionService.validerSession(token);
		} catch (SecurityException e) {
			//Pas valide
			//On trace
			
			//On jette
			
			e.printStackTrace();
			
		}
		
		String ipAdress = request.getRemoteAddr();
		
		log.info("IP " + ipAdress + ", Time " + LocalTime.now().toString());
		
		chain.doFilter(req, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		
		String testParam = config.getInitParameter("test-param");
		
		log.info("Test param " + testParam);
		
	}
	
}
