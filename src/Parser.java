import java.io.*;
import java.util.Scanner;
import java.util.Iterator;
import java.util.ArrayList;
import java.io.FileWriter;

/***********************************************************************************************************************
 * @file: Parser.java
 * @description: This program implements a parser capable of reading and processing input commands
 * @author: Olivia Sturges
 * @date: September 25, 2024
 * Note on changing the data: Took out a comma from the titles in rows 69 and 111.
 **********************************************************************************************************************/

public class Parser {

    // Create a BST tree of your class type (Note: Replace "Object" with your class type)
    private BST<PARKSANDRECData> mybst = new BST<>();

    public Parser(String filename) throws FileNotFoundException {
        process(new File(filename));
    }

    // Implement the process method
    // Remove redundant spaces for each input command
    public void process(File input) throws FileNotFoundException {
        // opens the input file
        FileInputStream inputFileNameStream = new FileInputStream(input);
        Scanner inputFileNameScanner = new Scanner(inputFileNameStream);

        // Creating an ArrayList to store values
        ArrayList<String> storage = new ArrayList<>();

        // Reading each line and skipping empty lines
        while (inputFileNameScanner.hasNextLine()) {
            String line = inputFileNameScanner.nextLine();
            while (line.isEmpty()) {
                line = inputFileNameScanner.nextLine();
            }
            String[] parts = line.split(" "); // splitting each line by " "
            // Only storing non-empty parts
            for (int i = 0; i < parts.length; i++) {
                if (!parts[i].isEmpty()) {
                    storage.add(parts[i]);
                }
            }

            //call operate_BST method;
            // Also puts storage back into String[], this time without redundant blanks
            if (storage.size() == 1) {
                String[] parts2 = {storage.get(0)};
                operate_BST(parts2);
            }
            else {
                String[] parts2 = {storage.get(0), storage.get(1)};
                operate_BST(parts2);
            }

            // Empties storage so process can start all over again
            for (int i = 0; i < parts.length; i++) {
                storage.remove(parts[i]);
            }
        }
        inputFileNameScanner.close(); // closes file
    }

    // Implement the operate_BST method
    // Determine the incoming command and operate on the BST
    public void operate_BST(String[] command) throws FileNotFoundException {
        // Hard coding reading in the data file because I do not know how to add a second command line argument
        FileInputStream fileDataStream = new FileInputStream("src/parks_and_rec_episodes.csv");
        Scanner fileDataScanner = new Scanner(fileDataStream);

        // ignore first line
        fileDataScanner.nextLine();

        switch (command[0]) {
            // add your cases here
            // call writeToFile
            case "insert": // inserts epiosde based off of overall episode number
                // flag for finding the overall episode number in the data (false if ep num not found, true otherwise)
                boolean found = false;
                // read the file line by line
                while (fileDataScanner.hasNext() & !found) { // each overall episode number only appears once in data so while loop stops when episode number is found
                    String line = fileDataScanner.nextLine();
                    String[] parts = line.split(","); // split the string into multiple parts

                    // check if overall episode number matches
                    if (parts[2].equals(command[1])) {
                        // Create a new PARKS&RECDATA object
                        // season, episode_num_in_season, episode_num_overall, title, directed_by, written_by, original_air_date, us_viewers
                        PARKSANDRECData data = new PARKSANDRECData(
                                Integer.parseInt(parts[0]), // Season
                                Integer.parseInt(parts[1]), // Episode number in season
                                Integer.parseInt(parts[2]), // Overall episode number
                                parts[3], // Episode
                                parts[4], // Directed By
                                parts[5], // Written By
                                parts[6], // Original Air Date
                                Double.parseDouble(parts[7]) // Number of US viewers
                        );
                        found = true;
                        mybst.insert(data); // insert object in tree
                        writeToFile("insert " + data, "./result.txt"); // call writeToFile
                    }
                }
                if (!found){ // Only 125 episodes so if the input is <= 0 or >= 126 the overall episode number wouldn't be found
                    writeToFile("Insert failed because overall episode number does not exist.", "./result.txt");
                }
                break;

            case "search": // searches for epiosde based off of overall episode number
                // flag for finding the overall episode number in the data (false if ep num not found, true otherwise)
                found = false;
                // read the file line by line
                while (fileDataScanner.hasNext() & !found) { // each overall episode number only appears once in data so while loop stops when episode number is found
                    String line = fileDataScanner.nextLine();
                    String[] parts = line.split(","); // split the string into multiple parts

                    // check if overall episode number matches
                    if (parts[2].equals(command[1])) {
                        // Create a new PARKS&RECDATA object to hold the temp data
                        // season, episode_num_in_season, episode_num_overall, title, directed_by, written_by, original_air_date, us_viewers
                        PARKSANDRECData temp = new PARKSANDRECData(
                                Integer.parseInt(parts[0]), // Season
                                Integer.parseInt(parts[1]), // Episode number in season
                                Integer.parseInt(parts[2]), // Overall episode number
                                parts[3], // Episode
                                parts[4], // Directed By
                                parts[5], // Written By
                                parts[6], // Original Air Date
                                Double.parseDouble(parts[7]) // Number of US viewers
                        );
                        found = true;
                        if (mybst.search(temp) == null) {
                            writeToFile("search failed", "./result.txt");
                        }
                        else {
                            writeToFile("found " + temp, "./result.txt");
                        }
                    }
                }
                if (!found) {
                    writeToFile("search failed", "./result.txt");
                }
                break;

            case "remove": // removes episode based off of overall episode number
                // flag for finding the overall episode number in the data (false if ep num not found, true otherwise)
                found = false;
                // read the file line by line
                while (fileDataScanner.hasNext() & !found) { // each overall episode number only appears once in data so while loop stops when episode number is found
                    String line = fileDataScanner.nextLine();
                    String[] parts = line.split(","); // split the string into multiple parts

                    // check if overall episode number matches
                    if (parts[2].equals(command[1])) {
                        // Create a new PARKS&RECDATA object to hold the temp data
                        // season, episode_num_in_season, episode_num_overall, title, directed_by, written_by, original_air_date, us_viewers
                        PARKSANDRECData temp = new PARKSANDRECData(
                                Integer.parseInt(parts[0]), // Season
                                Integer.parseInt(parts[1]), // Episode number in season
                                Integer.parseInt(parts[2]), // Overall episode number
                                parts[3], // Episode
                                parts[4], // Directed By
                                parts[5], // Written By
                                parts[6], // Original Air Date
                                Double.parseDouble(parts[7]) // Number of US viewers
                        );
                        found = true;
                        if (mybst.remove(temp) != null) {
                            mybst.remove(temp);
                            writeToFile("removed " + temp, "./result.txt");
                        } else {
                            writeToFile("remove failed", "./result.txt");
                        }
                    }
                }
                if (!found) {
                    writeToFile("remove failed", "./result.txt");
                }
                break;

            case "print": // prints episodes in ascending order by number of US viewers. 1 episode per line, blank line at the end
                Iterator<PARKSANDRECData> it = mybst.iterator();
                String s = "";
                while (it.hasNext()) {
                    s += it.next() + "\n"; // appends to new line or else 1 line would be super long
                }
                writeToFile(s, "./result.txt");
                break;

            // default case for Invalid Command
            default: writeToFile("Invalid Command", "./result.txt");
        }
        fileDataScanner.close(); // closes file
    }

    // Implement the writeToFile method
    // Generate the result file
    public void writeToFile(String content, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))){
            writer.write(content);
            writer.newLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
