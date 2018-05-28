package com.wallnit.wallnitlogin.WallnitPicassoLibrary;

import android.content.Context;
import android.os.Environment;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.wallnit.wallnitlogin.R;

import java.io.File;

/**
 * Created by Saurabh Gaur on 1/11/2017.
 */
public class PicassoClient {

    public static void downloadImgae(Context c, String url, ImageView img)
    {
        if(url != null && url.length()>0)
        {
            Picasso.with(c).load(url).placeholder(R.drawable.placeholder_third).into(img);
        }else {
            Picasso.with(c).load(R.drawable.placeholder_third).into(img);
        }
    }
}
