package ca1;
import java.util.List;
import java.util.Scanner;

public class App {
    
    public static void main (String[] args) {
        Scanner keyboard = new Scanner(System.in);
        
        Model model;
        int opt = 11;
        //this is the menu
        do {
            try {
                model = Model.getInstance();
                System.out.println("1. Create new Patients");
                System.out.println("2. Delete existing Patient");
                System.out.println("3. Edit a Patient");
                System.out.println("4. View all Patients");
                System.out.println("5. View a single Patients");
                System.out.println();
                System.out.println("6. Create new Doctors");
                System.out.println("7. Delete existing Doctors");
                System.out.println("8. Edit a Doctors");
                System.out.println("9. View all Doctors");
                System.out.println("10. View a single Doctors");
                System.out.println();
                System.out.println("11. Exit");



                opt = getInt(keyboard, "Enter option: " ,11);

                System.out.println("You chose option " + opt);
                switch (opt){
                    case 1: {
                        System.out.println("Creating Patient");
                        createPatient (keyboard, model);
                        break;
                    }

                    case 2: {
                        System.out.println("Deleting Patient");
                        deletePatient(keyboard, model);
                        break;
                    }

                    case 3: {
                        System.out.println("Editing Patients");
                         editPatient(keyboard, model);
                        break;
                    }

                    case 4: {
                        System.out.println("Viewing Patients");
                        viewPatients(model);
                        break;
                    }
                    
                     case 5: {
                        System.out.println("Viewing a single Patient");
                        viewPatient(keyboard, model);
                        break;
                    }

                    case 6: {
                        System.out.println("Creating Doctor");
                        createDoctor (keyboard, model);
                        break;
                    }

                    case 7: {
                        System.out.println("Deleting Doctor");
                        deleteDoctor(keyboard, model);
                        break;
                    }

                    case 8: {
                        System.out.println("Editing Doctors");
                         editDoctor(keyboard, model);
                        break;
                    }

                    case 9: {
                        System.out.println("Viewing Doctors");
                        viewDoctors(model);
                        break;
                    }
                    
                    case 10: {
                        System.out.println("Viewing Doctor");
                        viewDoctor(keyboard, model);
                        break;
                    }


                }    
            }
        
            catch (DataAccessException e) { 
                System.out.println();
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
        while (opt!=11);
    }
    //create patient method
    private static void createPatient(Scanner keyboard, Model model) throws DataAccessException {
       Patient p = readPatient(keyboard);
        //model.addPatient(p);
        if (model.addPatient(p)) {
            System.out.println("Patient added to database.");
        }
        else {
            System.out.println("Patient not added to database.");
        }
        System.out.println(); 
    }
    
    private static void deletePatient(Scanner keyboard, Model model) throws DataAccessException {
        int patientId = getInt(keyboard, "Enter the ID of the patient you want to delete:" ,-1);
        Patient p;

        p = model.findPatientById(patientId);
        if (p != null) {
            if(model.removePatient(p)) {
                System.out.println("Patient deleted");
            }
            else {
                System.out.println("Patient not deleted.");
            }
        }
        else {
            System.out.println("Patient not found");
        }
    }
    
    private static void viewPatients(Model model) {
        List<Patient> patients = model.getPatients();
        System.out.println();

        if (patients.isEmpty()) {
            System.out.println("There are no patients in the database.");
        }

        else {
            displayPatients(patients, model);
        }
        System.out.println();
    }
    
    private static void displayPatients(List<Patient> patients, Model model) {
        System.out.printf("%5s %20s %20s %15s %20s %5s\n", 
                    "ID", "First Name", "Last Name", "Address", "Phone", "Doctor ID" );
            for(Patient p : patients) {
                //Doctor d = model.findDoctorById(p.getDoctorID());
                System.out.printf("%5d %20s %20s %15s %20s %5d\n",
                        p.getPatientID(), 
                        p.getFName(),
                        p.getLName(),
                        p.getAddress(),
                        p.getPhone(),
                        p.getDoctorID());
                        //(d != null) ? d.getName() : "");
                        //displaying doctor name doesn't work due to null pointer exception
            }
    }
    
    private static void viewPatient(Scanner keyboard, Model model) throws DataAccessException {
        int patientId = getInt(keyboard, "Enter the ID of the patient you want to view:" ,-1);
        Patient p;

        p = model.findPatientById(patientId);
        if (p != null) {
            System.out.println("First Name  : " + p.getFName());
            System.out.println("Last Name   : " + p.getLName());
            System.out.println("Address     : " + p.getAddress());
            System.out.println("Phone       : " + p.getPhone());
            System.out.println("Doctor ID   : " + p.getDoctorID());
        }
        else {
            System.out.println("Patient not found");
        }
    }
    
    private static Patient readPatient(Scanner keyb) {
        String fName, lName, address, phone;
        int id=0, doctorID;
        String line;
        
        //line = getString(keyb, "Enter ID: ");
        //patientId = Integer.parseInt(line);
        fName = getString(keyb, "Enter first name: ");
        lName = getString(keyb, "Enter last name: ");
        address = getString(keyb, "Enter address: ");
        phone = getString(keyb, "Enter phone number: ");
        
        doctorID = getInt(keyb, "Enter Doctor ID (enter -1 for no manager): " ,-1);
        
        Patient p = new Patient(id, fName, lName, address, phone, doctorID);
        
        return p;
    }
    
    private static String getString(Scanner keyboard, String prompt) {
        System.out.print(prompt);
        return keyboard.nextLine();
    }


    private static void editPatient(Scanner keyboard, Model model) throws DataAccessException {
        int patientId = getInt(keyboard, "Enter the ID of the patient you want to edit:" ,-1);
        Patient p;
        
        p = model.findPatientById(patientId);
        if (p != null) {
            editPatientDetails(keyboard, p);
            if (model.updatePatient(p)) {
                System.out.println("Patient updated");
            }
            else {
                System.out.println("Patient not updated");
            }
        }
        else {
            System.out.println("Patient not found");
        }
    }

    private static void editPatientDetails(Scanner keyboard, Patient p) {
        String fName, lName, address, phone;
        int doctorID;
        
        fName = getString(keyboard, "Enter First name [" + p.getFName() + "]: ");
        lName = getString(keyboard, "Enter Last name [" + p.getLName() + "]: ");
        address = getString(keyboard, "Enter Address [" + p.getAddress() + "]: ");
        phone = getString(keyboard, "Enter Phone number [" + p.getPhone() + "]: ");
        doctorID = getInt(keyboard, "Enter Doctor ID [" + p.getDoctorID() + "]: " ,-1);
        
        
        if(fName.length() !=0) {
            p.setFName(fName);
        }
        
        if(lName.length() !=0) {
            p.setLName(lName);
        }
        
        if(address.length() !=0) {
            p.setAddress(address);
        }
        
        if(phone.length() !=0) {
            p.setPhone(phone);
        }
        
        if(doctorID != p.getDoctorID()) {
            p.setDoctorID(doctorID);
        }
    }
    
    private static void createDoctor(Scanner keyboard, Model model) throws DataAccessException {
       Doctor d = readDoctor(keyboard);
        model.addDoctor(d);
        if (model.addDoctor(d)) {
            System.out.println("Doctor added to database.");
        }
        else {
            System.out.println("Doctor not added to database.");
        }
        System.out.println(); 
    }
    
    private static void deleteDoctor(Scanner keyboard, Model model) throws DataAccessException {
        int doctorId = getInt(keyboard, "Enter the ID of the doctor you want to delete:" ,-1);
        Doctor d;

        d = model.findDoctorById(doctorId);
        if (d != null) {
            if(model.removeDoctor(d)) {
                System.out.println("Doctor deleted");
            }
            else {
                System.out.println("Doctor not deleted.");
            }
        }
    }
    
    private static void viewDoctors(Model model) {
        List<Doctor> doctors = model.getDoctors();
        System.out.println();

        if (doctors.isEmpty()) {
            System.out.println("There are no doctors in the database.");
        }

        else {
            System.out.printf("%5s %20s %20s %15s %20s \n", 
                    "ID", "Name", "Phone", "Email", "Expertise" );
            for(Doctor d : doctors) {
                System.out.printf("%5d %20s %20s %20s %20s \n",
                        d.getDoctorID(),
                        d.getName(),
                        d.getPhone(),
                        d.getEmail(),
                        d.getExpertise());
                      
            }
        }
        System.out.println();
    }
    
     private static void viewDoctor(Scanner keyboard, Model model) throws DataAccessException {
        int doctorId = getInt(keyboard, "Enter the ID of the doctor you want to view:" ,-1);
        Doctor d;

        d = model.findDoctorById(doctorId);
        if (d != null) {
            System.out.println("Name  : " + d.getName());
            System.out.println("Phone   : " + d.getPhone());
            System.out.println("Email     : " + d.getEmail());
            System.out.println("Expertise       : " + d.getExpertise());
            
            List<Patient> patientList = model.getPatientsByDoctorId(d.getDoctorID());
            if(patientList.isEmpty()) {
                System.out.println();
                System.out.println("This doctor has no patients.");
                System.out.println();
            }
            else {
                System.out.println("This doctor has the following patients: ");
                System.out.println();
                displayPatients(patientList, model);
            }
            
        }
        else {
            System.out.println("Doctor not found");
        }
    }
    
    private static Doctor readDoctor(Scanner keyb) {
        String name, phone, email, expertise;
        int doctorID=0;
        String line;
        
        //line = getString(keyb, "Enter ID: ");
        //doctorId = Integer.parseInt(line);
        name = getString(keyb, "Enter Doctor name: ");
        phone = getString(keyb, "Enter phone number: ");
        email = getString(keyb, "Enter email: ");
        expertise = getString(keyb, "Enter expertise: ");
        
        //line = getString(keyb, "Enter Doctor ID (enter -1 for no manager): ");
        //doctorID = Integer.parseInt(line);
        
        Doctor d = new Doctor(doctorID, name, phone, email, expertise);
        
        return d;
    }
    
    //private static String getString(Scanner keyboard, String prompt) {
      //  System.out.print(prompt);
        //return keyboard.nextLine();
    //}


    private static void editDoctor(Scanner keyboard, Model model) throws DataAccessException {
        int doctorId = getInt(keyboard, "Enter the ID of the doctor you want to edit:" ,-1);
        Doctor d;
        
        d = model.findDoctorById(doctorId);
        if (d != null) {
            editDoctorDetails(keyboard, d);
            if (model.updateDoctor(d)) {
                System.out.println("Doctor updated");
            }
            else {
                System.out.println("Doctor not updated");
            }
        }
        else {
            System.out.println("Doctor not found");
        }
    }

    private static void editDoctorDetails(Scanner keyboard, Doctor d) {
        String name, phone, email, expertise;
        int doctorID;
        String line;
        
        name = getString(keyboard, "Enter Name [" + d.getName() + "]: ");
        phone = getString(keyboard, "Enter Phone [" + d.getPhone() + "]: ");
        email = getString(keyboard, "Enter Email [" + d.getEmail() + "]: ");
        expertise = getString(keyboard, "Enter area of Expertise [" + d.getExpertise() + "]: ");
        //line = getString(keyboard, "Enter Doctor ID [" + p.getDoctorID() + "]: ");
        
        
        if(name.length() !=0) {
            d.setName(name);
        }
        
        if(phone.length() !=0) {
            d.setPhone(phone);
        }
        
        if(email.length() !=0) {
            d.setEmail(email);
        }
        
        if(expertise.length() !=0) {
            d.setExpertise(expertise);
        }
        
        //if(line.length() !=0) {
          //  doctorID = Integer.parseInt(line);
            //p.setDoctorID(doctorID);
        //}
    }
    
    private static int getInt(Scanner keyboard, String prompt, int defaultValue) {
        int opt = defaultValue;
        boolean finished = false;
        do {
            try {
                System.out.print(prompt);
                String line = keyboard.nextLine();
                if (line.length() > 0) {
                    opt = Integer.parseInt(line);
                }
                opt = Integer.parseInt(line);
                finished = true;
            }
            catch(NumberFormatException e) {
                System.out.println("Exception: " + e.getMessage());
            }
        }
        while (!finished);
        return opt;
    }
    
    
}
        
    
    
    

    

