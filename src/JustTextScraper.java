package src;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class JustTextScraper {

    public static void main(String[] args) {
        // Create a Scanner object to read input from the terminal
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter a URL
        System.out.println("Enter the URL of the webpage you want to fetch:");
        String url = scanner.nextLine(); // Read the URL entered by the user

        // Specify the output file path
        String outputFilePath = "just_text.txt"; // Changed to .txt for simplicity

        try {
            // Fetch the HTML content from the specified URL
            Document doc = Jsoup.connect(url).get();

            // Extract the body section of the HTML document
            Element body = doc.body();

            // Extract all text and images from the body
            StringBuilder content = new StringBuilder();
            // Extract text
            content.append(body.text()).append("\n\n");

            // Extract image URLs
            Elements images = body.select("img");
            for (Element img : images) {
                String src = img.absUrl("src"); // Get absolute URL for images
                if (!src.isEmpty()) {
                    content.append("Image URL: ").append(src).append("\n");
                }
            }

            // Write the extracted content to a file
            try (FileWriter fileWriter = new FileWriter(outputFilePath)) {
                fileWriter.write(content.toString());
                System.out.println("Text and image URLs successfully written to " + outputFilePath);
            } catch (IOException e) {
                System.err.println("An error occurred while writing to the file: " + e.getMessage());
            }

        } catch (IOException e) {
            System.err.println("An error occurred while fetching the HTML content: " + e.getMessage());
        }
    }
}
