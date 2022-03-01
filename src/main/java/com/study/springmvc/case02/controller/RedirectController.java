package com.study.springmvc.case02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/case02/redirect")
public class RedirectController {
	// 重定向 redirect:
	// 由 server 端發出 redirect 命令(會放在header中)給 client 端，並由 client 端去執行
	// 所以 client 端的瀏覽器網址會改變
	// 重定向可以指向內網與外網
	
	@RequestMapping("/demo1")
	public String demo1() {
		return "redirect:/index.jsp";
	}
	
	//透過redirect: 重定向到 show_time.jsp 要如何寫
	@RequestMapping("/demo2")
	public String demo2() {
		return "redirect:../time/now";
	}
	
	@RequestMapping("/demo3")
	public String demo3() {
		return "redirect:http://google.com";
	}
	
	// 重定向帶參數 I
	@RequestMapping("/demo4")
	public String demo4() {
		return "redirect:/show_param.jsp?username=Vincent&age=18";
	}
	
	// 重定向帶參數 II
	
	@RequestMapping("/demo5")
	public String demo5(RedirectAttributes attr) {
		attr.addAttribute("username", "Anita");
		attr.addAttribute("age", "19");
		return "redirect:/show_param.jsp";
	}
	
	// 重定向帶參數 III
	//http://localhost:8080/springmvc/mvc/case02/redirect/saveOrder?name=iPhone&price=25000&qty=5
	@RequestMapping("/saveOrder")
	public String saveOrder(@RequestParam("name") String name,
			@RequestParam("price") Integer price,
			@RequestParam("qty") Integer qty,
			RedirectAttributes attr) {
		attr.addFlashAttribute("name", name);
		attr.addFlashAttribute("price", price);
		attr.addFlashAttribute("qty", qty);
		return "redirect:./success";
	}
	
	@RequestMapping("/success")
	public String saveOrder() {
		return "case02/order_ok";
	}
	
	
}
