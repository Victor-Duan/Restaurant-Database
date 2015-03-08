/*
	Class: Inventory.java
	Author: Andy Yang, John Kang, Stephen Chan, Victor Duan
	Date: January 10, 2014
	School: A.Y. Jackson Secondary School
	Purpose: This is the inventory class and it is used to store a list of 
				Drawers. You can use the inventory class to check the amount 
				each ingredient left and to look up specific ingredients.
*/

   public class Inventory{
      private Drawer [] ingredientList;
   
   	//Constructor
      public Inventory (Drawer[] list){
         ingredientList = list ;
      }
   	
   	//Accessor
      public Drawer[] getIngredientList(){
         return ingredientList;
      }
   
   	//Mutator
      public void setIngredientList(Drawer[] list){
         ingredientList = list;
      }
   	
		//Adds an amount to a specified ingredient
      public boolean addIngredient(String name, double amount) {
         int foundIndex = searchIngredientByName(name);
      	
         if (foundIndex != RestaurantDatabase.getERROR()) {
            ingredientList[foundIndex].addAmount(amount);
            return true;
         }
         return false;
      }
   	
   	//given a list of Drawers, will identify whether or not there are sufficient ingredients to make the dish
      public boolean checkInventory(Drawer[] list){
         int counter = 0;
         for (int i = 0; i < list.length; i++){
            for (int j = 0; j < ingredientList.length && ingredientList[j] != null; j++){
               if (list[i].getStoredIngredient().getName().equals(ingredientList[j].getStoredIngredient().getName())){
                  if (list[i].getAmountLeft() <= ingredientList[j].getAmountLeft()){
                     counter++;
                  }
               }
            }
         }
         return (counter ==list.length);
      }
   	
   	//search for the ingredient entered by its name
   	//returns index of ingredient, -1 if not in the array
      public int searchIngredientByName(String name) {
         int counter = 0;
         while (counter < ingredientList.length) {
            if (name.compareTo(ingredientList[counter].getStoredIngredient().getName()) == 0) {
               return counter;
            }
				counter++;
         }
         return RestaurantDatabase.getERROR();
      }
   	
		//toString method to output the fields in an orderly fashion
      public String toString(){
         String list="";
         for (int i = 0; i< ingredientList.length; i++){
            if (ingredientList[i] != null) {
               list += ingredientList[i];
            }
         }
         return list;
      }
   }