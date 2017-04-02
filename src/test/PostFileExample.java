package test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostFileExample {
	public static void main(String[] args) {
		try {
			post("http://192.168.0.103:8080/api/mobileupload");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void post(String url) throws IOException{
        File file = new File("C:/Users/CDC/Desktop/org_20151118214035_479.jpg");
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        File file2 = new File("C:/Users/CDC/Desktop/back.png");
        RequestBody fileBody2 = RequestBody.create(MediaType.parse("application/octet-stream"), file2);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM) 
                .addFormDataPart("upload", "1.jpg", fileBody)
                .addFormDataPart("upload", "2.png", fileBody2)
                .addFormDataPart("username", "dcdc")
                .addFormDataPart("password", "cdffgg")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        final okhttp3.OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient  = httpBuilder
                //设置超时
                .connectTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(150, TimeUnit.SECONDS)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
            }

            @Override
            public void onFailure(Call arg0, IOException e) {
                // TODO Auto-generated method stub
                System.out.println(e.toString());

            }

        });
    }
}
