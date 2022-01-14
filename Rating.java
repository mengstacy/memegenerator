/**
 * March 19, 2021 
 * Stacy Meng 
 * swm8xb 
 * Homework 5
 */

// Sources: Class lectures, Cohort members (Daniel Tohti)

import java.util.ArrayList;

public class Rating {
    int score;
    User user;

    /*
     * Default constructor with score of 0
     */
    public Rating() {
        User u = new User();
        this.user = u;
        this.score = 0;
    }

    /*
     * Overloaded constructor that accepts user and a score
     */
    public Rating(User user, int score) {
        this.user = user;
        if (score != 1 && score != -1 && score != 0) {
            this.score = 0;
        } else {
            this.score = score;
        }

    }

    /**
     * @return the name
     */

    public int getScore() {
        return score;
    }

    /**
     * Determine if the score set is a valid score of +1, -1, or 0
     * 
     * @param score
     * @return boolean for if the score can be set or not
     */

    public boolean setScore(int score) {
        if (score != 1 && score != -1 && score != 0) {
            return false;
        } else {
            this.score = score;
            return true;
        }
    }

    /**
     * @return the user
     */

    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Returns string translating +1 score as upvote, 0 as pass, and -1 ad downvote
     */
    @Override
    public String toString() {
        String r = "";
        if (score == 1) {
            r = this.user.getUserName() + " rated as an upvote";
        }
        if (score == -1) {
            r = this.user.getUserName() + " rated as a downvote";
        }
        if (score == 0) {
            r = this.user.getUserName() + " rated as a pass";
        }

        return r;

    }

    @Override
    public boolean equals(Object object) {
        // return false if parameter object is null
        if (object == null) {
            return false;
        }

        if (!(object instanceof Rating)) {
            return false; // return false is parameter object is not of type Rating
        }

        Rating other = (Rating) object;
        // return true if score and user are the same
        if (this.score == other.score && this.user.equals(other.user)) {
            return true;
        } else {
            return false; // return false if conditions are not met
        }

    }

    /**
     * Main Method Testing
     */
    public static void main(String[] args) {
        User aU = new User();
        User bU = new User();
        
        //test new constructor
        Rating a = new Rating(aU, 1);
        Rating b = new Rating(bU, -1);
        
        //test toString() method
        System.out.println(a.toString()); // should return "Rating was an upvote"
        System.out.println(b.toString()); // should return "Rating was a downvote"
        
        //test equals() method
        Rating c = new Rating();
        c = a;
        System.out.println(a.equals(c)); //should return true
        System.out.println(a.equals(b)); //should return false
        
        //test setScore() method
        System.out.println(a.setScore(-1)); //should return true
        System.out.println(a.getScore()); //should return -1
        System.out.println(b.setScore(2)); //should return false
        System.out.println(b.getScore()); //should return -1, or no change
        
    }

    
}
