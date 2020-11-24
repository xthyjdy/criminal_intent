package vch.proj.classes;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vch.proj.R;

public class ListRow extends RecyclerView.ViewHolder {
    public ImageView mThumbnail;

    public ListRow(@NonNull View itemView) {
        super(itemView);

//        mThumbnail = (ImageView)itemView.findViewById(R.id.thumbnail);
    }
}
