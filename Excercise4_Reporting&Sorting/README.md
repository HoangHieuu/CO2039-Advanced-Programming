# 4 Exercise 4: Reporting and Sorting (20 points)

## Overview
In this exercise, you will implement a reporting and analysis subsystem over a collection of students, based on the UML class diagram shown in Figure 4. The objective is to practice object-oriented design and collection processing, with emphasis on:

* **Polymorphic collections** (`List<Student>`)
* **Stable grouping, sorting, and aggregation**
* **Clear separation** between storage (**Repository**) and processing (**Service**)
* **Reuse** of pluggable classification policies
* **Immutable result objects**

The system operates on a heterogeneous list of students (regular and honors) exclusively through their common superclass `Student`. All reporting and analysis logic must remain independent of concrete student subtypes.

---

## UML Class Diagram Description (Figure 4)

The diagram illustrates the relationship between the data storage, processing service, and result objects:

1.  **`StudentRepository`**:
    * **Attributes**:
        * `- students: List<Student>`: A private list storing student objects.
    * **Operations**:
        * `+ add(s: Student): void`: Adds a student to the internal list.
        * `+ findAll(): List<Student>`: Returns an unmodifiable list or a defensive copy of the internal students.

2.  **`ReportingService`**:
    * Has a "has-a / uses" relationship (1 to 1) with `StudentRepository`.
    * **Attributes**:
        * `- repo: StudentRepository`
    * **Operations**:
        * `+ ReportingService(repo: StudentRepository)`: Constructor.
        * `+ groupByClassification(policy: ClassificationPolicy): Map<Classification, List<Student>>`
        * `+ topKByGpa(k: int): List<Student>`: Marked with "{sort by GPA desc; tie-break required}".
        * `+ statsByCohort(cohort: String, policy: ClassificationPolicy): CohortStats`

3.  **`CohortStats`**:
    * Produced by `ReportingService`.
    * **Attributes (All marked as `{final}` for immutability)**:
        * `- cohort: String`
        * `- total: int`
        * `- counts: Map<Classification, Integer>` {unmodifiable}
        * `- regularCount: int`
        * `- honorsCount: int`
    * **Operations**:
        * `+ CohortStats(...)`: Constructor to initialize all fields.
        * Public Getters for all attributes.

---

## Tasks

### 1. Implement `StudentRepository`.
Implement a class `StudentRepository` responsible for storing students.
* Internally, the repository must store a `List<Student>` that may contain both `RegularStudent` and `HonorsStudent` instances.
* Implement the method `void add(Student s)` to add a student to the repository.
* Implement the method `List<Student> findAll()` which returns either an unmodifiable view or a defensive copy of the internal list.
* **Order rule**: The order of students in the returned list must exactly match the insertion order in the repository’s internal list. Direct external modification of the internal list must not be possible.

### 2. Implement `ReportingService`.
Implement a class `ReportingService` that performs analysis and reporting over students obtained from `StudentRepository`.

### 3. Group students by classification.
Implement the method:
`Map<Classification, List<Student>> groupByClassification(ClassificationPolicy policy)`

* Each student must be classified using the provided `ClassificationPolicy` and grouped according to the classification result.
* **Stability rule**: For each classification group, the resulting list must be **stable** with respect to the repository’s internal students list. Specifically, for any two students $A$ and $B$ belonging to the same classification, if $A$ appears before $B$ in `repo.students`, then $A$ must also appear before $B$ in the corresponding classified list.

### 4. Find the top $K$ students by GPA.
Implement the method:
`List<Student> topKByGpa(int k)`

Students must be sorted according to the following priority rules:
* (a) Higher GPA (`getGpa4()`) ranks first.
* (b) If GPA values are equal, prefer the student with a higher `disciplineScore`.
* (c) If both GPA and `disciplineScore` are equal, prefer the student with a lower `failedCount`.
* (d) If all criteria above are equal, prefer the student whose English name is **lexicographically larger**.

The returned list must contain **at most $k$ students**.

### 5. Implement `CohortStats`.
Implement an immutable value class `CohortStats` that represents aggregated statistics for a cohort.
* The class must store: The cohort identifier, the total number of students in the cohort, the number of students in each `Classification`, and the count of `RegularStudent` and `HonorsStudent`.
* All fields must be initialized via the constructor and be immutable after construction.
* Any collection exposed by this class must be unmodifiable.

### 6. Compute statistics for a cohort.
Implement the method:
`CohortStats statsByCohort(String cohort, ClassificationPolicy policy)`

This method computes statistics for all students belonging to the given cohort, using the provided `ClassificationPolicy`, and returns the result as a fully populated `CohortStats` object.

---

## Design Constraints
* The repository must store `List<Student>`, not subtype-specific lists.
* The method `findAll()` must not expose a mutable internal list.
* Reporting logic must not rely on `instanceof` for classification.
* All classification decisions must be delegated to `ClassificationPolicy`.
* `CohortStats` must be immutable and defensively encapsulated.