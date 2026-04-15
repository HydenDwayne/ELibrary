package view.fonts;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.InputStream;

import view.front_pages.FilePath;

public class Fonts {

    private Font appliedFont;

    public Fonts(String fontFamily, float size) {
        switch (fontFamily) {
            case "Poppins":
                appliedFont = loadFont("Poppins-Regular.ttf", size);
                break;

            case "PoppinsBold":
                appliedFont = loadFont("Poppins-Bold.ttf", size);
                break;

            case "IntroRust":
                appliedFont = loadFont("IntroRust.otf", size);
                break;

            case "ABeeZee":
                appliedFont = loadFont("ABeeZee-Regular.ttf", size);
                break;

            default:
                appliedFont = new Font("SansSerif", Font.PLAIN, (int) size);
                break;
        }
    }

    public Font getAppliedFont() {
        return appliedFont;
    }

    

    private static Font loadFont(String fileName, float size) {

        try (InputStream is = FilePath.font(fileName).openStream()) {

            return Font.createFont(Font.TRUETYPE_FONT, is)
                       .deriveFont(size);

        } catch (Exception e) {
            System.err.println("⚠ Failed to load font: " + fileName);
            return new Font("SansSerif", Font.PLAIN, (int) size);
        }
    }
}