
# CO2039 – Advanced Programming
## Lab 1: Student Classification System

## Overview

This document specifies **Lab 1** of the course Advanced Programming (CO2039). The objective of this lab is to practice fundamental object-oriented programming (OOP) principles through a structured class design and UML-based specification.

This lab consists of **four exercises**, with a **total score of 100 points**. Each exercise focuses on a different aspect of object-oriented design and builds upon the previous ones. In particular, this lab focuses on the following concepts:

* **Composition (HAS–A)** and **Encapsulation**, where each `Student` object owns a `Transcript` object for managing academic records and computing GPA.
* **Inheritance** and **Method Overriding**, where `RegularStudent` and `HonorsStudent` extend the abstract class `Student` and provide specialized behavior.

The class diagrams provided in this document follow standard UML notation:

* Class with name written in *italic* and marked with «abstract» is abstract.
* Method names written in *italic* indicate abstract methods that must be implemented by subclasses.
* Visibility modifiers are indicated using UML symbols:
    * `+` public
    * `#` protected
    * `-` private

You are expected to follow the UML specification *exactly* when implementing the classes. Additional fields or methods are not allowed unless explicitly stated. Design decisions such as data validation, method responsibilities, and access control must be consistent with the intent expressed in the diagrams.

The purpose of this lab is not only to produce working code, but also to demonstrate clean object-oriented design and disciplined use of abstraction.

---
**1**
# 1 Exercise 1: Transcript Management

## UML Class Diagram Description (Figure 1)

The system consists of the following components as shown in the class diagram:

1.  **Interface `GradedItem`**:
    * **Operations**:
        * `+ getCredits(): int`
        * `+ getScore(): double`
2.  **Class `CourseResult`**:
    * Realizes (implements) the `GradedItem` interface.
    * **Attributes**:
        * `- courseId: String` {non-empty}
        * `- credits: int` {> 0}
        * `- score: double` {0..10}
    * **Operations**:
        * `+ CourseResult(courseId: String, credits: int, score: double)` (Constructor)
        * `+ getCourseId(): String`
        * `+ getCredits(): int`
        * `+ getScore(): double`
3.  **Class `Transcript`**:
    * Has a **Composition** relationship with `CourseResult` (the `results` field). One `Transcript` owns `0..*` (zero to many) `CourseResult` objects.
    * **Attributes**:
        * `- results: List<CourseResult>`
    * **Operations**:
        * `+ Transcript()` (Constructor)
        * `+ addCourseResult(r: CourseResult): void`
        * `+ gpa4(): double`
        * `+ failedCount(): int`
        * `+ getResults(): List<CourseResult>` {unmodifiable/copy}

---

## Overview

In this exercise, you will design and implement a small academic transcript management system following the given UML class diagram in Figure 1. The goal is to practice core object-oriented programming principles, including:

* **Composition** (HAS-A relationships)
* **Encapsulation** and data protection
* **Interfaces and implementation**
* **Separation of responsibilities** between classes

The system models how a transcript stores course results and computes academic statistics such as GPA and the number of failed courses. You must follow the structure and constraints shown in the class diagram. Any deviation from the specified design may result in point deductions.

---

## Tasks

### 1. Define the interface `GradedItem`
Create an interface `GradedItem` representing an assessable academic item. The interface must declare the following methods:
* `int getCredits();`
* `double getScore();`

The interface must not contain any fields or implementation logic. It defines the minimum contract required for GPA computation.

### 2. Implement the class `CourseResult`
Implement a class `CourseResult` that represents the result of a single course and implements the `GradedItem` interface. The class must contain the following attributes:
* `courseId`: String (must be non-empty).
* `credits`: int (must be greater than 0).
* `score`: double (must be in the range [0.0, 10.0]).

All attributes must be **private** and validated in the constructor. Provide getter methods for all attributes. No setter methods are allowed.

### 3. Implement the class `Transcript`
Implement a class `Transcript` that manages a collection of `CourseResult` objects. The class must:
* Own a list of course results (composition).
* Initialize with an empty list.
* Prevent external modification of its internal list.

### 4. Add course results to the transcript
Implement the method:
`void addCourseResult(CourseResult r)`

This method adds a course result to the transcript. The argument must not be null.

### 5. Compute GPA on a 4.0 scale
Implement the method:
`double gpa4()`

GPA is computed as a weighted average based on course credits. Scores are converted from a 10-point scale to a 4-point scale using the following rules:

| 10-point score | 4-point scale |
| :--- | :--- |
| score $\ge$ 8.5 | 4.0 |
| score $\ge$ 7.0 | 3.0 |
| score $\ge$ 5.5 | 2.0 |
| score $\ge$ 4.0 | 1.0 |
| otherwise | 0.0 |

The GPA is then calculated as a weighted average:
$$GPA = \frac{\sum (point4(score_i) \cdot credits_i)}{\sum credits_i}$$

**Rounding rule:** The final GPA must be rounded to **one decimal place** using standard rounding in Java:
`Math.round(gpa * 10.0) / 10.0`

If the transcript contains no courses, the method must return `0.0`. If the transcript contains no course results, the GPA must be `0.0`.

### 6. Count failed courses
Implement the method:
`int failedCount()`

A course is considered failed if its score is strictly less than 5.0 (e.g., 4.9).

### 7. Protect internal data (encapsulation)
Implement the method:
`List<CourseResult> getResults()`

The returned list must not allow callers to modify the internal state of the transcript. You may return an unmodifiable view or a defensive copy. The item order in the returned list must be the same as the item order in the internal list `results`.

---

## Design Constraints
* GPA must not be stored as a field.
* GPA computation logic must reside only in `Transcript`.
* `CourseResult` must implement `GradedItem`.
* Internal collections must not be exposed in a mutable form.