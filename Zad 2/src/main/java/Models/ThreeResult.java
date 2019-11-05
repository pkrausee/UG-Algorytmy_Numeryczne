package Models;

public class ThreeResult<T, V, X> {
    public T First;
    public V Second;
    public X Third;

    public ThreeResult() {
    }

    public ThreeResult(T First, V Second, X Third) {
        this.First = First;
        this.Second = Second;
        this.Third = Third;
    }
}