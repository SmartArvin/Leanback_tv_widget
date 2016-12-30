#LeanBack


![输入图片说明](http://git.oschina.net/uploads/images/2016/1230/133515_2a3df266_111902.png "在这里输入图片标题")



## 优化记录


* 优化

  当垂直的RecyclerView过多的时候会有一些卡顿.

  http://blog.csdn.net/axi295309066/article/details/52741810

  http://blog.csdn.net/wuhengde/article/details/46833967




# 特点

 修改自 Lenaback源码.

一个适用于Android TV端的分页加载列表库，控件继承自RecyclerView，部分源码抽取自Google Support v17 Leanback包下源码，可兼容低版本环境。相比原始的RecyclerView，拥有以下特点

1.自动回焦至被选中的item；

2.item滚动居中;

3.焦点移动至边界位置时不会出现越界丢失焦点；

4.快速滑动和慢速滑动都不会出现丢焦现象；

5.支持分页加载；

6.支持移动边框.

7.支持多个垂直滚动.



## 源码分析



### 移动在中间分析

Leanaback GridViewLayoutManger.java 滚动在中间的分析

具体流程：

GridLayoutManager.java --> onRequestChildFocus ----> scrollToView ----> scrollGrid ----> mBaseGridView.scrollBy(scrollX, scrollY);

由于多个垂直，控件的父布局设置 tag="other"后，跑的流程不一样.

GridLayoutManager.java --> onRequestChildFocus ----> scrollOther

还可以使用 （这个方法有局限性，少的话可以使用，多的不建议使用)

ItemAlignmentFacet facet = new ItemAlignmentFacet();

ItemAlignmentFacet.ItemAlignmentDef alignDef1 = new ItemAlignmentFacet.ItemAlignmentDef();

facet.setAlignmentDefs(defs);

mRowPresenter.setFacet(ItemAlignmentFacet.class, facet);



### 一行高亮分析


ItemBridgeAdapter 设置监听事件.
```
ItemBridgeAdapter.AdapterListener mBridgeAdapterListener =

new ItemBridgeAdapter.AdapterListener() {

public void onCreate(ItemBridgeAdapter.ViewHolder viewHolder) {
setRowViewSelected(vh, false);
}

};
```

```
void setRowViewSelected(ViewHolder vh, boolean selected) {
((RowPresenter)vh.getPresenter()).setRowViewSelected(vh.getViewHolder(), selected);
}
```



