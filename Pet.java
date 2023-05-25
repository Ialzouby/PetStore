/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petstore;

/**
 *
 * @author issamalzouby
 */

//superclass

public abstract class Pet implements Comparable<Pet> {
    
    private String name; 
    private String breed;
    private String sex; 
    private int age; 
    private double weight; 
    private int ID; 
    private double price; 
    
    //constructor for pet
    
    public Pet(String name, String breed, String sex, int age, double weight, int ID, double price)
    {
        this.name = name; 
        this.breed = breed; 
        this.sex = sex; 
        this.age = age; 
        this.weight = weight; 
        this.ID = ID; 
        this.price = price; 
    }
    
    
    //getter and setter for name
    
    public String getName() {return name;}
    
    public void setName(String name){this.name = name;}
    
    //getter and setter for breed
    
    public String getBreed(){return breed;}
    
    public void setBreed(String breed){this.breed = breed;}
    
    //getter and setter for sex
    
    public String getSex(){return sex;}
    
    public void setSex(String sex){this.sex = sex;}
    
     //getter and setter for age
    
    public int getAge(){return age;}
    
    public void setAge(int age){this.age = age;}
    
     //getter and setter for weight
    
    public double getWeight(){return weight;}
    
    public void setWeight(double weight){this.weight = weight;}
    
    //getter and setter for ID
    public int getID(){return ID;}
    
    public void setID(int ID){this.ID = ID;}
    
    //getter and setter for weight
    
    public double getPrice(){return price;}
    
    public void setPrice(double price){this.price = price;}
    
    
    @Override
    public int compareTo(Pet pet)
    {
        return Double.compare(this.price, pet.price);
    }
    
   
    
    
    
}
