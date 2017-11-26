CREATE TABLE Contact_Info (    
    Email_Address				VARCHAR(255) NOT NULL,
    Mailing_Address				VARCHAR(255),
    Home_Phone_Number			NUMBER(38),
    Work_Phone_Number			NUMBER(38),
    Cell_Phone_Number				NUMBER(38),
    PRIMARY KEY(Email_Address)
);

CREATE TABLE Person (
    SSN						NUMBER(38) NOT NULL,
    Email_Address				VARCHAR(255),
    Name					VARCHAR(255),
    DoB						NUMBER(6),
    Race						VARCHAR(255),
    Gender					VARCHAR(1),
    Profession					VARCHAR(255),
    IsOnMailingList				VARCHAR(1),
    IsAffiliatedWithExtOrg			VARCHAR(1),
    PRIMARY KEY(SSN),
    FOREIGN KEY(Email_Address) REFERENCES Contact_Info
);

CREATE TABLE Emergency_Contact (
    SSN					NUMBER(38),
    Email_Address			VARCHAR(255),
    Emergency_Name			VARCHAR(255),
    Relationship				VARCHAR(255),
    FOREIGN KEY(SSN) REFERENCES Person,
    FOREIGN KEY(Email_Address) REFERENCES Contact_Info
);

CREATE TABLE External_Organization (
    Organization_Name		VARCHAR(255) NOT NULL,
    SSN				NUMBER(38),
    Org_Mailing_Address		VARCHAR(255),
    Org_Phone_Number		NUMBER(38),
    Org_Contact_Person		VARCHAR(255),
    IsOrgAnonymous		VARCHAR(1), 
    PRIMARY KEY(Organization_Name),
    FOREIGN KEY(SSN) REFERENCES Person
);

CREATE TABLE Donor (
    SSN			NUMBER(38),
    IsDonorAnonymous	VARCHAR(1),
    FOREIGN KEY(SSN) REFERENCES Person
);

CREATE TABLE Business (
    Organization_Name		VARCHAR(255),
    Business_Type		VARCHAR(255),
    Business_Size			NUMBER(38),
    Business_Website		VARCHAR(255),
    FOREIGN KEY(Organization_Name) REFERENCES External_Organization
);

CREATE TABLE Church (
    Organization_Name		VARCHAR(255),
    Religious_Affiliation		VARCHAR(255),
    FOREIGN KEY(Organization_Name) REFERENCES External_Organization
);

CREATE TABLE Team (
    Team_Name			VARCHAR(255) NOT NULL,
    Team_Type			VARCHAR(255),
    Date_of_Formation		NUMBER(6),
    Team_Lead			VARCHAR(255),
    PRIMARY KEY(Team_Name)
);

CREATE TABLE Reports_to (
    Team_Name			VARCHAR(255),
    SSN				NUMBER(38),
    Date_of_Report		NUMBER(6),
    Description			VARCHAR(255),
    FOREIGN KEY(Team_Name) REFERENCES Team,
    FOREIGN KEY(SSN) REFERENCES Person
);

CREATE TABLE May_Sponsor (
    Organization_Name		VARCHAR(255),
    Team_Name			VARCHAR(255),
    FOREIGN KEY(Organization_Name) REFERENCES External_Organization,
    FOREIGN KEY(Team_Name) REFERENCES Team
);

CREATE TABLE Donation (
    SSN				NUMBER(38),
    Organization_Name		VARCHAR(255),    
    Donation_Amount		NUMBER(38),
    Donation_Type		VARCHAR(255),
    Name_of_Campaign		VARCHAR(255),
    Check_Number		NUMBER(38),
    Card_Number		NUMBER(38),
    Card_Type			VARCHAR(255),
    Exp_Date			NUMBER(4),
    FOREIGN KEY(SSN) REFERENCES Person,
    FOREIGN KEY(Organization_Name) REFERENCES External_Organization
);

CREATE TABLE Employee (
    SSN				NUMBER(38),
    Salary			NUMBER(38),
    Martial_Status		VARCHAR(255),
    Hire_Date			NUMBER(6),
    FOREIGN KEY(SSN) REFERENCES Person
);

CREATE TABLE Expense (
    SSN				NUMBER(38),    
    Expense_Date		NUMBER(6),
    Expense_Amount		NUMBER(38),
    Expense_Description		VARCHAR(255),
    FOREIGN KEY(SSN) REFERENCES Person
);

CREATE TABLE Volunteer (
    SSN				NUMBER(38),
    Join_Date			NUMBER(6),
    Training_Date		NUMBER(6),
    Training_Location		VARCHAR(255),
    FOREIGN KEY(SSN) REFERENCES Person
);

CREATE TABLE Serves_on (
    SSN				NUMBER(38),
    Team_Name			VARCHAR(255),
    IsActive			VARCHAR(1),
    Jan_Hours_Worked		NUMBER(38),
    Feb_Hours_Worked		NUMBER(38),
    March_Hours_Worked	NUMBER(38),
    April_Hours_Worked		NUMBER(38),
    May_Hours_Worked		NUMBER(38),
    June_Hours_Worked		NUMBER(38),
    July_Hours_Worked		NUMBER(38),
    Aug_Hours_Worked		NUMBER(38),
    Sept_Hours_Worked		NUMBER(38),
    Oct_Hours_Worked		NUMBER(38),
    Nov_Hours_Worked		NUMBER(38),
    Dec_Hours_Worked		NUMBER(38),
    FOREIGN KEY(SSN) REFERENCES Person,
    FOREIGN KEY(Team_Name) REFERENCES Team
);

CREATE TABLE Client (
    SSN				NUMBER(38),
    Doctors_Name		VARCHAR(255),
    Attorneys_Name		VARCHAR(255),
    Doctors_Number		NUMBER(38),
    Attorneys_Number		NUMBER(38),
    Date_of_Assignment		NUMBER(6),
    FOREIGN KEY(SSN) REFERENCES Person
);

CREATE TABLE Cares_for (
    SSN				NUMBER(38),
    Team_Name			VARCHAR(255),
    IsActive			VARCHAR(1),
    FOREIGN KEY(SSN) REFERENCES Person,
    FOREIGN KEY(Team_Name) REFERENCES Team
);

CREATE TABLE Insurance_Policy (
    Policy_ID			NUMBER(38) NOT NULL,
    SSN				NUMBER(38),
    Provider_ID			NUMBER(38),
    Provider_Address		VARCHAR(255),
    Provider_Type		VARCHAR(255),
    PRIMARY KEY(Policy_ID),
    FOREIGN KEY(SSN) REFERENCES Person
);

CREATE TABLE Need (
    SSN				NUMBER(38),
    Need				VARCHAR(255),
    Value_of_Importance		NUMBER(2),
    FOREIGN KEY(SSN) REFERENCES Person
);
CREATE INDEX DoctorsNameIndex
ON Client (Doctors_Name);

CREATE INDEX DoctorsNumberIndex
ON Client (Doctors_Number);

CREATE INDEX ExpenseAmountIndex
ON Expense (Expense_Amount);

CREATE INDEX NameIndex
ON Person (Name);