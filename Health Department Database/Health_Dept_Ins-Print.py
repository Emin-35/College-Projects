#Connecting MySQL with Python
import pymysql

#Imporing prettytable library to show the tables more clearly
from prettytable import PrettyTable

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


#-----Functions for inserting data to tables-----


# For each table we take given datas(according to the table's attributes), and with using INSERT INTO command
# filling the tables, then commiting the changes so tables would show in MySQL.
# !Every function is same for 'filling' the tables so I won't be explaning over and over again!

def insert_department(dept_ID, dept_name):
    sql = "INSERT INTO Department (dept_ID, dept_name) VALUES (%s, %s)"
    cursor.execute(sql, (dept_ID, dept_name))
    connection.commit()

def insert_doctor(doc_ID, doc_name, doc_lastname, dept_ID):
    sql = "INSERT INTO Doctor (doc_ID, doc_name, doc_lastname, dept_ID) VALUES (%s, %s, %s, %s)"

    cursor.execute(sql, (doc_ID, doc_name, doc_lastname, dept_ID))
    connection.commit()

def insert_patient(pat_ID, pat_name, pat_lastname, pat_gender):
    sql = "INSERT INTO Patient (pat_ID, pat_name, pat_lastname, pat_gender) VALUES (%s, %s, %s, %s)"

    cursor.execute(sql, (pat_ID, pat_name, pat_lastname, pat_gender))
    connection.commit()

def insert_appointment(app_ID, app_date, app_hour, app_reason):
    sql = "INSERT INTO Appointment (app_ID, app_date, app_hour, app_reason) VALUES (%s, %s, %s, %s)"
    cursor.execute(sql, (app_ID, app_date, app_hour, app_reason))
    connection.commit()

def insert_medicine(med_ID, med_code, med_price):
    sql = "INSERT INTO Medicine (med_ID, med_code, med_price) VALUES (%s, %s, %s)"

    cursor.execute(sql, (med_ID, med_code, med_price))
    connection.commit()

def insert_works(doc_ID, dept_ID):
    sql = "INSERT INTO works (doc_ID, dept_ID) VALUES (%s, %s)"
    cursor.execute(sql, (doc_ID, dept_ID))
    connection.commit()

def insert_makes(app_ID, pat_ID, doc_ID):
    sql = "INSERT INTO makes (app_ID, pat_ID, doc_ID) VALUES (%s, %s, %s)"
    cursor.execute(sql, (app_ID, pat_ID, doc_ID))
    connection.commit()

def insert_gives(doc_ID, med_ID, pat_ID):
    sql = "INSERT INTO gives (doc_ID, med_ID, pat_ID) VALUES (%s, %s, %s)"
    cursor.execute(sql, (doc_ID, med_ID, pat_ID))
    connection.commit()

def insert_takes(pat_ID, med_ID):
    sql = "INSERT INTO gives (pat_ID, med_ID) VALUES (%s, %s)"
    cursor.execute(sql, (pat_ID, med_ID))
    connection.commit()

#-----Functions for inserting data to tables-----

#-----Functions for printing the datas from tables-----

# For each table we print datas(according to the table's attributes), with using SELECT * FROM command
# then fetching the datas so we won't lose any data.
# !Every function is same for 'printing' the tables so I won't be explaning over and over again!

def print_department_data():
    cursor.execute("SELECT * FROM Department")
    results = cursor.fetchall()
    for result in results:
        print(result)

def print_doctor_data():
    cursor.execute("SELECT * FROM Doctor")
    results = cursor.fetchall()
    for result in results:
        print(result)

def print_patient_data():
    cursor.execute("SELECT * FROM Patient")
    results = cursor.fetchall()
    for result in results:
        print(result)

def print_appointment_data():
    cursor.execute("SELECT * FROM Appointment")
    results = cursor.fetchall()
    for result in results:
        print(result)

def print_medicine_data():
    cursor.execute("SELECT * FROM Medicine")
    results = cursor.fetchall()
    for result in results:
        print(result)

def print_works_data():
    cursor.execute("SELECT * FROM works")
    results = cursor.fetchall()
    for result in results:
        print(result)

def print_makes_data():
    cursor.execute("SELECT * FROM makes")
    results = cursor.fetchall()
    for result in results:
        print(result)

def print_gives_data():
    cursor.execute("SELECT * FROM gives")
    results = cursor.fetchall()
    for result in results:
        print(result)

def print_takes_data():
    cursor.execute("SELECT * FROM takes")
    results = cursor.fetchall()
    for result in results:
        print(result)

#-----Functions for printing the datas from tables-----

#Using the functions and filling the database

insert_department(555, "KBB")
insert_department(444, "Enfeksyon Hastaliklari")
insert_department(333, "Cocuk Genel Bakim")
insert_department(222, "Dis")

insert_doctor(1, "Ali", "Öztürk", 555)
insert_doctor(2, "Ayse", "Hanim", 333)
insert_doctor(3, "Ali", "Yilmaz", 444)
insert_doctor(4, "Zeynep", "Demir", 222)
insert_doctor(5, "Mustafa", "Kaya", 555)

insert_medicine(1,"CXB", 50)
insert_medicine(2,"A0B", 43)
insert_medicine(3,"Y22", 12)
insert_medicine(4,"PTR", 90)
insert_medicine(5,"FBN", 65)

insert_works(1,555)
insert_works(2,333)
insert_works(3,444)
insert_works(4,222)
insert_works(5,555)

# Close the cursor and connection
cursor.close()
connection.close()