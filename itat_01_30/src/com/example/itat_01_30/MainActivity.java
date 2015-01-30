package com.example.itat_01_30;

import java.util.List;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	// 将DbUtils实例化
	DbUtils dbUtils = null;
	CStudent student = null;
	// viewutils
	@ViewInject(R.id.btn)
	private Button btn;
	@ViewInject(R.id.txt)
	private TextView txt;

	@OnClick({ R.id.btn, R.id.txt })
	public void test(View v) {
		switch (v.getId()) {
		case R.id.btn:
			Toast.makeText(MainActivity.this, "创建数据库", Toast.LENGTH_SHORT)
					.show();
			
			student.setSt_Name("张珊");
		
			try {
				dbUtils.save(student);
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.txt:
			try {
				List<CStudent> data = dbUtils.findAll(Selector.from(CStudent.class));
				System.out.println(data.get(1));
				CStudent student = data.get(1);
				System.out.println(student.getSt_Name());
				student.setSt_Name("lizi");
				dbUtils.update(student);
			} catch (DbException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//student.setSt_Name("李四");
			
			break;

		default:
			break;
		}
	}

	// DbUtils

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dbUtils = DbUtils.create(MainActivity.this, "user.db3");
		student = new CStudent();
		// 进行注册
		ViewUtils.inject(this);// 使用xutils
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
