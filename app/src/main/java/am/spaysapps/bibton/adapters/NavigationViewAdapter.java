package am.spaysapps.bibton.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import am.spaysapps.bibton.R;
import am.spaysapps.bibton.model.countryModel.CountryModel;
import am.spaysapps.bibton.shared.utils.Constants;
import am.spaysapps.bibton.view.fragments.phoneNumberFragment.PhoneNumberFragment;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class NavigationViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements RecyclerView.OnItemTouchListener {
    private Context context;
    private List<String> navigation_view_names;
    private LayoutInflater layoutInflater;
    private View view;
    private List<Drawable> navigation_view_icons;


    public NavigationViewAdapter(Context context, List<String> navigation_view_names,List<Drawable> navigation_view_icons) {
        this.context = context;
        this.navigation_view_names = navigation_view_names;
        this.navigation_view_icons=navigation_view_icons;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = layoutInflater.inflate(R.layout.menu_item_row, parent, false);
        RecyclerView.ViewHolder viewHolder = new NavigationViewAdapter.ViewHolder(view);

        return viewHolder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nav_view_names;
        ImageView nav_view_images;

        ViewHolder(View itemView) {

            super(itemView);
            itemView.setOnClickListener(this);
            nav_view_names = itemView.findViewById(R.id.menu_item_text_view);
            nav_view_images = itemView.findViewById(R.id.menu_item_icon);

        }

        @Override
        public void onClick(View v) {
//            Fragment newFragment = new PhoneNumberFragment();
//            FragmentTransaction transaction = ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction();
//            transaction.replace(R.id.frameLayoutWelcome, newFragment);
//
//            transaction.commit();
        }

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        NavigationViewAdapter.ViewHolder viewHolder = (NavigationViewAdapter.ViewHolder) holder;
        viewHolder.nav_view_names.setText(navigation_view_names.get(position));
        viewHolder.nav_view_images.setBackground(navigation_view_icons.get(position));
//        Picasso.get()
//                .load(countryArray.get(position).getFlag())
//                //.error(R.drawable.user_placeholder_error)
//                .into(viewHolder.country_flags);
    }

    @Override
    public int getItemCount() {
        return navigation_view_names.size();
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
