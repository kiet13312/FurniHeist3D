package com.kiet13312.furniheist3d;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Locale;

public class MainActivity extends Activity {
    private static final String LOCAL_HOST = "furniheist.local";
    private WebView game;

    @SuppressLint({"SetJavaScriptEnabled", "WebViewApiAvailability"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        enterImmersiveMode();

        game = new WebView(this);
        game.setBackgroundColor(0xFF79B86F);
        game.setOverScrollMode(View.OVER_SCROLL_NEVER);
        game.setVerticalScrollBarEnabled(false);
        game.setHorizontalScrollBarEnabled(false);
        game.setWebViewClient(new WebViewClient() {
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                WebResourceResponse local = serveLocalAsset(request.getUrl());
                return local != null ? local : super.shouldInterceptRequest(view, request);
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                WebResourceResponse local = serveLocalAsset(Uri.parse(url));
                return local != null ? local : super.shouldInterceptRequest(view, url);
            }
        });
        game.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage message) {
                Log.d("FurniHeist3D", message.message() + " (" + message.sourceId() + ":" + message.lineNumber() + ")");
                return true;
            }
        });

        WebSettings settings = game.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        settings.setMediaPlaybackRequiresUserGesture(true);

        setContentView(game);
        // All game assets are served under one local HTTPS origin, so ES modules and GLB fetches
        // work in WebView without internet access or file:// module restrictions.
        game.loadUrl("https://" + LOCAL_HOST + "/index.html");
    }

    private WebResourceResponse serveLocalAsset(Uri uri) {
        if (uri == null || !LOCAL_HOST.equalsIgnoreCase(uri.getHost())) return null;

        String path = uri.getPath();
        if (path == null || path.isEmpty() || "/".equals(path)) path = "/index.html";
        String assetPath = path.startsWith("/") ? path.substring(1) : path;
        if (assetPath.contains("..") || assetPath.contains("\\") || assetPath.startsWith(".")) {
            return errorResponse(403, "Forbidden");
        }

        try {
            InputStream stream = getAssets().open(assetPath);
            String lower = assetPath.toLowerCase(Locale.ROOT);
            String mime;
            String encoding = null;
            if (lower.endsWith(".html")) { mime = "text/html"; encoding = "UTF-8"; }
            else if (lower.endsWith(".js") || lower.endsWith(".mjs")) { mime = "text/javascript"; encoding = "UTF-8"; }
            else if (lower.endsWith(".css")) { mime = "text/css"; encoding = "UTF-8"; }
            else if (lower.endsWith(".json")) { mime = "application/json"; encoding = "UTF-8"; }
            else if (lower.endsWith(".txt")) { mime = "text/plain"; encoding = "UTF-8"; }
            else if (lower.endsWith(".glb")) { mime = "model/gltf-binary"; }
            else if (lower.endsWith(".gltf")) { mime = "model/gltf+json"; encoding = "UTF-8"; }
            else if (lower.endsWith(".png")) { mime = "image/png"; }
            else if (lower.endsWith(".jpg") || lower.endsWith(".jpeg")) { mime = "image/jpeg"; }
            else if (lower.endsWith(".webp")) { mime = "image/webp"; }
            else if (lower.endsWith(".svg")) { mime = "image/svg+xml"; encoding = "UTF-8"; }
            else { mime = "application/octet-stream"; }
            return new WebResourceResponse(mime, encoding, stream);
        } catch (IOException ex) {
            Log.e("FurniHeist3D", "Missing local game asset: " + assetPath, ex);
            return errorResponse(404, "Missing game asset: " + assetPath);
        }
    }

    private WebResourceResponse errorResponse(int code, String message) {
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        return new WebResourceResponse(
            "text/plain", "UTF-8", code,
            code == 403 ? "Forbidden" : "Not Found",
            Collections.emptyMap(),
            new ByteArrayInputStream(bytes)
        );
    }

    private void enterImmersiveMode() {
        getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) enterImmersiveMode();
    }

    @Override
    protected void onDestroy() {
        if (game != null) {
            game.loadUrl("about:blank");
            game.stopLoading();
            game.destroy();
            game = null;
        }
        super.onDestroy();
    }
}
