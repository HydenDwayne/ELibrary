package view.front_pages;

public class FilePath {
	private static String fontFilePath = FilePath.class.getResource("/view/fonts/").getPath().substring(1);
	private static String imgFilePath = FilePath.class.getResource("/view/img/").getPath().substring(1);
	
	public static String getFontFilePath() {
		return fontFilePath;
	}
	public static String getImgFilePath() {
		return imgFilePath;
	}
}
