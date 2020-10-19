package com.example.lg_animal.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.lg_animal.R;
import com.example.lg_animal.data.PetInfoSharedPreference;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class PetRegisterActivity extends AppCompatActivity {

    private final String[] petGender = { "남", "여"};

    private TextView tvPetGender;
    private EditText etPetName;
    private EditText etPetNumber;
    private EditText etPetAge;
    private EditText etPetWeight;
    private EditText etPetBCS;
    private EditText etPetKind;
    private ImageView ivPetImg;
    private String imageFilePath;
    private boolean pictureSuccess = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        initView();
    }

    private void initView() {
        tvPetGender = findViewById(R.id.tv_pet_gender);
        etPetName = findViewById(R.id.et_pet_name);
        etPetNumber = findViewById(R.id.et_pet_pass_number);
        etPetAge = findViewById(R.id.et_pet_age);
        etPetWeight = findViewById(R.id.et_pet_weight);
        etPetBCS = findViewById(R.id.et_pet_weight_bcs);
        etPetKind = findViewById(R.id.et_pet_weight_kind);

        ivPetImg = findViewById(R.id.iv_pet_img_settings);
        Button btnOK = findViewById(R.id.btn_manage_ok);
        Button btnCancel = findViewById(R.id.btn_manage_cancel);

        ivPetImg.setOnClickListener(mOnClick);
        tvPetGender.setOnClickListener(mOnClick);
        btnOK.setOnClickListener(mOnClick);
        btnCancel.setOnClickListener(mOnClick);
    }

    private void setPickerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(petGender, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int pos) {
                tvPetGender.setText(petGender[pos]);
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void setPetFinalInfoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        StringBuffer resultStr = new StringBuffer();
        resultStr.append(getString(R.string.pet_account_sub_title_name));
        resultStr.append(" ");
        resultStr.append(etPetName.getText());
        resultStr.append("\n");
        resultStr.append(getString(R.string.pet_account_sub_title_gender));
        resultStr.append(" ");
        resultStr.append(tvPetGender.getText());
        resultStr.append("\n");
        resultStr.append(getString(R.string.pet_account_sub_title_number));
        resultStr.append(" ");
        resultStr.append(etPetNumber.getText());
        resultStr.append("\n");
        resultStr.append(getString(R.string.pet_account_sub_title_age));
        resultStr.append(" ");
        resultStr.append(etPetAge.getText());
        resultStr.append("\n");
        resultStr.append(getString(R.string.pet_account_sub_title_weight));
        resultStr.append(" ");
        resultStr.append(etPetWeight.getText());
        resultStr.append("\n");
        resultStr.append(getString(R.string.pet_account_sub_title_bcs));
        resultStr.append(" ");
        resultStr.append(etPetBCS.getText());
        resultStr.append("\n");
        resultStr.append(getString(R.string.pet_account_sub_title_kind));
        resultStr.append(" ");
        resultStr.append(etPetKind.getText());
        resultStr.append("\n");
        resultStr.append("\n");
        resultStr.append(getString(R.string.pet_account_info_success_msg));
        builder.setTitle(R.string.pet_account_title).setMessage(resultStr);

        builder.setPositiveButton(R.string.btn_text_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (PetInfoSharedPreference.getInt(PetRegisterActivity.this,"pet_number") == 0) {
                    PetInfoSharedPreference.setInt(PetRegisterActivity.this, "pet_number", 1);
                }
                int position = PetInfoSharedPreference.getInt(PetRegisterActivity.this, "pet_number");
                PetInfoSharedPreference.setString(PetRegisterActivity.this, "pet_name" + position, etPetName.getText().toString());
                PetInfoSharedPreference.setString(PetRegisterActivity.this, "pet_gender" + position, tvPetGender.getText().toString());
                PetInfoSharedPreference.setString(PetRegisterActivity.this, "pet_number" + position, etPetNumber.getText().toString());
                PetInfoSharedPreference.setString(PetRegisterActivity.this, "pet_age" + position, etPetAge.getText().toString());
                PetInfoSharedPreference.setString(PetRegisterActivity.this, "pet_weight" + position, etPetWeight.getText().toString());
                PetInfoSharedPreference.setString(PetRegisterActivity.this, "pet_bcs" + position, etPetBCS.getText().toString());
                PetInfoSharedPreference.setString(PetRegisterActivity.this, "pet_kind" + position, etPetKind.getText().toString());
                PetInfoSharedPreference.setString(PetRegisterActivity.this, "pet_picture" + position, imageFilePath);

                Toast.makeText(PetRegisterActivity.this, getString(R.string.pet_account_success), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(PetRegisterActivity.this, SettingMainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        builder.setNegativeButton(R.string.btn_text_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(PetRegisterActivity.this, getString(R.string.pet_account_info_check_msg), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private View.OnClickListener mOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_manage_ok:
                    if (petSettingsApply()) {
                        setPetFinalInfoDialog();
                    }
                    break;
                case R.id.btn_manage_cancel:
                    Toast.makeText(PetRegisterActivity.this, getString(R.string.pet_account_cancel_message), Toast.LENGTH_SHORT).show();
                    onBackPressed();
                    break;
                case R.id.iv_pet_img_settings:
                    sendTakePhotoIntent();
                    break;
                case R.id.tv_pet_gender:
                    setPickerDialog();
                    break;
            }
        }
    };

    private boolean petSettingsApply() {
        if (etPetName.getText().length() == 0) {
            Toast.makeText(PetRegisterActivity.this, getString(R.string.pet_account_name_value_null), Toast.LENGTH_SHORT).show();
            return false;
        } else if (!tvPetGender.getText().equals(petGender[0]) && !tvPetGender.getText().equals(petGender[1])) {
            Toast.makeText(PetRegisterActivity.this, getString(R.string.pet_account_gender_value_null), Toast.LENGTH_SHORT).show();
            return false;
        } else if (etPetNumber.getText().length() == 0) {
            Toast.makeText(PetRegisterActivity.this, getString(R.string.pet_account_number_value_null), Toast.LENGTH_SHORT).show();
            return false;
        } else if (etPetAge.getText().length() == 0) {
            Toast.makeText(PetRegisterActivity.this, getString(R.string.pet_account_number_value_age), Toast.LENGTH_SHORT).show();
            return false;
        } else if (etPetWeight.getText().length() == 0) {
            Toast.makeText(PetRegisterActivity.this, getString(R.string.pet_account_number_value_weight), Toast.LENGTH_SHORT).show();
            return false;
        } else if (etPetBCS.getText().length() == 0) {
            Toast.makeText(PetRegisterActivity.this, getString(R.string.pet_account_number_value_bcs), Toast.LENGTH_SHORT).show();
            return false;
        } else if (etPetKind.getText().length() == 0) {
            Toast.makeText(PetRegisterActivity.this, getString(R.string.pet_account_number_value_kind), Toast.LENGTH_SHORT).show();
            return false;
        } else if (!pictureSuccess) {
            Toast.makeText(PetRegisterActivity.this, getString(R.string.pet_account_picture_value_null), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 999 && resultCode == RESULT_OK) {
            Bitmap bitmap = BitmapFactory.decodeFile(imageFilePath);
            ExifInterface exif = null;

            try {
                exif = new ExifInterface(imageFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

            int exifOrientation;
            int exifDegree;

            if (exif != null) {
                exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                exifDegree = exifOrientationToDegrees(exifOrientation);
            } else {
                exifDegree = 0;
            }

            pictureSuccess = true;
            ivPetImg.setImageBitmap(rotate(bitmap, exifDegree));
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.KOREA).toString();
        String imageFileName = "TEST_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,      /* prefix */
                ".jpg",         /* suffix */
                storageDir          /* directory */
        );
        imageFilePath = image.getAbsolutePath();
        return image;
    }

    private void sendTakePhotoIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            if (photoFile != null) {
                Uri photoUri = FileProvider.getUriForFile(this, getPackageName(), photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePictureIntent, 999);
            }
        }
    }

    private int exifOrientationToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }

    private Bitmap rotate(Bitmap bitmap, float degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
}
