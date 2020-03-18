package erik.munk.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;

@Slf4j
public class JsonUtils {

	private JsonUtils() {
	}

	public static JSONObject objectToJson(final Object o) throws JSONException, JsonProcessingException {
		return new JSONObject(new ObjectMapper().writeValueAsString(o));
	}

	public static <T> T jsonToObject(final String json, final Class<? extends T> classType) throws IOException {
		return new ObjectMapper().enable(MapperFeature.USE_STD_BEAN_NAMING).readValue(json, classType);
	}

	public static <T> T jsonToObject(final JSONObject json, final Class<? extends T> classType) throws IOException {
		return jsonToObject(json.toString(), classType);
	}

	/**
	 * @param base64EncodedString
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject stringBase64ToJSONObject(final String base64EncodedString) throws JSONException {
		byte[] byteArray = Base64.decodeBase64(base64EncodedString);
		return new JSONObject(new String(byteArray));
	}
}
