/*     */ package com.google.vr.cardboard;
/*     */
/*     */ import android.app.Activity;
/*     */ import android.app.AlertDialog;
/*     */ import android.app.Dialog;
/*     */ import android.app.DialogFragment;
/*     */ import android.app.FragmentManager;
/*     */ import android.content.ActivityNotFoundException;
/*     */ import android.content.Context;
/*     */ import android.content.DialogInterface;
/*     */ // import android.content.DialogInterface.OnClickListener;
/*     */ import android.content.Intent;
/*     */ import android.content.pm.ActivityInfo;
/*     */ import android.content.pm.PackageManager;
/*     */ import android.content.pm.ResolveInfo;
/*     */ import android.content.res.Resources;
/*     */ import android.net.Uri;
/*     */ import android.os.Bundle;
/*     */ import android.view.View;
/*     */ import android.view.Window;
/*     */ import android.widget.Toast;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ public class UiUtils
/*     */
{
    /*     */   private static final String CARDBOARD_WEBSITE =
        "http://google.com/cardboard/cfg";
    /*     */   private static final String CARDBOARD_CONFIGURE_ACTION =
        "com.google.vrtoolkit.cardboard.CONFIGURE";
    /*     */   private static final String INTENT_KEY = "intent";
    /*     */
    /*     */   public static void launchOrInstallCardboard(Context context,
            boolean confirm)
    /*     */
    {
        /*  37 */     PackageManager pm = context.getPackageManager();
        /*  38 */     Intent settingsIntent = new Intent();
        /*  39 */
        settingsIntent.setAction("com.google.vrtoolkit.cardboard.CONFIGURE");
        /*     */
        /*     */
        /*  42 */     List<ResolveInfo> resolveInfos = pm.queryIntentActivities(
                    settingsIntent, 0);
        /*  43 */     List<Intent> intentsToGoogleCardboard = new ArrayList();

        /*  44 */     for (ResolveInfo info : resolveInfos) {
            /*  45 */       String pkgName = info.activityInfo.packageName;

            /*  46 */       if (pkgName.startsWith("com.google.")) {
                /*  47 */         Intent intent = new Intent(settingsIntent);
                /*  48 */         intent.setClassName(pkgName, info.activityInfo.name);
                /*  49 */         intentsToGoogleCardboard.add(intent);
                /*     */
            }

            /*     */
        }

        /*     */

        /*  53 */     if (intentsToGoogleCardboard.isEmpty())
            /*     */     {
            /*     */
            /*  56 */       showInstallDialog(context);
            /*     */
            /*     */
            /*     */
        }
        /*     */     else
            /*     */     {
            /*     */
            /*     */
            /*  64 */       Intent intent = intentsToGoogleCardboard.size() == 1 ?
                                            (Intent)intentsToGoogleCardboard.get(0) : settingsIntent;

            /*  65 */       if (confirm)
                /*     */       {
                /*  67 */         showConfigureDialog(context, intent);
                /*     */
            }
            /*     */       else {
                /*  70 */         context.startActivity(intent);
                /*     */
            }

            /*     */
        }

        /*     */
    }
    /*     */
    /*     */   static void launchOrInstallCardboard(Context context)
    {
        /*  76 */     launchOrInstallCardboard(context, true);
        /*     */
    }
    /*     */
    /*     */   private static void showInstallDialog(Context context)
    {
        /*  80 */     FragmentManager fragmentManager = ((Activity)
                context).getFragmentManager();
        /*  81 */     new InstallSettingsDialogFragment().show(fragmentManager,
                "InstallCardboardDialog");
        /*     */
    }
    /*     */
    /*     */   private static void showConfigureDialog(Context context,
            Intent intent)
    {
        /*  85 */     FragmentManager fragmentManager = ((Activity)
                context).getFragmentManager();
        /*  86 */     DialogFragment dialog = new ConfigureSettingsDialogFragment();
        /*  87 */     Bundle bundle = new Bundle();
        /*  88 */     bundle.putParcelable("intent", intent);
        /*  89 */     dialog.setArguments(bundle);
        /*  90 */     dialog.show(fragmentManager, "ConfigureCardboardDialog");
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */   public static class ImmersiveDialogFragment
    /*     */     extends DialogFragment
    /*     */
    {
        /*     */     public void onStart()
        /*     */
        {
            /* 100 */       if (getDialog() == null) {
                /* 101 */         super.onStart();
                /* 102 */         return;
                /*     */
            }

            /*     */
            /*     */
            /*     */
            /*     */
            /*     */
            /* 109 */       getDialog().getWindow().setFlags(8, 8);
            /*     */
            /*     */
            /*     */
            /*     */
            /* 114 */       super.onStart();
            /*     */
            /*     */
            /* 117 */       getDialog()
            /* 118 */         .getWindow()
            /* 119 */         .getDecorView()
            /* 120 */         .setSystemUiVisibility(
                getActivity().getWindow().getDecorView().getSystemUiVisibility());
            /*     */
            /*     */
            /* 123 */       getDialog().getWindow().clearFlags(8);
            /*     */
        }
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   public static class InstallSettingsDialogFragment
    /*     */     extends UiUtils.ImmersiveDialogFragment
    /*     */
    {
        /* 138 */     private final DialogInterface.OnClickListener listener = new
        DialogInterface.OnClickListener()
        {
            /*     */       public void onClick(DialogInterface dialog, int id) {
                /*     */         try {
                    /* 141 */
                    UiUtils.InstallSettingsDialogFragment.this.getActivity().startActivity(
                        new Intent("android.intent.action.VIEW",
                                   Uri.parse("http://google.com/cardboard/cfg")));
                    /*     */
                }
                /*     */         catch (ActivityNotFoundException e) {
                    /* 144 */           Toast.makeText(
                        UiUtils.InstallSettingsDialogFragment.this.getActivity().getApplicationContext(),
                        Strings.getString("NO_BROWSER_TEXT"), 1).show();
                    /*     */
                }

                /*     */
            }
            /*     */
        };
        /*     */
        /*     */     public Dialog onCreateDialog(Bundle savedInstanceState)
        /*     */
        {
            /* 151 */       AlertDialog.Builder builder =
                UiUtils.createThemedAlertDialogBuilder(getActivity());
            /* 152 */       builder.setTitle(Strings.getString("DIALOG_TITLE"))
            /* 153 */         .setMessage(Strings.getString("DIALOG_MESSAGE_NO_CARDBOARD"))
            /* 154 */         .setPositiveButton(
                Strings.getString("GO_TO_PLAYSTORE_BUTTON"), this.listener)
            /* 155 */         .setNegativeButton(Strings.getString("CANCEL_BUTTON"), null);
            /* 156 */       return builder.create();
            /*     */
        }
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   public static class ConfigureSettingsDialogFragment
    /*     */     extends UiUtils.ImmersiveDialogFragment
    /*     */
    {
        /*     */     private Intent intent;
        /*     */
        /*     */
        /*     */
        /*     */
        /*     */
        /* 174 */     private final DialogInterface.OnClickListener listener = new
        DialogInterface.OnClickListener()
        {
            /*     */       public void onClick(DialogInterface dialog, int id) {
                /*     */         try {
                    /* 177 */
                    UiUtils.ConfigureSettingsDialogFragment.this.getActivity().startActivity(
                        UiUtils.ConfigureSettingsDialogFragment.this.intent);
                    /*     */
                }
                /*     */         catch (ActivityNotFoundException e)
                    /*     */         {
                    /* 181 */           UiUtils.showInstallDialog(
                        UiUtils.ConfigureSettingsDialogFragment.this.getActivity());
                    /*     */
                }

                /*     */
            }
            /*     */
        };
        /*     */
        /*     */     public void onCreate(Bundle savedInstanceState)
        /*     */
        {
            /* 188 */       super.onCreate(savedInstanceState);
            /* 189 */       this.intent = ((Intent)getArguments().getParcelable("intent"));
            /*     */
        }
        /*     */
        /*     */     public Dialog onCreateDialog(Bundle savedInstanceState)
        /*     */
        {
            /* 194 */       AlertDialog.Builder builder =
                UiUtils.createThemedAlertDialogBuilder(getActivity());
            /* 195 */       builder.setTitle(Strings.getString("DIALOG_TITLE"))
            /* 196 */         .setMessage(Strings.getString("DIALOG_MESSAGE_SETUP"))
            /* 197 */         .setPositiveButton(Strings.getString("SETUP_BUTTON"),
                                                 this.listener)
            /* 198 */         .setNegativeButton(Strings.getString("CANCEL_BUTTON"), null);
            /* 199 */       return builder.create();
            /*     */
        }
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   private static AlertDialog.Builder createThemedAlertDialogBuilder(
        Context context)
    /*     */
    {
        /* 215 */     int resId =
            context.getResources().getIdentifier("CardboardDialogTheme", "style",
                    context.getPackageName());
        /* 216 */     return new AlertDialog.Builder(context, resId);
        /*     */
    }
    /*     */
}


/* Location:              /home/jason_yao/cardboard/cardboard-java/CardboardSample/libs/cardboard.jar!/com/google/vr/cardboard/UiUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */