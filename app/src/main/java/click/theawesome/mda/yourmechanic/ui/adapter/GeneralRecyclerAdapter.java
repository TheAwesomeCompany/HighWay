package click.theawesome.mda.yourmechanic.ui.adapter;

import android.content.Context;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class GeneralRecyclerAdapter<T, H extends  RecyclerView.ViewHolder> extends RecyclerView.Adapter<H> {

    private final LayoutInflater mInflater;
    private final Comparator<T> mComparator;
    private final SortedList<T> mSortedList;


    public GeneralRecyclerAdapter(Context context, Class<T> itemClass, Comparator<T> comparator) {
        this.mInflater = LayoutInflater.from(context);
        this.mComparator = comparator;
        this.mSortedList = new SortedList<T>(itemClass, new SortedList.Callback<T>() {
            public int compare(T a, T b) {
                return GeneralRecyclerAdapter.this.mComparator.compare(a, b);
            }

            public void onInserted(int position, int count) {
                GeneralRecyclerAdapter.this.notifyItemRangeInserted(position, count);
            }

            public void onRemoved(int position, int count) {
                GeneralRecyclerAdapter.this.notifyItemRangeRemoved(position, count);
            }

            public void onMoved(int fromPosition, int toPosition) {
                GeneralRecyclerAdapter.this.notifyItemMoved(fromPosition, toPosition);
            }

            public void onChanged(int position, int count) {
                GeneralRecyclerAdapter.this.notifyItemRangeChanged(position, count);
            }

            @Override
            public boolean areContentsTheSame(T oldItem, T newItem) {
                return GeneralRecyclerAdapter.this.areItemContentsTheSame(oldItem, newItem);
            }

            @Override
            public boolean areItemsTheSame(T item1, T item2) {
                return GeneralRecyclerAdapter.this.areItemsTheSame(item1, item2);
            }
        });
    }

    protected abstract boolean areItemsTheSame(T item1, T item2);

    protected abstract boolean areItemContentsTheSame(T oldItem, T newItem);

    @Override
    public H onCreateViewHolder(ViewGroup parent, int viewType) {
        return  onCreateViewHolder(mInflater, parent, viewType);
    }

    protected abstract H onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(H holder, int position) {
        onGeneralBindViewHolder(holder, mSortedList.get(position));
    }

    protected abstract void onGeneralBindViewHolder(H holder, T item);

    @Override
    public int getItemCount() {
        return mSortedList.size();
    }

    public void  replaceAll(final List<T> items) {
        mSortedList.beginBatchedUpdates();

        List<T> itemsToRemove = new ArrayList<>();
        int j = 0;
        for(int count = mSortedList.size(); j < count; ++j) {
            T item = mSortedList.get(j);
            if(!items.contains(item)) {
                itemsToRemove.add(item);
            }
        }

        for(int i = itemsToRemove.size() - 1; i >= 0; --i) {
            T item = itemsToRemove.get(i);
            mSortedList.remove(item);
        }

        for(T item : items){
            mSortedList.add(item);
        }

        mSortedList.endBatchedUpdates();
    }
}

