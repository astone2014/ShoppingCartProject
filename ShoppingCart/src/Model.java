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

	/**
	 * Adds an item to the cart
	 *
	 * @param itemName
	 * @param count
	 * @param price
	 */
	public void cartAdd(String itemName, float price) {
		String filepath = CSVLocation;
		boolean replace = true;
		List<String[]> allLines = getCSV(filepath);
		String[] newCartItem = { "", "", "" };
		try {
			for (String[] column : allLines) {
				if (column[0].equals(itemName) && column[0] != "") {
					System.out.print("IncrementCart: " + itemName + " - Price: " + price + " Count: " + column[2]);
					column[2] = String.valueOf(Integer.parseInt(column[2]) + 1);
					System.out.println(" -> " + column[2]);
					replace = false;
				}
			}
		} catch (NullPointerException n) {
			System.out.println("no cart items");
			createAccountCSV(CSVLocation);
		}
		if (replace) {
			System.out.println("Added NEW Item: " + itemName + " - Price: " + price);
			System.out.println("EMPTY");
			newCartItem[0] = itemName;
			newCartItem[1] = String.valueOf(price);
			newCartItem[2] = String.valueOf(1);
			try {
				allLines.add(newCartItem);
			} catch (NullPointerException n) {
				System.out.println("no cart items");
			}
		}
		decrementStock(itemName);
		writeCSV(allLines, filepath, false);
		return;
	}
	
	public void cartRemove(String itemName, float price) {
		String filepath = CSVLocation;
		boolean replace = true;
		List<String[]> allLines = getCSV(filepath);
		String[] newCartItem = { "", "", "" };
		try {
			int x = 0;
			try{
				for (String[] column : allLines) {
					if (column[0].equals(itemName) && column[0] != "") {
						if(Integer.parseInt(column[2]) > 0){
							System.out.print("DecrementCart: " + itemName + " - Price: " + price + " Count: " + column[2]);
							column[2] = String.valueOf(Integer.parseInt(column[2]) - 1);
							System.out.println(" -> " + column[2]);
						}else if(Integer.parseInt(column[2]) == 0){
							System.out.println(x);
							System.out.println("Removed Item from Cart");
								allLines.remove(x);
						}
						replace = false;
					}
					x++;
				}
			}catch(ConcurrentModificationException c){
				System.out.println("Conc");						
			}
		} catch (NullPointerException n) {
			System.out.println("no cart items");
			createAccountCSV(CSVLocation);
		}
		if (replace) {
			System.out.println("Added NEW Item: " + itemName + " - Price: " + price);
			newCartItem[0] = itemName;
			newCartItem[1] = String.valueOf(price);
			newCartItem[2] = String.valueOf(1);
			try {
				allLines.add(newCartItem);
			} catch (NullPointerException n) {
				System.out.println("no cart items");
			}
		}
		incrementStock(itemName);
		writeCSV(allLines, filepath, false);
		return;
	}
	
	private void decrementStock(String productName){
		List<String[]>inventory = getCSV("products.csv");
		for(String[] product : inventory){
			if(product[0].equals(productName)){
				if(product[2].equals("0")){
					System.out.println("Dec Stock" + product[2]);
				}else{
					System.out.print("DecrementedStock: " + productName + " - " + product[2]);
					product[2] = String.valueOf((Integer.parseInt(product[2]) - 1));
					System.out.println(" -> " + product[2]);
				}
			}
		}
		writeCSV(inventory, "products.csv", false);
	}
	
	private void incrementStock(String productName){
		List<String[]>inventory = getCSV("products.csv");
		for(String[] product : inventory){
			if(product[0].equals(productName)){
				System.out.print("IncrementStock: " + productName + " - " + product[2]);
				product[2] = String.valueOf((Integer.parseInt(product[2]) + 1));
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
		FileWriter newfile;
		try {
			newfile = new FileWriter(csvlocation);
			newfile.write("");
			newfile.close();
		} catch (IOException e) {
			System.out.println("failed to create file");
		}
	}

	/**
	 * Get List of String arrays
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
			System.out.println("getAccountCart: No csv file found");
			createAccountCSV(AccountCartLocation);
			getCSV(AccountCartLocation);
		}
		return null;
	}

	/**
	 * Get wither the account is an admin or user account
	 *
	 * @return account type 1 = user 2 = admin
	 */
	public int getAccountType() {
		return AccountType;
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
		CSVLocation = Username + "_csv.csv";
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
			System.out.println("No csv file found");
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
			System.out.println("Could not find file");
			createAccountCSV(csvlocation);
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
		String[] nextRow;
		try {
			CSVReader reader = new CSVReader(new FileReader("accounts.csv"));
			while ((nextRow = reader.readNext()) != null) {
				if (userName.equals(nextRow[0])) {
					if (stringPassword.equals(nextRow[1])) {
						AccountType = Integer.parseUnsignedInt(nextRow[2]);
						setAccountUsername(userName);
						System.out.println("Welcome " + userName);
						return true;
					}
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
		String csvlocation = CSVLocation;
		String stringPassword = new String(userPassword);
		String[] nextRow;
		try {
			CSVReader reader = new CSVReader(new FileReader(filepath));
			while ((nextRow = reader.readNext()) != null) {
				if (userName.equals(nextRow[0]))
					return false;
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
