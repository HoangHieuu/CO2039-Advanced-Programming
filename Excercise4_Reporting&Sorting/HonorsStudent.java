public class HonorsStudent extends Student {
    public HonorsStudent(
        String id,
        String fullName,
        String cohort,
        String major,
        int disciplineScore,
        Transcript transcript
    ) {
        super(id, fullName, cohort, major, disciplineScore, transcript);
    }

    @Override
    public double tuitionDiscountRate() {
        return (getGpa4() >= 3.2 && getDisciplineScore() >= 80) ? 0.1 : 0.0;
    }

    public boolean maintainsHonorsStatus() {
        return getGpa4() >= 3.2 && getDisciplineScore() >= 75 && failedCount() == 0;
    }
}
