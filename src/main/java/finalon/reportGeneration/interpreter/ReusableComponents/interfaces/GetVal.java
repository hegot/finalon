package finalon.reportGeneration.interpreter.ReusableComponents.interfaces;


public interface GetVal extends Round {
    default String partStr(Double val, Double total) {
        return format(part(val, total));
    }

    default String format(Double input) {
        return round(input) + '%';
    }

    default Double part(Double val, Double total) {
        return (val / total) * 100;
    }
}
