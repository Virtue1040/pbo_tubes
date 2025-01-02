package module;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class ImageReader {
    public Image imageRead(String urls) throws IOException {
        try {

            String imageUrl = urls; // Replace with your image URL
            URL url = new URL(imageUrl);

            Image image = ImageIO.read(url);
            return image;
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
