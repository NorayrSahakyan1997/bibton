package am.bibton.view.activities.bibtonToBibtonActivity.bibtonToBibtonList;

import java.util.List;

import am.bibton.model.bibtonToBibtonList.BibtonToBibtonListChild;
import am.bibton.view.activities.IBaseView;

public interface IBibtonToBibtonListActivity extends IBaseView {
    void getBibtonToBibtonList(List<BibtonToBibtonListChild> getBibtonList);
}
