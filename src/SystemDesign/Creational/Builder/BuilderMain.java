package SystemDesign.Creational.Builder;

public class BuilderMain {
        public static void main(String[] args) {
            // Simple GET request - just the URL
            HttpRequest get = new HttpRequest.Builder("https://api.example.com/users")
                    .build();

            // POST with body and custom timeout
            HttpRequest post = new HttpRequest.Builder("https://api.example.com/users")
                    .method("POST")
                    .addHeader("Content-Type", "application/json")
                    .body("{\"name\":\"Alice\",\"email\":\"alice@example.com\"}")
                    .timeout(5000)
                    .build();

            // Authenticated PUT with query parameters
            HttpRequest put = new HttpRequest.Builder("https://api.example.com/config")
                    .method("PUT")
                    .addHeader("Authorization", "Bearer token123")
                    .addHeader("Content-Type", "application/json")
                    .addQueryParam("env", "production")
                    .addQueryParam("version", "2")
                    .body("{\"feature_flag\":true}")
                    .timeout(10000)
                    .build();

            System.out.println(get);
            System.out.println(post);
            System.out.println(put);
        }
    }