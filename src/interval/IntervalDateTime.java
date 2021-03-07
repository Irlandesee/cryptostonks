package interval;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;

public class IntervalDateTime extends Interval{

    private LocalDateTime startOfInterval = LocalDateTime.MIN;
    private LocalDateTime endOfInterval = LocalDateTime.MAX;

    public IntervalDateTime(){

    }

    public IntervalDateTime(LocalDateTime start, LocalDateTime end){
        this.startOfInterval = start;
        this.endOfInterval = end;
    }

    public LocalDateTime getStart(){
        return this.startOfInterval;
    }

    public String getIntervalStartString(){
        return startOfInterval.toString();
    }

    public LocalDateTime getEnd(){
        return this.endOfInterval;
    }

    public String getIntervalEndString(){return this.endOfInterval.toString();}

    /**
     * @param otherInterval
     * @return 0 if they are equal
     * 1 if this is after otherinterval
     *-1 if this is before otherinterval
     * what if they cross?
     */
    @Override
    public int compareTo(Interval otherInterval){
        if(this.equals(otherInterval))
            return 0;
        else if(this.getStart().isBefore((ChronoLocalDateTime<?>) otherInterval.getStartOfInterval())
            && this.getEnd().isBefore((ChronoLocalDateTime<?>) otherInterval.getEndOfInterval()))
            return -1;
        return 1;
    }
    @Override
    public boolean equals(Interval otherInterval){
        if(otherInterval instanceof IntervalDateTime){
            if(this.getStart().isEqual((ChronoLocalDateTime<?>) otherInterval.getStartOfInterval()) &&
                    this.getEnd().isEqual((ChronoLocalDateTime<?>) otherInterval.getEndOfInterval()))
                return true; }
        return false;
    }

    @Override
    public String toString(){
        return this.getIntervalStartString() + " " + this.getIntervalEndString();
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Interval)
            this.compareTo((IntervalDateTime) o);
        return -1;
    }
}
