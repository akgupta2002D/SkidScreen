package src;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HTMLparser {

    public static void main(String[] args) {
        // Create a Scanner object to read input from the terminal
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter a URL
        System.out.println("Enter the URL of the webpage you want to fetch:");
        String url = scanner.nextLine(); // Read the URL entered by the user

        // Specify the output file path
        String outputFilePath = "output.html"; // You can also prompt for this or make it dynamic

        try {
            // Fetch the HTML content from the specified URL
            Document doc = Jsoup.connect(url).get();

            // Get the entire HTML content as a string
            String htmlContent = doc.toString();

            // Write the HTML content to a file
            try (FileWriter fileWriter = new FileWriter(outputFilePath)) {
                fileWriter.write(htmlContent);
                System.out.println("HTML content successfully written to " + outputFilePath);
            } catch (IOException e) {
                System.err.println("An error occurred while writing to the file: " + e.getMessage());
            }

        } catch (IOException e) {
            System.err.println("An error occurred while fetching the HTML content: " + e.getMessage());
        }
    }
}
