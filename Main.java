/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package petstore;
import java.util.*;

/**
 *
 * @author issamalzouby
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System. in);
        PetStore ps = new PetStore("Issam's Pet Store");
        System.out.println("**** Welcome to " + ps.getStoreName() + "****");
        while (true) {

            System.out.println("Please select from one of the following menu otions");
            System.out.println("\t1. Buy a new pet");
            System.out.println("\t2. Register a new member");
            System.out.println("\t3. Start adoption drive (add new pets)");
            System.out.println("\t4. Check current inventory");
            System.out.println("\t5. Compare Pet Prices");
            System.out.println("\t6. Exit");

            int choice1 = scnr.nextInt();

            switch (choice1) {
                case 1:
                    //buy a pet
                    System.out.println("-----------------------------------");
                    purchase(ps, scnr, new ArrayList<Pet>());
                    break;
                case 2:
                    //register a new member
                    System.out.println("-----------------------------------");
                    registerNewUser(ps, scnr);
                    break;
                case 3:
                    //adoption drive
                    System.out.println("-----------------------------------");
                    startAdoption(ps,scnr,new ArrayList<Pet>());
                    break;
                case 4:
                    //check inventory value
                    System.out.println("-----------------------------------");
                    System.out.println("Total value is " + ps.inventoryValue());
                    System.out.println("-----------------------------------");
                    break;
                case 5:
                    //compare pet prices
                    System.out.println("-----------------------------------");
                    compareProducts(ps, scnr, new ArrayList<Pet>());
                    break;
                case 6:
                    //end
                    System.out.println("Thanks for visiting!");
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    //buy a pet
    private static void purchase(PetStore petStore, Scanner scnr, ArrayList<Pet> cart) {
        System.out.println("What type of pet are you here to purchase?");
        System.out.println("\t1. Any animal");

        int petTypeChoice = scnr.nextInt();

        // display inventory menu
        int itemNum = 1;
        ArrayList<Pet> update = new ArrayList();
      
        if (petTypeChoice == 1) {
            //inventory for all animal types
            ArrayList<Pet> inventory = petStore.getAvailablePets();
            if (!inventory.isEmpty()) {

                System.out.println("Which of the following Animals would you like to purchase?:");

                for (Pet pet : inventory) {
                    System.out.println(
                        "\t" + itemNum + ". $" + pet.getPrice() + " - " + pet.getBreed() + "(" + pet.getName() +
                        ")"
                    );
                    itemNum++;
                }
                // get user selection for product to add arraylist (cart)
                int choice = scnr.nextInt();
                if (choice <= inventory.size()) {
                    cart.add(inventory.get(choice - 1));
                    update.add(inventory.get(choice - 1));
                    //update inventory for this item
                    petStore.removePet("animal", choice - 1);
                    //cart summary
                    System.out.println(
                        "You have " + cart.size() + " items in your cart. Are you done shopping?"
                    );
                    System.out.println("\t1. Yes");
                    System.out.println("\t2. No");

                    int doneShopping = scnr.nextInt();
                    if (doneShopping == 1) {
                        System.out.println("TO DO - CHEKOUT ");
                        //uses adoption drive 
                        
                        
                        checkout(petStore, scnr, cart);
                    } else if (doneShopping == 2) { // more shopping
                        purchase(petStore, scnr, cart); // recursively call purchase(...) until done
                    } else {
                        System.out.println("Invalid Choice.");
                    }
                } else {
                    System.out.println("Please enter a valid product number. Try again");
                    purchase(petStore, scnr, cart);
                }
            } else {
                System.out.println("Sorry! we currently have no Animals available.");
            }

        }
    }
    
    
    //checkout and find total
    private static void checkout(PetStore petStore, Scanner scnr, ArrayList<Pet> cart) {
        // calculate total
        double total = 0;
      
        for (Pet pet : cart) {
            total += pet.getPrice();
        }
        System.out.println("-----------------------------------");
        System.out.println(
            "Your total comes to $" + total + ". \nPlease select which member is making this" +
            " purchase."
        );
        System.out.println("-----------------------------------");

       
        int num = 1;
      

        // loop for parent class to contain both premiuim and regular
        for (RegularMember user : petStore.getUserList()) {
            System.out.println(num + ". " + user.getName());
            num++;
        }
        
       
        System.out.println(num + ". Register a new Member.");

        System.out.println(""); // space line
        int memberSelect = scnr.nextInt();
        Member purchaser = null;
        PremiumMember premiumPurchaser = null;

        if (memberSelect > petStore.getUserList().size() + petStore.getPremiumUserList().size() + 1) { // invalid selection
            System.out.println("Invalid Selection");
            checkout(petStore, scnr, cart); // recursive call if valid user input

        } else { // valid selection
            if (memberSelect == (petStore.getUserList().size() + petStore.getPremiumUserList().size()) + 1) { // adding a new member
                boolean premium = registerNewUser(petStore, scnr);
                if (premium) {
                    premiumPurchaser = petStore.getPremiumUserList().get(
                        petStore.getPremiumUserList().size() - 1
                    );
                } else {
                    purchaser = petStore.getUserList().get(petStore.getUserList().size() - 1);
                }
            } else if (memberSelect <= (petStore.getUserList().size())) {
                purchaser = petStore.getUserList().get(memberSelect - 1);
            } else { // existing premium member
                premiumPurchaser = petStore.getPremiumUserList().get(
                    memberSelect - petStore.getUserList().size() - 1
                );
            }

            // check if premium member and fees are due
            if (purchaser == null && premiumPurchaser != null) {
                if( !premiumPurchaser.isDuesPaid()){
                System.out.println(
                    "Premium Membership dues unpaid, $5 will be added to purchase total to cover du" +
                    "es."
                );
                total += 5;
                }
                premiumPurchaser.setDuesPaid(true);
                // update amount of purchases for this member
                premiumPurchaser.setMoneySpent(total);
                // done
                System.out.println("-----------------------------------");
                System.out.println("Your purchase total was: $" + total);
                System.out.println(
                    "Congrats on your purchase " + premiumPurchaser.getName()
                );
                System.out.println("-----------------------------------");

            } else {
                // update amount of purchases for this member
                purchaser.setMoneySpent();
                System.out.println("-----------------------------------");
                System.out.println("Your purchase total was: $" + total);
                System.out.println("Congrats on your purchase " + purchaser.getName());
                System.out.println("-----------------------------------");

            }
        }
    }

    //registering new member
    private static boolean registerNewUser(PetStore petStore, Scanner scnr) {
        // prompt user to select membership type
        System.out.println("Let's get you registered as a new Member!");
        System.out.println( "Would you like to register as a free-tier or premium member?");
        System.out.println("\t1. Free-tier");
        System.out.println("\t2. Premium");

        // user selection
        int choice1 = scnr.nextInt();
        // if user selects a wrong choice 
        if (choice1 > 2 || choice1 < 1) {
            System.out.println("Invalid choice.");
            registerNewUser(petStore, scnr); // try again

        } else {
            // prompt user for name 
            System.out.println("Please enter your name:");
            scnr.nextLine();
            String name = scnr.next();
             System.out.println("Welcome to our membership program! Thank you for registering.");
            if (choice1 == 1) { // regular membership
                petStore.addNewUser(name, false);
                return false;
            } else { // user entered 2 - premimum membership
                petStore.addNewUser(name, true);
                return true;
            }
        }
        return false;
    }

    public static void startAdoption(PetStore petStore, Scanner scnr, ArrayList<Pet> pet){
        PetStore ps = petStore;
        //Scanner scnr = scnr;
        ArrayList<Pet> update = pet;
        
        //different fields of the new pet 
        System.out.println("What type of animal is it?");
        System.out.println("1) Dog \n2) Cat\n3)Exotic Pet");
        int type = scnr.nextInt();
        System.out.println("What is your pet's name?");
        String name= scnr.next();
        System.out.println("What is your pet's breed?");
        String breed= scnr.next();
        System.out.println("What is your pet's gender?");
        String sex = scnr.next();
        System.out.println("What is your pet's age?");
        int age = scnr.nextInt();
        System.out.println("What is your pet's weight?");
        double weight = scnr.nextDouble();

        //dog
        if(type == 1) {
            Dog newDog = new Dog(name,breed,sex,age,weight,ps.getNextPetID(),75);
            update.add(newDog);
            
        //cat
        } else if(type == 2) {
            Cat newCat = new Cat(name,breed,sex,age,weight,ps.getNextPetID(),75);
            update.add(newCat);
        //exotic pet
        } else if(type == 3) {
            ExoticPet newExoticPet = new ExoticPet(name,breed,sex,age,weight,ps.getNextPetID(),75);
            update.add(newExoticPet);
        }

        //basic question step
        System.out.println("Are we finished?");
        System.out.println("1) Yes exit \n2) Add more to adoption");
        int exit = scnr.nextInt();

        //exit options
        if (exit == 1) {
            System.out.println("TO DO - Exit?");
            ps.adoptionDrive(update);
        } else if (exit == 2) { 
            startAdoption(ps, scnr, update); 
        } else {
            System.out.println("Invalid Choice.");
        }
    
    }

    //compareProducts
    public static void compareProducts(PetStore ps, Scanner scnr, ArrayList<Pet> pet) {
        ArrayList<Pet> inventory = ps.getAvailablePets();
        System.out.println("Select animal  to compare");
        int itemNum = 1;
        
        
        for (Pet pets : inventory) {
            System.out.println(
                "\t" + itemNum + ". $" + pets.getPrice() + " - " + pets.getBreed() + "(" + pets.getName() +
                ")"
            );
            itemNum++;
        }
        
        
        itemNum = 1;
      
        int choice = scnr.nextInt();
        System.out.println("Select second animal to compare");
     
        for (Pet pets : inventory) {
            System.out.println(
                "\t" + itemNum + ". $" + pets.getPrice() + " - " + pets.getBreed() + "(" + pets.getName() +
                ")"
            );
            itemNum++;
        }
        int choice2 = scnr.nextInt();
        //compare the first and second
        Pet first = ps.getAvailablePets().get(choice - 1);
        Pet second =ps.getAvailablePets().get(choice2 - 1);
        System.out.println("----------------------------------- \nOutcome 1 means that the first animal is more expensive. \nOutcome -1 means that the second animal is more expensive. \nOutcome 0 means that the animals are the same.");
        System.out.println(first.compareTo(second));
        System.out.println("-----------------------------------");
    }
    
}
