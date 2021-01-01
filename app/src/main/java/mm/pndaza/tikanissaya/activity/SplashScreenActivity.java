package mm.pndaza.tikanissaya.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import mm.pndaza.tikanissaya.R;

public class SplashScreenActivity extends AppCompatActivity {

    private static final String DATABASE_NAME = "tika_nsy.db";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        File file = new File(getFilesDir() + "/databases/" + DATABASE_NAME );

        if (file.exists() == false) {
            new CopyDB().execute(new File[]{file});
        }
         else {
            Log.d("onCreate","database exist");
            startMainActivity();
        }

    }


    private void startMainActivity(){

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent intent = new Intent(SplashScreenActivity.this,MainActivity.class);
                SplashScreenActivity.this.startActivity(intent);
                SplashScreenActivity.this.finish();
            }
        },500);

    }


    public class CopyDB extends AsyncTask<File, Double, Void> {


        protected Void doInBackground(File... files) {

            File file = files[0];

            // check databases folder is exist and if not, make folder.
            if (file.getParentFile().exists() == false){
                file.getParentFile().mkdirs();
            }

            try {
                InputStream input = SplashScreenActivity.this.getAssets().open("databases/" + DATABASE_NAME);
                OutputStream output = new FileOutputStream(file);

                int bufferSize;
                final int size = input.available();
                long alreadyCopy = 0;

                byte[] buffer = new byte[1024];
                while ((bufferSize = input.read(buffer) ) > 0) {
                    alreadyCopy += bufferSize;
                    output.write(buffer);
                    publishProgress(1.0d * alreadyCopy / size );
                }
                input.close();
                output.close();

                Log.i("db copy", "success");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void result) {

            startMainActivity();
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }
    }

}
