import java.util.Comparator;

/**
 * March 19, 2021 
 * Stacy Meng 
 * swm8xb 
 * Homework 5
**/

public class CompareMemeByCreator implements Comparator<Meme>{
    public int compare(Meme a, Meme b){
        
        //sort by creator, natural 
        int creatorComp = a.getCreator().compareTo(b.getCreator());
        if (creatorComp != 0) {
            return creatorComp;
        }
        
        //sort by rating, descending
        int ratingComp = (int)(b.calculateOverallRating()-a.calculateOverallRating());
        if (ratingComp != 0) {
            return ratingComp;
        }
        
        //sort by caption, ascending
        int captionComp = a.getCaption().compareTo(b.getCaption());
        if (captionComp != 0) {
            return captionComp;
        }
        
        //sort by backgroundimage, natural
        int bIComp = a.getBackgroundImage().compareTo(b.getBackgroundImage());
        if (bIComp != 0) {
            return bIComp;
        }
        
        //sort by boolean
        return (int)Boolean.compare(b.getShared(), a.getShared());
        
    }
}
