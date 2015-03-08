   /*
	Class: Ingedient.java
	Author: Andy Yang, John Kang, Stephen Chan, Victor Duan
	Date: January 10, 2014
	School: A.Y. Jackson Secondary School
	Purpose: This subclass helps to create a drink and prints out whether it is alcoholic or not 
*/

public class Drink extends Dish {

      //Variable for Drink class (Check if a drink is alcholic)
      boolean alcoholic;
      
      //Super constructor
      public Drink(boolean alcoholic, String name, Drawer list[], int menuNumber, double cost){
         super(name, list, menuNumber, cost);
         this.alcoholic= alcoholic;
      }
   	
		//Accessor
		public boolean getAlcoholic() {
			return alcoholic;
		}
		
		//Mutator
		public void setAlcoholic(boolean alcoholic) {
			this.alcoholic = alcoholic;
		}
		
      //toString method
      public String toString(){
         if (alcoholic == true){
            return ("Acholoic: Yes" + "\n"+ super.toString());
         }
         else {
            return ("Alcoholic: No"+ "\n" + super.toString());
         }
      }
      
   }