package hospital_ER_queue;

class HospitalERTriageDriver {
    public static void main(String[] args) {
        System.out.println("=== Hospital ER Triage System ===");
        HospitalClass hospital = new HospitalClass();

        System.out.println("\nAdding patients...");
        hospital.addPatient("Jane", 5);
        hospital.addPatient("Nani", 2);
        hospital.addPatient("Toto", 4);
        hospital.addPatient("Diana", 1);

        // Print heap after insertions
        System.out.println("\nCurrent triage queue (internal heap):");
        hospital.printQueue();

        System.out.println("\nProcessing patients...");
        System.out.println("Seen: " + hospital.processPatient());
        System.out.println("Seen: " + hospital.processPatient());

        System.out.println("\nQueue after processing two patients:");
        hospital.printQueue();

        System.out.println("\n=== End of simulation ===");
    }

}
