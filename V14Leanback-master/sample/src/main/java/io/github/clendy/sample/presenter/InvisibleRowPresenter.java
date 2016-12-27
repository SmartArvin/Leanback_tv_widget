/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.github.clendy.sample.presenter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import io.github.clendy.leanback.widget.ListRow;
import io.github.clendy.leanback.widget.ObjectAdapter;
import io.github.clendy.leanback.widget.Presenter;
import io.github.clendy.leanback.widget.PresenterSelector;
import io.github.clendy.leanback.widget.RowPresenter;
import io.github.clendy.sample.R;

/**
 */
public class InvisibleRowPresenter extends RowPresenter {

    public InvisibleRowPresenter() {
//        setHeaderPresenter(null); // 屏蔽掉头.
    }

    /**
     * 不得不说，android 的 Leanback 确实很灵活.
     *  这里可以换成其它布局的，不一定是 LinerLayout.
     */
    @Override
    protected ViewHolder createRowViewHolder(ViewGroup parent) {
        // 测试 拼图墙效果.
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_patch_wall_view, null, false);
        // 测试横向的布局.
//        LinearLayout root = new LinearLayout(parent.getContext());
//        root.setOrientation(LinearLayout.VERTICAL);
//        root.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(root);
    }

//    @Override
//    protected void onBindRowViewHolder(RowPresenter.ViewHolder holder, Object item) {
//        super.onBindRowViewHolder(holder, item);
//        LinearLayout root = (LinearLayout) holder.view;
//        ListRow rowItem = (ListRow) item;
//        ObjectAdapter objectAdapter = rowItem.getAdapter();
//        PresenterSelector presenterSelector = objectAdapter.getPresenterSelector();
//        Presenter presenter = presenterSelector.getPresenter(new String());
//        // 测试，加载多个ITEM.
//        for (int i = 0; i < objectAdapter.size(); i++) {
//            Presenter.ViewHolder vh = presenter.onCreateViewHolder(root);
//            View view = vh.view;
//            root.addView(view, view.getLayoutParams());
//            presenter.onBindViewHolder(vh, objectAdapter.get(i));
//        }
//    }
//
//    @Override
//    protected void onUnbindRowViewHolder(ViewHolder holder) {
//        super.onUnbindRowViewHolder(holder);
//        LinearLayout root = (LinearLayout) holder.view;
//        root.removeAllViews();
//    }

}
