public class Employment2016 {
    private int medianHHIncome;
    private double medianHHIncomePercent;

    public Employment2016(int medianHHIncome, double medianHHIncomePercent) {
        this.medianHHIncome = medianHHIncome;
        this.medianHHIncomePercent = medianHHIncomePercent;
    }

    public int getMedianHHIncome() {
        return medianHHIncome;
    }

    public void setMedianHHIncome(int medianHHIncome) {
        this.medianHHIncome = medianHHIncome;
    }

    public double getMedianHHIncomePercent() {
        return medianHHIncomePercent;
    }

    public void setMedianHHIncomePercent(double medianHHIncomePercent) {
        this.medianHHIncomePercent = medianHHIncomePercent;
    }

    @Override
    public String toString() {
        return "Employment2016{" +
                "medianHHIncome=" + medianHHIncome +
                ", medianHHIncomePercent=" + medianHHIncomePercent +
                '}';
    }
}
