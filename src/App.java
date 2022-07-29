import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
	public static void main(String[] args) throws Exception {

		// 1 - Make an HTTP connection and fetch the top 250 contents;
		
		FakeMovieContentExtractor extractor = new FakeMovieContentExtractor();
		String url = "https://fake-movie-database-api.herokuapp.com/api?s=batman";
		
//		NasaContentExtractor extractor = new NasaContentExtractor();
//		String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2005-01-18&end_date=2005-01-27";
//		String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD";
		
//		ImdbContentExtractor extractor = new ImdbContentExtractor();
//		String url = "https://imdb-api.com/en/API/Top250TVs/key";
//		String url = "https://linguagens-imersao-api.herokuapp.com/linguagens";

		var http = new ClientHttp();
		String json = http.searchData(url);

		// 2 - View and manipulate the data.
		List<Content> contents = extractor.extractContents(json);

		var generator = new StickerGenerator();
		for (int i = 0; i < 4; i++) {

			Content content = contents.get(i);

			InputStream inputStream = new URL(content.getUrlImage()).openStream();
			String fileName = content.getTitle() + ".png";

			generator.create(inputStream, fileName);

			System.out.println("Title: " + content.getTitle());
			System.out.println();
		}
	}
}
