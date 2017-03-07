import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class LoadCardsToApi {
    public void loadBuyerCards(String sessionToken,String card_id, String phone,String barcode ) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        ResponseHandler<String> handler = new BasicResponseHandler();
        ObjectNode request = mapper.createObjectNode();

        HttpPost webshopPost = new HttpPost("http://rest-seller-api.crumzi.com/api/private/cards/seller/create");
        StringEntity entity = new StringEntity(request.toString());
        webshopPost.setEntity(entity);

        webshopPost.setHeader("Content-type", "application/json");
        webshopPost.setHeader("Session-Token", sessionToken);

       // id_type_card =2c9f91f45908ad94015909302fcc0006

        // test 2c9f91f45998b4450159a6cad687001d

        String body = String.format("{\"phone_number\": \"%s\",\n" +
                "\"card_type\": {\n" +
                "\"id\": \"%s\"\n" +
                "},\n" +
                "\"barcode\": \"%s\",\n" +
                "\"barcode_type\": \"code128c\",\n" +
                "\"seller_descr\": \"\"\n" +
                "}",phone,card_id,barcode);

        entity = new StringEntity(body);
        webshopPost.setEntity(entity);
        CloseableHttpResponse webshopResponce = httpclient.execute(webshopPost);
        String webshopBody = handler.handleResponse(webshopResponce);

        //throw new IllegalStateException("resp1 error ");
    }

}
