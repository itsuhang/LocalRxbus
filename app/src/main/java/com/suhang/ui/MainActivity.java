package com.suhang.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.suhang.localrxbus.R;
import com.suhang.localrxbus.adapter.MainRvAdapter;
import com.suhang.localrxbus.dagger.module.MainModule;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {
	@Inject
	MainRvAdapter mAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
		rv.setLayoutManager(new LinearLayoutManager(this));
		rv.setAdapter(mAdapter);
		subtribeData();
	}

	@Override
	protected void inject() {
		getBaseComponent().providerMainComponent(new MainModule()).inject(this);
	}

	/**
	 * 订阅Integer类型事件
	 */
	private void subtribeData() {
		//这里不需要再考虑回收问题
		mManager.subscribeResult(Integer.class).subscribe(new Consumer<Integer>() {
			@Override
			public void accept(@NonNull Integer integer) throws Exception {
				Toast.makeText(MainActivity.this, "被点到的item为: "+integer, Toast.LENGTH_SHORT).show();
			}
		});
	}
}
