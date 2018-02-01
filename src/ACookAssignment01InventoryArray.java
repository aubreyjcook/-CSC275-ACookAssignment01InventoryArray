import java.lang.reflect.Array;
import java.util.*;

public class ACookAssignment01InventoryArray {
	Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		new ACookAssignment01InventoryArray();

	}
	
	// This will act as our program switchboard
		public ACookAssignment01InventoryArray() {
			//String[] cargohold = new String[10];
			//Debug Note: Array Initialization used below is used to add dummy data for testing.
			String[] cargohold = new String[]{"zone","zone",null,"baby","uranium",null,"cold","arrow","baby","nine"};

			System.out.println("Welcome to the BlackStar Cargo Hold interface.");
			System.out.println("Please select a number from the options below");
			System.out.println("");

			while (true) {
				// Give the user a list of their options
				System.out.println("1: Add an item to the cargo hold.");
				System.out.println("2: Remove an item from the cargo hold.");
				System.out.println("3: Sort the contents of the cargo hold.");
				System.out.println("4: Search for an item.");
				System.out.println("5: Display the items in the cargo hold.");
				System.out.println("0: Exit the BlackStar Cargo Hold interface.");

				// Get the user input
				int userChoice = input.nextInt();
				input.nextLine();
				
				//Debug note: Simple array dummy data initialization.
				/*for(int i = 0; i < cargohold.length; i++) {
					cargohold[i] = "null";
				}*/
				
				switch (userChoice) {
					case 1:
						addItem(cargohold);
						break;
					case 2:
						removeItem(cargohold);
						break;
					case 3:
						sortItems(cargohold);
						break;
					case 4:
						searchItems(cargohold);
						break;
					case 5:
						displayItems(cargohold);
						break;
					case 0:
						System.out.println("Thank you for using the BlackStar Cargo Hold interface. See you again soon!");
						System.exit(0);
					default:
						System.out.println("Invalid value. Choose a number 0-5 only.");
						break;
				}
			}

		}

		private void addItem(String cargohold[]) {
			if(Arrays.asList(cargohold).contains(null)) { //if statement checks if there is any empty value in the array
				System.out.println("Enter the new item below.");
				String userInput = input.nextLine();
				for(int i = 0; i < cargohold.length; i++) {
					if (cargohold[i]==null) {
						cargohold[i] = userInput;
						System.out.println(userInput + " has been added successfully.");
						break;
					}
				}
			} else {
				System.out.println("The cargohold is full. You need to remove an item to add a new one.");
			}
			return;
		}

		private void removeItem(String cargohold[]) {
			System.out.println("Enter item to be removed.");
			String userInput = input.nextLine();
			
			if(Arrays.asList(cargohold).contains(userInput)) {
				/*System.out.println("Array if statement evaluated that the value is contained in the array.");*/
				for(int i = 0; i < cargohold.length; i++) {
					if (cargohold[i] != null && cargohold[i].equals(userInput)) {
						cargohold[i] = null;
						System.out.println("Item removed.");
						break;
					}
				}
			} else {
				System.out.println("That item is not in the cargohold.");
			}
			
			return;
		}

		private void sortItems(String cargohold[]) {
			// TODO: Sort the items in the cargo hold (No need to display them here) - Use Selection or Insertion sorts
			// NOTE: Special care is needed when dealing with strings! research the compareTo() method with strings
			
			//checking for null values in the array
			int nullCounter = 0;
			
			for(int i= 0; i < cargohold.length; i++) {
				if(cargohold[i]==null) {
					nullCounter++;					
				}
			}
			
			//moves all null values to the end of the array
			while(nullCounter > 0) {
				for(int i = 0; i < cargohold.length; i++) {
					if(cargohold[i]==null) {
						for (int j = (i + 1); j < cargohold.length; j++) {
							cargohold[j - 1] = cargohold[j];
						}
						cargohold[cargohold.length-1] = null;
						break;
					}
				}
				nullCounter--;
			}
			
			
			//sorting after moving null values to the end of the array
			for(int x = 0; x < cargohold.length; x++) { //this for loop repeats the sorting process according to the length of the array to enhance sorting accuracy
				for (int i = 0; i < cargohold.length-1; i++) {
					int minimum = i;
					//System.out.println(minimum);
					for (int j = i + 1; j < cargohold.length; j++) {
						//System.out.println(j);
						//System.out.println(cargohold[i].compareTo(cargohold[minimum]));
						if(cargohold[j] != null) {
							if(cargohold[j].compareTo(cargohold[minimum]) < 0) {
								minimum = j;
								//Debug Note: Outputs array reassignments in real time. 
								//System.out.println("Swapping " + cargohold[i] + " with " + cargohold[minimum]);
								//System.out.println(Arrays.asList(cargohold));
								String transfer = cargohold[i];
								cargohold[i] = cargohold[minimum];
								cargohold[j] = transfer;
							}
						}
					}
				}
			}
			System.out.println("Cargohold sorted.");
			return;
		}

		private void searchItems(String cargohold[]) {
			// TODO: Search for a user specified item
			System.out.println("Enter the item you wish to search for.");
			String userInput = input.nextLine();
			
			for(int i = 0; i < cargohold.length; i++) {
				if(cargohold[i] == null) {
					continue;
				} else if (userInput.equals(cargohold[i])) {
					System.out.println("The item was found at section number " + (i + 1));
					return;
				}
			}
			System.out.println("The item was not found in the cargo hold.");
		}

		private void displayItems(String cargohold[]) {
			// TODO: Display only the unique items along with a count of any duplicates
			//
			// For example it should say
			// Food - 2
			// Water - 3
			// Ammunition - 5
			//System.out.println(Arrays.asList(cargohold));
			
			int count = Collections.frequency(Arrays.asList(cargohold), null);
			String[] itemsFound = new String[10];
			
			if(count > 0) {
				System.out.println("There are " + count + " empty spaces in the cargohold.");
			}
			
			for (int i = 0; i < cargohold.length; i++) {
				if (cargohold[i] != null && !(Arrays.asList(itemsFound).contains(cargohold[i]))) {
					
					count = Collections.frequency(Arrays.asList(cargohold), cargohold[i]);					
					System.out.println("There are " + count + " " + cargohold[i] + " items in the cargohold.");
					itemsFound[i] = cargohold[i];
					
				}
			}
		}		
}
