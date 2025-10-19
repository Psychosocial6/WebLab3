package beans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import entities.ResultEntity;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import utils.DatabaseManager;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
@Named("historyBean")
public class HistoryBean implements Serializable {
    @Inject
    private DatabaseManager databaseManager;
    private List<ResultEntity> history;
    private ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        history = Collections.synchronizedList(databaseManager.getResults());
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public void addResult(ResultEntity result) {
        try {
            databaseManager.saveResult(result);
            history.add(0, result);
        }
        catch (Exception e) {
            throw new RuntimeException("Error adding result");
        }
    }

    public void clearHistory() {
        try {
            databaseManager.clearResults();
            history.clear();
        }
        catch (Exception e) {
            throw new RuntimeException("Error clearing history");
        }
    }

    public List<ResultEntity> getHistory() {
        return history;
    }

    public String getHistoryAsJson() {
        try {
            synchronized (history) {
                return objectMapper.writeValueAsString(history);
            }
        } catch (JsonProcessingException e) {
            return "[]";
        }
    }
}
