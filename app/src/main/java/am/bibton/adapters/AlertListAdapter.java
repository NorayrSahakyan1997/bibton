package am.bibton.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;

import java.util.List;

import am.bibton.R;
import am.bibton.model.alertModel.AlertResponse;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AlertListAdapter extends RecyclerSwipeAdapter<RecyclerView.ViewHolder> {
    private List<AlertResponse> alertResponses;
    private LayoutInflater layoutInflater;
    private AlertListAdapter.OnItemClickListener mListener;

    public AlertListAdapter(Context context, List<AlertResponse> alertResponses, AlertListAdapter.OnItemClickListener mListener) {
        this.alertResponses = alertResponses;
        this.mListener = mListener;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mainView = layoutInflater.inflate(R.layout.alert_item_row, parent, false);
        return new ViewHolder(mainView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String amount = Float.toString(alertResponses.get(position).getAmount());
        AlertListAdapter.ViewHolder viewHolder = (AlertListAdapter.ViewHolder) holder;
        viewHolder.fromIso.setText(alertResponses.get(position).getFrom_iso());
        viewHolder.deleteItem(viewHolder.delete, position);
        viewHolder.switchItem(viewHolder.switcherAlert, position);
        //setSwitcherToAlert(viewHolder, alertResponses.get(position).getStatus());
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

        void setSwitcher(int Id);

        void getSize(final int size);

    }

    @Override
    public int getItemCount() {
        mListener.getSize(alertResponses.size());
        return alertResponses.size();

    }

    private void setSwitcherToAlert(ViewHolder viewHolder, int position) {
        if (alertResponses.get(position).getStatus() != 1) {
            viewHolder.switcherAlert.setChecked(false);
        } else {
            viewHolder.switcherAlert.setChecked(true);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView amount;
        private TextView fromIso;
        private SwipeLayout swipeLayout;
        private TextView delete;
        private Switch switcherAlert;

        ViewHolder(View itemView) {
            super(itemView);
            fromIso = itemView.findViewById(R.id.from_iso_alert);
            amount = itemView.findViewById(R.id.amount_alert);
            swipeLayout = itemView.findViewById(R.id.swipe_layout_add_alert);
            delete = itemView.findViewById(R.id.delete_row_text_alert);
            switcherAlert = itemView.findViewById(R.id.switcher_alert);
        }

        void deleteItem(final View itemView, final int position) {
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

        void switchItem(final Switch switcherAlert, final int position) {
            switcherAlert.setOnCheckedChangeListener((buttonView, isChecked) ->
                    mListener.setSwitcher(alertResponses.get(position).getId()));
        }
    }
}
