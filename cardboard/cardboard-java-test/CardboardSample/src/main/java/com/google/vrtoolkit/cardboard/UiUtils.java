//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.vrtoolkit.cardboard;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import android.util.Log;

@UsedByNative
class UiUtils
{
    private static final String CARDBOARD_WEBSITE =
        "http://google.com/cardboard/cfg?vrtoolkit_version=0.5.4";
    private static final String CARDBOARD_CONFIGURE_ACTION =
        "com.google.vrtoolkit.cardboard.CONFIGURE";
    private static final String INTENT_EXTRAS_VERSION_KEY = "VERSION";
    private static final String NO_BROWSER_TEXT = "No browser to open website.";
    private static final String INTENT_KEY = "intent";

    UiUtils()
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

    }

    @UsedByNative
    static void launchOrInstallCardboard(Context context)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        PackageManager pm = context.getPackageManager();
        Intent settingsIntent = new Intent();
        settingsIntent.setAction("com.google.vrtoolkit.cardboard.CONFIGURE");
        settingsIntent.putExtra("VERSION", "0.5.4");
        List resolveInfos = pm.queryIntentActivities(settingsIntent, 0);
        ArrayList intentsToGoogleCardboard = new ArrayList();
        Iterator i$ = resolveInfos.iterator();

        while (i$.hasNext()) {
            ResolveInfo info = (ResolveInfo) i$.next();
            String pkgName = info.activityInfo.packageName;

            if (pkgName.startsWith("com.google.")) {
                Intent intent = new Intent(settingsIntent);
                intent.setClassName(pkgName, info.activityInfo.name);
                intentsToGoogleCardboard.add(intent);
            }
        }

        if (intentsToGoogleCardboard.isEmpty()) {
            showInstallDialog(context);
        } else if (intentsToGoogleCardboard.size() == 1) {
            showConfigureDialog(context, (Intent) intentsToGoogleCardboard.get(0));
        } else {
            showConfigureDialog(context, settingsIntent);
        }

    }

    private static void showInstallDialog(Context context)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
        (new UiUtils.InstallSettingsDialogFragment()).show(fragmentManager,
                "InstallCardboardDialog");
    }

    private static void showConfigureDialog(Context context, Intent intent)
    {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
        UiUtils.ConfigureSettingsDialogFragment dialog = new
        UiUtils.ConfigureSettingsDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("intent", intent);
        dialog.setArguments(bundle);
        dialog.show(fragmentManager, "ConfigureCardboardDialog");
    }

    public static class ConfigureSettingsDialogFragment extends DialogFragment
    {
        private static final String TITLE = "Configure";
        private static final String MESSAGE =
            "Set up your viewer for the best experience.";
        private static final String POSITIVE_BUTTON_TEXT = "Setup";
        private static final String NEGATIVE_BUTTON_TEXT = "Cancel";
        private Intent intent;
        private final OnClickListener listener = new OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id) {
                try {
                    ConfigureSettingsDialogFragment.this.getActivity().startActivity(
                        ConfigureSettingsDialogFragment.this.intent);
                } catch (ActivityNotFoundException var4) {
                    UiUtils.showInstallDialog(ConfigureSettingsDialogFragment.this.getActivity());
                }

            }
        };

        public ConfigureSettingsDialogFragment()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        }

        public void onCreate(Bundle savedInstanceState)
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            super.onCreate(savedInstanceState);
            this.intent = (Intent) this.getArguments().getParcelable("intent");
        }

        public Dialog onCreateDialog(Bundle savedInstanceState)
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            Builder builder = new Builder(this.getActivity());
            builder.setTitle("Configure").setMessage("Set up your viewer for the best experience.").setPositiveButton("Setup",
                    this.listener).setNegativeButton("Cancel", (OnClickListener) null);
            return builder.create();
        }
    }

    public static class InstallSettingsDialogFragment extends DialogFragment
    {
        private static final String TITLE = "Configure";
        private static final String MESSAGE =
            "Get the Cardboard app in order to configure your viewer.";
        private static final String POSITIVE_BUTTON_TEXT = "Go to Play Store";
        private static final String NEGATIVE_BUTTON_TEXT = "Cancel";
        private final OnClickListener listener = new OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id) {
                try {
                    InstallSettingsDialogFragment.this.getActivity().startActivity(
                        new Intent("android.intent.action.VIEW",
                                   Uri.parse("http://google.com/cardboard/cfg?vrtoolkit_version=0.5.4")));
                } catch (ActivityNotFoundException var4) {
                    Toast.makeText(
                        InstallSettingsDialogFragment.this.getActivity().getApplicationContext(),
                        "No browser to open website.", 1).show();
                }

            }
        };

        public InstallSettingsDialogFragment()
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

        }

        public Dialog onCreateDialog(Bundle savedInstanceState)
        {
	Log.i(Thread.currentThread().getStackTrace()[2].getClassName(), "YAO ["+ Thread.currentThread().getStackTrace()[2].getMethodName() +" | "+Thread.currentThread().getStackTrace()[2].getFileName()+":"+Thread.currentThread().getStackTrace()[2].getLineNumber()+"]");

            Builder builder = new Builder(this.getActivity());
            builder.setTitle("Configure").setMessage("Get the Cardboard app in order to configure your viewer.").setPositiveButton("Go to Play Store",
                    this.listener).setNegativeButton("Cancel", (OnClickListener) null);
            return builder.create();
        }
    }
}
