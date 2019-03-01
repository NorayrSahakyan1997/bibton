package am.bibton.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.squareup.picasso.Picasso;
import java.util.Collections;
import java.util.List;
import am.bibton.R;
import am.bibton.model.convertModel.ConvertResponse;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ConvertListAdapter extends RecyclerSwipeAdapter<RecyclerView.ViewHolder> {
    private List<ConvertResponse> convertResponse;
    private LayoutInflater layoutInflater;
    private final ConvertListAdapter.OnItemClickListener mListener;

    public ConvertListAdapter(Context context, List<ConvertResponse> convertResponse, ConvertListAdapter.OnItemClickListener mListener) {
        this.convertResponse = convertResponse;
        layoutInflater = LayoutInflater.from(context);
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mainView = layoutInflater.inflate(R.layout.convert_fragment_item_row, parent, false);
        return new ViewHolder(mainView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String convertRate;
        ConvertListAdapter.ViewHolder viewHolder = (ConvertListAdapter.ViewHolder) holder;
        ((ViewHolder) holder).countryName.setText(convertResponse.get(position).getTo_name());
        viewHolder.moneyShortName.setText(convertResponse.get(position).getTo_iso());
        convertRate = Float.toString(convertResponse.get(position).getRate());
        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, viewHolder.swipeLayout.findViewById(R.id.bottom_wraper_convert));
        if (convertRate.length() > 6) {
            convertRate.substring(1, 6);
            viewHolder.balanceAmount.setText(convertRate);
        } else {
            viewHolder.balanceAmount.setText(convertRate);
        }

        Picasso.get()
                .load(convertResponse.get(position).getTo_icon())
                .into(viewHolder.countryIcon);
        viewHolder.onClick(viewHolder.delete, position);
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return 0;
    }

    public interface OnItemClickListener {
        void onClick(final int position);
        void getAdapterSize(final int positon);
        void makeCurrencyMain(final int currencyIdn, final float amount);
    }

    @Override
    public int getItemCount() {
        mListener.getAdapterSize(convertResponse.size());
        return convertResponse.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView countryIcon;
        private TextView moneyShortName;
        private TextView countryName;
        private SwipeLayout swipeLayout;
        private TextView delete;
        private EditText balanceAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            countryIcon = itemView.findViewById(R.id.country_flag_convert_list);
            moneyShortName = itemView.findViewById(R.id.county_money_short_name_convert_list);
            countryName = itemView.findViewById(R.id.country_name_convert_list);
            swipeLayout = itemView.findViewById(R.id.swipe_layout_convert);
            delete = itemView.findViewById(R.id.delete_row_text_covert);
            balanceAmount=itemView.findViewById(R.id.balanceAmount);
        }

        void onClick(final View itemView, final int position) {
            itemView.setOnClickListener(v -> {
                mListener.onClick(convertResponse.get(position).getCompare_id());
                mItemManger.removeShownLayouts(swipeLayout);
                convertResponse.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, convertResponse.size());
                mItemManger.closeAllItems();
                notifyDataSetChanged();
            });
        }

        @Override
        public void onClick(View v) {
            Collections.swap(convertResponse, getAdapterPosition(), 0);
            notifyItemMoved(getAdapterPosition(), 0);
            mListener.makeCurrencyMain(convertResponse.get(getAdapterPosition()).getCurrency_id(), convertResponse.get(getAdapterPosition()).getRate());
        }

    }
}
