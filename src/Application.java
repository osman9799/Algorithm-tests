import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Application {
    private final static String FILE_PATH = "src/resources/input.txt";

    public static ArrayList<Character> tempList = new ArrayList<Character>();

    public static ArrayList<Character> openingPatterns = new ArrayList<Character>(Arrays.asList('{', '(', '[', '<'));
    public static ArrayList<Character> closingPatterns = new ArrayList<Character>(Arrays.asList('}', ')', ']', '>'));

    private static Map<Character, Character> OPEN_MAPPED_CLOSED;

    private static HashMap<Character, Integer> calculations = new HashMap<>();

    static {
        OPEN_MAPPED_CLOSED = new HashMap<>();
        OPEN_MAPPED_CLOSED.put('}', '{');
        OPEN_MAPPED_CLOSED.put(')', '(');
        OPEN_MAPPED_CLOSED.put(']', '[');
        OPEN_MAPPED_CLOSED.put('>', '<');

        calculations.put('}', 0);
        calculations.put(')', 0);
        calculations.put(']', 0);
        calculations.put('>', 0);
    }

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            ArrayList<Character> lineList;

            //Loop through each line
            while ((line = br.readLine()) != null) {
                lineList = getList(line);
                for(int i = 0; i < lineList.size()-1; i++) {
                    if (openingPatterns.contains(lineList.get(i))) {
                        tempList.add(lineList.get(i));
                    } else if (closingPatterns.contains(lineList.get(i))) {
                        if (!checkValidity(lineList.get(i), tempList.get(tempList.size()-1))) {
                            break;
                        }
                        // If the bracket closes validly then remove it from the list
                        if (!tempList.isEmpty()) {
                            tempList.remove(tempList.size()-1);
                        }
                    }

                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        printTotalErrorSyntaxScore();
    }

    private static void printTotalErrorSyntaxScore() {
        int curly = calculations.get('}')*1197;
        int parenthesis = calculations.get(')')*3;
        int square = calculations.get(']')*57;
        int xml = calculations.get('>')*25137;

        int total = curly+parenthesis+square+xml;

        System.out.println(total);
    }

    private static boolean checkValidity(Character closing, Character opening) {
        System.out.println(closing);
        System.out.println(opening);
        if (!OPEN_MAPPED_CLOSED.get(closing).equals(opening)) {
            calculations.put(closing, calculations.get(closing) + 1);
            return false;
        }
        return true;
    }

    public static ArrayList<Character> getList(String str) {
        ArrayList<Character> chars = new ArrayList<Character>();
        for (char c : str.toCharArray()) {
            chars.add(c);
        }
        return chars;
    }

}
