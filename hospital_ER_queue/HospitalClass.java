package hospital_ER_queue;

public class HospitalClass {
    private HeapPriorityQueue triage;
    private RecordNode recordRoot;

    public HospitalClass() {
        triage = new HeapPriorityQueue();
        recordRoot = new RecordNode("Hospital Records");
    }

    public void addPatient(String name, int priority) {
        Patient p = new Patient(name, priority);
        triage.insert(p);
    }

    // min num 1 = highest priority to be removd
    public Patient processPatient() {
        return triage.removeMin();
    }

    public void printQueue() {
        System.out.println(triage);
    }
}
