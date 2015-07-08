package fr.infini.domobox.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class Observer {

	public Observer(){
		
	}
	
	@Before("@annotation(fr.infini.domobox.aspect.Loggable)")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("log from " + joinPoint.toString());
		Object result = joinPoint.proceed();
		return result;
	}
}
