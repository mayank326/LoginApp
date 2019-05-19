public class Users {

    private String userName;
    private String userAge;
    private String userGender;
    private String userCountry;

    public Users(String userName,String userAge, String userGender, String userCountry) {
        this.userName = userName;
        this.userAge=userAge;
        this.userGender = userGender;
        this.userCountry = userCountry;
    }

    public String getUserAge() {
        return userAge;
    }


    public String getUserName() {
        return userName;
    }

    public String getUserGender() {
        return userGender;
    }

    public String getUserCountry() {
        return userCountry;
    }
}
