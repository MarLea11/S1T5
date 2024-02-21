package N1EX2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class App {

    private App(){}

    public static void runProgram() {

        Scanner input = new Scanner(System.in); // Creation of Scanner Object
        System.out.println("Write the path route"); // Asking for the path route
        String path = input.nextLine(); // Writing of the path route

        listFiles(path); // Calling the method and sending path route as parameter

    }

    public static void listFiles(String path) {
        File folder = new File(path);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Better read date format
        String formattedDate; // String variable to use later for the modified date
        if (folder.exists()) { // Condition that checks if the file/directory of the path introduced exists
            File[] files = folder.listFiles(); // Returns an array of the files in the directory

            if (files == null) { // checks if there is no files in the directory
                System.out.println("There is no files in the directory that you have selected");
            } else {
                for (File file : files) {
                    if (file.isDirectory()) { // Check if it is a directory

                        listFiles(file.getAbsolutePath()); // Recursive call
                        formattedDate = dateFormat.format(file.lastModified()); // Assign value to formatted date
                        System.out.println(file.getName() + " [D] " + "Last Modified: " + formattedDate);

                    } else if (file.isFile()) { // Check if it is a file

                        formattedDate = dateFormat.format(file.lastModified()); // Assign value to formatted date
                        System.out.println(file + " [F] " + "Last Modified: " + formattedDate);

                    }
                }
            }
        } else {
            System.err.println("The directory or path directory does not exist"); // If the directory does not exist
        }
    }
}

