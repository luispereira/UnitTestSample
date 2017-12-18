package com.lpereira.uts.unittestsample;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.lpereira.uts.unittestsample.entities.App;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lpereira on 17/12/2017.
 */

class AppListAdapter extends ArrayAdapter<App> {

    private final LayoutInflater vi;
    private final int resource;
    private final IMainActivity.View view;
    private final List<App> checkedApps = new ArrayList<>();
    private boolean isNotifyDataSetChanged;

    AppListAdapter(Context context, List<App> list, IMainActivity.View view) {
        super(context, R.layout.app_list_adapter, list);
        resource = R.layout.app_list_adapter;
        vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.view = view;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View retView;
        ViewHolder holder;

        if (convertView == null) {
            retView = vi.inflate(resource, null);
            holder = new ViewHolder();

            holder.image = retView.findViewById(R.id.ivAppIcon);
            holder.name = retView.findViewById(R.id.tvAppName);
            holder.version = retView.findViewById(R.id.tvVersion);
            holder.checkBox = retView.findViewById(R.id.cvToRemove);

            retView.setTag(holder); //error in this line

        } else {
            holder = (ViewHolder) convertView.getTag();
            retView = convertView;
        }

        final App app = getItem(position);
        if (app != null) {
            holder.name.setText(app.getName());
            holder.version.setText(app.getVersionName());
            if (!checkedApps.isEmpty() && checkedApps.contains(app)) {
                holder.checkBox.setChecked(true);
            } else {
                holder.checkBox.setChecked(false);
            }
            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (!isNotifyDataSetChanged) {
                        if (isChecked) {
                            if (checkedApps.isEmpty()) {
                                view.changeUninstallButton(true);
                            }
                            checkedApps.add(app);
                        } else {
                            removeCheckedApps(app);
                            if (checkedApps.isEmpty()) {
                                view.changeUninstallButton(false);
                            }
                        }
                    }
                }
            });
            Drawable drawable = getDrawable(app);
            handleImage(holder.image, drawable);
        }

        if (position == getCount() - 1) {
            isNotifyDataSetChanged = false;
        }

        return retView;
    }

    @Override
    public void notifyDataSetChanged() {
        isNotifyDataSetChanged = true;
        super.notifyDataSetChanged();
    }

    public List<App> getCheckedApps() {
        return checkedApps;
    }

    public void removeCheckedApps(App apps) {
        checkedApps.remove(apps);
    }

    private void handleImage(ImageView imageView, Drawable drawable) {
        if (shouldShowEmptyImage(drawable)) {
            imageView.setImageDrawable(getContext().getDrawable(R.drawable.ic_launcher_background));
        } else {
            imageView.setImageDrawable(drawable);
        }
    }

    private boolean shouldShowEmptyImage(Drawable drawable) {
        return drawable == null;
    }

    private Drawable getDrawable(App app) {
        Drawable drawable = null;
        try {
            drawable = getContext().getPackageManager().getApplicationIcon(app.getPackageName());
        } catch (PackageManager.NameNotFoundException e) {
            //ignore for now
        }
        return drawable;
    }

    static class ViewHolder {
        ImageView image = null;
        TextView name = null;
        TextView version = null;
        CheckBox checkBox = null;
    }
}
