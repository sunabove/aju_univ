package chat;

import java.io.*;
import java.net.*;

public class URLConnDemo {
	public static void main(String[] args) {
		try {
			String urlText = "http://www.naver.com";

			URL url = new URL(urlText);
			URLConnection urlConnection = url.openConnection();
			HttpURLConnection connection = null;
			if (urlConnection instanceof HttpURLConnection) {
				connection = (HttpURLConnection) urlConnection;
			} else {
				System.out.println("Please enter an HTTP URL.");
				return;
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String urlString = "";
			String current;
			String newLine = "\r\n";

			while ((current = in.readLine()) != null) {
				urlString += current + newLine;
			}
			System.out.println(urlString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
