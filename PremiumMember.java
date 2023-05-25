/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petstore;
import java.util.*;

/**
 *
 * @author issamalzouby
 */
public class PremiumMember extends Member {
    
       private ArrayList<Dog> dogsOwned = new ArrayList();
    private ArrayList<Cat> catsOwned = new ArrayList();
    private ArrayList<ExoticPet> exoticPetsOwned = new ArrayList();
    private boolean duesPaid;
    private double moneySpent = 0;

    public PremiumMember(String name, int userID, boolean duesPaid) {
        //super the methods from parent
        super(name, userID);
        this.duesPaid = duesPaid;
    }

    //dues get
    public boolean isDuesPaid() {return duesPaid;}

    //dues set
    public void setDuesPaid(boolean duesPaid) {this.duesPaid = duesPaid;}

    //get dog
    public ArrayList<Dog> getDogsOwned() {return dogsOwned;}

    //get cat
    public ArrayList<Cat> getCatsOwned() {return catsOwned;}

    //get exotic
    public ArrayList<ExoticPet> getExoticPetsOwned() {return exoticPetsOwned;}

    //get amountspent
    public double getMoneySpent() {return moneySpent;}

    //set amount spent
    public void setMoneySpent(double moneySpent) {this.moneySpent = this.moneySpent + moneySpent;}     
    //set dog
     public void addDog(Dog dog) {dogsOwned.add(dog);}
     //set cat
     public void addCat(Cat cat) {catsOwned.add(cat);}
     //set exotic
     public void addExoticPet(ExoticPet exoticPet) {exoticPetsOwned.add(exoticPet);}
    
    
    
    
}
