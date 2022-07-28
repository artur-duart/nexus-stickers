import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickerGenerator {

	public void create(InputStream inputStream, String fileName) throws Exception {

		// 1 - Image Reading;
		// InputStream inputStream = new FileInputStream(new File("input/movie.jpg"));
//		InputStream inputStream = new URL(
//				"https://ia.media-imdb.com/images/M/MV5BOGZmYzVkMmItM2NiOS00MDI3LWI4ZWQtMTg0YWZkODRkMmViXkEyXkFqcGdeQXVyODY0NzcxNw@@.jpg")
//				.openStream();
		BufferedImage originalImage = ImageIO.read(inputStream);

		// 2 - Creates new image in memory with transparency and new size;
		int width = originalImage.getWidth();
		int height = originalImage.getHeight();
		int newHeight = height + 200;
		BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

		// 3 - Copy the original image to the new image (in memory);
		Graphics2D graphics = (Graphics2D) newImage.getGraphics();
		graphics.drawImage(originalImage, 0, 0, null);

		// 4 - Config font;
		var font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
		graphics.setColor(Color.YELLOW);
		graphics.setFont(font);

		// 5 - Write a phrase on the new image;
		graphics.drawString("Batman!!", width / 4, newHeight - 100);

		// 6 - Save the new image in a file.
		ImageIO.write(newImage, "png", new File("output/" + fileName));
	}
}
