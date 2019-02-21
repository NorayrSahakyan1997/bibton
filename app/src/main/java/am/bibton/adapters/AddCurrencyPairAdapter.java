package am.bibton.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import am.bibton.R;
import am.bibton.model.walletCurrency.WalletCurrencyResponse;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AddCurrencyPairAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<WalletCurrencyResponse> currencyResponse;
    private LayoutInflater layoutInflater;
    private View mainView;
    private final AddCurrencyPairAdapter.OnItemClickListener mListener;


    public AddCurrencyPairAdapter(Context context, List<WalletCurrencyResponse> currencyResponse,AddCurrencyPairAdapter.OnItemClickListener mListener) {
        this.context = context;
        this.currencyResponse = currencyResponse;
        layoutInflater = LayoutInflater.from(context);
        this.mListener=mListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mainView = layoutInflater.inflate(R.layout.search_view_child, parent, false);
        RecyclerView.ViewHolder viewHolder = new AddCurrencyPairAdapter.ViewHolder(mainView);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AddCurrencyPairAdapter.ViewHolder viewHolder = (AddCurrencyPairAdapter.ViewHolder) holder;
        viewHolder.countryName.setText(currencyResponse.get(position).getCurrency_name());
        viewHolder.textIso.setText(currencyResponse.get(position).getCurrency_iso()+ "");
        Picasso.get()
                .load(currencyResponse.get(position).getCurrency_icon())
                .into(viewHolder.countryIcon);
        viewHolder.onClick(holder.itemView, position);

    }
    public interface OnItemClickListener {
        void onClick(final int position);
    }

    @Override
    public int getItemCount() {
        return currencyResponse.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textIso;
        private TextView countryName;
        private ImageView countryIcon;


        ViewHolder(View itemView) {
            super(itemView);
            textIso=itemView.findViewById(R.id.country_code);
            countryName=itemView.findViewById(R.id.country_names);
            countryIcon=itemView.findViewById(R.id.country_flags);
            textIso.setTextColor(context.getResources().getColor(R.color.grey));
            countryName.setTextColor(context.getResources().getColor(R.color.colorBlack));

        }

        void onClick(final View itemView, final int position) {
            itemView.setOnClickListener(v -> mListener.onClick(currencyResponse.get(getAdapterPosition()).getCurrency_id()));

        }



    }

}
