import java.util.Collections;
import java.util.ArrayList;

public class OrderableFeed extends Feed{
    
    public OrderableFeed(ArrayList<Meme> meme) {
        super();
        super.setMemes(meme);
    }
    public OrderableFeed() {
        super();
    }
    public void sortByCaption() {
        Collections.sort(getMemes());
    }
    public void sortByRating() {
        Collections.sort(getMemes(), new CompareMemeByRating());
        
    }
    public void sortByCreator() {
        Collections.sort(getMemes(), new CompareMemeByCreator());
    }
    
}
