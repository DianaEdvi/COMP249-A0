
public class Book {
	
	private String title;
	private String author;
	private long ISBN;
	private double price;
	private static int numBooks = 0;
	
	//default constructor
	public Book() {
		title = "Unknown title";
		author = "Unknown author";
		ISBN = 0;
		price = 0.0;
		numBooks++;
	}
	
	//paramaterized constructor 
	public Book(String inTitle, String inAuthor, long inISBN, double inPrice) {
		title = inTitle;
		author = inAuthor;
		ISBN = inISBN;
		price = inPrice;
		numBooks++;
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
	public static int getNumBooks() {
		return numBooks;
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
	
}
