package com.jony.common;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.Handler.Callback;

/**
 * 最底层 抽象Activity。</br> 此Activity 暴露 initDataThread方法 接收 实现线程方法接口类。</br>
 * initDataThread 方法可在 需要 异步操作 数据、网络、图片处理、等耗时操作时，可使用。</br>
 * 
 * @author jony
 */
public abstract class AbsActivity extends Activity implements Callback {
	public Handler mHandler = new Handler(this);
	private AsyncThreadMethod dtm;

	@Override
	public boolean handleMessage(Message arg0) {
		return false;
	}

	/**
	 * 启动底层异步操作线程。</br> 调用该方法后会启动一个线程，去执行 AsyncThreadMethod 的startThread
	 * 方法。</br> 当线程代码执行结束后 会执行 AsyncThreadMethod 的 endThread 方法。</br>
	 * 
	 * @param dtm
	 *            线程执行 方法接口
	 */
	public void initAsyncThread(AsyncThreadMethod dtm) {
		this.dtm = dtm;
		new Thread(new DataThread()).start();
	}

	/**
	 * 一个子线程，可用于一些异步的耗时操作，如加载数据，网络请求等。
	 */
	private class DataThread implements Runnable {
		@Override
		public void run() {
			if (dtm == null) {
				throw new RuntimeException(
						"AsyncThreadMethod is Null~Please initAsyncThread(DataThreadMethod NULL ?)");
			}
			dtm.startThread();
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					dtm.endThread();
				}
			});
		}
	}
}
