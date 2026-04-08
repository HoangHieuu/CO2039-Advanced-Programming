import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public final class CohortStats {
    private final String cohort;
    private final int total;
    private final Map<Classification, Integer> counts;
    private final int regularCount;
    private final int honorsCount;

    public CohortStats(
        String cohort,
        int total,
        Map<Classification, Integer> counts,
        int regularCount,
        int honorsCount
    ) {
        if (cohort == null || cohort.trim().isEmpty()) {
            throw new IllegalArgumentException("cohort must be non-empty");
        }
        if (total < 0 || regularCount < 0 || honorsCount < 0) {
            throw new IllegalArgumentException("counts must be non-negative");
        }
        if (counts == null) {
            throw new IllegalArgumentException("classification counts must not be null");
        }

        this.cohort = cohort.trim();
        this.total = total;
        this.regularCount = regularCount;
        this.honorsCount = honorsCount;

        EnumMap<Classification, Integer> copied = new EnumMap<>(Classification.class);
        for (Classification classification : Classification.values()) {
            Integer value = counts.get(classification);
            copied.put(classification, value == null ? 0 : value);
        }
        this.counts = Collections.unmodifiableMap(copied);
    }

    public String getCohort() {
        return cohort;
    }

    public int getTotal() {
        return total;
    }

    public Map<Classification, Integer> getCounts() {
        return counts;
    }

    public int getRegularCount() {
        return regularCount;
    }

    public int getHonorsCount() {
        return honorsCount;
    }
}
