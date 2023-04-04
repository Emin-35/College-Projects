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


#Creating global tarih and saat variable so we can update them
tarih = ""
saat = ""

#We need a function to check if the given time and date is valid for appointment for the given doctor, If not we want different date or time
def check_appointment_date(doktor_id):
    #To be able to use them
    global tarih, saat

    #Ask the patient for date and time and update the global variables
    tarih = input('Randevu tarihini girin: (YY-MM-DD) ')
    saat = input('Randevu saatini girin: (HH:MM) ')

    #Check if the given date or time is valid or not
    cursor.execute('SELECT * FROM Appointment INNER JOIN Makes ON Appointment.app_ID = Makes.app_ID WHERE app_date = %s AND app_hour = %s AND Makes.doc_ID = %s', (tarih, saat, doktor_id))

    if cursor.fetchone():
        #If yes ask once again
        print('Bu tarih ve saatte başka bir randevu var.Lütfen farklı bir tarih ve saat seçiniz.')
        #Call the function one more time to be recursive
        check_appointment_date(doktor_id)
    
    #Else exit the function
    else:
        return

def hasta_randevu_al():
    #To be able to used updated variables
    global tarih
    global saat

    # Execute a SELECT query to get the last inserted app_ID
    cursor.execute("SELECT app_ID FROM Appointment ORDER BY app_ID DESC LIMIT 1")

    # Fetch the result of the query
    result = cursor.fetchone()

    #Turn the result into an integer value
    last_ID = result.get("app_ID")

    #Add +1 so it basically acts like AUTO_INCREMENT command
    last_ID += 1

    # Print a table of doctors with their IDs, names, last names, and departments using PrettyTable to look good
    table = PrettyTable()
    table.field_names = ['Doc_ID', 'İsim', 'Soyisim', 'Departman']

    #Fist we select the datas for Doctor (ID, name, lastname, department).
    #For department value we simply going to take it from Department table with using INNER JOIN command
    #If the Doctor.dept_ID = Department.dept_ID we will simply take the Department.dept_name and put it in the table
    cursor.execute('SELECT * FROM Doctor INNER JOIN Department ON Doctor.dept_ID = Department.dept_ID')
    doctors = cursor.fetchall()
    for doctor in doctors:
        table.add_row([doctor['doc_ID'], doctor['doc_name'], doctor['doc_lastname'], doctor['dept_name']])
    print(table)

    # Ask the user which doctor they want to see
    doktor_id = input("Lütfen randevu almak istediğiniz doktorun ID'sini giriniz: ")

    # Ask the user for the Patient table details
    hasta_id = input("Lütfen hasta ID'sini giriniz: ")
    hasta_name = input("Lütfen adınız giriniz: ")
    hasta_lastname = input("Lütfen soy adınız giriniz: ")
    hasta_gender = input("Lütfen cinsiyetinizi giriniz:(E/K) ")

    #Call the helper function to check if the given doc_ID, time and date is available
    check_appointment_date(doktor_id)

    #Ask the user for the Appointment table details
    reason = input("Lütfen şikayetinizi özetleyiniz: ")
    
    # Insert the appointment datas into the Appointment table
    cursor.execute("INSERT INTO Appointment (app_ID, app_date, app_hour, app_reason) VALUES (%s, %s, %s, %s)", (last_ID, tarih, saat, reason))
    connection.commit()

    # Insert the patient datas into Patient table
    cursor.execute("INSERT INTO Patient (pat_ID, pat_name, pat_lastname, pat_gender) VALUES (%s, %s, %s, %s)", (hasta_id, hasta_name, hasta_lastname, hasta_gender))
    connection.commit()

    # Insert the given datas into the makes table
    cursor.execute("INSERT INTO makes (app_ID, doc_ID, pat_ID) VALUES (%s, %s, %s)", (last_ID, doktor_id, hasta_id))
    connection.commit()

    print("Randevu başarıyla oluşturuldu")

def doktor_randevulari(doc_ID):
    # Print a table of the doctor's appointments
    table = PrettyTable()
    table.field_names = ['Hasta ID', 'Randevu Tarihi', 'Randevu Saati', 'Randevu Nedeni']

    #Take the datas from Appointment INNER JOIN Makes INNER JOIN Patient 
    cursor.execute('''SELECT Patient.pat_ID, Appointment.app_date, Appointment.app_hour, Appointment.app_reason FROM Appointment INNER JOIN Makes ON Appointment.app_ID = Makes.app_ID INNER JOIN Patient ON Makes.pat_ID = Patient.pat_ID WHERE Makes.doc_ID = %s
    ''', (doc_ID))

    appointments = cursor.fetchall() #Save the values
    #Print the table by using prettyTable
    for appointment in appointments:
        table.add_row([appointment['pat_ID'], appointment['app_date'], appointment['app_hour'], appointment['app_reason']])
    print(table)

def doktor_ilac_yaz():
    # Get the doctor's ID
    doc_ID = input('Lütfen doktor numaranızı girin: ')

    # Get the patient's ID
    pat_ID = input('Lütfen hasta numaranızı girin: ')

    # Get the appointment ID
    cursor.execute('SELECT * FROM Makes WHERE doc_ID = %s AND pat_ID = %s', (doc_ID, pat_ID))
    makes = cursor.fetchone()
    if not makes:
        print('Bu doktor ve hasta için bir randevu bulunamadı.')
        return

    # Prescribe medications to the patient
    while True:
        # Get the medication ID
        # If doctor won't give any more medicine he/she can press q to exit
        med_ID = input('Lütfen ilaç kodunu girin (çıkmak için q): ')

        if med_ID != 'q':
            #If doctor wants to give medicine
            #Insert the "gives" relationship into the database with given datas
            cursor.execute('INSERT INTO gives(doc_ID, med_ID, pat_ID) VALUES (%s, %s, %s)', (doc_ID, med_ID, pat_ID))
            connection.commit()

            #Also fill the takes table so patient can see which medicines he/she needs to take
            cursor.execute('INSERT INTO takes( med_ID, pat_ID) VALUES ( %s, %s)', (med_ID, pat_ID))
            connection.commit()
        
        #Else leave the function
        elif med_ID == 'q':
            break
    
    print("İlaçlar: "+ pat_ID+ " "+ "ID'li hastaya yazılmıştır, İyi günler dileriz." )
    #Exit the code
    exit()

def hasta_ilaclari_goster():
    # Get the patient's ID
    pat_ID = input('Lütfen hasta ID.nizi girin: ')

    # Create a table to show the prescribed medications and their prices
    table = PrettyTable()
    table.field_names = ['Doktor ID', 'İlaç Kodu', 'İlaç Fiyatı']
    cursor.execute('SELECT * FROM Gives INNER JOIN Medicine ON Gives.med_ID = Medicine.med_ID WHERE pat_ID = %s', (pat_ID))
    medications = cursor.fetchall()
    total_cost = 0
    for medication in medications:
        table.add_row([medication['doc_ID'], medication['med_code'], medication['med_price']])
        total_cost += medication['med_price']
    print(table)

    # Print the total cost of the medications
    print(f'Toplam ilaç fiyatı: {total_cost}')
    exit()


def main():
    #Ask the user if he/she is doctor or patient
    user_type = input("Sağlık sistemine hoş geldiniz. Doktor girişi için doktor, Hasta girişi için hasta'yı seçiniz (doktor/hasta): ")

    #If he/she is a patient
    if user_type.lower() == 'hasta':
        #Ask him/her to is he/she wants to make an appointment or wants to see their medicines
        patient_input = input("Hasta randevusu için randevu, Randevunuz olup ilaçlarınızı görmek istiyorsanız ilaç'ları seçiniz (randevu/ilaç")
        #If he/she wants to make an appointment call the hasta_randevu_al() function
        if patient_input.lower() == 'randevu':
            hasta_randevu_al()
        #If he/she wants to see their medicines call the hasta_ilaç_göster() function
        elif patient_input.lower() == 'ilaç':
            hasta_ilaclari_goster()
        #Else give error and start again
        else:
            print("Bilinmeyen giriş")
            main()
    #Else if user is a doctor
    elif user_type.lower() == 'doktor':
        #Ask him/her is he/she wants to see their appointments or want to give medicine to their patients
        user_choice = input('Randevuları görüntülemek mi istiyorsunuz, hastaya ilaç yazmak mı istiyorsunuz? (randevu/ilaç): ')
        #If he/she wants so see their appointments, first ask their doc_ID then call doktor_randevulari(doc_ID) function with their ID
        if user_choice.lower() == 'randevu':
            doc_id = input("Lütfen doktor ID'nizi giriniz")
            doktor_randevulari(doc_id)
        #Else if he/she wants to give medicine to their patients, call doktor_ilac_yaz() function
        elif user_choice.lower() == 'ilaç':
            doktor_ilac_yaz()
        #Else give error and start again
        else:
            print('Geçersiz seçim')
            main()
    #Else give error and start again
    else:
        print('Geçersiz seçim')
        main()

main()

#hasta_randevu_al()

#doktor_randevulari(4)

#doktor_ilac_yaz()

#hasta_ilaclari_goster()



