package view.fonts;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import view.FilePath;

public class Fonts {

    private Font appliedFont;
    
    static String fontFilePath = FilePath.getFontFilePath();

    public Fonts(String font_family, float size) {

        switch (font_family) {
            case "Poppins":
                appliedFont = setPoppinsFont(size);
                checkFontFiles();
                break;
            case "PoppinsBold":
                appliedFont = setPoppinsBoldFont(size);
                checkFontFiles();
                break;
            case "IntroRust":
                appliedFont = setIntroRustFont(size);
                checkFontFiles();
                break;
            case "ABeeZee":
                appliedFont = setABeeZeeFont(size);
                checkFontFiles();
                break;
            default:
                throw new AssertionError();
        }

    }

    public void checkFontFiles() {
        if (appliedFont == null) {
            System.out.println("Error. File not found.");
        }
    }

    public Font getAppliedFont() {
        return appliedFont;
    }

    public static Font setPoppinsFont(float size) {
        
        try {
            Font poppins;
            poppins = Font.createFont(Font.TRUETYPE_FONT, new File(fontFilePath + "Poppins-Regular.ttf")).deriveFont(size);
            return poppins;
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            System.out.println("Error loading Poppins font. Font set to fallback: Sans Serif");
        }
        return null;
    }

    public static Font setPoppinsBoldFont(float size) {
        
        try {
            Font poppins;
            poppins = Font.createFont(Font.TRUETYPE_FONT, new File(fontFilePath + "Poppins-Bold.ttf")).deriveFont(size);
            return poppins;
        } catch (FontFormatException | IOException e) {
            System.out.println("Error loading PoppinsBold font. Font set to fallback: Sans Serif");
        }
        return null;
    }

    public static Font setIntroRustFont(float size) {
        
        try {
            Font introRust;
            introRust = Font.createFont(Font.TRUETYPE_FONT, new File(fontFilePath + "IntroRust.otf")).deriveFont(size);
            return introRust;
        } catch (FontFormatException | IOException e) {
            System.out.println("Error loading IntroRust font. Font set to fallback: Sans Serif");
        }
        return null;
    }

    public static Font setABeeZeeFont(float size) {
        
        try {
            Font aBeeZee;
            aBeeZee = Font.createFont(Font.TRUETYPE_FONT, new File(fontFilePath + "ABeeZee-Regular")).deriveFont(size);
            return aBeeZee;
        } catch (FontFormatException | IOException e) {
            System.out.println("Error loading ABeeZee font. Font set to fallback: Sans Serif");
        }
       return null;
    }
}
