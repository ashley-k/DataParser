public class County {
    private String name;
    private int fips;
    private Election2016 vote2016;
    private Education2016 educ2016;
    private Employment2016 employ2016;
    private int excessiveDrinkingPercent;
    private int avgViolentCrimes;

    public County(String name) {
        this.name = name;
        this.fips = 0;
        this.vote2016 = new Election2016(0,0,0);
        this.educ2016 = new Education2016(0,0,0,0);
        this.employ2016 = new Employment2016(0,0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFips() {
        return fips;
    }

    public void setFips(int fips) {
        this.fips = fips;
    }

    public void setAvgViolentCrimes(int avgViolentCrimes) {
        this.avgViolentCrimes = avgViolentCrimes;
    }

    public void setExcessiveDrinkingPercent(int excessiveDrinkingPercent) {
        this.excessiveDrinkingPercent = excessiveDrinkingPercent;
    }

    public int getAvgViolentCrimes() {
        return avgViolentCrimes;
    }

    public int getExcessiveDrinkingPercent() {
        return excessiveDrinkingPercent;
    }

    public Election2016 getVote2016() {
        return vote2016;
    }

    public void setVote2016(Election2016 vote2016) {
        this.vote2016 = vote2016;
    }

    public Education2016 getEduc2016() {
        return educ2016;
    }

    public void setEduc2016(Education2016 educ2016) {
        this.educ2016 = educ2016;
    }

    public Employment2016 getEmploy2016() {
        return employ2016;
    }

    public void setEmploy2016(Employment2016 employ2016) {
        this.employ2016 = employ2016;
    }
}
