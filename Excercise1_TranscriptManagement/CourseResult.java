public class CourseResult implements GradedItem {
    private final String courseId;
    private final int credits;
    private final double score;

    public CourseResult(String courseId, int credits, double score) {
        if (courseId == null || courseId.trim().isEmpty()) {
            throw new IllegalArgumentException("courseId must be non-empty");
        }
        if (credits <= 0) {
            throw new IllegalArgumentException("credits must be greater than 0");
        }
        if (score < 0.0 || score > 10.0) {
            throw new IllegalArgumentException("score must be in range [0.0, 10.0]");
        }

        this.courseId = courseId;
        this.credits = credits;
        this.score = score;
    }

    public String getCourseId() {
        return courseId;
    }

    @Override
    public int getCredits() {
        return credits;
    }

    @Override
    public double getScore() {
        return score;
    }
}
