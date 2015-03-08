/*
	Class: RestaurantRunner.java
	Author: Andy Yang, John Kang, Stephen Chan, Victor Duan
	Date: January 10, 2014
	School: A.Y. Jackson Secondary School
	Purpose: This is the main method of the whole program. The starting menu
				with all the different options will be selected here. The
				different options will be used to perform different tasks 
				selected by the user.
				
*/

import java.util.*;
import java.io.*;

public class RestaurantRunner {
   public static void main (String[] args) {
  
      //General variables to store user's choice from menu
      int input, subOption;
      
      //Varibles for case 5 (Used to order ingredients from suppliers)
      String supplierName, ingredient; 
      double amountWanted;
      
      //Variables for case 6 (Used for searching either dish / ingredient)
      String dishChoice;
      String ingredientChoice;
      
      //Variables for case 7 (Used to create a new dish)
      String newDish;
      int numIngredients;
      double amountNeeded;
      
      //Variables to keep track and help determine if user wishes to exit loop
      boolean exitLoop = false;
      String expectionCheck; 
      
      //Constant key to exit the program
      final char EXIT_KEYWORD = 'q';
      
      //Constant text file names
      final String SUPPLIERFILE = "Text Files/Supplier.txt";
		final String DISHFILE = "Text Files/Dishes.txt";
		final String DRAWERFILE = "Text Files/Drawers.txt";
		final String BUDGETFILE = "Text Files/Budget.txt";
      
      Scanner sc = new Scanner (System.in);
      
      RestaurantDatabase database = new RestaurantDatabase();
      
      //Load all text files (Dishes, Ingredients, Suppliers)
      database.loadDishes(DISHFILE);
      database.loadInventory(DRAWERFILE);
      database.loadSuppliers(SUPPLIERFILE);
		database.loadBudget(BUDGETFILE);
            
      while (!exitLoop) {
      
         //Display menu
         displayMenu();
      
         try {
            //Asks for the choice
            System.out.print ("\nEnter your choice: ");
            input = sc.nextInt();
            
            //Checks if the integer entered is a valid number (within the menu options)
            while (input <= 0 && input >= 9) {
               System.out.println ("Invalid input. Please choose an integer between 1-7.\n");
            }
            
            switch (input) {
               case 1:
                  //Asks user if he/she wishes to save or load text file
                  System.out.println ("\n1) Save text file");
                  System.out.println ("2) Load text file");
                                   
                  System.out.print ("\nEnter your choice: ");
                  do {   
                     input = sc.nextInt();
                  } while (!(input >= 1 && input <= 2));
                  
                  //Display submenu
                  textFileMenu();
                  
                  //Asks user to enter which text file they wish to save / load
                  System.out.print ("\nEnter your choice: ");
                  do {
                     subOption = sc.nextInt();
                  } while (!(subOption >= 1 && subOption <= 4));
                  
                  //Checks if the user chooses to save or load
                  //Then, checks which file to save or load and calls the required method
                  if (input == 1) {
                     if (subOption == 1) {
                        database.saveDishes(DISHFILE);
                     } else if (subOption == 2) {
                        database.saveInventory(DRAWERFILE);
                     } else if (subOption == 3) {
                        database.saveSuppliers(SUPPLIERFILE);
                     } else if (subOption == 4) {
                        database.saveBudget(BUDGETFILE);
                     }
                  } else if (input == 2){
                     if (subOption == 1) {
                        database.loadDishes(DISHFILE);
                     } else if (subOption == 2) {
                        database.loadInventory(DRAWERFILE);
                     } else if (subOption == 3) {
                        database.loadSuppliers(SUPPLIERFILE);
                     } else if (subOption == 4) {
                        database.loadBudget(BUDGETFILE);
                     }
                  }
               
                  break;
               case 2:
                  //Display submenu
                  textFileMenu();
                  
                  //Asks user to enter a choice from submenu
                  System.out.print ("\nEnter your choice: ");
                  do {
                     subOption = sc.nextInt();
                  } while (!(subOption >= 1 && subOption <= 3));
                  
                  //Runs the approcriate method according to the user's choice
                  if (subOption == 1) {
                     database.printAllDishes();
                  } else if (subOption == 2) {
                     database.printAllIngredients();
                  } else if (subOption == 3) {
                     database.printAllSuppliers();
                  }
                  break;
               case 3: 
                  //Prints out the dishes menu
                  database.printAllDishes();
               
                  //Flush  
                  sc.nextLine();
               
                  //Asks the user to enter the name of the dish that they wish to cook
                  System.out.print ("\nEnter your choice: ");
                  dishChoice = sc.nextLine();
               
                  database.cookDish(dishChoice);
                  break;
               case 4: 
                  //Prints out supplier text file
                  database.printAllSuppliers();
                  
                  //Flush
                  sc.nextLine();
                  
                  //Asks for the supplier, ingredient and amount that needs to be purchased
                  System.out.print ("\nSupplier to buy ingredient from: ");
                  supplierName = sc.nextLine();
                  
                  System.out.print ("\nPurchase the following ingredient: ");
                  ingredient = sc.nextLine();
                  
                  System.out.print ("\nEnter amount to purchase: ");
                  amountWanted = sc.nextDouble();
						//System.out.println("amountGathered");
                  
                  if (database.buyIngredient(supplierName, ingredient, amountWanted)) {
							System.out.println("Purchase Successful!");
						}
						else {
							System.out.println("Sorry, purchase unsuccessful");
						}
                  break;
						
               case 5:
                  //Call method to print account balance
                  database.printBudget();
                  break;
               case 6: 
                  //Display submenu for searching / sorting
                  displaySearchSortMenu();
                  
                  //Asks user for the choice and calls approcriate method
                  System.out.println ("\nEnter your choice: ");
                  do {
                     subOption = sc.nextInt();
                  } while (!(subOption >= 1 && subOption <= 4));
                  
                  //According to the option chosen, prints the approcriate text file and run the method
                  if (subOption == 1) {
                     database.printAllDishes();
                     
                     sc.nextLine();
                     
                     System.out.print ("\nEnter a dish to search for: ");
                     dishChoice = sc.nextLine();
                     
                     database.searchDishByName(dishChoice);
                  } else if (subOption == 2) {
                     database.printAllIngredients();
                     
                     sc.nextLine();
                     
                     System.out.print ("\nEnter an ingredient to search for: ");
                     ingredientChoice = sc.nextLine();
                     
                     database.searchIngredientByName (ingredientChoice);
                  } else if (subOption == 3) {
                     database.sortDishesAlpha();
                  } else if (subOption == 4) {
                     database.sortIngredientsAlpha();
                  }
                  break;
               case 7:
                  sc.nextLine();
               
                  System.out.print ("\nEnter the name of the dish: ");
                  newDish = sc.nextLine();
                  
                  System.out.print ("Number of ingredients needed [Ingredients must already be in the inventory]: ");
                  numIngredients = Integer.parseInt(sc.nextLine());
                  
                  //COMPLETE THIS CASE
               
                  break;
               case 8:
                  //Asks user for the # of dishes that should be printed in the ordered history
                  System.out.print ("\nNumber of recently ordered dishes that should be printed [0-9]: ");
                  input = sc.nextInt();
                  
                  //Checks if users entered a valid input, between 0-9
                  //If the input is invalid, keeps asking the user to enter a valid input
                  while (input < 0 && input > 9) {
                     System.out.print ("\nInvalid input! Enter a digit between 0-9: ");
                     input = sc.nextInt();
                  }
                  
                  //Calls the method to print out recently ordered dish
                  database.printMostRecent(input);
            }
            
         } catch (InputMismatchException imx) {
            expectionCheck = sc.nextLine();
         
            //Checks if the user has entered the letter 'q' or 'Q' to exit the loop
            //If so, change the boolean to exit out of the loop   
            if (expectionCheck.toLowerCase().charAt(0) == EXIT_KEYWORD) {
               exitLoop = true;
            } else {
               //Otherwise, ask the user to re-enter an input
               System.out.println ("You have entered an invalid input! Please enter an integer.\n");
            }
         }
         
      }
     
   }
   
   //Method to display the main menu
   public static void displayMenu () {
      System.out.println ("Main menu options:");
      System.out.println ("1) Save / load text files [Dishes / Ingredients / Suppliers / Budget]");
      System.out.println ("2) Print text files [Dishes / Ingredients / Suppliers]");
      System.out.println ("3) Cook dish");
      System.out.println ("4) Order ingredient");
      System.out.println ("5) Check account balance");
      System.out.println ("6) Search / sort for dishes or ingredients");
      System.out.println ("7) Add a dish");
      System.out.println ("8) Print out recently ordered dishes");
      System.out.println ();
      System.out.println ("Press 'q' or 'Q' to exit the program");
   }
   
   //Method to display submenu for option 1 (save / load text files)
   public static void textFileMenu () {
      System.out.println ("\n1) Dishes text file");
      System.out.println ("2) Ingredients text file");
      System.out.println ("3) Suppliers text file");
   }
   
   //Method to display search & sort menu for dishes and ingredients
   public static void displaySearchSortMenu () {
      System.out.println ("\n1) Search for a dish");
      System.out.println ("2) Search for an ingredient");
      System.out.println ("3) Sort the dish text file alphabetically");
      System.out.println ("4) Sort the ingredient text file alphabetically");
   }
   
}