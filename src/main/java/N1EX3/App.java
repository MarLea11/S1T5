package N1EX3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class App {

    private App(){}

    public static void runProgram() {

        Scanner input = new Scanner(System.in); // Creation of Scanner Object
        System.out.println("Write the path route"); // Asking for the path route
        String path = input.nextLine(); // Writing of the path route

        /* Creation of a file to write for more clarity
        try {
            File file = new File(path + "\\listDirectoryFiles.txt");
            if (file.createNewFile()) {
                System.out.println("The file " + file.getName() + " was created successfully.");
            } else {
                System.out.println("The file with the name " + file.getName() + " already exists.");
            }
        }catch (IOException e) {
                System.err.println("An error occurred.");
                e.printStackTrace();
        }*/

        // Creation of a writer object to write in the previously created file
        try (FileWriter writer = new FileWriter(path + "\\listDirectoryFiles.txt")) { // Create a FileWriter object
        listFiles(path, writer); // Passing the created object writer to listFiles method
        System.out.println("The list of directory and files has been saved successfully in 'listDirectoryFiles.txt");
        } catch(IOException e) { // Catch possible IOException
            System.err.println("Error occurred, the data save has failed: " + e.getMessage());
        }

    }

    public static void listFiles(String path, FileWriter writer) throws IOException {
        File folder = new File(path);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Better read date format
        String formattedDate; // String variable to use later for the modified date
        if (folder.exists()) { // Condition that checks if the file/directory of the path introduced exists
            File[] files = folder.listFiles(); // Returns an array of the files in the directory

            if (files == null) { // checks if there is no files in the directory
                System.out.println("There is no files in the directory that you have selected");
            } else {
                for (File file : files) {

                        listFiles(file.getAbsolutePath(), writer); // Recursive call adding writer to save each time
                        formattedDate = dateFormat.format(file.lastModified()); // Assign value to formatted date

                        writer.write(file.getName() + (file.isDirectory() ? " [D] " : " [F]")
                                        + " Last Modified: " + formattedDate + "\n");
                        // Gets the file name and then uses conditional expression to add a
                        // " [D]" if it is a directory or [F] if it is a file

                    }
                }
        } else {
            writer.write("The directory or path directory does not exist"); // If the directory does not exist
        }
    }
}
