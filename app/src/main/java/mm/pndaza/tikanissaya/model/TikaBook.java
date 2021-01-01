package mm.pndaza.tikanissaya.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TikaBook implements Parcelable {
    private String id;
    private String name;
    private int firstPage;
    private int lastPage;


    public TikaBook(String id, String name, int firstPage, int lastPage) {
        this.id = id;
        this.name = name;
        this.firstPage = firstPage;
        this.lastPage = lastPage;
    }

    protected TikaBook(Parcel in) {
        id = in.readString();
        name = in.readString();
        firstPage = in.readInt();
        lastPage = in.readInt();
    }

    public static final Creator<TikaBook> CREATOR = new Creator<TikaBook>() {
        @Override
        public TikaBook createFromParcel(Parcel in) {
            return new TikaBook(in);
        }

        @Override
        public TikaBook[] newArray(int size) {
            return new TikaBook[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeInt(firstPage);
        dest.writeInt(lastPage);
    }
}
