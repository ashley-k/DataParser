import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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


    public static ArrayList<ElectionResult> parse2016ElectionResults(String data) {
        ArrayList<ElectionResult> results = new ArrayList<>();
        String cleanData = cleanData(data);
        String lines[] = cleanData.split(",");

        for(String s : lines){
            ElectionResult current = getElectionResult(s);
            results.add(current);
        }
        return results;
    }

    private static String cleanData(String data) {
    }

    private static ElectionResult getElectionResult(String s) {
    }
}
