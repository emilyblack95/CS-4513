import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;
import oracle.jdbc.OracleTypes;

/** Package obtained from http://opencsv.sourceforge.net/ **/
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

/**
 * A CLIENT & DONOR DATABASE SYSTEM
 * @author: Emily Black
 * @date: 11/13/17
 */
public class JDBC_INDEX {
    public static void main(String[] args) throws SQLException {
    	
    	// Step 1. Load a database driver:
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        }
        catch(Exception x)
        {
            System.out.println( "Unable to load the driver class!" );
        }
        
        // Step 2. Create an Oracle JDBC Connection:
        Connection conn = DriverManager.getConnection ("host","username", "password");
        
        // Step 3. Create a new scanner/notDone variable:
        Scanner sc = new Scanner(System.in);
        boolean notDone = true;
        
        // Step 4. Print the client & donor database system menu:
        String cdMenu = 
        "WELCOME TO THE PAN CLIENT AND DONOR DATABASE SYSTEM.\n" 
        + "Please enter the number to the corresponding task you want to run.\n" 
        + "\n" 
        + "(1) Enter a new team into the database.\n" 
        + "(2) Enter a new client into the database and associate him or her with one or more teams.\n" 
        + "(3) Enter a new volunteer into the database and associate him or her with one or more teams.\n"
        + "(4) Enter the number of hours a volunteer worked this month for a particular team.\n" 
        + "(5) Enter a new employee into the database and associate him or her with one or more teams.\n"
        + "(6) Enter an expense charged by an employee.\n"
        + "(7) Enter a new organization and associate it to one or more PAN teams.\n"
        + "(8) Enter a new donor and associate him or her with several donations.\n"
        + "(9) Enter a new organization and associate it with several donations.\n"
        + "(10) Retrieve the name and phone number of the doctor of a particular client.\n" 
        + "(11) Retrieve the total amount of expenses charged by each employee for a particular period of time. The list should be sorted by the total amount of expenses\n"
        + "(12) Retrieve the list of volunteers that are members of teams that support a particular client.\n"
        + "(13) Retrieve the names and contact information of the clients that are supported by teams sponsored by an organization whose name starts with a letter between B and K.  The client list should be sorted by name.\n"
        + "(14) Retrieve the name and total amount donated by donors that are also employees.  The list should be sorted by the total amount of the donations, and indicate if each donor wishes to remain anonymous.\n"
        + "(15) For each team, retrieve the name and associated contact information of the volunteer that has worked the most total hours between March and June.\n" 
        + "(16) Increase the salary by 10% of all employees to whom more than one team must report.\n" 
        + "(17) Delete all clients who do not have health insurance and whose value of importance for transportation is less than 5.\n" 
        + "(18) Import: Enter new teams from a data file until the file is empty (the user will be asked to enter the input file name).\n"
        + "(19) Export: Retrieve names and mailing addresses of all people on the mailing list and output them to a data file instead of screen (the user will be asked to enter the output file name).\n"
        + "(20) Quit.";
        System.out.println("\n" + cdMenu + "\n");
        
        // Step 5. Begin reading input from user:
        do {
            System.out.println("Please enter the number to the corresponding task you want to run.");
            int taskToRun = Integer.parseInt(sc.nextLine());
            String[] parsedInput;
            String userInput;
            
            if (taskToRun == 1) {
                System.out.println("Please enter your input in the following format: Team name, Team type, Date of formation (Ex: 121495 = Dec. 14th, 1995), Team lead");
                userInput = sc.nextLine();
                parsedInput = userInput.split(", ");
                QUERY1(conn, parsedInput[0], parsedInput[1], parsedInput[2], parsedInput[3]);   
            } 
            
            else if (taskToRun == 2) {
                System.out.println("Please enter your input in the following format: Email address, Mailing address, Home phone number, Work phone number, Cell phone number, SSN, Name, DoB (Ex: 121495 = Dec. 14th, 1995), Race, Gender, Profession, Is on mailing list (Y or N), Is affiliated with external organization (Y or N), Doctors name, Attorneys name, Doctors number, Attorneys number, Date of assignment, Team name, Is active (Y or N)");
                userInput = sc.nextLine();
                parsedInput = userInput.split(", ");
                QUERY2(conn, parsedInput[0], parsedInput[1], parsedInput[2], parsedInput[3], parsedInput[4], parsedInput[5], parsedInput[6], parsedInput[7], parsedInput[8], parsedInput[9], parsedInput[10], parsedInput[11], parsedInput[12], parsedInput[13], parsedInput[14], parsedInput[15], parsedInput[16], parsedInput[17], parsedInput[18], parsedInput[19]);
            } 
            
            else if (taskToRun == 3) {
                System.out.println("Please enter your input in the following format: Email address input, Mailing address, Home phone number, Work phone number, Cell phone number, SSN, Name, DoB, Race, Gender, Profession, Is on mailing list (Y or N), Is affiliated with external org. (Y or N), Join date, Training date, Training location, Team name, Is active (Y or N), Jan hours worked, Feb hours worked, March hours worked, April hours worked, May hours worked, June hours worked, July hours worked, Aug hours worked, Sept hours worked, Oct hours worked, Nov hours worked, Dec hours worked");
                userInput = sc.nextLine();
                parsedInput = userInput.split(", ");
                QUERY3(conn, parsedInput[0], parsedInput[1], parsedInput[2], parsedInput[3], parsedInput[4], parsedInput[5], parsedInput[6], parsedInput[7], parsedInput[8], parsedInput[9], parsedInput[10], parsedInput[11], parsedInput[12], parsedInput[13], parsedInput[14], parsedInput[15], parsedInput[16], parsedInput[17], parsedInput[18], parsedInput[19], parsedInput[20], parsedInput[21], parsedInput[22], parsedInput[23], parsedInput[24], parsedInput[25], parsedInput[26], parsedInput[27], parsedInput[28], parsedInput[29]);
            } 
            
            else if (taskToRun == 4) {
                System.out.println("Please enter your input in the following format: SSN, Jan hours worked, Feb hours worked, March hours worked, April hours worked, May hours worked, June hours worked, July hours worked, Aug hours worked, Sept hours worked, Oct hours worked, Nov hours worked, Dec hours worked");
                userInput = sc.nextLine();
                parsedInput = userInput.split(", ");
                QUERY4(conn, parsedInput[0], parsedInput[1], parsedInput[2], parsedInput[3], parsedInput[4], parsedInput[5], parsedInput[6], parsedInput[7], parsedInput[8], parsedInput[9], parsedInput[10], parsedInput[11], parsedInput[12]);
            } 
            
            else if (taskToRun == 5) {
                System.out.println("Please enter your input in the following format: Email address, Mailing address, Home phone number, Work phone number, Cell phone number, SSN, Name, DoB (Ex: 121495 = Dec. 14th, 1995), Race, Gender, Profession, Is on mailing list (Y or N), Is affiliated with external organization (Y or N), Salary, Martial status, Hire date, Team name, Date of report (Ex: 121495 = Dec. 14th, 1995), Description");
                userInput = sc.nextLine();
                parsedInput = userInput.split(", ");
                QUERY5(conn, parsedInput[0], parsedInput[1], parsedInput[2], parsedInput[3], parsedInput[4], parsedInput[5], parsedInput[6], parsedInput[7], parsedInput[8], parsedInput[9], parsedInput[10], parsedInput[11], parsedInput[12], parsedInput[13], parsedInput[14], parsedInput[15], parsedInput[16], parsedInput[17], parsedInput[18]);

            } else if (taskToRun == 6) {
                System.out.println("Please enter your input in the following format: SSN, Expense date (Ex: 121495 = Dec. 14th, 1995), Expense amount, Expense description");
                userInput = sc.nextLine();
                parsedInput = userInput.split(", ");
                QUERY6(conn, parsedInput[0], parsedInput[1], parsedInput[2], parsedInput[3]);
            } 
            
            else if (taskToRun == 7) {
                System.out.println("Please enter your input in the following format: Organization name, SSN, Organization mailing address, Organization phone number, Organization contact person, Is org anonymous (Y or N), Team name");
                userInput = sc.nextLine();
                parsedInput = userInput.split(", ");
                QUERY7(conn, parsedInput[0], parsedInput[1], parsedInput[2], parsedInput[3], parsedInput[4], parsedInput[5], parsedInput[6]);
            }
            
            else if (taskToRun == 8) {
                System.out.println("Please enter your input in the following format: Email address, Mailing address, Home phone number, Work phone number, Cell phone number, SSN, Name, DoB (Ex: 121495 = Dec. 14th, 1995), Race, Gender, Profession, Is on mailing list (Y or N), Is affiliated with external organization (Y or N), Is donor anonymous (Y or N)");
                userInput = sc.nextLine();
                parsedInput = userInput.split(", ");
                QUERY8(conn, parsedInput[0], parsedInput[1], parsedInput[2], parsedInput[3], parsedInput[4], parsedInput[5], parsedInput[6], parsedInput[7], parsedInput[8], parsedInput[9], parsedInput[10], parsedInput[11], parsedInput[12], parsedInput[13]);   
            } 
            
            else if (taskToRun == 9) {
                System.out.println("Please enter your input in the following format: Organization name, SSN, Organization mailing address, Organization phone number, Organization contact person, Is org anonymous (Y or N)");
                userInput = sc.nextLine();
                parsedInput = userInput.split(", ");
                QUERY9(conn, parsedInput[0], parsedInput[1], parsedInput[2], parsedInput[3], parsedInput[4], parsedInput[5]);
            } 
            
            else if (taskToRun == 10) {
                System.out.println("Please enter your input in the following format: SSN");
                userInput = sc.nextLine();
                parsedInput = userInput.split(", ");
                QUERY10(conn, parsedInput[0]);
            } 
            
            else if (taskToRun == 11) {
                System.out.println("Please enter your input in the following format: Expense date");
                userInput = sc.nextLine();
                parsedInput = userInput.split(", ");
                QUERY11(conn, parsedInput[0]);
            } 
            
            else if (taskToRun == 12) {
                System.out.println("Please enter your input in the following format: SSN");
                userInput = sc.nextLine();
                parsedInput = userInput.split(", ");
                QUERY12(conn, parsedInput[0]);
            } 
            
            else if (taskToRun == 13) {
            	// Query takes no input
                QUERY13(conn);
                
            } 
            
            else if (taskToRun == 14) {
            	// Query takes no input
                QUERY14(conn);
            }
            
            else if (taskToRun == 15) {
            	// Query takes no input
                QUERY15(conn);
            } 
            
            else if (taskToRun == 16) {
            	// Query takes no input
                QUERY16(conn);
            } 
            
            else if (taskToRun == 17) {
            	// Query takes no input
                QUERY17(conn);
            }
            
            else if (taskToRun == 18) {
                System.out.println("Please enter the name of your CSV data file to import (Ex: myInputFile.csv). Please make sure your file is located in the project directory.");
                userInput = sc.nextLine();
                System.out.println(userInput);
                QUERY18(conn, userInput);   	
            } 
            
            else if (taskToRun == 19) {
                System.out.println("Please enter the name of your CSV data file to export (Ex: myExportFile.csv). The file will be placed in the project directory.");
                userInput = sc.nextLine();
                QUERY19(conn, userInput);
            } 
            
            else if (taskToRun == 20) {
                System.out.println("\n" + "CLOSING THE PAN CLIENT AND DONOR DATABASE SYSTEM, GOODBYE.");
                notDone = false;
            } 
            
            else {
            	System.out.print("\n");
                System.out.println(taskToRun + " isn't a valid task number. Try again!");
                System.out.print("\n");
            }
            
        } while (notDone);
        sc.close();
        conn.close();
    }
    
    // Step 6. Call specified SQL procedure:
    
    /** This method calls the SQL procedure QUERY1
     * @task: Enter a new team into the database.
     * @params: Team name, Team type, Date of formation, Team lead
     */
    public static void QUERY1(Connection conn, String arg1, String arg2, String arg3, String arg4) {
        try {
            CallableStatement stmt = conn.prepareCall("{call QUERY1(?, ?, ?, ?)}");
            stmt.setString(1, arg1);
            stmt.setString(2, arg2);
            stmt.setString(3, arg3);
            stmt.setString(4, arg4);
            stmt.execute();
            System.out.println("New team successfully created!");
        } catch (SQLException e) {
            System.out.println("(ERROR) SQL exception: " + e.getMessage());
        }
    }
    
    /** This method calls the SQL procedure QUERY2
     * @task: Enter a new client into the database and associate him or her with one or more teams.
     * @params: Email address, Mailing address, Home phone number, Work phone number, Cell phone number, SSN, Name, DoB (Ex: 121495 = Dec. 14th, 1995), Race, Gender, 
     * Profession, Is on mailing list (Y or N), Is affiliated with external organization (Y or N), Doctors name, Attorneys name, Doctors number, Attorneys number, 
     * Date of assignment, Team name, Is active (Y or N)
     */
    public static void QUERY2(Connection conn, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9, String arg10, String arg11, String arg12, String arg13, String arg14, String arg15, String arg16, String arg17, String arg18, String arg19, String arg20) {
        try {
            CallableStatement stmt = conn.prepareCall("{call QUERY2(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            stmt.setString(1, arg1);
            stmt.setString(2, arg2);
            stmt.setString(3, arg3);
            stmt.setString(4, arg4);
            stmt.setString(5, arg5);
            stmt.setString(6, arg6);
            stmt.setString(7, arg7);
            stmt.setString(8, arg8);
            stmt.setString(9, arg9);
            stmt.setString(10, arg10);
            stmt.setString(11, arg11);
            stmt.setString(12, arg12);
            stmt.setString(13, arg13);
            stmt.setString(14, arg14);
            stmt.setString(15, arg15);
            stmt.setString(16, arg16);
            stmt.setString(17, arg17);
            stmt.setString(18, arg18);
            stmt.setString(19, arg19);
            stmt.setString(20, arg20);
            stmt.execute();
            System.out.println("New client successfully created!");
        } catch (SQLException e) {
            System.out.println("(ERROR) SQL exception: " + e.getMessage());
        }
    }
    
    /**This method calls the SQL procedure QUERY3
     * @task: Enter a new volunteer into the database and associate him or her with one or more teams.
     * @params: Email address input, Mailing address, Home phone number, Work phone number, Cell phone number, SSN, Name, DoB, Race, Gender, Profession, 
     * Is on mailing list (Y or N), Is affiliated with external org. (Y or N), Join date, Training date, Training location, Team name, Is active (Y or N), 
     * Jan hours worked, Feb hours worked, March hours worked, April hours worked, May hours worked, June hours worked, July hours worked, Aug hours worked, 
     * Sept hours worked, Oct hours worked, Nov hours worked, Dec hours worked)
     */
    public static void QUERY3(Connection conn, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9, String arg10, String arg11, String arg12, String arg13, String arg14, String arg15, String arg16, String arg17, String arg18, String arg19, String arg20, String arg21, String arg22, String arg23, String arg24, String arg25, String arg26, String arg27, String arg28, String arg29, String arg30) {
        try {
            CallableStatement stmt = conn.prepareCall("{call QUERY3(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            stmt.setString(1, arg1);
            stmt.setString(2, arg2);
            stmt.setString(3, arg3);
            stmt.setString(4, arg4);
            stmt.setString(5, arg5);
            stmt.setString(6, arg6);
            stmt.setString(7, arg7);
            stmt.setString(8, arg8);
            stmt.setString(9, arg9);
            stmt.setString(10, arg10);
            stmt.setString(11, arg11);
            stmt.setString(12, arg12);
            stmt.setString(13, arg13);
            stmt.setString(14, arg14);
            stmt.setString(15, arg15);
            stmt.setString(16, arg16);
            stmt.setString(17, arg17);
            stmt.setString(18, arg18);
            stmt.setString(19, arg19);
            stmt.setString(20, arg20);
            stmt.setString(21, arg21);
            stmt.setString(22, arg22);
            stmt.setString(23, arg23);
            stmt.setString(24, arg24);
            stmt.setString(25, arg25);
            stmt.setString(26, arg26);
            stmt.setString(27, arg27);
            stmt.setString(28, arg28);
            stmt.setString(29, arg29);
            stmt.setString(30, arg30);
            stmt.execute();
            System.out.println("New client successfully created!");
        } catch (SQLException e) {
            System.out.println("(ERROR) SQL exception: " + e.getMessage());
        }
    }
    
    /**
     * This method calls the SQL procedure QUERY4
     * @task: Enter the number of hours a volunteer worked this month for a particular team.
     * @params: SSN, Jan hours worked, Feb hours worked, March hours worked, April hours worked, May hours worked, June hours worked, 
     * July hours worked, Aug hours worked, Sept hours worked, Oct hours worked, Nov hours worked, Dec hours worked)
     */
    public static void QUERY4(Connection conn, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9, String arg10, String arg11, String arg12, String arg13) {
        try {
            CallableStatement stmt = conn.prepareCall("{call QUERY4(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            stmt.setString(1, arg1);
            stmt.setString(2, arg2);
            stmt.setString(3, arg3);
            stmt.setString(4, arg4);
            stmt.setString(5, arg5);
            stmt.setString(6, arg6);
            stmt.setString(7, arg7);
            stmt.setString(8, arg8);
            stmt.setString(9, arg9);
            stmt.setString(10, arg10);
            stmt.setString(11, arg11);
            stmt.setString(12, arg12);
            stmt.setString(13, arg13);
            stmt.execute();
            System.out.println("New hours a volunteer worked successfully created!");
        } catch (SQLException e) {
            System.out.println("(ERROR) SQL exception: " + e.getMessage());
        }
    }
    
    /**
     * This method calls the SQL procedure QUERY5
     * @task: Enter a new employee into the database and associate him or her with one or more teams.
     * @params: Email address, Mailing address, Home phone number, Work phone number, Cell phone number, SSN, Name, DoB (Ex: 121495 = Dec. 14th, 1995), Race, Gender, 
     * Profession, Is on mailing list (Y or N), Is affiliated with external organization (Y or N), Salary, Martial status, Hire date, Team name, 
     * Date of report (Ex: 121495 = Dec. 14th, 1995), Description
     */
    public static void QUERY5(Connection conn, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9, String arg10, String arg11, String arg12, String arg13, String arg14, String arg15, String arg16, String arg17, String arg18, String arg19) {
        try {
            CallableStatement stmt = conn.prepareCall("{call QUERY5(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            stmt.setString(1, arg1);
            stmt.setString(2, arg2);
            stmt.setString(3, arg3);
            stmt.setString(4, arg4);
            stmt.setString(5, arg5);
            stmt.setString(6, arg6);
            stmt.setString(7, arg7);
            stmt.setString(8, arg8);
            stmt.setString(9, arg9);
            stmt.setString(10, arg10);
            stmt.setString(11, arg11);
            stmt.setString(12, arg12);
            stmt.setString(13, arg13);
            stmt.setString(14, arg14);
            stmt.setString(15, arg15);
            stmt.setString(16, arg16);
            stmt.setString(17, arg17);
            stmt.setString(18, arg18);
            stmt.setString(19, arg19);
            stmt.execute();
            System.out.println("New employee successfully created!");
        } catch (SQLException e) {
            System.out.println("(ERROR) SQL exception: " + e.getMessage());
        }
    }
    
    /**
     * This method calls the SQL procedure QUERY6
     * @task: Enter an expense charged by an employee.
     * @params: SSN, Expense date (Ex: 121495 = Dec. 14th, 1995), Expense amount, Expense description
     */
    public static void QUERY6(Connection conn, String arg1, String arg2, String arg3, String arg4) {
        try {
            CallableStatement stmt = conn.prepareCall("{call QUERY6(?, ?, ?, ?)}");
            stmt.setString(1, arg1);
            stmt.setString(2, arg2);
            stmt.setString(3, arg3);
            stmt.setString(4, arg4);
            stmt.execute();
            System.out.println("New expense successfully created!");
        } catch (SQLException e) {
            System.out.println("(ERROR) SQL exception: " + e.getMessage());
        }
    }
    
    /**
    * This method calls the SQL procedure QUERY7
    * @task: Enter a new organization and associate it to one or more PAN teams.
    * @params: Organization name, SSN, Organization mailing address, Organization phone number, Organization contact person, Is org anonymous (Y or N), Team name
    */
    public static void QUERY7(Connection conn, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7) {
        try {
            CallableStatement stmt = conn.prepareCall("{call QUERY7(?, ?, ?, ?, ?, ?, ?)}");
            stmt.setString(1, arg1);
            stmt.setString(2, arg2);
            stmt.setString(3, arg3);
            stmt.setString(4, arg4);
            stmt.setString(5, arg5);
            stmt.setString(6, arg6);
            stmt.setString(7, arg7);
            stmt.execute();
            System.out.println("New organization successfully created!");
        } catch (SQLException e) {
            System.out.println("(ERROR) SQL exception: " + e.getMessage());
        }
    }
    
    /**
     * This method calls the SQL procedure QUERY8
     * @task: Enter a new donor and associate him or her with several donations.
     * @params: Email address, Mailing address, Home phone number, Work phone number, Cell phone number, SSN, Name, DoB (Ex: 121495 = Dec. 14th, 1995), Race, Gender, 
     * Profession, Is on mailing list (Y or N), Is affiliated with external organization (Y or N), Is donor anonymous (Y or N)
     */
    public static void QUERY8(Connection conn, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9, String arg10, String arg11, String arg12, String arg13, String arg14) {
        try {
            CallableStatement stmt = conn.prepareCall("{call QUERY8(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            stmt.setString(1, arg1);
            stmt.setString(2, arg2);
            stmt.setString(3, arg3);
            stmt.setString(4, arg4);
            stmt.setString(5, arg5);
            stmt.setString(6, arg6);
            stmt.setString(7, arg7);
            stmt.setString(8, arg8);
            stmt.setString(9, arg9);
            stmt.setString(10, arg10);
            stmt.setString(11, arg11);
            stmt.setString(12, arg12);
            stmt.setString(13, arg13);
            stmt.setString(14, arg14);
            stmt.execute();
            System.out.println("New donor successfully created!");
        } catch (SQLException e) {
            System.out.println("(ERROR) SQL exception: " + e.getMessage());
        }
    }
    
    /**
     * This method calls the SQL procedure QUERY9
     * @task: Enter a new organization and associate it with several donations.
     * @params: Organization name, SSN, Organization mailing address, Organization phone number, Organization contact person, Is org anonymous (Y or N)
     */
    public static void QUERY9(Connection conn, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6) {
        try {
            CallableStatement stmt = conn.prepareCall("{call QUERY9(?, ?, ?, ?, ?, ?)}");
            stmt.setString(1, arg1);
            stmt.setString(2, arg2);
            stmt.setString(3, arg3);
            stmt.setString(4, arg4);
            stmt.setString(5, arg5);
            stmt.setString(6, arg6);
            stmt.execute();
            System.out.println("New organization successfully created!");
        } catch (SQLException e) {
            System.out.println("(ERROR) SQL exception: " + e.getMessage());
        }
    }
    
    /**
     * This method calls the SQL procedure QUERY10
     * @task: Retrieve the name and phone number of the doctor of a particular client.
     * @params: SSN
     */
    public static void QUERY10(Connection conn, String arg1) {
        try {
            CallableStatement stmt = conn.prepareCall("{call QUERY10(?, ?, ?)}");
            stmt.setString(1, arg1);
            stmt.registerOutParameter(2, Types.VARCHAR);
            stmt.registerOutParameter(3, Types.NUMERIC);
            stmt.execute();
            System.out.println("\n" + "RESULTS:" + "\n");
            System.out.println("Name of the client's doctor: " + stmt.getString(2));
            System.out.println("Number of the client's doctor: " + stmt.getLong(3) + "\n");
        } catch (SQLException e) {
            System.out.println("(ERROR) SQL exception: " + e.getMessage());
        }
    }
    
    /**
     * This method calls the SQL procedure QUERY11
     * @task: Retrieve the total amount of expenses charged by each employee for a particular period of time.  The list should be sorted by the total 
     * amount of expenses.
     * @params: Expense date
     */
    public static void QUERY11(Connection conn, String arg1) {
        try {
            CallableStatement stmt = conn.prepareCall("{call QUERY11(?, ?)}");
            stmt.setString(1, arg1);
            stmt.registerOutParameter(2, OracleTypes.CURSOR);
            stmt.execute();
            System.out.println("\n" + "RESULTS:" + "\n");
            System.out.println("EXPENSE AMOUNT, SSN");
            ResultSet rs = (ResultSet) stmt.getObject(2);
            while(rs.next()) {
            	System.out.println("$" + rs.getString("SUM(EXPENSE_AMOUNT)") + ", " + rs.getString("SSN"));
            }
            System.out.print("\n");
        } catch (SQLException e) {
            System.out.println("(ERROR) SQL exception: " + e.getMessage());
        }
    }
    
    /**
     * This method calls the SQL procedure QUERY12
     * @task: Retrieve the list of volunteers that are members of teams that support a particular client.
     * @params: SSN
     */
    public static void QUERY12(Connection conn, String arg1) {
        try {
            CallableStatement stmt = conn.prepareCall("{call QUERY12(?, ?)}");
            stmt.setString(1, arg1);
            stmt.registerOutParameter(2, OracleTypes.CURSOR);
            stmt.execute();
            System.out.println("\n" + "RESULTS:" + "\n");
            System.out.println("SSN, TEAM NAME");
            ResultSet rs = (ResultSet) stmt.getObject(2);
            while(rs.next()) {
            	System.out.println(rs.getString("SSN") + ", " + rs.getString("TEAM_NAME"));
            }
            System.out.print("\n");
        } catch (SQLException e) {
            System.out.println("(ERROR) SQL exception: " + e.getMessage());
        }
    }
    
    /**
     * This method calls the SQL procedure QUERY13
     * @task: Retrieve the names and contact information of the clients that are supported by teams sponsored by an organization whose name starts with a 
     * letter between B and K. The client list should be sorted by name.
     * @params: none, user input isn't needed
     */
    public static void QUERY13(Connection conn) {
        try {
            CallableStatement stmt = conn.prepareCall("{call QUERY13(?)}");
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.execute();
            System.out.println("\n" + "RESULTS:" + "\n");
            System.out.println("NAME, EMAIL ADDRESS, MAILING ADDRESS, HOME PHONE, WORK PHONE, CELL PHONE");
            ResultSet rs = (ResultSet) stmt.getObject(1);
            while(rs.next()) {
            	System.out.println(rs.getString("NAME") + ", " + rs.getString("EMAIL_ADDRESS") + ", " + rs.getString("MAILING_ADDRESS") + ", " + rs.getString("HOME_PHONE_NUMBER") + ", " + rs.getString("WORK_PHONE_NUMBER") + ", " + rs.getString("CELL_PHONE_NUMBER"));
            }
            System.out.print("\n");
        } catch (SQLException e) {
            System.out.println("(ERROR) SQL exception: " + e.getMessage());
        }
    }
    
    /**
     * This method calls the SQL procedure QUERY14
     * @task: Retrieve the name and total amount donated by donors that are also employees.  The list should be sorted by the total amount of the donations, 
     * and indicate if each donor wishes to remain anonymous.
     * @params: none, user input isn't needed
     */
    public static void QUERY14(Connection conn) {
        try {
            CallableStatement stmt = conn.prepareCall("{call QUERY14(?, ?, ?)}");
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.registerOutParameter(2, OracleTypes.CURSOR);
            stmt.registerOutParameter(3, OracleTypes.CURSOR);
            stmt.execute();
            System.out.println("\n" + "RESULTS:" + "\n");
            ResultSet rs1 = (ResultSet) stmt.getObject(1);
            while(rs1.next()) {
            	System.out.println("Total Amount of Donations: $" + rs1.getString("SUM(DONATION_AMOUNT)"));
            }
            ResultSet rs2 = (ResultSet) stmt.getObject(2);
            System.out.print("Contributors: ");
            while(rs2.next()) {
            	System.out.print(rs2.getString("NAME") + ", ");
            }
            ResultSet rs3 = (ResultSet) stmt.getObject(3);
            System.out.print("\n");
            System.out.print("Is Donor Anonymous (True or False): ");
            while(rs3.next()) {
            	System.out.print(rs3.getString("ISDONORANONYMOUS") + ", ");
            }
            System.out.println("\n");
        } catch (SQLException e) {
            System.out.println("(ERROR) SQL exception: " + e.getMessage());
        }
    }
    
    /**
     * This method calls the SQL procedure QUERY15
     * @task: For each team, retrieve the name and associated contact information of the volunteer that has worked the most total hours between March and June.
     * @params: none, user input isn't needed
     */
    public static void QUERY15(Connection conn) {
        try {
            CallableStatement stmt = conn.prepareCall("{call QUERY15(?)}");
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.execute();
            System.out.println("\n" + "RESULTS:" + "\n");
            System.out.println("EMAIL ADDRESS, MAILING ADDRESS, HOME PHONE, WORK PHONE, CELL PHONE, NAME, TEAM, HOURS");
            ResultSet rs = (ResultSet) stmt.getObject(1);
            while(rs.next()) {
            	System.out.println(rs.getString("EMAIL_ADDRESS") + ", " + rs.getString("MAILING_ADDRESS") + ", " + rs.getString("HOME_PHONE_NUMBER") + ", " + rs.getString("WORK_PHONE_NUMBER") + ", " + rs.getString("CELL_PHONE_NUMBER") +  ", " + rs.getString("NAME") +  ", " + rs.getString("TEAM_NAME") +  ", " + rs.getString("MAXSUM"));
            }
            System.out.print("\n");
        } catch (SQLException e) {
            System.out.println("(ERROR) SQL exception: " + e.getMessage());
        }
    }
    
    /**
     * This method calls the SQL procedure QUERY16
     * @task: Increase the salary by 10% of all employees to whom more than one team must report.
     * @params: none, user input isn't needed
     */
    public static void QUERY16(Connection conn) {
        try {
            CallableStatement stmt = conn.prepareCall("{call QUERY16()}");
            stmt.execute();
            System.out.println("\n" + "Salaries successfully updated!" + "\n");
        } catch (SQLException e) {
            System.out.println("(ERROR) SQL exception: " + e.getMessage());
        }
    }
    
    /**
     * This method calls the SQL procedure QUERY17
     * @task: Delete all clients who do not have health insurance and whose value of importance for transportation is less than 5.
     * @params: none, user input isn't needed
     */
    public static void QUERY17(Connection conn) {
        try {
            CallableStatement stmt = conn.prepareCall("{call QUERY17()}");
            stmt.execute();
            System.out.println("\n" + "Clients successfully updated!" + "\n");
        } catch (SQLException e) {
            System.out.println("(ERROR) SQL exception: " + e.getMessage());
        }
    }
    
    /**
     * This method calls the SQL procedure QUERY18
     * @task: Import: Enter new teams from a data file until the file is empty 
     * (the user should be asked to enter the input file name).
     * @params: Name of file
     */
    public static void QUERY18(Connection conn, String nameOfFile) {
        try {
        	// Can call query1, because it already handles adding data into the Team table
        	CallableStatement stmt = conn.prepareCall("{call QUERY1(?, ?, ?, ?)}");
        	CSVReader reader = null;
			try {
				reader = new CSVReader(new FileReader(nameOfFile));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}         
            String [] nextLine; 
            int lineNum = 0; 
            // Loop through the entire file, add each record (set) to the batch
            try {
				while ((nextLine = reader.readNext()) != null) {
					lineNum++;
					stmt.setString(1, nextLine[0]);
					stmt.setString(2, nextLine[1]);
					stmt.setDouble(3, Integer.parseInt(nextLine[2]));
					stmt.setString(4, nextLine[3]);
				    // Add the record to batch
					stmt.addBatch();
				}
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}                       
            // Start bulk batch insert             
            int[] totalRecords = new int[lineNum];
            try {
                    totalRecords = stmt.executeBatch();
            } catch(BatchUpdateException e) {
                    totalRecords = e.getUpdateCounts();
                    System.out.println("(ERROR) INSERTION exception: " + e.getMessage());
            }
            System.out.println("Total records inserted in bulk from CSV file: " + totalRecords.length);
            if(totalRecords.length > 1) {
            	System.out.println("New teams successfully imported!" + "\n");
            }
            else {
            	System.out.println("New team successfully imported!" + "\n");
            }
        	try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        } catch (SQLException e) {
            System.out.println("(ERROR) IMPORT exception: " + e.getMessage());
        }
    }
    
    /**
     * This method calls the SQL procedure QUERY19
     * @task: Export: Retrieve names and mailing addresses of all people on the mailing list and output them to a data file instead of screen 
     * (the user should be asked to enter the output file name).
     * @params: Name of file
     */
    public static void QUERY19(Connection conn, String nameOfFile) {
        try {
            CallableStatement stmt = conn.prepareCall("{call QUERY19(?)}");
            CSVWriter writer = null;
			try {
				writer = new CSVWriter(new FileWriter(nameOfFile));
			} catch (IOException e) {
				e.printStackTrace();
			}
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.execute();
            ResultSet rs = (ResultSet) stmt.getObject(1);
            // Writes all data from ResultSet to output file. Includes column names.
            try {
				writer.writeAll(rs, true);
			} catch (IOException e) {
				e.printStackTrace();
			}
            try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
            System.out.println("\n" + "Data successfully exported to " + nameOfFile + "\n");
        } catch (SQLException e) {
            System.out.println("(ERROR) EXPORT exception: " + e.getMessage());
        }
    }
}
