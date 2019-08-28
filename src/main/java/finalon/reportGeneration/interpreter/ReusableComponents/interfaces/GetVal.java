package finalon.reportGeneration.interpreter.ReusableComponents.interfaces;


public interface GetVal {
    default String partStr(Double val, Double total) {
        return format(part(val, total));
    }

    default String format(Double input) {
        return Round.format(input) + '%';
    }

    default Double part(Double val, Double total) {
        return (val / total) * 100;
    }
}
