package track.gpschamp.com.gpschamp.core.home;

/**
 * Created by sudhirharit on 05/02/18.
 */

public class HomeInteractor implements HomeContractor.Interactor {

    HomePresenter homePresenter;

    public HomeInteractor(HomePresenter homePresenter){
        this.homePresenter = homePresenter;
    }

    @Override
    public void objectListRequest() {

    }
}
