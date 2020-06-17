import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
//Matchers realiza a validação da mensagem de retorno atraves do Response
import static org.hamcrest.Matchers.*;

public class TesteApi {

	// testando API de cadastro

	@Test
	public void cadastro() {

		String url = "https://api.thecatapi.com/v1/user/passwordlesssignup";
		String corpo = "{\"email\": \"mateus.valentim19@hotmail.com\",\"appDescription\": \"automacaoApi\"}";

		Response response = given().contentType("application/json").body(corpo).when().post(url);
		response.then().body("message", containsString("SUCCESS")).statusCode(200);

		System.out.println("Retorno => " + response.body().asString());
	}

	@Test
	public void votacao() {

		String url = "https://api.thecatapi.com/v1/votes/";

		Response response = given().contentType("application/json")
				.body("{\"image_id\": \"MTYyNDU3Mg\", \"value\": \"true\", \"sub_id\": \"demo-b41aec\"}").when()
				.post(url);

		response.then().statusCode(200).body("message", containsString("SUCCESS"));

		System.out.println("Retorno => " + response.body().asString());
		String id = response.jsonPath().getString("id");

		System.out.println("ID => " + id);
	}

}
