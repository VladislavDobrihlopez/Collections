package Collections.Interfaces;

public interface CarCollection<T> extends Iterable<T> {
    public boolean add(T item);

    public int size();

    public boolean contains(T item);

    public boolean remove(T item);

    public void clear();
}
