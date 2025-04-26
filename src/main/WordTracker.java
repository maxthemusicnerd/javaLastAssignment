package main;

import java.io.*;
import java.util.*;
import implementations.*;
import utilities.*;

public class WordTracker {
    private static final String REPOSITORY_FILE = "repository.ser";

    // Frequency class that tracks word occurrences and related data
    public static class Frequency implements Comparable<Frequency>, Serializable {
        private static final long serialVersionUID = 1L;  // Required for serialization
        private final String word;
        private final Map<String, Set<Integer>> occurrences;

        public Frequency(String word) {
            this.word = word.toLowerCase();
            this.occurrences = new HashMap<>();
        }

        public void addOccurrence(String fileName, int lineNumber) {
            occurrences.putIfAbsent(fileName, new TreeSet<>());
            occurrences.get(fileName).add(lineNumber);
        }

        public String getWord() {
            return word;
        }

        public Map<String, Set<Integer>> getOccurrences() {
            return occurrences;
        }

        public int getFrequency() {
            return occurrences.values().stream().mapToInt(Set::size).sum();
        }

        @Override
        public int compareTo(Frequency other) {
            return this.word.compareTo(other.word);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Frequency)) {
                return false;
            }
            Frequency other = (Frequency) obj;
            return this.word.equals(other.word);
        }

        @Override
        public int hashCode() {
            return Objects.hash(word);
        }

        @Override
        public String toString() {
            return word + " -> " + occurrences.toString();
        }
    }

    // Load the word tree from serialized file if it exists
    @SuppressWarnings("unchecked")
    private static BSTree<Frequency> loadRepository() {
        File file = new File(REPOSITORY_FILE);
        if (!file.exists()) {
            return new BSTree<>();
        }
        
        // Delete the old incompatible file
        file.delete();
        return new BSTree<>();
    }

    // Save the word tree to serialized file
    private static void saveRepository(BSTree<Frequency> wordTree) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(REPOSITORY_FILE))) {
            oos.writeObject(wordTree);
        } catch (IOException e) {
            System.err.println("Error saving repository: " + e.getMessage());
        }
    }

    // The main method to process text files and track word occurrences
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java -jar WordTracker.jar <input.txt> [-pf/-pl/-po] [-f<output.txt>]");
            return;
        }

        String inputFilePath = args[0];
        String outputFilePath = null;
        String printOption = "-pf";  // Default option is -pf if not provided

        // Parse additional arguments
        for (int i = 1; i < args.length; i++) {
            if (args[i].startsWith("-f")) {
                outputFilePath = args[i].substring(2);
            } else if (args[i].equals("-pl") || args[i].equals("-pf") || args[i].equals("-po")) {
                printOption = args[i];
            }
        }

        // Load existing repository or create new tree
        BSTree<Frequency> wordTree = loadRepository();

        // Read the input file and process each word
        File inputFile = new File("res/" + inputFilePath);  // Relative to project root
        if (!inputFile.exists()) {
            inputFile = new File(inputFilePath);  // Try absolute path
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\W+");  // Split by non-word characters
                for (String word : words) {
                    if (!word.isEmpty()) {
                        Frequency occurrence = new Frequency(word);
                        // Check if the word already exists in the tree
                        BSTreeNode<Frequency> node = wordTree.search(occurrence);
                        Frequency existing;
                        if (node == null) {
                            wordTree.add(occurrence);
                            existing = occurrence;
                        } else {
                            existing = node.getElement();
                        }
                        existing.addOccurrence(inputFilePath, lineNumber);
                    }
                }
                lineNumber++;
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        // Generate report
        try (PrintWriter writer = outputFilePath != null ? 
                new PrintWriter(new FileWriter(outputFilePath)) : new PrintWriter(System.out)) {
            
            writer.println("Displaying format " + printOption);
            writer.println("-----------------------------");
            
            utilities.Iterator<Frequency> it = wordTree.inorderIterator();
            while (it.hasNext()) {
                Frequency word = it.next();
                Map<String, Set<Integer>> occurrences = word.getOccurrences();
                
                writer.println("Word: " + word.getWord());
                
                if (printOption.equals("-pf")) {
                    // Print just the files
                    writer.println("Found in files: " + String.join(", ", occurrences.keySet()));
                } 
                else if (printOption.equals("-pl")) {
                    // Print files with line numbers
                    for (Map.Entry<String, Set<Integer>> entry : occurrences.entrySet()) {
                        writer.println("File: " + entry.getKey() + 
                                      ", Lines: " + entry.getValue().toString()
                                          .replace("[", "").replace("]", ""));
                    }
                } 
                else if (printOption.equals("-po")) {
                    // Print files, lines, and frequency
                    writer.println("Total occurrences: " + word.getFrequency());
                    for (Map.Entry<String, Set<Integer>> entry : occurrences.entrySet()) {
                        writer.println("File: " + entry.getKey() + 
                                      ", Lines: " + entry.getValue().toString()
                                          .replace("[", "").replace("]", "") + 
                                      ", Count: " + entry.getValue().size());
                    }
                }
                
                writer.println(); // Add blank line between entries
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        // Save the updated repository
        saveRepository(wordTree);
    }
}