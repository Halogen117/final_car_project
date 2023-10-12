package carparkSystem;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Timer;
//import org.json.JSONArray;
//import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 *
 * @author
 */
public class carparkAPI {

    public static void main(String[] args) {
    	
    	Connection connectDB = null;
    	
    	Datab db = new Datab();
    	manipulateDb manDb = new manipulateDb();
    	//db.Conn();  //Connect
    	
    	String urlDB = "jdbc:postgresql://localhost/carparkInformation";
        String user = "postgres";
        String password = "";
        boolean run = true;
        try {
        	connectDB = DriverManager.getConnection(urlDB, user, password);
        	System.out.println("Connected to PostgreSQL server");
        	
        	// COMMENT OUT THIS LINE IF YOU ALREADY HAVE A TABLE
        	//db.createDatabase(connectDB);
        	db.createFavDb(connectDB);
        	db.createHistoryDb(connectDB);
        	db.createUserDb(connectDB);
        	
        
        String[] createAcc = new String[11];
        
        createAcc[0] = "user1";
        createAcc[1] = "Evan";
        createAcc[2] = "evanseah123@gmail.com";
        createAcc[3] = "qwerty123";
        createAcc[4] = "81257183";
        createAcc[5] = "Pet name?";
        createAcc[6] = "Favourite food?";
        createAcc[7] = "Favourite place?";
        createAcc[8] = "Fluffy";
        createAcc[9] = "Pizza";
        createAcc[10] = "Singapore";
        
        manipulateDb manip = new manipulateDb();
        
        manip.insertUserDb(connectDB, createAcc);
        
        //create array for ["name", "password", "phoneNum"] , and boolean array [false, false, false]
        
        manip.updateProfileDetails(connectDB, "user3", "Russel", "name");
        String [] userDetails = manip.getUserDetails(connectDB, "user3");
	        for(int i = 0; i < userDetails.length; i++) {
	        	System.out.println(userDetails[i]);
	        }
        
        connectDB.close();
        }
        catch (SQLException se) {
        	se.printStackTrace();
        }
    }
}

class Datab{
    
    ////////////////////// USER DATABASE /////////////////////////////
    public void createUserDb(Connection connectDB) {
    	try {
        	Statement statement = connectDB.createStatement();
        	
        	String sql = "CREATE TABLE user_DB" + //EDIT here
                	"(user_ID TEXT,"
                	+ "name TEXT," +
                	"email TEXT,"
                	+ "password TEXT,"
                	+ "phoneNum TEXT,"
                	+ "sec1 TEXT,"
                	+ "sec2 TEXT,"
                	+ "sec3 TEXT,"
                	+ "ans1 TEXT,"
                	+ "ans2 TEXT,"
                	+ "ans3 TEXT)";
                	
            statement.executeUpdate(sql);
            System.out.println("Table successfully created!");
        	}
        	catch(SQLException e) {
            	System.out.println("Error in Creating Table to History Server");
            	e.printStackTrace();
            }
    }
    ///////////////////////////////////////////////////////////////////
    
    //////////////////// HISTORY DATABASE /////////////////////////////
    public void createHistoryDb(Connection connectDB) {
    	try {
        	Statement statement = connectDB.createStatement();
        	
        	String sql = "CREATE TABLE history_DB" + //EDIT here
                	"(user_ID TEXT," +
                	"carpark_ID TEXT)";
                	
            statement.executeUpdate(sql);
            System.out.println("Table successfully created!");
        	}
        	catch(SQLException e) {
            	System.out.println("Error in Creating History Table to Server");
            	e.printStackTrace();
            }
    }
    /////////////////////////////////////////////////////////////////////
    
    /////////////////// FAVOURITE DATABASE //////////////////////////////
    public void createFavDb(Connection connectDB) {
    	try {
    	Statement statement = connectDB.createStatement();
    	
    	String sql = "CREATE TABLE favourite_DB" + //EDIT here
            	"(user_ID TEXT," +
            	"carpark_ID TEXT)";
            	
        statement.executeUpdate(sql);
        System.out.println("Table successfully created!");
    	}
    	catch(SQLException e) {
        	System.out.println("Error in Creating Favourite Table to Server");
        	e.printStackTrace();
        }
    }
}

class manipulateDb {
	
	//////////////////////FAVOURITE DATABASE ////////////////////////////////////////
	public void insertFavDb(Connection connectDB, String userID, String carparkID) {
		try {
        	//Connection connection = DriverManager.getConnection(url, user, password);
        	//System.out.println("Connected to PostgreSQL server");
        	
        	Statement statement = connectDB.createStatement();
        	
        	String sql = "INSERT INTO favourite_DB (user_ID,carpark_ID)" +
        			"VALUES('" + userID +"','"+ carparkID  + "')";
        	
        	statement.executeUpdate(sql);
        	System.out.println("Data Successfully Inserted!");
        	
        }
        catch(SQLException e) {
        	System.out.println("Error in updating to Favourite Server");
        	e.printStackTrace();
        }
	}
	
	public void updateFavDb(Connection connectDB, String userID, String carparkID) {
		
		try {
		Statement statement = connectDB.createStatement();
		String sql = "SELECT COUNT(*) " +
                "FROM favourite_DB " +
                "WHERE user_ID = '" + userID + "'";
    	
        // Execute the query
        ResultSet resultSet = statement.executeQuery(sql);
        

        // Retrieve the count from the result set
        if (resultSet.next()) {
            int count = resultSet.getInt(1); // Get the count from the first (and only) column
            System.out.println("Count: " + count);
            if(count < 5) {
            	insertFavDb(connectDB, userID, carparkID); //update favourite carpark DB
            	System.out.println("Data Successfully Inserted!");
            }
            else {
            	System.out.println("Max Number of favourite!");
            	
            }
        }
        
		}catch(SQLException e) {
    	System.out.println("Error in updating to Favourite Server");
    	e.printStackTrace();
		}

    }
	////////////////////////////////////////////////////////////////////////////////////
	
	
	////////////////////// HISTORY DATABASE ////////////////////////////////////////
	public void insertHistDb(Connection connectDB, String userID, String carparkID) {
		try {
        	//Connection connection = DriverManager.getConnection(url, user, password);
        	//System.out.println("Connected to PostgreSQL server");
        	
        	Statement statement = connectDB.createStatement();
        	
        	String sql = "INSERT INTO history_DB (user_ID,carpark_ID)" +
        			"VALUES('" + userID +"','"+ carparkID  + "')";
        	
        	statement.executeUpdate(sql);
        	System.out.println("Data Successfully Inserted!");
        	
        }
        catch(SQLException e) {
        	System.out.println("Error in updating to Favourite Server");
        	e.printStackTrace();
        }
	}
	
	public void updateSearchHist(Connection connectDB, String userID, String carparkID) {
		
		try {
		Statement statement = connectDB.createStatement();
		String sql = "SELECT COUNT(*) " +
                "FROM history_DB " +
                "WHERE user_ID = '" + userID + "'";
    	
        // Execute the query
        ResultSet resultSet = statement.executeQuery(sql);
        

        // Retrieve the count from the result set
        if (resultSet.next()) {
            int count = resultSet.getInt(1); // Get the count from the first (and only) column
            System.out.println("Count: " + count);
            if(count < 10) {
            	insertFavDb(connectDB, userID, carparkID); //update favourite carpark DB
            	System.out.println("Data Successfully Inserted!");
            }
            else {
            	System.out.println("Max Number of favourite!");
            }
        }
        
		}catch(SQLException e) {
    	System.out.println("Error in updating to History Server");
    	e.printStackTrace();
		}
    }
	/////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////// USER DATABASE ////////////////////////////////////////
	public void updateProfileDetails(Connection connectDB, String userID, String changes, String columnName) {
		try {
			Statement statement = connectDB.createStatement();
			/////////////////// check if userID column is the  /////////////////////////
			String sql = "UPDATE user_DB SET " + columnName + " = '" + changes + "' WHERE user_ID = '" + userID + "'";
			
			statement.executeUpdate(sql);
			System.out.println("Update Successful");

		}catch(SQLException e) {
			System.out.println("Update Failed!");
			e.printStackTrace();

		}
	}
	// FOR Profile Page Details
	public String [] getUserDetails(Connection connectDB, String userID) {
		String [] userDetails = new String[11];
		try {
		Statement statement = connectDB.createStatement();
		
		String sql = "SELECT * FROM user_DB WHERE user_ID = '" + userID + "'";
		
		ResultSet resultSet = statement.executeQuery(sql);
		// get User details
		if(resultSet.next()) {
			for(int i = 1; i <= userDetails.length; i++) {
				userDetails[i-1] = resultSet.getString(i);
			}
		}
		else {
			System.out.println("User Does not exist");
		}
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return userDetails;
	}
	
	// FOR Email validation
	public boolean checkEmailExist(Connection connectDB, String email){
		
		try {
			Statement statement = connectDB.createStatement();
			/////////////////// check if email and phone number already exist /////////////////////////
			String checkEmail = "SELECT * FROM user_DB WHERE email = '" + email + "'"; // [0] = user ID , [2] = email, [4] = phoneNumber
			
			ResultSet resultSet = statement.executeQuery(checkEmail);
			
			if(resultSet.next()) {
				//System.out.println("Email already Existed!");
				return true;
			}
			else {
				//System.out.println("No Exisiting Email!");
				return false;
			}
		}catch(SQLException e) {
			System.out.println("Error Validating Email!");
			e.printStackTrace();
			
		}
		
		return true;
	}
	
	//FOR Phone Number Validation
	public boolean checkContactExist(Connection connectDB, String phoneNum){
		
		try {
			Statement statement = connectDB.createStatement();
			/////////////////// check if email and phone number already exist /////////////////////////
			String checkContact = "SELECT * FROM user_DB WHERE phoneNum = '" + phoneNum + "'"; // [0] = user ID , [2] = email, [4] = phoneNumber
			
			ResultSet resultSet = statement.executeQuery(checkContact);
			
			if(resultSet.next()) {
				//System.out.println("Phone Number already Existed!");
				return true;
			}
			else {
				//System.out.println("No Exisiting Phone Number!");
				return false;
			}
		}catch(SQLException e) {
			System.out.println("Error Validating Phone Number!");
			e.printStackTrace();
		}
		return true;
	}
	
	//CreateAccount
	public void insertUserDb(Connection connectDB, String [] createAcc) {
		try {
			Statement statement = connectDB.createStatement();
			/////////////////// check if email and phone number already exist /////////////////////////
			boolean getContact = checkContactExist(connectDB, createAcc[4]);
			boolean getEmail = checkEmailExist(connectDB, createAcc[2]);
			
			if(getEmail) {
				System.out.println("Email already Existed!");
			}
			else if(getContact) {
				System.out.println("Phone Number already Existed");
			}
			else {
				String sql = "INSERT INTO user_DB "
						+ "VALUES('" + createAcc[0] + "','" + createAcc[1] + "','" + createAcc[2] + "','"
						+ createAcc[3] + "','" + createAcc[4] + "','" + createAcc[5] + "','"
						+ createAcc[6] + "','" + createAcc[7] + "','" + createAcc[8] + "','"
						+ createAcc[9] + "','" + createAcc[10] +"')";
				
				statement.executeUpdate(sql);
		        System.out.println("User Account Created!");
				//activate "Authentication Failed" display message code here
			}
		}catch(SQLException e) {
			System.out.println("User Account Creation Failed!");
			e.printStackTrace();
			
		}
	}
    
}


class authenticate{

	////////////// LOGIN AUTHENTICATION ////////////////////////
	public void login(Connection connectDB, String email, String password) {
		
		try {
			Statement statement = connectDB.createStatement();
			
			String sql = "SELECT * FROM user_DB WHERE email = '" + email +"' AND password = '" + password +"'";
			
			ResultSet resultSet = statement.executeQuery(sql);
			
			if(resultSet.next()) {
				System.out.println("Login Successful");
				//activate "Login Successful" display message code here
				//Close Guest Page and 
				//Display Member Page
			}
			else {
				System.out.println("Authentication Failed");
				//activate "Authentication Failed" display message code here
				//Reset password textbox
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	///////////////////////////////////////////////////////////
	
	////////////// GET SECURITY QUESTION ////////////////////////////
	public String [] getSecQuestion(Connection connectDB, String userID) {
		String [] secQuestion = new String[3];
		
		try {
		Statement statement = connectDB.createStatement();
		
		String sql = "SELECT * FROM user_DB WHERE user_ID = '" + userID +"'";
		
		ResultSet resultSet = statement.executeQuery(sql);
		
		if(resultSet.next()) {
			secQuestion[0] = resultSet.getString("sec1");
			secQuestion[1] = resultSet.getString("sec2");
			secQuestion[2] = resultSet.getString("sec3");
		}
		else {
			System.out.println("UserID not found!");
		}
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return secQuestion;
	}
	
	//////////////////////// VERIFY IF ANSWER IS CORRECT ///////////////////////////////////
	public boolean verification(Connection connectDB,String userID, String answer1, String answer2, String answer3) {
		
		try {
		Statement statement = connectDB.createStatement();
		
		String sql = "SELECT * FROM user_DB WHERE user_ID = '" + userID +"'";
		
		ResultSet resultSet = statement.executeQuery(sql);
		
		if(resultSet.next()) {
			if((answer1 != resultSet.getString("ans1")) || (answer2 != resultSet.getString("ans2")) || (answer3 != resultSet.getString("ans3"))) {
				System.out.println("Verification Failed!");
				return false;
			}
			else {
				System.out.println("Verification Successful!");
				return true;
			}
			
		}else {
			System.out.println("UserID not found!");
		}
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	////////////////////////////////////////////////////////////
}




    
  /*  final Timer timer = new Timer();
// Note that timer has been declared final, to allow use in anon. class below
timer.schedule( new TimerTask()
{
    private int i = 10;
    public void run()
    {
        System.out.println("30 Seconds Later");
        if (--i < 1) timer.cancel(); // Count down ten times, then cancel
    }
}, 30000, 30000 //Note the second argument for repetition
);
*/
