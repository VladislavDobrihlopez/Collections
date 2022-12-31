package Collections.Interfaces;

public interface CarQueue<T> extends CarCollection<T> {
    public boolean add(T item);

    public T peek();

    public T poll();
}
