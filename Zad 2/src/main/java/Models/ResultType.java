package Models;

import java.util.List;

public class ResultType <TType extends Number>{
    public List<TType[]> results;

    public ResultType(List<TType[]> results) {
        this.results = results;
    }

    public void reset(List<TType[]> list){
        this.results = list;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        for (TType[] result : results) {
            for (TType t : result) {
                builder.append(t);
                builder.append(" ");
            }
            builder.append(System.lineSeparator());
        }

        return builder.toString();
    }
}
