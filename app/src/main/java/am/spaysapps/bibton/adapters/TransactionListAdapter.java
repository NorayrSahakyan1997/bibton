package am.spaysapps.bibton.adapters;

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
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TransactionListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<TransactionResponse> transactionResponses;
    private View mainView;

    public TransactionListAdapter(Context context, List<TransactionResponse> transactionResponseList) {
        this.context = context;
        this.transactionResponses = transactionResponseList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mainView = layoutInflater.inflate(R.layout.transaction_list_row, parent, false);
        RecyclerView.ViewHolder viewHolder = new TransactionListAdapter.ViewHolder(mainView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TransactionListAdapter.ViewHolder viewHolder = (TransactionListAdapter.ViewHolder) holder;
        viewHolder.transaction_name.setText(transactionResponses.get(position).getText());
        viewHolder.transaction_cost.setText(transactionResponses.get(position).getTotal_amount() + "$");
        Picasso.get()
                .load(transactionResponses.get(position).getImage())
                .into(viewHolder.transaction_image);
    }

    @Override
    public int getItemCount() {
        return transactionResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView transaction_image;
        TextView transaction_name;
        TextView transaction_cost;

        ViewHolder(View itemView) {
            super(itemView);
            transaction_image = itemView.findViewById(R.id.transaction_item_image);
            transaction_name = itemView.findViewById(R.id.transaction_name);
            transaction_cost = itemView.findViewById(R.id.transaction_cost);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
