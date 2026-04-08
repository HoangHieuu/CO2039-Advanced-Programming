import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ReportingService {
    private final StudentRepository repo;

    public ReportingService(StudentRepository repo) {
        if (repo == null) {
            throw new IllegalArgumentException("repository must not be null");
        }
        this.repo = repo;
    }

    public Map<Classification, List<Student>> groupByClassification(ClassificationPolicy policy) {
        if (policy == null) {
            throw new IllegalArgumentException("policy must not be null");
        }

        EnumMap<Classification, List<Student>> grouped = new EnumMap<>(Classification.class);
        for (Classification classification : Classification.values()) {
            grouped.put(classification, new ArrayList<>());
        }

        for (Student student : repo.findAll()) {
            Classification classification = policy.classify(student);
            if (classification == null) {
                throw new IllegalStateException("policy returned null classification");
            }
            grouped.get(classification).add(student);
        }

        EnumMap<Classification, List<Student>> result = new EnumMap<>(Classification.class);
        for (Map.Entry<Classification, List<Student>> entry : grouped.entrySet()) {
            result.put(entry.getKey(), Collections.unmodifiableList(new ArrayList<>(entry.getValue())));
        }
        return Collections.unmodifiableMap(result);
    }

    public List<Student> topKByGpa(int k) {
        if (k <= 0) {
            return Collections.emptyList();
        }

        List<Student> ranked = new ArrayList<>(repo.findAll());
        Comparator<Student> comparator = Comparator
            .comparingDouble(Student::getGpa4).reversed()
            .thenComparing(Comparator.comparingInt(Student::getDisciplineScore).reversed())
            .thenComparingInt(Student::failedCount)
            .thenComparing(Student::getEnglishName, Comparator.reverseOrder());

        ranked.sort(comparator);

        int limit = Math.min(k, ranked.size());
        return Collections.unmodifiableList(new ArrayList<>(ranked.subList(0, limit)));
    }

    public CohortStats statsByCohort(String cohort, ClassificationPolicy policy) {
        if (cohort == null || cohort.trim().isEmpty()) {
            throw new IllegalArgumentException("cohort must be non-empty");
        }
        if (policy == null) {
            throw new IllegalArgumentException("policy must not be null");
        }

        String normalizedCohort = cohort.trim();

        EnumMap<Classification, Integer> counts = new EnumMap<>(Classification.class);
        for (Classification classification : Classification.values()) {
            counts.put(classification, 0);
        }

        int total = 0;
        int regularCount = 0;
        int honorsCount = 0;

        for (Student student : repo.findAll()) {
            if (!student.getCohort().equals(normalizedCohort)) {
                continue;
            }

            total++;

            Classification classification = policy.classify(student);
            if (classification == null) {
                throw new IllegalStateException("policy returned null classification");
            }
            counts.put(classification, counts.get(classification) + 1);

            if (student instanceof RegularStudent) {
                regularCount++;
            } else if (student instanceof HonorsStudent) {
                honorsCount++;
            }
        }

        return new CohortStats(normalizedCohort, total, counts, regularCount, honorsCount);
    }
}
