package am.bibton.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import am.bibton.R;
import am.bibton.model.bibtonToBibtonList.BibtonToBibtonListChild;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BibtonToBibtonListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<BibtonToBibtonListChild> bibtonToBibtonList;
    private BibtonToBibtonListAdapter.OnItemClickListener onItemClickListener;

    public BibtonToBibtonListAdapter(Context context, List<BibtonToBibtonListChild> bibtonToBibtonList, BibtonToBibtonListAdapter.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.bibtonToBibtonList = bibtonToBibtonList;
        this.onItemClickListener = onItemClickListener;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mainView = layoutInflater.inflate(R.layout.bibton_to_bibton_list_row, parent, false);
        return new BibtonToBibtonListAdapter.ViewHolder(mainView);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BibtonToBibtonListAdapter.ViewHolder viewHolder = (BibtonToBibtonListAdapter.ViewHolder) holder;
        if (bibtonToBibtonList.get(position).getName() != null || bibtonToBibtonList.get(position).getSurname() != null) {
            viewHolder.userName.setText(bibtonToBibtonList.get(position).getName().concat(" ").concat(bibtonToBibtonList.get(position).getSurname()));
        } else {
            viewHolder.userName.setText(bibtonToBibtonList.get(position).getPhone_number());
        }
        viewHolder.userId.setText("ID :"+bibtonToBibtonList.get(position).getWallet_id());
        viewHolder.onClick(holder.itemView,position);

    }

    public interface OnItemClickListener {
        void onClick(final String name, String surname, int uniqueId, String phoneNumber);
    }

    @Override
    public int getItemCount() {
        return bibtonToBibtonList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView userName;
        private TextView userId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userId = itemView.findViewById(R.id.userIDTextViewBibtonToBibtonList);
            userName = itemView.findViewById(R.id.userNameTextBibtonToBibtonList);
        }

        void onClick(final View itemView, final int position) {
            itemView.setOnClickListener(v -> {
                onItemClickListener.onClick(bibtonToBibtonList.get(position).getName(),
                        bibtonToBibtonList.get(position).getSurname(),
                        bibtonToBibtonList.get(position).getWallet_id(),
                        bibtonToBibtonList.get(position).getPhone_number());

            });
        }


    }

}
