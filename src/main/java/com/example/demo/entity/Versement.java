package com.example.demo.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


 


import lombok.Data;


@Entity
@DiscriminatorValue("V")
@Data
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="codeClient")

public class Versement extends Operation {

	
	
	
	 public Versement() {
	        super();
	    }

	    public Versement(Date dateOperation, double montant, Compte compte) {
	        super(dateOperation, montant, compte);
	    }
}
