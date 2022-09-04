package Collections;

public interface CarList extends CarCollection {
    public Car get(int index);

    public boolean add(Car car);

    public boolean add(Car car, int index);

    public boolean remove(Car car);

    public boolean removeAt(int index);

    public boolean contains(Car car);


    public int size();

    public void clear();
}
