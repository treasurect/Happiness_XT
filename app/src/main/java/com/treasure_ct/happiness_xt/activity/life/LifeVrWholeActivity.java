package com.treasure_ct.happiness_xt.activity.life;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;

import java.io.IOException;
import java.io.InputStream;

public class LifeVrWholeActivity extends BaseActivity {
    private VrPanoramaView vrPanoramaView;
    private ImagerLoaderTask imagerLoaderTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_vr_whole);
        vrPanoramaView = (VrPanoramaView) findViewById(R.id.vrPanoramaView);

        //隐藏信息详情  按钮
        vrPanoramaView.setInfoButtonEnabled(false);
        ////隐藏全屏模式按钮
        vrPanoramaView.setFullscreenButtonEnabled(false);

        //切换VR模式
        // 有两个模式：
        // 1.VrWidgetView.DisplayMode.FULLSCREEN_STEREO（手机模式）
        // 2.VrWidgetView.DisplayMode.FULLSCREEN_MONO（默认模式）；
//        vrPanoramaView.setDisplayMode(VrWidgetView.DisplayMode.FULLSCREEN_MONO);

        //设置VR运行状态的监听，如果VR运行出错，可以及时处理；
        vrPanoramaView.setEventListener(new MyVRLEventistener());

        //加载模型
        imagerLoaderTask = new ImagerLoaderTask();
        imagerLoaderTask.execute();
    }
    protected void onPause() {
        //暂停渲染和显示
        vrPanoramaView.pauseRendering();
        super.onPause();
    }

    @Override
    protected void onResume() {
        //继续渲染和显示
        vrPanoramaView.resumeRendering();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        //关闭渲染视图
        vrPanoramaView.shutdown();
        if (imagerLoaderTask != null) {
            //在退出Activity时，如果异步任务没有取消，就需要
            if (!imagerLoaderTask.isCancelled()) {
                imagerLoaderTask.cancel(true);
            }
        }
        super.onDestroy();
    }

    //模型加载
    private class ImagerLoaderTask extends AsyncTask<Void, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                InputStream inputStream = getAssets().open("test_camera.jpg");
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            //创建VrPanoramaView.options，去决定显示VR是普通效果还是立体效果；
            VrPanoramaView.Options options = new VrPanoramaView.Options();
            //TYPE_STEREO_OVER_UNDER立体效果，就是图片的上半部分放在左眼显示，下半部分放在右眼显示；
            //TYPE_MONO 普通效果；
            options.inputType = VrPanoramaView.Options.TYPE_MONO;
            //使用VR控件对象，显示效果 需要两个参数1.Bitmap对象2.VrPanoramaView.options；
            vrPanoramaView.loadImageFromBitmap(bitmap, options);
            super.onPostExecute(bitmap);
        }
    }

    //VR运行状态监听类，自定义一个类继承
    private class MyVRLEventistener extends VrPanoramaEventListener {

        //当VR视图加载成功时回调；
        @Override
        public void onLoadSuccess() {
            super.onLoadSuccess();
            Toast.makeText(LifeVrWholeActivity.this, "加载成功", Toast.LENGTH_SHORT).show();
        }

        //当VR视图加载失败时回调；
        @Override
        public void onLoadError(String errorMessage) {
            super.onLoadError(errorMessage);
            Toast.makeText(LifeVrWholeActivity.this, "加载成功", Toast.LENGTH_SHORT).show();
        }
    }
}
