package io.github.clendy.sample.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Collections;
import java.util.List;

import io.github.clendy.leanback.widget.ArrayObjectAdapter;
import io.github.clendy.leanback.widget.HeaderItem;
import io.github.clendy.leanback.widget.ItemBridgeAdapter;
import io.github.clendy.leanback.widget.ListRow;
import io.github.clendy.leanback.widget.OnChildViewHolderSelectedListener;
import io.github.clendy.leanback.widget.Presenter;
import io.github.clendy.leanback.widget.VerticalGridView;
import io.github.clendy.sample.R;
import io.github.clendy.sample.model.Movie;
import io.github.clendy.sample.model.MovieList;
import io.github.clendy.sample.presenter.ButtonListRow;
import io.github.clendy.sample.presenter.CardPresenter;
import io.github.clendy.sample.presenter.NewPresenterSelector;

/**
 * VerticalActivity
 *
 * @author Clendy
 * @time 2016/11/20 16:13
 * @e-mail yc330483161@outlook.com
 */
public class VerticalActivity extends Activity {

    private static final String TAG = "hailongqiu";

    VerticalGridView mRecyclerView;
    ArrayObjectAdapter mRowsAdapter;
    ItemBridgeAdapter mItemBridgeAdapter;

    private static final int NUM_ROWS = 10;
    private static final int NUM_COLS = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical);
        mRecyclerView = (VerticalGridView) findViewById(R.id.recyclerView);

        List<Movie> list = MovieList.setupMovies();
        NewPresenterSelector newPresenterSelector = new NewPresenterSelector();
        mRowsAdapter = new ArrayObjectAdapter(newPresenterSelector); // 填入Presenter选择器.
        CardPresenter cardPresenter = new CardPresenter();

        int i;
        // 电影海报测试数据.
        for (i = 0; i < NUM_ROWS; i++) {
            if (i != 0) {
                Collections.shuffle(list);
            }
            ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(cardPresenter);
            for (int j = 0; j < NUM_COLS; j++) {
                listRowAdapter.add(list.get(j % 5));
            }
            HeaderItem header = new HeaderItem(i, MovieList.MOVIE_CATEGORY[i % 5]);
            ListRow listRow = new ListRow(header, listRowAdapter);
            mRowsAdapter.add(listRow);
        }

        // 测试其它数据.
        HeaderItem gridHeader = new HeaderItem(i, "系统设置");
        GridItemPresenter mGridPresenter = new GridItemPresenter();
        ArrayObjectAdapter gridRowAdapter = new ArrayObjectAdapter(mGridPresenter);
        gridRowAdapter.add("音频");
        gridRowAdapter.add("投影设置");
        gridRowAdapter.add("明天是否");
        mRowsAdapter.add(new ButtonListRow(gridHeader, gridRowAdapter));

        // 多测试几行数据，检测.
        for (i = 0; i < NUM_ROWS; i++) {
            if (i != 0) {
                Collections.shuffle(list);
            }
            ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(cardPresenter);
            for (int j = 0; j < NUM_COLS; j++) {
                listRowAdapter.add(list.get(j % 5));
            }
            HeaderItem header = new HeaderItem(i, MovieList.MOVIE_CATEGORY[i % 5]);
            ListRow listRow = new ListRow(header, listRowAdapter);
            mRowsAdapter.add(listRow);
        }

        //
        mItemBridgeAdapter = new ItemBridgeAdapter(mRowsAdapter);
        mRecyclerView.setAdapter(mItemBridgeAdapter);
        mRecyclerView.setOnChildViewHolderSelectedListener(new OnChildViewHolderSelectedListener() {
            @Override
            public void onChildViewHolderSelected(RecyclerView parent, RecyclerView.ViewHolder child, int position, int subposition) {
                Log.d("hailongqiu", "一行的选中");
            }
        });
        // 不然有一些item放大被挡住了. (注意)
        mRecyclerView.setClipChildren(false);
        mRecyclerView.setClipToPadding(false);
        // 设置间隔.
        mRecyclerView.setPadding(30, 30, 30, 30);
        // 设置垂直item的间隔.
        mRecyclerView.setVerticalMargin(30);
        // 缓存测试.
//        mRecyclerView.getRecycledViewPool().setMaxRecycledViews(0, 1000);
    }

    private static final int GRID_ITEM_WIDTH = 200;
    private static final int GRID_ITEM_HEIGHT = 200;

    public static class GridItemPresenter extends Presenter {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent) {
            Button view = new Button(parent.getContext());
            view.setLayoutParams(new ViewGroup.LayoutParams(GRID_ITEM_WIDTH, GRID_ITEM_HEIGHT));
            view.setFocusable(true);
            view.setFocusableInTouchMode(true);
//            view.setBackgroundColor(getResources().getColor(R.color.default_background));
            view.setTextColor(Color.WHITE);
            view.setGravity(Gravity.CENTER);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
//            ListRowPresenter.ViewHolder vh = (ListRowPresenter.ViewHolder) viewHolder;
//            vh.getGridView().setLayoutManager();
            ((Button) viewHolder.view).setText((String) item);
        }

        @Override
        public void onUnbindViewHolder(ViewHolder viewHolder) {
        }
    }

}
