package com.example.demo.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



import com.example.demo.Services.ClientService;
import com.example.demo.Services.EmployesService;
import com.example.demo.Services.GroupeService;
import com.example.demo.Services.IBankMetier;
import com.example.demo.Services.IBankMetierImp;
import com.example.demo.entity.Client;
import com.example.demo.entity.Compte;
import com.example.demo.entity.Employes;
import com.example.demo.entity.Groupe;
@Controller 
public class TymleafAdminController {
	@Autowired
	protected GroupeService groupeservice ;
	@Autowired
	protected EmployesService empservice ;
	 
	@GetMapping("/admin/index")
    public String indexadmin(Model model) { 
        return  "indexadmin" ;	}
	


	//---------------------------------------------------------------------Controller Groupe Tymleaf---------------------------------------------------------------------
		@GetMapping("/AddGroupe")
	    public String addgroupe() {
	       
	
	        return  "addGroupe" ;	
		
		}
		
		@PostMapping("/savegroupe")
		    public String save_groupe(@ModelAttribute Groupe g) {
			 groupeservice.saveGroupe(g);
	           
		        return  "redirect:/Getallgroupe" ;	
			
		}
		
		
		@GetMapping("/updateGroupe/{id}")
		 public String updategroupe (Model model,@PathVariable Long id)
		 {
			 model.addAttribute("groupe",groupeservice.getGroupe(id)) ;
				
		        return  "updategroupepage" ;	
			
		 }
		 
		
		@GetMapping("/Getallgroupe")
	    public String showallgroupe(Model model) {
	        model.addAttribute("groupes",groupeservice.getAllGroupes()) ;
	
	        return  "listergroupe" ;	
		
		}
		
		 @GetMapping("/deletegroupe/{id}")
		    public String deletegroupe (@PathVariable Long id) {
			    groupeservice.deleteGroupetById(id);   
		        return  "redirect:/Getallgroupe" ;	
		 }
		//---------------------------------------------------------------------------Controller Employes Tymleaf --------------------------------------------------------
		 
		 @GetMapping("/AddEmployes")
		    public String addemployes(Model model) {
			 model.addAttribute("employee", new Employes());
			 model.addAttribute("empolyes", empservice.getAllEmployes()) ;
		     model.addAttribute("allgroupe",groupeservice.getAllGroupes());  
		        return  "addemployes" ;	
			
		  }
		
		@PostMapping("/saveEmployes")
		    public String save_employes(@ModelAttribute Employes e) {
                 
				empservice.saveEmployes(e);
	           
		        return  "redirect:/GetallEmploye" ;	
			
		  }
		
		@GetMapping("/GetallEmploye")
	    public String showallemp(Model model) {
	        model.addAttribute("emps",empservice.getAllEmployes()) ;
	
	        return  "Listeremployee" ;	
		
		}
		
		 @GetMapping("/deleteemployee/{id}")
		    public String deleteemp (@PathVariable Long id) {
			 empservice.deleteemptById(id); 
		        return  "redirect:/GetallEmploye" ;	
		 }
		
		 @GetMapping("/updateEmploye/{id}")
		 public String updateemp (Model model,@PathVariable Long id)
		 {
			 model.addAttribute("empolyes", empservice.getAllEmployes()) ;
		     model.addAttribute("allgroupe",groupeservice.getAllGroupes());
			 model.addAttribute("emp",empservice.getEmp(id)) ;
				
		        return  "updateemployee" ;	
			
		 }
		 
		 
		 @Autowired
		    public IBankMetierImp iBankMetier;

		    @RequestMapping(value = "/operations")
		    public String index(){
		        return "comptes";
		    }


		    @RequestMapping(value = "/consulterCompte" , method = RequestMethod.GET)
		    public String consulter(Model model , String codeCompte ,
		                            @RequestParam(name = "page",defaultValue = "0") int page ,
		                            @RequestParam(name = "size",defaultValue = "4") int size){
		        try{
		            Compte cp = iBankMetier.consulterCompter(codeCompte).get();
		            Page listOperations = iBankMetier.listOperation(codeCompte,page,size);
		            model.addAttribute("compte",cp);
		            model.addAttribute("listOperations",listOperations);
		            int[] pages = new int[listOperations.getTotalPages()];
		            model.addAttribute("pages",pages);
		            model.addAttribute("codeCompte",codeCompte);
		        }catch (Exception e){
		            model.addAttribute("exception","Compte introuvable");
		        }

		        return "comptes";
		    }
		    @RequestMapping(value="/save_op" ,method = RequestMethod.POST )
		    public String saveOperation(Model model ,  String typeOperation , String codeCompte , double montant , String codeCompte2){
		      try{
		          if(typeOperation.equals("VERS")){
		        	  iBankMetier.verser(codeCompte,montant);
		          }        if(typeOperation.equals("RETR")){
		        	  System.out.println("REt");
		        	  iBankMetier.reterait(codeCompte,montant);
		          }  if(typeOperation.equals("VIR")){
		        	  iBankMetier.verement(codeCompte,codeCompte2,montant);
		          }
		      }catch (Exception e){
		          model.addAttribute("exception",e);
		          return "redirect:/consulterCompte?codeCompte="+codeCompte+"&error="+e.getMessage();
		      }

		        return "redirect:/consulterCompte?codeCompte="+codeCompte;
		    }
		    @RequestMapping(value = "/")
		    public String home(){
		        return "redirect:operations";
		    }

}
