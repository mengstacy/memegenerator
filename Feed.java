/*
 * March 19, 2021 Stacy Meng swm8xb Homework 4
 */

import java.util.ArrayList;

public class Feed {
    
    /*
     * ArrayList of memes
     */
    ArrayList<Meme> memes;
    
    /**
     * Default constructor that makes empty arraylist
     */
    public Feed() {
        memes = new ArrayList<>();     
    }
    
    /**
     * @return ArrayList of memes in feed
     */
    public ArrayList<Meme> getMemes() {
        return this.memes;
    }

    /**
     * @param ArrayList of memes to put in feed
     */
    public void setMemes(ArrayList<Meme> memes) {
        this.memes = memes;
    }

    /**
     * Gets a new meme that user has not seen or has not been created by the user from feed
     * @param User user to view meme
     * @return Meme meme from feed if not null
     */
    public Meme getNewMeme(User user) {
        //check if feed is not empty
        if (memes.size() == 0) {
            return null;
        }
        
        for (Meme feedMeme: this.memes) {
            //return meme if user has not seen the meme and is not the creator of the meme
            if(!(user.getMemesViewed().contains(feedMeme)) && !(user.getMemesCreated().contains(feedMeme))) {
                return feedMeme;
            } 
        }
        return null;
    }

    /**
     * Returns Meme toString() for all memes in feed, each on separate line 
     */
    @Override
    public String toString() {
        String newString = ""; //create new String 
        if (memes.size() == 0) { //if feed is empty, return ""
            return "";
        }
        for (Meme nextMeme: memes) { 
           newString += nextMeme.toString() + "\n"; //add toString() of each meme to the new String
        }
       return newString; //return the new String
    }

   
}
