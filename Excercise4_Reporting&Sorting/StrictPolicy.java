public class StrictPolicy implements ClassificationPolicy {
    @Override
    public Classification classify(Student s) {
        if (s == null) {
            throw new IllegalArgumentException("student must not be null");
        }

        int failedCount = s.failedCount();
        double gpa = s.getGpa4();

        if (failedCount >= 1 || gpa < 2.2) {
            return Classification.FAIL;
        }
        if (gpa >= 3.7) {
            return Classification.EXCELLENT;
        }
        if (gpa >= 3.3) {
            return Classification.GOOD;
        }
        if (gpa >= 2.7) {
            return Classification.FAIR;
        }
        return Classification.PASS;
    }
}
