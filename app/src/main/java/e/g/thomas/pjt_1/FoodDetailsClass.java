package e.g.thomas.pjt_1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class FoodDetailsClass extends AppCompatActivity {


    public
        String name, imageUrl;
        TextView nameInDetailsPage;
        ImageView imageInDetailsPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_details);

        nameInDetailsPage = (TextView) findViewById(R.id.nameInDetailsPage);
        imageInDetailsPage = (ImageView)findViewById(R.id.imageInDetailsPage);

        Intent intent = getIntent();

        if(getIntent().hasExtra("name")){



            nameInDetailsPage.setText(intent.getStringExtra("name"));
            String im = intent.getStringExtra("image");
            Glide.with(this)
                    .load(im)
                    .into(imageInDetailsPage);

        }

    }




}
