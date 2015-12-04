package core.util;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class LoadStopWords {

	public static Set<String> getAllStopWords() throws IOException {
		Set<String> stopWords = new HashSet<String>();
		return stopWords;
	}

	public static void main(String[] args) throws IOException {
		System.out.println(getAllStopWords().size());
	}
}
