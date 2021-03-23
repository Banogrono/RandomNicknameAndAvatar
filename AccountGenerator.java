import java.io.*;
import java.util.*;

public class AccountGenerator {

    private static final String  AdjectivesWordList = "Adjectives.txt";  // put here adjective list file path
    private static final String NounsWordList = "Nouns.txt";             // put here noun list file path
    private static final String ColorsWordList = "Colors.txt";           // put here color list file path
    private static final File directory = new File("/Avatars");          // put here avatar directory path


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Nick: " + nameGenerator() + " | Avatar: " + avatarChooser());
            System.out.println("Generate new? y/n ");
            var answ = scanner.next();

            if (!answ.equals("y")) {
                return;
            }
        }
    }

    /**
     * Reads list of words in the provided file, and randomly chooses one of the words.
     * @param fileName file with word list, each word in new line, no spaces.
     * @return Randomly chosen word in form of string or null if word list could not be loaded.
     */
    static String getWord(String fileName) {
        ArrayList<String> words = new ArrayList<>();

        // Open file and read all words into array
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null)
            {
                words.add(line);
            }
            reader.close();
        }
        catch (Exception ignored) {
            System.out.println("E: Could not open file.");
            return null;
        }

        // randomly select word form the word list
        Random random = new Random();
        var randomIndex = random.nextInt(words.size());

        return words.get(randomIndex);
    }

    /**
     * Glues together 3 words and returns 'em.
     * @return Nickname glued from 3 words.
     */
    static String nameGenerator() {
        String adjective = getWord(AdjectivesWordList);
        String noun = getWord(NounsWordList);
        String color = getWord(ColorsWordList);

        if (adjective == null || noun == null || color == null) {
            System.out.println("E: Word cannot be null.");
        }

        // Removing spaces  and turning first letter in each word to Upper Case
        assert color != null;
        color.trim();
        var tempCharArray = color.toCharArray();
        tempCharArray[0] = Character.toUpperCase(tempCharArray[0]);
        color = new String(tempCharArray);

        assert adjective != null;
        adjective.trim();
        tempCharArray = adjective.toCharArray();
        tempCharArray[0] = Character.toUpperCase(tempCharArray[0]);
        adjective = new String(tempCharArray);

        assert noun != null;
        noun.trim();
         tempCharArray = noun.toCharArray();
        tempCharArray[0] = Character.toUpperCase(tempCharArray[0]);
        noun = new String(tempCharArray);

        return color + adjective + noun;
    }

    /**
     * Selects random file from directory and returns its name.
     * @return Random file name.
     */
    static String avatarChooser() {
        File[] files = directory.listFiles();
        assert files != null;

        if (files.length == 0) {
            System.out.println("Avatar directory is empty.");
            return "--";
        }

        Random rand = new Random();
        File file = files[rand.nextInt(files.length)];
        return file.getName();
    }
}
