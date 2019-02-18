package am.spaysapps.bibton.adapters.transactionAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import am.spaysapps.bibton.R;
import am.spaysapps.bibton.model.getTransactionList.TransactionResponse;
import am.spaysapps.bibton.shared.utils.Constants;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TransactionListChildAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<TransactionResponse> transactionResponses;
    private View mainView;
    private final OnItemClickListener mListener;


    public TransactionListChildAdapter(Context context, List<TransactionResponse> transactionResponses, OnItemClickListener mListener) {
        this.context = context;
        this.transactionResponses = transactionResponses;
        this.mListener = mListener;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        @SuppressLint("InflateParams") View mainView = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_list_child_row, null);
        mainView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, context.getResources().getInteger(R.integer.transaction_child_item_height)));
        RecyclerView.ViewHolder viewHolder = new ViewHolder(mainView);

        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if (transactionResponses.get(position).getTransfer_type() == 1) {
            viewHolder.transaction_cost.setTextColor(context.getResources().getColor(R.color.pass_code_active));
            viewHolder.transaction_cost.setText("+" + Constants.SYMBOL + transactionResponses.get(position).getTotal_amount());
        } else {

            viewHolder.transaction_cost.setText(Constants.SYMBOL + transactionResponses.get(position).getTotal_amount());

        }
        viewHolder.transaction_name.setText(transactionResponses.get(position).getText());

        Picasso.get()
                .load(transactionResponses.get(position).getImage())
                .into(viewHolder.transaction_image);
        viewHolder.onClick(holder.itemView, position);


    }

    @Override
    public int getItemCount() {
        return transactionResponses.size();
    }

    public interface OnItemClickListener {
        void onClick(final int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView transaction_image;
        TextView transaction_name;
        TextView transaction_cost;

        ViewHolder(View itemView) {
            super(itemView);
            transaction_image = itemView.findViewById(R.id.transaction_item_image);
            transaction_name = itemView.findViewById(R.id.transaction_name);
            transaction_cost = itemView.findViewById(R.id.transaction_cost);
        }

        void onClick(final View itemView, final int position) {
            itemView.setOnClickListener(v -> mListener.onClick(position));
        }

    }
}
