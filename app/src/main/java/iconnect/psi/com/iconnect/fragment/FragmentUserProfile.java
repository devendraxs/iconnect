package iconnect.psi.com.iconnect.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import iconnect.psi.com.iconnect.R;

public class FragmentUserProfile extends Fragment implements View.OnClickListener{
    private CircleImageView userProfile;
    private static final int CAMERA=1;
    private static final int FILE=2;
    private Bitmap bitmap;
    private String encodedImage=null;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.user_profile,container,false);
        userProfile=view.findViewById(R.id.userProfile);
        userProfile.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.userProfile:
                final String[] image=new String[]{"Camera","Gallery"};
                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.select_dialog_item,image);
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setTitle("Select Camera or Gallery");

                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int media) {
                        if (media==0){
                            Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            try {
                                startActivityForResult(intent,CAMERA);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            dialog.cancel();
                        }else if (media==1){
                            Intent intent=new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            startActivityForResult(Intent.createChooser(intent,"media"),FILE);
                        }
                    }
                });
                final AlertDialog dialog=builder.create();
                dialog.show();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (requestCode == 1 && resultCode == getActivity().RESULT_OK) {
                if (requestCode == FILE) {
                    try {
                        Uri selectedImage = data.getData();
                        InputStream imageStream = getActivity().getContentResolver().openInputStream(selectedImage);
                        bitmap = BitmapFactory.decodeStream(imageStream);
                        ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 0,
                                byteArrayBitmapStream);
                        byte[] b = byteArrayBitmapStream.toByteArray();
                        userProfile.setImageBitmap(bitmap);
                        encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }


                }else {
                    try {
                        bitmap = (Bitmap) data.getExtras().get("data");
                        userProfile.setImageBitmap(bitmap);
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }

            }
        }catch (Exception e){
        e.printStackTrace();
        }
    }
    }