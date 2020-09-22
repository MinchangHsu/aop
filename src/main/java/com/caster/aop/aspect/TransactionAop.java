package com.caster.aop.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author: caster
 * @date: 2020/5/19
 */
@Aspect
@Component
public class TransactionAop {

	/**
	 * 常用AOP通知(增強)類型
	 * before(前置通知)：  在方法開始執行前執行
	 * after(後置通知)：  在方法執行後執行
	 * afterReturning(返回後通知)：   在方法返回後執行
	 * afterThrowing(異常通知)： 在拋出異常時執行
	 * around(環繞通知)：  在方法執行前和執行後都會執行
	 * <p>
	 * 執行順序:
	 * around > before > around > after > afterReturning
	 */

	@Pointcut("execution(* com.caster.aop..*controller.*.doSomething*(..))")
	public void pointcut() {
	}

	@Before(value = "pointcut()")
	public void Before() {
		try {
			System.out.println("Before ........");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterReturning(value = "pointcut()", returning = "returnObject")
	public void afterReturning(JoinPoint joinPoint, Object returnObject) {
		try {
			ResponseEntity a = (ResponseEntity) returnObject;
			System.out.println("AfterReturning ........");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After(value = "pointcut()")
	public void After(JoinPoint joinPoint) {
		try {
			System.out.println("After ........");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Around(value = "pointcut()") //1
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();

		if(joinPoint.getArgs().length != 0)
			System.out.println(joinPoint.getArgs()[0]);

		System.out.println("Start around. Process start  time:" + start);
		try {
			return joinPoint.proceed();
		} catch (Throwable e) {
			System.out.println("around throwable ~ start ....");
			e.printStackTrace();
			System.out.println("around throwable ~ end   ....");
			throw e;
		} finally {
			long finish = System.currentTimeMillis();
			double executeTime = (finish - start) / 1000d;
			System.out.println(String.format("Final around. Process finish time:%s, execute time:%s", finish, executeTime));
		}
	}

	@AfterThrowing(value = "pointcut()")
	public void afterThrowing() {
		System.out.println("AfterThrowing rollback.");
	}
}


