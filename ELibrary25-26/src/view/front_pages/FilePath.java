package view.front_pages;

import java.net.URL;

public class FilePath {

    private FilePath() {}

    public static URL image(String name) {
        return FilePath.class.getResource("/view/img/" + name);
    }

    public static URL font(String name) {
        return FilePath.class.getResource("/view/fonts/" + name);
    }
}