package com.caster.aop.aspect;

import com.caster.aop.service.NewService;
import com.caster.aop.service.impl.NewServiceImpl;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * @author: caster
 * @date: 2020/9/22
 */
@Aspect
@Component
public class IntroductionAop {

	@DeclareParents(value = "com.caster..service..*", defaultImpl = NewServiceImpl.class)
	public NewService newService;

}
