package iconnect.psi.com.iconnect.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import iconnect.psi.com.iconnect.R;

public class FragmentPurpose extends Fragment implements View.OnClickListener {
    private static final int CAMERA=1;
    private static final int FILE=2;
    private ImageView upload;
    private ImageView camera;
    private Bitmap bitmap;
    private String encodedImage=null;
    private Button goNextPurpose;
    private OnButtonClickListener mOnButtonClickListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_purpose,container,false);

        upload=view.findViewById(R.id.upload);
        camera=view.findViewById(R.id.camera);
        upload.setOnClickListener(this);
        camera.setOnClickListener(this);
        goNextPurpose=view.findViewById(R.id.goNextPurpose);
        goNextPurpose.setOnClickListener(this);
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnButtonClickListener = (OnButtonClickListener)getActivity() ;
        } catch (ClassCastException e) {
            throw new ClassCastException(((Activity) context).getLocalClassName()
                    + " must implement OnButtonClickListener");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.camera:
                final String[] image=new String[]{"Camera","Gallery"};
                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.select_dialog_item,image);
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setTitle("Select Camera or Gallery");
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int media) {
                        if (media==0){
                            //Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            try {
                                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, CAMERA);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            dialog.cancel();
                        }else if (media==1){
                            Intent intent=new Intent();
                            intent.addCategory(Intent.CATEGORY_OPENABLE);
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            startActivityForResult(Intent.createChooser(intent,"media"),FILE);
                        }
                    }
                });
                final AlertDialog dialog=builder.create();
                dialog.show();
                break;
            case R.id.upload:
               File file=new File(Environment.getExternalStorageDirectory(),"Agenda.pdf");
               Uri path=Uri.fromFile(file);
                Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
                pdfOpenintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                pdfOpenintent.setDataAndType(path, "*/pdf*");/**/

/*
                try {
                    startActivity(pdfOpenintent);
                }
                catch (ActivityNotFoundException e) {

                }
*/
                File file1= new File(Environment.getExternalStorageDirectory().getAbsolutePath() +"/"+ "filename");
                Intent pdfViewIntent = new Intent(Intent.ACTION_VIEW);
                pdfViewIntent.setDataAndType(Uri.fromFile(file),"application/pdf");
                pdfViewIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                Intent intent = Intent.createChooser(pdfViewIntent, "Open File");
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Instruct the user to install a PDF reader here, or something
                }
                break;
            case R.id.goNextPurpose:
                mOnButtonClickListener.onButtonClicked(getView());

        }
    }

    interface OnButtonClickListener{
        void onButtonClicked(View view);

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
                        camera.setImageBitmap(bitmap);
                        encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }else {
                    try {
                        bitmap = (Bitmap) data.getExtras().get("data");
                        camera.setImageBitmap(bitmap);
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
