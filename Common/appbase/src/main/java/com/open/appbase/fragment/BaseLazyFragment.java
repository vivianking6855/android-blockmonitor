package com.open.appbase.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created on 2018/2/28.
 * lazy fragment: only load data if user visible fragment and data not load complete
 * you must setDataLoadCompleted(true), when your data load complete, otherwise it will load every time
 */
public abstract class BaseLazyFragment extends BaseFragment {
    // root view of Fragment
    private View mRootView;
    // if view init done
    private boolean isViewCreated;
    // if data load complete
    private boolean isDataLoadCompleted;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // if need keep view and rootv iew not null, return it
        if (mRootView != null && isKeepRootView()) {
            isViewCreated = true;
            return mRootView;
        }

        // inflater view
        mRootView = inflater.inflate(getLayout(), container, false);

        initData();
        initViews(mRootView);
        isViewCreated = true;

        // if visible load data, lazy load
        lazyLoad();

        return mRootView;
    }

    /**
     * 优先于oncreate方法执行，且每次切换fragment都会执行此方法！
     *
     * @param isVisibleToUser fragment visible or not
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        lazyLoad();
    }

    /**
     * load data, only when fragment visible and view created and data never load completed
     * you must set true, when your data load complete, otherwise it will load every time
     */
    private void lazyLoad() {
        if (getUserVisibleHint() && isViewCreated && !isDataLoadCompleted) {
            loadData();
        }
    }

    /**
     * Set data load completed.
     * you must set true, when your data load complete, otherwise it will load every time
     *
     * @param completed the completed
     */
    public void setDataLoadCompleted(boolean completed) {
        isDataLoadCompleted = completed;
    }

    /**
     * viewpager default will keep current and before, after these three fragments.
     * if return true, it will not recreate event fragment detached from activity, because fragment object is still exit
     * @return true : root view will not recreate if root view is not null.
     */
    protected boolean isKeepRootView(){
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewCreated = false;
    }
}