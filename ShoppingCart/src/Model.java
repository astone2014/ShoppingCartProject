import java.io.*;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class Model {
	private boolean loggedin;
	private int AccountType;
	private String Username;

	/**
	 * Adds an item to the cart
	 *
	 * @param itemName
	 * @param count 
	 * @param price 
	 */
	public void cartAdd(String itemName, float price) {
		String filepath = Username + "_cart.csv";
		boolean replace = true;
		List<String[]> allLines = getCSV(filepath);
		String[] newCartItem = {"","",""};
		try{
			for(String[] column : allLines){
				if(column[0].equals(itemName) && column[0] != ""){
					column[2] = String.valueOf((Integer.parseInt(column[2]) + 1));
					replace = false;
				}
			}
		}catch(NullPointerException n){
			System.out.println("no cart items");
			createAccountCSV(Username + "_cart");
		}
		if(replace){
			System.out.println("EMPTY");
			newCartItem[0] = itemName;
			newCartItem[1] = String.valueOf(price);
			newCartItem[2] = String.valueOf(1);
			try{
				allLines.add(newCartItem);
			} catch(NullPointerException n){
				System.out.println("no cart items");
			}
			}
		writeCSV(allLines, filepath, false);
		return;
	}

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
	 * Returns a list of rows to iterate through
	 *
	 * @return list of string arrays
	 */
	public List<String[]> getAccountCart() {
		String AccountCartLocation = Username + "_cart.csv";
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

	public int getAccountType() {
		return AccountType;
	}

	public String getAccountUsername() {
		return Username;
	}	
	
	public void setAccountUsername(String string) {
		Username = string;
	}

	public boolean getLoginStatus() {
		return loggedin;
	}

	/**
	 * Returns a list of rows to iterate through
	 * @param  
	 *
	 * @return list of string arrays
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
	
	public List<String[]> writeCSV(List<String[]> allLines, String csvlocation, boolean replace) {
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(csvlocation, replace));
			try{
				writer.writeAll(allLines);
			}catch(NullPointerException n){
				System.out.println("csv is empty");
				createAccountCSV(csvlocation);
				writeCSV(allLines, csvlocation, replace);
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Could not find file");
			createAccountCSV(csvlocation);
		}
		return null;
	}

	/**
	 * Loggs a user in
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
					}
					return true;
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Adds a user to the csv file
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
		String csvlocation = Username + "_cart.csv";
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
