package utils;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Validator {
    private static final Set<BigDecimal> availableXValues = new HashSet<>(List.of(new BigDecimal("-3"),
            new BigDecimal("-2"),
            new BigDecimal("-1"),
            new BigDecimal("0"),
            new BigDecimal("1"),
            new BigDecimal("2"),
            new BigDecimal("3"),
            new BigDecimal("4"),
            new BigDecimal("5")));
    private static final Set<BigDecimal> availableRValues = new HashSet<>(List.of(new BigDecimal("1"),
            new BigDecimal("2"),
            new BigDecimal("3"),
            new BigDecimal("4"),
            new BigDecimal("5")));
    private static final BigDecimal Y_MIN = new BigDecimal("-5");
    private static final BigDecimal Y_MAX = new BigDecimal("3");

    public static boolean validateData(BigDecimal x, BigDecimal y, BigDecimal r, String type) {
        if (type.equals("btn")) {
            if (!availableXValues.contains(x) && availableRValues.contains(r) && y.compareTo(Y_MIN) >= 0 && y.compareTo(Y_MAX) <= 0) {
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation error", "Some values are invalid");
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
                return false;
            }
            return true;
        }
        else if(type.equals("img")) {
            if (!availableRValues.contains(r)) {
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation error", "Wrong R value");
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
                return false;
            }
            return true;
        }
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation error", "Wrong operation type");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        return false;
    }
}
