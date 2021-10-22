package com.base.clean.ui.common

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import com.base.clean.R
import com.base.clean.base.BaseFragment
import com.base.clean.common.ProjectSharedPreferences
import com.base.clean.databinding.FragmentWebViewBinding
import com.base.clean.util.ConnectionManager

class WebViewFragment : BaseFragment() {

    lateinit var binding: FragmentWebViewBinding

    private var title: String = ""
    private var url: String = ""

    lateinit var prefs: ProjectSharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWebViewBinding.inflate(inflater, container, false)

        binding.webview.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                showProgressDialog(R.string.app_name, "webview-progress")
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                if (request != null && shouldOverrideUrlLoading(request.url.toString()))
                    return true
                return super.shouldOverrideUrlLoading(view, request)
            }

            override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
                if (shouldOverrideUrlLoading(url))
                    return true
                return super.shouldOverrideUrlLoading(view, url)
            }

            fun shouldOverrideUrlLoading(url: String): Boolean {
                if (url.startsWith("")) {
                    return true
                }
                if (url.contains("")) {
                    activity?.finish()
                    return true
                }
                if (url.contains("")) {
                    return true
                }
                if (url == "") {
                    return true
                }
                return false
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                hideProgressDialog("webview-progress")
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                hideProgressDialog("webview-progress")
            }

            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                super.onReceivedSslError(view, handler, error)
                hideProgressDialog("webview-progress")
            }
        }

        if (!ConnectionManager.isConnected(requireContext())) {
            //showMessageDialog(getString(R.string.dialog_no_internet_header), R.string.dialog_no_internet_content,)
        } else {
            val headerMap = HashMap<String, String>()
            headerMap.put("Authorization", "Bearer $accessToken")
            binding.webview.loadUrl(url, headerMap)
            //binding.webview.loadUrl(url)
        }

        return binding.root
    }

    val accessToken: String
        get() = prefs.getAccessToken()

    override fun getTitle(): String {
        return title
    }

    override fun onResume() {
        super.onResume()
        binding.webview.onResume()
        val activity = activity as AppCompatActivity
        if (activity.supportActionBar != null) {
            activity.supportActionBar?.title = title
        }
    }

    override fun onPause() {
        super.onPause()
        binding.webview.onPause()
    }
}