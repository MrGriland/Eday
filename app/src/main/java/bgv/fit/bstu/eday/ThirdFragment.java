package bgv.fit.bstu.eday;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class ThirdFragment extends Fragment {

    private ThirdViewModel mViewModel;
    EditText surname,name;
    TextView login;
    ImageView imageView;

    public static ThirdFragment newInstance() {
        return new ThirdFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.third_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    public void init(){
        surname = getView().findViewById(R.id.surnamet);
        name = getView().findViewById(R.id.namet);
        imageView = (ImageView) getView().findViewById(R.id.imageViewt);
        login = getView().findViewById(R.id.logint);
        surname.setText(MainActivity.UserSurname);
        name.setText(MainActivity.UserName);
        try {
            Bitmap bitmap = BitmapFactory.decodeByteArray(MainActivity.UserPhoto, 0, MainActivity.UserPhoto.length);
            imageView.setImageBitmap(bitmap);
        }
        catch (Exception e) {}
        login.setText(MainActivity.UserLogin);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ThirdViewModel.class);
        // TODO: Use the ViewModel
    }

}