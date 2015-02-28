package com.jony.test;

import java.util.ArrayList;
import java.util.List;

import com.jony.common.AbsActivity;
import com.jony.common.AbsAdapter;
import com.jony.common.AsyncThreadMethod;
import com.jony.common.R;
import com.jony.common.R.layout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AbsActivity implements AsyncThreadMethod {
	List<String> mListDatas;
	ListAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mListDatas = new ArrayList<String>();
		mListDatas.add("ZX");
		mListDatas.add("LX");
		mListDatas.add("WW");
		mListDatas.add("ZL");
		mListDatas.add("LB");
		ListView list = (ListView) findViewById(R.id.test_listview);
		adapter = new ListAdapter(this, mListDatas);
		list.setAdapter(adapter);
		initAsyncThread(this);
	}
	@Override
	public void startThread() {
		try {
			Thread.sleep(3000);
			mListDatas.add("异步刷新进来数据");
			mListDatas.add("异步刷新进来数据");
			mListDatas.add("异步刷新进来数据");
			mListDatas.add("异步刷新进来数据");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void endThread() {
		adapter.notifyDataSetChanged();
	}
	//数据适配器
	class ListAdapter extends AbsAdapter<String>{
		public ListAdapter(Context context, List<String> mListDatas) {
			super(context, mListDatas);
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent,
				String t) {
			ViewHolder holder = null;
			if(null == convertView){
				holder = new ViewHolder();
				convertView = View.inflate(mContext, R.layout.list_item_layout, null);
				holder.name_tv = (TextView) convertView.findViewById(R.id.name_tv);
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			holder.name_tv.setText(t);
			return convertView;
		}
		class ViewHolder{
			TextView name_tv;
		}
	}
}
