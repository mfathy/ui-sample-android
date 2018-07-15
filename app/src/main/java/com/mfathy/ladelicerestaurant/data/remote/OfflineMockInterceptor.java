package com.mfathy.ladelicerestaurant.data.remote;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Mohammed Fathy on 17/03/2018.
 * dev.mfathy@gmail.com
 * <p>
 * {@link OfflineMockInterceptor} class is a remote server local mock for API calls.
 * This class is used when there is not {@link ApiEndpoints} returns or return null.
 * Use this class to return different responses for API calls.
 */

public class OfflineMockInterceptor implements Interceptor {

    private static String MOCK_SERVER_FILE = "recipes.json";    //  Default file contains offline response.
    private Context mContext;
    private String mContentType = "application/json";

    private SharedPreferences mPreferences;

    OfflineMockInterceptor(Context context) {
        mContext = context;

        mPreferences = mContext.getSharedPreferences(MOCK_SERVER_FILE, Context.MODE_PRIVATE);
        String baseResponse = mPreferences.getString(MOCK_SERVER_FILE, null);
        if (baseResponse == null) {
            try {
                StringBuilder responseStringBuilder = readBaseResponse(MOCK_SERVER_FILE);
                mPreferences.edit().putString(MOCK_SERVER_FILE, responseStringBuilder.toString()).apply();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Set content type for header
     *
     * @param contentType Content type
     * @return FakeInterceptor
     */
    public OfflineMockInterceptor setContentType(String contentType) {
        mContentType = contentType;
        return this;
    }

    /**
     * This method intercept request chain to route each call to its correct response.
     * @param chain of requests.
     * @return Response of the request.
     * @throws IOException when there is no response.
     */
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Response response;
        String url = chain.request().url().encodedPath().toLowerCase();

        String baseResponse;
        if (url.equals("/recipes")) {
            baseResponse = mPreferences.getString(MOCK_SERVER_FILE, "[]");
            baseResponse = getCleanJsonString(baseResponse);
            response = new Response.Builder()
                    .code(200)
                    .message(baseResponse)
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_0)
                    .body(ResponseBody.create(MediaType.parse(mContentType), baseResponse.getBytes()))
                    .addHeader("content-type", mContentType)
                    .build();
        } else {
            response = chain.proceed(chain.request());
        }
        return response;
    }

    /**
     * This method is a file reader method to return a String of file's content.
     * @param fileName to read.
     * @return StringBuilder object of the file content.
     * @throws IOException when there is IO error while reading the file.
     */
    @NonNull
    private StringBuilder readBaseResponse(String fileName) throws IOException {
        InputStream is = mContext.getAssets().open(fileName);
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        StringBuilder responseStringBuilder = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            responseStringBuilder.append(line).append('\n');
        }
        return responseStringBuilder;
    }

    /**
     * This method cleans JSON String from un-identified characters.
     * @param target string to be cleaned.
     * @return clean string.
     */
    private String getCleanJsonString(String target) {
        String result = null;
        try {
            result = URLDecoder.decode(target, "UTF-8"); // remove url decoding
            result = result.replaceAll("^\"|\"$", ""); //remove all double quotes
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

