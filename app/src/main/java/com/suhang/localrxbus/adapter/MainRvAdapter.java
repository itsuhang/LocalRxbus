package com.suhang.localrxbus.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suhang.localrxbus.annotation.BaseScope;
import com.suhang.localrxbus.function.rx.RxBus;
import com.suhang.localrxbus.function.rx.SubstribeManager;

import javax.inject.Inject;

/**
 * Created by 苏杭 on 2017/5/24 21:14.
 */
@BaseScope
public class MainRvAdapter extends RecyclerView.Adapter<MainRvAdapter.MyViewHolder> {
	@Inject
	SubstribeManager mManager;
	//将Adapter也注入
	@Inject
	public MainRvAdapter() {
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		TextView tv = new TextView(parent.getContext());
		return new MyViewHolder(tv);
	}

	@Override
	public void onBindViewHolder(MyViewHolder holder, final int position) {
		TextView tv = (TextView) holder.itemView;
		tv.setText("随意的布局,随意的数据"+position);
		tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//发送事件,类型为Integer的position
				mManager.post(position);
			}
		});
	}

	@Override
	public int getItemCount() {
		return 100;
	}

	class MyViewHolder extends RecyclerView.ViewHolder {
		public MyViewHolder(View itemView) {
			super(itemView);
		}
	}
}
