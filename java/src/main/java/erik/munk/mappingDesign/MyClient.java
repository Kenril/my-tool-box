package erik.munk.mappingDesign;


import com.fasterxml.jackson.core.JsonProcessingException;
import erik.munk.utils.JsonUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MyClient {

	private final RestTemplate restTemplate;

	private final JsonUtils jsonUtils;

	public MyClient(RestTemplate restTemplate, JsonUtils jsonUtils) {
		this.restTemplate = restTemplate;
		this.jsonUtils = jsonUtils;
	}

	/**
	 * Gets a person from a third party service. E.G : Rest/Soap Api
	 * @return
	 */
	public Person retrievePerson() throws JsonProcessingException {
		String thirdPartyResult = restTemplate.getForObject("", String.class);
		return jsonUtils.jsonToObject(thirdPartyResult, Person.class);
	}
}
