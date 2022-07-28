import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
	public static void main(String[] args) throws Exception {

		// 1 - Make an HTTP connection and fetch the top 250 movies;
		String url = "https://fake-movie-database-api.herokuapp.com/api?s=batman";
		URI address = URI.create(url);
		var client = HttpClient.newHttpClient();
		var req = HttpRequest.newBuilder(address).GET().build();
		HttpResponse<String> res = client.send(req, BodyHandlers.ofString());
		String body = res.body();

		// 2 - Get only the data that matters (title, poster, year);
		var parser = new JsonParser();
		List<Map<String, String>> moviesList = parser.parse(body);

		// 3 - View and manipulate the data.
		var generator = new StickerGenerator();
		for (Map<String, String> movie : moviesList) {

			String urlImage = movie.get("Poster");
			String title = movie.get("Title");

			InputStream inputStream = new URL(urlImage).openStream();
			String fileName = title + ".png";

			generator.create(inputStream, fileName);

			System.out.println("Title: " + movie.get("Title"));
			System.out.println();
		}
	}
}
