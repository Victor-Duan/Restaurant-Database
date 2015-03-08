/*
	Class: Dish.java
	Author: Andy Yang, John Kang, Stephen Chan, Victor Duan
	Date: January 10, 2014
	School: A.Y. Jackson Secondary School
	Purpose: This class helps create a dish object and comapre the cost of 2 dishes. Also,
				it has a toString method to print the ingredients used to make the dish
*/

   abstract class Dish {
      
		//Variables for the dish class
		protected String name; 
      protected Drawer[] listIngredients;
      protected int menuItem;
      protected double cost;
   
		//Constructor for creating a dish object
      public Dish (String name, Drawer[] list, int menuNumber, double cost){
         this.name = name;
         listIngredients = list;
         this.menuItem = menuNumber;
         this.cost = cost;
      }
   	
		//Accessors	
		public String getName() {
			return name;
		}
		
		public Drawer[] getListIngredients() {
			return listIngredients;
		}
		
		public int getMenuItem() {
			return menuItem;
		}
		
		public double getCost() {
			return cost;
		}
		
		//Mutators
		public void setName(String newName) {
			name = newName;
		}
		
		public void setListIngredients(Drawer[] newListIngredients) {
			listIngredients = newListIngredients;
		}
		
		public void setMenuItem(int newMenuItem) {
			menuItem = newMenuItem;
		}
		
		public void setCost(double newCost) {
			cost = newCost;
		}
      
		//NOT SURE IF NEEDED
		/* lic boolean checkInventory(Drawer[] list){
      	return true;
      } */
   
		//Compare the cost of 2 dishes
      public double CompareToCost(Dish other){
         return(this.cost - other.cost);
      }
		
		//toString method
      public String toString(){
         String parameter1= "Name: " + name + "\n";
         String parameter2= "Menu Item: " + menuItem + "\n";
         String parameter3="Ingredients: "+"\n";
			
         for (int i =0; i < listIngredients.length; i++){
            parameter3 += "Ingredient Name: " + listIngredients[i].getStoredIngredient().getName() + "\n" 
				+ "Amount required: "+ listIngredients[i].getAmountLeft()+ listIngredients[i].getStoredIngredient().getUnitMeasurement() + "\n";
         }
         String parameter4= "Cost: $"+ cost + "\n";
         return (parameter1 + parameter2 + parameter3 + parameter4);
      }
   }