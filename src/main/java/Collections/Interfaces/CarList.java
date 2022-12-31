package Collections.Interfaces;

public interface CarList<T> extends CarCollection<T> {
    public T get(int index);

    public boolean add(T item);

    public boolean add(T item, int index);

    public boolean remove(T item);

    public boolean removeAt(int index);

    public boolean contains(T item);

    public int size();

    public void clear();
}
