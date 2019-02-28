package am.bibton.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;

import java.util.List;

import am.bibton.R;
import am.bibton.model.alertModel.AlertResponse;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AlertListAdapter extends RecyclerSwipeAdapter<RecyclerView.ViewHolder> {
    private Context context;
    private View mainView;
    private List<AlertResponse> alertResponses;
    private LayoutInflater layoutInflater;
    private final AlertListAdapter.OnItemClickListener mListener;

    public AlertListAdapter(Context context, List<AlertResponse> alertResponses, AlertListAdapter.OnItemClickListener mListener) {
        this.context = context;
        this.alertResponses = alertResponses;
        this.mListener = mListener;
        layoutInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mainView = layoutInflater.inflate(R.layout.alert_item_row, parent, false);
        RecyclerView.ViewHolder viewHolder = new AlertListAdapter.ViewHolder(mainView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String amount = Float.toString(alertResponses.get(position).getAmount());

        AlertListAdapter.ViewHolder viewHolder = (AlertListAdapter.ViewHolder) holder;
        viewHolder.fromIso.setText(alertResponses.get(position).getFrom_iso());
        viewHolder.onClick(viewHolder.delete, position);
        if (amount.length() > 15) {
            amount.substring(1, 15);
            viewHolder.amount.setText(amount);
        } else {
            viewHolder.amount.setText(amount);
        }
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return 0;
    }

    public interface OnItemClickListener {
        void onClick(final int position);
    }

    @Override
    public int getItemCount() {
        return alertResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView amount;
        private TextView fromIso;
        private SwipeLayout swipeLayout;
        private TextView delete;

        ViewHolder(View itemView) {
            super(itemView);
            fromIso = itemView.findViewById(R.id.from_iso_alert);
            amount = itemView.findViewById(R.id.amount_alert);
            swipeLayout = itemView.findViewById(R.id.swipe_layout_add_alert);
            delete=itemView.findViewById(R.id.delete_row_text_alert);
        }

        void onClick(final View itemView, final int position) {

            itemView.setOnClickListener(v -> {
                mListener.onClick(alertResponses.get(position).getId());
                mItemManger.removeShownLayouts(swipeLayout);
                alertResponses.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, alertResponses.size());
                mItemManger.closeAllItems();
                notifyDataSetChanged();
            });
        }
    }
}
