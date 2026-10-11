package app.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.domain.AdministrativeService;

@RequestMapping("loans")
@RestController
public class LoanController {
	
	@Autowired
	private AdministrativeService administrativeService;

}
