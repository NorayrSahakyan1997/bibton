package am.bibton.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import am.bibton.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PaymentsFragmentUserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater layoutInflater;
    private Context context;

    public PaymentsFragmentUserAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mainView = layoutInflater.inflate(R.layout.payments_item_row, parent, false);
        return new PaymentsFragmentUserAdapter.ViewHolder(mainView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PaymentsFragmentUserAdapter.ViewHolder viewHolder = (PaymentsFragmentUserAdapter.ViewHolder) holder;
        viewHolder.user_Name.setText(context.getResources().getString(R.string.userName));
    }


    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView user_Name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            user_Name = itemView.findViewById(R.id.user_name_payments);
        }
    }

}
