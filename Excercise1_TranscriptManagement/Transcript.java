import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Transcript {
    private final List<CourseResult> results;

    public Transcript() {
        this.results = new ArrayList<>();
    }

    public void addCourseResult(CourseResult r) {
        if (r == null) {
            throw new IllegalArgumentException("course result must not be null");
        }
        results.add(r);
    }

    public double gpa4() {
        if (results.isEmpty()) {
            return 0.0;
        }

        int totalCredits = 0;
        double weightedSum = 0.0;

        for (CourseResult result : results) {
            double score = result.getScore();
            double point4;

            if (score >= 8.5) {
                point4 = 4.0;
            } else if (score >= 7.0) {
                point4 = 3.0;
            } else if (score >= 5.5) {
                point4 = 2.0;
            } else if (score >= 4.0) {
                point4 = 1.0;
            } else {
                point4 = 0.0;
            }

            int credits = result.getCredits();
            totalCredits += credits;
            weightedSum += point4 * credits;
        }

        if (totalCredits == 0) {
            return 0.0;
        }

        double gpa = weightedSum / totalCredits;
        return Math.round(gpa * 10.0) / 10.0;
    }

    public int failedCount() {
        int count = 0;
        for (CourseResult result : results) {
            if (result.getScore() < 5.0) {
                count++;
            }
        }
        return count;
    }

    public List<CourseResult> getResults() {
        return Collections.unmodifiableList(new ArrayList<>(results));
    }
}
