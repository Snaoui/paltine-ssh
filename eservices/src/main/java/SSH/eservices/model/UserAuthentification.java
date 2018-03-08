package SSH.eservices.model;

public class UserAuthentification {

    private String password;
    private String email;

    public UserAuthentification(){}
    public UserAuthentification(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getPassword(){ return password;}
    public String getEmail(){ return email;}

    public void setPassword(String password){ this.password = password;}
    public void setEmail(String email){ this.email = email;}
}