package erik.munk.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class UserDTOValidationTest {


  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(new ValidationTestRestController()).build();
  }

  @Test
  void givenValid_whenCorrectInput_thenSuccess() throws Exception {
    MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/testingValid")
      .accept(MediaType.TEXT_HTML)
      .param("name", "test")
      .param("givenName", "test")
      .param("address", "test")
    ).andReturn();
    assertNotNull(result);
    assertNotNull(result.getResponse());
    assertEquals("success", result.getResponse().getContentAsString());
  }

  @Test
  void givenValidateOnSms_whenCorrectInput_thenSuccess() throws Exception {
    MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/validateOnSms")
      .accept(MediaType.TEXT_HTML)
      .param("name", "test")
      .param("givenName", "test")
    ).andReturn();
    assertNotNull(result);
    assertNotNull(result.getResponse());
    assertEquals("success", result.getResponse().getContentAsString());
  }

  @Test
  void givenValidateOnSms_whenNotNumberInput_thenError() throws Exception {
    MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/validateOnSms")
      .accept(MediaType.TEXT_HTML)
      .param("name", "test")
      .param("givenName", "test")
      .param("address", "test")
    ).andReturn();
    assertNotNull(result);
    assertNotNull(result.getResponse());
    JSONObject json = new JSONObject(result.getResponse().getContentAsString());
    assertEquals("error", json.get("status"));
    assertTrue(json.get("messages").toString().contains("telephonePortable"));
  }

}
