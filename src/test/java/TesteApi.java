import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
//Matchers realiza a validação da mensagem de retorno atraves do Response
import static org.hamcrest.Matchers.*;

public class TesteApi {

	String vote_id;
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

		vote_id = id;
		System.out.println("ID => " + id);
	}

	@Test
	public void deletaVotacao() {
		votacao();
		deletarVoto();
	}

	private void deletarVoto() {
		String url = "https://api.thecatapi.com/v1/votes/{vote_id}";

		Response response = given().contentType("application/json")
				.header("x-api-key", "e4c41546-a216-4002-8d82-53a575d52017").pathParam(vote_id, vote_id).when()
				.delete("https://api.thecatapi.com/v1/votes/{vote_id}");

		System.out.println("Retorno Deletar Voto =>" + response.body().asString());

		response.then().statusCode(200).body("message", containsString("SUCCESS"));

	}

}
