package beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@RequestScoped
@Named("navigationBean")
public class NavigationBean {

    public String redirectMain() {
        return "main.jsf?faces-redirect=true";
    }

    public String redirectBack() {
        return "index?faces-redirect=true";
    }
}
