import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

class Patient{
    private String name;
    private String patient_address;
    private String gender;
    private int age ;
    private int patientID;
    private String disease;
    Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
public void setPatient_address(String patient_address){
        this.patient_address=patient_address;
}
public String getPatient_address(){
        return patient_address;
}
public void setGender(String gender){
        this.gender=gender;
}
public String getGender(){
        return gender;
}
public void setAge(int age){
        this.age=age;
}
public int getAge(){
        return age;
}
public void setPatientID(int patientID){
        this.patientID=patientID;
}
public int getPatientID(){
        return patientID;
}
public void setDisease(String disease){
        this.disease = disease;
}
public String getDisease(){
        return disease;
}

    public void userInfo(Scanner sc){
        System.out.println("Please Enter The Problem/Disease: ");
        disease = sc.nextLine();
        System.out.println("Enter Name:");
        name = sc.nextLine();
        System.out.println("Enter Your Address:");
        patient_address = sc.nextLine();
        System.out.println("Enter Your Gender:");
        gender = sc.nextLine();
        System.out.println("Enter Your Age:");
        age = sc.nextInt();
        sc.nextLine();
        patientID = rand.nextInt(1000,9999);
        System.out.println("Your Patient ID Is:"+patientID);
    }

    public String TimeSlots(){
        System.out.println("--- Please Select Time Between (9:00 A.M To 12:00 P.M.) & (6:00 P.M. To 9:00 P.M. ----");
        System.out.println("Enter Time:");
        return sc.nextLine();
    }
}

class Doctor{
    String doctor_name;
    String doctorSpecialization;
    boolean doctorAvailability = true;
int doctorId;
String doctorTimings;

    Doctor(String doctor_name, String doctorSpecialization,boolean doctorAvailability,int doctorId){
        this.doctor_name=doctor_name;
        this.doctorSpecialization=doctorSpecialization;
        this.doctorAvailability=doctorAvailability;
        this.doctorId=doctorId;
    }
public String getAvailabilityStatus(){
        return doctorAvailability ? "Doctor Available" : "Doctor Not Available";
}
}

class Appointment{
    int appointmentId;
    Patient patient;
    Doctor doctor;
    String Timeslots;

    Appointment(int appointmentId,Patient patient,Doctor doctor,String Timeslots){
        this.appointmentId=appointmentId;
        this.patient=patient;
        this.doctor=doctor;
        this.Timeslots=Timeslots;
    }
}

public class HospitalMgtSystem {
    ArrayList<Doctor> doctors = new ArrayList<>();

    public void initDoctors() {
        doctors.add(new Doctor("Mr.Rohit Malhotra", "Cardiology", true, 101));
        doctors.add(new Doctor("Manvi Soni", "Dermatology", true, 102));
        doctors.add(new Doctor("Sunidhi Bindal", "Neurology", true, 103));
        doctors.add(new Doctor("Aarchi Sharma", "Pediatrics", true, 104));
        doctors.add(new Doctor("Arti Dhiman", "Gastroenterology", true, 105));
        doctors.add(new Doctor("Bhumika Ghai", "Endocrinology", true, 106));
        doctors.add(new Doctor("Parth Narang", "Pulmonology", true, 107));
        doctors.add(new Doctor("Divyansh Soni", "Psychiatry", true, 108));

    }

    public void showDoctors(){
        for(Doctor d:doctors){
            System.out.println(d.doctorId + "-" +d.doctor_name + "-" + d.doctorSpecialization );
        }
    }

    public void showAvailability(){
        for(Doctor d:doctors){
            System.out.println(d.doctor_name + "->" + " " + d.getAvailabilityStatus());
        }
    }

    public void searchBySpecialization(String spec) {
        boolean found = false;
        for (Doctor d : doctors) {
            if (d.doctorSpecialization.equalsIgnoreCase(spec)) {
                System.out.println(d.doctorId + " - " + d.doctor_name);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No Doctor Found For This Specialization!");
        }
    }
ArrayList<Appointment> appointments = new ArrayList<>();

public void bookAppointment(Patient patient , int doctorId,String time) {
    Doctor SelectedDoctor = null;

    for (Doctor d : doctors) {
        if (d.doctorId == doctorId) {
            SelectedDoctor = d;
            break;
        }
    }

    if (SelectedDoctor != null && SelectedDoctor.doctorAvailability) {
        Appointment a = new Appointment((int) (Math.random() * 10000), patient, SelectedDoctor, time);
        appointments.add(a);
        SelectedDoctor.doctorAvailability = false;
        System.out.println("Appointment Booked Successfully!");
    } else {
        System.out.println("Doctor Not Available!");
    }
}
    public void showAppointments(){
        for(Appointment a:appointments){
            System.out.println(
                    a.appointmentId + " | "+
                            a.patient.getName() + " | "+
                            a.doctor.doctor_name + " | "+
                            a.Timeslots
            );
        }
    }
public static void main(String[] args) {
Scanner input = new Scanner(System.in);
HospitalMgtSystem hospital = new HospitalMgtSystem();
    hospital.initDoctors();
Patient p = new Patient();

while(true){
    System.out.println("---- Shiv Hospital ----");
    System.out.println("1.Show Doctors 2. Show Doctor Availability 3.Search By Specialization 4.Enter Patient Details 5.Book Appointment 6.Show Appointments 7.Exit");
    System.out.println("Enter your choice");
    int choice = input.nextInt();
    input.nextLine();

    switch(choice){
        case 1:
            System.out.println("--- Doctors ---");
            hospital.showDoctors();
break;

        case 2:
            System.out.println("--- Showing Availability ---");
            hospital.showAvailability();
            break;
        case 3:
            System.out.println("Enter Doctor Specialization: ");
            String spec = input.nextLine();
            hospital.searchBySpecialization(spec);
            break;

        case 4:
            System.out.println("--- Please Enter Patient Details ---");
            p.userInfo(input);
        break;

        case 5:
            System.out.println("--- Book Appointment ---");
            System.out.println("Enter Doctor Id: ");
            int doctorId = input.nextInt();
            input.nextLine();
            System.out.println("Enter Time Line: ");
            String time = input.nextLine();
            hospital.bookAppointment(p,doctorId,time);
            break;

        case 6:
            System.out.println("--- Showing Appointments ---");
            hospital.showAppointments();
            break;

        case 7 :
            System.out.println("Thank You For Visiting! ");
            System.out.println("Stay Safe & Healthy! ");
            return;

        default:
            System.out.println("Invalid choice! Please Enter Between Range(1-7) ");
            break;
    }
}
}
}
