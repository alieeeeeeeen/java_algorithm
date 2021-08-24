package wordnet;

import edu.princeton.cs.algs4.*;

public class WordNet {

    // constructor takes the name of the two input files

    private ST<Integer, String> synsets;
    private ST<String, Bag<Integer>> words;
    private SAP sap;

    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) throw new NullPointerException("Null input.");

        this.synsets = new ST<>();
        this.words = new ST<>();

        In in = new In(synsets);
        int id;
        String line;
        String [] token;

        while(in.hasNextLine()) {
            line = in.readLine();
            token = line.split(",");
            id = Integer.parseInt(token[0]);
            this.synsets.put(id, token[1]);
            for (String word: token[1].split(" ")) {
                if (!words.contains(word)) {
                    Bag<Integer> bag = new Bag<>();
                    bag.add(id);
                    words.put(word, bag);
                } else {
                    words.get(word).add(id);
                }
            }
        }

        Digraph g = new Digraph(this.synsets.size() + 1);

        in = new In(hypernyms);
        while (in.hasNextLine()) {
            line = in.readLine();
            token = line.split(",");
            for (int i = 1; i < token.length; i++) {
                g.addEdge(Integer.parseInt(token[0]), Integer.parseInt(token[i]));
            }
        }

        sap = new SAP(g);

    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return words.keys();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) throw new NullPointerException();
        return words.contains(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        Iterable<Integer> a = words.get(nounA);
        Iterable<Integer> b = words.get(nounB);
        return sap.length(a, b);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        Iterable<Integer> a = words.get(nounA);
        Iterable<Integer> b = words.get(nounB);
        return synsets.get(sap.ancestor(a, b));
    }

    // do unit testing of this class
    public static void main(String[] args) {

    }
}
