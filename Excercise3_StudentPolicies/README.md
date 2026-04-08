# 3 Exercise 3: Student Policies (20 points)

## Overview
In this exercise, you will implement a student classification subsystem based on the UML class diagram shown in Figure 3. The goal is to practice object-oriented design using:

* Interfaces and polymorphism
* Strategy-based design
* Inheritance-friendly extension
* Separation of policy and domain logic

The system classifies students into academic categories using interchangeable classification policies. Each policy encapsulates a set of rules, while the classification logic remains independent of concrete student types. You must follow the structure and constraints specified in the class diagram. Using ad-hoc conditionals (e.g., `instanceof`) instead of polymorphism is not allowed.

---

## UML Class Diagram Description (Figure 3)

The classification policy subsystem is designed using the **Strategy Pattern**, consisting of the following elements:

1.  **`Classification` (Enumeration)**:
    * Defines the possible results of a classification.
    * **Literals**: `EXCELLENT`, `GOOD`, `FAIR`, `PASS`, `FAIL`.

2.  **`ClassificationPolicy` (Interface)**:
    * Acts as the base for all classification strategies.
    * **Operations**:
        * `+ classify(s: Student): Classification`: Takes a `Student` object as input and returns a `Classification` literal.

3.  **`Student` (Abstract Class)**:
    * Used as the input for the policy.
    * Provides necessary data via:
        * `+ getGpa4(): double`
        * `+ failedCount(): int`

4.  **`StandardPolicy` and `StrictPolicy` (Concrete Classes)**:
    * Both classes implement the `ClassificationPolicy` interface.
    * They override the `classify` method with specific logic thresholds defined in the tasks below.

---

## Tasks

### 1. Define the classification result.
Define an enumeration `Classification` with the following values:
* `EXCELLENT`
* `GOOD`
* `FAIR`
* `PASS`
* `FAIL`

Each student must be assigned exactly one classification.

### 2. Define the `ClassificationPolicy` interface.
Create an interface:
`Classification classify(Student s);`

The method takes a `Student` object and returns its classification. The implementation must work uniformly for all subclasses of `Student`. Policies must not depend on whether a student is a `RegularStudent` or an `HonorsStudent`.

### 3. Specify the classification inputs.
Classification policies must base their decisions **only** on:
* `s.getGpa4()`
* `s.failedCount()`

`getGpa4()` values must be obtained via `Transcript`. No policy is allowed to modify, recompute, or store `getGpa4()` values.

### 4. Implement the `StandardPolicy`.
Create a class `StandardPolicy` that implements `ClassificationPolicy`. Classification rules are:
* **FAIL**: if `failedCount() >= 2` OR `getGpa4() < 2.0`
* **PASS**: if $2.0 \le getGpa4() < 2.5$
* **FAIR**: if $2.5 \le getGpa4() < 3.2$
* **GOOD**: if $3.2 \le getGpa4() < 3.6$
* **EXCELLENT**: if `getGpa4() >= 3.6` AND `failedCount() == 0`

### 5. Implement the `StrictPolicy`.
Create a class `StrictPolicy` that also implements `ClassificationPolicy`. This policy applies stricter thresholds:
* **FAIL**: if `failedCount() >= 1` OR `getGpa4() < 2.2`
* **PASS**: if $2.2 \le getGpa4() < 2.7$
* **FAIR**: if $2.7 \le getGpa4() < 3.3$
* **GOOD**: if $3.3 \le getGpa4() < 3.7$
* **EXCELLENT**: if `getGpa4() >= 3.7` AND `failedCount() == 0`

---

## Design Constraints
* Do not use `instanceof` or type casting.
* Policies must rely only on public methods of `Student`.
* Academic calculations must be delegated to `Transcript`.
* Adding a new policy must not require modifying existing code.