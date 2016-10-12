package click.theawesome.mda.yourmechanic.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Comparator;

import click.theawesome.mda.yourmechanic.R;
import click.theawesome.mda.yourmechanic.databinding.ParcelItemBinding;
import click.theawesome.mda.yourmechanic.ui.model.Model;
import click.theawesome.mda.yourmechanic.ui.util.AssetsUtils;

/**
 * Created by mda on 10/10/16.
 */
public class HighwayAdapter extends GeneralRecyclerAdapter<Model, HighwayAdapter.ViewHolder> {

    public interface Listener {
        void onItemClicked(View view, Model model);
    }

    private final Listener mListener;

    public HighwayAdapter(Context context, Comparator<Model> comparator, Listener listener) {
        super(context, Model.class, comparator);
        mListener = listener;
    }

    @Override
    protected boolean areItemsTheSame(Model item1, Model item2) {
        return item1.getId() == item2.getId();
    }

    @Override
    protected boolean areItemContentsTheSame(Model oldItem, Model newItem) {
        return oldItem.equals(newItem);
    }

    @Override
    protected void onGeneralBindViewHolder(ViewHolder holder, Model item) {
        holder.bind(item);
    }

    @Override
    public ViewHolder onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup parent, int viewType) {
        final ParcelItemBinding binding = ParcelItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding, mListener);
    }

    static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        private final ParcelItemBinding mBinding;

        ViewHolder(ParcelItemBinding binding, Listener listener) {
            super(binding.cardView);
            binding.setListener(listener);
            mBinding = binding;
        }

        final void bind(Model item) {
            mBinding.setModel(item);
            AssetsUtils.loadImageFromAssets(mBinding.itemIcon, item.getUrl());
        }
    }

}
