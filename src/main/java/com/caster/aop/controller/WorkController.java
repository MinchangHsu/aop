package com.caster.aop.controller;


import com.caster.aop.anno.CalculateExecutionTime;
import com.caster.aop.service.NewService;
import com.caster.aop.service.OldService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * @author: caster
 * @date: 2020/8/31
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/work")
public class WorkController implements Serializable {

	final OldService oldService;

	@GetMapping("/{numberStr}")
	public ResponseEntity doSomething(@PathVariable("numberStr") String numberStr) {
		return ResponseEntity.ok("success");
	}

	@GetMapping("/doSomethingError")
	public void doSomethingError() throws Exception {
		throw new Exception("Exception that it go ~");
	}

	@GetMapping("/doSomethingIntroduction")
	public void doSomethingIntroduction() throws Exception {
		oldService.oldMethod();
		NewService newService = (NewService) oldService;
		newService.newMethod();
	}

	@GetMapping("/doNothingWork")
	@CalculateExecutionTime(value = "doNothingWork")
	public void doNothingWork() throws Exception {
		System.out.println("nothing.......");
	}
}
