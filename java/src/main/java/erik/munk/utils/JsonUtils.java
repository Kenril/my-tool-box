package erik.munk.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JsonUtils {

	public JSONObject objectToJson(final Object o) throws JSONException, JsonProcessingException {
		return new JSONObject(new ObjectMapper().writeValueAsString(o));
	}

	public <T> T jsonToObject(final String json, final Class<? extends T> classType) throws JsonProcessingException {
		return new ObjectMapper().enable(Feature.IGNORE_UNDEFINED).enable(JsonGenerator.Feature.IGNORE_UNKNOWN).readValue(json, classType);
	}

	public <T> T jsonToObject(final JSONObject json, final Class<? extends T> classType) throws JsonProcessingException {
		return jsonToObject(json.toString(), classType);
	}

	public JSONObject stringBase64ToJSONObject(final String base64EncodedString) throws JSONException {
		byte[] byteArray = Base64.decodeBase64(base64EncodedString);
		return new JSONObject(new String(byteArray));
	}
}
