	package main;
	
	import java.io.*;
	import java.util.*;
	import implementations.*;
	import utilities.*;
	
	public class WordTracker {
	
	    // Frequency class that tracks word occurrences and related data
	    public static class Frequency implements Comparable<Frequency> {
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
	
	        // Tree to store Frequencies
	        BSTree<Frequency> wordTree = new BSTree<>();
	
	        // Read the input file and process each word
	        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
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
	                            existing = node.getElement(); // Assuming BSTreeNode has a getElement() method
	                        }
	
	                        if (existing == null) {
	                            wordTree.add(occurrence);
	                            existing = occurrence;
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
	
	        // Determine which option to print
	        try (PrintWriter writer = outputFilePath != null ? new PrintWriter(new FileWriter(outputFilePath)) : new PrintWriter(System.out)) {
	            utilities.Iterator<Frequency> it = wordTree.inorderIterator();
	            writer.println("Displaying format " + printOption);
	            
	            while (it.hasNext()) {
	                Frequency word = it.next();
	                Map<String, Set<Integer>> occurrences = word.getOccurrences();
	                
	                writer.println("Word: " + word.getWord());
	                
	                if (printOption.equals("-pf")) {
	                    // Print just the files
	                    writer.println("found in file: " + occurrences.keySet());
	                } 
	                else if (printOption.equals("-pl")) {
	                    // Print files with line numbers
	                    for (Map.Entry<String, Set<Integer>> entry : occurrences.entrySet()) {
	                        writer.println("found in file: " + entry.getKey() + " on lines: " + entry.getValue());
	                    }
	                } 
	                else if (printOption.equals("-po")) {
	                    // Print files, lines, and frequency
	                    writer.println("Total occurrences: " + word.getFrequency());
	                    for (Map.Entry<String, Set<Integer>> entry : occurrences.entrySet()) {
	                        writer.println("found in file: " + entry.getKey() + " on lines: " + entry.getValue());
	                    }
	                }
	                
	                writer.println(); // Add blank line between entries
	            }
	        } catch (IOException e) {
	            System.out.println("Error writing to file: " + e.getMessage());
	        }
	    }
	}
