package com.example.demo.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Services.ClientService;
import com.example.demo.entity.Client;

@Controller 
public class TymleafEmployesController {
	@Autowired
	protected ClientService clientservice ;

	//Client
		@GetMapping("/AddClient")
	    public String addclient() {
	       
	
	        return  "addclient" ;	
		
		}
		 @PostMapping("/save")
		    public String save_client(@ModelAttribute Client c) {
		       
			 clientservice.saveClient(c);
	           
		        return  "redirect:/getallclient" ;	
			
			}
		 
		 @GetMapping("/getallclient")
		    public String showView2(Model model) {
		        model.addAttribute("clients",clientservice.getAllClients()) ;
		
		        return  "listerclients" ;	
			
			} 
		 
		 @GetMapping("/deleteclient/{id}")
		    public String deleteemp (@PathVariable Long id) {
			 clientservice.deleteClientById(id); 
		        return  "redirect:/getallclient" ;	
		 }
		
		//---------------------------------------------------------------------------------admin
		@GetMapping("/Employe/index")
	    public String indexemp(Model model) { 
	        return  "indexemp" ;	}
		
		
}

