package mak.app.nestedrecycler.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Contents implements Parcelable {
    protected ContentsType contentType;

    public Contents(ContentsType contentType) {
        this.contentType = contentType;
    }

    protected Contents(Parcel in) {
        this.contentType = ContentsType.parseFromValue(in.readInt());
    }

    public static final Creator<Contents> CREATOR = new Creator<Contents>() {
        @Override
        public Contents createFromParcel(Parcel in) {
            return new Contents(in);
        }

        @Override
        public Contents[] newArray(int size) {
            return new Contents[size];
        }
    };

    public ContentsType getContentType() {
        return contentType;
    }

    public void setContentType(ContentsType contentType) {
        this.contentType = contentType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(contentType.getValue());
    }

}