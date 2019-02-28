package am.bibton.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import java.util.List;
import am.bibton.R;
import am.bibton.model.rateModel.RateResponse;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RatesAdapter extends RecyclerSwipeAdapter<RecyclerView.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<RateResponse> rateResponseList;
    private final RatesAdapter.OnItemClickListener mListener;


    public RatesAdapter(Context context, List<RateResponse> rateParentModels, RatesAdapter.OnItemClickListener mListener) {
        Context context1 = context;
        this.rateResponseList = rateParentModels;
        this.mListener = mListener;
        layoutInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mainView = layoutInflater.inflate(R.layout.rates_item_row, parent, false);
        RecyclerView.ViewHolder viewHolder = new RatesAdapter.ViewHolder(mainView);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RatesAdapter.ViewHolder viewHolder = (RatesAdapter.ViewHolder) holder;
        viewHolder.fromIso.setText("1" + rateResponseList.get(position).getFrom_iso());
        viewHolder.fromName.setText(rateResponseList.get(position).getFrom_name());
        viewHolder.toRate.setText(rateResponseList.get(position).getTo_rate() + "");
        viewHolder.toName.setText(rateResponseList.get(position).getTo_name());
        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, viewHolder.swipeLayout.findViewById(R.id.bottom_wraper));
        viewHolder.onClick(viewHolder.delete, position);

    }

    @Override
    public int getItemCount() {
        mListener.onSize(rateResponseList.size());
        return rateResponseList.size();
    }

    public interface OnItemClickListener {
        void onClick(final int position);
        void onSize(final int size);
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView toRate;
        private TextView toName;
        private TextView fromName;
        private TextView fromIso;
        private SwipeLayout swipeLayout;
        private TextView delete;

        ViewHolder(View itemView) {
            super(itemView);
            fromIso = itemView.findViewById(R.id.from_iso);
            toName = itemView.findViewById(R.id.to_name);
            fromName = itemView.findViewById(R.id.from_name);
            toRate = itemView.findViewById(R.id.to_rate);
            swipeLayout = itemView.findViewById(R.id.swipe_layout_add_currency);
            delete = itemView.findViewById(R.id.delete_row_text);
        }

        void onClick(final View itemView, final int position) {
            itemView.setOnClickListener(v -> {
                mListener.onClick(rateResponseList.get(position).getPair_id());
                mItemManger.removeShownLayouts(swipeLayout);
                rateResponseList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, rateResponseList.size());
                mItemManger.closeAllItems();
                notifyDataSetChanged();
            });


        }


    }

}
