package track.gpschamp.com.gpschamp.core.home;

/**
 * Created by sudhirharit on 05/02/18.
 */

public class HomePresenter implements HomeContractor.Presenter {

    HomeContractor.View mView;
    HomeInteractor homeInteractor;
    public HomePresenter(HomeContractor.View mView){
        this.mView = mView;
        homeInteractor = new HomeInteractor(this);
    }

    @Override
    public void objectListResponse() {

    }

    @Override
    public void objectListRequest() {

    }
}
