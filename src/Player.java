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

    //Adds a year to the players age and updates their rating
    public int updateRating() {
        age++;
        int rand = (int)(Math.random()*10);

        if (rand <= 8) {
            if (age < 28) {
                rating += 1;
            } else {
                rating -= 2;
            }
            if(rand<=1){
                if (age < 28) {
                    rating += 1;
                } else {
                    rating -= 1;
                }
            }

        }
        if(rand == 2){
            if (age < 28) {
                rating -= 1;
            } else {
                rating += 1;
            }
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