/**
 * Created by student on 12/19/17.
 */
public class Player {
    private String name;
    private int rating,age;

    public Player(String name, int rating, int age) {
        this.name = name;
        this.rating = rating;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int updateRating() {
        if (18<=age&&age<28){
            this.setRating(getRating() +2);
            this.setAge(age+1);
        }
        if(age>=28){
            this.setRating(getRating() - 2);
            this.setAge(age+1);
        }
        return rating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}