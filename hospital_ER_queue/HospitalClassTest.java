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
        System.out.println("[NORMAL CASE] testHeapPriorityQueueIsEmpty");
        try {
            assertTrue("Queue should be empty initially", queue.isEmpty());
            assertEquals("Size should be 0 initially", 0, queue.size());
            System.out.println("PASS");
        } catch (AssertionError e) {
            System.out.println("FAIL");
            throw e;
        }
    }

    @Test
    public void testHeapPriorityQueueInsert() {
        System.out.println("[NORMAL CASE] testHeapPriorityQueueInsert");
        try {
            queue.insert(patient1);
            assertFalse("Queue should not be empty after insert", queue.isEmpty());
            assertEquals("Size should be 1 after one insert", 1, queue.size());
            System.out.println("PASS");
        } catch (AssertionError e) {
            System.out.println("FAIL");
            throw e;
        }
    }

    @Test
    public void testHeapPriorityQueueInsertMultiple() {
        System.out.println("[NORMAL CASE] testHeapPriorityQueueInsertMultiple");
        try {
            queue.insert(patient1);
            queue.insert(patient2);
            queue.insert(patient3);
            assertEquals("Size should be 3 after three inserts", 3, queue.size());
            System.out.println("PASS");
        } catch (AssertionError e) {
            System.out.println("FAIL");
            throw e;
        }
    }

    @Test
    public void testHeapPriorityQueueMin() {
        System.out.println("[NORMAL CASE] testHeapPriorityQueueMin");
        try {
            queue.insert(patient2);
            queue.insert(patient1);
            queue.insert(patient3);
            
            Patient min = queue.min();
            assertNotNull("Min should not be null", min);
            assertEquals("Min priority should be 1", 1, min.getPriority());
            System.out.println("PASS");
        } catch (AssertionError e) {
            System.out.println("FAIL");
            throw e;
        }
    }

    @Test
    public void testHeapPriorityQueueMinEmpty() {
        System.out.println("[EMPTY CASE] testHeapPriorityQueueMinEmpty");
        try {
            Patient min = queue.min();
            assertNull("Min should be null for empty queue", min);
            System.out.println("PASS");
        } catch (AssertionError e) {
            System.out.println("FAIL");
            throw e;
        }
    }

    @Test
    public void testHeapPriorityQueueRemoveMin() {
        System.out.println("[NORMAL CASE] testHeapPriorityQueueRemoveMin");
        try {
            queue.insert(patient2);
            queue.insert(patient1);
            queue.insert(patient3);
            
            Patient removed = queue.removeMin();
            assertNotNull("Removed patient should not be null", removed);
            assertEquals("Removed patient should have priority 1", 1, removed.getPriority());
            assertEquals("Size should be 2 after removal", 2, queue.size());
            System.out.println("PASS");
        } catch (AssertionError e) {
            System.out.println("FAIL");
            throw e;
        }
    }

    @Test
    public void testHeapPriorityQueueRemoveMinEmpty() {
        System.out.println("[EMPTY CASE] testHeapPriorityQueueRemoveMinEmpty");
        try {
            Patient removed = queue.removeMin();
            assertNull("Removed patient should be null for empty queue", removed);
            System.out.println("PASS");
        } catch (AssertionError e) {
            System.out.println("FAIL");
            throw e;
        }
    }

    @Test
    public void testHeapPriorityQueuePriorityOrder() {
        System.out.println("[NORMAL CASE] testHeapPriorityQueuePriorityOrder");
        try {
            queue.insert(patient3); 
            queue.insert(patient1);
            queue.insert(patient2);
            
            Patient first = queue.removeMin();
            assertEquals("First patient should have priority 1", 1, first.getPriority());
            
            Patient second = queue.removeMin();
            assertEquals("Second patient should have priority 2", 2, second.getPriority());
            
            Patient third = queue.removeMin();
            assertEquals("Third patient should have priority 3", 3, third.getPriority());
            System.out.println("PASS");
        } catch (AssertionError e) {
            System.out.println("FAIL");
            throw e;
        }
    }


    @Test
    public void testPatientGetName() {
        System.out.println("[NORMAL CASE] testPatientGetName");
        try {
            assertEquals("Patient name should match", "John Xie", patient1.getName());
            assertEquals("Patient name should match", "Aimee Duong", patient2.getName());
            assertEquals("Patient name should match", "Nani Pacheco", patient3.getName());
            System.out.println("PASS");
        } catch (AssertionError e) {
            System.out.println("FAIL");
            throw e;
        }
    }

    @Test
    public void testPatientGetPriority() {
        System.out.println("[NORMAL CASE] testPatientGetPriority");
        try {
            assertEquals("Patient priority should match", 1, patient1.getPriority());
            assertEquals("Patient priority should match", 2, patient2.getPriority());
            assertEquals("Patient priority should match", 3, patient3.getPriority());
            System.out.println("PASS");
        } catch (AssertionError e) {
            System.out.println("FAIL");
            throw e;
        }
    }



    @Test
    public void testHospitalClassAddPatient() {
        System.out.println("[NORMAL CASE] testHospitalClassAddPatient");
        try {
            hospital.addPatient("Alice Brown", 1);
            hospital.addPatient("Charlie Davis", 2);
            Patient processed = hospital.processPatient();
            assertNotNull("Processed patient should not be null", processed);
            assertEquals("Processed patient should have priority 1", 1, processed.getPriority());
            System.out.println("PASS");
        } catch (AssertionError e) {
            System.out.println("FAIL");
            throw e;
        }
    }



    @Test
    public void testHospitalClassProcessPatientOrder() {
        System.out.println("[NORMAL CASE] testHospitalClassProcessPatientOrder");
        try {
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
            System.out.println("PASS");
        } catch (AssertionError e) {
            System.out.println("FAIL");
            throw e;
        }
    }

    @Test
    public void testHospitalClassProcessPatientEmpty() {
        System.out.println("[EMPTY CASE] testHospitalClassProcessPatientEmpty");
        try {
            Patient processed = hospital.processPatient();
            assertNull("Processing from empty queue should return null", processed);
            System.out.println("PASS");
        } catch (AssertionError e) {
            System.out.println("FAIL");
            throw e;
        }
    }

    @Test
    public void testPriorityQueueWithSamePriorities() {
        System.out.println("[EDGE CASE] testPriorityQueueWithCollisions");
        try {
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
            System.out.println("PASS");
        } catch (AssertionError e) {
            System.out.println("FAIL");
            throw e;
        }
    }
}
