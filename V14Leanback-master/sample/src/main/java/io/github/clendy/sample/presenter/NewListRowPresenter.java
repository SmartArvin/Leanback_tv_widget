package io.github.clendy.sample.presenter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import io.github.clendy.leanback.widget.FocusHighlightHandler;
import io.github.clendy.leanback.widget.ItemBridgeAdapter;
import io.github.clendy.leanback.widget.ListRowPresenter;
import io.github.clendy.leanback.widget.RowPresenter;

/**
 * Created by hailongqiu on 2016/12/16.
 */
public class NewListRowPresenter extends ListRowPresenter {

    @Override
    protected void initializeRowViewHolder(RowPresenter.ViewHolder holder) {
        super.initializeRowViewHolder(holder);
        final ViewHolder rowViewHolder = (ViewHolder) holder;
        ItemBridgeAdapter itemBridgeAdapter = rowViewHolder.getBridgeAdapter();
        // 焦点事件处理.
        itemBridgeAdapter.setFocusHighlight(new FocusHighlightHandler() {
            @Override
            public void onItemFocused(View view, boolean hasFocus) {
                if (hasFocus) {
                    view.animate().scaleX(1.3f).scaleY(1.3f).start();
                } else {
                    view.animate().scaleX(1.0f).scaleY(1.0f).start();
                }
            }

            @Override
            public void onInitializeView(View view) {
            }
        });
        // 设置横向item的间隔.
        rowViewHolder.getGridView().setHorizontalMargin(10);
    }

    @Override
    protected RowPresenter.ViewHolder createRowViewHolder(ViewGroup parent) {
        ListRowPresenter.ViewHolder holder = (ViewHolder) super.createRowViewHolder(parent);
        // test layout.

//        android.support.v7.widget.GridLayoutManager lg = new android.support.v7.widget.GridLayoutManager(parent.getContext(), 4);
//        lg.setOrientation(RecyclerView.VERTICAL);
//        lg.setAutoMeasureEnabled(true);

//        lg.setSpanCount(5);
//            LinearLayoutManager lg = new LinearLayoutManager(gridView.getContext());
//            lg.setOrientation(LinearLayoutManager.HORIZONTAL);
//        GridLayoutManager lg = new GridLayoutManager(rowView.getGridView());
//        lg.setOrientation(RecyclerView.HORIZONTAL);
//        holder.getGridView().setLayoutManager(lg);
//        GridLayoutManager gm = (GridLayoutManager) holder.getGridView().getLayoutManager();
//        gm.setOrientation(RecyclerView.VERTICAL);
//        gm.setAutoMeasureEnabled(true);
//        gm.setOrientation(RecyclerView.VERTICAL);
//        gm.setAutoMeasureEnabled(true);
        return holder;
    }
}
