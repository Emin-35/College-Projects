#Connecting MySQL with Python
import pymysql

# Connect to the database
connection = pymysql.connect(host="localhost",
            user="root", 
            password="12173351070", 
            cursorclass=pymysql.cursors.DictCursor)

# Create a cursor object
cursor = connection.cursor()

# Create the database
cursor.execute("CREATE DATABASE IF NOT EXISTS health_dept")
cursor.execute("USE health_dept")

#-----TABLES-----

# Create the Department table
cursor.execute("CREATE TABLE Department(dept_ID INTEGER NOT NULL PRIMARY KEY, dept_name VARCHAR(255) NOT NULL)")

# Create the Doctor table
cursor.execute("CREATE TABLE Doctor (doc_ID INTEGER NOT NULL PRIMARY KEY, doc_name VARCHAR(255) NOT NULL, doc_lastname VARCHAR(255) NOT NULL, dept_ID INTEGER NOT NULL, FOREIGN KEY (dept_ID) REFERENCES Department(dept_ID))")

# Create the Patient table
cursor.execute("CREATE TABLE Patient (pat_ID INTEGER NOT NULL PRIMARY KEY, pat_name VARCHAR(255) NOT NULL, pat_lastname VARCHAR(255), pat_gender VARCHAR(255) NOT NULL)")

# Create the Appointment table
cursor.execute("CREATE TABLE Appointment (app_ID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, app_date DATE NOT NULL, app_hour TIME NOT NULL, app_reason VARCHAR(255))")

# Create the Medicine table
cursor.execute("CREATE TABLE Medicine (med_ID INTEGER NOT NULL PRIMARY KEY, med_code VARCHAR(255) NOT NULL, med_price INTEGER NOT NULL)")

# Create the makes table
# Patient makes an Appointment. makes is a relation table here
cursor.execute("CREATE TABLE makes (app_ID INTEGER NOT NULL AUTO_INCREMENT, doc_ID INTEGER NOT NULL, pat_ID INTEGER NOT NULL, FOREIGN KEY (app_ID) REFERENCES Appointment(app_ID), FOREIGN KEY (doc_ID) REFERENCES Doctor(doc_ID), FOREIGN KEY (pat_ID) REFERENCES Patient(pat_ID))")

# Create the gives table
# Doctor gives an Medicine to Patient. gives is a relation table here
cursor.execute("CREATE TABLE gives (doc_ID INTEGER NOT NULL,  med_ID INTEGER NOT NULL, pat_ID INTEGER NOT NULL, FOREIGN KEY (doc_ID) REFERENCES Doctor(doc_ID), FOREIGN KEY (med_ID) REFERENCES Medicine(med_ID), FOREIGN KEY (pat_ID) REFERENCES Patient(pat_ID))")

# Create the takes table
# Patient takes an Medicine. takes is a relation table here
cursor.execute("CREATE TABLE takes (pat_ID INTEGER NOT NULL, med_ID INTEGER NOT NULL, FOREIGN KEY (pat_ID) REFERENCES Patient(pat_ID), FOREIGN KEY (med_ID) REFERENCES Medicine(med_ID))")

# Create the works table
# Doctor works at a Department. works is a relation table here
cursor.execute("CREATE TABLE works (doc_ID INTEGER NOT NULL, dept_ID INTEGER NOT NULL, FOREIGN KEY (doc_ID) REFERENCES Doctor(doc_ID), FOREIGN KEY (dept_ID) REFERENCES Department(dept_ID))")

#-----TABLES-----

# Close the cursor and connection
cursor.close()
connection.close()