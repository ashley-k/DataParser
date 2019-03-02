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
        String lines[] = data.split("\n");

        for(String s : lines){
            String cleanedData = cleanData(s);
            ElectionResult current = getElectionResult(cleanedData);
            results.add(current);
        }
        return results;
    }

    private static String cleanData(String data) {
        String newData = "";
        boolean inString = false;
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

    }
}
