/**
 * March 19, 2021 
 * Stacy Meng 
 * swm8xb 
 * Homework 5
 */

import java.util.ArrayList;

public class BackgroundImage implements Comparable<BackgroundImage>{

    /**
     * String name of the image file
     */
    private String imageFileName;

    /**
     * String of the title of image
     */
    private String title;

    /**
     * String description of the image
     */
    private String description;

    /*
     * Basic constructor with no parameters and default image
     */
    public BackgroundImage() {
        this.imageFileName = "image.png";
        this.title = "The Title";
        this.description = "Description";
    }

    /*
     * Overloaded Constructor that takes in image file name, title, and description as parameters
     */

    public BackgroundImage(String imageFileName, String title, String description) {
        this.imageFileName = imageFileName;
        this.title = title;
        this.description = description;

    }

    /**
     * @return String of image file name
     */
    public String getImageFileName() {
        return imageFileName;
    }

    /**
     * @param String of image file name
     */
    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    /**
     * @return String title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param String of image title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return String of image description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param String description for image
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the title of the image and the description of the image in special formatting
     */
    @Override
    public String toString() {
        return title + " <" + this.description + ">";
    }

    /**
     * Checks to see if parameter is a BackgroundImage object and the title, description, and imageFileName match
     * 
     * @return boolean for if object passed in is equal to background image object or not
     */
    @Override
    public boolean equals(Object object) {
        if (object == null) { //return false if object is null
            return false;
        }

        if (!(object instanceof BackgroundImage)) {
            return false; //return false if object is not instance of BackgroundImage
        }

        BackgroundImage other = (BackgroundImage) object;
        //return true if  title, imageFileName, and description are the same
        if (title.equals(other.title) && imageFileName.equals(other.imageFileName) && description.equals(other.description)) {
            return true;
        } else {
            return false; // return false if none of conditions are met
        }

    }
    
    /**
     * Compares two background image objects 
     */
    public int compareTo(BackgroundImage image) {
        //first compare by filename ascending
        int first = imageFileName.compareTo(image.imageFileName);
        if(first != 0) {
            return first;
        }
        //if identical, compare by title ascending
        int second = title.compareTo(image.title);
        if(second != 0) {
            return second;
        }
        //if identical, compare by description ascending
        return description.compareTo(image.description);

    }

    /**
     * Main Method Testing
     */
    public static void main(String[] args) {
        //test default constructor
        BackgroundImage a = new BackgroundImage();
        //test new constructor that takes in title, description, and imageFileName
        BackgroundImage b = new BackgroundImage("image1.png", "Balloon", "The balloon is blue");
        BackgroundImage c = new BackgroundImage("image2.png", "Sky", "The sky is blue");
        BackgroundImage d = new BackgroundImage();
        d = b;
        
        //test toString() method
        System.out.println(a.toString()); // should print "The Title <Description>"
        System.out.println(b.toString()); // should print "Balloon <The balloon is blue>"
        System.out.println(c.toString()); // should print "Sky <The sky is blue>"
        
        //test equals() method
        System.out.println(b.equals(d)); // should print true
        System.out.println(a.equals(b)); // should print false
        
        //test compareTo() method
        BackgroundImage aBI = new BackgroundImage("image1.png", "Balloon", "The balloon is blue");
        BackgroundImage bBI = new BackgroundImage("image2.png", "Flower", "The flower is red");
        BackgroundImage dBI = new BackgroundImage("image2.png", "Flower", "The book is green");
        
        //compares w/ different image file names
        System.out.println(aBI.compareTo(bBI)); //should return negative
      //compares w/ different image file names
        System.out.println(bBI.compareTo(dBI)); //should return positive
        
        
    }

}
