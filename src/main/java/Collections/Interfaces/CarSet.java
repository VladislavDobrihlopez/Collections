package Collections.Interfaces;

public interface CarSet<T> extends CarCollection<T> {
    public boolean add(T item);

    public int size();

    public boolean remove(T item);

    public boolean contains(T item);

    public void clear();
}
