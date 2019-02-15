package am.spaysapps.bibton.view.activities.homeActivity.homeFragments.statementFragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;



import java.util.List;

import javax.inject.Inject;

import am.spaysapps.bibton.Bibton;
import am.spaysapps.bibton.R;
import am.spaysapps.bibton.adapters.StatementFragmentAdapter;
import am.spaysapps.bibton.model.getTransactionList.TransactionFilterRequestModel;
import am.spaysapps.bibton.model.getTransactionList.TransactionResponse;
import am.spaysapps.bibton.presenter.StatementFragmentPresenter;
import am.spaysapps.bibton.shared.utils.Constants;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class StatementFragment extends Fragment implements IStatementFragment {
    private View mainView;
    private ConstraintLayout constraint_from;
    private ConstraintLayout calendarView;
    private CalendarView calendar;
    @Inject
    StatementFragmentPresenter mPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.statement_fragmen_layout, container, false);
        Bibton.getInstance().getAuthorizationComponent().inject(this);
        TransactionFilterRequestModel transactionFilterRequestModel = new TransactionFilterRequestModel();
        transactionFilterRequestModel.setStart_date("2019");
        mPresenter.onViewCreated(this);
        mPresenter.getTransactionList(transactionFilterRequestModel);
        init();
        openCaldendars();
        return mainView;
    }

    private void init() {
        RecyclerView recycler_view_statement_fragment = mainView.findViewById(R.id.recycler_view_statement_fragment);
        RecyclerView.LayoutManager recycler_view_manager = new LinearLayoutManager(getContext());
        recycler_view_statement_fragment.setLayoutManager(recycler_view_manager);
        StatementFragmentAdapter statementFragmentAdapter = new StatementFragmentAdapter(getContext(), Constants.TRANSACTION_LIST);
        recycler_view_statement_fragment.setAdapter(statementFragmentAdapter);
        constraint_from = mainView.findViewById(R.id.constraint_from);
        calendarView = mainView.findViewById(R.id.calendar_view_constraint);
        calendar = mainView.findViewById(R.id.calendar);
    }

    private void openCaldendars() {
        constraint_from.setOnClickListener(v -> {
            calendarView.setVisibility(View.VISIBLE);
            calendar.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
                calendarView.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), dayOfMonth + "", Toast.LENGTH_SHORT).show();
            });
        });
        calendarView.setOnClickListener(v -> calendarView.setVisibility(View.INVISIBLE));
    }

    @Override
    public void getFilteredTransactionList(List<TransactionResponse> transactionResponses) {

    }

    @Override
    public void onError(String errorMessage) {

    }

    @Override
    public void showServerError() {

    }

    @Override
    public void showNetworkError() {

    }
}
