package am.bibton.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import am.bibton.R;
import am.bibton.model.addAlertModel.AddAlertListResponse;
import am.bibton.model.currencyModel.CurrencyResponse;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AddAlertAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<CurrencyResponse> currencyResponses;
    private Context context;
    private AddAlertAdapter.OnItemClickListener mListener;

    public AddAlertAdapter(Context context, List<CurrencyResponse> currencyResponses, AddAlertAdapter.OnItemClickListener mListener) {
        this.currencyResponses = currencyResponses;
        this.context = context;
        this.mListener = mListener;
        layoutInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mainView = layoutInflater.inflate(R.layout.search_view_child, parent, false);
        RecyclerView.ViewHolder viewHolder = new AddAlertAdapter.ViewHolder(mainView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AddAlertAdapter.ViewHolder viewHolder = (AddAlertAdapter.ViewHolder) holder;
        viewHolder.countryName.setText(currencyResponses.get(position).getName());
        viewHolder.textIso.setText(currencyResponses.get(position).getIso() + "");
        viewHolder.onClick(viewHolder.itemView, position);
        Picasso.get()
                .load(currencyResponses.get(position).getFlag())
                .into(viewHolder.countryIcon);

    }

    public interface OnItemClickListener {
        void onClick(final int position);
    }

    @Override
    public int getItemCount() {
        return currencyResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textIso;
        private TextView countryName;
        private ImageView countryIcon;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textIso = itemView.findViewById(R.id.country_code);
            countryName = itemView.findViewById(R.id.country_names);
            countryIcon = itemView.findViewById(R.id.country_flags);
            countryName.setTextColor(context.getResources().getColor(R.color.colorBlack));
            textIso.setTextColor(context.getResources().getColor(R.color.colorBlack));

        }

        void onClick(final View itemView, final int position) {
            itemView.setOnClickListener(v -> {
                mListener.onClick(currencyResponses.get(position).getCurrency_id());
            });
        }
    }
}
