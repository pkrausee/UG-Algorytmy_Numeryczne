package Models;

public class PairResult<T, V>
{
    public T First;
    public V Second;

    public PairResult() {
    }

    public PairResult(T First, V Second) {
        this.First = First;
        this.Second = Second;
    }
}
