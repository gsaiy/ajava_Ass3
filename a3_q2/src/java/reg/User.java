package reg;

public class User {
    private int id;
    private String name;
    private String lastName;
    private String city;
    private String mobile;
    private String email;
    
    public User() {
    }
    
    public User(int id, String name, String lastName, String city, String mobile, String email) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.city = city;
        this.mobile = mobile;
        this.email = email;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
}