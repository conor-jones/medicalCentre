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
            System.out.println();
            System.out.println("5. Create new Doctors");
            System.out.println("6. Delete existing Doctors");
            System.out.println("7. Edit a Doctors");
            System.out.println("8. View all Doctors");
            System.out.println();
            System.out.println("9. Exit");


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
                
                case 5: {
                    System.out.println("Creating Doctor");
                    createDoctor (keyboard, model);
                    break;
                }
                
                case 6: {
                    System.out.println("Deleting Doctor");
                    deleteDoctor(keyboard, model);
                    break;
                }
                
                case 7: {
                    System.out.println("Editing Doctors");
                     editDoctor(keyboard, model);
                    break;
                }
                
                case 8: {
                    System.out.println("Viewing Doctors");
                    viewDoctors(model);
                    break;
                }
                
                
            }    
        }
        
        while (opt!=9);
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
        int id = 0, doctorID;
        String line;
        
        //line = getString(keyb, "Enter ID: ");
        //patientId = Integer.parseInt(line);
        fName = getString(keyb, "Enter first name: ");
        lName = getString(keyb, "Enter last name: ");
        address = getString(keyb, "Enter address: ");
        phone = getString(keyb, "Enter phone number: ");
        
        line = getString(keyb, "Enter Doctor ID (enter -1 for no manager): ");
        doctorID = Integer.parseInt(line);
        
        Patient p = new Patient(id, fName, lName, address, phone, doctorID);
        
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
        int doctorID;
        String line;
        
        fName = getString(keyboard, "Enter First name [" + p.getFName() + "]: ");
        lName = getString(keyboard, "Enter Last name [" + p.getLName() + "]: ");
        address = getString(keyboard, "Enter Address [" + p.getAddress() + "]: ");
        phone = getString(keyboard, "Enter Phone number [" + p.getPhone() + "]: ");
        line = getString(keyboard, "Enter Doctor ID [" + p.getDoctorID() + "]: ");
        
        
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
        
        if(line.length() !=0) {
            doctorID = Integer.parseInt(line);
            p.setDoctorID(doctorID);
        }
    }
    
    private static void createDoctor(Scanner keyboard, Model model) {
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
    
    private static void deleteDoctor(Scanner keyboard, Model model) {
        System.out.println("Enter the ID of the doctor you want to delete:");
        int doctorId = Integer.parseInt(keyboard.nextLine());
        Doctor p;

        p = model.findDoctorById(doctorId);
        if (p != null) {
            if(model.removeDoctor(p)) {
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
            System.out.printf("%5s %20s %20s %15s %20s \n", "ID", "Name", "Phone", "Email", "Expertise" );
            for(Doctor d : doctors) {
                System.out.printf("%5d %20s %20s %15s %20s \n",
                        d.getDoctorID(),
                        d.getName(),
                        d.getPhone(),
                        d.getEmail(),
                        d.getExpertise());
                        //pr.getPhone());
                        //pr.getDoctorID();
            }
        }
        System.out.println();
    }
    
    private static Doctor readDoctor(Scanner keyb) {
        String name, phone, email, expertise;
        int doctorID;
        //String line;
        
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


    private static void editDoctor(Scanner keyboard, Model model) {
        System.out.println("Enter the ID of the doctor you want to edit:");
        int doctorId = Integer.parseInt(keyboard.nextLine());
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

    private static void editDoctorDetails(Scanner keyboard, Doctor p) {
        String name, phone, email, expertise;
        int doctorID;
        String line;
        
        name = getString(keyboard, "Enter Name [" + p.getName() + "]: ");
        phone = getString(keyboard, "Enter Phone [" + p.getPhone() + "]: ");
        email = getString(keyboard, "Enter Email [" + p.getEmail() + "]: ");
        expertise = getString(keyboard, "Enter area of Expertise [" + p.getExpertise() + "]: ");
        //line = getString(keyboard, "Enter Doctor ID [" + p.getDoctorID() + "]: ");
        
        
        if(name.length() !=0) {
            p.setName(name);
        }
        
        if(phone.length() !=0) {
            p.setPhone(phone);
        }
        
        if(email.length() !=0) {
            p.setEmail(email);
        }
        
        if(expertise.length() !=0) {
            p.setExpertise(expertise);
        }
        
        //if(line.length() !=0) {
          //  doctorID = Integer.parseInt(line);
            //p.setDoctorID(doctorID);
        //}
    }
    
    
    
    
}
        
    
    
    

    

