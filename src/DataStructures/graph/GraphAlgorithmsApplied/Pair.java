package Graphs.GraphAlgorithmsApplied;

public class Pair<T> {

    private ConsistencyVertex<T> i;
    private ConsistencyVertex<T> j;

    public Pair(ConsistencyVertex<T> i, ConsistencyVertex<T> j) {
        this.i = i;
        this.j = j;
    }

    public ConsistencyVertex<T> getI() {
        return i;
    }

    public void setI(ConsistencyVertex<T> i) {
        this.i = i;
    }

    public ConsistencyVertex<T> getJ() {
        return j;
    }

    public void setJ(ConsistencyVertex<T> j) {
        this.j = j;
    }

    @Override
    public int hashCode() {
        return i.hashCode() ^ j.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof Pair<?>) {
            Pair<?> p = (Pair<?>) o;
            return (i.equals(p.i) && j.equals(p.j) || i.equals(p.j) && j.equals(p.i)) ;
        } else {
            return false;
        }
    }
}
