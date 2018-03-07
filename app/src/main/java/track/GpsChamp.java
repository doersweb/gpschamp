package track;

import android.graphics.Bitmap;
import android.support.multidex.MultiDexApplication;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import org.greenrobot.greendao.database.Database;

import java.io.File;

import track.gpschamp.com.gpschamp.database.DaoMaster;
import track.gpschamp.com.gpschamp.database.DaoSession;

/**
 * Created by sudhirharit on 04/03/18.
 */

public class GpsChamp extends MultiDexApplication{

    public static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this, "gps_champ.db");
        Database db = openHelper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession(){
        return daoSession;
    }
}
