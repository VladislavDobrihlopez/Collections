package Collections;

public interface CarList {
    public Car get(int index);

    public void add(Car car);

    public void add(Car car, int index);

    public boolean remove(Car car);

    public boolean removeAt(int index);

    public int size();

    public void clear();
}
