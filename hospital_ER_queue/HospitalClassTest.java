package hospital_ER_queue;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class HospitalClassTest {
    private HospitalClass hospital;
    private HeapPriorityQueue queue;
    private Patient patient1;
    private Patient patient2;
    private Patient patient3;

    @Before
    public void setUp() {
        hospital = new HospitalClass();
        queue = new HeapPriorityQueue();
        patient1 = new Patient("John Xie", 1);
        patient2 = new Patient("Aimee Duong", 2);
        patient3 = new Patient("Nani Pacheco", 3);
    }

    @Test
    public void testHeapPriorityQueueIsEmpty() {
        assertTrue("Queue should be empty initially", queue.isEmpty());
        assertEquals("Size should be 0 initially", 0, queue.size());
    }

    @Test
    public void testHeapPriorityQueueInsert() {
        queue.insert(patient1);
        assertFalse("Queue should not be empty after insert", queue.isEmpty());
        assertEquals("Size should be 1 after one insert", 1, queue.size());
    }

    @Test
    public void testHeapPriorityQueueInsertMultiple() {
        queue.insert(patient1);
        queue.insert(patient2);
        queue.insert(patient3);
        assertEquals("Size should be 3 after three inserts", 3, queue.size());
    }

    @Test
    public void testHeapPriorityQueueMin() {
        queue.insert(patient2);
        queue.insert(patient1);
        queue.insert(patient3);

        Patient min = queue.min();
        assertNotNull("Min should not be null", min);
        assertEquals("Min should be patient with priority 1", patient1, min);
        assertEquals("Min priority should be 1", 1, min.getPriority());
    }

    @Test
    public void testHeapPriorityQueueMinEmpty() {
        Patient min = queue.min();
        assertNull("Min should be null for empty queue", min);
    }

    @Test
    public void testHeapPriorityQueueRemoveMin() {
        queue.insert(patient2);
        queue.insert(patient1);
        queue.insert(patient3);

        Patient removed = queue.removeMin();
        assertNotNull("Removed patient should not be null", removed);
        assertEquals("Removed patient should have priority 1", 1, removed.getPriority());
        assertEquals("Size should be 2 after removal", 2, queue.size());
    }

    @Test
    public void testHeapPriorityQueueRemoveMinEmpty() {
        Patient removed = queue.removeMin();
        assertNull("Removed patient should be null for empty queue", removed);
    }

    @Test
    public void testHeapPriorityQueuePriorityOrder() {
        // Insert patients in non-priority order
        queue.insert(patient3); // priority 3
        queue.insert(patient1); // priority 1
        queue.insert(patient2); // priority 2

        // Should remove in priority order: 1, 2, 3
        Patient first = queue.removeMin();
        assertEquals("First patient should have priority 1", 1, first.getPriority());

        Patient second = queue.removeMin();
        assertEquals("Second patient should have priority 2", 2, second.getPriority());

        Patient third = queue.removeMin();
        assertEquals("Third patient should have priority 3", 3, third.getPriority());
    }

    @Test
    public void testHeapPriorityQueueMultipleRemovals() {
        queue.insert(patient1);
        queue.insert(patient2);
        queue.insert(patient3);

        queue.removeMin();
        assertEquals("Size should be 2 after one removal", 2, queue.size());

        queue.removeMin();
        assertEquals("Size should be 1 after two removals", 1, queue.size());

        queue.removeMin();
        assertTrue("Queue should be empty after all removals", queue.isEmpty());
        assertEquals("Size should be 0 after all removals", 0, queue.size());
    }

    // ========== Patient Tests ==========

    @Test
    public void testPatientGetName() {
        assertEquals("Patient name should match", "John Xie", patient1.getName());
        assertEquals("Patient name should match", "Aimee Duong", patient2.getName());
    }

    @Test
    public void testPatientGetPriority() {
        assertEquals("Patient priority should match", 1, patient1.getPriority());
        assertEquals("Patient priority should match", 2, patient2.getPriority());
        assertEquals("Patient priority should match", 3, patient3.getPriority());
    }

    @Test
    public void testPatientUpdatePriority() {
        patient1.updatePriority(5);
        assertEquals("Priority should be updated to 5", 5, patient1.getPriority());
    }

    @Test
    public void testHospitalClassAddPatient() {
        hospital.addPatient("Alice Brown", 1);
        hospital.addPatient("Charlie Davis", 2);
        Patient processed = hospital.processPatient();
        assertNotNull("Processed patient should not be null", processed);
        assertEquals("Processed patient should have priority 1", 1, processed.getPriority());
    }

    @Test
    public void testHospitalClassProcessPatient() {
        hospital.addPatient("Alice Brown", 2);
        hospital.addPatient("Charlie Davis", 1);

        // Should process patient with priority 1 first
        Patient first = hospital.processPatient();
        assertNotNull("First processed patient should not be null", first);
        assertEquals("First processed patient should have priority 1", 1, first.getPriority());
        assertEquals("First processed patient name should match", "Charlie Davis", first.getName());
    }

    @Test
    public void testHospitalClassProcessPatientOrder() {
        hospital.addPatient("Patient A", 3);
        hospital.addPatient("Patient B", 1);
        hospital.addPatient("Patient C", 2);

        // Should process in priority order: 1, 2, 3
        Patient first = hospital.processPatient();
        assertEquals("First should have priority 1", 1, first.getPriority());
        assertEquals("First should be Patient B", "Patient B", first.getName());

        Patient second = hospital.processPatient();
        assertEquals("Second should have priority 2", 2, second.getPriority());
        assertEquals("Second should be Patient C", "Patient C", second.getName());

        Patient third = hospital.processPatient();
        assertEquals("Third should have priority 3", 3, third.getPriority());
        assertEquals("Third should be Patient A", "Patient A", third.getName());
    }

    @Test
    public void testHospitalClassProcessPatientEmpty() {
        Patient processed = hospital.processPatient();
        assertNull("Processing from empty queue should return null", processed);
    }

    @Test
    public void testPriorityQueueWithSamePriorities() {
        Patient p1 = new Patient("Patient 1", 2);
        Patient p2 = new Patient("Patient 2", 2);
        Patient p3 = new Patient("Patient 3", 2);

        queue.insert(p1);
        queue.insert(p2);
        queue.insert(p3);

        // All have same priority, should be able to remove all
        assertNotNull("Should be able to remove first", queue.removeMin());
        assertNotNull("Should be able to remove second", queue.removeMin());
        assertNotNull("Should be able to remove third", queue.removeMin());
        assertTrue("Queue should be empty", queue.isEmpty());
    }
}
