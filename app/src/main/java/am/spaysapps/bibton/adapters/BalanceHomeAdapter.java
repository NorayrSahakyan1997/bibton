package am.spaysapps.bibton.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import am.spaysapps.bibton.R;
import am.spaysapps.bibton.shared.utils.Constants;
import am.spaysapps.bibton.view.activities.welcomeActivity.welcomeFragments.phoneNumberFragment.PhoneNumberFragment;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class BalanceHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<String> country_names;
    private View mainView;


    public BalanceHomeAdapter(Context context, List<String> country_names) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.country_names = country_names;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mainView = layoutInflater.inflate(R.layout.recycler_balance_row, parent, false);
        RecyclerView.ViewHolder viewHolder = new BalanceHomeAdapter.ViewHolder(mainView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BalanceHomeAdapter.ViewHolder viewHolder = (BalanceHomeAdapter.ViewHolder) holder;
        viewHolder.country_name.setText(country_names.get(position));
        viewHolder.county_money_short_name.setText("AMD");
        viewHolder.country_flags.setBackground(context.getResources().getDrawable(R.drawable.armenian_flag));
    }

    @Override
    public int getItemCount() {
        return country_names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView county_money_short_name;
        ImageView country_flags;
        TextView country_name;

        ViewHolder(View itemView) {
            super(itemView);
            county_money_short_name = itemView.findViewById(R.id.county_money_short_name);
            country_flags = itemView.findViewById(R.id.country_flag_balance);
            country_name = itemView.findViewById(R.id.country_name_balance_list);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
