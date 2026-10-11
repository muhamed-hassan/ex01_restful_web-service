package app.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.domain.CustomerService;

@RequestMapping("customers")
@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
}
