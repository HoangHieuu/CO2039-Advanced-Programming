public class Main {
    public static void main(String[] args) {
        RegularStudent tc1 = new RegularStudent(
            "  S2  ",
            "  Le Van An  ",
            "  K20 ",
            " CSE  ",
            80,
            new Transcript()
        );
        assertEqInt("TC1a id trimmed", 1, tc1.getId().equals("S2") ? 1 : 0);
        assertEqInt("TC1b name trimmed", 1, tc1.getFullName().equals("Le Van An") ? 1 : 0);
        assertEqInt("TC1c cohort trimmed", 1, tc1.getCohort().equals("K20") ? 1 : 0);
        assertEqInt("TC1d major trimmed", 1, tc1.getMajor().equals("CSE") ? 1 : 0);

        assertThrows(
            "TC2 transcript=null rejected",
            Throwable.class,
            () -> new RegularStudent("S1", "Le Van An", "K20", "CSE", 80, null)
        );

        RegularStudent tc3a = new RegularStudent("S4", "Le Van An", "K20", "CSE", 0, new Transcript());
        RegularStudent tc3b = new RegularStudent("S5", "Le Van An", "K20", "CSE", 100, new Transcript());
        assertEqInt("TC3a discipline=0 ok", 0, tc3a.getDisciplineScore());
        assertEqInt("TC3b discipline=100 ok", 100, tc3b.getDisciplineScore());

        System.out.println("All tests passed.");
    }

    private static void assertEqInt(String name, int expected, int actual) {
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
