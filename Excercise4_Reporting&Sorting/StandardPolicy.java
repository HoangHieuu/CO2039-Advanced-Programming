public class StandardPolicy implements ClassificationPolicy {
    @Override
    public Classification classify(Student s) {
        if (s == null) {
            throw new IllegalArgumentException("student must not be null");
        }

        int failedCount = s.failedCount();
        double gpa = s.getGpa4();

        if (failedCount >= 2 || gpa < 2.0) {
            return Classification.FAIL;
        }
        if (gpa >= 3.6 && failedCount == 0) {
            return Classification.EXCELLENT;
        }
        if (gpa >= 3.2) {
            return Classification.GOOD;
        }
        if (gpa >= 2.5) {
            return Classification.FAIR;
        }
        return Classification.PASS;
    }
}
