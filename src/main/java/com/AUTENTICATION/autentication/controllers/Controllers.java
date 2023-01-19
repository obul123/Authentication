package com.AUTENTICATION.autentication.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controllers {
	
	@RequestMapping(method=RequestMethod.GET , path="/get/one")
	public String getString()
	{
		return "SUCCESS:";
	}			
	
	@RequestMapping(method=RequestMethod.GET , path="/get/two")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String getString1()
	{
		return "Authorised SUCESS";
	}
	
	@RequestMapping(method=RequestMethod.GET , path="/get/three")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String getString3()
	{
		return "Authorised FAILURE";
	}
	

}
