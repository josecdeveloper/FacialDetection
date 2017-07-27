package tutorial.facialdetection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputStream stream = getResources().openRawResource(R.raw.image02);
        Bitmap bitmap = BitmapFactory.decodeStream(stream);

        FaceDetector detector = new FaceDetector.Builder(getApplicationContext())
                .setTrackingEnabled(false)
                .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                .build();

        //Create a frame from the bitmap and run face detection on the frame
        Frame frame = new Frame.Builder().setBitmap(bitmap).build();
        SparseArray<Face> faces = detector.detect(frame);

        CustomView overlay = (CustomView) findViewById(R.id.customView);
        overlay.setContent(bitmap, faces);

        detector.release();

        if(!detector.isOperational()) {
            Toast.makeText(this, "Face detector dependencies are not yet available.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
