package entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "results")
public class ResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "x", nullable = false)
    private BigDecimal x;
    @Column(name = "y", nullable = false)
    private BigDecimal y;
    @Column(name = "r", nullable = false)
    private BigDecimal r;
    @Column(name = "result", nullable = false)
    private boolean result;
    @Column(name = "request_time", nullable = false)
    private double requestTime;
    @Column(name = "local_time", nullable = false)
    private LocalDateTime localTime;

    public ResultEntity() {
    }

    public ResultEntity(BigDecimal x, BigDecimal y, BigDecimal r, boolean result, double requestTime, LocalDateTime localTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
        this.requestTime = requestTime;
        this.localTime = localTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isResult() {
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

    public LocalDateTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalDateTime localTime) {
        this.localTime = localTime;
    }

    public String getFormattedLocalTime() {
        if (localTime == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy");
        return localTime.format(formatter);
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", result=" + result +
                ", requestTime=" + requestTime +
                ", localTime='" + localTime + '\'' +
                '}';
    }
}
