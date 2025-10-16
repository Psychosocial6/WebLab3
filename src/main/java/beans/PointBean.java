package beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import objects.Result;
import utils.AreaHitChecker;
import utils.Validator;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Named("pointBean")
@RequestScoped
public class PointBean implements Serializable {
    private BigDecimal x;
    private BigDecimal y;
    private BigDecimal r;
    private String type;
    private boolean result;
    private double requestTime;
    private String localTime;

    @Inject
    private HistoryBean historyBean;

    public void check() {
        if (!Validator.validateData(x, y, r, type)) {
            return;
        }
        long startTime = System.nanoTime();
        result = AreaHitChecker.checkHit(x, y, r);
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy");
        localTime = time.format(formatter);
        long endTime = System.nanoTime();
        requestTime = Math.round(((double) (endTime - startTime) / 1e6) * 1e6) / 1e6;

        Result requestResult = new Result(x, y, r, result, requestTime, localTime);
        historyBean.addResult(requestResult);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public double getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(double requestTime) {
        this.requestTime = requestTime;
    }

    public String getLocalTime() {
        return localTime;
    }

    public void setLocalTime(String localTime) {
        this.localTime = localTime;
    }

    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    public BigDecimal getR() {
        return r;
    }

    public void setR(BigDecimal r) {
        this.r = r;
    }
}
