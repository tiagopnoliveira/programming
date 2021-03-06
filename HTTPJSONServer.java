import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HTTPJSONServer {
    private static final String HOSTNAME = "localhost";
    private static final int PORT = 8080;
    private static final int BACKLOG = 1;

    private static final String HEADER_ALLOW = "Allow";
    private static final String HEADER_CONTENT_TYPE = "Content-Type";

    private static final Charset CHARSET = StandardCharsets.UTF_8;

    private static final int STATUS_OK = 200;
    private static final int STATUS_METHOD_NOT_ALLOWED = 405;

    private static final int NO_RESPONSE_LENGTH = -1;

    private static final String METHOD_GET = "GET";
    private static final String METHOD_OPTIONS = "OPTIONS";
    private static final String ALLOWED_METHODS = METHOD_GET + "," + METHOD_OPTIONS;

    public static void main(final String... args) throws IOException {
        final HttpServer server = HttpServer.create(new InetSocketAddress(HOSTNAME, PORT), BACKLOG);
        server.createContext("/products", he -> {
            try {
                final Headers headers = he.getResponseHeaders();
                final String requestMethod = he.getRequestMethod().toUpperCase();
                switch (requestMethod) {
                    case METHOD_GET:
                        final Map<String, List<String>> requestParameters = getRequestParameters(he.getRequestURI());
                        // do something with the request parameters
                        //~ List<String> q = requestParameters.get("q");
                        //~ for(String s : q) {
							//~ System.out.println(s);
						//~ }

                        final String responseBody = "jsonCallback([{\"sku\":43900,\"name\":\"Duracell - AAA Batteries (4-Pack)\",\"type\":\"HardGood\",\"price\":5.49,\"upc\":\"041333424019\",\"category\":[{\"id\":\"pcmcat312300050015\",\"name\":\"Connected Home & Housewares\"},{\"id\":\"pcmcat248700050021\",\"name\":\"Housewares\"},{\"id\":\"pcmcat303600050001\",\"name\":\"Household Batteries\"},{\"id\":\"abcat0208002\",\"name\":\"Alkaline Batteries\"}],\"shipping\":5.49,\"description\":\"Compatible with select electronic devices; AAA size; DURALOCK Power Preserve technology; 4-pack\",\"manufacturer\":\"Duracell\",\"model\":\"MN2400B4Z\",\"url\":\"http://www.bestbuy.com/site/duracell-aaa-batteries-4-pack/43900.p?id=1051384074145&skuId=43900&cmp=RMXCC\",\"image\":\"http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg\"},{\"sku\":48530,\"name\":\"Duracell - AA 1.5V CopperTop Batteries (4-Pack)\",\"type\":\"HardGood\",\"price\":5.49,\"upc\":\"041333415017\",\"category\":[{\"id\":\"pcmcat312300050015\",\"name\":\"Connected Home & Housewares\"},{\"id\":\"pcmcat248700050021\",\"name\":\"Housewares\"},{\"id\":\"pcmcat303600050001\",\"name\":\"Household Batteries\"},{\"id\":\"abcat0208002\",\"name\":\"Alkaline Batteries\"}],\"shipping\":5.49,\"description\":\"Long-lasting energy; DURALOCK Power Preserve technology; for toys, clocks, radios, games, remotes, PDAs and more\",\"manufacturer\":\"Duracell\",\"model\":\"MN1500B4Z\",\"url\":\"http://www.bestbuy.com/site/duracell-aa-1-5v-coppertop-batteries-4-pack/48530.p?id=1099385268988&skuId=48530&cmp=RMXCC\",\"image\":\"http://img.bbystatic.com/BestBuy_US/images/products/4853/48530_sa.jpg\"},{\"sku\":127687,\"name\":\"Duracell - AA Batteries (8-Pack)\",\"type\":\"HardGood\",\"price\":7.49,\"upc\":\"041333825014\",\"category\":[{\"id\":\"pcmcat312300050015\",\"name\":\"Connected Home & Housewares\"},{\"id\":\"pcmcat248700050021\",\"name\":\"Housewares\"},{\"id\":\"pcmcat303600050001\",\"name\":\"Household Batteries\"},{\"id\":\"abcat0208002\",\"name\":\"Alkaline Batteries\"}],\"shipping\":5.49,\"description\":\"Compatible with select electronic devices; AA size; DURALOCK Power Preserve technology; 8-pack\",\"manufacturer\":\"Duracell\",\"model\":\"MN1500B8Z\",\"url\":\"http://www.bestbuy.com/site/duracell-aa-batteries-8-pack/127687.p?id=1051384045676&skuId=127687&cmp=RMXCC\",\"image\":\"http://img.bbystatic.com/BestBuy_US/images/products/1276/127687_sa.jpg\"},{\"sku\":150115,\"name\":\"Energizer - MAX Batteries AA (4-Pack)\",\"type\":\"HardGood\",\"price\":4.99,\"upc\":\"039800011329\",\"category\":[{\"id\":\"pcmcat312300050015\",\"name\":\"Connected Home & Housewares\"},{\"id\":\"pcmcat248700050021\",\"name\":\"Housewares\"},{\"id\":\"pcmcat303600050001\",\"name\":\"Household Batteries\"},{\"id\":\"abcat0208002\",\"name\":\"Alkaline Batteries\"}],\"shipping\":5.49,\"description\":\"4-pack AA alkaline batteries; battery tester included\",\"manufacturer\":\"Energizer\",\"model\":\"E91BP-4\",\"url\":\"http://www.bestbuy.com/site/energizer-max-batteries-aa-4-pack/150115.p?id=1051384046217&skuId=150115&cmp=RMXCC\",\"image\":\"http://img.bbystatic.com/BestBuy_US/images/products/1501/150115_sa.jpg\"},{\"sku\":185230,\"name\":\"Duracell - C Batteries (4-Pack)\",\"type\":\"HardGood\",\"price\":8.99,\"upc\":\"041333440019\",\"category\":[{\"id\":\"pcmcat312300050015\",\"name\":\"Connected Home & Housewares\"},{\"id\":\"pcmcat248700050021\",\"name\":\"Housewares\"},{\"id\":\"pcmcat303600050001\",\"name\":\"Household Batteries\"},{\"id\":\"abcat0208002\",\"name\":\"Alkaline Batteries\"}],\"shipping\":5.49,\"description\":\"Compatible with select electronic devices; C size; DURALOCK Power Preserve technology; 4-pack\",\"manufacturer\":\"Duracell\",\"model\":\"MN1400R4Z\",\"url\":\"http://www.bestbuy.com/site/duracell-c-batteries-4-pack/185230.p?id=1051384046486&skuId=185230&cmp=RMXCC\",\"image\":\"http://img.bbystatic.com/BestBuy_US/images/products/1852/185230_sa.jpg\"},{\"sku\":185267,\"name\":\"Duracell - D Batteries (4-Pack)\",\"type\":\"HardGood\",\"price\":9.99,\"upc\":\"041333430010\",\"category\":[{\"id\":\"pcmcat312300050015\",\"name\":\"Connected Home & Housewares\"},{\"id\":\"pcmcat248700050021\",\"name\":\"Housewares\"},{\"id\":\"pcmcat303600050001\",\"name\":\"Household Batteries\"},{\"id\":\"abcat0208002\",\"name\":\"Alkaline Batteries\"}],\"shipping\":5.99,\"description\":\"Compatible with select electronic devices; D size; DURALOCK Power Preserve technology; 4-pack\",\"manufacturer\":\"Duracell\",\"model\":\"MN1300R4Z\",\"url\":\"http://www.bestbuy.com/site/duracell-d-batteries-4-pack/185267.p?id=1051384046551&skuId=185267&cmp=RMXCC\",\"image\":\"http://img.bbystatic.com/BestBuy_US/images/products/1852/185267_sa.jpg\"}]);";
                        headers.set(HEADER_CONTENT_TYPE, String.format("application/json; charset=%s", CHARSET));
                        final byte[] rawResponseBody = responseBody.getBytes(CHARSET);
                        he.sendResponseHeaders(STATUS_OK, rawResponseBody.length);
                        he.getResponseBody().write(rawResponseBody);
                        break;
                    case METHOD_OPTIONS:
                        headers.set(HEADER_ALLOW, ALLOWED_METHODS);
                        he.sendResponseHeaders(STATUS_OK, NO_RESPONSE_LENGTH);
                        break;
                    default:
                        headers.set(HEADER_ALLOW, ALLOWED_METHODS);
                        he.sendResponseHeaders(STATUS_METHOD_NOT_ALLOWED, NO_RESPONSE_LENGTH);
                        break;
                }
            } finally {
                he.close();
            }
        });
        server.start();
    }

    private static Map<String, List<String>> getRequestParameters(final URI requestUri) {
        final Map<String, List<String>> requestParameters = new LinkedHashMap<>();
        final String requestQuery = requestUri.getRawQuery();
        if (requestQuery != null) {
            final String[] rawRequestParameters = requestQuery.split("[&;]", -1);
            for (final String rawRequestParameter : rawRequestParameters) {
                final String[] requestParameter = rawRequestParameter.split("=", 2);
                final String requestParameterName = decodeUrlComponent(requestParameter[0]);
                requestParameters.putIfAbsent(requestParameterName, new ArrayList<>());
                final String requestParameterValue = requestParameter.length > 1 ? decodeUrlComponent(requestParameter[1]) : null;
                requestParameters.get(requestParameterName).add(requestParameterValue);
            }
        }
        return requestParameters;
    }

    private static String decodeUrlComponent(final String urlComponent) {
        try {
            return URLDecoder.decode(urlComponent, CHARSET.name());
        } catch (final UnsupportedEncodingException ex) {
            throw new InternalError(ex);
        }
    }
}
