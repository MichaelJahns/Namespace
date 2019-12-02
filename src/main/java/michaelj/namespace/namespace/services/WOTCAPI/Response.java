package michaelj.namespace.namespace.services.WOTCAPI;

import java.util.List;

public class Response {
    private int count;
    private List<dndClass> results;

    public int getCount() {
        return count;
    }
    public List<dndClass> getResults() {
        return results;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public void setResults(List<dndClass> results) {
        this.results = results;
    }

    public class dndClass{
        private String name;
        private String url;

        public void setUrl(String url) {
            this.url = url;
        }
        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }
        public String getName() {
            return name;
        }

    }
}
