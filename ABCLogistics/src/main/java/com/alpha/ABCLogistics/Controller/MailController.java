package com.alpha.ABCLogistics.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.ABCLogistics.Service.MailService;

@RestController
@RequestMapping("/mail")
public class MailController {
	@Autowired
	MailService mailservice;
	
	@GetMapping("/sendMail")
	public void sendMail()
	{
		String tomail="varunmamidyala2@gmail.com";
		String subject="This is the subject";
		String content="This is the Content";
		
		mailservice.sendMail(tomail,subject,content);
	}
	
	

}
