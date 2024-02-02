package src;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.*;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class EventImageScraper {

    public static void main(String[] args) {
        try {
            // URL of the webpage to scrape
            String webPageUrl = "https://skidmore.campuslabs.com/engage/";
            System.out.println("Connecting...");

            // Connect to the webpage
            Document doc = Jsoup.connect(webPageUrl).get();
            System.out.println("Connected and fetched the webpage!");

            int imageCount = 0; // To count the number of images downloaded

            // Loop through the first five image elements
            for (Element imgElement : doc.select("img")) {
                if (imageCount >= 5)
                    break; // Break the loop after downloading five images

                String imgUrl = imgElement.absUrl("src"); // Get absolute URL of the image
                if (imgUrl.isEmpty()
                        || (!imgUrl.endsWith(".jpg") && !imgUrl.endsWith(".jpeg") && !imgUrl.endsWith(".png")))
                    continue; // Skip if src is empty or not jpg/png

                // Download and save the image
                try {
                    URL url = new URL(imgUrl);
                    BufferedImage img = ImageIO.read(url);
                    String fileExtension = imgUrl.substring(imgUrl.lastIndexOf('.') + 1);
                    String fileName = "downloaded_image_" + imageCount + "." + fileExtension; // Unique filename for
                                                                                              // each image

                    ImageIO.write(img, fileExtension, new File(fileName));
                    System.out.println("Image successfully downloaded: " + fileName);
                    imageCount++; // Increment the counter after successful download
                } catch (IOException e) {
                    System.out.println("Error downloading image: " + imgUrl);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("Error connecting to the webpage.");
            e.printStackTrace();
        }
    }
}
