import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/***
 * Main class for data parser
 * @author Ashley Ke
 */

public class Main {
    public static void main(String[] args) {
        //Test of utils ashleyk
        DataManager data = new DataManager(new ArrayList<State>());

        String educationData = Utils.readFileAsString("data/Education.csv");
        String unemploymentData = Utils.readFileAsString("data/Unemployment.csv");
        String drinkingData = Utils.readFileAsString("data/Excessive drinking - Sheet1.csv");
        String violenceData = Utils.readFileAsString("data/Average Violent Crimes - Sheet1.csv");

        Utils.parse2016EducationStats(educationData, data);
        Utils.parse2016EmploymentStats(unemploymentData, data);
        Utils.parseAverageViolentCrimeStats(violenceData, data);
        Utils.parseExcessiveDrinking(drinkingData, data);

        String result = dataToString(data);

        writeDataToFile("data/Results.csv",result);
    }

    private static void writeDataToFile(String filePath, String data) {
        File outFile = new File(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
            writer.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String dataToString(DataManager data){
        String result = "";
        State california = data.getStates().get(0);
        for(County county : california.getCounties()){
            Employment2016 employment = county.getEmploy2016();
            Education2016 education = county.getEduc2016();
            int excessiveDrinkingPercent = county.getExcessiveDrinkingPercent();
            double avgViolentCrime = county.getAvgViolentCrimes();

            String countyData = "CA," + county.getName();
            countyData = countyData + "," + employment.getMedianHHIncome() + "," +  employment.getMedianHHIncomePercent();
            countyData = countyData + "," + education.getNoHighSchool() + "," + education.getOnlyHighSchool() + "," + education.getSomeCollege() + "," + education.getBachelorsOrMore();
            countyData = countyData + "," + excessiveDrinkingPercent;
            countyData = countyData + "," + avgViolentCrime;

            result = result + countyData + "\n";
        }
        return result;
    }

    public static void printData(DataManager data){
        for(State s : data.getStates()){
            System.out.println("STATE: " + s.getName());
            for(County c : s.getCounties()){
                System.out.println("\tCOUNTY: " + c.getName());

                //test education
                Education2016 education = c.getEduc2016();
                System.out.println("\t\t" + education.toString());

                //test employment
                Employment2016 employment = c.getEmploy2016();
                System.out.println("\t\t" + employment.toString());
            }
        }
    }
}
