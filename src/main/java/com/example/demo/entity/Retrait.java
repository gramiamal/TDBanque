package com.example.demo.entity;


import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;



import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@DiscriminatorValue("R")
@Data
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="codeClient")



public class Retrait extends Operation{
	 public Retrait() {
	        super();
	    }

	    public Retrait(Date dateOperation, double montant, Compte compte) {
	        super(dateOperation, montant, compte);
	    }

}
