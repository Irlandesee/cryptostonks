package interval;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;


public class IntervalDate extends Interval{

    private LocalDate startOfInterval = LocalDate.MIN;
    private LocalDate endOfInterval = LocalDate.MAX;

    public IntervalDate(){}

    public IntervalDate(LocalDate startOfInterval, LocalDate endOfInterval){
        this.startOfInterval = startOfInterval;
        this.endOfInterval = endOfInterval;
    }

    public LocalDate getStart(){
        return this.startOfInterval;
    }

    public String getStartString(){
        return this.startOfInterval.toString();
    }

    public LocalDate getEnd(){
        return this.endOfInterval;
    }

    public String getEndString(){
        return endOfInterval.toString();
    }

    @Override
    public boolean equals(Interval otherInterval){
        if(otherInterval instanceof IntervalDate){
            if(this.getStart().isEqual((ChronoLocalDate) otherInterval.getStartOfInterval()) &&
                    this.getEnd().isEqual((ChronoLocalDate) otherInterval.getEndOfInterval()))
                return true;
        }
        return false;
    }

    @Override
    public int compareTo(Interval otherInterval){
        if(this.equals(otherInterval))
            return 0;
        else if(this.getStart().isBefore((ChronoLocalDate) otherInterval.getStartOfInterval())
        && this.getEnd().isBefore((ChronoLocalDate) otherInterval.getEndOfInterval()))
            return -1;
        return 1;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Interval)
            this.compareTo((IntervalDateTime) o);
        return -1;
    }

    @Override
    public String toString(){
        return this.getStartString() + " " + this.getEndString();
    }

}
