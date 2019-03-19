package iconnect.psi.com.iconnect.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import iconnect.psi.com.iconnect.R;

public class FragmentUserProfile extends Fragment implements View.OnClickListener{
    private CircleImageView userProfile;
    private static final int CAMERA=1;
    private static final int FILE=2;
    private Bitmap bitmap;
    private String encodedImage=null;
    private String emp_name,Designation,CostCenter;
    private TextView name,designation,headQ;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.user_profile,container,false);
        userProfile=view.findViewById(R.id.userProfile);
        userProfile.setOnClickListener(this);
        name=view.findViewById(R.id.name);
        designation=view.findViewById(R.id.designation);
        headQ=view.findViewById(R.id.headQ);

        Bundle bundle=getArguments();
        emp_name=bundle.getString("emp_name");
        Designation=bundle.getString("Designation");
        CostCenter=bundle.getString("CostCenter");

        name.setText(emp_name);
        designation.setText(Designation);
        headQ.setText(CostCenter);
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
                          // Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            try {
                                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, CAMERA);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            dialog.cancel();
                        }else if (media==1){
                            Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                            galleryIntent.setType("image/*");
                            galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                            startActivityForResult(Intent.createChooser(galleryIntent,"media"),FILE);
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
                     Uri selectedImage = data.getData();
                     InputStream imageStream = getActivity().getContentResolver().openInputStream(selectedImage);
                     bitmap = BitmapFactory.decodeStream(imageStream);
                     ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
                     bitmap.compress(Bitmap.CompressFormat.PNG, 0,
                             byteArrayBitmapStream);
                     byte[] b = byteArrayBitmapStream.toByteArray();
                    Bitmap bitmap=(Bitmap) data.getExtras().get("data");
                    userProfile.setImageBitmap(bitmap);
                    encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

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