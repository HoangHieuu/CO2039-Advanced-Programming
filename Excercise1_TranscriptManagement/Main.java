public class Main {
    public static void main(String[] args) {
        Transcript t1 = new Transcript();
        assertEqDouble1("T1 empty GPA", 0.0, t1.gpa4());
        assertEqInt("T1 empty failed", 0, t1.failedCount());

        Transcript t2 = new Transcript();
        t2.addCourseResult(new CourseResult("C1", 3, 8.5));
        assertEqDouble1("T2 single 8.5 GPA", 4.0, t2.gpa4());
        assertEqInt("T2 failed=0", 0, t2.failedCount());

        Transcript t3 = new Transcript();
        t3.addCourseResult(new CourseResult("C1", 3, 4.0));
        assertEqDouble1("T3 single failed GPA", 1.0, t3.gpa4());
        assertEqInt("T3 failed=1", 1, t3.failedCount());

        System.out.println("All tests passed.");
    }

    private static void assertEqDouble1(String name, double expected, double actual) {
        double expectedRounded = Math.round(expected * 10.0) / 10.0;
        double actualRounded = Math.round(actual * 10.0) / 10.0;
        if (Double.compare(expectedRounded, actualRounded) != 0) {
            throw new AssertionError(
                name + " | expected: " + expectedRounded + ", actual: " + actualRounded
            );
        }
        System.out.println("PASS: " + name);
    }

    private static void assertEqInt(String name, int expected, int actual) {
        if (expected != actual) {
            throw new AssertionError(name + " | expected: " + expected + ", actual: " + actual);
        }
        System.out.println("PASS: " + name);
    }
}
