package com.suhang.localrxbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.suhang.localrxbus.adapter.MainRvAdapter;
import com.suhang.localrxbus.annotation.ActivityScope;
import com.suhang.localrxbus.application.App;
import com.suhang.localrxbus.dagger.module.ActivityModule;
import com.suhang.localrxbus.function.RxBus;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
@ActivityScope
public class MainActivity extends AppCompatActivity {
	@Inject
	RxBus mRxBus;
	@Inject
	MainRvAdapter mAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		((App)getApplication()).getAppComponent().baseComponent(new ActivityModule(this)).inject(this);
		RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
		rv.setLayoutManager(new LinearLayoutManager(this));
		rv.setAdapter(mAdapter);
		subtribeData();
	}

	/**
	 * 订阅Integer类型事件
	 */
	private void subtribeData() {
		mRxBus.toFlowable(Integer.class).subscribe(new Consumer<Integer>() {
			@Override
			public void accept(@NonNull Integer integer) throws Exception {
				Toast.makeText(MainActivity.this, "被点到的item为: "+integer, Toast.LENGTH_SHORT).show();
			}
		});
	}
}
