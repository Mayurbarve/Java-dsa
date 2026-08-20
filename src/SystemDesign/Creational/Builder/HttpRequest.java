package SystemDesign.Creational.Builder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private final String url;
    private final String method;
    private final Map<String, String> headers;
    private final Map<String, String> queryParams;
    private final String body;
    private final int timeout;

    public HttpRequest(Builder builder){
        this.url = builder.url;
        this.method = builder.method;
        this.headers = Collections.unmodifiableMap(new HashMap<>(builder.headers));
        this.queryParams = Collections.unmodifiableMap(new HashMap<>(builder.queryParams));
        this.body = builder.body;
        this.timeout = builder.timeout;
    }

    public String getUrl() { return url; }
    public String getMethod() { return method; }
    public Map<String, String> getHeaders() { return headers; }
    public Map<String, String> getQueryParams() { return queryParams; }
    public String getBody() { return body; }
    public int getTimeout() { return timeout; }

    @Override
    public String toString(){
        return "HttpRequest{url='" + url + "', method='" + method +
                "', headers=" + headers + ", queryParams=" + queryParams +
                ", body='" + body + "', timeout=" + timeout + "}";
    }

    public static class Builder {
        private String url;
        private String method = "GET"; // Good practice: set default HTTP method
        private Map<String, String> headers = new HashMap<>(); // Initialized to avoid NullPointerException
        private Map<String, String> queryParams = new HashMap<>(); // Initialized to avoid NullPointerException
        private String body;
        private int timeout = 30000;

        public Builder (String url) {
            this.url = url;
        }

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder addHeader(String key, String value) {
            this.headers.put(key, value);
            return this;
        }

        public Builder addQueryParam(String key, String value) {
            this.queryParams.put(key, value);
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder timeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public HttpRequest build() {
            return new HttpRequest(this);
        }
    }
}