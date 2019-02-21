package am.bibton.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.L;

import java.util.List;

import am.bibton.R;
import am.bibton.model.rateModel.RateParentModel;
import am.bibton.model.rateModel.RateResponse;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RatesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private View mainView;
    private List<RateResponse> rateResponseList;

    public RatesAdapter(Context context, List<RateResponse> rateParentModels) {
        this.context = context;
        this.rateResponseList = rateParentModels;
        layoutInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mainView = layoutInflater.inflate(R.layout.rates_item_row, parent, false);
        RecyclerView.ViewHolder viewHolder = new RatesAdapter.ViewHolder(mainView);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RatesAdapter.ViewHolder viewHolder = (RatesAdapter.ViewHolder) holder;
        viewHolder.fromIso.setText("1"+rateResponseList.get(position).getFrom_iso());
        viewHolder.fromName.setText(rateResponseList.get(position).getFrom_name());
        viewHolder.toRate.setText(rateResponseList.get(position).getTo_rate() + "");
        viewHolder.toName.setText(rateResponseList.get(position).getTo_name());


    }

    @Override
    public int getItemCount() {
        return rateResponseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView toRate;
        private TextView toName;
        private TextView fromName;
        private TextView fromIso;


        ViewHolder(View itemView) {

            super(itemView);
            fromIso = itemView.findViewById(R.id.from_iso);
            toName = itemView.findViewById(R.id.to_name);
            fromName = itemView.findViewById(R.id.from_name);
            toRate = itemView.findViewById(R.id.to_rate);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

        }

    }

}
