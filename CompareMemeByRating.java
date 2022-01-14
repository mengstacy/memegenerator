/**
 * March 19, 2021 
 * Stacy Meng 
 * swm8xb 
 * Homework 5
 */

import java.util.Comparator;

public class CompareMemeByRating implements Comparator<Meme>{
    public int compare(Meme a, Meme b){
        //sort overall rating descending
        int ratingComp = (int)(b.calculateOverallRating()-a.calculateOverallRating());
        if (ratingComp != 0) {
            return ratingComp;
        }
        
        //sort caption ascending
        int captionComp = a.getCaption().compareTo(b.getCaption());
        if (captionComp != 0) {
            return captionComp;
        }
        
        //sort BackgroundImage natural ordering
        int bIComp = a.getBackgroundImage().compareTo(b.getBackgroundImage());
        if (bIComp != 0) {
            return bIComp;
        }
        
        //sort creator natural ordering
        return a.getCreator().compareTo(b.getCreator());
        
    }
    
    
}
