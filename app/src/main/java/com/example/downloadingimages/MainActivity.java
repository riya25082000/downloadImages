package com.example.downloadingimages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    public void downloadImage(View view){
        ImageDownloader task = new ImageDownloader();
        Bitmap image;
        try {
            image=task.execute("https://i.pinimg.com/originals/96/5b/2d/965b2d35b63518799e3ce843e54b7a30.jpg").get();
            imageView.setImageBitmap(image);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwig-9Wl1cXiAhWRj-YKHR68A-4QjRx6BAgBEAU&url=https%3A%2F%2Fwww.pinterest.com%2Fpin%2F434456695289502491%2F&psig=AOvVaw1XDIQtiJS_TPeKKsKGZ0od&ust=1559388451207717
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=(ImageView)findViewById(R.id.imageView2);

    }
    public class ImageDownloader extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url=new URL(urls[0]);
                HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream=connection.getInputStream();
                Bitmap myBitmap= BitmapFactory.decodeStream(inputStream);
                return myBitmap;



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
