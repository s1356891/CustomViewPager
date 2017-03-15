package com.workspace.bin.customviewpager;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class PageControl {

	private ImageView[] imageViews;// 点载体的控件集合
	private ImageView imageView;// 点载体控件
	private int pageSize;// 点的个数
	private int selectedImage = R.drawable.business_banner_light;// 亮点
	private int unselectedImage = R.drawable.business_banner_dank;// 暗点
	private int currentPage = 0;//当前点的下标值
	private Context mContext;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public PageControl(Context context, LinearLayout layout, int pageSize) {
		this.mContext = context;
		this.pageSize = pageSize;
		initDots(layout);
	}
	
	public PageControl(Context context, LinearLayout layout, int pageSize , int rid1, int rid2) {
		this.mContext = context;
		this.pageSize = pageSize;
		selectedImage = rid1;
		unselectedImage = rid2;
		initDots(layout);
	}

	// 显示选中点显示亮图
	public void selectPage(int current) {
		for (int i = 0; i < imageViews.length; i++) {
			imageViews[current].setBackgroundResource(selectedImage);
			if (current != i) {
				imageViews[i].setBackgroundResource(unselectedImage);
			}
		}
	}

	// 初始化点切换效果
	void initDots(LinearLayout group) {
		imageViews = new ImageView[pageSize];
		for (int i = 0; i < pageSize; i++) {
			// 实例化点载体控件
			imageView = new ImageView(mContext);
			// 填充点载体控件到集合
			imageViews[i] = imageView;
			if (i == 0) {
				// 默认第一个显示亮图
				imageViews[i].setBackgroundResource(selectedImage);
			} else {
				imageViews[i].setBackgroundResource(unselectedImage);
			}
			LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(
					new ViewGroup.LayoutParams(20,
							20));
			layout.leftMargin = 25;
			layout.rightMargin = 25;
			// 实例化显示点切换效果的线性布局
			group.addView(imageViews[i],layout);
		}
	}

	// 是否第一个点
	boolean isFirst() {
		return this.currentPage == 0;
	}

	// 是否最后一个点
	boolean isLast() {
		return this.currentPage == pageSize;
	}

	// 到下一个点
	void turnToNextPage() {
		if (!isLast()) {
			currentPage++;
			selectPage(currentPage);
		}
	}

	// 到上一个点
	void turnToPrePage() {
		if (!isFirst()) {
			currentPage--;
			selectPage(currentPage);
		}
	}
}
