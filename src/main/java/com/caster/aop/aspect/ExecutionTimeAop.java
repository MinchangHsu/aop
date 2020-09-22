package com.caster.aop.aspect;


import com.caster.aop.anno.CalculateExecutionTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ExecutionTimeAop {

	@Pointcut(value = "@annotation(calculateExecutionTime)", argNames = "calculateExecutionTime")
	public void pointcut(CalculateExecutionTime calculateExecutionTime) {
	}

	@Around(value = "pointcut(calculateExecutionTime)", argNames = "joinPoint,calculateExecutionTime")
	public Object around(ProceedingJoinPoint joinPoint, CalculateExecutionTime calculateExecutionTime) throws Throwable {
		long startTime = System.currentTimeMillis();
		try {
			return joinPoint.proceed();
		} catch (Throwable throwable) {
			throw throwable;
		} finally {
			long finishTime = System.currentTimeMillis();
			System.out.println(String.format("方法名稱:%s, 執行時間: %s秒", calculateExecutionTime.value(), (finishTime - startTime) / 1000d));
		}
	}
}
