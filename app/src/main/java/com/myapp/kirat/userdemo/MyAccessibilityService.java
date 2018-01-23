package com.myapp.kirat.userdemo;

import android.accessibilityservice.AccessibilityService;
import android.os.Bundle;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * Created by akshay on 14/1/18.
 */





public class MyAccessibilityService extends AccessibilityService {

    private String f1831l = null;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyAccessibilityService", "onCreate");
    }

    @Override
    public void onAccessibilityEvent(final AccessibilityEvent event) {
        Log.d("MyAccessibilityService", "onAccessibilityEvent");
        AccessibilityNodeInfo rootInActiveWindow = getRootInActiveWindow();
        //Inspect app elements if ready
        if (rootInActiveWindow != null) {

            Log.d("TESTING", rootInActiveWindow.toString());

            AccessibilityNodeInfo nodeInfo = event.getSource();
            Log.i("Above m2637d", "ACC::onAccessibilityEvent: nodeInfo=" + nodeInfo);
            if (nodeInfo == null) {
                return;
            }

            List<AccessibilityNodeInfo> list = nodeInfo
                    .findAccessibilityNodeInfosByViewId("com.whatsapp:id/send");

            m2637d(event);



        }
    }

    // click on send button
    public boolean m2637d(AccessibilityEvent accessibilityEvent) {
        AccessibilityNodeInfo parent = accessibilityEvent.getSource().getParent();
        if (parent != null) {
            int i;
            if (!(parent == null || parent.getClassName() == null)) {
                Log.d("ServiceCustomADD", parent.getClassName().toString());
                if (parent.getClassName() != null && parent.getContentDescription() != null && parent.getClassName().equals("android.widget.ImageButton") && parent.getContentDescription().equals("Send")) {
                    parent.performAction(16);
                    Log.d("ServiceCustomADD", "Clicking.....");
                }
            }
            AccessibilityNodeInfo parent2 = parent.getParent();
            if (!(parent2 == null || parent2.getClassName() == null)) {
                Log.d("ServiceCustomADD", parent2.getClassName().toString());
                if (parent2.getClassName() != null && parent2.getContentDescription() != null && parent2.getClassName().equals("android.widget.ImageButton") && parent2.getContentDescription().equals("Send")) {
                    parent2.performAction(16);
                    Log.d("ServiceCustomADD", "Clicking.....");
                }
                for (i = 0; i < parent2.getChildCount(); i++) {
                    AccessibilityNodeInfo child = parent2.getChild(i);
                    if (!(child == null || child.getClassName() == null)) {
                        Log.d("ServiceCustom2W", child.getClassName().toString());
                        if (child.getClassName() == null || child.getContentDescription() == null) {
                            for (int i2 = 0; i2 < child.getChildCount(); i2++) {
                                AccessibilityNodeInfo child2 = child.getChild(i2);
                                if (!(child2 == null || child2.getClassName() == null)) {
                                    Log.d("ServiceCustom3W", child2.getClassName().toString());
                                    if (!(child2.getClassName() == null || child2.getContentDescription() == null)) {
                                        Log.d("ServiceCustom3W", child2.getClassName().toString() + " " + child2.getContentDescription().toString());
                                        if (child2.getClassName().equals("android.widget.ImageButton") && child2.getContentDescription().equals("Send")) {
                                            child2.performAction(16);
                                            Log.d("ServiceCustom3W", "Clicking.....");
                                            if (!child.getChild(i2 - 1).getClassName().equals("android.widget.HorizontalScrollView")) {
//                                                this.f1825f = false;
                                                return false;
                                            }
                                        }
                                    }
                                }
                            }
                            continue;
                        } else {
                            Log.d("ServiceCustom2W", child.getClassName().toString() + " " + child.getContentDescription().toString());
                            if (child.getClassName().equals("android.widget.ImageButton") && child.getContentDescription().equals("Send")) {
                                child.performAction(16);
                                Log.d("ServiceCustom2W", "Clicking.....");
                                if (!parent2.getChild(i - 1).getClassName().equals("android.widget.HorizontalScrollView")) {
//                                    this.f1825f = false;
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
            for (i = 0; i < parent.getChildCount(); i++) {
                AccessibilityNodeInfo child3 = parent.getChild(i);
                if (!(child3 == null || child3.getClassName() == null)) {
                    Log.d("ServiceCustomW", child3.getClassName().toString());
                    if (!(child3.getClassName() == null || child3.getContentDescription() == null)) {
                        Log.d("ServiceCustomW", child3.getClassName().toString() + " " + child3.getContentDescription().toString());
                        if (child3.getClassName().equals("android.widget.ImageButton") && child3.getContentDescription().equals("Send")) {
                            child3.performAction(16);
                            Log.d("ServiceCustomW", "Clicking.....");
                            if (parent.getChild(i - 1).getClassName().equals("android.widget.HorizontalScrollView")) {
                                Log.d("ServiceCustomW", "SHIT!!!!!!!!!!!!!!!");
                            } else {
//                                this.f1825f = false;
                                return false;
                            }
                        }
                    }
                }
            }
//            JsonService jsonService = new JsonService();
//            jsonService.phoneData();
        } else {
            Log.d("ServiceCustomW", "NULL SOURCE");
        }
        return true;
    }

    // check we are in  whatsapp coversation screen or not
    public boolean m2636c(AccessibilityEvent accessibilityEvent) {
        AccessibilityNodeInfo source = accessibilityEvent.getSource();
        if (!(source == null || source.getClassName() == null)) {
            Log.d("ServiceCustomC", source.getClassName().toString());
            AccessibilityNodeInfo parent = !source.getClassName().equals("android.widget.ListView") ? source.getParent() : source;
            if (!(parent == null || parent.getClassName() == null)) {
                Log.d("ServiceCustomCP", parent.getClassName().toString());
                if (parent.getClassName().equals("android.widget.ListView")) {
                    for (int i = 0; i < parent.getChildCount(); i++) {
                        AccessibilityNodeInfo child = parent.getChild(i);
                        if (!(child == null || child.getClassName() == null)) {
                            Log.d("ServiceCustom4444 " + i, (String) child.getClassName());
                            if (child.getClassName().equals("android.widget.RelativeLayout")) {
                                for (int i2 = 0; i2 < child.getChildCount(); i2++) {
                                    AccessibilityNodeInfo child2 = child.getChild(i2);
                                    if (!(child2 == null || child2.getClassName() == null)) {
                                        Log.d("ServiceCustomCL", child2.getClassName().toString());
                                        if (child2.getClassName().toString().equals("android.widget.TextView") && child2.getText() != null) {
                                            Log.d("ServiceCustomCLV", child2.getText().toString());
                                            if (child2.getText().toString().equalsIgnoreCase(this.f1831l)) {
                                                Log.d("ServiceCustomCLV", "YES");
                                                Log.d("ServiceCustomq ", "clicking...");
                                                Boolean valueOf = Boolean.valueOf(child.performAction(16));
                                                Log.d("ServiceCustomCLV Bool", valueOf.toString());
                                                if (valueOf.booleanValue()) {
                                                    return false;
                                                }
                                            } else {
                                                continue;
                                            }
                                        }
                                    }
                                }
                                continue;
                            } else {
                                continue;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }



    @Override
    public void onInterrupt() {
    }
}