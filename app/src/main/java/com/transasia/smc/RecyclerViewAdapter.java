package com.transasia.smc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by babu.c on 4/18/2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context context;
    private String mNavTitles[];
    private int mIcons[];


    RecyclerViewAdapter(String Titles[], int Icons[], Context cntx) {
        mNavTitles = Titles;
        mIcons = Icons;
        this.context = cntx;

    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);

        ViewHolder vhItem = new ViewHolder(v, viewType, context);

        return vhItem;

    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {


        holder.textView.setText(mNavTitles[position]);
        holder.imageView.setImageResource(mIcons[position]);


    }

    @Override
    public int getItemCount() {
        return mNavTitles.length;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        int Holderid;
        TextView textView;
        ImageView imageView;
        Context contxt;


        public ViewHolder(View itemView, int ViewType, Context c) {
            super(itemView);
            contxt = c;
            itemView.setClickable(true);
            itemView.setOnClickListener(this);

            textView = (TextView) itemView.findViewById(R.id.rowText);
            imageView = (ImageView) itemView.findViewById(R.id.rowIcon);
            Holderid = 1;


        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            HomeActivity user = (HomeActivity) contxt;
            user.Drawer.closeDrawers();
            Intent intent;
            Boolean isInternetPresent = false;
            ConnectionDetector cd = null;
            switch (position) {
                case 0:
                    user.Drawer.closeDrawers();
                    break;
                case 1:
                    intent = new Intent(contxt, AboutActivity.class);
                    contxt.startActivity(intent);
                    break;
                case 2:
                    intent = new Intent(contxt, CorporateServices.class);
                    contxt.startActivity(intent);
                    break;
                case 3:
                    cd = new ConnectionDetector(contxt.getApplicationContext());
                    isInternetPresent = cd.isOnline();

                    if (isInternetPresent) {
                        intent = new Intent(contxt, SMCLocation.class);
                        contxt.startActivity(intent);
                    } else {
                        user.Drawer.openDrawer(Gravity.LEFT);
                        Toast.makeText(contxt, "Please Enable wifi or mobile data", Toast.LENGTH_LONG).show();
                    }
                    break;
                case 4:
                    intent = new Intent(contxt, Contact.class);
                    contxt.startActivity(intent);
                    break;
            }

        }
    }


}
