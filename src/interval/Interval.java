package interval;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Interval<T> implements Comparable {

    public T startOfInterval;
    public T endOfInterval;

    public Interval(){
    }

    public Interval(T startOfInterval, T endOfInterval){
        if(startOfInterval instanceof LocalDateTime && endOfInterval instanceof LocalDateTime)
            new IntervalDateTime((LocalDateTime) startOfInterval, (LocalDateTime) endOfInterval);
        else if(startOfInterval instanceof LocalDate && endOfInterval instanceof LocalDate)
            new IntervalDate((LocalDate) startOfInterval, (LocalDate) endOfInterval);
    }

    public T getStartOfInterval(){
        return this.startOfInterval;
    }

    public void setStartOfInterval(Interval<T> startOfInterval){
        this.startOfInterval = (T) startOfInterval;
    }

    public T getEndOfInterval(){
        return this.endOfInterval;
    }

    public void setEndOfInterval(Interval<T> endOfInterval){
        this.endOfInterval = (T) endOfInterval;
    }

    public abstract int compareTo(Interval<T> otherInterval);

    public abstract boolean equals(Interval<T> otherInterval);

    public String toString(){
        return this.startOfInterval.toString() + " " + this.endOfInterval.toString();
    }

}
