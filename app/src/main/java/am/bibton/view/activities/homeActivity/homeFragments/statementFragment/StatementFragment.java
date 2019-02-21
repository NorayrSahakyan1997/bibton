package am.bibton.view.activities.homeActivity.homeFragments.statementFragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import javax.inject.Inject;
import am.bibton.Bibton;
import am.bibton.R;
import am.bibton.adapters.CurrencyAdapter;
import am.bibton.adapters.transactionAdapter.TransactionParentAdapter;
import am.bibton.model.getTransactionList.TransactionDateResponse;
import am.bibton.model.getTransactionList.TransactionFilterRequestModel;
import am.bibton.model.walletCurrency.WalletCurrencyResponse;
import am.bibton.presenter.StatementFragmentPresenter;
import am.bibton.shared.utils.Constants;
import am.bibton.view.activities.BaseFragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class StatementFragment extends BaseFragment implements IStatementFragment {
    private View mainView;
    private ConstraintLayout constraint_from;
    private ConstraintLayout calendarView;
    private CalendarView calendar;
    private ConstraintLayout constraint_to;
    private TextView text_data_from;
    private TextView text_data_to;
    private RecyclerView recycler_view_statement_fragment;
    private Context context;
    private ImageView statementFlagIcon;
    @Inject
    StatementFragmentPresenter mPresenter;
    private TransactionFilterRequestModel transactionFilterRequestModel;
    private ConstraintLayout constraintBalance;
    private TextView currencyName;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.statement_fragmen_layout, container, false);
        Bibton.getInstance().getAuthorizationComponent().inject(this);
        mPresenter.onViewCreated(this);
        transactionFilterRequestModel = new TransactionFilterRequestModel();
        mPresenter.getTransactionList(transactionFilterRequestModel);
        mPresenter.getTransactionList();
        mPresenter.getCurrencyList();
        init();
        handleClicksFromTO();
        openCurrencyConstraint();
        closeConstraintBalance();
        return mainView;
    }

    private void init() {
        recycler_view_statement_fragment = mainView.findViewById(R.id.recycler_view_statement_fragment);
        RecyclerView.LayoutManager recycler_view_manager = new LinearLayoutManager(getContext());
        recycler_view_statement_fragment.setLayoutManager(recycler_view_manager);
        constraint_from = mainView.findViewById(R.id.constraint_from);
        calendarView = mainView.findViewById(R.id.calendar_view_constraint);
        calendar = mainView.findViewById(R.id.calendar);
        constraint_to = mainView.findViewById(R.id.constraint_to);
        text_data_from = mainView.findViewById(R.id.text_view_data_from);
        text_data_to = mainView.findViewById(R.id.text_view_data_to);
        statementFlagIcon = mainView.findViewById(R.id.flag_image_statement);
        constraintBalance = mainView.findViewById(R.id.constraint_balance);
        currencyName = mainView.findViewById(R.id.currency_name);
    }

    private String starteDate;
    private String endDate;

    private void handleClicksFromTO() {
        calendar.setMaxDate(System.currentTimeMillis() - 5);

        constraint_from.setOnClickListener(v -> {
            calendarView.setVisibility(View.VISIBLE);

            calendar.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
                calendarView.setVisibility(View.GONE);
                starteDate = year + "-" + month + "-" + dayOfMonth;
                text_data_from.setText(starteDate);
                transactionFilterRequestModel.setStart_date(year + "-" + month + "-" + dayOfMonth);
                mPresenter.getTransactionList(transactionFilterRequestModel);

            });
        });
        constraint_to.setOnClickListener(v -> {
            calendarView.setVisibility(View.VISIBLE);
            calendar.setMaxDate(System.currentTimeMillis() + 100);

            calendar.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
                calendarView.setVisibility(View.GONE);
                endDate = year + "-" + month + "-" + dayOfMonth;
                text_data_to.setText(endDate);
                transactionFilterRequestModel.setEnd_date(year + "-" + month + "-" + dayOfMonth);
                mPresenter.getTransactionList(transactionFilterRequestModel);

            });
        });
        calendarView.setOnClickListener(v -> calendarView.setVisibility(View.INVISIBLE));

    }

    @Override
    public void getFilteredTransactionList(List<TransactionDateResponse> transactionResponses) {

    }

    private void openCurrencyConstraint() {
        currencyName.setOnClickListener(v -> constraintBalance.setVisibility(View.VISIBLE));
    }

    @Override
    public void getTransactionList(List<TransactionDateResponse> transactionResponses) {
        TransactionParentAdapter transactionParentAdapter = new TransactionParentAdapter(context, transactionResponses);
        recycler_view_statement_fragment.setLayoutManager(new LinearLayoutManager(context));
        recycler_view_statement_fragment.setAdapter(transactionParentAdapter);
    }

    @Override
    public void getWalletCurrency(List<WalletCurrencyResponse> getWalletCurrencyResponse) {
        Picasso.get()
                .load(getWalletCurrencyResponse.get(0).getCurrency_icon())
                .into(statementFlagIcon);
        currencyName.setText(getWalletCurrencyResponse.get(0).getCurrency_iso());

        CurrencyAdapter balanceHomeAdapter = new CurrencyAdapter(context, getWalletCurrencyResponse, position -> {
            constraintBalance.setVisibility(View.GONE);
            currencyName.setText(getWalletCurrencyResponse.get(position).getCurrency_iso());
            Picasso.get()
                    .load(getWalletCurrencyResponse.get(position).getCurrency_icon())
                    .into(statementFlagIcon);
            Constants.SYMBOL = getWalletCurrencyResponse.get(position).getSymbol();
            mPresenter.getTransactionListWithCurrency(getWalletCurrencyResponse.get(position).getCurrency_id());
        });


        RecyclerView recyclerView_balance_list = mainView.findViewById(R.id.recycle_balance_currency_statement);
        recyclerView_balance_list.setLayoutManager(new LinearLayoutManager(context));
        recyclerView_balance_list.setAdapter(balanceHomeAdapter);
    }

    private void closeConstraintBalance() {
        constraintBalance.setOnClickListener(v -> constraintBalance.setVisibility(View.GONE));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = context;
        super.onAttach(context);

    }
}
