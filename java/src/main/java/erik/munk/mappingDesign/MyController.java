package erik.munk.mappingDesign;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("mapping")
public class MyController {

	private MyClient client;

	public MyController(MyClient client) {
		this.client = client;
	}

	@GetMapping("person")
	public Person getPerson() throws JsonProcessingException {
		return client.retrievePerson();
	}

}
