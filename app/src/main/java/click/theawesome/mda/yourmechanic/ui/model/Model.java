package click.theawesome.mda.yourmechanic.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Model implements Parcelable {

    private int mId;
    private long mTime;
    private String mName;
    private String mUrl;
    private String mState;
    private String fromAddress;
    private String toAddress;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        mState = state;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Model model = (Model) o;

        if (mId != model.mId) return false;
        if (mTime != model.mTime) return false;
        if (!mName.equals(model.mName)) return false;
        if (!mUrl.equals(model.mUrl)) return false;
        if (!mState.equals(model.mState)) return false;
        if (!fromAddress.equals(model.fromAddress)) return false;
        return toAddress.equals(model.toAddress);

    }

    @Override
    public int hashCode() {
        int result = mId;
        result = 31 * result + (int) (mTime ^ (mTime >>> 32));
        result = 31 * result + mName.hashCode();
        result = 31 * result + mUrl.hashCode();
        result = 31 * result + mState.hashCode();
        result = 31 * result + fromAddress.hashCode();
        result = 31 * result + toAddress.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mId);
        dest.writeLong(this.mTime);
        dest.writeString(this.mName);
        dest.writeString(this.mUrl);
        dest.writeString(this.mState);
        dest.writeString(this.fromAddress);
        dest.writeString(this.toAddress);
    }

    public Model() {
    }

    protected Model(Parcel in) {
        this.mId = in.readInt();
        this.mTime = in.readLong();
        this.mName = in.readString();
        this.mUrl = in.readString();
        this.mState = in.readString();
        this.fromAddress = in.readString();
        this.toAddress = in.readString();
    }

    public static final Parcelable.Creator<Model> CREATOR = new Parcelable.Creator<Model>() {
        @Override
        public Model createFromParcel(Parcel source) {
            return new Model(source);
        }

        @Override
        public Model[] newArray(int size) {
            return new Model[size];
        }
    };
}
