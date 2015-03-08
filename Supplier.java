 /*
	Class: Supplier.java
	Author: Andy Yang, John Kang, Stephen Chan, Victor Duan
	Date: January 10, 2014
	School: A.Y. Jackson Secondary School
	Purpose: This is the the supplier class, it will be used when ingredients
			   are ordered in the main method.
	
*/
  
   public class Supplier {
      private String supplierName;
      private Produce[] produceList;
   
  	   //Constructor
      public Supplier(String supplierName, Produce[] produceList) {
         this.supplierName = supplierName;
         this.produceList = produceList;
      }
   	//Accessors
      public String getName() {
         return supplierName;
      }
   
      public Produce[] getProduceList() {
         return produceList;
      }
   
   	//Mutators
      public void setName(String name) {
         supplierName = name;
      }
   
      public void setProduceList(Produce[] produceList) {
         this.produceList = produceList;
      }
   
   	//Buys the desired produce 
      public double buyProduce(String produceName, double amountDesired) {
         int produceIndex = searchIngredientByName(produceName);
      	
         return (produceList[produceIndex].getUnitCost()) * amountDesired;
      }
   	
		//Searches the ingredient list to check for availability 
      private int searchIngredientByName(String name) {
         int counter = 0;
         while (counter < produceList.length) {
            if (name.compareTo(produceList[counter].getIngred().getName()) == 0) {
               return counter;
            }
            counter++;
         }
         return RestaurantDatabase.getERROR();
      }
   	
		//toString method to output the fields in an orderly fashion
      public String toString() {
         String supplierInfo = "Name: " + supplierName + "\n";
         String ingredientInfo = "";
      
         for (int i = 0; i < produceList.length; i++) {
            ingredientInfo += produceList[i].toString() + "\n";
         }
         return supplierInfo + ingredientInfo;
      }
   }