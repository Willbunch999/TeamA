
/**
 *
 * @author bhill
 */

public enum PunchType {
   CLOCK_OUT("CLOCK OUT"),
    CLOCK_IN("CLOCK IN"),
    TIME_OUT("TIME OUT");
    private final String description;
    private PunchType(String d) { description = d; }
    @Override
    public String toString() { return description; } 
 }
