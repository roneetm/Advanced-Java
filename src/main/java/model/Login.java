package model;

public class Login {
    private String email;
    private String password;
    private String partyId;

    public Login() {
    }

    @Override
    public String toString() {
        return "Login{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", partyId='" + partyId + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public Login(String email, String password, String partyId) {
        this.email = email;
        this.password = password;
        this.partyId = partyId;
    }
}
