package am.bibton.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import am.bibton.R;
import am.bibton.model.currencyModel.CurrencyResponse;
import am.bibton.shared.utils.Constants;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AddCurrencyPairAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<CurrencyResponse> currencyResponse;
    private LayoutInflater layoutInflater;
    private View mainView;
    private final AddCurrencyPairAdapter.OnItemClickListener mListener;
    private List<Integer> fromList;


    public AddCurrencyPairAdapter(Context context, List<CurrencyResponse> currencyResponse, List<Integer> fromList, AddCurrencyPairAdapter.OnItemClickListener mListener) {
        this.context = context;
        this.currencyResponse = currencyResponse;
        layoutInflater = LayoutInflater.from(context);
        this.fromList = fromList;
        this.mListener = mListener;
        fillExistedListFromIds();
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
        viewHolder.countryName.setText(currencyResponse.get(position).getName());
        viewHolder.textIso.setText(currencyResponse.get(position).getIso() + "");
        Picasso.get()
                .load(currencyResponse.get(position).getFlag())
                .into(viewHolder.countryIcon);
        viewHolder.onClick(holder.itemView, position);
        unableExistedItems(viewHolder, position);
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
            textIso = itemView.findViewById(R.id.country_code);
            countryName = itemView.findViewById(R.id.country_names);
            countryIcon = itemView.findViewById(R.id.country_flags);
            textIso.setTextColor(context.getResources().getColor(R.color.grey));
            countryName.setTextColor(context.getResources().getColor(R.color.colorBlack));
        }

        void onClick(final View itemView, final int position) {
            itemView.setOnClickListener(v -> {
                        mListener.onClick(currencyResponse.get(position).getCurrency_id());
                        if (Constants.CURRENCY_SUM == 0) {
                            Constants.CURRENCY_ID_FIRST = currencyResponse.get(position).getCurrency_id();
                        }
                    }
            );
        }
    }

    private void fillExistedListFromIds() {
        fromList = new ArrayList<>();
        for (int i = 0; i < Constants.RATELIST.size(); i++) {
            if (Constants.CURRENCY_ID_FIRST == Constants.RATELIST.get(i).getFrom_id()) {
                fromList.add(Constants.RATELIST.get(i).getTo_id());
            }
        }
    }

    private void unableExistedItems(ViewHolder viewHolder, int position) {
        if (Constants.CURRENCY_SUM == 1) {
            if (Constants.CURRENCY_ID_FIRST == currencyResponse.get(position).getCurrency_id()) {
                viewHolder.itemView.setEnabled(false);
                viewHolder.itemView.setClickable(false);
                viewHolder.itemView.setBackgroundColor(context.getResources().getColor(R.color.unableRow));
            }
            for (int i = 0; i < fromList.size(); i++) {
                if (fromList.get(i) == currencyResponse.get(position).getCurrency_id()) {
                    viewHolder.itemView.setEnabled(false);
                    viewHolder.itemView.setClickable(false);
                    viewHolder.itemView.setBackgroundColor(context.getResources().getColor(R.color.unableRow));
                }
            }

        }
    }


}
