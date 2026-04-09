public abstract class Student {
    protected String id;
    protected String fullName;
    protected String cohort;
    protected String major;
    protected int disciplineScore;
    protected Transcript transcript;

    protected Student(
        String id,
        String fullName,
        String cohort,
        String major,
        int disciplineScore,
        Transcript transcript
    ) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("id must be non-empty");
        }
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("fullName must be non-empty");
        }
        if (cohort == null || cohort.trim().isEmpty()) {
            throw new IllegalArgumentException("cohort must be non-empty");
        }
        if (major == null || major.trim().isEmpty()) {
            throw new IllegalArgumentException("major must be non-empty");
        }
        if (disciplineScore < 0 || disciplineScore > 100) {
            throw new IllegalArgumentException("disciplineScore must be in range [0, 100]");
        }
        if (transcript == null) {
            throw new IllegalArgumentException("transcript must not be null");
        }

        this.id = id.trim();
        this.fullName = fullName.trim();
        this.cohort = cohort.trim();
        this.major = major.trim();
        this.disciplineScore = disciplineScore;
        this.transcript = transcript;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCohort() {
        return cohort;
    }

    public String getMajor() {
        return major;
    }

    public int getDisciplineScore() {
        return disciplineScore;
    }

    protected Transcript getTranscript() {
        return transcript;
    }

    public void setCohort(String cohort) {
        if (cohort == null || cohort.trim().isEmpty()) {
            throw new IllegalArgumentException("cohort must be non-empty");
        }
        this.cohort = cohort.trim();
    }

    public void setMajor(String major) {
        if (major == null || major.trim().isEmpty()) {
            throw new IllegalArgumentException("major must be non-empty");
        }
        this.major = major.trim();
    }

    public void setDisciplineScore(int disciplineScore) {
        if (disciplineScore < 0 || disciplineScore > 100) {
            throw new IllegalArgumentException("disciplineScore must be in range [0, 100]");
        }
        this.disciplineScore = disciplineScore;
    }

    public String getEnglishName() {
        String[] parts = fullName.split("\\s+");
        String familyName = parts[0];

        StringBuilder givenAndMiddle = new StringBuilder();
        for (int i = 1; i < parts.length; i++) {
            if (i > 1) {
                givenAndMiddle.append('-');
            }
            givenAndMiddle.append(parts[i]);
        }

        return givenAndMiddle + " " + familyName;
    }

    public double getGpa4() {
        return getTranscript().gpa4();
    }

    public int failedCount() {
        return getTranscript().failedCount();
    }

    public abstract double tuitionDiscountRate();
}
