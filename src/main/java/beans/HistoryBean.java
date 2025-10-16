package beans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import objects.Result;

import java.util.ArrayList;

@ApplicationScoped
@Named("HistoryBean")
public class HistoryBean {
    private ArrayList<Result> history = new ArrayList<>();

    public void addResult(Result result) {
        history.add(result);
    }

    public void clearHistory() {
        history.clear();
    }

    public ArrayList<Result> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<Result> history) {
        this.history = history;
    }
}
