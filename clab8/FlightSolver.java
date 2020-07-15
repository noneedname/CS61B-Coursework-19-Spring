import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {
    int maxNumOfPassengers = 0;
    int tally = 0;

    public FlightSolver(ArrayList<Flight> flights) {
        PriorityQueue<Flight> startMinPQ = new PriorityQueue<>(
            (Flight f1, Flight f2) -> Integer.compare(f1.startTime(), f2.startTime())
        );
        PriorityQueue<Flight> endMinPQ = new PriorityQueue<>(
            (Flight f1, Flight f2) -> Integer.compare(f1.endTime(), f2.endTime())
        );
        startMinPQ.addAll(flights);
        endMinPQ.addAll(flights);

        while (startMinPQ.size() != 0) {
            if (startMinPQ.peek().startTime() <= endMinPQ.peek().endTime()) {
                tally += startMinPQ.poll().passengers;
            } else {
                tally -= endMinPQ.poll().passengers;
            }
            maxNumOfPassengers = Math.max(tally, maxNumOfPassengers);
        }
    }

    public int solve() {
        return maxNumOfPassengers;
    }

}
