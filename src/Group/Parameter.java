package Group;

import java.util.Objects;

public class Parameter {

    private int spendHourStandard;
    private int spendMoneyStandard;

    public Parameter(int spendHourStandard, int spendMoneyStandard) {
        this.spendHourStandard = spendHourStandard;
        this.spendMoneyStandard = spendMoneyStandard;
    }

    public static Parameter of(int spendHourStandard, int spendMoneyStandard){
        return new Parameter(spendHourStandard,spendMoneyStandard);
    }

    public int getSpendHourStandard() {
        return spendHourStandard;
    }

    public void setSpendHourStandard(int spendHourStandard) {
        this.spendHourStandard = spendHourStandard;
    }

    public int getSpendMoneyStandard() {
        return spendMoneyStandard;
    }

    public void setSpendMoneyStandard(int spendMoneyStandard) {
        this.spendMoneyStandard = spendMoneyStandard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parameter)) return false;
        Parameter parameter = (Parameter) o;
        return spendHourStandard == parameter.spendHourStandard && spendMoneyStandard == parameter.spendMoneyStandard;
    }

    @Override
    public int hashCode() {
        return Objects.hash(spendHourStandard, spendMoneyStandard);
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "spendHourStandard=" + spendHourStandard +
                ", spendMoneyStandard=" + spendMoneyStandard +
                '}';
    }
}
