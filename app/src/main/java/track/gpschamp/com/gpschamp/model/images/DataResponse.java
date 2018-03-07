package track.gpschamp.com.gpschamp.model.images;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sudhirharit on 27/02/18.
 */

public class DataResponse {

    @SerializedName("page")
    private String page;

    @SerializedName("total")
    private int total;

    @SerializedName("records")
    private int records;

    @SerializedName("rows")
    private List<ImagesData> dataList;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public List<ImagesData> getDataList() {
        return dataList;
    }

    public void setDataList(List<ImagesData> dataList) {
        this.dataList = dataList;
    }
}
