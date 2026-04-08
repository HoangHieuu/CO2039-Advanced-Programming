Here is the complete content for **Exercise 2** translated into English, maintaining the Markdown structure and technical accuracy.

---

# 2 Exercise 2: Student Classification (30 points)

## Overview
In this exercise, you will implement a student management subsystem based on the UML class diagram shown in Figure 2. The goal is to practice object-oriented design using:

* **Abstract classes and inheritance**
* **Method overriding and polymorphism**
* **Composition** (HAS-A relationship with the `Transcript` class)
* **Encapsulation and responsibility separation**

The system models different types of students and their academic status, as well as tuition discount rules that vary by student type. You must follow the structure and constraints specified in the class diagram. Using ad-hoc conditionals (e.g., `instanceof`) instead of polymorphism is **not allowed**.

---

## UML Class Diagram Description (Figure 2)

The student hierarchy consists of one abstract base class and two concrete subclasses:

### 1. Abstract Class `Student` («abstract»)
* **Attributes (Protected - marked with `#`):**
    * `id: String`
    * `fullName: String`
    * `cohort: String`
    * `major: String`
    * `disciplineScore: int (0-100)`
    * `transcript: Transcript (not null)`
* **Operations:**
    * `# Student(...)`: Protected constructor.
    * Public Getter methods for all attributes.
    * `# getTranscript(): Transcript`: Protected getter for the transcript.
    * Public Setter methods for: `cohort`, `major`, and `disciplineScore`.
    * `+ getEnglishName(): String`
    * `+ getGpa4(): double`
    * `+ failedCount(): int`
    * `+ tuitionDiscountRate(): double` («abstract»)

### 2. Class `RegularStudent` (Extends `Student`)
* **Operations:**
    * `+ RegularStudent(...)`: Constructor.
    * `+ tuitionDiscountRate(): double` {override}: Always returns `0.0`.
    * `+ eligibleForHonorsUpgrade(): boolean`: Checks eligibility based on: $GPA \ge 3.4$ && $disciplineScore \ge 85$ && $failedCount == 0$.

### 3. Class `HonorsStudent` (Extends `Student`)
* **Operations:**
    * `+ HonorsStudent(...)`: Constructor.
    * `+ tuitionDiscountRate(): double` {override}: Returns `0.1` if $GPA \ge 3.2$ && $disciplineScore \ge 80$; otherwise returns `0.0`.
    * `+ maintainsHonorsStatus(): boolean`: Checks status maintenance based on: $GPA \ge 3.2$ && $disciplineScore \ge 75$ && $failedCount == 0$.

---

## Tasks

### 1. Implement the abstract class `Student`
Create an abstract class `Student` with the following protected attributes: `id`, `fullName`, `cohort`, `major`, `disciplineScore` (range [0, 100]), and `transcript` (must not be null). All attributes must be properly encapsulated (not public).

### 2. Define the constructor and accessors for `Student`
Implement a protected constructor:
`Student(id, fullName, cohort, major, disciplineScore, transcript)`

* The constructor must initialize all attributes and reject invalid values.
* If any string attribute (e.g., `id`, `fullName`, `cohort`, `major`) contains leading or trailing whitespace, it must be **trimmed**. For example, `" An Hoa "` $\rightarrow$ `"An Hoa"`.
* Provide getter methods for all attributes. Provide setter methods only for: `cohort`, `major`, and `disciplineScore`.
* **Note:** The GPA must **not** be stored or modified directly in the `Student` class.

### 3. Implement `getEnglishName()`
Implement the method:
`String getEnglishName()`

This method converts a Vietnamese full name into English name order:
* Family name goes last.
* Given name and middle names come first.
* Middle names (if multiple) and the given name are joined using hyphens.

**Examples:**
* `"Le Van An"` $\rightarrow$ `"Van-An Le"`
* `"Tran An"` $\rightarrow$ `"An Tran"`
*(You may assume that each name contains at least two words).*

### 4. Declare the abstract tuition policy
Declare the abstract method:
`double tuitionDiscountRate();`
This method defines the tuition discount rate for a student and must be implemented by all subclasses.

### 5. Implement GPA-related helper methods in `Student`
To support classification policies without relying on concrete subclasses, the `Student` class must expose the following GPA-related methods:
* `getGpa4()`: Returns the GPA on a 4-point scale by delegating directly to the associated `Transcript` object.
* `failedCount()`: Returns the number of failed courses by delegating directly to `Transcript`.

### 6. Implement the class `RegularStudent`
Create a class `RegularStudent` that extends `Student`. Override `tuitionDiscountRate()` so that it always returns `0.0`.

### 7. Implement `eligibleForHonorsUpgrade()`
Add the method to `RegularStudent`. A regular student is eligible for an honors upgrade if and only if:
* $GPA \ge 3.4$
* $disciplineScore \ge 85$
* `failedCount() == 0`
*(GPA and failed-course information must be obtained via the `Transcript` object).*

### 8. Implement the class `HonorsStudent`
Create a class `HonorsStudent` that extends `Student`. Override `tuitionDiscountRate()` with the following rule:
* Return `0.1` if $GPA \ge 3.2$ and $disciplineScore \ge 80$.
* Otherwise, return `0.0`.
*(GPA must be obtained from `Transcript`. Hard-coding GPA values or storing GPA as a field is not allowed).*

### 9. Implement `maintainsHonorsStatus()`
Add the method to `HonorsStudent`. An honors student maintains honors status if and only if:
* $GPA \ge 3.2$
* $disciplineScore \ge 75$
* `failedCount() == 0`

---

## Design Constraints
* **Do not use `instanceof`.**
* Do not duplicate GPA computation logic.
* All academic calculations must be delegated to `Transcript`.
* Subclass-specific behavior must be implemented via **method overriding**.