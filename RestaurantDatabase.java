/*
	Class: RestaurantDatabase.java
	Author: Andy Yang, John Kang, Stephen Chan, Victor Duan
	Date: January 10, 2014
	School: A.Y. Jackson Secondary School
	Purpose: This class is responsible for changing and viewing the inventory, dishes and budget.
			   Main functions of this class include
					-ability to buy ingredients given a supplier name, ingredient name and amount
					-keeps track of the 10 most recently cooked dishes and prints them out
					-cook a dish given a name and adjusts the inventory amounts and budget
					-searching for a dish/ingredient/supplier by name
					-sorting ingredients/dishes by alphabet
					-read and load budget/dishes/ingredients/suppliers from a text file
					
*/
	
   import java.io.*;
	
   public class RestaurantDatabase {
   	
		//NUMTRACKRECENTLY - constant saying how many recently cooked dishes to keep track of
		//ERROR - return value for search methods if the name is not found in the array
		//EMPTYINDICES - number of blank objects so that more can be added	
		//					- workaround to the lack of knowledge about dynamic arrays
      final private static int NUMTRACKRECENTLY = 10;
      private final static int ERROR = -1;
      final private static int EMPTYINDICES = 10;
   	
		//dishList is the list of Dish objects
		//recentlyMade is the list of recently made dishes
		//storage is representative of the inventory of the restaurant
		//supplierList is a list of the suppliers
		//budget is budget LOL dont know what else there is to say
      private Dish[] dishList;
      private Dish[] recentlyMade = new Dish[NUMTRACKRECENTLY];
      private Inventory storage;
      private Supplier[] supplierList;
      private Budget budget;
   	
		//static method to get the value of the ERROR value if needed
      public static int getERROR() {
         return ERROR;
      }
   
   //read in the ingredient text file and creates an inventory object
      public void loadInventory(String fileName) {
         try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String ingredientType;
            String ingredientName;
            String measurement;
            double amount;
         	
				//read in the number of ingredients to be in the array
            int numIngredients = Integer.parseInt(in.readLine());
            in.readLine();
         	
				//creates an array with EMPTYINDICES more indicies than needed
            Drawer[] ingredientDrawers = new Drawer[numIngredients + EMPTYINDICES];
         	
				//loops to read ingredient info and create the object
            for (int i = 0; i < numIngredients; i++) {
               ingredientType = in.readLine();
               ingredientName = in.readLine();
               measurement = in.readLine();
               amount = Integer.parseInt(in.readLine());
            	
               if (ingredientType.compareTo("grain") == 0) {
                  ingredientDrawers[i] = new Drawer (amount, new Grain(ingredientName, measurement));
               }
               else if (ingredientType.compareTo("vegetable") == 0) {
                  ingredientDrawers[i] = new Drawer (amount, new Vegetable(ingredientName, measurement));
               }
               else if (ingredientType.compareTo("fruit") == 0) {
                  ingredientDrawers[i] = new Drawer (amount, new Fruit(ingredientName, measurement));
               }
               else if (ingredientType.compareTo("meat") == 0) {
                  ingredientDrawers[i] = new Drawer (amount, new Meat(ingredientName, measurement));
               }
               else if (ingredientType.compareTo("dairy") == 0) {
                  ingredientDrawers[i] = new Drawer (amount, new Dairy(ingredientName, measurement));
               }
               else if (ingredientType.compareTo("seafood") == 0) {
                  ingredientDrawers[i] = new Drawer (amount, new Seafood(ingredientName, measurement));
               }
               else if (ingredientType.compareTo("spice") == 0) {
                  ingredientDrawers[i] = new Drawer (amount, new Spice(ingredientName, measurement));
               }
               in.readLine();
            	
            }
         	
				//creates the Inventory object
            storage = new Inventory(ingredientDrawers);
            in.close();
         }
            catch (IOException iox ) {
               System.out.println("Error finding file");
            }
      }
   
   //loads an array of dishes from the text file
      public void loadDishes(String fileName) {
         try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
         	//variables that store the values to be loaded into the array
            int numDishes;
            int numIngredients;
         	
            String dishName;
            String dishType;
            boolean alcoholic = false;
            double dishCost;
         	
            String ingredientName;
            String unitAmount;
            double amountNeeded;
            Drawer[] ingredientList;
         	
         	//begin to read information from the file
         	//reads the number of dishes and creates an array of that size
            numDishes = Integer.parseInt(in.readLine());
            in.readLine();
            dishList = new Dish[numDishes];
         	
				//loops through each dish
            for (int i = 0; i < numDishes; i++) {

               dishName = in.readLine();
               dishType = in.readLine();
            
               if (dishType.compareTo("Drink") == 0) {
                  alcoholic = Boolean.parseBoolean(in.readLine());
               }
            	
               dishCost = Double.parseDouble(in.readLine());
               numIngredients = Integer.parseInt(in.readLine());
               ingredientList = new Drawer[numIngredients];
            	
					//loops through each ingredient in the list
               for (int j = 0; j < numIngredients; j++) {
                  ingredientName = in.readLine();
                  unitAmount = in.readLine();
                  amountNeeded = Double.parseDouble(in.readLine());
                  ingredientList[j] = new Drawer(amountNeeded, new Ingredient(ingredientName, unitAmount));
               }
            	
					//creates the proper child of Dish
               if (dishType.compareTo("Appetizer") == 0) {
                  dishList[i] = new Appetizer(dishName, ingredientList, i, dishCost);
               }
               else if (dishType.compareTo("Entree") == 0) {
                  dishList[i] = new Entree(dishName, ingredientList, i, dishCost);
               }
               else if (dishType.compareTo("Dessert") == 0) {
                  dishList[i] = new Dessert(dishName, ingredientList, i, dishCost);
               }
               else if (dishType.compareTo("Drink") == 0) {
                  dishList[i] = new Drink(alcoholic, dishName, ingredientList, i, dishCost);
               }
            	
               in.readLine();
            
            }
         	
            in.close();
         }
            catch (IOException iox){
               System.out.println("Error finding file");
            }
      }
   //loads suppliers from a text file
      public void loadSuppliers(String fileName) {
      	
         try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            int numSuppliers;
            int numProduce;
            String supplierName;
            String ingredientName;
            String unitMeasurement;
            double unitCost;
            Produce[] produceList;
         	
				//reads in number of suppliers
            numSuppliers = Integer.parseInt(in.readLine());
            in.readLine();
         	
            supplierList = new Supplier[numSuppliers];
         	
				//loops through the number of suppliers to create each new one
            for (int i = 0; i < numSuppliers; i++) {
            	
               supplierName = in.readLine();
               numProduce = Integer.parseInt(in.readLine());
               produceList = new Produce[numProduce];
            	
					//loops through to create each produce object that the one supplier has
               for (int j = 0; j < numProduce; j++) {
                  ingredientName = in.readLine();
                  unitMeasurement = in.readLine();
                  unitCost = Double.parseDouble(in.readLine());
                  produceList[j] = new Produce(new Ingredient(ingredientName, unitMeasurement), unitCost);
               }
            	
               supplierList[i] = new Supplier(supplierName, produceList);
               in.readLine();
            }
         	
            in.close();
         }
            catch (IOException iox) {
               System.out.println("Error reading file");
            }
      }
   	
   	//loads budget info from file
      public void loadBudget(String fileName) {
         try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
         	
            budget = new Budget(Double.parseDouble(in.readLine()));
         	
         }
            catch(IOException iox) {
               System.out.println("Error reading file");
            }
      }
   	
   	//write out all ingredients and amounts (the inventory) to a text file
      public void saveInventory(String fileName) {
         try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
         	int numIngredients;
				Drawer[] ingredientList;
				
				ingredientList = storage.getIngredientList();
				
				numIngredients = ingredientList.length;
				out.write(numIngredients + "");
				out.newLine();
				out.newLine();
				
				for (int i = 0; i < numIngredients && ingredientList[i] != null; i++) {
					if (ingredientList[i].getStoredIngredient() instanceof Grain) {
						out.write("grain");
					}
					else if (ingredientList[i].getStoredIngredient() instanceof Vegetable) {
						out.write("vegetable");
					}
					else if (ingredientList[i].getStoredIngredient() instanceof Fruit) {
						out.write("fruit");
					}
					else if (ingredientList[i].getStoredIngredient() instanceof Meat) {
						out.write("meat");
					}
					else if (ingredientList[i].getStoredIngredient() instanceof Dairy) {
						out.write("dairy");
					}
					else if (ingredientList[i].getStoredIngredient() instanceof Seafood) {
						out.write("seafood");
					}
					else if (ingredientList[i].getStoredIngredient() instanceof Spice) {
						out.write("spice");
					}


					out.newLine();
					out.write(ingredientList[i].getStoredIngredient().getName());
					out.newLine();
					out.write(ingredientList[i].getStoredIngredient().getUnitMeasurement());
					out.newLine();
					out.write(ingredientList[i].getAmountLeft() + "");
					out.newLine();
					out.newLine();
				}
				
				out.close();
         }
            catch (IOException iox) {
               System.out.println("Error saving to file");
            }
      }

   	
   	//write all dishes and ingredients to a text file
      public void saveDishes(String fileName) {
         try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
            int numIngredients;
         	
            out.write(dishList.length + "");
            out.newLine();
            out.newLine();
         	
				//loops through the array of dishes to write them out
				//must check for null because of how there can be empty indices
            for (int i = 0; i < dishList.length && dishList[i] != null; i++) {
               System.out.println(i);
               out.write(dishList[i].getName());
               out.newLine();
            	
               if (dishList[i] instanceof Appetizer) {
                  out.write("Appetizer");
               }
               else if (dishList[i] instanceof Entree) {
                  out.write("Entree");
               }
               else if (dishList[i] instanceof Dessert) {
                  out.write("Dessert");
               }
               else if (dishList[i] instanceof Drink) {
                  out.write("Drink");
                  out.newLine();
                  out.write(((Drink)dishList[i]).getAlcoholic() + "\n");
               }
               out.newLine();
            	
               out.write(dishList[i].getCost() + "");
               out.newLine();
               numIngredients = dishList[i].getListIngredients().length;
               out.write(numIngredients + "");
               out.newLine();
            	
               for (int j = 0; j < numIngredients; j++) {
                  out.write(dishList[i].getListIngredients()[j].getStoredIngredient().getName());
                  out.newLine();
                  out.write(dishList[i].getListIngredients()[j].getStoredIngredient().getUnitMeasurement());
                  out.newLine();
                  out.write(dishList[i].getListIngredients()[j].getAmountLeft() + "");
                  out.newLine();
               }
               out.newLine();
            }
         	
            out.close();
         }
            catch (IOException iox) {
               System.out.println("Error saving to file");
            }
      	
      }
   	
   	//write all suppliers to a text file
      public void saveSuppliers (String fileName) {
         try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
            int numProduce;
         	
            out.write(supplierList.length + "");
            out.newLine();
            out.newLine();
         	
            for (int i = 0; i < supplierList.length && supplierList[i] != null; i++) {
            	//writes out supplier name and number of produce the supplier offers
               out.write(supplierList[i].getName());
               out.newLine();
            	
               numProduce = supplierList[i].getProduceList().length;
            	
               out.write(numProduce + "");
               out.newLine();
            	
            	//writes out the individual items and their cost to the text file
               for (int j = 0; j < numProduce; j++) {
                  out.write(supplierList[i].getProduceList()[j].getIngred().getName());
                  out.newLine();
                  out.write(supplierList[i].getProduceList()[j].getIngred().getUnitMeasurement());
                  out.newLine();
                  out.write(supplierList[i].getProduceList()[j].getUnitCost() + "");
                  out.newLine();
               }
               out.newLine();
            }
         	
            out.close();
         }
         	
         
            catch (IOException iox) {
               System.out.println("Error saving to file");
            }
      	
      }
   	
   	//write budget information to a text file
      public void saveBudget(String fileName) {
         try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
            out.write(budget.getBalance() + "");
         }
            catch (IOException iox) {
               System.out.println("Error saving to file");
            }
      
      }
   //buys a certain amount of a certain ingredient from a certain supplier
      public boolean buyIngredient(String supplier, String ingreName, double amount) {
      
         Supplier produceSupplier = supplierList[findSupplierByName(supplier)];
      	
         double amountToPay = produceSupplier.buyProduce(ingreName, amount);
      	
			//check to see if the cost of ingredients can be covered by amount in budget
         if (amountToPay <= budget.getBalance()) {
            budget.removeRevenue(amountToPay);
         	//System.out.println("amonut deducted");
            storage.addIngredient(ingreName, amount);
            return true;
         }
         else {
            return false;
         }
      }
   
   //cooks a dish if all the ingredients are present
      public boolean cookDish(String dishName) {
         int currentDrawer;
         int currentDish;
         String curIngredientName;
         int curDrawer;
         Drawer[] inventoryDrawerList;
         Drawer[] ingredientList;
      	
         inventoryDrawerList = storage.getIngredientList();
         currentDish = searchDishByName(dishName);
         ingredientList = dishList[currentDish].getListIngredients();
      
      	//check to see if all ignredients are present
         if (storage.checkInventory(ingredientList)) {
            for (int i = 0; i < ingredientList.length; i++) {
               curIngredientName = ingredientList[i].getStoredIngredient().getName();
               curDrawer = storage.searchIngredientByName(curIngredientName);
            	
            	//deduct the amount of ingredient in the inventory by the needed amount
               inventoryDrawerList[curDrawer].deductAmount(ingredientList[i].getAmountLeft());
            }
         	
         	//add cost of dish to budget
            budget.addRevenue(dishList[currentDish].getCost());
         		
         	//refreshes list of recently made dishes
            for (int i = NUMTRACKRECENTLY - 1; i >= 1; i--) {
               recentlyMade[i] = recentlyMade[i-1];
            }
            recentlyMade[0] = dishList[currentDish];
            return true;
         }
         return false;
      }
   
   //search for the ingredient entered by its name
   //returns index of ingredient, -1 if not in the array
      public int searchIngredientByName(String name) {
         int counter = 0;
         while (counter < storage.getIngredientList().length) {
            if (name.compareTo(storage.getIngredientList()[counter].getStoredIngredient().getName()) == 0) {
               return counter;
            }
            counter++;
         }
         return -1;
      }
   	 
   
   //search for dish by name
   //return index of dish, -1 if it doesnt exist
      public int searchDishByName(String name) {
         int counter = 0;
         while (counter < dishList.length) {
            if (name.compareTo(dishList[counter].getName()) == 0) {
               return counter;
            }
            counter++;
         }
         return -1;
      
      }
   	
   	//finds the supplier by the given name
      public int findSupplierByName(String name) {
      	      
         int counter = 0;
         while (counter < supplierList.length) {
            if (name.compareTo(supplierList[counter].getName()) == 0) {
               return counter;
            }
            counter++;
         }
         return -1;
      
      }
   
   //sorts ingredients in the list alphabetically in ascending order
	//uses a standard selection sort to sort objects
      public void sortIngredientsAlpha() {
         int smallestIndex;
         Drawer tempValue;
         Drawer[] list = storage.getIngredientList();
         smallestIndex = 0;
      
         for (int i = 0; i < list.length; i++) {
            smallestIndex = i;
            for (int j = i; j < list.length; j++) {
               
               if (list[j] != null && list[smallestIndex] != null && list[j].getStoredIngredient().getName().compareTo(list[smallestIndex].getStoredIngredient().getName()) < 0) {
                  smallestIndex = j;
               }
            }
         	
            tempValue = list[smallestIndex];
            list[smallestIndex] = list[i];
            list[i] = tempValue;
         }
      }
   
   //sorts dishes in the array alphabetically in ascending order
	//uses a standard selection sort to sort objects
      public void sortDishesAlpha() {
         int smallestIndex = 0;
         Dish tempValue;
      
         for (int i = 0; i < dishList.length; i++) {
            smallestIndex = i;
         	
            for (int j = i; j < dishList.length; j++) {
               if (dishList[j].getName().compareTo(dishList[smallestIndex].getName()) < 0) {
                  smallestIndex = j;
               }
            }
         	
            tempValue = dishList[smallestIndex];
            dishList[smallestIndex] = dishList[i];
            dishList[i] = tempValue;
         }
      
      }
		
   //prints out the most recent dishes, the number to be printed out specified by 
      public void printMostRecent (int numEntries) {
         for (int i = 0; i < numEntries && recentlyMade[i] != null; i++) {
            System.out.println(recentlyMade[i]);
         }
      }
   
   //prints out all ingredients and their stored amounts
      public void printAllIngredients() {
         System.out.println(storage);
      }
   	
   //prints all dishes in the array
      public void printAllDishes() {
         for (int i = 0; i < dishList.length; i++) {
            System.out.println(dishList[i]);
         }
      }
   
   //prints the information of the dish specified by the user
      public void printSpecificDish(String name) {
         int specifiedDish = searchDishByName(name);
         if (specifiedDish >= 0) {
            System.out.println(dishList[specifiedDish]);
         }
         else {
            System.out.println("No dish by that name in memory");
         }
      }
   	
   //prints suppliers and their produce
      public void printAllSuppliers() {
         for (int i = 0; i < supplierList.length; i++) {
            System.out.println(supplierList[i]);
         }
      }
   
   //prints information about the budget
      public void printBudget() {
         System.out.println(budget);
      }
   }