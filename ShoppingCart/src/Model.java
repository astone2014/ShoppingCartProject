import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class Model {
	private boolean loggedin;
	private int AccountType;

	public boolean getLoginStatus() {
		return loggedin;
	}
	public int getAccountType() {
		return AccountType;
	}

	public boolean loginUser(String userName, char[] userPassword) {
		String stringPassword = new String(userPassword);
		String[] nextRow;
		try {
			CSVReader reader = new CSVReader(new FileReader("accounts.csv"));
			while ((nextRow = reader.readNext()) != null) {
				if (userName.equals(nextRow[0])) {
					if (stringPassword.equals(nextRow[1]))
						AccountType = Integer.parseUnsignedInt(nextRow[2]);
						System.out.println("Account Type: " + AccountType);
						return true;
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean signUpUser(String userName, char[] userPassword) {
		String filepath = "accounts.csv";
		String stringPassword = new String(userPassword);
		String[] nextRow;
		try {
			CSVReader reader = new CSVReader(new FileReader(filepath));
			while ((nextRow = reader.readNext()) != null) {
				if (userName.equals(nextRow[0])) {
						return false;
				}
			}
			reader.close();
			CSVWriter writer = new CSVWriter(new FileWriter(filepath, true));
			String[] newAccount = { userName, stringPassword, "1"};
			writer.writeNext(newAccount);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public List<String[]> getProducts(){
		try {
			CSVReader reader = new CSVReader(new FileReader("products.csv"));
			 List<String[]>readerToReturn = reader.readAll();
			reader.close();
			return readerToReturn;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<String[]> getAccountCart() {
		try {
			CSVReader reader = new CSVReader(new FileReader("carts.csv"));
			 List<String[]>readerToReturn = reader.readAll();
			reader.close();
			return readerToReturn;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void cartAdd(String itemName) {
		String filepath = "carts.csv";
		String[] nextRow;
		try {
			CSVReader reader = new CSVReader(new FileReader(filepath));
			while ((nextRow = reader.readNext()) != null) {
				if("1" == nextRow[0]){
					System.out.println(nextRow[0]);
				}
			}
			reader.close();
			CSVWriter writer = new CSVWriter(new FileWriter(filepath, true));
			String[] newAccount = { "", "" };
			writer.writeNext(newAccount);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
