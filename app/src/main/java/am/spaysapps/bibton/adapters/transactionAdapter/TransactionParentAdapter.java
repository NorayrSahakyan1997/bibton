package am.spaysapps.bibton.adapters.transactionAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import am.spaysapps.bibton.R;
import am.spaysapps.bibton.model.getTransactionList.TransactionParentModel;
import am.spaysapps.bibton.shared.utils.Constants;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TransactionParentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private View mainView;
    private RecyclerView recycler_item_2;
    private TransactionListAdapter transactionListAdapter;
    private List<TransactionParentModel> date;



    public TransactionParentAdapter(Context context,List<TransactionParentModel> date){
        this.context=context;
        this.date=date;
        layoutInflater=LayoutInflater.from(context);
        transactionListAdapter= new TransactionListAdapter(context);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mainView=layoutInflater.inflate(R.layout.transaction_list_parent_row,parent,false);

        RecyclerView.ViewHolder viewHolder= new TransactionParentAdapter.ViewHolder(mainView);
        recycler_item_2=mainView.findViewById(R.id.recycler_transaction_parent);
        recycler_item_2.setLayoutManager(new LinearLayoutManager(context));
        recycler_item_2.setAdapter(transactionListAdapter);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TransactionParentAdapter.ViewHolder viewHolder = (TransactionParentAdapter.ViewHolder) holder;
        viewHolder.textView_Parent_Date.setText(Constants.DATE.get(position));
    }

    @Override
    public int getItemCount() {
        return Constants.DATE.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_Parent_Date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_Parent_Date=(TextView)itemView.findViewById(R.id.text_date_transaction);
        }
    }
}
