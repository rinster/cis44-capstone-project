package hospital_ER_queue;

import java.time.*;

public class Patient implements Comparable<Patient> {
    private String name;
    private int priority;
    private LocalTime startWaitTime;

    public Patient(String name, int priority) {
        this.name = name;
        this.priority = priority;
        this.startWaitTime = LocalTime.now();
    }

    public String getName() {
        return this.name;
    }

    public int getPriority() {
        return this.priority;
    }

    public void updatePriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Patient other) {
        return Integer.compare(this.priority, other.priority);
    }

    @Override
    public String toString() {
        return name + " (priority " + priority + ")";
    }

}
