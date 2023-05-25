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
public class PetStore implements PetStoreSpecifications {
    
    private String storeName;
    private ArrayList<Pet> availablePets = new ArrayList();
    private ArrayList<Dog> availableDogs = new ArrayList();
    private ArrayList<Cat> availableCats = new ArrayList();
    private ArrayList<ExoticPet> availableExoticPets = new ArrayList();
    private ArrayList<RegularMember> userList = new ArrayList();
    private ArrayList<PremiumMember> premiumUserList = new ArrayList();
    private static int nextPetID = 1;
    private static int nextUserID = 1;
    
    
    public PetStore(String storeName) {
        this.storeName = storeName;
        Dog dog1 = new Dog(
            "Waffle",
            "German Shepherd",
            "Female",
            12,
            85,
            getNextPetID(),
            500
        );
        Dog dog2 = new Dog("Poe", "Corgi", "Male", 3, 35, getNextPetID(), 450);
        Cat cat1 = new Cat(
            "Karma",
            "Orange Tabby",
            "Female",
            6,
            15,
            getNextPetID(),
            200
        );
        Cat cat2 = new Cat("Kit", "Maine Coon", "Male", 6, 18, getNextPetID(), 150);
        ExoticPet ep1 = new ExoticPet(
            "Pancake",
            "Bearded Dragon",
            "Male",
            6,
            0.8,
            getNextPetID(),
            75
        );
        ExoticPet ep2 = new ExoticPet(
            "Noodle",
            "Ball Python",
            "Male",
            4,
            2,
            getNextPetID(),
            95
        );

        availableDogs.add(dog1);
        availableDogs.add(dog2);
        availableCats.add(cat1);
        availableCats.add(cat2);
        availableExoticPets.add(ep1);
        availableExoticPets.add(ep2);
        allPets();
        
        RegularMember user1 = new RegularMember("Jo", getNextUserID());
        user1.addCat(new Cat("Valjean", "White tabby", "Male", 1, 10, 0, 0));
        userList.add(user1);

        PremiumMember user2 = new PremiumMember("Sage", getNextUserID(), false);
        premiumUserList.add(user2);
        user2.addExoticPet(
            new ExoticPet("Smaug", "Bearded Dragon", "Male", 5, 1, 0, 0)
        );

    }
        
        public void allPets() {
        for (Pet pet : availableDogs) {
            availablePets.add(pet);
        }
        for (Pet pet : availableCats) {
            availablePets.add(pet);
        }
        for (Pet pet : availableExoticPets) {
            availablePets.add(pet);
        }
    }
        
        // getter for user and pet ID

    public static int getNextPetID() {
        nextPetID++;
        return nextPetID - 1;
    }
    
    public static int getNextUserID() {
        nextUserID++;
        return nextUserID - 1;
    }

    //getter and setter for sttore name

    public String getStoreName() {return storeName;}

    public void setStoreName(String storeName) {this.storeName = storeName;}
    
    //getter and setter for dogs available

    public ArrayList<Dog> getAvailableDogs() {return availableDogs;}

    public void setAvailableDogs(ArrayList<Dog> availableDogs) {this.availableDogs = availableDogs;}
    
    //getter and setter for available cats

    public ArrayList<Cat> getAvailableCats() {return availableCats;}

    public void setAvailableCats(ArrayList<Cat> availableCats) {this.availableCats = availableCats;}
    
    //getter and setter for exotic pets available

    public ArrayList<ExoticPet> getAvailableExoticPets() {return availableExoticPets;}

    public void setAvailableExoticPets(ArrayList<ExoticPet> availableExoticPets) {this.availableExoticPets = availableExoticPets;}
    
    //getter and setter fot UserList

    public ArrayList<RegularMember> getUserList() {return userList;}

    public void setUserList(ArrayList<RegularMember> userList) {this.userList = userList;}
    
    //setter and getter for premium list

    public ArrayList<PremiumMember> getPremiumUserList() {return premiumUserList;}

    public void setPremiumUserList(ArrayList<PremiumMember> premiumUserList) {this.premiumUserList = premiumUserList;}
    
    //removing pet
    

    public void removePet(String type, int index) {
        if (type.equals("dog")) 
            availableDogs.remove(index);
        else if (type.equals("cat")) 
            availableCats.remove(index);
        else if (type.equals("exoticPet")) 
            availableExoticPets.remove(index);
          else if (type.equals("Animal")) 
            availablePets.remove(index); 

    }
    

    //adding new user
    
     public void addNewUser(String name, boolean premium) {
        if (premium) {
            premiumUserList.add(new PremiumMember(name,getNextUserID(), true));
        } else {
            userList.add(new RegularMember(name,getNextUserID()));
        }
    }
        public ArrayList<Pet> getAvailablePets() {
            return availablePets;
        }

        public void setAvailableAnimals(ArrayList<Pet> update) {
            availablePets = update;
        }


    //ovveride the method from petstorespecification
    @Override
    public void adoptionDrive(ArrayList<Pet> pets) {
        for (Pet pet : pets) {
            availablePets.add(pet);
        }

    }

    //overide method from inventoryvalue
    @Override
    public double inventoryValue() {
        double value = 0.0;
        for (int i = 0; i < availablePets.size(); i++) {
            value= value + availablePets.get(i).getPrice();
        }
        return value;
    }
        
    
    
    
}
