package com.jony.common;

import java.util.List;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 	 AbsHolderAdapter  </br>
 *   
 * 	 描述：  一个继承了BaseAdapter的抽象类,重载了getView增加泛型参数。</br>
 *   子类必须要实现getView方法。</br>
 *   
 * @author jony
 *
 */
public abstract class AbsAdapter<T> extends BaseAdapter {
	
	public Context mContext;
	private List<T> mListDatas;
	
	/**
	 * 
	 * 创建一个新的实例 AbsHolderAdapter.  
	 *  
	 * @param context  上下文
	 * @param mListDatas 数据集合
	 */
	public AbsAdapter(Context context,List<T> mListDatas){
		this.mContext = context;
		this.mListDatas = mListDatas;
	}
	
	/**
	 * 
	 * setData的作用： 重新设置数据
	 * 适用条件：  
	 * @param lists   void  
	 * @exception   
	 * @since  1.0.0
	 */
	public void setData(List<T> lists) {
		this.mListDatas.clear();
		this.mListDatas = lists;
	}
	
	@Override
	public int getCount() {
		return mListDatas.size();
	}
	@Override
	public Object getItem(int position) {
		return mListDatas.get(position);
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	/**
	 * 这里已经对getView方法进行处理。 以后都不需要处理这个getView方法了。
	 * 只需要实现getHolder()就可以
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		//判断是否有复用的view
		return getView(position, convertView, parent,mListDatas.get(position));
	}
	/**
	 * 返回一个holder对象。以后写代码就只要写一个类基础AbsHolder类就可以了
	 */
	public abstract View getView(int position, View convertView, ViewGroup parent,T t);
}
