package am.spaysapps.bibton.view.activities.homeActivity.homeFragments.homeFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import am.spaysapps.bibton.Bibton;
import am.spaysapps.bibton.R;
import am.spaysapps.bibton.adapters.BalanceHomeAdapter;
import am.spaysapps.bibton.adapters.ServiceAdapterHorizontal;
import am.spaysapps.bibton.adapters.TransactionListAdapter;
import am.spaysapps.bibton.model.getTransactionList.TransactionRequestModel;
import am.spaysapps.bibton.model.getTransactionList.TransactionResponse;
import am.spaysapps.bibton.model.walletCurrency.WalletCurrencyParentResponse;
import am.spaysapps.bibton.model.walletCurrency.WalletCurrencyResponse;
import am.spaysapps.bibton.presenter.HomeFragmentPresenter;
import am.spaysapps.bibton.shared.utils.Constants;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends Fragment implements IHomeFragment {

    private Context context;
    private View mainView;
    private RecyclerView transaction_recycler_view;
    private ConstraintLayout open_wallet_layout;
    private ConstraintLayout constrait_wallet;
    @Inject
    HomeFragmentPresenter mPresenter;

    private TextView close_wallet_text_view;
    private ConstraintLayout first_item_selected;
    private ConstraintLayout second_item_selected;
    private TextView textViewWallet;
    private TextView text_view_bank_account;
    private TextView wallet_state_text_view;
    private ImageView wallet_image_view_state;
    private ConstraintLayout open_currency_view;
    private ConstraintLayout constraint_balance;
    private TextView currencyName;
    private Fragment current_fragment;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.home_fragment_layout, container, false);
        Bibton.getInstance().getAuthorizationComponent().inject(this);
        mPresenter.onViewCreated(this);

        TransactionRequestModel transactionRequestModel = new TransactionRequestModel();
        transactionRequestModel.setFrom_currency_id(Constants.CURRENCY_ID);
        mPresenter.getCurrencyList();
        mPresenter.getTransactionList(transactionRequestModel);
        init();
        wallet_State(Constants.IS_WALLET);
//        setCurrencyAdapter();
        open_wallet_layout();
        close_wallet_layout();
        first_item_selected();
        second_item_selected();
        open_currency_view();
        close_currency_view();
        return mainView;
    }

    private void init() {
        RecyclerView recyclerView_service_horizontal = mainView.findViewById(R.id.recyclerView_service_horizontal);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView_service_horizontal.setLayoutManager(layoutManager);
        ServiceAdapterHorizontal serviceAdapterHorizontal = new ServiceAdapterHorizontal(context);
        recyclerView_service_horizontal.setAdapter(serviceAdapterHorizontal);
        transaction_recycler_view = mainView.findViewById(R.id.recycler_view_transaction);
        RecyclerView.LayoutManager transaction_layout_manager = new LinearLayoutManager(getContext());
        transaction_recycler_view.setLayoutManager(transaction_layout_manager);
        open_wallet_layout = mainView.findViewById(R.id.open_wallet_layout);
        constrait_wallet = mainView.findViewById(R.id.constrait_wallet);
        close_wallet_text_view = mainView.findViewById(R.id.close_wallet_text_view);
        first_item_selected = mainView.findViewById(R.id.wallet_is_selected);
        second_item_selected = mainView.findViewById(R.id.bank_account_selected);
        textViewWallet = mainView.findViewById(R.id.text_view_wallet);
        text_view_bank_account = mainView.findViewById(R.id.text_view_bank_account);
        wallet_state_text_view = mainView.findViewById(R.id.wallet_state);
        wallet_image_view_state = mainView.findViewById(R.id.wallet_image_view_state);
        open_currency_view = mainView.findViewById(R.id.open_currency_view);
        constraint_balance = mainView.findViewById(R.id.constraint_balance);
        currencyName = mainView.findViewById(R.id.currency_name);
        current_fragment = new HomeFragment();

    }
//    private void setCurrencyAdapter(){
//        BalanceHomeAdapter balanceHomeAdapter = new BalanceHomeAdapter(context, walletCurrencyResponseList);
//
//        RecyclerView recyclerView_balance_list = mainView.findViewById(R.id.recycle_balance);
//        RecyclerView.LayoutManager recycler_Manager = new LinearLayoutManager(context);
//        recyclerView_balance_list.setLayoutManager(recycler_Manager);
//        recyclerView_balance_list.setAdapter(balanceHomeAdapter);
//    }

    private void open_wallet_layout() {
        open_wallet_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constrait_wallet.setVisibility(View.VISIBLE);
            }
        });
    }

    private void close_wallet_layout() {
        close_wallet_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constrait_wallet.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void open_currency_view() {
        open_currency_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraint_balance.setVisibility(View.VISIBLE);
            }
        });
    }

    private void close_currency_view() {
        constraint_balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraint_balance.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void first_item_selected() {
        first_item_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bankAccountIsSelected();

            }
        });
    }

    private void second_item_selected() {
        second_item_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                walletIsSelected();


            }
        });
    }

    private void bankAccountIsSelected() {
        second_item_selected.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
        text_view_bank_account.setTextColor(getResources().getColor(R.color.colorBlack));
        first_item_selected.setBackground(context.getResources().getDrawable(R.drawable.rectangle_shape_active));
        textViewWallet.setTextColor(context.getResources().getColor(R.color.white));
        constrait_wallet.setVisibility(View.INVISIBLE);
        wallet_state_text_view.setText(context.getResources().getText(R.string.wallet));
        wallet_image_view_state.setBackground(context.getResources().getDrawable(R.drawable.wallet_icon_white_black));
        Constants.IS_WALLET = false;

    }

    private void walletIsSelected() {
        first_item_selected.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
        textViewWallet.setTextColor(getResources().getColor(R.color.colorBlack));
        second_item_selected.setBackground(context.getResources().getDrawable(R.drawable.rectangle_shape_active));
        text_view_bank_account.setTextColor(context.getResources().getColor(R.color.white));
        constrait_wallet.setVisibility(View.INVISIBLE);
        wallet_state_text_view.setText(context.getResources().getText(R.string.bankAccount));
        wallet_image_view_state.setBackground(context.getResources().getDrawable(R.drawable.wallet_icon_colorful));
        Constants.IS_WALLET = true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    private void wallet_State(boolean isWallet) {
        if (isWallet) {
            walletIsSelected();
        } else {
            bankAccountIsSelected();
        }
    }

    @Override
    public void onDestroy() {
        mPresenter.stopSubscriptions();
        super.onDestroy();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public void getTransactionList(List<TransactionResponse> getTransactionList) {
        List<TransactionResponse> transactionResponseList;
        transactionResponseList = getTransactionList;
        TransactionListAdapter transactionListAdapter = new TransactionListAdapter(getContext(), transactionResponseList);
        transaction_recycler_view.setAdapter(transactionListAdapter);
        Constants.TRANSACTION_LIST = getTransactionList;

    }

    private RecyclerView recyclerView_balance_list;

    @Override
    public void getCurrencyWallet(List<WalletCurrencyResponse> getWalletCurrencyList) {
        List<WalletCurrencyResponse> walletCurrencyResponseList = getWalletCurrencyList;
        BalanceHomeAdapter balanceHomeAdapter = new BalanceHomeAdapter(context, walletCurrencyResponseList, current_fragment, constraint_balance, currencyName);

        recyclerView_balance_list = mainView.findViewById(R.id.recycle_balance);
        RecyclerView.LayoutManager recycler_Manager = new LinearLayoutManager(context);
        recyclerView_balance_list.setLayoutManager(recycler_Manager);
        recyclerView_balance_list.setAdapter(balanceHomeAdapter);


//        setCurrencyAdapter();

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
