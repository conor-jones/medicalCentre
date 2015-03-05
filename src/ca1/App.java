package ca1;
import java.util.List;
import java.util.Scanner;

public class App {
    
    public static void main (String[] args) {
        Scanner keyboard = new Scanner(System.in);
        
        Model model = Model.getInstance();
        int opt;
        //this is the menu
        do {
            System.out.println("1. Create new Patients");
            System.out.println("2. Delete existing Patient");
            System.out.println("3. Edit a Patient");
            System.out.println("4. View all Patients");
            System.out.println("5. Exit");
            System.out.println();

            System.out.println("Enter option: ");
            String line = keyboard.nextLine();
            opt = Integer.parseInt(line);

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
            }    
        }
        
        while (opt!=5);
    }
    //create patient method
    private static void createPatient(Scanner keyboard, Model model) {
       Patient p = readPatient(keyboard);
        model.addPatient(p);
        if (model.addPatient(p)) {
            System.out.println("Patient added to database.");
        }
        else {
            System.out.println("Patient not added to database.");
        }
        System.out.println(); 
    }
    
    private static void deletePatient(Scanner keyboard, Model model) {
        System.out.println("Enter the ID of the patient you want to delete:");
        int patientId = Integer.parseInt(keyboard.nextLine());
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
    }
    
    private static void viewPatients(Model model) {
        List<Patient> patients = model.getPatients();
        System.out.println();

        if (patients.isEmpty()) {
            System.out.println("There are no patients in the database.");
        }

        else {
            System.out.printf("%5s %20s %20s %15s %20s %5s\n", "ID", "First Name", "Last Name", "Address", "Phone", "Doctor ID" );
            for(Patient pr : patients) {
                System.out.printf("%5d %20s %20s %15s %20s %5d\n",
                        pr.getPatientID(),
                        pr.getFName(),
                        pr.getLName(),
                        pr.getLName(),
                        pr.getAddress(),
                        pr.getPhone());
                        pr.getDoctorID();
            }
        }
        System.out.println();
    }
    
    private static Patient readPatient(Scanner keyb) {
        String fName, lName, address, phone;
        int patientID, doctorID;
        String line;
        
        //line = getString(keyb, "Enter ID: ");
        //patientId = Integer.parseInt(line);
        fName = getString(keyb, "Enter first name: ");
        lName = getString(keyb, "Enter last name: ");
        address = getString(keyb, "Enter address: ");
        phone = getString(keyb, "Enter phone number: ");
        
        line = getString(keyb, "Enter Doctor ID (enter -1 for no manager): ");
        doctorID = Integer.parseInt(line);
        
        Patient p = new Patient(patientID, fName, lName, address, phone, doctorID);
        
        return p;
    }
    
    private static String getString(Scanner keyboard, String prompt) {
        System.out.print(prompt);
        return keyboard.nextLine();
    }


    private static void editPatient(Scanner keyboard, Model model) {
        System.out.println("Enter the ID of the patient you want to edit:");
        int patientId = Integer.parseInt(keyboard.nextLine());
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
        
        fName = getString(keyboard, "Enter First name [" + p.getFName() + "]: ");
        lName = getString(keyboard, "Enter Last name [" + p.getLName() + "]: ");
        address = getString(keyboard, "Enter Address [" + p.getAddress() + "]: ");
        phone = getString(keyboard, "Enter Phone number [" + p.getPhone() + "]: ");
        
        line = getString(keyb, "Enter Doctor ID [" + p.getDoctorID() + "]: ");
        doctorID = Integer.parseInt(line);
        
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
        
        if(doctorID.length() !=0) {
            p.setDoctorID(doctorID);
        }
    }
    
    
}
        
    
    
    

    

