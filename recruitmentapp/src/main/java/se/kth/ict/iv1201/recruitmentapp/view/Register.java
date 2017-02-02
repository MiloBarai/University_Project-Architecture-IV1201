/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.ict.iv1201.recruitmentapp.view;

/**
 *
 * @author masaralmosawi
 */


public class Register {
    private String name;
    private String surname;
    private String email;
    private int dateb;
    
    
  
  public Register() {
      
  }  

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public int getDateb() {
        return dateb;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateb(int dateb) {
        this.dateb = dateb;
    }
    
    public String add()
    {
        System.out.println("Thank you for joinibng us ");
        System.out.println(name +"  "+ surname +"  "+ email);
        return"success";
    }
 
  
}
