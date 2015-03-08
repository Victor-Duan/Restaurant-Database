/*
	Class: Ingedient.java
	Author: Andy Yang, John Kang, Stephen Chan, Victor Duan
	Date: January 10, 2014
	School: A.Y. Jackson Secondary School
	Purpose: This class helps create ingredient objects and contains a toString method 
            to print out the info regarding an ingredient
*/

   public class Ingredient{
     
    //Variables for the Ingredient class
      protected String name;
      protected String unitMeasurement;
   
   //Super constructor
      public Ingredient(String name, String units){
         this.name= name;
         unitMeasurement = units;
      }  
   
   //Accessors
      public String getName() {
         return name;
      }
   
      public String getUnitMeasurement() {
         return unitMeasurement;
      }
   
   //Mutators
      public void setName(String newName) {
         name = newName;
      }
   
      public void setUnitMeasurement(String newUnit) {
         unitMeasurement = newUnit;
      }
	
   //toString method	
		public String toString(){
			return ("Name: "+ name + "\n");
			//return ("Name: "+ name + " "+ unitMeasurement+ "\n");
		}
   }