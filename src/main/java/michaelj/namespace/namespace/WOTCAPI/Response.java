package michaelj.namespace.namespace.WOTCAPI;

import java.util.List;

public class Response {
    private int count;
    private List<dndClass> results;

    public void setCount(int count) {
        this.count = count;
    }
    public void setResults(List<dndClass> results) {
        this.results = results;
    }

    class dndClass{
        private String name;
        private String url;

        public void setUrl(String url) {
            this.url = url;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
