package com.caster.aop.service.impl;

import com.caster.aop.service.NewService;
import org.springframework.stereotype.Component;

/**
 * @author: caster
 * @date: 2020/9/7
 */
@Component
public class NewServiceImpl implements NewService {
	@Override
	public void newMethod() {
		System.out.println("newMethod ~~");
	}
}
