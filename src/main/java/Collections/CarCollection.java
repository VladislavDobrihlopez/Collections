package Collections;

public interface CarCollection extends Iterable<Car> {
    public boolean add(Car car);

    public int size();

    public boolean contains(Car car);

    public boolean remove(Car car);

    public void clear();
}
