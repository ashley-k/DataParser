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

        Utils.parse2016EducationStats(educationData, data);
        Utils.parse2016EmploymentStats(unemploymentData, data);

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
