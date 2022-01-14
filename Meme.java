/**
 * March 19, 2021 
 * Stacy Meng 
 * swm8xb 
 * Homework 5
 */

// Sources: Class lectures, Cohort members (Daniel Tohti)

import java.util.ArrayList;

public class Meme implements Comparable<Meme>{
    /*
     * creator of the meme which is of type User
     */
    User creator;

    /*
     * the background image of type BackgroundImage, which has an imagefile name, title, and description
     */
    BackgroundImage backgroundImage;

    /*
     * array of ratings for the meme
     */
    Rating[] ratings;

    /*
     * String caption for the meme
     */
    String caption;

    /*
     * String for alignment type of the meme
     */
    String captionVerticalAlign;

    /*
     * boolean to verify if the meme is shared or not
     */
    boolean shared;

    /*
     * Basic constructor with no parameters and defaults to bottom vertical align
     */
    public Meme() {
        ratings = new Rating[10]; // creates new ratings array with length 10
        BackgroundImage b = new BackgroundImage();
        backgroundImage = b;
        User u = new User();
        creator = u;
        caption = "caption"; // default caption is "caption"
        captionVerticalAlign = "bottom"; // default alignment is bottom
        shared = false;
    }

    /*
     * Overloaded Constructor that takes in backgroundImage, caption, and User
     */
    public Meme(BackgroundImage backgroundImage, String caption, User creator) {
        this.backgroundImage = backgroundImage;
        this.caption = caption;
        this.creator = creator;
        this.ratings = new Rating[10]; // default ratings length is length 10
        this.captionVerticalAlign = "bottom"; // default alignment is bottom
    }

    /**
     * @return User creator
     */
    public User getCreator() {
        return creator;
    }

    /**
     * @param the creator of type User
     */
    public void setCreator(User creator) {
        this.creator = creator;
    }

    /**
     * @return backgroundImage of Meme
     */
    public BackgroundImage getBackgroundImage() {
        return backgroundImage;
    }

    /**
     * @param backgroundImage for the Meme
     */
    public void setBackgroundImage(BackgroundImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    /**
     * @return array of Meme's ratings
     */
    public Rating[] getRatings() {
        return ratings;
    }

    /**
     * @param array of ratings for the Meme
     */
    public void setRatings(Rating[] ratings) {
        this.ratings = ratings;
    }

    /**
     * @return String for Meme caption
     */
    public String getCaption() {
        return caption;
    }

    /**
     * @param String of the caption of Meme
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * @return the form of vertical alignment of caption for the Meme, which can be top, bottom, or middle
     */
    public String getCaptionVerticalAlign() {
        return captionVerticalAlign;
    }

    /*
     * Checks if the new setting for caption vertical align is valid and assigns it to captionVerticalAlign if it is
     * @param the setting for captionVerticalAlign (can be top, bottom, or middle)
     * @return boolean for if the change in setting can be done or not
     */
    public boolean setCaptionVerticalAlign(String captionVerticalAlign) {
        // check if parameter is valid in that it is equal to top, bottom, or middle
        if (captionVerticalAlign.contentEquals("top") || captionVerticalAlign.contentEquals("bottom")
                || captionVerticalAlign.contentEquals("middle")) {
            this.captionVerticalAlign = captionVerticalAlign; // if it is valid, assign it
            return true; // return true if assignment successful
        }
        return false; // return false for non-valid parameter and assignment unsuccessful

    }

    /**
     * @return boolean for if meme is shared successfully or not
     */
    public boolean getShared() {
        return shared;
    }

    /**
     * @param boolean for if meme is shared or not
     */
    public void setShared(boolean shared) {
        this.shared = shared;
    }

    /**
     * Adds a new rating to the end of array of ratings if there is still space in the array If there is no space left,
     * shifts all the elements of the array one position up and discards the first element
     * 
     * @param new rating to be added to ratings array
     * @return boolean for if addition of rating to array is successful
     */
    public boolean addRating(Rating rating) {

        boolean available = false; // default boolean for if there is space available as false
        // if there is space at end of array, check for where the empty, or null, space is and add passed in rating
        for (int a = 0; a < ratings.length && available == false; a++) {
            if (this.ratings[a] == null) {
                this.ratings[a] = rating;
                available = true; // return true if there is space and new rating is added
            }
        }

        // if there is no space left in the ratings array, then shift array one value left
        if (available == false) {
            for (int j = 0; j < ratings.length - 1; j++) {
                this.ratings[j] = ratings[j + 1];
            }
            // add in the new rating at end
            ratings[ratings.length - 1] = rating;
            available = true;
        }

        return available;

    }

    /**
     * Calculates sum of all the ratings in ratings array
     * 
     * @return double of the overall ratings
     */
    public double calculateOverallRating() {
        double sum = 0.0;
        // loop through ratings to get score of each Rating in ratings array
        for (int i = 0; i < ratings.length; i++) {
            if (ratings[i] != null) {
                sum += ratings[i].getScore(); // add each score in array to running sum
            }
        }
        return sum;
    }

    /*
     * private helper method to show brackets around the caption in toString() method
     * @return String of caption in quotation marks
     */
    private String captionBrackets() {
        return " '" + caption + "' ";
    }

    /**
     * Private helper method to format ratings in the toString() method Counts all the +1 and -1 ratings for the Meme
     * 
     * @return String of ratings formatted into correct display format
     */
    private String ratingDisplay() {
        int plus = 0; // number of +1 ratings
        int minus = 0; // number of -1 ratings
        for (int i = 0; i < ratings.length; i++) {
            if (ratings[i] != null) {
                // if the rating is +1, or above 0, add to plus count
                if (ratings[i].getScore() > 0) {
                    plus++;
                    // if the rating is -1, or below 0, add to minus count
                } else if (ratings[i].getScore() < 0) {
                    minus++;
                }
            }
        }
        return " [+1: " + plus + ", -1: " + minus + "]"; // correct format to display sorted ratings
    }

    /**
     * Private helper method to format the additional tag for the creator of the meme
     */
    private String creatorString() {
        return " - created by " + this.creator.getUserName();
    }

    /**
     * Returns String of the backgroundImage(the image title and description), caption of meme in quotation marks, overall
     * rating, and +1 rating vs -1 ratings
     */
    @Override
    public String toString() {
        String ratingsString = Double.toString(calculateOverallRating()); // make overall rating double into a String
        return this.backgroundImage.toString() + captionBrackets() + ratingsString + ratingDisplay() + creatorString();

    }

    /**
     * Checks to see if parameter is a Meme object and the creator, caption, and backgroundImage match
     * 
     * @return boolean for if object passed in is equal to background image object or not
     */

    @Override
    public boolean equals(Object object) {
        // return false if parameter object is null
        if (object == null) {
            return false;
        }
        if (!(object instanceof Meme)) {
            return false; // return false is parameter object is not of type Meme
        }

        Meme other = (Meme) object;
        // return true if creator, caption, and backgroundImage are the same
        if (this.creator.equals(other.creator) && this.caption.equals(other.caption)
                && this.backgroundImage.equals(other.backgroundImage)) {
            return true;
        } else {
            return false; // return false if conditions are not met
        }
    }
    
    public int compareTo(Meme meme) {
        //first compare by caption ascending
        int first = caption.compareTo(meme.caption);
        if(first != 0) {
            return first;
        }
        //if identical, compare by BackgroundImage ascending
        int second = backgroundImage.compareTo(meme.backgroundImage);
        if(second != 0) {
            return second;
        }
        //if identical, compare by overall rating descending
        int third = (int)(meme.calculateOverallRating() - this.calculateOverallRating());
        if(third != 0) {
            return third;
        }
       //if identical, compare by shared
       //returns negative if a is false and b is true
        return (int)Boolean.compare(meme.getShared(), this.getShared());

    }

    /**
     * Main Method Testing
     */
    public static void main(String[] args) {
        // new BackgroundImage, User, and Ratings objects to use in testing
        BackgroundImage aBI = new BackgroundImage("image1.png", "Balloon", "The balloon is blue");
        User aU = new User();
        BackgroundImage bBI = new BackgroundImage("image2.png", "Flower", "The flower is red");
        User bU = new User();
        BackgroundImage dBI = new BackgroundImage("image3.png", "Book", "The book is green");
        User dU = new User();

        // new ratings objects to use for testing
        Rating r1 = new Rating(aU, 1);
        Rating r2 = new Rating(aU, 1);
        Rating r3 = new Rating(aU, 1);
        Rating r4 = new Rating(bU, 1);
        Rating r5 = new Rating(bU, 1);
        Rating r6 = new Rating(bU, -1);
        Rating r7 = new Rating(aU, -1);
        Rating r8 = new Rating(aU, -1);
        Rating r9 = new Rating(aU, -2); // not valid rating, should be set to 0
        Rating r10 = new Rating(aU, 0);

        // ratings array with default values
        Rating[] rTest1 = { new Rating(), new Rating(), new Rating(), new Rating(), new Rating(), new Rating(), new Rating(),
                new Rating(), new Rating(), new Rating() }; //overall rating is 0

        // ratings array with testing values
        Rating[] rTest2 = { r1, r2, r3, r4, r5, r6, r7, r8, r9, r10 }; //overall rating is 2

        // ratings array that isn't full
        Rating[] rTest3 = { r1, r2, r3, r4, r5, null }; //overall rating is 5

        // test new constructor
        Meme aM = new Meme(aBI, "balloons are cool", aU); // default empty ratings array
        Meme bM = new Meme(bBI, "flowers are pretty", bU); // testing new ratings array w/ full ratings array
        Meme dM = new Meme(dBI, "books are wordy", dU); // testing new ratings array w/ not full ratings array
        aM.setRatings(rTest1);
        bM.setRatings(rTest2);
        dM.setRatings(rTest3);
        
        //test compareTo() method
        Meme eM = new Meme(aBI, "balloons are cool", aU); // default empty ratings array
        Meme fM = new Meme(bBI, "flowers are pretty", bU); // testing new ratings array w/ full ratings array
        Meme gM = new Meme(bBI, "flowers are pretty", bU); // testing new ratings array w/ not full ratings array
       
        eM.setRatings(rTest1); //overall rating is 0
        fM.setRatings(rTest2); //overall rating is 2
        gM.setRatings(rTest2);
        fM.setShared(false);
        gM.setShared(true);
        
        System.out.println("compareTo()----------------------------");
        
        //test different captions
        System.out.println(eM.compareTo(fM)); //should return negative
        //test different shared, fM is not shared and gM is
        System.out.println(fM.compareTo(gM)); //should return positive
        
        System.out.println("CompareMemeByRating()----------------------------");
        
        //test CompareMemeByRating
               //test different rating
        CompareMemeByRating memeRating = new CompareMemeByRating();
        System.out.println(memeRating.compare(eM, fM)); //eM has higher ratings than fM, should return positive
               //test different caption, has same rating
        gM.setCaption("animals rock");
        System.out.println(memeRating.compare(fM, gM)); //gM be higher than fM, should return positive
        
        System.out.println("CompareMemeByCreator()----------------------------");
       
        //test CompareMemeByCreator
        aU.setUserName("betty");
        bU.setUserName("stacy");
            //test different creators
        CompareMemeByCreator memeCreator = new CompareMemeByCreator();
        System.out.println(memeCreator.compare(eM, fM)); //eM higher than fM, should return negative
            //test different ratings, same creator
        gM.setCreator(aU); //eM and gM have same creator
        System.out.println(memeCreator.compare(eM, gM)); //gM has higher ratings than eM, should return positive
     
        
        // test toString() method
        System.out.println("toString()----------------------------");

        System.out.println(aM.toString()); // should print Balloon <The balloon is blue> 'balloons are cool' 0.0 [+1: 0,-1: 0]"
        System.out.println(bM.toString()); // should print "Flower <The flower is red> 'flowers are pretty' 2.0 [+1: 5,-1: 3]"

        // test equals() method
        System.out.println("equals()----------------------------");

        Meme cM = new Meme();
        cM = aM; // new Meme equal to aM
        System.out.println(aM.equals(cM)); // should return true
        System.out.println(bM.equals(aM)); // should return false

        // test calculateOverallRating()
        System.out.println("calculateOverallRating()----------------------------");

        System.out.println(bM.calculateOverallRating()); // should return 2.0
        System.out.println(dM.calculateOverallRating()); // should return 5.0

        // test addRating()
        System.out.println("addRating()----------------------------");

        // 1.if there is space in ratings array
        System.out.println(dM.toString()); // should return "...5.0 [+1: 5, -1: 0]"
        System.out.println(dM.addRating(r6)); // add a rating with -1, return true
        System.out.println(dM.toString());// should return "...4.0 [+1: 5, -1: 1]"

        // 2.if there is no space in ratings array, should shift array 1 and add new rating
        Rating r11 = new Rating(bU, -1);
        System.out.println(bM.toString()); // should return "...2.0 [+1: 5, -1: 3]"
        System.out.println(bM.addRating(r11)); // add a rating with -1, return true
        // after shifting, deletes first rating(r1) w/ score 1 and adds rating(r11) with score -1
        System.out.println(bM.toString()); // should return "0.0 [+1: 4, -1: 4]"

        // test setCaptionVerticalAlign()
        System.out.println("setCaptionVerticalAlign()----------------------------");

        // 1. valid option
        System.out.println(aM.getCaptionVerticalAlign()); // should return bottom as default
        System.out.println(aM.setCaptionVerticalAlign("top")); // should return true
        System.out.println(aM.getCaptionVerticalAlign()); // should return top

        // 2.invalid option
        System.out.println(aM.setCaptionVerticalAlign("side")); // should return false
        System.out.println(aM.getCaptionVerticalAlign()); // should return top b/c there is no change since above parameter is
                                                          // invalid
        

    }
}
