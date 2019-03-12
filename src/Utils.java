import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {

    public static String readFileAsString(String filepath) {
        StringBuilder output = new StringBuilder();

        try (Scanner scanner = new Scanner(new File(filepath))) {

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                output.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output.toString();
    }

    public static void parse2016PresidentialResults(String input, DataManager data){
        String lines[] = input.split("\n");

        for(int i = 1; i < lines.length; i++){
            String cleanedData = cleanData(lines[i]);
            String items[] = cleanedData.split(",");

            double demVotes = Double.parseDouble(items[1]);
            double gopVotes = Double.parseDouble(items[2]);
            double totalVotes = Double.parseDouble(items[3]);
            Election2016 election = new Election2016(demVotes, gopVotes, totalVotes);

            String state_abbr = items[8];
            String county_name = items[9];
            int fips = Integer.parseInt(items[10]);

            State state = getState(state_abbr, data);
            County county = getCounty(county_name, state);
            county.setVote2016(election);
        }
    }

    public static void parse2016EducationStats(String input, DataManager data){
        String lines[] = input.split("\n");

        for(int i = 5; i < lines.length; i++) {
            String cleanedData = cleanData(lines[i]);
            cleanedData = fixData(cleanedData);
            String items[] = cleanedData.split(",");

            double noHighSchool = Double.parseDouble(items[43]);
            double onlyHighSchool = Double.parseDouble(items[44]);
            double someCollege = Double.parseDouble(items[45]);
            double bachelorsOrMore = Double.parseDouble(items[46]);
            Education2016 education = new Education2016(noHighSchool, onlyHighSchool, someCollege, bachelorsOrMore);

            String state_abbr = items[1];
            String county_name = items[2];
            int fips = Integer.parseInt(items[0]);

            State state = getState(state_abbr, data);
            County county = getCounty(county_name, state);
            county.setFips(fips);
            county.setEduc2016(education);
        }
    }

    public static void parse2016EmploymentStats(String input, DataManager data){
        String lines[] = input.split("\n");

        for(int i = 8; i < lines.length; i++) {
            String cleanedData = cleanData(lines[i]);
            cleanedData = fixData(cleanedData);
            String items[] = cleanedData.split(",");

            int totalLaborForce = Integer.parseInt(items[42]);
            int employedLaborForce = Integer.parseInt(items[43]);
            int unemployedLaborForce = Integer.parseInt(items[44]);
            double unemployedPercent = Double.parseDouble(items[45]);
            Employment2016 employment = new Employment2016(totalLaborForce, employedLaborForce, unemployedLaborForce, unemployedPercent);

            String state_abbr = items[1];
            String county_name = items[2];
            int fips = Integer.parseInt(items[0]);

            State state = getState(state_abbr, data);
            County county = getCounty(county_name, state);
            county.setFips(fips);
            county.setEmploy2016(employment);
        }
    }

    private static String fixData(String cleanedData) {
        String newData = "";
        if(cleanedData.substring(0,1).equals(",")) newData += "0";

        for(int i = 0; i < cleanedData.length()-1; i++){
            String letter1 = cleanedData.substring(i, i+1), letter2 = cleanedData.substring(i+1, i+2);
            if(letter1.equals(",") && letter2.equals(",")){
                newData = newData + letter1 + "0";
            } else {
                newData += letter1;
            }

            if(i == cleanedData.length()-2) newData += letter2;
        }
        if(cleanedData.substring(cleanedData.length()-1, cleanedData.length()).equals(",")) newData += "0";
        return newData;
    }

    private static State getState(String state_abbr, DataManager data) {
        List<State> states = data.getStates();
        for(State currentState : states){
            if(state_abbr.equals(currentState.getName())){
                return currentState;
            }
        }
        State newState = new State(state_abbr, new ArrayList<County>());
        data.getStates().add(newState);
        return newState;
    }

    private static County getCounty(String county_name, State state) {
        county_name = cleanCountyName(county_name);
        List<County> counties = state.getCounties();
        for(County currentCounty : counties){
            if(county_name.equals(currentCounty.getName())){
                return currentCounty;
            }
        }
        County newCounty = new County(county_name);
        state.getCounties().add(newCounty);
        return newCounty;
    }

    private static String cleanCountyName(String county_name) {
        if(county_name.length() < 2) return county_name;
        int n = county_name.length();
        char lastLetter = county_name.charAt(n-1), secondLastLetter = county_name.charAt(n-2);
        if(isUppercase(lastLetter) && isUppercase(secondLastLetter)){
            return county_name.substring(0, n-2);
        }
        return county_name;
    }

    private static boolean isUppercase(char letter) {
        return (letter >= 65 && letter <= 90);
    }

    public static ArrayList<ElectionResult> parse2016ElectionResults(String data) {
        ArrayList<ElectionResult> results = new ArrayList<>();
        String lines[] = data.split("\n");

        for(int i = 1; i < lines.length; i++){
            String cleanedData = cleanData(lines[i]);
            ElectionResult current = getElectionResult(cleanedData);
            results.add(current);
        }
        return results;
    }

    private static String cleanData(String data) {
        String newData = ""; boolean inString = false;
        String validChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.";
        for(int i = 0; i < data.length(); i++){
            String letter = data.substring(i,i+1);
            if(letter.equals(",")){
                if(!inString) newData += letter;
            } else if(letter.equals("\"")){
                inString = !inString;
            } else if(validChars.indexOf(letter) != -1){
                newData += letter;
            }
        }
        return newData;
    }

    private static ElectionResult getElectionResult(String s) {
        String items[] = s.split(",");
        double votes_dem = Double.parseDouble(items[1]);
        double votes_gop = Double.parseDouble(items[2]);
        double total_votes = Double.parseDouble(items[3]);
        double per_dem = Double.parseDouble(items[4]);
        double per_gop = Double.parseDouble(items[5]);
        double diff = Double.parseDouble(items[6]);
        double per_point_diff = Double.parseDouble(items[7]);
        String state_abbr = items[8];
        String county_name = items[9];
        int combined_fips = Integer.parseInt(items[10]);

        ElectionResult result = new ElectionResult(votes_dem, votes_gop, total_votes, per_dem, per_gop, diff, per_point_diff, state_abbr, county_name, combined_fips);
        return result;
    }
}
