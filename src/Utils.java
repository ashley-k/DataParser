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
            County county = getCounty(county_name, fips, state);
            county.setVote2016(election);
        }
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

    private static County getCounty(String county_name, int fips, State state) {
        List<County> counties = state.getCounties();
        for(County currentCounty : counties){
            if(county_name.equals(currentCounty.getName())){
                return currentCounty;
            }
        }
        County newCounty = new County(county_name, fips);
        state.getCounties().add(newCounty);
        return newCounty;
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
