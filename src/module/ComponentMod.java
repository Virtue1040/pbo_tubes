package module;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class ComponentMod {
    public static java.awt.Component getComponentByName(Container container, String name) {
        for (java.awt.Component comp : container.getComponents()) {
            if (comp.getName() != null && comp.getName().equals(name)) {
                return comp;
            }
        }
        return null;
    }

    public static String convertDateFormat(String date) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = inputFormat.parse(date);

            SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy");
            return outputFormat.format(parsedDate);
        } catch (ParseException e) {
            return "Invalid date format. Use yyyy-MM-dd.";
        }
    }

    public static String convertTimeToAmPm(String time) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("HH:mm:ss");
            Date date = inputFormat.parse(time);

            SimpleDateFormat outputFormat = new SimpleDateFormat("h:mm a");
            return outputFormat.format(date);

        } catch (ParseException e) {
            return "Format waktu tidak valid. Gunakan format HH:mm:ss.";
        }
    }

    public static String convertTime(String time) {
        try {
            String[] parts = time.split(":");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            String form = "";
            form += String.format("%d Jam ", hours);
            if (minutes > 0) {
                form += String.format("%d Menit", minutes);
            }
            return form;
        } catch (Exception e) {
            return "Format waktu tidak valid. Gunakan format HH:mm:ss.";
        }
    }

    public static ImageIcon getImageFromUrl(String urls) throws IOException {
        URL url = new URL(urls);
        BufferedImage c = ImageIO.read(url);
        ImageIcon image = new ImageIcon(c);
        return image;
    }

    public static ImageIcon getResizedImage(JComponent label, ImageIcon icon) {
        Image img = icon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    public static ImageIcon createRoundedIcon(ImageIcon icon, int cornerRadius) {

        Image image = icon.getImage();
        int width = image.getWidth(null);
        int height = image.getHeight(null);

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Shape roundedRectangle = new RoundRectangle2D.Float(0, 0, width, height, cornerRadius, cornerRadius);

        g2d.setClip(roundedRectangle);
        g2d.drawImage(image, 0, 0, null);

        g2d.dispose();

        return new ImageIcon(bufferedImage);
    }

    public static void savePanelAsPDF(JPanel panel, String filePath) {
        try {
            int panelWidth = panel.getWidth();
            int panelHeight = panel.getHeight();
            BufferedImage image = new BufferedImage(panelWidth, panelHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();
            panel.printAll(g2d);
            g2d.dispose();

            com.itextpdf.text.Rectangle pageSize = new com.itextpdf.text.Rectangle(panelWidth, panelHeight);
            Document document = new Document(pageSize);
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            com.itextpdf.text.Image pdfImage = com.itextpdf.text.Image.getInstance(image, null);
            pdfImage.setAbsolutePosition(0, 0);
            pdfImage.scaleToFit(panelWidth, panelHeight);
            document.add(pdfImage);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ImageIcon behaveCover(JComponent label, ImageIcon icon) {
        Image originalImage = icon.getImage();
        int imgWidth = originalImage.getWidth(null);
        int imgHeight = originalImage.getHeight(null);
        int targetWidth = label.getWidth();
        int targetHeight = label.getHeight();

        double imgAspect = (double) imgWidth / imgHeight;
        double targetAspect = (double) targetWidth / targetHeight;

        int newWidth, newHeight;

        if (imgAspect > targetAspect) {

            newHeight = targetHeight;
            newWidth = (int) (newHeight * imgAspect);
        } else {

            newWidth = targetWidth;
            newHeight = (int) (newWidth / imgAspect);
        }

        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        BufferedImage croppedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = croppedImage.createGraphics();

        int x = (newWidth - targetWidth) / 2;
        int y = (newHeight - targetHeight) / 2;

        g2d.drawImage(scaledImage, -x, -y, null);
        g2d.dispose();

        return new ImageIcon(croppedImage);
    }

    public static ImageIcon getResizedImage(int width, int height, ImageIcon icon) {
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    public static Icon makeIconTransparent(ImageIcon icon, float opacity) {
        BufferedImage img = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g2d.drawImage(icon.getImage(), 0, 0, null);
        g2d.dispose();

        return new ImageIcon(img);
    }
}
