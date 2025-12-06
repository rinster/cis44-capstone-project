# CIS 44 - Capstone Project - Hospital Triage System
**Author: Erine Tieu**

---

#### 1. Overview
Hospitals need fast and reliable way to organize incoming patients based on medical urgency. First come, first serve systems fail during peak times when high priority patients should come first. A system needs to be put in place so that those who need urgent care are not overlooked.

#### 2. Selected Data Structures

**MinHeap Priority Queue** 
This orders patients based on medical urgency by retrieivng the lowest key at `O(log n)` time. It also has fast reordering during large patient loads.
    - `add()` - O(log n)
    - `removeMin()` - O(log n)

**Tree Structure for Records Retrieval**
In the real world situation, we would use a document database to save patient records. Document databases are essentially tree like. In our case as we are using low level design we can proceed with a tree structure since files are managed that way as well. So when we need to add a record, we use the person's name as a key O(1). Retrieving a specific record of a specific person will be O(n), searching through of the tree. 
    - `addChild()` - O(1)
    - `getRecord()` - O(n)
```
Hospital
└─ Departments
   └─ Patients
      └─ Patient 12345 (Record)
         ├─ Demographics
         ├─ Visits
         │  ├─ Visit 2025-11-10
         │  │  ├─ Notes
         │  │  └─ Orders
         │  └─ Visit 2025-06-02
         ├─ Allergies
         ├─ Medications
         └─ Attachments
```

The design uses a Min-Heap for patient triage, ensuring that the highest-priority patients are always processed first, with O(log n) insertion and removal for efficient queue management. The Tree structure organizes patient medical records hierarchically, allowing fast O(1) additions and O(n) traversal for displaying all records. This combination balances speed and simplicity, providing predictable performance for core hospital operations while remaining easy to maintain and extend.

** Chron Job (Potential) ** 
Add a Chron job class that will run in the backgroud to fetch the latest patients in the heap

** Java Swing Fx for GUI **
Add a UI for the user to interact with the data, adding and fetching priority list