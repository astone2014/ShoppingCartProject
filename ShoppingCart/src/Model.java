import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class Model {
	private boolean loggedin;
	private int AccountType;
	private String Username;
	private String CSVLocation;
	
	// <---------------CART---------------->
	/**
	 * Adds an item to the cart
	 *
	 * @param itemName
	 * @param type 
	 * @param name 
	 * @param count
	 * @param price
	 */
	public void cartAdd(String id, String name, String type, String price) {
		boolean replace = true;
		List<String[]> allLines = getCSV(CSVLocation);
		String[] newCartItem = {"","","","",""};
		try {
			for (String[] product : allLines) {
				String cartName = product[1];
				String cartQuantity = product[4];
				
				if (cartName.equals(name)) {
					System.out.print("IncrementCart: " + name + " - Price: " + price + " Count: " + cartQuantity);
					cartQuantity = String.valueOf(Integer.parseInt(cartQuantity) + 1);
					System.out.println(" -> " + cartQuantity);
					product[4] = cartQuantity;
					replace = false;
				}
			}
		} catch (NullPointerException n) {
			System.out.println("no cart items");
			createAccountCSV(CSVLocation);
		}
		if (replace) {
			System.out.println("Added NEW Item: " + name + " - Price: " + price);
			newCartItem[0] = id;
			newCartItem[1] = name;
			newCartItem[2] = type;
			newCartItem[3] = price;
			newCartItem[4] = String.valueOf(1);
			
			try {
				allLines.add(newCartItem);
			} catch (NullPointerException n) {
				System.out.println("no cart items");
			}
		}
		decrementStock(name);
		writeCSV(allLines, CSVLocation, false);
		return;
	}

	public void cartRemove(String id, String name, String type, String price) {
		String filepath = CSVLocation;
		boolean replace = true;
		List<String[]> allLines = getCSV(filepath);
		String[] newCartItem = {"","","","",""};
		int x = 0;
		try {
			try {
				for (String[] product : allLines) {
					String cartName = product[1];
					String cartQuantity = product[4];
					
					if (cartName.equals(name)) {
						if (Integer.parseInt(cartQuantity) > 0) {
							System.out.print("DecrementCart: " + name + " - Price: " + price + " Count: " + cartQuantity);
							cartQuantity = String.valueOf(Integer.parseInt(cartQuantity) - 1);
							System.out.println(" -> " + cartQuantity);
						} else if (Integer.parseInt(cartQuantity) == 0) {
							System.out.println("REMOVED FROM CART");
							allLines.remove(x);
						}
						product[4] = cartQuantity;
						replace = false;
					}
					x++;
				}
			} catch (ConcurrentModificationException c) {
			}
		} catch (NullPointerException n) {
			createAccountCSV(CSVLocation);
		}
		if (replace) {
			System.out.println("Added NEW Item: " + name + " - Price: " + price);
			newCartItem[0] = String.valueOf(x);
			newCartItem[1] = name;
			newCartItem[2] = "new type";
			newCartItem[3] = price;
			newCartItem[4] = String.valueOf(1);
			try {
				allLines.add(newCartItem);
			} catch (NullPointerException n) {
				System.out.println("no cart items");
			}
		}
		writeCSV(allLines, filepath, false);
		return;
	}

	private void decrementStock(String name) {
		List<String[]> inventory = getCSV("products.csv");
		for (String[] product : inventory) {
			if (product[5].equals(name)) {
				if (product[2].equals("0")) {
					System.out.println("NO MORE STOCK");
				} else {
					System.out.print("DecrementedStock: " + name + " - " + product[2]);
					product[2] = String.valueOf(Integer.parseInt(product[2]) - 1);
					System.out.println(" -> " + product[2]);
				}
			}
		}
		writeCSV(inventory, "products.csv", false);
	}

	private void incrementStock(String productName) {
		List<String[]> inventory = getCSV("products.csv");
		for (String[] product : inventory) {
			if (product[5].equals(productName)) {
				System.out.print("IncrementStock: " + productName + " - " + product[2]);
				product[2] = String.valueOf(Integer.parseInt(product[2]) + 1);
				System.out.println(" -> " + product[2]);
			}
		}
		writeCSV(inventory, "products.csv", false);
	}

	/**
	 * Creates a user CSV file to hold cart info
	 *
	 * @param csvlocation
	 *            location of user csv file
	 */
	private void createAccountCSV(String csvlocation) {
		System.out.println("CREATING CSV...");
		FileWriter newfile;
		try {
			newfile = new FileWriter(csvlocation);
			newfile.write("");
			newfile.close();
		} catch (IOException e) {
			System.out.println("CREATE NEW CSV FAILED");
		}
	}

	/**
	 * Get List of String arrays
	 * 
	 * @return List<String[]>
	 */
	public List<String[]> getAccountCart() {
		String AccountCartLocation = CSVLocation;
		try {
			CSVReader reader = new CSVReader(new FileReader(AccountCartLocation));
			List<String[]> readerToReturn = reader.readAll();
			reader.close();
			return readerToReturn;
		} catch (IOException e) {
			getCSV(AccountCartLocation);
		}
		return null;
	}
	
	/**
	 * Returns a new index
	 * 
	 * @return List<String[]>
	 */
	public int getNewIndex() {
		int newindex = 0;
		try {
			CSVReader reader = new CSVReader(new FileReader("products.csv"));
			List<String[]> readerToReturn = reader.readAll();
			for(String[] count : readerToReturn){
				++newindex;
			}
			reader.close();
		} catch (IOException e) {
			return 0;
		}
		return newindex;
	}
	
	// <---------------LOGIN---------------->
	/**
	 * Get wither the account is an admin or user account
	 *
	 * @return account type 1 = user 2 = admin
	 */
	public String getAccountTypeString() {
		String accounttype;
		if (AccountType == 2) {
			accounttype = "Admin";
		} else {
			accounttype = "User";
		}
		return accounttype;
	}

	/**
	 * Get the username of the account
	 *
	 * @return username
	 */
	public String getAccountUsername() {
		return Username;
	}

	/**
	 * Set the username
	 *
	 * @param string
	 *            username to set
	 */
	public void setAccountUsername(String string) {
		Username = string;
		CSVLocation = Username + "_cart.csv";
	}

	public String getAccountCSVLocation() {
		return CSVLocation;
	}

	/**
	 * Check if user is logged in.
	 *
	 * @return true if logged in else false
	 */
	public boolean getLoginStatus() {
		return loggedin;
	}

	/**
	 * Gets a List(rows) of String[](columns)
	 *
	 * @param csvlocation
	 *            location to get csv data from
	 * @return list<String[]> representing csv file
	 */
	public List<String[]> getCSV(String csvlocation) {
		try {
			CSVReader reader = new CSVReader(new FileReader(csvlocation));
			List<String[]> readerToReturn = reader.readAll();
			reader.close();
			return readerToReturn;
		} catch (FileNotFoundException fnf) {
			createAccountCSV(csvlocation);
			getCSV(csvlocation);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Writes to a CSV file
	 *
	 * @param allLines
	 *            List<String[]> to write
	 * @param csvlocation
	 *            location to write
	 * @param replace
	 *            replace yes=true no=false
	 * @return List
	 */
	public void writeCSV(List<String[]> allLines, String csvlocation, boolean replace) {
			try {
				CSVWriter writer = new CSVWriter(new FileWriter(csvlocation, replace));
				try {
					writer.writeAll(allLines);
				} catch (NullPointerException n) {
					System.out.println("csv is empty");
					createAccountCSV(csvlocation);
					writeCSV(allLines, csvlocation, replace);
				}
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	/**
	 * Logs a user in to the store
	 *
	 * @param userName
	 *            username from username field
	 * @param userPassword
	 *            password from password field
	 * @return returns true is an account was found otherwise it returns false
	 */
	public boolean loginUser(String userName, char[] userPassword) {
		String stringPassword = new String(userPassword);
		String[] nextUser;
		try {
			CSVReader reader = new CSVReader(new FileReader("accounts.csv"));
			while ((nextUser = reader.readNext()) != null) {
				if (userName.equals(nextUser[0])) {
					if (stringPassword.equals(nextUser[1])) {
						AccountType = Integer.parseUnsignedInt(nextUser[2]);
						setAccountUsername(userName);
						System.out.println("Welcome " + userName);
						reader.close();
						return true;
					}
					reader.close();
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Adds a user to the CSV file
	 *
	 * @param userName
	 *            new account username
	 * @param userPassword
	 *            new account password
	 * @return returns true if added successfully and a false if the credentials
	 *         where taken
	 */
	public boolean signUpUser(String userName, char[] userPassword) {
		String filepath = "accounts.csv";
		String csvlocation = userName + "_cart.csv";
		String stringPassword = new String(userPassword);
		String[] nextUser;
		try {
			CSVReader reader = new CSVReader(new FileReader(filepath));
			while ((nextUser = reader.readNext()) != null) {
				if (userName.equals(nextUser[0])) {
					reader.close();
					return false;
				}
			}
			reader.close();
			CSVWriter writer = new CSVWriter(new FileWriter(filepath, true));
			String[] newAccount = { userName, stringPassword, "1" };
			writer.writeNext(newAccount);
			writer.close();
			System.out.println("Creating New File");
			FileWriter newfile = new FileWriter(csvlocation);
			newfile.write("");
			newfile.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
