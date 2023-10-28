package com.example.stayeasy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stayeasy.model.Hotel;

import java.util.ArrayList;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {
    private ArrayList<Hotel> hotels;
    private Context context;

    public HotelAdapter(Context context, ArrayList<Hotel> hotels) {
        this.context = context;
        this.hotels = hotels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Hotel hotel = hotels.get(position);

        holder.hotelName.setText(hotel.getName());
        holder.hotelDescription.setText(hotel.getDescription());
        holder.hotelImage.setImageResource(hotel.getImageResource());

        Button bookNowButton = holder.itemView.findViewById(R.id.bookNowButton); // Find the button within the item
        bookNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Personal_Info.class);

                intent.putExtra("hotelObject", hotel);

                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return hotels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView hotelImage;
        private TextView hotelName;
        private TextView hotelDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hotelImage = itemView.findViewById(R.id.hotelImage); // Replace with your ImageView's ID
            hotelName = itemView.findViewById(R.id.hotelName); // Replace with your TextView's ID
            hotelDescription = itemView.findViewById(R.id.hotelDescription); // Replace with your TextView's ID
        }
    }
}

