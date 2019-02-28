package am.bibton.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import am.bibton.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NavigationMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<String> menu_names;
    private int[] navigation_view_icons;

    public NavigationMenuAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        setMenuNames();
        setMenuIcons();
    }

    private void setMenuNames() {
        menu_names = new ArrayList<>();
        menu_names = Arrays.asList(context.getResources().getStringArray(R.array.menu_names_array));
    }

    private void setMenuIcons() {
        navigation_view_icons = new int[]{R.drawable.summery_icon, R.drawable.payment_icon, R.drawable.exchange_icon, R.drawable.statement_icon, R.drawable.settings_icon, R.drawable.help_icon};
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.menu_item_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nav_view_names;
        ImageView nav_view_images;
        ViewHolder(View itemView) {
            super(itemView);
            nav_view_names = itemView.findViewById(R.id.menu_item_text_view);
            nav_view_images = itemView.findViewById(R.id.menu_item_icon);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NavigationMenuAdapter.ViewHolder viewHolder = (NavigationMenuAdapter.ViewHolder) holder;
        viewHolder.nav_view_names.setText(menu_names.get(position));
        viewHolder.nav_view_images.setBackgroundResource(navigation_view_icons[position]);
    }

    @Override
    public int getItemCount() {
        return menu_names.size();
    }

}
