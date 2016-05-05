package Lab6;

import java.sql.*;
import java.util.Scanner;


public class lab6_ZUSER27 {
	static String tableName;
	static String userName;
	static String passWord;
	static Scanner input = new Scanner(System.in);
	public static void menu(int m){
		if(m == 1)
		{
			System.out.println("What table?");
			String tableName = input.next();
			selectStatement(tableName);
		}
		else if (m == 2)
		{
			minMaxAvg();
		}
		else if (m == 3)
		{
			System.out.println("Bye!");
			return;
		}
		
			
	}
	
	//Do MIN, MAX AND AVERAGE OF ACCT_BAL_CNY AND ACCT_BAL_USD
	private static void minMaxAvg() {
		// TODO Auto-generated method stub
		try{
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			Connection con = DriverManager.getConnection("jdbc:db2://192.86.32.68:5035/DALLASB", userName, passWord);
			Statement sql_statement = con.createStatement();
			ResultSet rs = sql_statement.executeQuery("select min(ACCT_BAL_CNY), max(ACCT_BAL_CNY), avg(ACCT_BAL_CNY), min(ACCT_BAL_USD), max(ACCT_BAL_USD), avg(ACCT_BAL_USD) from ACCOUNT");
			System.out.printf("%16s %20s %20s %20s %20s %20s", "MIN(ACCT_BAL_CNY)", "MAX(ACCT_BAL_CNY)", "AVG(ACCT_BAL_CNY)", "MIN(ACCT_BAL_USD", "MAX(ACCT_BAL_USD)", "AVG(ACCT_BAL_USD");
			System.out.println();
			while(rs.next())
			{
				double minCny = rs.getDouble(1);
				double maxCny = rs.getDouble(2);
				double avgCny = rs.getDouble(3);
				double minUsd = rs.getDouble(4);
				double maxUsd = rs.getDouble(5);
				double avgUsd = rs.getDouble(6);
				System.out.printf("\t    %.2f\t       %.2f\t             %.2f\t           %.2f\t       %.2f\t            %.2f", minCny, maxCny, avgCny, minUsd, maxUsd, avgUsd);
				System.out.println();
			}
			sql_statement.close();
			con.close();
		}catch(Exception ex)
		{
			System.out.println("Error");
		}
	}
	//Do "select *" statement
	private static void selectStatement(String tableName)
	{
		try{
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			Connection con = DriverManager.getConnection("jdbc:db2://192.86.32.68:5035/DALLASB", userName, passWord);
			Statement sql_statement = con.createStatement();
			ResultSet result = sql_statement.executeQuery(" select * from " + tableName);
			if(tableName.equalsIgnoreCase("account") || tableName.equalsIgnoreCase(userName + ".account"))
			{
				System.out.printf("%10s\t %12s\t %12s\t %12s\t %11s\t %12s\t %11s\t %26s", "ACCT_ID","ACCT_BAL_CNY", "ACCT_BAL_USD", "ACCT_EMP_ID", "ACCT_CUST_ID", "ACCT_BRAN_ID", "ACCT_STATUS", "ACCT_OPEN_TIME");
				System.out.println();
				while(result.next())
				{
					String id = result.getString("ACCT_ID");
					String cny = result.getString("ACCT_BAL_CNY");
					String usd = result.getString("ACCT_BAL_USD");
					String empid = result.getString("ACCT_EMP_ID");
					String custid = result.getString("ACCT_CUST_ID");
					String branid = result.getString("ACCT_BRAN_ID");
					String status = result.getString("ACCT_STATUS");
					String opentime = result.getString("ACCT_OPEN_TIME");
					System.out.printf("%10s \t %12s\t %12s\t %12s\t %11s\t %12s\t %11s\t %20s", id, cny, usd, empid, custid, branid, status, opentime);
					System.out.println();
				}
				sql_statement.close();
				con.close();
			}
			else if (tableName.equalsIgnoreCase("customer") || tableName.equalsIgnoreCase(userName + ".customer"))
			{
				System.out.printf("%12s %20s %19s %30s %27s %34s %56s %18s %14s %30s", "CUST_ID","CUST_IDENT", "CUST_FIRSTNAME", "CUST_LASTNAME", "CUST_PHONE", "CUST_ADDRESS", "CUST_EMP_ID", "CUST_BRAN_ID", "CUST_STATUS", "CUST_OPEN_TIME");
				System.out.println();
				while(result.next())
				{
					String id = result.getString("CUST_ID");
					String ident = result.getString("CUST_IDENT");
					String first = result.getString("CUST_FIRSTNAME");
					String last = result.getString("CUST_LASTNAME");
					String phone = result.getString("CUST_PHONE");
					String address = result.getString("CUST_ADDRESS");
					String empid = result.getString("CUST_EMP_ID");
					String branid = result.getString("CUST_BRAN_ID");
					String status = result.getString("CUST_STATUS");
					String opentime = result.getString("CUST_OPEN_TIME");
					System.out.printf("%12s %30s %25s %29s %20s %70s %12s %18s %14s %30s", id, ident, first, last, phone, address, empid, branid, status, opentime);
					System.out.println();
				}
				sql_statement.close();
				con.close();
			}
			else if (tableName.equalsIgnoreCase("branch") || tableName.equalsIgnoreCase(userName + ".branch"))
			{
				System.out.printf("%14s %25s %25s %72s %14s %30s", "BRAN_ID", "BRAN_NAME", "BRAN_PHONE", "BRAN_ADDRESS", "BRAN_STATUS", "BRAN_OPEN_TIME");
				System.out.println();
				while(result.next())
				{
					String id = result.getString("BRAN_ID");
					String name = result.getString("BRAN_NAME");
					String phone = result.getString("BRAN_PHONE");
					String address = result.getString("BRAN_ADDRESS");
					
					String status = result.getString("BRAN_STATUS");
					String opentime = result.getString("BRAN_OPEN_TIME");
					System.out.printf("%14s %25s %25s %72s %14s %30s", id, name, phone, address, status, opentime);
					System.out.println();
				}
				sql_statement.close();
				con.close();
			}
		}catch (SQLException ex){
			System.out.println("Error!! You may have some typos!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		int selection;
		System.out.println("Username?");
		userName = input.next();
		System.out.println("Password?");
		passWord = input.next();
		do{
			System.out.println("Choose a number for what to do!!");
			System.out.println("1 for select * statement");
			System.out.println("2 for Minimum, Maximum and Average of ACCT_BAL_CNY AND ACCT_BAL_USD");
			System.out.println("3 and you're out");
			selection = input.nextInt();
			if (selection != 1 && selection != 2  && selection != 3)
			{
				System.out.println("Please choose 1, 2 or 3!");
			}
			else
				menu(selection);
		}while(selection == 1 || selection == 2 || (selection != 1 && selection != 3) || (selection != 2 && selection != 3));
		
	}
	
}

