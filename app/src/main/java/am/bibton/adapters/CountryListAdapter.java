package am.bibton.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import am.bibton.R;
import am.bibton.model.countryModel.CountryModel;
import am.bibton.shared.utils.Constants;
import am.bibton.view.activities.welcomeActivity.welcomeFragments.phoneNumberFragment.PhoneNumberFragment;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class CountryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements RecyclerView.OnItemTouchListener {

    private List<CountryModel> countryArray;
    private LayoutInflater layoutInflater;
    private View view;
    private boolean state;
    private Context context;
    private CountryListAdapter.OnItemClickListener mListener;


    public CountryListAdapter(Context context, List<CountryModel> countryArray, boolean state, CountryListAdapter.OnItemClickListener mListener) {
        this.context = context;
        this.countryArray = countryArray;
        layoutInflater = LayoutInflater.from(context);
        this.state = state;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = layoutInflater.inflate(R.layout.search_view_child, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CountryListAdapter.ViewHolder viewHolder = (CountryListAdapter.ViewHolder) holder;
        viewHolder.country_names.setText(countryArray.get(position).getName());
        viewHolder.country_code.setText(countryArray.get(position).getPhone_code() + "");
        viewHolder.getCountryCode(holder.itemView,position);
        Picasso.get()
                .load(countryArray.get(position).getFlag())
                .into(viewHolder.country_flags);
    }

    public interface OnItemClickListener {
        void onClick(final String countryCode);
    }


    @Override
    public int getItemCount() {
        return countryArray.size();
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView country_names;
        ImageView country_flags;
        TextView country_code;

        ViewHolder(View itemView) {

            super(itemView);
            country_names = itemView.findViewById(R.id.country_names);
            country_flags = itemView.findViewById(R.id.country_flags);
            country_code = itemView.findViewById(R.id.country_code);
            if (state) {
                country_names.setTextColor(context.getResources().getColor(R.color.white));
                country_code.setTextColor(context.getResources().getColor(R.color.white));
            } else {
                country_names.setTextColor(context.getResources().getColor(R.color.colorBlack));
                country_code.setTextColor(context.getResources().getColor(R.color.colorBlack));
            }
        }

        void getCountryCode(final View itemView, final int position) {
            itemView.setOnClickListener(v -> {
                if (state) {
                    Fragment newFragment = new PhoneNumberFragment();
                    FragmentTransaction transaction = ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frameLayoutWelcome, newFragment);
                    Constants.COUNTRY_CODE = countryArray.get(getAdapterPosition()).getPhone_code();
                    Constants.COUNTRY_SHORT_NAME = countryArray.get(getAdapterPosition()).getShort_name();
                    transaction.commit();
                } else {
                    mListener.onClick(countryArray.get(position).getPhone_code());
                }
            });
        }


    }
}

