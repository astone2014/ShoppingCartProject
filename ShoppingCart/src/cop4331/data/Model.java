package cop4331.data;
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
	 * Adds an item from the cart
	 *
	 * @param id
	 *            ID of the item
	 * @param name
	 *            Name of the item
	 * @param type
	 *            Item type
	 * @param price
	 *            Item price
	 */
	public void cartAdd(String id, String name, String type, String price, String invoiceprice) {
		boolean replace = true;
		List<String[]> allLines = getCSV(CSVLocation);
		String[] newCartItem = { "", "", "", "", "", "" };
		if (getStock(name) == 0)
			return;
		try {
			for (String[] product : allLines) {
				String cartName = product[1];
				String cartQuantity = product[5];

				if (cartName.equals(name)) {
					cartQuantity = String.valueOf(Integer.parseInt(cartQuantity) + 1);
					product[5] = cartQuantity;
					replace = false;
				}
			}
		} catch (NullPointerException n) {
			createAccountCSV(CSVLocation);
		}
		if (replace) {
			newCartItem[0] = id;
			newCartItem[1] = name;
			newCartItem[2] = type;
			newCartItem[3] = price;
			newCartItem[4] = invoiceprice;
			newCartItem[5] = String.valueOf(1);
			try {
				allLines.add(newCartItem);
			} catch (NullPointerException n) {
			}
		}
		decrementStock(name);
		writeCSV(allLines, CSVLocation, false);
		return;
	}

	/**
	 * Removes an item from the cart
	 *
	 * @param id
	 *            ID of the item
	 * @param name
	 *            Name of the item
	 * @param type
	 *            Item type
	 * @param price
	 *            Item price
	 */
	public void cartRemove(String id, String name, String type, String price) {
		String filepath = CSVLocation;
		List<String[]> allLines = getCSV(filepath);
		int x = 0;
		try {
			try {
				for (String[] product : allLines) {
					String cartName = product[1];
					String cartQuantity = product[5];

					if (cartName.equals(name)) {
						if (Integer.parseInt(cartQuantity) > 0) {
							cartQuantity = String.valueOf(Integer.parseInt(cartQuantity) - 1);
						} else if (Integer.parseInt(cartQuantity) == 0) {
							allLines.remove(x);
							writeCSV(allLines, filepath, false);
							return;
						}
						product[5] = cartQuantity;
					}
					x++;
				}
			} catch (ConcurrentModificationException c) {
			}
		} catch (NullPointerException n) {
			createAccountCSV(CSVLocation);
		}
		incrementStock(name);
		writeCSV(allLines, filepath, false);
		return;
	}

	/**
	 * Finalize the transaction nu writing to sales.csv
	 *
	 * @param firstname
	 *            User first name
	 * @param lastname
	 *            User last name
	 * @param cc
	 *            User credit card number
	 * @param email
	 *            User email
	 * @param address
	 *            User home address
	 */
	public void completeTransaction(String firstname, String lastname, String cc, String email, String address) {
		List<String[]> sales = getCSV("sales.csv");
		String[] newSale = new String[8];

		newSale[0] = String.valueOf(getCartTotal());// total
		newSale[1] = String.valueOf(getCartCost());// cost
		newSale[2] = Username; // username
		newSale[3] = firstname;// firstname
		newSale[4] = lastname;// lastname
		newSale[5] = cc;// email
		newSale[6] = email;// email
		newSale[7] = address;// address

		sales.add(newSale);
		writeCSV(sales, "sales.csv", false);
	}

	/**
	 * Decrease the stock of an item by 1
	 *
	 * @param productName
	 *            Stock item to remove to inventory
	 */
	public void decrementStock(String name) {
		List<String[]> inventory = getCSV("products.csv");
		for (String[] product : inventory) {
			if (product[5].equals(name)) {
				if (product[2].equals("0")) {
				} else {
					product[2] = String.valueOf(Integer.parseInt(product[2]) - 1);
				}
			}
		}
		writeCSV(inventory, "products.csv", false);
	}

	/**
	 * Increase the stock of an item by 1
	 *
	 * @param productName
	 *            Stock item to add to inventory
	 */
	public void incrementStock(String productName) {
		List<String[]> inventory = getCSV("products.csv");
		for (String[] product : inventory) {
			if (product[5].equals(productName)) {
				product[2] = String.valueOf(Integer.parseInt(product[2]) + 1);
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
			for (@SuppressWarnings("unused")
			String[] count : readerToReturn) {
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
	 * @param csvlocation
	 * @param replace
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

	public void clearCSV(String csvlocation) {
		try {
			FileWriter writer = new FileWriter(csvlocation);
			writer.write("");
			writer.close();
		} catch (NullPointerException n) {
			System.out.println("csv is empty");
			createAccountCSV(csvlocation);
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
	public boolean signUpUser(String userName, char[] userPassword, int accountType) {
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
			String[] newAccount = { userName, stringPassword, String.valueOf(accountType) };
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

	/**
	 * returns the stock for a product otherwise it returns null.
	 * 
	 * @param name
	 * @return
	 */
	public int getStock(String name) {
		List<String[]> inventory = getCSV("products.csv");
		for (String[] product : inventory) {
			if (product[5].equals(name))
				return Integer.parseInt(product[2]);
		}
		return 0;
	}

	/**
	 * Returns the cart total
	 *
	 * @return float Cart total
	 */
	public float getCartTotal() {
		float total = 0;
		List<String[]> inventory = getCSV(CSVLocation);
		for (String[] product : inventory) {
			total += Float.parseFloat(product[3]) * Integer.parseInt(product[5]);
		}
		return total;
	}

	/**
	 * Returns the total invoice cost on the cart
	 *
	 * @return float Cart cost
	 */
	public float getCartCost() {
		float total = 0;
		List<String[]> inventory = getCSV(CSVLocation);
		for (String[] product : inventory) {
			total += Float.parseFloat(product[4]) * Integer.parseInt(product[5]);
		}
		return total;
	}

	/**
	 * Returns the total sales
	 *
	 * @return float All sales
	 */
	public float getSaleTotal() {
		float total = 0;
		List<String[]> sales = getCSV("sales.csv");
		for (String[] sale : sales) {
			if (sale[0].equals("Total")) {
				continue;
			} else {
				total += Float.parseFloat(sale[0]);
			}
		}
		return total;
	}

	/**
	 * Returns the total invoice cost for all sales
	 *
	 * @return float All costs
	 */
	public float getSaleCost() {
		float total = 0;
		List<String[]> sales = getCSV("sales.csv");
		for (String[] sale : sales) {
			if (sale[0].equals("Total")) {
				continue;
			} else {
				total += Float.parseFloat(sale[1]);
			}
		}
		return total;
	}

}
