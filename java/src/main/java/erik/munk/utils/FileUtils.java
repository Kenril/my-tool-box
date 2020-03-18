package erik.munk.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
public class FileUtils {

	public static final String RESOURCES_FOLDER = "src/test/resources/";

	public <T> T createObjectFromJsonFile(final String jsonFile, final Class<T> targetClass) throws FileNotFoundException {
		String json = readJsonFile(RESOURCES_FOLDER + jsonFile);
		T data = null;
		try {
			data = (T) JsonUtils.jsonToObject(json, targetClass);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static String readJsonFile(final String filename) throws FileNotFoundException {
		File file = readFile(filename);
		StringBuilder sb = new StringBuilder();
		try (BufferedReader fr = new BufferedReader(new FileReader(file))) {
			String s;
			while ((s = fr.readLine()) != null) {
				sb.append(s);
			}
		} catch (Exception e) {
			log.error("fail parsing json from file : {}", file.getPath());
		}
		return sb.toString();
	}

	public static File readFile(final String filename) throws FileNotFoundException {
		File file = new File(filename);
		if (!file.exists()) {
			log.error("Failed to find file {}", filename);
			throw new FileNotFoundException("File " + filename + " not found");
		}
		return file;
	}
}
