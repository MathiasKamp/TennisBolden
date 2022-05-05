package dk.zbc.tennisbolden;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * this class represents the main activity of a moving tennis ball program
 */

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private ImageView imageView;


    public static int xCoordinate = 0;
    public static int yCoordinate = 0;


    /**
     * this method instantiate the elements of the program, imageView, sensorManager and the used sensor (Accelerometer).
     * it gets executed as the program starts up.
     * @param savedInstanceState    : bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageView = findViewById(R.id.iv_ball);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    /**
     * this method gets executed after onCreate
     */
    @Override
    protected void onStart() {
        super.onStart();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    /**
     * This method gets executed after onStart and the MainActivity is now running and is visible
     */
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer,SensorManager.SENSOR_DELAY_FASTEST);
    }

    /**
     * this method gets executed when the activity is no longer visible, it unregisters the sensor from the sensor manager.
     */
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    /**
     * this method gets executed after unPause and unregisters the sensor from the sensor manager.
     */
    @Override
    protected void onStop() {

        sensorManager.unregisterListener(this);
        super.onStop();
    }

    /**
     * this method gets executed whenever the sensor registered changes value.
     * it sets the x and y coordinate registered by the sensor to the imageview.
     * @param sensorEvent  : SensorEvent is an event with the changed values from the sensor.
     */
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){

            xCoordinate -= (int) sensorEvent.values[0];
            yCoordinate += (int) sensorEvent.values[1];

            imageView.setY(yCoordinate);
            imageView.setX(xCoordinate);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}