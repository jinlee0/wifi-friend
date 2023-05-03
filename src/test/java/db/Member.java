package db;

public class Member {
    private String id;
    private String name;
    private String email;

    private Member() {

    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
