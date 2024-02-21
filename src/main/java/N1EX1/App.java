package N1EX1;

import java.io.File;
import java.util.Scanner;

public class App {

    private App(){}

    public static void runProgram() {

        Scanner input = new Scanner(System.in); // Creation of Scanner Object
        System.out.println("Write the path route"); // Asking for the path route
        String path = input.nextLine(); // Writing of the path route
        System.out.println("Write the extension"); // Asking for the extension of the files
        String extension = input.nextLine(); // Writing the extension of the files
        File folder = new File(path); // Creation of a folder directory with the path route variable created before

        File[] files;
        if(folder.exists()) { // Condition that checks if the file/directory of the path introduced exists
            if(folder.isDirectory()) { // Condition that checks if the file of the path introduced is a directory
                files = folder.listFiles(); // Returns an array of the files in the directory
                for (File file : files) // For each Loop to iterate the array of files previously returned
                    if (file.getName().endsWith(extension)) // Gets the name of the file and checks the extension
                        System.out.println(file.getName()); // Prints the specified file name
            }
        }
    }

}
