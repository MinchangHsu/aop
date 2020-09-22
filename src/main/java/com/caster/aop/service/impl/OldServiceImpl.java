package com.caster.aop.service.impl;

import com.caster.aop.service.OldService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author: caster
 * @date: 2020/9/7
 */
@Service
@Component
public class OldServiceImpl implements OldService {
	public void oldMethod() {
		System.out.println("oldMethod ~~");
	}
}
