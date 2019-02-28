package am.bibton.view.activities.ratesActivity.ratesFragments.convertFragment;

import java.util.List;

import am.bibton.model.ConvertModel.ConvertResponse;
import am.bibton.view.activities.IBaseView;

public interface IConvertFragment extends IBaseView {
    void getConvertList(List<ConvertResponse> getConvertList);
    void getConvertFromId(List<Integer> getConverFromId);
}
