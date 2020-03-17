package erik.munk.mappingDesign;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MyClient {

	private RestTemplate restTemplate;

	public MyClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	/**
	 * Gets a person from a third party service. E.G : Rest/Soap Api
	 * @return
	 */
	public Person retrievePerson() throws JsonProcessingException {
		String thirdPartyResult = restTemplate.getForObject("", String.class);
		return new ObjectMapper().readValue(thirdPartyResult, Person.class);
	}
}
