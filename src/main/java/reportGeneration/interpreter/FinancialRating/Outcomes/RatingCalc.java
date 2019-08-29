package reportGeneration.interpreter.FinancialRating.Outcomes;

import entities.Formula;
import entities.Item;
import reportGeneration.storage.ItemsStorage;

public class RatingCalc {
    private static double BAD = -1;
    private static double UNSAT = -0.5;
    private static double NEUT = 0;
    private static double GOOD = 0.5;
    private static double EXC = 1;
    private String endPeriod;
    private String startPeriod;
    private String type;
    private Double formulaVal;

    public RatingCalc(Formula formula, String startPeriod, String endPeriod) {
        this.formulaVal = formula.getVal(endPeriod);
        this.endPeriod = endPeriod;
        this.startPeriod = startPeriod;
        this.type = formula.getShortName();
    }

    private Double getValForType() {
        Double val = -1.0;
        if (formulaVal != null) {
            switch (type) {
                case "NetProfitMargin":
                    val = calcNetProfitMargin();
                    break;
                case "ReturnOnAssets":
                    val = calcReturnOnAssets();
                    break;
                case "DebtEquityRatio":
                    val = calcDebtEquityRatio();
                    break;
                case "CurrentRatio":
                    val = calcCurrentRatio();
                    break;
                case "NetSalesChange":
                    val = calcNetSalesChange();
                    break;
                case "OperatingIncomeMargin":
                    val = calcOperatingIncomeMargin();
                    break;
                case "EquityChange":
                    val = calcEquityChange();
                    break;
                case "QuickRatio":
                    val = calcQuickRatio();
                    break;
                case "DebtRatio":
                    val = calcDebtRatio();
                    break;
                case "TimesInterestEarned":
                    val = calcTimesInterestEarned();
                    break;
            }
        }
        return val;
    }

    private Double calcNetProfitMargin() {
        Item item = ItemsStorage.get("RevenueGeneral");
        Double val = BAD;
        if (item != null) {
            Double itemVal = (endPeriod != null) ? item.getVal(endPeriod) : -1;
            if (itemVal > 0) {
                if (formulaVal > 0.05) {
                    val = EXC;
                } else if (formulaVal >= 0.025) {
                    val = GOOD;
                } else if (formulaVal >= 0.01) {
                    val = NEUT;
                } else if (formulaVal >= 0) {
                    val = UNSAT;
                }
            }
        }
        return val;
    }

    private Double calcReturnOnAssets() {
        double val = BAD;
        Item item = ItemsStorage.get("AssetsGeneral");
        if (item != null) {
            Double itemVal = (endPeriod != null) ? item.getVal(endPeriod) : -1;
            Double itemValStart = (startPeriod != null) ? item.getVal(startPeriod) : -1;
            double itemsSum = itemVal + itemValStart;
            if (itemsSum > 0) {
                if (formulaVal > 0.2) {
                    val = EXC;
                } else if (formulaVal >= 0.07) {
                    val = GOOD;
                } else if (formulaVal >= 0.02) {
                    val = NEUT;
                } else if (formulaVal >= 0) {
                    val = UNSAT;
                }
            }
        }
        return val;
    }

    private Double calcCurrentRatio() {
        double val = BAD;
        if (formulaVal > 2) {
            val = EXC;
        } else if (formulaVal >= 1.5) {
            val = GOOD;
        } else if (formulaVal >= 1) {
            val = NEUT;
        } else if (formulaVal >= 0.7) {
            val = UNSAT;
        }
        return val;
    }


    private Double calcDebtEquityRatio() {
        Item item = ItemsStorage.get("EquityGeneral");
        Double val = BAD;
        if (item != null) {
            Double itemVal = (endPeriod != null) ? item.getVal(endPeriod) : -1;
            if (itemVal > 0) {
                if (formulaVal <= 0.6) {
                    val = EXC;
                } else if (formulaVal < 0.8) {
                    val = GOOD;
                } else if (formulaVal < 1) {
                    val = NEUT;
                } else if (formulaVal < 1.2) {
                    val = UNSAT;
                }
            }
        }
        return val;
    }


    private Double calcNetSalesChange() {
        Item item = ItemsStorage.get("RevenueGeneral");
        double val = BAD;
        if (item != null) {
            Double itemVal = item.getVal(endPeriod);
            double itemValStart = (startPeriod != null) ? item.getVal(startPeriod) : 0.0;
            if (itemVal > 0 && itemValStart > 0) {
                if (formulaVal > 0.3) {
                    val = EXC;
                } else if (formulaVal >= 0.1) {
                    val = GOOD;
                } else if (formulaVal >= 0) {
                    val = NEUT;
                } else if (formulaVal >= -0.1) {
                    val = UNSAT;
                }
            }
        }
        return val;
    }

    private Double calcOperatingIncomeMargin() {
        double val = BAD;
        if (formulaVal > 0.1) {
            val = EXC;
        } else if (formulaVal >= 0.075) {
            val = GOOD;
        } else if (formulaVal >= 0.05) {
            val = NEUT;
        } else if (formulaVal >= 0) {
            val = UNSAT;
        }
        return val;
    }

    private Double calcEquityChange() {
        Item item = ItemsStorage.get("EquityGeneral");
        double val = BAD;
        if (item != null) {
            Double itemVal = item.getVal(endPeriod);
            double itemValStart = (startPeriod != null) ? item.getVal(startPeriod) : 0.0;
            if (itemVal > 0 && itemValStart > 0) {
                if (formulaVal > 0.1) {
                    val = EXC;
                } else if (formulaVal >= 0.05) {
                    val = GOOD;
                } else if (formulaVal >= 0) {
                    val = NEUT;
                } else if (formulaVal >= -0.05) {
                    val = UNSAT;
                }
            }
        }
        return val;
    }

    private Double calcQuickRatio() {
        double val = BAD;
        if (formulaVal >= 1) {
            val = EXC;
        } else if (formulaVal >= 0.9) {
            val = GOOD;
        } else if (formulaVal >= 0.8) {
            val = NEUT;
        } else if (formulaVal >= 0.7) {
            val = UNSAT;
        }
        return val;
    }

    private Double calcDebtRatio() {
        Double val = BAD;
        Item item = ItemsStorage.get("EquityAndLiabilities");
        if (item != null) {
            Double itemVal = item.getVal(endPeriod);
            if (itemVal != null && itemVal > 0) {
                if (formulaVal < 0.5) {
                    val = EXC;
                } else if (formulaVal < 0.6) {
                    val = GOOD;
                } else if (formulaVal < 0.7) {
                    val = NEUT;
                } else if (formulaVal < 0.8) {
                    val = UNSAT;
                }
            }
        }
        return val;
    }

    private Double calcTimesInterestEarned() {
        double val = BAD;
        Item item = ItemsStorage.get("FinanceCosts");
        if (item != null) {
            Double itemVal = item.getVal(endPeriod);
            if (formulaVal > 3 || itemVal == 0) {
                val = EXC;
            } else if (formulaVal >= 2) {
                val = GOOD;
            } else if (formulaVal >= 1) {
                val = NEUT;
            } else if (formulaVal >= 0.8) {
                val = UNSAT;
            }
        }
        return val;
    }

    public Double getValue() {
        return getValForType();
    }
}
