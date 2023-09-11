import java.util.Scanner;

/**
 * 
 */

/**
 * @author diana
 *
 */
public class A0_Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner keyboard = new Scanner(System.in); //instantiate
		
		System.out.println("Welcome to the bookstore inventory system. "
				+ "Please enter the maximum number of books your bookstore can contain: ");
		
		int maxBooks = verifyInt(keyboard);
		Book[] inventory = new Book[maxBooks];
		
		mainMenu();
		
		int input = 0;
		int counter = 0;
		int counter02 = 0;
		
		while (!(input == 5)) {
			System.out.println("Please enter your choice: ");
			input = verifyInput(keyboard);
			if (input == 1) {	
				counter = verifyPassword(keyboard, counter);
				System.out.println("How many books would you like to enter?");
				int numBooks = verifyInt(keyboard);
				
				for (int i = 0; i < numBooks; i++) {
					inventory[i] = new Book(); 
				}
				System.out.println(Book.getNumBooks());
				
				int emptySlots = inventory.length - Book.getNumBooks();
				
				if (emptySlots <= 0) {
					System.out.println("There is no more inventory space for new books"); //would you like to remove one?
				}
				
				else if (emptySlots < numBooks){
					System.out.println("There are only " + emptySlots + " books left in your inventory."
							+ " Would you like to add" +  emptySlots + " books?\n"
									+ "   1.   Yes\n   2.   No");
					int s = verifyInt(keyboard);
					
					//create new books 
					if (s == 1) {
						for (int i = 0; i < emptySlots; i++) {
							for (int j = 0; j < 3; j++) {
								String[] attributes = new String[4];
								
								attributes[0] = "Title";
								attributes[1] = "Author";
								attributes[2] = "ISBN";
								attributes[3] = "Price";
								System.out.println("Please enter the " + attributes[j]);
								
							}
							inventory[i + Book.getNumBooks()] = new Book();
						}
							
					}
					
					else {
						System.out.println("Returning to the main menu...");
						mainMenu();
					}
					
				}
				
			}
			
			else if (input == 2) {
				counter = verifyPassword(keyboard, counter02);
				System.out.println("Which book number do you wish to update?");
				
				
				do {
					int s = verifyInt(keyboard);
					if (inventory[s].getISBN() == 0) {
						System.out.println("There is no book associated with this book number. " //what if input is bigger than maxbooks?
								+ "What would you like to do?"
								+ "   1.   Enter new book number"
								+ "   2.   Return to main menu");
						
					//this code is fucked rn
						
						if (verifyInt(keyboard) == 1) {
							continue;
						}
						else {}
				
					}
					
					else {
						//display book attributes method
					}
				}
				while (verifyInt(keyboard) == 1);
				
				
			}
			
			else if (input == 3) {
				System.out.println("Enter an author name: ");
			}
			
			else if (input == 4) {
				System.out.println("Enter a maximum value: ");
			}
		}
		
		System.out.println("Thank you for using the Bookstore Inventory Program. The program will now terminate.");
		System.exit(0);	
	}
	
	public static void mainMenu() {
		System.out.println("What do you want to do?\n   "
				+ "1.   Enter new books (password required)\n   "
				+ "2.   Change information of a book (password required)\n   "
				+ "3.   Display all books by a specific author\n   "
				+ "4.   Display all books under a certain price\n   "
				+ "5.   Quit\n");
	}
	
	public static int verifyInt(Scanner keyboard) {
		int input = 0;

		while (!keyboard.hasNextInt()) {
			System.out.println("Invalid input. Please enter an integer: ");
			keyboard.next();
		}
		
		input = keyboard.nextInt();

		return input;
	}
	
	public static int verifyInput(Scanner keyboard) {
		
		int input = verifyInt(keyboard);
		
		if (input < 1 || input > 5) {
			System.out.println("Invalid input. Please enter a number between 1 and 5: ");
		}
		
		return input;
	}
	
	public static int verifyPassword(Scanner keyboard, int counter) {
		
		String password = "249";
		String s;
		
		for (int i = 0; i < 3; i++) {
			System.out.println("Enter your password: ");
			s = keyboard.next();
			
			if (s.equals(password)) {
				System.out.println("Thank you for your input. ");	
				break;
			}
			else {
				counter++;
				if(i == 2) {
					if (counter == 12) {
						System.out.println("Program detected suspicious activities and will terminate immediately!");
						System.exit(0);
					}
					
					else {
						System.out.println("Too many attempts used. Redirecting to main menu...\n");
						A0_Driver.mainMenu();
					}
				} 
				else {
					System.out.println("Incorrect password. Try again.");

				}
			}
		}
		return counter;
	}
	
	
}
