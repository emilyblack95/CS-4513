CREATE OR REPLACE PACKAGE QUERIES AS
  PROCEDURE QUERY1
    (Team_Name_Input IN VARCHAR2, Team_Type_Input IN VARCHAR2, Date_of_Formation_Input, Team_Lead_Input IN VARCHAR2);
  PROCEDURE QUERY2
    (Email_Address_Input IN VARCHAR2, Mailing_Address_Input IN VARCHAR2, Home_Phone_Number_Input, Work_Phone_Number_Input, Cell_Phone_Number_Input, SSN_Input, Name_Input IN VARCHAR2, DoB_Input, Race_Input IN VARCHAR2, Gender_Input IN VARCHAR2, Profession_Input IN VARCHAR2, IsOnMailingList_Input IN VARCHAR2, IsAffiliatedWithExtOrg_Input IN VARCHAR2, Doctors_Name_Input IN VARCHAR2, Attorneys_Name_Input IN VARCHAR2, Doctors_Number_Input, Attorneys_Number_Input, Date_of_Assignment_Input, Team_Name_Input IN VARCHAR2, IsActive_Input IN VARCHAR2);
  PROCEDURE QUERY3
    (Email_Address_Input IN VARCHAR2, Mailing_Address_Input IN VARCHAR2, Home_Phone_Number_Input, Work_Phone_Number_Input, Cell_Phone_Number_Input, SSN_Input, Name_Input IN VARCHAR2, DoB_Input, Race_Input IN VARCHAR2, Gender_Input IN VARCHAR2, Profession_Input IN VARCHAR2, IsOnMailingList_Input IN VARCHAR2, IsAffiliatedWithExtOrg_Input IN VARCHAR2, Join_Date_Input, Training_Date_Input, Training_Location_Input IN VARCHAR2, Team_Name_Input IN VARCHAR2, IsActive_Input IN VARCHAR2, Jan_Hours_Worked_Input, Feb_Hours_Worked_Input, March_Hours_Worked_Input, April_Hours_Worked_Input, May_Hours_Worked_Input, June_Hours_Worked_Input, July_Hours_Worked_Input, Aug_Hours_Worked_Input, Sept_Hours_Worked_Input, Oct_Hours_Worked_Input, Nov_Hours_Worked_Input, Dec_Hours_Worked_Input IN NUMBER);
  PROCEDURE QUERY4
    (SSN_Input IN NUMBER, Jan_Hours_Worked_Input IN NUMBER, Feb_Hours_Worked_Input IN NUMBER, March_Hours_Worked_Input IN NUMBER, April_Hours_Worked_Input IN NUMBER, May_Hours_Worked_Input IN NUMBER, June_Hours_Worked_Input IN NUMBER, July_Hours_Worked_Input IN NUMBER, Aug_Hours_Worked_Input IN NUMBER, Sept_Hours_Worked_Input IN NUMBER, Oct_Hours_Worked_Input IN NUMBER, Nov_Hours_Worked_Input IN NUMBER, Dec_Hours_Worked_Input IN NUMBER);
  PROCEDURE QUERY5
    (Email_Address_Input IN VARCHAR2, Mailing_Address_Input IN VARCHAR2, Home_Phone_Number_Input, Work_Phone_Number_Input, Cell_Phone_Number_Input, SSN_Input, Name_Input IN VARCHAR2, DoB_Input, Race_Input IN VARCHAR2, Gender_Input IN VARCHAR2, Profession_Input IN VARCHAR2, IsOnMailingList_Input IN VARCHAR2, IsAffiliatedWithExtOrg_Input IN VARCHAR2, Salary_Input, Martial_Status_Input IN VARCHAR2, Hire_Date_Input, Team_Name_Input IN VARCHAR2, Date_of_Report_Input, Description_Input IN VARCHAR2);
  PROCEDURE QUERY6
    (SSN_Input, Expense_Date_Input, Expense_Amount_Input, Expense_Description_Input IN VARCHAR2);
  PROCEDURE QUERY7
    (Organization_Name_Input IN VARCHAR2, SSN_Input, Org_Mailing_Address_Input IN VARCHAR2, Org_Phone_Number_Input, Org_Contact_Person_Input IN VARCHAR2, IsOrgAnonymous_Input IN VARCHAR2, Team_Name_Input IN VARCHAR2);
  PROCEDURE QUERY8
    (Email_Address_Input IN VARCHAR2, Mailing_Address_Input IN VARCHAR2, Home_Phone_Number_Input IN NUMBER, Work_Phone_Number_Input IN NUMBER, Cell_Phone_Number_Input IN NUMBER, SSN_Input IN NUMBER, Name_Input IN VARCHAR2, DoB_Input IN NUMBER, Race_Input IN VARCHAR2, Gender_Input IN VARCHAR2, Profession_Input IN VARCHAR2, IsOnMailingList_Input IN VARCHAR2, IsAffiliatedWithExtOrg_Input IN VARCHAR2, IsDonorAnonymous_Input IN VARCHAR2);
  PROCEDURE QUERY9
	(Organization_Name_Input IN VARCHAR2, SSN_Input, Org_Mailing_Address_Input IN VARCHAR2, Org_Phone_Number_Input, Org_Contact_Person_Input IN VARCHAR2, IsOrgAnonymous_Input IN VARCHAR2);
  PROCEDURE QUERY10
    (SSN_Input, Doctors_Name_Output OUT VARCHAR2, Doctors_Number_Output OUT NUMBER);
  PROCEDURE QUERY11
    (Expense_Date_Input, Result_Table_Output OUT SYS_REFCURSOR);
  PROCEDURE QUERY12
    (SSN_Input, Result_Table_Output OUT SYS_REFCURSOR);
  PROCEDURE QUERY13
    (Result_Table_Output OUT SYS_REFCURSOR);
  PROCEDURE QUERY14
    (Donation_Result_Table_Output OUT SYS_REFCURSOR, Name_Result_Table_Output OUT SYS_REFCURSOR, Isanon_Result_Table_Output OUT SYS_REFCURSOR);
  PROCEDURE QUERY15
    (Result_Table_Output OUT SYS_REFCURSOR);
  PROCEDURE QUERY16;
  PROCEDURE QUERY17;
  PROCEDURE QUERY19
    (Result_Table_Output OUT SYS_REFCURSOR);

END QUERIES;

CREATE OR REPLACE PACKAGE BODY QUERIES AS

    --Query 1: Enter a new team into the database
	CREATE OR REPLACE PROCEDURE QUERY1 
	(Team_Name_Input IN VARCHAR2, Team_Type_Input IN VARCHAR2, Date_of_Formation_Input IN NUMBER, Team_Lead_Input IN VARCHAR2) AS
	BEGIN
		INSERT INTO TEAM(Team_Name, Team_Type, Date_of_Formation, Team_Lead)
		VALUES(Team_Name_Input, Team_Type_Input, Date_of_Formation_Input, Team_Lead_Input);
	END;
    
    --Query 2: Enter a new client into the database and associate him or her with one or more teams (assumes the client's team already exists)
	CREATE OR REPLACE PROCEDURE QUERY2
	(Email_Address_Input IN VARCHAR2, Mailing_Address_Input IN VARCHAR2, Home_Phone_Number_Input IN NUMBER, Work_Phone_Number_Input IN NUMBER, Cell_Phone_Number_Input IN NUMBER, SSN_Input IN NUMBER, Name_Input IN VARCHAR2, DoB_Input IN NUMBER, Race_Input IN VARCHAR2, Gender_Input IN VARCHAR2, Profession_Input IN VARCHAR2, IsOnMailingList_Input IN VARCHAR2, IsAffiliatedWithExtOrg_Input IN VARCHAR2, Doctors_Name_Input IN VARCHAR2, Attorneys_Name_Input IN VARCHAR2, Doctors_Number_Input IN NUMBER, Attorneys_Number_Input IN NUMBER, Date_of_Assignment_Input IN NUMBER, Team_Name_Input IN VARCHAR2, IsActive_Input IN VARCHAR2) AS
	BEGIN
		INSERT INTO CONTACT_INFO(Email_Address, Mailing_Address, Home_Phone_Number, Work_Phone_Number, Cell_Phone_Number)
		VALUES(Email_Address_Input, Mailing_Address_Input, Home_Phone_Number_Input, Work_Phone_Number_Input, Cell_Phone_Number_Input);
		INSERT INTO PERSON(SSN, Email_Address, Name, DoB, Race, Gender, Profession, IsOnMailingList, IsAffiliatedWithExtOrg)
		VALUES(SSN_Input, Email_Address_Input, Name_Input, DoB_Input, Race_Input, Gender_Input, Profession_Input, IsOnMailingList_Input, IsAffiliatedWithExtOrg_Input);
		INSERT INTO CLIENT(SSN, Doctors_Name, Attorneys_Name, Doctors_Number, Attorneys_Number, Date_of_Assignment)
		VALUES(SSN_Input, Doctors_Name_Input, Attorneys_Name_Input, Doctors_Number_Input, Attorneys_Number_Input, Date_of_Assignment_Input);
		INSERT INTO CARES_FOR(SSN, Team_Name, IsActive)
		VALUES(SSN_Input, Team_Name_Input, IsActive_Input);
	END;
    
    --Query 3: Enter a new volunteer into the database and associate him or her with one or more teams (assumes the volunteer's team already exists)
    CREATE OR REPLACE PROCEDURE QUERY3
	(Email_Address_Input IN VARCHAR2, Mailing_Address_Input IN VARCHAR2, Home_Phone_Number_Input IN NUMBER, Work_Phone_Number_Input IN NUMBER, Cell_Phone_Number_Input IN NUMBER, SSN_Input IN NUMBER, Name_Input IN VARCHAR2, DoB_Input IN NUMBER, Race_Input IN VARCHAR2, Gender_Input IN VARCHAR2, Profession_Input IN VARCHAR2, IsOnMailingList_Input IN VARCHAR2, IsAffiliatedWithExtOrg_Input IN VARCHAR2, Join_Date_Input IN NUMBER, Training_Date_Input IN NUMBER, Training_Location_Input IN VARCHAR2, Team_Name_Input IN VARCHAR2, IsActive_Input IN VARCHAR2, Jan_Hours_Worked_Input IN NUMBER, Feb_Hours_Worked_Input IN NUMBER, March_Hours_Worked_Input IN NUMBER, April_Hours_Worked_Input IN NUMBER, May_Hours_Worked_Input IN NUMBER, June_Hours_Worked_Input IN NUMBER, July_Hours_Worked_Input IN NUMBER, Aug_Hours_Worked_Input IN NUMBER, Sept_Hours_Worked_Input IN NUMBER, Oct_Hours_Worked_Input IN NUMBER, Nov_Hours_Worked_Input IN NUMBER, Dec_Hours_Worked_Input IN NUMBER) AS
	BEGIN
		INSERT INTO CONTACT_INFO(Email_Address, Mailing_Address, Home_Phone_Number, Work_Phone_Number, Cell_Phone_Number)
		VALUES(Email_Address_Input, Mailing_Address_Input, Home_Phone_Number_Input, Work_Phone_Number_Input, Cell_Phone_Number_Input);
		INSERT INTO PERSON(SSN, Email_Address, Name, DoB, Race, Gender, Profession, IsOnMailingList, IsAffiliatedWithExtOrg)
		VALUES(SSN_Input, Email_Address_Input, Name_Input, DoB_Input, Race_Input, Gender_Input, Profession_Input, IsOnMailingList_Input, IsAffiliatedWithExtOrg_Input);
		INSERT INTO VOLUNTEER(SSN, Join_Date, Training_Date, Training_Location)
		VALUES(SSN_Input, Join_Date_Input, Training_Date_Input, Training_Location_Input);
		INSERT INTO SERVES_ON(SSN, Team_Name, IsActive, Jan_Hours_Worked, Feb_Hours_Worked, March_Hours_Worked, April_Hours_Worked, May_Hours_Worked, June_Hours_Worked, July_Hours_Worked, Aug_Hours_Worked, Sept_Hours_Worked, Oct_Hours_Worked, Nov_Hours_Worked, Dec_Hours_Worked)
		VALUES(SSN_Input, Team_Name_Input, IsActive_Input, Jan_Hours_Worked_Input, Feb_Hours_Worked_Input, March_Hours_Worked_Input, April_Hours_Worked_Input, May_Hours_Worked_Input, June_Hours_Worked_Input, July_Hours_Worked_Input, Aug_Hours_Worked_Input, Sept_Hours_Worked_Input, Oct_Hours_Worked_Input, Nov_Hours_Worked_Input, Dec_Hours_Worked_Input);
    END;
    
    --Query 4: Enter the number of hours a volunteer worked this month for a particular team (assumes volunteer already exists)
    CREATE OR REPLACE PROCEDURE QUERY4
	(SSN_Input IN NUMBER, Team_Name_Input IN VARCHAR2, IsActive_Input IN VARCHAR2, Jan_Hours_Worked_Input IN NUMBER, Feb_Hours_Worked_Input IN NUMBER, March_Hours_Worked_Input IN NUMBER, April_Hours_Worked_Input IN NUMBER, May_Hours_Worked_Input IN NUMBER, June_Hours_Worked_Input IN NUMBER, July_Hours_Worked_Input IN NUMBER, Aug_Hours_Worked_Input IN NUMBER, Sept_Hours_Worked_Input IN NUMBER, Oct_Hours_Worked_Input IN NUMBER, Nov_Hours_Worked_Input IN NUMBER, Dec_Hours_Worked_Input IN NUMBER) AS
	BEGIN
        UPDATE SERVES_ON
        SET 
        Jan_Hours_Worked = Jan_Hours_Worked + Jan_Hours_Worked_Input,
        Feb_Hours_Worked = Feb_Hours_Worked + Feb_Hours_Worked_Input,
        March_Hours_Worked = March_Hours_Worked + March_Hours_Worked_Input,
        April_Hours_Worked = April_Hours_Worked + April_Hours_Worked_Input,
        May_Hours_Worked = May_Hours_Worked + May_Hours_Worked_Input,
        June_Hours_Worked = June_Hours_Worked + June_Hours_Worked_Input,
        July_Hours_Worked = July_Hours_Worked + July_Hours_Worked_Input,
        Aug_Hours_Worked = Aug_Hours_Worked + Aug_Hours_Worked_Input,
        Sept_Hours_Worked = Sept_Hours_Worked + Sept_Hours_Worked_Input,
        Oct_Hours_Worked = Oct_Hours_Worked + Oct_Hours_Worked_Input,
        Nov_Hours_Worked =Nov_Hours_Worked + Nov_Hours_Worked_Input,
        Dec_Hours_Worked = Dec_Hours_Worked + Dec_Hours_Worked_Input
        WHERE SSN_Input = SERVES_ON.SSN;
    END;
    
    --Query 5: Enter a new employee into the database and associate him or her with one or more teams (assumes the employee's team already exists)
    CREATE OR REPLACE PROCEDURE QUERY5
	(Email_Address_Input IN VARCHAR2, Mailing_Address_Input IN VARCHAR2, Home_Phone_Number_Input IN NUMBER, Work_Phone_Number_Input IN NUMBER, Cell_Phone_Number_Input IN NUMBER, SSN_Input IN NUMBER, Name_Input IN VARCHAR2, DoB_Input IN NUMBER, Race_Input IN VARCHAR2, Gender_Input IN VARCHAR2, Profession_Input IN VARCHAR2, IsOnMailingList_Input IN VARCHAR2, IsAffiliatedWithExtOrg_Input IN VARCHAR2, Salary_Input IN NUMBER, Martial_Status_Input IN VARCHAR2, Hire_Date_Input IN NUMBER, Team_Name_Input IN VARCHAR2, Date_of_Report_Input IN NUMBER, Description_Input IN VARCHAR2) AS
	BEGIN
		INSERT INTO CONTACT_INFO(Email_Address, Mailing_Address, Home_Phone_Number, Work_Phone_Number, Cell_Phone_Number)
		VALUES(Email_Address_Input, Mailing_Address_Input, Home_Phone_Number_Input, Work_Phone_Number_Input, Cell_Phone_Number_Input);
		INSERT INTO PERSON(SSN, Email_Address, Name, DoB, Race, Gender, Profession, IsOnMailingList, IsAffiliatedWithExtOrg)
		VALUES(SSN_Input, Email_Address_Input, Name_Input, DoB_Input, Race_Input, Gender_Input, Profession_Input, IsOnMailingList_Input, IsAffiliatedWithExtOrg_Input);
		INSERT INTO EMPLOYEE(SSN, Salary, Martial_Status, Hire_Date)
		VALUES(SSN_Input, Salary_Input, Martial_Status_Input, Hire_Date_Input);
		INSERT INTO REPORTS_TO(Team_Name, SSN, Date_of_Report, Description)
		VALUES(Team_Name_Input, SSN_Input, Date_of_Report_Input, Description_Input);
    END;
    
    --Query 6: Enter an expense charged by an employee (assumes the employee already exists)
    CREATE OR REPLACE PROCEDURE QUERY6
	(SSN_Input IN NUMBER, Expense_Date_Input IN NUMBER, Expense_Amount_Input IN NUMBER, Expense_Description_Input IN VARCHAR2) AS
	BEGIN
		INSERT INTO EXPENSE(SSN, Expense_Date, Expense_Amount, Expense_Description)
		VALUES(SSN_Input, Expense_Date_Input, Expense_Amount_Input, Expense_Description_Input);
    END;
    
    --Query 7: Enter a new organization and associate it to one or more PAN teams (assumes the team/employee, associated with the ext_org, already exists) 
    -- (EXTERNAL_ORGANIZATION => SSN may be null)
    PROCEDURE QUERY7
	(Organization_Name_Input IN VARCHAR2, SSN_Input IN NUMBER, Org_Mailing_Address_Input IN VARCHAR2, Org_Phone_Number_Input IN NUMBER, Org_Contact_Person_Input IN VARCHAR2, IsOrgAnonymous_Input IN VARCHAR2, Team_Name_Input IN VARCHAR2) AS
	BEGIN
		INSERT INTO EXTERNAL_ORGANIZATION(Organization_Name, SSN, Org_Mailing_Address, Org_Phone_Number, Org_Contact_Person, IsOrgAnonymous)
		VALUES(Organization_Name_Input, SSN_Input, Org_Mailing_Address_Input, Org_Phone_Number_Input, Org_Contact_Person_Input, IsOrgAnonymous_Input);
		INSERT INTO MAY_SPONSOR(Organization_Name, Team_Name)
		VALUES(Organization_Name_Input, Team_Name_Input);
    END;
    
    --Query 8: Enter a new donor and associate him or her with several donations (assumes the donor's donations already exist) 
    PROCEDURE QUERY8
	(Email_Address_Input IN VARCHAR2, Mailing_Address_Input IN VARCHAR2, Home_Phone_Number_Input IN NUMBER, Work_Phone_Number_Input IN NUMBER, Cell_Phone_Number_Input IN NUMBER, SSN_Input IN NUMBER, Name_Input IN VARCHAR2, DoB_Input IN NUMBER, Race_Input IN VARCHAR2, Gender_Input IN VARCHAR2, Profession_Input IN VARCHAR2, IsOnMailingList_Input IN VARCHAR2, IsAffiliatedWithExtOrg_Input IN VARCHAR2, IsDonorAnonymous_Input IN VARCHAR2) AS
	BEGIN
		INSERT INTO CONTACT_INFO(Email_Address, Mailing_Address, Home_Phone_Number, Work_Phone_Number, Cell_Phone_Number)
		VALUES(Email_Address_Input, Mailing_Address_Input, Home_Phone_Number_Input, Work_Phone_Number_Input, Cell_Phone_Number_Input);
		INSERT INTO PERSON(SSN, Email_Address, Name, DoB, Race, Gender, Profession, IsOnMailingList, IsAffiliatedWithExtOrg)
		VALUES(SSN_Input, Email_Address_Input, Name_Input, DoB_Input, Race_Input, Gender_Input, Profession_Input, IsOnMailingList_Input, IsAffiliatedWithExtOrg_Input);
		INSERT INTO DONOR(SSN, IsDonorAnonymous)
		VALUES(SSN_Input, IsDonorAnonymous_Input);
    END;
    
    --Query 9: Enter a new organization and associate it with several donations (assumes the organization's donations/employee associated with the ext_org already exist)
    PROCEDURE QUERY9
	(Organization_Name_Input IN VARCHAR2, SSN_Input IN NUMBER, Org_Mailing_Address_Input IN VARCHAR2, Org_Phone_Number_Input IN NUMBER, Org_Contact_Person_Input IN VARCHAR2, IsOrgAnonymous_Input IN VARCHAR2) AS
	BEGIN
		INSERT INTO EXTERNAL_ORGANIZATION(Organization_Name, SSN, Org_Mailing_Address, Org_Phone_Number, Org_Contact_Person, IsOrgAnonymous)
		VALUES(Organization_Name_Input, SSN_Input, Org_Mailing_Address_Input, Org_Phone_Number_Input, Org_Contact_Person_Input, IsOrgAnonymous_Input);
    END;
    
    --Query 10: Retrieve the name and phone number of the doctor of a particular client
	PROCEDURE QUERY10
	(SSN_Input IN NUMBER, Doctors_Name_Output OUT VARCHAR2, Doctors_Number_Output OUT NUMBER) AS
	BEGIN
		SELECT Doctors_Name INTO Doctors_Name_Output
		FROM Client
		WHERE Client.SSN = SSN_Input;
		
		SELECT Doctors_Number INTO Doctors_Number_Output
		FROM Client
		WHERE Client.SSN = SSN_Input;
	END;
    
    --Query 11: Retrieve the total amount of expenses charged by each employee for a particular period of time. The list should be sorted by the total amount of expenses
	PROCEDURE QUERY11 
	(Expense_Date_Input IN NUMBER, Result_Table_Output OUT SYS_REFCURSOR) AS 
	BEGIN
		OPEN Result_Table_Output FOR
		SELECT SSN, SUM(Expense_Amount) 
		FROM Expense
		WHERE Expense.EXPENSE_DATE = Expense_Date_Input
		GROUP BY SSN
		ORDER BY SUM(Expense_Amount);
	END;
	
    --Query 12: Retrieve the list of volunteers that are members of teams that support a particular client
	PROCEDURE QUERY12 
	(SSN_Input IN NUMBER, Result_Table_Output OUT SYS_REFCURSOR) AS 
	BEGIN
	  OPEN Result_Table_Output FOR
	  SELECT SSN, Team_Name
	  FROM Serves_on
	  WHERE Team_Name IN (SELECT Team_Name FROM Cares_for 
	  WHERE Serves_on.Team_Name = Cares_for.Team_Name AND SSN IN (SELECT SSN FROM Client WHERE Cares_for.SSN = Client.SSN AND Client.SSN = SSN_Input));
	END;
    
    --Query 13: Retrieve the names and contact information of the clients that are supported by teams sponsored by an organization whose name starts with a letter between B and K.  The client list should be sorted by name
	PROCEDURE QUERY13
	(Result_Table_Output OUT SYS_REFCURSOR) AS
	BEGIN
	  OPEN Result_Table_Output FOR
	  SELECT *
	  FROM CONTACT_INFO
	  FULL OUTER JOIN PERSON ON CONTACT_INFO.EMAIL_ADDRESS = PERSON.EMAIL_ADDRESS
	  WHERE SSN IN (SELECT SSN FROM CARES_FOR WHERE PERSON.SSN = CARES_FOR.SSN AND 
	  TEAM_NAME IN (SELECT TEAM_NAME FROM MAY_SPONSOR WHERE CARES_FOR.TEAM_NAME = MAY_SPONSOR.TEAM_NAME AND 
	  ORGANIZATION_NAME >= 'B' AND ORGANIZATION_NAME <= 'K'))
	  ORDER BY NAME;
	END;
    
    --Query 14: Retrieve the name and total amount donated by donors that are also employees.  The list should be sorted by the total amount of the donations, and indicate if each donor wishes to remain anonymous 
	PROCEDURE QUERY14 
	(Donation_Result_Table_Output OUT SYS_REFCURSOR, Name_Result_Table_Output OUT SYS_REFCURSOR, Isanon_Result_Table_Output OUT SYS_REFCURSOR) AS
	BEGIN
	  -- This cursor finds the sum of donations
	  OPEN Donation_Result_Table_Output FOR
	  SELECT SUM(DONATION_AMOUNT)
	  FROM DONATION
	  WHERE SSN IN (SELECT SSN FROM DONOR WHERE DONOR.SSN = DONATION.SSN AND
	  SSN IN (SELECT SSN FROM EMPLOYEE WHERE EMPLOYEE.SSN = DONOR.SSN))
	  ORDER BY SUM(DONATION_AMOUNT);
	  
	  -- This cursor finds the name
	  OPEN Name_Result_Table_Output FOR
	  SELECT NAME
	  FROM PERSON
	  WHERE SSN IN
	  (SELECT SSN FROM EMPLOYEE WHERE EMPLOYEE.SSN = PERSON.SSN AND
	  SSN IN (SELECT SSN FROM DONOR WHERE DONOR.SSN = EMPLOYEE.SSN));
	  
	  -- This cursor finds the isDonorAnan tag
	  OPEN Isanon_Result_Table_Output FOR
	  SELECT ISDONORANONYMOUS
	  FROM DONOR
	  WHERE SSN IN (SELECT SSN FROM EMPLOYEE WHERE EMPLOYEE.SSN = DONOR.SSN);
	END;
    
    --Query 15: For each team, retrieve the name and associated contact information of the volunteer that has worked the most total hours between March and June
	PROCEDURE QUERY15 
	(Result_Table_Output OUT SYS_REFCURSOR) AS
	BEGIN
	  OPEN Result_Table_Output FOR
	SELECT
	q0.*, q1.NAME, q4.TEAM_NAME, q4.MAXSUM
	FROM
		(SELECT * FROM CONTACT_INFO) q0
			INNER JOIN
			(SELECT SSN, NAME, EMAIL_ADDRESS FROM PERSON) q1
			ON q0.EMAIL_ADDRESS = q1.EMAIL_ADDRESS
				INNER JOIN
				(SELECT SSN FROM VOLUNTEER) q2
				ON q1.SSN = q2.SSN
					INNER JOIN
					(SELECT SSN, TEAM_NAME, MARCH_HOURS_WORKED+APRIL_HOURS_WORKED+MAY_HOURS_WORKED+JUNE_HOURS_WORKED AS SUM FROM SERVES_ON) q3
					ON q2.SSN = q3.SSN
						JOIN (SELECT TEAM_NAME, MAX(MARCH_HOURS_WORKED+APRIL_HOURS_WORKED+MAY_HOURS_WORKED+JUNE_HOURS_WORKED) AS MAXSUM FROM SERVES_ON GROUP BY TEAM_NAME) q4
						ON q3.SUM = q4.MAXSUM;
	END;
    
    --Query 16: Increase the salary by 10% of all employees to whom more than one team must report.
	PROCEDURE QUERY16 AS 
	BEGIN
	  UPDATE EMPLOYEE
	  SET SALARY = (CASE 
	  WHEN (SELECT COUNT(SSN) FROM REPORTS_TO WHERE EMPLOYEE.SSN = REPORTS_TO.SSN) > 1 THEN SALARY*1.1
	  WHEN (SELECT COUNT(SSN) FROM REPORTS_TO WHERE EMPLOYEE.SSN = REPORTS_TO.SSN) <= 1 THEN SALARY
	  END);
	END;
    
    --Query 17: Delete all clients who do not have health insurance and whose value of importance for transportation is less than 5
	PROCEDURE QUERY17 AS
	BEGIN
	  DELETE FROM CLIENT
	  WHERE (CLIENT.SSN IN (SELECT SSN FROM NEED WHERE CLIENT.SSN = NEED.SSN AND NEED.NEED = 'Transportation' AND NEED.VALUE_OF_IMPORTANCE < 5))
	  AND (CLIENT.SSN NOT IN (SELECT SSN FROM INSURANCE_POLICY WHERE PROVIDER_TYPE = 'Health'));
	END;
    
    --Query 18 calls Query 1
    
    --Query 19: Export: Retrieve names and mailing addresses of all people on the mailing list and output them to a data file instead of screen (the user should be asked to enter the output file name)
	PROCEDURE QUERY19 
	(Result_Table_Output OUT SYS_REFCURSOR) AS 
	BEGIN
	  OPEN Result_Table_Output FOR
	  SELECT PERSON.NAME, CONTACT_INFO.MAILING_ADDRESS
	  FROM PERSON,CONTACT_INFO
	  WHERE PERSON.EMAIL_ADDRESS = CONTACT_INFO.EMAIL_ADDRESS AND PERSON.ISONMAILINGLIST = 'Y';
	END;
	
END QUERIES;
