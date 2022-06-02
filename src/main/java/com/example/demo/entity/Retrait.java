package com.example.demo.entity;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("R")

public class Retrait extends Operation{

}
