package am.bibton.view.activities.homeActivity.homeFragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import am.bibton.R;
import am.bibton.adapters.PaymentsFragmentUserAdapter;
import am.bibton.view.activities.BaseFragment;
import am.bibton.view.activities.homeActivity.BibtonToBibtonActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PaymentFragment extends BaseFragment {
    private View mainView;
    private Context context;
    private ConstraintLayout constraintBibtonToBibton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.payment_fragment_layout, container, false);
        init();
        goToBibtonToBibonActivity();
        return mainView;
    }

    private void init() {
        RecyclerView recyclerView = mainView.findViewById(R.id.recycler_payments);
        constraintBibtonToBibton = mainView.findViewById(R.id.constraintBibtonToBibton);
        PaymentsFragmentUserAdapter paymentsFragmentUserAdapter = new PaymentsFragmentUserAdapter(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(paymentsFragmentUserAdapter);
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);

    }

    private void goToBibtonToBibonActivity() {
        constraintBibtonToBibton.setOnClickListener(v -> {
            Intent bibtonToBibtonActivity = new Intent(context, BibtonToBibtonActivity.class);
            context.startActivity(bibtonToBibtonActivity);

        });
    }
}
