package com.wallnit.wallnitlogin.WallnitImageFindExtension;

/**
 * Created by Saurabh Gaur on 12/5/2016.
 */
public class ImageExtensionGet {

    int length,i;
    String reverse = "",getExtension;
    public String getImageName(String imageName)
    {
        length = imageName.length();
        for(i = length-1; i>=0 ;i--)
        {
            reverse = reverse + imageName.charAt(i);
            switch (reverse)
            {
                case "gpj.":
                    getExtension="jpg";
                    break;
                case "fig.":
                    getExtension="gif";
                    break;
                case "gnp.":
                    getExtension="png";
                    break;
                case "gepj.":
                    getExtension="jpeg";
                    break;
            }
        }
        return getExtension;
    }
}
