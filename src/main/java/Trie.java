

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Trie {
  public static int ALPHABET_SIZE = 26;
  private String wordTemp;
  private OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream("out.txt"));

  public Trie() throws FileNotFoundException {
  }

  private class Node {
    private char value;
    private HashMap<Character, Node> children = new HashMap<>();
    private boolean isEndOfWord;
    private ArrayList<Integer> indcies = new ArrayList<>();
    public Node(char value) {
      this.value = value;
    }
    @Override
    public String toString() {
      return "value=" + value;
    }

    public boolean hasChild(char ch) {
      return children.containsKey(ch);
    }

    public void addChild(char ch) {
      children.put(ch, new Node(ch));
    }

    public Node getChild(char ch) {
      return children.get(ch);
    }

    public Node[] getChildren() {
      return children.values().toArray(new Node[0]);
    }

    public boolean hasChildren() {
      return !children.isEmpty();
    }

    public void removeChild(char ch) {
      children.remove(ch);
    }
  }

  private Node root = new Node(' ');

  public void insert(String word, ArrayList indexes) {
    var current = root;
    for (var ch : word.toCharArray()) {
      if (!current.hasChild(ch))
        current.addChild(ch);
      current = current.getChild(ch);
    }
    current.isEndOfWord = true;
    current.indcies = indexes;
  }



  public boolean contains(String word) throws IOException {
    if (word == null)
      return false;
    this.wordTemp = word;
    boolean temp = contains(root, word, 0);
    if (!temp)
      output.append(wordTemp + " : not found\n");
    return temp;
  }

  private boolean contains(Node root, String word, int index) throws IOException {
    // Base condition
    if (index == word.length()) {
      if (root.isEndOfWord)
        output.append(wordTemp + ": " + root.indcies + "\n");
      else
        output.append(wordTemp + " : not found");
      output.flush();
      return root.isEndOfWord;
    }

    if (root == null)
      return false;

    var ch = word.charAt(index);
    var child = root.getChild(ch);
    if (child == null)
      return false;

    return contains(child, word, index + 1);
  }

  public void printWords() {
    printWords(root, "");
  }

  private void printWords(Node root, String word) {
    if (root.isEndOfWord)
      System.out.println(word);

    for (var child : root.getChildren())
      printWords(child, word + child.value);
  }

  public OutputStreamWriter getOutput() {
    return output;
  }
}
