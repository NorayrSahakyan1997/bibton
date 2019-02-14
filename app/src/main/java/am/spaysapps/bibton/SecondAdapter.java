package am.spaysapps.bibton;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SecondAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    private LayoutInflater layoutInflater;
    private View mainView;
    private List<String> name = new ArrayList<>();
    private TextView text_recycler;

    public SecondAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        setList();
    }


    public void setList() {
        for (int i = 0; i < 3; i++) {
            name.add("adddd");
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mainView = layoutInflater.inflate(R.layout.recycler_item_2, parent, false);
        RecyclerView.ViewHolder viewHolder = new SecondAdapter.ViewHolder(mainView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SecondAdapter.ViewHolder viewHolder = (SecondAdapter.ViewHolder) holder;
        viewHolder.transaction_name.setText(name.get(position));
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView transaction_name;

        ViewHolder(View itemView) {
            super(itemView);
            transaction_name = itemView.findViewById(R.id.second_item_text);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
