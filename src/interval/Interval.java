package interval;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Interval<T> implements Comparable {

    public T startOfInterval;
    public T endOfInterval;

    public Interval(){
    }

    public T getStartOfInterval(){
        return this.startOfInterval;
    }

    public T getEndOfInterval(){
        return this.endOfInterval;
    }

    public abstract int compareTo(Interval<T> otherInterval);

    public abstract boolean equals(Interval<T> otherInterval);

    public String toString(){
        return this.startOfInterval.toString() + " " + this.endOfInterval.toString();
    }

}
