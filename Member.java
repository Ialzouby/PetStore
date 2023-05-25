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
public class Member {
    
    
    //var fields
    private String name; 
    private int userID;
    private ArrayList<Dog> dogsOwned = new ArrayList();
    private ArrayList<Cat> catsOwned = new ArrayList();
    private ArrayList<ExoticPet> exoticsOwned = new ArrayList();
    private double moneySpent = 0;
    
    //constructor for member
    
    public Member(String name, int userID)
    {
        this.name = name; 
        this.userID = userID;
    }
    
    //getter and setter for name 
    
    public String getName(){return name;}
    
    public void setName(String name){this.name = name;}
    
    //getter and setter for userID
    
    public int getUserID(){return userID;}
    
    public void setUserID(int userID){this.userID = userID;}
    
    //getters and setters for moneySpent
    
    public double getMoneySpent(){return moneySpent;}
    
    public void setMoneySpent(){this.moneySpent = this.moneySpent + moneySpent;}
    
    //getters for arraylists
    
    public ArrayList<Dog> getDogsOwned(){return dogsOwned;}
    
    public void addDog(Dog dog){dogsOwned.add(dog);}
    
    public ArrayList<Cat> getCatsOwned(){return catsOwned;}
    
    public void addCat(Cat cat){catsOwned.add(cat);}
    
    public ArrayList<ExoticPet> getExoticsOwned(){return exoticsOwned;}
    
    public void addExoticPet(ExoticPet exoticPet){exoticsOwned.add(exoticPet);}
    
}
