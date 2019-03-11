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
import am.bibton.model.currencyModel.CurrencyResponse;
import am.bibton.shared.utils.Constants;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AddConvertItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<CurrencyResponse> currencyResponse;
    private LayoutInflater layoutInflater;
    private final AddConvertItemAdapter.OnItemClickListener mListener;


    public AddConvertItemAdapter(Context context, List<CurrencyResponse> currencyResponse, AddConvertItemAdapter.OnItemClickListener mListener) {
        this.context = context;
        this.currencyResponse = currencyResponse;
        layoutInflater = LayoutInflater.from(context);
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mainView = layoutInflater.inflate(R.layout.search_view_child, parent, false);
        return new ViewHolder(mainView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AddConvertItemAdapter.ViewHolder viewHolder = (AddConvertItemAdapter.ViewHolder) holder;
        viewHolder.countryName.setText(currencyResponse.get(position).getName());
        viewHolder.textIso.setText(currencyResponse.get(position).getIso() + "");
        Picasso.get()
                .load(currencyResponse.get(position).getFlag())
                .into(viewHolder.countryIcon);
        viewHolder.onClick(holder.itemView, position);
        checkActivenessOfItems(viewHolder, position);

    }

    public interface OnItemClickListener{
        void onClick(final int position);

    }

    private void checkActivenessOfItems(ViewHolder viewHolder, int position) {
        if (Constants.FromCurrencyConvert != null) {
            for (int i = 0; i < Constants.FromCurrencyConvert.size(); i++) {
                if (Constants.FromCurrencyConvert.get(i) == currencyResponse.get(position).getCurrency_id()) {
                    viewHolder.itemView.setEnabled(false);
                    viewHolder.itemView.setClickable(false);
                    viewHolder.itemView.setBackgroundColor(context.getResources().getColor(R.color.unableRow));
                }
            }
        }
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
            itemView.setOnClickListener(v -> mListener.onClick(currencyResponse.get(position).getCurrency_id())
            );
        }


    }


}
