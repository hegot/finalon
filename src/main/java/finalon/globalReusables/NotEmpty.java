package finalon.globalReusables;

public class NotEmpty {
    public static boolean notZero(Double val){
        if(val != null && val != 0.0 && !val.isInfinite() && !val.isNaN()){
            return true;
        }
        return false;
    }
}
