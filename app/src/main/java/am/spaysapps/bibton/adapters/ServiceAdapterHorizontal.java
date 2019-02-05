package am.spaysapps.bibton.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.L;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import am.spaysapps.bibton.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ServiceAdapterHorizontal extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private View view;
    private LayoutInflater layoutInflater;
    private List<String> service_names;
    private int[] service_icons;

    public ServiceAdapterHorizontal(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        setMenuNames();
        setMenuIcons();
    }

    private void setMenuNames() {
        service_names = new ArrayList<>();
        service_names = Arrays.asList(context.getResources().getStringArray(R.array.service_names));

    }

    private void setMenuIcons() {
        service_icons = new int[]{R.drawable.nfc_pay_icon, R.drawable.qr_pay_icon, R.drawable.utility_icon, R.drawable.rates_icon, R.drawable.my_bibton_icon};
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = layoutInflater.inflate(R.layout.service_horizontal_item_row, parent, false);
        RecyclerView.ViewHolder viewHolder = new ServiceAdapterHorizontal.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ServiceAdapterHorizontal.ViewHolder viewHolder = (ServiceAdapterHorizontal.ViewHolder) holder;
        viewHolder.text_view_horizontal_item_row.setText(service_names.get(position));
        viewHolder.image_view_horizontal_item_row.setBackgroundResource(service_icons[position]);
    }

    @Override
    public int getItemCount() {
        return service_names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView text_view_horizontal_item_row;
        ImageView image_view_horizontal_item_row;

        ViewHolder(View itemView) {

            super(itemView);
            itemView.setOnClickListener(this);
            text_view_horizontal_item_row=itemView.findViewById(R.id.text_view_horizontal_item_row);
            image_view_horizontal_item_row=itemView.findViewById(R.id.image_view_horizontal_item_row);


        }

        @Override
        public void onClick(View v) {
//            Fragment newFragment = new PhoneNumberFragment();
//            FragmentTransaction transaction = ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction();
//            transaction.replace(R.id.frameLayoutWelcome, newFragment);
//
//            transaction.commit();
        }

    }

}
