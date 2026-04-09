public class Main {
    public static void main(String[] args) {
        ClassificationPolicy standard = new StandardPolicy();

        assertThrows(
            "TC1 Standard: classify(null) -> NPE",
            Throwable.class,
            () -> standard.classify(null)
        );

        Student tc2 = studentWithCourses("TC2", new CourseResult("C1", 3, 4.0));
        assertEqClassif("TC2 Standard: GPA<2.0 -> FAIL", Classification.FAIL, standard.classify(tc2));

        Student tc3 = studentWithCourses("TC3", new CourseResult("C1", 3, 5.5));
        assertEqClassif("TC3 Standard: GPA=2.0 -> PASS", Classification.PASS, standard.classify(tc3));

        System.out.println("All tests passed.");
    }

    private static Student studentWithCourses(String id, CourseResult... results) {
        Transcript transcript = new Transcript();
        for (CourseResult result : results) {
            transcript.addCourseResult(result);
        }
        return new RegularStudent(id, "Le Van An", "K20", "CSE", 90, transcript);
    }

    private static void assertEqClassif(String name, Classification expected, Classification actual) {
        if (expected != actual) {
            throw new AssertionError(name + " | expected: " + expected + ", actual: " + actual);
        }
        System.out.println("PASS: " + name);
    }

    private static void assertThrows(String name, Class<? extends Throwable> expected, Runnable action) {
        try {
            action.run();
        } catch (Throwable t) {
            if (expected.isInstance(t)) {
                System.out.println("PASS: " + name);
                return;
            }
            throw new AssertionError(
                name + " | expected exception: " + expected.getName() + ", actual: " + t.getClass().getName()
            );
        }
        throw new AssertionError(name + " | expected exception: " + expected.getName() + ", but none was thrown");
    }
}
