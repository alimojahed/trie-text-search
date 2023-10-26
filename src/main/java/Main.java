import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        String test = "From fairest creatures we desire increase,\n" +
                "That thereby beauty's rose might never die,\n" +
                "But as the riper should by time decease,\n" +
                "His tender heir might bear his memory:\n" +
                "But thou contracted to thine own bright eyes,\n" +
                "Feed'st thy light's flame with self-substantial fuel,\n" +
                "Making a famine where abundance lies,\n" +
                "Thy self thy foe, to thy sweet self too cruel:\n" +
                "Thou that art now the world's fresh ornament,\n" +
                "And only herald to the gaudy spring,\n" +
                "Within thine own bud buriest thy content,\n" +
                "And tender churl mak'st waste in niggarding:\n" +
                "Pity the world, or else this glutton be,\n" +
                "To eat the world's due, by the grave and thee.";
        String[] words = test.split(" ");
        Map<String, ArrayList<Integer>> map = new HashMap<>();
        for (int i =0; i<words.length;i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            int index = test.indexOf(words[i]);

            while (index >= 0) {
                temp.add(index);
                index = test.indexOf(words[i], index + 1);
            }
            map.put(words[i], temp);
        }
        Trie trie = new Trie();
        for (String s:map.keySet())
            trie.insert(s, map.get(s));
        trie.contains("we");

        trie.getOutput().close();
    }
}
