package reportGeneration.interpreter.FinancialRating.Outcomes;

import entities.Formula;
import reportGeneration.interpreter.ReusableComponents.interfaces.RatingWeight;
import reportGeneration.storage.Periods;

import java.text.DecimalFormat;

public class ScoreItem implements RatingWeight {
    private String code;
    private Formula formula;
    private Double weight;
    private String name;
    private String per0;
    private String per1;
    private String per2;
    private Double score1;
    private Double score2;
    private Double averageScore;
    private Double weightedScore;
    private DecimalFormat df;

    public ScoreItem(
            Formula formula
    ) {
        Periods periods = Periods.getInstance();
        this.formula = formula;
        this.name = formula.getName();
        this.per0 = periods.prePreEndKey();
        this.per1 = periods.preEndKey();
        this.per2 = periods.endKey();
        this.code = formula.getShortName();
        this.df = new DecimalFormat("#.##");
        if(formula.getShortName().length() > 0){
            this.weight = getWeight(code);
            this.score1 = calcScore1();
            this.score2 = calcScore2();
            this.averageScore = calcAverageScore();
            this.weightedScore = calcWeightedScore();
        }
    }

    private Double calcWeightedScore() {
        if (averageScore != null) {
            return Double.valueOf(df.format(weight * averageScore));
        }
        return null;
    }

    private Double calcAverageScore() {
        Double res = score2;
        if (score1 != null && score2 != null) {
            res = score1 * 0.35 + score2 * 0.65;
            if(res != null){
                res = Double.valueOf(df.format(res));
            }
        }
        return res;
    }

    private Double calcScore1() {
        if (per0 != null && per1 != null) {
            RatingCalc ratingCalc = new RatingCalc(formula, per0, per1);
            return ratingCalc.getValue();
        }
        return null;
    }

    private Double calcScore2() {
        if (per1 != null && per2 != null) {
            RatingCalc ratingCalc = new RatingCalc(formula, per1, per2);
            return ratingCalc.getValue();
        }
        return null;
    }

    public void setWeightedScore(Double weightedScore) {
        this.weightedScore = weightedScore;
    }

    public Double getWeightedScore() {
        return this.weightedScore;
    }

    public Double getAverageScore() {
        return this.averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    public Double getScore1() {
        return this.score1;
    }

    public void setScore1(Double score1) {
        this.score1 = score1;
    }

    public Double getScore2() {
        return this.score2;
    }

    public void setScore2(Double score2) {
        this.score2 = score2;
    }

    public Double getWeight() {
        return this.weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
