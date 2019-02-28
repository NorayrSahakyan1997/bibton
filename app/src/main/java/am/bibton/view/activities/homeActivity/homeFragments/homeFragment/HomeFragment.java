package am.bibton.view.activities.homeActivity.homeFragments.homeFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import javax.inject.Inject;
import am.bibton.Bibton;
import am.bibton.R;
import am.bibton.adapters.CurrencyAdapter;
import am.bibton.adapters.ServiceAdapterHorizontal;
import am.bibton.adapters.transactionAdapter.TransactionParentAdapter;
import am.bibton.model.getTransactionList.TransactionDateResponse;
import am.bibton.model.walletCurrency.WalletCurrencyResponse;
import am.bibton.presenter.HomeFragmentPresenter;
import am.bibton.shared.utils.Constants;
import am.bibton.view.activities.BaseFragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends BaseFragment implements IHomeFragment {

    private Context context;
    private View mainView;
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
    private ConstraintLayout constraint_balance;
    private TextView currencyName;
    private RecyclerView recycler_view_transaction;
    private TextView textViewCash;
    private ImageView wallet_image_view;
    private ImageView cardIconImageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.home_fragment_layout, container, false);
        Bibton.getInstance().getAuthorizationComponent().inject(this);
        mPresenter.onViewCreated(this);
        mPresenter.getCurrencyList();
        mPresenter.getTransactionList();
        init();
        wallet_State(Constants.IS_WALLET);
        open_wallet_layout();
        close_wallet_layout();
        first_item_selected();
        second_item_selected();
        open_currency_view();
        close_currency_view();
        //changeCashBalanceSizes();

        return mainView;
    }

    private void init() {
        RecyclerView recyclerView_service_horizontal = mainView.findViewById(R.id.recyclerView_service_horizontal);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_service_horizontal.setLayoutManager(layoutManager);
        ServiceAdapterHorizontal serviceAdapterHorizontal = new ServiceAdapterHorizontal(context);
        recyclerView_service_horizontal.setAdapter(serviceAdapterHorizontal);
        open_wallet_layout = mainView.findViewById(R.id.open_wallet_layout);
        constrait_wallet = mainView.findViewById(R.id.constrait_wallet);
        close_wallet_text_view = mainView.findViewById(R.id.close_wallet_text_view);
        first_item_selected = mainView.findViewById(R.id.wallet_is_selected);
        second_item_selected = mainView.findViewById(R.id.bank_account_selected);
        textViewWallet = mainView.findViewById(R.id.text_view_wallet);
        text_view_bank_account = mainView.findViewById(R.id.text_view_bank_account);
        wallet_state_text_view = mainView.findViewById(R.id.wallet_state);
        wallet_image_view_state = mainView.findViewById(R.id.wallet_image_view_state);
        constraint_balance = mainView.findViewById(R.id.constraint_balance);
        currencyName = mainView.findViewById(R.id.currency_name);
        recycler_view_transaction = mainView.findViewById(R.id.recycler_view_transaction);
        textViewCash = mainView.findViewById(R.id.cash_balance);
        wallet_image_view=mainView.findViewById(R.id.wallet_image_view);
        cardIconImageView=mainView.findViewById(R.id.card_icon_image);
    }

//    private void changeCashBalanceSizes() {
//        String s = "$19,000.00";
//        SpannableString ss1 = new SpannableString(s);
//        ss1.setSpan(new RelativeSizeSpan(1.5f), 0, 7, 0); // set size
//        ss1.setSpan(new RelativeSizeSpan(0.7f), 0, 1, 0); // set size
//        ss1.setSpan(new ForegroundColorSpan(Color.WHITE), 0, 6, 0);// set color
//        TextView tv = (TextView) mainView.findViewById(R.id.cash_balance);
//        tv.setText(ss1);
//    }


    private void open_wallet_layout() {
        open_wallet_layout.setOnClickListener(v -> constrait_wallet.setVisibility(View.VISIBLE));
    }

    private void close_wallet_layout() {
        close_wallet_text_view.setOnClickListener(v -> constrait_wallet.setVisibility(View.INVISIBLE));
    }

    private void open_currency_view() {
        currencyName.setOnClickListener(v -> constraint_balance.setVisibility(View.VISIBLE));
    }

    private void close_currency_view() {
        constraint_balance.setOnClickListener(v ->  constraint_balance.setVisibility(View.GONE));
        constrait_wallet.setOnClickListener(v -> constrait_wallet.setVisibility(View.GONE));
    }

    private void first_item_selected() {
        first_item_selected.setOnClickListener(v -> bankAccountIsSelected());
    }

    private void second_item_selected() {
        second_item_selected.setOnClickListener(v -> walletIsSelected());
    }

    private void bankAccountIsSelected() {
        second_item_selected.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
        text_view_bank_account.setTextColor(getResources().getColor(R.color.colorBlack));
        first_item_selected.setBackground(context.getResources().getDrawable(R.drawable.rectangle_shape_active));
        textViewWallet.setTextColor(context.getResources().getColor(R.color.white));
        constrait_wallet.setVisibility(View.INVISIBLE);
        wallet_state_text_view.setText(context.getResources().getText(R.string.bankAccount));
        wallet_image_view_state.setBackground(context.getResources().getDrawable(R.drawable.wallet_icon_colorful));
        wallet_image_view.setBackground(context.getResources().getDrawable(R.drawable.card_icon));
        Constants.IS_WALLET = false;
    }

    private void walletIsSelected() {
        first_item_selected.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
        textViewWallet.setTextColor(getResources().getColor(R.color.colorBlack));
        second_item_selected.setBackground(context.getResources().getDrawable(R.drawable.rectangle_shape_active));
        text_view_bank_account.setTextColor(context.getResources().getColor(R.color.white));
        constrait_wallet.setVisibility(View.INVISIBLE);
        wallet_state_text_view.setText(context.getResources().getText(R.string.wallet));
        wallet_image_view_state.setBackground(context.getResources().getDrawable(R.drawable.wallet_icon_white_black));
        Constants.IS_WALLET = true;
        wallet_image_view.setBackground(context.getResources().getDrawable(R.drawable.wallet_icon));
        cardIconImageView.setBackground(context.getResources().getDrawable(R.drawable.card_icon));
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
    public void getTransactionList(List<TransactionDateResponse> data) {
        TransactionParentAdapter transactionParentAdapter = new TransactionParentAdapter(context, data);
        recycler_view_transaction.setLayoutManager(new LinearLayoutManager(context));
        recycler_view_transaction.setAdapter(transactionParentAdapter);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void getCurrencyWallet(List<WalletCurrencyResponse> getWalletCurrencyList) {
        currencyName.setText(getWalletCurrencyList.get(0).getCurrency_iso());
        textViewCash.setText(getWalletCurrencyList.get(0).getSymbol() + getWalletCurrencyList.get(0).getBalance());
        CurrencyAdapter balanceHomeAdapter = new CurrencyAdapter(context, getWalletCurrencyList, position -> {
            constraint_balance.setVisibility(View.GONE);
            currencyName.setText(getWalletCurrencyList.get(position).getCurrency_iso());
            Constants.SYMBOL = getWalletCurrencyList.get(position).getSymbol();
            mPresenter.getTransactionListWithCurrency(getWalletCurrencyList.get(position).getCurrency_id());
            textViewCash.setText(getWalletCurrencyList.get(position).getSymbol() + getWalletCurrencyList.get(position).getBalance());
        });
        RecyclerView recyclerView_balance_list = mainView.findViewById(R.id.recycle_balance);
        recyclerView_balance_list.setLayoutManager(new LinearLayoutManager(context));
        recyclerView_balance_list.setAdapter(balanceHomeAdapter);
    }
}
