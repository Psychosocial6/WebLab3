package objects;

import java.io.Serializable;
import java.math.BigDecimal;

public class Result implements Serializable {
    private BigDecimal x;
    private BigDecimal y;
    private BigDecimal r;
    private boolean result;
    private double requestTime;
    private String localTime;

    public Result(BigDecimal x, BigDecimal y, BigDecimal r, boolean result, double requestTime, String localTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
        this.requestTime = requestTime;
        this.localTime = localTime;
    }

    public BigDecimal getX() {
        return x;
    }

    public BigDecimal getY() {
        return y;
    }

    public BigDecimal getR() {
        return r;
    }

    public boolean isResult() {
        return result;
    }

    public double getRequestTime() {
        return requestTime;
    }

    public String getLocalTime() {
        return localTime;
    }
}
