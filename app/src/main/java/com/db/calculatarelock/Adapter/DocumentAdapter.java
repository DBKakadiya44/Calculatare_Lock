package com.db.calculatarelock.Adapter;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.db.calculatarelock.R;
import com.db.calculatarelock.nav_Activitys.DocumentActivity;
import java.io.File;
import java.util.ArrayList;

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.ViewHolder>
{
    DocumentActivity documentActivity;
    ArrayList documentname;
    ArrayList documentpath;

    public DocumentAdapter(DocumentActivity documentActivity, ArrayList documentname, ArrayList documentpath) {
        this.documentActivity = documentActivity;
        this.documentname = documentname;
        this.documentpath = documentpath;
    }

    @NonNull
    @Override
    public DocumentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_document,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentAdapter.ViewHolder holder, int position) {
        holder.textView.setText(""+documentname.get(position).toString());

        holder.imageView.setOnClickListener(view -> {
            openDocument(documentpath.get(position).toString());
        });
    }

    @Override
    public int getItemCount() {
        return documentpath.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ConstraintLayout imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView5);
            textView = itemView.findViewById(R.id.docname);
        }
    }

    private void openDocument(String documentPath) {
        File file = new File(documentPath);
        Uri uri;
        Intent intent = new Intent(Intent.ACTION_VIEW);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(documentActivity, documentActivity.getApplicationContext().getPackageName() + ".provider", file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(file);
        }

        String mimeType = getMimeType(file.getAbsolutePath());
        intent.setDataAndType(uri, mimeType);

        try {
            documentActivity.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(documentActivity, "No application available to open the document", Toast.LENGTH_SHORT).show();
        }
    }

    public static String getMimeType(String filePath) {
        String extension = getFileExtension(filePath);
        if (extension != null) {
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.toLowerCase());
        }
        return null;
    }

    private static String getFileExtension(String filePath) {
        int lastDotPosition = filePath.lastIndexOf(".");
        if (lastDotPosition > 0) {
            return filePath.substring(lastDotPosition + 1);
        }
        return null;
    }
}
