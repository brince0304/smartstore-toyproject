package Group;

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
}
