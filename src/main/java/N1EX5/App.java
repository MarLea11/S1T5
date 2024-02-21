package N1EX5;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class App implements Serializable {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in); // Creation of Scanner Object
        System.out.println("Write the path route of the directory you want."); // Asking for the directory path route
        String path = input.nextLine(); // Writing of the directory path route

        // Creation of a writer object
        try (FileWriter writer = new FileWriter(path + "\\listDirectoryFiles.txt")) { // Create a FileWriter object
            listFiles(path, writer); // Passing the created object writer to listFiles method
            System.out.println("The list of directory and files has been saved successfully in 'listDirectoryFiles.txt");
        } catch(IOException e) { // Catch possible IOException
            System.err.println("Error occurred, the data save has failed: " + e.getMessage());
        }

        // To read and show txt file
        System.out.println("Enter the path of the txt file you want to show."); // Asking for the txt file route
        String txtPath = input.nextLine(); // Writing the txt file route
        showTxtFile(txtPath); // Calling the method to read the file and passing as parameter the txt file route

        // Serialize object
        serializeObject(path);

        // Deserialize object
        deserializeObject(path);

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

    // Read file method
    public static void showTxtFile(String txtPath) {

        try (BufferedReader reader = new BufferedReader(new FileReader(txtPath))) {
            String line;
            while((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error while reading the file: " + e.getMessage());
        }
    }

    // Object Serialization and deserialization
    public static void serializeObject(String path) {
        try (ObjectOutputStream serializedObject = new ObjectOutputStream(new FileOutputStream (path + "serializedFile.ser"))) {
            String objectSerialized = "My name is Marcos";
            serializedObject.writeObject(objectSerialized);
            System.out.println("Files serialized successfully");
        } catch(IOException e) {
            System.err.println("Error while doing serialization: " + e.getMessage());
        }

    }

    public static void deserializeObject(String path) {
        try (ObjectInputStream deserializedObject = new ObjectInputStream(new FileInputStream(path + "serializedFile.ser"))) {
            String objectDeserialized = (String)deserializedObject.readObject();
            System.out.println("Deserialized file: ");
            {
                System.out.println(objectDeserialized);
            }
        } catch(IOException e) {
            System.err.println("Error while doing deserialization: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Error while doing deserialization: " + e.getMessage());
        }
    }

}
