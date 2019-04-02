public class Death2016 {
    private int population;
    private double numDeaths;
    private double ageAdjustedDeathRate;

    public Death2016(int population, double numDeaths, double ageAdjustedDeathRate) {
        this.population = population;
        this.numDeaths = numDeaths;
        this.ageAdjustedDeathRate = ageAdjustedDeathRate;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getNumDeaths() {
        return numDeaths;
    }

    public void setNumDeaths(double numDeaths) {
        this.numDeaths = numDeaths;
    }

    public double getAgeAdjustedDeathRate() {
        return ageAdjustedDeathRate;
    }

    public void setAgeAdjustedDeathRate(double ageAdjustedDeathRate) {
        this.ageAdjustedDeathRate = ageAdjustedDeathRate;
    }

    @Override
    public String toString() {
        return "Death2016{" +
                "population=" + population +
                ", numDeaths=" + numDeaths +
                ", ageAdjustedDeathRate=" + ageAdjustedDeathRate +
                '}';
    }
}

