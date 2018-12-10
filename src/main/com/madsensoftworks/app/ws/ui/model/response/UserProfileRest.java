package main.com.madsensoftworks.app.ws.ui.model.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserProfileRest {

    private String publicUserId;
    private String firstName;
    private String lastName;
    private String email;
    private String href;

    public String getPublicUserId() {
        return publicUserId;
    }

    public void setPublicUserId(String publicUserId) {
        this.publicUserId = publicUserId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
