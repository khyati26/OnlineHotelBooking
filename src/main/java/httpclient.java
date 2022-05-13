import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.*;

public class httpclient {
	public static void main(String[] args) throws IOException {
		// Creating a HttpClient object
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {

			// Creating a HttpGet object
//		      HttpGet httpget = new HttpGet("https://gorest.co.in/public/v2/users");
//	
//		      //Executing the Get request
//		      try(CloseableHttpResponse response1 = httpclient.execute(httpget)){
//		      System.out.println(response1.getCode() + " " + response1.getReasonPhrase());
//		      HttpEntity entity1 = response1.getEntity();
//		       System.out.println(entity1.toString());
////		        EntityUtils.consume(entity1);
//		        System.out.println(EntityUtils.toString(entity1));
//		      }catch (Exception e) {
//				System.err.println("responce catch = "+e.getMessage());
//		      }

			String idSec = "rzp_test_uePllaIWhdcfHF:bfDdcirvf4jZWEcvIaAfvmn8";
			System.out.println(Base64.getEncoder().encodeToString(idSec.getBytes()));
			String authHeadVal = "Basic cnpwX3Rlc3RfdWVQbGxhSVdoZGNmSEY6YmZEZGNpcnZmNGpaV0VjdklhQWZ2bW44";

			HttpPost httpPost = new HttpPost("https://api.razorpay.com/v1/orders");
			httpPost.addHeader("content-type", "application/json");
			httpPost.addHeader("Authorization", authHeadVal);
			String jsonstr="{\r\n"
					+ "  \"amount\": 100,\r\n"
					+ "  \"currency\": \"INR\",\r\n"
					+ "  \"receipt\": \"Receipt no. 2\",\r\n"
					+ "  \"notes\": {\r\n"
					+ "    \"notes_key_1\": \"Tea, Earl Grey, Hot\",\r\n"
					+ "    \"notes_key_2\": \"Tea, Earl Grey… decaf.\"\r\n"
					+ "  }\r\n"
					+ "}";
			 
			HttpEntity httpEntity = new StringEntity(jsonstr);
			httpPost.setEntity(httpEntity);
//			List<NameValuePair> nvps = new ArrayList<>();
//			nvps.add(new BasicNameValuePair("key_id", "rzp_test_uePllaIWhdcfHF"));
//			nvps.add(new BasicNameValuePair("key_secret", "bfDdcirvf4jZWEcvIaAfvmn8"));
//			httpPost.setEntity(new UrlEncodedFormEntity(nvps));

			try (CloseableHttpResponse response2 = httpclient.execute(httpPost)) {
				System.out.println(response2.getCode() + " " + response2.getReasonPhrase());
				HttpEntity entity2 = response2.getEntity();
				// do something useful with the response body
				// and ensure it is fully consumed
				System.out.println(EntityUtils.toString(entity2));
			} catch (Exception e) {
				System.err.println("inner2 catch = " + e.getMessage());
			}

		} catch (Exception e) {
			System.err.println("outer catch = " + e.getMessage());
		}

	}
}
