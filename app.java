import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;
import java.net.InetSocketAddress;

public class App {
    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", new MyHandler());

        server.setExecutor(null);
        server.start();

        System.out.println("Server started on port 8080");
    }

    static class MyHandler implements HttpHandler {
        public void handle(HttpExchange exchange) {
            try {
                String response = "Hello from Docker Java Web App (Port 8080)";
                exchange.sendResponseHeaders(200, response.length());

                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
