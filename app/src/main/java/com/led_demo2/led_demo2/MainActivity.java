package com.led_demo2.led_demo2;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.audiofx.Equalizer;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int red_state=0;

    int blue_state=0;
    int light_red_state=0;
    int light_green_state=0;
    int light_blue_state=0;
    private static final String TAG = "led_demo2";
    float last_rad=0;
    float gravityNew = 0;
    private Equalizer mEqualizer;
    private static final String CHARGER_CURRENT_NOW =
            "/sys/class/power_supply/battery/BatteryAverageCurrent";
    Handler mHandler;
    BatteryReceiver batteryReceiver=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
      // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button0).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);
        findViewById(R.id.button10).setOnClickListener(this);
        findViewById(R.id.button11).setOnClickListener(this);
        Log.d(TAG, "onCreate");

        IntentFilter IntentFilter = new IntentFilter("android.intent.action.cz.gsensorcali.success");
        IntentFilter.addAction("android.intent.action.cz.gsensorcali.fail");
        IntentFilter.addAction("android.intent.action.cz.Infrared");
        IntentFilter.addAction("com.auric.intell.xld.rbt.key.power.down");
        IntentFilter.addAction("com.auric.intell.xld.rbt.key.power.up");
        IntentFilter.addAction("com.auric.intell.xld.rbt.key.head.down");
        IntentFilter.addAction("com.auric.intell.xld.rbt.key.head.up");

        IntentFilter.addAction("com.auric.intell.xld.rbt.face.l.down");
        IntentFilter.addAction("com.auric.intell.xld.rbt.face.l.up");
        IntentFilter.addAction("com.auric.intell.xld.rbt.face.r.down");
        IntentFilter.addAction("com.auric.intell.xld.rbt.face.r.up");
        IntentFilter.addAction("com.auric.intell.xld.rbt.face.head.down");
        IntentFilter.addAction("com.auric.intell.xld.rbt.face.head.up");
        IntentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        IntentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        IntentFilter.addAction("com.auric.intell.xld.rbt.event.shutdown");
        IntentFilter.addAction("com.auric.intell.xld.rbt.event.lowbattery");
        IntentFilter.addAction("com.auric.intell.xld.rbt.event.lowchg.current");
        IntentFilter.addAction("com.auric.intell.xld.rbt.stand");
        IntentFilter.addAction("com.auric.intell.xld.rbt.entry.factorymode");




        registerReceiver(mBroadcastReceiver, IntentFilter);
        //Runservice();

        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        batteryReceiver = new BatteryReceiver();
        registerReceiver(batteryReceiver, intentFilter);

        //run_thread();
       //g_sensor_receiver();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

/*
        SensorManager sm = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        sm.registerListener(new SensorEventListener() {

            public void onSensorChanged(SensorEvent event) {
                if (Sensor.TYPE_ACCELEROMETER != event.sensor.getType()) {
                    return;
                }

                float[] values = event.values;
                float ax = values[0];
                float ay = values[1];
                float az = values[2];

                gravityNew = (float) Math.sqrt(values[0] * values[0]
                        + values[1] * values[1] + values[2] * values[2]);


               // Log.d(TAG, "ax="+ax +" ay="+ay +" az="+az +"  gravityNew="+gravityNew);
               // Log.d(TAG, "gravityNew="+gravityNew );

                int medumValue = 19;//
                if (Math.abs(ax) > medumValue || Math.abs(ay) > medumValue || Math.abs(az) > medumValue) {
                   // playdora();

                }

                // TODO Have fun with the acceleration components...

            }

            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        }, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_FASTEST);
*/
     // test_speaker();


       // ledtest_thread();

        //test_mic();

       // run_thread();

    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        //
        unregisterReceiver(mBroadcastReceiver);
        unregisterReceiver(batteryReceiver);

        Log.d(TAG, "onDestory");
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // 当前为横屏
            Log.d(TAG, "ORIENTATION_LANDSCAPE=" + Configuration.ORIENTATION_LANDSCAPE);// 2
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // 当前为竖屏
            Log.d(TAG, "ORIENTATION_PORTRAIT=" + Configuration.ORIENTATION_PORTRAIT);// 1
        }
    }
    private void g_sensor_receiver(){
        SensorManager sm = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        sm.registerListener(new SensorEventListener() {

            public void onSensorChanged(SensorEvent event) {
                if (Sensor.TYPE_ACCELEROMETER != event.sensor.getType()) {
                    return;
                }

                float[] values = event.values;
                float ax = values[0];
                float ay = values[1];
                float az = values[2];
                float gravityNew = 0;

                gravityNew = (float) Math.sqrt(values[0] * values[0]
                        + values[1] * values[1] + values[2] * values[2]);

                Log.d(TAG, "ax="+ax +" ay="+ay +" az="+az +"  gravityNew="+gravityNew);

                calc_gsensor_data(ax,ay,az,gravityNew);


            }

            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        }, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }
    class BatteryReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if(Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())){
                int level = intent.getIntExtra("level", 0);
                int scale = intent.getIntExtra("scale", 100);
                Log.d(TAG, "battery level:"+(level*100)/scale);

                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                        status == BatteryManager.BATTERY_STATUS_FULL;

                Log.d(TAG, "isCharging"+isCharging);

                try {
                    Log.d(TAG, readCurrentFile(new File(CHARGER_CURRENT_NOW))+" mA");
                   // Toast.makeText(getApplicationContext(), readCurrentFile(new File(CHARGER_CURRENT_NOW))+" mA",Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                //Toast.makeText(getApplicationContext(), "battery level:"+(level*100)/scale,Toast.LENGTH_SHORT).show();


            }
        }

    }
    public String readCurrentFile(File file) throws IOException {
        InputStream input = new FileInputStream(file);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    input));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } finally {
            input.close();
        }
    }
    float gsensor_x[]={0,0,0,0,5,0,0,0,0,10,0,0,0,0,15};
    float gsensor_y[]={0,0,0,0,5,0,0,0,0,10,0,0,0,0,15};
    float gsensor_z[]={0,0,0,0,5,0,0,0,0,10,0,0,0,0,15};
    float gravity_ar[]={0,0,0,0,5,0,0,0,0,10,0,0,0,0,15};
    int gsensor_static_state=0;
    public void calc_gsensor_data(float ax,float ay,float az,float gravity){

        for(int i=15-1;i>0;i--){
            gsensor_x[i]= gsensor_x[i-1];
            gsensor_y[i]= gsensor_y[i-1];
            gsensor_z[i]= gsensor_z[i-1];
            gravity_ar[i]= gravity_ar[i-1];
        }
        gsensor_x[0]=ax;
        gsensor_x[0]=ay;
        gsensor_x[0]=az;
        gravity_ar[0]=gravity;
        float average_x =0;
        float average_y =0;
        float average_z =0;
        float average_gravity =0;
        for(int i=0;i<15;i++){
            average_x+=gsensor_x[i];
            average_y+=gsensor_y[i];
            average_z+=gsensor_z[i];
            average_gravity+=gravity_ar[i];
        }
        average_x =average_x/15;
        average_y =average_y/15;
        average_z =average_z/15;
        average_gravity =average_gravity/15;
       // if(Math.abs(average_x)<1)


    }

    MediaPlayer mp=null;
    public void test_speaker(){
        if(mp!=null){
            mp.stop();
            mp.release();
            mp=null;
        }
        if(mp == null){
            mp = MediaPlayer.create(this, R.raw.haorizi);//tada
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);

        }
        try {
            mp.prepare();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mp.setLooping(true);

        mEqualizer = new Equalizer(0, mp.getAudioSessionId());
        // 启用均衡控制效果
        mEqualizer.setEnabled(true);
        mp.start();



        //test_speaker_complete();
    }
    private int direct=0;
    static PowerManager pm = null;
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button0: {
                if (red_state == 0) {
                    Intent intent = new Intent("android.intent.action.cszj.gsensorcali");
                    intent.putExtra("data", "hello");
                    sendBroadcast(intent);
                    Log.d(TAG, "button0");
                } else {
                    Intent intent = new Intent("android.intent.action.ACTION_LED_R_OFF");
                    intent.putExtra("data", "hello");
                    sendBroadcast(intent);
                    Log.d(TAG, "button0 1");
                }
                break;
            }
            case R.id.button1: {
                AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

                int max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                int current = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                Log.d(TAG, "current vol=" + current+"max vol=" + max);

                if(current+1 >max)
                {
                    direct=1;
                }
                else if(current-1 <=0)
                {
                    direct=0;
                }
                direct=0;
                if(direct==1)
                {
                    mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
                }
                else
                {
                    mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
                }
                current = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                Log.d(TAG, "current vol=" + current);
                break;
            }
            case R.id.button2: {
                AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

                int max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                int current = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                Log.d(TAG, "current vol=" + current+"max vol=" + max);

                if(current+1 >max)
                {
                    direct=1;
                }
                else if(current-1 <=0)
                {
                    direct=0;
                }
                direct=1;
                if(direct==1)
                {
                    mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
                }
                else
                {
                    mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
                }
                current = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                Log.d(TAG, "current vol=" + current);
                break;
            }
            case R.id.button3: {
              // Runservice();

                Intent intent = new Intent("com.auric.intell.xld.os.gotosleep");
                intent.putExtra("data", "hello");
                sendBroadcast(intent);

               // wakehandler.sendEmptyMessageDelayed(0, 5000);

                break;




                // test_speaker();
                //test_mic();
            }
            case R.id.button4: {

                    Intent intent = new Intent("com.auric.intell.xld.os.led.r.on");
                    intent.putExtra("data", "hello");
                    sendBroadcast(intent);


                Log.d(TAG, "light_red_state=" + light_red_state);
                break;
            }
            case R.id.button5: {

                    Intent intent = new Intent("com.auric.intell.xld.os.led.g.on");
                    intent.putExtra("data", "hello");
                    sendBroadcast(intent);


                Log.d(TAG, "light_green_state=" + light_green_state);
                break;
            }
            case R.id.button6: {

                    Intent intent = new Intent("com.auric.intell.xld.os.led.b.on");
                    intent.putExtra("data", "hello");
                    sendBroadcast(intent);


                Log.d(TAG, "light_blue_state=" + light_blue_state);
                break;
            }
            case R.id.button7: {

                    Intent intent = new Intent("com.auric.intell.xld.os.led.p.on");
                    intent.putExtra("data", "hello");
                    sendBroadcast(intent);

                break;
            }
            case R.id.button8: {

                    Intent intent = new Intent("com.auric.intell.xld.os.led.y.on");
                    intent.putExtra("data", "hello");
                    sendBroadcast(intent);

                break;
            }
            case R.id.button9: {

                    Intent intent = new Intent("com.auric.intell.xld.os.led.r.blink.fast");
                    intent.putExtra("data", "hello");
                    sendBroadcast(intent);

                break;
            }
            case R.id.button10: {

                    Intent intent = new Intent("com.auric.intell.xld.os.led.b.blink.slow");
                    intent.putExtra("data", "hello");
                    sendBroadcast(intent);

                break;
            }
            case R.id.button11: {

                    Intent intent = new Intent("com.auric.intell.xld.os.led.all.off");
                    intent.putExtra("data", "hello");
                    sendBroadcast(intent);

                break;
            }
        }
    }
    static Process createSuProcess() throws IOException  {
        File rootUser = new File("/system/xbin/ru");
        if(rootUser.exists()) {
            return Runtime.getRuntime().exec(rootUser.getAbsolutePath());
        } else {
            return Runtime.getRuntime().exec("su");
        }
    }

    static Process createSuProcess(String cmd) throws IOException {

        DataOutputStream os = null;
        Process process = createSuProcess();

        try {
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(cmd + "\n");
            os.writeBytes("exit $?\n");
        } finally {
            if(os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                }
            }
        }

        return process;
    }
    private void Runservice() {
        final Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.mediatek.engineermode", "com.mediatek.engineermode.sensor.SensorListenerService"));
        startService(intent);
        Log.d(TAG, "Runservice");
    }

    public Handler runapphandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    runapphandler1.sendEmptyMessageDelayed(0, 5000);
                    Intent intent = new Intent("com.auric.intell.xld.rbt.key.head.up");
                    intent.putExtra("data", "hello");
                    sendBroadcast(intent);
                    Log.d(TAG, "sleep 1 s");
                    break;
                default:
                    break;
            }
        }
    };

    public Handler runapphandler1 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    runapphandler.sendEmptyMessageDelayed(0, 5000);
                    Intent intent = new Intent("com.auric.intell.xld.rbt.key.head.up");
                    intent.putExtra("data", "hello");
                    sendBroadcast(intent);
                    Log.d(TAG, "sleep 1 s");
                    break;
                default:
                    break;
            }
        }
    };
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "BroadcastReceiver");

            final String action = intent.getAction();
            if("android.intent.action.cz.gsensorcali.success".equals(action)){
                Log.d(TAG, "onReceive result=success");
                Toast.makeText(getApplicationContext(), "Gsensor校准成功",
                        Toast.LENGTH_SHORT).show();
            }
            else if("android.intent.action.cz.gsensorcali.fail".equals(action))
            {
                Log.d(TAG, "onReceive result=fail");
                Toast.makeText(getApplicationContext(), "Gsensor校准失败",
                        Toast.LENGTH_SHORT).show();
            }
            else if("com.auric.intell.xld.rbt.key.power.down".equals(action))
            {
                Toast.makeText(getApplicationContext(), "关机键按下",
                        Toast.LENGTH_SHORT).show();
                playbeep();
            }
            else if("com.auric.intell.xld.rbt.key.power.up".equals(action))
            {
                Toast.makeText(getApplicationContext(), "关机键抬起",
                        Toast.LENGTH_SHORT).show();
                playbeep();
                runapphandler.sendEmptyMessageDelayed(0, 5000);
            }
            else if("com.auric.intell.xld.rbt.key.head.down".equals(action))
            {
                Toast.makeText(getApplicationContext(), "头灯键按下",
                        Toast.LENGTH_SHORT).show();
               // playbeep();
                //test_mic();
            }
            else if("com.auric.intell.xld.rbt.key.head.up".equals(action))
            {
                Toast.makeText(getApplicationContext(), "头灯键抬起",
                        Toast.LENGTH_SHORT).show();
                Log.d(TAG, "head");
                //playbeep();

                if(is_recording == 0){
                    test_speaker();
                    test_mic();
                }
                else if(is_recording==1){
                    play_record();

                }
                else if(is_recording==2){
                    test_speaker();
                    test_mic();
                }
                is_recording++;
                if(is_recording>1)
                    is_recording=0;

            }
            else if("com.auric.intell.xld.rbt.face.l.down".equals(action))
            {
                Toast.makeText(getApplicationContext(), "左脸按下",
                        Toast.LENGTH_SHORT).show();
                playbeep();
            }
            else if("com.auric.intell.xld.rbt.face.l.up".equals(action))
            {
                Toast.makeText(getApplicationContext(), "左脸抬起",
                        Toast.LENGTH_SHORT).show();
                playbeep();
            }
            else if("com.auric.intell.xld.rbt.face.r.down".equals(action))
            {
                Toast.makeText(getApplicationContext(), "右脸按下",
                        Toast.LENGTH_SHORT).show();
                playbeep();
            }
            else if("com.auric.intell.xld.rbt.face.r.up".equals(action))
            {
                Toast.makeText(getApplicationContext(), "右脸抬起",
                        Toast.LENGTH_SHORT).show();
                playbeep();
            }
            else if("com.auric.intell.xld.rbt.face.head.down".equals(action))
            {
                Toast.makeText(getApplicationContext(), "头顶触摸按下",
                        Toast.LENGTH_SHORT).show();
                playbeep();
                //test_mic();
            }
            else if("com.auric.intell.xld.rbt.face.head.up".equals(action))
            {
                Toast.makeText(getApplicationContext(), "头顶触摸抬起",
                        Toast.LENGTH_SHORT).show();
                playbeep();
                //play_record();
            }
            else if("android.intent.action.cz.Infrared".equals(action))
            {
                //playbeep();
                /*
                if(mp == null){
                    mp = MediaPlayer.create(context, R.raw.beep_once);//tada
                    mp.setAudioStreamType(AudioManager.STREAM_MUSIC);

                }
                try {
                    mp.prepare();
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                mp.setLooping(false);


                mp.start();

                Toast.makeText(getApplicationContext(), "人体检测感应低",
                        Toast.LENGTH_SHORT).show();
                        */
            }
            else if("android.intent.action.ACTION_POWER_CONNECTED".equals(action))
            {
                Toast.makeText(getApplicationContext(), "充电器插入", Toast.LENGTH_SHORT).show();
            }
            else if("android.intent.action.ACTION_POWER_DISCONNECTED".equals(action))
            {
                Toast.makeText(getApplicationContext(), "充电器拔出", Toast.LENGTH_SHORT).show();
            }
            else if("com.auric.intell.xld.rbt.event.shutdown".equals(action))
            {
//                Intent intentBc = new Intent("com.auric.intell.xld.os.poweroff");
//                intentBc.putExtra("data", "hello");
//                sendBroadcast(intentBc);

                try {
                    createSuProcess("reboot -p").waitFor(); //关机命令
                    //createSuProcess("reboot").waitFor(); //这个部分代码是用来重启的
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if("com.auric.intell.xld.rbt.event.lowbattery".equals(action))
            {
                Toast.makeText(getApplicationContext(), "低电量", Toast.LENGTH_SHORT).show();
            }
            else if("com.auric.intell.xld.rbt.event.lowchg.current".equals(action))
            {
                Toast.makeText(getApplicationContext(), "请更换充电器", Toast.LENGTH_SHORT).show();
            }
            else if("com.auric.intell.xld.rbt.stand".equals(action))
            {
               // Toast.makeText(getApplicationContext(), "xxx", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "com.auric.intell.xld.rbt.stand");
            }
            else if("com.auric.intell.xld.rbt.entry.factorymode".equals(action))
            {
                // Toast.makeText(getApplicationContext(), "xxx", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "com.auric.intell.xld.rbt.factorymode");
                Toast.makeText(getApplicationContext(), "工厂模式", Toast.LENGTH_SHORT).show();
            }


        }
    };

    private void gsensor_Listener(){

    }

    public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
    {

        Log.d(TAG, "paramInt="+paramInt);
        if(paramInt == 4){//返回键

            Log.d(TAG, "onKeyDown");

        }

        return super.onKeyDown(paramInt, paramKeyEvent);


    }

    public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
    {

        Log.d(TAG, "paramInt="+paramInt);
        if(paramInt == 4){//返回键

            Log.d(TAG, "onKeyDown");

        }

        return false;

    }

    public void run_thread(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {



                        Intent intent = new Intent("com.auric.intell.xld.rbt.key.head.up");
                        intent.putExtra("data", "hello");
                        sendBroadcast(intent);
                        Log.d(TAG, "sleep 1 s");
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            }
        });
        thread.start();
    }

    private void playbeep() {
        AudioRecord ar;

        MediaPlayer mpplaybeep= new MediaPlayer();
        mpplaybeep.reset();
        mpplaybeep = MediaPlayer.create(this, R.raw.beep_once);//

        //MediaRecorder.AudioSource

        //MediaRecorder.AudioSource.VOICE_CALL
        mpplaybeep.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();


            }

        });

        try {
            mpplaybeep.prepare();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mpplaybeep.start();

    }
    private void playdora() {
        AudioRecord ar;

        MediaPlayer mpplaybeep= new MediaPlayer();
        mpplaybeep.reset();
        mpplaybeep = MediaPlayer.create(this, R.raw.dora);//

        //MediaRecorder.AudioSource

        //MediaRecorder.AudioSource.VOICE_CALL
        mpplaybeep.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();


            }

        });

        try {
            mpplaybeep.prepare();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mpplaybeep.start();

    }
    File recAudioFile;
    MediaRecorder mMediaRecorder=null;
    int is_recording=0;
    public void test_mic(){
        recAudioFile = new File("/mnt/sdcard", "new.amr");
        mMediaRecorder = new MediaRecorder();

        if (recAudioFile.exists()) {
            recAudioFile.delete();
        }
        Context context = getApplicationContext();
       // mContext = getApplicationContext();
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

       // AudioManager audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setParameters("ForceUseSpecificMic=0");
        audioManager.setParameters("SET_LOOPBACK_TYPE=1,1");




        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        mMediaRecorder.setOutputFile(recAudioFile.getAbsolutePath());

        try {
            mMediaRecorder.prepare();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        mMediaRecorder.start();
        Log.e(TAG, "mMediaRecorder start");

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }//1秒之后停止录音







    }
    public void play_record(){



        //停止录音
        if ((recAudioFile!=null)&&(mMediaRecorder!=null)) {
            mMediaRecorder.stop();
            mMediaRecorder.release();
            mMediaRecorder=null;
        }
        Log.e(TAG, "mMediaRecorder stop");


        //播放录音
        if(recAudioFile!=null){
            if(mp == null && recAudioFile.exists()){
                mp = MediaPlayer.create(this, Uri.fromFile(recAudioFile));//
            }
            mp.setLooping(false);
            mp.start();
        }
    }
    int led_state=0;
    Thread thread =null;
    public void ledtest_thread(){

         thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        led_state++;
                        if(led_state>2)
                            led_state=0;

                        Intent intent=null;

                        switch(led_state)
                        {
                            case 0:
                                intent = new Intent("com.auric.intell.xld.os.led.r.on");
                                intent.putExtra("data", "hello");
                                sendBroadcast(intent);
                                intent = new Intent("com.auric.intell.xld.os.led.g.off");
                                intent.putExtra("data", "hello");
                                sendBroadcast(intent);
                                intent = new Intent("com.auric.intell.xld.os.led.b.off");
                                intent.putExtra("data", "hello");
                                sendBroadcast(intent);
                                break;

                            case 1:
                                intent = new Intent("com.auric.intell.xld.os.led.r.off");
                                intent.putExtra("data", "hello");
                                sendBroadcast(intent);
                                intent = new Intent("com.auric.intell.xld.os.led.g.on");
                                intent.putExtra("data", "hello");
                                sendBroadcast(intent);
                                intent = new Intent("com.auric.intell.xld.os.led.b.off");
                                intent.putExtra("data", "hello");
                                sendBroadcast(intent);
                                break;

                            case 2:
                                intent = new Intent("com.auric.intell.xld.os.led.r.off");
                                intent.putExtra("data", "hello");
                                sendBroadcast(intent);
                                intent = new Intent("com.auric.intell.xld.os.led.g.off");
                                intent.putExtra("data", "hello");
                                sendBroadcast(intent);
                                intent = new Intent("com.auric.intell.xld.os.led.b.on");
                                intent.putExtra("data", "hello");
                                sendBroadcast(intent);
                                break;

                        }
                        Thread.sleep(800);
                        Log.d(TAG, "sleep 1 s");
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            }
        });
        thread.start();
    }
    public Handler wakehandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Intent intent = new Intent("com.auric.intell.xld.os.wakeUp");
                    intent.putExtra("data", "hello");
                    sendBroadcast(intent);

                    break;
                default:
                    break;
            }
        }
    };


}
