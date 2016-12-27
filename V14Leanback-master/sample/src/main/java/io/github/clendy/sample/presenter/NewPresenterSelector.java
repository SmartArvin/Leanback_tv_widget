package io.github.clendy.sample.presenter;

import io.github.clendy.leanback.widget.ItemAlignmentFacet;
import io.github.clendy.leanback.widget.ListRow;
import io.github.clendy.leanback.widget.ListRowPresenter;
import io.github.clendy.leanback.widget.Presenter;
import io.github.clendy.leanback.widget.PresenterSelector;
import io.github.clendy.sample.R;

/**
 * 多个选择器.
 */
public class NewPresenterSelector extends PresenterSelector {
    private ListRowPresenter mListRowPresenter = new ListRowPresenter();
    private NewListRowPresenter mShadowDisabledRowPresenter = new NewListRowPresenter();
    private InvisibleRowPresenter mTestRowPresenter = new InvisibleRowPresenter();

    public NewPresenterSelector() {
        //
        ItemAlignmentFacet facet = new ItemAlignmentFacet();
        // by default align details_frame to half window height
        ItemAlignmentFacet.ItemAlignmentDef alignDef1 = new ItemAlignmentFacet.ItemAlignmentDef();
        alignDef1.setItemAlignmentFocusViewId(R.id.button);
        alignDef1.setItemAlignmentOffset(500);
        alignDef1.setItemAlignmentOffsetPercent(0);
        // when description is selected, align details_frame to top edge
        ItemAlignmentFacet.ItemAlignmentDef alignDef2 = new ItemAlignmentFacet.ItemAlignmentDef();
        alignDef2.setItemAlignmentFocusViewId(R.id.button6);
        alignDef2.setItemAlignmentOffset(1200);
        alignDef2.setItemAlignmentOffsetPercent(0);
        ItemAlignmentFacet.ItemAlignmentDef[] defs =
                new ItemAlignmentFacet.ItemAlignmentDef[] {alignDef1, alignDef2};
        facet.setAlignmentDefs(defs);
        mTestRowPresenter.setFacet(ItemAlignmentFacet.class, facet);
        //
        mListRowPresenter.setNumRows(1);
        mListRowPresenter.setHeaderPresenter(new HeaderPresenter());
        mShadowDisabledRowPresenter.setNumRows(1);
        mShadowDisabledRowPresenter.setHeaderPresenter(new HeaderPresenter());
        mTestRowPresenter.setHeaderPresenter(new HeaderPresenter()); // 可以换不同的头样式，自己写.
    }

    @Override
    public Presenter getPresenter(Object item) {
        ListRow listRow = (ListRow) item;
//        listRow.getHeaderItem().getName();
        if ((item instanceof ButtonListRow)) return mTestRowPresenter;
        return mShadowDisabledRowPresenter;
    }

    @Override
    public Presenter[] getPresenters() {
        return new Presenter[]{
                mShadowDisabledRowPresenter,
                mListRowPresenter,
                mTestRowPresenter
        };
    }
}
