package com.example.dogapp.viewmodel;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogapp.R;
import com.example.dogapp.model.DogBreed;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class DogsAdapter extends RecyclerView.Adapter<DogsAdapter.ViewHolder> {

    private List<DogBreed> dogBreedList;

    public DogsAdapter(List<DogBreed> dogBreedList) {
        this.dogBreedList = dogBreedList;
    }

    @NonNull
    @Override
    public DogsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dog_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogsAdapter.ViewHolder holder, int position) {
        DogBreed dogBreed = dogBreedList.get(position);
        if (dogBreed == null) {
            return;
        }
        holder.twDogName.setText(dogBreed.getName());
        holder.twOrigin.setText(dogBreed.getOrigin());
        holder.dtName.setText(dogBreed.getName());
        holder.dtOrigin.setText(dogBreed.getOrigin());
        holder.dtLifeSpan.setText(dogBreed.getLifeSpan());
        holder.dtTemp.setText(dogBreed.getTemperament());
        Picasso.get()
                .load(dogBreed.getUrl())
                .placeholder(R.drawable.img)
                .into(holder.imgOfDog);

    }

    @Override
    public int getItemCount() {
        if (dogBreedList == null)
            return 0;
        return dogBreedList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView twDogName, twOrigin, dtName, dtOrigin, dtLifeSpan, dtTemp;
        public ImageView imgOfDog;
        public LinearLayout about, detail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            about = itemView.findViewById(R.id.about);
            detail = itemView.findViewById(R.id.detail);
            twDogName = itemView.findViewById(R.id.tw_name_dog);
            twOrigin = itemView.findViewById(R.id.tw_origin);
            imgOfDog = itemView.findViewById(R.id.img_dog);

            dtName = itemView.findViewById(R.id.dt_name);
            dtOrigin = itemView.findViewById(R.id.dt_origin);
            dtLifeSpan = itemView.findViewById(R.id.dt_life_span);
            dtTemp = itemView.findViewById(R.id.dt_temp);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DogBreed dog = dogBreedList.get(getPosition());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("dogBreed", dog);
                    Navigation.findNavController(view).navigate(R.id.detailsFragment, bundle);
                }
            });

            itemView.setOnTouchListener(new OnSwipeTouchListener() {
                @Override
                public boolean onSwipeLeft() {
                    if (about.getVisibility() == View.GONE) {
                        about.setVisibility(View.VISIBLE);
                        detail.setVisibility(View.GONE);
                    } else {
                        about.setVisibility(View.GONE);
                        detail.setVisibility(View.VISIBLE);
                    }
                    return true;
                }

                @Override
                public boolean onSwipeRight() {
                    onSwipeLeft();
                    return true;
                }
            });
        }

//        public ImageView getImage() {
//            return imgOfDog;
//        }
    }

    class OnSwipeTouchListener implements View.OnTouchListener {

        private final GestureDetector gestureDetector = new GestureDetector(new GestureListener());

        public boolean onTouch(final View v, final MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }

        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;


            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                boolean result = false;
                try {
                    float diffY = e2.getY() - e1.getY();
                    float diffX = e2.getX() - e1.getX();
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                result = onSwipeRight();
                            } else {
                                result = onSwipeLeft();
                            }
                        }
                    } else {
                        if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffY > 0) {
                                result = onSwipeBottom();
                            } else {
                                result = onSwipeTop();
                            }
                        }
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return result;
            }
        }

        public boolean onSwipeRight() {
            return false;
        }

        public boolean onSwipeLeft() {
            return false;
        }

        public boolean onSwipeTop() {
            return false;
        }

        public boolean onSwipeBottom() {
            return false;
        }
    }

}
