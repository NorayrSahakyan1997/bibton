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

public class BalanceHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<WalletCurrencyResponse> walletCurrencyResponses;
    private View mainView;
    private final BalanceHomeAdapter.OnItemClickListener mListener;


    public BalanceHomeAdapter(Context context, List<WalletCurrencyResponse> walletCurrencyResponses, BalanceHomeAdapter.OnItemClickListener mListener) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.walletCurrencyResponses=walletCurrencyResponses;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mainView = layoutInflater.inflate(R.layout.recycler_balance_row, parent, false);
        RecyclerView.ViewHolder viewHolder = new BalanceHomeAdapter.ViewHolder(mainView);

        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BalanceHomeAdapter.ViewHolder viewHolder = (BalanceHomeAdapter.ViewHolder) holder;
        viewHolder.country_name.setText(walletCurrencyResponses.get(position).getCurrency_name());
        viewHolder.county_money_short_name.setText(walletCurrencyResponses.get(position).getCurrency_iso());
        viewHolder.currency_amount.setText(walletCurrencyResponses.get(position).getSymbol() + walletCurrencyResponses.get(position).getBalance());
        Picasso.get()
                .load(walletCurrencyResponses.get(position).getCurrency_icon())
                .into(viewHolder.country_flags);
        viewHolder.onClick(holder.itemView, position);

    }

    @Override
    public int getItemCount() {
        return walletCurrencyResponses.size();
    }

    public interface OnItemClickListener {
        void onClick(final int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView county_money_short_name;
        ImageView country_flags;
        TextView country_name;
        TextView currency_amount;

        ViewHolder(View itemView) {
            super(itemView);
            county_money_short_name = itemView.findViewById(R.id.county_money_short_name);
            country_flags = itemView.findViewById(R.id.country_flag_balance);
            country_name = itemView.findViewById(R.id.country_name_balance_list);
            currency_amount = itemView.findViewById(R.id.currency_amount);
        }

        void onClick(final View itemView, final int position) {
            itemView.setOnClickListener(v -> mListener.onClick(position));
        }


    }
}
