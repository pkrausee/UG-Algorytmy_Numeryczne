package Models;

public class Pair <T, V>
{
    public T First;
    public V Second;

    public Pair() {
    }

    public Pair(T first, V second) {
        First = first;
        Second = second;
    }
}
