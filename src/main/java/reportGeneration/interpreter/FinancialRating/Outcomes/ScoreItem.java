package reportGeneration.interpreter.FinancialRating.Outcomes;

import entities.Formula;
import reportGeneration.interpreter.ReusableComponents.helpers.Formatter;
import reportGeneration.interpreter.ReusableComponents.helpers.RatingWeight;
import reportGeneration.storage.Periods;

import java.text.DecimalFormat;

public class ScoreItem {
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
    private String score1Str;
    private String score2Str;
    private String averageScoreStr;
    private String weightedScoreStr;
    private String weightStr;
    private DecimalFormat df;

    public ScoreItem(
            Formula formula
    ) {
        this.formula = formula;
        this.name = formula.getName();
        this.per0 = Periods.prePreEndKey();
        this.per1 = Periods.preEndKey();
        this.per2 = Periods.endKey();
        this.code = formula.getShortName();
        this.df = new DecimalFormat("#.##");
        if (formula.getShortName().length() > 0) {
            this.weight = RatingWeight.getWeight(code);
            this.score1 = calcScore1();
            this.score2 = calcScore2();
            this.averageScore = calcAverageScore();
            this.weightedScore = calcWeightedScore();

            this.weightStr = Formatter.commaFormat(weight);
            this.score1Str = Formatter.commaFormat(score1);
            this.score2Str = Formatter.commaFormat(score2);
            this.averageScoreStr = Formatter.commaFormat(averageScore);
            this.weightedScoreStr = Formatter.commaFormat(weightedScore);
        }
    }

    private Double calcWeightedScore() {
        if (averageScore != null) {
            String formatted = df.format(weight * averageScore);
            formatted = Formatter.clean(formatted);
            return Double.valueOf(formatted);
        }
        return null;
    }

    private Double calcAverageScore() {
        Double res = score2;
        if (score1 != null && score2 != null) {
            res = score1 * 0.35 + score2 * 0.65;
            if (res != null) {
                String formatted = Formatter.clean(df.format(res));
                res = Double.valueOf(formatted);
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
        return 0.0;
    }

    public Double getWeightedScore() {
        return this.weightedScore;
    }

    public void setWeightedScore(Double weightedScore) {
        this.weightedScore = weightedScore;
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

    public String getWeightStr() {
        return this.weightStr;
    }

    public String getScore1Str() {
        return this.score1Str;
    }

    public String getScore2Str() {
        return this.score2Str;
    }

    public String getAverageScoreStr() {
        return this.averageScoreStr;
    }

    public String getWeightedScoreStr() {
        return this.weightedScoreStr;
    }
}
