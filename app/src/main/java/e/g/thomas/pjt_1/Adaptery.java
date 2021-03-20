package e.g.thomas.pjt_1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.Model;

import java.io.File;
import java.util.List;

public class Adaptery extends RecyclerView.Adapter<Adaptery.MyViewHolder> {


    private Context mcContext;
    private List<FoodModelClass> mData;
    private FavDB favDB;

    public Adaptery(Context mcContext, List<FoodModelClass> mData) {
        this.mcContext = mcContext;
        this.mData = mData;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        favDB = new FavDB(mcContext);

        View v;
        LayoutInflater inflater = LayoutInflater.from((mcContext));
        v = inflater.inflate(R.layout.food_item , parent, false);


        return new MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {



        holder.id.setText(mData.get(position).getId());
        holder.name.setText(mData.get(position).getName());

        Glide.with(mcContext)
                .load(mData.get(position).getImg())
                .into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mcContext, FoodDetailsClass.class);
                intent.putExtra("name", mData.get(position).getName());

                intent.putExtra("image", mData.get(position).getImg());

                mcContext.startActivity(intent);



            }
        });


    }


    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id;
        TextView name;
        ImageView img;
        Button fabBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id_text);
            name = itemView.findViewById(R.id.nameInItemPage);
            img = itemView.findViewById(R.id.imageInItemPage);
            fabBtn = itemView.findViewById(R.id.favBtn);

            fabBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    FoodModelClass foodItem = mData.get(position);

                    if(foodItem.getFavStatus().equals("0")){
                        foodItem.setFavStatus("1");
                        favDB.insertIntoTheDatabase(foodItem.getName(), foodItem.getImg(), foodItem.getId(), foodItem.getFavStatus());
                        fabBtn.setBackgroundResource(R.drawable.fav_red);
                    }
                    else{
                        foodItem.setFavStatus("0");
                        favDB.remove_fav(foodItem.getId());
                        fabBtn.setBackgroundResource(R.drawable.fav_white);
                    }


                }
            });



        }



    }
}
