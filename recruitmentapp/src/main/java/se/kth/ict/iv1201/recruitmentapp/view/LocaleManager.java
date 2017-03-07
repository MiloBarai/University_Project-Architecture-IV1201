package se.kth.ict.iv1201.recruitmentapp.view;

import java.util.ResourceBundle;
import java.util.Locale;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * Sets the correct locale for the entire application.
 */
@Named(value="localeManager")
@ApplicationScoped
public class LocaleManager {
    
    private String locale;
    
    public void changeLocale() {
        locale = getLanguageCode();
    }
    
    public String getLocale() {
        return locale;
    }
    
    private String getLanguageCode() {
        System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("languageCode"));
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("languageCode");
    }
}
