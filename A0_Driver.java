import java.util.Scanner;

/**
 * ----------------------------------------------------
 * Assignment 0
 * Question: Part 2
 * Written by: Diana Edvi 40198139
 * ----------------------------------------------------
 * This program emulates a typical inventory program for a bookstore. The user is first prompted 
 * to enter a maximum amount of books that can fit into their inventory, and is subsequently provided
 * with 5 options to choose from:
 * Option 1 prompts the user to enter new books
 * Option 2 prompts the user to change information of a book
 * Option 3 displays all books by a certain author 
 * Option 4 displays all books under a certain price 
 * Option 5 terminates the program  
 */

public class A0_Driver {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in); //instantiate
		
		System.out.println("Welcome to Diana's Bookstore Inventory System.\n\n"
				+ "Please enter the maximum number of books your bookstore can contain: ");
		//Create inventory for max books
		int maxBooks = verifyInt(keyboard);
		while (maxBooks <= 0) {
			System.out.println("You can't have negative books! Try again");
			maxBooks = verifyInt(keyboard);
		}
		Book.setMaxNumBooks(maxBooks);
		Book[] inventory = new Book[maxBooks];
		
		mainMenu();
		
		int input = 0;
		int counter = 0;
		int counter02 = 0;
		//Loop that contains options from main menu 
		while (!(input == 5)) {
			int oldCounter = counter;
			System.out.println("Please enter your choice from 1 to 5: ");
			input = verifyInput(keyboard);
			//Option 1: Verifies password, then prompts user for amount of books 
			if (input == 1) {	
				counter = verifyPassword(keyboard, counter);
				if (oldCounter != counter && counter % 3 == 0) {
					continue;
				}
				int option = 0;
				
				//Loops until the user enters input to return to main menu
				do {
					System.out.println("How many books would you like to enter?");
					int numBooks = verifyInt(keyboard);
					while (numBooks <= 0) {
						System.out.println("You can't have negative books! Try again");
						numBooks = verifyInt(keyboard);
					}
					int totalBooks = 0; //used to avoid overwriting references in inventory array
					
					//Sort new books into inventory array
					if ((Book.getMaxNumBooks()- Book.getTotalNumBooks() >= numBooks )){
						totalBooks = Book.getTotalNumBooks();
						
						for (int i = 0; i < numBooks; i++) {
							System.out.println("Enter book # " + (i + totalBooks) + "'s information: ");
							System.out.print("Enter the author's name: ");
							keyboard.nextLine();
							String author = keyboard.nextLine();
							System.out.print("Enter the title: "); 
							String title = keyboard.nextLine();
							System.out.print("Enter the ISBN: "); 
							long isbn = verifyNumber(keyboard);
							while (isbn <= 0) {
								System.out.println("You can't have a negative ISBN! Try again");
								isbn = verifyInt(keyboard);
							}
							System.out.print("Enter the Price: ");
							double price = verifyDouble(keyboard);
							while (price <= 0) {
								System.out.println("You can't have a negative price! Try again");
								price = verifyInt(keyboard);
							}
							
							inventory[i + totalBooks] = new Book(author, title, isbn, price); 
							
							System.out.println("\nBook has been added successfully\n");
						}
						break;
					}
					//Checks the amount of space left in the inventory and prompts user accordingly  
					else if (Book.getMaxNumBooks() - Book.getTotalNumBooks() < numBooks) {
						//No more inventory space
						if (Book.getMaxNumBooks() - Book.getTotalNumBooks() == 0) {
							System.out.println("There is no more inventory space for new books");
							break;
						}
						//Informs user of remaining space and prompts user for input
						else {
							System.out.println("There is/are only " + (Book.getMaxNumBooks() - Book.getTotalNumBooks())
									+ " spot(s) left in your inventory. Would you like to add " + (Book.getMaxNumBooks() - Book.getTotalNumBooks()) 
									+ " book(s)?\n");
							
							System.out.println("   1.   Yes\n   2.   No");
							int s = verifyInt(keyboard);
							option = s;
							numBooks = Book.getMaxNumBooks() - Book.getTotalNumBooks();
							
							while (!(s == 2)) {
								if (s == 1) {
									totalBooks = Book.getTotalNumBooks();
									for (int i = 0; i < (numBooks); i++) {
									System.out.println("Enter book # " + (i + totalBooks) + "'s information (you can always change this in the future): ");
									System.out.print("Enter the author's name: ");
									keyboard.nextLine();
									String author = keyboard.next();
									System.out.print("Enter the title: ");
									String title = keyboard.next();
									System.out.print("Enter the ISBN: ");
									long isbn = verifyNumber(keyboard);
									while (isbn <= 0) {
										System.out.println("You can't have a negative ISBN! Try again");
										isbn = verifyInt(keyboard);
									}
									System.out.print("Enter the Price: ");
									double price = verifyDouble(keyboard);
									while (price <= 0) {
										System.out.println("You can't have a negative price! Try again");
										price = verifyInt(keyboard);
									}
									
										inventory[i + totalBooks] = new Book(author, title, isbn, price);
										
										System.out.println("\nBook havs been added successfully\n");
									}
									option = 2;
									break;
								}
								//Input validation
								else {
									System.out.println("Please choose one of the available options");
									System.out.println("   1.   Yes\n   2.   No");
									s = verifyInt(keyboard);
								}
							}
						}
					}
				}
				while (!(option == 2));
				System.out.println("Returning to main menu...\n");
				mainMenu();
			}
			//Option 2: Verifies password, then prompts user to update books 
			else if (input == 2) {
				int oldCounter02 = counter02;
				counter02 = verifyPassword(keyboard, counter02);
				if (oldCounter02 != counter02 && counter02 % 3 == 0) {
					continue;
				}
				int option = 0;
				//Loops until the user enters input to return to main menu
				outerLoop: do {
					
					bookOptions();
					option = verifyInput2(keyboard);
					if (option == 2) {
						break;
					}
					
					System.out.println("Which book number do you wish to update?"  );
					int bookNum = verifyInt(keyboard);
					//Checks that user does not enter number out of bounds of inventory array
					while (bookNum > (inventory.length-1)){
							System.out.println("There is no book associated with this number because your maximum number of books is " + maxBooks + ". ");
							
							bookOptions();
							option = verifyInput2(keyboard);
							if (option == 2) {
								break outerLoop;
							}

							System.out.println("\nWhich book number do you wish to update?");
							bookNum = verifyInt(keyboard);
					}
					//Checks that user does not enter a book number that doesn't exist
					if (inventory[bookNum] == null) {
						System.out.println("There is no book associated with this book number. ");
						
						bookOptions();
						option = verifyInput2(keyboard);
						if (option == 2) {
							break;
						}
					}
					//Changes information according to user's input
					else {
						System.out.println("Book # " + bookNum);
						System.out.println(inventory[bookNum].toString());
						int choice = 0;
						System.out.println("\nWhat information would you like to change?\n" 
								+ "   1.   author\n"
								+ "   2.   title\n"
								+ "   3.   ISBN\n"
								+ "   4.   price\n"
								+ "   5.   choose a new book\n");
						do {
							System.out.println("Enter your choice: ");
							choice = verifyInt(keyboard);
							
							if (choice == 1) {
								System.out.print("Enter new author's name: ");
								String author = keyboard.next();
								inventory[bookNum].setAuthor(author);
								System.out.println("Book # " + bookNum + "'s author has been changed.");
							}
							else if (choice == 2) {
								System.out.println("Enter new book title: ");
								String title = keyboard.next();
								inventory[bookNum].setTitle(title);
								System.out.println("Book # " + bookNum + "'s title has been changed.");
							}
							else if (choice ==3) {
								System.out.println("Enter new ISBN: ");
								long isbn = verifyNumber(keyboard);
								inventory[bookNum].setISBN(isbn);
								System.out.println("Book # " + bookNum + "'s ISBN has been changed.");
							}
							else if (choice ==4) {
								System.out.println("Enter new price: ");
								double price = verifyDouble(keyboard);
								inventory[bookNum].setPrice(price);
								System.out.println("Book # " + bookNum + "'s price has been changed.");
							}
						}
						while (!(choice == 5));	
					}
				}
				
				while (!(option == 2));
				
				System.out.println("Returning to main menu...\n");
				mainMenu();
			}
			//Option 3: Displays all books by the author input by the user
			else if (input == 3) {
				System.out.println("Enter an author name: ");
				keyboard.nextLine();
				String author = keyboard.nextLine();
				Book.findBooksBy(inventory, author); 
				System.out.println("\nReturning to main menu...\n");
				mainMenu();
			}
			//Option 4: Displays all books with a price lower than the number input by the user
			else if (input == 4) {
				System.out.println("Enter a maximum value: ");
				double price = verifyDouble(keyboard);
				
				Book.findCheaperThan(inventory, price);
				System.out.println("Returning to main menu...\n");
				mainMenu();
			}
		}
		
		System.out.println("Thank you for using Diana's Bookstore Inventory System. The program will now terminate.");
		System.exit(0);	
	}
	//Displays main menu
	public static void mainMenu() {
		System.out.println("What do you want to do?\n   "
				+ "1.   Enter new books (password required)\n   "
				+ "2.   Change information of a book (password required)\n   "
				+ "3.   Display all books by a specific author\n   "
				+ "4.   Display all books under a certain price\n   "
				+ "5.   Quit\n");
	}
	//Verifies that the user's input is an integer
	public static int verifyInt(Scanner keyboard) {
		int input = 0;
		while (!keyboard.hasNextInt()) {
			System.out.println("Invalid input. Please enter an integer: ");
			keyboard.next();
		}
		input = keyboard.nextInt();
		
		return input;
	}
	public static double verifyDouble(Scanner keyboard) {
		double input = 0;
		while (!keyboard.hasNextDouble()) {
			System.out.println("Invalid input. Please enter a number: ");
			keyboard.next();
		}
		input = keyboard.nextDouble();
		return input;
	}
	//Verifies that the input from the user is a number 
	public static long verifyNumber (Scanner keyboard) {
		long input = 0;
		while (!keyboard.hasNextLong()) {
			System.out.println("Invalid input. Please enter a number: ");
			keyboard.next();
		}
		input = keyboard.nextLong();
		return input;
	}
	//Verifies that the user chooses one of the offered options
	public static int verifyInput(Scanner keyboard) {
		int input = verifyInt(keyboard);
		if (input < 1 || input > 5) {
			System.out.println("Invalid input.");
		}
		return input;
	}
	//Verifirs that the user selects between two choices
	public static int verifyInput2(Scanner keyboard) {
		int option = verifyInt(keyboard);
		while (!(option == 1) && !(option == 2) ) {
			System.out.println("Please choose one of the available options");
			option = verifyInt(keyboard);
		}
		return option;
	}
	//Displays book selection options
	public static void bookOptions(){
		System.out.println("What would you like to do?\n"
				+ "   1.   Enter a book number\n"
				+ "   2.   Return to main menu");
	}
	
	//Verifies password, and closes the program if the wrong password has been entered 12 times in a row
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
