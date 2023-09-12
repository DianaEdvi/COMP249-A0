
public class Book {
	
	private String title;
	private String author;
	private long ISBN;
	private double price;
	private static int totalNumBooks = 0;
	private static int maxNumBooks = 0;
	
	//default constructor
	public Book() {
		title = "Unknown title";
		author = "Unknown author";
		ISBN = 0;
		price = 0.0;
		totalNumBooks++;
	}
	
	//paramaterized constructor 
	public Book(String inTitle, String inAuthor, long inISBN, double inPrice) {
		title = inTitle;
		author = inAuthor;
		ISBN = inISBN;
		price = inPrice;
		totalNumBooks++;
	}
	
	//getters 
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public long getISBN() {
		return ISBN;
	}
	public double getPrice() {
		return price;
	}
	public static int getTotalNumBooks() {
		return totalNumBooks;
	}
	public static int getMaxNumBooks() {
		return maxNumBooks;
	}
	
	//setters
	public void setTitle(String inTitle) {
		title = inTitle;
	}
	public void setAuthor(String inAuthor) {
		author = inAuthor;
	}
	public void setISBN(long inISBN) {
		ISBN = inISBN;
	}
	public void setPrice(double inPrice) {
		price = inPrice;
	}
	public static void setMaxNumBooks(int inMaxNumBooks) {
		maxNumBooks = inMaxNumBooks;
	}
	
	//Converts attributes' data to string
	public String toString() {
		return "Author: " + author
			 + "\nTitle: " + title
			 + "\nISBN: " + ISBN
			 + "\nPrice: $" + price; 
	}
	//Finds and displays all books under the same author
	public static void findBooksBy(Book[] inInventory, String inAuthor) {
		for (int i = 0; i < inInventory.length; i++) {
			if (inInventory[i].getAuthor().equals(inAuthor)) {
				System.out.println(inInventory[i].toString());
				System.out.println();
			}
		}
	}
	//Takes a value and finds and displays all books with the same or lower price
	public static void findCheaperThan(Book[] inInventory, double inPrice) {
		for (int i = 0; i < inInventory.length; i++) {
			if (inInventory[i].getPrice() <= inPrice) {
				System.out.println(inInventory[i].toString());
				System.out.println();
			}
		}
	}
}
