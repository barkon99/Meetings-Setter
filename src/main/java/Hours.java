import java.time.LocalTime;
import java.util.Objects;

public class Hours
{
    private LocalTime start;
    private LocalTime end;

    public Hours(LocalTime start, LocalTime end) {
        if(start.compareTo(end) < 0){ //sprawdzenie poprawnosci godzin
            this.start = start;
            this.end = end;
        }
        else {
            System.out.println("Hour of start can not be later than hour of end!");
        }

    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "[" +
                "start=" + start +
                ", end=" + end +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hours)) return false;
        Hours hours = (Hours) o;
        return Objects.equals(start, hours.start) &&
                Objects.equals(end, hours.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
