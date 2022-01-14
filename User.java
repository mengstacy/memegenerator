/**
 * March 19, 2021 
 * Stacy Meng 
 * swm8xb 
 * Homework 5
 */


import java.util.ArrayList;
import java.util.TreeSet;

public class User {
    /*
     * String for the username of user
     */
    private String userName;
    /*
     * ArrayList of all the memes created by the user
     */
    private ArrayList<Meme> memesCreated;
    /*
     * ArrayList of all the memes viewed by the user
     */
    private TreeSet<Meme> memesViewed;
    
    /*
     * Basic constructor with no parameters and empty string username
     */
    public User() {
        this.userName = "";
        this.memesCreated = new ArrayList<>();
        this.memesViewed = new TreeSet<>();
    }

    /*
     * Overloaded constructor that accepts String that will be the userName
     */
    public User(String name) {
        this.userName = name;
        this.memesCreated = new ArrayList<>();
        this.memesViewed = new TreeSet<>();
    }

    /**
     * @return String of the user's username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param String username to be part of User
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    /**
     * @return ArrayList of the memes created by the user
     */

    public ArrayList<Meme> getMemesCreated() {
        return memesCreated;
    }

    /**
     * @param ArrayList of the memes created by the user
     */
    public void setMemesCreated(ArrayList<Meme> memesCreated) {
        this.memesCreated = memesCreated;
    }
    
    /**
     * @return ArrayList of the memes viewed by the user
     */
    public ArrayList<Meme> getMemesViewed() {
        ArrayList<Meme> newMemesViewed = new ArrayList<Meme>(memesViewed);
        return newMemesViewed;
    }

    /**
     * @param ArrayList of the memes viewed by the user
     */
    public void setMemesViewed(ArrayList<Meme> memesViewed) {
        this.memesViewed = new TreeSet<Meme>(memesViewed);
        
    }

    /**
     * Adds a Meme to the User's memesViewed array and gives the Meme object passed in a rating
     * @param Meme meme
     * @param int rating for Rating object
     */
    public void rateMeme(Meme meme, int rating) {
        // add meme to the memesViewed array
        this.memesViewed.add(meme);
        // create new Rating object and assign it the passed in user and score
        Rating rate = new Rating(this, rating);
        meme.addRating(rate);

    }

    /**
     * Creates a new Meme from the image and caption passed in and adds it to the user's memesCreated
     * @param BackgroundImage image
     * @param String caption
     * @return the new Meme object created
     */
    public Meme createMeme(BackgroundImage image, String caption) {
        //Create a new meme with the parameters
        Meme newMeme = new Meme(image, caption, this);
        //add the meme to memesCreated array
        memesCreated.add(newMeme);
        return newMeme;

    }
    
    /**
     * Takes specified Meme out of user's memesCreated
     * @param Meme meme to be added
     * @return boolean for if meme was deleted or not
     */
    public boolean deleteMeme(Meme meme) {
        //check if memesCreated has the meme in the parameter and the meme has not been shared yet
        if (memesCreated.contains(meme) && !(meme.getShared())) {
            memesCreated.remove(meme); 
            return true; //return true if removed successfully
        } else {
            return false; //return false if meme is not removed, could be if the meme has been shared already
        }
    }

    /**
     * Adds a meme to the feed after setting the shared boolean to true 
     * @param Meme meme to be shared
     * @param Feed feed the meme is being added to 
     */
    public void shareMeme(Meme meme, Feed feed) {
        meme.setShared(true); //set the shared boolean to true
        feed.getMemes().add(meme); //add the meme to feed

    }
    
    /**
     * Gets the next meme in the feed and gives it the user's score for the meme and adds it to user's memes viewed array 
     * @param Feed feed where the meme is taken from
     * @param int ratingScore for the user's rating of the next meme
     * @return boolean if the meme was rated or not
     */
    public boolean rateNextMemeFromFeed(Feed feed, int ratingScore) {
        //get new meme from feed
        Meme nextMeme = feed.getNewMeme(this);
        //return false if meme is null
        if (nextMeme == null) {
            return false;
        } else {
            this.rateMeme(nextMeme, ratingScore); //rate the meme with the given ratingScore 
            return true;
        }

    }

    /**
     * Calculates the average ratings of all the memes for a User's memes created array
     * @return average of user's memes scores
     */
    public double calculateReputation() {
        double sum = 0;
        //if memesCreated is empty, return 0.0
        if (this.memesCreated.size() == 0) {
            return 0.0;
        }
        //add the overall ratings of all the memes in memesCreated
        for (int i = 0; i < this.memesCreated.size(); i++) {
            sum += this.memesCreated.get(i).calculateOverallRating();
        }
        //get the average of the sum of the overall ratings in memesCreated
        double avg = sum/(this.memesCreated.size());
        return avg;

    }
    /**
     * Returns String of the User's username, number of memes they have rated, and the reputation of their memes
     */

    @Override
    public String toString() {
        return userName + " has rated (" + memesViewed.size() + ") memes, (" + calculateReputation() + ")";
    }

    /**
     * Checks if two User objects are equal or not
     */
    @Override
    public boolean equals(Object object) {
        // return false if parameter object is null
        if (object == null) {
            return false;
        }
        if (!(object instanceof User)) {
            return false; // return false is parameter object is not of type User
        }

        User other = (User) object;
        // return true if usernames are same
        if (this.userName.equals(other.userName)) {
            return true;
        } else {
            return false; // return false if conditions are not met
        }
    }
    
    public int compareTo(User user) {
        //first compare by username ascending
        int first = this.userName.compareTo(user.userName);
        if(first != 0) {
            return first;
        }
        //if identical, compare by number of memes created descending
        int second = user.memesCreated.size() - this.memesCreated.size();
        return second;

    }
    
    public static void main(String[] args) {

        // new BackgroundImage, User, and Ratings objects to use in testing
        BackgroundImage aBI = new BackgroundImage("image1.png", "Balloon", "The balloon is blue");
        BackgroundImage bBI = new BackgroundImage("image2.png", "Flower", "The flower is red");
        BackgroundImage dBI = new BackgroundImage("image3.png", "Book", "The book is green");
        User stacy = new User("stacy");
        User morgan = new User("morgan");
        User nikki = new User("nikki");
        User bethany = new User("bethany");

        // new ratings objects to use for ratings array in Memes
        Rating r1 = new Rating(stacy, 1);
        Rating r2 = new Rating(stacy, -1);
        
        Rating r3 = new Rating(nikki, 1);
        Rating r4 = new Rating(nikki, 0);
        
        Rating r5 = new Rating(morgan, 1);
        Rating r6 = new Rating(morgan, 1);
        
        Rating r7 = new Rating(bethany, 0);
        Rating r8 = new Rating(bethany, -1);

        // ratings array with default values to be used in Meme objects
        Rating[] stacyR= {r3,r6,r8};
        Rating[] morganR = {r7,r3,r2};
        Rating[] nikkiR = {r5,r1,r8};

        // create new Meme objects
        Meme sM = new Meme(aBI, "balloons are cool", stacy); 
        Meme sM2 = new Meme(aBI, "balloons are awesome", stacy); 
        Meme sM3 = new Meme(aBI, "balloons are fun", stacy); 
        Meme mM = new Meme(bBI, "flowers are pretty", morgan);
        Meme nM = new Meme(dBI, "books are wordy", nikki); 
        Meme bM = new Meme(dBI, "i like harry potter", bethany); 
        
        sM.setRatings(stacyR); // sM now has ratings 1,1,-1
        mM.setRatings(morganR); 
        nM.setRatings(nikkiR); // nM has ratings -1,1,1
        
        
        //test Feed toString(), test Meme toString() 
        ArrayList<Meme> theMemes = new ArrayList<>();
        //add Meme objects to arraylist of Memes
        theMemes.add(sM);
        theMemes.add(sM2);
        theMemes.add(sM3);
        theMemes.add(mM);
        theMemes.add(nM);
        theMemes.add(bM);
       
        //OrderableFeed testing
        Feed a = new Feed(); // new Feed object
        a.setMemes(theMemes); //add ArrayList of memes created to Feed
        Feed b = new Feed(); //new Feed object with nothing in it
   
        
       //test OrderableFeed sortByCaption()
        OrderableFeed orderA = new OrderableFeed(a.getMemes());
            //test with full feed
        orderA.sortByCaption();
        System.out.println(orderA); 
            //test with null feed
        OrderableFeed orderB = new OrderableFeed(b.getMemes());
        orderB.sortByCaption();
        System.out.println(orderB); //should print nothing
        
       //test OrderableFeed sortByRating()
            //test with full feed
        orderA.sortByRating();
        System.out.println(orderA); 
            //test with null feed
        orderB.sortByRating();
        System.out.println(orderB); //should print nothing
        
        
       //test OrderableFeed sortByCreator()
            //test with full feed
        orderA.sortByCreator();
        System.out.println(orderA); 
        
            //test with null feed
        orderB.sortByCreator();
        System.out.println(orderB); //should print nothing
        
        System.out.println("-----------------------------------------------");
        //test OrderableFeed getNewMeme
        System.out.println(orderA.getNewMeme(stacy));
        System.out.println(orderA.getNewMeme(morgan));
        
        System.out.println("-----------------------------------------------");
        
        System.out.println(a.toString());
        /* Should print: (uses Meme toString() in each line)
         * Balloon <The balloon is blue> 'balloons are cool' -1.0 [+1: 1, -1: 2] - created by stacy
            Balloon <The balloon is blue> 'balloons are awesome' 0.0 [+1: 0, -1: 0] - created by stacy
            Balloon <The balloon is blue> 'balloons are fun' 0.0 [+1: 0, -1: 0] - created by stacy
            Flower <The flower is red> 'flowers are pretty' 0.0 [+1: 1, -1: 1] - created by morgan
            Book <The book is green> 'books are wordy' 1.0 [+1: 2, -1: 1] - created by nikki
            Book <The book is green> 'i like harry potter' 0.0 [+1: 0, -1: 0] - created by bethany" */
        
        System.out.println(b.toString()); //should return ""
        
        
        //Rating toString()
        System.out.println(r1.toString()); //should print "stacy rated as an upvote"
        System.out.println(r4.toString()); //should print "nikki rated as a pass"
        System.out.println("\n");
        
        //test User .equals() method
        User copy = new User();
        copy = stacy;
        System.out.println(copy.equals(stacy)); //should print true
        System.out.println(stacy.equals(morgan)); //should print false
        System.out.println("\n");
        
        //test Feed getNewMeme()
        ArrayList<Meme> stacysmemes = new ArrayList<>();
        stacysmemes.add(sM);
        stacysmemes.add(sM2);
        stacysmemes.add(sM3);
        
        //add new memes to user's memes created
        stacy.setMemesCreated(stacysmemes);
        System.out.println(a.getNewMeme(stacy)); // should print "Flower <The flower is red> 'flowers are pretty' 0.0 [+1: 1, -1: 1] - created by morgan"
        System.out.println(b.getNewMeme(stacy)); // should return null
        System.out.println("\n");
        
        //test User rateNextMemeFromFeed()
        System.out.println(stacy.rateNextMemeFromFeed(a, 1)); // should return true
        System.out.println(stacy.rateNextMemeFromFeed(b,-1)); // should return false
        System.out.println("\n");
        
        //test User createMeme()
        System.out.println(stacy.createMeme(aBI, "Balloons are squishy")); //should return "Balloon <The balloon is blue> 'Balloons are squishy' 0.0 [+1: 0, -1: 0] - created by stacy"
        System.out.println(stacy.getMemesCreated()); //should return "[Balloon <The balloon is blue> 'balloons are cool' -1.0 [+1: 1, -1: 2] - created by stacy, Balloon <The balloon is blue> 'balloons are awesome' 0.0 [+1: 0, -1: 0] - created by stacy, Balloon <The balloon is blue> 'balloons are fun' 0.0 [+1: 0, -1: 0] - created by stacy, Balloon <The balloon is blue> 'Balloons are squishy' 0.0 [+1: 0, -1: 0] - created by stacy]"
        
        System.out.println(morgan.createMeme(bBI, "flowers are beautiful")); //should return "Flower <The flower is red> 'flowers are beautiful' 0.0 [+1: 0, -1: 0] - created by morgan"
        System.out.println(morgan.getMemesCreated()); // "[Flower <The flower is red> 'flowers are beautiful' 0.0 [+1: 0, -1: 0] - created by morgan]"
        System.out.println("\n");
        
        //test User deleteMeme()
        System.out.println(stacy.deleteMeme(sM)); //should return true
        System.out.println(stacy.getMemesCreated()); // should return "[Balloon <The balloon is blue> 'balloons are awesome' 0.0 [+1: 0, -1: 0] - created by stacy, Balloon <The balloon is blue> 'balloons are fun' 0.0 [+1: 0, -1: 0] - created by stacy, Balloon <The balloon is blue> 'Balloons are squishy' 0.0 [+1: 0, -1: 0] - created by stacy]"
        
        System.out.println(morgan.deleteMeme(nM)); //should return false
        System.out.println("\n");
        
        //test User shareMeme()
        Meme nM2 = new Meme(dBI, "books are better than magazines", nikki); 
        stacy.shareMeme(nM2,a);
        System.out.println(nM2.getShared()); //should return true
        System.out.println(a); //meme added to feed array
        /*Should print 
        Balloon <The balloon is blue> 'balloons are cool' -1.0 [+1: 1, -1: 2] - created by stacy
        Balloon <The balloon is blue> 'balloons are awesome' 0.0 [+1: 0, -1: 0] - created by stacy
        Balloon <The balloon is blue> 'balloons are fun' 0.0 [+1: 0, -1: 0] - created by stacy
        Flower <The flower is red> 'flowers are pretty' 1.0 [+1: 2, -1: 1] - created by morgan
        Book <The book is green> 'books are wordy' 1.0 [+1: 2, -1: 1] - created by nikki
        Book <The book is green> 'i like harry potter' 0.0 [+1: 0, -1: 0] - created by bethany
        Book <The book is green> 'books are better than magazines' 0.0 [+1: 0, -1: 0] - created by nikki*/
        
        morgan.shareMeme(nM2, b);
        System.out.println(nM2.getShared()); //should return true
        System.out.println(b); //Should print "Book <The book is green> 'books are better than magazines' 0.0 [+1: 0, -1: 0] - created by nikki"
        
        //test User rateMeme()
        stacy.rateMeme(nM, 1); //stacy gives nikki's meme score 1, nM now has ratings 1,1,1,-1
        morgan.rateMeme(sM, -1); //morgan gives stacy's meme score -1, sM now has ratings 1,1,-1,-1
        
        //test User toString(), test User calculateReputation()
        nikki.rateMeme(sM2, 1);
        morgan.rateMeme(sM2, 1);
        
        nikki.getMemesCreated().add(nM);
        
        System.out.println(stacy.toString()); //should return "stacy has rated (3) memes, (0.6666666666666666)"
        System.out.println(nikki.toString()); //should return "nikki has rated (1) memes, (1.0)"
        System.out.println("\n");
        
        //test compareTo() method
        
        //test different usernames
        System.out.println(stacy.compareTo(morgan)); //should return positive
        
        //test different sizes of memes created (stacy created 3 memes, morgan created 1)
        morgan.setUserName("stacy"); 
        System.out.println(stacy.compareTo(morgan)); //should return negative
        
        

    }
    
    
    
    
    
    

}
