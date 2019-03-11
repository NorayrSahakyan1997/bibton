package am.bibton.view.activities.bibtnToBibtonActivity.bibtonToBibtonList;

import am.bibton.Bibton;
import am.bibton.R;
import am.bibton.adapters.BibtonToBibtonListAdapter;
import am.bibton.model.bibtonToBibtonList.BibtonToBibtonListChild;
import am.bibton.presenter.BibtonToBibtonListPresenter;
import am.bibton.view.activities.BaseActivity;
import am.bibton.view.activities.bibtnToBibtonActivity.bibtonToBibton.BibtonToBibtonActivity;
import am.bibton.view.activities.homeActivity.HomeActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import java.util.List;
import javax.inject.Inject;

public class BibtonToBibtonListActivity extends BaseActivity implements IBibtonToBibtonListActivity {
    private ConstraintLayout constraintEmptyStateBibtonList;
    private RecyclerView recyclerView;
    private ConstraintLayout addBibtonTrasactionConstraint;

    @Inject
    BibtonToBibtonListPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bibton_to_bibton);
        Bibton.getInstance().getAuthorizationComponent().inject(this);
        mPresenter.onViewCreated(this);
        mPresenter.getBibtontoBibtonList();
        init();
        goToBibtonToBibtonActivity();
    }

    private void init() {
        constraintEmptyStateBibtonList = findViewById(R.id.constraintEmptyStateBibtonList);
        addBibtonTrasactionConstraint = findViewById(R.id.addBibtonTrasactionConstraint);
        recyclerView = findViewById(R.id.recyclerBibtonToBibtonList);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }


    @Override
    public void getBibtonToBibtonList(List<BibtonToBibtonListChild> getBibtonList) {
        if (getBibtonList.size() != 0) {
            addBibtonTrasactionConstraint.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            constraintEmptyStateBibtonList.setVisibility(View.GONE);

            BibtonToBibtonListAdapter bibtonToBibtonListAdapter = new BibtonToBibtonListAdapter(this, getBibtonList, this::checkIfUserInfoIsAvailable);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(bibtonToBibtonListAdapter);
        } else {
            addBibtonTrasactionConstraint.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            constraintEmptyStateBibtonList.setVisibility(View.VISIBLE);
        }

    }

    public void goToBibtonToBibtonActivity(View view) {
        Intent intent = new Intent(this, BibtonToBibtonActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    private void goToBibtonToBibtonActivity() {
        constraintEmptyStateBibtonList.setOnClickListener(v -> {
            Intent goToAddAccountDetails = new Intent(this, BibtonToBibtonActivity.class);
            startActivity(goToAddAccountDetails);
        });
    }

    private void checkIfUserInfoIsAvailable(String name, String surName, int uniqueId, String phoneNumber) {
        Intent intent = new Intent(this, BibtonToBibtonActivity.class);

        if (name != null) {
            intent.putExtra("userName", name);

        }
        if (surName != null) {
            intent.putExtra("userSurname", surName);

        }
        if (phoneNumber != null) {
            intent.putExtra("phoneNumber", phoneNumber);

        }
        if (uniqueId != 0) {
            intent.putExtra("userUniqueId", uniqueId);

        }
        startActivity(intent);
    }

}
